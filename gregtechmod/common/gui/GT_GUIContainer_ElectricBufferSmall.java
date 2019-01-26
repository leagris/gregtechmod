package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricBufferSmall;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_ElectricBufferSmall extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_ElectricBufferSmall(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_ElectricBufferSmall(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/ElectricBufferSmall.png");
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
   }
}
