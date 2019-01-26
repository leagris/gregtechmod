package gregtechmod.common.containers;

import gregtechmod.GT_Mod;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Sonictron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Sonictron extends GT_ContainerMetaID_Machine {

   public GT_Container_Sonictron(InventoryPlayer aInventoryPlayer, GT_TileEntity_Sonictron aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      for(int j = 0; j < 8; ++j) {
         for(int i = 0; i < 8; ++i) {
            this.func_75146_a(new GT_Slot_Holo(this.mOldTileEntity, i + j * 8, 24 + 16 * i, 19 + 16 * j, false, true, 24));
         }
      }

   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aEntityPlayer) {
      if(aSlotIndex < 0) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aEntityPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            ItemStack tStack = tSlot.func_75211_c();
            if(aShifthold == 1) {
               tSlot.func_75215_d((ItemStack)null);
               return null;
            }

            int i;
            if(aMouseclick == 0) {
               if(tStack == null) {
                  tSlot.func_75215_d(GT_Utility.copy(new Object[]{GT_Mod.mSoundItems.get(0)}));
                  return null;
               }

               for(i = 1; i < GT_Mod.mSoundItems.size(); ++i) {
                  if(GT_Utility.areStacksEqual((ItemStack)GT_Mod.mSoundItems.get(i - 1), tStack)) {
                     tSlot.func_75215_d(GT_Utility.copy(new Object[]{GT_Mod.mSoundItems.get(i)}));
                     return null;
                  }
               }

               tSlot.func_75215_d((ItemStack)null);
               return null;
            }

            if(tStack == null) {
               return null;
            }

            for(i = 0; i < GT_Mod.mSoundItems.size(); ++i) {
               if(GT_Utility.areStacksEqual((ItemStack)GT_Mod.mSoundItems.get(i), tStack)) {
                  ++tStack.field_77994_a;
                  tStack.field_77994_a %= ((Integer)GT_Mod.mSoundCounts.get(i)).intValue() + 1;
                  if(tStack.field_77994_a == 0) {
                     ++tStack.field_77994_a;
                  }

                  return null;
               }
            }
         }

         return null;
      }
   }

   public boolean doesBindPlayerInventory() {
      return false;
   }
}
