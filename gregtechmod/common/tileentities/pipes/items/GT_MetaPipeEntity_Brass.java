package gregtechmod.common.tileentities.pipes.items;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Item;

public class GT_MetaPipeEntity_Brass extends GT_MetaPipeEntity_Item {

   public GT_MetaPipeEntity_Brass(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaPipeEntity_Brass() {}

   public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaPipeEntity_Brass();
   }

   public int getTextureIndex(byte aSide, byte aConnections, boolean aConnected, boolean aRedstone) {
      return aConnected?371:370;
   }

   public float getThickNess() {
      return 0.5F;
   }

   public int getPipeCapacity() {
      return 1;
   }

   public int getStepSize() {
      return '\u8000';
   }
}
