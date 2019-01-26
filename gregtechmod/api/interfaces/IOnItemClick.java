package gregtechmod.api.interfaces;

import gregtechmod.api.items.GT_MetaGenerated_Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IOnItemClick {

   boolean onItemUse(GT_MetaGenerated_Item var1, ItemStack var2, EntityPlayer var3, World var4, int var5, int var6, int var7, int var8, float var9, float var10, float var11);

   boolean onItemUseFirst(GT_MetaGenerated_Item var1, ItemStack var2, EntityPlayer var3, World var4, int var5, int var6, int var7, int var8, float var9, float var10, float var11);

   ItemStack onItemRightClick(GT_MetaGenerated_Item var1, ItemStack var2, World var3, EntityPlayer var4);
}
