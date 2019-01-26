package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.machines.GT_MetaTileEntity_Teleporter;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Teleporter extends GT_ContainerMetaTile_Machine {

   public int mTargetX = 0;
   public int mTargetY = 0;
   public int mTargetZ = 0;
   public int mTargetD = 0;
   public int mEgg = 0;


   public GT_Container_Teleporter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 8, 5, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 8, 23, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 8, 41, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 8, 59, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 26, 5, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 26, 23, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 26, 41, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 26, 59, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 152, 5, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 152, 23, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 152, 41, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 152, 59, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 134, 5, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 134, 23, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 134, 41, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 134, 59, false, false, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 0) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null && this.mTileEntity.getMetaTileEntity() != null) {
            GT_MetaTileEntity_Teleporter var10000;
            switch(aSlotIndex) {
            case 0:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetX -= aShifthold == 1?512:64;
               return null;
            case 1:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetY -= aShifthold == 1?512:64;
               return null;
            case 2:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetZ -= aShifthold == 1?512:64;
               return null;
            case 3:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetD -= aShifthold == 1?16:8;
               return null;
            case 4:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetX -= aShifthold == 1?16:1;
               return null;
            case 5:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetY -= aShifthold == 1?16:1;
               return null;
            case 6:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetZ -= aShifthold == 1?16:1;
               return null;
            case 7:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetD -= aShifthold == 1?4:1;
               return null;
            case 8:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetX += aShifthold == 1?512:64;
               return null;
            case 9:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetY += aShifthold == 1?512:64;
               return null;
            case 10:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetZ += aShifthold == 1?512:64;
               return null;
            case 11:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetD += aShifthold == 1?16:8;
               return null;
            case 12:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetX += aShifthold == 1?16:1;
               return null;
            case 13:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetY += aShifthold == 1?16:1;
               return null;
            case 14:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetZ += aShifthold == 1?16:1;
               return null;
            case 15:
               var10000 = (GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity();
               var10000.mTargetD += aShifthold == 1?4:1;
               return null;
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mTargetX = ((GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity()).mTargetX;
         this.mTargetY = ((GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity()).mTargetY;
         this.mTargetZ = ((GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity()).mTargetZ;
         this.mTargetD = ((GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity()).mTargetD;
         this.mEgg = ((GT_MetaTileEntity_Teleporter)this.mTileEntity.getMetaTileEntity()).hasDimensionalTeleportCapability()?1:0;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mTargetX & '\uffff');
            var1.func_71112_a(this, 101, this.mTargetX >>> 16);
            var1.func_71112_a(this, 102, this.mTargetY & '\uffff');
            var1.func_71112_a(this, 103, this.mTargetY >>> 16);
            var1.func_71112_a(this, 104, this.mTargetZ & '\uffff');
            var1.func_71112_a(this, 105, this.mTargetZ >>> 16);
            var1.func_71112_a(this, 106, this.mTargetD & '\uffff');
            var1.func_71112_a(this, 107, this.mTargetD >>> 16);
            var1.func_71112_a(this, 108, this.mEgg);
         }

      }
   }

   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 100:
         this.mTargetX = this.mTargetX & -65536 | par2;
         break;
      case 101:
         this.mTargetX = this.mTargetX & '\uffff' | par2 << 16;
         break;
      case 102:
         this.mTargetY = this.mTargetY & -65536 | par2;
         break;
      case 103:
         this.mTargetY = this.mTargetY & '\uffff' | par2 << 16;
         break;
      case 104:
         this.mTargetZ = this.mTargetZ & -65536 | par2;
         break;
      case 105:
         this.mTargetZ = this.mTargetZ & '\uffff' | par2 << 16;
         break;
      case 106:
         this.mTargetD = this.mTargetD & -65536 | par2;
         break;
      case 107:
         this.mTargetD = this.mTargetD & '\uffff' | par2 << 16;
         break;
      case 108:
         this.mEgg = par2;
      }

   }
}
