package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Extruder extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Extruder(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Extruder() {}

   public int getElectricTier() {
      return 2;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 181);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Extruder();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[1] != null && this.mInventory[1].field_77994_a > 0 && this.mInventory[2] != null && this.mInventory[2].field_77994_a > 0) {
         GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sExtruderRecipes, new ItemStack[]{this.mInventory[1], this.mInventory[2]});
         if(tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), (ItemStack)null) && tRecipe.isRecipeInputEqual(false, true, new ItemStack[]{this.mInventory[1], this.mInventory[2]})) {
            this.mEUt = tRecipe.mEUt;
            this.mMaxProgresstime = tRecipe.mDuration;
            this.mOutputItem1 = tRecipe.getOutput(0);
            return;
         }
      }

   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(208)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 1 && super.allowPutStack(aIndex, aSide, aStack) && GT_Recipe.findEqualRecipe(false, true, GT_Recipe.sExtruderRecipes, new ItemStack[]{GT_Utility.copyAmount(64L, new Object[]{aStack}), this.mInventory[2]}) != null;
   }

   public int getTopFacingInactive() {
      return 405;
   }

   public int getTopFacingActive() {
      return 406;
   }

   public int getSideFacingInactive() {
      return 242;
   }

   public int getSideFacingActive() {
      return 226;
   }

   public int getFrontFacingInactive() {
      return 407;
   }

   public int getFrontFacingActive() {
      return 408;
   }

   public String getDescription() {
      return "Universal Machine for Metal Working";
   }
}
