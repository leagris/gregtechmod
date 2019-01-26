package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingSand implements IOreRecipeRegistrator {

   public ProcessingSand() {
      OrePrefixes.sand.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aOreDictName.equals("sandCracked")) {
         if(aStack.func_77973_b() instanceof ItemBlock) {
            if(aStack.func_77973_b().func_77639_j() > GT_Mod.sBlockStackSize) {
               aStack.func_77973_b().func_77625_d(GT_Mod.sBlockStackSize);
            }

            GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.field_71973_m[((ItemBlock)aStack.func_77973_b()).func_77883_f()], false);
         }

         GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), -1, GT_ModHandler.getFuelCan(25000), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Saltpeter, 8L), (ItemStack)null, new ItemStack(Block.field_71939_E, 10), 2500);
      } else if(aOreDictName.equals("sandOil")) {
         GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), 1, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Oil, 1L), new ItemStack(Block.field_71939_E, 1, 0), (ItemStack)null, (ItemStack)null, 1000);
      }

   }
}
