package gregtechmod.common.covers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract class GT_Cover_RedstoneWirelessBase extends GT_CoverBehavior {

   public GT_Cover_RedstoneWirelessBase(ItemStack aStack) {
      super(aStack);
   }

   public boolean onCoverRemoval(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, boolean aForced) {
      GregTech_API.sWirelessRedstone.put(Integer.valueOf(aCoverVariable), Byte.valueOf((byte)0));
      return true;
   }

   public boolean onCoverRightclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(((double)aX > 0.375D && (double)aX < 0.625D || aSide > 3) && ((double)aY > 0.375D && (double)aY < 0.625D || aSide < 2) && ((double)aZ > 0.375D && (double)aZ < 0.625D || aSide == 2 || aSide == 3)) {
         GregTech_API.sWirelessRedstone.put(Integer.valueOf(aCoverVariable), Byte.valueOf((byte)0));
         aCoverVariable = GT_Utility.stackToInt(aPlayer.field_71071_by.func_70448_g());
         aTileEntity.setCoverDataAtSide(aSide, aCoverVariable);
         GT_Utility.sendChatToPlayer(aPlayer, "Frequency: " + aCoverVariable);
         return true;
      } else {
         return false;
      }
   }

   public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(((double)aX <= 0.375D || (double)aX >= 0.625D) && aSide <= 3 || ((double)aY <= 0.375D || (double)aY >= 0.625D) && aSide >= 2 || ((double)aZ <= 0.375D || (double)aZ >= 0.625D) && aSide != 2 && aSide != 3) {
         GregTech_API.sWirelessRedstone.put(Integer.valueOf(aCoverVariable), Byte.valueOf((byte)0));
         float[] tCoords = GT_Utility.getClickedFacingCoords(aSide, aX, aY, aZ);
         switch((byte)((byte)((int)(tCoords[0] * 2.0F)) + 2 * (byte)((int)(tCoords[1] * 2.0F)))) {
         case 0:
            aCoverVariable -= 32;
            break;
         case 1:
            aCoverVariable += 32;
            break;
         case 2:
            aCoverVariable -= 1024;
            break;
         case 3:
            aCoverVariable += 1024;
         }
      }

      GT_Utility.sendChatToPlayer(aPlayer, "Frequency: " + aCoverVariable);
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

   public String getDescription(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return "Frequency: " + aCoverVariable;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)1;
   }
}
