package gregtechmod.loaders.load;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.GT_FluidRegistry;
import gregtechmod.common.items.GT_MetaItem_Cell;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

public class GT_LiquidAndFuelLoader implements Runnable {

   public void run() {
      GT_Log.out.println("GT_Mod: Grabbing Liquids of other Mods to register Fluid Cells");
      ItemStack tStack = GT_ModHandler.getRCItem("fluid.creosote.cell", 1L);
      FluidStack tLiquid = GT_Utility.getFluidForFilledItem(tStack);
      if(tLiquid != null) {
         tLiquid = tLiquid.copy();
         tLiquid.amount = 1000;
         Materials.Creosote.mFluid = tLiquid;
      }

      Materials.Water.mFluid = Materials.Ice.mFluid = GT_ModHandler.getWater(1000L);
      Materials.Lava.mFluid = GT_ModHandler.getLava(1000L);
      Materials.Milk.mFluid = GT_Utility.getFluidForFilledItem(new ItemStack(Item.field_77771_aG, 1, 0));
      Materials.ConstructionFoam.mFluid = GT_Utility.getFluidForFilledItem(GT_ModHandler.getIC2Item("CFCell", 1L));
      Materials.UUMatter.mFluid = GT_Utility.getFluidForFilledItem(GT_ModHandler.getIC2Item("UuMatterCell", 1L));
      if(Materials.Milk.mFluid != null) {
         FluidContainerRegistry.registerFluidContainer(new FluidContainerData(Materials.Milk.mFluid.copy(), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Milk, 1L), GT_Items.Cell_Empty.get(1L, new Object[0])));
      }

      if(null != (tLiquid = FluidRegistry.getFluidStack("oil", 1000))) {
         Materials.Oil.mFluid = tLiquid.copy();
         FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Oil, 1L), GT_Items.Cell_Empty.get(1L, new Object[0])));
      }

      if(null != (tLiquid = FluidRegistry.getFluidStack("fuel", 1000))) {
         Materials.Fuel.mFluid = tLiquid.copy();
         FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Fuel, 1L), GT_Items.Cell_Empty.get(1L, new Object[0])));
      }

      if(null != (tLiquid = FluidRegistry.getFluidStack("bioethanol", 1000))) {
         Materials.Ethanol.mFluid = tLiquid.copy();
         FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Ethanol, 1L), GT_Items.Cell_Empty.get(1L, new Object[0])));
      }

      if(null != (tLiquid = FluidRegistry.getFluidStack("biomass", 1000))) {
         Materials.Biomass.mFluid = tLiquid.copy();
         FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Biomass, 1L), GT_Items.Cell_Empty.get(1L, new Object[0])));
      }

      if(null != (tLiquid = FluidRegistry.getFluidStack("ice", 1000))) {
         Materials.Water.mSolid = Materials.Ice.mSolid = tLiquid.copy();
         FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Ice, 1L), GT_Items.Cell_Empty.get(1L, new Object[0])));
      }

      if(null != (tLiquid = FluidRegistry.getFluidStack("seedoil", 1000))) {
         Materials.SeedOil.mFluid = tLiquid.copy();
         FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SeedOil, 1L), GT_Items.Cell_Empty.get(1L, new Object[0])));
      }

      if(null != (tLiquid = FluidRegistry.getFluidStack("creosote", 1000))) {
         Materials.Creosote.mFluid = tLiquid.copy();
         FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Creosote, 1L), GT_Items.Cell_Empty.get(1L, new Object[0])));
      }

      GT_FluidRegistry.addFluid("HeliumPlasma", "Helium Plasma", Materials.Helium, 3, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(131, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Hydrogen", (String)null, Materials.Hydrogen, 2, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(0, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Deuterium", (String)null, Materials.Deuterium, 2, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(1, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Tritium", (String)null, Materials.Tritium, 2, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(2, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Helium", (String)null, Materials.Helium, 2, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(3, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Helium-3", (String)null, Materials.Helium_3, 2, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(6, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Methane", (String)null, Materials.Methane, 2, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(9, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Nitrogen", (String)null, Materials.Nitrogen, 2, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(15, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("NitrogenDioxide", (String)null, Materials.NitrogenDioxide, 2, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(38, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Steam", (String)null, Materials.Water, 2, (ItemStack)null, (ItemStack)null);
      Materials.Ice.mGas = Materials.Water.mGas;
      GT_FluidRegistry.addFluid("Wolframium", (String)null, Materials.Tungsten, 0, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(4, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Lithium", (String)null, Materials.Lithium, 1, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(5, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Silicon", (String)null, Materials.Silicon, 0, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(7, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Berylium", (String)null, Materials.Beryllium, 0, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(10, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Calcium", (String)null, Materials.Calcium, 0, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(11, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Sodium", (String)null, Materials.Sodium, 0, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(12, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Chlorite", (String)null, Materials.Chlorine, 1, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(13, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Potassium", (String)null, Materials.Potassium, 1, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(14, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Mercury", (String)null, Materials.Mercury, 1, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(16, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("NitroFuel", "Nitro Diesel", Materials.NitroFuel, 1, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(22, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("SodiumPersulfate", "Sodium Persulfate", Materials.SodiumPersulfate, 1, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(32, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("CalciumCarbonate", "Calcium Carbonate", Materials.Calcite, 0, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(33, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("Glyceryl", (String)null, Materials.Glyceryl, 1, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(34, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_FluidRegistry.addFluid("NitroCoalFuel", "Nitro Coal Fuel", Materials.NitroCoalFuel, 1, GT_OreDictUnificator.get(GT_MetaItem_Cell.instance.getUnunifiedStack(35, 1)), GT_Items.Cell_Empty.get(1L, new Object[0]));
      GT_Log.out.println("GT_Mod: Initializing various Fuels.");
      new GT_Recipe(new ItemStack(Item.field_77775_ay), new ItemStack(Block.field_72089_ap), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Copper, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Tin, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Electrum, 1L), 30, 2);
      GregTech_API.sRecipeAdder.addFuel(new ItemStack(Item.field_77809_bD, 1), (ItemStack)null, 10, 5);
      GregTech_API.sRecipeAdder.addFuel(new ItemStack(Item.field_77732_bp, 1), (ItemStack)null, 500, 5);
      GregTech_API.sRecipeAdder.addFuel(new ItemStack(Block.field_82518_cd, 1), (ItemStack)null, Materials.NetherStar.mFuelPower * 2, Materials.NetherStar.mFuelType);
      GT_ModHandler.addBoilerFuel(GT_Utility.getFluidForFilledItem(GT_MetaItem_Cell.instance.getUnunifiedStack(35, 1)), 18000);
      GT_ModHandler.addBoilerFuel(GT_Utility.getFluidForFilledItem(GT_MetaItem_Cell.instance.getUnunifiedStack(5, 1)), 24000);
   }
}
