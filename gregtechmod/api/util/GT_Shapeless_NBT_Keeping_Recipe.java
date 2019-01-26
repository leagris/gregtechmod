package gregtechmod.api.util;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class GT_Shapeless_NBT_Keeping_Recipe extends ShapelessOreRecipe {

   public GT_Shapeless_NBT_Keeping_Recipe(ItemStack aResult, Object ... aRecipe) {
      super(aResult, aRecipe);
   }

   public boolean func_77569_a(InventoryCrafting aGrid, World aWorld) {
      ItemStack tStack = null;

      for(int i = 0; i < aGrid.func_70302_i_(); ++i) {
         if(aGrid.func_70301_a(i) != null) {
            if(tStack != null && (tStack.func_77942_o() != aGrid.func_70301_a(i).func_77942_o() || tStack.func_77942_o() && !tStack.func_77978_p().equals(aGrid.func_70301_a(i).func_77978_p()))) {
               return false;
            }

            tStack = aGrid.func_70301_a(i);
         }
      }

      return super.func_77569_a(aGrid, aWorld);
   }

   public ItemStack func_77572_b(InventoryCrafting aGrid) {
      ItemStack rStack = super.func_77572_b(aGrid);
      if(rStack != null) {
         for(int i = 0; i < aGrid.func_70302_i_(); ++i) {
            if(aGrid.func_70301_a(i) != null && aGrid.func_70301_a(i).func_77942_o()) {
               rStack.func_77982_d((NBTTagCompound)aGrid.func_70301_a(i).func_77978_p().func_74737_b());
               break;
            }
         }
      }

      return rStack;
   }
}
