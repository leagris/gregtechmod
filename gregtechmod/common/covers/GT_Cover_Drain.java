package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_Cover_Drain extends GT_CoverBehavior {

   public GT_Cover_Drain(ItemStack aStack) {
      super(aStack);
   }

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      if(aCoverVariable % 3 > 1 && aTileEntity instanceof IMachineProgress && ((IMachineProgress)aTileEntity).isAllowedToWork() != aCoverVariable % 3 < 2) {
         return aCoverVariable;
      } else {
         if(aSide != 6) {
            Block tBlock = aTileEntity.getBlockAtSide(aSide);
            if(aCoverVariable < 3 && aTileEntity instanceof IFluidHandler) {
               if(aSide == 1 && aTileEntity.getWorld().func_72896_J() && aTileEntity.getWorld().func_72874_g(aTileEntity.getXCoord(), aTileEntity.getZCoord()) - 2 < aTileEntity.getYCoord()) {
                  int tLiquid = (int)(aTileEntity.getBiome().field_76751_G * 10.0F);
                  if(tLiquid > 0) {
                     ((IFluidHandler)aTileEntity).fill(ForgeDirection.getOrientation(aSide), GT_ModHandler.getWater(aTileEntity.getWorld().func_72911_I()?(long)(tLiquid * 2):(long)tLiquid), true);
                  }
               }

               FluidStack tLiquid1 = null;
               if(tBlock != null) {
                  if((tBlock == Block.field_71943_B || tBlock == Block.field_71942_A) && aTileEntity.getMetaIDAtSide(aSide) == 0) {
                     tLiquid1 = GT_ModHandler.getWater(1000L);
                  } else if((tBlock == Block.field_71938_D || tBlock == Block.field_71944_C) && aTileEntity.getMetaIDAtSide(aSide) == 0) {
                     tLiquid1 = GT_ModHandler.getLava(1000L);
                  } else if(tBlock instanceof IFluidBlock) {
                     tLiquid1 = ((IFluidBlock)tBlock).drain(aTileEntity.getWorld(), aTileEntity.getOffsetX(aSide, 1), aTileEntity.getOffsetY(aSide, 1), aTileEntity.getOffsetZ(aSide, 1), false);
                  }

                  if(tLiquid1 != null && tLiquid1.getFluid() != null && (aSide > 1 || aSide == 0 && tLiquid1.getFluid().getDensity() <= 0 || aSide == 1 && tLiquid1.getFluid().getDensity() >= 0) && ((IFluidHandler)aTileEntity).fill(ForgeDirection.getOrientation(aSide), tLiquid1, false) == tLiquid1.amount) {
                     ((IFluidHandler)aTileEntity).fill(ForgeDirection.getOrientation(aSide), tLiquid1, true);
                     aTileEntity.getWorld().func_94571_i(aTileEntity.getXCoord() + ForgeDirection.getOrientation(aSide).offsetX, aTileEntity.getYCoord() + ForgeDirection.getOrientation(aSide).offsetY, aTileEntity.getZCoord() + ForgeDirection.getOrientation(aSide).offsetZ);
                  }
               }
            }

            if(aCoverVariable >= 3 && tBlock != null && (tBlock == Block.field_71938_D || tBlock == Block.field_71944_C || tBlock == Block.field_71943_B || tBlock == Block.field_71942_A || tBlock instanceof IFluidBlock)) {
               aTileEntity.getWorld().func_72832_d(aTileEntity.getOffsetX(aSide, 1), aTileEntity.getOffsetY(aSide, 1), aTileEntity.getOffsetZ(aSide, 1), 0, 0, 0);
            }
         }

         return aCoverVariable;
      }
   }

   public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      aCoverVariable = (aCoverVariable + 1) % 6;
      if(aCoverVariable == 0) {
         GT_Utility.sendChatToPlayer(aPlayer, "Import");
      }

      if(aCoverVariable == 1) {
         GT_Utility.sendChatToPlayer(aPlayer, "Import (conditional)");
      }

      if(aCoverVariable == 2) {
         GT_Utility.sendChatToPlayer(aPlayer, "Import (invert cond)");
      }

      if(aCoverVariable == 3) {
         GT_Utility.sendChatToPlayer(aPlayer, "Keep Liquids Away");
      }

      if(aCoverVariable == 4) {
         GT_Utility.sendChatToPlayer(aPlayer, "Keep Liquids Away (conditional)");
      }

      if(aCoverVariable == 5) {
         GT_Utility.sendChatToPlayer(aPlayer, "Keep Liquids Away (invert cond)");
      }

      return aCoverVariable;
   }

   public boolean letsLiquidIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable <= 1 || !(aTileEntity instanceof IMachineProgress) || ((IMachineProgress)aTileEntity).isAllowedToWork() == aCoverVariable < 2;
   }

   public boolean alwaysLookConnected(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)(aCoverVariable < 3?50:1);
   }
}
