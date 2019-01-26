package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_ElectricItemClearer extends GT_ContainerMetaTile_Machine {

   public GT_Container_ElectricItemClearer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 80, 23));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 1, 8, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 1, 26, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 1, 44, 63, false, true, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 1) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            if(this.mTileEntity.getMetaTileEntity() == null) {
               return null;
            }

            if(aSlotIndex == 1) {
               ((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bOutput;
               if(((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bOutput) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Energy");
               }

               return null;
            }

            if(aSlotIndex == 2) {
               ((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull;
               if(((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Redstone if slot contains something");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Redstone");
               }

               return null;
            }

            if(aSlotIndex == 3) {
               ((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bInvert = !((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bInvert;
               if(((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bInvert) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Invert Redstone");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t invert Redstone");
               }

               return null;
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public int getSlotCount() {
      return 1;
   }

   public int getShiftClickSlotCount() {
      return 1;
   }
}
