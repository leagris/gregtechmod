package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.energy.production.GT_MetaTileEntity_MagicEnergyAbsorber;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_MagicEnergyAbsorber extends GT_ContainerMetaTile_Machine {

   public boolean isActive1 = false;
   public boolean isActive2 = false;


   public GT_Container_MagicEnergyAbsorber(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 80, 17));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 1, 80, 53));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 10, 35, false, false, 1));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 2, 10, 18, false, false, 1));
   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex < 2) {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } else {
         Slot tSlot = (Slot)this.field_75151_b.get(aSlotIndex);
         if(tSlot != null) {
            if(this.mTileEntity.getMetaTileEntity() == null) {
               return null;
            }

            if(aSlotIndex == 2) {
               ((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.getMetaTileEntity()).isActive1 = !((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.getMetaTileEntity()).isActive1;
               return null;
            }

            if(aSlotIndex == 3) {
               ((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.getMetaTileEntity()).isActive2 = !((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.getMetaTileEntity()).isActive2;
               return null;
            }
         }

         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      }
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.isActive1 = ((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.getMetaTileEntity()).isActive1;
         this.isActive2 = ((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.getMetaTileEntity()).isActive2;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.isActive1?1:0);
            var1.func_71112_a(this, 101, this.isActive2?1:0);
         }

      }
   }

   @SideOnly(Side.CLIENT)
   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 100:
         this.isActive1 = par2 != 0;
         break;
      case 101:
         this.isActive2 = par2 != 0;
      }

   }

   public int getSlotCount() {
      return 2;
   }

   public int getShiftClickSlotCount() {
      return 1;
   }
}
