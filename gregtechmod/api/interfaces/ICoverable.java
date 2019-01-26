package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.IBasicEnergyContainer;
import gregtechmod.api.interfaces.IHasInventory;
import gregtechmod.api.interfaces.IRedstoneTileEntity;
import gregtechmod.api.util.GT_CoverBehavior;
import net.minecraft.item.ItemStack;

public interface ICoverable extends IRedstoneTileEntity, IHasInventory, IBasicEnergyContainer {

   boolean canPlaceCoverIDAtSide(byte var1, int var2);

   boolean canPlaceCoverItemAtSide(byte var1, ItemStack var2);

   boolean dropCover(byte var1, byte var2, boolean var3);

   void setCoverDataAtSide(byte var1, int var2);

   void setCoverIDAtSide(byte var1, int var2);

   void setCoverItemAtSide(byte var1, ItemStack var2);

   int getCoverDataAtSide(byte var1);

   int getCoverIDAtSide(byte var1);

   ItemStack getCoverItemAtSide(byte var1);

   GT_CoverBehavior getCoverBehaviorAtSide(byte var1);

   byte getInternalInputRedstoneSignal(byte var1);

   void setInternalOutputRedstoneSignal(byte var1, byte var2);

   void issueCoverUpdate(byte var1);
}
