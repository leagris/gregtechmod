package gregtechmod.common.items;

import gregtechmod.api.enums.MaterialStack;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Material extends GT_MetaItem_Abstract {

   public static GT_MetaItem_Abstract instance;


   public GT_MetaItem_Material(int aID, String aName) {
      super(aID, aName);
      instance = this;
   }

   public static ItemStack[] getStackList() {
      return instance.mStackList;
   }

   public static ItemStack addItem(int aMeta, String aName, OrePrefixes aPrefix, Object aMaterial, boolean aGlowing) {
      GT_LanguageManager.addStringLocalization(instance.func_77658_a() + "." + aMeta + ".name", aName);
      instance.mToolTipList[aMeta] = aMaterial == null?"":(aMaterial instanceof Materials?((Materials)aMaterial).getToolTip(aPrefix.mMaterialAmount / 3628800L):aMaterial.toString());
      instance.mGlowList[aMeta] = aGlowing;
      instance.mStackList[aMeta] = new ItemStack(instance, 1, aMeta);
      if(aMaterial != null && aPrefix != null) {
         if(aPrefix.mIsUnificatable) {
            GT_OreDictUnificator.set(aPrefix, aMaterial instanceof MaterialStack?((MaterialStack)aMaterial).mMaterial:aMaterial, instance.getUnunifiedStack(aMeta, 1));
         } else {
            GT_OreDictUnificator.registerOre(aPrefix.get(aMaterial instanceof MaterialStack?((MaterialStack)aMaterial).mMaterial:aMaterial), instance.getUnunifiedStack(aMeta, 1));
         }
      }

      return instance.getUnunifiedStack(aMeta, 1);
   }
}
