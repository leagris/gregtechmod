package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_Recipe {

   public static volatile int VERSION = 408;
   public static final ArrayList<GT_Recipe> sFusionRecipes = new ArrayList(50);
   public static final ArrayList<GT_Recipe> sCentrifugeRecipes = new ArrayList(1000);
   public static final ArrayList<GT_Recipe> sElectrolyzerRecipes = new ArrayList(200);
   public static final ArrayList<GT_Recipe> sGrinderRecipes = new ArrayList(200);
   public static final ArrayList<GT_Recipe> sBlastRecipes = new ArrayList(300);
   public static final ArrayList<GT_Recipe> sImplosionRecipes = new ArrayList(50);
   public static final ArrayList<GT_Recipe> sSawmillRecipes = new ArrayList(100);
   public static final ArrayList<GT_Recipe> sVacuumRecipes = new ArrayList(100);
   public static final ArrayList<GT_Recipe> sChemicalRecipes = new ArrayList(100);
   public static final ArrayList<GT_Recipe> sDistillationRecipes = new ArrayList(50);
   public static final ArrayList<GT_Recipe> sWiremillRecipes = new ArrayList(50);
   public static final ArrayList<GT_Recipe> sBenderRecipes = new ArrayList(400);
   public static final ArrayList<GT_Recipe> sAlloySmelterRecipes = new ArrayList(2000);
   public static final ArrayList<GT_Recipe> sAssemblerRecipes = new ArrayList(300);
   public static final ArrayList<GT_Recipe> sCannerRecipes = new ArrayList(300);
   public static final ArrayList<GT_Recipe> sCNCRecipes = new ArrayList(100);
   public static final ArrayList<GT_Recipe> sLatheRecipes = new ArrayList(400);
   public static final ArrayList<GT_Recipe> sCutterRecipes = new ArrayList(200);
   public static final ArrayList<GT_Recipe> sExtruderRecipes = new ArrayList(1000);
   public static final ArrayList<GT_Recipe> sHammerRecipes = new ArrayList(200);
   public static final ArrayList<GT_Recipe> sDieselFuels = new ArrayList();
   public static final ArrayList<GT_Recipe> sTurbineFuels = new ArrayList();
   public static final ArrayList<GT_Recipe> sHotFuels = new ArrayList();
   public static final ArrayList<GT_Recipe> sDenseLiquidFuels = new ArrayList();
   public static final ArrayList<GT_Recipe> sPlasmaFuels = new ArrayList();
   public static final ArrayList<GT_Recipe> sMagicFuels = new ArrayList();
   private static final IdentityHashMap<List<GT_Recipe>, HashMap<Integer, List<GT_Recipe>>> sRecipeMappings = new IdentityHashMap();
   public ItemStack[] mInputs;
   public ItemStack[] mOutputs;
   public int mDuration;
   public int mEUt;
   public int mStartEU;
   public boolean mEnabled;


   public static void reInit() {
      GT_Log.out.println("GT_Mod: Re-Unificating Recipes.");
      Iterator i$ = sRecipeMappings.entrySet().iterator();

      while(i$.hasNext()) {
         Entry tMapEntry = (Entry)i$.next();
         HashMap tMap = (HashMap)tMapEntry.getValue();
         if(tMap != null) {
            tMap.clear();
         }

         Iterator i$1 = ((List)tMapEntry.getKey()).iterator();

         while(i$1.hasNext()) {
            GT_Recipe tRecipe = (GT_Recipe)i$1.next();
            GT_OreDictUnificator.setStackArray(true, tRecipe.mInputs);
            GT_OreDictUnificator.setStackArray(true, tRecipe.mOutputs);
            if(tMap != null) {
               tRecipe.addToMap(tMap);
            }
         }
      }

   }

   public ItemStack getRepresentativeInput1() {
      return this.getRepresentativeInput(0);
   }

   public ItemStack getRepresentativeInput2() {
      return this.getRepresentativeInput(1);
   }

   public ItemStack getRepresentativeInput(int aIndex) {
      return aIndex >= 0 && aIndex < this.mInputs.length?GT_Utility.copy(new Object[]{this.mInputs[aIndex]}):null;
   }

   public ItemStack getOutput(int aIndex) {
      return aIndex >= 0 && aIndex < this.mOutputs.length?GT_Utility.copy(new Object[]{this.mOutputs[aIndex]}):null;
   }

   private final void addToMap(HashMap<Integer, List<GT_Recipe>> aMap) {
      ItemStack[] arr$ = this.mInputs;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ItemStack tStack = arr$[i$];
         if(tStack != null) {
            Integer tIntStack = Integer.valueOf(GT_Utility.stackToInt(tStack));
            Object tList = (List)aMap.get(tIntStack);
            if(tList == null) {
               aMap.put(tIntStack, tList = new ArrayList(2));
            }

            ((List)tList).add(this);
         }
      }

   }

   private final void addToLists(List<GT_Recipe> aList) {
      HashMap aMap = (HashMap)sRecipeMappings.get(aList);
      if(aMap == null) {
         sRecipeMappings.put(aList, aMap = new HashMap());
      }

      aList.add(this);
      this.addToMap(aMap);
   }

   public static GT_Recipe findEqualRecipe(boolean aShapeless, boolean aNotUnificated, List<GT_Recipe> aList, ItemStack ... aInputs) {
      if(aInputs.length < 1) {
         return null;
      } else {
         HashMap tMap = (HashMap)sRecipeMappings.get(aList);
         if(aNotUnificated) {
            GT_OreDictUnificator.setStackArray(true, aInputs);
         }

         if(tMap == null) {
            Iterator arr$ = aList.iterator();

            while(arr$.hasNext()) {
               GT_Recipe len$ = (GT_Recipe)arr$.next();
               if(len$.isRecipeInputEqual(aShapeless, false, aInputs)) {
                  return len$.mEnabled?len$:null;
               }
            }
         } else {
            ItemStack[] var11 = aInputs;
            int var12 = aInputs.length;

            for(int i$ = 0; i$ < var12; ++i$) {
               ItemStack tStack = var11[i$];
               if(tStack != null) {
                  aList = (List)tMap.get(Integer.valueOf(GT_Utility.stackToInt(tStack)));
                  Iterator i$1;
                  GT_Recipe tRecipe;
                  if(aList != null) {
                     i$1 = aList.iterator();

                     while(i$1.hasNext()) {
                        tRecipe = (GT_Recipe)i$1.next();
                        if(tRecipe.isRecipeInputEqual(aShapeless, false, aInputs)) {
                           return tRecipe.mEnabled?tRecipe:null;
                        }
                     }
                  }

                  aList = (List)tMap.get(Integer.valueOf(GT_Utility.stackToWildcard(tStack)));
                  if(aList != null) {
                     i$1 = aList.iterator();

                     while(i$1.hasNext()) {
                        tRecipe = (GT_Recipe)i$1.next();
                        if(tRecipe.isRecipeInputEqual(aShapeless, false, aInputs)) {
                           return tRecipe.mEnabled?tRecipe:null;
                        }
                     }
                  }
               }
            }
         }

         return null;
      }
   }

   public void checkCellBalance() {
      if(GregTech_API.SECONDARY_DEBUG_MODE && this.mInputs.length >= 1) {
         int tInputAmount = GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(this.mInputs);
         int tOutputAmount = GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(this.mOutputs);
         if(tInputAmount < tOutputAmount) {
            if(!Materials.Tin.contains(this.mInputs)) {
               GT_Log.err.println("You get more Cells, than you put in? There must be something wrong.");
               (new Exception()).printStackTrace(GT_Log.err);
            }
         } else if(tInputAmount > tOutputAmount && !Materials.Tin.contains(this.mOutputs)) {
            GT_Log.err.println("You get less Cells, than you put in? GT Machines usually don\'t destroy Cells.");
            (new Exception()).printStackTrace(GT_Log.err);
         }

      }
   }

   public boolean isRecipeInputEqual(boolean aShapeless, boolean aDecreaseStacksizeBySuccess, ItemStack ... aInputs) {
      if(aInputs.length >= 1 && this.mInputs.length >= 1) {
         if(aShapeless && aInputs.length > 1 && aInputs[1] != null && this.isRecipeInputEqual(false, aDecreaseStacksizeBySuccess, new ItemStack[]{aInputs[1], aInputs[0]})) {
            return true;
         } else if(aInputs[0] != null && (GT_Utility.areUnificationsEqual(aInputs[0], this.mInputs[0], true) || GT_Utility.areUnificationsEqual(GT_OreDictUnificator.get(false, aInputs[0]), this.mInputs[0], true)) && aInputs[0].field_77994_a >= this.mInputs[0].field_77994_a && (this.mInputs.length < 2 || aInputs.length > 1 && (GT_Utility.areUnificationsEqual(aInputs[1], this.mInputs[1], true) || GT_Utility.areUnificationsEqual(GT_OreDictUnificator.get(false, aInputs[1]), this.mInputs[1], true)) && aInputs[1].field_77994_a >= this.mInputs[1].field_77994_a)) {
            if(aDecreaseStacksizeBySuccess) {
               aInputs[0].field_77994_a -= this.mInputs[0].field_77994_a;
               if(this.mInputs.length > 1) {
                  aInputs[1].field_77994_a -= this.mInputs[1].field_77994_a;
               }
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean addRecipe(List<GT_Recipe> aList, boolean aShapeless, ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt, int aStartEU) {
      return addRecipe(aList, aShapeless, new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt, aStartEU));
   }

   public static boolean addRecipe(List<GT_Recipe> aList, boolean aShapeless, GT_Recipe aRecipe) {
      if(findEqualRecipe(aShapeless, false, aList, aRecipe.mInputs) != null) {
         return false;
      } else {
         aRecipe.addToLists(aList);
         return true;
      }
   }

   private GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt, int aStartEU) {
      this.mEnabled = true;
      aInput1 = GT_OreDictUnificator.get(true, aInput1);
      aInput2 = GT_OreDictUnificator.get(true, aInput2);
      aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
      aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
      aOutput3 = GT_OreDictUnificator.get(true, aOutput3);
      aOutput4 = GT_OreDictUnificator.get(true, aOutput4);
      if(aInput1 != null && aInput1.func_77960_j() != 32767) {
         if(GT_Utility.areStacksEqual(aInput1, aOutput1)) {
            if(aInput1.field_77994_a >= aOutput1.field_77994_a) {
               aInput1.field_77994_a -= aOutput1.field_77994_a;
               aOutput1 = null;
            } else {
               aOutput1.field_77994_a -= aInput1.field_77994_a;
            }
         }

         if(GT_Utility.areStacksEqual(aInput1, aOutput2)) {
            if(aInput1.field_77994_a >= aOutput2.field_77994_a) {
               aInput1.field_77994_a -= aOutput2.field_77994_a;
               aOutput2 = null;
            } else {
               aOutput2.field_77994_a -= aInput1.field_77994_a;
            }
         }

         if(GT_Utility.areStacksEqual(aInput1, aOutput3)) {
            if(aInput1.field_77994_a >= aOutput3.field_77994_a) {
               aInput1.field_77994_a -= aOutput3.field_77994_a;
               aOutput3 = null;
            } else {
               aOutput3.field_77994_a -= aInput1.field_77994_a;
            }
         }

         if(GT_Utility.areStacksEqual(aInput1, aOutput4)) {
            if(aInput1.field_77994_a >= aOutput4.field_77994_a) {
               aInput1.field_77994_a -= aOutput4.field_77994_a;
               aOutput4 = null;
            } else {
               aOutput4.field_77994_a -= aInput1.field_77994_a;
            }
         }
      }

      if(aInput2 != null && aInput2.func_77960_j() != 32767) {
         if(GT_Utility.areStacksEqual(aInput2, aOutput1)) {
            assert aOutput1 != null;

            if(aInput2.field_77994_a >= aOutput1.field_77994_a) {
               aInput2.field_77994_a -= aOutput1.field_77994_a;
               aOutput1 = null;
            } else {
               aOutput1.field_77994_a -= aInput2.field_77994_a;
            }
         }

         if(GT_Utility.areStacksEqual(aInput2, aOutput2)) {
            assert aOutput2 != null;

            if(aInput2.field_77994_a >= aOutput2.field_77994_a) {
               aInput2.field_77994_a -= aOutput2.field_77994_a;
               aOutput2 = null;
            } else {
               aOutput2.field_77994_a -= aInput2.field_77994_a;
            }
         }

         if(GT_Utility.areStacksEqual(aInput2, aOutput3)) {
            assert aOutput3 != null;

            if(aInput2.field_77994_a >= aOutput3.field_77994_a) {
               aInput2.field_77994_a -= aOutput3.field_77994_a;
               aOutput3 = null;
            } else {
               aOutput3.field_77994_a -= aInput2.field_77994_a;
            }
         }

         if(GT_Utility.areStacksEqual(aInput2, aOutput4)) {
            assert aOutput4 != null;

            if(aInput2.field_77994_a >= aOutput4.field_77994_a) {
               aInput2.field_77994_a -= aOutput4.field_77994_a;
               aOutput4 = null;
            } else {
               aOutput4.field_77994_a -= aInput2.field_77994_a;
            }
         }
      }

      for(byte i = 64; i > 1; --i) {
         if(aDuration / i > 0 && (aInput1 == null || aInput1.field_77994_a % i == 0) && (aInput2 == null || aInput2.field_77994_a % i == 0) && (aOutput1 == null || aOutput1.field_77994_a % i == 0) && (aOutput2 == null || aOutput2.field_77994_a % i == 0) && (aOutput3 == null || aOutput3.field_77994_a % i == 0) && (aOutput4 == null || aOutput4.field_77994_a % i == 0)) {
            if(aInput1 != null) {
               aInput1.field_77994_a /= i;
            }

            if(aInput2 != null) {
               aInput2.field_77994_a /= i;
            }

            if(aOutput1 != null) {
               aOutput1.field_77994_a /= i;
            }

            if(aOutput2 != null) {
               aOutput2.field_77994_a /= i;
            }

            if(aOutput3 != null) {
               aOutput3.field_77994_a /= i;
            }

            if(aOutput4 != null) {
               aOutput4.field_77994_a /= i;
            }

            aDuration /= i;
         }
      }

      if(aInput1 == null) {
         this.mInputs = new ItemStack[0];
      } else if(aInput2 == null) {
         this.mInputs = new ItemStack[]{aInput1};
      } else {
         this.mInputs = new ItemStack[]{aInput1, aInput2};
      }

      this.mOutputs = new ItemStack[]{aOutput1, aOutput2, aOutput3, aOutput4};
      this.mDuration = aDuration;
      this.mStartEU = aStartEU;
      this.mEUt = aEUt;
   }

   public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, int aStartEU, int aType) {
      this(aInput1, aOutput1, (ItemStack)null, (ItemStack)null, (ItemStack)null, aStartEU, aType);
   }

   public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aStartEU, int aType) {
      this(aInput1, (ItemStack)null, aOutput1, aOutput2, aOutput3, aOutput4, 0, 0, Math.max(1, aStartEU));
      if(this.mInputs.length > 0 && aStartEU > 0) {
         switch(aType) {
         case 0:
            this.addToLists(sDieselFuels);
            break;
         case 1:
            this.addToLists(sTurbineFuels);
            break;
         case 2:
            this.addToLists(sHotFuels);
            break;
         case 3:
         default:
            this.addToLists(sDenseLiquidFuels);
            break;
         case 4:
            this.addToLists(sPlasmaFuels);
            break;
         case 5:
            this.addToLists(sMagicFuels);
         }
      }

   }

   public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, int aStartEU) {
      this(aInput1, aInput2, aOutput1, (ItemStack)null, (ItemStack)null, (ItemStack)null, Math.max(aDuration, 1), aEUt, Math.max(Math.min(aStartEU, 160000000), 0));
      if(this.mInputs.length > 1 && findEqualRecipe(true, false, sFusionRecipes, this.mInputs) == null) {
         this.addToLists(sFusionRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration) {
      this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), 5, 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(false, false, sCentrifugeRecipes, this.mInputs) == null) {
         this.addToLists(sCentrifugeRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
      this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(false, false, sElectrolyzerRecipes, this.mInputs) == null) {
         this.addToLists(sElectrolyzerRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
      this(aInput1, (ItemStack)null, aOutput1, aOutput2, (ItemStack)null, (ItemStack)null, aDuration, aEUt, 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(true, false, sLatheRecipes, new ItemStack[]{this.mInputs[0]}) == null) {
         this.addToLists(sLatheRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, int aDuration, ItemStack aOutput1, int aEUt) {
      this(aInput1, (ItemStack)null, aOutput1, (ItemStack)null, (ItemStack)null, (ItemStack)null, aDuration, aEUt, 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(true, false, sCutterRecipes, new ItemStack[]{this.mInputs[0]}) == null) {
         this.addToLists(sCutterRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
      this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, (ItemStack)null, 200 * aInput1.field_77994_a, 30, 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(false, false, sSawmillRecipes, this.mInputs) == null) {
         this.addToLists(sSawmillRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
      this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, 100 * aInput1.field_77994_a, 120, 0);
      if(this.mInputs.length > 0 && aInput2 != null && this.mOutputs[0] != null && findEqualRecipe(false, false, sGrinderRecipes, this.mInputs) == null) {
         this.addToLists(sGrinderRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, int aCellAmount, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
      this(aInput1, aCellAmount > 0?GT_Items.Cell_Empty.get((long)Math.min(64, Math.max(1, aCellAmount)), new Object[0]):null, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(false, false, sDistillationRecipes, this.mInputs) == null) {
         this.addToLists(sDistillationRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt, int aLevel) {
      this(aInput1, aInput2, aOutput1, aOutput2, (ItemStack)null, (ItemStack)null, Math.max(aDuration, 1), Math.max(aEUt, 1), aLevel > 0?aLevel:100);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(true, false, sBlastRecipes, this.mInputs) == null) {
         this.addToLists(sBlastRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2) {
      this(aInput1, GT_ModHandler.getIC2Item("industrialTnt", aInput2 > 0?(aInput2 < 64?(long)aInput2:64L):1L, new ItemStack(Block.field_72091_am, aInput2 > 0?(aInput2 < 64?aInput2:64):1)), aOutput1, aOutput2, (ItemStack)null, (ItemStack)null, 20, 30, 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(false, false, sImplosionRecipes, this.mInputs) == null) {
         this.addToLists(sImplosionRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, int aEUt, int aDuration, ItemStack aOutput1) {
      this(aInput1, (ItemStack)null, aOutput1, (ItemStack)null, (ItemStack)null, (ItemStack)null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(true, false, sWiremillRecipes, new ItemStack[]{this.mInputs[0]}) == null) {
         this.addToLists(sWiremillRecipes);
      }

   }

   public GT_Recipe(int aEUt, int aDuration, ItemStack aInput1, ItemStack aOutput1) {
      this(aInput1, GT_Items.Circuit_Integrated.getWithDamage(0L, (long)aInput1.field_77994_a, new Object[0]), aOutput1, (ItemStack)null, (ItemStack)null, (ItemStack)null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(false, false, sBenderRecipes, this.mInputs) == null) {
         this.addToLists(sBenderRecipes);
      }

   }

   public GT_Recipe(int aEUt, int aDuration, ItemStack aInput1, ItemStack aShape, ItemStack aOutput1) {
      this(aInput1, aShape, aOutput1, (ItemStack)null, (ItemStack)null, (ItemStack)null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
      if(this.mInputs.length > 1 && this.mOutputs[0] != null && findEqualRecipe(false, false, sExtruderRecipes, this.mInputs) == null) {
         this.addToLists(sExtruderRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, int aEUt, ItemStack aInput2, int aDuration, ItemStack aOutput1) {
      this(aInput1, aInput2, aOutput1, (ItemStack)null, (ItemStack)null, (ItemStack)null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(true, false, sAssemblerRecipes, this.mInputs) == null) {
         this.addToLists(sAssemblerRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, ItemStack aInput2, int aEUt, int aDuration, ItemStack aOutput1) {
      this(aInput1, aInput2, aOutput1, (ItemStack)null, (ItemStack)null, (ItemStack)null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(true, false, sAlloySmelterRecipes, this.mInputs) == null) {
         this.addToLists(sAlloySmelterRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, int aEUt, ItemStack aInput2, int aDuration, ItemStack aOutput1, ItemStack aOutput2) {
      this(aInput1, aInput2, aOutput1, aOutput2, (ItemStack)null, (ItemStack)null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(true, false, sCannerRecipes, this.mInputs) == null) {
         this.addToLists(sCannerRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, int aDuration) {
      this(aInput1, (ItemStack)null, aOutput1, (ItemStack)null, (ItemStack)null, (ItemStack)null, Math.max(aDuration, 1), 120, 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(true, false, sVacuumRecipes, new ItemStack[]{this.mInputs[0]}) == null) {
         this.addToLists(sVacuumRecipes);
      }

   }

   public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration) {
      this(aInput1, aInput2, aOutput1, (ItemStack)null, (ItemStack)null, (ItemStack)null, Math.max(aDuration, 1), 30, 0);
      if(this.mInputs.length > 0 && this.mOutputs[0] != null && findEqualRecipe(true, false, sChemicalRecipes, this.mInputs) == null) {
         this.addToLists(sChemicalRecipes);
      }

   }

}
