package gregtechmod.api.util;

import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GT_OreDictUnificator {

   public static volatile int VERSION = 408;
   private static final HashMap<String, ItemStack> sName2OreMap = new HashMap();
   private static final HashMap<Integer, String> sItemhash2NameMap = new HashMap();
   private static final ArrayList<Integer> sBlackList = new ArrayList();
   private static int isRegisteringOre = 0;
   private static int isAddingOre = 0;


   public static void addToBlacklist(ItemStack aStack) {
      if(GT_Utility.isStackValid(aStack)) {
         sBlackList.add(Integer.valueOf(GT_Utility.stackToInt(aStack)));
      }

   }

   public static boolean isBlacklisted(ItemStack aStack) {
      return GT_Utility.isItemStackInList(aStack, sBlackList);
   }

   public static void add(OrePrefixes aPrefix, Object aMaterial, ItemStack aStack) {
      add(aPrefix.get(aMaterial), aStack);
   }

   public static void add(Object aName, ItemStack aStack) {
      set(aName, aStack, false, false);
   }

   public static void set(OrePrefixes aPrefix, Object aMaterial, ItemStack aStack) {
      set(aPrefix.get(aMaterial), aStack);
   }

   public static void set(Object aName, ItemStack aStack) {
      set(aName, aStack, true, false);
   }

   public static void set(Object aName, ItemStack aStack, boolean aOverwrite, boolean aAlreadyRegistered) {
      if(!GT_Utility.isStringInvalid(aName) && !GT_Utility.isStackInvalid(aStack) && Item.field_77676_L.getDamage(aStack) != 32767) {
         ++isAddingOre;
         aStack = GT_Utility.copyAmount(1L, new Object[]{aStack});
         if(!aAlreadyRegistered) {
            registerOre(aName.toString(), aStack);
         }

         addAssociation(aName, aStack);
         if(aOverwrite || GT_Utility.isStackInvalid(sName2OreMap.get(aName.toString()))) {
            sName2OreMap.put(aName.toString(), aStack);
         }

         --isAddingOre;
      }
   }

   public static ItemStack getFirstOre(Object aName, long aAmount) {
      return GT_Utility.isStringInvalid(aName)?null:(GT_Utility.isStackValid(sName2OreMap.get(aName.toString()))?GT_Utility.copyAmount(aAmount, new Object[]{sName2OreMap.get(aName.toString())}):GT_Utility.copyAmount(aAmount, getOres(aName).toArray()));
   }

   public static ItemStack get(Object aName, long aAmount) {
      return get(aName, (ItemStack)null, aAmount, true, true);
   }

   public static ItemStack get(Object aName, ItemStack aReplacement, long aAmount) {
      return get(aName, aReplacement, aAmount, true, true);
   }

   public static ItemStack get(OrePrefixes aPrefix, Object aMaterial, long aAmount) {
      return get(aPrefix, aMaterial, (ItemStack)null, aAmount);
   }

   public static ItemStack get(OrePrefixes aPrefix, Object aMaterial, ItemStack aReplacement, long aAmount) {
      return get(aPrefix.get(aMaterial), aReplacement, aAmount, false, true);
   }

   public static ItemStack get(Object aName, ItemStack aReplacement, long aAmount, boolean aMentionPossibleTypos, boolean aNoInvalidAmounts) {
      if(aNoInvalidAmounts && aAmount < 1L) {
         return null;
      } else {
         if(!sName2OreMap.containsKey(aName.toString()) && aMentionPossibleTypos) {
            GT_Log.err.println("Unknown Key for Unification, Typo? " + aName);
         }

         return GT_Utility.copyAmount(aAmount, new Object[]{sName2OreMap.get(aName.toString()), getFirstOre(aName, aAmount), aReplacement});
      }
   }

   public static ItemStack[] setStackArray(boolean aUseBlackList, ItemStack ... aStacks) {
      for(int i = 0; i < aStacks.length; ++i) {
         aStacks[i] = get(aUseBlackList, GT_Utility.copy(new Object[]{aStacks[i]}));
      }

      return aStacks;
   }

   public static ItemStack[] getStackArray(boolean aUseBlackList, Object ... aStacks) {
      ItemStack[] rStacks = new ItemStack[aStacks.length];

      for(int i = 0; i < aStacks.length; ++i) {
         rStacks[i] = get(aUseBlackList, GT_Utility.copy(new Object[]{aStacks[i]}));
      }

      return rStacks;
   }

   public static ItemStack setStack(ItemStack aStack) {
      return setStack(true, aStack);
   }

   public static ItemStack setStack(boolean aUseBlackList, ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return aStack;
      } else {
         ItemStack tStack = get(aUseBlackList, aStack);
         if(GT_Utility.areStacksEqual(aStack, tStack)) {
            return aStack;
         } else {
            aStack.field_77993_c = tStack.field_77993_c;
            Item.field_77676_L.setDamage(aStack, Item.field_77676_L.getDamage(tStack));
            return aStack;
         }
      }
   }

   public static ItemStack get(ItemStack aStack) {
      return get(true, aStack);
   }

   public static ItemStack get(boolean aUseBlackList, ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return null;
      } else if(aUseBlackList && GT_Utility.isItemStackInList(aStack, sBlackList)) {
         return GT_Utility.copy(new Object[]{aStack});
      } else {
         String tName = (String)sItemhash2NameMap.get(Integer.valueOf(GT_Utility.stackToInt(aStack)));
         ItemStack rStack = null;
         if(GT_Utility.isStringValid(tName)) {
            rStack = (ItemStack)sName2OreMap.get(tName);
         }

         if(GT_Utility.isStackInvalid(rStack)) {
            return GT_Utility.copy(new Object[]{aStack});
         } else {
            assert rStack != null;

            rStack.func_77982_d(aStack.func_77978_p());
            return GT_Utility.copyAmount((long)aStack.field_77994_a, new Object[]{rStack});
         }
      }
   }

   public static void addAssociation(Object aName, ItemStack aStack) {
      if(!GT_Utility.isStringInvalid(aName) && !GT_Utility.isStackInvalid(aStack)) {
         if(Item.field_77676_L.getDamage(aStack) == 32767) {
            aStack = GT_Utility.copyAmount(1L, new Object[]{aStack});

            for(byte i = 0; i < 16; ++i) {
               Item.field_77676_L.setDamage(aStack, i);
               sItemhash2NameMap.put(Integer.valueOf(GT_Utility.stackToInt(aStack)), aName.toString());
            }
         }

         sItemhash2NameMap.put(Integer.valueOf(GT_Utility.stackToInt(aStack)), aName.toString());
      }
   }

   public static String getAssociation(ItemStack aStack) {
      return (String)sItemhash2NameMap.get(Integer.valueOf(GT_Utility.stackToInt(aStack)));
   }

   public static boolean isItemStackInstanceOf(ItemStack aStack, Object aName) {
      if(!GT_Utility.isStringInvalid(aName) && !GT_Utility.isStackInvalid(aStack)) {
         Iterator i$ = getOres(aName.toString()).iterator();

         ItemStack tOreStack;
         do {
            if(!i$.hasNext()) {
               return false;
            }

            tOreStack = (ItemStack)i$.next();
         } while(!GT_Utility.areStacksEqual(tOreStack, aStack, !tOreStack.func_77942_o()));

         return true;
      } else {
         return false;
      }
   }

   public static boolean isItemStackDye(ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return false;
      } else {
         Dyes[] arr$ = Dyes.values();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Dyes tDye = arr$[i$];
            if(tDye != Dyes._NULL && isItemStackInstanceOf(aStack, tDye.toString())) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean registerOre(OrePrefixes aPrefix, Object aMaterial, ItemStack aStack) {
      return registerOre(aPrefix.get(aMaterial), aStack);
   }

   public static boolean registerOre(Object aName, ItemStack aStack) {
      if(!GT_Utility.isStringInvalid(aName) && !GT_Utility.isStackInvalid(aStack)) {
         String tName = aName.toString();
         if(tName.equals("")) {
            return false;
         } else {
            ArrayList tList = getOres(tName);

            for(int i = 0; i < tList.size(); ++i) {
               if(GT_Utility.areStacksEqual((ItemStack)tList.get(i), aStack, true)) {
                  return false;
               }
            }

            ++isRegisteringOre;
            OreDictionary.registerOre(tName, GT_Utility.copyAmount(1L, new Object[]{aStack}));
            --isRegisteringOre;
            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean isRegisteringOres() {
      return isRegisteringOre > 0;
   }

   public static boolean isAddingOres() {
      return isAddingOre > 0;
   }

   public static ArrayList<ItemStack> getOres(OrePrefixes aPrefix, Object aMaterial) {
      return getOres(aPrefix.get(aMaterial));
   }

   public static ArrayList<ItemStack> getOres(Object aOreName) {
      String aName = aOreName == null?"":aOreName.toString();
      ArrayList rList = new ArrayList();
      if(GT_Utility.isStringValid(aName)) {
         rList.addAll(OreDictionary.getOres(aName));
      }

      return rList;
   }

}
