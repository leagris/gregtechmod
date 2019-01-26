package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.storage.GT_MetaTileEntity_TradeOMat;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_TradeOMat_Settings extends GT_ContainerMetaTile_Machine {

   public int mPerformedTrades = 0;


   public GT_Container_TradeOMat_Settings(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 66, 152, 32));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 64, 8, 23, false, false, 0));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 65, 8, 41, false, false, 0));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex >= 1 && aSlotIndex <= 2) {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            ItemStack tStack = aPlayer.field_71071_by.func_70445_o();
            if(tStack != null) {
               tSlot.func_75215_d(GT_Utility.copy(new Object[]{tStack}));
            } else if(tSlot.func_75211_c() != null) {
               ItemStack var10000;
               if(aMouseclick == 0) {
                  var10000 = tSlot.func_75211_c();
                  var10000.field_77994_a -= aShifthold == 1?8:1;
                  if(tSlot.func_75211_c().field_77994_a <= 0) {
                     tSlot.func_75215_d((ItemStack)null);
                  }
               } else {
                  var10000 = tSlot.func_75211_c();
                  var10000.field_77994_a += aShifthold == 1?8:1;
                  if(tSlot.func_75211_c().field_77994_a > tSlot.func_75211_c().func_77976_d()) {
                     tSlot.func_75211_c().field_77994_a = tSlot.func_75211_c().func_77976_d();
                  }
               }
            }
         }

         return null;
      } else {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mPerformedTrades = ((GT_MetaTileEntity_TradeOMat)this.mTileEntity.getMetaTileEntity()).mPerformedTrades;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mPerformedTrades & '\uffff');
            var1.func_71112_a(this, 101, this.mPerformedTrades >>> 16);
         }

      }
   }

   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 100:
         this.mPerformedTrades = this.mPerformedTrades & -65536 | par2;
         break;
      case 101:
         this.mPerformedTrades = this.mPerformedTrades & '\uffff' | par2 << 16;
      }

   }

   public int getSlotCount() {
      return 1;
   }

   public int getShiftClickSlotCount() {
      return 1;
   }
}
