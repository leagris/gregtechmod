package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public abstract class GT_MetaTileEntity_BasicMachine extends MetaTileEntity {

   public boolean bAlloyInputFromOutputSide = false;
   public boolean bOutput = false;
   public boolean bOutputBlocked = false;
   public boolean bItemTransfer = false;
   public boolean bSeperatedInputs = false;
   public boolean bHasBeenUpdated = false;
   public boolean bStuttering = false;
   public int mMainFacing = -1;
   public int mProgresstime = 0;
   public int mMaxProgresstime = 0;
   public int mEUt = 0;
   public ItemStack mOutputItem1;
   public ItemStack mOutputItem2;


   public GT_MetaTileEntity_BasicMachine(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BasicMachine() {}

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isOverclockerUpgradable() {
      return this.getElectricTier() > 0;
   }

   public boolean isTransformerUpgradable() {
      return this.getElectricTier() > 0;
   }

   public boolean isBatteryUpgradable() {
      return this.getElectricTier() > 0;
   }

   public boolean isElectric() {
      return this.getElectricTier() > 0;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex > 0;
   }

   public boolean isFacingValid(byte aFacing) {
      return this.mMainFacing > 1 || aFacing > 1;
   }

   public boolean isEnetInput() {
      return this.getElectricTier() > 0;
   }

   public boolean isEnetOutput() {
      return this.getElectricTier() > 0;
   }

   public boolean isInputFacing(byte aSide) {
      return aSide != this.mMainFacing && this.getElectricTier() > 0?!this.isOutputFacing(aSide):false;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide != this.mMainFacing && this.getElectricTier() > 0?(this.bOutput?this.getBaseMetaTileEntity().getFrontFacing() == aSide:false):false;
   }

   public boolean isTeleporterCompatible() {
      return false;
   }

   public int getMinimumStoredEU() {
      return 1000;
   }

   public int maxEUInput() {
      return this.getElectricTier() > 0?GregTech_API.VOLTAGES[this.getElectricTier()]:0;
   }

   public int maxEUOutput() {
      return this.bOutput && this.getElectricTier() > 0?GregTech_API.VOLTAGES[this.getElectricTier()]:0;
   }

   public int maxEUStore() {
      return this.getElectricTier() * this.getElectricTier() * 2000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public int getInvSize() {
      return 6;
   }

   public int dechargerSlotStartIndex() {
      return 5;
   }

   public int dechargerSlotCount() {
      return this.getElectricTier() > 0?1:0;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public int getProgresstime() {
      return this.mProgresstime;
   }

   public int maxProgresstime() {
      return this.mMaxProgresstime;
   }

   public int increaseProgress(int aProgress) {
      this.mProgresstime += aProgress;
      return this.mMaxProgresstime - this.mProgresstime;
   }

   public boolean isLiquidInput(byte aSide) {
      return aSide != this.mMainFacing;
   }

   public boolean isLiquidOutput(byte aSide) {
      return aSide != this.mMainFacing;
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74757_a("bOutput", this.bOutput);
      aNBT.func_74757_a("bItemTransfer", this.bItemTransfer);
      aNBT.func_74757_a("bHasBeenUpdated", this.bHasBeenUpdated);
      aNBT.func_74757_a("bSeperatedInputs", this.bSeperatedInputs);
      aNBT.func_74757_a("bAlloyInputFromOutputSide", this.bAlloyInputFromOutputSide);
      aNBT.func_74768_a("mEUt", this.mEUt);
      aNBT.func_74768_a("mMainFacing", this.mMainFacing);
      aNBT.func_74768_a("mProgresstime", this.mProgresstime);
      aNBT.func_74768_a("mMaxProgresstime", this.mMaxProgresstime);
      NBTTagCompound tNBT;
      if(this.mOutputItem1 != null) {
         tNBT = new NBTTagCompound();
         this.mOutputItem1.func_77955_b(tNBT);
         aNBT.func_74782_a("mOutputItem1", tNBT);
      }

      if(this.mOutputItem2 != null) {
         tNBT = new NBTTagCompound();
         this.mOutputItem2.func_77955_b(tNBT);
         aNBT.func_74782_a("mOutputItem2", tNBT);
      }

   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.bOutput = aNBT.func_74767_n("bOutput");
      this.bItemTransfer = aNBT.func_74767_n("bItemTransfer");
      this.bHasBeenUpdated = aNBT.func_74767_n("bHasBeenUpdated");
      this.bSeperatedInputs = aNBT.func_74767_n("bSeperatedInputs");
      this.bAlloyInputFromOutputSide = aNBT.func_74767_n("bAlloyInputFromOutputSide");
      this.mEUt = aNBT.func_74762_e("mEUt");
      this.mMainFacing = aNBT.func_74762_e("mMainFacing");
      this.mProgresstime = aNBT.func_74762_e("mProgresstime");
      this.mMaxProgresstime = aNBT.func_74762_e("mMaxProgresstime");
      NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.func_74781_a("mOutputItem1");
      if(tNBT1 != null) {
         this.mOutputItem1 = GT_Utility.loadItem(tNBT1);
      }

      NBTTagCompound tNBT2 = (NBTTagCompound)aNBT.func_74781_a("mOutputItem2");
      if(tNBT2 != null) {
         this.mOutputItem2 = GT_Utility.loadItem(tNBT2);
      }

   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         boolean tSucceeded = false;
         if(this.mMainFacing < 2 && this.getBaseMetaTileEntity().getFrontFacing() > 1) {
            this.mMainFacing = this.getBaseMetaTileEntity().getFrontFacing();
         }

         if(this.mMainFacing >= 2 && !this.bHasBeenUpdated) {
            this.bHasBeenUpdated = true;
            this.getBaseMetaTileEntity().setFrontFacing(this.getBaseMetaTileEntity().getBackFacing());
         }

         if(this.mMaxProgresstime > 0) {
            this.getBaseMetaTileEntity().setActive(true);
            if(this.mProgresstime >= 0 && !this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(this.mEUt * (int)Math.pow(4.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()), false)) {
               if(!this.bStuttering) {
                  this.stutterProcess();
                  if(this.useStandardStutterSound()) {
                     this.sendSound((byte)8);
                  }

                  this.bStuttering = true;
               }
            } else {
               if((this.mProgresstime += (int)Math.pow(2.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount())) >= this.mMaxProgresstime) {
                  if(!this.getBaseMetaTileEntity().addStackToSlot(3, this.mOutputItem1)) {
                     this.getBaseMetaTileEntity().addStackToSlot(4, this.mOutputItem1);
                  }

                  if(!this.getBaseMetaTileEntity().addStackToSlot(3, this.mOutputItem2)) {
                     this.getBaseMetaTileEntity().addStackToSlot(4, this.mOutputItem2);
                  }

                  this.mOutputItem1 = null;
                  this.mOutputItem2 = null;
                  this.mProgresstime = 0;
                  this.mMaxProgresstime = 0;
                  tSucceeded = true;
                  this.endProcess();
               }

               this.bStuttering = false;
            }
         } else {
            this.getBaseMetaTileEntity().setActive(false);
         }

         if(this.bItemTransfer && (this.mInventory[3] != null || this.mInventory[4] != null) && this.getBaseMetaTileEntity().getFrontFacing() != this.mMainFacing && this.doesAutoOutput() && (tSucceeded || this.bOutputBlocked || this.getBaseMetaTileEntity().hasInventoryBeenModified() || this.getBaseMetaTileEntity().getTimer() % 600L == 0L) && this.getBaseMetaTileEntity().isUniversalEnergyStored(500)) {
            TileEntity tTileEntity2 = this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getFrontFacing());
            byte tCost = GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), tTileEntity2, this.getBaseMetaTileEntity().getFrontFacing(), this.getBaseMetaTileEntity().getBackFacing(), (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1);
            if(tCost > 0) {
               this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCost, true);
               tCost = GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), tTileEntity2, this.getBaseMetaTileEntity().getFrontFacing(), this.getBaseMetaTileEntity().getBackFacing(), (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1);
               if(tCost > 0) {
                  this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCost, true);
               }
            }
         }

         if(this.mInventory[3] == null && this.mInventory[4] == null) {
            this.bOutputBlocked = false;
         }

         if(this.allowToCheckRecipe()) {
            if(this.mMaxProgresstime <= 0 && this.getBaseMetaTileEntity().isAllowedToWork() && (tSucceeded || this.getBaseMetaTileEntity().hasInventoryBeenModified() || this.getBaseMetaTileEntity().getTimer() % 600L == 0L || this.getBaseMetaTileEntity().hasWorkJustBeenEnabled()) && this.getBaseMetaTileEntity().isUniversalEnergyStored(this.getMinimumStoredEU() - 100)) {
               this.checkRecipe();
               if(this.mInventory[1] != null && this.mInventory[1].field_77994_a <= 0) {
                  this.mInventory[1] = null;
               }

               if(this.mInventory[2] != null && this.mInventory[2].field_77994_a <= 0) {
                  this.mInventory[2] = null;
               }

               if(this.mMaxProgresstime > 0) {
                  this.mOutputItem1 = GT_OreDictUnificator.get(true, this.mOutputItem1);
                  this.mOutputItem2 = GT_OreDictUnificator.get(true, this.mOutputItem2);
                  if(GT_Utility.isDebugItem(this.mInventory[5])) {
                     this.mMaxProgresstime = 1;
                  }
               } else {
                  this.mOutputItem1 = null;
                  this.mOutputItem2 = null;
               }

               if(this.mOutputItem1 != null && this.mOutputItem1.field_77994_a > 64) {
                  this.mOutputItem1.field_77994_a = 64;
               }

               if(this.mOutputItem2 != null && this.mOutputItem2.field_77994_a > 64) {
                  this.mOutputItem2.field_77994_a = 64;
               }

               if(this.mMaxProgresstime > 0) {
                  this.startProcess();
               }
            }
         } else if(!this.bStuttering) {
            this.stutterProcess();
            if(this.useStandardStutterSound()) {
               this.sendSound((byte)8);
            }

            this.bStuttering = true;
         }
      }

   }

   public void onValueUpdate(byte aValue) {
      this.mMainFacing = aValue;
   }

   public byte getUpdateData() {
      return (byte)this.mMainFacing;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return this.mMainFacing < 2?(aSide == aFacing?(aActive?this.getFrontFacingActive():this.getFrontFacingInactive()):(aSide == 0?(aActive?this.getBottomFacingActive():this.getBottomFacingInactive()):(aSide == 1?(aActive?this.getTopFacingActive():this.getTopFacingInactive()):(aActive?this.getSideFacingActive():this.getSideFacingInactive())))):(aSide == this.mMainFacing?(aActive?this.getFrontFacingActive():this.getFrontFacingInactive()):(this.showPipeFacing() && aSide == aFacing?(aSide == 0?this.getBottomFacingPipe():(aSide == 1?this.getTopFacingPipe():this.getSideFacingPipe())):(aSide == 0?(aActive?this.getBottomFacingActive():this.getBottomFacingInactive()):(aSide == 1?(aActive?this.getTopFacingActive():this.getTopFacingInactive()):(aActive?this.getSideFacingActive():this.getSideFacingInactive())))));
   }

   public boolean spaceForOutput(ItemStack aOutput1, ItemStack aOutput2) {
      if((this.mInventory[3] == null || aOutput1 == null || this.mInventory[3].field_77994_a + aOutput1.field_77994_a <= this.mInventory[3].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[3], aOutput1)) && (this.mInventory[4] == null || aOutput2 == null || this.mInventory[4].field_77994_a + aOutput2.field_77994_a <= this.mInventory[4].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[4], aOutput2))) {
         return true;
      } else {
         this.bOutputBlocked = true;
         return false;
      }
   }

   public abstract void checkRecipe();

   public boolean hasTwoSeperateInputs() {
      return false;
   }

   public int getSideFacingActive() {
      return this.getSideFacingInactive();
   }

   public int getSideFacingInactive() {
      return 40;
   }

   public int getFrontFacingActive() {
      return this.getFrontFacingInactive();
   }

   public int getFrontFacingInactive() {
      return this.getSideFacingInactive();
   }

   public int getTopFacingActive() {
      return this.getTopFacingInactive();
   }

   public int getTopFacingInactive() {
      return 29;
   }

   public int getBottomFacingActive() {
      return this.getBottomFacingInactive();
   }

   public int getBottomFacingInactive() {
      return 32;
   }

   public int getBottomFacingPipe() {
      return 38;
   }

   public int getTopFacingPipe() {
      return 79;
   }

   public int getSideFacingPipe() {
      return 36;
   }

   public void doSound(byte aIndex, double aX, double aY, double aZ) {
      super.doSound(aIndex, aX, aY, aZ);
      if(aIndex == 8) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(210)), 100, 1.0F, aX, aY, aZ);
      }

   }

   public boolean doesAutoOutput() {
      return true;
   }

   public boolean allowToCheckRecipe() {
      return true;
   }

   public boolean showPipeFacing() {
      return true;
   }

   public void startProcess() {}

   public void endProcess() {}

   public void abortProcess() {}

   public void stutterProcess() {}

   public boolean useStandardStutterSound() {
      return true;
   }

   public int getElectricTier() {
      return 1;
   }

   public String[] getInfoData() {
      return new String[]{"Progress:", this.mProgresstime / 20 + "secs", this.mMaxProgresstime / 20 + "secs"};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getFrontFacing()) {
         this.bAlloyInputFromOutputSide = !this.bAlloyInputFromOutputSide;
         GT_Utility.sendChatToPlayer(aPlayer, this.bAlloyInputFromOutputSide?"Input from Output Side allowed":"Input from Output Side forbidden");
      }

   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing() && (aSide != this.mMainFacing || GregTech_API.getCoverBehavior(aCoverID).isGUIClickable(aSide, aCoverID, 0, this.getBaseMetaTileEntity()));
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aSide != this.mMainFacing?aIndex == 3 || aIndex == 4:false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aSide != this.mMainFacing && (this.bAlloyInputFromOutputSide || aSide != this.getBaseMetaTileEntity().getFrontFacing())?(this.hasTwoSeperateInputs() && GT_Utility.areStacksEqual(GT_OreDictUnificator.get(aStack), this.mInventory[aIndex == 1?2:1])?false:(this.bSeperatedInputs?(aSide < 2?aIndex == 1:aIndex == 2):aIndex == 1 || aIndex == 2)):false;
   }
}
