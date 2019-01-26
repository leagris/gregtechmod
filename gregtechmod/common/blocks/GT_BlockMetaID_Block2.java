package gregtechmod.common.blocks;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GT_BlockMetaID_Block2 extends Block {

   public static Icon[] mIcons = new Icon[52];


   public GT_BlockMetaID_Block2(int aID) {
      super(aID, Material.field_76243_f);
      this.func_71848_c(3.0F);
      this.func_71894_b(10.0F);
      this.func_71864_b("BlockMetaID_Block2");
      LanguageRegistry.addName(this, this.func_71917_a());
      String[] tRegionalNameList = new String[]{"Block of Lead", "Block of Electrum", "Block of Zinc", "Block of Olivine", "Block of Green Sapphire", "Block of Platinum", "Block of Tungsten", "Block of Nickel", "Tungstensteel Block", "Iridium Reinforced Tungstensteel Block", "Block of Invar", "Block of Osmium", "Block of Iridium", "Bronze Plated Bricks", "Block of Yellow Garnet", "Block of Red Garnet"};

      int i;
      for(i = 0; i < 16; ++i) {
         GT_LanguageManager.addStringLocalization(this.func_71917_a() + "." + i + ".name", tRegionalNameList[i]);
      }

      this.func_71884_a(Block.field_71977_i);
      this.func_71849_a(GregTech_API.TAB_GREGTECH);

      for(i = 0; i < 16; ++i) {
         MinecraftForge.setBlockHarvestLevel(this, i, "pickaxe", 2);
      }

   }

   @SideOnly(Side.CLIENT)
   public void func_94332_a(IconRegister par1IconRegister) {
      for(int i = 0; i < mIcons.length; ++i) {
         mIcons[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":this.func_71917_a() + "/" + i));
      }

      if(GregTech_API.sPostloadFinished) {
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateLead"), mIcons[0]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateElectrum"), mIcons[1]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateZinc"), mIcons[2]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateOlivine"), mIcons[3]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGreenSapphire"), mIcons[4]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("platePlatinum"), mIcons[5]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateTungsten"), mIcons[6]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateNickel"), mIcons[7]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateTungstenSteel"), mIcons[8]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateAlloyIridium"), mIcons[9]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateInvar"), mIcons[10]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateOsmium"), mIcons[11]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateIridium"), mIcons[12]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateDenseBronze"), mIcons[13]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGarnetYellow"), mIcons[14]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGarnetRed"), mIcons[15]);
      }

   }

   public boolean isBeaconBase(World aWorld, int aX, int aY, int aZ, int beaconX, int beaconY, int beaconZ) {
      return !GregTech_API.isMachineBlock(this, aWorld.func_72805_g(aX, aY, aZ));
   }

   public void func_71852_a(World aWorld, int aX, int aY, int aZ, int par5, int par6) {
      if(GregTech_API.isMachineBlock(this, aWorld.func_72805_g(aX, aY, aZ))) {
         GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
      }

   }

   public void func_71861_g(World aWorld, int aX, int aY, int aZ) {
      if(GregTech_API.isMachineBlock(this, aWorld.func_72805_g(aX, aY, aZ))) {
         GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
      }

   }

   public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
      return false;
   }

   public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
      if(world == null) {
         return 0.0F;
      } else {
         Integer tMeta = Integer.valueOf(world.func_72805_g(x, y, z));
         if(tMeta == null) {
            tMeta = Integer.valueOf(0);
         }

         return tMeta.intValue() == 0?60.0F:(tMeta.intValue() == 1?30.0F:(tMeta.intValue() == 2?30.0F:(tMeta.intValue() == 3?30.0F:(tMeta.intValue() == 4?30.0F:(tMeta.intValue() == 5?30.0F:(tMeta.intValue() == 6?100.0F:(tMeta.intValue() == 7?45.0F:(tMeta.intValue() == 8?300.0F:(tMeta.intValue() == 9?400.0F:(tMeta.intValue() == 10?60.0F:(tMeta.intValue() == 11?900.0F:(tMeta.intValue() == 12?600.0F:(tMeta.intValue() == 13?60.0F:(tMeta.intValue() == 14?30.0F:(tMeta.intValue() == 15?30.0F:super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ))))))))))))))));
      }
   }

   public float func_71934_m(World world, int x, int y, int z) {
      if(world == null) {
         return 0.0F;
      } else {
         Integer tMeta = Integer.valueOf(world.func_72805_g(x, y, z));
         if(tMeta == null) {
            tMeta = Integer.valueOf(0);
         }

         return tMeta.intValue() == 8?100.0F:(tMeta.intValue() == 9?200.0F:(tMeta.intValue() == 13?10.0F:3.0F));
      }
   }

   public Icon func_71895_b(IBlockAccess aWorld, int xCoord, int yCoord, int zCoord, int aSide) {
      int tMeta = aWorld.func_72805_g(xCoord, yCoord, zCoord);
      if((tMeta == 8 || tMeta == 9 || tMeta == 13) && (xCoord != 0 || yCoord != 0 || zCoord != 0) && GT_BlockMetaID_Block.mConnectedMachineTextures) {
         int tStartIndex = tMeta == 8?16:(tMeta == 9?28:40);
         boolean[] tConnectedSides = new boolean[]{aWorld.func_72798_a(xCoord, yCoord - 1, zCoord) == this.field_71990_ca && aWorld.func_72805_g(xCoord, yCoord - 1, zCoord) == tMeta, aWorld.func_72798_a(xCoord, yCoord + 1, zCoord) == this.field_71990_ca && aWorld.func_72805_g(xCoord, yCoord + 1, zCoord) == tMeta, aWorld.func_72798_a(xCoord + 1, yCoord, zCoord) == this.field_71990_ca && aWorld.func_72805_g(xCoord + 1, yCoord, zCoord) == tMeta, aWorld.func_72798_a(xCoord, yCoord, zCoord + 1) == this.field_71990_ca && aWorld.func_72805_g(xCoord, yCoord, zCoord + 1) == tMeta, aWorld.func_72798_a(xCoord - 1, yCoord, zCoord) == this.field_71990_ca && aWorld.func_72805_g(xCoord - 1, yCoord, zCoord) == tMeta, aWorld.func_72798_a(xCoord, yCoord, zCoord - 1) == this.field_71990_ca && aWorld.func_72805_g(xCoord, yCoord, zCoord - 1) == tMeta};
         switch(aSide) {
         case 0:
            if(tConnectedSides[0]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 2];
            } else if(tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 3];
            } else if(tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 4];
            } else if(tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
               return mIcons[tStartIndex + 5];
            } else if(!tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 8];
            } else if(tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 9];
            } else if(tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
               return mIcons[tStartIndex + 10];
            } else if(!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
               return mIcons[tStartIndex + 11];
            } else if(!tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides[4] && !tConnectedSides[2]) {
               return mIcons[tStartIndex + 1];
            } else if(!tConnectedSides[5] && !tConnectedSides[3]) {
               return mIcons[tStartIndex + 0];
            }
         case 1:
            if(tConnectedSides[1]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 2];
            } else if(tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 3];
            } else if(tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 4];
            } else if(tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
               return mIcons[tStartIndex + 5];
            } else if(!tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 8];
            } else if(tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
               return mIcons[tStartIndex + 9];
            } else if(tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
               return mIcons[tStartIndex + 10];
            } else if(!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
               return mIcons[tStartIndex + 11];
            } else if(!tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides[2] && !tConnectedSides[4]) {
               return mIcons[tStartIndex + 1];
            } else if(!tConnectedSides[3] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 0];
            }
         case 2:
            if(tConnectedSides[5]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 2];
            } else if(tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 5];
            } else if(tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 4];
            } else if(tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 3];
            } else if(!tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 11];
            } else if(tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 10];
            } else if(tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 9];
            } else if(!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 8];
            } else if(!tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides[2] && !tConnectedSides[4]) {
               return mIcons[tStartIndex + 1];
            } else if(!tConnectedSides[0] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 0];
            }
         case 3:
            if(tConnectedSides[3]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 4];
            } else if(tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 5];
            } else if(tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 2];
            } else if(tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 3];
            } else if(!tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 10];
            } else if(tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
               return mIcons[tStartIndex + 11];
            } else if(tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 8];
            } else if(!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 9];
            } else if(!tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides[2] && !tConnectedSides[4]) {
               return mIcons[tStartIndex + 1];
            } else if(!tConnectedSides[0] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 0];
            }
         case 4:
            if(tConnectedSides[4]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 5];
            } else if(tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 4];
            } else if(tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 3];
            } else if(tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 2];
            } else if(!tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 10];
            } else if(tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 9];
            } else if(tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 8];
            } else if(!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 11];
            } else if(!tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides[0] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 0];
            } else if(!tConnectedSides[3] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 1];
            }
         case 5:
            if(tConnectedSides[2]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 5];
            } else if(tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 2];
            } else if(tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 3];
            } else if(tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 4];
            } else if(!tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 11];
            } else if(tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
               return mIcons[tStartIndex + 8];
            } else if(tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 9];
            } else if(!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 10];
            } else if(!tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides[0] && !tConnectedSides[1]) {
               return mIcons[tStartIndex + 0];
            } else if(!tConnectedSides[3] && !tConnectedSides[5]) {
               return mIcons[tStartIndex + 1];
            }
         default:
            return mIcons[tStartIndex + 7];
         }
      } else {
         return this.func_71858_a(aSide, tMeta);
      }
   }

   public Icon func_71858_a(int aSide, int aMeta) {
      return aMeta >= 0 && aMeta < mIcons.length?mIcons[aMeta]:null;
   }

   public int func_71899_b(int par1) {
      return par1;
   }

   public int func_71873_h(World par1World, int par2, int par3, int par4) {
      return par1World.func_72805_g(par2, par3, par4);
   }

   public int func_71925_a(Random par1Random) {
      return 1;
   }

   public int func_71885_a(int par1, Random par2Random, int par3) {
      return this.field_71990_ca;
   }

   @SideOnly(Side.CLIENT)
   public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List) {
      for(int i = 0; i < 16; ++i) {
         par3List.add(new ItemStack(par1, 1, i));
      }

   }

}
