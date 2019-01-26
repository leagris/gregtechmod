package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricInventoryManager;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_ElectricInventoryManager extends GT_GUIContainerMetaTile_Machine {

   public GT_GUIContainer_ElectricInventoryManager(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(new GT_Container_ElectricInventoryManager(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/InventoryManager.png");
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null) {
         this.func_73729_b(x + 4, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[0] * 18, 202, 18, 54);
         this.func_73729_b(x + 60, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[1] * 18, 202, 18, 54);
         this.func_73729_b(x + 79, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[2] * 18, 202, 18, 54);
         this.func_73729_b(x + 135, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[3] * 18, 202, 18, 54);
         this.func_73729_b(x + 23, y + 59, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[0] * 18 + 126, 166, 18, 18);
         this.func_73729_b(x + 41, y + 59, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[1] * 18 + 126, 166, 18, 18);
         this.func_73729_b(x + 98, y + 59, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[2] * 18 + 126, 166, 18, 18);
         this.func_73729_b(x + 116, y + 59, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[3] * 18 + 126, 166, 18, 18);
         this.func_73729_b(x + 4, y + 59, 108, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetEnergy & 1) != 0?184:166, 18, 18);
         this.func_73729_b(x + 60, y + 59, 108, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetEnergy & 2) != 0?184:166, 18, 18);
         this.func_73729_b(x + 79, y + 59, 108, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetEnergy & 4) != 0?184:166, 18, 18);
         this.func_73729_b(x + 135, y + 59, 108, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetEnergy & 8) != 0?184:166, 18, 18);
         byte i = -1;
         int var10001 = x + 23;
         int var10002 = y + 4;
         int var7 = i + 1;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 23;
         var10002 = y + 22;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 23;
         var10002 = y + 40;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 41;
         var10002 = y + 4;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 41;
         var10002 = y + 22;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 41;
         var10002 = y + 40;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 98;
         var10002 = y + 4;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 98;
         var10002 = y + 22;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 98;
         var10002 = y + 40;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 116;
         var10002 = y + 4;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 116;
         var10002 = y + 22;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
         var10001 = x + 116;
         var10002 = y + 40;
         ++var7;
         this.func_73729_b(var10001, var10002, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[var7] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << var7) != 0?184:166, 18, 18);
      }

   }
}
