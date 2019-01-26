package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ProcessingStone implements IOreRecipeRegistrator {

   public ProcessingStone() {
      OrePrefixes.stone.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      Block aBlock = GT_Utility.getBlockFromStack(aStack);
      switch(ProcessingStone.NamelessClass1151424281.$SwitchMap$gregtechmod$api$enums$Materials[aMaterial.ordinal()]) {
      case 1:
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new ItemStack(Block.field_71939_E, 1, 0), (ItemStack)null, 10, false);
         break;
      case 2:
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_Items.Cell_Water.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Endstone, 16L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)Materials.Tungsten, 1L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)Materials.Endstone, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)Materials.Tungsten, 1L), 5, false);
         break;
      case 3:
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_Items.Cell_Water.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Netherrack, 16L), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Gold, 1L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Mercury, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Netherrack, 8L), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Gold, 5L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)Materials.Netherrack, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Gold, 1L), 5, false);
         break;
      case 4:
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(8L, new Object[]{aStack}), GT_Items.Cell_Water.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Netherrack, 16L), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Gold, 1L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(8L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Mercury, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Netherrack, 8L), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Gold, 5L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         break;
      case 5:
         if(aBlock != null) {
            aBlock.func_71894_b(20.0F);
         }

         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Items.IC2_Compressed_Coal_Ball.get(8L, new Object[0]), GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.IC2_Compressed_Coal_Chunk.get(1L, new Object[0]), 400, 4);
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_ModHandler.getRCItem("cube.crushed.obsidian", 1L, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L)), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L), 10, true);
         GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 1L), 200, 32);
         break;
      case 6:
      case 7:
      case 8:
      case 9:
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L), 10, false);
         break;
      case 10:
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)aMaterial, 2L), new ItemStack(Item.field_77804_ap, 1), 50, false);
         break;
      case 11:
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plateAlloy, (Object)Materials.Advanced, 1L), GT_Utility.copyAmount(8L, new Object[]{aStack}), GT_ModHandler.getIC2Item("reinforcedStone", 8L), 400, 4);
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_Items.Cell_Water.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 16L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)Materials.Thorium, 1L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 1L), 200, 32);
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Thorium, 1L), 1, false);
         break;
      case 12:
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plateAlloy, (Object)Materials.Advanced, 1L), GT_Utility.copyAmount(8L, new Object[]{aStack}), GT_ModHandler.getIC2Item("reinforcedStone", 8L), 400, 4);
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_Items.Cell_Water.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 16L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)Materials.Uranium, 1L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 1L), 200, 32);
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)Materials.Uranium, 1L), 1, false);
      }

   }

   // $FF: synthetic class
   static class NamelessClass1151424281 {

      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$Materials = new int[Materials.values().length];


      static {
         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Sand.ordinal()] = 1;
         } catch (NoSuchFieldError var12) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Endstone.ordinal()] = 2;
         } catch (NoSuchFieldError var11) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Netherrack.ordinal()] = 3;
         } catch (NoSuchFieldError var10) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.NetherBrick.ordinal()] = 4;
         } catch (NoSuchFieldError var9) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Obsidian.ordinal()] = 5;
         } catch (NoSuchFieldError var8) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Redrock.ordinal()] = 6;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Marble.ordinal()] = 7;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Basalt.ordinal()] = 8;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Quartzite.ordinal()] = 9;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Flint.ordinal()] = 10;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.GraniteBlack.ordinal()] = 11;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.GraniteRed.ordinal()] = 12;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
