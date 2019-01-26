package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Recycler extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Recycler(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Recycler() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 134);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Recycler();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[2] != null && this.spaceForOutput(GT_Items.IC2_Scrap.get(1L, new Object[0]), (ItemStack)null)) {
         this.mOutputItem1 = GT_ModHandler.getRecyclerOutput(this.mInventory[2], this.getBaseMetaTileEntity().getRandomNumber(8));
         this.mEUt = 1;
         this.mMaxProgresstime = 45;
         --this.mInventory[2].field_77994_a;
      }

   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(204)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public int getFrontFacingInactive() {
      return 248;
   }

   public int getFrontFacingActive() {
      return 249;
   }

   public int getTopFacingInactive() {
      return 232;
   }

   public int getTopFacingActive() {
      return 233;
   }

   public String getDescription() {
      return "compress, burn, obliterate and filter EVERYTHING";
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return super.allowPutStack(aIndex, aSide, aStack) && GT_ModHandler.getRecyclerOutput(GT_Utility.copyAmount(64L, new Object[]{aStack}), 0) != null;
   }
}
