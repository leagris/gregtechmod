package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.containers.GT_Container_AESU_Meta;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_AESU_Meta extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_AESU_Meta(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_AESU_Meta(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/AESU.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b("A.E.S.U.", 11, 8, 16448255);
      if(this.mContainer != null) {
         this.field_73886_k.func_78276_b("EU: " + GT_Utility.parseNumberToString(this.mContainer.mEnergy), 11, 16, 16448255);
         this.field_73886_k.func_78276_b("MAX: " + GT_Utility.parseNumberToString(this.mContainer.mStorage), 11, 24, 16448255);
         this.field_73886_k.func_78276_b("MAX EU/t IN: " + GT_Utility.parseNumberToString(this.mContainer.mInput), 11, 32, 16448255);
         this.field_73886_k.func_78276_b("BASE OUT: " + GT_Utility.parseNumberToString(((GT_Container_AESU_Meta)this.mContainer).mBasicOutput), 11, 40, 16448255);
         this.field_73886_k.func_78276_b("EU/t OUT: " + GT_Utility.parseNumberToString(this.mContainer.mOutput), 11, 48, 16448255);
      }

   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null) {
         int tScale = this.mContainer.mEnergy / Math.max(1, this.mContainer.mStorage / 97);
         this.func_73729_b(x + 8, y + 73, 0, 251, tScale, 5);
      }

   }
}
