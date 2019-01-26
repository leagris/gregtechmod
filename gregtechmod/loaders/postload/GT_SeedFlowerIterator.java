package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.GT_DummyWorld;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GT_SeedFlowerIterator implements Runnable {

   public void run() {
      GT_Log.out.println("GT_Mod: Iterating through the Seed-List of ForgeHooks, with a brilliant and 100% Reflection-free Method, to add Recipes for gaining Seed Oil from Seeds.");
      boolean temp = false;

      try {
         GT_DummyWorld e = (GT_DummyWorld)GregTech_API.sDummyWorld;
         FluidStack tLiquid = FluidRegistry.getFluidStack("seedoil", 15);

         ItemStack e1;
         while(e.mRandom.mIterationStep > 0) {
            e1 = ForgeHooks.getGrassSeed(e);
            if(e1 != null) {
               if(tLiquid != null && GT_ModHandler.addSqueezerRecipe(e1, tLiquid, 15)) {
                  temp = true;
               } else {
                  GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(64L, new Object[]{e1}), 1, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SeedOil, 1L), (ItemStack)null, (ItemStack)null, (ItemStack)null, 200);
               }
            }
         }

         GT_Log.out.println("GT_Mod: Iterating through the Grass-Flower-List of ForgeHooks, with a brilliant and 100% Reflection-free Method, to add Extractor Recipes for gaining more Dye from Flowers and also Compression Recipes for Plantballs.");
         e.mRandom.mIterationStep = Integer.MAX_VALUE;

         while(e.mRandom.mIterationStep > 0) {
            try {
               ForgeHooks.plantGrass(e, 24, 65, 24);
               if(e.mLastSetBlock != null) {
                  e1 = GT_ModHandler.getRecipeOutput(new ItemStack[]{e.mLastSetBlock});
                  if(GT_OreDictUnificator.isItemStackDye(e1)) {
                     GT_ModHandler.addExtractionRecipe(e.mLastSetBlock, GT_Utility.copyAmount((long)(e1.field_77994_a + 1), new Object[]{e1}));
                  }

                  GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(8L, new Object[]{e.mLastSetBlock}), GT_ModHandler.getIC2Item("compressedPlantBall", 1L));
               }
            } catch (Throwable var5) {
               GT_Log.err.println("Minor Bug: Wasn\'t able to simulate the planting of a Flower with Bonemeal, to add Extractor Recipe for Dye:\n");
               var5.printStackTrace(GT_Log.err);
            }
         }
      } catch (Throwable var6) {
         GT_Log.out.println("GT_Mod: failed to iterate somehow, maybe it\'s your Forge Version causing it. But it\'s not that important\n");
         var6.printStackTrace(GT_Log.err);
      }

      if(temp) {
         GT_Log.out.println("GT_Mod: Forestry was properly loaded, so the Seed Recipes got added to the Squeezer.");
      } else {
         GT_Log.out.println("GT_Mod: Forestry was NOT loaded, so the Recipes got added to the Industrial Centrifuge.");
         GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Item.field_77740_bh, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SeedOil, 1L), (ItemStack)null, (ItemStack)null, (ItemStack)null, 200);
         GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Item.field_77739_bg, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SeedOil, 1L), (ItemStack)null, (ItemStack)null, (ItemStack)null, 200);
         GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Item.field_77690_S, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SeedOil, 1L), (ItemStack)null, (ItemStack)null, (ItemStack)null, 200);
      }

      GT_ModHandler.addExtractionRecipe(new ItemStack(Block.field_72107_ae, 1, 0), new ItemStack(Item.field_77756_aW, 3, 1));
      GT_ModHandler.addExtractionRecipe(new ItemStack(Block.field_72097_ad, 1, 0), new ItemStack(Item.field_77756_aW, 3, 11));
   }
}
