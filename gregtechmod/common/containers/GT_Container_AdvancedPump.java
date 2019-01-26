package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_AdvancedPump extends GT_ContainerMetaTile_Machine {

   public int mContent = 0;


   public GT_Container_AdvancedPump(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 116, 17));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 80, 17));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 2, 80, 53));
   }

   public int getSlotCount() {
      return 3;
   }

   public int getShiftClickSlotCount() {
      return 2;
   }
}
