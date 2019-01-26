package gregtechmod.common.tileentities.redstone;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRedstoneCircuitBlock;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_CircuitryBehavior;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_RedstoneCircuitBlock extends MetaTileEntity implements IRedstoneCircuitBlock {

   public int mGate = 0;
   public int[] mGateData = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
   public boolean bOutput = true;


   public GT_MetaTileEntity_RedstoneCircuitBlock(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_RedstoneCircuitBlock() {}

   public boolean hasSidedRedstoneOutputBehavior() {
      return true;
   }

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
      return this.getBaseMetaTileEntity().getBackFacing() == aSide;
   }

   public int getMinimumStoredEU() {
      return 500;
   }

   public int maxEUInput() {
      return 32;
   }

   public int maxEUOutput() {
      return this.bOutput?32:0;
   }

   public int getInvSize() {
      return 5;
   }

   public int maxEUStore() {
      return 1000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 147);
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_RedstoneCircuitBlock();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mGate", this.mGate);
      aNBT.func_74783_a("mGateData", this.mGateData);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mGate = aNBT.func_74762_e("mGate");
      this.mGateData = aNBT.func_74759_k("mGateData");
      if(this.mGateData.length != 8) {
         this.mGateData = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
      }

   }

   public void switchOutput() {
      this.bOutput = !this.bOutput;
   }

   public void switchGateForward(boolean aShift) {
      try {
         Set e = GregTech_API.sCircuitryBehaviors.keySet();
         ArrayList tList = new ArrayList();
         tList.addAll(e);
         if(tList.size() <= 0) {
            return;
         }

         Collections.sort(tList);
         if(!GregTech_API.sCircuitryBehaviors.containsKey(Integer.valueOf(this.mGate))) {
            this.mGate = ((Integer)tList.get(0)).intValue();
         }

         int tIndex = Collections.binarySearch(tList, Integer.valueOf(this.mGate));

         for(tIndex += aShift?16:1; tIndex >= tList.size(); tIndex -= tList.size()) {
            ;
         }

         this.mGate = ((Integer)tList.get(tIndex)).intValue();
         this.switchGate();
      } catch (Throwable var5) {
         var5.printStackTrace(GT_Log.err);
      }

   }

   public void switchGateBackward(boolean aShift) {
      try {
         Set e = GregTech_API.sCircuitryBehaviors.keySet();
         ArrayList tList = new ArrayList();
         tList.addAll(e);
         if(tList.size() <= 0) {
            return;
         }

         Collections.sort(tList);
         if(!GregTech_API.sCircuitryBehaviors.containsKey(Integer.valueOf(this.mGate))) {
            this.mGate = ((Integer)tList.get(0)).intValue();
         }

         int tIndex = Collections.binarySearch(tList, Integer.valueOf(this.mGate));

         for(tIndex -= aShift?16:1; tIndex < 0; tIndex += tList.size()) {
            ;
         }

         this.mGate = ((Integer)tList.get(tIndex)).intValue();
         this.switchGate();
      } catch (Throwable var5) {
         var5.printStackTrace(GT_Log.err);
      }

   }

   public void onFacingChange() {
      this.resetRedstone();
   }

   private void resetRedstone() {
      this.getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)0, (byte)0);
      this.getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)1, (byte)0);
      this.getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)2, (byte)0);
      this.getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)3, (byte)0);
      this.getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)4, (byte)0);
      this.getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)5, (byte)0);
   }

   public void changeGateData(int aIndex, int aValue) {
      this.mGateData[aIndex] += aValue;
      this.validateGateData();
   }

   public void stackGateData(int aIndex, ItemStack aStack) {
      this.mGateData[aIndex] = GT_Utility.stackToInt(aStack);
      this.validateGateData();
   }

   private void switchGate() {
      this.resetRedstone();

      for(int tBehaviour = 0; tBehaviour < this.mGateData.length; ++tBehaviour) {
         this.mGateData[tBehaviour] = 0;
      }

      GT_CircuitryBehavior var4 = (GT_CircuitryBehavior)GregTech_API.sCircuitryBehaviors.get(Integer.valueOf(this.mGate));
      if(var4 != null) {
         try {
            var4.initParameters(this.mGateData, this);
         } catch (Throwable var3) {
            var3.printStackTrace(GT_Log.err);
         }
      }

      this.validateGateData();
   }

   private void validateGateData() {
      GT_CircuitryBehavior tBehaviour = (GT_CircuitryBehavior)GregTech_API.sCircuitryBehaviors.get(Integer.valueOf(this.mGate));
      if(tBehaviour != null) {
         try {
            tBehaviour.validateParameters(this.mGateData, this);
         } catch (Throwable var3) {
            var3.printStackTrace(GT_Log.err);
         }
      }

   }

   public void onFirstTick() {
      this.getBaseMetaTileEntity().setGenericRedstoneOutput(true);
      this.validateGateData();
   }

   public void onPreTick() {
      this.getBaseMetaTileEntity().setGenericRedstoneOutput(true);
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide()) {
         this.mInventory[0] = this.mInventory[1] = this.mInventory[2] = this.mInventory[3] = this.mInventory[4] = null;
         if(this.getBaseMetaTileEntity().isUniversalEnergyStored(400)) {
            if(this.getBaseMetaTileEntity().isActive()) {
               GT_CircuitryBehavior tBehaviour = (GT_CircuitryBehavior)GregTech_API.sCircuitryBehaviors.get(Integer.valueOf(this.mGate));
               if(tBehaviour != null) {
                  try {
                     tBehaviour.onTick(this.mGateData, this);
                     if(tBehaviour.displayItemStack(this.mGateData, this, 0)) {
                        this.mInventory[1] = GT_Utility.intToStack(this.mGateData[0]);
                     }

                     if(tBehaviour.displayItemStack(this.mGateData, this, 1)) {
                        this.mInventory[2] = GT_Utility.intToStack(this.mGateData[1]);
                     }

                     if(tBehaviour.displayItemStack(this.mGateData, this, 2)) {
                        this.mInventory[3] = GT_Utility.intToStack(this.mGateData[2]);
                     }

                     if(tBehaviour.displayItemStack(this.mGateData, this, 3)) {
                        this.mInventory[4] = GT_Utility.intToStack(this.mGateData[3]);
                     }
                  } catch (Throwable var3) {
                     var3.printStackTrace(GT_Log.err);
                  }
               }
            }

            this.getBaseMetaTileEntity().setErrorDisplayID(0);
         } else {
            this.getBaseMetaTileEntity().setErrorDisplayID(1);
         }
      }

   }

   public byte getOutputFacing() {
      return this.getBaseMetaTileEntity().getBackFacing();
   }

   public boolean setRedstone(byte aStrength, byte aSide) {
      if(this.getOutputRedstone(aSide) != aStrength) {
         if(this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(1, false)) {
            this.getBaseMetaTileEntity().setInternalOutputRedstoneSignal(aSide, aStrength);
            this.getBaseMetaTileEntity().setErrorDisplayID(0);
            return true;
         } else {
            this.getBaseMetaTileEntity().setErrorDisplayID(1);
            return false;
         }
      } else {
         return false;
      }
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == this.getOutputFacing()?(aSide == 0?(aRedstone?56:54):(aSide == 1?(aRedstone?53:52):(aRedstone?94:93))):(aSide == 0?(aRedstone?60:59):(aSide == 1?(aRedstone?58:57):(aRedstone?62:61)));
   }

   public String getDescription() {
      return "Computes Redstone";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public byte getOutputRedstone(byte aSide) {
      return this.getBaseMetaTileEntity().getOutputRedstoneSignal(aSide);
   }

   public byte getInputRedstone(byte aSide) {
      return this.getBaseMetaTileEntity().getInternalInputRedstoneSignal(aSide);
   }

   public Block getBlockAtSide(byte aSide) {
      return this.getBaseMetaTileEntity().getBlockAtSide(aSide);
   }

   public byte getMetaIDAtSide(byte aSide) {
      return this.getBaseMetaTileEntity().getMetaIDAtSide(aSide);
   }

   public TileEntity getTileEntityAtSide(byte aSide) {
      return this.getBaseMetaTileEntity().getTileEntityAtSide(aSide);
   }

   public int getRandom(int aRange) {
      return this.getBaseMetaTileEntity().getRandomNumber(aRange);
   }

   public GT_CoverBehavior getCover(byte aSide) {
      return this.getBaseMetaTileEntity().getCoverBehaviorAtSide(aSide);
   }

   public int getCoverID(byte aSide) {
      return this.getBaseMetaTileEntity().getCoverIDAtSide(aSide);
   }

   public int getCoverVariable(byte aSide) {
      return this.getBaseMetaTileEntity().getCoverDataAtSide(aSide);
   }

   public ICoverable getOwnTileEntity() {
      return this.getBaseMetaTileEntity();
   }
}
