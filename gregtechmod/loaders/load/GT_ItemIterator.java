package gregtechmod.loaders.load;

import buildcraft.api.tools.IToolWrench;
import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import mods.railcraft.api.core.items.IToolCrowbar;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import powercrystals.minefactoryreloaded.api.IToolHammer;

public class GT_ItemIterator implements Runnable {

   public void run() {
      ItemStack tCharmL = null;
      ItemStack tCharmLL = null;
      ItemStack tCharmI = null;
      ItemStack tCharmII = null;
      ItemStack tCharmIII = null;
      GT_Log.out.println("GT_Mod: Scanning for certain kinds of compatible Machineblocks.");
      ItemStack tStack;
      ItemStack tStack2;
      if(null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Bronze, 1L), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2}))) {
         GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Bronze, 8L), (ItemStack)null, 0, false);
         GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Bronze, 8L));
      }

      if(null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2 = GT_OreDictUnificator.get(OrePrefixes.plate, (Object)Materials.Bronze, 1L), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2}))) {
         GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRawMachineTier00, tStack);
         GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Bronze, 8L), (ItemStack)null, 0, false);
         GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Bronze, 8L));
      }

      ItemStack tStack3;
      if(null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Iron, 1L), tStack3 = new ItemStack(Block.field_71946_M, 1, 0), tStack2, tStack3, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Gold, 1L), tStack3, tStack2, tStack3, tStack2}))) {
         GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Iron, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Gold, 1L), 0, false);
      }

      if(null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 1L), tStack3 = new ItemStack(Block.field_71946_M, 1, 0), tStack2, tStack3, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Gold, 1L), tStack3, tStack2, tStack3, tStack2}))) {
         GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Steel, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Gold, 1L), 0, false);
      }

      GT_Log.out.println("GT_Mod: Registering various Tools to be usable on GregTech Machines");
      GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(new ItemStack[]{null, new ItemStack(Item.field_77703_o, 1), null, new ItemStack(Item.field_77669_D, 1)}));
      GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Item.field_77703_o, 1), null, null, null, new ItemStack(Item.field_77669_D, 1)}));
      GT_Log.out.println("GT_Mod: Adding Food Recipes to the Automatic Canning Machine. (also during the following Item Iteration)");
      GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Item.field_77737_bm, 1, 32767), GT_Items.IC2_Food_Can_Empty.get(2L, new Object[0]), GT_Items.IC2_Food_Can_Spoiled.get(2L, new Object[0]), (ItemStack)null, 200, 1);
      GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Item.field_77728_bu, 1, 32767), GT_Items.IC2_Food_Can_Empty.get(1L, new Object[0]), GT_Items.IC2_Food_Can_Spoiled.get(1L, new Object[0]), (ItemStack)null, 100, 1);
      GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Item.field_82800_bN, 1, 32767), GT_Items.IC2_Food_Can_Empty.get(1L, new Object[0]), GT_Items.IC2_Food_Can_Spoiled.get(1L, new Object[0]), (ItemStack)null, 100, 1);
      GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Item.field_77746_aZ, 1, 32767), GT_Items.IC2_Food_Can_Empty.get(6L, new Object[0]), GT_Items.IC2_Food_Can_Filled.get(6L, new Object[0]), (ItemStack)null, 600, 1);
      GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Block.field_72009_bg, 1, 32767), GT_Items.IC2_Food_Can_Empty.get(6L, new Object[0]), GT_Items.IC2_Food_Can_Filled.get(6L, new Object[0]), (ItemStack)null, 600, 1);
      GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Item.field_77671_F, 1, 32767), GT_Items.IC2_Food_Can_Empty.get(3L, new Object[0]), GT_Items.IC2_Food_Can_Filled.get(3L, new Object[0]), new ItemStack(Item.field_77670_E, 1), 300, 1);
      GT_Log.out.println("GT_Mod: Scanning ItemList.");

      for(int i = 0; i < Item.field_77698_e.length; ++i) {
         Item tItem;
         String tName;
         if((tItem = Item.field_77698_e[i]) != null && (tName = tItem.func_77658_a()) != null) {
            try {
               if(tItem instanceof IToolCrowbar) {
                  if(!tItem.func_77645_m() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
                     if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityRCCrowbars", false) && GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, 32767))) {
                        GT_Log.out.println("GT_Mod: Removed infinite RC Crowbar: " + tName);
                     }
                  } else if(GregTech_API.registerCrowbar(new ItemStack(tItem, 1, 32767))) {
                     GT_Log.out.println("GT_Mod: Registered valid RC Crowbar: " + tName);
                  }
               }
            } catch (Throwable var14) {
               ;
            }

            try {
               if(tItem instanceof IToolHammer) {
                  if(!tItem.func_77645_m() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
                     if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityMFRHammers", false) && GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, 32767))) {
                        GT_Log.out.println("GT_Mod: Removed infinite MFR Hammer: " + tName);
                     }
                  } else if(GregTech_API.registerCrowbar(new ItemStack(tItem, 1, 32767))) {
                     GT_Log.out.println("GT_Mod: Registered valid MFR Hammer: " + tName);
                  }
               }
            } catch (Throwable var15) {
               ;
            }

            try {
               if(tItem instanceof IToolWrench) {
                  if(!tItem.func_77645_m() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
                     if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityBCWrenches", false) && GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, 32767))) {
                        GT_Log.out.println("GT_Mod: Removed infinite BC Wrench: " + tName);
                     }
                  } else if(GregTech_API.registerWrench(new ItemStack(tItem, 1, 32767))) {
                     GT_Log.out.println("GT_Mod: Registered valid BC Wrench: " + tName);
                  }
               }
            } catch (Throwable var16) {
               ;
            }

            Block tBlock;
            if(tItem instanceof ItemBlock) {
               try {
                  tBlock = Block.field_71973_m[((ItemBlock)tItem).func_77883_f()];
                  if(tBlock != null && GT_Mod.sBlockStackSize < tItem.func_77639_j()) {
                     try {
                        if(tBlock.isGenMineableReplaceable(GregTech_API.sDummyWorld, 0, 0, 0, Block.field_71981_t.field_71990_ca) || tBlock.isGenMineableReplaceable(GregTech_API.sDummyWorld, 0, 0, 0, Block.field_72012_bb.field_71990_ca) || tBlock.isGenMineableReplaceable(GregTech_API.sDummyWorld, 0, 0, 0, Block.field_72082_bJ.field_71990_ca)) {
                           tItem.func_77625_d(GT_Mod.sBlockStackSize);
                        }
                     } catch (Throwable var17) {
                        var17.printStackTrace(GT_Log.err);
                     }
                  }
               } catch (IndexOutOfBoundsException var18) {
                  System.err.println("ERROR! A Mod attempted to return an invalid Block ID using ItemBlock! Please mention this to the respective Mod Author, who owns the following Item.");
                  System.err.println("ITEM: " + tItem + " - " + tItem.func_77628_j(new ItemStack(tItem)));
                  System.err.println("ATTEMPTED BLOCK ID: " + ((ItemBlock)tItem).func_77883_f());
                  System.err.println("");
                  var18.printStackTrace();
                  System.err.println("");
                  if(!GregTech_API.DEBUG_MODE) {
                     throw new IndexOutOfBoundsException();
                  }
               }
            }

            if(tItem instanceof ItemFood && tItem != GT_Items.IC2_Food_Can_Filled.getItem() && tItem != GT_Items.IC2_Food_Can_Spoiled.getItem()) {
               int var19 = (int)Math.ceil((double)((ItemFood)tItem).func_77847_f() / 2.0D);
               if(var19 > 0) {
                  GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(tItem, 1, 32767), GT_Items.IC2_Food_Can_Empty.get((long)var19, new Object[0]), GT_Items.IC2_Food_Can_Filled.get((long)var19, new Object[0]), GT_Utility.getContainerItem(new ItemStack(tItem, 1, 0)), var19 * 100, 1);
               }
            }

            if(tItem instanceof IFluidContainerItem) {
               GT_OreDictUnificator.addToBlacklist(new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.ItemSensorLocationCard") || tName.equals("item.ItemEnergySensorLocationCard") || tName.equals("item.ItemEnergyArrayLocationCard") || tName.equals("item.ItemTextCard")) {
               GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(tItem, 1, 32767), (ItemStack)null, GT_Items.Circuit_Basic.get(2L, new Object[0]), 200, 32);
            }

            if(tName.equals("item.ItemTimeCard")) {
               GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(tItem, 1, 32767), (ItemStack)null, GT_Items.Circuit_Basic.get(1L, new Object[0]), 100, 32);
            }

            if(tName.equals("tile.beehives")) {
               tBlock = GT_Utility.getBlockFromStack(new ItemStack(tItem, 1, 0));
               if(tBlock != null) {
                  ((GT_Tool_Item)GT_Items.Tool_Scoop_Aluminium.getItem()).addToMaterialList(tBlock.field_72018_cp);
               }
            }

            if(tName.equals("tile.ArsMagica:ore_vinteum")) {
               GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Vinteum, new ItemStack(tItem, 1, 0));
            }

            if(tName.equals("item.ArsMagica:purified_vinteum")) {
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), (ItemStack)null, 256, 5);
            }

            if(tName.equals("item.meefSteak") || tName.equals("item.venisonCooked")) {
               GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Methane, 1L), (ItemStack)null, (ItemStack)null, (ItemStack)null, 5000);
            }

            if(tName.equals("item.meefRaw") || tName.equals("item.venisonRaw")) {
               GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 12, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Methane, 1L), (ItemStack)null, (ItemStack)null, (ItemStack)null, 5000);
            }

            if(tName.equals("item.fieryBlood")) {
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), (ItemStack)null, 2048, 5);
            }

            if(tName.equals("tile.TFRoots")) {
               GT_ModHandler.addPulverisationRecipe(new ItemStack(tItem, 1, 0), new ItemStack(Item.field_77669_D, 2), new ItemStack(Item.field_77669_D, 1), 30);
               GT_ModHandler.addSawmillRecipe(new ItemStack(tItem, 1, 0), new ItemStack(Item.field_77669_D, 4), new ItemStack(Item.field_77669_D, 2));
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 1), new ItemStack(Item.field_77669_D, 4), 32, 5);
            }

            if(tName.equals("item.liveRoot")) {
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), new ItemStack(Item.field_77669_D, 2), 16, 5);
               GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(tItem, 1, 0), Materials.LiveRoot, 3628800L);
            }

            if(tName.equals("item.ironwoodIngot")) {
               GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.IronWood, new ItemStack(tItem, 1, 0));
            }

            if(tName.equals("item.steeleafIngot")) {
               GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.SteelLeaf, new ItemStack(tItem, 1, 0));
            }

            if(tName.equals("item.fieryIngot")) {
               GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.FieryBlood, new ItemStack(tItem, 1, 0));
            }

            if(tName.equals("item.tconstruct.manual")) {
               GT_OreDictUnificator.registerOre("bookTinkersManual", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.ArsMagica:spell_parchment")) {
               GT_OreDictUnificator.registerOre("paperArsSpellParchment", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.ArsMagica:spell_recipe")) {
               GT_OreDictUnificator.registerOre("paperArsSpellRecipe", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.ArsMagica:spell_book")) {
               GT_OreDictUnificator.registerOre("bookArsSpells", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.myst.page")) {
               GT_OreDictUnificator.registerOre("paperMystcraft", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.myst.agebook")) {
               GT_OreDictUnificator.registerOre("bookMystcraftAge", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.myst.linkbook")) {
               GT_OreDictUnificator.registerOre("bookMystcraftLink", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.myst.notebook")) {
               GT_OreDictUnificator.registerOre("bookNotes", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.itemManuelBook")) {
               GT_OreDictUnificator.registerOre("bookWritten", new ItemStack(tItem, 1, 0));
            }

            if(tName.equals("item.blueprintItem")) {
               GT_OreDictUnificator.registerOre("paperBlueprint", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.ccprintout")) {
               GT_OreDictUnificator.registerOre("paperWritten", new ItemStack(tItem, 1, 0));
               GT_OreDictUnificator.registerOre("paperWritten", new ItemStack(tItem, 1, 1));
               GT_OreDictUnificator.registerOre("bookWritten", new ItemStack(tItem, 1, 2));
            }

            if(tName.equals("item.blueprintItem")) {
               GT_OreDictUnificator.registerOre("paperBlueprint", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.wirelessmap")) {
               GT_OreDictUnificator.registerOre("paperMap", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.ItemResearchNotes")) {
               GT_OreDictUnificator.registerOre("paperResearch", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.ItemThaumonomicon")) {
               GT_OreDictUnificator.registerOre("bookThaumonomicon", new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.ItemEssence")) {
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 7), new ItemStack(tItem, 1, 0), 80, 5);
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 10), new ItemStack(tItem, 1, 0), 160, 5);
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 41), new ItemStack(tItem, 1, 0), 120, 5);
            }

            if(tName.equals("item.ItemWispEssence")) {
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 7), new ItemStack(tItem, 1, 0), 80, 5);
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 10), new ItemStack(tItem, 1, 0), 160, 5);
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 41), new ItemStack(tItem, 1, 0), 120, 5);
            }

            if(tName.equals("item.ItemResource")) {
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 4), (ItemStack)null, 4, 5);
               GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Thaumium, new ItemStack(tItem, 1, 2));
               GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Mercury, new ItemStack(tItem, 1, 3));
               GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Amber, new ItemStack(tItem, 1, 6));
               GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.Mercury, new ItemStack(tItem, 1, 3));
               GT_Mod.sThaumiumObtainable = true;
            }

            if(tName.equals("item.ItemShard")) {
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), (ItemStack)null, 160, 5);
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 1), (ItemStack)null, 320, 5);
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 2), (ItemStack)null, 160, 5);
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 3), (ItemStack)null, 160, 5);
               GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 4), (ItemStack)null, 240, 5);
            }

            if(tName.equals("item.ligniteCoal")) {
               GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Lignite, new ItemStack(tItem, 1, 0));
            }

            if(tName.equals("item.charmOfLife1")) {
               tCharmL = new ItemStack(tItem, 1, 0);
            }

            if(tName.equals("item.charmOfLife2")) {
               tCharmLL = new ItemStack(tItem, 1, 0);
            }

            if(tName.equals("item.charmOfKeeping1")) {
               tCharmI = new ItemStack(tItem, 1, 0);
            }

            if(tName.equals("item.charmOfKeeping2")) {
               tCharmII = new ItemStack(tItem, 1, 0);
            }

            if(tName.equals("item.charmOfKeeping3")) {
               tCharmIII = new ItemStack(tItem, 1, 0);
            }

            if(tName.equals("tile.railcraft.brick.quarried")) {
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 0));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 1));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 2));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 3));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 4));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 5));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 6));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 7));
            }

            if(tName.equals("tile.railcraft.brick.abyssal")) {
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 0));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 1));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 2));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 3));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 4));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 5));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 6));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 7));
            }

            if(tName.equals("tile.extrabiomes.redrock") || tName.equals("tile.bop.redRocks")) {
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock, new ItemStack(tItem, 1, 0));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock, new ItemStack(tItem, 1, 1));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock, new ItemStack(tItem, 1, 2));
            }

            if(tName.equals("tile.rpstone")) {
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 0));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 1));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 2));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 3));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 4));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 5));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 6));
            }

            if(tName.equals("tile.sedimentaryStone")) {
               GregTech_API.sRecipeAdder.addJackHammerMinableBlock(GT_Utility.getBlockFromStack(new ItemStack(tItem, 1)), false);
            }

            if(tName.equals("tile.igneousStone") || tName.equals("tile.igneousStoneBrick") || tName.equals("tile.igneousCobblestone")) {
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.GraniteRed, new ItemStack(tItem, 1, 0));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.GraniteBlack, new ItemStack(tItem, 1, 1));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Rhyolite, new ItemStack(tItem, 1, 2));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Andesite, new ItemStack(tItem, 1, 3));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Gabbro, new ItemStack(tItem, 1, 4));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 5));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Komatiite, new ItemStack(tItem, 1, 6));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Dacite, new ItemStack(tItem, 1, 7));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.GraniteRed, new ItemStack(tItem, 1, 8));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.GraniteBlack, new ItemStack(tItem, 1, 9));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Rhyolite, new ItemStack(tItem, 1, 10));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Andesite, new ItemStack(tItem, 1, 11));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Gabbro, new ItemStack(tItem, 1, 12));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 13));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Komatiite, new ItemStack(tItem, 1, 14));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Dacite, new ItemStack(tItem, 1, 15));
            }

            if(tName.equals("tile.metamorphicStone") || tName.equals("tile.metamorphicStoneBrick") || tName.equals("tile.metamorphicCobblestone")) {
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Gneiss, new ItemStack(tItem, 1, 0));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Eclogite, new ItemStack(tItem, 1, 1));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 2));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Quartzite, new ItemStack(tItem, 1, 3));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Blueschist, new ItemStack(tItem, 1, 4));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Greenschist, new ItemStack(tItem, 1, 5));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Soapstone, new ItemStack(tItem, 1, 6));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Migmatite, new ItemStack(tItem, 1, 7));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Gneiss, new ItemStack(tItem, 1, 8));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Eclogite, new ItemStack(tItem, 1, 9));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 10));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Quartzite, new ItemStack(tItem, 1, 11));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Blueschist, new ItemStack(tItem, 1, 12));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Greenschist, new ItemStack(tItem, 1, 13));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Soapstone, new ItemStack(tItem, 1, 14));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Migmatite, new ItemStack(tItem, 1, 15));
            }

            if(tName.equals("tile.blockCosmeticSolid")) {
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Obsidian, new ItemStack(tItem, 1, 0));
               GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Obsidian, new ItemStack(tItem, 1, 1));
            }

            if(tName.equals("tile.enderchest")) {
               GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingEnderChest, new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("tile.autoWorkbenchBlock")) {
               GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWorkBench, new ItemStack(tItem, 1, 0));
            }

            if(tName.equals("tile.pumpBlock")) {
               GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingPump, new ItemStack(tItem, 1, 0));
               if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "BCPump", false)) {
                  GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, 0));
               }
            }

            if(tName.equals("tile.tankBlock")) {
               GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingTank, new ItemStack(tItem, 1, 0));
            }

            if(tName.equals("item.minotaurAxe")) {
               GT_ModHandler.addPulverisationRecipe(new ItemStack(tItem, 1, 0), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Diamond, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Wood, 1L), 50, false);
            }

            if(tName.equals("item.bucketFuel")) {
               GT_ModHandler.addCraftingRecipe(GT_ModHandler.getRecipeOutput(new ItemStack[]{null, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 1L), null, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 1L), GT_ModHandler.getIC2Item("electronicCircuit", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 1L), new ItemStack(tItem, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 1L)}), new Object[]{" S ", "SCS", "SLS", Character.valueOf('S'), OrePrefixes.plate.get(Materials.Steel), Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Basic), Character.valueOf('L'), OrePrefixes.cell.get(Materials.Lithium)});
            }

            if(tName.equals("item.drawplateDiamond")) {
               GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolDrawplate, new ItemStack(tItem, 1, 32767));
            }

            if(tName.equals("item.canLava")) {
               GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 8), 0, GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Electrum, 4L), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Copper, 2L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Tin, 1L), 5000);
            }

            if(tName.equals("item.refractoryLava")) {
               GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 8), 0, GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Electrum, 4L), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Copper, 2L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Tin, 1L), 5000);
            }
         }
      }

      if(tCharmL != null) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(4L, new Object[]{tCharmL}), (ItemStack)null, GT_Utility.copyAmount(1L, new Object[]{tCharmLL}), 800, 2);
      }

      if(tCharmI != null) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(4L, new Object[]{tCharmI}), (ItemStack)null, GT_Utility.copyAmount(1L, new Object[]{tCharmII}), 800, 2);
      }

      if(tCharmII != null) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(4L, new Object[]{tCharmII}), (ItemStack)null, GT_Utility.copyAmount(1L, new Object[]{tCharmIII}), 800, 2);
      }

   }
}
