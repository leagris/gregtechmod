package gregtechmod.common.items;

import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Component extends GT_MetaItem_Abstract {

   public static GT_MetaItem_Abstract instance;


   public GT_MetaItem_Component(int aID, String aName) {
      super(aID, aName);
      instance = this;
   }

   public static ItemStack[] getStackList() {
      return instance.mStackList;
   }

   public static ItemStack addItem(int aMeta, String aName, Object aMaterial, String aToolTip) {
      if(instance.mStackList[aMeta] != null) {
         throw new IllegalArgumentException("" + aMeta);
      } else {
         GT_LanguageManager.addStringLocalization(instance.func_77658_a() + "." + aMeta + ".name", aName);
         instance.mToolTipList[aMeta] = GT_LanguageManager.addStringLocalization(instance.func_77658_a() + "." + aMeta + ".tooltip", aToolTip);
         instance.mStackList[aMeta] = new ItemStack(instance, 1, aMeta);
         if(aMaterial != null && !aMaterial.equals("")) {
            GT_OreDictUnificator.registerOre(aMaterial, instance.getUnunifiedStack(aMeta, 1));
         }

         return instance.getUnunifiedStack(aMeta, 1);
      }
   }
}
