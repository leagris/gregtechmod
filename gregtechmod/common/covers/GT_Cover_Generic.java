package gregtechmod.common.covers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_Cover_Generic extends GT_CoverBehavior {

   public GT_Cover_Generic() {
      GregTech_API.sGenericBehavior = this;
   }

   public boolean isSimpleCover() {
      return true;
   }

   public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      aCoverVariable = aCoverVariable + 1 & 15;
      GT_Utility.sendChatToPlayer(aPlayer, ((aCoverVariable & 1) != 0?"Redstone ":"") + ((aCoverVariable & 2) != 0?"Energy ":"") + ((aCoverVariable & 4) != 0?"Fluids ":"") + ((aCoverVariable & 8) != 0?"Items ":""));
      return aCoverVariable;
   }

   public boolean letsRedstoneGoIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (aCoverVariable & 1) != 0;
   }

   public boolean letsRedstoneGoOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (aCoverVariable & 1) != 0;
   }

   public boolean letsEnergyIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (aCoverVariable & 2) != 0;
   }

   public boolean letsEnergyOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (aCoverVariable & 2) != 0;
   }

   public boolean letsLiquidIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (aCoverVariable & 4) != 0;
   }

   public boolean letsLiquidOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (aCoverVariable & 4) != 0;
   }

   public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (aCoverVariable & 8) != 0;
   }

   public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (aCoverVariable & 8) != 0;
   }
}
