package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Item_Destructopack extends Container {

   ItemStack mItem;


   public GT_Container_Item_Destructopack(InventoryPlayer aInventoryPlayer, ItemStack aItem) {
      this.mItem = aItem;
      IInventory tInventory = new IInventory() {
         public void func_70299_a(int var1, ItemStack var2) {}
         public void func_70295_k_() {}
         public void func_70296_d() {}
         public boolean func_70300_a(EntityPlayer var1) {
            return false;
         }
         public ItemStack func_70304_b(int var1) {
            return null;
         }
         public ItemStack func_70301_a(int var1) {
            return null;
         }
         public int func_70302_i_() {
            return 0;
         }
         public int func_70297_j_() {
            return 0;
         }
         public String func_70303_b() {
            return null;
         }
         public ItemStack func_70298_a(int var1, int var2) {
            return null;
         }
         public void func_70305_f() {}
         public boolean func_94042_c() {
            return false;
         }
         public boolean func_94041_b(int i, ItemStack itemstack) {
            return false;
         }
      };
      this.func_75146_a(new GT_Slot_Holo(tInventory, 0, 80, 17, false, false, 1));

      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.func_75146_a(new Slot(aInventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.func_75146_a(new Slot(aInventoryPlayer, i, 8 + i * 18, 142));
      }

   }

   public boolean func_75145_c(EntityPlayer player) {
      return true;
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 0) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else if(aSlotIndex >= 1) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         if(aPlayer.field_71071_by.func_70445_o() != null) {
            if(aMouseclick == 0) {
               if(aShifthold == 1) {
                  for(int i = 0; i < aPlayer.field_71071_by.func_70302_i_(); ++i) {
                     ItemStack tStack = aPlayer.field_71071_by.func_70301_a(i);
                     if(tStack != null && GT_Utility.areStacksEqual(tStack, aPlayer.field_71071_by.func_70445_o())) {
                        aPlayer.field_71071_by.func_70299_a(i, (ItemStack)null);
                     }
                  }
               }

               aPlayer.field_71071_by.func_70437_b((ItemStack)null);
            } else {
               if(aPlayer.field_71071_by.func_70445_o().field_77994_a >= 2) {
                  --aPlayer.field_71071_by.func_70445_o().field_77994_a;
                  return aPlayer.field_71071_by.func_70445_o();
               }

               aPlayer.field_71071_by.func_70437_b((ItemStack)null);
            }
         }

         return null;
      }
   }

   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int aSlotIndex) {
      Slot slotObject = (Slot)this.field_75151_b.get(aSlotIndex);
      slotObject.func_75215_d((ItemStack)null);
      return null;
   }
}
