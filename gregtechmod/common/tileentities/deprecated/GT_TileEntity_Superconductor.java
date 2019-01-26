package gregtechmod.common.tileentities.deprecated;

import gregtechmod.common.tileentities.deprecated.GT_TileEntityMetaID_Machine;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.entity.player.EntityPlayer;

public class GT_TileEntity_Superconductor extends GT_TileEntityMetaID_Machine implements IEnergyConductor {

   public boolean isFacingValid(int aFacing) {
      return false;
   }

   public boolean isAccessible(EntityPlayer aPlayer) {
      return false;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isOutputFacing(short aDirection) {
      return true;
   }

   public boolean isInputFacing(short aDirection) {
      return true;
   }

   public int maxEUInput() {
      return Integer.MAX_VALUE;
   }

   public int maxEUOutput() {
      return Integer.MAX_VALUE;
   }

   public String func_70303_b() {
      return "";
   }

   public float getWrenchDropRate() {
      return 1.0F;
   }

   public int getTexture(int aSide, int aMeta) {
      if(this.field_70329_l == 0 && this.field_70330_m == 0 && this.field_70327_n == 0) {
         return 103;
      } else {
         boolean[] tConnectedSides = new boolean[]{this.field_70331_k.func_72796_p(this.field_70329_l, this.field_70330_m - 1, this.field_70327_n) instanceof IEnergyTile, this.field_70331_k.func_72796_p(this.field_70329_l, this.field_70330_m + 1, this.field_70327_n) instanceof IEnergyTile, this.field_70331_k.func_72796_p(this.field_70329_l + 1, this.field_70330_m, this.field_70327_n) instanceof IEnergyTile, this.field_70331_k.func_72796_p(this.field_70329_l, this.field_70330_m, this.field_70327_n + 1) instanceof IEnergyTile, this.field_70331_k.func_72796_p(this.field_70329_l - 1, this.field_70330_m, this.field_70327_n) instanceof IEnergyTile, this.field_70331_k.func_72796_p(this.field_70329_l, this.field_70330_m, this.field_70327_n - 1) instanceof IEnergyTile};
         switch(aSide) {
         case 0:
            if(tConnectedSides[0]) {
               return 103;
            } else if(tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return 102;
            } else if(!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return 98;
            } else if(tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return 99;
            } else if(tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
               return 100;
            } else if(tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
               return 101;
            } else if(!tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return 104;
            } else if(tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
               return 105;
            } else if(tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
               return 106;
            } else if(!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
               return 107;
            } else if(!tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
               return 103;
            } else if(!tConnectedSides[4] && !tConnectedSides[2]) {
               return 97;
            } else if(!tConnectedSides[5] && !tConnectedSides[3]) {
               return 96;
            }
         case 1:
            if(tConnectedSides[1]) {
               return 103;
            } else if(tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return 102;
            } else if(!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return 98;
            } else if(tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return 99;
            } else if(tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
               return 100;
            } else if(tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
               return 101;
            } else if(!tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return 104;
            } else if(tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
               return 105;
            } else if(tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
               return 106;
            } else if(!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
               return 107;
            } else if(!tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
               return 103;
            } else if(!tConnectedSides[2] && !tConnectedSides[4]) {
               return 97;
            } else if(!tConnectedSides[3] && !tConnectedSides[5]) {
               return 96;
            }
         case 2:
            if(tConnectedSides[5]) {
               return 103;
            } else if(tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return 102;
            } else if(!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return 98;
            } else if(tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return 101;
            } else if(tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
               return 100;
            } else if(tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
               return 99;
            } else if(!tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return 107;
            } else if(tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
               return 106;
            } else if(tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
               return 105;
            } else if(!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
               return 104;
            } else if(!tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
               return 103;
            } else if(!tConnectedSides[2] && !tConnectedSides[4]) {
               return 97;
            } else if(!tConnectedSides[0] && !tConnectedSides[1]) {
               return 96;
            }
         case 3:
            if(tConnectedSides[3]) {
               return 103;
            } else if(tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return 102;
            } else if(!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return 100;
            } else if(tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return 101;
            } else if(tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
               return 98;
            } else if(tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
               return 99;
            } else if(!tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return 106;
            } else if(tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
               return 107;
            } else if(tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
               return 104;
            } else if(!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
               return 105;
            } else if(!tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
               return 103;
            } else if(!tConnectedSides[2] && !tConnectedSides[4]) {
               return 97;
            } else if(!tConnectedSides[0] && !tConnectedSides[1]) {
               return 96;
            }
         case 4:
            if(tConnectedSides[4]) {
               return 103;
            } else if(tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return 102;
            } else if(!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return 101;
            } else if(tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return 100;
            } else if(tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
               return 99;
            } else if(tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
               return 98;
            } else if(!tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return 106;
            } else if(tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
               return 105;
            } else if(tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
               return 104;
            } else if(!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
               return 107;
            } else if(!tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
               return 103;
            } else if(!tConnectedSides[0] && !tConnectedSides[1]) {
               return 96;
            } else if(!tConnectedSides[3] && !tConnectedSides[5]) {
               return 97;
            }
         case 5:
            if(tConnectedSides[2]) {
               return 103;
            } else if(tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return 102;
            } else if(!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return 101;
            } else if(tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return 98;
            } else if(tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
               return 99;
            } else if(tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
               return 100;
            } else if(!tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return 107;
            } else if(tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
               return 104;
            } else if(tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
               return 105;
            } else if(!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
               return 106;
            } else if(!tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
               return 103;
            } else if(!tConnectedSides[0] && !tConnectedSides[1]) {
               return 96;
            } else if(!tConnectedSides[3] && !tConnectedSides[5]) {
               return 97;
            }
         default:
            return 103;
         }
      }
   }

   public double getConductionLoss() {
      return 0.0D;
   }

   public int getInsulationEnergyAbsorption() {
      return 8192;
   }

   public int getInsulationBreakdownEnergy() {
      return Integer.MAX_VALUE;
   }

   public int getConductorBreakdownEnergy() {
      return Integer.MAX_VALUE;
   }

   public void removeInsulation() {
      this.doExplosion(10000);
   }

   public void removeConductor() {
      this.doExplosion(10000);
   }

   public void doEnergyExplosion() {}
}
