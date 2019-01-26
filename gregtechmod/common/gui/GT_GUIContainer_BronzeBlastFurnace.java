package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_BronzeBlastFurnace;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_BronzeBlastFurnace extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_BronzeBlastFurnace(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_BronzeBlastFurnace(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/BronzeBlastFurnace.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b("Bronze Blast Furnace", 8, 4, 4210752);
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null && this.mContainer.mProgressTime > 0) {
         this.func_73729_b(x + 58, y + 28, 176, 0, Math.max(0, Math.min(20, (this.mContainer.mProgressTime > 0?1:0) + this.mContainer.mProgressTime * 20 / (this.mContainer.mMaxProgressTime < 1?1:this.mContainer.mMaxProgressTime))), 11);
      }

   }
}
