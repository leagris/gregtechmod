package gregtechmod.api.events;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.Cancelable;
import net.minecraftforge.event.world.WorldEvent;

@Cancelable
public class GT_ScannerEvent extends WorldEvent {

   public final EntityPlayer mPlayer;
   public final int mX;
   public final int mY;
   public final int mZ;
   public final int mScanLevel;
   public final ArrayList<String> mList;
   public final byte mSide;
   public final float mClickX;
   public final float mClickY;
   public final float mClickZ;
   public final TileEntity mTileEntity;
   public final Block mBlock;
   public int mEUCost = 0;


   public GT_ScannerEvent(World aWorld, EntityPlayer aPlayer, int aX, int aY, int aZ, byte aSide, int aScanLevel, Block aBlock, TileEntity aTileEntity, ArrayList<String> aList, float aClickX, float aClickY, float aClickZ) {
      super(aWorld);
      this.mPlayer = aPlayer;
      this.mScanLevel = aScanLevel;
      this.mTileEntity = aTileEntity;
      this.mBlock = aBlock;
      this.mList = aList;
      this.mSide = aSide;
      this.mX = aX;
      this.mY = aY;
      this.mZ = aZ;
      this.mClickX = aClickX;
      this.mClickY = aClickY;
      this.mClickZ = aClickZ;
   }
}
