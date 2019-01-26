package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Render;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_TradeOMat_Main extends GT_ContainerMetaTile_Machine {

   public GT_Container_TradeOMat_Main(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 54, 44, 14));
      this.func_75146_a(new Slot(this.mTileEntity, 55, 62, 14));
      this.func_75146_a(new Slot(this.mTileEntity, 56, 80, 14));
      this.func_75146_a(new Slot(this.mTileEntity, 57, 98, 14));
      this.func_75146_a(new Slot(this.mTileEntity, 58, 116, 14));
      this.func_75146_a(new Slot(this.mTileEntity, 59, 44, 50));
      this.func_75146_a(new Slot(this.mTileEntity, 60, 62, 50));
      this.func_75146_a(new Slot(this.mTileEntity, 61, 80, 50));
      this.func_75146_a(new Slot(this.mTileEntity, 62, 98, 50));
      this.func_75146_a(new Slot(this.mTileEntity, 63, 116, 50));
      this.func_75146_a(new GT_Slot_Render(this.mTileEntity, 64, 8, 14));
      this.func_75146_a(new GT_Slot_Render(this.mTileEntity, 65, 8, 50));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      return aSlotIndex >= 10 && aSlotIndex <= 11?null:super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
   }

   public int getSlotCount() {
      return 10;
   }

   public int getShiftClickSlotCount() {
      return 5;
   }
}
