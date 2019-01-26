package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.IHasWorldObjectAndCoords;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public interface IHasInventory extends ISidedInventory, IHasWorldObjectAndCoords {

   boolean hasInventoryBeenModified();

   boolean isValidSlot(int var1);

   boolean addStackToSlot(int var1, ItemStack var2);

   boolean addStackToSlot(int var1, ItemStack var2, int var3);
}
