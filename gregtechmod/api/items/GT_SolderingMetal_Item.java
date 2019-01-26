package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class GT_SolderingMetal_Item extends GT_Tool_Item {

   public GT_SolderingMetal_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "Used in conjunction with Soldering Tools", aMaxDamage, aEntityDamage, false);
      GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolSolderingMetal, new ItemStack(this, 1, 32767));
      GregTech_API.registerSolderingMetal(new ItemStack(this, 1, 32767));
      this.setUsageAmounts(1, 3, 1);
   }
}
