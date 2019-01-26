package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.IHasWorldObjectAndCoords;

public interface IRedstoneReceiver extends IHasWorldObjectAndCoords {

   byte getInputRedstoneSignal(byte var1);

   byte getStrongestRedstone();

   boolean getRedstone();

   boolean getRedstone(byte var1);
}
