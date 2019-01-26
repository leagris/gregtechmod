package gregtechmod.api.enums;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.Element;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ItemTextures;
import gregtechmod.api.enums.GT_SpecialToolEffect;
import gregtechmod.api.enums.MaterialStack;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public enum Materials {

   _NULL("_NULL", 0, -1, GT_ItemTextures.SET_EMPTY, 0, 255, 255, 255, 0, "NULL", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, Element._NULL),
   Aluminium("Aluminium", 1, 19, GT_ItemTextures.SET_DULL, 203, 128, 200, 240, 0, "Aluminium", 0, 0, 0, 0, 1700, 1700, true, false, 3, 1, 1, Dyes.dyeLightBlue, Element.Al),
   Americium("Americium", 2, 103, GT_ItemTextures.SET_METALLIC, 11, 200, 200, 200, 0, "Americium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, Element.Am),
   Antimony("Antimony", 3, 58, GT_ItemTextures.SET_SHINY, 11, 220, 220, 240, 0, "Antimony", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeLightGray, Element.Sb),
   Arsenic("Arsenic", 4, 39, GT_ItemTextures.SET_FLUID, 48, 255, 255, 255, 0, "Arsenic", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeOrange, Element.As),
   Barium("Barium", 5, 63, GT_ItemTextures.SET_METALLIC, 57, 255, 255, 255, 0, "Barium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, Element.Ba),
   Beryllium("Beryllium", 6, 8, GT_ItemTextures.SET_METALLIC, 123, 100, 180, 100, 0, "Beryllium", 0, 0, 0, 0, 0, 0, false, false, 6, 1, 1, Dyes.dyeGreen, Element.Be),
   Boron("Boron", 7, 9, GT_ItemTextures.SET_DULL, 57, 250, 250, 250, 0, "Boron", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, Element.B),
   Caesium("Caesium", 8, 62, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Caesium", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes._NULL, Element.Cs),
   Calcium("Calcium", 9, 26, GT_ItemTextures.SET_METALLIC, 49, 255, 245, 245, 0, "Calcium", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyePink, Element.Ca),
   Carbon("Carbon", 10, 10, GT_ItemTextures.SET_DULL, 241, 20, 20, 20, 0, "Carbon", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeBlack, Element.C),
   Cadmium("Cadmium", 11, 55, GT_ItemTextures.SET_SHINY, 57, 50, 50, 60, 0, "Cadmium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeGray, Element.Cd),
   Cerium("Cerium", 12, 65, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Cerium", 0, 0, 0, 0, 1068, 1068, true, false, 4, 1, 1, Dyes._NULL, Element.Ce),
   Chlorine("Chlorine", 13, 23, GT_ItemTextures.SET_FLUID, 48, 255, 255, 255, 0, "Chlorine", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeCyan, Element.Cl),
   Chrome("Chrome", 14, 30, GT_ItemTextures.SET_SHINY, 203, 255, 230, 230, 0, "Chrome", 0, 0, 0, 0, 1700, 1700, true, false, 5, 1, 1, Dyes.dyePink, Element.Cr),
   Cobalt("Cobalt", 15, 33, GT_ItemTextures.SET_METALLIC, 75, 80, 80, 250, 0, "Cobalt", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBlue, Element.Co),
   Copper("Copper", 16, 35, GT_ItemTextures.SET_SHINY, 139, 255, 100, 0, 0, "Copper", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeOrange, Element.Cu),
   Deuterium("Deuterium", 17, 2, GT_ItemTextures.SET_FLUID, 48, 255, 255, 0, 240, "Deuterium", 0, 0, 0, 0, 0, 0, false, true, 10, 1, 1, Dyes.dyeYellow, Element.D),
   Dysprosium("Dysprosium", 18, 73, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Dysprosium", 0, 0, 0, 0, 1680, 1680, true, false, 4, 1, 1, Dyes._NULL, Element.Dy),
   Empty("Empty", 19, 0, GT_ItemTextures.SET_EMPTY, 48, 255, 255, 255, 255, "Empty", 0, 0, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes._NULL, Element._NULL),
   Erbium("Erbium", 20, 75, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Erbium", 0, 0, 0, 0, 1802, 1802, true, false, 4, 1, 1, Dyes._NULL, Element.Er),
   Europium("Europium", 21, 70, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Europium", 0, 0, 0, 0, 1099, 1099, true, false, 4, 1, 1, Dyes._NULL, Element.Eu),
   Fluorine("Fluorine", 22, 14, GT_ItemTextures.SET_FLUID, 48, 255, 255, 255, 127, "Fluorine", 0, 0, 0, 0, 0, 0, false, true, 2, 1, 1, Dyes.dyeGreen, Element.F),
   Gadolinium("Gadolinium", 23, 71, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Gadolinium", 0, 0, 0, 0, 1585, 1585, true, false, 4, 1, 1, Dyes._NULL, Element.Gd),
   Gold("Gold", 24, 86, GT_ItemTextures.SET_SHINY, 203, 255, 255, 30, 0, "Gold", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeYellow, Element.Au),
   Holmium("Holmium", 25, 74, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Holmium", 0, 0, 0, 0, 1734, 1734, true, false, 4, 1, 1, Dyes._NULL, Element.Ho),
   Hydrogen("Hydrogen", 26, 1, GT_ItemTextures.SET_FLUID, 48, 0, 0, 255, 240, "Hydrogen", 1, 15, 0, 0, 0, 0, false, true, 2, 1, 1, Dyes.dyeBlue, Element.H),
   Helium("Helium", 27, 4, GT_ItemTextures.SET_FLUID, 48, 255, 255, 0, 240, "Helium", 0, 0, 0, 0, 0, 0, false, true, 5, 1, 1, Dyes.dyeYellow, Element.He),
   Helium_3("Helium_3", 28, 5, GT_ItemTextures.SET_FLUID, 48, 255, 255, 0, 240, "Helium-3", 0, 0, 0, 0, 0, 0, false, true, 10, 1, 1, Dyes.dyeYellow, Element.He_3),
   Indium("Indium", 29, 56, GT_ItemTextures.SET_METALLIC, 11, 255, 255, 255, 0, "Indium", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeGray, Element.In),
   Iridium("Iridium", 30, 84, GT_ItemTextures.SET_DULL, 203, 240, 240, 245, 0, "Iridium", 0, 0, 0, 0, 0, 0, false, false, 10, 1, 1, Dyes.dyeWhite, Element.Ir),
   Iron("Iron", 31, 32, GT_ItemTextures.SET_METALLIC, 203, 200, 200, 200, 0, "Iron", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, Element.Fe),
   Lanthanum("Lanthanum", 32, 64, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Lanthanum", 0, 0, 0, 0, 1193, 1193, true, false, 4, 1, 1, Dyes._NULL, Element.La),
   Lead("Lead", 33, 89, GT_ItemTextures.SET_DULL, 203, 140, 100, 140, 0, "Lead", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyePurple, Element.Pb),
   Lithium("Lithium", 34, 6, GT_ItemTextures.SET_DULL, 57, 225, 220, 255, 0, "Lithium", 3, 60, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeLightBlue, Element.Li),
   Lutetium("Lutetium", 35, 78, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Lutetium", 0, 0, 0, 0, 1925, 1925, true, false, 4, 1, 1, Dyes._NULL, Element.Lu),
   Magic("Magic", 36, -128, GT_ItemTextures.SET_SHINY, 255, 100, 0, 200, 0, "Magic", 5, 32, 0, 0, 0, 0, false, false, 7, 1, 1, Dyes.dyePurple, Element.Ma),
   Magnesium("Magnesium", 37, 18, GT_ItemTextures.SET_METALLIC, 59, 255, 200, 200, 0, "Magnesium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyePink, Element.Mg),
   Manganese("Manganese", 38, 31, GT_ItemTextures.SET_DULL, 75, 250, 250, 250, 0, "Manganese", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeWhite, Element.Mn),
   Mercury("Mercury", 39, 87, GT_ItemTextures.SET_SHINY, 48, 255, 220, 220, 0, "Mercury", 5, 32, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, Element.Hg),
   Niobium("Niobium", 40, 47, GT_ItemTextures.SET_METALLIC, 11, 255, 255, 255, 0, "Niobium", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes._NULL, Element.Nb),
   Molybdenum("Molybdenum", 41, 48, GT_ItemTextures.SET_SHINY, 75, 180, 180, 220, 0, "Molybdenum", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlue, Element.Mo),
   Neodymium("Neodymium", 42, 67, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Neodymium", 0, 0, 0, 0, 1297, 1297, true, false, 4, 1, 1, Dyes._NULL, Element.Nd),
   Neutronium("Neutronium", 43, 129, GT_ItemTextures.SET_DULL, 203, 250, 250, 250, 0, "Neutronium", 0, 0, 0, 0, 0, 0, false, false, 20, 1, 1, Dyes.dyeWhite, Element.Nt),
   Nickel("Nickel", 44, 34, GT_ItemTextures.SET_METALLIC, 203, 200, 200, 250, 0, "Nickel", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeLightBlue, Element.Ni),
   Nitrogen("Nitrogen", 45, 12, GT_ItemTextures.SET_FLUID, 48, 0, 150, 200, 240, "Nitrogen", 0, 0, 0, 0, 0, 0, false, true, 2, 1, 1, Dyes.dyeCyan, Element.N),
   Osmium("Osmium", 46, 83, GT_ItemTextures.SET_METALLIC, 203, 50, 50, 255, 0, "Osmium", 0, 0, 0, 0, 0, 0, false, false, 10, 1, 1, Dyes.dyeBlue, Element.Os),
   Oxygen("Oxygen", 47, 13, GT_ItemTextures.SET_FLUID, 48, 0, 100, 200, 240, "Oxygen", 0, 0, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes.dyeWhite, Element.O),
   Palladium("Palladium", 48, 52, GT_ItemTextures.SET_SHINY, 203, 128, 128, 128, 0, "Palladium", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeGray, Element.Pd),
   Phosphor("Phosphor", 49, 21, GT_ItemTextures.SET_DULL, 57, 255, 255, 0, 0, "Phosphor", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeYellow, Element.P),
   Platinum("Platinum", 50, 85, GT_ItemTextures.SET_SHINY, 203, 255, 255, 200, 0, "Platinum", 0, 0, 0, 0, 0, 0, false, false, 6, 1, 1, Dyes.dyeOrange, Element.Pt),
   Plutonium("Plutonium", 51, 100, GT_ItemTextures.SET_METALLIC, 75, 240, 50, 50, 0, "Plutonium 244", 0, 0, 2000000, 0, 0, 0, false, false, 6, 1, 1, Dyes.dyeLime, Element.Pu),
   Plutonium241("Plutonium241", 52, 101, GT_ItemTextures.SET_SHINY, 75, 250, 70, 70, 0, "Plutonium 241", 0, 0, 2000000, 0, 0, 0, false, false, 6, 1, 1, Dyes.dyeLime, Element.Pu_241),
   Potassium("Potassium", 53, 25, GT_ItemTextures.SET_METALLIC, 49, 250, 250, 250, 0, "Potassium", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeWhite, Element.K),
   Praseodymium("Praseodymium", 54, 66, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Praseodymium", 0, 0, 0, 0, 1208, 1208, true, false, 4, 1, 1, Dyes._NULL, Element.Pr),
   Promethium("Promethium", 55, 68, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Promethium", 0, 0, 0, 0, 1315, 1315, true, false, 4, 1, 1, Dyes._NULL, Element.Pm),
   Rubidium("Rubidium", 56, 43, GT_ItemTextures.SET_METALLIC, 11, 240, 30, 30, 0, "Rubidium", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeRed, Element.Rb),
   Samarium("Samarium", 57, 69, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Samarium", 0, 0, 0, 0, 1345, 1345, true, false, 4, 1, 1, Dyes._NULL, Element.Sm),
   Scandium("Scandium", 58, 27, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Scandium", 0, 0, 0, 0, 1814, 1814, true, false, 2, 1, 1, Dyes.dyeYellow, Element.Sc),
   Silicon("Silicon", 59, 20, GT_ItemTextures.SET_METALLIC, 59, 60, 60, 80, 0, "Silicon", 0, 0, 0, 0, 1500, 1500, true, false, 1, 1, 1, Dyes.dyeBlack, Element.Si),
   Silver("Silver", 60, 54, GT_ItemTextures.SET_SHINY, 203, 220, 220, 255, 0, "Silver", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, Element.Ag),
   Sodium("Sodium", 61, 17, GT_ItemTextures.SET_METALLIC, 49, 0, 0, 150, 0, "Sodium", 3, 30, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlue, Element.Na),
   Strontium("Strontium", 62, 44, GT_ItemTextures.SET_METALLIC, 9, 200, 200, 200, 0, "Strontium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Element.Sr),
   Sulfur("Sulfur", 63, 22, GT_ItemTextures.SET_DULL, 57, 200, 200, 0, 0, "Sulfur", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeYellow, Element.S),
   Tantalum("Tantalum", 64, 80, GT_ItemTextures.SET_METALLIC, 11, 255, 255, 255, 0, "Tantalum", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes._NULL, Element.Ta),
   Tellurium("Tellurium", 65, 59, GT_ItemTextures.SET_METALLIC, 11, 255, 255, 255, 0, "Tellurium", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeGray, Element.Te),
   Terbium("Terbium", 66, 72, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Terbium", 0, 0, 0, 0, 1629, 1629, true, false, 4, 1, 1, Dyes._NULL, Element.Tb),
   Thorium("Thorium", 67, 96, GT_ItemTextures.SET_SHINY, 75, 0, 30, 0, 0, "Thorium", 0, 0, 500000, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeBlack, Element.Th),
   Thulium("Thulium", 68, 76, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Thulium", 0, 0, 0, 0, 1818, 1818, true, false, 4, 1, 1, Dyes._NULL, Element.Tm),
   Tin("Tin", 69, 57, GT_ItemTextures.SET_DULL, 139, 220, 220, 220, 0, "Tin", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeWhite, Element.Sn),
   Titanium("Titanium", 70, 28, GT_ItemTextures.SET_METALLIC, 203, 220, 160, 240, 0, "Titanium", 0, 0, 0, 0, 1500, 1500, true, false, 5, 1, 1, Dyes.dyePurple, Element.Ti),
   Tritium("Tritium", 71, 3, GT_ItemTextures.SET_METALLIC, 48, 255, 0, 0, 240, "Tritium", 0, 0, 0, 0, 0, 0, false, true, 10, 1, 1, Dyes.dyeRed, Element.T),
   Tungsten("Tungsten", 72, 81, GT_ItemTextures.SET_METALLIC, 203, 50, 50, 50, 0, "Tungsten", 0, 0, 0, 0, 2500, 2500, true, false, 4, 1, 1, Dyes.dyeBlack, Element.W),
   Uranium("Uranium", 73, 98, GT_ItemTextures.SET_METALLIC, 75, 50, 240, 50, 0, "Uranium 238", 0, 0, 1000000, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeGreen, Element.U),
   Uranium235("Uranium235", 74, 97, GT_ItemTextures.SET_SHINY, 75, 70, 250, 70, 0, "Uranium 235", 0, 0, 1000000, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeGreen, Element.U_235),
   Vanadium("Vanadium", 75, 29, GT_ItemTextures.SET_METALLIC, 11, 50, 50, 50, 0, "Vanadium", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeBlack, Element.V),
   Ytterbium("Ytterbium", 76, 77, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Ytterbium", 0, 0, 0, 0, 1097, 1097, true, false, 4, 1, 1, Dyes._NULL, Element.Yb),
   Yttrium("Yttrium", 77, 45, GT_ItemTextures.SET_METALLIC, 59, 255, 255, 255, 0, "Yttrium", 0, 0, 0, 0, 1799, 1799, true, false, 4, 1, 1, Dyes._NULL, Element.Y),
   Zinc("Zinc", 78, 36, GT_ItemTextures.SET_METALLIC, 11, 250, 250, 250, 0, "Zinc", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeWhite, Element.Zn),
   Organic("Organic", 79, -1, GT_ItemTextures.SET_LEAF, false),
   Crystal("Crystal", 80, -1, GT_ItemTextures.SET_SHINY, false),
   Quartz("Quartz", 81, -1, GT_ItemTextures.SET_QUARTZ, false),
   Metal("Metal", 82, -1, GT_ItemTextures.SET_METALLIC, false),
   Cobblestone("Cobblestone", 83, -1, GT_ItemTextures.SET_DULL, false),
   Adamantium("Adamantium", 84, 319, GT_ItemTextures.SET_SHINY, 203, 255, 255, 255, 0, "Adamantium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Adamite("Adamite", 85, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Adamite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Adluorite("Adluorite", 86, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Adluorite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Agate("Agate", 87, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Agate", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Alduorite("Alduorite", 88, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Alduorite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Amber("Amber", 89, 514, GT_ItemTextures.SET_RUBY, 13, 255, 128, 0, 127, "Amber", 5, 3, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes.dyeOrange),
   Ammonium("Ammonium", 90, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Ammonium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Amordrine("Amordrine", 91, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Amordrine", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Andesite("Andesite", 92, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Andesite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Angmallen("Angmallen", 93, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Angmallen", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Ardite("Ardite", 94, -1, GT_ItemTextures.SET_NONE, 75, 255, 0, 0, 0, "Ardite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow),
   Aredrite("Aredrite", 95, -1, GT_ItemTextures.SET_NONE, 75, 255, 0, 0, 0, "Aredrite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow),
   Atlarus("Atlarus", 96, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Atlarus", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Bitumen("Bitumen", 97, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Bitumen", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Black("Black", 98, -1, GT_ItemTextures.SET_NONE, 0, 0, 0, 0, 0, "Black", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBlack),
   Blizz("Blizz", 99, 851, GT_ItemTextures.SET_SHINY, 1, 220, 233, 255, 0, "Blizz", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Blueschist("Blueschist", 100, 852, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Blueschist", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes.dyeLightBlue),
   Bluestone("Bluestone", 101, 813, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Bluestone", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlue),
   Bloodstone("Bloodstone", 102, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Bloodstone", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeRed),
   Blutonium("Blutonium", 103, -1, GT_ItemTextures.SET_SHINY, 11, 0, 0, 255, 0, "Blutonium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBlue),
   Carmot("Carmot", 104, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Carmot", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Celenegil("Celenegil", 105, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Celenegil", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   CertusQuartz("CertusQuartz", 106, 516, GT_ItemTextures.SET_QUARTZ, 77, 210, 210, 230, 0, "Certus Quartz", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeLightGray),
   Ceruclase("Ceruclase", 107, -1, GT_ItemTextures.SET_NONE, 11, 255, 255, 255, 0, "Ceruclase", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Citrine("Citrine", 108, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Citrine", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   CobaltHexahydrate("CobaltHexahydrate", 109, 853, GT_ItemTextures.SET_METALLIC, 17, 80, 80, 250, 0, "Cobalt Hexahydrate", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlue),
   ConstructionFoam("ConstructionFoam", 110, 854, GT_ItemTextures.SET_DULL, 17, 128, 128, 128, 0, "Construction Foam", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray),
   Chalk("Chalk", 111, 856, GT_ItemTextures.SET_FINE, 1, 250, 250, 250, 0, "Chalk", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes.dyeWhite),
   Chert("Chert", 112, 857, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Chert", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes._NULL),
   Chimerite("Chimerite", 113, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Chimerite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Coral("Coral", 114, -1, GT_ItemTextures.SET_NONE, 1, 255, 128, 255, 0, "Coral", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   CrudeOil("CrudeOil", 115, 858, GT_ItemTextures.SET_DULL, 1, 10, 10, 10, 0, "Crude Oil", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack),
   Chrysocolla("Chrysocolla", 116, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Chrysocolla", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   CrystalFlux("CrystalFlux", 117, 517, GT_ItemTextures.SET_QUARTZ, 5, 100, 50, 100, 0, "Flux Crystal", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Cyanite("Cyanite", 118, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Cyanite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeCyan),
   Dacite("Dacite", 119, 859, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Dacite", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes.dyeLightGray),
   DarkIron("DarkIron", 120, 342, GT_ItemTextures.SET_DULL, 75, 55, 40, 60, 0, "Dark Iron", 0, 0, 0, 0, 0, 0, false, false, 5, 1, 1, Dyes.dyePurple),
   DarkStone("DarkStone", 121, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Dark Stone", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBlack),
   Demonite("Demonite", 122, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Demonite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeRed),
   Desh("Desh", 123, 884, GT_ItemTextures.SET_DULL, 11, 40, 40, 40, 0, "Desh", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack),
   Desichalkos("Desichalkos", 124, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Desichalkos", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Dilithium("Dilithium", 125, 515, GT_ItemTextures.SET_DIAMOND, 29, 255, 250, 250, 127, "Dilithium", 0, 0, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes.dyeWhite),
   Draconic("Draconic", 126, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Draconic", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeRed),
   Duranium("Duranium", 127, 328, GT_ItemTextures.SET_METALLIC, 67, 255, 255, 255, 0, "Duranium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Eclogite("Eclogite", 128, 860, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Eclogite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   ElectrumFlux("ElectrumFlux", 129, 320, GT_ItemTextures.SET_SHINY, 67, 255, 255, 120, 0, "Fluxed Electrum", 0, 0, 0, 0, 3000, 3000, true, false, 1, 1, 1, Dyes.dyeYellow),
   Emery("Emery", 130, 861, GT_ItemTextures.SET_DULL, 9, 255, 255, 255, 0, "Emery", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Enderium("Enderium", 131, 321, GT_ItemTextures.SET_DULL, 67, 89, 145, 135, 0, "Enderium", 0, 0, 0, 0, 3000, 3000, true, false, 1, 1, 1, Dyes.dyeGreen),
   Energized("Energized", 132, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Energized", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Epidote("Epidote", 133, 862, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Epidote", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes._NULL),
   Eximite("Eximite", 134, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Eximite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   FieryBlood("FieryBlood", 135, -1, GT_ItemTextures.SET_NONE, 67, 255, 255, 255, 0, "Fiery Blood", 5, 2048, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeRed),
   Firestone("Firestone", 136, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Firestone", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeOrange),
   Fluorite("Fluorite", 137, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Fluorite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeGreen),
   FoolsRuby("FoolsRuby", 138, 512, GT_ItemTextures.SET_RUBY, 13, 255, 100, 100, 127, "Ruby", 0, 0, 0, 0, 0, 0, false, true, 3, 1, 1, Dyes.dyeRed),
   Force("Force", 139, 521, GT_ItemTextures.SET_DIAMOND, 207, 255, 255, 0, 0, "Force", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeYellow),
   Forcicium("Forcicium", 140, 518, GT_ItemTextures.SET_DIAMOND, 29, 50, 50, 70, 0, "Forcicium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeGreen),
   Forcillium("Forcillium", 141, 519, GT_ItemTextures.SET_DIAMOND, 29, 50, 50, 70, 0, "Forcillium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeGreen),
   Gabbro("Gabbro", 142, 863, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Gabbro", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes._NULL),
   Glowstone("Glowstone", 143, 811, GT_ItemTextures.SET_SHINY, 17, 255, 255, 0, 0, "Glowstone", 0, 0, 25000, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow),
   Gneiss("Gneiss", 144, 864, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Gneiss", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes._NULL),
   Graphite("Graphite", 145, 865, GT_ItemTextures.SET_DULL, 201, 128, 128, 128, 0, "Graphite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeGray),
   Greenschist("Greenschist", 146, 866, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Green Schist", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGreen),
   Greenstone("Greenstone", 147, 867, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Greenstone", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGreen),
   Greywacke("Greywacke", 148, 868, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Greywacke", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray),
   Haderoth("Haderoth", 149, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Haderoth", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Hematite("Hematite", 150, -1, GT_ItemTextures.SET_NONE, 11, 255, 255, 255, 0, "Hematite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Hepatizon("Hepatizon", 151, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Hepatizon", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   HSLA("HSLA", 152, 322, GT_ItemTextures.SET_METALLIC, 195, 128, 128, 128, 0, "HSLA Steel", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Ignatius("Ignatius", 153, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Ignatius", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Infernal("Infernal", 154, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Infernal", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Infuscolium("Infuscolium", 155, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Infuscolium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   InfusedGold("InfusedGold", 156, 323, GT_ItemTextures.SET_SHINY, 203, 255, 200, 60, 0, "Infused Gold", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeYellow),
   InfusedAir("InfusedAir", 157, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Infused Air", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeYellow),
   InfusedFire("InfusedFire", 158, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Infused Fire", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeRed),
   InfusedEarth("InfusedEarth", 159, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Infused Earth", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeGreen),
   InfusedWater("InfusedWater", 160, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Infused Water", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBlue),
   InfusedVis("InfusedVis", 161, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Infused Vis", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyePurple),
   InfusedDull("InfusedDull", 162, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Infused Dull", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeLightGray),
   InfusedEntropy("InfusedEntropy", 163, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Infused Entropy", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   InfusedOrder("InfusedOrder", 164, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Infused Order", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Inolashite("Inolashite", 165, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Inolashite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Invisium("Invisium", 166, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Invisium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Jade("Jade", 167, 537, GT_ItemTextures.SET_SHINY, 9, 0, 100, 0, 0, "Jade", 0, 0, 0, 0, 0, 0, false, false, 5, 1, 1, Dyes.dyeGreen),
   Jasper("Jasper", 168, 511, GT_ItemTextures.SET_EMERALD, 13, 200, 80, 80, 100, "Jasper", 0, 0, 0, 0, 0, 0, false, true, 3, 1, 1, Dyes.dyeRed),
   Kalendrite("Kalendrite", 169, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Kalendrite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Komatiite("Komatiite", 170, 869, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Komatiite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow),
   Lava("Lava", 171, 700, GT_ItemTextures.SET_FLUID, 16, 255, 64, 0, 0, "Lava", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange),
   Lemurite("Lemurite", 172, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Lemurite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Limestone("Limestone", 173, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Limestone", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Lodestone("Lodestone", 174, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Lodestone", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Luminite("Luminite", 175, -1, GT_ItemTextures.SET_NONE, 9, 250, 250, 250, 0, "Luminite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeWhite),
   Magma("Magma", 176, -1, GT_ItemTextures.SET_NONE, 0, 255, 64, 0, 0, "Magma", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange),
   Mawsitsit("Mawsitsit", 177, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Mawsitsit", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Mercassium("Mercassium", 178, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Mercassium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   MeteoricIron("MeteoricIron", 179, 340, GT_ItemTextures.SET_METALLIC, 75, 100, 50, 80, 0, "Meteoric Iron", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray),
   MeteoricSteel("MeteoricSteel", 180, 341, GT_ItemTextures.SET_METALLIC, 67, 50, 25, 40, 0, "Meteoric Steel", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray),
   Meteorite("Meteorite", 181, -1, GT_ItemTextures.SET_NONE, 9, 80, 35, 60, 0, "Meteorite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePurple),
   Meutoite("Meutoite", 182, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Meutoite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Migmatite("Migmatite", 183, 872, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Migmatite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Mimichite("Mimichite", 184, -1, GT_ItemTextures.SET_GEM_VERTICAL, 13, 255, 255, 255, 0, "Mimichite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Monazite("Monazite", 185, 520, GT_ItemTextures.SET_DIAMOND, 13, 50, 70, 50, 0, "Monazite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeGreen),
   Moonstone("Moonstone", 186, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Moonstone", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeWhite),
   Naquadah("Naquadah", 187, 324, GT_ItemTextures.SET_METALLIC, 91, 50, 50, 50, 0, "Naquadah", 0, 0, 0, 0, 0, 0, false, false, 10, 1, 1, Dyes.dyeBlack),
   NaquadahAlloy("NaquadahAlloy", 188, 325, GT_ItemTextures.SET_METALLIC, 195, 40, 40, 40, 0, "Naquadah Alloy", 0, 0, 0, 0, 0, 0, false, false, 10, 1, 1, Dyes.dyeBlack),
   Nether("Nether", 189, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Nether", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   NetherBrick("NetherBrick", 190, 814, GT_ItemTextures.SET_DULL, 1, 100, 0, 0, 0, "Nether Brick", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeRed),
   NetherQuartz("NetherQuartz", 191, 522, GT_ItemTextures.SET_QUARTZ, 13, 230, 210, 210, 0, "Nether Quartz", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeWhite),
   NetherStar("NetherStar", 192, 506, GT_ItemTextures.SET_NETHERSTAR, 5, 255, 255, 255, 0, "Nether Star", 5, '\uc350', 0, 0, 0, 0, false, false, 15, 1, 1, Dyes.dyeWhite),
   Nikolite("Nikolite", 193, 812, GT_ItemTextures.SET_SHINY, 9, 60, 180, 200, 0, "Nikolite", 0, 0, 5000, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeCyan),
   ObsidianFlux("ObsidianFlux", 194, -1, GT_ItemTextures.SET_DULL, 3, 80, 50, 100, 0, "Fluxed Obsidian", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePurple),
   Oilsands("Oilsands", 195, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Oilsands", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Onyx("Onyx", 196, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Onyx", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Orichalcum("Orichalcum", 197, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Orichalcum", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Osmonium("Osmonium", 198, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Osmonium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBlue),
   Oureclase("Oureclase", 199, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Oureclase", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Painite("Painite", 200, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Painite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Peanutwood("Peanutwood", 201, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Peanut Wood", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Petroleum("Petroleum", 202, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Petroleum", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Pewter("Pewter", 203, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Pewter", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Pitchblende("Pitchblende", 204, 873, GT_ItemTextures.SET_DULL, 9, 200, 210, 0, 0, "Pitchblende", 0, 0, 0, 0, 0, 0, false, false, 5, 1, 1, Dyes.dyeYellow),
   Phoenixite("Phoenixite", 205, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Phoenixite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Potash("Potash", 206, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Potash", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Prometheum("Prometheum", 207, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Prometheum", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Quartzite("Quartzite", 208, 523, GT_ItemTextures.SET_QUARTZ, 5, 210, 230, 210, 0, "Quartzite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeWhite),
   Quicklime("Quicklime", 209, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Quicklime", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Randomite("Randomite", 210, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Randomite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   RefinedGlowstone("RefinedGlowstone", 211, 326, GT_ItemTextures.SET_METALLIC, 3, 255, 255, 0, 0, "Refined Glowstone", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow),
   RefinedObsidian("RefinedObsidian", 212, 327, GT_ItemTextures.SET_METALLIC, 3, 80, 50, 100, 0, "Refined Obsidian", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePurple),
   Rhyolite("Rhyolite", 213, 875, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Rhyolite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Rubracium("Rubracium", 214, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Rubracium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   RyuDragonRyder("RyuDragonRyder", 215, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Ryu Dragon Ryder", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Sand("Sand", 216, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Sand", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow),
   Sanguinite("Sanguinite", 217, -1, GT_ItemTextures.SET_NONE, 11, 255, 255, 255, 0, "Sanguinite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Siltstone("Siltstone", 218, 876, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Siltstone", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Soapstone("Soapstone", 219, 877, GT_ItemTextures.SET_DULL, 1, 95, 145, 95, 0, "Soapstone", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Spinel("Spinel", 220, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Spinel", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Starconium("Starconium", 221, -1, GT_ItemTextures.SET_NONE, 11, 255, 255, 255, 0, "Starconium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Sugilite("Sugilite", 222, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Sugilite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Sunstone("Sunstone", 223, -1, GT_ItemTextures.SET_NONE, 9, 255, 255, 255, 0, "Sunstone", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeYellow),
   Tar("Tar", 224, -1, GT_ItemTextures.SET_NONE, 0, 10, 10, 10, 0, "Tar", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack),
   Tartarite("Tartarite", 225, -1, GT_ItemTextures.SET_NONE, 11, 255, 255, 255, 0, "Tartarite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Tapazite("Tapazite", 226, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Tapazite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeGreen),
   Thyrium("Thyrium", 227, -1, GT_ItemTextures.SET_NONE, 11, 255, 255, 255, 0, "Thyrium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Tourmaline("Tourmaline", 228, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Tourmaline", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   Tritanium("Tritanium", 229, 329, GT_ItemTextures.SET_METALLIC, 67, 255, 255, 255, 0, "Tritanium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite),
   Turquoise("Turquoise", 230, -1, GT_ItemTextures.SET_NONE, 1, 255, 255, 255, 0, "Turquoise", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes._NULL),
   UUMatter("UUMatter", 231, 703, GT_ItemTextures.SET_FLUID, 16, 128, 0, 196, 0, "UUMatter", 0, 0, 0, 0, 0, 0, false, false, 10, 1, 1, Dyes.dyePink),
   Void("Void", 232, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 200, "Void", 0, 0, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes._NULL),
   Voidstone("Voidstone", 233, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 200, "Voidstone", 0, 0, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes._NULL),
   Vulcanite("Vulcanite", 234, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Vulcanite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Vyroxeres("Vyroxeres", 235, -1, GT_ItemTextures.SET_NONE, 75, 255, 255, 255, 0, "Vyroxeres", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL),
   Wimalite("Wimalite", 236, -1, GT_ItemTextures.SET_NONE, 8, 255, 255, 255, 0, "Wimalite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeYellow),
   Yellorite("Yellorite", 237, -1, GT_ItemTextures.SET_NONE, 8, 255, 255, 255, 0, "Yellorite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeYellow),
   Yellorium("Yellorium", 238, -1, GT_ItemTextures.SET_NONE, 3, 255, 255, 255, 0, "Yellorium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeYellow),
   Zectium("Zectium", 239, -1, GT_ItemTextures.SET_NONE, 11, 255, 255, 255, 0, "Zectium", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBlack),
   Advanced("Advanced", 240, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Advanced", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Basic("Basic", 241, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Basic", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Antimatter("Antimatter", 242, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Antimatter", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePink),
   BioFuel("BioFuel", 243, 705, GT_ItemTextures.SET_FLUID, 16, 255, 128, 0, 0, "Biofuel", 0, 6, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange),
   Biomass("Biomass", 244, 704, GT_ItemTextures.SET_FLUID, 16, 0, 255, 0, 0, "Biomass", 3, 8, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGreen),
   Chocolate("Chocolate", 245, 886, GT_ItemTextures.SET_FINE, 1, 190, 95, 0, 0, "Chocolate", 0, 0, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes.dyeWhite),
   Cluster("Cluster", 246, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 127, "Cluster", 0, 0, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes.dyeWhite),
   CoalFuel("CoalFuel", 247, 710, GT_ItemTextures.SET_FLUID, 16, 50, 50, 70, 0, "Coalfuel", 0, 16, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack),
   Coffee("Coffee", 248, 887, GT_ItemTextures.SET_FINE, 1, 150, 75, 0, 0, "Coffee", 0, 0, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes.dyeWhite),
   Creosote("Creosote", 249, 712, GT_ItemTextures.SET_FLUID, 16, 128, 64, 0, 0, "Creosote", 3, 3, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBrown),
   Data("Data", 250, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Data", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Elite("Elite", 251, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Elite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Ethanol("Ethanol", 252, 706, GT_ItemTextures.SET_FLUID, 16, 255, 128, 0, 0, "Ethanol", 0, 128, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePurple),
   Fuel("Fuel", 253, 708, GT_ItemTextures.SET_FLUID, 16, 255, 255, 0, 0, "Diesel", 0, 128, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow),
   Good("Good", 254, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Good", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Gunpowder("Gunpowder", 255, 800, GT_ItemTextures.SET_DULL, 1, 128, 128, 128, 0, "Gunpowder", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray),
   Infinite("Infinite", 256, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Infinite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   LimePure("LimePure", 257, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Pure Lime", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLime),
   Master("Master", 258, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Master", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Meat("Meat", 259, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Meat", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePink),
   MeatRaw("MeatRaw", 260, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Raw Meat", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePink),
   MeatCooked("MeatCooked", 261, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Cooked Meat", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePink),
   Milk("Milk", 262, 885, GT_ItemTextures.SET_FINE, 17, 254, 254, 254, 0, "Milk", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite),
   Mud("Mud", 263, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Mud", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBrown),
   Oil("Oil", 264, 707, GT_ItemTextures.SET_FLUID, 16, 0, 0, 0, 0, "Oil", 3, 16, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack),
   Paper("Paper", 265, 879, GT_ItemTextures.SET_PAPER, 1, 250, 250, 250, 0, "Paper", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite),
   Peat("Peat", 266, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Peat", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBrown),
   Primitive("Primitive", 267, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Primitive", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Quantum("Quantum", 268, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Quantum", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite),
   Red("Red", 269, -1, GT_ItemTextures.SET_NONE, 0, 255, 0, 0, 0, "Red", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeRed),
   Reinforced("Reinforced", 270, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Reinforced", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray),
   SeedOil("SeedOil", 271, 713, GT_ItemTextures.SET_FLUID, 16, 196, 255, 0, 0, "Seed Oil", 3, 2, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLime),
   Stone("Stone", 272, 299, GT_ItemTextures.SET_ROUGH, 193, 205, 205, 205, 0, "Stone", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   TNT("TNT", 273, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "TNT", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeRed),
   Ultimate("Ultimate", 274, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 0, "Ultimate", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray),
   Unstable("Unstable", 275, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 127, "Unstable", 0, 0, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes.dyeWhite),
   Unstableingot("Unstableingot", 276, -1, GT_ItemTextures.SET_NONE, 0, 255, 255, 255, 127, "Unstable", 0, 0, 0, 0, 0, 0, false, true, 1, 1, 1, Dyes.dyeWhite),
   Wheat("Wheat", 277, 881, GT_ItemTextures.SET_POWDER, 1, 255, 255, 196, 0, "Wheat", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow),
   Wood("Wood", 278, 809, GT_ItemTextures.SET_WOOD, 193, 200, 100, 0, 0, "Wood", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBrown),
   AluminiumBrass("AluminiumBrass", 279, -1, GT_ItemTextures.SET_METALLIC, 67, 255, 255, 255, 0, "Aluminium Brass", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow),
   Osmiridium("Osmiridium", 280, 317, GT_ItemTextures.SET_METALLIC, 195, 100, 100, 255, 0, "Osmiridium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightBlue),
   Sunnarium("Sunnarium", 281, 318, GT_ItemTextures.SET_SHINY, 3, 255, 255, 0, 0, "Sunnarium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow),
   Endstone("Endstone", 282, 808, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Endstone", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes.dyeYellow),
   Netherrack("Netherrack", 283, 807, GT_ItemTextures.SET_DULL, 1, 200, 0, 0, 0, "Netherrack", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes.dyeRed),
   SoulSand("SoulSand", 284, -1, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Soulsand", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes.dyeBrown),
   Almandine("Almandine", 285, 820, GT_ItemTextures.SET_ROUGH, 9, 255, 0, 0, 0, "Almandine", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeRed, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 2L), new MaterialStack(Iron, 3L), new MaterialStack(Silicon, 3L), new MaterialStack(Oxygen, 12L)})),
   Andradite("Andradite", 286, 821, GT_ItemTextures.SET_ROUGH, 9, 150, 120, 0, 0, "Andradite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeYellow, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 3L), new MaterialStack(Iron, 2L), new MaterialStack(Silicon, 3L), new MaterialStack(Oxygen, 12L)})),
   Asbestos("Asbestos", 287, 946, GT_ItemTextures.SET_DULL, 9, 230, 230, 230, 0, "Asbestos", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnesium, 3L), new MaterialStack(Silicon, 2L), new MaterialStack(Hydrogen, 4L), new MaterialStack(Oxygen, 9L)})),
   Ash("Ash", 288, 815, GT_ItemTextures.SET_DULL, 1, 150, 150, 150, 0, "Ashes", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 1L)})),
   BandedIron("BandedIron", 289, 917, GT_ItemTextures.SET_DULL, 9, 145, 90, 90, 0, "Banded Iron", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBrown, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 2L), new MaterialStack(Oxygen, 3L)})),
   BatteryAlloy("BatteryAlloy", 290, 315, GT_ItemTextures.SET_DULL, 3, 156, 124, 160, 0, "Battery Alloy", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePurple, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Lead, 4L), new MaterialStack(Antimony, 1L)})),
   Bauxite("Bauxite", 291, 822, GT_ItemTextures.SET_DULL, 9, 200, 100, 0, 0, "Bauxite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBrown, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Titanium, 1L), new MaterialStack(Aluminium, 16L), new MaterialStack(Hydrogen, 10L), new MaterialStack(Oxygen, 12L)})),
   BlueTopaz("BlueTopaz", 292, 513, GT_ItemTextures.SET_GEM_HORIZONTAL, 77, 0, 0, 255, 127, "Blue Topaz", 0, 0, 0, 0, 0, 0, false, true, 3, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 2L), new MaterialStack(Silicon, 1L), new MaterialStack(Fluorine, 2L), new MaterialStack(Hydrogen, 2L), new MaterialStack(Oxygen, 6L)})),
   Bone("Bone", 293, 806, GT_ItemTextures.SET_DULL, 0, 250, 250, 250, 0, "Bone", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 1L)})),
   Brass("Brass", 294, 301, GT_ItemTextures.SET_METALLIC, 195, 255, 180, 0, 0, "Brass", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Zinc, 1L), new MaterialStack(Copper, 3L)})),
   Bronze("Bronze", 295, 300, GT_ItemTextures.SET_METALLIC, 195, 255, 128, 0, 0, "Bronze", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Tin, 1L), new MaterialStack(Copper, 3L)})),
   BrownLimonite("BrownLimonite", 296, 930, GT_ItemTextures.SET_METALLIC, 9, 200, 100, 0, 0, "Brown Limonite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBrown, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 1L), new MaterialStack(Hydrogen, 1L), new MaterialStack(Oxygen, 2L)})),
   Calcite("Calcite", 297, 823, GT_ItemTextures.SET_DULL, 1, 250, 230, 220, 0, "Calcite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 1L), new MaterialStack(Carbon, 1L), new MaterialStack(Oxygen, 3L)})),
   Cassiterite("Cassiterite", 298, 824, GT_ItemTextures.SET_METALLIC, 8, 220, 220, 220, 0, "Cassiterite", 0, 0, 0, 0, 0, 0, false, false, 4, 3, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Tin, 1L), new MaterialStack(Oxygen, 2L)})),
   CassiteriteSand("CassiteriteSand", 299, 937, GT_ItemTextures.SET_SAND, 8, 220, 220, 220, 0, "Cassiterite Sand", 0, 0, 0, 0, 0, 0, false, false, 4, 3, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Tin, 1L), new MaterialStack(Oxygen, 2L)})),
   Celestine("Celestine", 300, 913, GT_ItemTextures.SET_DULL, 9, 200, 205, 240, 0, "Celestine", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Strontium, 1L), new MaterialStack(Sulfur, 1L), new MaterialStack(Oxygen, 4L)})),
   Chalcopyrite("Chalcopyrite", 301, 855, GT_ItemTextures.SET_DULL, 9, 160, 120, 40, 0, "Chalcopyrite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Copper, 1L), new MaterialStack(Iron, 1L), new MaterialStack(Sulfur, 2L)})),
   Charcoal("Charcoal", 302, 536, GT_ItemTextures.SET_FINE, 5, 100, 70, 70, 0, "Charcoal", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 1L)})),
   Chromite("Chromite", 303, 825, GT_ItemTextures.SET_METALLIC, 9, 35, 20, 15, 0, "Chromite", 0, 0, 0, 0, 1700, 1700, true, false, 6, 1, 1, Dyes.dyePink, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 1L), new MaterialStack(Chrome, 2L), new MaterialStack(Oxygen, 4L)})),
   Cinnabar("Cinnabar", 304, 826, GT_ItemTextures.SET_ROUGH, 9, 150, 0, 0, 0, "Cinnabar", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBrown, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Mercury, 1L), new MaterialStack(Sulfur, 1L)})),
   Clay("Clay", 305, 805, GT_ItemTextures.SET_DULL, 1, 255, 255, 255, 0, "Clay", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeLightBlue, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Sodium, 2L), new MaterialStack(Lithium, 1L), new MaterialStack(Aluminium, 2L), new MaterialStack(Silicon, 2L)})),
   Coal("Coal", 306, 535, GT_ItemTextures.SET_ROUGH, 13, 70, 70, 70, 0, "Coal", 0, 0, 0, 0, 0, 0, false, false, 2, 2, 1, Dyes.dyeBlack, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 1L)})),
   Cobaltite("Cobaltite", 307, 827, GT_ItemTextures.SET_METALLIC, 9, 80, 80, 250, 0, "Cobaltite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Cobalt, 1L), new MaterialStack(Arsenic, 1L), new MaterialStack(Sulfur, 1L)})),
   Cooperite("Cooperite", 308, 828, GT_ItemTextures.SET_METALLIC, 9, 255, 255, 200, 0, "Cooperite", 0, 0, 0, 0, 0, 0, false, false, 5, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Platinum, 3L), new MaterialStack(Nickel, 1L), new MaterialStack(Sulfur, 1L), new MaterialStack(Palladium, 1L)})),
   Cupronickel("Cupronickel", 309, 310, GT_ItemTextures.SET_METALLIC, 67, 227, 150, 128, 0, "Cupronickel", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Copper, 1L), new MaterialStack(Nickel, 1L)})),
   DarkAsh("DarkAsh", 310, 816, GT_ItemTextures.SET_DULL, 1, 50, 50, 50, 0, "Dark Ashes", 0, 0, 0, 0, 0, 0, false, false, 1, 2, 1, Dyes.dyeGray, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 1L)})),
   DeepIron("DeepIron", 311, 829, GT_ItemTextures.SET_METALLIC, 75, 150, 140, 140, 0, "Deep Iron", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyePink, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 1L)})),
   Diamond("Diamond", 312, 500, GT_ItemTextures.SET_DIAMOND, 205, 200, 255, 255, 127, "Diamond", 0, 0, 0, 0, 0, 0, false, true, 5, 128, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 1L)})),
   Electrum("Electrum", 313, 303, GT_ItemTextures.SET_SHINY, 195, 255, 255, 100, 0, "Electrum", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Silver, 1L), new MaterialStack(Gold, 1L)})),
   Emerald("Emerald", 314, 501, GT_ItemTextures.SET_EMERALD, 77, 80, 255, 80, 127, "Emerald", 0, 0, 0, 0, 0, 0, false, true, 5, 1, 1, Dyes.dyeGreen, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Beryllium, 3L), new MaterialStack(Aluminium, 2L), new MaterialStack(Silicon, 6L), new MaterialStack(Oxygen, 18L)})),
   Galena("Galena", 315, 830, GT_ItemTextures.SET_DULL, 9, 100, 60, 100, 0, "Galena", 0, 0, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyePurple, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Lead, 3L), new MaterialStack(Silver, 3L), new MaterialStack(Sulfur, 2L)})),
   Garnierite("Garnierite", 316, 906, GT_ItemTextures.SET_METALLIC, 9, 50, 200, 70, 0, "Garnierite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightBlue, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Nickel, 1L), new MaterialStack(Oxygen, 1L)})),
   Glyceryl("Glyceryl", 317, 714, GT_ItemTextures.SET_FLUID, 16, 0, 150, 150, 0, "Glyceryl", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 3L), new MaterialStack(Hydrogen, 5L), new MaterialStack(Nitrogen, 3L), new MaterialStack(Oxygen, 9L)})),
   GreenSapphire("GreenSapphire", 318, 504, GT_ItemTextures.SET_GEM_HORIZONTAL, 77, 100, 255, 130, 127, "Green Sapphire", 0, 0, 0, 0, 0, 0, false, true, 5, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 2L), new MaterialStack(Oxygen, 3L)})),
   Grossular("Grossular", 319, 831, GT_ItemTextures.SET_ROUGH, 9, 200, 100, 0, 0, "Grossular", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 3L), new MaterialStack(Aluminium, 2L), new MaterialStack(Silicon, 3L), new MaterialStack(Oxygen, 12L)})),
   Ice("Ice", 320, 702, GT_ItemTextures.SET_SHINY, 17, 200, 200, 255, 0, "Ice", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlue, 0, Arrays.asList(new MaterialStack[]{new MaterialStack(Hydrogen, 2L), new MaterialStack(Oxygen, 1L)})),
   Ilmenite("Ilmenite", 321, 918, GT_ItemTextures.SET_METALLIC, 9, 70, 55, 50, 0, "Ilmenite", 0, 0, 0, 0, 0, 0, false, false, 1, 2, 1, Dyes.dyePurple, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 1L), new MaterialStack(Titanium, 1L), new MaterialStack(Oxygen, 3L)})),
   Invar("Invar", 322, 302, GT_ItemTextures.SET_METALLIC, 195, 180, 180, 120, 0, "Invar", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBrown, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 2L), new MaterialStack(Nickel, 1L)})),
   Kanthal("Kanthal", 323, 312, GT_ItemTextures.SET_METALLIC, 67, 194, 210, 223, 0, "Kanthal", 0, 0, 0, 0, 2500, 2500, true, false, 1, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 1L), new MaterialStack(Aluminium, 1L), new MaterialStack(Chrome, 1L)})),
   Lazurite("Lazurite", 324, 524, GT_ItemTextures.SET_LAPIS, 13, 100, 120, 255, 0, "Lazurite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 6L), new MaterialStack(Silicon, 6L), new MaterialStack(Calcium, 8L), new MaterialStack(Sodium, 8L)})),
   LiveRoot("LiveRoot", 325, 832, GT_ItemTextures.SET_WOOD, 1, 220, 200, 0, 0, "Liveroot", 5, 16, 0, 0, 0, 0, false, false, 2, 4, 3, Dyes.dyeBrown, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Wood, 3L), new MaterialStack(Magic, 1L)})),
   Magnalium("Magnalium", 326, 313, GT_ItemTextures.SET_DULL, 195, 200, 190, 255, 0, "Magnalium", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightBlue, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnesium, 1L), new MaterialStack(Aluminium, 2L)})),
   Magnesite("Magnesite", 327, 908, GT_ItemTextures.SET_METALLIC, 9, 250, 250, 180, 0, "Magnesite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePink, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnesium, 1L), new MaterialStack(Carbon, 1L), new MaterialStack(Oxygen, 3L)})),
   Magnetite("Magnetite", 328, 870, GT_ItemTextures.SET_METALLIC, 9, 30, 30, 30, 0, "Magnetite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 3L), new MaterialStack(Oxygen, 4L)})),
   Methane("Methane", 329, 715, GT_ItemTextures.SET_FLUID, 16, 255, 255, 255, 0, "Methane", 1, 45, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeMagenta, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 1L), new MaterialStack(Hydrogen, 4L)})),
   Molybdenite("Molybdenite", 330, 942, GT_ItemTextures.SET_METALLIC, 9, 25, 25, 25, 0, "Molybdenite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Molybdenum, 1L), new MaterialStack(Sulfur, 2L)})),
   Nichrome("Nichrome", 331, 311, GT_ItemTextures.SET_METALLIC, 67, 205, 206, 246, 0, "Nichrome", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Nickel, 4L), new MaterialStack(Chrome, 1L)})),
   NitroCarbon("NitroCarbon", 332, 716, GT_ItemTextures.SET_FLUID, 16, 0, 75, 100, 0, "Nitro-Carbon", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Nitrogen, 1L), new MaterialStack(Carbon, 1L)})),
   NitrogenDioxide("NitrogenDioxide", 333, 717, GT_ItemTextures.SET_FLUID, 16, 100, 175, 255, 0, "Nitrogen Dioxide", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Nitrogen, 1L), new MaterialStack(Oxygen, 2L)})),
   Obsidian("Obsidian", 334, 804, GT_ItemTextures.SET_DULL, 1, 80, 50, 100, 0, "Obsidian", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnesium, 1L), new MaterialStack(Iron, 1L), new MaterialStack(Silicon, 2L), new MaterialStack(Oxygen, 8L)})),
   Phosphate("Phosphate", 335, 833, GT_ItemTextures.SET_DULL, 25, 255, 255, 0, 0, "Phosphate", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeYellow, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Phosphor, 1L), new MaterialStack(Oxygen, 4L)})),
   PigIron("PigIron", 336, 307, GT_ItemTextures.SET_METALLIC, 75, 200, 180, 180, 0, "Pig Iron", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyePink, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 1L)})),
   Plastic("Plastic", 337, 874, GT_ItemTextures.SET_DULL, 195, 200, 200, 200, 0, "Plastic", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 1L), new MaterialStack(Hydrogen, 2L)})),
   Powellite("Powellite", 338, 883, GT_ItemTextures.SET_DULL, 9, 255, 255, 0, 0, "Powellite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 1L), new MaterialStack(Molybdenum, 1L), new MaterialStack(Oxygen, 4L)})),
   Pumice("Pumice", 339, 926, GT_ItemTextures.SET_DULL, 9, 230, 185, 185, 0, "Pumice", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Stone, 1L)})),
   Pyrite("Pyrite", 340, 834, GT_ItemTextures.SET_ROUGH, 9, 150, 120, 40, 0, "Pyrite", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 1L), new MaterialStack(Sulfur, 2L)})),
   Pyrolusite("Pyrolusite", 341, 943, GT_ItemTextures.SET_DULL, 9, 150, 150, 170, 0, "Pyrolusite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Manganese, 1L), new MaterialStack(Oxygen, 2L)})),
   Pyrope("Pyrope", 342, 835, GT_ItemTextures.SET_METALLIC, 9, 120, 50, 100, 0, "Pyrope", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyePurple, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 2L), new MaterialStack(Magnesium, 3L), new MaterialStack(Silicon, 3L), new MaterialStack(Oxygen, 12L)})),
   RockSalt("RockSalt", 343, 944, GT_ItemTextures.SET_FINE, 9, 240, 200, 200, 0, "Rock Salt", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Potassium, 1L), new MaterialStack(Chlorine, 1L)})),
   Rubber("Rubber", 344, 880, GT_ItemTextures.SET_SHINY, 195, 0, 0, 0, 0, "Rubber", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 0, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 5L), new MaterialStack(Hydrogen, 8L)})),
   Ruby("Ruby", 345, 502, GT_ItemTextures.SET_RUBY, 77, 255, 100, 100, 127, "Ruby", 0, 0, 0, 0, 0, 0, false, true, 5, 1, 1, Dyes.dyeRed, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Chrome, 1L), new MaterialStack(Aluminium, 2L), new MaterialStack(Oxygen, 3L)})),
   Salt("Salt", 346, 817, GT_ItemTextures.SET_FINE, 9, 250, 250, 250, 0, "Salt", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Sodium, 1L), new MaterialStack(Chlorine, 1L)})),
   Saltpeter("Saltpeter", 347, 836, GT_ItemTextures.SET_FINE, 9, 230, 230, 230, 0, "Saltpeter", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Potassium, 1L), new MaterialStack(Nitrogen, 1L), new MaterialStack(Oxygen, 3L)})),
   Sapphire("Sapphire", 348, 503, GT_ItemTextures.SET_GEM_VERTICAL, 77, 100, 100, 200, 127, "Sapphire", 0, 0, 0, 0, 0, 0, false, true, 5, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 2L), new MaterialStack(Oxygen, 3L)})),
   Scheelite("Scheelite", 349, 910, GT_ItemTextures.SET_DULL, 9, 200, 140, 20, 0, "Scheelite", 0, 0, 0, 0, 2500, 2500, false, false, 4, 1, 1, Dyes.dyeBlack, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Tungsten, 1L), new MaterialStack(Calcium, 2L), new MaterialStack(Oxygen, 4L)})),
   SiliconDioxide("SiliconDioxide", 350, 837, GT_ItemTextures.SET_QUARTZ, 17, 255, 255, 255, 0, "Silicon Dioxide", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Silicon, 1L), new MaterialStack(Oxygen, 2L)})),
   Sodalite("Sodalite", 351, 525, GT_ItemTextures.SET_LAPIS, 13, 20, 20, 255, 0, "Sodalite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 3L), new MaterialStack(Silicon, 3L), new MaterialStack(Sodium, 4L), new MaterialStack(Chlorine, 1L)})),
   SodiumPersulfate("SodiumPersulfate", 352, 718, GT_ItemTextures.SET_FLUID, 16, 255, 255, 255, 0, "Sodium Persulfate", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Sodium, 1L), new MaterialStack(Sulfur, 1L), new MaterialStack(Oxygen, 4L)})),
   SodiumSulfide("SodiumSulfide", 353, 719, GT_ItemTextures.SET_FLUID, 16, 255, 255, 255, 0, "Sodium Sulfide", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Sodium, 1L), new MaterialStack(Sulfur, 1L)})),
   SolderingAlloy("SolderingAlloy", 354, 314, GT_ItemTextures.SET_DULL, 3, 220, 220, 230, 0, "Soldering Alloy", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Tin, 9L), new MaterialStack(Antimony, 1L)})),
   Spessartine("Spessartine", 355, 838, GT_ItemTextures.SET_DULL, 9, 255, 100, 100, 0, "Spessartine", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeRed, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 2L), new MaterialStack(Manganese, 3L), new MaterialStack(Silicon, 3L), new MaterialStack(Oxygen, 12L)})),
   Sphalerite("Sphalerite", 356, 839, GT_ItemTextures.SET_DULL, 9, 255, 255, 255, 0, "Sphalerite", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeYellow, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Zinc, 1L), new MaterialStack(Sulfur, 1L)})),
   StainlessSteel("StainlessSteel", 357, 306, GT_ItemTextures.SET_SHINY, 195, 200, 200, 220, 0, "Stainless Steel", 0, 0, 0, 0, 1700, 1700, true, false, 1, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 6L), new MaterialStack(Chrome, 1L), new MaterialStack(Manganese, 1L), new MaterialStack(Nickel, 1L)})),
   Steel("Steel", 358, 305, GT_ItemTextures.SET_METALLIC, 195, 128, 128, 128, 0, "Steel", 0, 0, 0, 0, 1000, 1000, true, false, 4, 51, 50, Dyes.dyeGray, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 50L), new MaterialStack(Carbon, 1L)})),
   Stibnite("Stibnite", 359, 945, GT_ItemTextures.SET_METALLIC, 9, 70, 70, 70, 0, "Stibnite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Antimony, 2L), new MaterialStack(Sulfur, 3L)})),
   SulfuricAcid("SulfuricAcid", 360, 720, GT_ItemTextures.SET_FLUID, 16, 255, 128, 0, 0, "Sulfuric Acid", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Hydrogen, 2L), new MaterialStack(Sulfur, 1L), new MaterialStack(Oxygen, 4L)})),
   Tanzanite("Tanzanite", 361, 508, GT_ItemTextures.SET_GEM_VERTICAL, 77, 64, 0, 200, 127, "Tanzanite", 0, 0, 0, 0, 0, 0, false, true, 5, 1, 1, Dyes.dyePurple, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 2L), new MaterialStack(Aluminium, 3L), new MaterialStack(Silicon, 3L), new MaterialStack(Hydrogen, 1L), new MaterialStack(Oxygen, 13L)})),
   Tetrahedrite("Tetrahedrite", 362, 840, GT_ItemTextures.SET_DULL, 9, 200, 32, 0, 0, "Tetrahedrite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Copper, 3L), new MaterialStack(Antimony, 1L), new MaterialStack(Sulfur, 3L), new MaterialStack(Iron, 1L)})),
   Topaz("Topaz", 363, 507, GT_ItemTextures.SET_GEM_HORIZONTAL, 77, 255, 128, 0, 127, "Topaz", 0, 0, 0, 0, 0, 0, false, true, 5, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 2L), new MaterialStack(Silicon, 1L), new MaterialStack(Fluorine, 2L), new MaterialStack(Hydrogen, 2L), new MaterialStack(Oxygen, 6L)})),
   Tungstate("Tungstate", 364, 841, GT_ItemTextures.SET_DULL, 9, 55, 50, 35, 0, "Tungstate", 0, 0, 0, 0, 2500, 2500, true, false, 4, 1, 1, Dyes.dyeBlack, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Tungsten, 1L), new MaterialStack(Lithium, 2L), new MaterialStack(Oxygen, 4L)})),
   Ultimet("Ultimet", 365, 344, GT_ItemTextures.SET_SHINY, 195, 180, 180, 230, 0, "Ultimet", 0, 0, 0, 0, 2700, 2700, true, false, 1, 1, 1, Dyes.dyeLightBlue, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Cobalt, 5L), new MaterialStack(Chrome, 2L), new MaterialStack(Nickel, 1L), new MaterialStack(Molybdenum, 1L)})),
   Uraninite("Uraninite", 366, 922, GT_ItemTextures.SET_METALLIC, 9, 35, 35, 35, 0, "Uraninite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLime, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Uranium, 1L), new MaterialStack(Oxygen, 2L)})),
   Uvarovite("Uvarovite", 367, 842, GT_ItemTextures.SET_DIAMOND, 9, 180, 255, 180, 0, "Uvarovite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeGreen, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 3L), new MaterialStack(Chrome, 2L), new MaterialStack(Silicon, 3L), new MaterialStack(Oxygen, 12L)})),
   Water("Water", 368, 701, GT_ItemTextures.SET_FLUID, 16, 0, 0, 255, 0, "Water", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlue, 0, Arrays.asList(new MaterialStack[]{new MaterialStack(Hydrogen, 2L), new MaterialStack(Oxygen, 1L)})),
   Wulfenite("Wulfenite", 369, 882, GT_ItemTextures.SET_DULL, 9, 255, 128, 0, 0, "Wulfenite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Lead, 1L), new MaterialStack(Molybdenum, 1L), new MaterialStack(Oxygen, 4L)})),
   WroughtIron("WroughtIron", 370, 304, GT_ItemTextures.SET_METALLIC, 67, 200, 180, 180, 0, "Wrought Iron", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 1L)})),
   YellowLimonite("YellowLimonite", 371, 931, GT_ItemTextures.SET_METALLIC, 9, 200, 200, 0, 0, "Yellow Limonite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 1L), new MaterialStack(Hydrogen, 1L), new MaterialStack(Oxygen, 2L)})),
   Perlite("Perlite", 372, 925, GT_ItemTextures.SET_DULL, 9, 30, 20, 30, 0, "Perlite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Obsidian, 2L), new MaterialStack(Water, 1L)})),
   Borax("Borax", 373, 941, GT_ItemTextures.SET_FINE, 9, 250, 250, 250, 0, "Borax", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Sodium, 2L), new MaterialStack(Boron, 4L), new MaterialStack(Water, 10L), new MaterialStack(Oxygen, 7L)})),
   Lignite("Lignite", 374, 538, GT_ItemTextures.SET_LIGNITE, 13, 100, 70, 70, 0, "Lignite Coal", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 2L), new MaterialStack(Water, 4L), new MaterialStack(DarkAsh, 1L)})),
   Olivine("Olivine", 375, 505, GT_ItemTextures.SET_RUBY, 77, 150, 255, 150, 127, "Olivine", 0, 0, 0, 0, 0, 0, false, true, 5, 1, 1, Dyes.dyeLime, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnesium, 2L), new MaterialStack(Iron, 1L), new MaterialStack(SiliconDioxide, 2L)})),
   Opal("Opal", 376, 510, GT_ItemTextures.SET_OPAL, 77, 0, 0, 255, 0, "Opal", 0, 0, 0, 0, 0, 0, false, true, 3, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(SiliconDioxide, 1L)})),
   Amethyst("Amethyst", 377, 509, GT_ItemTextures.SET_FLINT, 77, 210, 50, 210, 127, "Amethyst", 0, 0, 0, 0, 0, 0, false, true, 3, 1, 1, Dyes.dyePink, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(SiliconDioxide, 4L), new MaterialStack(Iron, 1L)})),
   Redstone("Redstone", 378, 810, GT_ItemTextures.SET_ROUGH, 9, 200, 0, 0, 0, "Redstone", 0, 0, 5000, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Silicon, 1L), new MaterialStack(Pyrite, 5L), new MaterialStack(Ruby, 1L), new MaterialStack(Mercury, 3L)})),
   Lapis("Lapis", 379, 526, GT_ItemTextures.SET_LAPIS, 13, 70, 70, 220, 0, "Lapis", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeBlue, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Lazurite, 12L), new MaterialStack(Sodalite, 2L), new MaterialStack(Pyrite, 1L), new MaterialStack(Calcite, 1L)})),
   Blaze("Blaze", 380, 801, GT_ItemTextures.SET_SHINY, 65, 255, 255, 255, 0, "Blaze", 0, 0, 0, 0, 0, 0, false, false, 2, 3, 2, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(DarkAsh, 1L), new MaterialStack(Sulfur, 1L), new MaterialStack(Magic, 1L)})),
   EnderPearl("EnderPearl", 381, 532, GT_ItemTextures.SET_SHINY, 1, 108, 220, 200, 0, "Enderpearl", 0, 0, 25000, 0, 0, 0, false, false, 1, 16, 10, Dyes.dyeGreen, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Beryllium, 1L), new MaterialStack(Potassium, 4L), new MaterialStack(Nitrogen, 5L), new MaterialStack(Magic, 6L)})),
   EnderEye("EnderEye", 382, 533, GT_ItemTextures.SET_SHINY, 1, 160, 250, 230, 0, "Endereye", 5, 10, '\uc350', 0, 0, 0, false, false, 1, 2, 1, Dyes.dyeGreen, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(EnderPearl, 1L), new MaterialStack(Blaze, 1L)})),
   Flint("Flint", 383, 802, GT_ItemTextures.SET_FLINT, 65, 0, 32, 64, 0, "Flint", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(SiliconDioxide, 1L)})),
   Diatomite("Diatomite", 384, 948, GT_ItemTextures.SET_DULL, 9, 225, 225, 225, 0, "Diatomite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Flint, 8L), new MaterialStack(BandedIron, 1L), new MaterialStack(Sapphire, 1L)})),
   VolcanicAsh("VolcanicAsh", 385, 940, GT_ItemTextures.SET_FLINT, 1, 60, 50, 50, 0, "Volcanic Ashes", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Flint, 6L), new MaterialStack(Iron, 1L), new MaterialStack(Magnesium, 1L)})),
   Niter("Niter", 386, 531, GT_ItemTextures.SET_FLINT, 13, 255, 200, 200, 0, "Niter", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePink, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Saltpeter, 1L)})),
   Pyrotheum("Pyrotheum", 387, 843, GT_ItemTextures.SET_SHINY, 1, 255, 128, 0, 0, "Pyrotheum", 2, 62, 0, 0, 0, 0, false, false, 2, 3, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Coal, 1L), new MaterialStack(Redstone, 1L), new MaterialStack(Blaze, 1L)})),
   HydratedCoal("HydratedCoal", 388, 818, GT_ItemTextures.SET_ROUGH, 1, 70, 70, 100, 0, "Hydrated Coal", 0, 0, 0, 0, 0, 0, false, false, 1, 9, 8, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Coal, 8L), new MaterialStack(Water, 1L)})),
   Apatite("Apatite", 389, 530, GT_ItemTextures.SET_DIAMOND, 13, 200, 200, 255, 0, "Apatite", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 5L), new MaterialStack(Phosphate, 3L), new MaterialStack(Chlorine, 1L)})),
   Alumite("Alumite", 390, -1, GT_ItemTextures.SET_METALLIC, 67, 255, 255, 255, 0, "Alumite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePink, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 5L), new MaterialStack(Iron, 2L), new MaterialStack(Obsidian, 2L)})),
   Manyullyn("Manyullyn", 391, -1, GT_ItemTextures.SET_METALLIC, 67, 255, 255, 255, 0, "Manyullyn", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePurple, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Cobalt, 1L), new MaterialStack(Aredrite, 1L)})),
   IronWood("IronWood", 392, 338, GT_ItemTextures.SET_WOOD, 195, 220, 175, 0, 0, "Ironwood", 5, 8, 0, 0, 0, 0, false, false, 2, 19, 9, Dyes.dyeBrown, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 9L), new MaterialStack(LiveRoot, 9L), new MaterialStack(Gold, 1L)})),
   ShadowIron("ShadowIron", 393, 336, GT_ItemTextures.SET_METALLIC, 75, 120, 120, 120, 0, "Shadowiron", 0, 0, 0, 0, 0, 0, false, false, 3, 4, 3, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 3L), new MaterialStack(Magic, 1L)})),
   ShadowSteel("ShadowSteel", 394, 337, GT_ItemTextures.SET_METALLIC, 67, 90, 90, 90, 0, "Shadowsteel", 0, 0, 0, 0, 1700, 1700, true, false, 4, 4, 3, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Steel, 3L), new MaterialStack(Magic, 1L)})),
   SteelLeaf("SteelLeaf", 395, 339, GT_ItemTextures.SET_LEAF, 67, 0, 127, 0, 0, "Steelleaf", 5, 24, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeGreen, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Steel, 1L), new MaterialStack(Magic, 1L)})),
   BlackSteel("BlackSteel", 396, 334, GT_ItemTextures.SET_METALLIC, 67, 100, 100, 100, 0, "Black Steel", 0, 0, 0, 0, 1200, 1200, true, false, 4, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Steel, 1L)})),
   DamascusSteel("DamascusSteel", 397, 335, GT_ItemTextures.SET_METALLIC, 67, 110, 110, 110, 0, "Damascus Steel", 0, 0, 0, 0, 1500, 1500, true, false, 4, 1, 1, Dyes.dyeGray, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Steel, 1L)})),
   TungstenSteel("TungstenSteel", 398, 316, GT_ItemTextures.SET_METALLIC, 195, 100, 100, 160, 0, "Tungstensteel", 0, 0, 0, 0, 3000, 3000, true, false, 4, 1, 1, Dyes.dyeBlue, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Steel, 1L), new MaterialStack(Tungsten, 1L)})),
   NitroCoalFuel("NitroCoalFuel", 399, 711, GT_ItemTextures.SET_FLUID, 16, 50, 70, 50, 0, "Nitro-Coalfuel", 0, 48, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 0, Arrays.asList(new MaterialStack[]{new MaterialStack(Glyceryl, 1L), new MaterialStack(CoalFuel, 4L)})),
   NitroFuel("NitroFuel", 400, 709, GT_ItemTextures.SET_FLUID, 16, 200, 255, 0, 0, "Nitro-Diesel", 0, 384, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeLime, 0, Arrays.asList(new MaterialStack[]{new MaterialStack(Glyceryl, 1L), new MaterialStack(Fuel, 4L)})),
   AstralSilver("AstralSilver", 401, 333, GT_ItemTextures.SET_SHINY, 75, 230, 230, 255, 0, "Astral Silver", 0, 0, 0, 0, 0, 0, false, false, 4, 3, 2, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Silver, 2L), new MaterialStack(Magic, 1L)})),
   Midasium("Midasium", 402, 332, GT_ItemTextures.SET_SHINY, 75, 255, 200, 40, 0, "Midasium", 0, 0, 0, 0, 0, 0, false, false, 4, 3, 2, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Gold, 2L), new MaterialStack(Magic, 1L)})),
   Mithril("Mithril", 403, 331, GT_ItemTextures.SET_SHINY, 75, 255, 255, 210, 0, "Mithril", 0, 0, 0, 0, 0, 0, false, false, 4, 3, 2, Dyes.dyeLightBlue, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Platinum, 2L), new MaterialStack(Magic, 1L)})),
   BlueAlloy("BlueAlloy", 404, 309, GT_ItemTextures.SET_DULL, 3, 100, 180, 255, 0, "Blue Alloy", 0, 0, 0, 0, 0, 0, false, false, 3, 5, 1, Dyes.dyeLightBlue, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Silver, 1L), new MaterialStack(Nikolite, 4L)})),
   RedAlloy("RedAlloy", 405, 308, GT_ItemTextures.SET_DULL, 3, 200, 0, 0, 0, "Red Alloy", 0, 0, 0, 0, 0, 0, false, false, 3, 5, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Metal, 1L), new MaterialStack(Redstone, 4L)})),
   CobaltBrass("CobaltBrass", 406, 343, GT_ItemTextures.SET_METALLIC, 195, 180, 180, 160, 0, "Cobalt Brass", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Brass, 7L), new MaterialStack(Aluminium, 1L), new MaterialStack(Cobalt, 1L)})),
   Phosphorus("Phosphorus", 407, 534, GT_ItemTextures.SET_FLINT, 29, 255, 255, 0, 0, "Phosphorus", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 3L), new MaterialStack(Phosphate, 2L)})),
   Basalt("Basalt", 408, 844, GT_ItemTextures.SET_ROUGH, 1, 30, 20, 20, 0, "Basalt", 0, 0, 0, 0, 0, 0, false, false, 2, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Olivine, 1L), new MaterialStack(Calcite, 3L), new MaterialStack(Flint, 8L), new MaterialStack(DarkAsh, 4L)})),
   GarnetRed("GarnetRed", 409, 527, GT_ItemTextures.SET_RUBY, 77, 200, 80, 80, 127, "Red Garnet", 0, 0, 0, 0, 0, 0, false, true, 4, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Pyrope, 3L), new MaterialStack(Almandine, 5L), new MaterialStack(Spessartine, 8L)})),
   GarnetYellow("GarnetYellow", 410, 528, GT_ItemTextures.SET_RUBY, 77, 200, 200, 80, 127, "Yellow Garnet", 0, 0, 0, 0, 0, 0, false, true, 4, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Andradite, 5L), new MaterialStack(Grossular, 8L), new MaterialStack(Uvarovite, 3L)})),
   Marble("Marble", 411, 845, GT_ItemTextures.SET_FINE, 1, 200, 200, 200, 0, "Marble", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnesium, 1L), new MaterialStack(Calcite, 7L)})),
   Sugar("Sugar", 412, 803, GT_ItemTextures.SET_FINE, 1, 250, 250, 250, 0, "Sugar", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Carbon, 2L), new MaterialStack(Water, 5L), new MaterialStack(Oxygen, 25L)})),
   Thaumium("Thaumium", 413, 330, GT_ItemTextures.SET_SHINY, 195, 100, 0, 200, 0, "Thaumium", 0, 0, 0, 0, 0, 0, false, false, 5, 2, 1, Dyes.dyePurple, 0, Arrays.asList(new MaterialStack[]{new MaterialStack(Metal, 1L), new MaterialStack(Magic, 1L)})),
   Vinteum("Vinteum", 414, 529, GT_ItemTextures.SET_EMERALD, 77, 100, 200, 255, 0, "Vinteum", 5, 32, 0, 0, 0, 0, false, false, 4, 1, 1, Dyes.dyeLightBlue, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Magic, 1L)})),
   Vis("Vis", 415, -1, GT_ItemTextures.SET_SHINY, 0, 128, 0, 255, 0, "Vis", 5, 32, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePurple, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Magic, 1L)})),
   Redrock("Redrock", 416, 846, GT_ItemTextures.SET_ROUGH, 1, 255, 80, 50, 0, "Redrock", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcite, 2L), new MaterialStack(Flint, 1L), new MaterialStack(Clay, 1L)})),
   PotassiumFeldspar("PotassiumFeldspar", 417, 847, GT_ItemTextures.SET_FINE, 1, 120, 40, 40, 0, "Potassium Feldspar", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyePink, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Potassium, 1L), new MaterialStack(Aluminium, 1L), new MaterialStack(Silicon, 3L), new MaterialStack(Oxygen, 8L)})),
   Biotite("Biotite", 418, 848, GT_ItemTextures.SET_METALLIC, 1, 20, 30, 20, 0, "Biotite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeGray, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Potassium, 1L), new MaterialStack(Magnesium, 3L), new MaterialStack(Aluminium, 3L), new MaterialStack(Fluorine, 2L), new MaterialStack(Silicon, 3L), new MaterialStack(Oxygen, 10L)})),
   GraniteBlack("GraniteBlack", 419, 849, GT_ItemTextures.SET_ROUGH, 193, 10, 10, 10, 0, "Black Granite", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(SiliconDioxide, 4L), new MaterialStack(Biotite, 1L)})),
   GraniteRed("GraniteRed", 420, 850, GT_ItemTextures.SET_ROUGH, 193, 255, 0, 128, 0, "Red Granite", 0, 0, 0, 0, 0, 0, false, false, 0, 1, 1, Dyes.dyeMagenta, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 2L), new MaterialStack(PotassiumFeldspar, 1L), new MaterialStack(Oxygen, 3L)})),
   Chrysotile("Chrysotile", 421, 912, GT_ItemTextures.SET_DULL, 9, 110, 140, 110, 0, "Chrysotile", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Asbestos, 1L)})),
   VanadiumMagnetite("VanadiumMagnetite", 422, 923, GT_ItemTextures.SET_METALLIC, 9, 35, 35, 60, 0, "Vanadium Magnetite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnetite, 1L), new MaterialStack(Vanadium, 1L)})),
   BasalticMineralSand("BasalticMineralSand", 423, 935, GT_ItemTextures.SET_SAND, 9, 40, 50, 40, 0, "Basaltic Mineral Sand", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnetite, 1L), new MaterialStack(Basalt, 1L)})),
   GraniticMineralSand("GraniticMineralSand", 424, 936, GT_ItemTextures.SET_SAND, 9, 40, 60, 60, 0, "Granitic Mineral Sand", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnetite, 1L), new MaterialStack(GraniteBlack, 1L)})),
   GarnetSand("GarnetSand", 425, 938, GT_ItemTextures.SET_SAND, 9, 200, 100, 0, 0, "Garnet Sand", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(GarnetRed, 1L), new MaterialStack(GarnetYellow, 1L)})),
   QuartzSand("QuartzSand", 426, 939, GT_ItemTextures.SET_SAND, 9, 200, 200, 200, 0, "Quartz Sand", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack[]{new MaterialStack(CertusQuartz, 1L), new MaterialStack(Quartzite, 1L)})),
   Bastnasite("Bastnasite", 427, 905, GT_ItemTextures.SET_FINE, 9, 200, 110, 45, 0, "Bastnasite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Cerium, 1L), new MaterialStack(Carbon, 1L), new MaterialStack(Fluorine, 1L), new MaterialStack(Oxygen, 3L)})),
   Pentlandite("Pentlandite", 428, 909, GT_ItemTextures.SET_DULL, 9, 165, 150, 5, 0, "Pentlandite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Nickel, 9L), new MaterialStack(Sulfur, 8L)})),
   Spodumene("Spodumene", 429, 920, GT_ItemTextures.SET_DULL, 9, 190, 170, 170, 0, "Spodumene", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Lithium, 1L), new MaterialStack(Aluminium, 1L), new MaterialStack(Silicon, 2L), new MaterialStack(Oxygen, 6L)})),
   Pollucite("Pollucite", 430, 919, GT_ItemTextures.SET_DULL, 9, 240, 210, 210, 0, "Pollucite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Caesium, 2L), new MaterialStack(Aluminium, 2L), new MaterialStack(Silicon, 4L), new MaterialStack(Water, 2L), new MaterialStack(Oxygen, 12L)})),
   Tantalite("Tantalite", 431, 921, GT_ItemTextures.SET_METALLIC, 9, 145, 80, 40, 0, "Tantalite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Manganese, 1L), new MaterialStack(Tantalum, 2L), new MaterialStack(Oxygen, 6L)})),
   Lepidolite("Lepidolite", 432, 907, GT_ItemTextures.SET_FINE, 9, 240, 50, 140, 0, "Lepidolite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Potassium, 1L), new MaterialStack(Lithium, 3L), new MaterialStack(Aluminium, 4L), new MaterialStack(Fluorine, 2L), new MaterialStack(Oxygen, 10L)})),
   Glauconite("Glauconite", 433, 933, GT_ItemTextures.SET_DULL, 9, 130, 180, 60, 0, "Glauconite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Potassium, 1L), new MaterialStack(Magnesium, 2L), new MaterialStack(Aluminium, 4L), new MaterialStack(Hydrogen, 2L), new MaterialStack(Oxygen, 12L)})),
   Vermiculite("Vermiculite", 434, 932, GT_ItemTextures.SET_METALLIC, 9, 200, 180, 15, 0, "Vermiculite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Iron, 3L), new MaterialStack(Aluminium, 4L), new MaterialStack(Silicon, 4L), new MaterialStack(Hydrogen, 2L), new MaterialStack(Water, 4L), new MaterialStack(Oxygen, 12L)})),
   Bentonite("Bentonite", 435, 927, GT_ItemTextures.SET_ROUGH, 9, 245, 215, 210, 0, "Bentonite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Sodium, 1L), new MaterialStack(Magnesium, 6L), new MaterialStack(Silicon, 12L), new MaterialStack(Hydrogen, 6L), new MaterialStack(Water, 5L), new MaterialStack(Oxygen, 36L)})),
   FullersEarth("FullersEarth", 436, 928, GT_ItemTextures.SET_FINE, 9, 160, 160, 120, 0, "Fullers Earth", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnesium, 1L), new MaterialStack(Silicon, 4L), new MaterialStack(Hydrogen, 1L), new MaterialStack(Water, 4L), new MaterialStack(Oxygen, 11L)})),
   Malachite("Malachite", 437, 871, GT_ItemTextures.SET_DULL, 9, 5, 95, 5, 0, "Malachite", 0, 0, 0, 0, 0, 0, false, false, 3, 1, 1, Dyes.dyeGreen, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Copper, 2L), new MaterialStack(Carbon, 1L), new MaterialStack(Hydrogen, 2L), new MaterialStack(Oxygen, 5L)})),
   Mirabilite("Mirabilite", 438, 900, GT_ItemTextures.SET_DULL, 9, 240, 250, 210, 0, "Mirabilite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Sodium, 2L), new MaterialStack(Sulfur, 1L), new MaterialStack(Water, 10L), new MaterialStack(Oxygen, 4L)})),
   Mica("Mica", 439, 901, GT_ItemTextures.SET_FINE, 9, 195, 195, 205, 0, "Mica", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Potassium, 1L), new MaterialStack(Aluminium, 3L), new MaterialStack(Silicon, 3L), new MaterialStack(Fluorine, 2L), new MaterialStack(Oxygen, 10L)})),
   Trona("Trona", 440, 903, GT_ItemTextures.SET_METALLIC, 9, 135, 135, 95, 0, "Trona", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Sodium, 3L), new MaterialStack(Carbon, 2L), new MaterialStack(Hydrogen, 1L), new MaterialStack(Water, 2L), new MaterialStack(Oxygen, 6L)})),
   Barite("Barite", 441, 904, GT_ItemTextures.SET_DULL, 9, 230, 235, 255, 0, "Barite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Barium, 1L), new MaterialStack(Sulfur, 1L), new MaterialStack(Oxygen, 4L)})),
   Gypsum("Gypsum", 442, 934, GT_ItemTextures.SET_DULL, 9, 230, 230, 250, 0, "Gypsum", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 1L), new MaterialStack(Sulfur, 1L), new MaterialStack(Water, 2L), new MaterialStack(Oxygen, 4L)})),
   Alunite("Alunite", 443, 911, GT_ItemTextures.SET_METALLIC, 9, 225, 180, 65, 0, "Alunite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Potassium, 1L), new MaterialStack(Aluminium, 3L), new MaterialStack(Silicon, 2L), new MaterialStack(Hydrogen, 6L), new MaterialStack(Oxygen, 14L)})),
   Dolomite("Dolomite", 444, 914, GT_ItemTextures.SET_FLINT, 9, 225, 205, 205, 0, "Dolomite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 1L), new MaterialStack(Magnesium, 1L), new MaterialStack(Carbon, 2L), new MaterialStack(Oxygen, 6L)})),
   Wollastonite("Wollastonite", 445, 915, GT_ItemTextures.SET_DULL, 9, 240, 240, 240, 0, "Wollastonite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Calcium, 1L), new MaterialStack(Silicon, 1L), new MaterialStack(Oxygen, 3L)})),
   Zeolite("Zeolite", 446, 916, GT_ItemTextures.SET_DULL, 9, 240, 230, 230, 0, "Zeolite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Sodium, 1L), new MaterialStack(Calcium, 4L), new MaterialStack(Silicon, 27L), new MaterialStack(Aluminium, 9L), new MaterialStack(Water, 28L), new MaterialStack(Oxygen, 72L)})),
   Kyanite("Kyanite", 447, 924, GT_ItemTextures.SET_FLINT, 9, 110, 110, 250, 0, "Kyanite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 2L), new MaterialStack(Silicon, 1L), new MaterialStack(Oxygen, 5L)})),
   Kaolinite("Kaolinite", 448, 929, GT_ItemTextures.SET_DULL, 9, 245, 235, 235, 0, "Kaolinite", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Aluminium, 2L), new MaterialStack(Silicon, 2L), new MaterialStack(Hydrogen, 4L), new MaterialStack(Oxygen, 9L)})),
   Talc("Talc", 449, 902, GT_ItemTextures.SET_DULL, 9, 90, 180, 90, 0, "Talc", 0, 0, 0, 0, 0, 0, false, false, 1, 1, 1, Dyes._NULL, 1, Arrays.asList(new MaterialStack[]{new MaterialStack(Magnesium, 3L), new MaterialStack(Silicon, 4L), new MaterialStack(Hydrogen, 2L), new MaterialStack(Oxygen, 12L)})),
   @Deprecated
   Palygorskite("Palygorskite", 450, FullersEarth, false),
   @Deprecated
   Adamantine("Adamantine", 451, Adamantium, true),
   @Deprecated
   FzDarkIron("FzDarkIron", 452, DarkIron, false),
   @Deprecated
   FZDarkIron("FZDarkIron", 453, DarkIron, false),
   @Deprecated
   Ashes("Ashes", 454, Ash, false),
   @Deprecated
   DarkAshes("DarkAshes", 455, DarkAsh, false),
   @Deprecated
   Abyssal("Abyssal", 456, Basalt, false),
   @Deprecated
   Adamant("Adamant", 457, Adamantium, true),
   @Deprecated
   AluminumBrass("AluminumBrass", 458, AluminiumBrass, false),
   @Deprecated
   Aluminum("Aluminum", 459, Aluminium, false),
   @Deprecated
   NaturalAluminum("NaturalAluminum", 460, Aluminium, false),
   @Deprecated
   NaturalAluminium("NaturalAluminium", 461, Aluminium, false),
   @Deprecated
   Americum("Americum", 462, Americium, false),
   @Deprecated
   Beryl("Beryl", 463, Emerald, false),
   @Deprecated
   BlackGranite("BlackGranite", 464, GraniteBlack, false),
   @Deprecated
   CalciumCarbonate("CalciumCarbonate", 465, Calcite, false),
   @Deprecated
   CreosoteOil("CreosoteOil", 466, Creosote, false),
   @Deprecated
   Chromium("Chromium", 467, Chrome, false),
   @Deprecated
   Diesel("Diesel", 468, Fuel, false),
   @Deprecated
   Enderpearl("Enderpearl", 469, EnderPearl, false),
   @Deprecated
   Endereye("Endereye", 470, EnderEye, false),
   @Deprecated
   EyeOfEnder("EyeOfEnder", 471, EnderEye, false),
   @Deprecated
   Eyeofender("Eyeofender", 472, EnderEye, false),
   @Deprecated
   Flour("Flour", 473, Wheat, false),
   @Deprecated
   Garnet("Garnet", 474, GarnetRed, true),
   @Deprecated
   Granite("Granite", 475, GraniteBlack, false),
   @Deprecated
   Kalium("Kalium", 476, Potassium, false),
   @Deprecated
   Lapislazuli("Lapislazuli", 477, Lapis, false),
   @Deprecated
   LapisLazuli("LapisLazuli", 478, Lapis, false),
   @Deprecated
   Monazit("Monazit", 479, Monazite, false),
   @Deprecated
   Natrium("Natrium", 480, Sodium, false),
   @Deprecated
   NitroDiesel("NitroDiesel", 481, NitroFuel, false),
   @Deprecated
   Obby("Obby", 482, Obsidian, false),
   @Deprecated
   Peridot("Peridot", 483, Olivine, true),
   @Deprecated
   Phosphorite("Phosphorite", 484, Phosphorus, true),
   @Deprecated
   Quarried("Quarried", 485, Marble, false),
   @Deprecated
   Quicksilver("Quicksilver", 486, Mercury, true),
   @Deprecated
   QuickSilver("QuickSilver", 487, Mercury, false),
   @Deprecated
   RedRock("RedRock", 488, Redrock, false),
   @Deprecated
   RefinedIron("RefinedIron", 489, Iron, false),
   @Deprecated
   RedGranite("RedGranite", 490, GraniteRed, false),
   @Deprecated
   Sheldonite("Sheldonite", 491, Cooperite, false),
   @Deprecated
   Soulsand("Soulsand", 492, SoulSand, false),
   @Deprecated
   SilverLead("SilverLead", 493, Galena, false),
   @Deprecated
   Titan("Titan", 494, Titanium, false),
   @Deprecated
   Uran("Uran", 495, Uranium, false),
   @Deprecated
   Wolframite("Wolframite", 496, Tungstate, false),
   @Deprecated
   Wolframium("Wolframium", 497, Tungsten, false),
   @Deprecated
   Wolfram("Wolfram", 498, Tungsten, false),
   @Deprecated
   WrougtIron("WrougtIron", 499, WroughtIron, false);
   private final ArrayList<ItemStack> mMaterialItems;
   private final List<SubTag> mSubTags;
   public final short[] mRGBa;
   public final IIconContainer[] mIconSet;
   public boolean mBlastFurnaceRequired;
   public boolean mTransparent;
   public GT_SpecialToolEffect mSpecialEffect;
   public byte mEffectLevel;
   public String mChemicalFormula;
   public String mDefaultLocalName;
   public Dyes mColor;
   public short mMeltingPoint;
   public short mBlastFurnaceTemp;
   public int mTypes;
   public int mAmplificationValue;
   public int mUUMEnergy;
   public int mFuelPower;
   public int mFuelType;
   public int mExtraData;
   public int mOreValue;
   public int mOreMultiplier;
   public int mByProductMultiplier;
   public int mSmeltingMultiplier;
   public long mDensity;
   public Element mElement;
   public Materials mDirectSmelting;
   public Materials mOreReplacement;
   public final int mMetaItemSubID;
   public final boolean mUnificatable;
   public final Materials mMaterialInto;
   public final List<MaterialStack> mMaterialList;
   public final List<Materials> mOreByProducts;
   public final List<Materials> mOreReRegistrations;
   public FluidStack mSolid;
   public FluidStack mFluid;
   public FluidStack mGas;
   public FluidStack mPlasma;
   public static volatile int VERSION;
   // $FF: synthetic field
   private static final Materials[] $VALUES = new Materials[]{_NULL, Aluminium, Americium, Antimony, Arsenic, Barium, Beryllium, Boron, Caesium, Calcium, Carbon, Cadmium, Cerium, Chlorine, Chrome, Cobalt, Copper, Deuterium, Dysprosium, Empty, Erbium, Europium, Fluorine, Gadolinium, Gold, Holmium, Hydrogen, Helium, Helium_3, Indium, Iridium, Iron, Lanthanum, Lead, Lithium, Lutetium, Magic, Magnesium, Manganese, Mercury, Niobium, Molybdenum, Neodymium, Neutronium, Nickel, Nitrogen, Osmium, Oxygen, Palladium, Phosphor, Platinum, Plutonium, Plutonium241, Potassium, Praseodymium, Promethium, Rubidium, Samarium, Scandium, Silicon, Silver, Sodium, Strontium, Sulfur, Tantalum, Tellurium, Terbium, Thorium, Thulium, Tin, Titanium, Tritium, Tungsten, Uranium, Uranium235, Vanadium, Ytterbium, Yttrium, Zinc, Organic, Crystal, Quartz, Metal, Cobblestone, Adamantium, Adamite, Adluorite, Agate, Alduorite, Amber, Ammonium, Amordrine, Andesite, Angmallen, Ardite, Aredrite, Atlarus, Bitumen, Black, Blizz, Blueschist, Bluestone, Bloodstone, Blutonium, Carmot, Celenegil, CertusQuartz, Ceruclase, Citrine, CobaltHexahydrate, ConstructionFoam, Chalk, Chert, Chimerite, Coral, CrudeOil, Chrysocolla, CrystalFlux, Cyanite, Dacite, DarkIron, DarkStone, Demonite, Desh, Desichalkos, Dilithium, Draconic, Duranium, Eclogite, ElectrumFlux, Emery, Enderium, Energized, Epidote, Eximite, FieryBlood, Firestone, Fluorite, FoolsRuby, Force, Forcicium, Forcillium, Gabbro, Glowstone, Gneiss, Graphite, Greenschist, Greenstone, Greywacke, Haderoth, Hematite, Hepatizon, HSLA, Ignatius, Infernal, Infuscolium, InfusedGold, InfusedAir, InfusedFire, InfusedEarth, InfusedWater, InfusedVis, InfusedDull, InfusedEntropy, InfusedOrder, Inolashite, Invisium, Jade, Jasper, Kalendrite, Komatiite, Lava, Lemurite, Limestone, Lodestone, Luminite, Magma, Mawsitsit, Mercassium, MeteoricIron, MeteoricSteel, Meteorite, Meutoite, Migmatite, Mimichite, Monazite, Moonstone, Naquadah, NaquadahAlloy, Nether, NetherBrick, NetherQuartz, NetherStar, Nikolite, ObsidianFlux, Oilsands, Onyx, Orichalcum, Osmonium, Oureclase, Painite, Peanutwood, Petroleum, Pewter, Pitchblende, Phoenixite, Potash, Prometheum, Quartzite, Quicklime, Randomite, RefinedGlowstone, RefinedObsidian, Rhyolite, Rubracium, RyuDragonRyder, Sand, Sanguinite, Siltstone, Soapstone, Spinel, Starconium, Sugilite, Sunstone, Tar, Tartarite, Tapazite, Thyrium, Tourmaline, Tritanium, Turquoise, UUMatter, Void, Voidstone, Vulcanite, Vyroxeres, Wimalite, Yellorite, Yellorium, Zectium, Advanced, Basic, Antimatter, BioFuel, Biomass, Chocolate, Cluster, CoalFuel, Coffee, Creosote, Data, Elite, Ethanol, Fuel, Good, Gunpowder, Infinite, LimePure, Master, Meat, MeatRaw, MeatCooked, Milk, Mud, Oil, Paper, Peat, Primitive, Quantum, Red, Reinforced, SeedOil, Stone, TNT, Ultimate, Unstable, Unstableingot, Wheat, Wood, AluminiumBrass, Osmiridium, Sunnarium, Endstone, Netherrack, SoulSand, Almandine, Andradite, Asbestos, Ash, BandedIron, BatteryAlloy, Bauxite, BlueTopaz, Bone, Brass, Bronze, BrownLimonite, Calcite, Cassiterite, CassiteriteSand, Celestine, Chalcopyrite, Charcoal, Chromite, Cinnabar, Clay, Coal, Cobaltite, Cooperite, Cupronickel, DarkAsh, DeepIron, Diamond, Electrum, Emerald, Galena, Garnierite, Glyceryl, GreenSapphire, Grossular, Ice, Ilmenite, Invar, Kanthal, Lazurite, LiveRoot, Magnalium, Magnesite, Magnetite, Methane, Molybdenite, Nichrome, NitroCarbon, NitrogenDioxide, Obsidian, Phosphate, PigIron, Plastic, Powellite, Pumice, Pyrite, Pyrolusite, Pyrope, RockSalt, Rubber, Ruby, Salt, Saltpeter, Sapphire, Scheelite, SiliconDioxide, Sodalite, SodiumPersulfate, SodiumSulfide, SolderingAlloy, Spessartine, Sphalerite, StainlessSteel, Steel, Stibnite, SulfuricAcid, Tanzanite, Tetrahedrite, Topaz, Tungstate, Ultimet, Uraninite, Uvarovite, Water, Wulfenite, WroughtIron, YellowLimonite, Perlite, Borax, Lignite, Olivine, Opal, Amethyst, Redstone, Lapis, Blaze, EnderPearl, EnderEye, Flint, Diatomite, VolcanicAsh, Niter, Pyrotheum, HydratedCoal, Apatite, Alumite, Manyullyn, IronWood, ShadowIron, ShadowSteel, SteelLeaf, BlackSteel, DamascusSteel, TungstenSteel, NitroCoalFuel, NitroFuel, AstralSilver, Midasium, Mithril, BlueAlloy, RedAlloy, CobaltBrass, Phosphorus, Basalt, GarnetRed, GarnetYellow, Marble, Sugar, Thaumium, Vinteum, Vis, Redrock, PotassiumFeldspar, Biotite, GraniteBlack, GraniteRed, Chrysotile, VanadiumMagnetite, BasalticMineralSand, GraniticMineralSand, GarnetSand, QuartzSand, Bastnasite, Pentlandite, Spodumene, Pollucite, Tantalite, Lepidolite, Glauconite, Vermiculite, Bentonite, FullersEarth, Malachite, Mirabilite, Mica, Trona, Barite, Gypsum, Alunite, Dolomite, Wollastonite, Zeolite, Kyanite, Kaolinite, Talc, Palygorskite, Adamantine, FzDarkIron, FZDarkIron, Ashes, DarkAshes, Abyssal, Adamant, AluminumBrass, Aluminum, NaturalAluminum, NaturalAluminium, Americum, Beryl, BlackGranite, CalciumCarbonate, CreosoteOil, Chromium, Diesel, Enderpearl, Endereye, EyeOfEnder, Eyeofender, Flour, Garnet, Granite, Kalium, Lapislazuli, LapisLazuli, Monazit, Natrium, NitroDiesel, Obby, Peridot, Phosphorite, Quarried, Quicksilver, QuickSilver, RedRock, RefinedIron, RedGranite, Sheldonite, Soulsand, SilverLead, Titan, Uran, Wolframite, Wolframium, Wolfram, WrougtIron};


   public static Materials get(String aMaterialName) {
      Object tObject = GT_Utility.getFieldContent(Materials.class, aMaterialName, false, false);
      return tObject != null && tObject instanceof Materials?(Materials)tObject:_NULL;
   }

   public static Materials getRealMaterial(String aMaterialName) {
      return get(aMaterialName).mMaterialInto;
   }

   public static void init(GT_Config aConfiguration) {
      Materials[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Materials tMaterial = arr$[i$];
         String tString = tMaterial.toString().toLowerCase();
         if(tMaterial.mBlastFurnaceRequired) {
            tMaterial.mBlastFurnaceRequired = aConfiguration.get(GT_ConfigCategories.Materials.blastfurnacerequirements, tString, true);
         }

         if(tMaterial.mAmplificationValue > 0) {
            tMaterial.mAmplificationValue = aConfiguration.get(GT_ConfigCategories.Materials.UUM_MaterialCost, tString, tMaterial.mAmplificationValue);
         }

         if(tMaterial.mUUMEnergy > 0) {
            tMaterial.mUUMEnergy = aConfiguration.get(GT_ConfigCategories.Materials.UUM_EnergyCost, tString, tMaterial.mUUMEnergy);
         }

         if(tMaterial.mBlastFurnaceRequired && aConfiguration.get(GT_ConfigCategories.Materials.blastinductionsmelter, tString, tMaterial.mBlastFurnaceTemp < 1500)) {
            GT_ModHandler.ThermalExpansion.addSmelterBlastOre(tMaterial);
         }
      }

   }

   public boolean isRadioactive() {
      if(this.mElement != null) {
         return this.mElement.mHalfLifeSeconds >= 0;
      } else {
         Iterator i$ = this.mMaterialList.iterator();

         MaterialStack tMaterial;
         do {
            if(!i$.hasNext()) {
               return false;
            }

            tMaterial = (MaterialStack)i$.next();
         } while(!tMaterial.mMaterial.isRadioactive());

         return true;
      }
   }

   public int getProtons() {
      if(this.mElement != null) {
         return this.mElement.getProtons();
      } else if(this.mMaterialList.size() <= 0) {
         return Element.Tc.getProtons();
      } else {
         int rAmount = 0;
         int tAmount = 0;

         MaterialStack tMaterial;
         for(Iterator i$ = this.mMaterialList.iterator(); i$.hasNext(); rAmount = (int)((long)rAmount + tMaterial.mAmount * (long)tMaterial.mMaterial.getProtons())) {
            tMaterial = (MaterialStack)i$.next();
            tAmount = (int)((long)tAmount + tMaterial.mAmount);
         }

         return (int)(this.getDensity() * (long)rAmount / ((long)tAmount * 3628800L));
      }
   }

   public int getNeutrons() {
      if(this.mElement != null) {
         return this.mElement.getNeutrons();
      } else if(this.mMaterialList.size() <= 0) {
         return Element.Tc.getNeutrons();
      } else {
         int rAmount = 0;
         int tAmount = 0;

         MaterialStack tMaterial;
         for(Iterator i$ = this.mMaterialList.iterator(); i$.hasNext(); rAmount = (int)((long)rAmount + tMaterial.mAmount * (long)tMaterial.mMaterial.getNeutrons())) {
            tMaterial = (MaterialStack)i$.next();
            tAmount = (int)((long)tAmount + tMaterial.mAmount);
         }

         return (int)(this.getDensity() * (long)rAmount / ((long)tAmount * 3628800L));
      }
   }

   public int getMass() {
      if(this.mElement != null) {
         return this.mElement.getMass();
      } else if(this.mMaterialList.size() <= 0) {
         return Element.Tc.getMass();
      } else {
         int rAmount = 0;
         int tAmount = 0;

         MaterialStack tMaterial;
         for(Iterator i$ = this.mMaterialList.iterator(); i$.hasNext(); rAmount = (int)((long)rAmount + tMaterial.mAmount * (long)tMaterial.mMaterial.getMass())) {
            tMaterial = (MaterialStack)i$.next();
            tAmount = (int)((long)tAmount + tMaterial.mAmount);
         }

         return (int)(this.getDensity() * (long)rAmount / ((long)tAmount * 3628800L));
      }
   }

   public long getDensity() {
      return this.mDensity;
   }

   public String getToolTip() {
      return this.getToolTip(1L, false);
   }

   public String getToolTip(boolean aShowQuestionMarks) {
      return this.getToolTip(1L, aShowQuestionMarks);
   }

   public String getToolTip(long aMultiplier) {
      return this.getToolTip(aMultiplier, false);
   }

   public String getToolTip(long aMultiplier, boolean aShowQuestionMarks) {
      return !aShowQuestionMarks && this.mChemicalFormula.equals("?")?"":(this.getDensity() * aMultiplier >= 7257600L && !this.mMaterialList.isEmpty()?(this.mElement == null && (this.mMaterialList.size() >= 2 || ((MaterialStack)this.mMaterialList.get(0)).mAmount != 1L)?"(" + this.mChemicalFormula + ")":this.mChemicalFormula) + this.getDensity() * aMultiplier / 3628800L:this.mChemicalFormula);
   }

   public Materials add(ItemStack aStack) {
      if(aStack != null && !this.contains(new ItemStack[]{aStack})) {
         this.mMaterialItems.add(aStack);
      }

      return this;
   }

   public boolean contains(ItemStack ... aStacks) {
      if(aStacks != null && aStacks.length > 0) {
         Iterator i$ = this.mMaterialItems.iterator();

         while(i$.hasNext()) {
            ItemStack tStack = (ItemStack)i$.next();
            ItemStack[] arr$ = aStacks;
            int len$ = aStacks.length;

            for(int i$1 = 0; i$1 < len$; ++i$1) {
               ItemStack aStack = arr$[i$1];
               if(GT_Utility.areStacksEqual(aStack, tStack, !tStack.func_77942_o())) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public boolean remove(ItemStack aStack) {
      if(aStack == null) {
         return false;
      } else {
         boolean temp = false;

         for(int i = 0; i < this.mMaterialItems.size(); ++i) {
            if(GT_Utility.areStacksEqual(aStack, (ItemStack)this.mMaterialItems.get(i))) {
               this.mMaterialItems.remove(i--);
               temp = true;
            }
         }

         return temp;
      }
   }

   public Materials add(SubTag aTag) {
      if(aTag != null && !this.contains(aTag)) {
         this.mSubTags.add(aTag);
      }

      return this;
   }

   public boolean contains(SubTag aTag) {
      return this.mSubTags.contains(aTag);
   }

   public boolean remove(SubTag aTag) {
      return this.mSubTags.remove(aTag);
   }

   public Materials addOreByProduct(Materials aMaterial) {
      if(!this.mOreByProducts.contains(aMaterial.mMaterialInto)) {
         this.mOreByProducts.add(aMaterial.mMaterialInto);
      }

      return this;
   }

   public Materials setOreMultiplier(int aOreMultiplier) {
      if(aOreMultiplier > 0) {
         this.mOreMultiplier = aOreMultiplier;
      }

      return this;
   }

   public Materials setByProductMultiplier(int aByProductMultiplier) {
      if(aByProductMultiplier > 0) {
         this.mByProductMultiplier = aByProductMultiplier;
      }

      return this;
   }

   public Materials setSmeltingMultiplier(int aSmeltingMultiplier) {
      if(aSmeltingMultiplier > 0) {
         this.mSmeltingMultiplier = aSmeltingMultiplier;
      }

      return this;
   }

   public Materials setDirectSmelting(Materials aMaterial) {
      if(aMaterial != null) {
         this.mDirectSmelting = aMaterial.mMaterialInto;
      }

      return this;
   }

   public Materials setOreReplacement(Materials aMaterial) {
      if(aMaterial != null) {
         this.mOreReplacement = aMaterial.mMaterialInto;
      }

      return this;
   }

   public Materials setSpecialEffect(GT_SpecialToolEffect aSpecialEffect, int aLevel) {
      if(aSpecialEffect != null && aLevel > 0 && aLevel <= 10) {
         this.mSpecialEffect = aSpecialEffect;
         this.mEffectLevel = (byte)aLevel;
      }

      return this;
   }

   private Materials(String var1, int var2, int aMetaItemSubID, IIconContainer[] aIconSet, boolean aUnificatable) {
      this.mMaterialItems = new ArrayList();
      this.mSubTags = new ArrayList();
      this.mRGBa = new short[]{(short)255, (short)255, (short)255, (short)0};
      this.mBlastFurnaceRequired = false;
      this.mTransparent = false;
      this.mSpecialEffect = null;
      this.mEffectLevel = 0;
      this.mChemicalFormula = "?";
      this.mDefaultLocalName = "null";
      this.mColor = Dyes._NULL;
      this.mMeltingPoint = 0;
      this.mBlastFurnaceTemp = 0;
      this.mTypes = 0;
      this.mAmplificationValue = 0;
      this.mUUMEnergy = 0;
      this.mFuelPower = 0;
      this.mFuelType = 0;
      this.mExtraData = 0;
      this.mOreValue = 0;
      this.mOreMultiplier = 1;
      this.mByProductMultiplier = 1;
      this.mSmeltingMultiplier = 1;
      this.mDensity = 3628800L;
      this.mElement = null;
      this.mDirectSmelting = this;
      this.mOreReplacement = this;
      this.mMaterialList = new ArrayList();
      this.mOreByProducts = new ArrayList();
      this.mOreReRegistrations = new ArrayList();
      this.mSolid = null;
      this.mFluid = null;
      this.mGas = null;
      this.mPlasma = null;
      this.mUnificatable = aUnificatable;
      this.mMaterialInto = this;
      this.mMetaItemSubID = aMetaItemSubID;
      this.mIconSet = (IIconContainer[])Arrays.copyOf(aIconSet, 64);
      if(aMetaItemSubID >= 0) {
         if(GregTech_API.sGeneratedMaterials[aMetaItemSubID] != null) {
            throw new IllegalArgumentException("The Index " + aMetaItemSubID + " is already used!");
         }

         GregTech_API.sGeneratedMaterials[aMetaItemSubID] = this;
      }

   }

   private Materials(String var1, int var2, Materials aMaterialInto, boolean aReRegisterIntoThis) {
      this.mMaterialItems = new ArrayList();
      this.mSubTags = new ArrayList();
      this.mRGBa = new short[]{(short)255, (short)255, (short)255, (short)0};
      this.mBlastFurnaceRequired = false;
      this.mTransparent = false;
      this.mSpecialEffect = null;
      this.mEffectLevel = 0;
      this.mChemicalFormula = "?";
      this.mDefaultLocalName = "null";
      this.mColor = Dyes._NULL;
      this.mMeltingPoint = 0;
      this.mBlastFurnaceTemp = 0;
      this.mTypes = 0;
      this.mAmplificationValue = 0;
      this.mUUMEnergy = 0;
      this.mFuelPower = 0;
      this.mFuelType = 0;
      this.mExtraData = 0;
      this.mOreValue = 0;
      this.mOreMultiplier = 1;
      this.mByProductMultiplier = 1;
      this.mSmeltingMultiplier = 1;
      this.mDensity = 3628800L;
      this.mElement = null;
      this.mDirectSmelting = this;
      this.mOreReplacement = this;
      this.mMaterialList = new ArrayList();
      this.mOreByProducts = new ArrayList();
      this.mOreReRegistrations = new ArrayList();
      this.mSolid = null;
      this.mFluid = null;
      this.mGas = null;
      this.mPlasma = null;
      this.mUnificatable = false;
      this.mDefaultLocalName = aMaterialInto.mDefaultLocalName;
      this.mMaterialInto = aMaterialInto.mMaterialInto;
      if(aReRegisterIntoThis) {
         this.mMaterialInto.mOreReRegistrations.add(this);
      }

      this.mChemicalFormula = aMaterialInto.mChemicalFormula;
      this.mMetaItemSubID = -1;
      this.mIconSet = GT_ItemTextures.SET_NONE;
   }

   private Materials(String var1, int var2, int aMetaItemSubID, IIconContainer[] aIconSet, int aTypes, int aR, int aG, int aB, int aA, String aLocalName, int aFuelType, int aFuelPower, int aAmplificationValue, int aUUMEnergy, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor) {
      this(var1, var2, aMetaItemSubID, aIconSet, true);
      this.mDefaultLocalName = aLocalName;
      this.mMeltingPoint = (short)aMeltingPoint;
      this.mBlastFurnaceTemp = (short)aBlastFurnaceTemp;
      this.mBlastFurnaceRequired = aBlastFurnaceRequired;
      this.mTransparent = aTransparent;
      this.mAmplificationValue = aAmplificationValue;
      this.mUUMEnergy = aUUMEnergy;
      this.mFuelPower = aFuelPower;
      this.mFuelType = aFuelType;
      this.mOreValue = aOreValue;
      this.mDensity = 3628800L * (long)aDensityMultiplier / (long)aDensityDivider;
      this.mColor = aColor == null?Dyes._NULL:aColor;
      this.mRGBa[0] = (short)aR;
      this.mRGBa[1] = (short)aG;
      this.mRGBa[2] = (short)aB;
      this.mRGBa[3] = (short)aA;
      this.mTypes = aTypes;
   }

   private Materials(String var1, int var2, int aMetaItemSubID, IIconContainer[] aIconSet, int aTypes, int aR, int aG, int aB, int aA, String aLocalName, int aFuelType, int aFuelPower, int aAmplificationValue, int aUUMEnergy, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, Element aElement) {
      this(var1, var2, aMetaItemSubID, aIconSet, aTypes, aR, aG, aB, aA, aLocalName, aFuelType, aFuelPower, aAmplificationValue, aUUMEnergy, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor);
      this.mElement = aElement;
      this.mElement.mLinkedMaterials.add(this);
      if(aElement == Element._NULL) {
         this.mChemicalFormula = "Empty";
      } else {
         this.mChemicalFormula = aElement.toString();
         this.mChemicalFormula = this.mChemicalFormula.replaceAll("_", "-");
      }

   }

   private Materials(String var1, int var2, int aMetaItemSubID, IIconContainer[] aIconSet, int aTypes, int aR, int aG, int aB, int aA, String aLocalName, int aFuelType, int aFuelPower, int aAmplificationValue, int aUUMEnergy, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, int aExtraData, List aMaterialList) {
      this(var1, var2, aMetaItemSubID, aIconSet, aTypes, aR, aG, aB, aA, aLocalName, aFuelType, aFuelPower, aAmplificationValue, aUUMEnergy, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor);
      this.mExtraData = aExtraData;
      this.mMaterialList.addAll(aMaterialList);
      this.mChemicalFormula = "";

      MaterialStack tMaterial;
      for(Iterator i$ = this.mMaterialList.iterator(); i$.hasNext(); this.mChemicalFormula = this.mChemicalFormula + tMaterial.toString()) {
         tMaterial = (MaterialStack)i$.next();
      }

      this.mChemicalFormula = this.mChemicalFormula.replaceAll("_", "-");
   }

   static {
      Thorium.add(SubTag.ENCHANTMENT_GLOW);
      Uranium.add(SubTag.ENCHANTMENT_GLOW);
      Plutonium.add(SubTag.ENCHANTMENT_GLOW);
      NetherStar.add(SubTag.ENCHANTMENT_GLOW);
      Pyrotheum.add(SubTag.ENCHANTMENT_GLOW);
      Thaumium.add(SubTag.ENCHANTMENT_GLOW);
      Vinteum.add(SubTag.ENCHANTMENT_GLOW);
      Magic.add(SubTag.ENCHANTMENT_GLOW);
      Rubber.add(SubTag.NO_SMASHING);
      Plastic.add(SubTag.NO_SMASHING);
      Stone.add(SubTag.NO_SMASHING);
      Paper.add(SubTag.NO_SMASHING);
      Wood.add(SubTag.NO_SMASHING);
      Gunpowder.add(SubTag.NO_SMELTING);
      Stone.add(SubTag.NO_SMELTING);
      Paper.add(SubTag.NO_SMELTING);
      Wood.add(SubTag.NO_SMELTING);
      Redstone.add(SubTag.PULVERIZING_CINNABAR);
      Pyrite.add(SubTag.BLASTFURNACE_CALCITE_DOUBLE);
      YellowLimonite.add(SubTag.BLASTFURNACE_CALCITE_DOUBLE);
      Iron.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
      PigIron.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
      DeepIron.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
      ShadowIron.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
      WroughtIron.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
      MeteoricIron.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
      BrownLimonite.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
      Gold.add(SubTag.WASHING_MERCURY);
      Silver.add(SubTag.WASHING_MERCURY);
      Osmium.add(SubTag.WASHING_MERCURY);
      Mithril.add(SubTag.WASHING_MERCURY);
      Platinum.add(SubTag.WASHING_MERCURY);
      Midasium.add(SubTag.WASHING_MERCURY);
      Cooperite.add(SubTag.WASHING_MERCURY);
      AstralSilver.add(SubTag.WASHING_MERCURY);
      Zinc.add(SubTag.WASHING_SODIUMPERSULFATE);
      Nickel.add(SubTag.WASHING_SODIUMPERSULFATE);
      Copper.add(SubTag.WASHING_SODIUMPERSULFATE);
      Cobalt.add(SubTag.WASHING_SODIUMPERSULFATE);
      Cobaltite.add(SubTag.WASHING_SODIUMPERSULFATE);
      Tetrahedrite.add(SubTag.WASHING_SODIUMPERSULFATE);
      Salt.setOreMultiplier(2).setSmeltingMultiplier(2);
      RockSalt.setOreMultiplier(2).setSmeltingMultiplier(2);
      Scheelite.setOreMultiplier(2).setSmeltingMultiplier(2);
      Tungstate.setOreMultiplier(2).setSmeltingMultiplier(2);
      Cassiterite.setOreMultiplier(2).setSmeltingMultiplier(2);
      CassiteriteSand.setOreMultiplier(2).setSmeltingMultiplier(2);
      NetherQuartz.setOreMultiplier(2).setSmeltingMultiplier(2);
      CertusQuartz.setOreMultiplier(2).setSmeltingMultiplier(2);
      Phosphorus.setOreMultiplier(3).setSmeltingMultiplier(3);
      Sulfur.setOreMultiplier(4).setSmeltingMultiplier(4);
      Saltpeter.setOreMultiplier(4).setSmeltingMultiplier(4);
      Apatite.setOreMultiplier(4).setSmeltingMultiplier(4).setByProductMultiplier(2);
      Nikolite.setOreMultiplier(5).setSmeltingMultiplier(5);
      Redstone.setOreMultiplier(5).setSmeltingMultiplier(5);
      Glowstone.setOreMultiplier(5).setSmeltingMultiplier(5);
      Lapis.setOreMultiplier(6).setSmeltingMultiplier(6).setByProductMultiplier(4);
      Sodalite.setOreMultiplier(6).setSmeltingMultiplier(6).setByProductMultiplier(4);
      Lazurite.setOreMultiplier(6).setSmeltingMultiplier(6).setByProductMultiplier(4);
      Monazite.setOreMultiplier(8).setSmeltingMultiplier(8).setByProductMultiplier(2);
      Plastic.setSpecialEffect(GT_SpecialToolEffect.Bouncy, 1);
      Rubber.setSpecialEffect(GT_SpecialToolEffect.Bouncy, 2);
      Thorium.setSpecialEffect(GT_SpecialToolEffect.Radioactive, 1);
      Uranium.setSpecialEffect(GT_SpecialToolEffect.Radioactive, 2);
      Plutonium.setSpecialEffect(GT_SpecialToolEffect.Radioactive, 3);
      Stone.setSpecialEffect(GT_SpecialToolEffect.Crushing, 1);
      GraniteRed.setSpecialEffect(GT_SpecialToolEffect.Crushing, 2);
      GraniteBlack.setSpecialEffect(GT_SpecialToolEffect.Crushing, 2);
      Adamantium.setSpecialEffect(GT_SpecialToolEffect.Crushing, 3);
      Electrum.setSpecialEffect(GT_SpecialToolEffect.BaneOfEnder, 1);
      Silver.setSpecialEffect(GT_SpecialToolEffect.BaneOfEnder, 2);
      AstralSilver.setSpecialEffect(GT_SpecialToolEffect.BaneOfEnder, 3);
      IronWood.setSpecialEffect(GT_SpecialToolEffect.Fortune, 1);
      SteelLeaf.setSpecialEffect(GT_SpecialToolEffect.Fortune, 2);
      Midasium.setSpecialEffect(GT_SpecialToolEffect.Fortune, 1);
      Mithril.setSpecialEffect(GT_SpecialToolEffect.Fortune, 2);
      Vinteum.setSpecialEffect(GT_SpecialToolEffect.Fortune, 1);
      Thaumium.setSpecialEffect(GT_SpecialToolEffect.Fortune, 2);
      Magic.setSpecialEffect(GT_SpecialToolEffect.Fortune, 3);
      Flint.setSpecialEffect(GT_SpecialToolEffect.Fire, 1);
      DarkIron.setSpecialEffect(GT_SpecialToolEffect.Fire, 2);
      FieryBlood.setSpecialEffect(GT_SpecialToolEffect.Fire, 3);
      Blaze.setSpecialEffect(GT_SpecialToolEffect.Fire, 3);
      DeepIron.setSpecialEffect(GT_SpecialToolEffect.Magnetic, 1);
      MeteoricIron.setSpecialEffect(GT_SpecialToolEffect.Magnetic, 2);
      MeteoricSteel.setSpecialEffect(GT_SpecialToolEffect.Magnetic, 3);
      Cinnabar.setDirectSmelting(Mercury).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT).add(SubTag.SMELTING_TO_GEM);
      Celestine.setDirectSmelting(Strontium).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
      Tetrahedrite.setDirectSmelting(Copper).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
      Chalcopyrite.setDirectSmelting(Copper).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
      Malachite.setDirectSmelting(Copper).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
      Pentlandite.setDirectSmelting(Nickel).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
      Sphalerite.setDirectSmelting(Zinc).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
      Pyrite.setDirectSmelting(Iron).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
      YellowLimonite.setDirectSmelting(Iron).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
      BrownLimonite.setDirectSmelting(Iron);
      BandedIron.setDirectSmelting(Iron);
      Cassiterite.setDirectSmelting(Tin);
      CassiteriteSand.setDirectSmelting(Tin);
      Chromite.setDirectSmelting(Chrome);
      Garnierite.setDirectSmelting(Nickel);
      Cobaltite.setDirectSmelting(Cobalt);
      Stibnite.setDirectSmelting(Antimony);
      Cooperite.setDirectSmelting(Platinum);
      Pyrolusite.setDirectSmelting(Manganese);
      Magnesite.setDirectSmelting(Magnesium);
      Molybdenite.setDirectSmelting(Molybdenum);
      Glauconite.addOreByProduct(Sodium).addOreByProduct(Aluminium).addOreByProduct(Iron);
      Vermiculite.addOreByProduct(Iron).addOreByProduct(Aluminium).addOreByProduct(Magnesium);
      FullersEarth.addOreByProduct(Aluminium).addOreByProduct(Silicon).addOreByProduct(Magnesium);
      Bentonite.addOreByProduct(Aluminium).addOreByProduct(Calcium).addOreByProduct(Magnesium);
      Bastnasite.addOreByProduct(Yttrium).addOreByProduct(Lanthanum).addOreByProduct(Cerium);
      Uraninite.addOreByProduct(Uranium).addOreByProduct(Thorium).addOreByProduct(Plutonium);
      Galena.addOreByProduct(Sulfur).addOreByProduct(Silver).addOreByProduct(Lead);
      Lapis.addOreByProduct(Lazurite).addOreByProduct(Sodalite).addOreByProduct(Pyrite);
      Pyrite.addOreByProduct(Sulfur).addOreByProduct(Phosphorus).addOreByProduct(Iron);
      Copper.addOreByProduct(Cobalt).addOreByProduct(Gold).addOreByProduct(Nickel);
      Nickel.addOreByProduct(Cobalt).addOreByProduct(Platinum).addOreByProduct(Iron);
      GarnetRed.addOreByProduct(Spessartine).addOreByProduct(Pyrope).addOreByProduct(Almandine);
      GarnetYellow.addOreByProduct(Andradite).addOreByProduct(Grossular).addOreByProduct(Uvarovite);
      Cooperite.addOreByProduct(Palladium).addOreByProduct(Nickel).addOreByProduct(Iridium);
      Cinnabar.addOreByProduct(Redstone).addOreByProduct(Sulfur).addOreByProduct(Glowstone);
      Pitchblende.addOreByProduct(Thorium).addOreByProduct(Uranium).addOreByProduct(Plutonium);
      Tantalite.addOreByProduct(Manganese).addOreByProduct(Niobium).addOreByProduct(Tantalum);
      Pollucite.addOreByProduct(Caesium).addOreByProduct(Aluminium).addOreByProduct(Rubidium);
      Chrysotile.addOreByProduct(Asbestos).addOreByProduct(Silicon).addOreByProduct(Magnesium);
      Asbestos.addOreByProduct(Asbestos).addOreByProduct(Silicon).addOreByProduct(Magnesium);
      Sphalerite.addOreByProduct(Zinc).addOreByProduct(GarnetYellow).addOreByProduct(Cadmium);
      Chalcopyrite.addOreByProduct(Pyrite).addOreByProduct(Cobalt).addOreByProduct(Cadmium);
      Pentlandite.addOreByProduct(Iron).addOreByProduct(Sulfur).addOreByProduct(Cobalt);
      Uranium.addOreByProduct(Lead).addOreByProduct(Plutonium).addOreByProduct(Thorium);
      Scheelite.addOreByProduct(Manganese).addOreByProduct(Molybdenum).addOreByProduct(Calcium);
      Tungstate.addOreByProduct(Manganese).addOreByProduct(Silver).addOreByProduct(Lithium);
      Tungsten.addOreByProduct(Manganese).addOreByProduct(Molybdenum);
      Diatomite.addOreByProduct(BandedIron).addOreByProduct(Sapphire);
      Iron.addOreByProduct(Nickel).addOreByProduct(Tin);
      Lepidolite.addOreByProduct(Lithium).addOreByProduct(Caesium);
      Gold.addOreByProduct(Copper).addOreByProduct(Nickel);
      Tin.addOreByProduct(Iron).addOreByProduct(Zinc);
      Antimony.addOreByProduct(Zinc).addOreByProduct(Iron);
      Silver.addOreByProduct(Lead).addOreByProduct(Sulfur);
      Lead.addOreByProduct(Silver).addOreByProduct(Sulfur);
      Thorium.addOreByProduct(Uranium).addOreByProduct(Lead);
      Plutonium.addOreByProduct(Uranium).addOreByProduct(Lead);
      Electrum.addOreByProduct(Gold).addOreByProduct(Silver);
      Bronze.addOreByProduct(Copper).addOreByProduct(Tin);
      Brass.addOreByProduct(Copper).addOreByProduct(Zinc);
      Coal.addOreByProduct(Lignite).addOreByProduct(Thorium);
      Redstone.addOreByProduct(Cinnabar).addOreByProduct(Glowstone);
      Glowstone.addOreByProduct(Redstone).addOreByProduct(Gold);
      Ilmenite.addOreByProduct(Iron).addOreByProduct(Titanium);
      Bauxite.addOreByProduct(Grossular).addOreByProduct(Titanium);
      Manganese.addOreByProduct(Chrome).addOreByProduct(Iron);
      Sapphire.addOreByProduct(Aluminium).addOreByProduct(GreenSapphire);
      GreenSapphire.addOreByProduct(Aluminium).addOreByProduct(Sapphire);
      Platinum.addOreByProduct(Nickel).addOreByProduct(Iridium);
      Emerald.addOreByProduct(Beryllium).addOreByProduct(Aluminium);
      Olivine.addOreByProduct(Pyrope).addOreByProduct(Magnesium);
      Chrome.addOreByProduct(Iron).addOreByProduct(Magnesium);
      Chromite.addOreByProduct(Iron).addOreByProduct(Magnesium);
      Tetrahedrite.addOreByProduct(Antimony).addOreByProduct(Zinc);
      QuartzSand.addOreByProduct(CertusQuartz).addOreByProduct(Quartzite);
      GarnetSand.addOreByProduct(GarnetRed).addOreByProduct(GarnetYellow);
      Magnetite.addOreByProduct(Iron).addOreByProduct(Gold);
      GraniticMineralSand.addOreByProduct(GraniteBlack).addOreByProduct(Magnetite);
      BasalticMineralSand.addOreByProduct(Basalt).addOreByProduct(Magnetite);
      Basalt.addOreByProduct(Olivine).addOreByProduct(DarkAsh);
      Celestine.addOreByProduct(Strontium).addOreByProduct(Sulfur);
      VanadiumMagnetite.addOreByProduct(Magnetite).addOreByProduct(Vanadium);
      Lazurite.addOreByProduct(Sodalite).addOreByProduct(Lapis);
      Sodalite.addOreByProduct(Lazurite).addOreByProduct(Lapis);
      Spodumene.addOreByProduct(Aluminium).addOreByProduct(Lithium);
      Ruby.addOreByProduct(Chrome).addOreByProduct(GarnetRed);
      Phosphorus.addOreByProduct(Apatite).addOreByProduct(Phosphate);
      Iridium.addOreByProduct(Platinum).addOreByProduct(Osmium);
      Pyrope.addOreByProduct(GarnetRed).addOreByProduct(Magnesium);
      Almandine.addOreByProduct(GarnetRed).addOreByProduct(Aluminium);
      Spessartine.addOreByProduct(GarnetRed).addOreByProduct(Manganese);
      Andradite.addOreByProduct(GarnetYellow).addOreByProduct(Iron);
      Grossular.addOreByProduct(GarnetYellow).addOreByProduct(Calcium);
      Uvarovite.addOreByProduct(GarnetYellow).addOreByProduct(Chrome);
      YellowLimonite.addOreByProduct(Nickel).addOreByProduct(Cobalt);
      BrownLimonite.addOreByProduct(YellowLimonite);
      Pyrolusite.addOreByProduct(Manganese);
      Molybdenite.addOreByProduct(Molybdenum);
      Stibnite.addOreByProduct(Antimony);
      Garnierite.addOreByProduct(Nickel);
      Lignite.addOreByProduct(Coal);
      Diamond.addOreByProduct(Graphite);
      Beryllium.addOreByProduct(Emerald);
      Forcicium.addOreByProduct(Thorium);
      Forcillium.addOreByProduct(Thorium);
      Monazite.addOreByProduct(Thorium);
      Quartzite.addOreByProduct(CertusQuartz);
      CertusQuartz.addOreByProduct(Quartzite);
      Calcite.addOreByProduct(Andradite);
      Apatite.addOreByProduct(Phosphorus);
      Zinc.addOreByProduct(Tin);
      Nikolite.addOreByProduct(Diamond);
      Magnesite.addOreByProduct(Magnesium);
      NetherQuartz.addOreByProduct(Netherrack);
      PigIron.addOreByProduct(Iron);
      DeepIron.addOreByProduct(Iron);
      ShadowIron.addOreByProduct(Iron);
      DarkIron.addOreByProduct(Iron);
      MeteoricIron.addOreByProduct(Iron);
      Steel.addOreByProduct(Iron);
      Mithril.addOreByProduct(Platinum);
      Midasium.addOreByProduct(Gold);
      AstralSilver.addOreByProduct(Silver);
      Graphite.addOreByProduct(Carbon);
      Netherrack.addOreByProduct(Sulfur);
      Flint.addOreByProduct(Obsidian);
      Cobaltite.addOreByProduct(Cobalt);
      Cobalt.addOreByProduct(Cobaltite);
      Sulfur.addOreByProduct(Sulfur);
      Saltpeter.addOreByProduct(Saltpeter);
      Endstone.addOreByProduct(Helium_3);
      Osmium.addOreByProduct(Iridium);
      Magnesium.addOreByProduct(Olivine);
      Aluminium.addOreByProduct(Bauxite);
      Titanium.addOreByProduct(Almandine);
      Obsidian.addOreByProduct(Olivine);
      Ash.addOreByProduct(Carbon);
      DarkAsh.addOreByProduct(Carbon);
      Redrock.addOreByProduct(Clay);
      Marble.addOreByProduct(Calcite);
      Clay.addOreByProduct(Clay);
      Cassiterite.addOreByProduct(Tin);
      CassiteriteSand.addOreByProduct(Tin);
      GraniteBlack.addOreByProduct(Biotite);
      GraniteRed.addOreByProduct(PotassiumFeldspar);
      Phosphate.addOreByProduct(Phosphor);
      Phosphor.addOreByProduct(Phosphate);
      Jade.addOreByProduct(Jade);
      Tanzanite.addOreByProduct(Opal);
      Opal.addOreByProduct(Tanzanite);
      Amethyst.addOreByProduct(Amethyst);
      Jasper.addOreByProduct(FoolsRuby);
      FoolsRuby.addOreByProduct(Jasper);
      Amber.addOreByProduct(Amber);
      Topaz.addOreByProduct(BlueTopaz);
      BlueTopaz.addOreByProduct(Topaz);
      Niter.addOreByProduct(Saltpeter);
      Vinteum.addOreByProduct(Vinteum);
      Force.addOreByProduct(Force);
      Dilithium.addOreByProduct(Dilithium);
      Naquadah.addOreByProduct(Naquadah);
      Neutronium.addOreByProduct(Neutronium);
      Lithium.addOreByProduct(Lithium);
      Silicon.addOreByProduct(SiliconDioxide);
      Salt.addOreByProduct(RockSalt);
      RockSalt.addOreByProduct(Salt);
      FoolsRuby.mChemicalFormula = Ruby.mChemicalFormula;
      Naquadah.mChemicalFormula = "Nq";
      VERSION = 408;
   }
}
