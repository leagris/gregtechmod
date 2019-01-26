package gregtechmod.common.gui;

import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.containers.GT_Container_Sonictron;
import gregtechmod.common.gui.GT_GUIContainerMetaID_Machine;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Sonictron;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_Sonictron extends GT_GUIContainerMetaID_Machine {

   public GT_GUIContainer_Sonictron(InventoryPlayer aInventoryPlayer, GT_TileEntity_Sonictron aTileEntity) {
      super((GT_ContainerMetaID_Machine)(new GT_Container_Sonictron(aInventoryPlayer, aTileEntity)), aTileEntity, "gregtech_addon:textures/gui/Sonictron.png");
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
   }
}
