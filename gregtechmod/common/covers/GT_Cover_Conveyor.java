package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class GT_Cover_Conveyor extends GT_CoverBehavior {

   public GT_Cover_Conveyor(ItemStack aStack) {
      super(aStack);
   }

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      if(aCoverVariable % 6 > 1 && aTileEntity instanceof IMachineProgress && ((IMachineProgress)aTileEntity).isAllowedToWork() != aCoverVariable % 6 < 4) {
         return aCoverVariable;
      } else {
         TileEntity tTileEntity = aTileEntity.getTileEntityAtSide(aSide);
         if((aCoverVariable % 2 != 1 || aSide != 1) && (aCoverVariable % 2 != 0 || aSide != 0) && aTileEntity.getUniversalEnergyCapacity() >= 128) {
            if(aTileEntity.isUniversalEnergyStored(128)) {
               aTileEntity.decreaseStoredEnergyUnits(GT_Utility.moveOneItemStack(aCoverVariable % 2 == 0?aTileEntity:tTileEntity, aCoverVariable % 2 != 0?aTileEntity:tTileEntity, aCoverVariable % 2 != 0?GT_Utility.getOppositeSide(aSide):aSide, aCoverVariable % 2 == 0?GT_Utility.getOppositeSide(aSide):aSide, (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1), true);
            }
         } else {
            GT_Utility.moveOneItemStack(aCoverVariable % 2 == 0?aTileEntity:tTileEntity, aCoverVariable % 2 != 0?aTileEntity:tTileEntity, aCoverVariable % 2 != 0?GT_Utility.getOppositeSide(aSide):aSide, aCoverVariable % 2 == 0?GT_Utility.getOppositeSide(aSide):aSide, (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1);
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

   public boolean letsLiquidIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsLiquidOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable >= 6 || aCoverVariable % 2 != 0;
   }

   public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable >= 6 || aCoverVariable % 2 == 0;
   }

   public boolean alwaysLookConnected(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)10;
   }
}
