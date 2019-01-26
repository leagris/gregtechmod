package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_2by2 extends GT_ContainerMetaTile_Machine {

   public GT_Container_2by2(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 71, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 89, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 2, 71, 44));
      this.func_75146_a(new Slot(this.mTileEntity, 3, 89, 44));
   }

   public int getSlotCount() {
      return 4;
   }

   public int getShiftClickSlotCount() {
      return 4;
   }
}
