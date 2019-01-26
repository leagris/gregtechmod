package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Hatch_Input extends GT_MetaTileEntity_BasicTank {

   public GT_MetaTileEntity_Hatch_Input(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Hatch_Input() {}

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
      this.getBaseMetaTileEntity().openGUI(aPlayer, 153, GregTech_API.gregtechmod);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Hatch_Input();
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
      return "For inputting Materials into Multiblocks";
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

   public int getTankPressure() {
      return -100;
   }
}
