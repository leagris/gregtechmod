package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.gui.GT_Slot_Render;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricAutoWorkbench;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_ElectricAutoWorkbench extends GT_ContainerMetaTile_Machine {

   public int mMode;
   public int mThroughPut;


   public GT_Container_ElectricAutoWorkbench(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 8, 5));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 26, 5));
      this.func_75146_a(new Slot(this.mTileEntity, 2, 44, 5));
      this.func_75146_a(new Slot(this.mTileEntity, 3, 8, 23));
      this.func_75146_a(new Slot(this.mTileEntity, 4, 26, 23));
      this.func_75146_a(new Slot(this.mTileEntity, 5, 44, 23));
      this.func_75146_a(new Slot(this.mTileEntity, 6, 8, 41));
      this.func_75146_a(new Slot(this.mTileEntity, 7, 26, 41));
      this.func_75146_a(new Slot(this.mTileEntity, 8, 44, 41));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 9, 8, 60));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 10, 26, 60));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 11, 44, 60));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 12, 62, 60));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 13, 80, 60));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 14, 98, 60));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 15, 116, 60));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 16, 134, 60));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 17, 152, 60));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 18, 152, 41));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 19, 64, 6, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 20, 81, 6, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 21, 98, 6, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 22, 64, 23, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 23, 81, 23, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 24, 98, 23, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 25, 64, 40, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 26, 81, 40, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 27, 98, 40, false, false, 1));
      this.func_75146_a(new GT_Slot_Render(this.mTileEntity, 28, 152, 5));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 29, 121, 41, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 29, 121, 5, false, false, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 18) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            if(this.mTileEntity.getMetaTileEntity() == null) {
               return null;
            }

            if(aSlotIndex > 18 && aSlotIndex < 28) {
               ItemStack tStack = aPlayer.field_71071_by.func_70445_o();
               if(tStack != null) {
                  tStack = GT_Utility.copyAmount(1L, new Object[]{tStack});
               }

               tSlot.func_75215_d(tStack);
               return null;
            }

            if(aSlotIndex == 28) {
               return null;
            }

            if(aSlotIndex == 29) {
               if(aMouseclick == 0) {
                  ((GT_MetaTileEntity_ElectricAutoWorkbench)this.mTileEntity.getMetaTileEntity()).switchModeForward();
               } else {
                  ((GT_MetaTileEntity_ElectricAutoWorkbench)this.mTileEntity.getMetaTileEntity()).switchModeBackward();
               }

               return null;
            }

            if(aSlotIndex == 30) {
               ((GT_MetaTileEntity_ElectricAutoWorkbench)this.mTileEntity.getMetaTileEntity()).switchThrough();
               return null;
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mMode = ((GT_MetaTileEntity_ElectricAutoWorkbench)this.mTileEntity.getMetaTileEntity()).mMode;
         this.mThroughPut = ((GT_MetaTileEntity_ElectricAutoWorkbench)this.mTileEntity.getMetaTileEntity()).mThroughPut;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mMode);
            var1.func_71112_a(this, 101, this.mThroughPut);
         }

      }
   }

   public void func_75132_a(ICrafting par1ICrafting) {
      super.func_75132_a(par1ICrafting);
   }

   @SideOnly(Side.CLIENT)
   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 100:
         this.mMode = par2;
         break;
      case 101:
         this.mThroughPut = par2;
      }

   }

   public int getSlotCount() {
      return 19;
   }

   public int getShiftClickSlotCount() {
      return 9;
   }
}
