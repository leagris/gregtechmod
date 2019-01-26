package gregtechmod.common.items;

import gregtechmod.api.items.GT_Generic_Item;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.item.ItemStack;

public class GT_NeutronReflector_Item extends GT_Generic_Item implements IReactorComponent {

   public GT_NeutronReflector_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage) {
      super(aID, aUnlocalized, aEnglish, (String)null);
      this.func_77625_d(64);
      this.func_77656_e(aMaxDamage);
   }

   public boolean acceptUraniumPulse(IReactor aReactor, ItemStack aStack, ItemStack pulsingStack, int x, int y, int pulseX, int pulseY, boolean aHeatRun) {
      if(aStack.field_77994_a > 1) {
         return false;
      } else {
         ((IReactorComponent)pulsingStack.func_77973_b()).acceptUraniumPulse(aReactor, pulsingStack, aStack, pulseX, pulseY, x, y, aHeatRun);
         if(this.func_77612_l() > 0) {
            if(aStack.func_77960_j() + 1 >= this.func_77612_l()) {
               aReactor.setItemAt(x, y, (ItemStack)null);
            } else {
               aStack.func_77964_b(aStack.func_77960_j() + 1);
            }
         }

         return true;
      }
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
      return -1.0F;
   }

   public int alterHeat(IReactor aReactor, ItemStack aStack, int x, int y, int aHeat) {
      return aHeat;
   }

   public void processChamber(IReactor aReactor, ItemStack aStack, int x, int y, boolean aHeatRun) {}
}
