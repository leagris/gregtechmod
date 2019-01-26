package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_4by4 extends GT_ContainerMetaTile_Machine {

   public GT_Container_4by4(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 53, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 71, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 2, 89, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 3, 107, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 4, 53, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 5, 71, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 6, 89, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 7, 107, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 8, 53, 44));
      this.func_75146_a(new Slot(this.mTileEntity, 9, 71, 44));
      this.func_75146_a(new Slot(this.mTileEntity, 10, 89, 44));
      this.func_75146_a(new Slot(this.mTileEntity, 11, 107, 44));
      this.func_75146_a(new Slot(this.mTileEntity, 12, 53, 62));
      this.func_75146_a(new Slot(this.mTileEntity, 13, 71, 62));
      this.func_75146_a(new Slot(this.mTileEntity, 14, 89, 62));
      this.func_75146_a(new Slot(this.mTileEntity, 15, 107, 62));
   }

   public int getSlotCount() {
      return 16;
   }

   public int getShiftClickSlotCount() {
      return 16;
   }
}
