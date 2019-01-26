package gregtechmod.common.blocks;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GT_BlockMetaID_Stone1 extends Block {

   public static final int Metablockcount = 16;
   public static Icon[] mIcons = new Icon[16];


   public GT_BlockMetaID_Stone1(int aID) {
      super(aID, Material.field_76246_e);
      this.func_71894_b(60.0F);
      this.func_71864_b("BlockMetaID_Stone1");
      LanguageRegistry.addName(this, this.func_71917_a());
      String[] tRegionalNameList = new String[]{"Black Granite", "Black Granite Cobblestone", "Mossy Black Granite Cobblestone", "Black Granite Bricks", "Cracked Black Granite Bricks", "Mossy Black Granite Bricks", "Chiseled Black Granite", "Smooth Black Granite", "Red Granite", "Red Granite Cobblestone", "Mossy Red Granite Cobblestone", "Red Granite Bricks", "Cracked Red Granite Bricks", "Mossy Red Granite Bricks", "Chiseled Red Granite", "Smooth Red Granite"};

      int i;
      for(i = 0; i < 16; ++i) {
         GT_LanguageManager.addStringLocalization(this.func_71917_a() + "." + i + ".name", tRegionalNameList[i]);
      }

      this.func_71884_a(Block.field_71976_h);
      this.func_71849_a(GregTech_API.TAB_GREGTECH);

      for(i = 0; i < 16; ++i) {
         MinecraftForge.setBlockHarvestLevel(this, i, "pickaxe", 3);
      }

   }

   public float func_71934_m(World par1World, int par2, int par3, int par4) {
      return this.field_71989_cb = Block.field_71981_t.field_71989_cb * 3.0F;
   }

   @SideOnly(Side.CLIENT)
   public void func_94332_a(IconRegister par1IconRegister) {
      for(int i = 0; i < mIcons.length; ++i) {
         mIcons[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":this.func_71917_a() + "/" + i));
      }

      if(GregTech_API.sPostloadFinished) {
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGraniteBlack"), mIcons[7]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGraniteRed"), mIcons[15]);
      }

   }

   public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
      return world.func_72805_g(x, y, z) % 8 < 3;
   }

   public Icon func_71895_b(IBlockAccess aWorld, int xCoord, int yCoord, int zCoord, int aSide) {
      return this.func_71858_a(aSide, aWorld.func_72805_g(xCoord, yCoord, zCoord));
   }

   public Icon func_71858_a(int aSide, int aMeta) {
      return aMeta >= 0 && aMeta < mIcons.length?mIcons[aMeta]:null;
   }

   public int func_71899_b(int par1) {
      return par1 % 8 == 0?par1 + 1:par1;
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
