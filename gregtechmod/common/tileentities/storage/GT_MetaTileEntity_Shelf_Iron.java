package gregtechmod.common.tileentities.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.common.tileentities.storage.GT_MetaTileEntity_Shelf;

public class GT_MetaTileEntity_Shelf_Iron extends GT_MetaTileEntity_Shelf {

   public GT_MetaTileEntity_Shelf_Iron(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Shelf_Iron() {}

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Shelf_Iron();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?216 + this.mType:(aSide == 0?32:(aSide == 1?29:40));
   }
}
