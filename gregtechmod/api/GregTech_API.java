package gregtechmod.api;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.interfaces.IGT_Mod;
import gregtechmod.api.interfaces.IGT_RecipeAdder;
import gregtechmod.api.interfaces.IMachineBlockUpdateable;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import gregtechmod.api.util.GT_CircuitryBehavior;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_CreativeTab;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.world.GT_Worldgen;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GregTech_API {

   public static volatile int VERSION = 408;
   public static IGT_Mod gregtechmod;
   public static IGT_RecipeAdder sRecipeAdder;
   public static List<Runnable> sBeforeGTPreload = new ArrayList();
   public static List<Runnable> sAfterGTPreload = new ArrayList();
   public static List<Runnable> sBeforeGTLoad = new ArrayList();
   public static List<Runnable> sAfterGTLoad = new ArrayList();
   public static List<Runnable> sBeforeGTPostload = new ArrayList();
   public static List<Runnable> sAfterGTPostload = new ArrayList();
   public static List<Runnable> sBeforeGTServerstart = new ArrayList();
   public static List<Runnable> sAfterGTServerstart = new ArrayList();
   public static List<Runnable> sBeforeGTServerstop = new ArrayList();
   public static List<Runnable> sAfterGTServerstop = new ArrayList();
   public static List<Runnable> sGTCoverload = new ArrayList();
   public static List<Runnable> sGTBlockIconload = new ArrayList();
   public static List<Runnable> sGTItemIconload = new ArrayList();
   public static Object sBlockIcons;
   public static Object sItemIcons;
   public static boolean DEBUG_MODE = false;
   public static boolean SECONDARY_DEBUG_MODE = false;
   public static boolean IC_ENERGY_COMPATIBILITY = true;
   public static boolean UE_ENERGY_COMPATIBILITY = true;
   public static boolean BC_ENERGY_COMPATIBILITY = true;
   public static GT_Config sRecipeFile = null;
   public static GT_Config sMachineFile = null;
   public static GT_Config sWorldgenFile = null;
   public static GT_Config sMaterialProperties = null;
   public static GT_Config sUnification = null;
   public static GT_Config sSpecialFile = null;
   public static final short ITEM_WILDCARD_DAMAGE = 32767;
   public static final short MAXIMUM_METATILE_IDS = 32766;
   public static Icon FAIL_ICON = null;
   public static final CreativeTabs TAB_GREGTECH = new GT_CreativeTab();
   public static final CreativeTabs TAB_GREGTECH_MATERIALS = new GT_CreativeTab();
   public static int TICKS_FOR_LAG_AVERAGING = 25;
   public static int MILLISECOND_THRESHOLD_UNTIL_LAG_WARNING = 100;
   public static final IMetaTileEntity[] mMetaTileList = new IMetaTileEntity[32766];
   public static final String GENERIC_CHANNEL = "gregtech";
   public static final String SOUND_PACKET_CHANNEL = "GTSound";
   public static final String TILEENTITY_PACKET_CHANNEL = "GTTile";
   public static final String IC2_MOD_ID = "ic2";
   public static final String IC2_TEXTURE_PATH = "ic2:";
   public static final String MOD_ID = "gregtech_addon";
   public static final String TEXTURE_FOLDER = "textures/";
   public static final String TEXTURE_PATH_ITEM = "gregtech_addon:";
   public static final String TEXTURE_PATH_BLOCK = "gregtech_addon:";
   public static final String GUI_PATH = "gregtech_addon:textures/gui/";
   public static final Block[] sBlockList = new Block[16];
   public static final Item[] sItemList = new Item[256];
   public static long sServerTickCounter = 0L;
   public static long sClientTickCounter = 0L;
   public static long sWorldTickCounter = 0L;
   public static boolean sDoShowAllItemsInCreative = false;
   public static boolean sColoredGUI = true;
   public static boolean sConstantEnergy = true;
   public static boolean sMachineExplosions = true;
   public static boolean sMachineFlammable = true;
   public static boolean sMachineNonWrenchExplosions = true;
   public static boolean sMachineRainExplosions = true;
   public static boolean sMachineThunderExplosions = true;
   public static boolean sMachineFireExplosions = true;
   public static boolean sMachineWireFire = true;
   public static boolean sPreloadStarted = false;
   public static boolean sPreloadFinished = false;
   public static boolean sLoadStarted = false;
   public static boolean sLoadFinished = false;
   public static boolean sPostloadStarted = false;
   public static boolean sPostloadFinished = false;
   public static final Map<Integer, Icon> sCovers = new HashMap();
   public static final Map<Integer, GT_CircuitryBehavior> sCircuitryBehaviors = new HashMap();
   public static final Map<Integer, GT_CoverBehavior> sCoverBehaviors = new HashMap();
   public static final Map<Block, Integer> sMachineIDs = new HashMap();
   public static final Map<Integer, Byte> sWirelessRedstone = new HashMap();
   public static final Map<Integer, Integer> sIDSUList = new HashMap();
   public static final Map<String, ItemStack> sBookList = new HashMap();
   public static final Map<Integer, String> sSoundList = new HashMap();
   public static final List<Integer> sToolList = new ArrayList();
   public static final List<Integer> sCrowbarList = new ArrayList();
   public static final List<Integer> sScrewdriverList = new ArrayList();
   public static final List<Integer> sWrenchList = new ArrayList();
   public static final List<Integer> sSoftHammerList = new ArrayList();
   public static final List<Integer> sHardHammerList = new ArrayList();
   public static final List<Integer> sSolderingToolList = new ArrayList();
   public static final List<Integer> sSolderingMetalList = new ArrayList();
   public static final List<Integer> sDimensionalList = new ArrayList();
   public static final List<String> sContinentalWaterList = new ArrayList();
   public static final List<String> sTetrahedriteList = new ArrayList();
   public static final List<String> sCassiteriteList = new ArrayList();
   public static final List<String> sNickelList = new ArrayList();
   public static final List<String> sRubyList = new ArrayList();
   public static final List<String> sSapphireList = new ArrayList();
   public static final List<String> sBauxiteList = new ArrayList();
   public static final List<GT_Worldgen> sWorldgenList = new ArrayList();
   public static final int VOLTAGE_ULTRALOW = 8;
   public static final int VOLTAGE_LOW = 32;
   public static final int VOLTAGE_MEDIUM = 128;
   public static final int VOLTAGE_HIGH = 512;
   public static final int VOLTAGE_EXTREME = 2048;
   public static final int VOLTAGE_INSANE = 8192;
   public static final int VOLTAGE_LUDICROUS = 32768;
   public static final int VOLTAGE_ZPM = 131072;
   public static final int VOLTAGE_ULTIMATE = 1000000;
   public static final int[] VOLTAGES = new int[]{8, 32, 128, 512, 2048, 8192, '\u8000', 131072, 1000000};
   public static final Materials[] sGeneratedMaterials = new Materials[1000];
   public static final long MATERIAL_UNIT = 3628800L;
   public static World sDummyWorld;
   private static Class sBaseMetaTileEntityClass = null;
   public static GT_CoverBehavior sGenericBehavior;


   public static boolean isGregTechLoaded() {
      return gregtechmod != null;
   }

   public static ItemStack getGregTechBlock(int aIndex, int aAmount, int aMeta) {
      if(aIndex < sBlockList.length && aIndex >= 0) {
         if(sBlockList[aIndex] != null) {
            return new ItemStack(sBlockList[aIndex], aAmount, aMeta);
         }

         GT_Log.err.println("GregTech_API ERROR: This Block Index " + aIndex + " wasn\'t initialized, you either called it before the Init-Phase (Only Post-Init or later will work), or this Item doesn\'t exist now");
      } else {
         GT_Log.err.println("GregTech_API ERROR: The Index " + aIndex + " is not part of my Block Range");
      }

      return null;
   }

   public static int getCapsuleCellContainerCount(ItemStack aStack) {
      return GT_ModHandler.getCapsuleCellContainerCount(aStack);
   }

   @Deprecated
   public static ItemStack getGregTechItem(int aIndex, int aAmount, int aMeta) {
      if(aIndex < sItemList.length && aIndex >= 0) {
         if(sItemList[aIndex] != null) {
            return GT_OreDictUnificator.get(true, new ItemStack(sItemList[aIndex], aAmount, aMeta));
         }

         GT_Log.err.println("GregTech_API ERROR: This Item Index " + aIndex + " wasn\'t initialized, you either called it before the Init-Phase (Only Post-Init or later will work), or this Item doesn\'t exist now");
      } else {
         GT_Log.err.println("GregTech_API ERROR: The Index " + aIndex + " is not part of my Item Range");
      }

      return null;
   }

   public static ItemStack getGregTechComponent(int aComponentIndex, int aAmount) {
      return aComponentIndex >= 0?GT_OreDictUnificator.get(true, new ItemStack(sItemList[3], aAmount, aComponentIndex)):null;
   }

   public static ItemStack getGregTechMaterial(int aComponentIndex, int aAmount) {
      return aComponentIndex >= 0?GT_OreDictUnificator.get(true, new ItemStack(sItemList[0], aAmount, aComponentIndex)):null;
   }

   public static ItemStack getUnificatedOreDictStack(ItemStack aOreStack) {
      if(!sPreloadFinished) {
         GT_Log.err.println("GregTech_API ERROR: " + aOreStack.func_77973_b() + "." + aOreStack.func_77960_j() + " - OreDict Unification Entries are not registered now, please call it in the postload phase.");
      }

      return GT_OreDictUnificator.get(true, aOreStack);
   }

   public static boolean causeMachineUpdate(World aWorld, int aX, int aY, int aZ) {
      if(!aWorld.field_72995_K) {
         stepToUpdateMachine(aWorld, aX, aY, aZ, new ArrayList());
      }

      return true;
   }

   private static void stepToUpdateMachine(World aWorld, int aX, int aY, int aZ, ArrayList<ChunkPosition> aList) {
      aList.add(new ChunkPosition(aX, aY, aZ));
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      if(tTileEntity != null && tTileEntity instanceof IMachineBlockUpdateable) {
         ((IMachineBlockUpdateable)tTileEntity).onMachineBlockUpdate();
      }

      if(aList.size() < 5 || tTileEntity != null && tTileEntity instanceof IMachineBlockUpdateable || isMachineBlock(Block.field_71973_m[aWorld.func_72798_a(aX, aY, aZ)], aWorld.func_72805_g(aX, aY, aZ))) {
         if(!aList.contains(new ChunkPosition(aX + 1, aY, aZ))) {
            stepToUpdateMachine(aWorld, aX + 1, aY, aZ, aList);
         }

         if(!aList.contains(new ChunkPosition(aX - 1, aY, aZ))) {
            stepToUpdateMachine(aWorld, aX - 1, aY, aZ, aList);
         }

         if(!aList.contains(new ChunkPosition(aX, aY + 1, aZ))) {
            stepToUpdateMachine(aWorld, aX, aY + 1, aZ, aList);
         }

         if(!aList.contains(new ChunkPosition(aX, aY - 1, aZ))) {
            stepToUpdateMachine(aWorld, aX, aY - 1, aZ, aList);
         }

         if(!aList.contains(new ChunkPosition(aX, aY, aZ + 1))) {
            stepToUpdateMachine(aWorld, aX, aY, aZ + 1, aList);
         }

         if(!aList.contains(new ChunkPosition(aX, aY, aZ - 1))) {
            stepToUpdateMachine(aWorld, aX, aY, aZ - 1, aList);
         }
      }

   }

   public static boolean registerMachineBlock(Block aBlock, int aMeta) {
      if(GT_Utility.isBlockInvalid(aBlock)) {
         return false;
      } else {
         sMachineIDs.put(aBlock, Integer.valueOf(aMeta));
         return true;
      }
   }

   public static boolean registerMachineBlock(Block aBlock, boolean ... aMeta) {
      if(!GT_Utility.isBlockInvalid(aBlock) && aMeta != null && aMeta.length != 0) {
         int rMeta = 0;

         for(byte i = 0; i < aMeta.length && i < 16; ++i) {
            if(aMeta[i]) {
               rMeta |= 1 << i;
            }
         }

         sMachineIDs.put(aBlock, Integer.valueOf(rMeta));
         return true;
      } else {
         return false;
      }
   }

   public static boolean isMachineBlock(Block aBlock, int aMeta) {
      return GT_Utility.isBlockInvalid(aBlock)?false:sMachineIDs.containsKey(aBlock) && (((Integer)sMachineIDs.get(aBlock)).intValue() & 1 << aMeta) != 0;
   }

   public static Item constructCoolantCellItem(int aID, String aUnlocalized, String aEnglish, int aMaxStore) {
      try {
         return (Item)Class.forName("gregtechmod.api.items.GT_CoolantCellIC_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxStore)});
      } catch (Throwable var6) {
         try {
            return (Item)Class.forName("gregtechmod.api.items.GT_CoolantCell_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxStore)});
         } catch (Throwable var5) {
            return new GT_Generic_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", false);
         }
      }
   }

   public static Item constructElectricArmorItem(int aID, String aUnlocalized, String aEnglish, int aCharge, int aTransfer, int aTier, int aDamageEnergyCost, int aSpecials, double aArmorAbsorbtionPercentage, boolean aChargeProvider, int aType, int aArmorIndex) {
      try {
         return (Item)Class.forName("gregtechmod.api.items.GT_EnergyArmorIC_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aCharge), Integer.valueOf(aTransfer), Integer.valueOf(aTier), Integer.valueOf(aDamageEnergyCost), Integer.valueOf(aSpecials), Double.valueOf(aArmorAbsorbtionPercentage), Boolean.valueOf(aChargeProvider), Integer.valueOf(aType), Integer.valueOf(aArmorIndex)});
      } catch (Throwable var15) {
         try {
            return (Item)Class.forName("gregtechmod.api.items.GT_EnergyArmor_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aCharge), Integer.valueOf(aTransfer), Integer.valueOf(aTier), Integer.valueOf(aDamageEnergyCost), Integer.valueOf(aSpecials), Double.valueOf(aArmorAbsorbtionPercentage), Boolean.valueOf(aChargeProvider), Integer.valueOf(aType), Integer.valueOf(aArmorIndex)});
         } catch (Throwable var14) {
            return new GT_Generic_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", false);
         }
      }
   }

   public static Item constructElectricEnergyStorageItem(int aID, String aUnlocalized, String aEnglish, int aCharge, int aTransfer, int aTier, int aEmptyID, int aFullID) {
      try {
         return (Item)Class.forName("gregtechmod.api.items.GT_EnergyStoreIC_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aCharge), Integer.valueOf(aTransfer), Integer.valueOf(aTier), Integer.valueOf(aEmptyID), Integer.valueOf(aFullID)});
      } catch (Throwable var10) {
         try {
            return (Item)Class.forName("gregtechmod.api.items.GT_EnergyStore_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aCharge), Integer.valueOf(aTransfer), Integer.valueOf(aTier), Integer.valueOf(aEmptyID), Integer.valueOf(aFullID)});
         } catch (Throwable var9) {
            return new GT_Generic_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", false);
         }
      }
   }

   public static GT_Tool_Item constructHardHammerItem(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      try {
         return (GT_Tool_Item)Class.forName("gregtechmod.api.items.GT_HardHammer_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxDamage), Integer.valueOf(aEntityDamage)});
      } catch (Throwable var6) {
         return new GT_Tool_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", aMaxDamage, aEntityDamage, false);
      }
   }

   public static GT_Tool_Item constructCrowbarItem(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      try {
         return (GT_Tool_Item)Class.forName("gregtechmod.api.items.GT_CrowbarRC_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxDamage), Integer.valueOf(aEntityDamage)});
      } catch (Throwable var7) {
         try {
            return (GT_Tool_Item)Class.forName("gregtechmod.api.items.GT_Crowbar_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxDamage), Integer.valueOf(aEntityDamage)});
         } catch (Throwable var6) {
            return new GT_Tool_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", aMaxDamage, aEntityDamage, false);
         }
      }
   }

   public static GT_Tool_Item constructWrenchItem(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDisChargedGTID) {
      try {
         return (GT_Tool_Item)Class.forName("gregtechmod.api.items.GT_Wrench_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxDamage), Integer.valueOf(aEntityDamage), Integer.valueOf(aDisChargedGTID)});
      } catch (Throwable var7) {
         return new GT_Tool_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", aMaxDamage, aEntityDamage, false);
      }
   }

   public static GT_Tool_Item constructElectricScrewdriverItem(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDisChargedGTID) {
      try {
         return (GT_Tool_Item)Class.forName("gregtechmod.api.items.GT_ScrewdriverIC_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxDamage), Integer.valueOf(aEntityDamage), Integer.valueOf(aDisChargedGTID)});
      } catch (Throwable var7) {
         return new GT_Tool_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", aMaxDamage, aEntityDamage, false);
      }
   }

   public static GT_Tool_Item constructElectricWrenchItem(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDisChargedGTID) {
      try {
         return (GT_Tool_Item)Class.forName("gregtechmod.api.items.GT_WrenchIC_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxDamage), Integer.valueOf(aEntityDamage), Integer.valueOf(aDisChargedGTID)});
      } catch (Throwable var7) {
         return new GT_Tool_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", aMaxDamage, aEntityDamage, false);
      }
   }

   public static GT_Tool_Item constructElectricSawItem(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
      try {
         return (GT_Tool_Item)Class.forName("gregtechmod.api.items.GT_SawIC_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxDamage), Integer.valueOf(aEntityDamage), Integer.valueOf(aToolQuality), Float.valueOf(aToolStrength), Integer.valueOf(aEnergyConsumptionPerBlockBreak), Integer.valueOf(aDisChargedGTID)});
      } catch (Throwable var10) {
         return new GT_Tool_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", aMaxDamage, aEntityDamage, false);
      }
   }

   public static GT_Tool_Item constructElectricDrillItem(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
      try {
         return (GT_Tool_Item)Class.forName("gregtechmod.api.items.GT_DrillIC_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxDamage), Integer.valueOf(aEntityDamage), Integer.valueOf(aToolQuality), Float.valueOf(aToolStrength), Integer.valueOf(aEnergyConsumptionPerBlockBreak), Integer.valueOf(aDisChargedGTID)});
      } catch (Throwable var10) {
         return new GT_Tool_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", aMaxDamage, aEntityDamage, false);
      }
   }

   public static GT_Tool_Item constructElectricSolderingToolItem(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDisChargedGTID) {
      try {
         return (GT_Tool_Item)Class.forName("gregtechmod.api.items.GT_SolderingToolIC_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxDamage), Integer.valueOf(aEntityDamage), Integer.valueOf(aDisChargedGTID)});
      } catch (Throwable var7) {
         return new GT_Tool_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", aMaxDamage, aEntityDamage, false);
      }
   }

   public static GT_Tool_Item constructEmptyElectricToolItem(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aChargedGTID) {
      try {
         return (GT_Tool_Item)Class.forName("gregtechmod.api.items.GT_EmptyToolIC_Item").getConstructors()[0].newInstance(new Object[]{Integer.valueOf(aID), aUnlocalized, aEnglish, Integer.valueOf(aMaxDamage), Integer.valueOf(aChargedGTID)});
      } catch (Throwable var6) {
         return new GT_Tool_Item(aID, aUnlocalized, aEnglish, "Doesn\'t work as intended, this is a Bug", aMaxDamage, 0, false);
      }
   }

   public static BaseMetaTileEntity constructBaseMetaTileEntity() {
      if(sBaseMetaTileEntityClass == null) {
         try {
            if(UE_ENERGY_COMPATIBILITY && BC_ENERGY_COMPATIBILITY && IC_ENERGY_COMPATIBILITY) {
               return (BaseMetaTileEntity)(sBaseMetaTileEntityClass = Class.forName("gregtechmod.api.metatileentity.BaseMetaTileEntityICUEMJ")).newInstance();
            }
         } catch (Throwable var8) {
            ;
         }

         try {
            if(BC_ENERGY_COMPATIBILITY && IC_ENERGY_COMPATIBILITY) {
               return (BaseMetaTileEntity)(sBaseMetaTileEntityClass = Class.forName("gregtechmod.api.metatileentity.BaseMetaTileEntityICMJ")).newInstance();
            }
         } catch (Throwable var7) {
            ;
         }

         try {
            if(UE_ENERGY_COMPATIBILITY && IC_ENERGY_COMPATIBILITY) {
               return (BaseMetaTileEntity)(sBaseMetaTileEntityClass = Class.forName("gregtechmod.api.metatileentity.BaseMetaTileEntityICUE")).newInstance();
            }
         } catch (Throwable var6) {
            ;
         }

         try {
            if(IC_ENERGY_COMPATIBILITY) {
               return (BaseMetaTileEntity)(sBaseMetaTileEntityClass = Class.forName("gregtechmod.api.metatileentity.BaseMetaTileEntityIC")).newInstance();
            }
         } catch (Throwable var5) {
            ;
         }

         try {
            if(UE_ENERGY_COMPATIBILITY && BC_ENERGY_COMPATIBILITY) {
               return (BaseMetaTileEntity)(sBaseMetaTileEntityClass = Class.forName("gregtechmod.api.metatileentity.BaseMetaTileEntityUEMJ")).newInstance();
            }
         } catch (Throwable var4) {
            ;
         }

         try {
            if(UE_ENERGY_COMPATIBILITY) {
               return (BaseMetaTileEntity)(sBaseMetaTileEntityClass = Class.forName("gregtechmod.api.metatileentity.BaseMetaTileEntityUE")).newInstance();
            }
         } catch (Throwable var3) {
            ;
         }

         try {
            if(BC_ENERGY_COMPATIBILITY) {
               return (BaseMetaTileEntity)(sBaseMetaTileEntityClass = Class.forName("gregtechmod.api.metatileentity.BaseMetaTileEntityMJ")).newInstance();
            }
         } catch (Throwable var2) {
            ;
         }

         try {
            sBaseMetaTileEntityClass = BaseMetaTileEntity.class;
            return (BaseMetaTileEntity)BaseMetaTileEntity.class.newInstance();
         } catch (Throwable var9) {
            ;
         }
      }

      try {
         return (BaseMetaTileEntity)((BaseMetaTileEntity)sBaseMetaTileEntityClass.newInstance());
      } catch (Throwable var1) {
         GT_Log.err.println("GT_Mod: Fatal Error ocurred while initializing TileEntities, crashing Minecraft.");
         var1.printStackTrace(GT_Log.err);
         throw new RuntimeException(var1);
      }
   }

   public static void registerCover(ItemStack aStack, Icon aCover) {
      int tStack = GT_Utility.stackToInt(aStack);
      if(tStack != 0 && sCovers.get(Integer.valueOf(tStack)) == null) {
         sCovers.put(Integer.valueOf(tStack), aCover);
      }

   }

   public static void registerCover(ItemStack aStack, String aCover) {
      if(aCover != null && !aCover.equals("") && sBlockIcons != null) {
         try {
            registerCover(aStack, ((IconRegister)sBlockIcons).func_94245_a(aCover));
            return;
         } catch (Throwable var3) {
            ;
         }
      }

      registerCover(aStack, (Icon)null);
   }

   public static void registerCover(Collection<ItemStack> aStackList, Icon aCover) {
      Iterator i$ = aStackList.iterator();

      while(i$.hasNext()) {
         ItemStack tStack = (ItemStack)i$.next();
         registerCover(tStack, aCover);
      }

   }

   public static void registerCover(Collection<ItemStack> aStackList, String aCover) {
      Iterator i$ = aStackList.iterator();

      while(i$.hasNext()) {
         ItemStack tStack = (ItemStack)i$.next();
         registerCover(tStack, aCover);
      }

   }

   public static GT_CoverBehavior getCoverBehavior(ItemStack aStack) {
      return getCoverBehavior(GT_Utility.stackToInt(aStack));
   }

   public static GT_CoverBehavior getCoverBehavior(int aStack) {
      GT_CoverBehavior rCover = (GT_CoverBehavior)sCoverBehaviors.get(Integer.valueOf(aStack));
      return rCover == null?sGenericBehavior:rCover;
   }

   public static boolean registerWrench(ItemStack aTool) {
      return registerTool(aTool, sWrenchList);
   }

   public static boolean registerCrowbar(ItemStack aTool) {
      return registerTool(aTool, sCrowbarList);
   }

   public static boolean registerScrewdriver(ItemStack aTool) {
      return registerTool(aTool, sScrewdriverList);
   }

   public static boolean registerSoftHammer(ItemStack aTool) {
      return registerTool(aTool, sSoftHammerList);
   }

   public static boolean registerHardHammer(ItemStack aTool) {
      return registerTool(aTool, sHardHammerList);
   }

   public static boolean registerSolderingTool(ItemStack aTool) {
      return registerTool(aTool, sSolderingToolList);
   }

   public static boolean registerSolderingMetal(ItemStack aTool) {
      return registerTool(aTool, sSolderingMetalList);
   }

   public static boolean registerTool(ItemStack aTool, Collection<Integer> aToolList) {
      if(aTool != null && !GT_Utility.isItemStackInList(aTool, sToolList) && (aTool.func_77973_b().func_77645_m() || GT_ModHandler.isElectricItem(aTool))) {
         aToolList.add(Integer.valueOf(GT_Utility.stackToInt(GT_Utility.copyMetaData(32767L, new Object[]{aTool}))));
         sToolList.add(Integer.valueOf(GT_Utility.stackToInt(GT_Utility.copyMetaData(32767L, new Object[]{aTool}))));
         return true;
      } else {
         return false;
      }
   }

   static {
      sContinentalWaterList.add(BiomeGenBase.field_76781_i.field_76791_y);
      sContinentalWaterList.add(BiomeGenBase.field_76777_m.field_76791_y);
      sContinentalWaterList.add("Lake");
      sCassiteriteList.add(BiomeGenBase.field_76768_g.field_76791_y);
      sCassiteriteList.add(BiomeGenBase.field_76784_u.field_76791_y);
      sCassiteriteList.add(BiomeGenBase.field_76774_n.field_76791_y);
      sCassiteriteList.add(BiomeGenBase.field_76775_o.field_76791_y);
      sCassiteriteList.add(BiomeGenBase.field_76789_p.field_76791_y);
      sCassiteriteList.add(BiomeGenBase.field_76788_q.field_76791_y);
      sCassiteriteList.add(BiomeGenBase.field_76770_e.field_76791_y);
      sCassiteriteList.add(BiomeGenBase.field_76783_v.field_76791_y);
      sCassiteriteList.add("Alps");
      sCassiteriteList.add("Glacier");
      sCassiteriteList.add("Ice Wasteland");
      sCassiteriteList.add("Flying Mountains");
      sCassiteriteList.add("Rock Mountains");
      sCassiteriteList.add("Snow Mountains");
      sCassiteriteList.add("Snow Forest");
      sCassiteriteList.add("Tall Pine Forest");
      sCassiteriteList.add("Tundra");
      sCassiteriteList.add("Snow Island");
      sCassiteriteList.add("Rock Island");
      sCassiteriteList.add("Mountain Ridge");
      sCassiteriteList.add("Volcano Island");
      sTetrahedriteList.add(BiomeGenBase.field_76782_w.field_76791_y);
      sTetrahedriteList.add(BiomeGenBase.field_76792_x.field_76791_y);
      sTetrahedriteList.add(BiomeGenBase.field_76780_h.field_76791_y);
      sTetrahedriteList.add(BiomeGenBase.field_76789_p.field_76791_y);
      sTetrahedriteList.add(BiomeGenBase.field_76788_q.field_76791_y);
      sTetrahedriteList.add(BiomeGenBase.field_76770_e.field_76791_y);
      sTetrahedriteList.add(BiomeGenBase.field_76783_v.field_76791_y);
      sTetrahedriteList.add("Bog");
      sTetrahedriteList.add("Cliffs");
      sTetrahedriteList.add("Valley");
      sTetrahedriteList.add("Canyon");
      sTetrahedriteList.add("Flying Mountains");
      sTetrahedriteList.add("Rock Mountains");
      sTetrahedriteList.add("Jungle Island");
      sTetrahedriteList.add("Rock Island");
      sTetrahedriteList.add("Mountain Ridge");
      sTetrahedriteList.add("Volcano Island");
      sRubyList.add(BiomeGenBase.field_76769_d.field_76791_y);
      sRubyList.add(BiomeGenBase.field_76786_s.field_76791_y);
      sRubyList.add("Sahel");
      sRubyList.add("Dunes");
      sRubyList.add("Desert Mountains");
      sRubyList.add("Mountainous Desert");
      sRubyList.add("Mountain Ridge");
      sRubyList.add("Savanna");
      sRubyList.add("Savannah");
      sRubyList.add("Shrubland");
      sRubyList.add("Wasteland");
      sRubyList.add("Fire Swamp");
      sRubyList.add("Highlands");
      sRubyList.add("Volcano");
      sRubyList.add("Steppe");
      sRubyList.add("Scrubland");
      sRubyList.add("Prairie");
      sRubyList.add("Lush Desert");
      sRubyList.add("Deadlands");
      sRubyList.add("Badlands");
      sRubyList.add("Dunes");
      sRubyList.add("Mesa");
      sRubyList.add("Outback");
      sRubyList.add("Canyon");
      sRubyList.add("Desert Island");
      sRubyList.add("Volcano Island");
      sRubyList.add("Desert Oil Field");
      sSapphireList.add(BiomeGenBase.field_76771_b.field_76791_y);
      sSapphireList.add(BiomeGenBase.field_76787_r.field_76791_y);
      sSapphireList.add(BiomeGenBase.field_76776_l.field_76791_y);
      sSapphireList.add("Twilight Lake");
      sSapphireList.add("Twilight Stream");
      sSapphireList.add("Lake Border");
      sSapphireList.add("Glacier");
      sSapphireList.add("Mangrove");
      sSapphireList.add("Oasis");
      sSapphireList.add("Origin Valley");
      sSapphireList.add("Sacred Springs");
      sSapphireList.add("Tropics");
      sSapphireList.add("Tropical Islands");
      sSapphireList.add("Estuary");
      sSapphireList.add("Ocean Oil Field");
      sBauxiteList.add(BiomeGenBase.field_76772_c.field_76791_y);
      sBauxiteList.add(BiomeGenBase.field_76767_f.field_76791_y);
      sBauxiteList.add(BiomeGenBase.field_76785_t.field_76791_y);
      sBauxiteList.add("Bald Hill");
      sBauxiteList.add("Forest Island");
      sBauxiteList.add("Pinelands");
      sBauxiteList.add("Highlands");
      sBauxiteList.add("Lowlands");
      sBauxiteList.add("Rainforest");
      sBauxiteList.add("Autumn Woods");
      sBauxiteList.add("Autumn Forest");
      sBauxiteList.add("Birch Forest");
      sBauxiteList.add("Birch Hills");
      sBauxiteList.add("Forested Hills");
      sBauxiteList.add("Forested Island");
      sBauxiteList.add("Green Hills");
      sBauxiteList.add("Meadow");
      sBauxiteList.add("Redwood Forest");
      sBauxiteList.add("Redwood Lush");
      sBauxiteList.add("Woodlands");
      sBauxiteList.add("Twilight Forest");
      sBauxiteList.add("Dense Twilight Forest");
      sBauxiteList.add("Dark Forest");
      sBauxiteList.add("Enchanted Forest");
      sBauxiteList.add("Woodland");
      sBauxiteList.add("Woodlands");
      sBauxiteList.add("Woodland Mountains");
      sBauxiteList.add("Thicket");
      sBauxiteList.add("Seasonal Forest");
      sBauxiteList.add("Redwood Forest");
      sBauxiteList.add("Quagmire");
      sBauxiteList.add("Orchard");
      sBauxiteList.add("Pasture");
      sBauxiteList.add("Mystic Grove");
      sBauxiteList.add("Meadow");
      sBauxiteList.add("Marsh");
      sBauxiteList.add("Maple Woods");
      sBauxiteList.add("Grove");
      sBauxiteList.add("Grassland");
      sBauxiteList.add("Garden");
      sBauxiteList.add("Fungi Forest");
      sBauxiteList.add("Deciduous Forest");
      sBauxiteList.add("Coniferous Forest");
      sBauxiteList.add("Cherry Blossom Grove");
      sNickelList.add(BiomeGenBase.field_76770_e.field_76791_y);
      sNickelList.add(BiomeGenBase.field_76783_v.field_76791_y);
      sNickelList.add("Alps");
      sNickelList.add("Flying Mountains");
      sNickelList.add("Rock Mountains");
      sNickelList.add("Snow Mountains");
      sNickelList.add("Rock Island");
      sNickelList.add("Mountain Ridge");
      sNickelList.add("Volcano Island");
      sNickelList.add("Dark Forest");
      sNickelList.add("Mountainous Desert");
      sNickelList.add("Volcano");
      sDimensionalList.add(Integer.valueOf(-1));
      sDimensionalList.add(Integer.valueOf(0));
      sDimensionalList.add(Integer.valueOf(1));
      sSoundList.put(Integer.valueOf(0), "random.break");
      sSoundList.put(Integer.valueOf(1), "random.anvil_use");
      sSoundList.put(Integer.valueOf(2), "random.anvil_break");
      sSoundList.put(Integer.valueOf(3), "random.click");
      sSoundList.put(Integer.valueOf(4), "random.fizz");
      sSoundList.put(Integer.valueOf(5), "random.explode");
      sSoundList.put(Integer.valueOf(100), "ic2:tools.Wrench");
      sSoundList.put(Integer.valueOf(101), "ic2:tools.RubberTrampoline");
      sSoundList.put(Integer.valueOf(102), "ic2:tools.Painter");
      sSoundList.put(Integer.valueOf(103), "ic2:tools.BatteryUse");
      sSoundList.put(Integer.valueOf(104), "ic2:tools.chainsaw.ChainsawUseOne");
      sSoundList.put(Integer.valueOf(105), "ic2:tools.chainsaw.ChainsawUseTwo");
      sSoundList.put(Integer.valueOf(106), "ic2:tools.drill.DrillSoft");
      sSoundList.put(Integer.valueOf(107), "ic2:tools.drill.DrillHard");
      sSoundList.put(Integer.valueOf(108), "ic2:tools.ODScanner");
      sSoundList.put(Integer.valueOf(200), "ic2:machines.ExtractorOp");
      sSoundList.put(Integer.valueOf(201), "ic2:machines.MaceratorOp");
      sSoundList.put(Integer.valueOf(202), "ic2:machines.InductionLoop");
      sSoundList.put(Integer.valueOf(203), "ic2:machines.CompressorOp");
      sSoundList.put(Integer.valueOf(204), "ic2:machines.RecyclerOp");
      sSoundList.put(Integer.valueOf(205), "ic2:machines.MinerOp");
      sSoundList.put(Integer.valueOf(206), "ic2:machines.PumpOp");
      sSoundList.put(Integer.valueOf(207), "ic2:machines.ElectroFurnaceLoop");
      sSoundList.put(Integer.valueOf(208), "ic2:machines.InductionLoop");
      sSoundList.put(Integer.valueOf(209), "ic2:machines.MachineOverload");
      sSoundList.put(Integer.valueOf(210), "ic2:machines.InterruptOne");
      sSoundList.put(Integer.valueOf(211), "ic2:machines.KaChing");
      sSoundList.put(Integer.valueOf(212), "ic2:machines.MagnetizerLoop");
   }
}
