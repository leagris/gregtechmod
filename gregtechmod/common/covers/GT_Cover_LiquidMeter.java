package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_Cover_LiquidMeter extends GT_CoverBehavior {

   public GT_Cover_LiquidMeter(ItemStack aStack) {
      super(aStack);
   }

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      if(aTileEntity instanceof IFluidHandler) {
         FluidTankInfo[] tTanks = ((IFluidHandler)aTileEntity).getTankInfo(ForgeDirection.UNKNOWN);
         long tAll = 0L;
         long tFull = 0L;
         if(tTanks != null) {
            FluidTankInfo[] arr$ = tTanks;
            int len$ = tTanks.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               FluidTankInfo tTank = arr$[i$];
               if(tTank != null) {
                  tAll += (long)tTank.capacity;
                  FluidStack tLiquid = tTank.fluid;
                  if(tLiquid != null) {
                     tFull += (long)tLiquid.amount;
                  }
               }
            }
         }

         tAll /= 15L;
         if(tAll > 0L) {
            aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable == 0?(byte)((int)(tFull / tAll)):(byte)((int)(15L - tFull / tAll)));
         } else {
            aTileEntity.setOutputRedstoneSignal(aSide, (byte)(aCoverVariable == 0?0:15));
         }
      } else {
         aTileEntity.setOutputRedstoneSignal(aSide, (byte)0);
      }

      return aCoverVariable;
   }

   public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(aCoverVariable == 0) {
         GT_Utility.sendChatToPlayer(aPlayer, "Inverted");
      } else {
         GT_Utility.sendChatToPlayer(aPlayer, "Normal");
      }

      return aCoverVariable == 0?1:0;
   }

   public boolean letsEnergyIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsEnergyOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsLiquidIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsLiquidOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean manipulatesSidedRedstoneOutput(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)5;
   }
}
