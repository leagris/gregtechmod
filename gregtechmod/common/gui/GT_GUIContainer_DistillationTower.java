package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_DistillationTower;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_DistillationTower extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_DistillationTower(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_DistillationTower(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/DistillationTower.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b("Distillation", 116, 4, 4210752);
      this.field_73886_k.func_78276_b("Tower", 116, 12, 4210752);
      if(!((GT_Container_DistillationTower)this.mContainer).mMachine) {
         this.field_73886_k.func_78276_b("Incomplete", 116, 20, 4210752);
         this.field_73886_k.func_78276_b("Machine", 116, 28, 4210752);
         this.field_73886_k.func_78276_b("Casing!", 116, 36, 4210752);
      }

      if((this.mContainer.mDisplayErrorCode & 1) != 0) {
         this.field_73886_k.func_78276_b("Insufficient", 116, 44, 4210752);
         this.field_73886_k.func_78276_b("Energy", 116, 52, 4210752);
         this.field_73886_k.func_78276_b("Line!", 116, 60, 4210752);
      }

   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null && this.mContainer.mProgressTime > 0) {
         int tScale = ((GT_Container_DistillationTower)this.mContainer).mProgressScale;
         this.func_73729_b(x + 80, y + 76 - tScale, 176, 76 - tScale, 16, tScale);
      }

   }
}
