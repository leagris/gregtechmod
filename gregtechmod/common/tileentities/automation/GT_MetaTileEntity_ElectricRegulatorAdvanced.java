package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import java.util.Arrays;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricRegulatorAdvanced extends GT_MetaTileEntity_ElectricBufferSmall {

   public int[] mTargetSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};


   public GT_MetaTileEntity_ElectricRegulatorAdvanced(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_ElectricRegulatorAdvanced() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isOverclockerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public int maxEUInput() {
      return 128;
   }

   public int maxEUPulses() {
      return 4;
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

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 123);
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 9;
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getBackFacing();
   }

   public int getMinimumStoredEU() {
      return 1000;
   }

   public int getInvSize() {
      return 19;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricRegulatorAdvanced();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74768_a("mTargetSlot1", this.mTargetSlots[0]);
      aNBT.func_74768_a("mTargetSlot2", this.mTargetSlots[1]);
      aNBT.func_74768_a("mTargetSlot3", this.mTargetSlots[2]);
      aNBT.func_74768_a("mTargetSlot4", this.mTargetSlots[3]);
      aNBT.func_74768_a("mTargetSlot5", this.mTargetSlots[4]);
      aNBT.func_74768_a("mTargetSlot6", this.mTargetSlots[5]);
      aNBT.func_74768_a("mTargetSlot7", this.mTargetSlots[6]);
      aNBT.func_74768_a("mTargetSlot8", this.mTargetSlots[7]);
      aNBT.func_74768_a("mTargetSlot9", this.mTargetSlots[8]);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mTargetSlots[0] = aNBT.func_74762_e("mTargetSlot1");
      this.mTargetSlots[1] = aNBT.func_74762_e("mTargetSlot2");
      this.mTargetSlots[2] = aNBT.func_74762_e("mTargetSlot3");
      this.mTargetSlots[3] = aNBT.func_74762_e("mTargetSlot4");
      this.mTargetSlots[4] = aNBT.func_74762_e("mTargetSlot5");
      this.mTargetSlots[5] = aNBT.func_74762_e("mTargetSlot6");
      this.mTargetSlots[6] = aNBT.func_74762_e("mTargetSlot7");
      this.mTargetSlots[7] = aNBT.func_74762_e("mTargetSlot8");
      this.mTargetSlots[8] = aNBT.func_74762_e("mTargetSlot9");
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isUniversalEnergyStored(500) && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || this.getBaseMetaTileEntity().getTimer() % 50L == 0L)) {
         int i = 0;

         for(int tCosts = 0; i < 9 && tCosts == 0; ++i) {
            if(this.mInventory[i + 9] != null) {
               tCosts = GT_Utility.moveOneItemStackIntoSlot(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing()), this.getBaseMetaTileEntity().getBackFacing(), this.mTargetSlots[i], Arrays.asList(new ItemStack[]{this.mInventory[i + 9]}), false, (byte)this.mInventory[i + 9].field_77994_a, (byte)this.mInventory[i + 9].field_77994_a, (byte)64, (byte)1) * 3;
               if(tCosts > 0) {
                  this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCosts, true);
               }
            }
         }
      }

   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aSide != this.getBaseMetaTileEntity().getBackFacing() && GT_Utility.areStacksEqual(aStack, this.mInventory[aIndex + 9]);
   }

   public String getDescription() {
      return "Lets you save up to 8 additional Machines when used correctly!";
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == aFacing) {
         return 135 + (aRedstone?8:0);
      } else if(GT_Utility.getOppositeSide(aSide) == aFacing) {
         return 113 + (aRedstone?8:0);
      } else {
         int tIndex = 135 + (aRedstone?8:0);
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
}
