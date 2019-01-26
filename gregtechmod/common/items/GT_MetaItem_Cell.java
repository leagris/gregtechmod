package gregtechmod.common.items;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Cell extends GT_MetaItem_Abstract {

   public static GT_MetaItem_Abstract instance;


   public GT_MetaItem_Cell(int aID, String aName) {
      super(aID, aName);
      instance = this;
   }

   public static ItemStack[] getStackList() {
      return instance.mStackList;
   }

   public static ItemStack addItem(int aMeta, String aName, boolean aPlasma, Materials aMaterial) {
      GT_LanguageManager.addStringLocalization(instance.func_77658_a() + "." + aMeta + ".name", aName);
      instance.mToolTipList[aMeta] = aMaterial.getToolTip(OrePrefixes.cell.mMaterialAmount / 3628800L);
      instance.mStackList[aMeta] = new ItemStack(instance, 1, aMeta);
      if(aPlasma) {
         GT_OreDictUnificator.set(OrePrefixes.cellPlasma, aMaterial, instance.getUnunifiedStack(aMeta, 1));
      } else {
         GT_OreDictUnificator.set(OrePrefixes.cell, aMaterial, instance.getUnunifiedStack(aMeta, 1));
      }

      return instance.getUnunifiedStack(aMeta, 1);
   }
}
