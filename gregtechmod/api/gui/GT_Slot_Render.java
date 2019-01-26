package gregtechmod.api.gui;

import gregtechmod.api.gui.GT_Slot_Holo;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class GT_Slot_Render extends GT_Slot_Holo {

   public GT_Slot_Render(IInventory par1iInventory, int par2, int par3, int par4) {
      super(par1iInventory, par2, par3, par4, false, false, 0);
   }

   public void func_75215_d(ItemStack aStack) {
      if(this.field_75224_c instanceof TileEntity && ((TileEntity)this.field_75224_c).field_70331_k.field_72995_K) {
         this.field_75224_c.func_70299_a(this.getSlotIndex(), aStack);
      }

      this.func_75218_e();
   }
}
