package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_Container_BasicTank;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GT_GUIContainer_Hatch_Output extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_Hatch_Output(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_BasicTank(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/BasicTank.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b(StatCollector.func_74838_a("container.inventory"), 8, this.field_74195_c - 96 + 2, 4210752);
      this.field_73886_k.func_78276_b("Output Hatch", 8, 6, 4210752);
      if(this.mContainer != null) {
         this.field_73886_k.func_78276_b("Liquid Amount", 10, 20, 16448255);
         this.field_73886_k.func_78276_b(GT_Utility.parseNumberToString(((GT_Container_BasicTank)this.mContainer).mContent), 10, 30, 16448255);
      }

   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
   }
}
