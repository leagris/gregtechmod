package gregtechmod.api.interfaces;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import net.minecraft.item.ItemStack;

public interface IOreRecipeRegistrator {

   void registerOre(OrePrefixes var1, Materials var2, String var3, String var4, ItemStack var5);
}
