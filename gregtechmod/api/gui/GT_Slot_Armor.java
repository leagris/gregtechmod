package gregtechmod.api.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Slot_Armor extends Slot {

   final int mArmorType;
   final EntityPlayer mPlayer;


   public GT_Slot_Armor(IInventory par2IInventory, int par3, int par4, int par5, int par6, EntityPlayer aPlayer) {
      super(par2IInventory, par3, par4, par5);
      this.mArmorType = par6;
      this.mPlayer = aPlayer;
   }

   public int func_75219_a() {
      return 1;
   }

   public boolean func_75214_a(ItemStack aStack) {
      return aStack != null && aStack.func_77973_b() != null && aStack.func_77973_b().isValidArmor(aStack, this.mArmorType, this.mPlayer);
   }
}
