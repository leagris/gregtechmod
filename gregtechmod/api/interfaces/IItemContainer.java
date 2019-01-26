package gregtechmod.api.interfaces;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IItemContainer {

   Item getItem();

   Block getBlock();

   boolean isStackEqual(Object var1);

   boolean isStackEqual(Object var1, boolean var2, boolean var3);

   ItemStack get(long var1, Object ... var3);

   ItemStack getWildcard(long var1, Object ... var3);

   ItemStack getUndamaged(long var1, Object ... var3);

   ItemStack getAlmostBroken(long var1, Object ... var3);

   ItemStack getWithDamage(long var1, long var3, Object ... var5);

   IItemContainer registerOre(Object ... var1);

   IItemContainer registerWildcardAsOre(Object ... var1);

   ItemStack getWithCharge(long var1, int var3, Object ... var4);

   boolean hasBeenSet();
}
