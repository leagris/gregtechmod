package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Steel;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_SteelFurnace extends GT_MetaTileEntity_BasicMachine_Steel {

   public GT_MetaTileEntity_SteelFurnace(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_SteelFurnace() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 172);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_SteelFurnace();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[2] != null && null != (this.mOutputItem1 = GT_ModHandler.getSmeltingOutput(this.mInventory[2], true, this.mInventory[3]))) {
         this.mEUt = 6;
         this.mMaxProgresstime = 150;
      }

   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return super.allowPutStack(aIndex, aSide, aStack) && GT_ModHandler.getSmeltingOutput(GT_Utility.copyAmount(64L, new Object[]{aStack}), false, (ItemStack)null) != null;
   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(207)), 10, 1.0F, aX, aY, aZ);
   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public int getFrontFacingInactive() {
      return 360;
   }

   public int getFrontFacingActive() {
      return 361;
   }

   public int getSideFacingInactive() {
      return 362;
   }

   public int getBottomFacingInactive() {
      return 363;
   }

   public String getDescription() {
      return "Better than Bronze Furnii";
   }
}
