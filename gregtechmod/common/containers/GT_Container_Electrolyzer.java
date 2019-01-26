package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.machines.GT_MetaTileEntity_Electrolyzer;
import java.util.Iterator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class GT_Container_Electrolyzer extends GT_ContainerMetaTile_Machine {

   public int mProgress;
   public int mMaxProgress;
   public int mProgressScale;


   public GT_Container_Electrolyzer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 80, 46));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 50, 46));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 2, 50, 16));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 3, 70, 16));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 4, 90, 16));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 5, 110, 16));
      this.func_75146_a(new GT_Slot_Holo(this.mTileEntity, 6, 110, 46, false, false, 64));
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mProgress = ((GT_MetaTileEntity_Electrolyzer)this.mTileEntity.getMetaTileEntity()).getProgresstime();
         this.mMaxProgress = ((GT_MetaTileEntity_Electrolyzer)this.mTileEntity.getMetaTileEntity()).maxProgresstime();
         this.mProgressScale = Math.max(0, Math.min(10, (this.mProgress > 0?1:0) + this.mProgress * 10 / (this.mMaxProgress < 1?1:this.mMaxProgress)));
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mProgress);
            var1.func_71112_a(this, 101, this.mMaxProgress);
            var1.func_71112_a(this, 102, this.mProgressScale);
         }

      }
   }

   @SideOnly(Side.CLIENT)
   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 100:
         this.mProgress = par2;
         break;
      case 101:
         this.mMaxProgress = par2;
         break;
      case 102:
         this.mProgressScale = par2;
      }

   }

   public int getSlotCount() {
      return 6;
   }

   public int getShiftClickSlotCount() {
      return 2;
   }
}
