package gregtechmod.common.items;

import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.items.GT_MetaGenerated_Item;

public class GT_MetaGenerated_Item_01 extends GT_MetaGenerated_Item {

   public GT_MetaGenerated_Item_01(int aID) {
      super(aID, "GregTech_MetaGenerated_Item_01", new OrePrefixes[]{OrePrefixes.dustTiny, OrePrefixes.dustSmall, OrePrefixes.dust, OrePrefixes.dustImpure, OrePrefixes.dustPure, OrePrefixes.crushed, OrePrefixes.crushedPurified, OrePrefixes.crushedCentrifuged, OrePrefixes.gem, OrePrefixes.nugget, null, OrePrefixes.ingot, OrePrefixes.ingotHot, OrePrefixes.ingotDouble, OrePrefixes.ingotTriple, OrePrefixes.ingotQuadruple, OrePrefixes.ingotQuintuple, OrePrefixes.plate, OrePrefixes.plateDouble, OrePrefixes.plateTriple, OrePrefixes.plateQuadruple, OrePrefixes.plateQuintuple, OrePrefixes.plateDense, OrePrefixes.stick, OrePrefixes.lense, OrePrefixes.round, OrePrefixes.bolt, OrePrefixes.screw, OrePrefixes.ring, null, OrePrefixes.cell, OrePrefixes.cellPlasma});
   }

   public String getDefaultLocalization(OrePrefixes aPrefix, Materials aMaterial, int aMetaData) {
      switch(GT_MetaGenerated_Item_01.NamelessClass725017202.$SwitchMap$gregtechmod$api$enums$Materials[aMaterial.ordinal()]) {
      case 1:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + "Flour";
         }
         break;
      case 2:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + "crushed Ice";
         }
         break;
      case 3:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Pulp";
         }

         if(aPrefix.name().startsWith("nugget")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Chip";
         }
         break;
      case 4:
      case 5:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Pulp";
         }

         if(aPrefix.name().startsWith("plate")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Sheet";
         }

         if(aPrefix.name().startsWith("ingot")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Bar";
         }

         if(aPrefix.name().startsWith("nugget")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Chip";
         }
         break;
      case 6:
         if(aPrefix.name().startsWith("ingot")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
         }
      case 7:
      case 8:
      case 9:
      case 10:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Powder";
         }
         break;
      case 11:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + "Chad";
         }

         if(aPrefix == OrePrefixes.plate) {
            return "Sheet of Paper";
         }

         if(aPrefix == OrePrefixes.plateDouble) {
            return "Paperboard";
         }

         if(aPrefix == OrePrefixes.plateTriple) {
            return "Carton";
         }

         if(aPrefix == OrePrefixes.plateQuadruple) {
            return "Cardboard";
         }

         if(aPrefix == OrePrefixes.plateQuintuple) {
            return "Thick Cardboard";
         }

         if(aPrefix == OrePrefixes.plateDense) {
            return "Strong Cardboard";
         }
         break;
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
         }
         break;
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
         }

         if(aPrefix == OrePrefixes.crushedCentrifuged) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
         }

         if(aPrefix == OrePrefixes.crushedPurified) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
         }

         if(aPrefix == OrePrefixes.crushed) {
            return "Ground " + aMaterial.mDefaultLocalName;
         }
      }

      return super.getDefaultLocalization(aPrefix, aMaterial, aMetaData);
   }

   public String getOreDictString(OrePrefixes aPrefix, Materials aMaterial) {
      return aMaterial == Materials.Wood && aPrefix == OrePrefixes.plate?"plankWood":super.getOreDictString(aPrefix, aMaterial);
   }

   public IIconContainer getIconContainer(int aMetaData, Materials aMaterial) {
      return aMaterial.mIconSet[aMetaData / 1000];
   }

   public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
      return aDoShowAllItems || aPrefix != OrePrefixes.nugget && aPrefix != OrePrefixes.dustTiny && aPrefix != OrePrefixes.dustSmall && aPrefix != OrePrefixes.dustImpure && aPrefix != OrePrefixes.dustPure && aPrefix != OrePrefixes.crushed && aPrefix != OrePrefixes.crushedPurified && aPrefix != OrePrefixes.crushedCentrifuged && aPrefix != OrePrefixes.ingotHot && aPrefix != OrePrefixes.cellPlasma;
   }

   public boolean doesMaterialAllowGeneration(OrePrefixes aPrefix, Materials aMaterial) {
      if(!super.doesMaterialAllowGeneration(aPrefix, aMaterial)) {
         return false;
      } else {
         switch(GT_MetaGenerated_Item_01.NamelessClass725017202.$SwitchMap$gregtechmod$api$enums$OrePrefixes[aPrefix.ordinal()]) {
         case 1:
            return (aMaterial.mTypes & 15) != 0;
         case 2:
            return (aMaterial.mTypes & 15) != 0;
         case 3:
            return (aMaterial.mTypes & 15) != 0;
         case 4:
            return (aMaterial.mTypes & 8) != 0 || aMaterial == Materials.GraniteRed || aMaterial == Materials.GraniteBlack || aMaterial == Materials.Quartzite || aMaterial == Materials.Flint || aMaterial == Materials.Redrock || aMaterial == Materials.Basalt || aMaterial == Materials.Marble || aMaterial == Materials.Netherrack || aMaterial == Materials.Endstone;
         case 5:
            return (aMaterial.mTypes & 8) != 0;
         case 6:
            return (aMaterial.mTypes & 8) != 0;
         case 7:
            return (aMaterial.mTypes & 8) != 0;
         case 8:
            return (aMaterial.mTypes & 8) != 0;
         case 9:
            return (aMaterial.mTypes & 4) != 0;
         case 10:
            return (aMaterial.mTypes & 2) != 0;
         case 11:
            return (aMaterial.mTypes & 2) != 0;
         case 12:
            return (aMaterial.mTypes & 2) != 0 && aMaterial.mBlastFurnaceTemp > 1750;
         case 13:
            return (aMaterial.mTypes & 6) != 0 || aMaterial == Materials.Paper || aMaterial == Materials.Redstone || aMaterial == Materials.GraniteRed || aMaterial == Materials.GraniteBlack || aMaterial == Materials.Glowstone || aMaterial == Materials.Nikolite || aMaterial == Materials.Obsidian || aMaterial == Materials.Wood;
         case 14:
            return aMaterial == Materials.Paper;
         case 15:
            return aMaterial == Materials.Paper;
         case 16:
            return aMaterial == Materials.Paper;
         case 17:
            return aMaterial == Materials.Paper;
         case 18:
            return (aMaterial.mTypes & 2) != 0;
         case 19:
            return (aMaterial.mTypes & 4) != 0 && aMaterial.mTransparent && aMaterial.mColor != Dyes._NULL || aMaterial == Materials.EnderPearl || aMaterial == Materials.EnderEye;
         case 20:
            return (aMaterial.mTypes & 2) != 0;
         case 21:
            return (aMaterial.mTypes & 2) != 0;
         case 22:
            return (aMaterial.mTypes & 2) != 0;
         case 23:
            return (aMaterial.mTypes & 2) != 0;
         case 24:
            return (aMaterial.mTypes & 16) != 0;
         case 25:
            return (aMaterial.mTypes & 32) != 0;
         default:
            return false;
         }
      }
   }

   // $FF: synthetic class
   static class NamelessClass725017202 {

      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$Materials;
      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$OrePrefixes = new int[OrePrefixes.values().length];


      static {
         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.dustTiny.ordinal()] = 1;
         } catch (NoSuchFieldError var54) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.dustSmall.ordinal()] = 2;
         } catch (NoSuchFieldError var53) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.dust.ordinal()] = 3;
         } catch (NoSuchFieldError var52) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.dustImpure.ordinal()] = 4;
         } catch (NoSuchFieldError var51) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.dustPure.ordinal()] = 5;
         } catch (NoSuchFieldError var50) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.crushed.ordinal()] = 6;
         } catch (NoSuchFieldError var49) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.crushedPurified.ordinal()] = 7;
         } catch (NoSuchFieldError var48) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.crushedCentrifuged.ordinal()] = 8;
         } catch (NoSuchFieldError var47) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.gem.ordinal()] = 9;
         } catch (NoSuchFieldError var46) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.nugget.ordinal()] = 10;
         } catch (NoSuchFieldError var45) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.ingot.ordinal()] = 11;
         } catch (NoSuchFieldError var44) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.ingotHot.ordinal()] = 12;
         } catch (NoSuchFieldError var43) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.plate.ordinal()] = 13;
         } catch (NoSuchFieldError var42) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.plateDouble.ordinal()] = 14;
         } catch (NoSuchFieldError var41) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.plateTriple.ordinal()] = 15;
         } catch (NoSuchFieldError var40) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.plateQuadruple.ordinal()] = 16;
         } catch (NoSuchFieldError var39) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.plateQuintuple.ordinal()] = 17;
         } catch (NoSuchFieldError var38) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.stick.ordinal()] = 18;
         } catch (NoSuchFieldError var37) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.lense.ordinal()] = 19;
         } catch (NoSuchFieldError var36) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.round.ordinal()] = 20;
         } catch (NoSuchFieldError var35) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.bolt.ordinal()] = 21;
         } catch (NoSuchFieldError var34) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.screw.ordinal()] = 22;
         } catch (NoSuchFieldError var33) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.ring.ordinal()] = 23;
         } catch (NoSuchFieldError var32) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.cell.ordinal()] = 24;
         } catch (NoSuchFieldError var31) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.cellPlasma.ordinal()] = 25;
         } catch (NoSuchFieldError var30) {
            ;
         }

         $SwitchMap$gregtechmod$api$enums$Materials = new int[Materials.values().length];

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Wheat.ordinal()] = 1;
         } catch (NoSuchFieldError var29) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Ice.ordinal()] = 2;
         } catch (NoSuchFieldError var28) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Wood.ordinal()] = 3;
         } catch (NoSuchFieldError var27) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Plastic.ordinal()] = 4;
         } catch (NoSuchFieldError var26) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Rubber.ordinal()] = 5;
         } catch (NoSuchFieldError var25) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.SteelLeaf.ordinal()] = 6;
         } catch (NoSuchFieldError var24) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Blaze.ordinal()] = 7;
         } catch (NoSuchFieldError var23) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Milk.ordinal()] = 8;
         } catch (NoSuchFieldError var22) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Chocolate.ordinal()] = 9;
         } catch (NoSuchFieldError var21) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Coffee.ordinal()] = 10;
         } catch (NoSuchFieldError var20) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Paper.ordinal()] = 11;
         } catch (NoSuchFieldError var19) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Ash.ordinal()] = 12;
         } catch (NoSuchFieldError var18) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.DarkAsh.ordinal()] = 13;
         } catch (NoSuchFieldError var17) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Gunpowder.ordinal()] = 14;
         } catch (NoSuchFieldError var16) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Sugar.ordinal()] = 15;
         } catch (NoSuchFieldError var15) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Salt.ordinal()] = 16;
         } catch (NoSuchFieldError var14) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.RockSalt.ordinal()] = 17;
         } catch (NoSuchFieldError var13) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.VolcanicAsh.ordinal()] = 18;
         } catch (NoSuchFieldError var12) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Vermiculite.ordinal()] = 19;
         } catch (NoSuchFieldError var11) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Bentonite.ordinal()] = 20;
         } catch (NoSuchFieldError var10) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Kaolinite.ordinal()] = 21;
         } catch (NoSuchFieldError var9) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Talc.ordinal()] = 22;
         } catch (NoSuchFieldError var8) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.BasalticMineralSand.ordinal()] = 23;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.GraniticMineralSand.ordinal()] = 24;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.CassiteriteSand.ordinal()] = 25;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.GarnetSand.ordinal()] = 26;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.QuartzSand.ordinal()] = 27;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Pitchblende.ordinal()] = 28;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.FullersEarth.ordinal()] = 29;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
