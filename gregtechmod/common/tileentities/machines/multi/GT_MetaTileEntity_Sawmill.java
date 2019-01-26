package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_Sawmill extends MetaTileEntity {

   public int mProgresstime = 0;
   public int mMaxProgresstime = 0;
   public int mEUt = 0;
   public int mUpdate = 5;
   public int mWaterAmount = 0;
   private ItemStack mOutputItem1;
   private ItemStack mOutputItem2;
   private ItemStack mOutputItem3;
   public boolean mMachine = false;


   public GT_MetaTileEntity_Sawmill(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Sawmill() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isOverclockerUpgradable() {
      return true;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return aFacing == 0;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isInputFacing(byte aSide) {
      return true;
   }

   public int maxEUInput() {
      return 128;
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

   public int getInvSize() {
      return 6;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 116);
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

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Sawmill();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mEUt", this.mEUt);
      aNBT.func_74768_a("mWaterAmount", this.mWaterAmount);
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

      if(this.mOutputItem3 != null) {
         tNBT = new NBTTagCompound();
         this.mOutputItem3.func_77955_b(tNBT);
         aNBT.func_74782_a("mOutputItem3", tNBT);
      }

   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mUpdate = 5;
      this.mEUt = aNBT.func_74762_e("mEUt");
      this.mWaterAmount = aNBT.func_74762_e("mWaterAmount");
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

      NBTTagCompound tNBT3 = (NBTTagCompound)aNBT.func_74781_a("mOutputItem3");
      if(tNBT3 != null) {
         this.mOutputItem3 = GT_Utility.loadItem(tNBT3);
      }

   }

   private boolean checkMachine() {
      for(int i = -1; i < 2; ++i) {
         for(int j = 1; j < 2; ++j) {
            for(int k = -1; k < 2; ++k) {
               if(this.getBaseMetaTileEntity().getBlockOffset(i, j - 2, k) != GregTech_API.sBlockList[0]) {
                  return false;
               }

               if(this.getBaseMetaTileEntity().getMetaIDOffset(i, j - 2, k) != (i == 0 && k == 0?14:13)) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   public void onMachineBlockUpdate() {
      this.mUpdate = 5;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.mUpdate-- == 0) {
            this.mMachine = this.checkMachine();
         }

         this.getBaseMetaTileEntity().setActive(this.mMachine);
         if(this.mMachine && this.mMaxProgresstime > 0) {
            if(this.mProgresstime >= 0 && !this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(this.mEUt * (int)Math.pow(4.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()), false)) {
               if(GregTech_API.sConstantEnergy) {
                  this.mProgresstime = -10;
                  this.getBaseMetaTileEntity().setErrorDisplayID(1);
               }
            } else if((this.mProgresstime += (int)Math.pow(2.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount())) >= this.mMaxProgresstime) {
               this.addOutputProducts();
               this.mOutputItem1 = null;
               this.mOutputItem2 = null;
               this.mOutputItem3 = null;
               this.mProgresstime = 0;
               this.mMaxProgresstime = 0;
               this.getBaseMetaTileEntity().setErrorDisplayID(0);
            }
         } else if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isUniversalEnergyStored(100)) {
            this.checkRecipe();
         }
      }

   }

   private void addOutputProducts() {
      if(this.mOutputItem1 != null) {
         if(this.mInventory[2] == null) {
            this.mInventory[2] = GT_Utility.copy(new Object[]{this.mOutputItem1});
         } else if(GT_Utility.areStacksEqual(this.mInventory[2], this.mOutputItem1)) {
            this.mInventory[2].field_77994_a = Math.min(this.mOutputItem1.func_77976_d(), this.mOutputItem1.field_77994_a + this.mInventory[2].field_77994_a);
         }
      }

      if(this.mOutputItem2 != null) {
         if(this.mInventory[3] == null) {
            this.mInventory[3] = GT_Utility.copy(new Object[]{this.mOutputItem2});
         } else if(GT_Utility.areStacksEqual(this.mInventory[3], this.mOutputItem2)) {
            this.mInventory[3].field_77994_a = Math.min(this.mOutputItem2.func_77976_d(), this.mOutputItem2.field_77994_a + this.mInventory[3].field_77994_a);
         }
      }

      if(this.mOutputItem3 != null) {
         if(this.mInventory[4] == null) {
            this.mInventory[4] = GT_Utility.copy(new Object[]{this.mOutputItem3});
         } else if(GT_Utility.areStacksEqual(this.mInventory[4], this.mOutputItem3)) {
            this.mInventory[4].field_77994_a = Math.min(this.mOutputItem3.func_77976_d(), this.mOutputItem3.field_77994_a + this.mInventory[4].field_77994_a);
         }
      }

   }

   private boolean spaceForOutput(GT_Recipe aRecipe) {
      return (this.mInventory[2] == null || aRecipe.getOutput(0) == null || this.mInventory[2].field_77994_a + aRecipe.getOutput(0).field_77994_a <= this.mInventory[2].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[2], aRecipe.getOutput(0))) && (this.mInventory[3] == null || aRecipe.getOutput(1) == null || this.mInventory[3].field_77994_a + aRecipe.getOutput(1).field_77994_a <= this.mInventory[3].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[3], aRecipe.getOutput(1))) && (this.mInventory[4] == null || aRecipe.getOutput(2) == null || this.mInventory[4].field_77994_a + aRecipe.getOutput(2).field_77994_a <= this.mInventory[4].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[4], aRecipe.getOutput(2)));
   }

   private boolean checkRecipe() {
      if(!this.mMachine) {
         return false;
      } else {
         if(this.mInventory[0] != null) {
            GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sSawmillRecipes, new ItemStack[]{this.mInventory[0], this.mInventory[1]});
            if(tRecipe != null) {
               if(this.spaceForOutput(tRecipe) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[0], this.mInventory[1]})) {
                  if(this.mInventory[0] != null && this.mInventory[0].field_77994_a == 0) {
                     this.mInventory[0] = null;
                  }

                  if(this.mInventory[1] != null && this.mInventory[1].field_77994_a == 0) {
                     this.mInventory[1] = null;
                  }

                  this.mMaxProgresstime = tRecipe.mDuration;
                  this.mEUt = tRecipe.mEUt;
                  this.mOutputItem1 = GT_Utility.copy(new Object[]{tRecipe.getOutput(0)});
                  this.mOutputItem2 = GT_Utility.copy(new Object[]{tRecipe.getOutput(1)});
                  this.mOutputItem3 = GT_Utility.copy(new Object[]{tRecipe.getOutput(2)});
                  return true;
               }
            } else {
               ItemStack tWaterStack = this.mWaterAmount >= 1000?GT_Items.Cell_Water.get((long)(this.mWaterAmount / 1000), new Object[0]):null;
               tRecipe = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sSawmillRecipes, new ItemStack[]{this.mInventory[0], tWaterStack});
               if(tWaterStack != null && tRecipe != null && this.spaceForOutput(tRecipe) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[0], tWaterStack})) {
                  if(this.mInventory[0] != null && this.mInventory[0].field_77994_a == 0) {
                     this.mInventory[0] = null;
                  }

                  this.mWaterAmount -= (this.mWaterAmount / 1000 - tWaterStack.field_77994_a) * 1000;
                  this.mMaxProgresstime = tRecipe.mDuration;
                  this.mEUt = tRecipe.mEUt;
                  this.mOutputItem1 = GT_Utility.copy(new Object[]{tRecipe.getOutput(0)});
                  this.mOutputItem2 = GT_Utility.copy(new Object[]{tRecipe.getOutput(1)});
                  if(!GT_Items.Cell_Empty.isStackEqual(tRecipe.getOutput(2))) {
                     this.mOutputItem3 = GT_Utility.copy(new Object[]{tRecipe.getOutput(2)});
                  }

                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex > 1;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return GT_Utility.getFluidForFilledItem(aStack) != null?aIndex == 1:aIndex == 0;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 1?68 + (aActive?1:0):(aSide == 0?71:(aSide < 4?76:75));
   }

   public String[] getInfoData() {
      return new String[]{"Progress:", this.mProgresstime / 20 + "secs", this.mMaxProgresstime / 20 + "secs"};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public String getDescription() {
      return "Get mo Planks for ya Logs";
   }

   public int fill(FluidStack resource, boolean doFill) {
      if(resource != null && resource.fluidID > 0) {
         FluidStack tLiquid = GT_ModHandler.getWater((long)this.mWaterAmount);
         if(!tLiquid.isFluidEqual(resource)) {
            return 0;
         } else {
            int space = this.getCapacity() - tLiquid.amount;
            if(resource.amount <= space) {
               if(doFill && space > 0) {
                  this.mWaterAmount += resource.amount;
               }

               return resource.amount;
            } else {
               if(doFill && space > 0) {
                  this.mWaterAmount = this.getCapacity();
               }

               return space;
            }
         }
      } else {
         return 0;
      }
   }

   public FluidStack getFluid() {
      return GT_ModHandler.getWater((long)this.mWaterAmount);
   }

   public int getTankPressure() {
      return -100;
   }

   public int getCapacity() {
      return 10000;
   }
}
