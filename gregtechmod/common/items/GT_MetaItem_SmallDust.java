package gregtechmod.common.items;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import gregtechmod.common.items.GT_MetaItem_TinyDust;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_SmallDust extends GT_MetaItem_Abstract {

   public static GT_MetaItem_Abstract instance;


   public GT_MetaItem_SmallDust(int aID, String aName) {
      super(aID, aName);
      instance = this;
   }

   public static ItemStack[] getStackList() {
      return instance.mStackList;
   }

   public static ItemStack addItem(int aMeta, String aName, Materials aMaterial, boolean aGlow) {
      GT_LanguageManager.addStringLocalization(instance.func_77658_a() + "." + aMeta + ".name", "Small Pile of " + aName);
      instance.mToolTipList[aMeta] = aMaterial.getToolTip(OrePrefixes.dustSmall.mMaterialAmount / 3628800L);
      instance.mGlowList[aMeta] = aGlow;
      instance.mStackList[aMeta] = new ItemStack(instance, 1, aMeta);
      GT_OreDictUnificator.set(OrePrefixes.dustSmall, aMaterial, instance.getUnunifiedStack(aMeta, 1));
      GT_MetaItem_TinyDust.addItem(aMeta, aName, aMaterial, aGlow);
      return instance.getUnunifiedStack(aMeta, 1);
   }
}
