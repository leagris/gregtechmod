package gregtechmod.common;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

public class GT_OreDictHandler {

   public static final GT_OreDictHandler instance = new GT_OreDictHandler();
   private final HashMap<OreRegisterEvent, String> mEvents = new HashMap();
   private final List<String> mIgnoredItems = Arrays.asList(new String[]{"itemRawRubber", "itemSilicon", "itemBacon", "itemJetpackAccelerator", "itemLazurite", "itemIridium", "itemTear", "itemClaw", "itemFertilizer", "itemTar", "itemSlimeball", "itemCoke", "itemBeeswax", "itemBeeQueen", "itemForcicium", "itemForcillium", "itemRoyalJelly", "itemHoneydew", "itemHoney", "itemPollen", "itemReedTypha", "itemSulfuricAcid", "itemPotash", "itemCompressedCarbon", "itemBitumen", "itemBioFuel", "itemCokeSugar", "itemCokeCactus", "itemCharcoalSugar", "itemCharcoalCactus", "itemSludge", "itemEnrichedAlloy", "itemQuicksilver", "itemMercury", "itemOsmium", "itemUltimateCircuit", "itemEnergizedStar", "itemAntimatterMolecule", "itemAntimatterGlob", "itemCoal", "itemBoat", "itemHerbalMedicineCake", "itemCakeSponge", "itemFishandPumpkinCakeSponge", "itemSoulCleaver", "itemInstantCake", "itemWhippingCream", "itemGlisteningWhippingCream", "itemCleaver", "itemHerbalMedicineWhippingCream", "itemStrangeWhippingCream", "itemBlazeCleaver", "itemBakedCakeSponge", "itemMagmaCake", "itemGlisteningCake", "itemOgreCleaver", "itemFishandPumpkinCake", "itemMagmaWhippingCream", "itemMultimeter", "itemSuperconductor"});
   private final List<String> mIgnoredNames = Arrays.asList(new String[]{"whiteStone", "stoneSlab", "clayBowl", "clayPlate", "ceramicBowl", "ceramicPlate", "ovenRack", "clayCup", "ceramicCup", "batteryBox", "transmutationStone", "torchRedstoneActive", "coal", "charcoal", "cloth", "cobblestoneSlab", "stoneBrickSlab", "cobblestoneWall", "stoneBrickWall", "cobblestoneStair", "stoneBrickStair", "blockCloud", "blockDirt", "blockTyrian", "blockCarpet", "blockFft", "blockLavastone", "blockHolystone", "blockConcrete", "sunnariumPart", "brSmallMachineCyaniteProcessor", "meteoriteCoal", "blockCobble", "pressOreProcessor", "crusherOreProcessor", "grinderOreProcessor", "blockRubber", "blockHoney", "blockHoneydew", "blockPeat", "blockRadioactive", "blockSlime", "blockCocoa", "blockSugarCane", "blockLeather", "blockClayBrick", "solarPanelHV", "cableRedNet", "stoneBowl", "crafterWood", "taintedSoil", "brickXyEngineering", "breederUranium", "wireMill", "chunkLazurite", "aluminumNatural", "aluminiumNatural", "naturalAluminum", "naturalAluminium", "antimatterMilligram", "antimatterGram", "strangeMatter", "coalGenerator", "electricFurnace", "unfinishedTank", "valvePart", "aquaRegia", "leatherSeal", "leatherSlimeSeal", "hambone", "slimeball", "enrichedUranium", "camoPaste"});
   private final List<String> mInvalidNames = Arrays.asList(new String[]{"bloodstoneOre", "universalCable", "bronzeTube", "ironTube", "netherTube", "obbyTube", "infiniteBattery", "eliteBattery", "advancedBattery", "10kEUStore", "blueDye", "MonazitOre", "quartzCrystal", "whiteLuminiteCrystal", "darkStoneIngot", "invisiumIngot", "demoniteOrb", "enderGem", "starconiumGem", "osmoniumIngot", "tapaziteGem", "zectiumIngot", "foolsRubyGem", "rubyGem", "meteoriteGem", "adamiteShard", "sapphireGem", "copperIngot", "ironStick", "goldStick", "diamondStick", "reinforcedStick", "draconicStick", "emeraldStick", "copperStick", "tinStick", "silverStick", "bronzeStick", "steelStick", "leadStick", "manyullynStick", "arditeStick", "cobaltStick", "aluminiumStick", "alumiteStick", "oilsandsOre", "copperWire", "superconductorWire", "sulfuricAcid", "conveyorBelt", "ironWire", "aluminumWire", "aluminiumWire", "silverWire", "tinWire", "dustSiliconSmall", "AluminumOre", "plateHeavyT2", "blockWool", "alloyPlateEnergizedHardened", "gasWood", "alloyPlateEnergized", "SilverOre", "LeadOre", "TinOre", "CopperOre", "silverOre", "leadOre", "tinOre", "copperOre", "bauxiteOre", "HSLivingmetalIngot", "oilMoving", "oilStill", "oilBucket", "petroleumOre", "dieselFuel", "diamondNugget", "planks", "wood", "stick", "sticks", "naquadah", "obsidianRod", "stoneRod", "thaumiumRod", "steelRod", "netherrackRod", "woodRod", "ironRod", "cactusRod", "flintRod", "copperRod", "cobaltRod", "alumiteRod", "blueslimeRod", "arditeRod", "manyullynRod", "bronzeRod", "boneRod", "slimeRod"});
   private final List<String> mIgnoredPrefixes = Arrays.asList(new String[]{"reactor", "mffs", "projred", "ganys"});
   public final ArrayList<ItemStack> mRegisteredStacks = new ArrayList();
   private boolean mActivated = false;


   @ForgeSubscribe
   public void registerOre(OreRegisterEvent aEvent) {
      if(!GT_Mod.mDoNotInit) {
         ModContainer tContainer = Loader.instance().activeModContainer();
         String aMod = tContainer == null?"UNKNOWN_MOD_ID":tContainer.getModId();
         String aOriginalMod = aMod;
         if(GT_OreDictUnificator.isRegisteringOres()) {
            aMod = "gregtech_addon";
         } else if(aMod.equals("gregtech_addon")) {
            aMod = "UNKNOWN_MOD_ID";
         }

         if(aEvent != null && aEvent.Ore != null && aEvent.Ore.func_77973_b() != null && aEvent.Name != null && !aEvent.Name.isEmpty()) {
            try {
               if(aEvent.Ore.field_77994_a != 1) {
                  System.err.println("\nWARNING: \'" + aEvent.Name + "\' is either being misused by another Mod or has been wrongly registered, as the stackSize of the Event-Stack is not 1!!!");
               }

               aEvent.Ore.field_77994_a = 1;
               if(!aOriginalMod.toLowerCase().contains("xycraft") && !aOriginalMod.toLowerCase().contains("tconstruct") && (!aOriginalMod.toLowerCase().contains("natura") || aOriginalMod.toLowerCase().contains("natural"))) {
                  String e = aMod + " -> " + aEvent.Name;
                  if(this.mActivated || GregTech_API.sPostloadStarted || GT_Mod.sSortToTheEnd && GregTech_API.sLoadFinished) {
                     e = aOriginalMod + " --Late--> " + aEvent.Name;
                     System.err.println("\nWARNING: " + aOriginalMod + " attempted to register " + aEvent.Name + " very late at the OreDictionary! Some Functionality, such as OreDict-Unification may not work as expected! Sometimes registration in @PostInit is required, but you should always register OreDictionary Items in the @preInit Phase whenever possible.");
                  }

                  String tAssosiation = GT_OreDictUnificator.getAssociation(aEvent.Ore);
                  if(GT_Utility.isStringValid(tAssosiation) && tAssosiation.equals(aEvent.Name)) {
                     GT_Log.ore.println(e + " is ambiguous, this is an Error.");
                     System.err.println("WARNING: The OreDict-Registration of " + aEvent.Name + " by " + aOriginalMod + " is ambiguous. Please check if the Item hasn\'t already been registered under that Name, before registering it a second time!");
                  } else {
                     this.mRegisteredStacks.add(aEvent.Ore);
                     if(aEvent.Name.startsWith("item") && this.mIgnoredItems.contains(aEvent.Name)) {
                        GT_Log.ore.println(e);
                        if(aEvent.Name.equals("itemCopperWire")) {
                           GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWireCopper, aEvent.Ore);
                        }

                     } else if(this.mIgnoredNames.contains(aEvent.Name)) {
                        GT_Log.ore.println(e + " is getting ignored via hardcode.");
                     } else if(aEvent.Name.equals("stone")) {
                        GT_OreDictUnificator.registerOre("stoneSmooth", aEvent.Ore);
                     } else if(aEvent.Name.equals("cobblestone")) {
                        GT_OreDictUnificator.registerOre("stoneCobble", aEvent.Ore);
                     } else if(!aEvent.Name.contains("|") && !aEvent.Name.contains("*") && !aEvent.Name.contains(":") && !aEvent.Name.contains(".") && !aEvent.Name.contains("$")) {
                        Iterator aPrefix = this.mIgnoredPrefixes.iterator();

                        String tName;
                        while(aPrefix.hasNext()) {
                           tName = (String)aPrefix.next();
                           if(aEvent.Name.startsWith(tName)) {
                              GT_Log.ore.println(e + " is using an ignored Prefix and is therefor getting ignored via hardcode.");
                              return;
                           }
                        }

                        if(aEvent.Name.equals("copperWire")) {
                           GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWireCopper, aEvent.Ore);
                        }

                        if(aEvent.Name.equals("sheetPlastic")) {
                           GT_OreDictUnificator.registerOre(OrePrefixes.plate, Materials.Plastic, aEvent.Ore);
                        }

                        if(aEvent.Name.contains(" ")) {
                           GT_Log.ore.println(e + " is getting re-registered because the OreDict Name containing invalid spaces.");
                           System.err.println("\nWARNING: \'" + aEvent.Name + "\' is an invalid OreDictionary Name, as it contains spaces! Register it without spaces to fix that.");
                           GT_OreDictUnificator.registerOre(aEvent.Name.replaceAll(" ", ""), GT_Utility.copyAmount(1L, new Object[]{aEvent.Ore}));
                           aEvent.Ore.func_82834_c("Invalid OreDictionary Tag");
                        } else if(this.mInvalidNames.contains(aEvent.Name)) {
                           GT_Log.ore.println(e + " is wrongly registered and therefor getting ignored.");
                           System.err.println("\nWARNING: \'" + aEvent.Name + "\' is an invalid OreDictionary Name. The Name doesn\'t fit to the Type of Item and/or doesn\'t follow a proper OreDictionary Convention. If you are the Owner of the Mod who adds this Item, please do the following: ");
                           if(aEvent.Name.equals("oilsandsOre")) {
                              System.err.println("Please change it to \'sandOil\'");
                              GT_OreDictUnificator.registerOre("sandOil", aEvent.Ore);
                           } else if(aEvent.Name.equals("10kEUStore")) {
                              System.err.println("Use \'crafting10kEUStore\', you forgot to add the prefix");
                           } else if(aEvent.Name.equals("sulfuricAcid")) {
                              System.err.println("Please use \'bottleSulfuricAcid\' instead, since it is likely a vanilla bottle containing the Material \'SulfuricAcid\'");
                           } else if(aEvent.Name.equals("stick")) {
                              System.err.println("Use \'stickWood\' instead, it is already registered in vanilla-forge");
                           } else if(aEvent.Name.equals("wood")) {
                              System.err.println("Use \'logWood\' instead, it is already registered in vanilla-forge");
                           } else if(aEvent.Name.equals("plank")) {
                              System.err.println("Use \'plankWood\' instead, it is already registered in vanilla-forge");
                           } else if(aEvent.Name.endsWith("Tube")) {
                              System.err.println("Put the \'Tube\' in the beginning of the Name to get \'tube" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Tube", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Cable")) {
                              System.err.println("Put the \'Cable\' in the beginning of the Name to get \'cable" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Cable", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Battery")) {
                              System.err.println("Put the \'Battery\' in the beginning of the Name to get \'battery" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Battery", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Ingot")) {
                              System.err.println("Put the \'Ingot\' in the beginning of the Name to get \'ingot" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Ingot", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Crystal")) {
                              System.err.println("Put the \'Crystal\' in the beginning of the Name to get \'crystal" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Crystal", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Shard")) {
                              System.err.println("Put the \'Shard\' in the beginning of the Name to get \'shard" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Shard", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Rod")) {
                              System.err.println("Put the \'Rod\' in the beginning of the Name to get \'rod" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Rod", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Orb")) {
                              System.err.println("Put the \'Orb\' in the beginning of the Name to get \'orb" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Irb", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Gem")) {
                              System.err.println("Put the \'Gem\' in the beginning of the Name to get \'gem" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Gem", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Stick")) {
                              System.err.println("Put the \'Stick\' in the beginning of the Name to get \'stick" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Stick", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Plate")) {
                              System.err.println("Put the \'Plate\' in the beginning of the Name to get \'plate" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Plate", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Ore")) {
                              System.err.println("Put the \'Ore\' in the beginning of the Name to get \'ore" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Ore", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Dye")) {
                              System.err.println("Put the \'Dye\' in the beginning of the Name to get \'dye" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Dye", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Wire")) {
                              System.err.println("Put the \'Wire\' in the beginning of the Name to get \'wire" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Wire", "")) + "\'");
                           } else if(aEvent.Name.endsWith("Nugget")) {
                              System.err.println("Put the \'Nugget\' in the beginning of the Name to get \'nugget" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Nugget", "")) + "\'");
                           } else {
                              System.err.println("I don\'t know exactly what to suggest about this Name, please consult me personally at GregTech.");
                           }

                           System.err.println("Private Prefixes could also be a solution if the first Suggestion doesn\'t apply. In that case the suggestion for the name is \'" + aOriginalMod + ":" + aEvent.Name + "\' don\'t forget to insert the \':\' inbetween the Mod ID and OreDict Name, that is the most important part.");
                           System.err.println("If you are not the Owner then report it to the Owner of the Mod, which the Item belongs to.");
                        } else {
                           OrePrefixes aPrefix1 = OrePrefixes.getOrePrefix(aEvent.Name);
                           if(aPrefix1 == null) {
                              if(aEvent.Name.toLowerCase().equals(aEvent.Name)) {
                                 System.err.println("Improperly registered Ore: " + aEvent.Name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, as it is 100% lowercased!!! Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be \'" + aOriginalMod + ":" + aEvent.Name + "\' don\'t forget to insert the \':\' inbetween the Mod ID and OreDict Name, that is the most important part.");
                                 GT_Log.ore.println(e + " is invalid due to being solely lowercased.");
                                 return;
                              }

                              if(aEvent.Name.toUpperCase().equals(aEvent.Name)) {
                                 System.err.println("Improperly registered Ore: " + aEvent.Name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, as it is 100% uppercased!!! Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be \'" + aOriginalMod + ":" + aEvent.Name + "\' don\'t forget to insert the \':\' inbetween the Mod ID and OreDict Name, that is the most important part.");
                                 GT_Log.ore.println(e + " is invalid due to being solely uppercased.");
                                 return;
                              }

                              if(GT_Utility.sUpperCasedCharacters.contains(Character.valueOf(aEvent.Name.charAt(0)))) {
                                 System.err.println("Improperly registered Ore: " + aEvent.Name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, because it starts with an uppercased Letter. Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be \'" + aOriginalMod + ":" + aEvent.Name + "\' don\'t forget to insert the \':\' inbetween the Mod ID and OreDict Name, that is the most important part.");
                                 GT_Log.ore.println(e + " is invalid due to the first character being uppercased.");
                                 return;
                              }
                           } else {
                              if(aPrefix1 != aPrefix1.mPrefixInto) {
                                 tName = aEvent.Name.replaceFirst(aPrefix1.toString(), aPrefix1.mPrefixInto.toString());
                                 if(!GT_OreDictUnificator.isRegisteringOres()) {
                                    GT_Log.ore.println(e + " uses a depricated Prefix, and is getting re-registered as " + tName);
                                 }

                                 GT_OreDictUnificator.registerOre(tName, aEvent.Ore);
                                 return;
                              }

                              tName = aEvent.Name.replaceFirst(aPrefix1.toString(), "");
                              if(tName.length() > 0) {
                                 if(GT_Utility.sUpperCasedCharacters.contains(Character.valueOf(tName.charAt(0))) || GT_Utility.sNumberedCharacters.contains(Character.valueOf(tName.charAt(0))) || tName.charAt(0) == 95) {
                                    if(aPrefix1.mDontUnificateActively || GT_Utility.getBlockFromStack(aEvent.Ore) != null) {
                                       GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                                    }

                                    if(aPrefix1.mIsMaterialBased) {
                                       Materials aMaterial = Materials.get(tName);
                                       if(!aPrefix1.isIgnored(aMaterial)) {
                                          aPrefix1.add(GT_Utility.copyAmount(1L, new Object[]{aEvent.Ore}));
                                       }

                                       if(aMaterial != aMaterial.mMaterialInto) {
                                          GT_OreDictUnificator.registerOre(aPrefix1, aMaterial.mMaterialInto, aEvent.Ore);
                                          if(!GT_OreDictUnificator.isRegisteringOres()) {
                                             GT_Log.ore.println(e + " uses a deprecated Material and is getting re-registered as " + aPrefix1.get(aMaterial.mMaterialInto));
                                          }

                                          return;
                                       }

                                       if(aPrefix1.mMaterialAmount >= 0L && aPrefix1.mMaterialAmount < 3628800L || aMaterial == Materials.Stone) {
                                          GT_ModHandler.addToRecyclerBlackList(GT_Utility.copyAmount(1L, new Object[]{GT_Utility.copyAmount(1L, new Object[]{aEvent.Ore})}));
                                       }

                                       if(aMaterial == Materials._NULL) {
                                          System.out.println("Material Name: " + aEvent.Name + " !!!Unknown Material detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it\'s just an Information.");
                                          GT_Log.ore.println(e + " uses an unknown Material. Report this to GregTech.");
                                          return;
                                       }

                                       Iterator i$ = aMaterial.mOreReRegistrations.iterator();

                                       while(i$.hasNext()) {
                                          Materials tReRegisteredMaterial = (Materials)i$.next();
                                          GT_OreDictUnificator.registerOre(aPrefix1, tReRegisteredMaterial, aEvent.Ore);
                                       }

                                       aMaterial.add(GT_Utility.copyAmount(1L, new Object[]{aEvent.Ore}));
                                       label446:
                                       switch(GT_OreDictHandler.NamelessClass1622818954.$SwitchMap$gregtechmod$api$enums$OrePrefixes[aPrefix1.ordinal()]) {
                                       case 1:
                                          if(aMaterial == Materials.Basic) {
                                             GT_OreDictUnificator.registerOre("crafting10kEUStore", aEvent.Ore);
                                             GT_OreDictUnificator.registerOre("calclavia:BATTERY", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Advanced) {
                                             GT_OreDictUnificator.registerOre("crafting100kEUStore", aEvent.Ore);
                                             GT_OreDictUnificator.registerOre("calclavia:ADVANCED_BATTERY", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Lithium) {
                                             GT_OreDictUnificator.registerOre("crafting100kEUStore", aEvent.Ore);
                                             GT_OreDictUnificator.registerOre("craftingLiBattery", aEvent.Ore);
                                             GT_OreDictUnificator.registerOre("calclavia:ADVANCED_BATTERY", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Elite) {
                                             GT_OreDictUnificator.registerOre("crafting1kkEUStore", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Master) {
                                             GT_OreDictUnificator.registerOre("crafting10kkEUStore", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Ultimate) {
                                             GT_OreDictUnificator.registerOre("crafting100kkEUStore", aEvent.Ore);
                                          }
                                          break;
                                       case 2:
                                          if(aMaterial == Materials.Basic) {
                                             GT_OreDictUnificator.registerOre("craftingCircuitTier02", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Advanced) {
                                             GT_OreDictUnificator.registerOre("craftingCircuitTier04", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Data) {
                                             GT_OreDictUnificator.registerOre("craftingCircuitTier05", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Elite) {
                                             GT_OreDictUnificator.registerOre("craftingCircuitTier06", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Master) {
                                             GT_OreDictUnificator.registerOre("craftingCircuitTier07", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Ultimate) {
                                             GT_OreDictUnificator.registerOre("craftingCircuitTier08", aEvent.Ore);
                                          }
                                          break;
                                       case 3:
                                          if(aMaterial == Materials.CertusQuartz) {
                                             GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.CertusQuartz, aEvent.Ore);
                                          }
                                          break;
                                       case 4:
                                          switch(GT_OreDictHandler.NamelessClass1622818954.$SwitchMap$gregtechmod$api$enums$Materials[aMaterial.ordinal()]) {
                                          case 1:
                                          case 2:
                                             GT_OreDictUnificator.registerOre(Dyes.dyeBlue, aEvent.Ore);
                                             break label446;
                                          case 3:
                                             GT_OreDictUnificator.registerOre(Dyes.dyeCyan, aEvent.Ore);
                                             break label446;
                                          case 4:
                                             GT_OreDictUnificator.registerOre(Dyes.dyeBrown, aEvent.Ore);
                                             break label446;
                                          case 5:
                                             GT_OreDictUnificator.registerOre(OrePrefixes.crystal, Materials.CertusQuartz, aEvent.Ore);
                                          case 6:
                                          case 7:
                                          case 8:
                                             GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingQuartz, aEvent.Ore);
                                          default:
                                             break label446;
                                          }
                                       case 5:
                                          if(aMaterial.mTransparent && aMaterial.mColor != Dyes._NULL) {
                                             GT_OreDictUnificator.registerOre("craftingLense" + aMaterial.mColor.toString().replaceFirst("dye", ""), aEvent.Ore);
                                          }
                                          break;
                                       case 6:
                                          if(aMaterial == Materials.Steel || aMaterial == Materials.StainlessSteel) {
                                             GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingPlateSteel, aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Plastic) {
                                             GT_OreDictUnificator.registerOre("sheetPlastic", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Rubber) {
                                             GT_OreDictUnificator.registerOre("sheetRubber", aEvent.Ore);
                                          }
                                          break;
                                       case 7:
                                          if(aMaterial == Materials.Empty) {
                                             GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                                          }
                                          break;
                                       case 8:
                                          if(aMaterial == Materials.Steel || aMaterial == Materials.StainlessSteel) {
                                             GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingGearGTSteel, aEvent.Ore);
                                          }

                                          GT_OreDictUnificator.registerOre(OrePrefixes.gear, aMaterial, aEvent.Ore);
                                          break;
                                       case 9:
                                          if(!GT_RecipeRegistrator.sRodMaterialList.contains(aMaterial)) {
                                             GT_RecipeRegistrator.sRodMaterialList.add(aMaterial);
                                          }

                                          if(aMaterial == Materials.Wood) {
                                             GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Tin || aMaterial == Materials.Lead || aMaterial == Materials.SolderingAlloy) {
                                             GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolSolderingMetal, aEvent.Ore);
                                          }
                                          break;
                                       case 10:
                                          if(aMaterial == Materials.Wood) {
                                             GT_OreDictUnificator.registerOre("pulpWood", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Lapis) {
                                             GT_OreDictUnificator.registerOre(Dyes.dyeBlue, aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Lazurite) {
                                             GT_OreDictUnificator.registerOre(Dyes.dyeCyan, aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Sodalite) {
                                             GT_OreDictUnificator.registerOre(Dyes.dyeBlue, aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.YellowLimonite) {
                                             GT_OreDictUnificator.registerOre(Dyes.dyeYellow, aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.BrownLimonite) {
                                             GT_OreDictUnificator.registerOre(Dyes.dyeBrown, aEvent.Ore);
                                          }
                                          break;
                                       case 11:
                                          if(aMaterial == Materials.Rubber) {
                                             GT_OreDictUnificator.registerOre("itemRubber", aEvent.Ore);
                                          }

                                          if(aMaterial == Materials.Brass && aEvent.Ore.func_77960_j() == 2 && aEvent.Ore.func_77977_a().equals("item.ingotBrass") && (new ItemStack(aEvent.Ore.func_77973_b(), 1, 0)).func_77977_a().contains("red")) {
                                             GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.RedAlloy, new ItemStack(aEvent.Ore.func_77973_b(), 1, 0));
                                             GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.BlueAlloy, new ItemStack(aEvent.Ore.func_77973_b(), 1, 1));
                                             GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Brass, new ItemStack(aEvent.Ore.func_77973_b(), 1, 2));
                                             GregTech_API.sRecipeAdder.addWiremillRecipe(GT_ModHandler.getIC2Item("copperCableItem", 3L), new ItemStack(aEvent.Ore.func_77973_b(), 1, 8), 400, 1);
                                             GregTech_API.sRecipeAdder.addWiremillRecipe(GT_ModHandler.getIC2Item("ironCableItem", 6L), new ItemStack(aEvent.Ore.func_77973_b(), 1, 9), 400, 2);
                                             GregTech_API.sRecipeAdder.addCutterRecipe(new ItemStack(aEvent.Ore.func_77973_b(), 1, 3), new ItemStack(aEvent.Ore.func_77973_b(), 16, 4), 400, 8);
                                          }
                                       }

                                       if(aPrefix1.mIsUnificatable && !aMaterial.mUnificatable) {
                                          return;
                                       }
                                    } else {
                                       aPrefix1.add(GT_Utility.copyAmount(1L, new Object[]{aEvent.Ore}));
                                    }
                                 }
                              } else {
                                 if(!aPrefix1.mIsSelfReferencing) {
                                    System.err.println("\nWARNING: \'" + aEvent.Name + "\' is an OreDictionary Name which may cause Problems, due to being a Prefix, please use another one.");
                                    System.err.println("Private Prefixes are a solution. Please use \'" + aOriginalMod + ":" + aEvent.Name + "\' don\'t forget to insert the \':\' inbetween the Mod ID and OreDict Name, that is the most important part.");
                                    GT_Log.ore.println(e + " uses a Prefix as full OreDict Name, and is therefor invalid.");
                                    aEvent.Ore.func_82834_c("Invalid OreDictionary Tag");
                                    return;
                                 }

                                 aPrefix1.add(GT_Utility.copyAmount(1L, new Object[]{aEvent.Ore}));
                              }

                              switch(GT_OreDictHandler.NamelessClass1622818954.$SwitchMap$gregtechmod$api$enums$OrePrefixes[aPrefix1.ordinal()]) {
                              case 12:
                                 GT_OreDictUnificator.registerOre("stone", aEvent.Ore);
                                 break;
                              case 13:
                                 GT_OreDictUnificator.registerOre("cobblestone", aEvent.Ore);
                                 break;
                              case 14:
                                 if(tName.equals("sheetPlastic")) {
                                    GT_OreDictUnificator.registerOre(OrePrefixes.plate, Materials.Plastic, aEvent.Ore);
                                 }
                                 break;
                              case 15:
                                 if(tName.equals("ToolSolderingMetal")) {
                                    GregTech_API.registerSolderingMetal(aEvent.Ore);
                                 }

                                 if(tName.equals("IndustrialDiamond")) {
                                    GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                                 }

                                 if(tName.equals("RawMachineTier01")) {
                                    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRawMachineTier00, aEvent.Ore);
                                 }

                                 if(tName.equals("CircuitTier02")) {
                                    GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Basic, aEvent.Ore);
                                 }

                                 if(tName.equals("CircuitTier04")) {
                                    GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Advanced, aEvent.Ore);
                                 }

                                 if(tName.equals("CircuitTier05")) {
                                    GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Data, aEvent.Ore);
                                 }

                                 if(tName.equals("CircuitTier06")) {
                                    GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Elite, aEvent.Ore);
                                 }

                                 if(tName.equals("CircuitTier07")) {
                                    GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Master, aEvent.Ore);
                                 }

                                 if(tName.equals("CircuitTier08")) {
                                    GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Ultimate, aEvent.Ore);
                                 }

                                 if(tName.equals("WireCopper")) {
                                    GT_OreDictUnificator.registerOre(OrePrefixes.wire, Materials.Copper, aEvent.Ore);
                                 }
                                 break;
                              case 16:
                                 if(tName.equals("Rubber")) {
                                    GT_OreDictUnificator.registerOre("logRubber", aEvent.Ore);
                                 }
                                 break;
                              case 17:
                                 if(tName.equals("Rubber")) {
                                    GT_OreDictUnificator.registerOre(OrePrefixes.ingot, Materials.Rubber, aEvent.Ore);
                                 }
                              }
                           }

                           GT_Log.ore.println(e);
                           this.mEvents.put(aEvent, aOriginalMod);
                           if(this.mActivated) {
                              this.registerRecipes(aEvent, aOriginalMod);
                           }

                        }
                     } else {
                        GT_Log.ore.println(e + " is using a private Prefix and is therefor getting ignored properly.");
                     }
                  }
               } else {
                  if(GregTech_API.DEBUG_MODE) {
                     GT_Log.ore.println(aMod + " -> " + aEvent.Name + " is getting ignored, because of racism. :P");
                  }

               }
            } catch (Throwable var12) {
               var12.printStackTrace(GT_Log.err);
            }
         } else {
            GT_Log.ore.println(aMod + " did something very bad! The registration is too invalid to even be shown properly. This happens only if you register null, invalid Items, empty Strings or even nonexisting Events to the OreDict.");
            System.err.println("CRITICAL-ERROR: The OreDict-Registration of an Item by " + aMod + " is too invalid to even be shown properly. This happens only if you register null, invalid Items, empty Strings or even nonexisting Events to the OreDict.");
         }
      }
   }

   public void registerHandler() {
      MinecraftForge.EVENT_BUS.register(this);
      String[] arr$ = OreDictionary.getOreNames();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String tOreName = arr$[i$];
         Iterator i$1 = OreDictionary.getOres(tOreName).iterator();

         while(i$1.hasNext()) {
            ItemStack tOreStack = (ItemStack)i$1.next();
            this.registerOre(new OreRegisterEvent(tOreName, tOreStack));
         }
      }

   }

   public void registerUnificationEntries() {
      GregTech_API.sUnification.mConfig.save();
      GregTech_API.sUnification.mConfig.load();
      Iterator i$ = this.mEvents.entrySet().iterator();

      Entry tEvent;
      OrePrefixes tPrefix;
      while(i$.hasNext()) {
         tEvent = (Entry)i$.next();
         if(!(((OreRegisterEvent)tEvent.getKey()).Ore.func_77973_b() instanceof GT_MetaGenerated_Item)) {
            tPrefix = OrePrefixes.getOrePrefix(((OreRegisterEvent)tEvent.getKey()).Name);
            if(tPrefix != null && tPrefix.mIsUnificatable) {
               GT_OreDictUnificator.addAssociation(((OreRegisterEvent)tEvent.getKey()).Name, ((OreRegisterEvent)tEvent.getKey()).Ore);
               if(!GT_OreDictUnificator.isBlacklisted(((OreRegisterEvent)tEvent.getKey()).Ore)) {
                  if(!((String)tEvent.getValue()).equals("UNKNOWN_MOD_ID") && GregTech_API.sUnification.get(GT_ConfigCategories.specialunificationtargets + "." + (String)tEvent.getValue(), ((OreRegisterEvent)tEvent.getKey()).Name, false)) {
                     GT_OreDictUnificator.set(((OreRegisterEvent)tEvent.getKey()).Name, ((OreRegisterEvent)tEvent.getKey()).Ore, true, true);
                  } else {
                     GT_OreDictUnificator.set(((OreRegisterEvent)tEvent.getKey()).Name, ((OreRegisterEvent)tEvent.getKey()).Ore, false, true);
                  }
               }
            }
         }
      }

      i$ = this.mEvents.entrySet().iterator();

      while(i$.hasNext()) {
         tEvent = (Entry)i$.next();
         if(((OreRegisterEvent)tEvent.getKey()).Ore.func_77973_b() instanceof GT_MetaGenerated_Item) {
            tPrefix = OrePrefixes.getOrePrefix(((OreRegisterEvent)tEvent.getKey()).Name);
            if(tPrefix != null && tPrefix.mIsUnificatable) {
               GT_OreDictUnificator.addAssociation(((OreRegisterEvent)tEvent.getKey()).Name, ((OreRegisterEvent)tEvent.getKey()).Ore);
               if(!GT_OreDictUnificator.isBlacklisted(((OreRegisterEvent)tEvent.getKey()).Ore)) {
                  if(!((String)tEvent.getValue()).equals("UNKNOWN_MOD_ID") && GregTech_API.sUnification.get(GT_ConfigCategories.specialunificationtargets + "." + (String)tEvent.getValue(), ((OreRegisterEvent)tEvent.getKey()).Name, false)) {
                     GT_OreDictUnificator.set(((OreRegisterEvent)tEvent.getKey()).Name, ((OreRegisterEvent)tEvent.getKey()).Ore, true, true);
                  } else {
                     GT_OreDictUnificator.set(((OreRegisterEvent)tEvent.getKey()).Name, ((OreRegisterEvent)tEvent.getKey()).Ore, false, true);
                  }
               }
            }
         }
      }

      GregTech_API.sUnification.mConfig.save();
      GT_Recipe.reInit();
   }

   public void activateHandler() {
      this.mActivated = true;
      Iterator i$ = this.mEvents.entrySet().iterator();

      while(i$.hasNext()) {
         Entry tEvent = (Entry)i$.next();
         this.registerRecipes((OreRegisterEvent)tEvent.getKey(), (String)tEvent.getValue());
      }

   }

   public void registerRecipes(OreRegisterEvent aEvent, String aMod) {
      if(aEvent.Ore != null && aEvent.Ore.func_77973_b() != null) {
         if(aEvent.Ore.field_77994_a != 1) {
            aEvent.Ore.field_77994_a = 1;
            System.err.println("\nWARNING: \'" + aEvent.Name + "\' is either being misused by another Mod or has been wrongly registered, as the stackSize of the Event-Stack is not 1.");
         }

         OrePrefixes aPrefix = OrePrefixes.getOrePrefix(aEvent.Name);
         Materials aMaterial = aPrefix == null?Materials._NULL:OrePrefixes.getRealMaterial(aEvent.Name, aPrefix);
         if(aPrefix != null) {
            if(!aPrefix.isIgnored(aMaterial)) {
               aPrefix.processOre(aMaterial, aEvent.Name, aMod, GT_Utility.copyAmount(1L, new Object[]{aEvent.Ore}));
            }
         } else {
            System.out.println("Thingy Name: " + aEvent.Name + " !!!Unknown \'Thingy\' detected!!! This Object seems to probably not follow a valid OreDictionary Convention, or I missed a Convention. Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it\'s just an Information.");
         }

      }
   }


   // $FF: synthetic class
   static class NamelessClass1622818954 {

      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$Materials;
      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$OrePrefixes = new int[OrePrefixes.values().length];


      static {
         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.battery.ordinal()] = 1;
         } catch (NoSuchFieldError var25) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.circuit.ordinal()] = 2;
         } catch (NoSuchFieldError var24) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.crystal.ordinal()] = 3;
         } catch (NoSuchFieldError var23) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.gem.ordinal()] = 4;
         } catch (NoSuchFieldError var22) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.lense.ordinal()] = 5;
         } catch (NoSuchFieldError var21) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.plate.ordinal()] = 6;
         } catch (NoSuchFieldError var20) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.cell.ordinal()] = 7;
         } catch (NoSuchFieldError var19) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.gearGt.ordinal()] = 8;
         } catch (NoSuchFieldError var18) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.stick.ordinal()] = 9;
         } catch (NoSuchFieldError var17) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.dust.ordinal()] = 10;
         } catch (NoSuchFieldError var16) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.ingot.ordinal()] = 11;
         } catch (NoSuchFieldError var15) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.stoneSmooth.ordinal()] = 12;
         } catch (NoSuchFieldError var14) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.stoneCobble.ordinal()] = 13;
         } catch (NoSuchFieldError var13) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.sheet.ordinal()] = 14;
         } catch (NoSuchFieldError var12) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.crafting.ordinal()] = 15;
         } catch (NoSuchFieldError var11) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.wood.ordinal()] = 16;
         } catch (NoSuchFieldError var10) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.item.ordinal()] = 17;
         } catch (NoSuchFieldError var9) {
            ;
         }

         $SwitchMap$gregtechmod$api$enums$Materials = new int[Materials.values().length];

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Lapis.ordinal()] = 1;
         } catch (NoSuchFieldError var8) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Sodalite.ordinal()] = 2;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Lazurite.ordinal()] = 3;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Chocolate.ordinal()] = 4;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.CertusQuartz.ordinal()] = 5;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Quartz.ordinal()] = 6;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Quartzite.ordinal()] = 7;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.NetherQuartz.ordinal()] = 8;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
