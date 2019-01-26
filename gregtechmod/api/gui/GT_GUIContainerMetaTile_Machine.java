package gregtechmod.api.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_GUIContainer;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemDye;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainerMetaTile_Machine extends GT_GUIContainer {

   public final GT_ContainerMetaTile_Machine mContainer;


   public GT_GUIContainerMetaTile_Machine(GT_ContainerMetaTile_Machine aContainer, String aGUIbackground) {
      super(aContainer, aGUIbackground);
      this.mContainer = aContainer;
   }

   public GT_GUIContainerMetaTile_Machine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aGUIbackground) {
      this(new GT_ContainerMetaTile_Machine(aInventoryPlayer, aTileEntity), aGUIbackground);
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      if(GregTech_API.sColoredGUI && this.mContainer != null && this.mContainer.mTileEntity != null) {
         int tColor = this.mContainer.mTileEntity.getColorization() & 15;
         if(tColor >= 0 && tColor < ItemDye.field_77859_b.length) {
            tColor = ItemDye.field_77859_b[tColor];
            GL11.glColor4f((float)(tColor >> 16 & 255) / 255.0F, (float)(tColor >> 8 & 255) / 255.0F, (float)(tColor & 255) / 255.0F, 1.0F);
         } else {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }
      } else {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }

   }
}
