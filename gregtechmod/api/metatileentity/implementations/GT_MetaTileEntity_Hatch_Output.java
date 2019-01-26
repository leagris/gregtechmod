package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Hatch_Output extends GT_MetaTileEntity_BasicTank {

   public byte mMode = 0;


   public GT_MetaTileEntity_Hatch_Output(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Hatch_Output() {}

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 154, GregTech_API.gregtechmod);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Hatch_Output();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74774_a("mMode", this.mMode);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mMode = aNBT.func_74771_c("mMode");
   }

   public boolean doesFillContainers() {
      return this.getBaseMetaTileEntity().isAllowedToWork();
   }

   public boolean doesEmptyContainers() {
      return this.getBaseMetaTileEntity().isAllowedToWork();
   }

   public boolean canTankBeFilled() {
      return this.getBaseMetaTileEntity().isAllowedToWork();
   }

   public boolean canTankBeEmptied() {
      return this.getBaseMetaTileEntity().isAllowedToWork();
   }

   public boolean displaysItemStack() {
      return true;
   }

   public boolean displaysStackSize() {
      return false;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?(aSide == 0?38:(aSide == 1?79:36)):(aSide == 0?32:(aSide == 1?29:40));
   }

   public String getDescription() {
      return "Use Screwdriver to specify Output Type";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing() && aIndex == 1;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing() && aIndex == 0;
   }

   public int getCapacity() {
      return 16000;
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aCoverID != -1 && aCoverID != -2;
   }

   public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(this.getBaseMetaTileEntity().getCoverBehaviorAtSide(aSide).isGUIClickable(aSide, this.getBaseMetaTileEntity().getCoverIDAtSide(aSide), this.getBaseMetaTileEntity().getCoverDataAtSide(aSide), this.getBaseMetaTileEntity())) {
         this.mMode = (byte)((this.mMode + 1) % 8);
         switch(this.mMode) {
         case 0:
            GT_Utility.sendChatToPlayer(aPlayer, "Outputs Liquids, Steam and Items");
            break;
         case 1:
            GT_Utility.sendChatToPlayer(aPlayer, "Outputs Steam and Items");
            break;
         case 2:
            GT_Utility.sendChatToPlayer(aPlayer, "Outputs Steam and Liquids");
            break;
         case 3:
            GT_Utility.sendChatToPlayer(aPlayer, "Outputs Steam");
            break;
         case 4:
            GT_Utility.sendChatToPlayer(aPlayer, "Outputs Liquids and Items");
            break;
         case 5:
            GT_Utility.sendChatToPlayer(aPlayer, "Outputs only Items");
            break;
         case 6:
            GT_Utility.sendChatToPlayer(aPlayer, "Outputs only Liquids");
            break;
         case 7:
            GT_Utility.sendChatToPlayer(aPlayer, "Outputs nothing");
         }

      }
   }

   public boolean outputsSteam() {
      return this.mMode < 4;
   }

   public boolean outputsLiquids() {
      return this.mMode % 2 == 0;
   }

   public boolean outputsItems() {
      return this.mMode % 4 < 2;
   }

   public int getTankPressure() {
      return 100;
   }
}
