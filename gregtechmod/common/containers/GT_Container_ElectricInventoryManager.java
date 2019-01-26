package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricInventoryManager;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_ElectricInventoryManager extends GT_ContainerMetaTile_Machine {

   public int[] mTargetDirections = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   public int[] mRangeDirections = new int[]{0, 0, 0, 0};
   public int mTargetInOut = 0;
   public int mTargetEnergy = 0;


   public GT_Container_ElectricInventoryManager(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 155, 5));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 155, 23));
      this.func_75146_a(new Slot(this.mTileEntity, 2, 155, 41));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 3, 5, 5, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 4, 5, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 5, 5, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 6, 61, 5, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 7, 61, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 8, 61, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 80, 5, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 80, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 11, 80, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 12, 136, 5, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 13, 136, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 14, 136, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 24, 5, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 24, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 24, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 42, 5, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 42, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 42, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 99, 5, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 99, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 99, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 117, 5, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 117, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 117, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 24, 60, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 42, 60, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 99, 60, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 117, 60, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 5, 60, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 61, 60, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 80, 60, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 136, 60, false, true, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex >= 3 && aSlotIndex < this.getAllSlotCount()) {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            if(this.mTileEntity.getMetaTileEntity() == null) {
               return null;
            }

            if(aSlotIndex < 15) {
               ItemStack tStack = aPlayer.field_71071_by.func_70445_o();
               if(tStack != null) {
                  tStack = GT_Utility.copy(new Object[]{tStack});
                  if(aMouseclick != 0) {
                     tStack.func_77964_b(32767);
                  }

                  tSlot.func_75215_d(tStack);
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

               return null;
            }

            if(aSlotIndex >= 27 && aSlotIndex <= 30) {
               ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).iterateRangeDirection(aSlotIndex - 27);
            } else if(aSlotIndex >= 31 && aSlotIndex <= 34) {
               ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).switchRangeEnergy(aSlotIndex - 31);
            } else if(aSlotIndex % 3 == 0) {
               if(aMouseclick != 0) {
                  ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).switchSlot1InOut((aSlotIndex - 15) / 3);
               } else {
                  ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).iterateSlot1Direction((aSlotIndex - 15) / 3);
               }
            } else if(aSlotIndex % 3 == 1) {
               if(aMouseclick != 0) {
                  ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).switchSlot2InOut((aSlotIndex - 16) / 3);
               } else {
                  ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).iterateSlot2Direction((aSlotIndex - 16) / 3);
               }
            } else if(aSlotIndex % 3 == 2) {
               if(aMouseclick != 0) {
                  ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).switchSlot3InOut((aSlotIndex - 17) / 3);
               } else {
                  ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).iterateSlot3Direction((aSlotIndex - 17) / 3);
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
         this.mTargetDirections = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         this.mRangeDirections = new int[]{0, 0, 0, 0};
         this.mRangeDirections[0] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getRangeDirection(0);
         this.mRangeDirections[1] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getRangeDirection(1);
         this.mRangeDirections[2] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getRangeDirection(2);
         this.mRangeDirections[3] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getRangeDirection(3);
         this.mTargetDirections[0] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot1Direction(0);
         this.mTargetDirections[1] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot2Direction(0);
         this.mTargetDirections[2] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot3Direction(0);
         this.mTargetDirections[3] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot1Direction(1);
         this.mTargetDirections[4] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot2Direction(1);
         this.mTargetDirections[5] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot3Direction(1);
         this.mTargetDirections[6] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot1Direction(2);
         this.mTargetDirections[7] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot2Direction(2);
         this.mTargetDirections[8] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot3Direction(2);
         this.mTargetDirections[9] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot1Direction(3);
         this.mTargetDirections[10] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot2Direction(3);
         this.mTargetDirections[11] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot3Direction(3);
         this.mTargetInOut = 0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot1InOut(0)?1:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot2InOut(0)?2:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot3InOut(0)?4:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot1InOut(1)?8:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot2InOut(1)?16:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot3InOut(1)?32:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot1InOut(2)?64:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot2InOut(2)?128:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot3InOut(2)?256:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot1InOut(3)?512:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot2InOut(3)?1024:0;
         this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getSlot3InOut(3)?2048:0;
         this.mTargetEnergy = 0;
         this.mTargetEnergy |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getRangeEnergy(0)?1:0;
         this.mTargetEnergy |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getRangeEnergy(1)?2:0;
         this.mTargetEnergy |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getRangeEnergy(2)?4:0;
         this.mTargetEnergy |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.getMetaTileEntity()).getRangeEnergy(3)?8:0;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();

            int i;
            for(i = 0; i < 12; ++i) {
               var1.func_71112_a(this, 100 + i, this.mTargetDirections[i]);
            }

            var1.func_71112_a(this, 113, this.mTargetInOut);
            var1.func_71112_a(this, 114, this.mTargetEnergy);

            for(i = 0; i < 4; ++i) {
               var1.func_71112_a(this, 115 + i, this.mRangeDirections[i]);
            }
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
         this.mTargetDirections[0] = par2;
         break;
      case 101:
         this.mTargetDirections[1] = par2;
         break;
      case 102:
         this.mTargetDirections[2] = par2;
         break;
      case 103:
         this.mTargetDirections[3] = par2;
         break;
      case 104:
         this.mTargetDirections[4] = par2;
         break;
      case 105:
         this.mTargetDirections[5] = par2;
         break;
      case 106:
         this.mTargetDirections[6] = par2;
         break;
      case 107:
         this.mTargetDirections[7] = par2;
         break;
      case 108:
         this.mTargetDirections[8] = par2;
         break;
      case 109:
         this.mTargetDirections[9] = par2;
         break;
      case 110:
         this.mTargetDirections[10] = par2;
         break;
      case 111:
         this.mTargetDirections[11] = par2;
      case 112:
      default:
         break;
      case 113:
         this.mTargetInOut = par2;
         break;
      case 114:
         this.mTargetEnergy = par2;
         break;
      case 115:
         this.mRangeDirections[0] = par2;
         break;
      case 116:
         this.mRangeDirections[1] = par2;
         break;
      case 117:
         this.mRangeDirections[2] = par2;
         break;
      case 118:
         this.mRangeDirections[3] = par2;
      }

   }

   public int getSlotCount() {
      return 3;
   }

   public int getShiftClickSlotCount() {
      return 3;
   }
}
