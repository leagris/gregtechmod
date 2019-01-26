package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_TradeOMat_Inventory_Objects;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_TradeOMat_Inventory_Objects extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_TradeOMat_Inventory_Objects(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_TradeOMat_Inventory_Objects(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/Tradeomat_Inventory.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b("Your Offer", 8, 5, 4210752);
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
   }
}
