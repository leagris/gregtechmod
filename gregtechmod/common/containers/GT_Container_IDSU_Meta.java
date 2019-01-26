package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Armor;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.energy.storage.GT_MetaTileEntity_IDSU;
import java.util.Iterator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class GT_Container_IDSU_Meta extends GT_ContainerMetaTile_Machine {

   public int mPlayerHash = 0;


   public GT_Container_IDSU_Meta(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 0, 128, 14));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 128, 50));
      this.func_75146_a(new GT_Slot_Armor(aInventoryPlayer, 36, 152, 59, 3, aInventoryPlayer.field_70458_d));
      this.func_75146_a(new GT_Slot_Armor(aInventoryPlayer, 37, 152, 41, 2, aInventoryPlayer.field_70458_d));
      this.func_75146_a(new GT_Slot_Armor(aInventoryPlayer, 38, 152, 23, 1, aInventoryPlayer.field_70458_d));
      this.func_75146_a(new GT_Slot_Armor(aInventoryPlayer, 39, 152, 5, 0, aInventoryPlayer.field_70458_d));
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mPlayerHash = ((GT_MetaTileEntity_IDSU)this.mTileEntity.getMetaTileEntity()).mFrequency;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mPlayerHash & '\uffff');
            var1.func_71112_a(this, 101, this.mPlayerHash >>> 16);
         }

      }
   }

   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 100:
         this.mPlayerHash = this.mPlayerHash & -65536 | par2;
         break;
      case 101:
         this.mPlayerHash = this.mPlayerHash & '\uffff' | par2 << 16;
      }

   }

   public int getSlotCount() {
      return 2;
   }

   public int getShiftClickSlotCount() {
      return 2;
   }
}
