package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Lathe extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Lathe(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Lathe() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 159);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Lathe();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[2] != null && this.mInventory[2].field_77994_a > 0) {
         GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(true, false, GT_Recipe.sLatheRecipes, new ItemStack[]{this.mInventory[2]});
         if(tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), tRecipe.getOutput(1)) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[2], null})) {
            this.mEUt = tRecipe.mEUt;
            this.mMaxProgresstime = tRecipe.mDuration;
            this.mOutputItem1 = tRecipe.getOutput(0);
            this.mOutputItem2 = tRecipe.getOutput(1);
            return;
         }
      }

   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return super.allowPutStack(aIndex, aSide, aStack) && GT_Recipe.findEqualRecipe(true, true, GT_Recipe.sLatheRecipes, new ItemStack[]{GT_Utility.copyAmount(64L, new Object[]{aStack})}) != null;
   }

   public int getFrontFacingInactive() {
      return 304;
   }

   public int getFrontFacingActive() {
      return 305;
   }

   public String getDescription() {
      return "Produces Metal Rods more efficiently";
   }
}
