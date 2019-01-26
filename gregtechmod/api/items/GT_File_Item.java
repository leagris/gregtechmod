package gregtechmod.api.items;

import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class GT_File_Item extends GT_Tool_Item {

   public GT_File_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "For sharpening or rounding Edges", aMaxDamage, aEntityDamage, true);
      GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolFile, new ItemStack(this, 1, 32767));
      this.setUsageAmounts(1, 3, 2);
   }
}
