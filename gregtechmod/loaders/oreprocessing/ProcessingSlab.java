package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingSlab implements IOreRecipeRegistrator {

   public ProcessingSlab() {
      OrePrefixes.slab.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aOreDictName.startsWith("slabWood")) {
         if(aStack.func_77973_b() instanceof ItemBlock && GT_Mod.sPlankStackSize < aStack.func_77973_b().func_77639_j()) {
            aStack.func_77973_b().func_77625_d(GT_Mod.sPlankStackSize);
         }

         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)Materials.Wood, 2L), (ItemStack)null, 0, false);
         GregTech_API.sRecipeAdder.addCannerRecipe(GT_ModHandler.getRCItem("fluid.creosote.bucket", 1L), GT_Utility.copyAmount(3L, new Object[]{aStack}), GT_ModHandler.getRCItem("part.tie.wood", 1L), new ItemStack(Item.field_77788_aw, 1), 200, 4);
         GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Creosote, 1L), GT_Utility.copyAmount(3L, new Object[]{aStack}), GT_ModHandler.getRCItem("part.tie.wood", 1L), GT_Items.Cell_Empty.get(1L, new Object[0]), 200, 4);
      }

   }
}
