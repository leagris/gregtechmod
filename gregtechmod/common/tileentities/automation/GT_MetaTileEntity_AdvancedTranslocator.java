package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_AdvancedTranslocator extends MetaTileEntity {

   public boolean bOutput = true;
   public boolean bInvertFilter = false;
   public byte mInputSide = 0;
   public byte mOutputSide = 0;
   public byte mSuccess = 0;


   public GT_MetaTileEntity_AdvancedTranslocator(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_AdvancedTranslocator() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isOverclockerUpgradable() {
      return true;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public boolean isOutputFacing(byte aSide) {
      return this.getBaseMetaTileEntity().getFrontFacing() == aSide || this.getBaseMetaTileEntity().getBackFacing() == aSide;
   }

   public int getMinimumStoredEU() {
      return 2000;
   }

   public int maxEUInput() {
      return 32;
   }

   public int maxEUOutput() {
      return this.bOutput?32:0;
   }

   public int maxEUStore() {
      return 10000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public int getInvSize() {
      return 10;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 104);
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean connectsToItemPipe(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing() || aSide == this.getBaseMetaTileEntity().getBackFacing();
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_AdvancedTranslocator();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74757_a("bOutput", this.bOutput);
      aNBT.func_74757_a("bInvertFilter", this.bInvertFilter);
      aNBT.func_74768_a("mInputSide", this.mInputSide);
      aNBT.func_74768_a("mOutputSide", this.mOutputSide);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.bOutput = aNBT.func_74767_n("bOutput");
      this.bInvertFilter = aNBT.func_74767_n("bInvertFilter");
      this.mInputSide = (byte)aNBT.func_74762_e("mInputSide");
      this.mOutputSide = (byte)aNBT.func_74762_e("mOutputSide");
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isUniversalEnergyStored(2000) && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || this.getBaseMetaTileEntity().getTimer() % (long)(197 - this.getBaseMetaTileEntity().getOverclockerUpgradeCount() * 49) == 0L || this.getBaseMetaTileEntity().getTimer() % 5L == 0L && this.mSuccess > 0 || this.mSuccess >= 20)) {
         --this.mSuccess;
         ArrayList tList = new ArrayList();

         int tCost;
         for(tCost = 0; tCost < 9; ++tCost) {
            tList.add(this.mInventory[tCost]);
         }

         tCost = (this.getBaseMetaTileEntity().getOverclockerUpgradeCount() + 1) * GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getFrontFacing()), this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing()), this.mInputSide, this.mOutputSide, tList, this.bInvertFilter, (byte)64, (byte)1, (byte)64, (byte)1) * (this.mInventory[0] == null && this.mInventory[1] == null && this.mInventory[2] == null && this.mInventory[3] == null && this.mInventory[4] == null && this.mInventory[5] == null && this.mInventory[6] == null && this.mInventory[7] == null && this.mInventory[8] == null?1:2);
         if(tCost > 0) {
            this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCost, true);
            this.mSuccess = 30;
         }
      }

   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing() && aSide != this.getBaseMetaTileEntity().getBackFacing();
   }

   public String getDescription() {
      return "No matter how often you click that dang Button, the second Facing WON\'T CHANGE!!!";
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == aFacing) {
         return 112 + (aRedstone?8:0);
      } else if(GT_Utility.getOppositeSide(aSide) == aFacing) {
         return 113 + (aRedstone?8:0);
      } else {
         int tIndex = 129 + (aRedstone?8:0);
         switch(aFacing) {
         case 0:
            return tIndex + 64;
         case 1:
            return tIndex + 32;
         case 2:
            switch(aSide) {
            case 0:
               return tIndex + 32;
            case 1:
               return tIndex + 32;
            case 2:
            case 3:
            default:
               break;
            case 4:
               return tIndex + 16;
            case 5:
               return tIndex + 48;
            }
         case 3:
            switch(aSide) {
            case 0:
               return tIndex + 64;
            case 1:
               return tIndex + 64;
            case 2:
            case 3:
            default:
               break;
            case 4:
               return tIndex + 48;
            case 5:
               return tIndex + 16;
            }
         case 4:
            switch(aSide) {
            case 0:
               return tIndex + 48;
            case 1:
               return tIndex + 16;
            case 2:
               return tIndex + 48;
            case 3:
               return tIndex + 16;
            }
         case 5:
            switch(aSide) {
            case 0:
               return tIndex + 16;
            case 1:
               return tIndex + 48;
            case 2:
               return tIndex + 16;
            case 3:
               return tIndex + 48;
            }
         default:
            return tIndex;
         }
      }
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }
}
