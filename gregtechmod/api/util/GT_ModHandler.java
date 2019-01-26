package gregtechmod.api.util;

import buildcraft.api.power.IPowerReceptor;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.recipes.RecipeManagers;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IBasicEnergyContainer;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_ItsNotMyFaultException;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_PulverizerRecipe;
import gregtechmod.api.util.GT_Shaped_NBT_Keeping_Recipe;
import gregtechmod.api.util.GT_Shaped_Recipe;
import gregtechmod.api.util.GT_Shapeless_NBT_Keeping_Recipe;
import gregtechmod.api.util.GT_Shapeless_Recipe;
import gregtechmod.api.util.GT_Utility;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.item.ElectricItem;
import ic2.api.item.IBoxable;
import ic2.api.item.IElectricItem;
import ic2.api.item.ItemWrapper;
import ic2.api.item.Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import ic2.api.recipe.Recipes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import mods.railcraft.api.crafting.IRockCrusherRecipe;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.api.fuel.FuelManager;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import universalelectricity.core.block.IElectricalStorage;

public class GT_ModHandler {

   public static volatile int VERSION = 408;
   private static final Map<String, ItemStack> sIC2ItemMap = new HashMap();
   private static Map<Integer, Object> sPulverizerRecipes = new HashMap();
   private static Map<IRecipeInput, RecipeOutput> sExtractorRecipes = new HashMap();
   private static Map<IRecipeInput, RecipeOutput> sMaceratorRecipes = new HashMap();
   private static Map<IRecipeInput, RecipeOutput> sCompressorRecipes = new HashMap();
   private static Map<IRecipeInput, RecipeOutput> sOreWashingRecipes = new HashMap();
   private static Map<IRecipeInput, RecipeOutput> sThermalCentrifugeRecipes = new HashMap();
   private static Map<IRecipeInput, RecipeOutput> sMassfabRecipes = new HashMap();
   private static boolean sBufferCraftingRecipes = true;
   private static final List<IRecipe> sAllRecipeList = Collections.synchronizedList(new ArrayList(2000));
   private static final List<IRecipe> sBufferRecipeList = new ArrayList(300);
   private static final List<IRecipe> sSingleNonBlockDamagableRecipeList = new ArrayList(300);
   public static Object sBoxableWrapper = GT_Utility.callConstructor("gregtechmod.api.util.GT_IBoxableWrapper", 0, (Object)null, false, new Object[0]);


   public static boolean isWater(FluidStack aLiquid) {
      return aLiquid == null?false:aLiquid.isFluidEqual(getWater(1L));
   }

   public static FluidStack getWater(long aAmount) {
      return FluidRegistry.getFluidStack("water", (int)aAmount);
   }

   public static boolean isLava(FluidStack aLiquid) {
      return aLiquid == null?false:aLiquid.isFluidEqual(getLava(1L));
   }

   public static FluidStack getLava(long aAmount) {
      return FluidRegistry.getFluidStack("lava", (int)aAmount);
   }

   public static boolean isSteam(FluidStack aLiquid) {
      return aLiquid == null?false:aLiquid.isFluidEqual(getSteam(1L));
   }

   public static FluidStack getSteam(long aAmount) {
      return FluidRegistry.getFluidStack("steam", (int)aAmount);
   }

   public static ItemStack getEmptyFuelCan(long aAmount) {
      return GT_Items.IC2_Fuel_Can_Empty.get(aAmount, new Object[0]);
   }

   public static ItemStack getEmptyCell(long aAmount) {
      return GT_Items.Cell_Empty.get(aAmount, new Object[0]);
   }

   public static ItemStack getAirCell(long aAmount) {
      return GT_Items.Cell_Air.get(aAmount, new Object[0]);
   }

   public static ItemStack getWaterCell(long aAmount) {
      return GT_Items.Cell_Water.get(aAmount, new Object[0]);
   }

   public static ItemStack getLavaCell(long aAmount) {
      return GT_Items.Cell_Lava.get(aAmount, new Object[0]);
   }

   public static ItemStack setFuelValue(ItemStack aStack, short aValue) {
      aStack.func_77982_d(GT_Utility.getNBTContainingShort(aStack.func_77978_p(), "GT.ItemFuelValue", aValue));
      return aStack;
   }

   public static ItemStack getFuelCan(int aValue) {
      if(aValue < 5) {
         return GT_Items.IC2_Fuel_Can_Empty.get(1L, new Object[0]);
      } else {
         ItemStack rFuelCanStack = GT_Items.IC2_Fuel_Can_Filled.get(1L, new Object[0]);
         if(rFuelCanStack == null) {
            return null;
         } else {
            NBTTagCompound tNBT = new NBTTagCompound();
            tNBT.func_74768_a("value", aValue / 5);
            rFuelCanStack.func_77982_d(tNBT);
            return rFuelCanStack;
         }
      }
   }

   public static int getFuelCanValue(ItemStack aFuelCan) {
      if(!GT_Utility.isStackInvalid(aFuelCan) && GT_Items.IC2_Fuel_Can_Filled.isStackEqual(aFuelCan, false, true)) {
         NBTTagCompound tNBT = aFuelCan.func_77978_p();
         return tNBT == null?0:tNBT.func_74762_e("value") * 5;
      } else {
         return 0;
      }
   }

   public static ItemStack getIC2Item(String aItem, long aAmount, ItemStack aReplacement) {
      if(!GT_Utility.isStringInvalid(aItem) && GregTech_API.sPreloadStarted) {
         if(!sIC2ItemMap.containsKey(aItem)) {
            try {
               sIC2ItemMap.put(aItem, Items.getItem(aItem));
            } catch (Throwable var5) {
               ;
            }
         }

         return GT_Utility.copyAmount(aAmount, new Object[]{sIC2ItemMap.get(aItem), aReplacement});
      } else {
         return null;
      }
   }

   public static ItemStack getIC2Item(String aItem, long aAmount, int aMeta, ItemStack aReplacement) {
      ItemStack rStack = getIC2Item(aItem, aAmount, aReplacement);
      if(rStack == null) {
         return null;
      } else {
         Item.field_77676_L.setDamage(rStack, aMeta);
         return rStack;
      }
   }

   public static ItemStack getIC2Item(String aItem, long aAmount, int aMeta) {
      return getIC2Item(aItem, aAmount, aMeta, (ItemStack)null);
   }

   public static ItemStack getIC2Item(String aItem, long aAmount) {
      return getIC2Item(aItem, aAmount, (ItemStack)null);
   }

   public static ItemStack getRCItem(String aItem, long aAmount) {
      return getRCItem(aItem, aAmount, (ItemStack)null);
   }

   public static ItemStack getRCItem(String aItem, long aAmount, ItemStack aReplacement) {
      return !GT_Utility.isStringInvalid(aItem) && GregTech_API.sPreloadStarted?GT_Utility.copyAmount(aAmount, new Object[]{GameRegistry.findItemStack("Railcraft", aItem, (int)aAmount), aReplacement}):null;
   }

   public static ItemStack getRCItem(String aItem, long aAmount, int aMeta) {
      ItemStack rStack = getRCItem(aItem, aAmount);
      if(rStack == null) {
         return null;
      } else {
         Item.field_77676_L.setDamage(rStack, aMeta);
         return rStack;
      }
   }

   public static ItemStack getRCItem(String aItem, long aAmount, int aMeta, ItemStack aReplacement) {
      ItemStack rStack = getRCItem(aItem, aAmount, aReplacement);
      if(rStack == null) {
         return null;
      } else {
         Item.field_77676_L.setDamage(rStack, aMeta);
         return rStack;
      }
   }

   public static ItemStack getTEItem(String aItem, long aAmount, ItemStack aReplacement) {
      return !GT_Utility.isStringInvalid(aItem) && GregTech_API.sPreloadStarted?GT_Utility.copyAmount(aAmount, new Object[]{GameRegistry.findItemStack("ThermalExpansion", aItem, (int)aAmount), aReplacement}):null;
   }

   public static ItemStack getTEItem(String aItem, long aAmount, int aMeta) {
      ItemStack rStack = getTEItem(aItem, aAmount);
      if(rStack == null) {
         return null;
      } else {
         Item.field_77676_L.setDamage(rStack, aMeta);
         return rStack;
      }
   }

   public static ItemStack getTEItem(String aItem, long aAmount) {
      return getTEItem(aItem, aAmount, (ItemStack)null);
   }

   public static ItemStack getTEItem(String aItem, long aAmount, int aMeta, ItemStack aReplacement) {
      ItemStack rStack = getTEItem(aItem, aAmount, aReplacement);
      if(rStack == null) {
         return null;
      } else {
         Item.field_77676_L.setDamage(rStack, aMeta);
         return rStack;
      }
   }

   public static void addBoilerFuel(FluidStack aLiquid, int aValue) {
      if(aLiquid != null && aValue > 0) {
         if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Fuels.boilerfuels, aLiquid.getFluid().getUnlocalizedName(), true)) {
            try {
               FuelManager.addBoilerFuel(aLiquid.getFluid(), aValue);
            } catch (Throwable var3) {
               ;
            }

         }
      }
   }

   public static boolean getModeKeyDown(EntityPlayer aPlayer) {
      return false;
   }

   public static boolean getBoostKeyDown(EntityPlayer aPlayer) {
      return false;
   }

   public static boolean getJumpKeyDown(EntityPlayer aPlayer) {
      return false;
   }

   public static boolean addValuableOre(int aID, int aMeta, int aValue) {
      if(aValue <= 0) {
         return false;
      } else {
         try {
            Class.forName("ic2.core.IC2").getMethod("addValuableOre", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE}).invoke((Object)null, new Object[]{Integer.valueOf(aID), Integer.valueOf(aMeta), Integer.valueOf(aValue)});
         } catch (Throwable var4) {
            ;
         }

         return true;
      }
   }

   public static boolean addScrapboxDrop(float aChance, ItemStack aOutput) {
      aOutput = GT_OreDictUnificator.get(true, aOutput);
      if(aOutput != null && aChance > 0.0F) {
         aOutput.field_77994_a = 1;
         if(GT_Config.system && !GT_Utility.areStacksEqual(aOutput, new ItemStack(Item.field_77678_N, 1, 0))) {
            return false;
         } else {
            aChance = (float)GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.scrapboxdrops, aOutput, (double)aChance);
            if(aChance <= 0.0F) {
               return false;
            } else {
               try {
                  GT_Utility.callMethod(GT_Utility.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", true, true), "addDrop", true, false, true, new Object[]{GT_Utility.copy(new Object[]{aOutput}), Float.valueOf(aChance)});
                  GT_Utility.callMethod(GT_Utility.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", true, true), "addRecipe", true, true, false, new Object[]{GT_Utility.copy(new Object[]{aOutput}), Float.valueOf(aChance)});
               } catch (Throwable var3) {
                  ;
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }

   public static boolean addToRecyclerBlackList(ItemStack aRecycledStack) {
      if(aRecycledStack == null) {
         return false;
      } else {
         try {
            Recipes.recyclerBlacklist.add(GT_Utility.copy(new Object[]{aRecycledStack}));
         } catch (Throwable var2) {
            ;
         }

         return true;
      }
   }

   public static boolean addSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
      aOutput = GT_OreDictUnificator.get(true, aOutput);
      if(aInput != null && aOutput != null && GT_Utility.getContainerItem(aInput) == null) {
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.smelting, aInput, true)) {
            return false;
         } else {
            FurnaceRecipes.func_77602_a().addSmelting(aInput.field_77993_c, aInput.func_77960_j(), GT_Utility.copy(new Object[]{aOutput}), 0.0F);
            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean addSmeltingAndAlloySmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
      if(aInput != null && aOutput != null) {
         boolean temp = false;
         if(aInput.field_77994_a == 1 && addSmeltingRecipe(aInput, aOutput)) {
            temp = true;
         }

         if(GregTech_API.sRecipeAdder.addAlloySmelterRecipe(aInput, (ItemStack)null, aOutput, 130, 3)) {
            temp = true;
         }

         if(addInductionSmelterRecipe(aInput, (ItemStack)null, aOutput, (ItemStack)null, aOutput.field_77994_a * 100, 0)) {
            temp = true;
         }

         return temp;
      } else {
         return false;
      }
   }

   public static boolean addSqueezerRecipe(ItemStack aInput, FluidStack aOutput, int aTime) {
      if(aInput != null && aOutput != null) {
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.squeezer, aInput, true)) {
            return false;
         } else {
            try {
               RecipeManagers.squeezerManager.addRecipe(aTime > 0?aTime:100, new ItemStack[]{GT_Utility.copy(new Object[]{aInput})}, aOutput);
               return true;
            } catch (Throwable var4) {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public static boolean addLiquidTransposerRecipe(ItemStack aEmptyContainer, FluidStack aLiquid, ItemStack aFullContainer, int aMJ) {
      aFullContainer = GT_OreDictUnificator.get(true, aFullContainer);
      if(aEmptyContainer != null && aFullContainer != null && aLiquid != null) {
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.liquidtransposer, aFullContainer, true)) {
            return false;
         } else {
            try {
               GT_ModHandler.ThermalExpansion.addTransposerFill(aMJ, aEmptyContainer, aFullContainer, aLiquid, true);
            } catch (Throwable var5) {
               ;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean addLiquidTransposerFillRecipe(ItemStack aEmptyContainer, FluidStack aLiquid, ItemStack aFullContainer, int aMJ) {
      aFullContainer = GT_OreDictUnificator.get(true, aFullContainer);
      if(aEmptyContainer != null && aFullContainer != null && aLiquid != null) {
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.liquidtransposerfilling, aFullContainer, true)) {
            return false;
         } else {
            try {
               GT_ModHandler.ThermalExpansion.addTransposerFill(aMJ, aEmptyContainer, aFullContainer, aLiquid, false);
            } catch (Throwable var5) {
               ;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean addLiquidTransposerEmptyRecipe(ItemStack aFullContainer, FluidStack aLiquid, ItemStack aEmptyContainer, int aMJ) {
      aEmptyContainer = GT_OreDictUnificator.get(true, aEmptyContainer);
      if(aFullContainer != null && aEmptyContainer != null && aLiquid != null) {
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.liquidtransposeremptying, aFullContainer, true)) {
            return false;
         } else {
            try {
               GT_ModHandler.ThermalExpansion.addTransposerExtract(aMJ, aFullContainer, aEmptyContainer, aLiquid, 100, false);
            } catch (Throwable var5) {
               ;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean addExtractionRecipe(ItemStack aInput, ItemStack aOutput) {
      aOutput = GT_OreDictUnificator.get(true, aOutput);
      if(aInput != null && aOutput != null) {
         GT_Utility.removeSimpleIC2MachineRecipe(aInput, getExtractorRecipeList(), (ItemStack)null);
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.extractor, aInput, true)) {
            return false;
         } else {
            GT_Utility.addSimpleIC2MachineRecipe(aInput, getExtractorRecipeList(), (NBTTagCompound)null, new Object[]{aOutput});
            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean addRCBlastFurnaceRecipe(ItemStack aInput, ItemStack aOutput, int aTime) {
      aOutput = GT_OreDictUnificator.get(true, aOutput);
      if(aInput != null && aOutput != null && aTime > 0) {
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.rcblastfurnace, aInput, true)) {
            return false;
         } else {
            aInput = GT_Utility.copy(new Object[]{aInput});
            aOutput = GT_Utility.copy(new Object[]{aOutput});

            try {
               RailcraftCraftingManager.blastFurnace.addRecipe(aInput, true, false, aTime, aOutput);
               return true;
            } catch (Throwable var4) {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public static Object getPulverizerRecipe(ItemStack aInput) {
      if(aInput == null) {
         return null;
      } else {
         Object tObject = sPulverizerRecipes.get(Integer.valueOf(GT_Utility.stackToInt(aInput)));
         if(tObject != null) {
            return tObject;
         } else {
            ItemStack tInput = GT_Utility.copy(new Object[]{aInput});
            Item.field_77676_L.setDamage(tInput, 32767);
            tObject = sPulverizerRecipes.get(Integer.valueOf(GT_Utility.stackToInt(tInput)));
            return tObject != null?tObject:null;
         }
      }
   }

   public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1) {
      return addPulverisationRecipe(aInput, aOutput1, (ItemStack)null, 0, false);
   }

   public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2) {
      return addPulverisationRecipe(aInput, aOutput1, aOutput2, 100, false);
   }

   public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance) {
      return addPulverisationRecipe(aInput, aOutput1, aOutput2, aChance, false);
   }

   public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, boolean aOverwrite) {
      return addPulverisationRecipe(aInput, aOutput1, (ItemStack)null, 0, aOverwrite);
   }

   public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, boolean aOverwrite) {
      return addPulverisationRecipe(aInput, aOutput1, aOutput2, 100, aOverwrite);
   }

   public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance, boolean aOverwrite) {
      aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
      aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
      if(aInput != null && aOutput1 != null) {
         GT_Utility.removeSimpleIC2MachineRecipe(aInput, getMaceratorRecipeList(), (ItemStack)null);
         if(GT_Utility.getContainerItem(aInput) == null) {
            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.maceration, aInput, true)) {
               GT_Utility.addSimpleIC2MachineRecipe(aInput, getMaceratorRecipeList(), (NBTTagCompound)null, new Object[]{aOutput1});
            }

            if(aOutput2 != null && GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.pulverization, aInput, true)) {
               sPulverizerRecipes.put(Integer.valueOf(GT_Utility.stackToInt(aInput)), new GT_PulverizerRecipe(aInput, aOutput1, aOutput2, aChance <= 0?10:aChance));
            }

            if(!OrePrefixes.log.contains(aInput)) {
               if(Materials.Wood.contains(new ItemStack[]{aOutput1})) {
                  if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.pulverization, aInput, true)) {
                     if(aOutput2 == null) {
                        GT_ModHandler.ThermalExpansion.addSawmillRecipe(80, GT_Utility.copy(new Object[]{aInput}), GT_Utility.copy(new Object[]{aOutput1}));
                     } else {
                        GT_ModHandler.ThermalExpansion.addSawmillRecipe(80, GT_Utility.copy(new Object[]{aInput}), GT_Utility.copy(new Object[]{aOutput1}), GT_Utility.copy(new Object[]{aOutput2}), aChance <= 0?10:aChance);
                     }
                  }
               } else {
                  if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.rockcrushing, aInput, true)) {
                     try {
                        if(aInput.field_77993_c != Block.field_72089_ap.field_71990_ca) {
                           IRockCrusherRecipe e = RailcraftCraftingManager.rockCrusher.createNewRecipe(GT_Utility.copyAmount(1L, new Object[]{aInput}), aInput.func_77960_j() != 32767, false);
                           e.addOutput(GT_Utility.copy(new Object[]{aOutput1}), 1.0F / (float)aInput.field_77994_a);
                           e.addOutput(GT_Utility.copy(new Object[]{aOutput2}), 0.01F * (float)(aChance <= 0?10:aChance) / (float)aInput.field_77994_a);
                        }
                     } catch (Throwable var6) {
                        ;
                     }
                  }

                  if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.pulverization, aInput, true)) {
                     if(aOutput2 == null) {
                        GT_ModHandler.ThermalExpansion.addPulverizerRecipe(80, GT_Utility.copy(new Object[]{aInput}), GT_Utility.copy(new Object[]{aOutput1}));
                     } else {
                        GT_ModHandler.ThermalExpansion.addPulverizerRecipe(80, GT_Utility.copy(new Object[]{aInput}), GT_Utility.copy(new Object[]{aOutput1}), GT_Utility.copy(new Object[]{aOutput2}), aChance <= 0?10:aChance);
                     }
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean addSawmillRecipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2) {
      aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
      aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
      if(aInput1 != null && aOutput1 != null) {
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.sawmill, aInput1, true)) {
            return false;
         } else {
            try {
               GT_ModHandler.ThermalExpansion.addSawmillRecipe(160, aInput1, aOutput1, aOutput2, 100);
            } catch (Throwable var4) {
               ;
            }

            GregTech_API.sRecipeAdder.addSawmillRecipe(aInput1, GT_Items.Cell_Water.get(1L, new Object[0]), aOutput1, aOutput2, GT_Items.Cell_Empty.get(1L, new Object[0]));
            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, boolean aAllowSecondaryInputEmpty) {
      if(aInput1 != null && (aInput2 != null || aAllowSecondaryInputEmpty) && aOutput1 != null) {
         aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
         boolean temp = false;
         if(GregTech_API.sRecipeAdder.addAlloySmelterRecipe(aInput1, aInput2, aOutput1, aDuration, aEUt)) {
            temp = true;
         }

         if(addInductionSmelterRecipe(aInput1, aInput2, aOutput1, (ItemStack)null, aDuration * 2, 0)) {
            temp = true;
         }

         return temp;
      } else {
         return false;
      }
   }

   public static boolean addInductionSmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aEnergy, int aChance) {
      aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
      aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
      if(aInput1 != null && aOutput1 != null && GT_Utility.getContainerItem(aInput1) == null) {
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.inductionsmelter, aInput2 == null?aInput1:aOutput1, true)) {
            return false;
         } else {
            try {
               GT_ModHandler.ThermalExpansion.addSmelterRecipe(aEnergy, GT_Utility.copy(new Object[]{aInput1}), aInput2 == null?new ItemStack(Block.field_71939_E, 1, 0):aInput2, aOutput1, aOutput2, aChance);
            } catch (Throwable var7) {
               ;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   @Deprecated
   public static boolean addDustToIngotSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
      return false;
   }

   public static boolean addOreToIngotSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
      aOutput = GT_OreDictUnificator.get(true, aOutput);
      if(aInput != null && aOutput != null) {
         FurnaceRecipes.func_77602_a().addSmelting(aInput.field_77993_c, aInput.func_77960_j(), GT_Utility.copy(new Object[]{aOutput}), 0.0F);
         return true;
      } else {
         return false;
      }
   }

   public static Map<IRecipeInput, RecipeOutput> getExtractorRecipeList() {
      try {
         return Recipes.extractor.getRecipes();
      } catch (Throwable var1) {
         return sExtractorRecipes;
      }
   }

   public static Map<IRecipeInput, RecipeOutput> getCompressorRecipeList() {
      try {
         return Recipes.compressor.getRecipes();
      } catch (Throwable var1) {
         return sCompressorRecipes;
      }
   }

   public static Map<IRecipeInput, RecipeOutput> getMaceratorRecipeList() {
      try {
         return Recipes.macerator.getRecipes();
      } catch (Throwable var1) {
         return sMaceratorRecipes;
      }
   }

   public static Map<IRecipeInput, RecipeOutput> getThermalCentrifugeRecipeList() {
      try {
         return Recipes.centrifuge.getRecipes();
      } catch (Throwable var1) {
         return sThermalCentrifugeRecipes;
      }
   }

   public static Map<IRecipeInput, RecipeOutput> getOreWashingRecipeList() {
      try {
         return Recipes.oreWashing.getRecipes();
      } catch (Throwable var1) {
         return sOreWashingRecipes;
      }
   }

   public static Map<IRecipeInput, RecipeOutput> getMassFabricatorList() {
      try {
         return Recipes.matterAmplifier.getRecipes();
      } catch (Throwable var1) {
         return sMassfabRecipes;
      }
   }

   public static boolean addThermalCentrifugeRecipe(ItemStack aInput, int aHeat, Object ... aOutput) {
      if(aInput != null && aOutput != null) {
         GT_Utility.removeSimpleIC2MachineRecipe(aInput, getThermalCentrifugeRecipeList(), (ItemStack)null);
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.thermalcentrifuge, aInput, true)) {
            return false;
         } else {
            NBTTagCompound tNBT = new NBTTagCompound();
            tNBT.func_74768_a("minHeat", aHeat);
            GT_Utility.addSimpleIC2MachineRecipe(aInput, getThermalCentrifugeRecipeList(), tNBT, aOutput);
            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean addOreWasherRecipe(ItemStack aInput, int aWaterAmount, Object ... aOutput) {
      if(aInput != null && aOutput != null) {
         GT_Utility.removeSimpleIC2MachineRecipe(aInput, getOreWashingRecipeList(), (ItemStack)null);
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.orewashing, aInput, true)) {
            return false;
         } else {
            NBTTagCompound tNBT = new NBTTagCompound();
            tNBT.func_74768_a("amount", aWaterAmount);
            GT_Utility.addSimpleIC2MachineRecipe(aInput, getOreWashingRecipeList(), tNBT, aOutput);
            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean addCompressionRecipe(ItemStack aInput, ItemStack aOutput) {
      aOutput = GT_OreDictUnificator.get(true, aOutput);
      if(aInput != null && aOutput != null) {
         GT_Utility.removeSimpleIC2MachineRecipe(aInput, getCompressorRecipeList(), (ItemStack)null);
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.compression, aInput, true)) {
            return false;
         } else {
            GT_Utility.addSimpleIC2MachineRecipe(aInput, getCompressorRecipeList(), (NBTTagCompound)null, new Object[]{aOutput});
            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean addIC2MatterAmplifier(ItemStack aAmplifier, int aValue) {
      if(aAmplifier != null && aValue > 0) {
         if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.massfabamplifier, aAmplifier, true)) {
            return false;
         } else {
            try {
               NBTTagCompound e = new NBTTagCompound();
               e.func_74768_a("amplification", aValue);
               GT_Utility.callMethod(Recipes.matterAmplifier, "addRecipe", false, false, false, new Object[]{aAmplifier, e});
            } catch (Throwable var3) {
               ;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean addRollingMachineRecipe(ItemStack aResult, Object[] aRecipe) {
      aResult = GT_OreDictUnificator.get(true, aResult);
      if(aResult != null && aRecipe != null && aResult.field_77994_a > 0) {
         try {
            RailcraftCraftingManager.rollingMachine.getRecipeList().add(new ShapedOreRecipe(GT_Utility.copy(new Object[]{aResult}), aRecipe));
            return true;
         } catch (Throwable var3) {
            return addCraftingRecipe(GT_Utility.copy(new Object[]{aResult}), false, aRecipe);
         }
      } else {
         return false;
      }
   }

   public static void stopBufferingCraftingRecipes() {
      sBufferCraftingRecipes = false;
      Iterator i$ = sBufferRecipeList.iterator();

      while(i$.hasNext()) {
         IRecipe tRecipe = (IRecipe)i$.next();
         GameRegistry.addRecipe(tRecipe);
      }

      sBufferRecipeList.clear();
   }

   public static boolean addCraftingRecipe(ItemStack aResult, Object[] aRecipe) {
      return addCraftingRecipe(aResult, isElectricItem(aResult), aRecipe);
   }

   public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, Object[] aRecipe) {
      return addCraftingRecipe(aResult, aUseIC2Handler, false, aRecipe);
   }

   public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aMirrored, Object[] aRecipe) {
      return addCraftingRecipe(aResult, aUseIC2Handler, aMirrored, true, aRecipe);
   }

   public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aMirrored, boolean aBuffered, Object[] aRecipe) {
      return addCraftingRecipe(aResult, aUseIC2Handler, aMirrored, aBuffered, false, aRecipe);
   }

   public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aMirrored, boolean aBuffered, boolean aKeepNBT, Object[] aRecipe) {
      aResult = GT_OreDictUnificator.get(true, aResult);
      if(aResult != null && aResult.func_77960_j() == 32767) {
         Item.field_77676_L.setDamage(aResult, 0);
      }

      if(aRecipe != null && aRecipe.length > 0) {
         for(byte e = 0; e < aRecipe.length; ++e) {
            if(aRecipe[e] instanceof Enum) {
               aRecipe[e] = aRecipe[e].toString();
            }
         }

         try {
            String var16 = "";
            int idx = 0;
            if(aRecipe[idx] instanceof Boolean) {
               ++idx;
            }

            while(aRecipe[idx] instanceof String) {
               String itemMap = (String)aRecipe[idx++];

               for(var16 = var16 + itemMap; itemMap.length() < 3; itemMap = itemMap + " ") {
                  ;
               }

               if(itemMap.length() > 3) {
                  throw new IllegalArgumentException();
               }
            }

            if(aRecipe[idx] instanceof Boolean) {
               ++idx;
            }

            HashMap var17 = new HashMap();
            var17.put(Character.valueOf(' '), (Object)null);

            while(true) {
               if(idx < aRecipe.length) {
                  label160: {
                     if(aRecipe[idx] == null || aRecipe[idx + 1] == null) {
                        if(GregTech_API.DEBUG_MODE) {
                           GT_Log.err.println("WARNING: Missing Item for shaped Recipe: " + (aResult == null?"null":aResult.func_82833_r()));
                        }

                        return false;
                     }

                     Character tRecipe = (Character)aRecipe[idx];
                     Object x = aRecipe[idx + 1];
                     if(x instanceof ItemStack) {
                        var17.put(tRecipe, GT_Utility.copy(new Object[]{(ItemStack)x}));
                     } else {
                        if(!(x instanceof String)) {
                           throw new IllegalArgumentException();
                        }

                        ItemStack arr$ = GT_OreDictUnificator.getFirstOre(x, 1L);
                        if(arr$ == null) {
                           break label160;
                        }

                        var17.put(tRecipe, arr$);
                     }

                     idx += 2;
                     continue;
                  }
               }

               ItemStack[] var18 = new ItemStack[9];
               int var19 = -1;
               char[] var20 = var16.toCharArray();
               int len$ = var20.length;

               for(int i$ = 0; i$ < len$; ++i$) {
                  char chr = var20[i$];
                  ++var19;
                  var18[var19] = (ItemStack)var17.get(Character.valueOf(chr));
                  if(var18[var19] != null && var18[var19].func_77960_j() == 32767) {
                     Item.field_77676_L.setDamage(var18[var19], 0);
                  }
               }

               removeRecipe(var18);
               break;
            }
         } catch (Throwable var15) {
            var15.printStackTrace(GT_Log.err);
         }

         if(aResult != null && aResult.field_77994_a > 0) {
            if(aResult.func_77960_j() != 32767 && aResult.func_77960_j() >= 0) {
               if(aKeepNBT) {
                  GameRegistry.addRecipe((new GT_Shaped_NBT_Keeping_Recipe(GT_Utility.copy(new Object[]{aResult}), aRecipe)).setMirrored(aMirrored));
                  return true;
               } else {
                  if(sBufferCraftingRecipes && aBuffered) {
                     sBufferRecipeList.add((new GT_Shaped_Recipe(GT_Utility.copy(new Object[]{aResult}), aRecipe)).setMirrored(aMirrored));
                  } else {
                     GameRegistry.addRecipe((new GT_Shaped_Recipe(GT_Utility.copy(new Object[]{aResult}), aRecipe)).setMirrored(aMirrored));
                  }

                  return true;
               }
            } else {
               throw new IllegalArgumentException();
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean addShapelessCraftingRecipe(ItemStack aResult, Object[] aRecipe) {
      return addShapelessCraftingRecipe(aResult, isElectricItem(aResult), aRecipe);
   }

   public static boolean addShapelessCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, Object[] aRecipe) {
      return addShapelessCraftingRecipe(aResult, aUseIC2Handler, true, aRecipe);
   }

   public static boolean addShapelessCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aBuffered, Object[] aRecipe) {
      return addShapelessCraftingRecipe(aResult, aUseIC2Handler, aBuffered, false, aRecipe);
   }

   public static boolean addShapelessCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aBuffered, boolean aKeepNBT, Object[] aRecipe) {
      aResult = GT_OreDictUnificator.get(true, aResult);
      if(aRecipe != null && aRecipe.length > 0) {
         for(byte e = 0; e < aRecipe.length; ++e) {
            if(aRecipe[e] instanceof Enum) {
               aRecipe[e] = aRecipe[e].toString();
            }
         }

         try {
            ItemStack[] var12 = new ItemStack[9];
            int i = 0;
            Object[] arr$ = aRecipe;
            int len$ = aRecipe.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               Object tObject = arr$[i$];
               if(tObject == null) {
                  if(GregTech_API.DEBUG_MODE) {
                     GT_Log.err.println("WARNING: Missing Item for shapeless Recipe: " + (aResult == null?"null":aResult.func_82833_r()));
                  }

                  return false;
               }

               if(tObject instanceof ItemStack) {
                  var12[i] = (ItemStack)tObject;
               } else if(tObject instanceof String) {
                  var12[i] = GT_OreDictUnificator.getFirstOre(tObject, 1L);
               } else if(!(tObject instanceof Boolean)) {
                  throw new IllegalArgumentException();
               }

               ++i;
            }

            removeRecipe(var12);
         } catch (Throwable var11) {
            var11.printStackTrace(GT_Log.err);
         }

         if(aResult != null && aResult.field_77994_a > 0) {
            if(aResult.func_77960_j() != 32767 && aResult.func_77960_j() >= 0) {
               if(aKeepNBT) {
                  GameRegistry.addRecipe(new GT_Shapeless_NBT_Keeping_Recipe(GT_Utility.copy(new Object[]{aResult}), aRecipe));
                  return true;
               } else {
                  if(sBufferCraftingRecipes && aBuffered) {
                     sBufferRecipeList.add(new GT_Shapeless_Recipe(GT_Utility.copy(new Object[]{aResult}), aRecipe));
                  } else {
                     GameRegistry.addRecipe(new GT_Shapeless_Recipe(GT_Utility.copy(new Object[]{aResult}), aRecipe));
                  }

                  return true;
               }
            } else {
               throw new IllegalArgumentException();
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean removeFurnaceSmelting(ItemStack aInput) {
      if(aInput != null) {
         FurnaceRecipes.func_77602_a().getMetaSmeltingList().remove(Arrays.asList(new Integer[]{Integer.valueOf(aInput.field_77993_c), Integer.valueOf(aInput.func_77960_j())}));
         FurnaceRecipes.func_77602_a().func_77599_b().remove(Integer.valueOf(aInput.field_77993_c));
         return true;
      } else {
         return false;
      }
   }

   public static ItemStack removeRecipe(ItemStack ... aRecipe) {
      if(aRecipe == null) {
         return null;
      } else {
         boolean temp = false;

         for(byte rReturn = 0; rReturn < aRecipe.length; ++rReturn) {
            if(aRecipe[rReturn] != null) {
               temp = true;
               break;
            }
         }

         if(!temp) {
            return null;
         } else {
            ItemStack var9 = null;
            InventoryCrafting aCrafting = new InventoryCrafting(new Container() {
               public boolean func_75145_c(EntityPlayer var1) {
                  return false;
               }
            }, 3, 3);

            for(int tList = 0; tList < aRecipe.length && tList < 9; ++tList) {
               aCrafting.func_70299_a(tList, aRecipe[tList]);
            }

            ArrayList var10 = (ArrayList)CraftingManager.func_77594_a().func_77592_b();

            int i;
            for(i = 0; i < var10.size(); ++i) {
               try {
                  if(((IRecipe)var10.get(i)).func_77569_a(aCrafting, GregTech_API.sDummyWorld)) {
                     var9 = ((IRecipe)var10.get(i)).func_77572_b(aCrafting);
                     var10.remove(i--);
                  }
               } catch (Throwable var8) {
                  var8.printStackTrace(GT_Log.err);
               }
            }

            for(i = 0; i < sBufferRecipeList.size(); ++i) {
               try {
                  if(((IRecipe)sBufferRecipeList.get(i)).func_77569_a(aCrafting, GregTech_API.sDummyWorld)) {
                     var9 = ((IRecipe)sBufferRecipeList.get(i)).func_77572_b(aCrafting);
                     sBufferRecipeList.remove(i--);
                  }
               } catch (Throwable var7) {
                  var7.printStackTrace(GT_Log.err);
               }
            }

            return var9;
         }
      }
   }

   public static boolean removeRecipeByOutput(ItemStack aOutput) {
      if(aOutput == null) {
         return false;
      } else {
         boolean rReturn = false;
         ArrayList tList = (ArrayList)CraftingManager.func_77594_a().func_77592_b();
         aOutput = GT_OreDictUnificator.get(aOutput);

         for(int i = 0; i < tList.size(); ++i) {
            if(GT_Utility.areStacksEqual(GT_OreDictUnificator.get(((IRecipe)tList.get(i)).func_77571_b()), aOutput)) {
               tList.remove(i--);
               rReturn = true;
            }
         }

         return rReturn;
      }
   }

   public static ItemStack getAllRecipeOutput(World aWorld, ItemStack ... aRecipe) {
      if(aRecipe == null) {
         return null;
      } else {
         boolean temp = false;

         for(byte aCrafting = 0; aCrafting < aRecipe.length; ++aCrafting) {
            if(aRecipe[aCrafting] != null) {
               temp = true;
               break;
            }
         }

         if(!temp) {
            return null;
         } else {
            InventoryCrafting var12 = new InventoryCrafting(new Container() {
               public boolean func_75145_c(EntityPlayer var1) {
                  return false;
               }
            }, 3, 3);

            for(int tList = 0; tList < 9 && tList < aRecipe.length; ++tList) {
               var12.func_70299_a(tList, aRecipe[tList]);
            }

            List var13 = CraftingManager.func_77594_a().func_77592_b();
            List tIndex = sAllRecipeList;
            synchronized(sAllRecipeList) {
               if(sAllRecipeList.size() != var13.size()) {
                  sAllRecipeList.clear();
                  sAllRecipeList.addAll(var13);
               }

               int tStack1 = 0;

               for(int tStack2 = sAllRecipeList.size(); tStack1 < tStack2; ++tStack1) {
                  IRecipe tNewDamage = (IRecipe)sAllRecipeList.get(tStack1);
                  if(tNewDamage.func_77569_a(var12, aWorld)) {
                     if(tStack1 > 10) {
                        sAllRecipeList.remove(tStack1);
                        sAllRecipeList.add(tStack1 - 10, tNewDamage);
                     }

                     return tNewDamage.func_77572_b(var12);
                  }
               }
            }

            int var14 = 0;
            ItemStack var15 = null;
            ItemStack var16 = null;
            int var17 = 0;

            for(int j = var12.func_70302_i_(); var17 < j; ++var17) {
               ItemStack tStack = var12.func_70301_a(var17);
               if(tStack != null) {
                  if(var14 == 0) {
                     var15 = tStack;
                  }

                  if(var14 == 1) {
                     var16 = tStack;
                  }

                  ++var14;
               }
            }

            if(var14 == 2) {
               assert var15 != null && var16 != null;

               if(var15.func_77973_b() == var16.func_77973_b() && var15.field_77994_a == 1 && var16.field_77994_a == 1 && var15.func_77973_b().isRepairable()) {
                  var17 = var15.func_77958_k() + var15.func_77960_j() - var16.func_77960_j() + var15.func_77958_k() / 20;
                  return new ItemStack(var15.field_77993_c, 1, var17 < 0?0:var17);
               }
            }

            return null;
         }
      }
   }

   public static ItemStack getRecipeOutput(ItemStack ... aRecipe) {
      return getRecipeOutput(false, aRecipe);
   }

   public static ItemStack getRecipeOutput(boolean aUncopiedStack, ItemStack ... aRecipe) {
      if(aRecipe == null) {
         return null;
      } else {
         boolean temp = false;

         for(byte aCrafting = 0; aCrafting < aRecipe.length; ++aCrafting) {
            if(aRecipe[aCrafting] != null) {
               temp = true;
               break;
            }
         }

         if(!temp) {
            return null;
         } else {
            InventoryCrafting var8 = new InventoryCrafting(new Container() {
               public boolean func_75145_c(EntityPlayer var1) {
                  return false;
               }
            }, 3, 3);

            for(int tList = 0; tList < 9 && tList < aRecipe.length; ++tList) {
               var8.func_70299_a(tList, aRecipe[tList]);
            }

            ArrayList var9 = (ArrayList)CraftingManager.func_77594_a().func_77592_b();

            for(int i = 0; i < var9.size(); ++i) {
               temp = false;

               try {
                  temp = ((IRecipe)var9.get(i)).func_77569_a(var8, GregTech_API.sDummyWorld);
               } catch (Throwable var7) {
                  var7.printStackTrace(GT_Log.err);
               }

               if(temp) {
                  ItemStack tOutput = aUncopiedStack?((IRecipe)var9.get(i)).func_77571_b():((IRecipe)var9.get(i)).func_77572_b(var8);
                  if(tOutput != null && tOutput.field_77994_a > 0) {
                     if(aUncopiedStack) {
                        return tOutput;
                     }

                     return GT_Utility.copy(new Object[]{tOutput});
                  }

                  if(!GregTech_API.sPostloadFinished) {
                     throw new GT_ItsNotMyFaultException("Seems another Mod added a Crafting Recipe with null Output. Tell the Developer of said Mod to fix that.");
                  }
               }
            }

            return null;
         }
      }
   }

   public static ArrayList<ItemStack> getVanillyToolRecipeOutputs(ItemStack ... aRecipe) {
      if(!GregTech_API.sPostloadStarted || GregTech_API.sPostloadFinished) {
         sSingleNonBlockDamagableRecipeList.clear();
      }

      if(sSingleNonBlockDamagableRecipeList.isEmpty()) {
         Iterator rList = ((ArrayList)CraftingManager.func_77594_a().func_77592_b()).iterator();

         while(rList.hasNext()) {
            IRecipe tRecipe = (IRecipe)rList.next();
            ItemStack tStack = tRecipe.func_77571_b();
            if(tStack != null && tStack.func_77973_b() != null && tStack.func_77976_d() == 1 && tStack.func_77958_k() > 0 && !isElectricItem(tStack) && !(tStack.func_77973_b() instanceof ItemBlock) && !(tRecipe instanceof ShapelessRecipes) && !(tRecipe instanceof ShapelessOreRecipe)) {
               boolean temp;
               int len$;
               int i$;
               if(tRecipe instanceof ShapedOreRecipe) {
                  temp = true;
                  Object[] var10 = ((ShapedOreRecipe)tRecipe).getInput();
                  len$ = var10.length;

                  for(i$ = 0; i$ < len$; ++i$) {
                     Object var11 = var10[i$];
                     if(var11 != null && var11 instanceof ItemStack && (((ItemStack)var11).func_77973_b() == null || ((ItemStack)var11).func_77976_d() < 2 || ((ItemStack)var11).func_77958_k() > 0 || ((ItemStack)var11).func_77973_b() instanceof ItemBlock)) {
                        temp = false;
                        break;
                     }
                  }

                  if(temp) {
                     sSingleNonBlockDamagableRecipeList.add(tRecipe);
                  }
               } else if(!(tRecipe instanceof ShapedRecipes)) {
                  sSingleNonBlockDamagableRecipeList.add(tRecipe);
               } else {
                  temp = true;
                  ItemStack[] arr$ = ((ShapedRecipes)tRecipe).field_77574_d;
                  len$ = arr$.length;

                  for(i$ = 0; i$ < len$; ++i$) {
                     ItemStack tObject = arr$[i$];
                     if(tObject != null && (tObject.func_77973_b() == null || tObject.func_77976_d() < 2 || tObject.func_77958_k() > 0 || tObject.func_77973_b() instanceof ItemBlock)) {
                        temp = false;
                        break;
                     }
                  }

                  if(temp) {
                     sSingleNonBlockDamagableRecipeList.add(tRecipe);
                  }
               }
            }
         }

         GT_Log.out.println("GT_Mod: Created a List of Tool Recipes containing " + sSingleNonBlockDamagableRecipeList.size() + " Recipes for recycling." + (sSingleNonBlockDamagableRecipeList.size() > 2048?" Scanning all these Recipes is the reason for the startup Lag you receive right now.":""));
      }

      ArrayList var9 = getRecipeOutputs(sSingleNonBlockDamagableRecipeList, true, aRecipe);
      if(!GregTech_API.sPostloadStarted || GregTech_API.sPostloadFinished) {
         sSingleNonBlockDamagableRecipeList.clear();
      }

      return var9;
   }

   public static ArrayList<ItemStack> getRecipeOutputs(ItemStack ... aRecipe) {
      return getRecipeOutputs(CraftingManager.func_77594_a().func_77592_b(), false, aRecipe);
   }

   public static ArrayList<ItemStack> getRecipeOutputs(List<IRecipe> aList, boolean aDeleteFromList, ItemStack ... aRecipe) {
      ArrayList rList = new ArrayList();
      if(aRecipe == null) {
         return rList;
      } else {
         boolean temp = false;

         for(byte aCrafting = 0; aCrafting < aRecipe.length; ++aCrafting) {
            if(aRecipe[aCrafting] != null) {
               temp = true;
               break;
            }
         }

         if(!temp) {
            return rList;
         } else {
            InventoryCrafting var9 = new InventoryCrafting(new Container() {
               public boolean func_75145_c(EntityPlayer var1) {
                  return false;
               }
            }, 3, 3);

            int i;
            for(i = 0; i < 9 && i < aRecipe.length; ++i) {
               var9.func_70299_a(i, aRecipe[i]);
            }

            for(i = 0; i < aList.size(); ++i) {
               temp = false;

               try {
                  temp = ((IRecipe)aList.get(i)).func_77569_a(var9, GregTech_API.sDummyWorld);
               } catch (Throwable var8) {
                  var8.printStackTrace(GT_Log.err);
               }

               if(temp) {
                  ItemStack tOutput = ((IRecipe)aList.get(i)).func_77572_b(var9);
                  if(tOutput != null && tOutput.field_77994_a > 0) {
                     rList.add(GT_Utility.copy(new Object[]{tOutput}));
                     if(aDeleteFromList) {
                        aList.remove(i--);
                     }
                  } else if(!GregTech_API.sPostloadFinished) {
                     throw new GT_ItsNotMyFaultException("Seems another Mod added a Crafting Recipe with null Output. Tell the Developer of said Mod to fix that.");
                  }
               }
            }

            return rList;
         }
      }
   }

   public static ItemStack getMaceratorOutput(ItemStack aInput, boolean aRemoveInput, ItemStack aOutputSlot) {
      return GT_Utility.copy(new Object[]{getMachineOutput(aInput, getMaceratorRecipeList(), aRemoveInput, new ItemStack[]{aOutputSlot})[0]});
   }

   public static ItemStack getExtractorOutput(ItemStack aInput, boolean aRemoveInput, ItemStack aOutputSlot) {
      return GT_Utility.copy(new Object[]{getMachineOutput(aInput, getExtractorRecipeList(), aRemoveInput, new ItemStack[]{aOutputSlot})[0]});
   }

   public static ItemStack getCompressorOutput(ItemStack aInput, boolean aRemoveInput, ItemStack aOutputSlot) {
      return GT_Utility.copy(new Object[]{getMachineOutput(aInput, getCompressorRecipeList(), aRemoveInput, new ItemStack[]{aOutputSlot})[0]});
   }

   public static ItemStack getSmeltingOutput(ItemStack aInput, boolean aRemoveInput, ItemStack aOutputSlot) {
      if(aInput == null) {
         return null;
      } else {
         ItemStack rStack = GT_OreDictUnificator.get(FurnaceRecipes.func_77602_a().getSmeltingResult(aInput));
         if(rStack != null && (aOutputSlot == null || GT_Utility.areStacksEqual(rStack, aOutputSlot) && rStack.field_77994_a + aOutputSlot.field_77994_a <= aOutputSlot.func_77976_d())) {
            if(aRemoveInput) {
               --aInput.field_77994_a;
            }

            return rStack;
         } else {
            return null;
         }
      }
   }

   public static ItemStack[] getMachineOutput(ItemStack aInput, Map<IRecipeInput, RecipeOutput> aRecipeList, boolean aRemoveInput, ItemStack ... aOutputSlots) {
      if(aOutputSlots != null && aOutputSlots.length > 0) {
         if(aInput == null) {
            return new ItemStack[aOutputSlots.length];
         } else {
            try {
               Iterator e = aRecipeList.entrySet().iterator();

               while(e.hasNext()) {
                  Entry tEntry = (Entry)e.next();
                  if(((IRecipeInput)tEntry.getKey()).matches(aInput)) {
                     if(((IRecipeInput)tEntry.getKey()).getAmount() <= aInput.field_77994_a) {
                        ItemStack[] tList = (ItemStack[])((ItemStack[])((RecipeOutput)tEntry.getValue()).items.toArray());
                        if(tList.length != 0) {
                           ItemStack[] rList = new ItemStack[aOutputSlots.length];

                           for(byte i = 0; i < aOutputSlots.length; ++i) {
                              if(tList[i] != null) {
                                 if(aOutputSlots[i] != null && (!GT_Utility.areStacksEqual(tList[i], aOutputSlots[i]) || tList[i].field_77994_a + aOutputSlots[i].field_77994_a > aOutputSlots[i].func_77976_d())) {
                                    return new ItemStack[aOutputSlots.length];
                                 }

                                 rList[i] = GT_Utility.copy(new Object[]{tList[i]});
                              }
                           }

                           if(aRemoveInput) {
                              aInput.field_77994_a -= ((IRecipeInput)tEntry.getKey()).getAmount();
                           }

                           return rList;
                        }
                     }
                     break;
                  }
               }
            } catch (Throwable var9) {
               if(GregTech_API.DEBUG_MODE) {
                  var9.printStackTrace(GT_Log.err);
               }
            }

            return new ItemStack[aOutputSlots.length];
         }
      } else {
         return new ItemStack[0];
      }
   }

   public static ItemStack getRecyclerOutput(ItemStack aInput, int aScrapChance) {
      if(aInput != null && aScrapChance == 0) {
         try {
            return Recipes.recyclerWhitelist.isEmpty()?(Recipes.recyclerBlacklist.contains(aInput)?null:GT_Items.IC2_Scrap.get(1L, new Object[0])):(Recipes.recyclerWhitelist.contains(aInput)?GT_Items.IC2_Scrap.get(1L, new Object[0]):null);
         } catch (Throwable var4) {
            try {
               return Recipes.recyclerBlacklist.contains(aInput)?null:GT_Items.IC2_Scrap.get(1L, new Object[0]);
            } catch (Throwable var3) {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public static ItemStack getRandomScrapboxDrop() {
      return Recipes.scrapboxDrops.getDrop(GT_Items.IC2_Scrapbox.get(1L, new Object[0]), false);
   }

   public static boolean addTileToEnet(World aWorld, TileEntity aTileEntity) {
      try {
         Object e = GT_Utility.callPublicMethod(aTileEntity, "isAddedToEnergyNet", new Object[0]);
         if(aTileEntity instanceof IEnergyTile && e != null && e instanceof Boolean && !((Boolean)e).booleanValue()) {
            Event tEvent = (Event)Class.forName("ic2.api.energy.event.EnergyTileLoadEvent").getConstructors()[0].newInstance(new Object[]{(IEnergyTile)aTileEntity});
            MinecraftForge.EVENT_BUS.post(tEvent);
            return true;
         }
      } catch (Throwable var4) {
         GT_Log.err.println("E-net Error. Please report to GregTech ASAP!");
         var4.printStackTrace(GT_Log.err);
      }

      return false;
   }

   public static boolean removeTileFromEnet(World aWorld, TileEntity aTileEntity) {
      try {
         Object e = GT_Utility.callPublicMethod(aTileEntity, "isAddedToEnergyNet", new Object[0]);
         if(aTileEntity instanceof IEnergyTile && e != null && e instanceof Boolean && ((Boolean)e).booleanValue()) {
            Event tEvent = (Event)Class.forName("ic2.api.energy.event.EnergyTileUnloadEvent").getConstructors()[0].newInstance(new Object[]{(IEnergyTile)aTileEntity});
            MinecraftForge.EVENT_BUS.post(tEvent);
            return true;
         }
      } catch (Throwable var4) {
         GT_Log.err.println("E-net Error. Please report to GregTech ASAP!");
         var4.printStackTrace(GT_Log.err);
      }

      return false;
   }

   public static int emitEnergyToEnet(int aVoltage, World aWorld, TileEntity aTileEntity) {
      return aVoltage;
   }

   public static int chargeElectricItem(ItemStack aStack, int aCharge, int aTier, boolean aIgnoreLimit, boolean aSimulate) {
      try {
         if(isElectricItem(aStack)) {
            return ElectricItem.manager.charge(aStack, aCharge, aTier, aIgnoreLimit, aSimulate);
         }
      } catch (Throwable var6) {
         ;
      }

      return 0;
   }

   public static int dischargeElectricItem(ItemStack aStack, int aCharge, int aTier, boolean aIgnoreLimit, boolean aSimulate, boolean aIgnoreDischargability) {
      try {
         if(isElectricItem(aStack) && (aIgnoreDischargability || ((IElectricItem)aStack.func_77973_b()).canProvideEnergy(aStack))) {
            return ElectricItem.manager.discharge(aStack, aCharge, aTier, aIgnoreLimit, aSimulate);
         }
      } catch (Throwable var7) {
         ;
      }

      return 0;
   }

   public static int getMaxElectricCharge(ItemStack aStack) {
      try {
         if(isElectricItem(aStack)) {
            return ((IElectricItem)aStack.func_77973_b()).getMaxCharge(aStack);
         }
      } catch (Throwable var2) {
         ;
      }

      return 0;
   }

   public static boolean canUseElectricItem(ItemStack aStack, int aCharge) {
      try {
         if(isElectricItem(aStack)) {
            return ElectricItem.manager.canUse(aStack, aCharge);
         }
      } catch (Throwable var3) {
         ;
      }

      return false;
   }

   public static boolean useElectricItem(ItemStack aStack, int aCharge, EntityPlayer aPlayer) {
      try {
         if(isElectricItem(aStack)) {
            ElectricItem.manager.use(aStack, 0, aPlayer);
            if(ElectricItem.manager.canUse(aStack, aCharge)) {
               return ElectricItem.manager.use(aStack, aCharge, aPlayer);
            }
         }
      } catch (Throwable var4) {
         ;
      }

      return false;
   }

   public static boolean damageOrDechargeItem(ItemStack aStack, int aDamage, int aDecharge, EntityLivingBase aPlayer) {
      if(!GT_Utility.isStackInvalid(aStack) && (aStack.func_77976_d() > 1 || aStack.field_77994_a <= 1)) {
         if(aPlayer != null && aPlayer instanceof EntityPlayer && ((EntityPlayer)aPlayer).field_71075_bZ.field_75098_d) {
            return true;
         } else {
            if(isElectricItem(aStack)) {
               if(canUseElectricItem(aStack, aDecharge)) {
                  if(aPlayer != null && aPlayer instanceof EntityPlayer) {
                     return useElectricItem(aStack, aDecharge, (EntityPlayer)aPlayer);
                  }

                  return dischargeElectricItem(aStack, aDecharge, Integer.MAX_VALUE, true, false, true) >= aDecharge;
               }
            } else if(aStack.func_77973_b().func_77645_m()) {
               if(aPlayer == null) {
                  aStack.func_77964_b(aStack.func_77960_j() + aDamage);
               } else {
                  aStack.func_77972_a(aDamage, aPlayer);
               }

               if(aStack.func_77960_j() >= aStack.func_77958_k()) {
                  aStack.func_77964_b(aStack.func_77958_k() + 1);
                  if(GT_Utility.getContainerItem(aStack) != null) {
                     ItemStack tStack = aStack.func_77973_b().getContainerItemStack(aStack);
                     if(tStack != null) {
                        aStack.field_77993_c = tStack.field_77993_c;
                        aStack.func_77964_b(tStack.func_77960_j());
                        aStack.field_77994_a = tStack.field_77994_a;
                        aStack.func_77982_d(tStack.func_77978_p());
                     }
                  }
               }

               return true;
            }

            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean useSolderingIron(ItemStack aStack, EntityLivingBase aPlayer) {
      if(aPlayer != null && aStack != null) {
         if(GT_Utility.isItemStackInList(aStack, GregTech_API.sSolderingToolList)) {
            if(!(aPlayer instanceof EntityPlayer)) {
               damageOrDechargeItem(aStack, 1, 1000, aPlayer);
               return true;
            }

            EntityPlayer tPlayer = (EntityPlayer)aPlayer;
            if(tPlayer.field_71075_bZ.field_75098_d) {
               return true;
            }

            for(int i = 0; i < tPlayer.field_71071_by.field_70462_a.length; ++i) {
               if(GT_Utility.isItemStackInList(tPlayer.field_71071_by.field_70462_a[i], GregTech_API.sSolderingMetalList) && damageOrDechargeItem(aStack, 1, 1000, tPlayer)) {
                  if(tPlayer.field_71071_by.field_70462_a[i].func_77960_j() >= tPlayer.field_71071_by.field_70462_a[i].func_77958_k()) {
                     tPlayer.field_71071_by.field_70462_a[i] = null;
                  }

                  if(damageOrDechargeItem(tPlayer.field_71071_by.field_70462_a[i], 1, 1000, tPlayer)) {
                     if(tPlayer.field_71071_by.field_70462_a[i].func_77960_j() >= tPlayer.field_71071_by.field_70462_a[i].func_77958_k()) {
                        tPlayer.field_71071_by.field_70462_a[i] = null;
                     }

                     if(tPlayer.field_71069_bz != null) {
                        tPlayer.field_71069_bz.func_75142_b();
                     }

                     return true;
                  }
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static boolean isChargerItem(ItemStack aStack) {
      try {
         if(isElectricItem(aStack)) {
            return ((IElectricItem)aStack.func_77973_b()).canProvideEnergy(aStack);
         }
      } catch (Throwable var2) {
         ;
      }

      return false;
   }

   public static boolean isElectricItem(ItemStack aStack) {
      try {
         return aStack != null && aStack.func_77973_b() instanceof IElectricItem && ((IElectricItem)aStack.func_77973_b()).getTier(aStack) < Integer.MAX_VALUE;
      } catch (Throwable var2) {
         return false;
      }
   }

   public static boolean isElectricItem(ItemStack aStack, byte aTier) {
      try {
         return aStack != null && aStack.func_77973_b() instanceof IElectricItem && ((IElectricItem)aStack.func_77973_b()).getTier(aStack) <= aTier;
      } catch (Throwable var3) {
         return false;
      }
   }

   public static boolean acceptsGT(TileEntity aTileEntity) {
      try {
         return aTileEntity instanceof IBasicEnergyContainer;
      } catch (Throwable var2) {
         return false;
      }
   }

   public static boolean acceptsEU(TileEntity aTileEntity) {
      try {
         return aTileEntity instanceof IEnergyAcceptor;
      } catch (Throwable var2) {
         return false;
      }
   }

   public static boolean acceptsMJ(TileEntity aTileEntity) {
      try {
         return aTileEntity instanceof IPowerReceptor;
      } catch (Throwable var2) {
         return false;
      }
   }

   public static boolean acceptsUE(TileEntity aTileEntity) {
      try {
         return aTileEntity instanceof IElectricalStorage;
      } catch (Throwable var2) {
         return false;
      }
   }

   public static void registerBoxableItemToToolBox(ItemStack aStack) {
      if(aStack != null) {
         registerBoxableItemToToolBox(aStack.func_77973_b());
      }

   }

   public static void registerBoxableItemToToolBox(Item aItem) {
      if(aItem != null && sBoxableWrapper != null) {
         try {
            ItemWrapper.registerBoxable(aItem, (IBoxable)sBoxableWrapper);
         } catch (Throwable var2) {
            ;
         }
      }

   }

   public static int getCapsuleCellContainerCountMultipliedWithStackSize(ItemStack ... aStacks) {
      int rAmount = 0;
      ItemStack[] arr$ = aStacks;
      int len$ = aStacks.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ItemStack tStack = arr$[i$];
         if(tStack != null) {
            rAmount += getCapsuleCellContainerCount(tStack) * tStack.field_77994_a;
         }
      }

      return rAmount;
   }

   public static int getCapsuleCellContainerCount(ItemStack aStack) {
      if(aStack == null) {
         return 0;
      } else {
         Item tItem = aStack.func_77973_b();
         return tItem == null?0:(!GT_Utility.areStacksEqual(GT_Utility.getContainerForFilledItem(aStack), GT_Items.Cell_Empty.get(1L, new Object[0])) && !OrePrefixes.cell.contains(aStack) && !OrePrefixes.cellPlasma.contains(aStack)?(GT_Utility.areStacksEqual(aStack, getIC2Item("hydratedCoalCell", 1L, 32767))?1:0):1);
      }
   }


   public static class ThermalExpansion {

      public static void addFurnaceRecipe(int energy, ItemStack input, ItemStack output) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74768_a("energy", energy);
         toSend.func_74766_a("input", new NBTTagCompound());
         toSend.func_74766_a("output", new NBTTagCompound());
         input.func_77955_b(toSend.func_74775_l("input"));
         output.func_77955_b(toSend.func_74775_l("output"));
         FMLInterModComms.sendMessage("ThermalExpansion", "FurnaceRecipe", toSend);
      }

      public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput) {
         addPulverizerRecipe(energy, input, primaryOutput, (ItemStack)null, 0);
      }

      public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput) {
         addPulverizerRecipe(energy, input, primaryOutput, secondaryOutput, 100);
      }

      public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74768_a("energy", energy);
         toSend.func_74766_a("input", new NBTTagCompound());
         toSend.func_74766_a("primaryOutput", new NBTTagCompound());
         toSend.func_74766_a("secondaryOutput", new NBTTagCompound());
         input.func_77955_b(toSend.func_74775_l("input"));
         primaryOutput.func_77955_b(toSend.func_74775_l("primaryOutput"));
         if(secondaryOutput != null) {
            secondaryOutput.func_77955_b(toSend.func_74775_l("secondaryOutput"));
         }

         toSend.func_74768_a("secondaryChance", secondaryChance);
         FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", toSend);
      }

      public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput) {
         addSawmillRecipe(energy, input, primaryOutput, (ItemStack)null, 0);
      }

      public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput) {
         addSawmillRecipe(energy, input, primaryOutput, secondaryOutput, 100);
      }

      public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74768_a("energy", energy);
         toSend.func_74766_a("input", new NBTTagCompound());
         toSend.func_74766_a("primaryOutput", new NBTTagCompound());
         toSend.func_74766_a("secondaryOutput", new NBTTagCompound());
         input.func_77955_b(toSend.func_74775_l("input"));
         primaryOutput.func_77955_b(toSend.func_74775_l("primaryOutput"));
         if(secondaryOutput != null) {
            secondaryOutput.func_77955_b(toSend.func_74775_l("secondaryOutput"));
         }

         toSend.func_74768_a("secondaryChance", secondaryChance);
         FMLInterModComms.sendMessage("ThermalExpansion", "SawmillRecipe", toSend);
      }

      public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput) {
         addSmelterRecipe(energy, primaryInput, secondaryInput, primaryOutput, (ItemStack)null, 0);
      }

      public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput) {
         addSmelterRecipe(energy, primaryInput, secondaryInput, primaryOutput, secondaryOutput, 100);
      }

      public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74768_a("energy", energy);
         toSend.func_74766_a("primaryInput", new NBTTagCompound());
         toSend.func_74766_a("secondaryInput", new NBTTagCompound());
         toSend.func_74766_a("primaryOutput", new NBTTagCompound());
         toSend.func_74766_a("secondaryOutput", new NBTTagCompound());
         primaryInput.func_77955_b(toSend.func_74775_l("primaryInput"));
         secondaryInput.func_77955_b(toSend.func_74775_l("secondaryInput"));
         primaryOutput.func_77955_b(toSend.func_74775_l("primaryOutput"));
         if(secondaryOutput != null) {
            secondaryOutput.func_77955_b(toSend.func_74775_l("secondaryOutput"));
         }

         toSend.func_74768_a("secondaryChance", secondaryChance);
         FMLInterModComms.sendMessage("ThermalExpansion", "SmelterRecipe", toSend);
      }

      public static void addSmelterBlastOre(Materials aMaterial) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74778_a("oreType", aMaterial.toString());
         FMLInterModComms.sendMessage("ThermalExpansion", "SmelterBlastOreType", toSend);
      }

      public static void addCrucibleRecipe(int energy, ItemStack input, FluidStack output) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74768_a("energy", energy);
         toSend.func_74766_a("input", new NBTTagCompound());
         toSend.func_74766_a("output", new NBTTagCompound());
         input.func_77955_b(toSend.func_74775_l("input"));
         output.writeToNBT(toSend.func_74775_l("output"));
         FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", toSend);
      }

      public static void addTransposerFill(int energy, ItemStack input, ItemStack output, FluidStack fluid, boolean reversible) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74768_a("energy", energy);
         toSend.func_74766_a("input", new NBTTagCompound());
         toSend.func_74766_a("output", new NBTTagCompound());
         toSend.func_74766_a("fluid", new NBTTagCompound());
         input.func_77955_b(toSend.func_74775_l("input"));
         output.func_77955_b(toSend.func_74775_l("output"));
         toSend.func_74757_a("reversible", reversible);
         fluid.writeToNBT(toSend.func_74775_l("fluid"));
         FMLInterModComms.sendMessage("ThermalExpansion", "TransposerFillRecipe", toSend);
      }

      public static void addTransposerExtract(int energy, ItemStack input, ItemStack output, FluidStack fluid, int chance, boolean reversible) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74768_a("energy", energy);
         toSend.func_74766_a("input", new NBTTagCompound());
         toSend.func_74766_a("output", new NBTTagCompound());
         toSend.func_74766_a("fluid", new NBTTagCompound());
         input.func_77955_b(toSend.func_74775_l("input"));
         output.func_77955_b(toSend.func_74775_l("output"));
         toSend.func_74757_a("reversible", reversible);
         toSend.func_74768_a("chance", chance);
         fluid.writeToNBT(toSend.func_74775_l("fluid"));
         FMLInterModComms.sendMessage("ThermalExpansion", "TransposerExtractRecipe", toSend);
      }

      public static void addMagmaticFuel(String fluidName, int energy) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74778_a("fluidName", fluidName);
         toSend.func_74768_a("energy", energy);
         FMLInterModComms.sendMessage("ThermalExpansion", "MagmaticFuel", toSend);
      }

      public static void addCompressionFuel(String fluidName, int energy) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74778_a("fluidName", fluidName);
         toSend.func_74768_a("energy", energy);
         FMLInterModComms.sendMessage("ThermalExpansion", "CompressionFuel", toSend);
      }

      public static void addCoolant(String fluidName, int energy) {
         NBTTagCompound toSend = new NBTTagCompound();
         toSend.func_74778_a("fluidName", fluidName);
         toSend.func_74768_a("energy", energy);
         FMLInterModComms.sendMessage("ThermalExpansion", "Coolant", toSend);
      }
   }
}
