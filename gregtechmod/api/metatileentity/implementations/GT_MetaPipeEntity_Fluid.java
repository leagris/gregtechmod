package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaPipeEntity;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.FluidEvent.FluidDrainingEvent;
import net.minecraftforge.fluids.FluidEvent.FluidFillingEvent;

public abstract class GT_MetaPipeEntity_Fluid extends MetaPipeEntity {

   public FluidStack mFluid;
   public byte mLastReceivedFrom = 0;
   public byte oLastReceivedFrom = 0;


   public GT_MetaPipeEntity_Fluid(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaPipeEntity_Fluid() {}

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isFacingValid(byte aFacing) {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public final boolean renderInside() {
      return false;
   }

   public final int getInvSize() {
      return 0;
   }

   public int getProgresstime() {
      return this.getFluidAmount();
   }

   public int maxProgresstime() {
      return this.getCapacity();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      if(this.mFluid != null) {
         try {
            aNBT.func_74766_a("mLiquid", this.mFluid.writeToNBT(new NBTTagCompound("mLiquid")));
         } catch (Throwable var3) {
            ;
         }
      }

      aNBT.func_74774_a("mLastReceivedFrom", this.mLastReceivedFrom);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mFluid = FluidStack.loadFluidStackFromNBT(aNBT.func_74775_l("mLiquid"));
      this.mLastReceivedFrom = aNBT.func_74771_c("mLastReceivedFrom");
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().getTimer() % 5L == 0L) {
         this.mLastReceivedFrom = (byte)(this.mLastReceivedFrom & 63);
         if(this.mLastReceivedFrom == 63) {
            this.mLastReceivedFrom = 0;
         }

         if(this.mLastReceivedFrom == this.oLastReceivedFrom) {
            HashMap tTanks = new HashMap();
            this.mConnections = 0;

            for(byte tAmount = 0; tAmount < 6; ++tAmount) {
               IFluidHandler i$ = this.getBaseMetaTileEntity().getITankContainerAtSide(tAmount);
               if(i$ != null) {
                  if(i$ instanceof IGregTechTileEntity && this.getBaseMetaTileEntity().getColorization() >= 0) {
                     byte tTileEntity = ((IGregTechTileEntity)i$).getColorization();
                     if(tTileEntity >= 0 && (tTileEntity & 15) != (this.getBaseMetaTileEntity().getColorization() & 15)) {
                        continue;
                     }
                  }

                  FluidTankInfo[] var8 = i$.getTankInfo(ForgeDirection.getOrientation(tAmount).getOpposite());
                  if(var8 != null && var8.length > 0) {
                     if(this.getBaseMetaTileEntity().getCoverBehaviorAtSide(tAmount).letsLiquidIn(tAmount, this.getBaseMetaTileEntity().getCoverIDAtSide(tAmount), this.getBaseMetaTileEntity().getCoverDataAtSide(tAmount), this.getBaseMetaTileEntity())) {
                        this.mConnections = (byte)(this.mConnections | 1 << tAmount);
                     }

                     if(this.getBaseMetaTileEntity().getCoverBehaviorAtSide(tAmount).letsLiquidOut(tAmount, this.getBaseMetaTileEntity().getCoverIDAtSide(tAmount), this.getBaseMetaTileEntity().getCoverDataAtSide(tAmount), this.getBaseMetaTileEntity())) {
                        this.mConnections = (byte)(this.mConnections | 1 << tAmount);
                        if((1 << tAmount & this.mLastReceivedFrom) == 0) {
                           tTanks.put(i$, ForgeDirection.getOrientation(tAmount).getOpposite());
                        }
                     }

                     if(this.getBaseMetaTileEntity().getCoverBehaviorAtSide(tAmount).alwaysLookConnected(tAmount, this.getBaseMetaTileEntity().getCoverIDAtSide(tAmount), this.getBaseMetaTileEntity().getCoverDataAtSide(tAmount), this.getBaseMetaTileEntity())) {
                        this.mConnections = (byte)(this.mConnections | 1 << tAmount);
                     }
                  }
               }
            }

            int var6 = Math.min(this.getFluidCapacityPerTick() * 20, this.mFluid == null?0:this.mFluid.amount) / 2;
            if(tTanks.size() > 0) {
               int tFilledAmount;
               Iterator var7;
               IFluidHandler var9;
               if(var6 >= tTanks.size()) {
                  var7 = tTanks.keySet().iterator();

                  while(var7.hasNext()) {
                     var9 = (IFluidHandler)var7.next();
                     tFilledAmount = var9.fill((ForgeDirection)tTanks.get(var9), this.drain(var6 / tTanks.size(), false), false);
                     if(tFilledAmount > 0) {
                        var9.fill((ForgeDirection)tTanks.get(var9), this.drain(tFilledAmount, true), true);
                     }
                  }
               } else if(this.mFluid != null && this.mFluid.amount > 0) {
                  var7 = tTanks.keySet().iterator();

                  while(var7.hasNext()) {
                     var9 = (IFluidHandler)var7.next();
                     tFilledAmount = var9.fill((ForgeDirection)tTanks.get(var9), this.drain(this.mFluid.amount, false), false);
                     if(tFilledAmount > 0) {
                        var9.fill((ForgeDirection)tTanks.get(var9), this.drain(tFilledAmount, true), true);
                     }

                     if(this.mFluid == null || this.mFluid.amount <= 0) {
                        break;
                     }
                  }
               }
            }

            this.mLastReceivedFrom = 0;
         }

         this.oLastReceivedFrom = this.mLastReceivedFrom;
      }

   }

   public abstract int getFluidCapacityPerTick();

   public final int getCapacity() {
      return this.getFluidCapacityPerTick() * 20;
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public final FluidStack getFluid() {
      return this.mFluid;
   }

   public final int getFluidAmount() {
      return this.mFluid != null?this.mFluid.amount:0;
   }

   public final int fill_default(ForgeDirection aSide, FluidStack aFluid, boolean doFill) {
      if(aFluid != null && aFluid.fluidID > 0) {
         if(this.mFluid != null && this.mFluid.fluidID > 0) {
            if(!this.mFluid.isFluidEqual(aFluid)) {
               return 0;
            } else {
               int space = this.getCapacity() - this.mFluid.amount;
               if(aFluid.amount <= space) {
                  if(doFill) {
                     this.mFluid.amount += aFluid.amount;
                     this.mLastReceivedFrom = (byte)(this.mLastReceivedFrom | 1 << aSide.ordinal());
                  }

                  return aFluid.amount;
               } else {
                  if(doFill) {
                     this.mFluid.amount = this.getCapacity();
                     this.mLastReceivedFrom = (byte)(this.mLastReceivedFrom | 1 << aSide.ordinal());
                  }

                  return space;
               }
            }
         } else if(aFluid.amount <= this.getCapacity()) {
            if(doFill) {
               this.mFluid = aFluid.copy();
               this.mLastReceivedFrom = (byte)(this.mLastReceivedFrom | 1 << aSide.ordinal());
            }

            return aFluid.amount;
         } else {
            if(doFill) {
               this.mFluid = aFluid.copy();
               this.mLastReceivedFrom = (byte)(this.mLastReceivedFrom | 1 << aSide.ordinal());
               this.mFluid.amount = this.getCapacity();
               if(this.getBaseMetaTileEntity() != null) {
                  FluidEvent.fireEvent(new FluidFillingEvent(this.mFluid, this.getBaseMetaTileEntity().getWorld(), this.getBaseMetaTileEntity().getXCoord(), this.getBaseMetaTileEntity().getYCoord(), this.getBaseMetaTileEntity().getZCoord(), this));
               }
            }

            return this.getCapacity();
         }
      } else {
         return 0;
      }
   }

   public final FluidStack drain(int maxDrain, boolean doDrain) {
      if(this.mFluid == null) {
         return null;
      } else if(this.mFluid.amount <= 0) {
         this.mFluid = null;
         return null;
      } else {
         int used = maxDrain;
         if(this.mFluid.amount < maxDrain) {
            used = this.mFluid.amount;
         }

         if(doDrain) {
            this.mFluid.amount -= used;
         }

         FluidStack drained = this.mFluid.copy();
         drained.amount = used;
         if(this.mFluid.amount <= 0) {
            this.mFluid = null;
         }

         if(doDrain && this.getBaseMetaTileEntity() != null) {
            FluidEvent.fireEvent(new FluidDrainingEvent(drained, this.getBaseMetaTileEntity().getWorld(), this.getBaseMetaTileEntity().getXCoord(), this.getBaseMetaTileEntity().getYCoord(), this.getBaseMetaTileEntity().getZCoord(), this));
         }

         return drained;
      }
   }

   public int getTankPressure() {
      return (this.mFluid == null?0:this.mFluid.amount) - this.getCapacity() / 2;
   }

   public String getDescription() {
      return "Fluid Capacity: " + this.getFluidCapacityPerTick() * 20 + "L/sec";
   }
}
