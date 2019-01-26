package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_ElectricItemClearer extends GT_MetaTileEntity_ElectricBufferSmall {

   public GT_MetaTileEntity_ElectricItemClearer(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_ElectricItemClearer() {}

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

   public int maxEUStore() {
      return 10000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 108);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricItemClearer();
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing() && aSide != this.getBaseMetaTileEntity().getBackFacing();
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.mInventory[0] == null && this.getBaseMetaTileEntity().isUniversalEnergyStored(64 * (int)Math.pow(2.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount())) && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || this.getBaseMetaTileEntity().getTimer() % 20L == 0L || this.mSuccess == 20) && null != (this.mInventory[0] = GT_Utility.suckOneItemStackAt(this.getBaseMetaTileEntity().getWorld(), (double)(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), 2 + this.getBaseMetaTileEntity().getOverclockerUpgradeCount()) - (1 + this.getBaseMetaTileEntity().getOverclockerUpgradeCount())), (double)(this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), 2 + this.getBaseMetaTileEntity().getOverclockerUpgradeCount()) - (1 + this.getBaseMetaTileEntity().getOverclockerUpgradeCount())), (double)(this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), 2 + this.getBaseMetaTileEntity().getOverclockerUpgradeCount()) - (1 + this.getBaseMetaTileEntity().getOverclockerUpgradeCount())), (double)(3 + this.getBaseMetaTileEntity().getOverclockerUpgradeCount() * 2), (double)(3 + this.getBaseMetaTileEntity().getOverclockerUpgradeCount() * 2), (double)(3 + this.getBaseMetaTileEntity().getOverclockerUpgradeCount() * 2)))) {
         this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(this.mInventory[0].field_77994_a * (int)Math.pow(2.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()), true);
         this.mSuccess = 20;
      }

      super.onPostTick();
   }

   public String getDescription() {
      return "Clears out a 3x3x3 in front of it";
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == aFacing) {
         return 117 + (aRedstone?8:0);
      } else if(GT_Utility.getOppositeSide(aSide) == aFacing) {
         return 113 + (aRedstone?8:0);
      } else {
         int tIndex = 128 + (aRedstone?8:0);
         switch(aFacing) {
         case 0:
            return tIndex + 64;
         case 1:
            return tIndex + 32;
         case 2:
            switch(aSide) {
            case 0:
               return tIndex + 32;
            case 1:
               return tIndex + 32;
            case 2:
            case 3:
            default:
               break;
            case 4:
               return tIndex + 16;
            case 5:
               return tIndex + 48;
            }
         case 3:
            switch(aSide) {
            case 0:
               return tIndex + 64;
            case 1:
               return tIndex + 64;
            case 2:
            case 3:
            default:
               break;
            case 4:
               return tIndex + 48;
            case 5:
               return tIndex + 16;
            }
         case 4:
            switch(aSide) {
            case 0:
               return tIndex + 48;
            case 1:
               return tIndex + 16;
            case 2:
               return tIndex + 48;
            case 3:
               return tIndex + 16;
            }
         case 5:
            switch(aSide) {
            case 0:
               return tIndex + 16;
            case 1:
               return tIndex + 48;
            case 2:
               return tIndex + 16;
            case 3:
               return tIndex + 48;
            }
         default:
            return tIndex;
         }
      }
   }
}
