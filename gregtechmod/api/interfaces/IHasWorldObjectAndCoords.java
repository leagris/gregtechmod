package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.IFluidHandler;

public interface IHasWorldObjectAndCoords {

   World getWorld();

   int getXCoord();

   short getYCoord();

   int getZCoord();

   boolean isServerSide();

   boolean isClientSide();

   int getRandomNumber(int var1);

   TileEntity getTileEntity(int var1, int var2, int var3);

   TileEntity getTileEntityOffset(int var1, int var2, int var3);

   TileEntity getTileEntityAtSide(byte var1);

   TileEntity getTileEntityAtSideAndDistance(byte var1, int var2);

   IInventory getIInventory(int var1, int var2, int var3);

   IInventory getIInventoryOffset(int var1, int var2, int var3);

   IInventory getIInventoryAtSide(byte var1);

   IInventory getIInventoryAtSideAndDistance(byte var1, int var2);

   IFluidHandler getITankContainer(int var1, int var2, int var3);

   IFluidHandler getITankContainerOffset(int var1, int var2, int var3);

   IFluidHandler getITankContainerAtSide(byte var1);

   IFluidHandler getITankContainerAtSideAndDistance(byte var1, int var2);

   IGregTechTileEntity getIGregTechTileEntity(int var1, int var2, int var3);

   IGregTechTileEntity getIGregTechTileEntityOffset(int var1, int var2, int var3);

   IGregTechTileEntity getIGregTechTileEntityAtSide(byte var1);

   IGregTechTileEntity getIGregTechTileEntityAtSideAndDistance(byte var1, int var2);

   Block getBlock(int var1, int var2, int var3);

   Block getBlockOffset(int var1, int var2, int var3);

   Block getBlockAtSide(byte var1);

   Block getBlockAtSideAndDistance(byte var1, int var2);

   short getBlockID(int var1, int var2, int var3);

   short getBlockIDOffset(int var1, int var2, int var3);

   short getBlockIDAtSide(byte var1);

   short getBlockIDAtSideAndDistance(byte var1, int var2);

   byte getMetaID(int var1, int var2, int var3);

   byte getMetaIDOffset(int var1, int var2, int var3);

   byte getMetaIDAtSide(byte var1);

   byte getMetaIDAtSideAndDistance(byte var1, int var2);

   byte getLightLevel(int var1, int var2, int var3);

   byte getLightLevelOffset(int var1, int var2, int var3);

   byte getLightLevelAtSide(byte var1);

   byte getLightLevelAtSideAndDistance(byte var1, int var2);

   boolean getOpacity(int var1, int var2, int var3);

   boolean getOpacityOffset(int var1, int var2, int var3);

   boolean getOpacityAtSide(byte var1);

   boolean getOpacityAtSideAndDistance(byte var1, int var2);

   boolean getSky(int var1, int var2, int var3);

   boolean getSkyOffset(int var1, int var2, int var3);

   boolean getSkyAtSide(byte var1);

   boolean getSkyAtSideAndDistance(byte var1, int var2);

   boolean getAir(int var1, int var2, int var3);

   boolean getAirOffset(int var1, int var2, int var3);

   boolean getAirAtSide(byte var1);

   boolean getAirAtSideAndDistance(byte var1, int var2);

   BiomeGenBase getBiome();

   BiomeGenBase getBiome(int var1, int var2);

   int getOffsetX(byte var1, int var2);

   short getOffsetY(byte var1, int var2);

   int getOffsetZ(byte var1, int var2);

   void sendBlockEvent(byte var1, byte var2);

   long getTimer();

   void setLightValue(byte var1);

   void writeToNBT(NBTTagCompound var1);

   void readFromNBT(NBTTagCompound var1);

   boolean isInvalidTileEntity();

   boolean openGUI(EntityPlayer var1, int var2, Object var3);

   boolean openGUI(EntityPlayer var1, int var2);
}
