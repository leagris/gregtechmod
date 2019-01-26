package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_EnergyInput;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_InputBus;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Muffler;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_OutputBus;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public abstract class GT_MetaTileEntity_MultiBlockBase extends MetaTileEntity {

   public boolean mMachine = false;
   public boolean mWrench = false;
   public boolean mScrewdriver = false;
   public boolean mSoftHammer = false;
   public boolean mHardHammer = false;
   public boolean mSolderingTool = false;
   public boolean mCrowbar = false;
   public int mPollution = 0;
   public int mProgresstime = 0;
   public int mMaxProgresstime = 0;
   public int mEUt = 0;
   public int mEfficiencyIncrease = 0;
   public int mUpdate = 0;
   public int mStartUpCheck = 100;
   public int mRuntime = 0;
   public int mEfficiency = 0;
   public ItemStack[] mOutputItems = null;
   public ArrayList<GT_MetaTileEntity_Hatch_Input> mInputHatches = new ArrayList();
   public ArrayList<GT_MetaTileEntity_Hatch_Output> mOutputHatches = new ArrayList();
   public ArrayList<GT_MetaTileEntity_Hatch_InputBus> mInputBusses = new ArrayList();
   public ArrayList<GT_MetaTileEntity_Hatch_OutputBus> mOutputBusses = new ArrayList();
   public ArrayList<GT_MetaTileEntity_Hatch_Dynamo> mDynamoHatches = new ArrayList();
   public ArrayList<GT_MetaTileEntity_Hatch_Muffler> mMufflerHatches = new ArrayList();
   public ArrayList<GT_MetaTileEntity_Hatch_EnergyInput> mEnergyHatches = new ArrayList();
   public ArrayList<GT_MetaTileEntity_Hatch_Maintenance> mMaintenanceHatches = new ArrayList();


   public GT_MetaTileEntity_MultiBlockBase(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_MultiBlockBase() {}

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex > 0;
   }

   public int getProgresstime() {
      return this.mProgresstime;
   }

   public int maxProgresstime() {
      return this.mMaxProgresstime;
   }

   public int increaseProgress(int aProgress) {
      return aProgress;
   }

   public int getInvSize() {
      return 2;
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mEUt", this.mEUt);
      aNBT.func_74768_a("mProgresstime", this.mProgresstime);
      aNBT.func_74768_a("mMaxProgresstime", this.mMaxProgresstime);
      aNBT.func_74768_a("mEfficiencyIncrease", this.mEfficiencyIncrease);
      aNBT.func_74768_a("mEfficiency", this.mEfficiency);
      aNBT.func_74768_a("mPollution", this.mPollution);
      aNBT.func_74768_a("mRuntime", this.mRuntime);
      if(this.mOutputItems != null) {
         for(int i = 0; i < this.mOutputItems.length; ++i) {
            if(this.mOutputItems[i] != null) {
               NBTTagCompound tNBT = new NBTTagCompound();
               this.mOutputItems[i].func_77955_b(tNBT);
               aNBT.func_74782_a("mOutputItem" + i, tNBT);
            }
         }
      }

      aNBT.func_74757_a("mWrench", this.mWrench);
      aNBT.func_74757_a("mScrewdriver", this.mScrewdriver);
      aNBT.func_74757_a("mSoftHammer", this.mSoftHammer);
      aNBT.func_74757_a("mHardHammer", this.mHardHammer);
      aNBT.func_74757_a("mSolderingTool", this.mSolderingTool);
      aNBT.func_74757_a("mCrowbar", this.mCrowbar);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mEUt = aNBT.func_74762_e("mEUt");
      this.mProgresstime = aNBT.func_74762_e("mProgresstime");
      this.mMaxProgresstime = aNBT.func_74762_e("mMaxProgresstime");
      this.mEfficiencyIncrease = aNBT.func_74762_e("mEfficiencyIncrease");
      this.mEfficiency = aNBT.func_74762_e("mEfficiency");
      this.mPollution = aNBT.func_74762_e("mPollution");
      this.mRuntime = aNBT.func_74762_e("mRuntime");
      this.mOutputItems = new ItemStack[this.getAmountOfOutputs()];

      for(int i = 0; i < this.mOutputItems.length; ++i) {
         NBTTagCompound tNBT = (NBTTagCompound)aNBT.func_74781_a("mOutputItem" + i);
         if(tNBT != null) {
            this.mOutputItems[i] = GT_Utility.loadItem(tNBT);
         }
      }

      this.mWrench = aNBT.func_74767_n("mWrench");
      this.mScrewdriver = aNBT.func_74767_n("mScrewdriver");
      this.mSoftHammer = aNBT.func_74767_n("mSoftHammer");
      this.mHardHammer = aNBT.func_74767_n("mHardHammer");
      this.mSolderingTool = aNBT.func_74767_n("mSolderingTool");
      this.mCrowbar = aNBT.func_74767_n("mCrowbar");
   }

   public void onMachineBlockUpdate() {
      this.mUpdate = 50;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.mEfficiency < 0) {
            this.mEfficiency = 0;
         }

         if(--this.mUpdate == 0 || --this.mStartUpCheck == 0) {
            this.mInputHatches.clear();
            this.mInputBusses.clear();
            this.mOutputHatches.clear();
            this.mOutputBusses.clear();
            this.mDynamoHatches.clear();
            this.mEnergyHatches.clear();
            this.mMufflerHatches.clear();
            this.mMaintenanceHatches.clear();
            this.mMachine = this.checkMachine(this.mInventory[1]);
         }

         if(this.mStartUpCheck < 0) {
            if(this.mMachine) {
               Iterator arr$ = this.mMaintenanceHatches.iterator();

               while(arr$.hasNext()) {
                  GT_MetaTileEntity_Hatch_Maintenance len$ = (GT_MetaTileEntity_Hatch_Maintenance)arr$.next();
                  if(isValidMetaTileEntity(len$)) {
                     if(len$.mWrench) {
                        this.mWrench = true;
                     }

                     if(len$.mScrewdriver) {
                        this.mScrewdriver = true;
                     }

                     if(len$.mSoftHammer) {
                        this.mSoftHammer = true;
                     }

                     if(len$.mHardHammer) {
                        this.mHardHammer = true;
                     }

                     if(len$.mSolderingTool) {
                        this.mSolderingTool = true;
                     }

                     if(len$.mCrowbar) {
                        this.mCrowbar = true;
                     }

                     len$.mWrench = false;
                     len$.mScrewdriver = false;
                     len$.mSoftHammer = false;
                     len$.mHardHammer = false;
                     len$.mSolderingTool = false;
                     len$.mCrowbar = false;
                  }
               }

               if(this.getRepairStatus() > 0) {
                  if(this.mMaxProgresstime > 0 && this.doRandomMaintenanceDamage()) {
                     if(this.onRunningTick(this.mInventory[1])) {
                        if(!this.polluteEnvironment(this.getPollutionPerTick(this.mInventory[1]))) {
                           this.stopMachine();
                        }

                        if(this.mMaxProgresstime > 0 && ++this.mProgresstime >= this.mMaxProgresstime) {
                           if(this.mOutputItems != null) {
                              ItemStack[] var5 = this.mOutputItems;
                              int var6 = var5.length;

                              for(int i$ = 0; i$ < var6; ++i$) {
                                 ItemStack tStack = var5[i$];
                                 if(tStack != null) {
                                    this.addOutput(tStack);
                                 }
                              }
                           }

                           this.mEfficiency = Math.max(0, Math.min(this.mEfficiency + this.mEfficiencyIncrease, this.getMaxEfficiency(this.mInventory[1]) - (this.getIdealStatus() - this.getRepairStatus()) * 1000));
                           this.mOutputItems = null;
                           this.mProgresstime = 0;
                           this.mMaxProgresstime = 0;
                           this.mEfficiencyIncrease = 0;
                           if(this.getBaseMetaTileEntity().isAllowedToWork()) {
                              this.checkRecipe(this.mInventory[1]);
                           }
                        }
                     }
                  } else {
                     if(this.getBaseMetaTileEntity().isAllowedToWork()) {
                        this.checkRecipe(this.mInventory[1]);
                     }

                     if(this.mMaxProgresstime <= 0) {
                        this.mEfficiency = Math.max(0, this.mEfficiency - 1000);
                     }
                  }
               } else {
                  this.stopMachine();
               }
            } else {
               this.stopMachine();
            }
         }

         this.getBaseMetaTileEntity().setErrorDisplayID(this.getBaseMetaTileEntity().getErrorDisplayID() & -128 | (this.mWrench?0:1) | (this.mScrewdriver?0:2) | (this.mSoftHammer?0:4) | (this.mHardHammer?0:8) | (this.mSolderingTool?0:16) | (this.mCrowbar?0:32) | (this.mMachine?0:64));
         this.getBaseMetaTileEntity().setActive(this.mMaxProgresstime > 0);
      }

   }

   public boolean polluteEnvironment(int aPollutionLevel) {
      this.mPollution += aPollutionLevel;
      Iterator i$ = this.mMufflerHatches.iterator();

      while(i$.hasNext()) {
         GT_MetaTileEntity_Hatch_Muffler tHatch = (GT_MetaTileEntity_Hatch_Muffler)i$.next();
         if(isValidMetaTileEntity(tHatch)) {
            if(this.mPollution < 10000) {
               break;
            }

            if(tHatch.polluteEnvironment()) {
               this.mPollution -= 10000;
            }
         }
      }

      return this.mPollution < 10000;
   }

   public boolean onRunningTick(ItemStack aStack) {
      int tConsumedEU;
      if(this.mEUt > 0) {
         tConsumedEU = (int)((long)this.mEUt * (long)this.mEfficiency / 10000L);
         this.addEnergyOutput(tConsumedEU);
      } else if(this.mEUt < 0) {
         tConsumedEU = (int)((long)(-this.mEUt) * 10000L / (long)Math.max(1000, this.mEfficiency));
         if(!this.drainEnergyInput(tConsumedEU)) {
            this.stopMachine();
            return false;
         }
      }

      return true;
   }

   public abstract boolean isCorrectMachinePart(ItemStack var1);

   public abstract boolean checkRecipe(ItemStack var1);

   public abstract boolean checkMachine(ItemStack var1);

   public abstract int getMaxEfficiency(ItemStack var1);

   public abstract int getPollutionPerTick(ItemStack var1);

   public abstract int getDamageToComponent(ItemStack var1);

   public abstract int getAmountOfOutputs();

   public abstract boolean explodesOnComponentBreak(ItemStack var1);

   public void stopMachine() {
      this.mOutputItems = null;
      this.mEUt = 0;
      this.mEfficiency = 0;
      this.mProgresstime = 0;
      this.mMaxProgresstime = 0;
      this.mEfficiencyIncrease = 0;
      this.getBaseMetaTileEntity().disableWorking();
   }

   public int getRepairStatus() {
      return (this.mWrench?1:0) + (this.mScrewdriver?1:0) + (this.mSoftHammer?1:0) + (this.mHardHammer?1:0) + (this.mSolderingTool?1:0) + (this.mCrowbar?1:0);
   }

   public int getIdealStatus() {
      return 6;
   }

   public static boolean isValidMetaTileEntity(MetaTileEntity aMetaTileEntity) {
      return aMetaTileEntity.getBaseMetaTileEntity() != null && aMetaTileEntity.getBaseMetaTileEntity().getMetaTileEntity() == aMetaTileEntity;
   }

   public boolean doRandomMaintenanceDamage() {
      if(this.isCorrectMachinePart(this.mInventory[1]) && this.getRepairStatus() != 0) {
         if(this.mRuntime++ > 1000) {
            this.mRuntime = 0;
            if(this.getBaseMetaTileEntity().getRandomNumber(6000) == 0) {
               switch(this.getBaseMetaTileEntity().getRandomNumber(6)) {
               case 0:
                  this.mWrench = false;
                  break;
               case 1:
                  this.mScrewdriver = false;
                  break;
               case 2:
                  this.mSoftHammer = false;
                  break;
               case 3:
                  this.mHardHammer = false;
                  break;
               case 4:
                  this.mSolderingTool = false;
                  break;
               case 5:
                  this.mCrowbar = false;
               }
            }

            if(this.mInventory[1] != null && this.getBaseMetaTileEntity().getRandomNumber(2) == 0) {
               this.mInventory[1].func_77964_b(this.mInventory[1].func_77960_j() + this.getDamageToComponent(this.mInventory[1]));
               if(this.mInventory[1].func_77960_j() >= this.mInventory[1].func_77958_k()) {
                  if(this.explodesOnComponentBreak(this.mInventory[1])) {
                     this.mInventory[1] = null;
                     Iterator i$ = this.mInputHatches.iterator();

                     MetaTileEntity tTileEntity;
                     while(i$.hasNext()) {
                        tTileEntity = (MetaTileEntity)i$.next();
                        tTileEntity.getBaseMetaTileEntity().doExplosion(1000000);
                     }

                     i$ = this.mOutputHatches.iterator();

                     while(i$.hasNext()) {
                        tTileEntity = (MetaTileEntity)i$.next();
                        tTileEntity.getBaseMetaTileEntity().doExplosion(1000000);
                     }

                     i$ = this.mDynamoHatches.iterator();

                     while(i$.hasNext()) {
                        tTileEntity = (MetaTileEntity)i$.next();
                        tTileEntity.getBaseMetaTileEntity().doExplosion(1000000);
                     }

                     i$ = this.mMufflerHatches.iterator();

                     while(i$.hasNext()) {
                        tTileEntity = (MetaTileEntity)i$.next();
                        tTileEntity.getBaseMetaTileEntity().doExplosion(1000000);
                     }

                     i$ = this.mEnergyHatches.iterator();

                     while(i$.hasNext()) {
                        tTileEntity = (MetaTileEntity)i$.next();
                        tTileEntity.getBaseMetaTileEntity().doExplosion(1000000);
                     }

                     i$ = this.mMaintenanceHatches.iterator();

                     while(i$.hasNext()) {
                        tTileEntity = (MetaTileEntity)i$.next();
                        tTileEntity.getBaseMetaTileEntity().doExplosion(1000000);
                     }

                     this.getBaseMetaTileEntity().doExplosion(1000000);
                  } else {
                     this.mInventory[1] = null;
                  }

                  return false;
               }
            }
         }

         return true;
      } else {
         this.stopMachine();
         return false;
      }
   }

   public boolean addEnergyOutput(int aEU) {
      if(aEU <= 0) {
         return true;
      } else {
         Iterator i$ = this.mDynamoHatches.iterator();

         GT_MetaTileEntity_Hatch_Dynamo tHatch;
         do {
            if(!i$.hasNext()) {
               return false;
            }

            tHatch = (GT_MetaTileEntity_Hatch_Dynamo)i$.next();
         } while(!isValidMetaTileEntity(tHatch) || !tHatch.getBaseMetaTileEntity().increaseStoredEnergyUnits(aEU, false));

         return true;
      }
   }

   public boolean drainEnergyInput(int aEU) {
      if(aEU <= 0) {
         return true;
      } else {
         Iterator i$ = this.mEnergyHatches.iterator();

         GT_MetaTileEntity_Hatch_EnergyInput tHatch;
         do {
            if(!i$.hasNext()) {
               return false;
            }

            tHatch = (GT_MetaTileEntity_Hatch_EnergyInput)i$.next();
         } while(!isValidMetaTileEntity(tHatch) || !tHatch.getBaseMetaTileEntity().decreaseStoredEnergyUnits(aEU, false));

         return true;
      }
   }

   public boolean addOutput(FluidStack aLiquid) {
      if(aLiquid == null) {
         return false;
      } else {
         Iterator i$ = this.mOutputHatches.iterator();

         while(i$.hasNext()) {
            GT_MetaTileEntity_Hatch_Output tHatch = (GT_MetaTileEntity_Hatch_Output)i$.next();
            if(isValidMetaTileEntity(tHatch) && aLiquid.isFluidEqual(GT_ModHandler.getSteam(1L))) {
               if(!tHatch.outputsSteam()) {
                  continue;
               }
            } else if(!tHatch.outputsLiquids()) {
               continue;
            }

            int tAmount = tHatch.fill(aLiquid, false);
            if(tAmount >= aLiquid.amount) {
               return tHatch.fill(aLiquid, true) >= aLiquid.amount;
            }
         }

         return false;
      }
   }

   public boolean depleteInput(FluidStack aLiquid) {
      if(aLiquid == null) {
         return false;
      } else {
         Iterator i$ = this.mInputHatches.iterator();

         while(i$.hasNext()) {
            GT_MetaTileEntity_Hatch_Input tHatch = (GT_MetaTileEntity_Hatch_Input)i$.next();
            if(isValidMetaTileEntity(tHatch)) {
               FluidStack tLiquid = tHatch.getFluid();
               if(tLiquid != null && tLiquid.isFluidEqual(aLiquid)) {
                  tLiquid = tHatch.drain(aLiquid.amount, false);
                  if(tLiquid != null && tLiquid.amount >= aLiquid.amount) {
                     tLiquid = tHatch.drain(aLiquid.amount, true);
                     return tLiquid != null && tLiquid.amount >= aLiquid.amount;
                  }
               }
            }
         }

         return false;
      }
   }

   public boolean addOutput(ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return false;
      } else {
         aStack = GT_Utility.copy(new Object[]{aStack});
         FluidStack aLiquid = GT_Utility.getFluidForFilledItem(aStack);
         Iterator i$;
         int tAmount;
         GT_MetaTileEntity_Hatch_Output var6;
         if(aLiquid == null) {
            i$ = this.mOutputBusses.iterator();

            while(i$.hasNext()) {
               GT_MetaTileEntity_Hatch_OutputBus tHatch = (GT_MetaTileEntity_Hatch_OutputBus)i$.next();
               if(isValidMetaTileEntity(tHatch)) {
                  for(tAmount = tHatch.func_70302_i_() - 1; tAmount >= 0; --tAmount) {
                     if(tHatch.getBaseMetaTileEntity().addStackToSlot(tAmount, aStack)) {
                        return true;
                     }
                  }
               }
            }

            i$ = this.mOutputHatches.iterator();

            while(i$.hasNext()) {
               var6 = (GT_MetaTileEntity_Hatch_Output)i$.next();
               if(isValidMetaTileEntity(var6) && var6.outputsItems() && var6.getBaseMetaTileEntity().addStackToSlot(1, aStack)) {
                  return true;
               }
            }
         } else {
            i$ = this.mOutputHatches.iterator();

            while(i$.hasNext()) {
               var6 = (GT_MetaTileEntity_Hatch_Output)i$.next();
               if(isValidMetaTileEntity(var6) && aLiquid.isFluidEqual(GT_ModHandler.getSteam(1L))) {
                  if(!var6.outputsSteam()) {
                     continue;
                  }
               } else if(!var6.outputsLiquids()) {
                  continue;
               }

               tAmount = var6.fill(aLiquid, false);
               if(tAmount >= aLiquid.amount) {
                  return var6.fill(aLiquid, true) >= aLiquid.amount;
               }
            }
         }

         return false;
      }
   }

   public boolean depleteInput(ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return false;
      } else {
         FluidStack aLiquid = GT_Utility.getFluidForFilledItem(aStack);
         if(aLiquid != null) {
            return this.depleteInput(aLiquid);
         } else {
            Iterator i$ = this.mInputHatches.iterator();

            while(i$.hasNext()) {
               GT_MetaTileEntity_Hatch_Input tHatch = (GT_MetaTileEntity_Hatch_Input)i$.next();
               if(isValidMetaTileEntity(tHatch) && GT_Utility.areStacksEqual(aStack, tHatch.getBaseMetaTileEntity().func_70301_a(0)) && tHatch.getBaseMetaTileEntity().func_70301_a(0).field_77994_a >= aStack.field_77994_a) {
                  tHatch.getBaseMetaTileEntity().func_70298_a(0, aStack.field_77994_a);
                  return true;
               }
            }

            i$ = this.mInputBusses.iterator();

            while(i$.hasNext()) {
               GT_MetaTileEntity_Hatch_InputBus var6 = (GT_MetaTileEntity_Hatch_InputBus)i$.next();
               if(isValidMetaTileEntity(var6)) {
                  for(int i = var6.getBaseMetaTileEntity().func_70302_i_() - 1; i >= 0; --i) {
                     if(GT_Utility.areStacksEqual(aStack, var6.getBaseMetaTileEntity().func_70301_a(i)) && var6.getBaseMetaTileEntity().func_70301_a(0).field_77994_a >= aStack.field_77994_a) {
                        var6.getBaseMetaTileEntity().func_70298_a(0, aStack.field_77994_a);
                        return true;
                     }
                  }
               }
            }

            return false;
         }
      }
   }

   public ArrayList<ItemStack> getStoredOutputs() {
      ArrayList rList = new ArrayList();
      Iterator i$ = this.mOutputHatches.iterator();

      while(i$.hasNext()) {
         GT_MetaTileEntity_Hatch_Output tHatch = (GT_MetaTileEntity_Hatch_Output)i$.next();
         if(isValidMetaTileEntity(tHatch)) {
            rList.add(tHatch.getBaseMetaTileEntity().func_70301_a(1));
         }
      }

      i$ = this.mOutputBusses.iterator();

      while(i$.hasNext()) {
         GT_MetaTileEntity_Hatch_OutputBus var5 = (GT_MetaTileEntity_Hatch_OutputBus)i$.next();
         if(isValidMetaTileEntity(var5)) {
            for(int i = var5.getBaseMetaTileEntity().func_70302_i_() - 1; i >= 0; --i) {
               rList.add(var5.getBaseMetaTileEntity().func_70301_a(i));
            }
         }
      }

      return rList;
   }

   public ArrayList<ItemStack> getStoredInputs() {
      ArrayList rList = new ArrayList();
      Iterator i$ = this.mInputHatches.iterator();

      while(i$.hasNext()) {
         GT_MetaTileEntity_Hatch_Input tHatch = (GT_MetaTileEntity_Hatch_Input)i$.next();
         if(isValidMetaTileEntity(tHatch)) {
            rList.add(tHatch.getBaseMetaTileEntity().func_70301_a(0));
         }
      }

      i$ = this.mInputBusses.iterator();

      while(i$.hasNext()) {
         GT_MetaTileEntity_Hatch_InputBus var5 = (GT_MetaTileEntity_Hatch_InputBus)i$.next();
         if(isValidMetaTileEntity(var5)) {
            for(int i = var5.getBaseMetaTileEntity().func_70302_i_() - 1; i >= 0; --i) {
               rList.add(var5.getBaseMetaTileEntity().func_70301_a(i));
            }
         }
      }

      return rList;
   }

   public boolean addToMachineList(IGregTechTileEntity aTileEntity) {
      if(aTileEntity == null) {
         return false;
      } else {
         IMetaTileEntity aMetaTileEntity = aTileEntity.getMetaTileEntity();
         return aMetaTileEntity == null?false:(aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Input?this.mInputHatches.add((GT_MetaTileEntity_Hatch_Input)aMetaTileEntity):(aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_InputBus?this.mInputBusses.add((GT_MetaTileEntity_Hatch_InputBus)aMetaTileEntity):(aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Output?this.mOutputHatches.add((GT_MetaTileEntity_Hatch_Output)aMetaTileEntity):(aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_OutputBus?this.mOutputBusses.add((GT_MetaTileEntity_Hatch_OutputBus)aMetaTileEntity):(aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_EnergyInput?this.mEnergyHatches.add((GT_MetaTileEntity_Hatch_EnergyInput)aMetaTileEntity):(aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Dynamo?this.mDynamoHatches.add((GT_MetaTileEntity_Hatch_Dynamo)aMetaTileEntity):(aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Maintenance?this.mMaintenanceHatches.add((GT_MetaTileEntity_Hatch_Maintenance)aMetaTileEntity):(aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Muffler?this.mMufflerHatches.add((GT_MetaTileEntity_Hatch_Muffler)aMetaTileEntity):false))))))));
      }
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?32:(aSide == 1?29:40);
   }

   public String[] getInfoData() {
      return new String[]{"Progress:", this.mProgresstime / 20 + "secs", this.mMaxProgresstime / 20 + "secs", "Efficiency:", (float)this.mEfficiency / 100.0F + "%", "Problems:", "" + (this.getIdealStatus() - this.getRepairStatus())};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }
}
