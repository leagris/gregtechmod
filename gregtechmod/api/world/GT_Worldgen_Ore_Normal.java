package gregtechmod.api.world;

import gregtechmod.api.world.GT_Worldgen_Ore;
import java.util.Collection;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class GT_Worldgen_Ore_Normal extends GT_Worldgen_Ore {

   public GT_Worldgen_Ore_Normal(String aName, boolean aDefault, int aBlockID, int aBlockMeta, int aDimensionType, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid) {
      super(aName, aDefault, aBlockID, aBlockMeta, aDimensionType, aAmount, aSize, aProbability, aMinY, aMaxY, aBiomeList, aAllowToGenerateinVoid);
   }

   public boolean executeWorldgen(World aWorld, Random aRandom, String aBiome, int aDimensionType, int aChunkX, int aChunkZ, IChunkProvider aChunkGenerator, IChunkProvider aChunkProvider) {
      if(aDimensionType == this.mDimensionType && (this.mBiomeList.isEmpty() || this.mBiomeList.contains(aBiome)) && (this.mProbability <= 1 || aRandom.nextInt(this.mProbability) == 0)) {
         for(int i = 0; i < this.mAmount; ++i) {
            int tX = aChunkX + aRandom.nextInt(16);
            int tY = this.mMinY + aRandom.nextInt(this.mMaxY - this.mMinY);
            int tZ = aChunkZ + aRandom.nextInt(16);
            if(this.mAllowToGenerateinVoid || aWorld.func_72798_a(tX, tY, tZ) != 0) {
               float var6 = aRandom.nextFloat() * 3.1415927F;
               double var7 = (double)((float)(tX + 8) + MathHelper.func_76126_a(var6) * (float)this.mSize / 8.0F);
               double var9 = (double)((float)(tX + 8) - MathHelper.func_76126_a(var6) * (float)this.mSize / 8.0F);
               double var11 = (double)((float)(tZ + 8) + MathHelper.func_76134_b(var6) * (float)this.mSize / 8.0F);
               double var13 = (double)((float)(tZ + 8) - MathHelper.func_76134_b(var6) * (float)this.mSize / 8.0F);
               double var15 = (double)(tY + aRandom.nextInt(3) - 2);
               double var17 = (double)(tY + aRandom.nextInt(3) - 2);

               for(int var19 = 0; var19 <= this.mSize; ++var19) {
                  double var20 = var7 + (var9 - var7) * (double)var19 / (double)this.mSize;
                  double var22 = var15 + (var17 - var15) * (double)var19 / (double)this.mSize;
                  double var24 = var11 + (var13 - var11) * (double)var19 / (double)this.mSize;
                  double var26 = aRandom.nextDouble() * (double)this.mSize / 16.0D;
                  double var28 = (double)(MathHelper.func_76126_a((float)var19 * 3.1415927F / (float)this.mSize) + 1.0F) * var26 + 1.0D;
                  double var30 = (double)(MathHelper.func_76126_a((float)var19 * 3.1415927F / (float)this.mSize) + 1.0F) * var26 + 1.0D;
                  int var32 = MathHelper.func_76128_c(var20 - var28 / 2.0D);
                  int var33 = MathHelper.func_76128_c(var22 - var30 / 2.0D);
                  int var34 = MathHelper.func_76128_c(var24 - var28 / 2.0D);
                  int var35 = MathHelper.func_76128_c(var20 + var28 / 2.0D);
                  int var36 = MathHelper.func_76128_c(var22 + var30 / 2.0D);
                  int var37 = MathHelper.func_76128_c(var24 + var28 / 2.0D);

                  for(int var38 = var32; var38 <= var35; ++var38) {
                     double var39 = ((double)var38 + 0.5D - var20) / (var28 / 2.0D);
                     if(var39 * var39 < 1.0D) {
                        for(int var41 = var33; var41 <= var36; ++var41) {
                           double var42 = ((double)var41 + 0.5D - var22) / (var30 / 2.0D);
                           if(var39 * var39 + var42 * var42 < 1.0D) {
                              for(int var44 = var34; var44 <= var37; ++var44) {
                                 double var45 = ((double)var44 + 0.5D - var24) / (var28 / 2.0D);
                                 Block block = Block.field_71973_m[aWorld.func_72798_a(var38, var41, var44)];
                                 if(var39 * var39 + var42 * var42 + var45 * var45 < 1.0D && (this.mAllowToGenerateinVoid && aWorld.func_72798_a(var38, var41, var44) == 0 || block != null && (block.isGenMineableReplaceable(aWorld, var38, var41, var44, Block.field_71981_t.field_71990_ca) || block.isGenMineableReplaceable(aWorld, var38, var41, var44, Block.field_72082_bJ.field_71990_ca) || block.isGenMineableReplaceable(aWorld, var38, var41, var44, Block.field_72012_bb.field_71990_ca)))) {
                                    aWorld.func_72832_d(var38, var41, var44, this.mBlockID, this.mBlockMeta, 0);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }
}
