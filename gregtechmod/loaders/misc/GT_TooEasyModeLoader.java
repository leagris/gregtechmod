package gregtechmod.loaders.misc;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_TooEasyModeLoader implements Runnable {

   public void run() {
      GT_ModHandler.addCraftingRecipe(new ItemStack(Item.field_77788_aw, 111), new Object[]{"T T", " T ", Character.valueOf('T'), "ingotTin"});
      GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Bronze, -1L), new Object[]{"ingotCopper", "ingotCopper", "ingotCopper", "ingotTin"});
   }
}
