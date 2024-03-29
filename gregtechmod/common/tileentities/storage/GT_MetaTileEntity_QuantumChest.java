package gregtechmod.common.tileentities.storage;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.common.tileentities.storage.GT_MetaTileEntity_DigitalChest;

public class GT_MetaTileEntity_QuantumChest extends GT_MetaTileEntity_DigitalChest {

   public static int sQuantumItemCount = 2000000000;


   public GT_MetaTileEntity_QuantumChest(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
      this.isDigitalChest = false;
   }

   public GT_MetaTileEntity_QuantumChest() {
      this.isDigitalChest = false;
   }

   public void onConfigLoad(GT_Config aConfig) {
      sQuantumItemCount = Math.max(sDigitalItemCount, aConfig.get(GT_ConfigCategories.machineconfig, "QuantumChest.MaxItems", sQuantumItemCount));
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_QuantumChest();
   }

   public int getMaxItemCount() {
      return sQuantumItemCount - 192;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?55:(aSide == 0?3:0);
   }

   public String getDescription() {
      return "2 Milliards, uhhm I mean Billions, Items to store here!";
   }

}
