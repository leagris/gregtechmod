package gregtechmod.common.items;

import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class GT_Mortar_Item extends GT_Generic_Item {

   ItemStack mBrokenItem;


   public GT_Mortar_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, ItemStack aBrokenItem) {
      super(aID, aUnlocalized, aEnglish, "Used to turn Ingots into Dust");
      this.func_77656_e(aMaxDamage - 1);
      this.func_77625_d(1);
      this.setNoRepair();
      this.mBrokenItem = aBrokenItem;
   }

   public ItemStack getContainerItemStack(ItemStack aStack) {
      return aStack.func_77960_j() >= this.func_77612_l()?GT_Utility.copy(new Object[]{this.mBrokenItem}):new ItemStack(this, 1, aStack.func_77960_j() + 1);
   }

   public boolean func_77634_r() {
      return true;
   }

   public boolean func_77630_h(ItemStack par1ItemStack) {
      return false;
   }
}
