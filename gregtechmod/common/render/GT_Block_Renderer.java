package gregtechmod.common.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.common.blocks.GT_BlockMetaID_Machine;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class GT_Block_Renderer implements ISimpleBlockRenderingHandler {

   public final int mRenderID = RenderingRegistry.getNextAvailableRenderId();
   public static GT_Block_Renderer instance;


   public GT_Block_Renderer() {
      instance = this;
      RenderingRegistry.registerBlockHandler(this);
   }

   public void renderInventoryBlock(Block aBlock, int aMeta, int aModelID, RenderBlocks aRenderer) {
      if(aMeta >= 0 && aMeta < 16) {
         renderNormalInventoryBlock(aBlock, aMeta, aRenderer);
      } else if(aMeta > 15 && aMeta < 32766 && GregTech_API.mMetaTileList[aMeta] != null) {
         switch(GregTech_API.mMetaTileList[aMeta].getTileEntityBaseType()) {
         case 1:
         case 2:
         case 3:
         default:
            renderNormalInventoryBlock(aBlock, aMeta, aRenderer);
         }
      }
   }

   private static void renderNormalInventoryBlock(Block aBlock, int aMeta, RenderBlocks aRenderer) {
      aBlock.func_71919_f();
      aRenderer.func_83018_a(aBlock);
      GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
      float tR = 1.0F;
      float tG = 1.0F;
      float tB = 1.0F;
      Tessellator.field_78398_a.func_78382_b();
      Tessellator.field_78398_a.func_78375_b(0.0F, -1.0F, 0.0F);
      renderNegativeYFacing((IBlockAccess)null, aRenderer, aBlock, 0, 0, 0, aRenderer.func_94165_a(aBlock, 0, aMeta), true, tR, tG, tB, false);
      Tessellator.field_78398_a.func_78381_a();
      Tessellator.field_78398_a.func_78382_b();
      Tessellator.field_78398_a.func_78375_b(0.0F, 1.0F, 0.0F);
      renderPositiveYFacing((IBlockAccess)null, aRenderer, aBlock, 0, 0, 0, aRenderer.func_94165_a(aBlock, 1, aMeta), true, tR, tG, tB, false);
      Tessellator.field_78398_a.func_78381_a();
      Tessellator.field_78398_a.func_78382_b();
      Tessellator.field_78398_a.func_78375_b(0.0F, 0.0F, -1.0F);
      renderNegativeZFacing((IBlockAccess)null, aRenderer, aBlock, 0, 0, 0, aRenderer.func_94165_a(aBlock, 2, aMeta), true, tR, tG, tB, false);
      Tessellator.field_78398_a.func_78381_a();
      Tessellator.field_78398_a.func_78382_b();
      Tessellator.field_78398_a.func_78375_b(0.0F, 0.0F, 1.0F);
      renderPositiveZFacing((IBlockAccess)null, aRenderer, aBlock, 0, 0, 0, aRenderer.func_94165_a(aBlock, 3, aMeta), true, tR, tG, tB, false);
      Tessellator.field_78398_a.func_78381_a();
      Tessellator.field_78398_a.func_78382_b();
      Tessellator.field_78398_a.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderNegativeXFacing((IBlockAccess)null, aRenderer, aBlock, 0, 0, 0, aRenderer.func_94165_a(aBlock, 4, aMeta), true, tR, tG, tB, false);
      Tessellator.field_78398_a.func_78381_a();
      Tessellator.field_78398_a.func_78382_b();
      Tessellator.field_78398_a.func_78375_b(1.0F, 0.0F, 0.0F);
      renderPositiveXFacing((IBlockAccess)null, aRenderer, aBlock, 0, 0, 0, aRenderer.func_94165_a(aBlock, 5, aMeta), true, tR, tG, tB, false);
      Tessellator.field_78398_a.func_78381_a();
      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
   }

   public boolean renderWorldBlock(IBlockAccess aWorld, int aX, int aY, int aZ, Block aBlock, int aModelID, RenderBlocks aRenderer) {
      int aMeta = aWorld.func_72805_g(aX, aY, aZ);
      switch(aMeta) {
      case 1:
         return renderPipeBlock(aWorld, aX, aY, aZ, aBlock, aRenderer);
      case 2:
         return renderPipeBlock(aWorld, aX, aY, aZ, aBlock, aRenderer);
      case 3:
         return renderPipeBlock(aWorld, aX, aY, aZ, aBlock, aRenderer);
      default:
         return renderStandardBlock(aWorld, aX, aY, aZ, aBlock, aRenderer, false);
      }
   }

   public static boolean renderStandardBlock(IBlockAccess aWorld, int aX, int aY, int aZ, Block aBlock, RenderBlocks aRenderer, boolean aRenderInside) {
      aBlock.func_71905_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      aRenderer.func_83018_a(aBlock);
      int tC = aBlock.func_71920_b(aWorld, aX, aY, aZ);
      float tR = (float)(tC >> 16 & 255) / 255.0F;
      float tG = (float)(tC >> 8 & 255) / 255.0F;
      float tB = (float)(tC & 255) / 255.0F;
      renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aBlock.func_71895_b(aWorld, aX, aY, aZ, 0), true, tR, tG, tB, aRenderInside);
      renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aBlock.func_71895_b(aWorld, aX, aY, aZ, 1), true, tR, tG, tB, aRenderInside);
      renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aBlock.func_71895_b(aWorld, aX, aY, aZ, 2), true, tR, tG, tB, aRenderInside);
      renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aBlock.func_71895_b(aWorld, aX, aY, aZ, 3), true, tR, tG, tB, aRenderInside);
      renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aBlock.func_71895_b(aWorld, aX, aY, aZ, 4), true, tR, tG, tB, aRenderInside);
      renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aBlock.func_71895_b(aWorld, aX, aY, aZ, 5), true, tR, tG, tB, aRenderInside);
      return true;
   }

   public static boolean renderPipeBlock(IBlockAccess aWorld, int aX, int aY, int aZ, Block aBlock, RenderBlocks aRenderer) {
      TileEntity aTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      if(aTileEntity instanceof BaseMetaPipeEntity) {
         aRenderer.field_78666_e = false;
         BaseMetaPipeEntity tTileEntity = (BaseMetaPipeEntity)aTileEntity;
         boolean tRenderInside = false;
         if((tTileEntity.mConnections & 192) != 0) {
            return renderStandardBlock(aWorld, aX, aY, aZ, aBlock, aRenderer, tRenderInside);
         }

         float tThickness = tTileEntity.getThickNess();
         if(tThickness >= 1.0F) {
            return renderStandardBlock(aWorld, aX, aY, aZ, aBlock, aRenderer, tRenderInside);
         }

         float sp = (1.0F - tThickness) / 2.0F;
         byte tConnections = 0;

         for(byte tIcons = 0; tIcons < 6; ++tIcons) {
            if((tTileEntity.mConnections & 1 << tIcons) != 0) {
               tConnections = (byte)(tConnections | 1 << (tIcons + 2) % 6);
            }
         }

         Icon[] var19 = new Icon[6];
         Icon[] tCovers = new Icon[6];
         boolean[] tIsCovered = new boolean[6];

         byte tC;
         for(tC = 0; tC < 6; ++tC) {
            tIsCovered[tC] = tTileEntity.getCoverIDAtSide(tC) != 0;
         }

         if(tIsCovered[0] && tIsCovered[1] && tIsCovered[2] && tIsCovered[3] && tIsCovered[4] && tIsCovered[5]) {
            return renderStandardBlock(aWorld, aX, aY, aZ, aBlock, aRenderer, tRenderInside);
         }

         for(tC = 0; tC < 6; ++tC) {
            tCovers[tC] = aBlock.func_71895_b(aWorld, aX, aY, aZ, tC);
            var19[tC] = tTileEntity.getUncoveredIcon(tC, (byte)1);
            if(var19[tC] == null) {
               int tR = tTileEntity.getUncoveredIndex(tC, (byte)1);
               if(tR >= 0 && tR < GT_BlockMetaID_Machine.mIcons.length) {
                  var19[tC] = GT_BlockMetaID_Machine.mIcons[tR];
               }

               if(var19[tC] == null) {
                  var19[tC] = GregTech_API.FAIL_ICON;
               }
            }
         }

         Tessellator.field_78398_a.func_78380_c(aBlock.func_71874_e(aWorld, aX, aY, aZ));
         int var20 = aBlock.func_71920_b(aWorld, aX, aY, aZ);
         float var21 = (float)(var20 >> 16 & 255) / 255.0F;
         float tG = (float)(var20 >> 8 & 255) / 255.0F;
         float tB = (float)(var20 & 255) / 255.0F;
         if(tConnections == 0) {
            aBlock.func_71905_a(sp, sp, sp, sp + tThickness, sp + tThickness, sp + tThickness);
            aRenderer.func_83018_a(aBlock);
            renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[0], false, var21, tG, tB, tRenderInside);
            renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[1], false, var21, tG, tB, tRenderInside);
            renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[2], false, var21, tG, tB, tRenderInside);
            renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[3], false, var21, tG, tB, tRenderInside);
            renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[4], false, var21, tG, tB, tRenderInside);
            renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[5], false, var21, tG, tB, tRenderInside);
         } else if(tConnections == 3) {
            aBlock.func_71905_a(0.0F, sp, sp, 1.0F, sp + tThickness, sp + tThickness);
            aRenderer.func_83018_a(aBlock);
            renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[0], false, var21, tG, tB, tRenderInside);
            renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[1], false, var21, tG, tB, tRenderInside);
            renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[2], false, var21, tG, tB, tRenderInside);
            renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[3], false, var21, tG, tB, tRenderInside);
            if(!tIsCovered[4]) {
               renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[4], false, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[5]) {
               renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[5], false, var21, tG, tB, tRenderInside);
            }
         } else if(tConnections == 12) {
            aBlock.func_71905_a(sp, 0.0F, sp, sp + tThickness, 1.0F, sp + tThickness);
            aRenderer.func_83018_a(aBlock);
            renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[2], false, var21, tG, tB, tRenderInside);
            renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[3], false, var21, tG, tB, tRenderInside);
            renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[4], false, var21, tG, tB, tRenderInside);
            renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[5], false, var21, tG, tB, tRenderInside);
            if(!tIsCovered[0]) {
               renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[0], false, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[1]) {
               renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[1], false, var21, tG, tB, tRenderInside);
            }
         } else if(tConnections == 48) {
            aBlock.func_71905_a(sp, sp, 0.0F, sp + tThickness, sp + tThickness, 1.0F);
            aRenderer.func_83018_a(aBlock);
            renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[0], false, var21, tG, tB, tRenderInside);
            renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[1], false, var21, tG, tB, tRenderInside);
            renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[4], false, var21, tG, tB, tRenderInside);
            renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[5], false, var21, tG, tB, tRenderInside);
            if(!tIsCovered[2]) {
               renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[2], false, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[3]) {
               renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[3], false, var21, tG, tB, tRenderInside);
            }
         } else {
            if((tConnections & 1) == 0) {
               aBlock.func_71905_a(sp, sp, sp, sp + tThickness, sp + tThickness, sp + tThickness);
               aRenderer.func_83018_a(aBlock);
               renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[4], false, var21, tG, tB, tRenderInside);
            } else {
               aBlock.func_71905_a(0.0F, sp, sp, sp, sp + tThickness, sp + tThickness);
               aRenderer.func_83018_a(aBlock);
               renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[0], false, var21, tG, tB, tRenderInside);
               renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[1], false, var21, tG, tB, tRenderInside);
               renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[2], false, var21, tG, tB, tRenderInside);
               renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[3], false, var21, tG, tB, tRenderInside);
               if(!tIsCovered[4]) {
                  renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[4], false, var21, tG, tB, tRenderInside);
               }
            }

            if((tConnections & 2) == 0) {
               aBlock.func_71905_a(sp, sp, sp, sp + tThickness, sp + tThickness, sp + tThickness);
               aRenderer.func_83018_a(aBlock);
               renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[5], false, var21, tG, tB, tRenderInside);
            } else {
               aBlock.func_71905_a(sp + tThickness, sp, sp, 1.0F, sp + tThickness, sp + tThickness);
               aRenderer.func_83018_a(aBlock);
               renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[0], false, var21, tG, tB, tRenderInside);
               renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[1], false, var21, tG, tB, tRenderInside);
               renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[2], false, var21, tG, tB, tRenderInside);
               renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[3], false, var21, tG, tB, tRenderInside);
               if(!tIsCovered[5]) {
                  renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[5], false, var21, tG, tB, tRenderInside);
               }
            }

            if((tConnections & 4) == 0) {
               aBlock.func_71905_a(sp, sp, sp, sp + tThickness, sp + tThickness, sp + tThickness);
               aRenderer.func_83018_a(aBlock);
               renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[0], false, var21, tG, tB, tRenderInside);
            } else {
               aBlock.func_71905_a(sp, 0.0F, sp, sp + tThickness, sp, sp + tThickness);
               aRenderer.func_83018_a(aBlock);
               renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[2], false, var21, tG, tB, tRenderInside);
               renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[3], false, var21, tG, tB, tRenderInside);
               renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[4], false, var21, tG, tB, tRenderInside);
               renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[5], false, var21, tG, tB, tRenderInside);
               if(!tIsCovered[0]) {
                  renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[0], false, var21, tG, tB, tRenderInside);
               }
            }

            if((tConnections & 8) == 0) {
               aBlock.func_71905_a(sp, sp, sp, sp + tThickness, sp + tThickness, sp + tThickness);
               aRenderer.func_83018_a(aBlock);
               renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[1], false, var21, tG, tB, tRenderInside);
            } else {
               aBlock.func_71905_a(sp, sp + tThickness, sp, sp + tThickness, 1.0F, sp + tThickness);
               aRenderer.func_83018_a(aBlock);
               renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[2], false, var21, tG, tB, tRenderInside);
               renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[3], false, var21, tG, tB, tRenderInside);
               renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[4], false, var21, tG, tB, tRenderInside);
               renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[5], false, var21, tG, tB, tRenderInside);
               if(!tIsCovered[1]) {
                  renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[1], false, var21, tG, tB, tRenderInside);
               }
            }

            if((tConnections & 16) == 0) {
               aBlock.func_71905_a(sp, sp, sp, sp + tThickness, sp + tThickness, sp + tThickness);
               aRenderer.func_83018_a(aBlock);
               renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[2], false, var21, tG, tB, tRenderInside);
            } else {
               aBlock.func_71905_a(sp, sp, 0.0F, sp + tThickness, sp + tThickness, sp);
               aRenderer.func_83018_a(aBlock);
               renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[0], false, var21, tG, tB, tRenderInside);
               renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[1], false, var21, tG, tB, tRenderInside);
               renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[4], false, var21, tG, tB, tRenderInside);
               renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[5], false, var21, tG, tB, tRenderInside);
               if(!tIsCovered[2]) {
                  renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[2], false, var21, tG, tB, tRenderInside);
               }
            }

            if((tConnections & 32) == 0) {
               aBlock.func_71905_a(sp, sp, sp, sp + tThickness, sp + tThickness, sp + tThickness);
               aRenderer.func_83018_a(aBlock);
               renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[3], false, var21, tG, tB, tRenderInside);
            } else {
               aBlock.func_71905_a(sp, sp, sp + tThickness, sp + tThickness, sp + tThickness, 1.0F);
               aRenderer.func_83018_a(aBlock);
               renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[0], false, var21, tG, tB, tRenderInside);
               renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[1], false, var21, tG, tB, tRenderInside);
               renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[4], false, var21, tG, tB, tRenderInside);
               renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[5], false, var21, tG, tB, tRenderInside);
               if(!tIsCovered[3]) {
                  renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, var19[3], false, var21, tG, tB, tRenderInside);
               }
            }
         }

         if(tIsCovered[0]) {
            aBlock.func_71905_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
            aRenderer.func_83018_a(aBlock);
            renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[0], true, var21, tG, tB, tRenderInside);
            renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[0], true, var21, tG, tB, tRenderInside);
            if(!tIsCovered[2]) {
               renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[0], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[3]) {
               renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[0], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[4]) {
               renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[0], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[5]) {
               renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[0], true, var21, tG, tB, tRenderInside);
            }
         }

         if(tIsCovered[1]) {
            aBlock.func_71905_a(0.0F, 0.875F, 0.0F, 1.0F, 1.0F, 1.0F);
            aRenderer.func_83018_a(aBlock);
            renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[1], true, var21, tG, tB, tRenderInside);
            renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[1], true, var21, tG, tB, tRenderInside);
            if(!tIsCovered[2]) {
               renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[1], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[3]) {
               renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[1], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[4]) {
               renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[1], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[5]) {
               renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[1], true, var21, tG, tB, tRenderInside);
            }
         }

         if(tIsCovered[2]) {
            aBlock.func_71905_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
            aRenderer.func_83018_a(aBlock);
            if(!tIsCovered[0]) {
               renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[2], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[1]) {
               renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[2], true, var21, tG, tB, tRenderInside);
            }

            renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[2], true, var21, tG, tB, tRenderInside);
            renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[2], true, var21, tG, tB, tRenderInside);
            if(!tIsCovered[4]) {
               renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[2], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[5]) {
               renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[2], true, var21, tG, tB, tRenderInside);
            }
         }

         if(tIsCovered[3]) {
            aBlock.func_71905_a(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
            aRenderer.func_83018_a(aBlock);
            if(!tIsCovered[0]) {
               renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[3], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[1]) {
               renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[3], true, var21, tG, tB, tRenderInside);
            }

            renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[3], true, var21, tG, tB, tRenderInside);
            renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[3], true, var21, tG, tB, tRenderInside);
            if(!tIsCovered[4]) {
               renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[3], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[5]) {
               renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[3], true, var21, tG, tB, tRenderInside);
            }
         }

         if(tIsCovered[4]) {
            aBlock.func_71905_a(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 1.0F);
            aRenderer.func_83018_a(aBlock);
            if(!tIsCovered[0]) {
               renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[4], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[1]) {
               renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[4], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[2]) {
               renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[4], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[3]) {
               renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[4], true, var21, tG, tB, tRenderInside);
            }

            renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[4], true, var21, tG, tB, tRenderInside);
            renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[4], true, var21, tG, tB, tRenderInside);
         }

         if(tIsCovered[5]) {
            aBlock.func_71905_a(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            aRenderer.func_83018_a(aBlock);
            if(!tIsCovered[0]) {
               renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[5], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[1]) {
               renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[5], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[2]) {
               renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[5], true, var21, tG, tB, tRenderInside);
            }

            if(!tIsCovered[3]) {
               renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[5], true, var21, tG, tB, tRenderInside);
            }

            renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[5], true, var21, tG, tB, tRenderInside);
            renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tCovers[5], true, var21, tG, tB, tRenderInside);
         }

         aBlock.func_71905_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         aRenderer.func_83018_a(aBlock);
      }

      return true;
   }

   public static void renderNegativeYFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, Icon aIcon, boolean aFullBlock, float aR, float aG, float aB, boolean aInsideToo) {
      if(aWorld != null) {
         if(aFullBlock && !aBlock.func_71877_c(aWorld, aX, aY - 1, aZ, 0)) {
            return;
         }

         Tessellator.field_78398_a.func_78380_c(aBlock.func_71874_e(aWorld, aX, aFullBlock?aY - 1:aY, aZ));
      }

      Tessellator.field_78398_a.func_78386_a(aR * 0.5F, aG * 0.5F, aB * 0.5F);
      double d3 = (double)aIcon.func_94214_a(aRenderer.field_83026_h * 16.0D);
      double d4 = (double)aIcon.func_94214_a(aRenderer.field_83021_g * 16.0D);
      double d5 = (double)aIcon.func_94207_b(aRenderer.field_83025_k * 16.0D);
      double d6 = (double)aIcon.func_94207_b(aRenderer.field_83022_l * 16.0D);
      if(aRenderer.field_83021_g < 0.0D || aRenderer.field_83026_h > 1.0D) {
         d3 = (double)aIcon.func_94212_f();
         d4 = (double)aIcon.func_94209_e();
      }

      if(aRenderer.field_83025_k < 0.0D || aRenderer.field_83022_l > 1.0D) {
         d5 = (double)aIcon.func_94206_g();
         d6 = (double)aIcon.func_94210_h();
      }

      double d11 = (double)aX + aRenderer.field_83021_g;
      double d12 = (double)aX + aRenderer.field_83026_h;
      double d13 = (double)aY + aRenderer.field_83027_i;
      double d14 = (double)aZ + aRenderer.field_83025_k;
      double d15 = (double)aZ + aRenderer.field_83022_l;
      Tessellator.field_78398_a.func_78374_a(d11, d13, d15, d3, d6);
      Tessellator.field_78398_a.func_78374_a(d11, d13, d14, d3, d5);
      Tessellator.field_78398_a.func_78374_a(d12, d13, d14, d4, d5);
      Tessellator.field_78398_a.func_78374_a(d12, d13, d15, d4, d6);
      if(aInsideToo) {
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
         renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aIcon, false, aR, aG, aB, false);
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
      }

   }

   public static void renderPositiveYFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, Icon aIcon, boolean aFullBlock, float aR, float aG, float aB, boolean aInsideToo) {
      if(aWorld != null) {
         if(aFullBlock && !aBlock.func_71877_c(aWorld, aX, aY + 1, aZ, 1)) {
            return;
         }

         Tessellator.field_78398_a.func_78380_c(aBlock.func_71874_e(aWorld, aX, aFullBlock?aY + 1:aY, aZ));
      }

      Tessellator.field_78398_a.func_78386_a(aR * 1.0F, aG * 1.0F, aB * 1.0F);
      aRenderer.func_78617_b(aBlock, (double)aX, (double)aY, (double)aZ, aIcon);
      if(aInsideToo) {
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
         renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aIcon, false, aR, aG, aB, false);
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
      }

   }

   public static void renderNegativeZFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, Icon aIcon, boolean aFullBlock, float aR, float aG, float aB, boolean aInsideToo) {
      if(aWorld != null) {
         if(aFullBlock && !aBlock.func_71877_c(aWorld, aX, aY, aZ - 1, 2)) {
            return;
         }

         Tessellator.field_78398_a.func_78380_c(aBlock.func_71874_e(aWorld, aX, aY, aFullBlock?aZ - 1:aZ));
      }

      Tessellator.field_78398_a.func_78386_a(aR * 0.8F, aG * 0.8F, aB * 0.8F);
      aRenderer.field_78666_e = !aFullBlock;
      aRenderer.func_78611_c(aBlock, (double)aX, (double)aY, (double)aZ, aIcon);
      aRenderer.field_78666_e = false;
      if(aInsideToo) {
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
         renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aIcon, false, aR, aG, aB, false);
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
      }

   }

   public static void renderPositiveZFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, Icon aIcon, boolean aFullBlock, float aR, float aG, float aB, boolean aInsideToo) {
      if(aWorld != null) {
         if(aFullBlock && !aBlock.func_71877_c(aWorld, aX, aY, aZ + 1, 3)) {
            return;
         }

         Tessellator.field_78398_a.func_78380_c(aBlock.func_71874_e(aWorld, aX, aY, aFullBlock?aZ + 1:aZ));
      }

      Tessellator.field_78398_a.func_78386_a(aR * 0.8F, aG * 0.8F, aB * 0.8F);
      aRenderer.func_78622_d(aBlock, (double)aX, (double)aY, (double)aZ, aIcon);
      if(aInsideToo) {
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
         renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aIcon, false, aR, aG, aB, false);
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
      }

   }

   public static void renderNegativeXFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, Icon aIcon, boolean aFullBlock, float aR, float aG, float aB, boolean aInsideToo) {
      if(aWorld != null) {
         if(aFullBlock && !aBlock.func_71877_c(aWorld, aX - 1, aY, aZ, 4)) {
            return;
         }

         Tessellator.field_78398_a.func_78380_c(aBlock.func_71874_e(aWorld, aFullBlock?aX - 1:aX, aY, aZ));
      }

      Tessellator.field_78398_a.func_78386_a(aR * 0.6F, aG * 0.6F, aB * 0.6F);
      aRenderer.func_78573_e(aBlock, (double)aX, (double)aY, (double)aZ, aIcon);
      if(aInsideToo) {
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
         renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aIcon, false, aR, aG, aB, false);
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
      }

   }

   public static void renderPositiveXFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, Icon aIcon, boolean aFullBlock, float aR, float aG, float aB, boolean aInsideToo) {
      if(aWorld != null) {
         if(aFullBlock && !aBlock.func_71877_c(aWorld, aX + 1, aY, aZ, 5)) {
            return;
         }

         Tessellator.field_78398_a.func_78380_c(aBlock.func_71874_e(aWorld, aFullBlock?aX + 1:aX, aY, aZ));
      }

      Tessellator.field_78398_a.func_78386_a(aR * 0.6F, aG * 0.6F, aB * 0.6F);
      aRenderer.field_78666_e = !aFullBlock;
      aRenderer.func_78605_f(aBlock, (double)aX, (double)aY, (double)aZ, aIcon);
      aRenderer.field_78666_e = false;
      if(aInsideToo) {
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
         renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, aIcon, false, aR, aG, aB, false);
         aBlock.func_71905_a(1.0F - (float)aBlock.func_83009_v(), 1.0F - (float)aBlock.func_83008_x(), 1.0F - (float)aBlock.func_83005_z(), 1.0F - (float)aBlock.func_83007_w(), 1.0F - (float)aBlock.func_83010_y(), 1.0F - (float)aBlock.func_83006_A());
      }

   }

   public boolean shouldRender3DInInventory() {
      return true;
   }

   public int getRenderId() {
      return this.mRenderID;
   }
}
