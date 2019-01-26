package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Render;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricTypeFilter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_ElectricTypeFilter extends GT_ContainerMetaTile_Machine {

   public GT_Container_ElectricTypeFilter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 98, 5));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 116, 5));
      this.func_75146_a(new Slot(this.mTileEntity, 2, 134, 5));
      this.func_75146_a(new Slot(this.mTileEntity, 3, 98, 23));
      this.func_75146_a(new Slot(this.mTileEntity, 4, 116, 23));
      this.func_75146_a(new Slot(this.mTileEntity, 5, 134, 23));
      this.func_75146_a(new Slot(this.mTileEntity, 6, 98, 41));
      this.func_75146_a(new Slot(this.mTileEntity, 7, 116, 41));
      this.func_75146_a(new Slot(this.mTileEntity, 8, 134, 41));
      this.func_75146_a(new GT_Slot_Render(this.mTileEntity, 9, 35, 23));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 8, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 26, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 44, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 62, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 80, 63, false, true, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 9) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            if(this.mTileEntity.getMetaTileEntity() == null) {
               return null;
            }

            if(aSlotIndex == 9) {
               ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).clickTypeIcon(aMouseclick != 0);
               return null;
            }

            if(aSlotIndex == 10) {
               ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bOutput;
               if(((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bOutput) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Energy");
               }

               return null;
            }

            if(aSlotIndex == 11) {
               ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull;
               if(((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Redstone if slots contain something");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Redstone");
               }

               return null;
            }

            if(aSlotIndex == 12) {
               ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvert = !((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvert;
               if(((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvert) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Invert Redstone");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t invert Redstone");
               }

               return null;
            }

            if(aSlotIndex == 13) {
               ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter = !((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter;
               if(((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Invert Filter");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t invert Filter");
               }

               return null;
            }

            if(aSlotIndex == 14) {
               ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bNBTAllowed = !((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bNBTAllowed;
               if(((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bNBTAllowed) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Allow Items with NBT");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t allow Items with NBT");
               }

               return null;
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public int getSlotCount() {
      return 9;
   }

   public int getShiftClickSlotCount() {
      return 9;
   }
}
