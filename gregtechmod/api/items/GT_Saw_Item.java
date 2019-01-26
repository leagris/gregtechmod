package gregtechmod.api.items;

import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class GT_Saw_Item extends GT_Tool_Item {

   public GT_Saw_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
      super(aID, aUnlocalized, aEnglish, "For sawing Logs into Planks", aMaxDamage, aEntityDamage, true, -1, aDisChargedGTID, aToolQuality, aToolStrength);
      GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolSaw, new ItemStack(this, 1, 32767));
      if(GT_ModHandler.isElectricItem(new ItemStack(this, 1, 32767))) {
         this.setSilkyness(1);
      }

      this.addToMaterialList(Material.field_76257_i);
      this.addToMaterialList(Material.field_76254_j);
      this.addToMaterialList(Material.field_76245_d);
      this.addToMaterialList(Material.field_76255_k);
      this.addToMaterialList(Material.field_76260_u);
      this.addToOreDictList("treeLeaves");
      this.addToOreDictList("logRubber");
      this.addToBlockList(Block.field_71945_L);
      this.addToBlockList(Block.field_111038_cB);
      this.addToBlockList(Block.field_72091_am);
      this.addToBlockList(Block.field_71959_S);
      this.setToolClasses(new String[]{"axe"});
      this.setElectricConsumptionPerBrokenBlock(aEnergyConsumptionPerBlockBreak);
      this.setUsageAmounts(1, 3, 1);
   }
}
