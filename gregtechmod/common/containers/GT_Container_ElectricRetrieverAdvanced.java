package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricRetrieverAdvanced;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_ElectricRetrieverAdvanced extends GT_ContainerMetaTile_Machine {

   public int[] mTargetSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};


   public GT_Container_ElectricRetrieverAdvanced(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.mTargetSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 64, 7, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 1, 81, 7, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 98, 7, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 3, 64, 24, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 4, 81, 24, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 5, 98, 24, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 6, 64, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 7, 81, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 8, 98, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 119, 7, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 136, 7, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 153, 7, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 119, 24, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 136, 24, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 153, 24, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 119, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 136, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 153, 41, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 8, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 26, 63, false, true, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 0) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            if(this.mTileEntity.getMetaTileEntity() == null) {
               return null;
            }

            if(aSlotIndex == 18) {
               ((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).bOutput;
               if(((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).bOutput) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Energy");
               }

               return null;
            }

            if(aSlotIndex == 19) {
               ((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mPartialRequests = !((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mPartialRequests;
               if(((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mPartialRequests) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Partial Requests allowed");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Partial Requests forbidden");
               }

               return null;
            }

            if(aSlotIndex >= 0 && aSlotIndex < 9) {
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

               return null;
            }

            if(aSlotIndex >= 9 && aSlotIndex < 18) {
               ((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mTargetSlots[aSlotIndex - 9] = Math.min(99, Math.max(0, ((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mTargetSlots[aSlotIndex - 9] + (aMouseclick == 0?-1:1) * (aShifthold == 0?1:16)));
               return null;
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mTargetSlots = new int[9];

         for(int var2 = 0; var2 < 9; ++var2) {
            this.mTargetSlots[var2] = ((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mTargetSlots[var2];
         }

         Iterator var4 = this.field_75149_d.iterator();

         while(var4.hasNext()) {
            ICrafting var1 = (ICrafting)var4.next();

            for(int i = 0; i < 9; ++i) {
               var1.func_71112_a(this, 100 + i, this.mTargetSlots[i]);
            }
         }

      }
   }

   @SideOnly(Side.CLIENT)
   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 100:
         this.mTargetSlots[0] = par2;
         break;
      case 101:
         this.mTargetSlots[1] = par2;
         break;
      case 102:
         this.mTargetSlots[2] = par2;
         break;
      case 103:
         this.mTargetSlots[3] = par2;
         break;
      case 104:
         this.mTargetSlots[4] = par2;
         break;
      case 105:
         this.mTargetSlots[5] = par2;
         break;
      case 106:
         this.mTargetSlots[6] = par2;
         break;
      case 107:
         this.mTargetSlots[7] = par2;
         break;
      case 108:
         this.mTargetSlots[8] = par2;
      }

   }

   public int getSlotCount() {
      return 0;
   }

   public int getShiftClickSlotCount() {
      return 0;
   }
}
