package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_ElectricInventoryManager extends MetaTileEntity {

   public int[] mSlotRange = new int[4];
   public boolean mWorkedLastTick = false;


   public GT_MetaTileEntity_ElectricInventoryManager(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_ElectricInventoryManager() {}

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

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public int maxEUInput() {
      return 128;
   }

   public int maxEUOutput() {
      return 32;
   }

   public int maxEUPulses() {
      return 4;
   }

   public int maxEUStore() {
      return 100000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 129);
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 3;
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public boolean isOutputFacing(byte aSide) {
      for(int i = 0; i < this.mSlotRange.length; ++i) {
         if(aSide == this.getRangeDirection(i) && this.getRangeEnergy(i)) {
            return true;
         }
      }

      return false;
   }

   public int getMinimumStoredEU() {
      return '\uc350';
   }

   public int getInvSize() {
      return 16;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricInventoryManager();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mSlotRange0", this.mSlotRange[0]);
      aNBT.func_74768_a("mSlotRange1", this.mSlotRange[1]);
      aNBT.func_74768_a("mSlotRange2", this.mSlotRange[2]);
      aNBT.func_74768_a("mSlotRange3", this.mSlotRange[3]);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mSlotRange[0] = aNBT.func_74762_e("mSlotRange0");
      this.mSlotRange[1] = aNBT.func_74762_e("mSlotRange1");
      this.mSlotRange[2] = aNBT.func_74762_e("mSlotRange2");
      this.mSlotRange[3] = aNBT.func_74762_e("mSlotRange3");
   }

   public void iterateRangeDirection(int aIndex) {
      this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & -8 | ((this.mSlotRange[aIndex] & 7) + 1) % 6;
   }

   public void switchRangeEnergy(int aIndex) {
      this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & -9 | ((this.mSlotRange[aIndex] & 8) > 0?0:8);
   }

   public void iterateSlot1Direction(int aIndex) {
      this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & -113 | (((this.mSlotRange[aIndex] & 112) >> 4) + 1) % 6 << 4;
   }

   public void iterateSlot2Direction(int aIndex) {
      this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & -897 | (((this.mSlotRange[aIndex] & 896) >> 7) + 1) % 6 << 7;
   }

   public void iterateSlot3Direction(int aIndex) {
      this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & -7169 | (((this.mSlotRange[aIndex] & 7168) >> 10) + 1) % 6 << 10;
   }

   public void switchSlot1InOut(int aIndex) {
      this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & -8193 | ((this.mSlotRange[aIndex] & 8192) > 0?0:8192);
   }

   public void switchSlot2InOut(int aIndex) {
      this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & -16385 | ((this.mSlotRange[aIndex] & 16384) > 0?0:16384);
   }

   public void switchSlot3InOut(int aIndex) {
      this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & -32769 | ((this.mSlotRange[aIndex] & '\u8000') > 0?0:'\u8000');
   }

   public byte getRangeDirection(int aIndex) {
      return (byte)(this.mSlotRange[aIndex] & 7);
   }

   public byte getSlot1Direction(int aIndex) {
      return (byte)((this.mSlotRange[aIndex] & 112) >> 4);
   }

   public byte getSlot2Direction(int aIndex) {
      return (byte)((this.mSlotRange[aIndex] & 896) >> 7);
   }

   public byte getSlot3Direction(int aIndex) {
      return (byte)((this.mSlotRange[aIndex] & 7168) >> 10);
   }

   public boolean getRangeEnergy(int aIndex) {
      return (this.mSlotRange[aIndex] & 8) > 0;
   }

   public boolean getSlot1InOut(int aIndex) {
      return (this.mSlotRange[aIndex] & 8192) > 0;
   }

   public boolean getSlot2InOut(int aIndex) {
      return (this.mSlotRange[aIndex] & 16384) > 0;
   }

   public boolean getSlot3InOut(int aIndex) {
      return (this.mSlotRange[aIndex] & '\u8000') > 0;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isUniversalEnergyStored(5000) && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || this.getBaseMetaTileEntity().getTimer() % 100L == 0L || this.mWorkedLastTick || this.getBaseMetaTileEntity().hasInventoryBeenModified())) {
         this.mWorkedLastTick = false;
         TileEntity[] tTileEntities = new TileEntity[]{this.getBaseMetaTileEntity().getTileEntityAtSide((byte)0), this.getBaseMetaTileEntity().getTileEntityAtSide((byte)1), this.getBaseMetaTileEntity().getTileEntityAtSide((byte)2), this.getBaseMetaTileEntity().getTileEntityAtSide((byte)3), this.getBaseMetaTileEntity().getTileEntityAtSide((byte)4), this.getBaseMetaTileEntity().getTileEntityAtSide((byte)5), null, null};
         int tCost = 0;

         for(int i = 0; i < 4; ++i) {
            ArrayList tList = new ArrayList();
            tList.add((Object)null);
            ItemStack tStack = this.mInventory[3 + i * 3 + 0];
            if(tStack == null) {
               if(this.getSlot1InOut(i)) {
                  tCost += 5 * GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), tTileEntities[this.getRangeDirection(i)], this.getSlot1Direction(i), this.getSlot1Direction(i), (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1);
               } else {
                  tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[this.getRangeDirection(i)], this.getBaseMetaTileEntity(), this.getSlot1Direction(i), this.getSlot1Direction(i), (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1);
               }
            } else {
               tList.set(0, tStack);
               if(this.getSlot1InOut(i)) {
                  tCost += 5 * GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), tTileEntities[this.getRangeDirection(i)], this.getSlot1Direction(i), this.getSlot1Direction(i), tList, false, (byte)tStack.field_77994_a, (byte)1, (byte)64, (byte)1);
               } else {
                  tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[this.getRangeDirection(i)], this.getBaseMetaTileEntity(), this.getSlot1Direction(i), this.getSlot1Direction(i), tList, false, (byte)tStack.field_77994_a, (byte)1, (byte)64, (byte)1);
               }
            }

            tStack = this.mInventory[3 + i * 3 + 1];
            if(tStack == null) {
               if(this.getSlot2InOut(i)) {
                  tCost += 5 * GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), tTileEntities[this.getRangeDirection(i)], this.getSlot2Direction(i), this.getSlot2Direction(i), (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1);
               } else {
                  tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[this.getRangeDirection(i)], this.getBaseMetaTileEntity(), this.getSlot2Direction(i), this.getSlot2Direction(i), (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1);
               }
            } else {
               tList.set(0, tStack);
               if(this.getSlot2InOut(i)) {
                  tCost += 5 * GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), tTileEntities[this.getRangeDirection(i)], this.getSlot2Direction(i), this.getSlot2Direction(i), tList, false, (byte)tStack.field_77994_a, (byte)1, (byte)64, (byte)1);
               } else {
                  tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[this.getRangeDirection(i)], this.getBaseMetaTileEntity(), this.getSlot2Direction(i), this.getSlot2Direction(i), tList, false, (byte)tStack.field_77994_a, (byte)1, (byte)64, (byte)1);
               }
            }

            tStack = this.mInventory[3 + i * 3 + 2];
            if(tStack == null) {
               if(this.getSlot3InOut(i)) {
                  tCost += 5 * GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), tTileEntities[this.getRangeDirection(i)], this.getSlot3Direction(i), this.getSlot3Direction(i), (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1);
               } else {
                  tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[this.getRangeDirection(i)], this.getBaseMetaTileEntity(), this.getSlot3Direction(i), this.getSlot3Direction(i), (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1);
               }
            } else {
               tList.set(0, tStack);
               if(this.getSlot3InOut(i)) {
                  tCost += 5 * GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), tTileEntities[this.getRangeDirection(i)], this.getSlot3Direction(i), this.getSlot3Direction(i), tList, false, (byte)tStack.field_77994_a, (byte)1, (byte)64, (byte)1);
               } else {
                  tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[this.getRangeDirection(i)], this.getBaseMetaTileEntity(), this.getSlot3Direction(i), this.getSlot3Direction(i), tList, false, (byte)tStack.field_77994_a, (byte)1, (byte)64, (byte)1);
               }
            }
         }

         if(tCost > 0) {
            this.mWorkedLastTick = true;
            this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCost, true);
         }
      }

   }

   public String getDescription() {
      return "It\'s simpler than you think. I promise.";
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      switch(aSide) {
      case 0:
         return 113 + (aRedstone?8:0);
      case 1:
         return 112 + (aRedstone?8:0);
      case 2:
         return 116 + (aRedstone?8:0);
      case 3:
         return 213 + (aRedstone?8:0);
      case 4:
         return 212 + (aRedstone?8:0);
      case 5:
         return 117 + (aRedstone?8:0);
      default:
         return 0;
      }
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return true;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return true;
   }
}
