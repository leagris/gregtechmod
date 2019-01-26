package gregtechmod.api.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_BasicMachine extends GT_ContainerMetaTile_Machine {

   public boolean mOutputting = false;
   public boolean mItemTransfer = false;
   public boolean mSeperatedInputs = false;


   public GT_Container_BasicMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 1, 35, 25));
      this.func_75146_a(new Slot(this.mTileEntity, 2, 53, 25));
      this.func_75146_a(new Slot(this.mTileEntity, 5, 80, 63));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 3, 107, 25));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 4, 125, 25));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 8, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 26, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 44, 63, false, true, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 5) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            if(this.mTileEntity.getMetaTileEntity() == null) {
               return null;
            }

            if(aSlotIndex == 5) {
               ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_BasicMachine)this.mTileEntity.getMetaTileEntity()).bOutput;
               return null;
            }

            if(aSlotIndex == 6) {
               ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.getMetaTileEntity()).bItemTransfer = !((GT_MetaTileEntity_BasicMachine)this.mTileEntity.getMetaTileEntity()).bItemTransfer;
               return null;
            }

            if(aSlotIndex == 7) {
               ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.getMetaTileEntity()).bSeperatedInputs = !((GT_MetaTileEntity_BasicMachine)this.mTileEntity.getMetaTileEntity()).bSeperatedInputs;
               return null;
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mOutputting = ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.getMetaTileEntity()).bOutput;
         this.mItemTransfer = ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.getMetaTileEntity()).bItemTransfer;
         this.mSeperatedInputs = ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.getMetaTileEntity()).bSeperatedInputs;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 101, this.mOutputting?1:0);
            var1.func_71112_a(this, 102, this.mItemTransfer?1:0);
            var1.func_71112_a(this, 103, this.mSeperatedInputs?1:0);
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
      case 101:
         this.mOutputting = par2 != 0;
         break;
      case 102:
         this.mItemTransfer = par2 != 0;
         break;
      case 103:
         this.mSeperatedInputs = par2 != 0;
      }

   }

   public int getSlotCount() {
      return 5;
   }

   public int getShiftClickSlotCount() {
      return 2;
   }
}
