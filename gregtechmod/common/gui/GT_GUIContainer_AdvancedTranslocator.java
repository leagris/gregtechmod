package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_AdvancedTranslocator;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_AdvancedTranslocator extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_AdvancedTranslocator(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_AdvancedTranslocator(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/AdvancedTranslocator.png");
   }

   protected void func_74189_g(int par1, int par2) {
      String tINString = "";
      String tOUTString = "";
      switch(((GT_Container_AdvancedTranslocator)this.mContainer).mInputSide) {
      case 0:
         tINString = "DOWN";
         break;
      case 1:
         tINString = "UP";
         break;
      case 2:
         tINString = "NORTH";
         break;
      case 3:
         tINString = "SOUTH";
         break;
      case 4:
         tINString = "WEST";
         break;
      case 5:
         tINString = "EAST";
         break;
      default:
         tINString = "FAIL";
      }

      switch(((GT_Container_AdvancedTranslocator)this.mContainer).mOutputSide) {
      case 0:
         tOUTString = "DOWN";
         break;
      case 1:
         tOUTString = "UP";
         break;
      case 2:
         tOUTString = "NORTH";
         break;
      case 3:
         tOUTString = "SOUTH";
         break;
      case 4:
         tOUTString = "WEST";
         break;
      case 5:
         tOUTString = "EAST";
         break;
      default:
         tOUTString = "FAIL";
      }

      this.field_73886_k.func_78276_b(tINString, 7, 7, 16448255);
      this.field_73886_k.func_78276_b(tOUTString, 138, 7, 16448255);
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
   }
}
