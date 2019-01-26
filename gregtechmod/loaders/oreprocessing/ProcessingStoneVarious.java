package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ProcessingStoneVarious implements IOreRecipeRegistrator {

   public ProcessingStoneVarious() {
      OrePrefixes.stone.add((IOreRecipeRegistrator)this);
      OrePrefixes.stoneCobble.add((IOreRecipeRegistrator)this);
      OrePrefixes.stoneBricks.add((IOreRecipeRegistrator)this);
      OrePrefixes.stoneChiseled.add((IOreRecipeRegistrator)this);
      OrePrefixes.stoneCracked.add((IOreRecipeRegistrator)this);
      OrePrefixes.stoneMossy.add((IOreRecipeRegistrator)this);
      OrePrefixes.stoneMossyBricks.add((IOreRecipeRegistrator)this);
      OrePrefixes.stoneSmooth.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      Block aBlock = GT_Utility.getBlockFromStack(aStack);
      if(aBlock != null) {
         if(aStack.func_77973_b().func_77639_j() > GT_Mod.sBlockStackSize) {
            aStack.func_77973_b().func_77625_d(GT_Mod.sBlockStackSize);
         }

         int tHarvestLevel = MinecraftForge.getBlockHarvestLevel(aBlock, aStack.func_77960_j() < 0 && aStack.func_77960_j() >= 16?0:aStack.func_77960_j(), "pickaxe");
         if(tHarvestLevel <= 3) {
            GregTech_API.sRecipeAdder.addJackHammerMinableBlock(aBlock, tHarvestLevel >= 3);
         }
      }

   }
}
