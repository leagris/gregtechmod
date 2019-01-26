package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_Translocator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Translocator extends GT_ContainerMetaTile_Machine {

   public GT_Container_Translocator(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 63, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 1, 80, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 97, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 3, 63, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 4, 80, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 5, 97, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 6, 63, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 7, 80, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 8, 97, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 8, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 26, 63, false, true, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 0) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            if(this.mTileEntity.getMetaTileEntity() == null) {
               return null;
            }

            if(aSlotIndex < 9) {
               tSlot.func_75215_d(GT_Utility.copyAmount(1L, new Object[]{aPlayer.field_71071_by.func_70445_o()}));
               return null;
            }

            if(aSlotIndex == 9) {
               ((GT_MetaTileEntity_Translocator)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_Translocator)this.mTileEntity.getMetaTileEntity()).bOutput;
               if(((GT_MetaTileEntity_Translocator)this.mTileEntity.getMetaTileEntity()).bOutput) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Energy");
               }

               return null;
            }

            if(aSlotIndex == 10) {
               ((GT_MetaTileEntity_Translocator)this.mTileEntity.getMetaTileEntity()).bInvertFilter = !((GT_MetaTileEntity_Translocator)this.mTileEntity.getMetaTileEntity()).bInvertFilter;
               if(((GT_MetaTileEntity_Translocator)this.mTileEntity.getMetaTileEntity()).bInvertFilter) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Inverted the Filter");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Filter works normal");
               }

               return null;
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public int getSlotCount() {
      return 0;
   }

   public int getShiftClickSlotCount() {
      return 0;
   }
}
