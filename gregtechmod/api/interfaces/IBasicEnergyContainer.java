package gregtechmod.api.interfaces;


public interface IBasicEnergyContainer {

   boolean isUniversalEnergyStored(int var1);

   int getUniversalEnergyStored();

   int getUniversalEnergyCapacity();

   int getOutputAmperage();

   int getOutputVoltage();

   int getInputVoltage();

   boolean decreaseStoredEnergyUnits(int var1, boolean var2);

   boolean increaseStoredEnergyUnits(int var1, boolean var2);

   boolean injectEnergyUnits(byte var1, int var2, int var3);

   boolean drainEnergyUnits(byte var1, int var2, int var3);

   boolean inputEnergyFrom(byte var1);

   boolean outputsEnergyTo(byte var1);

   int getAverageElectricInput();

   int getAverageElectricOutput();

   int getStoredEU();

   int getEUCapacity();

   int getStoredMJ();

   int getMJCapacity();

   boolean increaseStoredMJ(int var1, boolean var2);

   int getStoredSteam();

   int getSteamCapacity();

   boolean increaseStoredSteam(int var1, boolean var2);
}
