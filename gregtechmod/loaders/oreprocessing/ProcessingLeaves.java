package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingLeaves implements IOreRecipeRegistrator {

   public ProcessingLeaves() {
      OrePrefixes.treeLeaves.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aStack.func_77973_b() instanceof ItemBlock && GT_Mod.sWoodStackSize < aStack.func_77973_b().func_77639_j()) {
         aStack.func_77973_b().func_77625_d(GT_Mod.sWoodStackSize);
      }

   }
}
