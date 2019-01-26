package gregtechmod.common.tileentities.machines;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_Electrolyzer extends GT_MetaTileEntity_BasicTank {

   private int mProgresstime = 0;
   private int mMaxProgresstime = 0;
   private int mEUt = 0;
   private ItemStack mOutputItem1;
   private ItemStack mOutputItem2;
   private ItemStack mOutputItem3;
   private ItemStack mOutputItem4;


   public GT_MetaTileEntity_Electrolyzer(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Electrolyzer() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isOverclockerUpgradable() {
      return true;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 6;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return false;
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
      return 7;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 109);
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
      return new GT_MetaTileEntity_Electrolyzer();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74768_a("mEUt", this.mEUt);
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

      if(this.mOutputItem4 != null) {
         tNBT = new NBTTagCompound();
         this.mOutputItem4.func_77955_b(tNBT);
         aNBT.func_74782_a("mOutputItem4", tNBT);
      }

   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mEUt = aNBT.func_74762_e("mEUt");
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

      NBTTagCompound tNBT4 = (NBTTagCompound)aNBT.func_74781_a("mOutputItem4");
      if(tNBT4 != null) {
         this.mOutputItem4 = GT_Utility.loadItem(tNBT4);
      }

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
      return true;
   }

   public boolean displaysStackSize() {
      return true;
   }

   public int getInputSlot() {
      return 1;
   }

   public int getOutputSlot() {
      return 2;
   }

   public int getStackDisplaySlot() {
      return 6;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.mMaxProgresstime > 0) {
            this.getBaseMetaTileEntity().setActive(true);
            if(this.mProgresstime >= 0 && !this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(this.mEUt * (int)Math.pow(4.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()), false)) {
               this.getBaseMetaTileEntity().setActive(false);
               if(GregTech_API.sConstantEnergy) {
                  this.mProgresstime = -10;
                  this.getBaseMetaTileEntity().setErrorDisplayID(1);
               }
            } else if((this.mProgresstime += (int)Math.pow(2.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount())) >= this.mMaxProgresstime) {
               this.addOutputProducts();
               this.mOutputItem1 = null;
               this.mOutputItem2 = null;
               this.mOutputItem3 = null;
               this.mOutputItem4 = null;
               this.mProgresstime = 0;
               this.mMaxProgresstime = 0;
               this.getBaseMetaTileEntity().setErrorDisplayID(0);
            }
         } else {
            this.getBaseMetaTileEntity().setActive(false);
            if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isUniversalEnergyStored(100)) {
               this.checkRecipe();
            }
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

      if(this.mOutputItem4 != null) {
         if(this.mInventory[5] == null) {
            this.mInventory[5] = GT_Utility.copy(new Object[]{this.mOutputItem4});
         } else if(GT_Utility.areStacksEqual(this.mInventory[5], this.mOutputItem4)) {
            this.mInventory[5].field_77994_a = Math.min(this.mOutputItem4.func_77976_d(), this.mOutputItem4.field_77994_a + this.mInventory[5].field_77994_a);
         }
      }

   }

   private boolean spaceForOutput(GT_Recipe aRecipe) {
      return (this.mInventory[2] == null || aRecipe.getOutput(0) == null || this.mInventory[2].field_77994_a + aRecipe.getOutput(0).field_77994_a <= this.mInventory[2].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[2], aRecipe.getOutput(0))) && (this.mInventory[3] == null || aRecipe.getOutput(1) == null || this.mInventory[3].field_77994_a + aRecipe.getOutput(1).field_77994_a <= this.mInventory[3].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[3], aRecipe.getOutput(1))) && (this.mInventory[4] == null || aRecipe.getOutput(2) == null || this.mInventory[4].field_77994_a + aRecipe.getOutput(2).field_77994_a <= this.mInventory[4].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[4], aRecipe.getOutput(2))) && (this.mInventory[5] == null || aRecipe.getOutput(3) == null || this.mInventory[5].field_77994_a + aRecipe.getOutput(3).field_77994_a <= this.mInventory[5].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[5], aRecipe.getOutput(3)));
   }

   private boolean checkRecipe() {
      if(this.mInventory[0] != null) {
         GT_Recipe tStack = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sElectrolyzerRecipes, new ItemStack[]{this.mInventory[0], this.mInventory[1]});
         if(tStack != null && this.spaceForOutput(tStack) && tStack.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[0], this.mInventory[1]})) {
            if(this.mInventory[0] != null && this.mInventory[0].field_77994_a == 0) {
               this.mInventory[0] = null;
            }

            if(this.mInventory[1] != null && this.mInventory[1].field_77994_a == 0) {
               this.mInventory[1] = null;
            }

            this.mMaxProgresstime = tStack.mDuration;
            this.mEUt = tStack.mEUt;
            this.mOutputItem1 = GT_Utility.copy(new Object[]{tStack.getOutput(0)});
            this.mOutputItem2 = GT_Utility.copy(new Object[]{tStack.getOutput(1)});
            this.mOutputItem3 = GT_Utility.copy(new Object[]{tStack.getOutput(2)});
            this.mOutputItem4 = GT_Utility.copy(new Object[]{tStack.getOutput(3)});
            return true;
         }
      }

      if(this.mFluid != null) {
         ItemStack tStack1 = GT_Utility.fillFluidContainer(this.mFluid, new ItemStack(Item.field_77788_aw, 1));
         FluidStack tFluid = GT_Utility.getFluidForFilledItem(tStack1);
         if(tStack1 != null && tFluid != null) {
            tStack1.field_77994_a = this.mFluid.amount / tFluid.amount;
            int tAmount = tStack1.field_77994_a;
            GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sElectrolyzerRecipes, new ItemStack[]{tStack1, this.mInventory[1]});
            if(tRecipe != null && this.spaceForOutput(tRecipe) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{tStack1, this.mInventory[1]})) {
               this.mFluid.amount -= (tAmount - tStack1.field_77994_a) * tFluid.amount;
               if(this.mFluid.amount <= 0) {
                  this.mFluid = null;
               }

               if(this.mInventory[1] != null && this.mInventory[1].field_77994_a == 0) {
                  this.mInventory[1] = null;
               }

               this.mMaxProgresstime = tRecipe.mDuration;
               this.mEUt = tRecipe.mEUt;
               this.mOutputItem1 = GT_Utility.copy(new Object[]{tRecipe.getOutput(0)});
               this.mOutputItem2 = GT_Utility.copy(new Object[]{tRecipe.getOutput(1)});
               this.mOutputItem3 = GT_Utility.copy(new Object[]{tRecipe.getOutput(2)});
               if(!GT_Utility.areStacksEqual(tRecipe.getOutput(3), new ItemStack(Item.field_77788_aw, 1))) {
                  this.mOutputItem4 = GT_Utility.copy(new Object[]{tRecipe.getOutput(3)});
               }

               return true;
            }
         }
      }

      return false;
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex > 1;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return GT_Items.Cell_Empty.isStackEqual(aStack)?aIndex == 1:aIndex == 0;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?32:(aSide == 1?29:(aActive?65:64));
   }

   public String[] getInfoData() {
      return new String[]{"Progress:", this.mProgresstime / 20 + "secs", this.mMaxProgresstime / 20 + "secs"};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public String getDescription() {
      return "Give your Water a shocking Experience with this Device";
   }

   public int getTankPressure() {
      return -100;
   }

   public int getCapacity() {
      return '\ufa00';
   }
}
