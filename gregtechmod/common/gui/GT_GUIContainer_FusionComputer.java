package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_FusionComputer;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_FusionComputer extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_FusionComputer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_FusionComputer(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/FusionComputer.png");
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null) {
         int tScale = this.mContainer.mEnergy / 1088435;
         this.func_73729_b(x + 5, y + 156, 0, 251, tScale, 5);
      }

   }
}
