package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricAutoWorkbench;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_ElectricAutoWorkbench extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_ElectricAutoWorkbench(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_ElectricAutoWorkbench(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/ElectricAutoWorkbench.png");
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null) {
         int tMode = ((GT_Container_ElectricAutoWorkbench)this.mContainer).mMode;
         if(tMode != 0) {
            this.func_73729_b(x + 120, y + 40, tMode * 18, 166, 18, 18);
         }

         tMode = ((GT_Container_ElectricAutoWorkbench)this.mContainer).mThroughPut;
         this.func_73729_b(x + 120, y + 4, tMode * 18, 184, 18, 18);
      }

   }
}
