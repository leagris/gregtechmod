package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricRetrieverAdvanced;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_ElectricRetrieverAdvanced extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_ElectricRetrieverAdvanced(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_ElectricRetrieverAdvanced(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/ElectricRetrieverAdvanced.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[0], 120, 9, 16448255);
      this.field_73886_k.func_78276_b("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[1], 137, 9, 16448255);
      this.field_73886_k.func_78276_b("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[2], 155, 9, 16448255);
      this.field_73886_k.func_78276_b("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[3], 120, 26, 16448255);
      this.field_73886_k.func_78276_b("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[4], 137, 26, 16448255);
      this.field_73886_k.func_78276_b("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[5], 155, 26, 16448255);
      this.field_73886_k.func_78276_b("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[6], 120, 43, 16448255);
      this.field_73886_k.func_78276_b("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[7], 137, 43, 16448255);
      this.field_73886_k.func_78276_b("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[8], 155, 43, 16448255);
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
   }
}
