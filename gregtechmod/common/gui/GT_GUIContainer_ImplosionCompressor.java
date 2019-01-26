package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ImplosionCompressor;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GT_GUIContainer_ImplosionCompressor extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_ImplosionCompressor(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_ImplosionCompressor(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/Implosion.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b(StatCollector.func_74838_a("container.inventory"), 8, this.field_74195_c - 94, 4210752);
      this.field_73886_k.func_78276_b("Implosion Compressor", 8, 4, 4210752);
      if(!((GT_Container_ImplosionCompressor)this.mContainer).mMachine) {
         this.field_73886_k.func_78276_b("Incomplete Machine Casing!", 8, this.field_74195_c - 103, 4210752);
      }

   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null && this.mContainer.mProgressTime > 0) {
         int tScale = ((GT_Container_ImplosionCompressor)this.mContainer).mProgressScale;
         this.func_73729_b(x + 58, y + 28, 176, 0, tScale, 11);
      }

   }
}
