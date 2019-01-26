package gregtechmod.common.tileentities.pipes.items;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Item;

public class GT_MetaPipeEntity_Electrum_Large extends GT_MetaPipeEntity_Item {

   public GT_MetaPipeEntity_Electrum_Large(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaPipeEntity_Electrum_Large() {}

   public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaPipeEntity_Electrum_Large();
   }

   public int getTextureIndex(byte aSide, byte aConnections, boolean aConnected, boolean aRedstone) {
      return aConnected?377:375;
   }

   public float getThickNess() {
      return 0.75F;
   }

   public int getPipeCapacity() {
      return 4;
   }

   public int getStepSize() {
      return 8192;
   }
}
