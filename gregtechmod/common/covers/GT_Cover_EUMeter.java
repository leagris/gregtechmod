package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Cover_EUMeter extends GT_CoverBehavior {

   public GT_Cover_EUMeter(ItemStack aStack) {
      super(aStack);
   }

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      boolean tScale = false;
      int tScale1;
      if(aCoverVariable < 2) {
         tScale1 = aTileEntity.getUniversalEnergyCapacity() / 15;
         if(tScale1 > 0) {
            aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable % 2 == 0?(byte)(aTileEntity.getUniversalEnergyStored() / tScale1):(byte)(15 - aTileEntity.getUniversalEnergyStored() / tScale1));
         } else {
            aTileEntity.setOutputRedstoneSignal(aSide, (byte)(aCoverVariable % 2 == 0?0:15));
         }
      } else if(aCoverVariable < 4) {
         tScale1 = aTileEntity.getEUCapacity() / 15;
         if(tScale1 > 0) {
            aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable % 2 == 0?(byte)(aTileEntity.getStoredEU() / tScale1):(byte)(15 - aTileEntity.getStoredEU() / tScale1));
         } else {
            aTileEntity.setOutputRedstoneSignal(aSide, (byte)(aCoverVariable % 2 == 0?0:15));
         }
      } else if(aCoverVariable < 6) {
         tScale1 = aTileEntity.getMJCapacity() / 15;
         if(tScale1 > 0) {
            aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable % 2 == 0?(byte)(aTileEntity.getStoredMJ() / tScale1):(byte)(15 - aTileEntity.getStoredMJ() / tScale1));
         } else {
            aTileEntity.setOutputRedstoneSignal(aSide, (byte)(aCoverVariable % 2 == 0?0:15));
         }
      } else if(aCoverVariable < 8) {
         tScale1 = aTileEntity.getSteamCapacity() / 15;
         if(tScale1 > 0) {
            aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable % 2 == 0?(byte)(aTileEntity.getStoredSteam() / tScale1):(byte)(15 - aTileEntity.getStoredSteam() / tScale1));
         } else {
            aTileEntity.setOutputRedstoneSignal(aSide, (byte)(aCoverVariable % 2 == 0?0:15));
         }
      } else if(aCoverVariable < 10) {
         tScale1 = aTileEntity.getInputVoltage() / 15;
         if(tScale1 > 0) {
            aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable % 2 == 0?(byte)(aTileEntity.getAverageElectricInput() / tScale1):(byte)(15 - aTileEntity.getAverageElectricInput() / tScale1));
         } else {
            aTileEntity.setOutputRedstoneSignal(aSide, (byte)(aCoverVariable % 2 == 0?0:15));
         }
      } else if(aCoverVariable < 12) {
         tScale1 = aTileEntity.getOutputVoltage() * aTileEntity.getOutputAmperage() / 15;
         if(tScale1 > 0) {
            aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable % 2 == 0?(byte)(aTileEntity.getAverageElectricOutput() / tScale1):(byte)(15 - aTileEntity.getAverageElectricOutput() / tScale1));
         } else {
            aTileEntity.setOutputRedstoneSignal(aSide, (byte)(aCoverVariable % 2 == 0?0:15));
         }
      }

      return aCoverVariable;
   }

   public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      aCoverVariable = (aCoverVariable + 1) % 12;
      if(aCoverVariable == 0) {
         GT_Utility.sendChatToPlayer(aPlayer, "Normal Universal Storage");
      }

      if(aCoverVariable == 1) {
         GT_Utility.sendChatToPlayer(aPlayer, "Inverted Universal Storage");
      }

      if(aCoverVariable == 2) {
         GT_Utility.sendChatToPlayer(aPlayer, "Normal Electricity Storage");
      }

      if(aCoverVariable == 3) {
         GT_Utility.sendChatToPlayer(aPlayer, "Inverted Electricity Storage");
      }

      if(aCoverVariable == 4) {
         GT_Utility.sendChatToPlayer(aPlayer, "Normal MJ Storage");
      }

      if(aCoverVariable == 5) {
         GT_Utility.sendChatToPlayer(aPlayer, "Inverted MJ Storage");
      }

      if(aCoverVariable == 6) {
         GT_Utility.sendChatToPlayer(aPlayer, "Normal Steam Storage");
      }

      if(aCoverVariable == 7) {
         GT_Utility.sendChatToPlayer(aPlayer, "Inverted Steam Storage");
      }

      if(aCoverVariable == 8) {
         GT_Utility.sendChatToPlayer(aPlayer, "Normal Average Electric Input");
      }

      if(aCoverVariable == 9) {
         GT_Utility.sendChatToPlayer(aPlayer, "Inverted Average Electric Input");
      }

      if(aCoverVariable == 10) {
         GT_Utility.sendChatToPlayer(aPlayer, "Normal Average Electric Output");
      }

      if(aCoverVariable == 11) {
         GT_Utility.sendChatToPlayer(aPlayer, "Inverted Average Electric Output");
      }

      return aCoverVariable;
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
