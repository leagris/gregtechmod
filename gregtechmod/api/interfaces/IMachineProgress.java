package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.IHasWorldObjectAndCoords;

public interface IMachineProgress extends IHasWorldObjectAndCoords {

   int getProgress();

   int getMaxProgress();

   boolean increaseProgress(int var1);

   boolean hasThingsToDo();

   boolean hasWorkJustBeenEnabled();

   void enableWorking();

   void disableWorking();

   boolean isAllowedToWork();

   void setWorkDataValue(byte var1);

   byte getWorkDataValue();

   boolean isActive();

   void setActive(boolean var1);
}
