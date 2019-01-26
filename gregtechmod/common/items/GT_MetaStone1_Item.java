package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GT_MetaStone1_Item extends ItemBlock {

   public GT_MetaStone1_Item(int par1) {
      super(par1);
      this.func_77656_e(0);
      this.func_77627_a(true);
      this.func_77637_a(GregTech_API.TAB_GREGTECH);
   }

   public int func_77647_b(int par1) {
      return par1;
   }

   public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean par4) {
      if(this.getDamage(aStack) % 8 > 2) {
         aList.add("Mobs can\'t spawn on this Block");
      }

   }

   public String func_77667_c(ItemStack aStack) {
      return this.func_77658_a() + "." + this.getDamage(aStack);
   }
}
