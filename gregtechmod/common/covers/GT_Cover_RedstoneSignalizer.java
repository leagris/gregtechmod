package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Cover_RedstoneSignalizer extends GT_CoverBehavior {

   public GT_Cover_RedstoneSignalizer(ItemStack aStack) {
      super(aStack);
   }

   public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      aCoverVariable = (aCoverVariable + 1) % 48;
      if(aCoverVariable / 16 == 0) {
         GT_Utility.sendChatToPlayer(aPlayer, "Signal = " + (aCoverVariable & 15));
      } else if(aCoverVariable / 16 == 1) {
         GT_Utility.sendChatToPlayer(aPlayer, "Conditional Signal = " + (aCoverVariable & 15));
      } else if(aCoverVariable / 16 == 2) {
         GT_Utility.sendChatToPlayer(aPlayer, "Inverted Conditional Signal = " + (aCoverVariable & 15));
      }

      return aCoverVariable;
   }

   public boolean letsRedstoneGoIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
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

   public byte getRedstoneInput(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      if(aCoverVariable < 16) {
         return (byte)(aCoverVariable & 15);
      } else if(aTileEntity instanceof IMachineProgress) {
         if(((IMachineProgress)aTileEntity).isAllowedToWork()) {
            if(aCoverVariable / 16 == 1) {
               return (byte)(aCoverVariable & 15);
            }
         } else if(aCoverVariable / 16 == 2) {
            return (byte)(aCoverVariable & 15);
         }

         return (byte)0;
      } else {
         return (byte)(aCoverVariable & 15);
      }
   }
}
