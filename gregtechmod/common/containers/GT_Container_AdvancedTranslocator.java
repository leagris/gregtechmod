package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_AdvancedTranslocator;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_AdvancedTranslocator extends GT_ContainerMetaTile_Machine {

   public int mInputSide;
   public int mOutputSide;


   public GT_Container_AdvancedTranslocator(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 0, 63, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 1, 80, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 97, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 3, 63, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 4, 80, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 5, 97, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 6, 63, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 7, 80, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 8, 97, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 8, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 26, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 43, 5, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 117, 5, false, true, 1));
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

            if(aSlotIndex < 9) {
               ItemStack tStack = aPlayer.field_71071_by.func_70445_o();
               if(tStack != null) {
                  tStack = GT_Utility.copyAmount(1L, new Object[]{tStack});
               }

               tSlot.func_75215_d(tStack);
               return null;
            }

            if(aSlotIndex == 9) {
               ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).bOutput;
               if(((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).bOutput) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Energy");
               }

               return null;
            }

            if(aSlotIndex == 10) {
               ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).bInvertFilter = !((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).bInvertFilter;
               if(((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).bInvertFilter) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Inverted the Filter");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Filter works normal");
               }

               return null;
            }

            if(aSlotIndex == 11) {
               ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).mInputSide = (byte)((((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).mInputSide + 1) % 6);
            } else if(aSlotIndex == 12) {
               ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).mOutputSide = (byte)((((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).mOutputSide + 1) % 6);
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mInputSide = ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).mInputSide;
         this.mOutputSide = ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.getMetaTileEntity()).mOutputSide;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mInputSide);
            var1.func_71112_a(this, 101, this.mOutputSide);
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
         this.mInputSide = par2;
         break;
      case 101:
         this.mOutputSide = par2;
      }

   }
}
