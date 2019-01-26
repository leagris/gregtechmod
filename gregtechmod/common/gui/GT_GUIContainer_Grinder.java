package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_Grinder;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GT_GUIContainer_Grinder extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_Grinder(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_Grinder(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/Grinder.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b("Industrial Grinder", 8, 4, 4210752);
      if(!((GT_Container_Grinder)this.mContainer).mMachine) {
         this.field_73886_k.func_78276_b("Incomplete Machine Casing!", 8, this.field_74195_c - 104, 4210752);
      }

      if((this.mContainer.mDisplayErrorCode & 1) != 0) {
         this.field_73886_k.func_78276_b("Insufficient Energy Line!", 8, this.field_74195_c - 94, 4210752);
      } else {
         this.field_73886_k.func_78276_b(StatCollector.func_74838_a("container.inventory"), 8, this.field_74195_c - 94, 4210752);
      }

   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null) {
         int tScale = ((GT_Container_Grinder)this.mContainer).mProgressScale;
         if(this.mContainer.mProgressTime > 0) {
            this.func_73729_b(x + 58, y + 28, 176, 0, tScale, 11);
         }

         tScale = ((GT_Container_Grinder)this.mContainer).mWaterAmount / 550;
         if(tScale > 0) {
            this.func_73729_b(x + 33, y + 33 + 18 - tScale, 176, 51 - tScale, 18, tScale);
         }
      }

   }
}
