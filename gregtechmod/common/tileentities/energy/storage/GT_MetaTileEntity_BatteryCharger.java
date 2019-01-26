package gregtechmod.common.tileentities.energy.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.common.tileentities.energy.storage.GT_MetaTileEntity_ChargerBox;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BatteryCharger extends GT_MetaTileEntity_ChargerBox {

   public GT_MetaTileEntity_BatteryCharger(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BatteryCharger() {}

   public int getInvSize() {
      return 9;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 98);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_BatteryCharger();
   }
}
