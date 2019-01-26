package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.redstone.GT_MetaTileEntity_RedstoneCircuitBlock;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_RedstoneCircuitBlock extends GT_ContainerMetaTile_Machine {

   public int[] mData = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
   public int mGate = 0;


   public GT_Container_RedstoneCircuitBlock(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 8, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 8, 24, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 8, 42, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 8, 60, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 152, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 152, 24, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 152, 42, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 1, 26, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 26, 24, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 3, 26, 42, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 4, 26, 60, false, true, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex >= 0 && aSlotIndex <= 6) {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            if(this.mTileEntity.getMetaTileEntity() == null) {
               return null;
            }

            if(aSlotIndex < 4) {
               ItemStack tStack = aPlayer.field_71071_by.func_70445_o();
               if(tStack == null) {
                  ((GT_MetaTileEntity_RedstoneCircuitBlock)this.mTileEntity.getMetaTileEntity()).changeGateData(aSlotIndex, aMouseclick == 0?(aShifthold == 0?1:(aShifthold == 1?128:16)):(aShifthold == 0?-1:(aShifthold == 1?-128:-16)));
               } else {
                  tStack = GT_Utility.copy(new Object[]{tStack});
                  if(aMouseclick != 0) {
                     tStack.func_77964_b(32767);
                  }

                  ((GT_MetaTileEntity_RedstoneCircuitBlock)this.mTileEntity.getMetaTileEntity()).stackGateData(aSlotIndex, tStack);
               }

               return null;
            }

            if(aSlotIndex == 4) {
               ((GT_MetaTileEntity_RedstoneCircuitBlock)this.mTileEntity.getMetaTileEntity()).switchOutput();
            } else if(aSlotIndex == 5) {
               this.mTileEntity.setActive(!this.mTileEntity.isActive());
            } else if(aSlotIndex == 6) {
               if(aMouseclick == 0) {
                  ((GT_MetaTileEntity_RedstoneCircuitBlock)this.mTileEntity.getMetaTileEntity()).switchGateForward(aShifthold != 0);
               } else {
                  ((GT_MetaTileEntity_RedstoneCircuitBlock)this.mTileEntity.getMetaTileEntity()).switchGateBackward(aShifthold != 0);
               }
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mGate = ((GT_MetaTileEntity_RedstoneCircuitBlock)this.mTileEntity.getMetaTileEntity()).mGate;
         this.mData = ((GT_MetaTileEntity_RedstoneCircuitBlock)this.mTileEntity.getMetaTileEntity()).mGateData;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mGate & '\uffff');
            var1.func_71112_a(this, 101, this.mGate >>> 16);
            var1.func_71112_a(this, 102, this.mData[0] & '\uffff');
            var1.func_71112_a(this, 103, this.mData[0] >>> 16);
            var1.func_71112_a(this, 104, this.mData[1] & '\uffff');
            var1.func_71112_a(this, 105, this.mData[1] >>> 16);
            var1.func_71112_a(this, 106, this.mData[2] & '\uffff');
            var1.func_71112_a(this, 107, this.mData[2] >>> 16);
            var1.func_71112_a(this, 108, this.mData[3] & '\uffff');
            var1.func_71112_a(this, 109, this.mData[3] >>> 16);
            var1.func_71112_a(this, 110, this.mData[4] & '\uffff');
            var1.func_71112_a(this, 111, this.mData[4] >>> 16);
            var1.func_71112_a(this, 112, this.mData[5] & '\uffff');
            var1.func_71112_a(this, 113, this.mData[5] >>> 16);
            var1.func_71112_a(this, 114, this.mData[6] & '\uffff');
            var1.func_71112_a(this, 115, this.mData[6] >>> 16);
            var1.func_71112_a(this, 116, this.mData[7] & '\uffff');
            var1.func_71112_a(this, 117, this.mData[7] >>> 16);
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
         this.mGate = this.mGate & -65536 | par2;
         break;
      case 101:
         this.mGate = this.mGate & '\uffff' | par2 << 16;
         break;
      case 102:
         this.mData[0] = this.mData[0] & -65536 | par2;
         break;
      case 103:
         this.mData[0] = this.mData[0] & '\uffff' | par2 << 16;
         break;
      case 104:
         this.mData[1] = this.mData[1] & -65536 | par2;
         break;
      case 105:
         this.mData[1] = this.mData[1] & '\uffff' | par2 << 16;
         break;
      case 106:
         this.mData[2] = this.mData[2] & -65536 | par2;
         break;
      case 107:
         this.mData[2] = this.mData[2] & '\uffff' | par2 << 16;
         break;
      case 108:
         this.mData[3] = this.mData[3] & -65536 | par2;
         break;
      case 109:
         this.mData[3] = this.mData[3] & '\uffff' | par2 << 16;
         break;
      case 110:
         this.mData[4] = this.mData[4] & -65536 | par2;
         break;
      case 111:
         this.mData[4] = this.mData[4] & '\uffff' | par2 << 16;
         break;
      case 112:
         this.mData[5] = this.mData[5] & -65536 | par2;
         break;
      case 113:
         this.mData[5] = this.mData[5] & '\uffff' | par2 << 16;
         break;
      case 114:
         this.mData[6] = this.mData[6] & -65536 | par2;
         break;
      case 115:
         this.mData[6] = this.mData[6] & '\uffff' | par2 << 16;
         break;
      case 116:
         this.mData[7] = this.mData[7] & -65536 | par2;
         break;
      case 117:
         this.mData[7] = this.mData[7] & '\uffff' | par2 << 16;
      }

   }
}
