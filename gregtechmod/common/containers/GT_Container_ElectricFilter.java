package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricFilter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_ElectricFilter extends GT_ContainerMetaTile_Machine {

   public GT_Container_ElectricFilter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
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
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 18, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 35, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 11, 52, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 12, 18, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 13, 35, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 14, 52, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 18, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 16, 35, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 17, 52, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 8, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 26, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 44, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 62, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 80, 63, false, true, 1));
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

            if(aSlotIndex < 18) {
               ItemStack tStack = aPlayer.field_71071_by.func_70445_o();
               if(tStack == null) {
                  tStack = tSlot.func_75211_c();
                  if(aMouseclick == 0) {
                     tSlot.func_75215_d((ItemStack)null);
                  } else if(tStack != null) {
                     tStack.func_77964_b(32767);
                  }
               } else {
                  tSlot.func_75215_d(GT_Utility.copyAmount(1L, new Object[]{tStack}));
               }

               return null;
            }

            if(aSlotIndex == 18) {
               ((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bOutput;
               if(((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bOutput) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Energy");
               }

               return null;
            }

            if(aSlotIndex == 19) {
               ((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull;
               if(((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Redstone if slots contain something");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Redstone");
               }

               return null;
            }

            if(aSlotIndex == 20) {
               ((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvert = !((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvert;
               if(((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvert) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Invert Redstone");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t invert Redstone");
               }

               return null;
            }

            if(aSlotIndex == 21) {
               ((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter = !((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter;
               if(((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Invert Filter");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t invert Filter");
               }

               return null;
            }

            if(aSlotIndex == 22) {
               ((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bIgnoreNBT = !((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bIgnoreNBT;
               if(((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bIgnoreNBT) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Ignore NBT");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "NBT has to match");
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
