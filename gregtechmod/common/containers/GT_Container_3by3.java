package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_3by3 extends GT_ContainerMetaTile_Machine {

   public GT_Container_3by3(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 62, 17));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 80, 17));
      this.func_75146_a(new Slot(this.mTileEntity, 2, 98, 17));
      this.func_75146_a(new Slot(this.mTileEntity, 3, 62, 35));
      this.func_75146_a(new Slot(this.mTileEntity, 4, 80, 35));
      this.func_75146_a(new Slot(this.mTileEntity, 5, 98, 35));
      this.func_75146_a(new Slot(this.mTileEntity, 6, 62, 53));
      this.func_75146_a(new Slot(this.mTileEntity, 7, 80, 53));
      this.func_75146_a(new Slot(this.mTileEntity, 8, 98, 53));
   }

   public int getSlotCount() {
      return 9;
   }

   public int getShiftClickSlotCount() {
      return 9;
   }
}
