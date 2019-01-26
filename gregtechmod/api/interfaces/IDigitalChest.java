package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.IHasWorldObjectAndCoords;
import net.minecraft.item.ItemStack;

public interface IDigitalChest extends IHasWorldObjectAndCoords {

   boolean isDigitalChest();

   ItemStack[] getStoredItemData();

   void setItemCount(int var1);

   int getMaxItemCount();
}
