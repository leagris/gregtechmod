package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.storage.GT_MetaTileEntity_BronzeCraftingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_BronzeWorkbench extends GT_ContainerMetaTile_Machine {

   public GT_Container_BronzeWorkbench(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 8, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 26, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 2, 44, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 3, 62, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 4, 8, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 5, 26, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 6, 44, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 7, 62, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 8, 8, 44));
      this.func_75146_a(new Slot(this.mTileEntity, 9, 26, 44));
      this.func_75146_a(new Slot(this.mTileEntity, 10, 44, 44));
      this.func_75146_a(new Slot(this.mTileEntity, 11, 62, 44));
      this.func_75146_a(new Slot(this.mTileEntity, 12, 8, 62));
      this.func_75146_a(new Slot(this.mTileEntity, 13, 26, 62));
      this.func_75146_a(new Slot(this.mTileEntity, 14, 44, 62));
      this.func_75146_a(new Slot(this.mTileEntity, 15, 62, 62));
      this.func_75146_a(new Slot(this.mTileEntity, 16, 82, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 17, 100, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 18, 118, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 19, 136, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 20, 154, 8));
      this.func_75146_a(new Slot(this.mTileEntity, 21, 82, 28));
      this.func_75146_a(new Slot(this.mTileEntity, 22, 100, 28));
      this.func_75146_a(new Slot(this.mTileEntity, 23, 118, 28));
      this.func_75146_a(new Slot(this.mTileEntity, 24, 82, 46));
      this.func_75146_a(new Slot(this.mTileEntity, 25, 100, 46));
      this.func_75146_a(new Slot(this.mTileEntity, 26, 118, 46));
      this.func_75146_a(new Slot(this.mTileEntity, 27, 82, 64));
      this.func_75146_a(new Slot(this.mTileEntity, 28, 100, 64));
      this.func_75146_a(new Slot(this.mTileEntity, 29, 118, 64));
      this.func_75146_a(new Slot(this.mTileEntity, 33, 154, 28));
      this.func_75146_a(new Slot(this.mTileEntity, 34, 154, 64));
      this.func_75146_a(new Slot(this.mTileEntity, 30, 136, 28));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 31, 136, 64, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 32, 154, 46, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 32, 136, 46, false, false, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex >= 21 && aSlotIndex <= 35) {
         if(this.mTileEntity != null && this.mTileEntity.getMetaTileEntity() != null) {
            try {
               ItemStack e = ((Slot)this.field_75151_b.get(aSlotIndex)).func_75211_c();
               if(e != null && e.field_77994_a <= 0 && !GT_Utility.areStacksEqual(e, aPlayer.field_71071_by.func_70445_o())) {
                  return null;
               }

               if(aSlotIndex == 32) {
                  if(aMouseclick == 0 && aShifthold == 1) {
                     ((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).setBluePrint((ItemStack)null);
                     return null;
                  }
               } else {
                  if(aSlotIndex == 33) {
                     ItemStack tCraftedStack = ((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).getCraftingOutput();
                     if(tCraftedStack != null) {
                        ItemStack tStack2;
                        if(aShifthold != 1) {
                           if(aMouseclick == 0) {
                              if(((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).canDoCraftingOutput()) {
                                 aPlayer.field_71071_by.func_70437_b(((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).consumeMaterials(aPlayer, aPlayer.field_71071_by.func_70445_o()));
                              }

                              return aPlayer.field_71071_by.func_70445_o();
                           }

                           int var11 = 0;

                           while(true) {
                              if(var11 < tCraftedStack.func_77976_d() / tCraftedStack.field_77994_a && ((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).canDoCraftingOutput()) {
                                 if(GT_Utility.areStacksEqual(tStack2 = ((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).getCraftingOutput(), tCraftedStack) && (e == null || e.field_77994_a == tStack2.field_77994_a)) {
                                    aPlayer.field_71071_by.func_70437_b(((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).consumeMaterials(aPlayer, aPlayer.field_71071_by.func_70445_o()));
                                    ++var11;
                                    continue;
                                 }

                                 return aPlayer.field_71071_by.func_70445_o();
                              }

                              return aPlayer.field_71071_by.func_70445_o();
                           }
                        }

                        byte i = 0;

                        while(i < aPlayer.field_71071_by.field_70462_a.length) {
                           byte j = 0;

                           while(true) {
                              if(j < tCraftedStack.func_77976_d() / tCraftedStack.field_77994_a && ((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).canDoCraftingOutput()) {
                                 if(GT_Utility.areStacksEqual(tStack2 = ((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).getCraftingOutput(), tCraftedStack) && (e == null || e.field_77994_a == tStack2.field_77994_a)) {
                                    aPlayer.field_71071_by.field_70462_a[i] = ((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).consumeMaterials(aPlayer, aPlayer.field_71071_by.field_70462_a[i]);
                                    ++j;
                                    continue;
                                 }

                                 return aPlayer.field_71071_by.func_70445_o();
                              }

                              ++i;
                              break;
                           }
                        }
                     }

                     return null;
                  }

                  if(aSlotIndex == 34) {
                     ((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).mFlushMode = true;
                     return null;
                  }

                  if(aSlotIndex == 35) {
                     ((GT_MetaTileEntity_BronzeCraftingTable)this.mTileEntity.getMetaTileEntity()).sortIntoTheInputSlots();
                     return null;
                  }
               }
            } catch (Throwable var10) {
               var10.printStackTrace(GT_Log.err);
            }

            return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
         } else {
            return null;
         }
      } else {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public int getSlotCount() {
      return 33;
   }

   public int getShiftClickSlotCount() {
      return 21;
   }
}
