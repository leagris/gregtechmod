package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.IMachineProgress;

public interface IUpgradableMachine extends IMachineProgress {

   boolean isUpgradable();

   boolean isMufflerUpgradable();

   boolean isMJConverterUpgradable();

   boolean isSteamEngineUpgradable();

   boolean isOverclockerUpgradable();

   boolean isTransformerUpgradable();

   boolean isBatteryUpgradable(int var1, byte var2);

   boolean addMufflerUpgrade();

   boolean addMJConverterUpgrade();

   boolean addSteamEngineUpgrade();

   boolean addOverclockerUpgrade();

   boolean addTransformerUpgrade();

   boolean addBatteryUpgrade(int var1, byte var2);

   boolean hasMufflerUpgrade();

   boolean hasMJConverterUpgrade();

   boolean hasSteamEngineUpgrade();

   byte getOverclockerUpgradeCount();

   byte getTransformerUpgradeCount();

   int getUpgradeStorageVolume();
}
