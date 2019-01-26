package gregtechmod.common.tileentities.automation;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Scrapboxinator extends GT_MetaTileEntity_ElectricBufferSmall {

   public GT_MetaTileEntity_Scrapboxinator(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Scrapboxinator() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isOverclockerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public int getMinimumStoredEU() {
      return 2000;
   }

   public int maxEUStore() {
      return 10000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int getInvSize() {
      return 3;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 111);
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isUniversalEnergyStored(500) && (this.getBaseMetaTileEntity().getTimer() % 200L == 0L || this.mSuccess > 0 && this.getBaseMetaTileEntity().getTimer() % 5L == 0L || this.mSuccess >= 20) && this.mInventory[0] == null && this.mInventory[1] != null && this.mInventory[1].field_77994_a > 0) {
         if(GT_Items.IC2_Scrapbox.isStackEqual(this.mInventory[1])) {
            if((this.mInventory[0] = GT_ModHandler.getRandomScrapboxDrop()) != null) {
               this.getBaseMetaTileEntity().func_70298_a(1, 1);
               this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(100, true);
               this.mSuccess = 30;
            }
         } else if(GT_Items.IC2_Scrap.isStackEqual(this.mInventory[1])) {
            if(this.mInventory[1].field_77994_a > 8 && (this.mInventory[0] = GT_ModHandler.getRandomScrapboxDrop()) != null) {
               this.getBaseMetaTileEntity().func_70298_a(1, 9);
               this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(200, true);
               this.mSuccess = 30;
            }
         } else {
            this.mInventory[0] = this.mInventory[1];
            this.mInventory[1] = null;
         }
      }

      super.onPostTick();
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 1 && aSide != this.getBaseMetaTileEntity().getBackFacing() && (GT_Items.IC2_Scrap.isStackEqual(aStack) || GT_Items.IC2_Scrapbox.isStackEqual(aStack));
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Scrapboxinator();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return GT_Utility.getOppositeSide(aSide) == aFacing?113 + (aRedstone?8:0):119 + (aRedstone?8:0);
   }

   public String getDescription() {
      return "Makes Scrapboxes and Scrap into random Stuff!";
   }
}
