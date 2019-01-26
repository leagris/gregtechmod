package gregtechmod.api.metatileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public abstract class MetaTileEntity implements IMetaTileEntity {

   public static volatile int VERSION = 408;
   public String mName;
   public boolean doTickProfilingInThisTick = true;
   private IGregTechTileEntity mBaseMetaTileEntity;
   public ItemStack[] mInventory = new ItemStack[this.getInvSize()];


   public byte getTileEntityBaseType() {
      return (byte)0;
   }

   public IGregTechTileEntity getBaseMetaTileEntity() {
      return this.mBaseMetaTileEntity;
   }

   public ItemStack getStackForm(long aAmount) {
      return new ItemStack(GregTech_API.sBlockList[1], (int)aAmount, this.getBaseMetaTileEntity().getMetaTileID());
   }

   public MetaTileEntity(int aID, String aBasicName, String aRegionalName) {
      if(!GregTech_API.sPostloadStarted && GregTech_API.sPreloadStarted) {
         if(GregTech_API.mMetaTileList[aID] == null) {
            GregTech_API.mMetaTileList[aID] = this;
            this.mName = aBasicName.replaceAll(" ", "_");
            this.setBaseMetaTileEntity(GregTech_API.constructBaseMetaTileEntity());
            this.getBaseMetaTileEntity().setMetaTileID((short)aID);
            GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Machine." + this.mName + ".name", aRegionalName);
         } else {
            throw new IllegalArgumentException("MetaMachine-Slot Nr. " + aID + " is already occupied!");
         }
      } else {
         throw new IllegalAccessError("This Constructor has to be called in the load Phase");
      }
   }

   public void setBaseMetaTileEntity(IGregTechTileEntity aBaseMetaTileEntity) {
      if(this.mBaseMetaTileEntity != null && aBaseMetaTileEntity == null) {
         this.mBaseMetaTileEntity.getMetaTileEntity().inValidate();
         this.mBaseMetaTileEntity.setMetaTileEntity((IMetaTileEntity)null);
      }

      this.mBaseMetaTileEntity = aBaseMetaTileEntity;
      if(this.mBaseMetaTileEntity != null) {
         this.mBaseMetaTileEntity.setMetaTileEntity(this);
      }

   }

   public MetaTileEntity() {}

   public void onServerStart() {}

   public void onServerStop() {}

   public void onConfigLoad(GT_Config aConfig) {}

   public void setItemNBT(NBTTagCompound aNBT) {}

   public Icon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return null;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister aBlockIconRegister) {}

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return true;
   }

   public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {}

   public void onExplosion() {}

   public void onFirstTick() {}

   public void onPreTick() {}

   public void onPostTick() {}

   public void inValidate() {}

   public void onRemoval() {}

   public void onFirstServerTick() {}

   public void initDefaultModes(NBTTagCompound aNBT) {}

   public void onOpenGUI() {}

   public void onCloseGUI() {}

   public void onRightclick(EntityPlayer aPlayer) {}

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      this.onRightclick(aPlayer);
      return true;
   }

   public void onLeftclick(EntityPlayer aPlayer) {}

   public void onValueUpdate(byte aValue) {}

   public byte getUpdateData() {
      return (byte)0;
   }

   public void doSound(byte aIndex, double aX, double aY, double aZ) {}

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {}

   public void stopSoundLoop(byte aValue, double aX, double aY, double aZ) {}

   public final void sendSound(byte aIndex) {
      if(!this.getBaseMetaTileEntity().hasMufflerUpgrade()) {
         this.getBaseMetaTileEntity().sendBlockEvent((byte)4, aIndex);
      }

   }

   public final void sendLoopStart(byte aIndex) {
      if(!this.getBaseMetaTileEntity().hasMufflerUpgrade()) {
         this.getBaseMetaTileEntity().sendBlockEvent((byte)5, aIndex);
      }

   }

   public final void sendLoopEnd(byte aIndex) {
      if(!this.getBaseMetaTileEntity().hasMufflerUpgrade()) {
         this.getBaseMetaTileEntity().sendBlockEvent((byte)6, aIndex);
      }

   }

   public boolean isElectric() {
      return true;
   }

   public boolean isPneumatic() {
      return false;
   }

   public boolean isSteampowered() {
      return false;
   }

   public boolean isEnetOutput() {
      return false;
   }

   public boolean isEnetInput() {
      return false;
   }

   public int maxEUStore() {
      return 0;
   }

   public int maxEUInput() {
      return 0;
   }

   public int maxEUOutput() {
      return 0;
   }

   public int maxEUPulses() {
      return 1;
   }

   public boolean isOutputFacing(byte aSide) {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return false;
   }

   public boolean isTransformingLowEnergy() {
      return true;
   }

   public boolean isFacingValid(byte aFacing) {
      return false;
   }

   public boolean isWrenchable() {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return true;
   }

   public boolean setStackToZeroInsteadOfNull(int aIndex) {
      return false;
   }

   public void setEUVar(int aEU) {
      ((BaseMetaTileEntity)this.mBaseMetaTileEntity).mStoredEnergy = aEU;
   }

   public int getEUVar() {
      return ((BaseMetaTileEntity)this.mBaseMetaTileEntity).mStoredEnergy;
   }

   public void setMJVar(int aMJ) {
      ((BaseMetaTileEntity)this.mBaseMetaTileEntity).mStoredMJ = aMJ;
   }

   public int getMJVar() {
      return ((BaseMetaTileEntity)this.mBaseMetaTileEntity).mStoredMJ;
   }

   public void setSteamVar(int aSteam) {
      ((BaseMetaTileEntity)this.mBaseMetaTileEntity).mStoredSteam = aSteam;
   }

   public int getSteamVar() {
      return ((BaseMetaTileEntity)this.mBaseMetaTileEntity).mStoredSteam;
   }

   public int maxSteamStore() {
      return 0;
   }

   public int maxMJStore() {
      return 0;
   }

   public int getMinimumStoredEU() {
      return 512;
   }

   public int getInputTier() {
      return GT_Utility.getTier(this.getBaseMetaTileEntity().getInputVoltage());
   }

   public int getOutputTier() {
      return GT_Utility.getTier(this.getBaseMetaTileEntity().getOutputVoltage());
   }

   public int getDechargeCyles() {
      return this.getInputTier();
   }

   public int getChargeCyles() {
      return this.getOutputTier();
   }

   public int rechargerSlotStartIndex() {
      return 0;
   }

   public int rechargerSlotCount() {
      return 0;
   }

   public int dechargerSlotStartIndex() {
      return 0;
   }

   public int dechargerSlotCount() {
      return 0;
   }

   public boolean ownerControl() {
      return false;
   }

   public boolean unbreakable() {
      return false;
   }

   public ArrayList<String> getSpecialDebugInfo(EntityPlayer aPlayer, int aLogLevel, ArrayList<String> aList) {
      return aList;
   }

   public boolean isLiquidInput(byte aSide) {
      return true;
   }

   public boolean isLiquidOutput(byte aSide) {
      return true;
   }

   public FluidStack getFluid() {
      return null;
   }

   public int fill(FluidStack resource, boolean doFill) {
      return 0;
   }

   public FluidStack drain(int maxDrain, boolean doDrain) {
      return null;
   }

   public int getTankPressure() {
      return 0;
   }

   public int getCapacity() {
      return 0;
   }

   public void onMachineBlockUpdate() {}

   public void receiveClientEvent(byte aEventID, byte aValue) {}

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isOverclockerUpgradable() {
      return false;
   }

   public boolean isTransformerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return false;
   }

   public int getProgresstime() {
      return 0;
   }

   public int maxProgresstime() {
      return 0;
   }

   public int increaseProgress(int aProgress) {
      return 0;
   }

   public boolean hasSidedRedstoneOutputBehavior() {
      return false;
   }

   public void onFacingChange() {}

   public boolean isTeleporterCompatible() {
      return this.isEnetOutput() && this.getBaseMetaTileEntity().getOutputVoltage() >= 128 && this.getBaseMetaTileEntity().getUniversalEnergyCapacity() >= 500000;
   }

   public byte getComparatorValue(byte aSide) {
      return (byte)0;
   }

   public boolean acceptsRotationalEnergy(byte aSide) {
      return false;
   }

   public boolean injectRotationalEnergy(byte aSide, int aSpeed, int aEnergy) {
      return false;
   }

   public String getSpecialVoltageToolTip() {
      return null;
   }

   public boolean isGivingInformation() {
      return false;
   }

   public String[] getInfoData() {
      return new String[0];
   }

   public boolean isDigitalChest() {
      return false;
   }

   public ItemStack[] getStoredItemData() {
      return null;
   }

   public void setItemCount(int aCount) {}

   public int getMaxItemCount() {
      return 0;
   }

   public abstract int getInvSize();

   public int func_70302_i_() {
      return this.getInvSize();
   }

   public ItemStack func_70301_a(int aIndex) {
      return aIndex >= 0 && aIndex < this.mInventory.length?this.mInventory[aIndex]:null;
   }

   public void func_70299_a(int aIndex, ItemStack aStack) {
      if(aIndex >= 0 && aIndex < this.mInventory.length) {
         this.mInventory[aIndex] = aStack;
      }

   }

   public String func_70303_b() {
      return GregTech_API.mMetaTileList[this.getBaseMetaTileEntity().getMetaTileID()] != null?GregTech_API.mMetaTileList[this.getBaseMetaTileEntity().getMetaTileID()].getMetaName():"";
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_94041_b(int aIndex, ItemStack aStack) {
      return this.getBaseMetaTileEntity().isValidSlot(aIndex);
   }

   public ItemStack func_70298_a(int aIndex, int aAmount) {
      ItemStack rStack = this.func_70301_a(aIndex);
      if(rStack != null) {
         if(rStack.field_77994_a <= aAmount) {
            if(this.setStackToZeroInsteadOfNull(aIndex)) {
               rStack.field_77994_a = 0;
            } else {
               this.func_70299_a(aIndex, (ItemStack)null);
            }
         } else {
            rStack = rStack.func_77979_a(aAmount);
            if(rStack.field_77994_a == 0 && !this.setStackToZeroInsteadOfNull(aIndex)) {
               this.func_70299_a(aIndex, (ItemStack)null);
            }
         }
      }

      return rStack;
   }

   public int[] func_94128_d(int aSide) {
      ArrayList tList = new ArrayList();

      for(int rArray = 0; rArray < this.func_70302_i_(); ++rArray) {
         if(this.isValidSlot(rArray)) {
            tList.add(Integer.valueOf(rArray));
         }
      }

      int[] var5 = new int[tList.size()];

      for(int i = 0; i < var5.length; ++i) {
         var5[i] = ((Integer)tList.get(i)).intValue();
      }

      return var5;
   }

   public boolean func_102007_a(int aIndex, ItemStack aStack, int aSide) {
      return this.isValidSlot(aIndex) && aStack != null && (this.mInventory[aIndex] == null || GT_Utility.areStacksEqual(aStack, this.mInventory[aIndex])) && this.allowPutStack(aIndex, (byte)aSide, aStack);
   }

   public boolean func_102008_b(int aIndex, ItemStack aStack, int aSide) {
      return this.isValidSlot(aIndex) && aStack != null && this.allowPullStack(aIndex, (byte)aSide, aStack);
   }

   public boolean canFill(ForgeDirection aSide, Fluid aFluid) {
      return this.fill(aSide, new FluidStack(aFluid, 1), false) == 1;
   }

   public boolean canDrain(ForgeDirection aSide, Fluid aFluid) {
      return this.drain(aSide, new FluidStack(aFluid, 1), false) != null;
   }

   public FluidTankInfo[] getTankInfo(ForgeDirection aSide) {
      return this.getCapacity() <= 0 && !this.getBaseMetaTileEntity().hasSteamEngineUpgrade()?new FluidTankInfo[0]:new FluidTankInfo[]{this.getInfo()};
   }

   public int fill_default(ForgeDirection aSide, FluidStack aFluid, boolean doFill) {
      return this.fill(aFluid, doFill);
   }

   public int fill(ForgeDirection aSide, FluidStack aFluid, boolean doFill) {
      if(this.getBaseMetaTileEntity().hasSteamEngineUpgrade() && GT_ModHandler.isSteam(aFluid) && aFluid.amount > 1) {
         int tSteam = Math.min(aFluid.amount / 2, this.getBaseMetaTileEntity().getSteamCapacity() - this.getBaseMetaTileEntity().getStoredSteam());
         if(tSteam > 0) {
            if(doFill) {
               this.getBaseMetaTileEntity().increaseStoredSteam(tSteam, true);
            }

            return tSteam * 2;
         } else {
            return 0;
         }
      } else {
         return this.fill_default(aSide, aFluid, doFill);
      }
   }

   public FluidStack drain(ForgeDirection aSide, FluidStack aFluid, boolean doDrain) {
      return this.getFluid() != null && aFluid != null && this.getFluid().isFluidEqual(aFluid)?this.drain(aFluid.amount, doDrain):null;
   }

   public FluidStack drain(ForgeDirection aSide, int maxDrain, boolean doDrain) {
      return this.drain(maxDrain, doDrain);
   }

   public int getFluidAmount() {
      return 0;
   }

   public FluidTankInfo getInfo() {
      return new FluidTankInfo(this);
   }

   public String getMetaName() {
      return this.mName;
   }

   public ItemStack func_70304_b(int i) {
      return null;
   }

   public boolean func_94042_c() {
      return false;
   }

   public boolean doTickProfilingMessageDuringThisTick() {
      return this.doTickProfilingInThisTick;
   }

   public void func_70296_d() {}

   public boolean func_70300_a(EntityPlayer entityplayer) {
      return false;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean connectsToItemPipe(byte aSide) {
      return false;
   }

   public float getExplosionResistance(byte aSide) {
      return 10.0F;
   }

   public ItemStack[] getRealInventory() {
      return this.mInventory;
   }

}
