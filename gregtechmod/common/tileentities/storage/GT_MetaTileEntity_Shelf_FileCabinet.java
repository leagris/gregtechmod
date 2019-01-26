package gregtechmod.common.tileentities.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.common.tileentities.storage.GT_MetaTileEntity_Shelf;

public class GT_MetaTileEntity_Shelf_FileCabinet extends GT_MetaTileEntity_Shelf {

   public GT_MetaTileEntity_Shelf_FileCabinet(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Shelf_FileCabinet() {}

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Shelf_FileCabinet();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?223:(aSide == 0?32:(aSide == 1?29:40));
   }
}
