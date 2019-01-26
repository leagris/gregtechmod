package gregtechmod.api.items;

import gregtechmod.api.items.GT_Generic_Item;
import net.minecraft.item.ItemStack;

public class GT_Durable_Item extends GT_Generic_Item {

   public GT_Durable_Item(int aID, String aUnlocalized, String aEnglish, String aTooltip, int aMaxDamage) {
      super(aID, aUnlocalized, aEnglish, aTooltip);
      this.func_77656_e(aMaxDamage);
      this.func_77625_d(1);
   }

   public int func_77619_b() {
      return 0;
   }

   public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {
      return false;
   }

   public boolean func_82789_a(ItemStack aStack, ItemStack aSecondStack) {
      return false;
   }

   public boolean func_77636_d(ItemStack par1ItemStack) {
      return false;
   }
}
