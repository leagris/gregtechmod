package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricTypeSorter;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_ElectricTypeSorter extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_ElectricTypeSorter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_ElectricTypeSorter(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/ElectricTypeSorter.png");
   }

   protected void func_74189_g(int par1, int par2) {
      String tINString = "";
      String tOUTString = "";
      switch(this.mContainer.mTileEntity.getFrontFacing()) {
      case 0:
         tINString = "UP";
         break;
      case 1:
         tINString = "DOWN";
         break;
      case 2:
         tINString = "SOUTH";
         break;
      case 3:
         tINString = "NORTH";
         break;
      case 4:
         tINString = "EAST";
         break;
      case 5:
         tINString = "WEST";
         break;
      default:
         tINString = "FAIL";
      }

      switch(((GT_Container_ElectricTypeSorter)this.mContainer).mTargetDirection) {
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

      this.field_73886_k.func_78276_b(tINString, 138, 7, 16448255);
      this.field_73886_k.func_78276_b(tOUTString, 100, 65, 16448255);
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null) {
         this.func_73729_b(x + 70, y + 22, ((GT_Container_ElectricTypeSorter)this.mContainer).mMode * 18, 166, 18, 18);
      }

   }
}
