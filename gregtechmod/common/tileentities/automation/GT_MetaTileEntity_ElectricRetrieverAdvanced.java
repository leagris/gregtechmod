package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Item;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricRetrieverAdvanced extends MetaTileEntity {

   public int[] mTargetSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
   public boolean mPartialRequests = false;
   public boolean bOutput = false;


   public GT_MetaTileEntity_ElectricRetrieverAdvanced(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_ElectricRetrieverAdvanced() {}

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isTeleporterCompatible() {
      return false;
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
      return false;
   }

   public int maxEUOutput() {
      return this.bOutput?32:0;
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
      this.getBaseMetaTileEntity().openGUI(aPlayer, 182);
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getBackFacing();
   }

   public int getMinimumStoredEU() {
      return 2500;
   }

   public int getInvSize() {
      return 10;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean connectsToItemPipe(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing();
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricRetrieverAdvanced();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mTargetSlot1", this.mTargetSlots[0]);
      aNBT.func_74768_a("mTargetSlot2", this.mTargetSlots[1]);
      aNBT.func_74768_a("mTargetSlot3", this.mTargetSlots[2]);
      aNBT.func_74768_a("mTargetSlot4", this.mTargetSlots[3]);
      aNBT.func_74768_a("mTargetSlot5", this.mTargetSlots[4]);
      aNBT.func_74768_a("mTargetSlot6", this.mTargetSlots[5]);
      aNBT.func_74768_a("mTargetSlot7", this.mTargetSlots[6]);
      aNBT.func_74768_a("mTargetSlot8", this.mTargetSlots[7]);
      aNBT.func_74768_a("mTargetSlot9", this.mTargetSlots[8]);
      aNBT.func_74757_a("mPartialRequests", this.mPartialRequests);
      aNBT.func_74757_a("bOutput", this.bOutput);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mTargetSlots[0] = aNBT.func_74762_e("mTargetSlot1");
      this.mTargetSlots[1] = aNBT.func_74762_e("mTargetSlot2");
      this.mTargetSlots[2] = aNBT.func_74762_e("mTargetSlot3");
      this.mTargetSlots[3] = aNBT.func_74762_e("mTargetSlot4");
      this.mTargetSlots[4] = aNBT.func_74762_e("mTargetSlot5");
      this.mTargetSlots[5] = aNBT.func_74762_e("mTargetSlot6");
      this.mTargetSlots[6] = aNBT.func_74762_e("mTargetSlot7");
      this.mTargetSlots[7] = aNBT.func_74762_e("mTargetSlot8");
      this.mTargetSlots[8] = aNBT.func_74762_e("mTargetSlot9");
      this.mPartialRequests = aNBT.func_74767_n("mPartialRequests");
      this.bOutput = aNBT.func_74767_n("bOutput");
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isUniversalEnergyStored(2000) && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || this.getBaseMetaTileEntity().getTimer() % 50L == 0L)) {
         IInventory tInventory = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
         IGregTechTileEntity tConnectedPipe = this.getBaseMetaTileEntity().getIGregTechTileEntityAtSide(this.getBaseMetaTileEntity().getFrontFacing());
         if(tInventory != null && tConnectedPipe != null && tConnectedPipe.getMetaTileEntity() != null && tConnectedPipe.getMetaTileEntity() instanceof GT_MetaPipeEntity_Item) {
            boolean temp = false;
            boolean[] tNeedsItems = new boolean[9];

            for(int tMap = 0; tMap < 9; ++tMap) {
               tNeedsItems[tMap] = false;
               if(this.mInventory[tMap] != null) {
                  ItemStack tList = tInventory.func_70301_a(this.mTargetSlots[tMap]);
                  if(tList == null || GT_Utility.areStacksEqual(tList, this.mInventory[tMap]) && tList.field_77994_a < this.mInventory[tMap].field_77994_a) {
                     temp = true;
                     tNeedsItems[tMap] = true;
                  }
               }
            }

            if(temp) {
               this.doTickProfilingInThisTick = false;
               LinkedHashMap var13 = GT_Utility.sortMapByValuesAcending(GT_Utility.scanPipes((GT_MetaPipeEntity_Item)((GT_MetaPipeEntity_Item)tConnectedPipe.getMetaTileEntity()), new HashMap(), 0L, true));
               ArrayList var14 = new ArrayList();
               Iterator i$ = var13.keySet().iterator();

               while(i$.hasNext()) {
                  GT_MetaPipeEntity_Item tPipe = (GT_MetaPipeEntity_Item)i$.next();
                  if(!temp) {
                     GT_MetaPipeEntity_Item var16;
                     for(Iterator var15 = var14.iterator(); var15.hasNext(); ++var16.mTransferredItems) {
                        var16 = (GT_MetaPipeEntity_Item)var15.next();
                     }

                     return;
                  }

                  var14.add(tPipe);

                  for(byte tSide = 0; temp && tSide < 6; ++tSide) {
                     if(tPipe.getBaseMetaTileEntity().getCoverBehaviorAtSide(tSide).letsItemsIn(tSide, tPipe.getBaseMetaTileEntity().getCoverIDAtSide(tSide), tPipe.getBaseMetaTileEntity().getCoverDataAtSide(tSide), tPipe.getBaseMetaTileEntity())) {
                        IInventory tTileEntity = tPipe.getBaseMetaTileEntity().getIInventoryAtSide(tSide);
                        if(tTileEntity != tInventory) {
                           int i = 0;

                           for(boolean tCosts = false; temp && i < 9; ++i) {
                              if(tNeedsItems[i]) {
                                 int var17 = GT_Utility.moveOneItemStackIntoSlot(tTileEntity, tInventory, GT_Utility.getOppositeSide(tSide), this.mTargetSlots[i], Arrays.asList(new ItemStack[]{this.mInventory[i]}), false, (byte)this.mInventory[i].field_77994_a, this.mPartialRequests?1:(byte)this.mInventory[i].field_77994_a, (byte)64, (byte)1) * 20;
                                 if(var17 > 0) {
                                    this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(var17, true);
                                    temp = false;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getBackFacing() && aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public String getDescription() {
      return "Retrieves and Regulates Items through a GT Pipe System!";
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == aFacing) {
         return 112 + (aRedstone?8:0);
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
