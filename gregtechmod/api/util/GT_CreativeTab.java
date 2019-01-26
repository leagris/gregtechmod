package gregtechmod.api.util;

import cpw.mods.fml.common.registry.LanguageRegistry;
import gregtechmod.api.enums.GT_Items;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class GT_CreativeTab extends CreativeTabs {

   public GT_CreativeTab() {
      super("GregTech");
      LanguageRegistry.instance().addStringLocalization("itemGroup.GregTech", "GregTech Intergalactical");
   }

   public ItemStack getIconItemStack() {
      return GT_Items.Tool_Cheat.getUndamaged(1L, new Object[]{new ItemStack(Block.field_72083_ai, 1)});
   }
}
