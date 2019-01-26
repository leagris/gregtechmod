package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public interface IRedstoneCircuitBlock {

   byte getOutputFacing();

   boolean setRedstone(byte var1, byte var2);

   byte getOutputRedstone(byte var1);

   byte getInputRedstone(byte var1);

   GT_CoverBehavior getCover(byte var1);

   int getCoverID(byte var1);

   int getCoverVariable(byte var1);

   Block getBlockAtSide(byte var1);

   byte getMetaIDAtSide(byte var1);

   TileEntity getTileEntityAtSide(byte var1);

   ICoverable getOwnTileEntity();

   int getRandom(int var1);
}
