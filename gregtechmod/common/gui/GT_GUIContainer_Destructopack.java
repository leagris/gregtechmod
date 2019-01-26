package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainer;
import gregtechmod.common.containers.GT_Container_Item_Destructopack;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class GT_GUIContainer_Destructopack extends GT_GUIContainer {

   public GT_GUIContainer_Destructopack(InventoryPlayer aInventoryPlayer, ItemStack aItem) {
      super(new GT_Container_Item_Destructopack(aInventoryPlayer, aItem), "gregtech_addon:textures/gui/Destructopack.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b("Destructopack", 8, 6, 4210752);
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
   }
}
