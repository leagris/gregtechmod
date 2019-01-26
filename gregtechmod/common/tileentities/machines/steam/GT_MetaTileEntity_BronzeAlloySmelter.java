package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_BronzeAlloySmelter extends GT_MetaTileEntity_BasicMachine_Bronze {

   public GT_MetaTileEntity_BronzeAlloySmelter(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BronzeAlloySmelter() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 166);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_BronzeAlloySmelter();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[1] != null || this.mInventory[2] != null) {
         GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(true, false, GT_Recipe.sAlloySmelterRecipes, new ItemStack[]{this.mInventory[1], this.mInventory[2]});
         if(tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), (ItemStack)null) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[1], this.mInventory[2]})) {
            this.mEUt = tRecipe.mEUt;
            this.mMaxProgresstime = tRecipe.mDuration * 2;
            this.mOutputItem1 = GT_Utility.copy(new Object[]{tRecipe.getOutput(0)});
            return;
         }
      }

      this.mOutputItem1 = null;
   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(208)), 10, 1.0F, aX, aY, aZ);
   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public boolean hasTwoSeperateInputs() {
      return true;
   }

   public int getFrontFacingInactive() {
      return 332;
   }

   public int getFrontFacingActive() {
      return 333;
   }

   public int getSideFacingInactive() {
      return 334;
   }

   public int getBottomFacingInactive() {
      return 335;
   }

   public String getDescription() {
      return "Steam powered combination Smelter";
   }
}
