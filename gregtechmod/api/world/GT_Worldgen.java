package gregtechmod.api.world;

import gregtechmod.api.GregTech_API;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public abstract class GT_Worldgen {

   public final String mWorldGenName;


   public GT_Worldgen(String aName, boolean aDefault) {
      this.mWorldGenName = aName;
      if(GregTech_API.sWorldgenFile.get("worldgen", this.mWorldGenName, aDefault)) {
         GregTech_API.sWorldgenList.add(this);
      }

   }

   public boolean executeWorldgen(World aWorld, Random aRandom, String aBiome, int aDimensionType, int aChunkX, int aChunkZ, IChunkProvider aChunkGenerator, IChunkProvider aChunkProvider) {
      return false;
   }

   public boolean executeCavegen(World aWorld, Random aRandom, String aBiome, int aDimensionType, int aChunkX, int aChunkZ, IChunkProvider aChunkGenerator, IChunkProvider aChunkProvider) {
      return false;
   }
}
