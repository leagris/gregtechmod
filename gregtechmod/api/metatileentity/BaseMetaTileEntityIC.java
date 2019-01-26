package gregtechmod.api.metatileentity;

import gregtechmod.api.interfaces.IIC2TileEntity;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import ic2.api.Direction;
import net.minecraft.tileentity.TileEntity;

public class BaseMetaTileEntityIC extends BaseMetaTileEntity implements IIC2TileEntity {

   public int injectEnergy(Direction aDirection, int aAmount) {
      return this.injectEnergyUnits((byte)aDirection.toSideValue(), aAmount, 1)?0:aAmount;
   }

   public boolean isTeleporterCompatible(Direction aSide) {
      return this.hasValidMetaTileEntity() && this.mMetaTileEntity.isTeleporterCompatible();
   }

   public boolean acceptsEnergyFrom(TileEntity aReceiver, Direction aDirection) {
      return this.inputEnergyFrom((byte)aDirection.toSideValue());
   }

   public boolean emitsEnergyTo(TileEntity aReceiver, Direction aDirection) {
      return this.outputsEnergyTo((byte)aDirection.toSideValue());
   }
}
