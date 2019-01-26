package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricBufferAdvanced extends GT_MetaTileEntity_ElectricBufferSmall {

   public int mTargetSlot = 0;


   public GT_MetaTileEntity_ElectricBufferAdvanced(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_ElectricBufferAdvanced() {}

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
      return true;
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

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 105);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricBufferAdvanced();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74768_a("mTargetSlot", this.mTargetSlot);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mTargetSlot = aNBT.func_74762_e("mTargetSlot");
   }

   public String getDescription() {
      return "A Buffer, which lets you specify the target Slot!";
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isUniversalEnergyStored(500) && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || this.getBaseMetaTileEntity().getTimer() % 200L == 0L || this.mSuccess > 0 && this.getBaseMetaTileEntity().getTimer() % 5L == 0L || this.mSuccess >= 20 || this.getBaseMetaTileEntity().hasInventoryBeenModified())) {
         boolean tPrice = false;
         int var3;
         this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(var3 = GT_Utility.moveOneItemStackIntoSlot(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing()), this.getBaseMetaTileEntity().getBackFacing(), this.mTargetSlot, (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1) * 3, true);
         if(var3 > 0) {
            this.mSuccess = 30;
         }

         this.getBaseMetaTileEntity().setGenericRedstoneOutput(this.bInvert);
         if(this.bRedstoneIfFull) {
            this.getBaseMetaTileEntity().setGenericRedstoneOutput(!this.bInvert);

            for(int i = 0; i < this.mInventory.length; ++i) {
               if(this.isValidSlot(i) && this.mInventory[i] == null) {
                  this.getBaseMetaTileEntity().setGenericRedstoneOutput(this.bInvert);
                  this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(1, true);
                  break;
               }
            }
         }
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == aFacing) {
         return 132 + (aRedstone?8:0);
      } else if(GT_Utility.getOppositeSide(aSide) == aFacing) {
         return 113 + (aRedstone?8:0);
      } else {
         int tIndex = 132 + (aRedstone?8:0);
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
