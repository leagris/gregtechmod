package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.IHasWorldObjectAndCoords;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.tile.IEnergyStorage;

public interface IIC2TileEntity extends IEnergyStorage, IEnergySink, IEnergySource, IHasWorldObjectAndCoords {
}
