package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_VacuumFreezer extends MetaTileEntity {

   public int mProgresstime = 0;
   public int mMaxProgresstime = 0;
   public int mEUt = 0;
   public int mUpdate = 5;
   public ItemStack mOutputItem1;
   public boolean mMachine = false;


   public GT_MetaTileEntity_VacuumFreezer(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_VacuumFreezer() {}

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
      return 2;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 122);
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
      return new GT_MetaTileEntity_VacuumFreezer();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mEUt", this.mEUt);
      aNBT.func_74768_a("mProgresstime", this.mProgresstime);
      aNBT.func_74768_a("mMaxProgresstime", this.mMaxProgresstime);
      if(this.mOutputItem1 != null) {
         NBTTagCompound tNBT = new NBTTagCompound();
         this.mOutputItem1.func_77955_b(tNBT);
         aNBT.func_74782_a("mOutputItem1", tNBT);
      }

   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mUpdate = 5;
      this.mEUt = aNBT.func_74762_e("mEUt");
      this.mProgresstime = aNBT.func_74762_e("mProgresstime");
      this.mMaxProgresstime = aNBT.func_74762_e("mMaxProgresstime");
      NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.func_74781_a("mOutputItem1");
      if(tNBT1 != null) {
         this.mOutputItem1 = GT_Utility.loadItem(tNBT1);
      }

   }

   private boolean checkMachine() {
      for(int i = -1; i < 2; ++i) {
         for(int j = -1; j < 2; ++j) {
            for(int k = -1; k < 2; ++k) {
               if(i == 0 && j == 0 && k == 0) {
                  if(!this.getBaseMetaTileEntity().getAirOffset(i, j - 2, k)) {
                     return false;
                  }
               } else {
                  if(this.getBaseMetaTileEntity().getBlockOffset(i, j - 2, k) != GregTech_API.sBlockList[0]) {
                     return false;
                  }

                  if(this.getBaseMetaTileEntity().getMetaIDOffset(i, j - 2, k) != ((i != 0 || j != 0 || k == 0) && (i != 0 || j == 0 || k != 0) && (i == 0 || j != 0 || k != 0)?14:15)) {
                     return false;
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
         if(this.mInventory[1] == null) {
            this.mInventory[1] = GT_Utility.copy(new Object[]{this.mOutputItem1});
         } else if(GT_Utility.areStacksEqual(this.mInventory[1], this.mOutputItem1)) {
            this.mInventory[1].field_77994_a = Math.min(this.mOutputItem1.func_77976_d(), this.mOutputItem1.field_77994_a + this.mInventory[1].field_77994_a);
         }
      }

   }

   private boolean spaceForOutput(GT_Recipe aRecipe) {
      return this.mInventory[1] == null || aRecipe.getOutput(0) == null || this.mInventory[1].field_77994_a + aRecipe.getOutput(0).field_77994_a <= this.mInventory[1].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[1], aRecipe.getOutput(0));
   }

   private boolean checkRecipe() {
      if(!this.mMachine) {
         return false;
      } else {
         GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(true, false, GT_Recipe.sVacuumRecipes, new ItemStack[]{this.mInventory[0]});
         if(tRecipe != null && this.spaceForOutput(tRecipe) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[0], null})) {
            if(this.mInventory[0] != null && this.mInventory[0].field_77994_a == 0) {
               this.mInventory[0] = null;
            }

            this.mMaxProgresstime = tRecipe.mDuration;
            this.mEUt = tRecipe.mEUt;
            this.mOutputItem1 = GT_Utility.copy(new Object[]{tRecipe.getOutput(0)});
            return true;
         } else {
            return false;
         }
      }
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 1;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0 && GT_Recipe.findEqualRecipe(true, true, GT_Recipe.sVacuumRecipes, new ItemStack[]{GT_Utility.copyAmount(64L, new Object[]{aStack})}) != null;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 1?68 + (aActive?1:0):(aSide == 0?71:77);
   }

   public String[] getInfoData() {
      return new String[]{"Progress:", this.mProgresstime / 20 + "secs", this.mMaxProgresstime / 20 + "secs"};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public String getDescription() {
      return "Cools down anything";
   }
}
