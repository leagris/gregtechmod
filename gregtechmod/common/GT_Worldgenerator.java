package gregtechmod.common;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.world.GT_Worldgen;
import gregtechmod.common.GT_MinableOreGenerator;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Post;

public class GT_Worldgenerator implements IWorldGenerator {

   public static boolean sAsteroids = true;
   public static boolean[] sGeneratedOres = new boolean[]{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};


   public GT_Worldgenerator() {
      GameRegistry.registerWorldGenerator(this);
   }

   @ForgeSubscribe
   public void receiveWorldgenEvent(Post aEvent) {
      this.generate(aEvent.rand, aEvent.chunkX, aEvent.chunkZ, aEvent.world, aEvent.chunkProvider, aEvent.chunkProvider);
   }

   public void generate(Random aRandom, int aX, int aZ, World aWorld, IChunkProvider aChunkGenerator, IChunkProvider aChunkProvider) {
      aRandom = new Random((long)aRandom.nextInt());
      aX *= 16;
      aZ *= 16;
      String tBiome = aWorld.func_72807_a(aX + 8, aZ + 8).field_76791_y;
      boolean tDimensionType = false;
      if(tBiome == null) {
         tBiome = BiomeGenBase.field_76772_c.field_76791_y;
      }

      byte tDimensionType1;
      if(!tBiome.equals(BiomeGenBase.field_76778_j.field_76791_y) && aWorld.field_73011_w.field_76574_g != -1 && !(aChunkGenerator instanceof ChunkProviderHell)) {
         if(!tBiome.equals(BiomeGenBase.field_76779_k.field_76791_y) && aWorld.field_73011_w.field_76574_g != 1 && !(aChunkGenerator instanceof ChunkProviderEnd)) {
            tDimensionType1 = 0;
         } else {
            generateEnd(aWorld, aRandom, aX, aZ, aChunkGenerator, aChunkProvider);
            tDimensionType1 = 1;
         }
      } else {
         tDimensionType1 = -1;
      }

      Iterator tChunk = GregTech_API.sWorldgenList.iterator();

      while(tChunk.hasNext()) {
         GT_Worldgen tWorldGen = (GT_Worldgen)tChunk.next();

         try {
            tWorldGen.executeWorldgen(aWorld, aRandom, tBiome, tDimensionType1, aX, aZ, aChunkGenerator, aChunkProvider);
         } catch (Throwable var12) {
            var12.printStackTrace(GT_Log.err);
         }
      }

      Chunk tChunk1 = aWorld.func_72938_d(aX, aZ);
      if(tChunk1 != null) {
         tChunk1.field_76643_l = true;
      }

   }

   private static void generateEnd(World aWorld, Random aRandom, int aX, int aZ, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      int i;
      if(sAsteroids && aRandom.nextInt(100) == 0) {
         i = 10 + aRandom.nextInt(237);
         int i1;
         if(aRandom.nextInt(25) == 0) {
            (new GT_MinableOreGenerator(Block.field_72082_bJ.field_71990_ca, 0, 100 + aRandom.nextInt(101), true, 0)).func_76484_a(aWorld, aRandom, aX + aRandom.nextInt(16), i, aZ + aRandom.nextInt(16));
            if(sGeneratedOres[9]) {
               for(i1 = 0; i1 < 5; ++i1) {
                  (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 9, 16, true, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), i + aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
               }
            }

            if(sGeneratedOres[10]) {
               for(i1 = 0; i1 < 2; ++i1) {
                  (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 10, 4, true, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), i + aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
               }
            }

            if(sGeneratedOres[11]) {
               for(i1 = 0; i1 < 7; ++i1) {
                  (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 11, 8, true, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), i + aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
               }
            }

            if(sGeneratedOres[12]) {
               for(i1 = 0; i1 < 12; ++i1) {
                  (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 12, 16, true, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), i + aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
               }
            }
         }

         for(i1 = 0; i1 < 5; ++i1) {
            (new GT_MinableOreGenerator(Block.field_72082_bJ.field_71990_ca, 0, 30 + aRandom.nextInt(31), true, 0)).func_76484_a(aWorld, aRandom, aX + aRandom.nextInt(16), i + aRandom.nextInt(51) - 25, aZ + aRandom.nextInt(16));
         }

         if(sGeneratedOres[9]) {
            for(i1 = 0; i1 < 5; ++i1) {
               (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 9, 12, true, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), i + aRandom.nextInt(41) - 20, aZ - 8 + aRandom.nextInt(24));
            }
         }

         if(sGeneratedOres[10]) {
            for(i1 = 0; i1 < 1; ++i1) {
               (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 10, 4, true, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), i + aRandom.nextInt(41) - 20, aZ - 8 + aRandom.nextInt(24));
            }
         }

         if(sGeneratedOres[11]) {
            for(i1 = 0; i1 < 3; ++i1) {
               (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 11, 8, true, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), i + aRandom.nextInt(41) - 20, aZ - 8 + aRandom.nextInt(24));
            }
         }
      }

      if(sGeneratedOres[9]) {
         for(i = 0; i < 4; ++i) {
            (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 9, 16, false, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
         }
      }

      if(sGeneratedOres[10]) {
         for(i = 0; i < 1; ++i) {
            (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 10, 4, false, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
         }
      }

      if(sGeneratedOres[11]) {
         for(i = 0; i < 5; ++i) {
            (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 11, 8, false, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
         }
      }

      if(sGeneratedOres[12]) {
         for(i = 0; i < 8; ++i) {
            (new GT_MinableOreGenerator(GregTech_API.sBlockList[2].field_71990_ca, 12, 16, false, Block.field_72082_bJ.field_71990_ca)).func_76484_a(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
         }
      }

   }

}
