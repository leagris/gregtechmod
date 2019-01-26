package gregtechmod.api.enums;

import gregtechmod.api.util.GT_Utility;

public enum Dyes {

   dyeBlack("dyeBlack", 0, 0, "Black"),
   dyeRed("dyeRed", 1, 1, "Red"),
   dyeGreen("dyeGreen", 2, 2, "Green"),
   dyeBrown("dyeBrown", 3, 3, "Brown"),
   dyeBlue("dyeBlue", 4, 4, "Blue"),
   dyePurple("dyePurple", 5, 5, "Purple"),
   dyeCyan("dyeCyan", 6, 6, "Cyan"),
   dyeLightGray("dyeLightGray", 7, 7, "Light Gray"),
   dyeGray("dyeGray", 8, 8, "Gray"),
   dyePink("dyePink", 9, 9, "Pink"),
   dyeLime("dyeLime", 10, 10, "Lime"),
   dyeYellow("dyeYellow", 11, 11, "Yellow"),
   dyeLightBlue("dyeLightBlue", 12, 12, "Light Blue"),
   dyeMagenta("dyeMagenta", 13, 13, "Magenta"),
   dyeOrange("dyeOrange", 14, 14, "Orange"),
   dyeWhite("dyeWhite", 15, 15, "White"),
   _NULL("_NULL", 16, -1, "");
   public final byte mColor;
   public final String mName;
   // $FF: synthetic field
   private static final Dyes[] $VALUES = new Dyes[]{dyeBlack, dyeRed, dyeGreen, dyeBrown, dyeBlue, dyePurple, dyeCyan, dyeLightGray, dyeGray, dyePink, dyeLime, dyeYellow, dyeLightBlue, dyeMagenta, dyeOrange, dyeWhite, _NULL};


   private Dyes(String var1, int var2, int aColor, String aName) {
      this.mColor = (byte)aColor;
      this.mName = aName;
   }

   public static Dyes get(int aColor) {
      return aColor >= 0 && aColor < 16?values()[aColor]:_NULL;
   }

   public static Dyes get(String aColor) {
      Object tObject = GT_Utility.getFieldContent(Dyes.class, aColor, false, false);
      return tObject != null && tObject instanceof Dyes?(Dyes)tObject:_NULL;
   }

}
