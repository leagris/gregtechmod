package gregtechmod.api.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.gui.GT_Slot_Render;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import java.util.Iterator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class GT_Container_BasicTank extends GT_ContainerMetaTile_Machine {

   public int mContent = 0;


   public GT_Container_BasicTank(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 80, 17));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 1, 80, 53));
      this.func_75146_a(new GT_Slot_Render(this.mTileEntity, 2, 59, 42));
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         if(((GT_MetaTileEntity_BasicTank)this.mTileEntity.getMetaTileEntity()).mFluid != null) {
            this.mContent = ((GT_MetaTileEntity_BasicTank)this.mTileEntity.getMetaTileEntity()).mFluid.amount;
         } else {
            this.mContent = 0;
         }

         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mContent & '\uffff');
            var1.func_71112_a(this, 101, this.mContent >>> 16);
         }

      }
   }

   @SideOnly(Side.CLIENT)
   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 100:
         this.mContent = this.mContent & -65536 | par2;
         break;
      case 101:
         this.mContent = this.mContent & '\uffff' | par2 << 16;
      }

   }

   public int getSlotCount() {
      return 2;
   }

   public int getShiftClickSlotCount() {
      return 1;
   }
}
