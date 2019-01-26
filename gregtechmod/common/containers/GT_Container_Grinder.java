package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.machines.multi.GT_MetaTileEntity_Grinder;
import java.util.Iterator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class GT_Container_Grinder extends GT_ContainerMetaTile_Machine {

   public int mProgress;
   public int mMaxProgress;
   public int mProgressScale;
   public int mWaterAmount;
   public boolean mMachine = true;


   public GT_Container_Grinder(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 34, 16));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 34, 34));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 2, 86, 25));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 3, 104, 25));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 4, 122, 25));
      this.func_75146_a(new GT_Slot_Output(this.mTileEntity, 5, 140, 25));
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mMachine = ((GT_MetaTileEntity_Grinder)this.mTileEntity.getMetaTileEntity()).mMachine;
         this.mWaterAmount = ((GT_MetaTileEntity_Grinder)this.mTileEntity.getMetaTileEntity()).mFluidAmount;
         this.mProgress = ((GT_MetaTileEntity_Grinder)this.mTileEntity.getMetaTileEntity()).getProgresstime();
         this.mMaxProgress = ((GT_MetaTileEntity_Grinder)this.mTileEntity.getMetaTileEntity()).maxProgresstime();
         this.mProgressScale = Math.max(0, Math.min(20, (this.mProgress > 0?1:0) + this.mProgress * 20 / (this.mMaxProgress < 1?1:this.mMaxProgress)));
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mProgress);
            var1.func_71112_a(this, 101, this.mMaxProgress);
            var1.func_71112_a(this, 102, this.mProgressScale);
            var1.func_71112_a(this, 103, this.mMachine?1:0);
            var1.func_71112_a(this, 104, this.mWaterAmount);
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
         break;
      case 103:
         this.mMachine = par2 != 0;
         break;
      case 104:
         this.mWaterAmount = par2;
      }

   }

   public int getSlotCount() {
      return 6;
   }

   public int getShiftClickSlotCount() {
      return 2;
   }
}
