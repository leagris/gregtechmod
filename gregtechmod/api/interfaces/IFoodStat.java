package gregtechmod.api.interfaces;

import gregtechmod.api.items.GT_MetaGenerated_Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public interface IFoodStat {

   int getFoodLevel(GT_MetaGenerated_Item var1, ItemStack var2, EntityPlayer var3);

   float getSaturation(GT_MetaGenerated_Item var1, ItemStack var2, EntityPlayer var3);

   void onEaten(GT_MetaGenerated_Item var1, ItemStack var2, EntityPlayer var3);

   boolean alwaysEdible(GT_MetaGenerated_Item var1, ItemStack var2, EntityPlayer var3);

   EnumAction getFoodAction(GT_MetaGenerated_Item var1, ItemStack var2);
}
