package gregtechmod.api.util;

import cpw.mods.fml.common.registry.LanguageRegistry;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class GT_LanguageManager {

   public static volatile int VERSION = 408;
   public static Configuration sEnglishFile;


   public static String addStringLocalization(String aKey, String aEnglish) {
      return addStringLocalization(aKey, aEnglish, true);
   }

   public static String addStringLocalization(String aKey, String aEnglish, boolean aWriteIntoLangFile) {
      if(aWriteIntoLangFile) {
         aEnglish = writeToLangFile(aKey, aEnglish);
      }

      LanguageRegistry.instance().addStringLocalization(aKey.trim(), aEnglish);
      return aEnglish;
   }

   private static synchronized String writeToLangFile(String aKey, String aEnglish) {
      Property tProperty = sEnglishFile.get("LanguageFile", aKey.trim(), aEnglish);
      if(!tProperty.wasRead()) {
         sEnglishFile.save();
      }

      if(sEnglishFile.get("EnableLangFile", "UseThisFileAsLanguageFile", false).getBoolean(false)) {
         aEnglish = tProperty.getString();
      }

      return aEnglish;
   }

   public static String getTranslation(String aKey) {
      String tTrimmedKey = aKey.trim();
      String rTranslation = LanguageRegistry.instance().getStringLocalization(tTrimmedKey);
      if(GT_Utility.isStringInvalid(rTranslation)) {
         rTranslation = StatCollector.func_74838_a(tTrimmedKey);
         if(GT_Utility.isStringInvalid(rTranslation) || tTrimmedKey.equals(rTranslation)) {
            if(aKey.endsWith(".name")) {
               rTranslation = StatCollector.func_74838_a(tTrimmedKey.substring(0, tTrimmedKey.length() - 5));
               if(GT_Utility.isStringInvalid(rTranslation) || tTrimmedKey.substring(0, tTrimmedKey.length() - 5).equals(rTranslation)) {
                  return aKey;
               }
            } else {
               rTranslation = StatCollector.func_74838_a(tTrimmedKey + ".name");
               if(GT_Utility.isStringInvalid(rTranslation) || (tTrimmedKey + ".name").equals(rTranslation)) {
                  return aKey;
               }
            }
         }
      }

      return rTranslation;
   }

   public static String getTranslation(String aKey, String aSeperator) {
      String rTranslation = "";
      String[] arr$ = aKey.split(aSeperator);
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String tString = arr$[i$];
         rTranslation = rTranslation + getTranslation(tString);
      }

      return rTranslation;
   }

   public static String getTranslateableItemStackName(ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return "null";
      } else {
         NBTTagCompound tNBT = aStack.func_77978_p();
         if(tNBT != null && tNBT.func_74764_b("display")) {
            String tName = tNBT.func_74775_l("display").func_74779_i("Name");
            if(GT_Utility.isStringValid(tName)) {
               return tName;
            }
         }

         return aStack.func_77977_a() + ".name";
      }
   }

}
