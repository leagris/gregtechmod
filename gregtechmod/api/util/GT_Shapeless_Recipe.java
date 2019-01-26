package gregtechmod.api.util;

import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class GT_Shapeless_Recipe extends ShapelessOreRecipe {

   public GT_Shapeless_Recipe(ItemStack aResult, Object ... aRecipe) {
      super(aResult, aRecipe);
   }

   public ItemStack func_77572_b(InventoryCrafting aGrid) {
      ItemStack rStack = super.func_77572_b(aGrid);
      if(rStack != null) {
         int tCharge = 0;

         for(int i = 0; i < aGrid.func_70302_i_(); ++i) {
            tCharge += GT_ModHandler.dischargeElectricItem(aGrid.func_70301_a(i), Integer.MAX_VALUE, 2147483646, true, true, true);
         }

         GT_ModHandler.chargeElectricItem(rStack, tCharge, Integer.MAX_VALUE, true, false);
      }

      return rStack;
   }
}
