package gregtechmod.api.items;

import gregtechmod.api.items.GT_Screwdriver_Item;
import ic2.api.item.IElectricItem;

public class GT_ScrewdriverIC_Item extends GT_Screwdriver_Item implements IElectricItem {

   public GT_ScrewdriverIC_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
      super(aID, aUnlocalized, aEnglish, aMaxDamage, aEntityDamage, aDischargedGTID);
   }
}
