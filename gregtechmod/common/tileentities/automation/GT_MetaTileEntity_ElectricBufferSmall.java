package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricBufferSmall extends MetaTileEntity {

   public boolean bOutput = false;
   public boolean bRedstoneIfFull = false;
   public boolean bInvert = false;
   public int mSuccess = 0;
   public int mTargetStackSize = 0;


   public GT_MetaTileEntity_ElectricBufferSmall(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_ElectricBufferSmall() {}

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

   public boolean isValidSlot(int aIndex) {
      return aIndex < this.getInvSize() - 1;
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
      return this.getBaseMetaTileEntity().getBackFacing() == aSide;
   }

   public boolean isTeleporterCompatible() {
      return false;
   }

   public int getMinimumStoredEU() {
      return 1000;
   }

   public int maxEUInput() {
      return 32;
   }

   public int maxEUOutput() {
      return this.bOutput?32:0;
   }

   public int maxEUStore() {
      return 1250;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public int getInvSize() {
      return 2;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 102);
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricBufferSmall();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74757_a("bInvert", this.bInvert);
      aNBT.func_74757_a("bOutput", this.bOutput);
      aNBT.func_74757_a("bRedstoneIfFull", this.bRedstoneIfFull);
      aNBT.func_74768_a("mTargetStackSize", this.mTargetStackSize);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.bInvert = aNBT.func_74767_n("bInvert");
      this.bOutput = aNBT.func_74767_n("bOutput");
      this.bRedstoneIfFull = aNBT.func_74767_n("bRedstoneIfFull");
      this.mTargetStackSize = aNBT.func_74762_e("mTargetStackSize");
   }

   public void setItemNBT(NBTTagCompound aNBT) {
      super.setItemNBT(aNBT);
      if(this.mTargetStackSize > 0) {
         aNBT.func_74768_a("mTargetStackSize", this.mTargetStackSize);
      }

   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getBackFacing();
   }

   public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getBackFacing()) {
         this.mTargetStackSize = (byte)((this.mTargetStackSize + 1) % 65);
         if(this.mTargetStackSize == 0) {
            GT_Utility.sendChatToPlayer(aPlayer, "Do not regulate Item Stack Size");
         } else {
            GT_Utility.sendChatToPlayer(aPlayer, "Regulate Item Stack Size to: " + this.mTargetStackSize);
         }
      }

   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isUniversalEnergyStored(500) && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || this.getBaseMetaTileEntity().getTimer() % 200L == 0L || this.getBaseMetaTileEntity().getTimer() % 5L == 0L && (this.mSuccess > 0 || this.mInventory[0] != null && this.getBaseMetaTileEntity().getTimer() % 10L == 0L && this.getInvSize() <= 2) || this.mSuccess >= 50 || this.getBaseMetaTileEntity().hasInventoryBeenModified())) {
         --this.mSuccess;
         int i;
         if(this.getInvSize() > 2 || this.mInventory[0] != null) {
            i = GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing()), this.getBaseMetaTileEntity().getBackFacing(), this.getBaseMetaTileEntity().getFrontFacing(), (List)null, false, this.mTargetStackSize == 0?64:(byte)this.mTargetStackSize, this.mTargetStackSize == 0?1:(byte)this.mTargetStackSize, (byte)64, (byte)1) * (this.getInvSize() > 10?2:1);
            if(i > 0) {
               this.mSuccess = 50;
               this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(i, true);
            }
         }

         this.getBaseMetaTileEntity().setGenericRedstoneOutput(this.bInvert);
         if(this.bRedstoneIfFull) {
            this.getBaseMetaTileEntity().setGenericRedstoneOutput(!this.bInvert);

            for(i = 0; i < this.mInventory.length; ++i) {
               if(this.isValidSlot(i) && this.mInventory[i] == null) {
                  this.getBaseMetaTileEntity().setGenericRedstoneOutput(this.bInvert);
                  this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(1, true);
                  break;
               }
            }
         }
      }

   }

   public String getDescription() {
      return "A small directable Hopper with an inbuilt Energy Conduit!";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return true;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aSide != this.getBaseMetaTileEntity().getBackFacing();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == aFacing) {
         return 130 + (aRedstone?8:0);
      } else if(GT_Utility.getOppositeSide(aSide) == aFacing) {
         return 113 + (aRedstone?8:0);
      } else {
         int tIndex = 130 + (aRedstone?8:0);
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
