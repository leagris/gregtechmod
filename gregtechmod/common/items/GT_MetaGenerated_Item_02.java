package gregtechmod.common.items;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import java.util.Arrays;
import java.util.List;

public class GT_MetaGenerated_Item_02 extends GT_MetaGenerated_Item {

   private static final List<Materials> sTempToolHeadMaterials = Arrays.asList(new Materials[]{Materials.Wood, Materials.Stone, Materials.Iron, Materials.Gold, Materials.Diamond, Materials.Bronze, Materials.Steel, Materials.TungstenSteel, Materials.Rubber, Materials.Plastic});


   public GT_MetaGenerated_Item_02(int aID) {
      super(aID, "GregTech_MetaGenerated_Item_02", new OrePrefixes[]{OrePrefixes.toolHeadSword, OrePrefixes.toolHeadPickaxe, OrePrefixes.toolHeadShovel, OrePrefixes.toolHeadAxe, OrePrefixes.toolHeadHoe, OrePrefixes.toolHeadHammer, OrePrefixes.toolHeadFile, OrePrefixes.toolHeadSaw, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, OrePrefixes.gearGt});
   }

   public String getDefaultLocalization(OrePrefixes aPrefix, Materials aMaterial, int aMetaData) {
      return super.getDefaultLocalization(aPrefix, aMaterial, aMetaData);
   }

   public IIconContainer getIconContainer(int aMetaData, Materials aMaterial) {
      return aMaterial.mIconSet[32 + aMetaData / 1000];
   }

   public boolean doesMaterialAllowGeneration(OrePrefixes aPrefix, Materials aMaterial) {
      if(!super.doesMaterialAllowGeneration(aPrefix, aMaterial)) {
         return false;
      } else {
         switch(GT_MetaGenerated_Item_02.NamelessClass767087894.$SwitchMap$gregtechmod$api$enums$OrePrefixes[aPrefix.ordinal()]) {
         case 1:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case 2:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case 3:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case 4:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case 5:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case 6:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case 7:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case 8:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case 9:
            return (aMaterial.mTypes & 128) != 0;
         default:
            return false;
         }
      }
   }


   // $FF: synthetic class
   static class NamelessClass767087894 {

      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$OrePrefixes = new int[OrePrefixes.values().length];


      static {
         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.toolHeadSword.ordinal()] = 1;
         } catch (NoSuchFieldError var9) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.toolHeadPickaxe.ordinal()] = 2;
         } catch (NoSuchFieldError var8) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.toolHeadShovel.ordinal()] = 3;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.toolHeadAxe.ordinal()] = 4;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.toolHeadHoe.ordinal()] = 5;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.toolHeadHammer.ordinal()] = 6;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.toolHeadFile.ordinal()] = 7;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.toolHeadSaw.ordinal()] = 8;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.gearGt.ordinal()] = 9;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
