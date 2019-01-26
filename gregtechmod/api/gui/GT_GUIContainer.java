package gregtechmod.api.gui;

import gregtechmod.api.util.GT_Log;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class GT_GUIContainer extends GuiContainer {

   public boolean mCrashed = false;
   public ResourceLocation mGUIbackground;


   public GT_GUIContainer(Container aContainer, String aGUIbackground) {
      super(aContainer);
      this.mGUIbackground = new ResourceLocation(aGUIbackground);
   }

   protected void func_74189_g(int par1, int par2) {}

   protected void func_74185_a(float par1, int par2, int par3) {
      this.field_73882_e.field_71446_o.func_110577_a(this.mGUIbackground);
   }

   public void func_73863_a(int par1, int par2, float par3) {
      try {
         super.func_73863_a(par1, par2, par3);
      } catch (Throwable var5) {
         Tessellator.field_78398_a.field_78415_z = false;
      }

   }

   protected void func_74192_a(Slot par1Slot) {
      try {
         super.func_74192_a(par1Slot);
      } catch (Throwable var3) {
         Tessellator.field_78398_a.field_78415_z = false;
         if(!this.mCrashed) {
            GT_Log.out.println("Clientside Slot drawing Crash prevented. Seems one Itemstack causes Problems with negative Damage Values or the Wildcard Damage Value. This is absolutely NOT a Bug of the GregTech-Addon, so don\'t even think about reporting it to me, it\'s a Bug of the Mod, which belongs to the almost-crash-causing Item, so bug that Mods Author and not me! Did you hear it? NOT ME!!!");
            var3.printStackTrace();
            this.mCrashed = true;
         }
      }

   }
}
