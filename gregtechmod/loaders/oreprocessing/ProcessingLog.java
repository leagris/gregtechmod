package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingLog implements IOreRecipeRegistrator {

   public ProcessingLog() {
      OrePrefixes.log.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aOreDictName.equals("logRubber")) {
         GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), 5, GT_Items.IC2_Resin.get(8L, new Object[0]), GT_ModHandler.getIC2Item("plantBall", 6L), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Methane, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Carbon, 4L), 5000);
         GT_ModHandler.addSawmillRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.IC2_Resin.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Wood, 16L));
         GT_ModHandler.addExtractionRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Rubber, 1L));
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Wood, 6L), GT_Items.IC2_Resin.get(1L, new Object[0]), 33, false);
      } else {
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Wood, 6L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Wood, 1L), 80, false);
      }

      if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", false) && GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(GT_Utility.copyAmount(1L, new Object[]{aStack}), false, (ItemStack)null), new ItemStack(Item.field_77705_m, 1, 1))) {
         GT_ModHandler.removeFurnaceSmelting(GT_Utility.copyAmount(1L, new Object[]{aStack}));
      }

      if(aStack.func_77973_b() instanceof ItemBlock && GT_Mod.sWoodStackSize < aStack.func_77973_b().func_77639_j()) {
         aStack.func_77973_b().func_77625_d(GT_Mod.sWoodStackSize);
      }

      int aMeta = aStack.func_77960_j();
      ItemStack tStack;
      if(aMeta == 32767) {
         for(int tPlanks = 0; tPlanks < 16; ++tPlanks) {
            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", false) && GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(new ItemStack(aStack.func_77973_b(), 1, tPlanks), false, (ItemStack)null), new ItemStack(Item.field_77705_m, 1, 1))) {
               GT_ModHandler.removeFurnaceSmelting(new ItemStack(aStack.func_77973_b(), 1, tPlanks));
            }

            tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(aStack.func_77973_b(), 1, tPlanks)});
            if(tStack != null) {
               ItemStack tPlanks1 = GT_Utility.copy(new Object[]{tStack});
               tPlanks1.field_77994_a = tPlanks1.field_77994_a * 3 / 2;
               GregTech_API.sRecipeAdder.addCutterRecipe(new ItemStack(aStack.func_77973_b(), 1, tPlanks), GT_Utility.copyAmount(GT_Mod.sNerfedWoodPlank?(long)tStack.field_77994_a:(long)(tStack.field_77994_a * 5 / 4), new Object[]{tStack}), 200, 8);
               GT_ModHandler.addSawmillRecipe(new ItemStack(aStack.func_77973_b(), 1, tPlanks), tPlanks1, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Wood, 1L));
               GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(aStack.func_77973_b(), 1, tPlanks)});
               GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GT_Mod.sNerfedWoodPlank?(long)tStack.field_77994_a:(long)(tStack.field_77994_a * 5 / 4), new Object[]{tStack}), new Object[]{"S", "L", Character.valueOf('S'), GT_ToolDictNames.craftingToolSaw, Character.valueOf('L'), new ItemStack(aStack.func_77973_b(), 1, tPlanks)});
               GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount((long)(tStack.field_77994_a / (GT_Mod.sNerfedWoodPlank?2:1)), new Object[]{tStack}), new Object[]{new ItemStack(aStack.func_77973_b(), 1, tPlanks)});
            }
         }
      } else {
         tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{GT_Utility.copyAmount(1L, new Object[]{aStack})});
         if(tStack != null) {
            ItemStack var10 = GT_Utility.copy(new Object[]{tStack});
            var10.field_77994_a = var10.field_77994_a * 3 / 2;
            GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Utility.copyAmount(GT_Mod.sNerfedWoodPlank?(long)tStack.field_77994_a:(long)(tStack.field_77994_a * 5 / 4), new Object[]{tStack}), 200, 8);
            GT_ModHandler.addSawmillRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), var10, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Wood, 1L));
            GT_ModHandler.removeRecipe(new ItemStack[]{GT_Utility.copyAmount(1L, new Object[]{aStack})});
            GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GT_Mod.sNerfedWoodPlank?(long)tStack.field_77994_a:(long)(tStack.field_77994_a * 5 / 4), new Object[]{tStack}), new Object[]{"S", "L", Character.valueOf('S'), GT_ToolDictNames.craftingToolSaw, Character.valueOf('L'), GT_Utility.copyAmount(1L, new Object[]{aStack})});
            GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount((long)(tStack.field_77994_a / (GT_Mod.sNerfedWoodPlank?2:1)), new Object[]{tStack}), new Object[]{GT_Utility.copyAmount(1L, new Object[]{aStack})});
         }
      }

   }
}
