package gregtechmod.common;

import gregtechmod.common.GT_IteratorRandom;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.logging.ILogAgent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class GT_DummyWorld extends World {

   public GT_IteratorRandom mRandom;
   public ItemStack mLastSetBlock;


   public GT_DummyWorld(ISaveHandler par1iSaveHandler, String par2Str, WorldProvider par3WorldProvider, WorldSettings par4WorldSettings, Profiler par5Profiler) {
      super(par1iSaveHandler, par2Str, par4WorldSettings, par3WorldProvider, par5Profiler, (ILogAgent)null);
      this.mRandom = new GT_IteratorRandom();
      this.mLastSetBlock = null;
      this.field_73012_v = this.mRandom;
   }

   public GT_DummyWorld() {
      this(new ISaveHandler() {
         public void func_75755_a(WorldInfo var1, NBTTagCompound var2) {}
         public void func_75761_a(WorldInfo var1) {}
         public WorldInfo func_75757_d() {
            return null;
         }
         public IPlayerFileData func_75756_e() {
            return null;
         }
         public File func_75758_b(String var1) {
            return null;
         }
         public IChunkLoader func_75763_a(WorldProvider var1) {
            return null;
         }
         public void func_75759_a() {}
         public void func_75762_c() throws MinecraftException {}
         public String func_75760_g() {
            return null;
         }
      }, "DUMMY_DIMENSION", new WorldProvider() {
         public String func_80007_l() {
            return "DUMMY_DIMENSION";
         }
      }, new WorldSettings(new WorldInfo(new NBTTagCompound())), new Profiler());
   }

   protected IChunkProvider func_72970_h() {
      return null;
   }

   public Entity func_73045_a(int aEntityID) {
      return null;
   }

   public boolean func_72832_d(int aX, int aY, int aZ, int aID, int aMeta, int aFlags) {
      this.mLastSetBlock = new ItemStack(aID, 1, aMeta);
      return true;
   }

   public BiomeGenBase func_72807_a(int aX, int aZ) {
      return aX >= 16 && aZ >= 16 && aX < 32 && aZ < 32?BiomeGenBase.field_76772_c:BiomeGenBase.field_76771_b;
   }

   public int func_72883_k(int aX, int aY, int aZ) {
      return 10;
   }

   public int func_72798_a(int aX, int aY, int aZ) {
      return aX >= 16 && aZ >= 16 && aX < 32 && aZ < 32?(aY == 64?Block.field_71980_u.field_71990_ca:0):0;
   }

   public int func_72805_g(int aX, int aY, int aZ) {
      return 0;
   }

   public boolean func_72937_j(int aX, int aY, int aZ) {
      return aX >= 16 && aZ >= 16 && aX < 32 && aZ < 32?aY > 64:true;
   }
}
