package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_BlastFurnace extends MetaTileEntity {

   public int mProgresstime = 0;
   public int mMaxProgresstime = 0;
   public int mEUt = 0;
   public int mHeatCapacity = 0;
   public int mUpdate = 5;
   public int mHeatingCoilTier = 0;
   public ItemStack mOutputItem1;
   public ItemStack mOutputItem2;
   public boolean mMachine = false;


   public GT_MetaTileEntity_BlastFurnace(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BlastFurnace() {}

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
      return aFacing > 1;
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
      return 4;
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
      return new GT_MetaTileEntity_BlastFurnace();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mEUt", this.mEUt);
      aNBT.func_74768_a("mProgresstime", this.mProgresstime);
      aNBT.func_74768_a("mMaxProgresstime", this.mMaxProgresstime);
      aNBT.func_74774_a("mHeatingCoilTier", (byte)this.mHeatingCoilTier);
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
      this.mUpdate = 5;
      this.mEUt = aNBT.func_74762_e("mEUt");
      this.mProgresstime = aNBT.func_74762_e("mProgresstime");
      this.mMaxProgresstime = aNBT.func_74762_e("mMaxProgresstime");
      this.mHeatingCoilTier = aNBT.func_74771_c("mHeatingCoilTier");
      NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.func_74781_a("mOutputItem1");
      if(tNBT1 != null) {
         this.mOutputItem1 = GT_Utility.loadItem(tNBT1);
      }

      NBTTagCompound tNBT2 = (NBTTagCompound)aNBT.func_74781_a("mOutputItem2");
      if(tNBT2 != null) {
         this.mOutputItem2 = GT_Utility.loadItem(tNBT2);
      }

   }

   public void setItemNBT(NBTTagCompound aNBT) {
      if(this.mHeatingCoilTier > 0) {
         aNBT.func_74774_a("mHeatingCoilTier", (byte)this.mHeatingCoilTier);
      }

   }

   public void onRightclick(EntityPlayer aPlayer) {
      ItemStack tPlayerItem = aPlayer.field_71071_by.func_70448_g();
      if(this.mHeatingCoilTier <= 0 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier01")) {
         if(tPlayerItem.field_77994_a > 3 || aPlayer.field_71075_bZ.field_75098_d) {
            if(!aPlayer.field_71075_bZ.field_75098_d) {
               tPlayerItem.field_77994_a -= 4;
            }

            this.mHeatingCoilTier = 1;
            this.mUpdate = 5;
         }

      } else if(this.mHeatingCoilTier == 1 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier02")) {
         if(tPlayerItem.field_77994_a > 3 || aPlayer.field_71075_bZ.field_75098_d) {
            if(!aPlayer.field_71075_bZ.field_75098_d) {
               tPlayerItem.field_77994_a -= 4;
            }

            this.mHeatingCoilTier = 2;
            this.mUpdate = 5;
         }

      } else if(this.mHeatingCoilTier == 2 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier03")) {
         if(tPlayerItem.field_77994_a > 3 || aPlayer.field_71075_bZ.field_75098_d) {
            if(!aPlayer.field_71075_bZ.field_75098_d) {
               tPlayerItem.field_77994_a -= 4;
            }

            this.mHeatingCoilTier = 3;
            this.mUpdate = 5;
         }

      } else {
         this.getBaseMetaTileEntity().openGUI(aPlayer, 113);
      }
   }

   private boolean checkMachine() {
      int xDir = ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getFrontFacing()).offsetX * 2;
      int yDir = ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getFrontFacing()).offsetY * 2;
      int zDir = ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getFrontFacing()).offsetZ * 2;
      this.mHeatCapacity = this.mHeatingCoilTier * 500;

      for(int i = -1; i < 2; ++i) {
         for(int j = 0; j < 4; ++j) {
            for(int k = -1; k < 2; ++k) {
               Block tBlock = this.getBaseMetaTileEntity().getBlockOffset(-xDir + i, -yDir + j, -zDir + k);
               if(i == 0 && (j == 1 || j == 2) && k == 0) {
                  if(GT_Utility.arrayContains(tBlock, new Object[]{Block.field_71938_D, Block.field_71944_C})) {
                     this.mHeatCapacity += 250;
                  } else if(!this.getBaseMetaTileEntity().getAirOffset(-xDir + i, -yDir + j, -zDir + k)) {
                     return false;
                  }
               } else {
                  if(tBlock != GregTech_API.sBlockList[0]) {
                     return false;
                  }

                  byte tMeta = this.getBaseMetaTileEntity().getMetaIDOffset(-xDir + i, -yDir + j, -zDir + k);
                  if(tMeta == 13) {
                     this.mHeatCapacity += 30;
                  } else if(tMeta == 14) {
                     this.mHeatCapacity += 50;
                  } else {
                     if(tMeta != 15) {
                        return false;
                     }

                     this.mHeatCapacity += 70;
                  }
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
            if(!this.mMachine) {
               this.mHeatCapacity = 0;
            }
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

   }

   private boolean spaceForOutput(GT_Recipe aRecipe) {
      return (this.mInventory[2] == null || aRecipe.getOutput(0) == null || this.mInventory[2].field_77994_a + aRecipe.getOutput(0).field_77994_a <= this.mInventory[2].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[2], aRecipe.getOutput(0))) && (this.mInventory[3] == null || aRecipe.getOutput(1) == null || this.mInventory[3].field_77994_a + aRecipe.getOutput(1).field_77994_a <= this.mInventory[3].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[3], aRecipe.getOutput(1)));
   }

   private boolean checkRecipe() {
      if(!this.mMachine) {
         return false;
      } else {
         GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(true, false, GT_Recipe.sBlastRecipes, new ItemStack[]{this.mInventory[0], this.mInventory[1]});
         if(tRecipe != null && this.mHeatCapacity >= tRecipe.mStartEU && this.spaceForOutput(tRecipe) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[0], this.mInventory[1]})) {
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
            return true;
         } else {
            return false;
         }
      }
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?68 + (aActive?1:0):(GT_Utility.getOppositeSide(aSide) == aFacing?71:72);
   }

   public String[] getInfoData() {
      return new String[]{"Progress:", this.mProgresstime / 20 + "secs", this.mMaxProgresstime / 20 + "secs"};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public String getDescription() {
      return "You\'ll have a Blast!";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex > 1;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex < 2 && !GT_Utility.areStacksEqual(aStack, this.mInventory[aIndex == 0?1:0]);
   }
}
