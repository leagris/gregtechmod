package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidEvent.FluidDrainingEvent;
import net.minecraftforge.fluids.FluidEvent.FluidFillingEvent;

public abstract class GT_MetaTileEntity_BasicTank extends MetaTileEntity {

   public FluidStack mFluid;


   public GT_MetaTileEntity_BasicTank(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BasicTank() {}

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 2;
   }

   public int getInvSize() {
      return 3;
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      if(this.mFluid != null) {
         try {
            aNBT.func_74766_a("mLiquid", this.mFluid.writeToNBT(new NBTTagCompound("mLiquid")));
         } catch (Throwable var3) {
            ;
         }
      }

   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mFluid = FluidStack.loadFluidStackFromNBT(aNBT.func_74775_l("mLiquid"));
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?38:(aSide == 1?79:36);
   }

   public abstract boolean doesFillContainers();

   public abstract boolean doesEmptyContainers();

   public abstract boolean canTankBeFilled();

   public abstract boolean canTankBeEmptied();

   public abstract boolean displaysItemStack();

   public abstract boolean displaysStackSize();

   public int getInputSlot() {
      return 0;
   }

   public int getOutputSlot() {
      return 1;
   }

   public int getStackDisplaySlot() {
      return 2;
   }

   public boolean isFluidInputAllowed(FluidStack aFluid) {
      return true;
   }

   public boolean isFluidChangingAllowed() {
      return true;
   }

   public FluidStack getFillableStack() {
      return this.mFluid;
   }

   public FluidStack setFillableStack(FluidStack aFluid) {
      this.mFluid = aFluid;
      return this.mFluid;
   }

   public FluidStack getDrainableStack() {
      return this.mFluid;
   }

   public FluidStack setDrainableStack(FluidStack aFluid) {
      this.mFluid = aFluid;
      return this.mFluid;
   }

   public void onPreTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.isFluidChangingAllowed() && this.mFluid != null && this.mFluid.amount <= 0) {
            this.mFluid = null;
         }

         if(this.displaysItemStack()) {
            if(this.getDrainableStack() != null) {
               this.mInventory[this.getStackDisplaySlot()] = GT_Items.Display_Fluid.getWithDamage(this.displaysStackSize()?(long)Math.max(1, Math.min(this.getDrainableStack().amount / 1000, 64)):1L, (long)this.getDrainableStack().fluidID, new Object[0]);
            } else if(GT_Items.Display_Fluid.isStackEqual(this.mInventory[this.getStackDisplaySlot()], true, true)) {
               this.mInventory[this.getStackDisplaySlot()] = null;
            }
         }

         FluidStack var10000;
         if(this.doesEmptyContainers()) {
            FluidStack tOutput = GT_Utility.getFluidForFilledItem(this.mInventory[this.getInputSlot()]);
            if(tOutput != null && this.isFluidInputAllowed(tOutput)) {
               if(this.getFillableStack() == null) {
                  if(this.isFluidInputAllowed(tOutput) && tOutput.amount <= this.getCapacity() && this.getBaseMetaTileEntity().addStackToSlot(this.getOutputSlot(), GT_Utility.getContainerForFilledItem(this.mInventory[this.getInputSlot()]), 1)) {
                     this.setFillableStack(tOutput.copy());
                     this.getBaseMetaTileEntity().func_70298_a(this.getInputSlot(), 1);
                  }
               } else if(tOutput.isFluidEqual(this.getFillableStack()) && tOutput.amount + this.getFillableStack().amount <= this.getCapacity() && this.getBaseMetaTileEntity().addStackToSlot(this.getOutputSlot(), GT_Utility.getContainerForFilledItem(this.mInventory[this.getInputSlot()]), 1)) {
                  var10000 = this.getFillableStack();
                  var10000.amount += tOutput.amount;
                  this.getBaseMetaTileEntity().func_70298_a(this.getInputSlot(), 1);
               }
            }
         }

         if(this.doesFillContainers()) {
            ItemStack tOutput1 = GT_Utility.fillFluidContainer(this.getDrainableStack(), this.mInventory[this.getInputSlot()]);
            if(tOutput1 != null && this.getBaseMetaTileEntity().addStackToSlot(this.getOutputSlot(), tOutput1, 1)) {
               FluidStack tFluid = GT_Utility.getFluidForFilledItem(tOutput1);
               this.getBaseMetaTileEntity().func_70298_a(this.getInputSlot(), 1);
               if(tFluid != null) {
                  var10000 = this.getDrainableStack();
                  var10000.amount -= tFluid.amount;
               }

               if(this.getDrainableStack().amount <= 0 && this.isFluidChangingAllowed()) {
                  this.setDrainableStack((FluidStack)null);
               }
            }
         }
      }

   }

   public final FluidStack getFluid() {
      return this.getDrainableStack();
   }

   public final int getFluidAmount() {
      return this.getDrainableStack() != null?this.getDrainableStack().amount:0;
   }

   public final int fill(FluidStack aFluid, boolean doFill) {
      if(aFluid != null && aFluid.fluidID > 0 && this.canTankBeFilled() && this.isFluidInputAllowed(aFluid)) {
         if(this.getFillableStack() != null && this.getFillableStack().fluidID > 0) {
            if(!this.getFillableStack().isFluidEqual(aFluid)) {
               return 0;
            } else {
               int space = this.getCapacity() - this.getFillableStack().amount;
               if(aFluid.amount <= space) {
                  if(doFill) {
                     FluidStack var10000 = this.getFillableStack();
                     var10000.amount += aFluid.amount;
                  }

                  return aFluid.amount;
               } else {
                  if(doFill) {
                     this.getFillableStack().amount = this.getCapacity();
                  }

                  return space;
               }
            }
         } else if(aFluid.amount <= this.getCapacity()) {
            if(doFill) {
               this.setFillableStack(aFluid.copy());
            }

            return aFluid.amount;
         } else {
            if(doFill) {
               this.setFillableStack(aFluid.copy());
               this.getFillableStack().amount = this.getCapacity();
               if(this.getBaseMetaTileEntity() != null) {
                  FluidEvent.fireEvent(new FluidFillingEvent(this.getFillableStack(), this.getBaseMetaTileEntity().getWorld(), this.getBaseMetaTileEntity().getXCoord(), this.getBaseMetaTileEntity().getYCoord(), this.getBaseMetaTileEntity().getZCoord(), this));
               }
            }

            return this.getCapacity();
         }
      } else {
         return 0;
      }
   }

   public final FluidStack drain(int maxDrain, boolean doDrain) {
      if(this.getDrainableStack() != null && this.canTankBeEmptied()) {
         if(this.getDrainableStack().amount <= 0 && this.isFluidChangingAllowed()) {
            this.setDrainableStack((FluidStack)null);
            return null;
         } else {
            int used = maxDrain;
            if(this.getDrainableStack().amount < maxDrain) {
               used = this.getDrainableStack().amount;
            }

            if(doDrain) {
               FluidStack var10000 = this.getDrainableStack();
               var10000.amount -= used;
            }

            FluidStack drained = this.getDrainableStack().copy();
            drained.amount = used;
            if(this.getDrainableStack().amount <= 0 && this.isFluidChangingAllowed()) {
               this.setDrainableStack((FluidStack)null);
            }

            if(doDrain && this.getBaseMetaTileEntity() != null) {
               FluidEvent.fireEvent(new FluidDrainingEvent(drained, this.getBaseMetaTileEntity().getWorld(), this.getBaseMetaTileEntity().getXCoord(), this.getBaseMetaTileEntity().getYCoord(), this.getBaseMetaTileEntity().getZCoord(), this));
            }

            return drained;
         }
      } else {
         return null;
      }
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == this.getOutputSlot();
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == this.getInputSlot();
   }
}
