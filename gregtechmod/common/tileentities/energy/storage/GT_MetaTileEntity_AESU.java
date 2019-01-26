package gregtechmod.common.tileentities.energy.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_AESU extends MetaTileEntity {

   public int mOutput = 0;


   public GT_MetaTileEntity_AESU(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_AESU() {}

   public boolean isBatteryUpgradable() {
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

   public boolean isTransformingLowEnergy() {
      return false;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing();
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public boolean isTeleporterCompatible() {
      return true;
   }

   public int maxEUInput() {
      return 8192;
   }

   public int maxEUStore() {
      return 100000000;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 2;
   }

   public int getInvSize() {
      return 3;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 150);
   }

   public String getSpecialVoltageToolTip() {
      return "Max EU/p OUT: 0 - 8192 (Adjustable)";
   }

   public int maxEUOutput() {
      return this.getBaseMetaTileEntity().isAllowedToWork()?(this.getBaseMetaTileEntity().getWorkDataValue() == 0?this.mOutput:Math.max(0, Math.min(8192, this.mOutput * this.getBaseMetaTileEntity().getWorkDataValue()))):0;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_AESU();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mOutput", this.mOutput);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mOutput = aNBT.func_74762_e("mOutput");
   }

   public int getInputTier() {
      return 5;
   }

   public int getOutputTier() {
      return 5;
   }

   public int rechargerSlotStartIndex() {
      return 0;
   }

   public int rechargerSlotCount() {
      return 1;
   }

   public int dechargerSlotStartIndex() {
      return 1;
   }

   public int dechargerSlotCount() {
      return 1;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?311:16;
   }

   public String getDescription() {
      return "Adjustable Energy Storage Unit";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex < 2;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex < 2;
   }
}
