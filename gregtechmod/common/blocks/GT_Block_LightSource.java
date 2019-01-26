package gregtechmod.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_LightSource;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class GT_Block_LightSource extends BlockContainer {

   public GT_Block_LightSource(int aID) {
      super(aID, Material.field_76249_a);
      this.func_71864_b("GT_TransparentTileEntity");
      this.func_71900_a(1.0F);
      this.func_71868_h(1);
      this.func_71905_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @SideOnly(Side.CLIENT)
   public void func_94332_a(IconRegister par1IconRegister) {
      this.field_94336_cN = par1IconRegister.func_94245_a("gregtech_addon:void");
   }

   public AxisAlignedBB func_71872_e(World par1World, int par2, int par3, int par4) {
      return null;
   }

   public boolean func_71913_a(int par1, boolean par2) {
      return par2 && par1 == 0;
   }

   public int func_71856_s_() {
      return 1;
   }

   public boolean func_71926_d() {
      return false;
   }

   public boolean func_71886_c() {
      return false;
   }

   public boolean isAirBlock(World world, int x, int y, int z) {
      return true;
   }

   public int func_71899_b(int par1) {
      return 0;
   }

   public int func_71925_a(Random par1Random) {
      return 0;
   }

   public int func_71885_a(int par1, Random par2Random, int par3) {
      return 0;
   }

   public TileEntity func_72274_a(World aWorld) {
      return new GT_TileEntity_LightSource();
   }

   @SideOnly(Side.CLIENT)
   public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List) {}
}
