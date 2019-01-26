package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_BronzeSteamHammer extends GT_MetaTileEntity_BasicMachine_Bronze {

   public GT_MetaTileEntity_BronzeSteamHammer(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BronzeSteamHammer() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 167);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_BronzeSteamHammer();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[2] != null) {
         GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(true, false, GT_Recipe.sHammerRecipes, new ItemStack[]{this.mInventory[2]});
         if(tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), (ItemStack)null) && tRecipe.isRecipeInputEqual(true, true, new ItemStack[]{this.mInventory[2]})) {
            this.mEUt = tRecipe.mEUt * 2;
            this.mMaxProgresstime = tRecipe.mDuration;
            this.mOutputItem1 = tRecipe.getOutput(0);
            return;
         }
      }

   }

   public void startProcess() {
      this.sendSound((byte)10);
   }

   public void doSound(byte aIndex, double aX, double aY, double aZ) {
      super.doSound(aIndex, aX, aY, aZ);
      if(aIndex == 10) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(1)), 2, 1.0F, 0.01F, aX, aY, aZ);
      }

   }

   public int getFrontFacingInactive() {
      return 336;
   }

   public int getFrontFacingActive() {
      return 337;
   }

   public String getDescription() {
      return "Hammers Ingots into Plates";
   }
}
