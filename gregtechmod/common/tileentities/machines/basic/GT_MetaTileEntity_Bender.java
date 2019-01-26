package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Bender extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Bender(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Bender() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 140);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Bender();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(GT_Utility.isStackValid(this.mInventory[1])) {
         GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sBenderRecipes, new ItemStack[]{this.mInventory[1], this.mInventory[2]});
         if(tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), (ItemStack)null) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[1], this.mInventory[2]})) {
            this.mEUt = tRecipe.mEUt;
            this.mMaxProgresstime = tRecipe.mDuration;
            this.mOutputItem1 = tRecipe.getOutput(0);
            return;
         }

         if(GT_Utility.isStackInvalid(this.mInventory[2])) {
            for(int i = 64; i > 0; --i) {
               tRecipe = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sBenderRecipes, new ItemStack[]{this.mInventory[1], GT_Items.Circuit_Integrated.getWithDamage(0L, (long)i, new Object[0])});
               if(tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), (ItemStack)null) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[1], GT_Items.Circuit_Integrated.getWithDamage(0L, (long)i, new Object[0])})) {
                  this.mEUt = tRecipe.mEUt;
                  this.mMaxProgresstime = tRecipe.mDuration;
                  this.mOutputItem1 = tRecipe.getOutput(0);
                  return;
               }

               tRecipe = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sBenderRecipes, new ItemStack[]{GT_Items.Circuit_Integrated.getWithDamage(0L, (long)i, new Object[0]), this.mInventory[1]});
               if(tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), (ItemStack)null) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{GT_Items.Circuit_Integrated.getWithDamage(0L, (long)i, new Object[0]), this.mInventory[1]})) {
                  this.mEUt = tRecipe.mEUt;
                  this.mMaxProgresstime = tRecipe.mDuration;
                  this.mOutputItem1 = tRecipe.getOutput(0);
                  return;
               }
            }
         }
      }

   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(203)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      if(super.allowPutStack(aIndex, aSide, aStack) && aIndex == 1) {
         if(GT_Utility.isStackValid(this.mInventory[2])) {
            return GT_Recipe.findEqualRecipe(false, true, GT_Recipe.sBenderRecipes, new ItemStack[]{GT_Utility.copyAmount(64L, new Object[]{aStack}), this.mInventory[2]}) != null;
         }

         for(int i = 1; i <= 64; ++i) {
            if(GT_Recipe.findEqualRecipe(false, true, GT_Recipe.sBenderRecipes, new ItemStack[]{GT_Utility.copyAmount(64L, new Object[]{aStack}), GT_Items.Circuit_Integrated.getWithDamage(0L, (long)i, new Object[0])}) != null) {
               return true;
            }
         }
      }

      return false;
   }

   public int getFrontFacingInactive() {
      return 238;
   }

   public int getFrontFacingActive() {
      return 239;
   }

   public String getDescription() {
      return "Boo, he\'s bad! We want BENDER!!!";
   }
}
