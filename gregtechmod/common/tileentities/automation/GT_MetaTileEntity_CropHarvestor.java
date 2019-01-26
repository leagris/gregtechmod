package gregtechmod.common.tileentities.automation;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_CropHarvestor extends GT_MetaTileEntity_ElectricBufferSmall {

   public GT_MetaTileEntity_CropHarvestor(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_CropHarvestor() {}

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

   public int getMinimumStoredEU() {
      return 1000 + (int)Math.pow(4.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()) * 100;
   }

   public int maxEUStore() {
      return 10000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 110);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_CropHarvestor();
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isAllowedToWork() && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || this.getBaseMetaTileEntity().getTimer() % 200L == 0L)) {
         int tOverclockerFactor = (int)Math.pow(4.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount());
         int i = 1;

         for(int j = (int)Math.pow(2.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()); i <= j; ++i) {
            int tX = this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), i);
            short tY = this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), i);
            int tZ = this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), i);
            TileEntity tTileEntity = this.getBaseMetaTileEntity().getTileEntityAtSideAndDistance(this.getBaseMetaTileEntity().getFrontFacing(), i);

            try {
               if(tTileEntity != null && tTileEntity instanceof ICropTile) {
                  if(!Crops.instance.getCropList()[((ICropTile)tTileEntity).getID()].canGrow((ICropTile)tTileEntity) && Crops.instance.getCropList()[((ICropTile)tTileEntity).getID()].canBeHarvested((ICropTile)tTileEntity) && this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(100 * tOverclockerFactor, false)) {
                     ((ICropTile)tTileEntity).harvest(false);
                     break;
                  }
               } else {
                  Block e = this.getBaseMetaTileEntity().getBlockAtSideAndDistance(this.getBaseMetaTileEntity().getFrontFacing(), i);
                  if(e != null && !this.getBaseMetaTileEntity().getAirAtSideAndDistance(this.getBaseMetaTileEntity().getFrontFacing(), i)) {
                     float tHardness = e.func_71934_m(this.getBaseMetaTileEntity().getWorld(), tX, tY, tZ);
                     if(tHardness < 0.0F) {
                        break;
                     }

                     ItemStack tStack = new ItemStack(e, 1, this.getBaseMetaTileEntity().getMetaIDAtSideAndDistance(this.getBaseMetaTileEntity().getFrontFacing(), i));
                     if((OrePrefixes.log.contains(tStack) || OrePrefixes.wood.contains(tStack) || OrePrefixes.treeLeaves.contains(tStack)) && this.getBaseMetaTileEntity().decreaseStoredEnergyUnits((int)Math.max(tHardness, 1.0F) * 100 * tOverclockerFactor, false)) {
                        this.getBaseMetaTileEntity().getWorld().func_94578_a(tX, tY, tZ, true);
                     }
                     break;
                  }
               }
            } catch (Throwable var11) {
               ;
            }

            if(this.mInventory[0] == null && this.getBaseMetaTileEntity().isUniversalEnergyStored(32 * tOverclockerFactor) && (this.mInventory[0] = GT_Utility.suckOneItemStackAt(this.getBaseMetaTileEntity().getWorld(), (double)tX - 0.5D, (double)tY - 0.5D, (double)tZ - 0.5D, 2.0D, 2.0D, 2.0D)) != null) {
               this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(Math.max(32, this.mInventory[0].field_77994_a * tOverclockerFactor / 2), true);
               break;
            }
         }
      }

      super.onPostTick();
   }

   public String getDescription() {
      return "Harvests the Cropsticks and Logs in front of it";
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing() && aSide != this.getBaseMetaTileEntity().getBackFacing();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == aFacing) {
         return 118 + (aRedstone?8:0);
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
