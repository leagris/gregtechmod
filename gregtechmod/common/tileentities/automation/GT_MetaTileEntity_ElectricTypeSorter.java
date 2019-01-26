package gregtechmod.common.tileentities.automation;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricTypeSorter extends GT_MetaTileEntity_ElectricBufferSmall {

   public byte mTargetDirection;
   public byte mMode = 0;
   public static String[] sTypeList = new String[]{"ore", "gem", "nugget", "dustSmall", "dust", "ingot", "block", "treeLeaves", "treeSapling", "logWood", "plankWood", "", "ore", "beeComb"};


   public GT_MetaTileEntity_ElectricTypeSorter(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_ElectricTypeSorter() {}

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

   public int maxEUStore() {
      return 10000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int getMinimumStoredEU() {
      return 2000;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 1;
   }

   public boolean isOutputFacing(byte aSide) {
      return this.mTargetDirection == aSide || this.getBaseMetaTileEntity().getBackFacing() == aSide;
   }

   public int getInvSize() {
      return 2;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 139);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricTypeSorter();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74768_a("mMode", this.mMode);
      aNBT.func_74768_a("mTargetDirection", this.mTargetDirection);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mMode = (byte)aNBT.func_74762_e("mMode");
      this.mTargetDirection = (byte)aNBT.func_74762_e("mTargetDirection");
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.mTargetDirection && aSide != this.getBaseMetaTileEntity().getBackFacing();
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aSide != this.getBaseMetaTileEntity().getBackFacing() && aSide != this.mTargetDirection;
   }

   public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getBackFacing() || aSide == this.mTargetDirection) {
         this.mTargetStackSize = (byte)((this.mTargetStackSize + 1) % 64);
         if(this.mTargetStackSize == 0) {
            GT_Utility.sendChatToPlayer(aPlayer, "Do not regulate Item Stack Size");
         } else {
            GT_Utility.sendChatToPlayer(aPlayer, "Regulate Item Stack Size to: " + this.mTargetStackSize);
         }
      }

   }

   public void switchModeForward() {
      ++this.mMode;
      this.switchMode();
   }

   public void switchModeBackward() {
      --this.mMode;
      this.switchMode();
   }

   public void switchMode() {
      if(this.mMode >= sTypeList.length) {
         this.mMode = 0;
      }

      if(this.mMode < 0) {
         this.mMode = (byte)(sTypeList.length - 1);
      }

   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isUniversalEnergyStored(1000) && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || this.getBaseMetaTileEntity().getTimer() % 20L == 0L)) {
         if(this.mInventory[0] != null) {
            int tPrice = 0;
            OrePrefixes tPrefix = OrePrefixes.getOrePrefix(sTypeList[this.mMode]);
            if(this.mMode == 11 && this.mInventory[0].func_77973_b() instanceof ItemFood || tPrefix != null && (tPrefix.contains(this.mInventory[0]) || tPrefix == OrePrefixes.dustSmall && OrePrefixes.dustTiny.contains(this.mInventory[0])) || GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[0], sTypeList[this.mMode])) {
               this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(tPrice = GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity().getTileEntityAtSide(this.mTargetDirection), this.getBaseMetaTileEntity().getBackFacing(), GT_Utility.getOppositeSide(this.mTargetDirection), (List)null, false, this.mTargetStackSize != 0?(byte)this.mTargetStackSize:64, this.mTargetStackSize != 0?(byte)this.mTargetStackSize:1, (byte)64, (byte)1) * 3, true);
            }

            if(tPrice <= 0) {
               this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing()), this.getBaseMetaTileEntity().getBackFacing(), this.getBaseMetaTileEntity().getFrontFacing(), (List)null, false, this.mTargetStackSize != 0?(byte)this.mTargetStackSize:64, this.mTargetStackSize != 0?(byte)this.mTargetStackSize:1, (byte)64, (byte)1) * 2, true);
            }
         }

         this.getBaseMetaTileEntity().setGenericRedstoneOutput(this.bInvert);
         if(this.bRedstoneIfFull) {
            this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(1, true);
            if(this.mInventory[0] != null) {
               this.getBaseMetaTileEntity().setGenericRedstoneOutput(!this.bInvert);
            }
         }
      }

   }

   public String getDescription() {
      return "Like the regular Sorter, but with special Item Types instead of Filter Items";
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == this.mTargetDirection) {
         return 116 + (aRedstone?8:0);
      } else if(aSide == aFacing) {
         return 134 + (aRedstone?8:0);
      } else if(GT_Utility.getOppositeSide(aSide) == aFacing) {
         return 113 + (aRedstone?8:0);
      } else {
         int tIndex = 134 + (aRedstone?8:0);
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

   public void onValueUpdate(byte aValue) {
      this.mTargetDirection = aValue;
   }

   public byte getUpdateData() {
      return this.mTargetDirection;
   }

}
