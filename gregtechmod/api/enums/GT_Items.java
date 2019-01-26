package gregtechmod.api.enums;

import gregtechmod.api.interfaces.IItemContainer;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum GT_Items implements IItemContainer {

   TE_Slag("TE_Slag", 0),
   TE_Slag_Rich("TE_Slag_Rich", 1),
   TE_Rockwool("TE_Rockwool", 2),
   TE_Hardened_Glass("TE_Hardened_Glass", 3),
   IC2_Scrap("IC2_Scrap", 4),
   IC2_Scrapbox("IC2_Scrapbox", 5),
   IC2_Fertilizer("IC2_Fertilizer", 6),
   IC2_Mixed_Metal_Ingot("IC2_Mixed_Metal_Ingot", 7),
   IC2_Resin("IC2_Resin", 8),
   IC2_Crop_Seeds("IC2_Crop_Seeds", 9),
   IC2_Grin_Powder("IC2_Grin_Powder", 10),
   IC2_Energium_Dust("IC2_Energium_Dust", 11),
   IC2_Compressed_Coal_Ball("IC2_Compressed_Coal_Ball", 12),
   IC2_Compressed_Coal_Chunk("IC2_Compressed_Coal_Chunk", 13),
   IC2_Fuel_Rod_Empty("IC2_Fuel_Rod_Empty", 14),
   IC2_Fuel_Can_Empty("IC2_Fuel_Can_Empty", 15),
   IC2_Fuel_Can_Filled("IC2_Fuel_Can_Filled", 16),
   IC2_Food_Can_Empty("IC2_Food_Can_Empty", 17),
   IC2_Food_Can_Filled("IC2_Food_Can_Filled", 18),
   IC2_Food_Can_Spoiled("IC2_Food_Can_Spoiled", 19),
   IC2_Industrial_Diamond("IC2_Industrial_Diamond", 20),
   Shape_Empty("Shape_Empty", 21),
   Shape_Mold_Plate("Shape_Mold_Plate", 22),
   Shape_Mold_Casing("Shape_Mold_Casing", 23),
   Shape_Mold_Gear("Shape_Mold_Gear", 24),
   Shape_Mold_Credit("Shape_Mold_Credit", 25),
   Shape_Extruder_Plate("Shape_Extruder_Plate", 26),
   Shape_Extruder_Cell("Shape_Extruder_Cell", 27),
   Shape_Extruder_Ring("Shape_Extruder_Ring", 28),
   Shape_Extruder_Rod("Shape_Extruder_Rod", 29),
   Shape_Extruder_Bolt("Shape_Extruder_Bolt", 30),
   Shape_Extruder_Ingot("Shape_Extruder_Ingot", 31),
   Shape_Extruder_Wire("Shape_Extruder_Wire", 32),
   Shape_Extruder_Casing("Shape_Extruder_Casing", 33),
   Shape_Extruder_Pipe_Small("Shape_Extruder_Pipe_Small", 34),
   Shape_Extruder_Pipe_Medium("Shape_Extruder_Pipe_Medium", 35),
   Shape_Extruder_Pipe_Large("Shape_Extruder_Pipe_Large", 36),
   Shape_Extruder_Block("Shape_Extruder_Block", 37),
   Shape_Extruder_Sword("Shape_Extruder_Sword", 38),
   Shape_Extruder_Pickaxe("Shape_Extruder_Pickaxe", 39),
   Shape_Extruder_Shovel("Shape_Extruder_Shovel", 40),
   Shape_Extruder_Axe("Shape_Extruder_Axe", 41),
   Shape_Extruder_Hoe("Shape_Extruder_Hoe", 42),
   Shape_Extruder_Hammer("Shape_Extruder_Hammer", 43),
   Shape_Extruder_File("Shape_Extruder_File", 44),
   Shape_Extruder_Saw("Shape_Extruder_Saw", 45),
   Shape_Extruder_Gear("Shape_Extruder_Gear", 46),
   Credit_Copper("Credit_Copper", 47),
   Credit_Iron("Credit_Iron", 48),
   Credit_Silver("Credit_Silver", 49),
   Credit_Gold("Credit_Gold", 50),
   Credit_Platinum("Credit_Platinum", 51),
   Credit_Osmium("Credit_Osmium", 52),
   Credit_Greg_Copper("Credit_Greg_Copper", 53),
   Credit_Greg_Cupronickel("Credit_Greg_Cupronickel", 54),
   Credit_Greg_Silver("Credit_Greg_Silver", 55),
   Credit_Greg_Gold("Credit_Greg_Gold", 56),
   Credit_Greg_Platinum("Credit_Greg_Platinum", 57),
   Credit_Greg_Osmium("Credit_Greg_Osmium", 58),
   Credit_Greg_Naquadah("Credit_Greg_Naquadah", 59),
   Credit_Greg_Neutronium("Credit_Greg_Neutronium", 60),
   Coin_Gold_Ancient("Coin_Gold_Ancient", 61),
   Coin_Doge("Coin_Doge", 62),
   Coin_Chocolate("Coin_Chocolate", 63),
   Cell_Empty("Cell_Empty", 64),
   Cell_Water("Cell_Water", 65),
   Cell_Lava("Cell_Lava", 66),
   Cell_Air("Cell_Air", 67),
   Can_Food_Empty("Can_Food_Empty", 68),
   Can_Food_Filled("Can_Food_Filled", 69),
   Can_Food_Spoiled("Can_Food_Spoiled", 70),
   Bottle_Purple_Drink("Bottle_Purple_Drink", 71),
   Food_Potato_On_Stick("Food_Potato_On_Stick", 72),
   Food_Potato_On_Stick_Roasted("Food_Potato_On_Stick_Roasted", 73),
   Crop_Drop_Argentia("Crop_Drop_Argentia", 74),
   Crop_Drop_Plumbilia("Crop_Drop_Plumbilia", 75),
   Crop_Drop_Indigo("Crop_Drop_Indigo", 76),
   Crop_Drop_Ferru("Crop_Drop_Ferru", 77),
   Crop_Drop_Aurelia("Crop_Drop_Aurelia", 78),
   Crop_Drop_OilBerry("Crop_Drop_OilBerry", 79),
   Crop_Drop_MilkWart("Crop_Drop_MilkWart", 80),
   Crop_Drop_BobsYerUncleRanks("Crop_Drop_BobsYerUncleRanks", 81),
   Crop_Drop_Coppon("Crop_Drop_Coppon", 82),
   Crop_Drop_Tine("Crop_Drop_Tine", 83),
   Circuit_Integrated("Circuit_Integrated", 84),
   Circuit_Board_Basic("Circuit_Board_Basic", 85),
   Circuit_Board_Advanced("Circuit_Board_Advanced", 86),
   Circuit_Board_Elite("Circuit_Board_Elite", 87),
   Circuit_Parts_Advanced("Circuit_Parts_Advanced", 88),
   Circuit_Basic("Circuit_Basic", 89),
   Circuit_Advanced("Circuit_Advanced", 90),
   Circuit_Data("Circuit_Data", 91),
   Circuit_Elite("Circuit_Elite", 92),
   Circuit_Master("Circuit_Master", 93),
   Circuit_Ultimate("Circuit_Ultimate", 94),
   Battery_Hull_LV("Battery_Hull_LV", 95),
   Battery_Hull_MV("Battery_Hull_MV", 96),
   Battery_SU_LV_SulfuricAcid("Battery_SU_LV_SulfuricAcid", 97),
   Battery_SU_LV_Mercury("Battery_SU_LV_Mercury", 98),
   Battery_SU_MV_SulfuricAcid("Battery_SU_MV_SulfuricAcid", 99),
   Battery_SU_MV_Mercury("Battery_SU_MV_Mercury", 100),
   Battery_RE_LV_Lithium("Battery_RE_LV_Lithium", 101),
   Battery_RE_LV_Sodium("Battery_RE_LV_Sodium", 102),
   Battery_RE_MV_Lithium("Battery_RE_MV_Lithium", 103),
   Battery_RE_MV_Sodium("Battery_RE_MV_Sodium", 104),
   ZPM("ZPM", 105),
   Fuel_Can_Plastic_Empty("Fuel_Can_Plastic_Empty", 106),
   Fuel_Can_Plastic_Filled("Fuel_Can_Plastic_Filled", 107),
   Upgrade_Overclocker("Upgrade_Overclocker", 108),
   Upgrade_Transformer("Upgrade_Transformer", 109),
   Upgrade_Battery("Upgrade_Battery", 110),
   McGuffium_239("McGuffium_239", 111),
   Display_Fluid("Display_Fluid", 112),
   NC_SensorCard("NC_SensorCard", 113),
   NC_SensorKit("NC_SensorKit", 114),
   Tool_Mortar_Iron("Tool_Mortar_Iron", 115),
   Tool_Mortar_Wood("Tool_Mortar_Wood", 116),
   Tool_Cheat("Tool_Cheat", 117),
   Tool_Scanner("Tool_Scanner", 118),
   Tool_Crowbar_Iron("Tool_Crowbar_Iron", 119),
   Tool_Screwdriver_Iron("Tool_Screwdriver_Iron", 120),
   Tool_Screwdriver_TungstenSteel("Tool_Screwdriver_TungstenSteel", 121),
   Tool_Screwdriver_Electric("Tool_Screwdriver_Electric", 122),
   Tool_Wrench_Iron("Tool_Wrench_Iron", 123),
   Tool_Wrench_Bronze("Tool_Wrench_Bronze", 124),
   Tool_Wrench_Steel("Tool_Wrench_Steel", 125),
   Tool_Wrench_TungstenSteel("Tool_Wrench_TungstenSteel", 126),
   Tool_Wrench_Electric("Tool_Wrench_Electric", 127),
   Tool_Wrench_Advanced("Tool_Wrench_Advanced", 128),
   Tool_Hammer_Forge("Tool_Hammer_Forge", 129),
   Tool_Hammer_Rubber("Tool_Hammer_Rubber", 130),
   Tool_Hammer_Plastic("Tool_Hammer_Plastic", 131),
   Tool_Hammer_Iron("Tool_Hammer_Iron", 132),
   Tool_Hammer_Bronze("Tool_Hammer_Bronze", 133),
   Tool_Hammer_Steel("Tool_Hammer_Steel", 134),
   Tool_Hammer_TungstenSteel("Tool_Hammer_TungstenSteel", 135),
   Tool_File_Iron("Tool_File_Iron", 136),
   Tool_File_Bronze("Tool_File_Bronze", 137),
   Tool_File_Steel("Tool_File_Steel", 138),
   Tool_File_TungstenSteel("Tool_File_TungstenSteel", 139),
   Tool_Saw_Iron("Tool_Saw_Iron", 140),
   Tool_Saw_Bronze("Tool_Saw_Bronze", 141),
   Tool_Saw_Steel("Tool_Saw_Steel", 142),
   Tool_Saw_TungstenSteel("Tool_Saw_TungstenSteel", 143),
   Tool_Saw_Electric("Tool_Saw_Electric", 144),
   Tool_Saw_Advanced("Tool_Saw_Advanced", 145),
   Tool_Drill_Advanced("Tool_Drill_Advanced", 146),
   Tool_SolderingIron_Electric("Tool_SolderingIron_Electric", 147),
   Tool_SolderingMaterial_Tin("Tool_SolderingMaterial_Tin", 148),
   Tool_SolderingMaterial_Lead("Tool_SolderingMaterial_Lead", 149),
   Tool_Rockcutter("Tool_Rockcutter", 150),
   Tool_Teslastaff("Tool_Teslastaff", 151),
   Tool_DataOrb("Tool_DataOrb", 152),
   Tool_Sonictron("Tool_Sonictron", 153),
   Tool_Destructopack("Tool_Destructopack", 154),
   Tool_Sword_Flint("Tool_Sword_Flint", 155),
   Tool_Sword_Bronze("Tool_Sword_Bronze", 156),
   Tool_Sword_Steel("Tool_Sword_Steel", 157),
   Tool_Sword_TungstenSteel("Tool_Sword_TungstenSteel", 158),
   Tool_Pickaxe_Flint("Tool_Pickaxe_Flint", 159),
   Tool_Pickaxe_Bronze("Tool_Pickaxe_Bronze", 160),
   Tool_Pickaxe_Steel("Tool_Pickaxe_Steel", 161),
   Tool_Pickaxe_TungstenSteel("Tool_Pickaxe_TungstenSteel", 162),
   Tool_Shovel_Flint("Tool_Shovel_Flint", 163),
   Tool_Shovel_Bronze("Tool_Shovel_Bronze", 164),
   Tool_Shovel_Steel("Tool_Shovel_Steel", 165),
   Tool_Shovel_TungstenSteel("Tool_Shovel_TungstenSteel", 166),
   Tool_Axe_Flint("Tool_Axe_Flint", 167),
   Tool_Axe_Bronze("Tool_Axe_Bronze", 168),
   Tool_Axe_Steel("Tool_Axe_Steel", 169),
   Tool_Axe_TungstenSteel("Tool_Axe_TungstenSteel", 170),
   Tool_Hoe_Flint("Tool_Hoe_Flint", 171),
   Tool_Hoe_Bronze("Tool_Hoe_Bronze", 172),
   Tool_Hoe_Steel("Tool_Hoe_Steel", 173),
   Tool_Hoe_TungstenSteel("Tool_Hoe_TungstenSteel", 174),
   Tool_Scoop_Aluminium("Tool_Scoop_Aluminium", 175),
   Tool_Jackhammer_Bronze("Tool_Jackhammer_Bronze", 176),
   Tool_Jackhammer_Steel("Tool_Jackhammer_Steel", 177),
   Tool_Jackhammer_Diamond("Tool_Jackhammer_Diamond", 178),
   Spray_Empty("Spray_Empty", 179),
   Spray_Bug("Spray_Bug", 180),
   Spray_Ice("Spray_Ice", 181),
   Spray_Hardener("Spray_Hardener", 182),
   Spray_CFoam("Spray_CFoam", 183),
   Spray_Pepper("Spray_Pepper", 184),
   Spray_Hydration("Spray_Hydration", 185),
   Spray_Color_00("Spray_Color_00", 186),
   Spray_Color_01("Spray_Color_01", 187),
   Spray_Color_02("Spray_Color_02", 188),
   Spray_Color_03("Spray_Color_03", 189),
   Spray_Color_04("Spray_Color_04", 190),
   Spray_Color_05("Spray_Color_05", 191),
   Spray_Color_06("Spray_Color_06", 192),
   Spray_Color_07("Spray_Color_07", 193),
   Spray_Color_08("Spray_Color_08", 194),
   Spray_Color_09("Spray_Color_09", 195),
   Spray_Color_10("Spray_Color_10", 196),
   Spray_Color_11("Spray_Color_11", 197),
   Spray_Color_12("Spray_Color_12", 198),
   Spray_Color_13("Spray_Color_13", 199),
   Spray_Color_14("Spray_Color_14", 200),
   Spray_Color_15("Spray_Color_15", 201),
   Armor_Cheat("Armor_Cheat", 202),
   Armor_Cloaking("Armor_Cloaking", 203),
   Armor_Lamp("Armor_Lamp", 204),
   Armor_LithiumPack("Armor_LithiumPack", 205),
   Armor_LapotronicPack("Armor_LapotronicPack", 206),
   Armor_ForceField("Armor_ForceField", 207),
   Energy_LapotronicOrb("Energy_LapotronicOrb", 208),
   Energy_Lithium("Energy_Lithium", 209),
   Energy_Lithium_Empty("Energy_Lithium_Empty", 210),
   Reactor_Coolant_He_1("Reactor_Coolant_He_1", 211),
   Reactor_Coolant_He_3("Reactor_Coolant_He_3", 212),
   Reactor_Coolant_He_6("Reactor_Coolant_He_6", 213),
   Reactor_Coolant_NaK_1("Reactor_Coolant_NaK_1", 214),
   Reactor_Coolant_NaK_3("Reactor_Coolant_NaK_3", 215),
   Reactor_Coolant_NaK_6("Reactor_Coolant_NaK_6", 216),
   Reactor_NeutronReflector("Reactor_NeutronReflector", 217),
   Component_Turbine_Bronze("Component_Turbine_Bronze", 218),
   Component_Turbine_Steel("Component_Turbine_Steel", 219),
   Component_Turbine_Magnalium("Component_Turbine_Magnalium", 220),
   Component_Turbine_TungstenSteel("Component_Turbine_TungstenSteel", 221),
   Component_Turbine_Carbon("Component_Turbine_Carbon", 222),
   Component_LavaFilter("Component_LavaFilter", 223),
   Machine_Bronze_Boiler("Machine_Bronze_Boiler", 224),
   Machine_Bronze_Furnace("Machine_Bronze_Furnace", 225),
   Machine_Bronze_CraftingTable("Machine_Bronze_CraftingTable", 226),
   Machine_Bronze_Macerator("Machine_Bronze_Macerator", 227),
   Machine_Bronze_Extractor("Machine_Bronze_Extractor", 228),
   Machine_Bronze_Hammer("Machine_Bronze_Hammer", 229),
   Machine_Bronze_Compressor("Machine_Bronze_Compressor", 230),
   Machine_Bronze_BlastFurnace("Machine_Bronze_BlastFurnace", 231),
   Machine_Bronze_AlloySmelter("Machine_Bronze_AlloySmelter", 232),
   Machine_Steel_Boiler("Machine_Steel_Boiler", 233),
   Machine_Steel_Furnace("Machine_Steel_Furnace", 234),
   Frame_Iron("Frame_Iron", 235),
   Frame_Aluminium("Frame_Aluminium", 236),
   Frame_Steel("Frame_Steel", 237),
   Frame_StainlessSteel("Frame_StainlessSteel", 238),
   Frame_TungstenSteel("Frame_TungstenSteel", 239),
   Pipe_Bronze_Small("Pipe_Bronze_Small", 240),
   Pipe_Bronze_Medium("Pipe_Bronze_Medium", 241),
   Pipe_Bronze_Large("Pipe_Bronze_Large", 242),
   Pipe_Steel_Small("Pipe_Steel_Small", 243),
   Pipe_Steel_Medium("Pipe_Steel_Medium", 244),
   Pipe_Steel_Large("Pipe_Steel_Large", 245),
   Pipe_StainlessSteel_Small("Pipe_StainlessSteel_Small", 246),
   Pipe_StainlessSteel_Medium("Pipe_StainlessSteel_Medium", 247),
   Pipe_StainlessSteel_Large("Pipe_StainlessSteel_Large", 248),
   Pipe_TungstenSteel_Small("Pipe_TungstenSteel_Small", 249),
   Pipe_TungstenSteel_Medium("Pipe_TungstenSteel_Medium", 250),
   Pipe_TungstenSteel_Large("Pipe_TungstenSteel_Large", 251),
   Pipe_Brass_Medium("Pipe_Brass_Medium", 252),
   Pipe_Brass_Large("Pipe_Brass_Large", 253),
   Pipe_Electrum_Medium("Pipe_Electrum_Medium", 254),
   Pipe_Electrum_Large("Pipe_Electrum_Large", 255),
   Pipe_Platinum_Medium("Pipe_Platinum_Medium", 256),
   Pipe_Platinum_Large("Pipe_Platinum_Large", 257),
   NULL("NULL", 258);
   public static final GT_Items[] SPRAY_CAN_DYES = new GT_Items[]{Spray_Color_00, Spray_Color_01, Spray_Color_02, Spray_Color_03, Spray_Color_04, Spray_Color_05, Spray_Color_06, Spray_Color_07, Spray_Color_08, Spray_Color_09, Spray_Color_10, Spray_Color_11, Spray_Color_12, Spray_Color_13, Spray_Color_14, Spray_Color_15};
   private ItemStack mStack;
   private boolean mHasNotBeenSet = true;
   // $FF: synthetic field
   private static final GT_Items[] $VALUES = new GT_Items[]{TE_Slag, TE_Slag_Rich, TE_Rockwool, TE_Hardened_Glass, IC2_Scrap, IC2_Scrapbox, IC2_Fertilizer, IC2_Mixed_Metal_Ingot, IC2_Resin, IC2_Crop_Seeds, IC2_Grin_Powder, IC2_Energium_Dust, IC2_Compressed_Coal_Ball, IC2_Compressed_Coal_Chunk, IC2_Fuel_Rod_Empty, IC2_Fuel_Can_Empty, IC2_Fuel_Can_Filled, IC2_Food_Can_Empty, IC2_Food_Can_Filled, IC2_Food_Can_Spoiled, IC2_Industrial_Diamond, Shape_Empty, Shape_Mold_Plate, Shape_Mold_Casing, Shape_Mold_Gear, Shape_Mold_Credit, Shape_Extruder_Plate, Shape_Extruder_Cell, Shape_Extruder_Ring, Shape_Extruder_Rod, Shape_Extruder_Bolt, Shape_Extruder_Ingot, Shape_Extruder_Wire, Shape_Extruder_Casing, Shape_Extruder_Pipe_Small, Shape_Extruder_Pipe_Medium, Shape_Extruder_Pipe_Large, Shape_Extruder_Block, Shape_Extruder_Sword, Shape_Extruder_Pickaxe, Shape_Extruder_Shovel, Shape_Extruder_Axe, Shape_Extruder_Hoe, Shape_Extruder_Hammer, Shape_Extruder_File, Shape_Extruder_Saw, Shape_Extruder_Gear, Credit_Copper, Credit_Iron, Credit_Silver, Credit_Gold, Credit_Platinum, Credit_Osmium, Credit_Greg_Copper, Credit_Greg_Cupronickel, Credit_Greg_Silver, Credit_Greg_Gold, Credit_Greg_Platinum, Credit_Greg_Osmium, Credit_Greg_Naquadah, Credit_Greg_Neutronium, Coin_Gold_Ancient, Coin_Doge, Coin_Chocolate, Cell_Empty, Cell_Water, Cell_Lava, Cell_Air, Can_Food_Empty, Can_Food_Filled, Can_Food_Spoiled, Bottle_Purple_Drink, Food_Potato_On_Stick, Food_Potato_On_Stick_Roasted, Crop_Drop_Argentia, Crop_Drop_Plumbilia, Crop_Drop_Indigo, Crop_Drop_Ferru, Crop_Drop_Aurelia, Crop_Drop_OilBerry, Crop_Drop_MilkWart, Crop_Drop_BobsYerUncleRanks, Crop_Drop_Coppon, Crop_Drop_Tine, Circuit_Integrated, Circuit_Board_Basic, Circuit_Board_Advanced, Circuit_Board_Elite, Circuit_Parts_Advanced, Circuit_Basic, Circuit_Advanced, Circuit_Data, Circuit_Elite, Circuit_Master, Circuit_Ultimate, Battery_Hull_LV, Battery_Hull_MV, Battery_SU_LV_SulfuricAcid, Battery_SU_LV_Mercury, Battery_SU_MV_SulfuricAcid, Battery_SU_MV_Mercury, Battery_RE_LV_Lithium, Battery_RE_LV_Sodium, Battery_RE_MV_Lithium, Battery_RE_MV_Sodium, ZPM, Fuel_Can_Plastic_Empty, Fuel_Can_Plastic_Filled, Upgrade_Overclocker, Upgrade_Transformer, Upgrade_Battery, McGuffium_239, Display_Fluid, NC_SensorCard, NC_SensorKit, Tool_Mortar_Iron, Tool_Mortar_Wood, Tool_Cheat, Tool_Scanner, Tool_Crowbar_Iron, Tool_Screwdriver_Iron, Tool_Screwdriver_TungstenSteel, Tool_Screwdriver_Electric, Tool_Wrench_Iron, Tool_Wrench_Bronze, Tool_Wrench_Steel, Tool_Wrench_TungstenSteel, Tool_Wrench_Electric, Tool_Wrench_Advanced, Tool_Hammer_Forge, Tool_Hammer_Rubber, Tool_Hammer_Plastic, Tool_Hammer_Iron, Tool_Hammer_Bronze, Tool_Hammer_Steel, Tool_Hammer_TungstenSteel, Tool_File_Iron, Tool_File_Bronze, Tool_File_Steel, Tool_File_TungstenSteel, Tool_Saw_Iron, Tool_Saw_Bronze, Tool_Saw_Steel, Tool_Saw_TungstenSteel, Tool_Saw_Electric, Tool_Saw_Advanced, Tool_Drill_Advanced, Tool_SolderingIron_Electric, Tool_SolderingMaterial_Tin, Tool_SolderingMaterial_Lead, Tool_Rockcutter, Tool_Teslastaff, Tool_DataOrb, Tool_Sonictron, Tool_Destructopack, Tool_Sword_Flint, Tool_Sword_Bronze, Tool_Sword_Steel, Tool_Sword_TungstenSteel, Tool_Pickaxe_Flint, Tool_Pickaxe_Bronze, Tool_Pickaxe_Steel, Tool_Pickaxe_TungstenSteel, Tool_Shovel_Flint, Tool_Shovel_Bronze, Tool_Shovel_Steel, Tool_Shovel_TungstenSteel, Tool_Axe_Flint, Tool_Axe_Bronze, Tool_Axe_Steel, Tool_Axe_TungstenSteel, Tool_Hoe_Flint, Tool_Hoe_Bronze, Tool_Hoe_Steel, Tool_Hoe_TungstenSteel, Tool_Scoop_Aluminium, Tool_Jackhammer_Bronze, Tool_Jackhammer_Steel, Tool_Jackhammer_Diamond, Spray_Empty, Spray_Bug, Spray_Ice, Spray_Hardener, Spray_CFoam, Spray_Pepper, Spray_Hydration, Spray_Color_00, Spray_Color_01, Spray_Color_02, Spray_Color_03, Spray_Color_04, Spray_Color_05, Spray_Color_06, Spray_Color_07, Spray_Color_08, Spray_Color_09, Spray_Color_10, Spray_Color_11, Spray_Color_12, Spray_Color_13, Spray_Color_14, Spray_Color_15, Armor_Cheat, Armor_Cloaking, Armor_Lamp, Armor_LithiumPack, Armor_LapotronicPack, Armor_ForceField, Energy_LapotronicOrb, Energy_Lithium, Energy_Lithium_Empty, Reactor_Coolant_He_1, Reactor_Coolant_He_3, Reactor_Coolant_He_6, Reactor_Coolant_NaK_1, Reactor_Coolant_NaK_3, Reactor_Coolant_NaK_6, Reactor_NeutronReflector, Component_Turbine_Bronze, Component_Turbine_Steel, Component_Turbine_Magnalium, Component_Turbine_TungstenSteel, Component_Turbine_Carbon, Component_LavaFilter, Machine_Bronze_Boiler, Machine_Bronze_Furnace, Machine_Bronze_CraftingTable, Machine_Bronze_Macerator, Machine_Bronze_Extractor, Machine_Bronze_Hammer, Machine_Bronze_Compressor, Machine_Bronze_BlastFurnace, Machine_Bronze_AlloySmelter, Machine_Steel_Boiler, Machine_Steel_Furnace, Frame_Iron, Frame_Aluminium, Frame_Steel, Frame_StainlessSteel, Frame_TungstenSteel, Pipe_Bronze_Small, Pipe_Bronze_Medium, Pipe_Bronze_Large, Pipe_Steel_Small, Pipe_Steel_Medium, Pipe_Steel_Large, Pipe_StainlessSteel_Small, Pipe_StainlessSteel_Medium, Pipe_StainlessSteel_Large, Pipe_TungstenSteel_Small, Pipe_TungstenSteel_Medium, Pipe_TungstenSteel_Large, Pipe_Brass_Medium, Pipe_Brass_Large, Pipe_Electrum_Medium, Pipe_Electrum_Large, Pipe_Platinum_Medium, Pipe_Platinum_Large, NULL};


   private GT_Items(String var1, int var2) {}

   public IItemContainer set(Item aItem) {
      this.mHasNotBeenSet = false;
      if(aItem == null) {
         return this;
      } else {
         ItemStack aStack = new ItemStack(aItem, 1, 0);
         this.mStack = GT_Utility.copyAmount(1L, new Object[]{aStack});
         return this;
      }
   }

   public IItemContainer set(ItemStack aStack) {
      this.mHasNotBeenSet = false;
      this.mStack = GT_Utility.copyAmount(1L, new Object[]{aStack});
      return this;
   }

   public Item getItem() {
      if(this.mHasNotBeenSet) {
         throw new IllegalAccessError("The Enum \'" + this.toString() + "\' has not been set to an Item at this time!");
      } else {
         return GT_Utility.isStackInvalid(this.mStack)?null:this.mStack.func_77973_b();
      }
   }

   public Block getBlock() {
      if(this.mHasNotBeenSet) {
         throw new IllegalAccessError("The Enum \'" + this.toString() + "\' has not been set to an Item at this time!");
      } else {
         return GT_Utility.getBlockFromStack(this.getItem());
      }
   }

   public final boolean hasBeenSet() {
      return !this.mHasNotBeenSet;
   }

   public boolean isStackEqual(Object aStack) {
      return this.isStackEqual(aStack, false, false);
   }

   public boolean isStackEqual(Object aStack, boolean aWildcard, boolean aIgnoreNBT) {
      return GT_Utility.isStackInvalid(aStack)?false:GT_Utility.areUnificationsEqual((ItemStack)aStack, aWildcard?this.getWildcard(1L, new Object[0]):this.get(1L, new Object[0]), aIgnoreNBT);
   }

   public ItemStack get(long aAmount, Object ... aReplacements) {
      if(this.mHasNotBeenSet) {
         throw new IllegalAccessError("The Enum \'" + this.toString() + "\' has not been set to an Item at this time!");
      } else {
         return GT_Utility.isStackInvalid(this.mStack)?GT_Utility.copyAmount(aAmount, aReplacements):GT_Utility.copyAmount(aAmount, new Object[]{GT_OreDictUnificator.get(this.mStack)});
      }
   }

   public ItemStack getWildcard(long aAmount, Object ... aReplacements) {
      if(this.mHasNotBeenSet) {
         throw new IllegalAccessError("The Enum \'" + this.toString() + "\' has not been set to an Item at this time!");
      } else {
         return GT_Utility.isStackInvalid(this.mStack)?GT_Utility.copyAmount(aAmount, aReplacements):GT_Utility.copyAmountAndMetaData(aAmount, 32767L, new Object[]{GT_OreDictUnificator.get(this.mStack)});
      }
   }

   public ItemStack getUndamaged(long aAmount, Object ... aReplacements) {
      if(this.mHasNotBeenSet) {
         throw new IllegalAccessError("The Enum \'" + this.toString() + "\' has not been set to an Item at this time!");
      } else {
         return GT_Utility.isStackInvalid(this.mStack)?GT_Utility.copyAmount(aAmount, aReplacements):GT_Utility.copyAmountAndMetaData(aAmount, 0L, new Object[]{GT_OreDictUnificator.get(this.mStack)});
      }
   }

   public ItemStack getAlmostBroken(long aAmount, Object ... aReplacements) {
      if(this.mHasNotBeenSet) {
         throw new IllegalAccessError("The Enum \'" + this.toString() + "\' has not been set to an Item at this time!");
      } else {
         return GT_Utility.isStackInvalid(this.mStack)?GT_Utility.copyAmount(aAmount, aReplacements):GT_Utility.copyAmountAndMetaData(aAmount, (long)(this.mStack.func_77958_k() - 1), new Object[]{GT_OreDictUnificator.get(this.mStack)});
      }
   }

   public ItemStack getWithCharge(long aAmount, int aEnergy, Object ... aReplacements) {
      ItemStack rStack = this.get(1L, aReplacements);
      if(GT_Utility.isStackInvalid(rStack)) {
         return null;
      } else {
         GT_ModHandler.chargeElectricItem(rStack, aEnergy, Integer.MAX_VALUE, true, false);
         return GT_Utility.copyAmount(aAmount, new Object[]{rStack});
      }
   }

   public ItemStack getWithDamage(long aAmount, long aMetaValue, Object ... aReplacements) {
      if(this.mHasNotBeenSet) {
         throw new IllegalAccessError("The Enum \'" + this.toString() + "\' has not been set to an Item at this time!");
      } else {
         return GT_Utility.isStackInvalid(this.mStack)?GT_Utility.copyAmount(aAmount, aReplacements):GT_Utility.copyAmountAndMetaData(aAmount, aMetaValue, new Object[]{GT_OreDictUnificator.get(this.mStack)});
      }
   }

   public IItemContainer registerOre(Object ... aOreNames) {
      if(this.mHasNotBeenSet) {
         throw new IllegalAccessError("The Enum \'" + this.toString() + "\' has not been set to an Item at this time!");
      } else {
         Object[] arr$ = aOreNames;
         int len$ = aOreNames.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Object tOreName = arr$[i$];
            GT_OreDictUnificator.registerOre(tOreName, this.get(1L, new Object[0]));
         }

         return this;
      }
   }

   public IItemContainer registerWildcardAsOre(Object ... aOreNames) {
      if(this.mHasNotBeenSet) {
         throw new IllegalAccessError("The Enum \'" + this.toString() + "\' has not been set to an Item at this time!");
      } else {
         Object[] arr$ = aOreNames;
         int len$ = aOreNames.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Object tOreName = arr$[i$];
            GT_OreDictUnificator.registerOre(tOreName, this.getWildcard(1L, new Object[0]));
         }

         return this;
      }
   }

}
