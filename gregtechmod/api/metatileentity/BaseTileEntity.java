package gregtechmod.api.metatileentity;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IHasWorldObjectAndCoords;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.network.packet.Packet54PlayNoteBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

public abstract class BaseTileEntity extends TileEntity implements IHasWorldObjectAndCoords {

   public boolean ignoreUnloadedChunks = true;
   private final TileEntity[] mBufferedTileEntities = new TileEntity[6];


   private final void clearNullMarkersFromTileEntityBuffer() {
      for(int i = 0; i < this.mBufferedTileEntities.length; ++i) {
         if(this.mBufferedTileEntities[i] == this) {
            this.mBufferedTileEntities[i] = null;
         }
      }

   }

   public final World getWorld() {
      return this.field_70331_k;
   }

   public final int getXCoord() {
      return this.field_70329_l;
   }

   public final short getYCoord() {
      return (short)this.field_70330_m;
   }

   public final int getZCoord() {
      return this.field_70327_n;
   }

   public final int getOffsetX(byte aSide, int aMultiplier) {
      return this.field_70329_l + ForgeDirection.getOrientation(aSide).offsetX * aMultiplier;
   }

   public final short getOffsetY(byte aSide, int aMultiplier) {
      return (short)(this.field_70330_m + ForgeDirection.getOrientation(aSide).offsetY * aMultiplier);
   }

   public final int getOffsetZ(byte aSide, int aMultiplier) {
      return this.field_70327_n + ForgeDirection.getOrientation(aSide).offsetZ * aMultiplier;
   }

   public final boolean isServerSide() {
      return !this.field_70331_k.field_72995_K;
   }

   public final boolean isClientSide() {
      return this.field_70331_k.field_72995_K;
   }

   public final boolean openGUI(EntityPlayer aPlayer, int aID) {
      return this.openGUI(aPlayer, aID, GregTech_API.gregtechmod);
   }

   public final boolean openGUI(EntityPlayer aPlayer, int aID, Object aMod) {
      if(aPlayer != null && aMod != null) {
         aPlayer.openGui(aMod, aID, this.field_70331_k, this.field_70329_l, this.field_70330_m, this.field_70327_n);
         return true;
      } else {
         return false;
      }
   }

   public final int getRandomNumber(int aRange) {
      return this.field_70331_k.field_73012_v.nextInt(aRange);
   }

   public final BiomeGenBase getBiome(int aX, int aZ) {
      return this.field_70331_k.func_72807_a(aX, aZ);
   }

   public final BiomeGenBase getBiome() {
      return this.getBiome(this.field_70329_l, this.field_70327_n);
   }

   public final short getBlockIDOffset(int aX, int aY, int aZ) {
      return this.getBlockID(this.field_70329_l + aX, this.field_70330_m + aY, this.field_70327_n + aZ);
   }

   public final short getBlockIDAtSide(byte aSide) {
      return this.getBlockIDAtSideAndDistance(aSide, 1);
   }

   public final short getBlockIDAtSideAndDistance(byte aSide, int aDistance) {
      return this.getBlockID(this.getOffsetX(aSide, aDistance), this.getOffsetY(aSide, aDistance), this.getOffsetZ(aSide, aDistance));
   }

   public final Block getBlock(int aX, int aY, int aZ) {
      return Block.field_71973_m[this.getBlockID(aX, aY, aZ)];
   }

   public final Block getBlockOffset(int aX, int aY, int aZ) {
      return Block.field_71973_m[this.getBlockIDOffset(aX, aY, aZ)];
   }

   public final Block getBlockAtSide(byte aSide) {
      return Block.field_71973_m[this.getBlockIDAtSide(aSide)];
   }

   public final Block getBlockAtSideAndDistance(byte aSide, int aDistance) {
      return Block.field_71973_m[this.getBlockIDAtSideAndDistance(aSide, aDistance)];
   }

   public final byte getMetaIDOffset(int aX, int aY, int aZ) {
      return this.getMetaID(this.field_70329_l + aX, this.field_70330_m + aY, this.field_70327_n + aZ);
   }

   public final byte getMetaIDAtSide(byte aSide) {
      return this.getMetaIDAtSideAndDistance(aSide, 1);
   }

   public final byte getMetaIDAtSideAndDistance(byte aSide, int aDistance) {
      return this.getMetaID(this.getOffsetX(aSide, aDistance), this.getOffsetY(aSide, aDistance), this.getOffsetZ(aSide, aDistance));
   }

   public final byte getLightLevelOffset(int aX, int aY, int aZ) {
      return this.getLightLevel(this.field_70329_l + aX, this.field_70330_m + aY, this.field_70327_n + aZ);
   }

   public final byte getLightLevelAtSide(byte aSide) {
      return this.getLightLevelAtSideAndDistance(aSide, 1);
   }

   public final byte getLightLevelAtSideAndDistance(byte aSide, int aDistance) {
      return this.getLightLevel(this.getOffsetX(aSide, aDistance), this.getOffsetY(aSide, aDistance), this.getOffsetZ(aSide, aDistance));
   }

   public final boolean getOpacityOffset(int aX, int aY, int aZ) {
      return this.getOpacity(this.field_70329_l + aX, this.field_70330_m + aY, this.field_70327_n + aZ);
   }

   public final boolean getOpacityAtSide(byte aSide) {
      return this.getOpacityAtSideAndDistance(aSide, 1);
   }

   public final boolean getOpacityAtSideAndDistance(byte aSide, int aDistance) {
      return this.getOpacity(this.getOffsetX(aSide, aDistance), this.getOffsetY(aSide, aDistance), this.getOffsetZ(aSide, aDistance));
   }

   public final boolean getSkyOffset(int aX, int aY, int aZ) {
      return this.getSky(this.field_70329_l + aX, this.field_70330_m + aY, this.field_70327_n + aZ);
   }

   public final boolean getSkyAtSide(byte aSide) {
      return this.getSkyAtSideAndDistance(aSide, 1);
   }

   public final boolean getSkyAtSideAndDistance(byte aSide, int aDistance) {
      return this.getSky(this.getOffsetX(aSide, aDistance), this.getOffsetY(aSide, aDistance), this.getOffsetZ(aSide, aDistance));
   }

   public final boolean getAirOffset(int aX, int aY, int aZ) {
      return this.getAir(this.field_70329_l + aX, this.field_70330_m + aY, this.field_70327_n + aZ);
   }

   public final boolean getAirAtSide(byte aSide) {
      return this.getAirAtSideAndDistance(aSide, 1);
   }

   public final boolean getAirAtSideAndDistance(byte aSide, int aDistance) {
      return this.getAir(this.getOffsetX(aSide, aDistance), this.getOffsetY(aSide, aDistance), this.getOffsetZ(aSide, aDistance));
   }

   public final TileEntity getTileEntityOffset(int aX, int aY, int aZ) {
      return this.getTileEntity(this.field_70329_l + aX, this.field_70330_m + aY, this.field_70327_n + aZ);
   }

   public final TileEntity getTileEntityAtSideAndDistance(byte aSide, int aDistance) {
      return aDistance == 1?this.getTileEntityAtSide(aSide):this.getTileEntity(this.getOffsetX(aSide, aDistance), this.getOffsetY(aSide, aDistance), this.getOffsetZ(aSide, aDistance));
   }

   public final IInventory getIInventory(int aX, int aY, int aZ) {
      TileEntity tTileEntity = this.getTileEntity(aX, aY, aZ);
      return tTileEntity instanceof IInventory?(IInventory)tTileEntity:null;
   }

   public final IInventory getIInventoryOffset(int aX, int aY, int aZ) {
      TileEntity tTileEntity = this.getTileEntityOffset(aX, aY, aZ);
      return tTileEntity instanceof IInventory?(IInventory)tTileEntity:null;
   }

   public final IInventory getIInventoryAtSide(byte aSide) {
      TileEntity tTileEntity = this.getTileEntityAtSide(aSide);
      return tTileEntity instanceof IInventory?(IInventory)tTileEntity:null;
   }

   public final IInventory getIInventoryAtSideAndDistance(byte aSide, int aDistance) {
      TileEntity tTileEntity = this.getTileEntityAtSideAndDistance(aSide, aDistance);
      return tTileEntity instanceof IInventory?(IInventory)tTileEntity:null;
   }

   public final IFluidHandler getITankContainer(int aX, int aY, int aZ) {
      TileEntity tTileEntity = this.getTileEntity(aX, aY, aZ);
      return tTileEntity instanceof IFluidHandler?(IFluidHandler)tTileEntity:null;
   }

   public final IFluidHandler getITankContainerOffset(int aX, int aY, int aZ) {
      TileEntity tTileEntity = this.getTileEntityOffset(aX, aY, aZ);
      return tTileEntity instanceof IFluidHandler?(IFluidHandler)tTileEntity:null;
   }

   public final IFluidHandler getITankContainerAtSide(byte aSide) {
      TileEntity tTileEntity = this.getTileEntityAtSide(aSide);
      return tTileEntity instanceof IFluidHandler?(IFluidHandler)tTileEntity:null;
   }

   public final IFluidHandler getITankContainerAtSideAndDistance(byte aSide, int aDistance) {
      TileEntity tTileEntity = this.getTileEntityAtSideAndDistance(aSide, aDistance);
      return tTileEntity instanceof IFluidHandler?(IFluidHandler)tTileEntity:null;
   }

   public final IGregTechTileEntity getIGregTechTileEntity(int aX, int aY, int aZ) {
      TileEntity tTileEntity = this.getTileEntity(aX, aY, aZ);
      return tTileEntity instanceof IGregTechTileEntity?(IGregTechTileEntity)tTileEntity:null;
   }

   public final IGregTechTileEntity getIGregTechTileEntityOffset(int aX, int aY, int aZ) {
      TileEntity tTileEntity = this.getTileEntityOffset(aX, aY, aZ);
      return tTileEntity instanceof IGregTechTileEntity?(IGregTechTileEntity)tTileEntity:null;
   }

   public final IGregTechTileEntity getIGregTechTileEntityAtSide(byte aSide) {
      TileEntity tTileEntity = this.getTileEntityAtSide(aSide);
      return tTileEntity instanceof IGregTechTileEntity?(IGregTechTileEntity)tTileEntity:null;
   }

   public final IGregTechTileEntity getIGregTechTileEntityAtSideAndDistance(byte aSide, int aDistance) {
      TileEntity tTileEntity = this.getTileEntityAtSideAndDistance(aSide, aDistance);
      return tTileEntity instanceof IGregTechTileEntity?(IGregTechTileEntity)tTileEntity:null;
   }

   public final short getBlockID(int aX, int aY, int aZ) {
      return this.ignoreUnloadedChunks && (aX >> 4 != this.field_70329_l >> 4 || aZ >> 4 != this.field_70327_n >> 4) && !this.field_70331_k.func_72899_e(aX, aY, aZ)?0:(short)this.field_70331_k.func_72798_a(aX, aY, aZ);
   }

   public final byte getMetaID(int aX, int aY, int aZ) {
      return this.ignoreUnloadedChunks && (aX >> 4 != this.field_70329_l >> 4 || aZ >> 4 != this.field_70327_n >> 4) && !this.field_70331_k.func_72899_e(aX, aY, aZ)?0:(byte)this.field_70331_k.func_72805_g(aX, aY, aZ);
   }

   public final byte getLightLevel(int aX, int aY, int aZ) {
      return this.ignoreUnloadedChunks && (aX >> 4 != this.field_70329_l >> 4 || aZ >> 4 != this.field_70327_n >> 4) && !this.field_70331_k.func_72899_e(aX, aY, aZ)?0:(byte)((int)(this.field_70331_k.func_72801_o(aX, aY, aZ) * 15.0F));
   }

   public final boolean getSky(int aX, int aY, int aZ) {
      return this.ignoreUnloadedChunks && (aX >> 4 != this.field_70329_l >> 4 || aZ >> 4 != this.field_70327_n >> 4) && !this.field_70331_k.func_72899_e(aX, aY, aZ)?true:this.field_70331_k.func_72937_j(aX, aY, aZ);
   }

   public final boolean getOpacity(int aX, int aY, int aZ) {
      return this.ignoreUnloadedChunks && (aX >> 4 != this.field_70329_l >> 4 || aZ >> 4 != this.field_70327_n >> 4) && !this.field_70331_k.func_72899_e(aX, aY, aZ)?false:GT_Utility.isOpaqueBlock(this.field_70331_k, aX, aY, aZ);
   }

   public final boolean getAir(int aX, int aY, int aZ) {
      return this.ignoreUnloadedChunks && (aX >> 4 != this.field_70329_l >> 4 || aZ >> 4 != this.field_70327_n >> 4) && !this.field_70331_k.func_72899_e(aX, aY, aZ)?true:GT_Utility.isAirBlock(this.field_70331_k, aX, aY, aZ);
   }

   public final TileEntity getTileEntity(int aX, int aY, int aZ) {
      return this.ignoreUnloadedChunks && (aX >> 4 != this.field_70329_l >> 4 || aZ >> 4 != this.field_70327_n >> 4) && !this.field_70331_k.func_72899_e(aX, aY, aZ)?null:this.field_70331_k.func_72796_p(aX, aY, aZ);
   }

   public final TileEntity getTileEntityAtSide(byte aSide) {
      if(aSide >= 0 && aSide < 6 && this.mBufferedTileEntities[aSide] != this) {
         if(this.mBufferedTileEntities[aSide] == null) {
            this.mBufferedTileEntities[aSide] = this.getTileEntity(this.getOffsetX(aSide, 1), this.getOffsetY(aSide, 1), this.getOffsetZ(aSide, 1));
            if(this.mBufferedTileEntities[aSide] == null) {
               this.mBufferedTileEntities[aSide] = this;
               return null;
            } else {
               return this.mBufferedTileEntities[aSide];
            }
         } else if(this.mBufferedTileEntities[aSide].func_70320_p()) {
            this.mBufferedTileEntities[aSide] = null;
            return this.getTileEntityAtSide(aSide);
         } else {
            return this.mBufferedTileEntities[aSide].field_70329_l == this.getOffsetX(aSide, 1) && this.mBufferedTileEntities[aSide].field_70330_m == this.getOffsetY(aSide, 1) && this.mBufferedTileEntities[aSide].field_70327_n == this.getOffsetZ(aSide, 1)?this.mBufferedTileEntities[aSide]:null;
         }
      } else {
         return null;
      }
   }

   public void func_70312_q() {
      this.clearNullMarkersFromTileEntityBuffer();
      super.func_70312_q();
   }

   public void func_70313_j() {
      this.clearNullMarkersFromTileEntityBuffer();
      super.func_70313_j();
   }

   public void onChunkUnload() {
      this.clearNullMarkersFromTileEntityBuffer();
      super.onChunkUnload();
   }

   public final void onAdjacentBlockChange(int aX, int aY, int aZ) {
      this.clearNullMarkersFromTileEntityBuffer();
   }

   public final void sendBlockEvent(byte aID, byte aValue) {
      try {
         Iterator e = this.field_70331_k.field_73010_i.iterator();

         while(e.hasNext()) {
            Object tObject = e.next();
            if(!(tObject instanceof EntityPlayerMP)) {
               this.field_70331_k.func_72965_b(this.field_70329_l, this.field_70330_m, this.field_70327_n, this.getBlockIDOffset(0, 0, 0), aID, aValue);
               break;
            }

            EntityPlayerMP tPlayer = (EntityPlayerMP)tObject;
            if(Math.abs(tPlayer.field_70165_t - (double)this.field_70329_l) < 256.0D && Math.abs(tPlayer.field_70161_v - (double)this.field_70327_n) < 256.0D) {
               tPlayer.field_71135_a.func_72567_b(new Packet54PlayNoteBlock(this.field_70329_l, this.field_70330_m, this.field_70327_n, this.getBlockIDOffset(0, 0, 0), aID, aValue));
            }
         }
      } catch (Throwable var6) {
         this.field_70331_k.func_72965_b(this.field_70329_l, this.field_70330_m, this.field_70327_n, this.getBlockIDOffset(0, 0, 0), aID, aValue);
         if(GregTech_API.DEBUG_MODE) {
            var6.printStackTrace(GT_Log.err);
         }
      }

   }
}
