package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Cover_Shutter extends GT_CoverBehavior {

   public GT_Cover_Shutter(ItemStack aStack) {
      super(aStack);
   }

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable;
   }

   public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      aCoverVariable = (aCoverVariable + 1) % 4;
      if(aCoverVariable == 0) {
         GT_Utility.sendChatToPlayer(aPlayer, "Open if work enabled");
      }

      if(aCoverVariable == 1) {
         GT_Utility.sendChatToPlayer(aPlayer, "Open if work disabled");
      }

      if(aCoverVariable == 2) {
         GT_Utility.sendChatToPlayer(aPlayer, "Only Output allowed");
      }

      if(aCoverVariable == 3) {
         GT_Utility.sendChatToPlayer(aPlayer, "Only Input allowed");
      }

      return aCoverVariable;
   }

   public boolean letsRedstoneGoIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable < 2?(aTileEntity instanceof IMachineProgress?(((IMachineProgress)aTileEntity).isAllowedToWork()?aCoverVariable % 2 == 0:aCoverVariable % 2 != 0):true):aCoverVariable == 3;
   }

   public boolean letsRedstoneGoOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable < 2?(aTileEntity instanceof IMachineProgress?(((IMachineProgress)aTileEntity).isAllowedToWork()?aCoverVariable % 2 == 0:aCoverVariable % 2 != 0):true):aCoverVariable == 2;
   }

   public boolean letsEnergyIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable < 2?(aTileEntity instanceof IMachineProgress?(((IMachineProgress)aTileEntity).isAllowedToWork()?aCoverVariable % 2 == 0:aCoverVariable % 2 != 0):true):aCoverVariable == 3;
   }

   public boolean letsEnergyOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable < 2?(aTileEntity instanceof IMachineProgress?(((IMachineProgress)aTileEntity).isAllowedToWork()?aCoverVariable % 2 == 0:aCoverVariable % 2 != 0):true):aCoverVariable == 2;
   }

   public boolean letsLiquidIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable < 2?(aTileEntity instanceof IMachineProgress?(((IMachineProgress)aTileEntity).isAllowedToWork()?aCoverVariable % 2 == 0:aCoverVariable % 2 != 0):true):aCoverVariable == 3;
   }

   public boolean letsLiquidOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable < 2?(aTileEntity instanceof IMachineProgress?(((IMachineProgress)aTileEntity).isAllowedToWork()?aCoverVariable % 2 == 0:aCoverVariable % 2 != 0):true):aCoverVariable == 2;
   }

   public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable < 2?(aTileEntity instanceof IMachineProgress?(((IMachineProgress)aTileEntity).isAllowedToWork()?aCoverVariable % 2 == 0:aCoverVariable % 2 != 0):true):aCoverVariable == 3;
   }

   public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable < 2?(aTileEntity instanceof IMachineProgress?(((IMachineProgress)aTileEntity).isAllowedToWork()?aCoverVariable % 2 == 0:aCoverVariable % 2 != 0):true):aCoverVariable == 2;
   }

   public boolean alwaysLookConnected(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)0;
   }
}
