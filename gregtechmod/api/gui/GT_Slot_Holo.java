package gregtechmod.api.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Slot_Holo extends Slot {

   public final int mSlotIndex;
   public boolean mCanInsertItem;
   public boolean mCanStackItem;
   public int mMaxStacksize = 127;


   public GT_Slot_Holo(IInventory par1iInventory, int par2, int par3, int par4, boolean aCanInsertItem, boolean aCanStackItem, int aMaxStacksize) {
      super(par1iInventory, par2, par3, par4);
      this.mCanInsertItem = aCanInsertItem;
      this.mCanStackItem = aCanStackItem;
      this.mMaxStacksize = aMaxStacksize;
      this.mSlotIndex = par2;
   }

   public boolean func_75214_a(ItemStack par1ItemStack) {
      return this.mCanInsertItem;
   }

   public int func_75219_a() {
      return this.mMaxStacksize;
   }

   public boolean func_75216_d() {
      return false;
   }

   public ItemStack func_75209_a(int par1) {
      return !this.mCanStackItem?null:super.func_75209_a(par1);
   }

   public boolean func_82869_a(EntityPlayer par1EntityPlayer) {
      return false;
   }
}
