package gregtechmod.api.enums;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.item.ItemStack;

public enum OrePrefixes {

   @Deprecated
   pulp("pulp", 0, "", "", false, false, false, false, false, -1L),
   @Deprecated
   leaves("leaves", 1, "", "", false, false, false, false, false, -1L),
   @Deprecated
   sapling("sapling", 2, "", "", false, false, false, false, false, -1L),
   @Deprecated
   itemDust("itemDust", 3, "", "", false, false, false, false, false, -1L),
   oreNether("oreNether", 4, "Nether ", " Ore", true, true, false, false, false, -1L),
   oreDense("oreDense", 5, "Dense ", " Ore", true, true, false, false, false, -1L),
   oreEnd("oreEnd", 6, "End ", " Ore", true, true, false, false, false, -1L),
   @Deprecated
   oreGem("oreGem", 7, "", "", false, false, false, false, false, -1L),
   ore("ore", 8, "", " Ore", true, true, false, false, false, -1L),
   crushedCentrifuged("crushedCentrifuged", 9, "Centrifuged ", " Ore", true, true, false, false, false, -1L),
   crushedPurified("crushedPurified", 10, "Purified ", " Ore", true, true, false, false, false, -1L),
   crushed("crushed", 11, "Crushed ", " Ore", true, true, false, false, false, -1L),
   ingotQuintuple("ingotQuintuple", 12, "Quintuple ", " Ingot", true, true, false, false, false, 18144000L),
   ingotQuadruple("ingotQuadruple", 13, "Quadruple ", " Ingot", true, true, false, false, false, 14515200L),
   @Deprecated
   ingotQuad("ingotQuad", 14, "", "", false, false, false, false, false, -1L),
   ingotTriple("ingotTriple", 15, "Triple ", " Ingot", true, true, false, false, false, 10886400L),
   ingotDouble("ingotDouble", 16, "Double ", " Ingot", true, true, false, false, false, 7257600L),
   ingotHot("ingotHot", 17, "Hot ", " Ingot", true, true, false, false, false, 3628800L),
   ingot("ingot", 18, "", " Ingot", true, true, false, false, false, 3628800L),
   gem("gem", 19, "", "", true, true, false, false, false, 3628800L),
   @Deprecated
   dustDirty("dustDirty", 20, "", "", false, false, false, false, false, -1L),
   dustTiny("dustTiny", 21, "Tiny Pile of ", " Dust", true, true, false, false, false, 403200L),
   dustSmall("dustSmall", 22, "Small Pile of ", " Dust", true, true, false, false, false, 907200L),
   dustImpure("dustImpure", 23, "Impure Pile of ", " Dust", true, true, false, false, false, 3628800L),
   dustRefined("dustRefined", 24, "Refined Pile of ", " Dust", true, true, false, false, false, 3628800L),
   dustPure("dustPure", 25, "Purified Pile of ", " Dust", true, true, false, false, false, 3628800L),
   dust("dust", 26, "", " Dust", true, true, false, false, false, 3628800L),
   nugget("nugget", 27, "", " Nugget", true, true, false, false, false, 403200L),
   plateAlloy("plateAlloy", 28, "", "", true, false, false, false, false, -1L),
   plateDense("plateDense", 29, "Dense ", " Plate", true, true, false, false, false, 32659200L),
   plateQuintuple("plateQuintuple", 30, "Quintuple ", " Plate", true, true, false, false, false, 18144000L),
   plateQuadruple("plateQuadruple", 31, "Quadruple ", " Plate", true, true, false, false, false, 14515200L),
   @Deprecated
   plateQuad("plateQuad", 32, "", "", false, false, false, false, false, -1L),
   plateTriple("plateTriple", 33, "Triple ", " Plate", true, true, false, false, false, 10886400L),
   plateDouble("plateDouble", 34, "Double ", " Plate", true, true, false, false, false, 7257600L),
   plate("plate", 35, "", " Plate", true, true, false, false, false, 3628800L),
   block("block", 36, "Block of ", "", true, true, false, false, false, 32659200L),
   stick("stick", 37, "", " Rod", true, true, false, false, false, 1814400L),
   lense("lense", 38, "", " Lens", true, true, false, false, false, 2721600L),
   round("round", 39, "", " Round", true, true, false, false, false, 403200L),
   bolt("bolt", 40, "", " Bolt", true, true, false, false, false, 453600L),
   screw("screw", 41, "", " Screw", true, true, false, false, false, 453600L),
   ring("ring", 42, "", " Ring", true, true, false, false, false, 907200L),
   cellPlasma("cellPlasma", 43, "", " Plasma Cell", true, true, true, true, false, 3628800L),
   cell("cell", 44, "", " Cell", true, true, true, true, false, 3628800L),
   bucket("bucket", 45, "", " Bucket", true, true, true, true, false, 3628800L),
   bottle("bottle", 46, "", " Bottle", true, true, true, true, false, -1L),
   capsule("capsule", 47, "", " Capsule", false, true, true, true, false, 3628800L),
   crystal("crystal", 48, "", " Crystal", false, true, false, false, false, 3628800L),
   craftingTool("craftingTool", 49, "", "", false, false, false, false, false, -1L),
   crafting("crafting", 50, "", "", false, false, false, false, false, -1L),
   craft("craft", 51, "", "", false, false, false, false, false, -1L),
   log("log", 52, "", "", false, false, false, false, false, -1L),
   slab("slab", 53, "", "", false, false, false, false, false, -1L),
   stair("stair", 54, "", "", false, false, false, false, false, -1L),
   plank("plank", 55, "", "", false, false, false, false, false, -1L),
   treeSapling("treeSapling", 56, "", "", false, false, true, false, false, -1L),
   treeLeaves("treeLeaves", 57, "", "", false, false, true, false, false, -1L),
   tree("tree", 58, "", "", false, false, false, false, false, -1L),
   stoneCobble("stoneCobble", 59, "", "", false, false, true, false, false, -1L),
   stoneSmooth("stoneSmooth", 60, "", "", false, false, true, false, false, -1L),
   stoneMossyBricks("stoneMossyBricks", 61, "", "", false, false, true, false, false, -1L),
   stoneMossy("stoneMossy", 62, "", "", false, false, true, false, false, -1L),
   @Deprecated
   stoneBricksMossy("stoneBricksMossy", 63, "", "", false, false, false, false, false, -1L),
   stoneBricks("stoneBricks", 64, "", "", false, false, true, false, false, -1L),
   @Deprecated
   stoneBrick("stoneBrick", 65, "", "", false, false, false, false, false, -1L),
   stoneCracked("stoneCracked", 66, "", "", false, false, true, false, false, -1L),
   stoneChiseled("stoneChiseled", 67, "", "", false, false, true, false, false, -1L),
   stone("stone", 68, "", "", false, true, true, false, false, -1L),
   cobblestone("cobblestone", 69, "", "", false, true, true, false, false, -1L),
   glass("glass", 70, "", "", false, false, true, false, false, -1L),
   record("record", 71, "", "", false, false, true, false, false, -1L),
   rubble("rubble", 72, "", "", true, true, true, false, false, -1L),
   scraps("scraps", 73, "", "", true, true, false, false, false, -1L),
   scrap("scrap", 74, "", "", false, false, false, false, false, -1L),
   item("item", 75, "", "", false, false, false, false, false, -1L),
   book("book", 76, "", "", false, false, false, false, false, -1L),
   paper("paper", 77, "", "", false, false, false, false, false, -1L),
   dye("dye", 78, "", "", false, false, false, false, false, -1L),
   armorHelmet("armorHelmet", 79, "", "", false, true, false, false, false, 18144000L),
   armorChestplate("armorChestplate", 80, "", "", false, true, false, false, false, 29030400L),
   armorLeggins("armorLeggins", 81, "", "", false, true, false, false, false, 25401600L),
   armorBoots("armorBoots", 82, "", "", false, true, false, false, false, 14515200L),
   armor("armor", 83, "", "", false, false, false, false, false, -1L),
   toolHeadSword("toolHeadSword", 84, "", " Sword Blade", true, true, false, false, false, 7257600L),
   toolHeadPickaxe("toolHeadPickaxe", 85, "", " Pickaxe Head", true, true, false, false, false, 10886400L),
   toolHeadShovel("toolHeadShovel", 86, "", " Shovel Head", true, true, false, false, false, 3628800L),
   toolHeadAxe("toolHeadAxe", 87, "", " Axe Head", true, true, false, false, false, 10886400L),
   toolHeadHoe("toolHeadHoe", 88, "", " Hoe Head", true, true, false, false, false, 7257600L),
   toolHeadFile("toolHeadFile", 89, "", " File Head", true, true, false, false, false, 7257600L),
   toolHeadHammer("toolHeadHammer", 90, "", " Hammer Head", true, true, false, false, false, 21772800L),
   toolHeadSaw("toolHeadSaw", 91, "", " Saw Blade", true, true, false, false, false, 7257600L),
   toolHeadScrewdriver("toolHeadScrewdriver", 92, "", " Screwdriver Tip", true, true, false, false, false, 3628800L),
   toolSword("toolSword", 93, "", "", false, true, false, false, false, 7257600L),
   toolPickaxe("toolPickaxe", 94, "", "", false, true, false, false, false, 10886400L),
   toolShovel("toolShovel", 95, "", "", false, true, false, false, false, 3628800L),
   toolAxe("toolAxe", 96, "", "", false, true, false, false, false, 10886400L),
   toolHoe("toolHoe", 97, "", "", false, true, false, false, false, 7257600L),
   toolShears("toolShears", 98, "", "", false, true, false, false, false, 7257600L),
   tool("tool", 99, "", "", false, false, false, false, false, -1L),
   pipeTiny("pipeTiny", 100, "Tiny ", " Pipe", true, true, false, false, true, 1814400L),
   pipeSmall("pipeSmall", 101, "Small ", " Pipe", true, true, false, false, true, 3628800L),
   pipeMedium("pipeMedium", 102, "Medium ", " Pipe", true, true, false, false, true, 10886400L),
   pipeLarge("pipeLarge", 103, "Large ", " Pipe", true, true, false, false, true, 21772800L),
   pipeHuge("pipeHuge", 104, "Huge ", " Pipe", true, true, false, false, true, 43545600L),
   pipe("pipe", 105, "", " Pipe", false, false, false, false, false, -1L),
   gearGt("gearGt", 106, "", " Gear", true, true, false, false, false, 14515200L),
   batterySingleuse("batterySingleuse", 107, "", "", false, true, false, false, false, -1L),
   battery("battery", 108, "", "", false, true, false, false, false, -1L),
   circuitBoard("circuitBoard", 109, "", "", true, true, false, false, false, -1L),
   circuitPart("circuitPart", 110, "", "", true, true, false, false, false, -1L),
   circuit("circuit", 111, "", "", true, true, false, false, false, -1L),
   computer("computer", 112, "", "", true, true, false, false, true, -1L),
   cluster("cluster", 113, "", "", false, false, false, false, false, -1L),
   grafter("grafter", 114, "", "", false, false, false, false, false, -1L),
   scoop("scoop", 115, "", "", false, false, false, false, false, -1L),
   frame("frame", 116, "", "", false, false, false, false, false, -1L),
   tome("tome", 117, "", "", false, false, false, false, false, -1L),
   junk("junk", 118, "", "", false, false, false, false, false, -1L),
   bee("bee", 119, "", "", false, false, false, false, false, -1L),
   rod("rod", 120, "", "", false, false, false, false, false, -1L),
   dirt("dirt", 121, "", "", false, false, false, false, false, -1L),
   sand("sand", 122, "", "", false, false, false, false, false, -1L),
   grass("grass", 123, "", "", false, false, false, false, false, -1L),
   gravel("gravel", 124, "", "", false, false, false, false, false, -1L),
   mushroom("mushroom", 125, "", "", false, false, false, false, false, -1L),
   wood("wood", 126, "", "", false, false, false, false, false, -1L),
   drop("drop", 127, "", "", false, false, false, false, false, -1L),
   fuel("fuel", 128, "", "", false, false, false, false, false, -1L),
   panel("panel", 129, "", "", false, false, false, false, false, -1L),
   brick("brick", 130, "", "", false, false, false, false, false, -1L),
   chunk("chunk", 131, "", "", false, false, false, false, false, -1L),
   wire("wire", 132, "", "", false, false, false, false, false, -1L),
   seed("seed", 133, "", "", false, false, false, false, false, -1L),
   reed("reed", 134, "", "", false, false, false, false, false, -1L),
   sheet("sheet", 135, "", "", false, false, false, false, false, -1L),
   crop("crop", 136, "", "", false, false, false, false, false, -1L),
   plant("plant", 137, "", "", false, false, false, false, false, -1L),
   coin("coin", 138, "", "", false, false, false, false, false, -1L),
   lumar("lumar", 139, "", "", false, false, false, false, false, -1L),
   ground("ground", 140, "", "", false, false, false, false, false, -1L),
   cable("cable", 141, "", "", false, false, false, false, false, -1L),
   reduced("reduced", 142, "", "", false, false, false, false, false, -1L),
   component("component", 143, "", "", false, false, false, false, false, -1L),
   crystalline("crystalline", 144, "", "", false, false, false, false, false, -1L),
   cleanGravel("cleanGravel", 145, "", "", false, false, false, false, false, -1L),
   dirtyGravel("dirtyGravel", 146, "", "", false, false, false, false, false, -1L),
   wax("wax", 147, "", "", false, false, false, false, false, -1L),
   wall("wall", 148, "", "", false, false, false, false, false, -1L),
   tube("tube", 149, "", "", false, false, false, false, false, -1L),
   list("list", 150, "", "", false, false, false, false, false, -1L),
   food("food", 151, "", "", false, false, false, false, false, -1L),
   gear("gear", 152, "", "", false, false, false, false, false, -1L),
   coral("coral", 153, "", "", false, false, false, false, false, -1L),
   shard("shard", 154, "", "", false, false, false, false, false, -1L),
   clump("clump", 155, "", "", false, false, false, false, false, -1L),
   flower("flower", 156, "", "", false, false, false, false, false, -1L),
   storage("storage", 157, "", "", false, false, false, false, false, -1L),
   material("material", 158, "", "", false, false, false, false, false, -1L),
   plasma("plasma", 159, "", "", false, false, false, false, false, -1L),
   element("element", 160, "", "", false, false, false, false, false, -1L),
   molecule("molecule", 161, "", "", false, false, false, false, false, -1L),
   wafer("wafer", 162, "", "", false, false, false, false, false, -1L),
   compressed("compressed", 163, "", "", false, false, false, false, false, -1L),
   fertilizer("fertilizer", 164, "", "", false, false, false, false, false, -1L),
   chest("chest", 165, "", "", false, false, false, false, false, -1L),
   raw("raw", 166, "", "", false, false, false, false, false, -1L);
   public final ArrayList<ItemStack> mPrefixedItems = new ArrayList();
   public final String mLocalizedMaterialPre;
   public final String mLocalizedMaterialPost;
   public final boolean mIsUnificatable;
   public final boolean mIsMaterialBased;
   public final boolean mIsSelfReferencing;
   public final boolean mIsContainer;
   public final boolean mDontUnificateActively;
   public OrePrefixes mPrefixInto = this;
   private final ArrayList<Materials> mNotGeneratedItems = new ArrayList();
   private final ArrayList<Materials> mIgnoredMaterials = new ArrayList();
   private final ArrayList<IOreRecipeRegistrator> mOreProcessing = new ArrayList();
   public final long mMaterialAmount;
   public static volatile int VERSION;
   // $FF: synthetic field
   private static final OrePrefixes[] $VALUES = new OrePrefixes[]{pulp, leaves, sapling, itemDust, oreNether, oreDense, oreEnd, oreGem, ore, crushedCentrifuged, crushedPurified, crushed, ingotQuintuple, ingotQuadruple, ingotQuad, ingotTriple, ingotDouble, ingotHot, ingot, gem, dustDirty, dustTiny, dustSmall, dustImpure, dustRefined, dustPure, dust, nugget, plateAlloy, plateDense, plateQuintuple, plateQuadruple, plateQuad, plateTriple, plateDouble, plate, block, stick, lense, round, bolt, screw, ring, cellPlasma, cell, bucket, bottle, capsule, crystal, craftingTool, crafting, craft, log, slab, stair, plank, treeSapling, treeLeaves, tree, stoneCobble, stoneSmooth, stoneMossyBricks, stoneMossy, stoneBricksMossy, stoneBricks, stoneBrick, stoneCracked, stoneChiseled, stone, cobblestone, glass, record, rubble, scraps, scrap, item, book, paper, dye, armorHelmet, armorChestplate, armorLeggins, armorBoots, armor, toolHeadSword, toolHeadPickaxe, toolHeadShovel, toolHeadAxe, toolHeadHoe, toolHeadFile, toolHeadHammer, toolHeadSaw, toolHeadScrewdriver, toolSword, toolPickaxe, toolShovel, toolAxe, toolHoe, toolShears, tool, pipeTiny, pipeSmall, pipeMedium, pipeLarge, pipeHuge, pipe, gearGt, batterySingleuse, battery, circuitBoard, circuitPart, circuit, computer, cluster, grafter, scoop, frame, tome, junk, bee, rod, dirt, sand, grass, gravel, mushroom, wood, drop, fuel, panel, brick, chunk, wire, seed, reed, sheet, crop, plant, coin, lumar, ground, cable, reduced, component, crystalline, cleanGravel, dirtyGravel, wax, wall, tube, list, food, gear, coral, shard, clump, flower, storage, material, plasma, element, molecule, wafer, compressed, fertilizer, chest, raw};


   public boolean add(ItemStack aStack) {
      if(aStack == null) {
         return false;
      } else {
         if(!this.contains(aStack)) {
            this.mPrefixedItems.add(aStack);
         }

         while(this.mPrefixedItems.contains((Object)null)) {
            this.mPrefixedItems.remove((Object)null);
         }

         return true;
      }
   }

   public boolean contains(ItemStack aStack) {
      if(aStack == null) {
         return false;
      } else {
         Iterator i$ = this.mPrefixedItems.iterator();

         ItemStack tStack;
         do {
            if(!i$.hasNext()) {
               return false;
            }

            tStack = (ItemStack)i$.next();
         } while(!GT_Utility.areStacksEqual(aStack, tStack, !tStack.func_77942_o()));

         return true;
      }
   }

   public boolean dontGenerateItem(Materials aMaterial) {
      return this.mNotGeneratedItems.contains(aMaterial);
   }

   public boolean ignoreMaterials(Materials ... aMaterials) {
      Materials[] arr$ = aMaterials;
      int len$ = aMaterials.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Materials tMaterial = arr$[i$];
         this.mIgnoredMaterials.add(tMaterial);
      }

      return true;
   }

   public boolean isIgnored(Materials aMaterial) {
      return this.mIgnoredMaterials.contains(aMaterial);
   }

   public boolean add(IOreRecipeRegistrator aRegistrator) {
      return aRegistrator == null?false:this.mOreProcessing.add(aRegistrator);
   }

   public void processOre(Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aMaterial != null && (aMaterial != Materials._NULL || this.mIsSelfReferencing || !this.mIsMaterialBased) && GT_Utility.isStackValid(aStack)) {
         Iterator i$ = this.mOreProcessing.iterator();

         while(i$.hasNext()) {
            IOreRecipeRegistrator tRegistrator = (IOreRecipeRegistrator)i$.next();
            tRegistrator.registerOre(this, aMaterial, aOreDictName, aModName, GT_Utility.copyAmount(1L, new Object[]{aStack}));
         }
      }

   }

   private OrePrefixes(String var1, int var2, String aLocalizedMaterialPre, String aLocalizedMaterialPost, boolean aIsUnificatable, boolean aIsMaterialBased, boolean aIsSelfReferencing, boolean aIsContainer, boolean aDontUnificateActively, long aMaterialAmount) {
      this.mIsUnificatable = aIsUnificatable;
      this.mIsMaterialBased = aIsMaterialBased;
      this.mIsSelfReferencing = aIsSelfReferencing;
      this.mIsContainer = aIsContainer;
      this.mDontUnificateActively = aDontUnificateActively;
      this.mMaterialAmount = aMaterialAmount;
      this.mLocalizedMaterialPre = aLocalizedMaterialPre;
      this.mLocalizedMaterialPost = aLocalizedMaterialPost;
   }

   public static OrePrefixes getOrePrefix(String aOre) {
      OrePrefixes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         OrePrefixes tPrefix = arr$[i$];
         if(aOre.startsWith(tPrefix.toString())) {
            if(tPrefix == oreNether && aOre.equals("oreNetherQuartz")) {
               return ore;
            }

            return tPrefix;
         }
      }

      return null;
   }

   public static String stripPrefix(String aOre) {
      OrePrefixes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         OrePrefixes tPrefix = arr$[i$];
         if(aOre.startsWith(tPrefix.toString())) {
            return aOre.replaceFirst(tPrefix.toString(), "");
         }
      }

      return aOre;
   }

   public static String replacePrefix(String aOre, OrePrefixes aPrefix) {
      OrePrefixes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         OrePrefixes tPrefix = arr$[i$];
         if(aOre.startsWith(tPrefix.toString())) {
            return aOre.replaceFirst(tPrefix.toString(), aPrefix.toString());
         }
      }

      return "";
   }

   public static OrePrefixes getPrefix(String aPrefixName) {
      return getPrefix(aPrefixName, (OrePrefixes)null);
   }

   public static OrePrefixes getPrefix(String aPrefixName, OrePrefixes aReplacement) {
      Object tObject = GT_Utility.getFieldContent(OrePrefixes.class, aPrefixName, false, false);
      return tObject != null && tObject instanceof OrePrefixes?(OrePrefixes)tObject:aReplacement;
   }

   public String get(Object aMaterial) {
      return this.toString() + aMaterial;
   }

   public static Materials getMaterial(String aOre) {
      return Materials.get(stripPrefix(aOre));
   }

   public static Materials getMaterial(String aOre, OrePrefixes aPrefix) {
      return Materials.get(aOre.replaceFirst(aPrefix.toString(), ""));
   }

   public static Materials getRealMaterial(String aOre, OrePrefixes aPrefix) {
      return Materials.getRealMaterial(aOre.replaceFirst(aPrefix.toString(), ""));
   }

   public static boolean isInstanceOf(String aName, OrePrefixes aPrefix) {
      return aName == null?false:aName.startsWith(aPrefix.toString());
   }

   static {
      pulp.mPrefixInto = dust;
      oreGem.mPrefixInto = ore;
      leaves.mPrefixInto = treeLeaves;
      sapling.mPrefixInto = treeSapling;
      itemDust.mPrefixInto = dust;
      dustDirty.mPrefixInto = dustImpure;
      ingotQuad.mPrefixInto = ingotQuadruple;
      plateQuad.mPrefixInto = plateQuadruple;
      stoneBrick.mPrefixInto = stoneBricks;
      stoneBricksMossy.mPrefixInto = stoneMossyBricks;
      block.ignoreMaterials(new Materials[]{Materials.Glowstone, Materials.DarkIron});
      gem.mNotGeneratedItems.add(Materials.Coal);
      gem.mNotGeneratedItems.add(Materials.Charcoal);
      gem.mNotGeneratedItems.add(Materials.NetherStar);
      gem.mNotGeneratedItems.add(Materials.Diamond);
      gem.mNotGeneratedItems.add(Materials.Emerald);
      gem.mNotGeneratedItems.add(Materials.Lapis);
      gem.mNotGeneratedItems.add(Materials.NetherQuartz);
      gem.mNotGeneratedItems.add(Materials.EnderPearl);
      gem.mNotGeneratedItems.add(Materials.EnderEye);
      gem.mNotGeneratedItems.add(Materials.Flint);
      dust.mNotGeneratedItems.add(Materials.Bone);
      dust.mNotGeneratedItems.add(Materials.Redstone);
      dust.mNotGeneratedItems.add(Materials.Glowstone);
      dust.mNotGeneratedItems.add(Materials.Gunpowder);
      dust.mNotGeneratedItems.add(Materials.Sugar);
      dust.mNotGeneratedItems.add(Materials.Blaze);
      stick.mNotGeneratedItems.add(Materials.Wood);
      stick.mNotGeneratedItems.add(Materials.Bone);
      stick.mNotGeneratedItems.add(Materials.Blaze);
      ingot.mNotGeneratedItems.add(Materials.Iron);
      ingot.mNotGeneratedItems.add(Materials.Gold);
      nugget.mNotGeneratedItems.add(Materials.Gold);
      plate.mNotGeneratedItems.add(Materials.Paper);
      cell.mNotGeneratedItems.add(Materials.Empty);
      cell.mNotGeneratedItems.add(Materials.Water);
      cell.mNotGeneratedItems.add(Materials.Lava);
      cell.mNotGeneratedItems.add(Materials.Oxygen);
      cell.mNotGeneratedItems.add(Materials.ConstructionFoam);
      cell.mNotGeneratedItems.add(Materials.UUMatter);
      cell.mNotGeneratedItems.add(Materials.BioFuel);
      cell.mNotGeneratedItems.add(Materials.CoalFuel);
      cellPlasma.mNotGeneratedItems.add(Materials.Empty);
      bucket.mNotGeneratedItems.add(Materials.Empty);
      bucket.mNotGeneratedItems.add(Materials.Lava);
      bucket.mNotGeneratedItems.add(Materials.Milk);
      bucket.mNotGeneratedItems.add(Materials.Water);
      bottle.mNotGeneratedItems.add(Materials.Empty);
      bottle.mNotGeneratedItems.add(Materials.Water);
      bottle.mNotGeneratedItems.add(Materials.Milk);
      block.mNotGeneratedItems.add(Materials.Iron);
      block.mNotGeneratedItems.add(Materials.Gold);
      block.mNotGeneratedItems.add(Materials.Lapis);
      block.mNotGeneratedItems.add(Materials.Emerald);
      block.mNotGeneratedItems.add(Materials.Redstone);
      block.mNotGeneratedItems.add(Materials.Diamond);
      block.mNotGeneratedItems.add(Materials.Coal);
      VERSION = 408;
   }
}
