package gregtechmod.common.tileentities.frames;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Frame;

public class GT_MetaPipeEntity_Frame_Aluminium extends GT_MetaPipeEntity_Frame {

   public GT_MetaPipeEntity_Frame_Aluminium(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaPipeEntity_Frame_Aluminium() {}

   public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaPipeEntity_Frame_Aluminium();
   }

   public int getTextureIndex(byte aSide, byte aConnections, boolean aConnected, boolean aRedstone) {
      return 387;
   }
}
