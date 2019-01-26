package gregtechmod.common;

import cpw.mods.fml.client.registry.RenderingRegistry;
import gregtechmod.GT_Mod;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.GT_Proxy;
import gregtechmod.common.GT_SoundAdder;
import gregtechmod.common.GT_TickHandler;
import gregtechmod.common.render.GT_MetaGenerated_Item_Renderer;
import gregtechmod.common.render.GT_Renderer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Client extends GT_Proxy {

   public static GT_TickHandler mClientTickHandler = new GT_TickHandler(false);
   public static GT_SoundAdder mSoundAdder = new GT_SoundAdder();
   public static GT_Renderer mRenderer = new GT_Renderer();


   public boolean isServerSide() {
      return true;
   }

   public boolean isClientSide() {
      return true;
   }

   public boolean isBukkitSide() {
      return false;
   }

   public boolean registerRenderers() {
      new GT_MetaGenerated_Item_Renderer();
      return true;
   }

   public EntityPlayer getThePlayer() {
      try {
         return Minecraft.func_71410_x().field_71439_g;
      } catch (Throwable var2) {
         return null;
      }
   }

   public int addArmor(String aPrefix) {
      try {
         return RenderingRegistry.addNewArmourRendererPrefix(aPrefix);
      } catch (Throwable var3) {
         return 0;
      }
   }

   public void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
      float tFloat = 1.0F;
      String tString = "note.harp";
      if(aStack != null) {
         for(int i = 0; i < GT_Mod.mSoundItems.size(); ++i) {
            if(GT_Utility.areStacksEqual((ItemStack)GT_Mod.mSoundItems.get(i), aStack)) {
               tString = (String)GT_Mod.mSoundNames.get(i);
               break;
            }
         }

         if(tString.startsWith("random.explode")) {
            if(aStack.field_77994_a == 3) {
               tString = "random.fuse";
            } else if(aStack.field_77994_a == 2) {
               tString = "random.old_explode";
            }
         }

         if(tString.startsWith("streaming.")) {
            switch(aStack.field_77994_a) {
            case 1:
               tString = tString + "13";
               break;
            case 2:
               tString = tString + "cat";
               break;
            case 3:
               tString = tString + "blocks";
               break;
            case 4:
               tString = tString + "chirp";
               break;
            case 5:
               tString = tString + "far";
               break;
            case 6:
               tString = tString + "mall";
               break;
            case 7:
               tString = tString + "mellohi";
               break;
            case 8:
               tString = tString + "stal";
               break;
            case 9:
               tString = tString + "strad";
               break;
            case 10:
               tString = tString + "ward";
               break;
            case 11:
               tString = tString + "11";
               break;
            case 12:
               tString = tString + "wait";
               break;
            default:
               tString = tString + "wherearewenow";
            }
         }

         if(tString.startsWith("note.")) {
            tFloat = (float)Math.pow(2.0D, (double)(aStack.field_77994_a - 13) / 12.0D);
         }

         if(tString.startsWith("streaming.")) {
            aWorld.func_72934_a(tString.substring(10, tString.length()), (int)aX, (int)aY, (int)aZ);
         } else {
            aWorld.func_72980_b(aX, aY, aZ, tString, 3.0F, tFloat, false);
         }

      }
   }

}
