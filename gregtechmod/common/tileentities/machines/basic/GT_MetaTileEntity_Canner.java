package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Canner extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Canner(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Canner() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 138);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Canner();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[1] != null || this.mInventory[2] != null) {
         GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(true, false, GT_Recipe.sCannerRecipes, new ItemStack[]{this.mInventory[1], this.mInventory[2]});
         if(tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), tRecipe.getOutput(1)) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[1], this.mInventory[2]})) {
            this.mEUt = tRecipe.mEUt;
            this.mMaxProgresstime = tRecipe.mDuration;
            this.mOutputItem1 = tRecipe.getOutput(0);
            this.mOutputItem2 = tRecipe.getOutput(1);
            return;
         }
      }

      this.mOutputItem1 = null;
   }

   public boolean hasTwoSeperateInputs() {
      return true;
   }

   public int getFrontFacingInactive() {
      return 254;
   }

   public int getFrontFacingActive() {
      return 255;
   }

   public String getDescription() {
      return "Unmobile Food Canning Machine GTA4";
   }
}
