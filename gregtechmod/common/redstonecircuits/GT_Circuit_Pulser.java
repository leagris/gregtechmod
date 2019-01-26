package gregtechmod.common.redstonecircuits;

import gregtechmod.api.interfaces.IRedstoneCircuitBlock;
import gregtechmod.api.util.GT_CircuitryBehavior;

public class GT_Circuit_Pulser extends GT_CircuitryBehavior {

   public GT_Circuit_Pulser(int aIndex) {
      super(aIndex);
   }

   public void initParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      aCircuitData[0] = 1;
      aCircuitData[1] = 16;
      aCircuitData[4] = 0;
   }

   public void validateParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      if(aCircuitData[0] < 1) {
         aCircuitData[0] = 1;
      }

      if(aCircuitData[1] < 0) {
         aCircuitData[1] = 0;
      }

      if(aCircuitData[1] > 16) {
         aCircuitData[1] = 16;
      }

      if(aCircuitData[4] < 0) {
         aCircuitData[4] = 0;
      }

   }

   public void onTick(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      byte tRedstone = aCircuitData[1] == 0?getWeakestNonZeroRedstone(aRedstoneCircuitBlock):getStrongestRedstone(aRedstoneCircuitBlock);
      if(aCircuitData[4] == 0) {
         aCircuitData[5] = tRedstone;
      }

      if(tRedstone > 0 || aCircuitData[4] > 0) {
         int var10003 = aCircuitData[4];
         int var10000 = aCircuitData[4];
         aCircuitData[4] = var10003 + 1;
         if(var10000 >= aCircuitData[0] && tRedstone <= 0) {
            aCircuitData[4] = 0;
         }
      }

      aRedstoneCircuitBlock.setRedstone(aCircuitData[4] > 0 && aCircuitData[4] <= aCircuitData[0]?(aCircuitData[1] > 0 && aCircuitData[1] <= 15?(byte)aCircuitData[1]:(byte)aCircuitData[5]):0, aRedstoneCircuitBlock.getOutputFacing());
   }

   public String getName() {
      return "Pulser";
   }

   public String getDescription() {
      return "Limits&Enlengths";
   }

   public String getDataDescription(int[] aCircuitData, int aCircuitDataIndex) {
      switch(aCircuitDataIndex) {
      case 0:
         return "Length";
      case 1:
         return "RS Out";
      default:
         return "";
      }
   }

   public boolean displayItemStack(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock, int aIndex) {
      return false;
   }

   public String getDataDisplay(int[] aCircuitData, int aCircuitDataIndex) {
      if(aCircuitDataIndex == 1) {
         if(aCircuitData[aCircuitDataIndex] == 16) {
            return "HIGHEST";
         }

         if(aCircuitData[aCircuitDataIndex] == 0) {
            return "LOWEST";
         }
      }

      return aCircuitDataIndex > 1?"":null;
   }
}
