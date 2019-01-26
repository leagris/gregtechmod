package gregtechmod.api.util;

import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class GT_Config {

   public static volatile int VERSION = 408;
   public static boolean system = false;
   public static Configuration sConfigFileIDs;
   public final Configuration mConfig;


   public static int addIDConfig(Object aCategory, String aName, int aDefault) {
      if(GT_Utility.isStringInvalid(aName)) {
         return aDefault;
      } else {
         Property tProperty = sConfigFileIDs.get(aCategory.toString().replaceAll("\\|", "."), aName.replaceAll("\\|", "."), aDefault);
         int rResult = tProperty.getInt(aDefault);
         if(!tProperty.wasRead()) {
            sConfigFileIDs.save();
         }

         return rResult;
      }
   }

   public GT_Config(Configuration aConfig) {
      this.mConfig = aConfig;
      this.mConfig.load();
      this.mConfig.save();
   }

   public static String getStackConfigName(ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return "";
      } else {
         String rName;
         if(GT_Utility.isStringValid(rName = GT_OreDictUnificator.getAssociation(aStack))) {
            return rName;
         } else {
            try {
               if(GT_Utility.isStringValid(rName = aStack.func_77977_a())) {
                  return rName;
               }
            } catch (Throwable var3) {
               ;
            }

            return aStack.func_77973_b() + "." + aStack.func_77960_j();
         }
      }
   }

   public boolean get(Object aCategory, ItemStack aStack, boolean aDefault) {
      return this.get(aCategory, getStackConfigName(aStack), aDefault);
   }

   public boolean get(Object aCategory, String aName, boolean aDefault) {
      if(GT_Utility.isStringInvalid(aName)) {
         return aDefault;
      } else {
         Property tProperty = this.mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName + "_" + aDefault).replaceAll("\\|", "_"), aDefault);
         boolean rResult = tProperty.getBoolean(aDefault);
         if(!tProperty.wasRead()) {
            this.mConfig.save();
         }

         return rResult;
      }
   }

   public int get(Object aCategory, ItemStack aStack, int aDefault) {
      return this.get(aCategory, getStackConfigName(aStack), aDefault);
   }

   public int get(Object aCategory, String aName, int aDefault) {
      if(GT_Utility.isStringInvalid(aName)) {
         return aDefault;
      } else {
         Property tProperty = this.mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName + "_" + aDefault).replaceAll("\\|", "_"), aDefault);
         int rResult = tProperty.getInt(aDefault);
         if(!tProperty.wasRead()) {
            this.mConfig.save();
         }

         return rResult;
      }
   }

   public double get(Object aCategory, ItemStack aStack, double aDefault) {
      return this.get(aCategory, getStackConfigName(aStack), aDefault);
   }

   public double get(Object aCategory, String aName, double aDefault) {
      if(GT_Utility.isStringInvalid(aName)) {
         return aDefault;
      } else {
         Property tProperty = this.mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName + "_" + aDefault).replaceAll("\\|", "_"), aDefault);
         double rResult = tProperty.getDouble(aDefault);
         if(!tProperty.wasRead()) {
            this.mConfig.save();
         }

         return rResult;
      }
   }

}
