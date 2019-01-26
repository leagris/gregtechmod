package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricSorter;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_ElectricSorter extends GT_ContainerMetaTile_Machine {

   public int mTargetDirection;


   public GT_Container_ElectricSorter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 25, 23));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 1, 63, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 80, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 3, 97, 6, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 4, 63, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 5, 80, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 6, 97, 23, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 7, 63, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 8, 80, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 97, 40, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 8, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 26, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 44, 63, false, true, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 134, 63, false, true, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 1) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            if(this.mTileEntity.getMetaTileEntity() == null) {
               return null;
            }

            if(aSlotIndex < 10) {
               ItemStack tStack = aPlayer.field_71071_by.func_70445_o();
               if(tStack == null) {
                  tStack = tSlot.func_75211_c();
                  if(aMouseclick == 0) {
                     tSlot.func_75215_d((ItemStack)null);
                  } else if(tStack != null) {
                     tStack.func_77964_b(32767);
                  }
               } else {
                  tSlot.func_75215_d(GT_Utility.copyAmount(1L, new Object[]{tStack}));
               }

               return null;
            }

            if(aSlotIndex == 10) {
               ((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bOutput;
               if(((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bOutput) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Energy");
               }

               return null;
            }

            if(aSlotIndex == 11) {
               ((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull;
               if(((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Emit Redstone if slot contains something");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t emit Redstone");
               }

               return null;
            }

            if(aSlotIndex == 12) {
               ((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bInvert = !((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bInvert;
               if(((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.getMetaTileEntity()).bInvert) {
                  GT_Utility.sendChatToPlayer(aPlayer, "Invert Redstone");
               } else {
                  GT_Utility.sendChatToPlayer(aPlayer, "Don\'t invert Redstone");
               }

               return null;
            }

            if(aSlotIndex == 13) {
               ((GT_MetaTileEntity_ElectricSorter)this.mTileEntity.getMetaTileEntity()).mTargetDirection = (byte)((((GT_MetaTileEntity_ElectricSorter)this.mTileEntity.getMetaTileEntity()).mTargetDirection + 1) % 6);
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mTargetDirection = ((GT_MetaTileEntity_ElectricSorter)this.mTileEntity.getMetaTileEntity()).mTargetDirection;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mTargetDirection);
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
         this.mTargetDirection = par2;
      default:
      }
   }

   public int getSlotCount() {
      return 1;
   }

   public int getShiftClickSlotCount() {
      return 1;
   }
}
