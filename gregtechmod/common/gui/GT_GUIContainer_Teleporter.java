package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.containers.GT_Container_Teleporter;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_Teleporter extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_Teleporter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_Teleporter(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/Teleporter.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b("Teleporter", 46, 8, 16448255);
      if(this.mContainer != null) {
         this.field_73886_k.func_78276_b("X: " + GT_Utility.parseNumberToString(((GT_Container_Teleporter)this.mContainer).mTargetX), 46, 16, 16448255);
         this.field_73886_k.func_78276_b("Y: " + GT_Utility.parseNumberToString(((GT_Container_Teleporter)this.mContainer).mTargetY), 46, 24, 16448255);
         this.field_73886_k.func_78276_b("Z: " + GT_Utility.parseNumberToString(((GT_Container_Teleporter)this.mContainer).mTargetZ), 46, 32, 16448255);
         if(((GT_Container_Teleporter)this.mContainer).mEgg > 0) {
            this.field_73886_k.func_78276_b("Dim: " + GT_Utility.parseNumberToString(((GT_Container_Teleporter)this.mContainer).mTargetD), 46, 40, 16448255);
         }
      }

   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
   }
}
