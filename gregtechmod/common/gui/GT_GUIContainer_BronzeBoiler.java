package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_BronzeBoiler;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_BronzeBoiler extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_BronzeBoiler(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_BronzeBoiler(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/BronzeBoiler.png");
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b("Small Coal Boiler", 8, 4, 4210752);
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null) {
         int tScale = ((GT_Container_BronzeBoiler)this.mContainer).mSteamAmount;
         if(tScale > 0) {
            this.func_73729_b(x + 70, y + 25 + 54 - tScale, 194, 54 - tScale, 10, tScale);
         }

         tScale = ((GT_Container_BronzeBoiler)this.mContainer).mWaterAmount;
         if(tScale > 0) {
            this.func_73729_b(x + 83, y + 25 + 54 - tScale, 204, 54 - tScale, 10, tScale);
         }

         tScale = ((GT_Container_BronzeBoiler)this.mContainer).mTemperature;
         if(tScale > 0) {
            this.func_73729_b(x + 96, y + 25 + 54 - tScale, 214, 54 - tScale, 10, tScale);
         }

         tScale = ((GT_Container_BronzeBoiler)this.mContainer).mProcessingEnergy;
         if(tScale > 0) {
            this.func_73729_b(x + 117, y + 44 + 14 - tScale, 177, 14 - tScale, 15, tScale + 1);
         }
      }

   }
}
