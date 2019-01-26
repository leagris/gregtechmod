package gregtechmod.common.render;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;

public class GT_Renderer extends RenderPlayer {

   public GT_Renderer() {
      MinecraftForge.EVENT_BUS.register(this);
      this.func_76976_a(RenderManager.field_78727_a);
   }

   @ForgeSubscribe
   public void receiveRenderEvent(Pre aEvent) {
      if(GT_Utility.getFullInvisibility(aEvent.entityPlayer)) {
         aEvent.setCanceled(true);
      }
   }

   @ForgeSubscribe
   public void receiveRenderSpecialsEvent(net.minecraftforge.client.event.RenderPlayerEvent.Specials.Pre aEvent) {
      AbstractClientPlayer aPlayer = (AbstractClientPlayer)aEvent.entityPlayer;
      float aPartialTicks = aEvent.partialTicks;
      if(GT_Utility.getFullInvisibility(aPlayer)) {
         aEvent.setCanceled(true);
      } else if(!aPlayer.func_82150_aj()) {
         if(!GT_Utility.getPotion(aPlayer, Integer.valueOf(Potion.field_76441_p.field_76415_H).intValue())) {
            try {
               ResourceLocation e = null;
               if(GT_Mod.mBrainTechCapeList.contains(aPlayer.field_71092_bJ.toLowerCase())) {
                  e = new ResourceLocation("gregtech_addon", "textures/BrainTechCape.png");
               }

               if(GT_Mod.mGregTechCapeList.contains(aPlayer.field_71092_bJ.toLowerCase())) {
                  e = new ResourceLocation("gregtech_addon", "textures/GregTechCape.png");
               }

               if(aPlayer.field_71092_bJ.equalsIgnoreCase("Mr_Brain")) {
                  e = new ResourceLocation("gregtech_addon", "textures/MrBrainCape.png");
               }

               if(aPlayer.field_71092_bJ.equalsIgnoreCase("GregoriusT")) {
                  e = new ResourceLocation("gregtech_addon", "textures/GregoriusCape.png");
               }

               if(e != null && !aPlayer.func_82238_cc()) {
                  this.func_110776_a(e);
                  GL11.glPushMatrix();
                  GL11.glTranslatef(0.0F, 0.0F, 0.125F);
                  double d0 = aPlayer.field_71091_bM + (aPlayer.field_71094_bP - aPlayer.field_71091_bM) * (double)aPartialTicks - (aPlayer.field_70169_q + (aPlayer.field_70165_t - aPlayer.field_70169_q) * (double)aPartialTicks);
                  double d1 = aPlayer.field_71096_bN + (aPlayer.field_71095_bQ - aPlayer.field_71096_bN) * (double)aPartialTicks - (aPlayer.field_70167_r + (aPlayer.field_70163_u - aPlayer.field_70167_r) * (double)aPartialTicks);
                  double d2 = aPlayer.field_71097_bO + (aPlayer.field_71085_bR - aPlayer.field_71097_bO) * (double)aPartialTicks - (aPlayer.field_70166_s + (aPlayer.field_70161_v - aPlayer.field_70166_s) * (double)aPartialTicks);
                  float f6 = aPlayer.field_70760_ar + (aPlayer.field_70761_aq - aPlayer.field_70760_ar) * aPartialTicks;
                  double d3 = (double)MathHelper.func_76126_a(f6 * 3.1415927F / 180.0F);
                  double d4 = (double)(-MathHelper.func_76134_b(f6 * 3.1415927F / 180.0F));
                  float f7 = (float)d1 * 10.0F;
                  float f8 = (float)(d0 * d3 + d2 * d4) * 100.0F;
                  float f9 = (float)(d0 * d4 - d2 * d3) * 100.0F;
                  if(f7 < -6.0F) {
                     f7 = -6.0F;
                  }

                  if(f7 > 32.0F) {
                     f7 = 32.0F;
                  }

                  if(f8 < 0.0F) {
                     f8 = 0.0F;
                  }

                  float f10 = aPlayer.field_71107_bF + (aPlayer.field_71109_bG - aPlayer.field_71107_bF) * aPartialTicks;
                  f7 += MathHelper.func_76126_a((aPlayer.field_70141_P + (aPlayer.field_70140_Q - aPlayer.field_70141_P) * aPartialTicks) * 6.0F) * 32.0F * f10;
                  if(aPlayer.func_70093_af()) {
                     f7 += 25.0F;
                  }

                  GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                  ((ModelBiped)this.field_77045_g).func_78111_c(0.0625F);
                  GL11.glPopMatrix();
               }
            } catch (Throwable var20) {
               if(GregTech_API.DEBUG_MODE) {
                  var20.printStackTrace(GT_Log.err);
               }
            }

         }
      }
   }
}
