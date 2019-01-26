package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GT_MetaOre_Item extends ItemBlock {

   public GT_MetaOre_Item(int par1) {
      super(par1);
      this.func_77656_e(0);
      this.func_77627_a(true);
      this.func_77637_a(GregTech_API.TAB_GREGTECH);
   }

   public int func_77647_b(int par1) {
      return par1;
   }

   public String func_77667_c(ItemStack aStack) {
      return this.func_77658_a() + "." + aStack.func_77960_j();
   }
}
