package gregtechmod.common.redstonecircuits;

import gregtechmod.api.interfaces.IRedstoneCircuitBlock;
import gregtechmod.api.util.GT_CircuitryBehavior;

public class GT_Circuit_Equals extends GT_CircuitryBehavior {

   public GT_Circuit_Equals(int aIndex) {
      super(aIndex);
   }

   public void initParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      aCircuitData[0] = 0;
      aCircuitData[1] = 0;
   }

   public void validateParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      if(aCircuitData[0] < 0) {
         aCircuitData[0] = 0;
      }

      if(aCircuitData[0] > 15) {
         aCircuitData[0] = 15;
      }

      if(aCircuitData[1] < 0) {
         aCircuitData[3] = 0;
      }

      if(aCircuitData[1] > 1) {
         aCircuitData[3] = 1;
      }

   }

   public void onTick(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      byte var10001;
      label17: {
         label16: {
            if(aCircuitData[1] == 0) {
               if(getStrongestRedstone(aRedstoneCircuitBlock) == aCircuitData[0]) {
                  break label16;
               }
            } else if(getStrongestRedstone(aRedstoneCircuitBlock) != aCircuitData[0]) {
               break label16;
            }

            var10001 = 0;
            break label17;
         }

         var10001 = 15;
      }

      aRedstoneCircuitBlock.setRedstone(var10001, aRedstoneCircuitBlock.getOutputFacing());
   }

   public String getName() {
      return "Equals";
   }

   public String getDescription() {
      return "signal == this";
   }

   public String getDataDescription(int[] aCircuitData, int aCircuitDataIndex) {
      switch(aCircuitDataIndex) {
      case 0:
         return "Signal";
      case 1:
         return aCircuitData[1] == 0?"Equal":"Unequal";
      default:
         return "";
      }
   }

   public boolean displayItemStack(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock, int aIndex) {
      return false;
   }

   public String getDataDisplay(int[] aCircuitData, int aCircuitDataIndex) {
      return aCircuitDataIndex > 0?"":null;
   }
}
