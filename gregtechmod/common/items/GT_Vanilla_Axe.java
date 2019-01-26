package gregtechmod.common.items;

import gregtechmod.common.items.GT_Vanilla_Tool;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class GT_Vanilla_Axe extends GT_Vanilla_Tool {

   public GT_Vanilla_Axe(int aID, String aUnlocalized, String aEnglish, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage + 3.0F, ItemAxe.field_77868_c);
      MinecraftForge.setToolClass(this, "axe", this.mHarvestLevel);
   }

   public float func_77638_a(ItemStack aStack, Block aBlock) {
      return aBlock != null && (aBlock.field_72018_cp == Material.field_76245_d || aBlock.field_72018_cp == Material.field_76254_j || aBlock.field_72018_cp == Material.field_76255_k)?this.field_77864_a:super.func_77638_a(aStack, aBlock);
   }
}
