package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Armor;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.energy.storage.GT_MetaTileEntity_AESU;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_AESU_Meta extends GT_ContainerMetaTile_Machine {

   public int mBasicOutput = 0;


   public GT_Container_AESU_Meta(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 128, 14));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 128, 50));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 107, 5, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 107, 23, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 107, 41, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 107, 59, false, false, 1));
      this.func_75146_a(new GT_Slot_Armor(aInventoryPlayer, 36, 152, 59, 3, aInventoryPlayer.field_70458_d));
      this.func_75146_a(new GT_Slot_Armor(aInventoryPlayer, 37, 152, 41, 2, aInventoryPlayer.field_70458_d));
      this.func_75146_a(new GT_Slot_Armor(aInventoryPlayer, 38, 152, 23, 1, aInventoryPlayer.field_70458_d));
      this.func_75146_a(new GT_Slot_Armor(aInventoryPlayer, 39, 152, 5, 0, aInventoryPlayer.field_70458_d));
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mBasicOutput = ((GT_MetaTileEntity_AESU)this.mTileEntity.getMetaTileEntity()).mOutput;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mBasicOutput & '\uffff');
            var1.func_71112_a(this, 101, this.mBasicOutput >>> 16);
         }

      }
   }

   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 100:
         this.mBasicOutput = this.mBasicOutput & -65536 | par2;
         break;
      case 101:
         this.mBasicOutput = this.mBasicOutput & '\uffff' | par2 << 16;
      }

   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 0) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            int tOutput = ((GT_MetaTileEntity_AESU)this.mTileEntity.getMetaTileEntity()).mOutput;
            switch(aSlotIndex) {
            case 2:
               ((GT_MetaTileEntity_AESU)this.mTileEntity.getMetaTileEntity()).mOutput = Math.min(8192, tOutput + (aShifthold == 1?256:64));
               return null;
            case 3:
               ((GT_MetaTileEntity_AESU)this.mTileEntity.getMetaTileEntity()).mOutput = Math.min(8192, tOutput + (aShifthold == 1?16:1));
               return null;
            case 4:
               ((GT_MetaTileEntity_AESU)this.mTileEntity.getMetaTileEntity()).mOutput = Math.max(0, tOutput - (aShifthold == 1?16:1));
               return null;
            case 5:
               ((GT_MetaTileEntity_AESU)this.mTileEntity.getMetaTileEntity()).mOutput = Math.max(0, tOutput - (aShifthold == 1?256:64));
               return null;
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public int getSlotCount() {
      return 2;
   }

   public int getShiftClickSlotCount() {
      return 2;
   }
}
