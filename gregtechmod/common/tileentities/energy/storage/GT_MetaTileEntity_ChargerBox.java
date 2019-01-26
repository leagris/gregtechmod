package gregtechmod.common.tileentities.energy.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ChargerBox extends MetaTileEntity {

   public byte mTier = 0;
   public boolean mCharge = false;
   public boolean mDecharge = false;


   public GT_MetaTileEntity_ChargerBox(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_ChargerBox() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isBatteryUpgradable() {
      return false;
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

   public boolean isTransformingLowEnergy() {
      return false;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing();
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public int maxEUInput() {
      return 32;
   }

   public int maxEUOutput() {
      return 32;
   }

   public int maxEUStore() {
      return this.getBaseMetaTileEntity().getOutputVoltage() * this.getBaseMetaTileEntity().getOutputAmperage() * 16 + 10000;
   }

   public boolean isValidSlot(int aIndex) {
      return true;
   }

   public int getInvSize() {
      return 1;
   }

   public int func_70297_j_() {
      return 1;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 96);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ChargerBox();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74774_a("mTier", this.mTier);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mTier = aNBT.func_74771_c("mTier");
   }

   public int rechargerSlotStartIndex() {
      return 0;
   }

   public int rechargerSlotCount() {
      return this.mCharge && this.getBaseMetaTileEntity().getStoredEU() * 2 > this.getBaseMetaTileEntity().getEUCapacity() / 3?this.getInvSize():0;
   }

   public int dechargerSlotStartIndex() {
      return 0;
   }

   public int dechargerSlotCount() {
      return this.mDecharge && this.getBaseMetaTileEntity().getStoredEU() < this.getBaseMetaTileEntity().getEUCapacity() / 3?this.getInvSize():0;
   }

   public int getDechargeCyles() {
      return 1;
   }

   public int getChargeCyles() {
      return 1;
   }

   public void onPreTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         this.mTier = this.getBaseMetaTileEntity().getTransformerUpgradeCount();
         this.mCharge = this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().getStoredEU() * 2 > this.getBaseMetaTileEntity().getEUCapacity() / 3;
         this.mDecharge = this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().getStoredEU() < this.getBaseMetaTileEntity().getEUCapacity() / 3;
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

   public String getDescription() {
      return "For storing Energy using Batteries and similar";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      boolean var10000;
      if(GT_ModHandler.isElectricItem(this.mInventory[aIndex])) {
         label27: {
            if(this.getBaseMetaTileEntity().isAllowedToWork()) {
               if(this.getBaseMetaTileEntity().getStoredEU() < this.getBaseMetaTileEntity().getEUCapacity() / 3) {
                  if(!GT_ModHandler.canUseElectricItem(this.mInventory[aIndex], 1)) {
                     break label27;
                  }
               } else if(GT_ModHandler.canUseElectricItem(this.mInventory[aIndex], GT_ModHandler.getMaxElectricCharge(this.mInventory[aIndex]))) {
                  break label27;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return GT_ModHandler.isElectricItem(aStack);
   }
}
