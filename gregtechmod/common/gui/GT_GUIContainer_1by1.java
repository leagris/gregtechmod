package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_1by1;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_1by1 extends GT_GUIContainerMetaTile_Machine {

   private final String mName;


   public GT_GUIContainer_1by1(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName) {
      super(new GT_Container_1by1(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/1by1.png");
      this.mName = aName;
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b(this.mName, 8, 4, 4210752);
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
   }
}
