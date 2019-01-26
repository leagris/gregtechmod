package gregtechmod.api.world;

import gregtechmod.api.world.GT_Worldgen_Ore;
import java.util.Collection;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class GT_Worldgen_Ore_SingleBlock extends GT_Worldgen_Ore {

   public GT_Worldgen_Ore_SingleBlock(String aName, boolean aDefault, int aBlockID, int aBlockMeta, int aDimensionType, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid) {
      super(aName, aDefault, aBlockID, aBlockMeta, aDimensionType, aAmount, aSize, aProbability, aMinY, aMaxY, aBiomeList, aAllowToGenerateinVoid);
   }

   public boolean executeWorldgen(World aWorld, Random aRandom, String aBiome, int aDimensionType, int aChunkX, int aChunkZ, IChunkProvider aChunkGenerator, IChunkProvider aChunkProvider) {
      if(aDimensionType == this.mDimensionType && (this.mBiomeList.isEmpty() || this.mBiomeList.contains(aBiome)) && (this.mProbability <= 1 || aRandom.nextInt(this.mProbability) == 0)) {
         for(int i = 0; i < this.mAmount; ++i) {
            int tX = aChunkX + aRandom.nextInt(16);
            int tY = this.mMinY + aRandom.nextInt(this.mMaxY - this.mMinY);
            int tZ = aChunkZ + aRandom.nextInt(16);
            Block tBlock = Block.field_71973_m[aWorld.func_72798_a(tX, tY, tZ)];
            if(this.mAllowToGenerateinVoid && aWorld.func_72798_a(tX, tY, tZ) == 0 || tBlock != null && (tBlock.isGenMineableReplaceable(aWorld, tX, tY, tZ, Block.field_71981_t.field_71990_ca) || tBlock.isGenMineableReplaceable(aWorld, tX, tY, tZ, Block.field_72082_bJ.field_71990_ca) || tBlock.isGenMineableReplaceable(aWorld, tX, tY, tZ, Block.field_72012_bb.field_71990_ca))) {
               aWorld.func_72832_d(tX, tY, tZ, this.mBlockID, this.mBlockMeta, 0);
            }
         }

         return true;
      } else {
         return false;
      }
   }
}
