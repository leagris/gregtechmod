package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.IHasWorldObjectAndCoords;

public interface IRedstoneEmitter extends IHasWorldObjectAndCoords {

   byte getOutputRedstoneSignal(byte var1);

   void setOutputRedstoneSignal(byte var1, byte var2);

   byte getStrongOutputRedstoneSignal(byte var1);

   void setStrongOutputRedstoneSignal(byte var1, byte var2);

   byte getComparatorValue(byte var1);
}
