package gregtechmod.api.items;

import gregtechmod.api.items.GT_Tool_Item;
import net.minecraft.block.Block;

public class GT_Drill_Item extends GT_Tool_Item {

   public GT_Drill_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
      super(aID, aUnlocalized, aEnglish, "For quickly making Holes", aMaxDamage, aEntityDamage, true, -1, aDisChargedGTID, aToolQuality, aToolStrength);
      this.setElectricConsumptionPerBrokenBlock(aEnergyConsumptionPerBlockBreak);
      this.addToBlockList(Block.field_72006_bl);
      this.addToBlockList(Block.field_72091_am);
   }

   public boolean func_77634_r() {
      return false;
   }
}
