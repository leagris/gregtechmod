package gregtechmod.api.items;

import gregtechmod.api.items.GT_RadioactiveCell_Item;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.item.ItemStack;

public class GT_RadioactiveCellIC_Item extends GT_RadioactiveCell_Item implements IReactorComponent {

   public GT_RadioactiveCellIC_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDelay, int aCellcount, int aPulseRate, ItemStack aDepleted) {
      super(aID, aUnlocalized, aEnglish, aMaxDelay, aCellcount, aPulseRate, aDepleted);
   }

   public boolean acceptUraniumPulse(IReactor aReactor, ItemStack aStack, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean aHeatRun) {
      return aStack.field_77994_a != 1?false:aReactor.addOutput(1.0F) > 0.0F;
   }

   public boolean canStoreHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
      return false;
   }

   public int getMaxHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
      return 0;
   }

   public int getCurrentHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
      return 0;
   }

   public float influenceExplosion(IReactor aReactor, ItemStack aStack) {
      return (float)(aStack.field_77994_a * this.cellCount * (this.pulserate > 0?this.pulserate:1));
   }

   public int alterHeat(IReactor aReactor, ItemStack aStack, int x, int y, int aHeat) {
      return aHeat;
   }

   public void processChamber(IReactor aReactor, ItemStack aStack, int x, int y, boolean aHeatRun) {}

   protected class ItemStackCoord {

      public ItemStack stack;
      public int x;
      public int y;
      final GT_RadioactiveCell_Item mThis;


      public ItemStackCoord(GT_RadioactiveCell_Item var11, ItemStack var2, int var3, int var4) {
         this.mThis = var11;
         this.stack = var2;
         this.x = var3;
         this.y = var4;
      }
   }
}
