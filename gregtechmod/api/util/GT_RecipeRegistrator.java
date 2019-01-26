package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_RecipeRegistrator {

   public static volatile int VERSION = 408;
   public static final List<Materials> sRodMaterialList = new ArrayList();
   private static final ItemStack sMt1 = new ItemStack(0, 1, 0);
   private static final ItemStack sMt2 = new ItemStack(0, 1, 1);
   private static final String s_H = "H";
   private static final String s_F = "F";
   private static final String s_I = "I";
   private static final String s_P = "P";
   private static final String s_R = "R";
   private static final String s_W = "W";
   private static final ItemStack[][] sShapes1 = new ItemStack[][]{{sMt1, null, sMt1, sMt1, sMt1, sMt1, null, sMt1, null}, {null, sMt1, null, sMt1, sMt1, sMt1, sMt1, null, sMt1}, {sMt1, sMt1, sMt1, sMt1, null, sMt1, null, null, null}, {sMt1, null, sMt1, sMt1, sMt1, sMt1, sMt1, sMt1, sMt1}, {sMt1, sMt1, sMt1, sMt1, null, sMt1, sMt1, null, sMt1}, {null, null, null, sMt1, null, sMt1, sMt1, null, sMt1}, {null, sMt1, null, null, sMt1, null, null, sMt2, null}, {sMt1, sMt1, sMt1, null, sMt2, null, null, sMt2, null}, {null, sMt1, null, null, sMt2, null, null, sMt2, null}, {sMt1, sMt1, null, sMt1, sMt2, null, null, sMt2, null}, {null, sMt1, sMt1, null, sMt2, sMt1, null, sMt2, null}, {sMt1, sMt1, null, null, sMt2, null, null, sMt2, null}, {null, sMt1, sMt1, null, sMt2, null, null, sMt2, null}, {null, sMt1, null, sMt1, null, null, null, sMt1, sMt2}, {null, sMt1, null, null, null, sMt1, sMt2, sMt1, null}, {null, sMt1, null, sMt1, null, sMt1, null, null, sMt2}, {null, sMt1, null, sMt1, null, sMt1, sMt2, null, null}, {null, sMt2, null, null, sMt1, null, null, sMt1, null}, {null, sMt2, null, null, sMt2, null, sMt1, sMt1, sMt1}, {null, sMt2, null, null, sMt2, null, null, sMt1, null}, {null, sMt2, null, sMt1, sMt2, null, sMt1, sMt1, null}, {null, sMt2, null, null, sMt2, sMt1, null, sMt1, sMt1}, {null, sMt2, null, null, sMt2, null, sMt1, sMt1, null}, {sMt1, null, null, null, sMt2, null, null, null, sMt2}, {null, null, sMt1, null, sMt2, null, sMt2, null, null}, {sMt1, null, null, null, sMt2, null, null, null, null}, {null, null, sMt1, null, sMt2, null, null, null, null}, {sMt1, sMt2, null, null, null, null, null, null, null}, {sMt2, sMt1, null, null, null, null, null, null, null}, {sMt1, null, null, sMt2, null, null, null, null, null}, {sMt2, null, null, sMt1, null, null, null, null, null}, {sMt1, sMt1, sMt1, sMt1, sMt1, sMt1, null, sMt2, null}, {sMt1, sMt1, null, sMt1, sMt1, sMt2, sMt1, sMt1, null}, {null, sMt1, sMt1, sMt2, sMt1, sMt1, null, sMt1, sMt1}, {null, sMt2, null, sMt1, sMt1, sMt1, sMt1, sMt1, sMt1}, {sMt1, sMt1, sMt1, sMt1, sMt2, sMt1, null, sMt2, null}, {sMt1, sMt1, null, sMt1, sMt2, sMt2, sMt1, sMt1, null}, {null, sMt1, sMt1, sMt2, sMt2, sMt1, null, sMt1, sMt1}, {null, sMt2, null, sMt1, sMt2, sMt1, sMt1, sMt1, sMt1}, {sMt1, null, null, null, sMt1, null, null, null, null}, {null, sMt1, null, sMt1, null, null, null, null, null}, {sMt1, sMt1, null, sMt2, null, sMt1, sMt2, null, null}, {null, sMt1, sMt1, sMt1, null, sMt2, null, null, sMt2}};
   private static final String[][] sShapesA = new String[][]{null, null, {"Helmet", "PPP", "PHP"}, {"ChestPlate", "PHP", "PPP", "PPP"}, {"Pants", "PPP", "PHP", "P P"}, {"Boots", "P P", "PHP"}, {"Sword", " P ", "FPH", " R "}, {"Pickaxe", "PII", "FRH", " R "}, {"Shovel", "FPH", " R ", " R "}, {"Axe", "PIH", "PR ", "FR "}, {"Axe", "PIH", "PR ", "FR "}, {"Hoe", "PIH", "FR ", " R "}, {"Hoe", "PIH", "FR ", " R "}, {"Sickle", " P ", "PF ", "HPR"}, {"Sickle", " P ", "PF ", "HPR"}, {"Sickle", " P ", "PF ", "HPR"}, {"Sickle", " P ", "PF ", "HPR"}, {"Sword", " R ", "FPH", " P "}, {"Pickaxe", " R ", "FRH", "PII"}, {"Shovel", " R ", " R ", "FPH"}, {"Axe", "FR ", "PR ", "PIH"}, {"Axe", "FR ", "PR ", "PIH"}, {"Hoe", " R ", "FR ", "PIH"}, {"Hoe", " R ", "FR ", "PIH"}, {"Spear", "PH ", "FR ", "  R"}, {"Spear", "PH ", "FR ", "  R"}, {"Knive", "HP", "RF"}, {"Knive", "FH", "PR"}, {"Knive", "FH", "PR"}, {"Knive", "PF", "RH"}, {"Knive", "PF", "RH"}, null, null, null, null, {"WarAxe", "PPP", "PRP", "FRH"}, null, null, null, {"Shears", "HP", "PF"}, {"Shears", "HP", "PF"}, {"Scythe", "IPH", "RFP", "R  "}, {"Scythe", "HPI", "PFR", "  R"}};


   public static void registerBasicReverseMaceratingAndSmelting(ItemStack aStack, Materials aMaterial, long aMaterialAmount, boolean aAllowAlloySmelter) {
      registerBasicReverseMacerating(aStack, aMaterial, aMaterialAmount);
      registerBasicReverseSmelting(aStack, aMaterial, aMaterialAmount, aAllowAlloySmelter);
   }

   public static void registerBasicReverseMaceratingAndSmelting(ItemStack aStack, Materials aMaterial, long aMaterialAmount) {
      registerBasicReverseMaceratingAndSmelting(aStack, aMaterial, aMaterialAmount, true);
   }

   public static void registerBasicReverseSmelting(ItemStack aStack, Materials aMaterial, long aMaterialAmount, boolean aAllowAlloySmelter) {
      if(aStack != null && aMaterial != null && aMaterialAmount > 0L) {
         aMaterialAmount /= (long)aStack.field_77994_a;
         ItemStack tStack1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L);
         ItemStack tStack2 = GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)aMaterial, 1L);
         if(tStack1 != null && aMaterialAmount % 3628800L == 0L) {
            if(aAllowAlloySmelter) {
               GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, (long)((int)(aMaterialAmount / 3628800L))));
            } else {
               GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, (long)((int)(aMaterialAmount / 3628800L))));
            }
         } else {
            boolean temp = true;
            byte tLimit = aAllowAlloySmelter?(byte)aStack.func_77976_d():1;
            byte i;
            if(tStack2 != null && temp) {
               for(i = 1; i <= tLimit && aMaterialAmount * 9L * (long)i / 3628800L <= 64L; ++i) {
                  if(aMaterialAmount * 9L * (long)i >= 3628800L && aMaterialAmount * 9L * (long)i % 3628800L == 0L && (tStack1 == null || aMaterialAmount * (long)i % 3628800L != 0L)) {
                     if(aAllowAlloySmelter) {
                        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copyAmount((long)i, new Object[]{aStack}), GT_Utility.copyAmount(aMaterialAmount * 9L * (long)i / 3628800L, new Object[]{tStack2}));
                     } else {
                        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount((long)i, new Object[]{aStack}), GT_Utility.copyAmount(aMaterialAmount * 9L * (long)i / 3628800L, new Object[]{tStack2}));
                     }

                     temp = false;
                     break;
                  }
               }
            }

            if(tStack1 != null && temp) {
               for(i = 1; i <= tLimit && aMaterialAmount * (long)i / 3628800L <= 64L; ++i) {
                  if(aMaterialAmount * (long)i >= 3628800L && aMaterialAmount * (long)i % 3628800L == 0L) {
                     if(aAllowAlloySmelter) {
                        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copyAmount((long)i, new Object[]{aStack}), GT_Utility.copyAmount(aMaterialAmount * (long)i / 3628800L, new Object[]{tStack1}));
                     } else {
                        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount((long)i, new Object[]{aStack}), GT_Utility.copyAmount(aMaterialAmount * (long)i / 3628800L, new Object[]{tStack1}));
                     }

                     temp = false;
                     break;
                  }
               }
            }

            if(tStack2 != null && temp) {
               for(i = 1; i <= tLimit && aMaterialAmount * 9L * (long)i / 3628800L <= 64L; ++i) {
                  if(aMaterialAmount * 9L * (long)i >= 3628800L) {
                     if(aAllowAlloySmelter) {
                        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copyAmount((long)i, new Object[]{aStack}), GT_Utility.copyAmount(aMaterialAmount * 9L * (long)i / 3628800L, new Object[]{tStack2}));
                     } else {
                        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount((long)i, new Object[]{aStack}), GT_Utility.copyAmount(aMaterialAmount * 9L * (long)i / 3628800L, new Object[]{tStack2}));
                     }

                     temp = false;
                     break;
                  }
               }
            }

            if(tStack1 != null && temp) {
               for(i = 1; i <= tLimit && aMaterialAmount * (long)i / 3628800L <= 64L; ++i) {
                  if(aMaterialAmount * (long)i >= 3628800L) {
                     if(aAllowAlloySmelter) {
                        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copyAmount((long)i, new Object[]{aStack}), GT_Utility.copyAmount(aMaterialAmount * (long)i / 3628800L, new Object[]{tStack1}));
                     } else {
                        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount((long)i, new Object[]{aStack}), GT_Utility.copyAmount(aMaterialAmount * (long)i / 3628800L, new Object[]{tStack1}));
                     }

                     temp = false;
                     break;
                  }
               }
            }
         }

      }
   }

   public static void registerBasicReverseMacerating(ItemStack aStack, Materials aMaterial, long aMaterialAmount) {
      if(aStack != null && aMaterial != null && aMaterialAmount > 0L) {
         aMaterialAmount /= (long)aStack.field_77994_a;
         if(aMaterialAmount % 3628800L == 0L) {
            GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, (long)((int)(aMaterialAmount / 3628800L))), (ItemStack)null, 0, true);
         } else if(aMaterialAmount * 4L % 3628800L == 0L) {
            GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)aMaterial, (long)((int)(aMaterialAmount * 4L / 3628800L))), (ItemStack)null, 0, true);
         } else if(aMaterialAmount * 9L >= 3628800L && !GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)aMaterial, (long)((int)(aMaterialAmount * 9L / 3628800L))), (ItemStack)null, 0, true) && !GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)aMaterial, (long)((int)(aMaterialAmount * 4L / 3628800L))), (ItemStack)null, 0, true)) {
            GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, (long)((int)(aMaterialAmount / 3628800L))), (ItemStack)null, 0, true);
         }

      }
   }

   public static void registerUsagesForMaterials(ItemStack aMat, ItemStack aOutput, String aPlate, boolean aBackSmelting, boolean aBackMacerating, boolean aRecipeReplacing) {
      if(aMat != null && aOutput != null) {
         aMat = GT_Utility.copy(new Object[]{aMat});
         aOutput = GT_Utility.copy(new Object[]{aOutput});
         ItemStack tUnificated = GT_OreDictUnificator.get(true, aMat);
         String aAssotiation = GT_OreDictUnificator.getAssociation(aMat);
         if(aOutput.field_77994_a < 1) {
            aOutput.field_77994_a = 1;
         }

         if(aAssotiation == null || !aAssotiation.startsWith(OrePrefixes.ingot.toString())) {
            aPlate = null;
         }

         if(aPlate != null && GT_OreDictUnificator.getFirstOre(aPlate, 1L) == null) {
            aPlate = null;
         }

         ItemStack tStack;
         if(!GT_Utility.areStacksEqual(GT_OreDictUnificator.get(aMat), new ItemStack(Item.field_77703_o, 1))) {
            if((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{aMat, null, aMat, null, aMat, null, null, null, null})) != null && GT_Utility.areStacksEqual(tStack, new ItemStack(Item.field_77788_aw, 1))) {
               GT_ModHandler.removeRecipe(new ItemStack[]{aMat, null, aMat, null, aMat, null, null, null, null});
            }

            if((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{null, null, null, aMat, null, aMat, null, aMat, null})) != null && GT_Utility.areStacksEqual(tStack, new ItemStack(Item.field_77788_aw, 1))) {
               GT_ModHandler.removeRecipe(new ItemStack[]{null, null, null, aMat, null, aMat, null, aMat, null});
            }

            if((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{aMat, null, aMat, aMat, aMat, aMat, null, null, null})) != null && GT_Utility.areStacksEqual(tStack, new ItemStack(Item.field_77773_az, 1))) {
               GT_ModHandler.removeRecipe(new ItemStack[]{aMat, null, aMat, aMat, aMat, aMat, null, null, null});
            }

            if((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{null, null, null, aMat, null, aMat, aMat, aMat, aMat})) != null && GT_Utility.areStacksEqual(tStack, new ItemStack(Item.field_77773_az, 1))) {
               GT_ModHandler.removeRecipe(new ItemStack[]{null, null, null, aMat, null, aMat, aMat, aMat, aMat});
            }
         }

         if(aBackMacerating || aBackSmelting) {
            sMt1.field_77993_c = aMat.field_77993_c;
            sMt1.field_77994_a = 1;
            Item.field_77676_L.setDamage(sMt1, Item.field_77676_L.getDamage(aMat));
            ItemStack[][] i$ = sShapes1;
            int tMaterial = i$.length;

            int i;
            ItemStack[] tRecipe;
            int tAmount1;
            int tAmount2;
            for(int tMt2 = 0; tMt2 < tMaterial; ++tMt2) {
               ItemStack[] tMt3 = i$[tMt2];
               i = 0;
               tRecipe = tMt3;
               tAmount1 = tMt3.length;

               for(tAmount2 = 0; tAmount2 < tAmount1; ++tAmount2) {
                  ItemStack i$1 = tRecipe[tAmount2];
                  if(i$1 == sMt1) {
                     ++i;
                  }
               }

               Iterator var25 = GT_ModHandler.getRecipeOutputs(tMt3).iterator();

               while(var25.hasNext()) {
                  ItemStack var26 = (ItemStack)var25.next();
                  if(aBackMacerating) {
                     GT_ModHandler.addPulverisationRecipe(var26, GT_Utility.copyAmount((long)i, new Object[]{aOutput}), (ItemStack)null, 0, false);
                  }

                  if(aBackSmelting) {
                     GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(var26, GT_Utility.copyAmount((long)i, new Object[]{tUnificated}));
                  }
               }
            }

            Iterator var21 = sRodMaterialList.iterator();

            while(var21.hasNext()) {
               Materials var22 = (Materials)var21.next();
               ItemStack var23 = GT_OreDictUnificator.get(OrePrefixes.stick, (Object)var22, 1L);
               ItemStack var24 = GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)var22, 2L);
               if(var23 != null) {
                  sMt2.field_77993_c = var23.field_77993_c;
                  sMt2.field_77994_a = 1;
                  Item.field_77676_L.setDamage(sMt2, Item.field_77676_L.getDamage(var23));

                  for(i = 0; i < sShapes1.length; ++i) {
                     tRecipe = sShapes1[i];
                     tAmount1 = 0;
                     tAmount2 = 0;
                     ItemStack[] var27 = tRecipe;
                     int tCrafted = tRecipe.length;

                     for(int i$2 = 0; i$2 < tCrafted; ++i$2) {
                        ItemStack tMat = var27[i$2];
                        if(tMat == sMt1) {
                           ++tAmount1;
                        }

                        if(tMat == sMt2) {
                           ++tAmount2;
                        }
                     }

                     Iterator var28 = GT_ModHandler.getVanillyToolRecipeOutputs(tRecipe).iterator();

                     while(var28.hasNext()) {
                        ItemStack var29 = (ItemStack)var28.next();
                        if(aBackMacerating) {
                           GT_ModHandler.addPulverisationRecipe(var29, GT_Utility.copyAmount((long)tAmount1, new Object[]{aOutput}), tAmount2 > 0?GT_Utility.mul((long)tAmount2, new Object[]{var24}):null, 100, false);
                        }

                        if(aBackSmelting) {
                           GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(var29, GT_Utility.copyAmount((long)tAmount1, new Object[]{tUnificated}));
                        }

                        if(aRecipeReplacing && aPlate != null && sShapesA[i] != null && sShapesA[i].length > 1 && GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, aAssotiation.replaceFirst(OrePrefixes.ingot.toString(), "") + "." + sShapesA[i][0], true) && null != (tStack = GT_ModHandler.removeRecipe(tRecipe))) {
                           switch(sShapesA[i].length) {
                           case 2:
                              GT_ModHandler.addCraftingRecipe(tStack, false, false, true, new Object[]{sShapesA[i][1], Character.valueOf("P".charAt(0)), aPlate, Character.valueOf("H".charAt(0)), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf("R".charAt(0)), OrePrefixes.stick.toString() + var22, Character.valueOf("I".charAt(0)), aAssotiation, Character.valueOf("F".charAt(0)), GT_ToolDictNames.craftingToolFile, Character.valueOf("W".charAt(0)), GT_ToolDictNames.craftingToolWrench});
                              break;
                           case 3:
                              GT_ModHandler.addCraftingRecipe(tStack, false, false, true, new Object[]{sShapesA[i][1], sShapesA[i][2], Character.valueOf("P".charAt(0)), aPlate, Character.valueOf("H".charAt(0)), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf("R".charAt(0)), OrePrefixes.stick.toString() + var22, Character.valueOf("I".charAt(0)), aAssotiation, Character.valueOf("F".charAt(0)), GT_ToolDictNames.craftingToolFile, Character.valueOf("W".charAt(0)), GT_ToolDictNames.craftingToolWrench});
                              break;
                           default:
                              GT_ModHandler.addCraftingRecipe(tStack, false, false, true, new Object[]{sShapesA[i][1], sShapesA[i][2], sShapesA[i][3], Character.valueOf("P".charAt(0)), aPlate, Character.valueOf("H".charAt(0)), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf("R".charAt(0)), OrePrefixes.stick.toString() + var22, Character.valueOf("I".charAt(0)), aAssotiation, Character.valueOf("F".charAt(0)), GT_ToolDictNames.craftingToolFile, Character.valueOf("W".charAt(0)), GT_ToolDictNames.craftingToolWrench});
                           }
                        }
                     }
                  }
               }
            }
         }

      }
   }

}
