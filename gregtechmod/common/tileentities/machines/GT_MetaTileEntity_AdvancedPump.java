package gregtechmod.common.tileentities.machines;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.BaseTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkPosition;
import net.minecraftforge.fluids.IFluidBlock;

public class GT_MetaTileEntity_AdvancedPump extends GT_MetaTileEntity_BasicTank {

   public ArrayList<ChunkPosition> mPumpList = new ArrayList();
   public int mPumpTimer = 0;
   public int mPumpCountBelow = 0;
   public Block mPumpedBlock1 = null;
   public Block mPumpedBlock2 = null;


   public GT_MetaTileEntity_AdvancedPump(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_AdvancedPump() {}

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

   public boolean isValidSlot(int aIndex) {
      return true;
   }

   public boolean isFacingValid(byte aFacing) {
      return false;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isInputFacing(byte aSide) {
      return aSide != 0;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == 0;
   }

   public int maxEUInput() {
      return 128;
   }

   public int maxEUOutput() {
      return 128;
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

   public int getInvSize() {
      return 3;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 130);
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_AdvancedPump();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74768_a("mPumpedBlock1", this.mPumpedBlock1 == null?0:this.mPumpedBlock1.field_71990_ca);
      aNBT.func_74768_a("mPumpedBlock2", this.mPumpedBlock2 == null?0:this.mPumpedBlock2.field_71990_ca);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mPumpedBlock1 = Block.field_71973_m[aNBT.func_74762_e("mPumpedBlock1")];
      this.mPumpedBlock2 = Block.field_71973_m[aNBT.func_74762_e("mPumpedBlock2")];
   }

   public boolean doesFillContainers() {
      return true;
   }

   public boolean doesEmptyContainers() {
      return false;
   }

   public boolean canTankBeFilled() {
      return false;
   }

   public boolean canTankBeEmptied() {
      return true;
   }

   public boolean displaysItemStack() {
      return false;
   }

   public boolean displaysStackSize() {
      return false;
   }

   public int getInputSlot() {
      return 1;
   }

   public int getOutputSlot() {
      return 2;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         --this.mPumpTimer;
         if(this.getBaseMetaTileEntity() instanceof BaseTileEntity) {
            ((BaseTileEntity)this.getBaseMetaTileEntity()).ignoreUnloadedChunks = false;
         }

         this.doTickProfilingInThisTick = true;
         this.mPumpCountBelow = 0;

         IGregTechTileEntity tTileEntity;
         for(int tMovedOneDown = 1; tMovedOneDown < 21 && (tTileEntity = this.getBaseMetaTileEntity().getIGregTechTileEntityAtSideAndDistance((byte)0, tMovedOneDown)) != null && tTileEntity.getMetaTileEntity() != null && tTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_AdvancedPump; ++tMovedOneDown) {
            this.getBaseMetaTileEntity().setActive(tTileEntity.isActive());
            ++this.mPumpCountBelow;
            --((GT_MetaTileEntity_AdvancedPump)tTileEntity.getMetaTileEntity()).mPumpTimer;
         }

         if(this.mPumpCountBelow <= 0) {
            if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isUniversalEnergyStored(16000) && (this.mFluid == null || this.mFluid.amount + 1000 <= this.getCapacity())) {
               boolean var5 = false;
               if(this.mPumpList.isEmpty() && this.getBaseMetaTileEntity().getTimer() % 100L == 0L) {
                  var5 = this.moveOneDown();
               }

               if(!GT_Utility.isBlockInvalid(this.mPumpedBlock1) && !GT_Utility.isBlockInvalid(this.mPumpedBlock2)) {
                  if(this.getYOfPumpHead() < this.getBaseMetaTileEntity().getYCoord()) {
                     if(var5 || this.mPumpList.isEmpty() && this.getBaseMetaTileEntity().getTimer() % 200L == 100L || this.getBaseMetaTileEntity().getTimer() % 72000L == 100L) {
                        this.mPumpList.clear();
                        int y = this.getBaseMetaTileEntity().getYCoord() - 1;

                        for(int yHead = this.getYOfPumpHead(); this.mPumpList.isEmpty() && y >= yHead; --y) {
                           this.scanForFluid(this.getBaseMetaTileEntity().getXCoord(), y, this.getBaseMetaTileEntity().getZCoord(), this.mPumpList, this.getBaseMetaTileEntity().getXCoord(), this.getBaseMetaTileEntity().getZCoord(), 64);
                        }
                     }

                     if(!var5 && this.mPumpTimer <= 0) {
                        while(!this.mPumpList.isEmpty() && !this.consumeFluid(((ChunkPosition)this.mPumpList.get(this.mPumpList.size() - 1)).field_76930_a, ((ChunkPosition)this.mPumpList.get(this.mPumpList.size() - 1)).field_76928_b, ((ChunkPosition)this.mPumpList.remove(this.mPumpList.size() - 1)).field_76929_c)) {
                           ;
                        }

                        this.mPumpTimer = 20;
                     }
                  }
               } else {
                  this.getFluidAt(this.getBaseMetaTileEntity().getXCoord(), this.getYOfPumpHead() - 1, this.getBaseMetaTileEntity().getZCoord());
                  if(GT_Utility.isBlockInvalid(this.mPumpedBlock1) || GT_Utility.isBlockInvalid(this.mPumpedBlock2)) {
                     this.getFluidAt(this.getBaseMetaTileEntity().getXCoord(), this.getYOfPumpHead(), this.getBaseMetaTileEntity().getZCoord() + 1);
                  }

                  if(GT_Utility.isBlockInvalid(this.mPumpedBlock1) || GT_Utility.isBlockInvalid(this.mPumpedBlock2)) {
                     this.getFluidAt(this.getBaseMetaTileEntity().getXCoord(), this.getYOfPumpHead(), this.getBaseMetaTileEntity().getZCoord() - 1);
                  }

                  if(GT_Utility.isBlockInvalid(this.mPumpedBlock1) || GT_Utility.isBlockInvalid(this.mPumpedBlock2)) {
                     this.getFluidAt(this.getBaseMetaTileEntity().getXCoord() + 1, this.getYOfPumpHead(), this.getBaseMetaTileEntity().getZCoord());
                  }

                  if(GT_Utility.isBlockInvalid(this.mPumpedBlock1) || GT_Utility.isBlockInvalid(this.mPumpedBlock2)) {
                     this.getFluidAt(this.getBaseMetaTileEntity().getXCoord() - 1, this.getYOfPumpHead(), this.getBaseMetaTileEntity().getZCoord());
                  }
               }
            }

            this.getBaseMetaTileEntity().setActive(!this.mPumpList.isEmpty());
         }
      }

   }

   private boolean moveOneDown() {
      if(this.mInventory[0] != null && this.mInventory[0].field_77994_a >= 1 && GT_Utility.areStacksEqual(this.mInventory[0], GT_ModHandler.getIC2Item("miningPipe", 1L))) {
         int yHead = this.getYOfPumpHead();
         if(yHead <= 0) {
            return false;
         } else if(!this.consumeFluid(this.getBaseMetaTileEntity().getXCoord(), yHead - 1, this.getBaseMetaTileEntity().getZCoord()) && !this.getBaseMetaTileEntity().getAir(this.getBaseMetaTileEntity().getXCoord(), yHead - 1, this.getBaseMetaTileEntity().getZCoord())) {
            return false;
         } else if(!this.getBaseMetaTileEntity().getWorld().func_94575_c(this.getBaseMetaTileEntity().getXCoord(), yHead - 1, this.getBaseMetaTileEntity().getZCoord(), GT_Utility.getIDOfBlock(GT_Utility.getBlockFromStack(GT_ModHandler.getIC2Item("miningPipeTip", 1L))))) {
            return false;
         } else {
            if(yHead != this.getBaseMetaTileEntity().getYCoord()) {
               this.getBaseMetaTileEntity().getWorld().func_94575_c(this.getBaseMetaTileEntity().getXCoord(), yHead, this.getBaseMetaTileEntity().getZCoord(), GT_Utility.getIDOfBlock(GT_Utility.getBlockFromStack(GT_ModHandler.getIC2Item("miningPipe", 1L))));
            }

            this.getBaseMetaTileEntity().func_70298_a(0, 1);
            return true;
         }
      } else {
         return false;
      }
   }

   private int getYOfPumpHead() {
      int y;
      for(y = this.getBaseMetaTileEntity().getYCoord() - 1; this.getBaseMetaTileEntity().getBlock(this.getBaseMetaTileEntity().getXCoord(), y, this.getBaseMetaTileEntity().getZCoord()) == GT_Utility.getBlockFromStack(GT_ModHandler.getIC2Item("miningPipe", 1L)); --y) {
         ;
      }

      if(y == this.getBaseMetaTileEntity().getYCoord() - 1) {
         if(this.getBaseMetaTileEntity().getBlock(this.getBaseMetaTileEntity().getXCoord(), y, this.getBaseMetaTileEntity().getZCoord()) != GT_Utility.getBlockFromStack(GT_ModHandler.getIC2Item("miningPipeTip", 1L))) {
            return y + 1;
         }
      } else if(this.getBaseMetaTileEntity().getBlock(this.getBaseMetaTileEntity().getXCoord(), y, this.getBaseMetaTileEntity().getZCoord()) != GT_Utility.getBlockFromStack(GT_ModHandler.getIC2Item("miningPipeTip", 1L))) {
         this.getBaseMetaTileEntity().getWorld().func_94575_c(this.getBaseMetaTileEntity().getXCoord(), y, this.getBaseMetaTileEntity().getZCoord(), GT_Utility.getIDOfBlock(GT_Utility.getBlockFromStack(GT_ModHandler.getIC2Item("miningPipeTip", 1L))));
      }

      return y;
   }

   private void scanForFluid(int aX, int aY, int aZ, ArrayList<ChunkPosition> aList, int mX, int mZ, int mDist) {
      this.doTickProfilingInThisTick = false;
      ArrayList tList1 = new ArrayList();
      ArrayList tList2 = new ArrayList();
      tList1.add(new ChunkPosition(aX, aY, aZ));

      while(!tList1.isEmpty()) {
         Iterator y = tList1.iterator();

         while(y.hasNext()) {
            ChunkPosition tPos = (ChunkPosition)y.next();
            if(tPos.field_76930_a < mX + mDist) {
               this.addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(tPos.field_76930_a + 1, tPos.field_76928_b, tPos.field_76929_c, tList2, aList);
            }

            if(tPos.field_76930_a > mX - mDist) {
               this.addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(tPos.field_76930_a - 1, tPos.field_76928_b, tPos.field_76929_c, tList2, aList);
            }

            if(tPos.field_76929_c < mZ + mDist) {
               this.addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(tPos.field_76930_a, tPos.field_76928_b, tPos.field_76929_c + 1, tList2, aList);
            }

            if(tPos.field_76929_c > mZ - mDist) {
               this.addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(tPos.field_76930_a, tPos.field_76928_b, tPos.field_76929_c - 1, tList2, aList);
            }

            this.addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(tPos.field_76930_a, tPos.field_76928_b + 1, tPos.field_76929_c, tList2, aList);
            ChunkPosition tCoordinate = new ChunkPosition(aX, aY + 1, aZ);
            if(tPos.field_76930_a == mX && tPos.field_76929_c == mZ && tPos.field_76928_b < this.getBaseMetaTileEntity().getYCoord() && !aList.contains(tCoordinate) && !tList2.contains(tCoordinate)) {
               tList2.add(tCoordinate);
            }
         }

         aList.addAll(tList2);
         tList1 = tList2;
         tList2 = new ArrayList();
      }

      for(int var13 = this.getBaseMetaTileEntity().getYCoord(); var13 >= aY; --var13) {
         aList.remove(new ChunkPosition(aX, var13, aZ));
      }

   }

   private boolean addToFirstListIfFluidAndNotAlreadyAddedToAnyOfTheLists(int aX, int aY, int aZ, ArrayList<ChunkPosition> aList1, ArrayList<ChunkPosition> aList2) {
      ChunkPosition tCoordinate = new ChunkPosition(aX, aY, aZ);
      if(!aList1.contains(tCoordinate) && !aList2.contains(tCoordinate)) {
         Block aBlock = this.getBaseMetaTileEntity().getBlock(aX, aY, aZ);
         if(this.mPumpedBlock1 == aBlock || this.mPumpedBlock2 == aBlock) {
            aList1.add(tCoordinate);
            return true;
         }
      }

      return false;
   }

   private void getFluidAt(int aX, int aY, int aZ) {
      Block aBlock = this.getBaseMetaTileEntity().getBlock(aX, aY, aZ);
      if(GT_Utility.isBlockValid(aBlock)) {
         if(aBlock == Block.field_71938_D || aBlock == Block.field_71944_C) {
            this.mPumpedBlock1 = Block.field_71938_D;
            this.mPumpedBlock2 = Block.field_71944_C;
            return;
         }

         if(aBlock == Block.field_71943_B || aBlock == Block.field_71942_A) {
            this.mPumpedBlock1 = Block.field_71943_B;
            this.mPumpedBlock2 = Block.field_71942_A;
            return;
         }

         if(aBlock instanceof IFluidBlock) {
            this.mPumpedBlock1 = aBlock;
            this.mPumpedBlock2 = aBlock;
            return;
         }
      }

      this.mPumpedBlock1 = null;
      this.mPumpedBlock2 = null;
   }

   private boolean consumeFluid(int aX, int aY, int aZ) {
      Block aBlock = this.getBaseMetaTileEntity().getBlock(aX, aY, aZ);
      byte aMeta = this.getBaseMetaTileEntity().getMetaID(aX, aY, aZ);
      if(GT_Utility.isBlockValid(aBlock) && (this.mPumpedBlock1 == aBlock || this.mPumpedBlock2 == aBlock)) {
         if(aBlock == Block.field_71943_B || aBlock == Block.field_71942_A) {
            if(aMeta == 0) {
               if(this.mFluid == null) {
                  this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(1000, true);
                  this.mFluid = GT_ModHandler.getWater(1000L);
               } else {
                  if(!GT_ModHandler.isWater(this.mFluid)) {
                     return false;
                  }

                  this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(1000, true);
                  this.mFluid.amount += 1000;
               }
            } else {
               this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(250, true);
            }
         }

         if(aBlock == Block.field_71938_D || aBlock == Block.field_71944_C) {
            if(aMeta == 0) {
               if(this.mFluid == null) {
                  this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(1000, true);
                  this.mFluid = GT_ModHandler.getLava(1000L);
               } else {
                  if(!GT_ModHandler.isLava(this.mFluid)) {
                     return false;
                  }

                  this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(1000, true);
                  this.mFluid.amount += 1000;
               }
            } else {
               this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(250, true);
            }
         }

         if(aBlock instanceof IFluidBlock) {
            if(this.mFluid != null) {
               return false;
            }

            this.mFluid = ((IFluidBlock)aBlock).drain(this.getBaseMetaTileEntity().getWorld(), aX, aY, aZ, true);
            this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(this.mFluid == null?1000:this.mFluid.amount, true);
         }

         this.getBaseMetaTileEntity().getWorld().func_72832_d(aX, aY, aZ, 0, 0, 2);
         return true;
      } else {
         return false;
      }
   }

   public void onValueUpdate(byte aValue) {
      this.mPumpCountBelow = aValue;
   }

   public byte getUpdateData() {
      return (byte)this.mPumpCountBelow;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      switch(this.mPumpCountBelow) {
      case 0:
         if(aSide == 0) {
            return 110;
         } else {
            if(aSide == 1) {
               return aActive?69:68;
            }

            return 109;
         }
      case 1:
         if(aSide == 0) {
            return 110;
         } else {
            if(aSide == 1) {
               return 110;
            }

            return aActive?69:68;
         }
      case 19:
         if(aSide == 0) {
            return 110;
         } else {
            if(aSide == 1) {
               return 109;
            }

            return 308;
         }
      case 20:
         return 109;
      default:
         return aSide == 0?110:(aSide == 1?110:308);
      }
   }

   public String getDescription() {
      return "The best way of emptying Oceans!";
   }

   public int getCapacity() {
      return 16000;
   }

   public int getTankPressure() {
      return 100;
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 2;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return GT_Utility.areStacksEqual(aStack, GT_ModHandler.getIC2Item("miningPipe", 1L))?aIndex == 0:aIndex == 1;
   }
}
