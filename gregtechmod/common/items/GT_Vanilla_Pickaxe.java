package gregtechmod.common.items;

import gregtechmod.common.items.GT_Vanilla_Tool;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class GT_Vanilla_Pickaxe extends GT_Vanilla_Tool {

   public GT_Vanilla_Pickaxe(int aID, String aUnlocalized, String aEnglish, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage + 2.0F, ItemPickaxe.field_77867_c);
      MinecraftForge.setToolClass(this, "pickaxe", this.mHarvestLevel);
   }

   public boolean func_77641_a(Block aBlock) {
      return aBlock == Block.field_72089_ap?this.mHarvestLevel >= 3:(aBlock != Block.field_72071_ax && aBlock != Block.field_72073_aw?(aBlock != Block.field_72068_bR && aBlock != Block.field_72076_bV?(aBlock != Block.field_72105_ah && aBlock != Block.field_71941_G?(aBlock != Block.field_72083_ai && aBlock != Block.field_71949_H?(aBlock != Block.field_71948_O && aBlock != Block.field_71947_N?(aBlock != Block.field_72047_aN && aBlock != Block.field_72048_aO?(aBlock.field_72018_cp == Material.field_76246_e?true:(aBlock.field_72018_cp == Material.field_76243_f?true:aBlock.field_72018_cp == Material.field_82717_g)):this.mHarvestLevel >= 2):this.mHarvestLevel >= 1):this.mHarvestLevel >= 1):this.mHarvestLevel >= 2):this.mHarvestLevel >= 2):this.mHarvestLevel >= 2);
   }

   public float func_77638_a(ItemStack aStack, Block aBlock) {
      return aBlock != null && (aBlock.field_72018_cp == Material.field_76243_f || aBlock.field_72018_cp == Material.field_82717_g || aBlock.field_72018_cp == Material.field_76246_e)?this.field_77864_a:super.func_77638_a(aStack, aBlock);
   }
}
