package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_CircuitryBehavior;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.containers.GT_Container_RedstoneCircuitBlock;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_RedstoneCircuitBlock extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_RedstoneCircuitBlock(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_RedstoneCircuitBlock(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/RedstoneCircuitBlock.png");
   }

   protected void func_74189_g(int par1, int par2) {
      GT_CircuitryBehavior tCircuit = (GT_CircuitryBehavior)GregTech_API.sCircuitryBehaviors.get(Integer.valueOf(((GT_Container_RedstoneCircuitBlock)this.mContainer).mGate));
      if(tCircuit != null) {
         this.field_73886_k.func_78276_b(tCircuit.getName(), 46, 8, 16448255);
         this.field_73886_k.func_78276_b(tCircuit.getDescription(), 46, 19, 16448255);
         this.field_73886_k.func_78276_b(tCircuit.getDataDescription(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData, 0), 46, 33, 16448255);
         this.field_73886_k.func_78276_b(tCircuit.getDataDescription(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData, 1), 46, 44, 16448255);
         this.field_73886_k.func_78276_b(tCircuit.getDataDescription(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData, 2), 46, 55, 16448255);
         this.field_73886_k.func_78276_b(tCircuit.getDataDescription(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData, 3), 46, 66, 16448255);
         String tString = tCircuit.getDataDisplay(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData, 0);
         this.field_73886_k.func_78276_b(tString == null?GT_Utility.parseNumberToString(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData[0]):tString, 99, 33, 16448255);
         tString = tCircuit.getDataDisplay(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData, 1);
         this.field_73886_k.func_78276_b(tString == null?GT_Utility.parseNumberToString(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData[1]):tString, 99, 44, 16448255);
         tString = tCircuit.getDataDisplay(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData, 2);
         this.field_73886_k.func_78276_b(tString == null?GT_Utility.parseNumberToString(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData[2]):tString, 99, 55, 16448255);
         tString = tCircuit.getDataDisplay(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData, 3);
         this.field_73886_k.func_78276_b(tString == null?GT_Utility.parseNumberToString(((GT_Container_RedstoneCircuitBlock)this.mContainer).mData[3]):tString, 99, 66, 16448255);
      }

   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null) {
         if(((GT_Container_RedstoneCircuitBlock)this.mContainer).mOutput > 0) {
            this.func_73729_b(x + 151, y + 5, 176, 0, 18, 18);
         }

         if((((GT_Container_RedstoneCircuitBlock)this.mContainer).mActive & 1) > 0) {
            this.func_73729_b(x + 151, y + 23, 176, 18, 18, 18);
         }

         if(((GT_Container_RedstoneCircuitBlock)this.mContainer).mDisplayErrorCode > 0) {
            if(((GT_Container_RedstoneCircuitBlock)this.mContainer).mTileEntity.getTimer() / 5L % 2L == 0L) {
               this.func_73729_b(x + 140, y + 9, 194, 0, 7, 7);
            }
         } else {
            this.func_73729_b(x + 140, y + 9, 201, 0, 7, 7);
         }
      }

   }
}
