package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_Cover_Valve extends GT_CoverBehavior {

   public GT_Cover_Valve(ItemStack aStack) {
      super(aStack);
   }

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      if(aCoverVariable % 6 > 1 && aTileEntity instanceof IMachineProgress && ((IMachineProgress)aTileEntity).isAllowedToWork() != aCoverVariable % 6 < 4) {
         return aCoverVariable;
      } else {
         if(aTileEntity instanceof IFluidHandler) {
            IFluidHandler tTank2 = aTileEntity.getITankContainerAtSide(aSide);
            if(tTank2 != null) {
               IFluidHandler tTank1 = (IFluidHandler)aTileEntity;
               FluidStack tLiquid;
               if(aCoverVariable % 2 == 0) {
                  tLiquid = tTank1.drain(ForgeDirection.getOrientation(aSide), 1000, false);
                  if(tLiquid != null) {
                     tLiquid = tLiquid.copy();
                     tLiquid.amount = tTank2.fill(ForgeDirection.getOrientation(aSide).getOpposite(), tLiquid, false);
                     if(tLiquid.amount > 0) {
                        if((aCoverVariable % 2 != 1 || aSide != 1) && (aCoverVariable % 2 != 0 || aSide != 0) && aTileEntity.getUniversalEnergyCapacity() >= Math.min(1, tLiquid.amount / 100)) {
                           if(aTileEntity.isUniversalEnergyStored(Math.min(1, tLiquid.amount / 100))) {
                              aTileEntity.decreaseStoredEnergyUnits(Math.min(1, tLiquid.amount / 100), true);
                              tTank2.fill(ForgeDirection.getOrientation(aSide).getOpposite(), tTank1.drain(ForgeDirection.getOrientation(aSide), tLiquid.amount, true), true);
                           }
                        } else {
                           tTank2.fill(ForgeDirection.getOrientation(aSide).getOpposite(), tTank1.drain(ForgeDirection.getOrientation(aSide), tLiquid.amount, true), true);
                        }
                     }
                  }
               } else {
                  tLiquid = tTank2.drain(ForgeDirection.getOrientation(aSide).getOpposite(), 1000, false);
                  if(tLiquid != null) {
                     tLiquid = tLiquid.copy();
                     tLiquid.amount = tTank1.fill(ForgeDirection.getOrientation(aSide), tLiquid, false);
                     if(tLiquid.amount > 0) {
                        if((aCoverVariable % 2 != 1 || aSide != 1) && (aCoverVariable % 2 != 0 || aSide != 0) && aTileEntity.getUniversalEnergyCapacity() >= Math.min(1, tLiquid.amount / 100)) {
                           if(aTileEntity.isUniversalEnergyStored(Math.min(1, tLiquid.amount / 100))) {
                              aTileEntity.decreaseStoredEnergyUnits(Math.min(1, tLiquid.amount / 100), true);
                              tTank1.fill(ForgeDirection.getOrientation(aSide), tTank2.drain(ForgeDirection.getOrientation(aSide).getOpposite(), tLiquid.amount, true), true);
                           }
                        } else {
                           tTank1.fill(ForgeDirection.getOrientation(aSide), tTank2.drain(ForgeDirection.getOrientation(aSide).getOpposite(), tLiquid.amount, true), true);
                        }
                     }
                  }
               }
            }
         }

         return aCoverVariable;
      }
   }

   public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      aCoverVariable = (aCoverVariable + 1) % 12;
      if(aCoverVariable == 0) {
         GT_Utility.sendChatToPlayer(aPlayer, "Export");
      }

      if(aCoverVariable == 1) {
         GT_Utility.sendChatToPlayer(aPlayer, "Import");
      }

      if(aCoverVariable == 2) {
         GT_Utility.sendChatToPlayer(aPlayer, "Export (conditional)");
      }

      if(aCoverVariable == 3) {
         GT_Utility.sendChatToPlayer(aPlayer, "Import (conditional)");
      }

      if(aCoverVariable == 4) {
         GT_Utility.sendChatToPlayer(aPlayer, "Export (invert cond)");
      }

      if(aCoverVariable == 5) {
         GT_Utility.sendChatToPlayer(aPlayer, "Import (invert cond)");
      }

      if(aCoverVariable == 6) {
         GT_Utility.sendChatToPlayer(aPlayer, "Export allow Input");
      }

      if(aCoverVariable == 7) {
         GT_Utility.sendChatToPlayer(aPlayer, "Import allow Output");
      }

      if(aCoverVariable == 8) {
         GT_Utility.sendChatToPlayer(aPlayer, "Export allow Input (conditional)");
      }

      if(aCoverVariable == 9) {
         GT_Utility.sendChatToPlayer(aPlayer, "Import allow Output (conditional)");
      }

      if(aCoverVariable == 10) {
         GT_Utility.sendChatToPlayer(aPlayer, "Export allow Input (invert cond)");
      }

      if(aCoverVariable == 11) {
         GT_Utility.sendChatToPlayer(aPlayer, "Import allow Output (invert cond)");
      }

      return aCoverVariable;
   }

   public boolean letsRedstoneGoIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsRedstoneGoOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsEnergyIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsEnergyOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsLiquidIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable > 1 && aTileEntity instanceof IMachineProgress && ((IMachineProgress)aTileEntity).isAllowedToWork() != aCoverVariable % 6 < 4?false:aCoverVariable >= 6 || aCoverVariable % 2 != 0;
   }

   public boolean letsLiquidOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable > 1 && aTileEntity instanceof IMachineProgress && ((IMachineProgress)aTileEntity).isAllowedToWork() != aCoverVariable % 6 < 4?false:aCoverVariable >= 6 || aCoverVariable % 2 == 0;
   }

   public boolean alwaysLookConnected(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)1;
   }
}
