package gregtechmod.api.gui;

import gregtechmod.api.gui.GT_Container_BasicMachine;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_BasicMachine extends GT_GUIContainerMetaTile_Machine {

   String mName;
   public final byte mProgressBarDirection;
   public final byte mProgressBarAmount;


   public GT_GUIContainer_BasicMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName, String aTextureFile) {
      this(aInventoryPlayer, aTileEntity, aName, aTextureFile, (byte)0, (byte)1);
   }

   public GT_GUIContainer_BasicMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aName, String aTextureFile, byte aProgressBarDirection, byte aProgressBarAmount) {
      super(new GT_Container_BasicMachine(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/" + aTextureFile);
      this.mName = "";
      this.mProgressBarDirection = aProgressBarDirection;
      this.mProgressBarAmount = (byte)Math.max(1, aProgressBarAmount);
      this.mName = aName;
   }

   protected void func_74189_g(int par1, int par2) {
      this.field_73886_k.func_78276_b(this.mName, 8, 4, 4210752);
   }

   protected void func_74185_a(float par1, int par2, int par3) {
      super.func_74185_a(par1, par2, par3);
      int x = (this.field_73880_f - this.field_74194_b) / 2;
      int y = (this.field_73881_g - this.field_74195_c) / 2;
      this.func_73729_b(x, y, 0, 0, this.field_74194_b, this.field_74195_c);
      if(this.mContainer != null) {
         if(((GT_Container_BasicMachine)this.mContainer).mOutputting) {
            this.func_73729_b(x + 7, y + 62, 176, 18, 18, 18);
         }

         if(((GT_Container_BasicMachine)this.mContainer).mItemTransfer) {
            this.func_73729_b(x + 25, y + 62, 176, 36, 18, 18);
         }

         if(((GT_Container_BasicMachine)this.mContainer).mSeperatedInputs) {
            this.func_73729_b(x + 43, y + 62, 176, 54, 18, 18);
         }

         if(this.mContainer.mMaxProgressTime > 0) {
            int tSize = this.mProgressBarDirection < 2?20:18;
            int tProgress = Math.max(1, Math.min(tSize * this.mProgressBarAmount, (this.mContainer.mProgressTime > 0?1:0) + this.mContainer.mProgressTime * tSize * this.mProgressBarAmount / this.mContainer.mMaxProgressTime)) % (tSize + 1);
            switch(this.mProgressBarDirection) {
            case 0:
               this.func_73729_b(x + 78, y + 24, 176, 0, tProgress, 18);
               break;
            case 1:
               this.func_73729_b(x + 78 + 20 - tProgress, y + 24, 196 - tProgress, 0, tProgress, 18);
               break;
            case 2:
               this.func_73729_b(x + 78, y + 24, 176, 0, 20, tProgress);
               break;
            case 3:
               this.func_73729_b(x + 78, y + 24 + 18 - tProgress, 176, 18 - tProgress, 20, tProgress);
               break;
            case 4:
               tProgress = 20 - tProgress;
               this.func_73729_b(x + 78, y + 24, 176, 0, tProgress, 18);
               break;
            case 5:
               tProgress = 20 - tProgress;
               this.func_73729_b(x + 78 + 20 - tProgress, y + 24, 196 - tProgress, 0, tProgress, 18);
               break;
            case 6:
               tProgress = 18 - tProgress;
               this.func_73729_b(x + 78, y + 24, 176, 0, 20, tProgress);
               break;
            case 7:
               tProgress = 18 - tProgress;
               this.func_73729_b(x + 78, y + 24 + 18 - tProgress, 176, 18 - tProgress, 20, tProgress);
            }
         }
      }

   }
}
