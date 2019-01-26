package gregtechmod.api.gui;

import gregtechmod.api.gui.GT_Container_MultiMachine;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_MultiMachine extends GT_GUIContainerMetaTile_Machine {

   String mName = "";


   public GT_GUIContainer_MultiMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName, String aTextureFile) {
      super(new GT_Container_MultiMachine(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/" + aTextureFile);
      this.mName = aName;
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b(this.mName, 10, 8, 16448255);
      if(this.mContainer != null) {
         if((((GT_Container_MultiMachine)this.mContainer).mDisplayErrorCode & 1) != 0) {
            this.field_73886_k.func_78276_b("Pipe is loose.", 10, 16, 16448255);
         }

         if((((GT_Container_MultiMachine)this.mContainer).mDisplayErrorCode & 2) != 0) {
            this.field_73886_k.func_78276_b("Screws are missing.", 10, 24, 16448255);
         }

         if((((GT_Container_MultiMachine)this.mContainer).mDisplayErrorCode & 4) != 0) {
            this.field_73886_k.func_78276_b("Something is stuck.", 10, 32, 16448255);
         }

         if((((GT_Container_MultiMachine)this.mContainer).mDisplayErrorCode & 8) != 0) {
            this.field_73886_k.func_78276_b("Platings are dented.", 10, 40, 16448255);
         }

         if((((GT_Container_MultiMachine)this.mContainer).mDisplayErrorCode & 16) != 0) {
            this.field_73886_k.func_78276_b("Circuitry burned out.", 10, 48, 16448255);
         }

         if((((GT_Container_MultiMachine)this.mContainer).mDisplayErrorCode & 32) != 0) {
            this.field_73886_k.func_78276_b("That doesn\'t belong there.", 10, 56, 16448255);
         }

         if((((GT_Container_MultiMachine)this.mContainer).mDisplayErrorCode & 64) != 0) {
            this.field_73886_k.func_78276_b("Incomplete Structure.", 10, 64, 16448255);
         }

         if(((GT_Container_MultiMachine)this.mContainer).mDisplayErrorCode == 0) {
            if(((GT_Container_MultiMachine)this.mContainer).mActive == 0) {
               this.field_73886_k.func_78276_b("Hit with Rubber Hammer", 10, 16, 16448255);
               this.field_73886_k.func_78276_b("to (re-)start the Machine", 10, 24, 16448255);
               this.field_73886_k.func_78276_b("if it doesn\'t start.", 10, 32, 16448255);
            } else {
               this.field_73886_k.func_78276_b("Running perfectly.", 10, 16, 16448255);
            }
         }
      }

   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
   }
}
