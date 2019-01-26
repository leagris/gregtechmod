package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_TradeOMat_Inventory_Objects extends GT_ContainerMetaTile_Machine {

   public GT_Container_TradeOMat_Inventory_Objects(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      for(int y = 0; y < 3; ++y) {
         for(int x = 0; x < 9; ++x) {
            this.func_75146_a(new Slot(this.mTileEntity, x + y * 9, 8 + x * 18, 23 + y * 18));
         }
      }

   }

   public int getSlotCount() {
      return 27;
   }

   public int getShiftClickSlotCount() {
      return 27;
   }
}
