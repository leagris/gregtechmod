package gregtechmod;

import com.google.common.collect.ArrayListMultimap;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Element;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ItemTextures;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGT_Mod;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import gregtechmod.api.metatileentity.MetaPipeEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_CircuitryBehavior;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_ItsNotMyFaultException;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.GT_ConnectionHandler;
import gregtechmod.common.GT_DummyWorld;
import gregtechmod.common.GT_GUIHandler;
import gregtechmod.common.GT_OreDictHandler;
import gregtechmod.common.GT_PacketHandler;
import gregtechmod.common.GT_Proxy;
import gregtechmod.common.GT_RecipeAdder;
import gregtechmod.common.GT_TickHandler;
import gregtechmod.common.GT_Worldgenerator;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.blocks.GT_BlockMetaID_Block2;
import gregtechmod.common.blocks.GT_BlockMetaID_Machine;
import gregtechmod.common.blocks.GT_BlockMetaID_Ore;
import gregtechmod.common.blocks.GT_BlockMetaID_Stone1;
import gregtechmod.common.blocks.GT_Block_LightSource;
import gregtechmod.common.covers.GT_Cover_Generic;
import gregtechmod.common.covers.GT_Cover_None;
import gregtechmod.common.covers.GT_Cover_Redstone;
import gregtechmod.common.items.GT_MetaBlock2_Item;
import gregtechmod.common.items.GT_MetaBlock_Item;
import gregtechmod.common.items.GT_MetaMachine_Item;
import gregtechmod.common.items.GT_MetaOre_Item;
import gregtechmod.common.items.GT_MetaStone1_Item;
import gregtechmod.common.render.GT_Block_Renderer;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_ComputerCube;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_LightSource;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_PlayerDetector;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Sonictron;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Superconductor;
import gregtechmod.loaders.load.GT_CoverBehaviorLoader;
import gregtechmod.loaders.load.GT_ItemIterator;
import gregtechmod.loaders.load.GT_LiquidAndFuelLoader;
import gregtechmod.loaders.load.GT_SonictronLoader;
import gregtechmod.loaders.misc.GT_CoverLoader;
import gregtechmod.loaders.misc.GT_TooEasyModeLoader;
import gregtechmod.loaders.postload.GT_BlockResistanceLoader;
import gregtechmod.loaders.postload.GT_BookAndLootLoader;
import gregtechmod.loaders.postload.GT_CraftingRecipeLoader;
import gregtechmod.loaders.postload.GT_CropLoader;
import gregtechmod.loaders.postload.GT_ItemMaxStacksizeLoader;
import gregtechmod.loaders.postload.GT_MachineRecipeLoader;
import gregtechmod.loaders.postload.GT_MinableRegistrator;
import gregtechmod.loaders.postload.GT_RecyclerBlacklistLoader;
import gregtechmod.loaders.postload.GT_RecyclingRecipeLoader;
import gregtechmod.loaders.postload.GT_ScrapboxDropLoader;
import gregtechmod.loaders.postload.GT_SeedFlowerIterator;
import gregtechmod.loaders.postload.GT_UUMRecipeLoader;
import gregtechmod.loaders.postload.GT_Worldgenloader;
import gregtechmod.loaders.preload.GT_CircuitBehaviorLoader;
import gregtechmod.loaders.preload.GT_DictRegistratorPostItem;
import gregtechmod.loaders.preload.GT_DictRegistratorPreItem;
import gregtechmod.loaders.preload.GT_ItemLoader;
import gregtechmod.loaders.preload.GT_MetaTileEntityLoader;
import gregtechmod.loaders.preload.GT_OreProcessingLoader;
import ic2.api.recipe.RecipeOutput;
import ic2.api.recipe.Recipes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import mods.railcraft.api.core.items.TagList;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerRegisterEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(
   modid = "gregtech_addon",
   name = "GregTech-Addon",
   version = "MC162",
   useMetadata = false,
   dependencies = "required-after:IC2; after:Railcraft; after:ThermalExpansion; after:ThermalExpansion|Transport; after:ThermalExpansion|Energy; after:ThermalExpansion|Factory; before:RedPowerCore; before:RedPowerBase; before:RedPowerMachine; before:RedPowerCompat; before:RedPowerWiring; before:RedPowerLogic; before:RedPowerLighting; before:RedPowerWorld; before:RedPowerControl;"
)
@NetworkMod(
   clientSideRequired = true,
   serverSideRequired = false,
   channels = {"gregtech", "GTSound", "GTTile"},
   packetHandler = GT_PacketHandler.class,
   connectionHandler = GT_ConnectionHandler.class
)
public class GT_Mod implements IGT_Mod {

   @Instance
   public static GT_Mod instance;
   @SidedProxy(
      clientSide = "gregtechmod.common.GT_Client",
      serverSide = "gregtechmod.common.GT_Server",
      bukkitSide = "gregtechmod.common.GT_Bukkit"
   )
   public static GT_Proxy gregtechproxy;
   public static final int VERSION = 408;
   public static final int REQUIRED_IC2 = 397;
   public static boolean sThaumiumObtainable = false;
   public static boolean sNerfDustCrafting = true;
   public static boolean sSortToTheEnd = true;
   public static boolean sCraftingUnification = true;
   public static boolean sInventoryUnification = true;
   public static boolean sIncreaseDungeonLoot = true;
   public static boolean sAxeWhenAdventure = true;
   public static boolean sSurvivalIntoAdventure = false;
   public static boolean sNerfedWoodPlank = true;
   public static boolean sNerfedWoodenTools = true;
   public static boolean sNerfedStoneTools = true;
   public static boolean sInvisibleOres = false;
   public static boolean sHardRock = false;
   public static boolean sHungerEffect = true;
   public static boolean mOnline = true;
   public static boolean mAlreadyPlayed = false;
   public static boolean mDetectIDConflicts = false;
   public static boolean mDoNotInit = false;
   public static int sItemDespawnTime = 6000;
   public static int sUpgradeCount = 4;
   public static int sBlockStackSize = 64;
   public static int sOreStackSize = 64;
   public static int sWoodStackSize = 64;
   public static int sPlankStackSize = 64;
   public static int[] sItemIDs = new int[256];
   public static int[] sBlockIDs = new int[]{4058, 4059, 4060, 4061, 4057, 4062};
   public static World mUniverse = null;
   public static final ArrayList<String> mGregTechCapeList = new ArrayList(Arrays.asList(new String[]{"Plem", "invultri", "Malevolence_", "Archibald_McShane", "Sirbab", "kehaan", "bpgames123", "semig0d", "9000bowser", "Sovereignty89", "Kris1432", "xander_cage_", "samuraijp", "bsaa", "SpwnX", "tworf", "Kadah", "kanni", "Stute", "Hegik", "Onlyme", "t3hero", "Hotchi", "jagoly", "Nullav", "BH5432", "Sibmer", "inceee", "foxxx0", "Hartok", "TMSama", "Shlnen", "Carsso", "zessirb", "meep310", "Seldron", "yttr1um", "hohounk", "freebug", "Sylphio", "jmarler", "Saberawr", "r00teniy", "Neonbeta", "yinscape", "voooon24", "Quintine", "peach774", "lepthymo", "bildeman", "Kremnari", "Aerosalo", "OndraSter", "oscares91", "mr10movie", "Daxx367x2", "EGERTRONx", "aka13_404", "Abouttabs", "Johnstaal", "djshiny99", "megatronp", "DZCreeper", "Kane_Hart", "Truculent", "vidplace7", "simon6689", "MomoNasty", "UnknownXLV", "goreacraft", "Fluttermine", "Daddy_Cecil", "MrMaleficus", "TigersFangs", "cublikefoot", "chainman564", "NikitaBuker", "Misha999777", "25FiveDetail", "AntiCivilBoy", "michaelbrady", "xXxIceFirexXx", "Speedynutty68", "GarretSidzaka", "HallowCharm977", "mastermind1919", "The_Hypersonic", "diamondguy2798", "zF4ll3nPr3d4t0r", "CrafterOfMines57", "XxELIT3xSNIP3RxX", "adamros", "alexbegt"}));
   public static final ArrayList<String> mBrainTechCapeList = new ArrayList(Arrays.asList(new String[]{"Friedi4321"}));
   public static final ArrayList<String> mSoundNames = new ArrayList();
   public static final ArrayList<ItemStack> mSoundItems = new ArrayList();
   public static final ArrayList<Integer> mSoundCounts = new ArrayList();
   public static String sMessage = "";
   private boolean tNothingRegistered = true;


   private static final void checkVersions() {
      if(408 != GregTech_API.VERSION || 408 != BaseMetaTileEntity.VERSION || 408 != BaseMetaPipeEntity.VERSION || 408 != MetaTileEntity.VERSION || 408 != MetaPipeEntity.VERSION || 408 != GT_CircuitryBehavior.VERSION || 408 != GT_CoverBehavior.VERSION || 408 != GT_Config.VERSION || 408 != GT_LanguageManager.VERSION || 408 != GT_ModHandler.VERSION || 408 != GT_OreDictUnificator.VERSION || 408 != GT_Recipe.VERSION || 408 != GT_Utility.VERSION || 408 != GT_RecipeRegistrator.VERSION || 408 != Element.VERSION || 408 != Materials.VERSION || 408 != OrePrefixes.VERSION) {
         throw new GT_ItsNotMyFaultException("One of your Mods included GregTech-API Files inside it\'s download, mention this to the Mod Author, who does this bad thing, and tell him/her to use reflection. I have added a Version check, to prevent Authors from breaking my Mod that way.");
      }
   }

   public GT_Mod() {
      checkVersions();

      try {
         Class.forName("ic2.core.IC2").getField("enableOreDictCircuit").set((Object)null, Boolean.valueOf(true));
      } catch (Throwable var7) {
         var7.printStackTrace();
      }

      try {
         Class.forName("ic2.core.IC2").getField("enableCraftingBucket").set((Object)null, Boolean.valueOf(false));
      } catch (Throwable var6) {
         var6.printStackTrace();
      }

      try {
         Class.forName("ic2.core.IC2").getField("enableEnergyInStorageBlockItems").set((Object)null, Boolean.valueOf(false));
      } catch (Throwable var5) {
         var5.printStackTrace();
      }

      GregTech_API.gregtechmod = this;
      GregTech_API.sRecipeAdder = new GT_RecipeAdder();
      GregTech_API.sDummyWorld = new GT_DummyWorld();
      GregTech_API.sGTCoverload.add(new GT_CoverLoader());
      GT_OreDictHandler.instance.registerHandler();

      for(int arr$ = 0; arr$ < mGregTechCapeList.size(); ++arr$) {
         mGregTechCapeList.set(arr$, ((String)mGregTechCapeList.get(arr$)).toLowerCase());
      }

      FluidContainerData[] var8 = FluidContainerRegistry.getRegisteredFluidContainerData();
      int len$ = var8.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         FluidContainerData tData = var8[i$];
         if(tData.filledContainer.func_77973_b() == Item.field_77726_bs) {
            tData.fluid.amount = 0;
            break;
         }
      }

      MinecraftForge.EVENT_BUS.register(this);
      new GT_Cover_None();
      new GT_Cover_Generic();
      new GT_Cover_Redstone();
      new GT_ItemTextures();
   }

   @EventHandler
   public void preload(FMLPreInitializationEvent aEvent) {
      checkVersions();

      try {
         Integer.parseInt(((String)Class.forName("ic2.core.IC2").getField("VERSION").get((Object)null)).substring(4, 7));
      } catch (Throwable var16) {
         throw new GT_ItsNotMyFaultException("Ancient IndustrialCraft Version detected, please update your IndustrialCraft here: ic2api.player.to:8080/job/IC2_experimental/?");
      }

      if(!GregTech_API.sPreloadStarted) {
         Iterator tFile = GregTech_API.sBeforeGTPreload.iterator();

         while(tFile.hasNext()) {
            Runnable tMainConfig = (Runnable)tFile.next();

            try {
               tMainConfig.run();
            } catch (Throwable var15) {
               var15.printStackTrace(GT_Log.err);
            }
         }

         File var20 = new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "GregTech.cfg");
         Configuration var21 = new Configuration(var20);
         var21.load();
         var20 = new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "BlockItemIDs.cfg");
         GT_Config.sConfigFileIDs = new Configuration(var20);
         GT_Config.sConfigFileIDs.load();
         GregTech_API.sRecipeFile = new GT_Config(new Configuration(new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "DynamicConfig.cfg")));
         GregTech_API.sMachineFile = new GT_Config(new Configuration(new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "MachineStats.cfg")));
         GregTech_API.sWorldgenFile = new GT_Config(new Configuration(new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "WorldGeneration.cfg")));
         GregTech_API.sMaterialProperties = new GT_Config(new Configuration(new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "MaterialProperties.cfg")));
         GregTech_API.sUnification = new GT_Config(new Configuration(new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "Unification.cfg")));
         GregTech_API.sSpecialFile = new GT_Config(new Configuration(new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "Other.cfg")));
         GT_Log.mLogFile = new File(aEvent.getModConfigurationDirectory().getParentFile(), "GregTech.log");
         if(GT_Log.mLogFile.exists()) {
            mAlreadyPlayed = true;

            try {
               GT_Log.out = GT_Log.err = new PrintStream(GT_Log.mLogFile);
            } catch (FileNotFoundException var14) {
               ;
            }
         }

         GT_Log.mOreDictLogFile = new File(aEvent.getModConfigurationDirectory().getParentFile(), "OreDict.log");
         if(!GT_Log.mOreDictLogFile.exists()) {
            try {
               GT_Log.mOreDictLogFile.createNewFile();
            } catch (Throwable var13) {
               ;
            }
         }

         try {
            List i$ = ((GT_Log.LogBuffer)GT_Log.ore).mBufferedOreDictLog;
            GT_Log.ore = new PrintStream(GT_Log.mOreDictLogFile);
            GT_Log.ore.println("**********************************************************************");
            GT_Log.ore.println("* This is the complete Log of the GregTech OreDictionary Handler     *");
            GT_Log.ore.println("* Everything in the OreDict goes through it sometimes causing Errors *");
            GT_Log.ore.println("* These Errors are getting logged aswell as properly registered Ores *");
            GT_Log.ore.println("* If you see something fishy going on in this Log, such as improper  *");
            GT_Log.ore.println("* Items being registered, then mention it to the corresponding Mod   *");
            GT_Log.ore.println("* In case it mentions GregTech itself improperly registering Stuff   *");
            GT_Log.ore.println("* then please contact me about that immediatly                       *");
            GT_Log.ore.println("*                                                                    *");
            GT_Log.ore.println("* In case of something being \'ignored properly\', that one isnt a Bug *");
            GT_Log.ore.println("**********************************************************************");
            Iterator tRunnable = i$.iterator();

            while(tRunnable.hasNext()) {
               String e = (String)tRunnable.next();
               GT_Log.ore.println(e);
            }
         } catch (Throwable var19) {
            ;
         }

         GT_Log.out.println("GT_Mod: Preload-Phase started!");
         GT_Log.ore.println("GT_Mod: Preload-Phase started!");
         GregTech_API.sPreloadStarted = true;
         GT_Log.out.println("GT_Mod: Getting required Items of other Mods.");
         GT_Items.TE_Slag.set(GT_ModHandler.getTEItem("slag", 1L));
         GT_Items.TE_Slag_Rich.set(GT_ModHandler.getTEItem("slagRich", 1L));
         GT_Items.TE_Rockwool.set(GT_ModHandler.getTEItem("rockwool", 1L));
         GT_Items.TE_Hardened_Glass.set(GT_ModHandler.getTEItem("glassHardened", 1L));
         GT_Items.Cell_Empty.set(GT_ModHandler.getIC2Item("cell", 1L, GT_ModHandler.getIC2Item("cellEmpty", 1L, GT_ModHandler.getIC2Item("emptyCell", 1L))));
         GT_Items.Cell_Water.set(GT_ModHandler.getIC2Item("waterCell", 1L, GT_ModHandler.getIC2Item("cellWater", 1L)));
         GT_Items.Cell_Lava.set(GT_ModHandler.getIC2Item("lavaCell", 1L, GT_ModHandler.getIC2Item("cellLava", 1L)));
         GT_Items.Cell_Air.set(GT_ModHandler.getIC2Item("airCell", 1L, GT_ModHandler.getIC2Item("cellAir", 1L, GT_ModHandler.getIC2Item("cellOxygen", 1L))));
         GT_Items.IC2_Fuel_Can_Empty.set(GT_ModHandler.getIC2Item("fuelCan", 1L, GT_ModHandler.getIC2Item("fuelCanEmpty", 1L, GT_ModHandler.getIC2Item("emptyFuelCan", 1L))));
         GT_Items.IC2_Fuel_Can_Filled.set(GT_ModHandler.getIC2Item("filledFuelCan", 1L));
         GT_Items.IC2_Mixed_Metal_Ingot.set(GT_ModHandler.getIC2Item("mixedMetalIngot", 1L));
         GT_Items.IC2_Fertilizer.set(GT_ModHandler.getIC2Item("fertilizer", 1L));
         GT_Items.IC2_Resin.set(GT_ModHandler.getIC2Item("resin", 1L));
         GT_Items.IC2_Crop_Seeds.set(GT_ModHandler.getIC2Item("cropSeed", 1L));
         GT_Items.IC2_Grin_Powder.set(GT_ModHandler.getIC2Item("grinPowder", 1L));
         GT_Items.IC2_Energium_Dust.set(GT_ModHandler.getIC2Item("energiumDust", 1L));
         GT_Items.IC2_Scrap.set(GT_ModHandler.getIC2Item("scrap", 1L));
         GT_Items.IC2_Scrapbox.set(GT_ModHandler.getIC2Item("scrapBox", 1L));
         GT_Items.IC2_Fuel_Rod_Empty.set(GT_ModHandler.getIC2Item("fuelRod", 1L));
         GT_Items.IC2_Food_Can_Empty.set(GT_ModHandler.getIC2Item("tinCan", 1L));
         GT_Items.IC2_Food_Can_Filled.set(GT_ModHandler.getIC2Item("filledTinCan", 1L, 0));
         GT_Items.IC2_Food_Can_Spoiled.set(GT_ModHandler.getIC2Item("filledTinCan", 1L, 1));
         GT_Items.IC2_Industrial_Diamond.set(GT_ModHandler.getIC2Item("industrialDiamond", 1L, new ItemStack(Item.field_77702_n, 1)));
         GT_Items.IC2_Compressed_Coal_Ball.set(GT_ModHandler.getIC2Item("compressedCoalBall", 1L));
         GT_Items.IC2_Compressed_Coal_Chunk.set(GT_ModHandler.getIC2Item("coalChunk", 1L));
         GT_Items.Tool_Sword_Bronze.set(GT_ModHandler.getIC2Item("bronzeSword", 1L));
         GT_Items.Tool_Pickaxe_Bronze.set(GT_ModHandler.getIC2Item("bronzePickaxe", 1L));
         GT_Items.Tool_Shovel_Bronze.set(GT_ModHandler.getIC2Item("bronzeShovel", 1L));
         GT_Items.Tool_Axe_Bronze.set(GT_ModHandler.getIC2Item("bronzeAxe", 1L));
         GT_Items.Tool_Hoe_Bronze.set(GT_ModHandler.getIC2Item("bronzeHoe", 1L));
         GT_Items.Tool_Hammer_Forge.set(GT_ModHandler.getIC2Item("ForgeHammer", 1L));
         GT_Items.Credit_Iron.set(GT_ModHandler.getIC2Item("coin", 1L));
         GT_Items.Circuit_Basic.set(GT_ModHandler.getIC2Item("electronicCircuit", 1L));
         GT_Items.Circuit_Advanced.set(GT_ModHandler.getIC2Item("advancedCircuit", 1L));
         GT_Items.Upgrade_Overclocker.set(GT_ModHandler.getIC2Item("overclockerUpgrade", 1L));
         GT_Items.Upgrade_Transformer.set(GT_ModHandler.getIC2Item("transformerUpgrade", 1L));
         GT_Items.Upgrade_Battery.set(GT_ModHandler.getIC2Item("energyStorageUpgrade", 1L));
         GT_Log.out.println("GT_Mod: Setting Configs");
         if(var21.get("general", "TooEasyMode", false).getBoolean(false)) {
            GregTech_API.sAfterGTPostload.add(new GT_TooEasyModeLoader());
         }

         GregTech_API.DEBUG_MODE = var21.get("general", "Debug", false).getBoolean(false);
         GregTech_API.SECONDARY_DEBUG_MODE = var21.get("general", "Debug2", false).getBoolean(false);
         GregTech_API.TICKS_FOR_LAG_AVERAGING = var21.get("general", "TicksForLagAveragingWithScanner", 25).getInt(25);
         GregTech_API.MILLISECOND_THRESHOLD_UNTIL_LAG_WARNING = var21.get("general", "MillisecondsPassedInGTTileEntityUntilLagWarning", 100).getInt(100);
         if(var21.get("general", "disable_STDOUT", false).getBoolean(false)) {
            System.out.close();
         }

         if(var21.get("general", "disable_STDERR", false).getBoolean(false)) {
            System.err.close();
         }

         GregTech_API.UE_ENERGY_COMPATIBILITY = var21.get("compatibility", "UniversalElectricity.Energy", true).getBoolean(true);
         GregTech_API.IC_ENERGY_COMPATIBILITY = var21.get("compatibility", "Industrialcraft.Energy", true).getBoolean(true);
         GregTech_API.BC_ENERGY_COMPATIBILITY = var21.get("compatibility", "Buildcraft.Energy", true).getBoolean(true);
         GregTech_API.sMachineExplosions = var21.get("machines", "machines_explosion_damage", true).getBoolean(false);
         GregTech_API.sMachineFlammable = var21.get("machines", "machines_flammable", true).getBoolean(false);
         GregTech_API.sMachineNonWrenchExplosions = var21.get("machines", "explosions_on_nonwrenching", true).getBoolean(false);
         GregTech_API.sMachineWireFire = var21.get("machines", "wirefire_on_explosion", true).getBoolean(false);
         GregTech_API.sMachineFireExplosions = var21.get("machines", "fire_causes_explosions", true).getBoolean(false);
         GregTech_API.sMachineRainExplosions = var21.get("machines", "rain_causes_explosions", true).getBoolean(false);
         GregTech_API.sMachineThunderExplosions = var21.get("machines", "lightning_causes_explosions", true).getBoolean(false);
         GregTech_API.sConstantEnergy = var21.get("machines", "constant_need_of_energy", true).getBoolean(false);
         GregTech_API.sColoredGUI = var21.get("machines", "colored_guis_when_painted", true).getBoolean(false);
         GregTech_API.sDoShowAllItemsInCreative = var21.get("general", "show_all_metaitems_in_creative_and_NEI", false).getBoolean(false);
         sItemDespawnTime = var21.get("general", "ItemDespawnTime", 6000).getInt(6000);
         sNerfDustCrafting = var21.get("general", "NerfDustCrafting", true).getBoolean(true);
         sIncreaseDungeonLoot = var21.get("general", "IncreaseDungeonLoot", true).getBoolean(true);
         sAxeWhenAdventure = var21.get("general", "AdventureModeStartingAxe", true).getBoolean(true);
         sSurvivalIntoAdventure = var21.get("general", "forceAdventureMode", false).getBoolean(false);
         sHungerEffect = var21.get("general", "AFK_Hunger", false).getBoolean(false);
         sHardRock = var21.get("general", "harderstone", false).getBoolean(false);
         sInvisibleOres = var21.get("general", "hiddenores", true).getBoolean(true);
         sInventoryUnification = var21.get("general", "InventoryUnification", true).getBoolean(true);
         sCraftingUnification = var21.get("general", "CraftingUnification", true).getBoolean(true);
         sNerfedWoodPlank = var21.get("general", "WoodNeedsSawForCrafting", true).getBoolean(true);
         sNerfedWoodenTools = var21.get("general", "smallerWoodToolDurability", true).getBoolean(true);
         sNerfedStoneTools = var21.get("general", "smallerStoneToolDurability", true).getBoolean(true);
         sSortToTheEnd = var21.get("general", "EnsureToBeLoadedLast", true).getBoolean(true);
         if(var21.get("general", "hardermobspawners", true).getBoolean(true)) {
            Block.field_72065_as.field_71989_cb = 2000.0F;
         }

         sBlockIDs[0] = GT_Config.sConfigFileIDs.getBlock("Block", sBlockIDs[0]).getInt();
         sBlockIDs[1] = GT_Config.sConfigFileIDs.getBlock("Machine", sBlockIDs[1]).getInt();
         sBlockIDs[2] = GT_Config.sConfigFileIDs.getBlock("Ore", sBlockIDs[2]).getInt();
         sBlockIDs[3] = GT_Config.sConfigFileIDs.getBlock("LightSource", sBlockIDs[3]).getInt();
         sBlockIDs[4] = GT_Config.sConfigFileIDs.getBlock("Block2", sBlockIDs[4]).getInt();
         sBlockIDs[5] = GT_Config.sConfigFileIDs.getBlock("Stone1", sBlockIDs[5]).getInt();
         sItemIDs[0] = GT_Config.sConfigFileIDs.getItem("MATERIALS", 21000).getInt();
         sItemIDs[1] = GT_Config.sConfigFileIDs.getItem("DUSTS", 21001).getInt();
         sItemIDs[2] = GT_Config.sConfigFileIDs.getItem("CELLS", 21002).getInt();
         sItemIDs[3] = GT_Config.sConfigFileIDs.getItem("COMPONENTS", 21003).getInt();
         sItemIDs[4] = GT_Config.sConfigFileIDs.getItem("SMALLDUSTS", 21004).getInt();
         sItemIDs[5] = GT_Config.sConfigFileIDs.getItem("NUGGETS", 21005).getInt();
         sItemIDs[6] = GT_Config.sConfigFileIDs.getItem("DIRTYDUSTS", 21006).getInt();
         sItemIDs[7] = GT_Config.sConfigFileIDs.getItem("TINYDUSTS", 21007).getInt();
         sItemIDs[8] = GT_Config.sConfigFileIDs.getItem("METAITEM_01", 21008).getInt();
         sItemIDs[9] = GT_Config.sConfigFileIDs.getItem("METAITEM_02", 21009).getInt();
         sItemIDs[10] = GT_Config.sConfigFileIDs.getItem("INTEGRATED_CIRCUIT", 21010).getInt();
         sItemIDs[15] = GT_Config.sConfigFileIDs.getItem("LIQUIDDISPLAY", 21015).getInt();
         sItemIDs[16] = GT_Config.sConfigFileIDs.getItem("NCSensorCard", 21016).getInt();
         sItemIDs[17] = GT_Config.sConfigFileIDs.getItem("NCSensorKit", 21017).getInt();
         sItemIDs[18] = GT_Config.sConfigFileIDs.getItem("CheatyDevice", 21018).getInt();
         sItemIDs[30] = GT_Config.sConfigFileIDs.getItem("IronMortar", 21030).getInt();
         sItemIDs[31] = GT_Config.sConfigFileIDs.getItem("Mortar", 21031).getInt();
         sItemIDs[32] = GT_Config.sConfigFileIDs.getItem("HandheldSonictron", 21032).getInt();
         sItemIDs[33] = GT_Config.sConfigFileIDs.getItem("Destructopack", 21033).getInt();
         sItemIDs[34] = GT_Config.sConfigFileIDs.getItem("Heliumcoolant060k", 21034).getInt();
         sItemIDs[35] = GT_Config.sConfigFileIDs.getItem("Heliumcoolant120k", 21035).getInt();
         sItemIDs[36] = GT_Config.sConfigFileIDs.getItem("Heliumcoolant180k", 21036).getInt();
         sItemIDs[37] = GT_Config.sConfigFileIDs.getItem("LapotronicEnergycrystal", 21037).getInt();
         sItemIDs[38] = GT_Config.sConfigFileIDs.getItem("CloakingDevice", 21038).getInt();
         sItemIDs[39] = GT_Config.sConfigFileIDs.getItem("JackHammerIron", 21039).getInt();
         sItemIDs[40] = GT_Config.sConfigFileIDs.getItem("Neutronreflector", 21040).getInt();
         sItemIDs[41] = GT_Config.sConfigFileIDs.getItem("JackHammerSteel", 21041).getInt();
         sItemIDs[42] = GT_Config.sConfigFileIDs.getItem("JackHammerDiamond", 21042).getInt();
         sItemIDs[43] = GT_Config.sConfigFileIDs.getItem("Dataorb", 21043).getInt();
         sItemIDs[44] = GT_Config.sConfigFileIDs.getItem("Lamphelmet", 21044).getInt();
         sItemIDs[45] = GT_Config.sConfigFileIDs.getItem("Lapotronpack", 21045).getInt();
         sItemIDs[46] = GT_Config.sConfigFileIDs.getItem("Rockcutter", 21046).getInt();
         sItemIDs[47] = GT_Config.sConfigFileIDs.getItem("Teslastaff", 21047).getInt();
         sItemIDs[48] = GT_Config.sConfigFileIDs.getItem("Aluminium Scoop", 21048).getInt();
         sItemIDs[55] = GT_Config.sConfigFileIDs.getItem("DebugScanner", 21055).getInt();
         sItemIDs[56] = GT_Config.sConfigFileIDs.getItem("LithiumbatteryEmpty", 21056).getInt();
         sItemIDs[57] = GT_Config.sConfigFileIDs.getItem("LithiumbatteryFull", 21057).getInt();
         sItemIDs[58] = GT_Config.sConfigFileIDs.getItem("Lithiumpack", 21058).getInt();
         sItemIDs[60] = GT_Config.sConfigFileIDs.getItem("NaKcoolant060k", 21060).getInt();
         sItemIDs[61] = GT_Config.sConfigFileIDs.getItem("NaKcoolant120k", 21061).getInt();
         sItemIDs[62] = GT_Config.sConfigFileIDs.getItem("NaKcoolant180k", 21062).getInt();
         sItemIDs[63] = GT_Config.sConfigFileIDs.getItem("Scanner", 21063).getInt();
         sItemIDs[64] = GT_Config.sConfigFileIDs.getItem("Crowbar", 21064).getInt();
         sItemIDs[65] = GT_Config.sConfigFileIDs.getItem("Screwdriver", 21065).getInt();
         sItemIDs[66] = GT_Config.sConfigFileIDs.getItem("WrenchSteel", 21066).getInt();
         sItemIDs[67] = GT_Config.sConfigFileIDs.getItem("WrenchRefIron", 21067).getInt();
         sItemIDs[68] = GT_Config.sConfigFileIDs.getItem("WrenchTungstensteel", 21068).getInt();
         sItemIDs[69] = GT_Config.sConfigFileIDs.getItem("WrenchBronze", 21069).getInt();
         sItemIDs[70] = GT_Config.sConfigFileIDs.getItem("WrenchElectric", 21070).getInt();
         sItemIDs[71] = GT_Config.sConfigFileIDs.getItem("WrenchAdvanced", 21071).getInt();
         sItemIDs[72] = GT_Config.sConfigFileIDs.getItem("HammerRubber", 21072).getInt();
         sItemIDs[73] = GT_Config.sConfigFileIDs.getItem("HammerIron", 21073).getInt();
         sItemIDs[74] = GT_Config.sConfigFileIDs.getItem("HammerBronze", 21074).getInt();
         sItemIDs[75] = GT_Config.sConfigFileIDs.getItem("HammerSteel", 21075).getInt();
         sItemIDs[76] = GT_Config.sConfigFileIDs.getItem("HammerTungstenSteel", 21076).getInt();
         sItemIDs[77] = GT_Config.sConfigFileIDs.getItem("SolderingTool", 21077).getInt();
         sItemIDs[78] = GT_Config.sConfigFileIDs.getItem("SolderingTin", 21078).getInt();
         sItemIDs[79] = GT_Config.sConfigFileIDs.getItem("SolderingLead", 21079).getInt();
         sItemIDs[80] = GT_Config.sConfigFileIDs.getItem("TurbineBronze", 21080).getInt();
         sItemIDs[81] = GT_Config.sConfigFileIDs.getItem("TurbineSteel", 21081).getInt();
         sItemIDs[82] = GT_Config.sConfigFileIDs.getItem("TurbineMagnalium", 21082).getInt();
         sItemIDs[83] = GT_Config.sConfigFileIDs.getItem("TurbineTungstensteel", 21083).getInt();
         sItemIDs[84] = GT_Config.sConfigFileIDs.getItem("TurbineCarbon", 21084).getInt();
         sItemIDs[85] = GT_Config.sConfigFileIDs.getItem("LavaFilter", 21085).getInt();
         sItemIDs[86] = GT_Config.sConfigFileIDs.getItem("FileIron", 21086).getInt();
         sItemIDs[87] = GT_Config.sConfigFileIDs.getItem("FileBronze", 21087).getInt();
         sItemIDs[88] = GT_Config.sConfigFileIDs.getItem("FileSteel", 21088).getInt();
         sItemIDs[89] = GT_Config.sConfigFileIDs.getItem("FileTungstenSteel", 21089).getInt();
         sItemIDs[90] = GT_Config.sConfigFileIDs.getItem("Spray_Bug", 21090).getInt();
         sItemIDs[91] = GT_Config.sConfigFileIDs.getItem("Spray_Ice", 21091).getInt();
         sItemIDs[92] = GT_Config.sConfigFileIDs.getItem("Spray_Hardener", 21092).getInt();
         sItemIDs[93] = GT_Config.sConfigFileIDs.getItem("Spray_CFoam", 21093).getInt();
         sItemIDs[94] = GT_Config.sConfigFileIDs.getItem("Spray_Pepper", 21094).getInt();
         sItemIDs[95] = GT_Config.sConfigFileIDs.getItem("Spray_Hydration", 21095).getInt();
         sItemIDs[96] = GT_Config.sConfigFileIDs.getItem("Spray_00", 21096).getInt();
         sItemIDs[97] = GT_Config.sConfigFileIDs.getItem("Spray_01", 21097).getInt();
         sItemIDs[98] = GT_Config.sConfigFileIDs.getItem("Spray_02", 21098).getInt();
         sItemIDs[99] = GT_Config.sConfigFileIDs.getItem("Spray_03", 21099).getInt();
         sItemIDs[100] = GT_Config.sConfigFileIDs.getItem("Spray_04", 21100).getInt();
         sItemIDs[101] = GT_Config.sConfigFileIDs.getItem("Spray_05", 21101).getInt();
         sItemIDs[102] = GT_Config.sConfigFileIDs.getItem("Spray_06", 21102).getInt();
         sItemIDs[103] = GT_Config.sConfigFileIDs.getItem("Spray_07", 21103).getInt();
         sItemIDs[104] = GT_Config.sConfigFileIDs.getItem("Spray_08", 21104).getInt();
         sItemIDs[105] = GT_Config.sConfigFileIDs.getItem("Spray_09", 21105).getInt();
         sItemIDs[106] = GT_Config.sConfigFileIDs.getItem("Spray_10", 21106).getInt();
         sItemIDs[107] = GT_Config.sConfigFileIDs.getItem("Spray_11", 21107).getInt();
         sItemIDs[108] = GT_Config.sConfigFileIDs.getItem("Spray_12", 21108).getInt();
         sItemIDs[109] = GT_Config.sConfigFileIDs.getItem("Spray_13", 21109).getInt();
         sItemIDs[110] = GT_Config.sConfigFileIDs.getItem("Spray_14", 21110).getInt();
         sItemIDs[111] = GT_Config.sConfigFileIDs.getItem("Spray_15", 21111).getInt();
         sItemIDs[112] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_01", 21112).getInt();
         sItemIDs[113] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_02", 21113).getInt();
         sItemIDs[114] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_03", 21114).getInt();
         sItemIDs[115] = GT_Config.sConfigFileIDs.getItem("Saw_Iron", 21115).getInt();
         sItemIDs[116] = GT_Config.sConfigFileIDs.getItem("Saw_Bronze", 21116).getInt();
         sItemIDs[117] = GT_Config.sConfigFileIDs.getItem("Saw_Steel", 21117).getInt();
         sItemIDs[118] = GT_Config.sConfigFileIDs.getItem("Saw_Tungstensteel", 21118).getInt();
         sItemIDs[119] = GT_Config.sConfigFileIDs.getItem("Saw_Electric", 21119).getInt();
         sItemIDs[120] = GT_Config.sConfigFileIDs.getItem("Saw_Advanced", 21120).getInt();
         sItemIDs[121] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_04", 21121).getInt();
         sItemIDs[122] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_05", 21122).getInt();
         sItemIDs[123] = GT_Config.sConfigFileIDs.getItem("Drill_Advanced", 21123).getInt();
         sItemIDs[124] = GT_Config.sConfigFileIDs.getItem("Flint_Sword", 21124).getInt();
         sItemIDs[125] = GT_Config.sConfigFileIDs.getItem("Flint_Pickaxe", 21125).getInt();
         sItemIDs[126] = GT_Config.sConfigFileIDs.getItem("Flint_Shovel", 21126).getInt();
         sItemIDs[127] = GT_Config.sConfigFileIDs.getItem("Flint_Axe", 21127).getInt();
         sItemIDs[128] = GT_Config.sConfigFileIDs.getItem("Flint_Hoe", 21128).getInt();
         sItemIDs[129] = GT_Config.sConfigFileIDs.getItem("Steel_Sword", 21129).getInt();
         sItemIDs[130] = GT_Config.sConfigFileIDs.getItem("Steel_Pickaxe", 21130).getInt();
         sItemIDs[131] = GT_Config.sConfigFileIDs.getItem("Steel_Shovel", 21131).getInt();
         sItemIDs[132] = GT_Config.sConfigFileIDs.getItem("Steel_Axe", 21132).getInt();
         sItemIDs[133] = GT_Config.sConfigFileIDs.getItem("Steel_Hoe", 21133).getInt();
         sItemIDs[134] = GT_Config.sConfigFileIDs.getItem("TungstenSteel_Sword", 21134).getInt();
         sItemIDs[135] = GT_Config.sConfigFileIDs.getItem("TungstenSteel_Pickaxe", 21135).getInt();
         sItemIDs[136] = GT_Config.sConfigFileIDs.getItem("TungstenSteel_Shovel", 21136).getInt();
         sItemIDs[137] = GT_Config.sConfigFileIDs.getItem("TungstenSteel_Axe", 21137).getInt();
         sItemIDs[138] = GT_Config.sConfigFileIDs.getItem("TungstenSteel_Hoe", 21138).getInt();
         sItemIDs[139] = GT_Config.sConfigFileIDs.getItem("Screwdriver_Tungstensteel", 21139).getInt();
         sItemIDs[140] = GT_Config.sConfigFileIDs.getItem("Screwdriver_Electric", 21140).getInt();
         sItemIDs[141] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_06", 21141).getInt();
         sItemIDs[142] = GT_Config.sConfigFileIDs.getItem("HammerPlastic", 21142).getInt();
         mOnline = var21.get("general", "online", true).getBoolean(false);
         GT_BlockMetaID_Block.mConnectedMachineTextures = var21.get("general", "ConnectedMachineCasingTextures", true).getBoolean(false);
         sUpgradeCount = Math.min(64, Math.max(1, var21.get("features", "UpgradeStacksize", 4).getInt()));
         sOreStackSize = Math.min(64, Math.max(16, var21.get("features", "MaxOreStackSize", 64).getInt()));
         sWoodStackSize = Math.min(64, Math.max(16, var21.get("features", "MaxLogStackSize", 64).getInt()));
         sPlankStackSize = Math.min(64, Math.max(16, var21.get("features", "MaxPlankStackSize", 64).getInt()));
         sBlockStackSize = Math.min(64, Math.max(16, var21.get("features", "MaxOtherBlockStackSize", 64).getInt()));
         GT_Worldgenerator.sAsteroids = GregTech_API.sWorldgenFile.get("worldgen.end", "EnderAsteroids", true);
         GT_Worldgenerator.sGeneratedOres[9] = GregTech_API.sWorldgenFile.get("worldgen.end", "Tungstateore", true);
         GT_Worldgenerator.sGeneratedOres[10] = GregTech_API.sWorldgenFile.get("worldgen.end", "Cooperiteore", true);
         GT_Worldgenerator.sGeneratedOres[11] = GregTech_API.sWorldgenFile.get("worldgen.end", "Olivineore", true);
         GT_Worldgenerator.sGeneratedOres[12] = GregTech_API.sWorldgenFile.get("worldgen.end", "Sodaliteore", true);
         GT_Config.system = Calendar.getInstance().get(2) + 1 == 4 && Calendar.getInstance().get(5) >= 1 && Calendar.getInstance().get(5) <= 2;
         Materials.init(GregTech_API.sMaterialProperties);
         GT_Log.out.println("GT_Mod: Saving Configs");
         var21.save();
         GT_Config.sConfigFileIDs.save();
         GT_Log.out.println("GT_Mod: Generating Lang-File");
         GT_LanguageManager.sEnglishFile = new Configuration(new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "GregTech.lang"));
         GT_LanguageManager.sEnglishFile.load();
         GT_Log.out.println("GT_Mod: Removing all original Scrapbox Drops.");

         try {
            GT_Utility.getField("ic2.core.item.ItemScrapbox$Drop", "topChance", true, true).set((Object)null, Integer.valueOf(0));
            ((List)GT_Utility.getFieldContent(GT_Utility.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", true, true), "drops", true, true)).clear();
         } catch (Throwable var18) {
            if(GregTech_API.DEBUG_MODE) {
               var18.printStackTrace(GT_Log.err);
            }
         }

         GT_Log.out.println("GT_Mod: Adding Scrap with a Weight of 200.0F to the Scrapbox Drops.");
         GT_ModHandler.addScrapboxDrop(200.0F, GT_ModHandler.getIC2Item("scrap", 1L));
         if(this.isClientSide()) {
            GT_Log.out.println("GT_Mod: Register BlockRenderer");
            new GT_Block_Renderer();
            GT_Log.out.println("GT_Mod: Downloading Cape List.");

            try {
               (new Thread(new Runnable() {
                  public void run() {
                     try {
                        Scanner e = new Scanner((new URL("https://dl.dropbox.com/u/88825306/CapeList.txt")).openStream());

                        while(e.hasNextLine()) {
                           String tName = e.nextLine();
                           if(!GT_Mod.mGregTechCapeList.contains(tName.toLowerCase())) {
                              GT_Mod.mGregTechCapeList.add(tName.toLowerCase());
                           }
                        }
                     } catch (Throwable var3) {
                        ;
                     }

                  }
               })).start();
            } catch (Throwable var12) {
               ;
            }

            GT_Log.out.println("GT_Mod: Downloading News.");

            try {
               (new Thread(new Runnable() {
                  public void run() {
                     try {
                        for(Scanner e = new Scanner((new URL("https://dl.dropboxusercontent.com/u/88825306/Message.txt")).openStream()); e.hasNextLine(); GT_Mod.sMessage = GT_Mod.sMessage + e.nextLine() + " ") {
                           ;
                        }
                     } catch (Throwable var2) {
                        ;
                     }

                  }
               })).start();
            } catch (Throwable var11) {
               ;
            }
         }

         GT_Log.out.println("GT_Mod: Adding Blocks.");
         GameRegistry.registerBlock(GregTech_API.sBlockList[0] = new GT_BlockMetaID_Block(sBlockIDs[0]), GT_MetaBlock_Item.class, GregTech_API.sBlockList[0].func_71917_a(), "gregtech_addon");
         GameRegistry.registerBlock(GregTech_API.sBlockList[1] = new GT_BlockMetaID_Machine(sBlockIDs[1]), GT_MetaMachine_Item.class, GregTech_API.sBlockList[1].func_71917_a(), "gregtech_addon");
         GameRegistry.registerBlock(GregTech_API.sBlockList[2] = new GT_BlockMetaID_Ore(sBlockIDs[2]), GT_MetaOre_Item.class, GregTech_API.sBlockList[2].func_71917_a(), "gregtech_addon");
         GameRegistry.registerBlock(GregTech_API.sBlockList[3] = new GT_Block_LightSource(sBlockIDs[3]), ItemBlock.class, GregTech_API.sBlockList[3].func_71917_a(), "gregtech_addon");
         GameRegistry.registerBlock(GregTech_API.sBlockList[4] = new GT_BlockMetaID_Block2(sBlockIDs[4]), GT_MetaBlock2_Item.class, GregTech_API.sBlockList[4].func_71917_a(), "gregtech_addon");
         GameRegistry.registerBlock(GregTech_API.sBlockList[5] = new GT_BlockMetaID_Stone1(sBlockIDs[5]), GT_MetaStone1_Item.class, GregTech_API.sBlockList[5].func_71917_a(), "gregtech_addon");
         GregTech_API.registerMachineBlock(GregTech_API.sBlockList[0], new boolean[]{true, true, false, false, false, false, true, false, false, false, true, false, false, true, true, true});
         GregTech_API.registerMachineBlock(GregTech_API.sBlockList[1], new boolean[]{true});
         GregTech_API.registerMachineBlock(GregTech_API.sBlockList[4], new boolean[]{false, false, false, false, false, false, false, false, true, true, false, false, false, true});
         GT_Log.out.println("GT_Mod: Register the few old TileEntities.");
         GameRegistry.registerTileEntity(GT_TileEntity_ComputerCube.class, "GregTech_Computercube");
         GameRegistry.registerTileEntity(GT_TileEntity_Sonictron.class, "Sonictron");
         GameRegistry.registerTileEntity(GT_TileEntity_Superconductor.class, "Superconductorwire");
         GameRegistry.registerTileEntity(GT_TileEntity_PlayerDetector.class, "Playerdetector");
         GameRegistry.registerTileEntity(GT_TileEntity_LightSource.class, "GT_LightSource");
         GT_Log.out.println("GT_Mod: Registering the BaseMetaTileEntity.");
         GameRegistry.registerTileEntity(GregTech_API.constructBaseMetaTileEntity().getClass(), "MetatileEntity");
         GT_Log.out.println("GT_Mod: Registering the BaseMetaPipeEntity.");
         GameRegistry.registerTileEntity(BaseMetaPipeEntity.class, "MetaPipeEntity");
         GT_Log.out.println("GT_Mod: Testing BaseMetaTileEntity.");
         if(GregTech_API.constructBaseMetaTileEntity() == null) {
            GT_Log.out.println("GT_Mod: Fatal Error ocurred while initializing TileEntities, crashing Minecraft.");
            throw new RuntimeException("");
         } else {
            (new GT_OreProcessingLoader()).run();
            (new GT_MetaTileEntityLoader()).run();
            (new GT_DictRegistratorPreItem()).run();
            (new GT_ItemLoader()).run();
            (new GT_DictRegistratorPostItem()).run();
            (new GT_CircuitBehaviorLoader()).run();
            (new GT_CoverBehaviorLoader()).run();
            (new GT_SonictronLoader()).run();
            gregtechproxy.registerRenderers();
            FluidContainerData[] var22 = FluidContainerRegistry.getRegisteredFluidContainerData();
            int var25 = var22.length;

            for(int var28 = 0; var28 < var25; ++var28) {
               FluidContainerData tGregTech = var22[var28];
               if(tGregTech.filledContainer.func_77973_b() == Item.field_77726_bs) {
                  tGregTech.fluid.amount = 0;
                  break;
               }
            }

            if(sSortToTheEnd) {
               try {
                  GT_Log.out.println("GT_Mod: Sorting GregTech to the end of the Mod List for further processing.");
                  LoadController var23 = (LoadController)GT_Utility.getFieldContent(Loader.instance(), "modController", true, true);
                  List var26 = var23.getActiveModList();
                  ArrayList var29 = new ArrayList();
                  ModContainer var30 = null;

                  for(short i = 0; i < var26.size(); ++i) {
                     ModContainer tMod = (ModContainer)var26.get(i);
                     if(tMod.getModId().equalsIgnoreCase("gregtech_addon")) {
                        var30 = tMod;
                     } else {
                        var29.add(tMod);
                     }
                  }

                  if(var30 != null) {
                     var29.add(var30);
                  }

                  GT_Utility.getField(var23, "activeModList", true, true).set(var23, var29);
               } catch (Throwable var17) {
                  if(GregTech_API.DEBUG_MODE) {
                     var17.printStackTrace(GT_Log.err);
                  }
               }
            }

            GregTech_API.sPreloadFinished = true;
            GT_Log.out.println("GT_Mod: Preload-Phase finished!");
            GT_Log.ore.println("GT_Mod: Preload-Phase finished!");
            Iterator var24 = GregTech_API.sAfterGTPreload.iterator();

            while(var24.hasNext()) {
               Runnable var27 = (Runnable)var24.next();

               try {
                  var27.run();
               } catch (Throwable var10) {
                  var10.printStackTrace(GT_Log.err);
               }
            }

         }
      }
   }

   @EventHandler
   public void load(FMLInitializationEvent aEvent) {
      if(!mDoNotInit) {
         if(!GregTech_API.sLoadStarted) {
            Iterator i$ = GregTech_API.sBeforeGTLoad.iterator();

            Runnable tRunnable;
            while(i$.hasNext()) {
               tRunnable = (Runnable)i$.next();

               try {
                  tRunnable.run();
               } catch (Throwable var7) {
                  var7.printStackTrace(GT_Log.err);
               }
            }

            checkVersions();
            GT_Log.out.println("GT_Mod: Beginning Load-Phase.");
            GT_Log.ore.println("GT_Mod: Beginning Load-Phase.");
            GregTech_API.sLoadStarted = true;
            if(sSortToTheEnd) {
               (new GT_ItemIterator()).run();
               GT_OreDictHandler.instance.registerUnificationEntries();
               (new GT_LiquidAndFuelLoader()).run();
            }

            FluidContainerData[] var8 = FluidContainerRegistry.getRegisteredFluidContainerData();
            int var9 = var8.length;

            for(int e = 0; e < var9; ++e) {
               FluidContainerData tData = var8[e];
               if(tData.filledContainer.func_77973_b() == Item.field_77726_bs) {
                  tData.fluid.amount = 0;
                  break;
               }
            }

            GregTech_API.sLoadFinished = true;
            GT_Log.out.println("GT_Mod: Load-Phase finished!");
            GT_Log.ore.println("GT_Mod: Load-Phase finished!");
            i$ = GregTech_API.sAfterGTLoad.iterator();

            while(i$.hasNext()) {
               tRunnable = (Runnable)i$.next();

               try {
                  tRunnable.run();
               } catch (Throwable var6) {
                  var6.printStackTrace(GT_Log.err);
               }
            }

         }
      }
   }

   @EventHandler
   public void postload(FMLPostInitializationEvent aEvent) {
      if(!mDoNotInit) {
         if(!GregTech_API.sPostloadStarted) {
            Iterator tMat = GregTech_API.sBeforeGTPostload.iterator();

            while(tMat.hasNext()) {
               Runnable tStack = (Runnable)tMat.next();

               try {
                  tStack.run();
               } catch (Throwable var11) {
                  var11.printStackTrace(GT_Log.err);
               }
            }

            checkVersions();
            GT_Log.out.println("GT_Mod: Beginning PostLoad-Phase.");
            GT_Log.ore.println("GT_Mod: Beginning PostLoad-Phase.");
            GregTech_API.sPostloadStarted = true;
            GT_Log.out.println("GT_Mod: Checking if Items got overloaded.");

            for(int var13 = 0; var13 < GregTech_API.sItemList.length; ++var13) {
               if(GregTech_API.sItemList[var13] != null && Item.field_77698_e[GregTech_API.sItemList[var13].field_77779_bT] != GregTech_API.sItemList[var13]) {
                  GT_Log.err.println("GT_Mod: Another Mods ItemID is conflicting.");
                  GT_Log.err.println("GT_Mod: Errored.");
                  throw new GT_ItsNotMyFaultException("One of the GregTech-Items got overloaded. Check the ID-Config of the Mod you just installed or updated for Conflicts mentioned in the ForgeModLoader0.log with \'CONFLICT @\'. That is an Item-ID-Conflict! Don\'t bother ANY Modauthor with that, we won\'t help you with that at all! Also if you are one of the very few Idiots, who can\'t read this (I\'m not assuming you are that stupid Person, ID-Conflicts can happen to everyone), then don\'t f***ing go to my Thread and complain!!! Yes, that rage was fully justified, as I got a bunch of Idiots already. Note: This is an ID-Conflict Error in 100% of the cases and is 100% Failproof. If this Message appears to you then YOU HAVE AN ID-CONFLICT, no matter if you believe it or not, there IS A F***ING ID-CONFLICT AND YOU HAVE TO LOOK INTO ALL CONFIGS TO RESOLVE IT, and by ALL CONFIGS I also mean the GregTech ID-Config, because it can conflict with itself aswell!");
               }
            }

            GT_Log.out.println("GT_Mod: Adding Configs specific for MetaTileEntities");
            IMetaTileEntity[] var14 = GregTech_API.mMetaTileList;
            int var15 = var14.length;

            for(int i$ = 0; i$ < var15; ++i$) {
               IMetaTileEntity tRunnable = var14[i$];

               try {
                  if(tRunnable != null) {
                     tRunnable.onConfigLoad(GregTech_API.sMachineFile);
                  }
               } catch (Throwable var10) {
                  var10.printStackTrace(GT_Log.err);
               }
            }

            if(sSortToTheEnd) {
               GT_OreDictHandler.instance.registerUnificationEntries();
            } else {
               (new GT_ItemIterator()).run();
               GT_OreDictHandler.instance.registerUnificationEntries();
               (new GT_LiquidAndFuelLoader()).run();
            }

            (new GT_BookAndLootLoader()).run();
            (new GT_ItemMaxStacksizeLoader()).run();
            (new GT_BlockResistanceLoader()).run();
            (new GT_RecyclerBlacklistLoader()).run();
            (new GT_MinableRegistrator()).run();
            (new GT_SeedFlowerIterator()).run();
            (new GT_CraftingRecipeLoader()).run();
            (new GT_MachineRecipeLoader()).run();
            (new GT_ScrapboxDropLoader()).run();
            (new GT_UUMRecipeLoader()).run();
            (new GT_CropLoader()).run();
            (new GT_Worldgenloader()).run();
            (new GT_RecyclingRecipeLoader()).run();
            GT_RecipeRegistrator.registerUsagesForMaterials(new ItemStack(Block.field_71988_x, 1), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Wood, 1L), (String)null, false, true, false);
            GT_Log.out.println("GT_Mod: Activating OreDictionary Handler, this can take some time, as it scans the whole OreDictionary");
            System.out.println("If your Log stops here, you were too impatient. Wait a bit more next time, before killing Minecraft with the Task Manager.");
            GT_OreDictHandler.instance.activateHandler();
            System.out.println("Congratulations, you have been waiting long enough. Have a Cake.");
            GT_Log.out.println("GT_Mod: Adding Stone related Recipes");
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 0), new ItemStack(GregTech_API.sBlockList[5], 1, 7));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 1), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 2), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 3), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 4), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 5), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 6), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 7), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 8), new ItemStack(GregTech_API.sBlockList[5], 1, 15));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 9), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 10), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 11), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 12), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 13), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 14), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 15), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
            GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 3), (ItemStack)null, new ItemStack(GregTech_API.sBlockList[5], 1, 4), 200, 4);
            GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 11), (ItemStack)null, new ItemStack(GregTech_API.sBlockList[5], 1, 12), 200, 4);
            GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 7), (ItemStack)null, new ItemStack(GregTech_API.sBlockList[5], 1, 6), 200, 4);
            GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 15), (ItemStack)null, new ItemStack(GregTech_API.sBlockList[5], 1, 14), 200, 4);
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.field_72007_bm, 1, 3), new Object[]{new ItemStack(Block.field_72085_aj, 1, 8), GT_ToolDictNames.craftingToolFile});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 6), new Object[]{new ItemStack(GregTech_API.sBlockList[5], 1, 7), GT_ToolDictNames.craftingToolFile});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 14), new Object[]{new ItemStack(GregTech_API.sBlockList[5], 1, 15), GT_ToolDictNames.craftingToolFile});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.field_71978_w, 1, 0), new Object[]{new ItemStack(Block.field_71981_t, 1, 0), GT_ToolDictNames.craftingToolHardHammer});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.field_72007_bm, 1, 2), new Object[]{new ItemStack(Block.field_72007_bm, 1, 0), GT_ToolDictNames.craftingToolHardHammer});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 4), new Object[]{new ItemStack(GregTech_API.sBlockList[5], 1, 3), GT_ToolDictNames.craftingToolHardHammer});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 12), new Object[]{new ItemStack(GregTech_API.sBlockList[5], 1, 11), GT_ToolDictNames.craftingToolHardHammer});
            GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 4, 3), new Object[]{"XX", "XX", Character.valueOf('X'), new ItemStack(GregTech_API.sBlockList[5], 4, 0)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 4, 11), new Object[]{"XX", "XX", Character.valueOf('X'), new ItemStack(GregTech_API.sBlockList[5], 4, 8)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 4, 3), new Object[]{"XX", "XX", Character.valueOf('X'), new ItemStack(GregTech_API.sBlockList[5], 4, 7)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 4, 11), new Object[]{"XX", "XX", Character.valueOf('X'), new ItemStack(GregTech_API.sBlockList[5], 4, 15)});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.field_72085_aj, 1, 8), false, new Object[]{new ItemStack(Block.field_72085_aj, 1, 0)});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.field_72085_aj, 1, 0), false, new Object[]{new ItemStack(Block.field_72085_aj, 1, 8)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_72085_aj, 1, 0), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72079_ak, 1, 0)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_71978_w, 1, 0), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72079_ak, 1, 3)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_72081_al, 1, 0), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72079_ak, 1, 4)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_72007_bm, 1, 0), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72079_ak, 1, 5)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_72033_bA, 1, 0), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72079_ak, 1, 6)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_94339_ct, 1, 0), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72079_ak, 1, 7)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_72085_aj, 1, 8), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72079_ak, 1, 8)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_71988_x, 1, 0), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72092_bO, 1, 0)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_71988_x, 1, 1), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72092_bO, 1, 1)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_71988_x, 1, 2), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72092_bO, 1, 2)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Block.field_71988_x, 1, 3), false, false, new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(Block.field_72092_bO, 1, 3)});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Item.field_77669_D, 2, 0), false, new Object[]{new ItemStack(Block.field_71961_Y, 1, 32767)});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Item.field_77669_D, 2, 0), false, new Object[]{new ItemStack(Block.field_71962_X, 1, 0)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(Item.field_94585_bY, 1, 0), false, false, new Object[]{" T ", "TQT", "SSS", Character.valueOf('Q'), "craftingQuartz", Character.valueOf('S'), "stoneSmooth", Character.valueOf('T'), "craftingRedstoneTorch"});
            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "ic2forgehammer", true)) {
               GT_ModHandler.removeRecipeByOutput(GT_Items.Tool_Hammer_Forge.getWildcard(1L, new Object[0]));
            }

            ItemStack var16 = new ItemStack(Item.field_77703_o);
            ItemStack var17;
            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.PressurePlate", true) && null != (var17 = GT_ModHandler.removeRecipe(new ItemStack[]{var16, var16, null, null, null, null, null, null, null}))) {
               GT_ModHandler.addCraftingRecipe(var17, new Object[]{"XXT", Character.valueOf('X'), "plateIron", Character.valueOf('T'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('S'), "stickWood", Character.valueOf('I'), "ingotIron", Character.valueOf('F'), GT_ToolDictNames.craftingToolFile, Character.valueOf('W'), GT_ToolDictNames.craftingToolWrench});
            }

            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Bucket", true) && null != (var17 = GT_ModHandler.removeRecipe(new ItemStack[]{var16, null, var16, null, var16, null, null, null, null}))) {
               GT_ModHandler.addCraftingRecipe(var17, new Object[]{"XTX", " X ", Character.valueOf('X'), "plateIron", Character.valueOf('T'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('S'), "stickWood", Character.valueOf('I'), "ingotIron", Character.valueOf('F'), GT_ToolDictNames.craftingToolFile, Character.valueOf('W'), GT_ToolDictNames.craftingToolWrench});
            }

            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Minecart", true) && null != (var17 = GT_ModHandler.removeRecipe(new ItemStack[]{var16, null, var16, var16, var16, var16, null, null, null}))) {
               GT_ModHandler.addCraftingRecipe(var17, new Object[]{"XTX", "XXX", Character.valueOf('X'), "plateIron", Character.valueOf('T'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('S'), "stickWood", Character.valueOf('I'), "ingotIron", Character.valueOf('F'), GT_ToolDictNames.craftingToolFile, Character.valueOf('W'), GT_ToolDictNames.craftingToolWrench});
            }

            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Door", true) && null != (var17 = GT_ModHandler.removeRecipe(new ItemStack[]{var16, var16, null, var16, var16, null, var16, var16, null}))) {
               GT_ModHandler.addCraftingRecipe(var17, new Object[]{"XX ", "XXT", "XX ", Character.valueOf('X'), "plateIron", Character.valueOf('T'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('S'), "stickWood", Character.valueOf('I'), "ingotIron", Character.valueOf('F'), GT_ToolDictNames.craftingToolFile, Character.valueOf('W'), GT_ToolDictNames.craftingToolWrench});
            }

            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Cauldron", true) && null != (var17 = GT_ModHandler.removeRecipe(new ItemStack[]{var16, null, var16, var16, null, var16, var16, var16, var16}))) {
               GT_ModHandler.addCraftingRecipe(var17, new Object[]{"X X", "XTX", "XXX", Character.valueOf('X'), "plateIron", Character.valueOf('T'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('S'), "stickWood", Character.valueOf('I'), "ingotIron", Character.valueOf('F'), GT_ToolDictNames.craftingToolFile, Character.valueOf('W'), GT_ToolDictNames.craftingToolWrench});
            }

            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Hopper", true) && null != (var17 = GT_ModHandler.removeRecipe(new ItemStack[]{var16, null, var16, var16, new ItemStack(Block.field_72077_au, 1, 0), var16, null, var16, null}))) {
               GT_ModHandler.addCraftingRecipe(var17, new Object[]{"XWX", "XCX", " X ", Character.valueOf('X'), "plateIron", Character.valueOf('T'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('S'), "stickWood", Character.valueOf('I'), "ingotIron", Character.valueOf('F'), GT_ToolDictNames.craftingToolFile, Character.valueOf('W'), GT_ToolDictNames.craftingToolWrench, Character.valueOf('C'), "craftingChest"});
            }

            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Bars", true) && null != (var17 = GT_ModHandler.removeRecipe(new ItemStack[]{var16, var16, var16, var16, var16, var16, null, null, null}))) {
               var17.field_77994_a /= 2;
               GT_ModHandler.addCraftingRecipe(var17, new Object[]{" W ", "XXX", "XXX", Character.valueOf('X'), "stickIron", Character.valueOf('T'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('S'), "stickWood", Character.valueOf('I'), "ingotIron", Character.valueOf('F'), GT_ToolDictNames.craftingToolFile, Character.valueOf('W'), GT_ToolDictNames.craftingToolWrench});
            }

            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("ironFence", 6L), new Object[]{"XXX", "XXX", " W ", Character.valueOf('X'), "stickIron", Character.valueOf('T'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('S'), "stickWood", Character.valueOf('I'), "ingotIron", Character.valueOf('F'), GT_ToolDictNames.craftingToolFile, Character.valueOf('W'), GT_ToolDictNames.craftingToolWrench});
            var16 = new ItemStack(Item.field_77717_p);
            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Gold.PressurePlate", true) && null != (var17 = GT_ModHandler.removeRecipe(new ItemStack[]{var16, var16, null, null, null, null, null, null, null}))) {
               GT_ModHandler.addCraftingRecipe(var17, new Object[]{"XXT", Character.valueOf('X'), "plateGold", Character.valueOf('T'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('S'), "stickWood", Character.valueOf('I'), "ingotGold", Character.valueOf('F'), GT_ToolDictNames.craftingToolFile, Character.valueOf('W'), GT_ToolDictNames.craftingToolWrench});
            }

            var16 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Rubber, 1L);
            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Rubber.Sheet", true) && null != (var17 = GT_ModHandler.removeRecipe(new ItemStack[]{var16, var16, var16, var16, var16, var16, null, null, null}))) {
               GT_ModHandler.addCraftingRecipe(var17, new Object[]{"XXX", "XXX", Character.valueOf('X'), "plateRubber"});
            }

            var17 = GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(Block.field_71988_x, 1, 0), null, null, new ItemStack(Block.field_71988_x, 1, 0)});
            if(var17 != null) {
               GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(sNerfedWoodPlank?(long)var17.field_77994_a:(long)(var17.field_77994_a * 5 / 4), new Object[]{var17}), new Object[]{"S", "P", "P", Character.valueOf('P'), "plankWood", Character.valueOf('S'), GT_ToolDictNames.craftingToolSaw});
               GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(sNerfedWoodPlank?(long)(var17.field_77994_a / 2):(long)var17.field_77994_a, new Object[]{var17}), new Object[]{"P", "P", Character.valueOf('P'), "plankWood"});
            }

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.field_72046_aM, 1, 0), new Object[]{"PP", Character.valueOf('P'), "plankWood"}));
            if(sNerfedWoodenTools) {
               GT_Log.out.println("GT_Mod: Nerfing Wood Tool Durability");
               Item.field_77715_r.func_77656_e(12);
               Item.field_77713_t.func_77656_e(12);
               Item.field_77714_s.func_77656_e(12);
               Item.field_77712_u.func_77656_e(12);
               Item.field_77678_N.func_77656_e(12);
            }

            if(sNerfedStoneTools) {
               GT_Log.out.println("GT_Mod: Nerfing Stone Tool Durability");
               Item.field_77711_v.func_77656_e(48);
               Item.field_77720_x.func_77656_e(48);
               Item.field_77710_w.func_77656_e(48);
               Item.field_77719_y.func_77656_e(48);
               Item.field_77679_O.func_77656_e(48);
            }

            Iterator var18;
            Runnable var20;
            if(!this.isClientSide()) {
               GT_Log.out.println("GT_Mod: Starting Cover Load Phase Serverside");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateSilver"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateRuby"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateSapphire"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateAluminium"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateTitanium"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateChrome"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateSteel"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateBrass"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateLead"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateElectrum"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateZinc"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateOlivine"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGreenSapphire"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("platePlatinum"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateTungsten"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateNickel"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateTungstenSteel"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateAlloyIridium"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateInvar"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateOsmium"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateIridium"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateDenseBronze"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGarnetYellow"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGarnetRed"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGraniteBlack"), "");
               GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGraniteRed"), "");
               var18 = GregTech_API.sGTCoverload.iterator();

               while(var18.hasNext()) {
                  var20 = (Runnable)var18.next();

                  try {
                     var20.run();
                  } catch (Throwable var9) {
                     var9.printStackTrace(GT_Log.err);
                  }
               }
            }

            FluidContainerData[] var19 = FluidContainerRegistry.getRegisteredFluidContainerData();
            int var22 = var19.length;

            for(int e = 0; e < var22; ++e) {
               FluidContainerData tData = var19[e];
               if(tData.filledContainer.func_77973_b() == Item.field_77726_bs) {
                  tData.fluid.amount = 0;
                  break;
               }
            }

            GT_Log.out.println("GT_Mod: Adding buffered Recipes.");
            GT_ModHandler.stopBufferingCraftingRecipes();
            GregTech_API.sPostloadFinished = true;
            GT_Log.out.println("GT_Mod: PostLoad-Phase finished!");
            GT_Log.ore.println("GT_Mod: PostLoad-Phase finished!");
            if(GregTech_API.DEBUG_MODE) {
               try {
                  GT_Log.out.println("GT_Mod: Printing registered Channels");
                  ArrayListMultimap var21 = (ArrayListMultimap)GT_Utility.getField((Object)NetworkRegistry.instance(), "universalPacketHandlers").get(NetworkRegistry.instance());
                  Iterator var23 = var21.keySet().iterator();

                  while(var23.hasNext()) {
                     String var24 = (String)var23.next();
                     GT_Log.out.println(var24);
                  }
               } catch (Throwable var12) {
                  var12.printStackTrace(GT_Log.err);
               }
            }

            var18 = GregTech_API.sAfterGTPostload.iterator();

            while(var18.hasNext()) {
               var20 = (Runnable)var18.next();

               try {
                  var20.run();
               } catch (Throwable var8) {
                  var8.printStackTrace(GT_Log.err);
               }
            }

            GT_Log.out.println("GT_Mod: Loading finished, deallocating temporary Init Variables.");
            sItemIDs = null;
            sBlockIDs = null;
            GregTech_API.sBeforeGTPreload = null;
            GregTech_API.sAfterGTPreload = null;
            GregTech_API.sBeforeGTLoad = null;
            GregTech_API.sAfterGTLoad = null;
            GregTech_API.sBeforeGTPostload = null;
            GregTech_API.sAfterGTPostload = null;
         }
      }
   }

   @EventHandler
   public void start(FMLServerStartingEvent aEvent) {
      if(!mDoNotInit) {
         Iterator tStacks = GregTech_API.sBeforeGTServerstart.iterator();

         while(tStacks.hasNext()) {
            Runnable i$ = (Runnable)tStacks.next();

            try {
               i$.run();
            } catch (Throwable var9) {
               var9.printStackTrace(GT_Log.err);
            }
         }

         FluidContainerData[] var10 = FluidContainerRegistry.getRegisteredFluidContainerData();
         int var13 = var10.length;

         int tRunnable;
         for(tRunnable = 0; tRunnable < var13; ++tRunnable) {
            FluidContainerData e = var10[tRunnable];
            if(e.filledContainer.func_77973_b() == Item.field_77726_bs) {
               e.fluid.amount = 0;
               break;
            }
         }

         GT_Log.out.println("GT_Mod: ServerStart-Phase started!");
         GT_Log.ore.println("GT_Mod: ServerStart-Phase started!");
         mUniverse = null;
         GT_TickHandler.isFirstTick = true;
         new GT_GUIHandler();
         IMetaTileEntity[] var11 = GregTech_API.mMetaTileList;
         var13 = var11.length;

         for(tRunnable = 0; tRunnable < var13; ++tRunnable) {
            IMetaTileEntity var15 = var11[tRunnable];

            try {
               if(var15 != null) {
                  var15.onServerStart();
               }
            } catch (Throwable var8) {
               var8.printStackTrace(GT_Log.err);
            }
         }

         GT_Log.out.println("GT_Mod: Unificating outputs of all known Recipe Types.");
         ArrayList var12 = new ArrayList(10000);
         GT_Log.out.println("GT_Mod: IC2 Machines");
         Iterator var14 = Recipes.cannerBottle.getRecipes().values().iterator();

         ItemStack tContent;
         RecipeOutput var16;
         Iterator var17;
         while(var14.hasNext()) {
            var16 = (RecipeOutput)var14.next();
            var17 = var16.items.iterator();

            while(var17.hasNext()) {
               tContent = (ItemStack)var17.next();
               var12.add(tContent);
            }
         }

         var14 = Recipes.centrifuge.getRecipes().values().iterator();

         while(var14.hasNext()) {
            var16 = (RecipeOutput)var14.next();
            var17 = var16.items.iterator();

            while(var17.hasNext()) {
               tContent = (ItemStack)var17.next();
               var12.add(tContent);
            }
         }

         var14 = Recipes.compressor.getRecipes().values().iterator();

         while(var14.hasNext()) {
            var16 = (RecipeOutput)var14.next();
            var17 = var16.items.iterator();

            while(var17.hasNext()) {
               tContent = (ItemStack)var17.next();
               var12.add(tContent);
            }
         }

         var14 = Recipes.extractor.getRecipes().values().iterator();

         while(var14.hasNext()) {
            var16 = (RecipeOutput)var14.next();
            var17 = var16.items.iterator();

            while(var17.hasNext()) {
               tContent = (ItemStack)var17.next();
               var12.add(tContent);
            }
         }

         var14 = Recipes.macerator.getRecipes().values().iterator();

         while(var14.hasNext()) {
            var16 = (RecipeOutput)var14.next();
            var17 = var16.items.iterator();

            while(var17.hasNext()) {
               tContent = (ItemStack)var17.next();
               var12.add(tContent);
            }
         }

         var14 = Recipes.metalformerCutting.getRecipes().values().iterator();

         while(var14.hasNext()) {
            var16 = (RecipeOutput)var14.next();
            var17 = var16.items.iterator();

            while(var17.hasNext()) {
               tContent = (ItemStack)var17.next();
               var12.add(tContent);
            }
         }

         var14 = Recipes.metalformerExtruding.getRecipes().values().iterator();

         while(var14.hasNext()) {
            var16 = (RecipeOutput)var14.next();
            var17 = var16.items.iterator();

            while(var17.hasNext()) {
               tContent = (ItemStack)var17.next();
               var12.add(tContent);
            }
         }

         var14 = Recipes.metalformerRolling.getRecipes().values().iterator();

         while(var14.hasNext()) {
            var16 = (RecipeOutput)var14.next();
            var17 = var16.items.iterator();

            while(var17.hasNext()) {
               tContent = (ItemStack)var17.next();
               var12.add(tContent);
            }
         }

         var14 = Recipes.matterAmplifier.getRecipes().values().iterator();

         while(var14.hasNext()) {
            var16 = (RecipeOutput)var14.next();
            var17 = var16.items.iterator();

            while(var17.hasNext()) {
               tContent = (ItemStack)var17.next();
               var12.add(tContent);
            }
         }

         var14 = Recipes.oreWashing.getRecipes().values().iterator();

         while(var14.hasNext()) {
            var16 = (RecipeOutput)var14.next();
            var17 = var16.items.iterator();

            while(var17.hasNext()) {
               tContent = (ItemStack)var17.next();
               var12.add(tContent);
            }
         }

         GT_Log.out.println("GT_Mod: Dungeon Loot");
         WeightedRandomChestContent[] var18 = ChestGenHooks.getInfo("dungeonChest").getItems(new Random());
         tRunnable = var18.length;

         int var19;
         WeightedRandomChestContent var20;
         for(var19 = 0; var19 < tRunnable; ++var19) {
            var20 = var18[var19];
            var12.add(var20.field_76297_b);
         }

         var18 = ChestGenHooks.getInfo("bonusChest").getItems(new Random());
         tRunnable = var18.length;

         for(var19 = 0; var19 < tRunnable; ++var19) {
            var20 = var18[var19];
            var12.add(var20.field_76297_b);
         }

         var18 = ChestGenHooks.getInfo("villageBlacksmith").getItems(new Random());
         tRunnable = var18.length;

         for(var19 = 0; var19 < tRunnable; ++var19) {
            var20 = var18[var19];
            var12.add(var20.field_76297_b);
         }

         var18 = ChestGenHooks.getInfo("strongholdCrossing").getItems(new Random());
         tRunnable = var18.length;

         for(var19 = 0; var19 < tRunnable; ++var19) {
            var20 = var18[var19];
            var12.add(var20.field_76297_b);
         }

         var18 = ChestGenHooks.getInfo("strongholdLibrary").getItems(new Random());
         tRunnable = var18.length;

         for(var19 = 0; var19 < tRunnable; ++var19) {
            var20 = var18[var19];
            var12.add(var20.field_76297_b);
         }

         var18 = ChestGenHooks.getInfo("strongholdCorridor").getItems(new Random());
         tRunnable = var18.length;

         for(var19 = 0; var19 < tRunnable; ++var19) {
            var20 = var18[var19];
            var12.add(var20.field_76297_b);
         }

         var18 = ChestGenHooks.getInfo("pyramidJungleDispenser").getItems(new Random());
         tRunnable = var18.length;

         for(var19 = 0; var19 < tRunnable; ++var19) {
            var20 = var18[var19];
            var12.add(var20.field_76297_b);
         }

         var18 = ChestGenHooks.getInfo("pyramidJungleChest").getItems(new Random());
         tRunnable = var18.length;

         for(var19 = 0; var19 < tRunnable; ++var19) {
            var20 = var18[var19];
            var12.add(var20.field_76297_b);
         }

         var18 = ChestGenHooks.getInfo("pyramidDesertyChest").getItems(new Random());
         tRunnable = var18.length;

         for(var19 = 0; var19 < tRunnable; ++var19) {
            var20 = var18[var19];
            var12.add(var20.field_76297_b);
         }

         var18 = ChestGenHooks.getInfo("mineshaftCorridor").getItems(new Random());
         tRunnable = var18.length;

         for(var19 = 0; var19 < tRunnable; ++var19) {
            var20 = var18[var19];
            var12.add(var20.field_76297_b);
         }

         GT_Log.out.println("GT_Mod: Smelting");
         var14 = FurnaceRecipes.func_77602_a().func_77599_b().values().iterator();

         Object var21;
         while(var14.hasNext()) {
            var21 = var14.next();
            var12.add((ItemStack)var21);
         }

         var14 = FurnaceRecipes.func_77602_a().getMetaSmeltingList().values().iterator();

         ItemStack var22;
         while(var14.hasNext()) {
            var22 = (ItemStack)var14.next();
            var12.add(var22);
         }

         if(sCraftingUnification) {
            GT_Log.out.println("GT_Mod: Crafting Recipes");
            var14 = CraftingManager.func_77594_a().func_77592_b().iterator();

            while(var14.hasNext()) {
               var21 = var14.next();
               if(var21 instanceof IRecipe) {
                  var12.add(((IRecipe)var21).func_77571_b());
               }
            }
         }

         var14 = var12.iterator();

         while(var14.hasNext()) {
            var22 = (ItemStack)var14.next();
            if(GT_OreDictHandler.instance.mRegisteredStacks.contains(var22)) {
               System.err.println("GT-ERR-01: @ " + var22.func_77977_a() + "   " + var22.func_82833_r());
               System.err.println("A Recipe used an OreDict Item as Output directly, without copying it before!!! This is a typical CallByReference/CallByValue Error");
               System.err.println("Said Item will be renamed to make the invalid Recipe visible, so that you can report it properly.");
               System.err.println("Please check all Recipes outputting this Item, and report the Recipes to their Owner.");
               System.err.println("The Owner of the ==>RECIPE<==, NOT the Owner of the Item, which has been mentioned above!!!");
               System.err.println("And ONLY Recipes which are ==>OUTPUTTING<== the Item, sorry but I don\'t want failed Bug Reports.");
               System.err.println("GregTech just reports this Error to you, so you can report it to the Mod causing the Problem.");
               System.err.println("Even though I make that Bug visible, I can not and will not fix that for you, that\'s for the causing Mod to fix.");
               System.err.println("And speaking of failed Reports:");
               System.err.println("Both IC2 and GregTech CANNOT be the CAUSE of this Problem, so don\'t report it to either of them.");
               System.err.println("I REPEAT, BOTH, IC2 and GregTech CANNOT be the source of THIS BUG. NO MATTER WHAT.");
               System.err.println("Asking in the IC2 Forums, which Mod is causing that won\'t help anyone, since it is not possible to determine, which Mod it is.");
               System.err.println("If it would be possible, then I would have had added the Mod which is causing it to the Message already. But it is not possible.");
               System.err.println("Sorry, but this Error is serious enough to justify this Wall-O-Text and the partially Allcapsed Language.");
               var22.func_82834_c("ERROR! PLEASE CHECK YOUR LOG FOR \'GT-ERR-01\'!");
            } else {
               GT_OreDictUnificator.setStack(var22);
            }
         }

         GT_Log.out.println("GT_Mod: ServerStart-Phase finished!");
         GT_Log.ore.println("GT_Mod: ServerStart-Phase finished!");
         var14 = GregTech_API.sAfterGTServerstart.iterator();

         while(var14.hasNext()) {
            Runnable var23 = (Runnable)var14.next();

            try {
               var23.run();
            } catch (Throwable var7) {
               var7.printStackTrace(GT_Log.err);
            }
         }

      }
   }

   @EventHandler
   public void start(FMLServerStartedEvent aEvent) {
      if(!mDoNotInit) {
         GT_Recipe.reInit();
         GregTech_API.sWirelessRedstone.clear();
      }
   }

   @EventHandler
   public void stop(FMLServerStoppingEvent aEvent) {
      if(!mDoNotInit) {
         Iterator i$ = GregTech_API.sBeforeGTServerstop.iterator();

         Runnable tRunnable;
         while(i$.hasNext()) {
            tRunnable = (Runnable)i$.next();

            try {
               tRunnable.run();
            } catch (Throwable var11) {
               var11.printStackTrace(GT_Log.err);
            }
         }

         GregTech_API.sWirelessRedstone.clear();
         IMetaTileEntity[] var14 = GregTech_API.mMetaTileList;
         int var16 = var14.length;

         int e;
         for(e = 0; e < var16; ++e) {
            IMetaTileEntity j = var14[e];

            try {
               if(j != null) {
                  j.onServerStop();
               }
            } catch (Throwable var10) {
               var10.printStackTrace(GT_Log.err);
            }
         }

         mUniverse = null;

         try {
            if(GregTech_API.DEBUG_MODE || GT_Log.out != System.out) {
               GT_Log.out.println("*");
               GT_Log.out.println("Printing List of all registered Objects inside the OreDictionary, now with free extra Sorting:");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               String[] var15 = OreDictionary.getOreNames();
               Arrays.sort(var15);
               String[] var17 = var15;
               e = var15.length;

               int var20;
               for(var20 = 0; var20 < e; ++var20) {
                  String e1 = var17[var20];
                  int tAmount = OreDictionary.getOres(e1).size();
                  if(tAmount > 0) {
                     GT_Log.out.println((tAmount < 10?" ":"") + tAmount + "x " + e1);
                  }
               }

               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("Outputting all the Names inside the Itemslist, this List can become very long");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");

               String var19;
               for(var16 = 0; var16 < Item.field_77698_e.length; ++var16) {
                  if(Item.field_77698_e[var16] != null) {
                     GT_Log.out.println(Item.field_77698_e[var16].func_77658_a());
                     if(Item.field_77698_e[var16].func_77614_k()) {
                        var19 = "";

                        for(var20 = 0; var20 < 16; ++var20) {
                           try {
                              var19 = Item.field_77698_e[var16].func_77667_c(new ItemStack(Item.field_77698_e[var16], 1, var20));
                              if(var19 != null && !var19.equals("")) {
                                 GT_Log.out.println(var20 + ": " + Item.field_77698_e[var16].func_77658_a());
                              }
                           } catch (Throwable var9) {
                              ;
                           }
                        }
                     }
                  }
               }

               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("Outputting all the Names registered by Railcraft");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");

               try {
                  Iterator var18 = TagList.getTags().iterator();

                  while(var18.hasNext()) {
                     var19 = (String)var18.next();
                     GT_Log.out.println(var19);
                  }
               } catch (Throwable var12) {
                  ;
               }

               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("Outputting all the Names inside the Biomeslist");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");

               for(var16 = 0; var16 < BiomeGenBase.field_76773_a.length; ++var16) {
                  if(BiomeGenBase.field_76773_a[var16] != null) {
                     GT_Log.out.println(BiomeGenBase.field_76773_a[var16].field_76756_M + " = " + BiomeGenBase.field_76773_a[var16].field_76791_y);
                  }
               }

               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("Printing List of generatable Materials");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");

               for(var16 = 0; var16 < GregTech_API.sGeneratedMaterials.length; ++var16) {
                  if(GregTech_API.sGeneratedMaterials[var16] == null) {
                     GT_Log.out.println("Index " + var16 + ":" + null);
                  } else {
                     GT_Log.out.println("Index " + var16 + ":" + GregTech_API.sGeneratedMaterials[var16]);
                  }
               }

               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("END GregTech-Debug");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
               GT_Log.out.println("*");
            }
         } catch (Throwable var13) {
            if(GregTech_API.DEBUG_MODE) {
               var13.printStackTrace(GT_Log.err);
            }
         }

         i$ = GregTech_API.sAfterGTServerstop.iterator();

         while(i$.hasNext()) {
            tRunnable = (Runnable)i$.next();

            try {
               tRunnable.run();
            } catch (Throwable var8) {
               var8.printStackTrace(GT_Log.err);
            }
         }

      }
   }

   @ForgeSubscribe
   public void onFluidContainerRegistration(FluidContainerRegisterEvent aFluidEvent) {
      if(this.tNothingRegistered) {
         FluidContainerData[] arr$ = FluidContainerRegistry.getRegisteredFluidContainerData();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            FluidContainerData tData = arr$[i$];
            if(tData.filledContainer.func_77973_b() == Item.field_77726_bs) {
               tData.fluid.amount = 0;
            }

            GT_OreDictUnificator.addToBlacklist(tData.filledContainer);
         }

         this.tNothingRegistered = false;
      }

      GT_OreDictUnificator.addToBlacklist(aFluidEvent.data.filledContainer);
   }

   public static File getSaveDirectory() {
      if(mUniverse == null) {
         return null;
      } else {
         SaveHandler tSaveHandler = (SaveHandler)mUniverse.func_72860_G();
         File rFile = null;
         Field[] tFields = SaveHandler.class.getDeclaredFields();

         for(int i = 0; i < tFields.length; ++i) {
            if(tFields[i].getType() == File.class) {
               tFields[i].setAccessible(true);

               try {
                  File e = (File)tFields[i].get(tSaveHandler);
                  if(rFile == null || rFile.getParentFile() == e) {
                     rFile = e;
                  }
               } catch (Exception var5) {
                  ;
               }
            }
         }

         return rFile;
      }
   }

   public boolean allowPacketToBeSent(Packet aPacket, EntityPlayerMP aPlayer) {
      return true;
   }

   public boolean isServerSide() {
      return gregtechproxy.isServerSide();
   }

   public boolean isClientSide() {
      return gregtechproxy.isClientSide();
   }

   public boolean isBukkitSide() {
      return gregtechproxy.isBukkitSide();
   }

   public EntityPlayer getThePlayer() {
      return gregtechproxy.getThePlayer();
   }

   public int addArmor(String aArmorPrefix) {
      return gregtechproxy.addArmor(aArmorPrefix);
   }

   public void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
      gregtechproxy.doSonictronSound(aStack, aWorld, aX, aY, aZ);
   }

   static {
      checkVersions();
   }
}
