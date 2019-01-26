package gregtechmod.common.items;

import gregtechmod.common.items.GT_Vanilla_Tool;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSpade;
import net.minecraftforge.common.MinecraftForge;

public class GT_Vanilla_Shovel extends GT_Vanilla_Tool {

   public GT_Vanilla_Shovel(int aID, String aUnlocalized, String aEnglish, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage + 1.0F, ItemSpade.field_77866_c);
      MinecraftForge.setToolClass(this, "shovel", this.mHarvestLevel);
   }

   public boolean func_77641_a(Block aBlock) {
      return aBlock == Block.field_72037_aS?true:aBlock == Block.field_72039_aU;
   }
}
