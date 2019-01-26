package gregtechmod.common.tileentities.pipes.fluids;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Fluid;

public class GT_MetaPipeEntity_Bronze_Large extends GT_MetaPipeEntity_Fluid {

   public GT_MetaPipeEntity_Bronze_Large(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaPipeEntity_Bronze_Large() {}

   public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaPipeEntity_Bronze_Large();
   }

   public int getTextureIndex(byte aSide, byte aConnections, boolean aConnected, boolean aRedstone) {
      return aConnected?372:364;
   }

   public float getThickNess() {
      return 0.75F;
   }

   public int getFluidCapacityPerTick() {
      return 240;
   }
}
