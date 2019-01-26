package gregtechmod.api.items;

import gregtechmod.api.items.GT_Drill_Item;
import ic2.api.item.IElectricItem;

public class GT_DrillIC_Item extends GT_Drill_Item implements IElectricItem {

   public GT_DrillIC_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
      super(aID, aUnlocalized, aEnglish, aMaxDamage, aEntityDamage, aToolQuality, aToolStrength, aEnergyConsumptionPerBlockBreak, aDisChargedGTID);
   }
}
