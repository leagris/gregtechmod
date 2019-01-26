package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract class GT_CoverBehavior {

   public static volatile int VERSION = 408;


   public GT_CoverBehavior(ItemStack[] aCovers) {
      ItemStack[] arr$ = aCovers;
      int len$ = aCovers.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ItemStack tCover = arr$[i$];
         GregTech_API.sCoverBehaviors.put(Integer.valueOf(GT_Utility.stackToInt(tCover)), this);
      }

   }

   public GT_CoverBehavior(int[] aCovers) {
      int[] arr$ = aCovers;
      int len$ = aCovers.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         int tCover = arr$[i$];
         GregTech_API.sCoverBehaviors.put(Integer.valueOf(tCover), this);
      }

   }

   public GT_CoverBehavior(ItemStack aCover) {
      GregTech_API.sCoverBehaviors.put(Integer.valueOf(GT_Utility.stackToInt(aCover)), this);
   }

   public GT_CoverBehavior(int aCover) {
      GregTech_API.sCoverBehaviors.put(Integer.valueOf(aCover), this);
   }

   public GT_CoverBehavior() {}

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return aCoverVariable;
   }

   public boolean onCoverRightclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      return false;
   }

   public boolean onCoverRightclickClient(byte aSide, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      return false;
   }

   public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      return aCoverVariable;
   }

   public boolean isCoverPlaceable(byte aSide, int aCoverID, ICoverable aTileEntity) {
      return true;
   }

   public boolean onCoverRemoval(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, boolean aForced) {
      return true;
   }

   public String getDescription(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return "";
   }

   public float getBlastProofLevel(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return 30.0F;
   }

   public boolean letsRedstoneGoIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public boolean letsRedstoneGoOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public boolean letsEnergyIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public boolean letsEnergyOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public boolean letsLiquidIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public boolean letsLiquidOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public boolean isGUIClickable(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public boolean manipulatesSidedRedstoneOutput(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public boolean alwaysLookConnected(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return false;
   }

   public byte getRedstoneInput(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return this.letsRedstoneGoIn(aSide, aCoverID, aCoverVariable, aTileEntity)?aInputRedstone:0;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)0;
   }

   public boolean isSimpleCover() {
      return false;
   }

}
