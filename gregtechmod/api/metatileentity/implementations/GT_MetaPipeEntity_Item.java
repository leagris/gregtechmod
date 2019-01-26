package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.MetaPipeEntity;
import gregtechmod.api.util.GT_Utility;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityHopper;

public abstract class GT_MetaPipeEntity_Item extends MetaPipeEntity {

   public int mTransferredItems = 0;
   public byte mLastReceivedFrom = 0;
   public byte oLastReceivedFrom = 0;


   public GT_MetaPipeEntity_Item(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaPipeEntity_Item() {}

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isFacingValid(byte aFacing) {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return true;
   }

   public final boolean renderInside() {
      return false;
   }

   public final int getInvSize() {
      return this.getMaxPipeCapacity();
   }

   public int getProgresstime() {
      return this.getPipeContent() * 64;
   }

   public int maxProgresstime() {
      return this.getMaxPipeCapacity() * 64;
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74774_a("mLastReceivedFrom", this.mLastReceivedFrom);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mLastReceivedFrom = aNBT.func_74771_c("mLastReceivedFrom");
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().getTimer() % 10L == 0L) {
         this.mConnections = 0;
         if(this.getBaseMetaTileEntity().getTimer() % 20L == 0L) {
            this.mTransferredItems = 0;
         }

         for(byte tMap = 0; tMap < 6; ++tMap) {
            TileEntity i$ = this.getBaseMetaTileEntity().getTileEntityAtSide(tMap);
            if(i$ != null) {
               boolean tTileEntity = GT_Utility.isConnectableNonInventoryPipe(i$, GT_Utility.getOppositeSide(tMap));
               if(i$ instanceof IGregTechTileEntity) {
                  tTileEntity = true;
                  if(this.getBaseMetaTileEntity().getMetaTileEntity() == null) {
                     continue;
                  }

                  if(this.getBaseMetaTileEntity().getColorization() >= 0) {
                     byte tSlots = ((IGregTechTileEntity)i$).getColorization();
                     if(tSlots >= 0 && (tSlots & 15) != (this.getBaseMetaTileEntity().getColorization() & 15)) {
                        continue;
                     }
                  }

                  if(((IGregTechTileEntity)i$).getMetaTileEntity().connectsToItemPipe(GT_Utility.getOppositeSide(tMap))) {
                     this.mConnections = (byte)(this.mConnections | 1 << tMap);
                     continue;
                  }
               }

               if(i$ instanceof IInventory) {
                  tTileEntity = true;
                  if(((IInventory)i$).func_70302_i_() <= 0) {
                     continue;
                  }
               }

               if(i$ instanceof ISidedInventory) {
                  tTileEntity = true;
                  int[] var7 = ((ISidedInventory)i$).func_94128_d(GT_Utility.getOppositeSide(tMap));
                  if(var7 == null || var7.length <= 0) {
                     continue;
                  }
               }

               if(tTileEntity) {
                  if(this.getBaseMetaTileEntity().getCoverBehaviorAtSide(tMap).alwaysLookConnected(tMap, this.getBaseMetaTileEntity().getCoverIDAtSide(tMap), this.getBaseMetaTileEntity().getCoverDataAtSide(tMap), this.getBaseMetaTileEntity())) {
                     this.mConnections = (byte)(this.mConnections | 1 << tMap);
                  } else if(this.getBaseMetaTileEntity().getCoverBehaviorAtSide(tMap).letsItemsIn(tMap, this.getBaseMetaTileEntity().getCoverIDAtSide(tMap), this.getBaseMetaTileEntity().getCoverDataAtSide(tMap), this.getBaseMetaTileEntity())) {
                     this.mConnections = (byte)(this.mConnections | 1 << tMap);
                  } else if(this.getBaseMetaTileEntity().getCoverBehaviorAtSide(tMap).letsItemsOut(tMap, this.getBaseMetaTileEntity().getCoverIDAtSide(tMap), this.getBaseMetaTileEntity().getCoverDataAtSide(tMap), this.getBaseMetaTileEntity())) {
                     this.mConnections = (byte)(this.mConnections | 1 << tMap);
                  }
               }
            }
         }

         if(this.oLastReceivedFrom == this.mLastReceivedFrom && !this.isInventoryEmpty() && this.pipeCapacityCheck()) {
            this.doTickProfilingInThisTick = false;
            LinkedHashMap var5 = GT_Utility.sortMapByValuesAcending(GT_Utility.scanPipes(this, new HashMap(), 0L, false));
            Iterator var6 = var5.keySet().iterator();

            while(var6.hasNext()) {
               GT_MetaPipeEntity_Item var8 = (GT_MetaPipeEntity_Item)var6.next();
               var8.sendItemStack(this.getBaseMetaTileEntity());
               if(this.isInventoryEmpty()) {
                  break;
               }
            }
         }

         if(this.isInventoryEmpty()) {
            this.mLastReceivedFrom = 6;
         }

         this.oLastReceivedFrom = this.mLastReceivedFrom;
      }

   }

   private void sendItemStack(Object aSender) {
      if(this.pipeCapacityCheck()) {
         byte tOffset = (byte)this.getBaseMetaTileEntity().getRandomNumber(6);
         boolean tSide = false;

         for(byte i = 0; i < 6; ++i) {
            byte var5 = (byte)((i + tOffset) % 6);
            if(this.isInventoryEmpty() || var5 != this.mLastReceivedFrom || aSender != this.getBaseMetaTileEntity()) {
               this.insertItemStackIntoTileEntity(aSender, var5);
            }
         }

         ++this.mTransferredItems;
      }

   }

   public void insertItemStackIntoTileEntity(Object aSender, byte aSide) {
      if(this.getBaseMetaTileEntity().getCoverBehaviorAtSide(aSide).letsItemsOut(aSide, this.getBaseMetaTileEntity().getCoverIDAtSide(aSide), this.getBaseMetaTileEntity().getCoverDataAtSide(aSide), this.getBaseMetaTileEntity())) {
         TileEntity tInventory = this.getBaseMetaTileEntity().getTileEntityAtSide(aSide);
         if(tInventory != null && !(tInventory instanceof BaseMetaPipeEntity) && (!(tInventory instanceof TileEntityHopper) && !(tInventory instanceof TileEntityDispenser) || this.getBaseMetaTileEntity().getMetaIDAtSide(aSide) != GT_Utility.getOppositeSide(aSide))) {
            while(true) {
               if(GT_Utility.moveOneItemStack(aSender, tInventory, (byte)6, GT_Utility.getOppositeSide(aSide), (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1) > 0) {
                  continue;
               }
            }
         }
      }

   }

   public boolean pipeCapacityCheck() {
      return this.mTransferredItems <= 0 || this.getPipeContent() < this.getMaxPipeCapacity();
   }

   private int getPipeContent() {
      return this.mTransferredItems;
   }

   private int getMaxPipeCapacity() {
      return Math.max(1, this.getPipeCapacity());
   }

   public abstract int getPipeCapacity();

   public abstract int getStepSize();

   public boolean func_102008_b(int aIndex, ItemStack aStack, int aSide) {
      return true;
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return true;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      if(this.isInventoryEmpty()) {
         this.mLastReceivedFrom = aSide;
      }

      return this.mLastReceivedFrom == aSide && this.mInventory[aIndex] == null;
   }

   public String getDescription() {
      return "Item Capacity: " + this.getMaxPipeCapacity() + " Stacks/sec";
   }

   private boolean isInventoryEmpty() {
      ItemStack[] arr$ = this.mInventory;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ItemStack tStack = arr$[i$];
         if(tStack != null) {
            return false;
         }
      }

      return true;
   }
}
