package gregtechmod.api.enums;

import java.util.ArrayList;

public final class SubTag {

   private static long sSubtagID = 0L;
   public static final ArrayList<SubTag> sSubTags = new ArrayList();
   public static final SubTag BLASTFURNACE_CALCITE_DOUBLE = new SubTag("BLASTFURNACE_CALCITE_DOUBLE");
   public static final SubTag BLASTFURNACE_CALCITE_TRIPLE = new SubTag("BLASTFURNACE_CALCITE_TRIPLE");
   public static final SubTag INDUCTIONSMELTING_LOW_OUTPUT = new SubTag("INDUCTIONSMELTING_LOW_OUTPUT");
   public static final SubTag WASHING_SODIUMPERSULFATE = new SubTag("WASHING_SODIUMPERSULFATE");
   public static final SubTag WASHING_MERCURY = new SubTag("WASHING_MERCURY");
   public static final SubTag PULVERIZING_CINNABAR = new SubTag("PULVERIZING_CINNABAR");
   public static final SubTag NO_SMASHING = new SubTag("NO_SMASHING");
   public static final SubTag NO_SMELTING = new SubTag("NO_SMELTING");
   public static final SubTag SMELTING_TO_GEM = new SubTag("SMELTING_TO_GEM");
   public static final SubTag ENCHANTMENT_GLOW = new SubTag("ENCHANTMENT_GLOW");
   public final long mSubtagID;
   public final String mName;


   public SubTag(String aName) {
      this.mSubtagID = (long)(sSubtagID++);
      this.mName = aName;
      sSubTags.add(this);
   }

}
