package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

public class GT_MetaTileEntity_MachineBox extends GT_MetaTileEntity_BasicTank {

   public byte mTier = 0;
   public static final String DESCRIPTIONTEXT = EnumChatFormatting.RESET + "You just need " + EnumChatFormatting.DARK_PURPLE + "I" + EnumChatFormatting.LIGHT_PURPLE + "m" + EnumChatFormatting.DARK_RED + "a" + EnumChatFormatting.RED + "g" + EnumChatFormatting.YELLOW + "i" + EnumChatFormatting.GREEN + "n" + EnumChatFormatting.AQUA + "a" + EnumChatFormatting.DARK_AQUA + "t" + EnumChatFormatting.BLUE + "i" + EnumChatFormatting.DARK_BLUE + "o" + EnumChatFormatting.DARK_PURPLE + "n" + EnumChatFormatting.RESET + " to use this.";


   public GT_MetaTileEntity_MachineBox(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_MachineBox() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing();
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public int getMinimumStoredEU() {
      return 1000;
   }

   public int maxEUInput() {
      return 32;
   }

   public int maxEUOutput() {
      return 32;
   }

   public int maxEUStore() {
      return 10000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 1;
   }

   public int getInvSize() {
      return 2;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_MachineBox();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74774_a("mTier", this.mTier);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mTier = aNBT.func_74771_c("mTier");
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      return false;
   }

   public void onPreTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         this.mTier = this.getBaseMetaTileEntity().getTransformerUpgradeCount();
      }

   }

   public void onValueUpdate(byte aValue) {
      this.mTier = aValue;
   }

   public byte getUpdateData() {
      return this.mTier;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == aFacing) {
         if(this.mTier <= 0) {
            return 282;
         }

         if(this.mTier == 1) {
            return 310;
         }

         if(this.mTier == 2) {
            return 17;
         }

         if(this.mTier == 3) {
            return 18;
         }

         if(this.mTier >= 4) {
            return 311;
         }
      }

      return this.mTier <= 0?(aSide == 0?32:(aSide == 1?29:40)):(this.mTier == 1?9:16);
   }

   public boolean doesFillContainers() {
      return false;
   }

   public boolean doesEmptyContainers() {
      return false;
   }

   public boolean canTankBeFilled() {
      return true;
   }

   public boolean canTankBeEmptied() {
      return true;
   }

   public boolean displaysItemStack() {
      return false;
   }

   public boolean displaysStackSize() {
      return false;
   }

   public String getDescription() {
      return DESCRIPTIONTEXT;
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0;
   }

   public int getCapacity() {
      return 1000;
   }

   public int getTankPressure() {
      return 0;
   }

}
