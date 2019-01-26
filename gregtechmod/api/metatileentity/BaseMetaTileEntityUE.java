package gregtechmod.api.metatileentity;

import gregtechmod.api.interfaces.IUETileEntity;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import java.util.EnumSet;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.compatibility.Compatibility;
import universalelectricity.core.block.IConductor;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.item.ElectricItemHelper;

public class BaseMetaTileEntityUE extends BaseMetaTileEntity implements IUETileEntity {

   public void updateStatus() {
      super.updateStatus();
   }

   public void chargeItem(ItemStack aStack) {
      super.chargeItem(aStack);
      float tEnergy = ElectricItemHelper.chargeItem(aStack, (float)(this.getOfferedEnergy() * (double)(Compatibility.BC3_RATIO * 0.4F)));
      if(tEnergy > 0.0F) {
         this.decreaseStoredEU((int)(tEnergy / (Compatibility.BC3_RATIO * 0.4F)) + 1, true);
      }

   }

   public void dischargeItem(ItemStack aStack) {
      super.dischargeItem(aStack);
      float tEnergy = ElectricItemHelper.dischargeItem(aStack, (float)(this.demandedEnergyUnits() * (double)(Compatibility.BC3_RATIO * 0.4F)));
      if(tEnergy > 0.0F) {
         this.increaseStoredEnergyUnits((int)(tEnergy / (Compatibility.BC3_RATIO * 0.4F)), true);
      }

   }

   public float receiveElectricity(ForgeDirection aSide, ElectricityPack aPacket, boolean doReceive) {
      if(!this.getUEConsumingSides().contains(aSide)) {
         return 0.0F;
      } else {
         int aInserted = (int)(aPacket.getWatts() / (Compatibility.BC3_RATIO * 0.4F));
         return doReceive?(this.injectEnergyUnits((byte)aSide.ordinal(), aInserted, 1)?(float)aInserted:0.0F):(this.getEUCapacity() - this.getStoredEU() >= aInserted?(float)aInserted:0.0F);
      }
   }

   public ElectricityPack provideElectricity(ForgeDirection aSide, ElectricityPack aRequested, boolean doProvide) {
      if(!this.getUEProducingSides().contains(aSide)) {
         return null;
      } else {
         int aExtracted = (int)this.getOfferedEnergy();
         return doProvide?((double)(aRequested.getWatts() / (Compatibility.BC3_RATIO * 0.4F)) >= this.getOfferedEnergy()?(this.decreaseStoredEU(aExtracted, false)?new ElectricityPack((float)aExtracted * Compatibility.BC3_RATIO * 0.4F, 1.0F):null):(this.decreaseStoredEU((int)(aRequested.getWatts() / (Compatibility.BC3_RATIO * 0.4F)), false)?new ElectricityPack((float)((int)(aRequested.getWatts() / (Compatibility.BC3_RATIO * 0.4F))) * Compatibility.BC3_RATIO * 0.4F, 1.0F):null)):((double)(aRequested.getWatts() / (Compatibility.BC3_RATIO * 0.4F)) >= this.getOfferedEnergy()?new ElectricityPack((float)aExtracted * Compatibility.BC3_RATIO * 0.4F, 1.0F):new ElectricityPack((float)((int)(aRequested.getWatts() / (Compatibility.BC3_RATIO * 0.4F))) * Compatibility.BC3_RATIO * 0.4F, 1.0F));
      }
   }

   public float getRequest(ForgeDirection aSide) {
      return !this.getUEConsumingSides().contains(aSide)?0.0F:(float)(this.demandedEnergyUnits() * (double)(Compatibility.BC3_RATIO * 0.4F));
   }

   public float getProvide(ForgeDirection aSide) {
      return !this.getUEProducingSides().contains(aSide)?0.0F:(float)(this.getOfferedEnergy() * (double)(Compatibility.BC3_RATIO * 0.4F));
   }

   public float getVoltage() {
      return (float)(this.getOfferedEnergy() * (double)(Compatibility.BC3_RATIO * 0.4F));
   }

   public boolean canConnect(ForgeDirection aSide) {
      return this.getUEProducingSides().contains(aSide) || this.getUEConsumingSides().contains(aSide);
   }

   private EnumSet<ForgeDirection> getUEConsumingSides() {
      EnumSet rSides = EnumSet.noneOf(ForgeDirection.class);

      for(byte i = 0; i < 6; ++i) {
         if(this.inputEnergyFrom(i)) {
            TileEntity tTileEntity = this.getTileEntityAtSide(i);
            if(tTileEntity != null && tTileEntity instanceof IConductor) {
               rSides.add(ForgeDirection.getOrientation(i));
            }
         }
      }

      return rSides;
   }

   private EnumSet<ForgeDirection> getUEProducingSides() {
      EnumSet rSides = EnumSet.noneOf(ForgeDirection.class);

      for(byte i = 0; i < 6; ++i) {
         if(this.outputsEnergyTo(i)) {
            TileEntity tTileEntity = this.getTileEntityAtSide(i);
            if(tTileEntity != null && tTileEntity instanceof IConductor) {
               rSides.add(ForgeDirection.getOrientation(i));
            }
         }
      }

      return rSides;
   }
}
