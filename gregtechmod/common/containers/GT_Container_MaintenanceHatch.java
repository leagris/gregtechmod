package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class GT_Container_MaintenanceHatch extends GT_ContainerMetaTile_Machine {

   public GT_Container_MaintenanceHatch(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 80, 35, false, false, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex != 0) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         if(aPlayer.field_71071_by.func_70445_o() != null) {
            ((GT_MetaTileEntity_Hatch_Maintenance)this.mTileEntity.getMetaTileEntity()).onToolClick(aPlayer.field_71071_by.func_70445_o(), aPlayer);
            if(aPlayer.field_71071_by.func_70445_o().field_77994_a <= 0) {
               aPlayer.field_71071_by.func_70437_b((ItemStack)null);
            }
         }

         return null;
      }
   }
}
