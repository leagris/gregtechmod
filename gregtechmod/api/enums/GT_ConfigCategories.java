package gregtechmod.api.enums;


public enum GT_ConfigCategories {

   news("news", 0),
   general("general", 1),
   machineconfig("machineconfig", 2),
   specialunificationtargets("specialunificationtargets", 3);
   // $FF: synthetic field
   private static final GT_ConfigCategories[] $VALUES = new GT_ConfigCategories[]{news, general, machineconfig, specialunificationtargets};


   private GT_ConfigCategories(String var1, int var2) {}


   public static enum Fuels {

      boilerfuels("boilerfuels", 0);
      // $FF: synthetic field
      private static final GT_ConfigCategories.Fuels[] $VALUES = new GT_ConfigCategories.Fuels[]{boilerfuels};


      private Fuels(String var1, int var2) {}

   }

   public static enum Recipes {

      harderrecipes("harderrecipes", 0),
      gregtechrecipes("gregtechrecipes", 1),
      disabledrecipes("disabledrecipes", 2),
      recipereplacements("recipereplacements", 3),
      storageblockcrafting("storageblockcrafting", 4),
      storageblockdecrafting("storageblockdecrafting", 5);
      // $FF: synthetic field
      private static final GT_ConfigCategories.Recipes[] $VALUES = new GT_ConfigCategories.Recipes[]{harderrecipes, gregtechrecipes, disabledrecipes, recipereplacements, storageblockcrafting, storageblockdecrafting};


      private Recipes(String var1, int var2) {}

   }

   public static enum Materials {

      blastfurnacerequirements("blastfurnacerequirements", 0),
      blastinductionsmelter("blastinductionsmelter", 1),
      UUM_MaterialCost("UUM_MaterialCost", 2),
      UUM_EnergyCost("UUM_EnergyCost", 3);
      // $FF: synthetic field
      private static final GT_ConfigCategories.Materials[] $VALUES = new GT_ConfigCategories.Materials[]{blastfurnacerequirements, blastinductionsmelter, UUM_MaterialCost, UUM_EnergyCost};


      private Materials(String var1, int var2) {}

   }

   public static enum Machines {

      smelting("smelting", 0),
      squeezer("squeezer", 1),
      liquidtransposer("liquidtransposer", 2),
      liquidtransposerfilling("liquidtransposerfilling", 3),
      liquidtransposeremptying("liquidtransposeremptying", 4),
      extractor("extractor", 5),
      sawmill("sawmill", 6),
      compression("compression", 7),
      thermalcentrifuge("thermalcentrifuge", 8),
      orewashing("orewashing", 9),
      inductionsmelter("inductionsmelter", 10),
      rcblastfurnace("rcblastfurnace", 11),
      scrapboxdrops("scrapboxdrops", 12),
      massfabamplifier("massfabamplifier", 13),
      maceration("maceration", 14),
      rockcrushing("rockcrushing", 15),
      pulverization("pulverization", 16);
      // $FF: synthetic field
      private static final GT_ConfigCategories.Machines[] $VALUES = new GT_ConfigCategories.Machines[]{smelting, squeezer, liquidtransposer, liquidtransposerfilling, liquidtransposeremptying, extractor, sawmill, compression, thermalcentrifuge, orewashing, inductionsmelter, rcblastfurnace, scrapboxdrops, massfabamplifier, maceration, rockcrushing, pulverization};


      private Machines(String var1, int var2) {}

   }

   public static enum Tools {

      mortar("mortar", 0),
      hammerrings("hammerrings", 1),
      hammerplating("hammerplating", 2),
      hammerdoubleingot("hammerdoubleingot", 3),
      hammertripleingot("hammertripleingot", 4),
      hammerquadrupleingot("hammerquadrupleingot", 5),
      hammerquintupleingot("hammerquintupleingot", 6),
      hammerdoubleplate("hammerdoubleplate", 7),
      hammertripleplate("hammertripleplate", 8),
      hammerquadrupleplate("hammerquadrupleplate", 9),
      hammerquintupleplate("hammerquintupleplate", 10),
      hammercrushing("hammercrushing", 11);
      // $FF: synthetic field
      private static final GT_ConfigCategories.Tools[] $VALUES = new GT_ConfigCategories.Tools[]{mortar, hammerrings, hammerplating, hammerdoubleingot, hammertripleingot, hammerquadrupleingot, hammerquintupleingot, hammerdoubleplate, hammertripleplate, hammerquadrupleplate, hammerquintupleplate, hammercrushing};


      private Tools(String var1, int var2) {}

   }
}
