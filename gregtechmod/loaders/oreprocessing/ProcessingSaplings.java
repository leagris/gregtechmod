package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingSaplings implements IOreRecipeRegistrator {

   public ProcessingSaplings() {
      OrePrefixes.treeSapling.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aStack.func_77973_b() instanceof ItemBlock && GT_Mod.sWoodStackSize < aStack.func_77973_b().func_77639_j()) {
         aStack.func_77973_b().func_77625_d(GT_Mod.sWoodStackSize);
      }

      GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)Materials.Wood, 2L), (ItemStack)null, 0, false);
   }
}
