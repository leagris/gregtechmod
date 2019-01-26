package gregtechmod.common.render;

import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import gregtechmod.api.util.GT_Utility;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

public class GT_MetaGenerated_Item_Renderer implements IItemRenderer {

   public GT_MetaGenerated_Item_Renderer() {
      Iterator i$ = GT_MetaGenerated_Item.sInstances.values().iterator();

      while(i$.hasNext()) {
         GT_MetaGenerated_Item tItem = (GT_MetaGenerated_Item)i$.next();
         if(tItem != null && tItem.useStandardMetaItemRenderer()) {
            MinecraftForgeClient.registerItemRenderer(tItem.field_77779_bT, this);
         }
      }

   }

   public boolean handleRenderType(ItemStack aStack, ItemRenderType aType) {
      return !GT_Utility.isStackInvalid(aStack) && aStack.func_77960_j() >= 0?aType == ItemRenderType.EQUIPPED_FIRST_PERSON || aType == ItemRenderType.INVENTORY || aType == ItemRenderType.EQUIPPED || aType == ItemRenderType.ENTITY:false;
   }

   public boolean shouldUseRenderHelper(ItemRenderType aType, ItemStack aStack, ItemRendererHelper aHelper) {
      return GT_Utility.isStackInvalid(aStack)?false:aType == ItemRenderType.ENTITY;
   }

   public void renderItem(ItemRenderType type, ItemStack aStack, Object ... data) {
      if(!GT_Utility.isStackInvalid(aStack)) {
         short aMetaData = (short)aStack.func_77960_j();
         GT_MetaGenerated_Item aItem = (GT_MetaGenerated_Item)aStack.func_77973_b();
         GL11.glEnable(3042);
         if(type == ItemRenderType.ENTITY) {
            if(RenderItem.field_82407_g) {
               GL11.glScalef(0.85F, 0.85F, 0.85F);
               GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
               GL11.glTranslated(-0.5D, -0.42D, 0.0D);
            } else {
               GL11.glTranslated(-0.5D, -0.42D, 0.0D);
            }
         }

         GL11.glColor3f(1.0F, 1.0F, 1.0F);
         Icon tIcon;
         if(aMetaData < 32000) {
            IIconContainer tStats = aItem.getIconContainer(aMetaData);
            Icon tCharge = null;
            Icon tFluidIcon = null;
            if(tStats == null) {
               tIcon = aStack.func_77954_c();
            } else {
               tIcon = tStats.getIcon();
               tCharge = tStats.getOverlayIcon();
            }

            if(tIcon == null) {
               return;
            }

            if(tCharge != null) {
               FluidStack tModulation = GT_Utility.getFluidForFilledItem(aStack);
               if(tModulation != null) {
                  tFluidIcon = tModulation.getFluid().getIcon(tModulation);
               }
            }

            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            GL11.glBlendFunc(770, 771);
            if(tFluidIcon == null) {
               short[] tModulation1 = aItem.getRGBa(aStack);
               GL11.glColor3f((float)tModulation1[0] / 255.0F, (float)tModulation1[1] / 255.0F, (float)tModulation1[2] / 255.0F);
            }

            if(type.equals(ItemRenderType.INVENTORY)) {
               renderIcon(tIcon, 16.0D, 0.001D, 0.0F, 0.0F, -1.0F);
            } else {
               ItemRenderer.func_78439_a(Tessellator.field_78398_a, tIcon.func_94212_f(), tIcon.func_94206_g(), tIcon.func_94209_e(), tIcon.func_94210_h(), tIcon.func_94211_a(), tIcon.func_94216_b(), 0.0625F);
            }

            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            if(tFluidIcon != null) {
               Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
               GL11.glBlendFunc(770, 771);
               GL11.glDepthFunc(514);
               if(type.equals(ItemRenderType.INVENTORY)) {
                  renderIcon(tFluidIcon, 16.0D, 0.001D, 0.0F, 0.0F, -1.0F);
               } else {
                  ItemRenderer.func_78439_a(Tessellator.field_78398_a, tFluidIcon.func_94212_f(), tFluidIcon.func_94206_g(), tFluidIcon.func_94209_e(), tFluidIcon.func_94210_h(), tFluidIcon.func_94211_a(), tFluidIcon.func_94216_b(), 0.0625F);
               }

               GL11.glDepthFunc(515);
            }

            if(tCharge != null) {
               Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
               GL11.glBlendFunc(770, 771);
               if(type.equals(ItemRenderType.INVENTORY)) {
                  renderIcon(tCharge, 16.0D, 0.001D, 0.0F, 0.0F, -1.0F);
               } else {
                  ItemRenderer.func_78439_a(Tessellator.field_78398_a, tCharge.func_94212_f(), tCharge.func_94206_g(), tCharge.func_94209_e(), tCharge.func_94210_h(), tCharge.func_94211_a(), tCharge.func_94216_b(), 0.0625F);
               }
            }
         } else {
            Integer[] tStats1 = (Integer[])aItem.mElectricStats.get(Short.valueOf(aMetaData));
            if(tStats1 != null && tStats1[3].intValue() < 0) {
               long tCharge1 = (long)aItem.getCharge(aStack);
               if(tCharge1 <= 0L) {
                  tIcon = aItem.mIconList[aMetaData - 32000][1];
               } else if(tCharge1 >= (long)tStats1[0].intValue()) {
                  tIcon = aItem.mIconList[aMetaData - 32000][8];
               } else {
                  tIcon = aItem.mIconList[aMetaData - 32000][7 - (int)Math.max(0L, Math.min(5L, ((long)tStats1[0].intValue() - tCharge1) * 6L / (long)tStats1[0].intValue()))];
               }
            } else {
               tIcon = aItem.mIconList[aMetaData - 32000][0];
            }

            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            GL11.glBlendFunc(770, 771);
            if(type.equals(ItemRenderType.INVENTORY)) {
               renderIcon(tIcon, 16.0D, 0.001D, 0.0F, 0.0F, -1.0F);
            } else {
               ItemRenderer.func_78439_a(Tessellator.field_78398_a, tIcon.func_94212_f(), tIcon.func_94206_g(), tIcon.func_94209_e(), tIcon.func_94210_h(), tIcon.func_94211_a(), tIcon.func_94216_b(), 0.0625F);
            }
         }

         GL11.glDisable(3042);
      }
   }

   public static void renderIcon(Icon icon, double size, double z, float nx, float ny, float nz) {
      renderIcon(icon, 0.0D, 0.0D, size, size, z, nx, ny, nz);
   }

   public static void renderIcon(Icon icon, double xStart, double yStart, double xEnd, double yEnd, double z, float nx, float ny, float nz) {
      if(icon != null) {
         Tessellator.field_78398_a.func_78382_b();
         Tessellator.field_78398_a.func_78375_b(nx, ny, nz);
         if(nz > 0.0F) {
            Tessellator.field_78398_a.func_78374_a(xStart, yStart, z, (double)icon.func_94209_e(), (double)icon.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(xEnd, yStart, z, (double)icon.func_94212_f(), (double)icon.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(xEnd, yEnd, z, (double)icon.func_94212_f(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(xStart, yEnd, z, (double)icon.func_94209_e(), (double)icon.func_94210_h());
         } else {
            Tessellator.field_78398_a.func_78374_a(xStart, yEnd, z, (double)icon.func_94209_e(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(xEnd, yEnd, z, (double)icon.func_94212_f(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(xEnd, yStart, z, (double)icon.func_94212_f(), (double)icon.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(xStart, yStart, z, (double)icon.func_94209_e(), (double)icon.func_94206_g());
         }

         Tessellator.field_78398_a.func_78381_a();
      }
   }
}
