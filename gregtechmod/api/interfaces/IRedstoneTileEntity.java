package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.IRedstoneEmitter;
import gregtechmod.api.interfaces.IRedstoneReceiver;

public interface IRedstoneTileEntity extends IRedstoneEmitter, IRedstoneReceiver {

   void setGenericRedstoneOutput(boolean var1);

   void issueBlockUpdate();
}
