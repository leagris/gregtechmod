package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public abstract class GT_MetaTileEntity_BasicGenerator extends GT_MetaTileEntity_BasicTank {

   public GT_MetaTileEntity_BasicGenerator(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BasicGenerator() {}

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 2;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isOutputFacing(byte aSide) {
      return true;
   }

   public int maxEUOutput() {
      return this.getBaseMetaTileEntity().isAllowedToWork()?12:0;
   }

   public int maxEUStore() {
      return 1000000;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean doesFillContainers() {
      return this.getBaseMetaTileEntity().isAllowedToWork();
   }

   public boolean doesEmptyContainers() {
      return this.getBaseMetaTileEntity().isAllowedToWork();
   }

   public boolean canTankBeFilled() {
      return this.getBaseMetaTileEntity().isAllowedToWork();
   }

   public boolean canTankBeEmptied() {
      return this.getBaseMetaTileEntity().isAllowedToWork();
   }

   public boolean displaysItemStack() {
      return true;
   }

   public boolean displaysStackSize() {
      return false;
   }

   public boolean isFluidInputAllowed(FluidStack aFluid) {
      return this.getFuelValue(aFluid) > 0;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().getTimer() % 10L == 0L) {
         int tFuelValue;
         if(this.mFluid == null) {
            if(this.getBaseMetaTileEntity().getUniversalEnergyStored() < this.getBaseMetaTileEntity().getOutputVoltage() + this.getMinimumStoredEU()) {
               this.mInventory[this.getStackDisplaySlot()] = null;
            } else {
               if(this.mInventory[this.getStackDisplaySlot()] == null) {
                  this.mInventory[this.getStackDisplaySlot()] = new ItemStack(Block.field_72067_ar, 1);
               }

               this.mInventory[this.getStackDisplaySlot()].func_82834_c("Generating: " + (this.getBaseMetaTileEntity().getUniversalEnergyStored() - this.getMinimumStoredEU()) + " EU");
            }
         } else {
            tFuelValue = this.getFuelValue(this.mFluid);
            if(tFuelValue > 0) {
               while(this.getBaseMetaTileEntity().getUniversalEnergyStored() < this.getBaseMetaTileEntity().getOutputVoltage() * 10 + this.getMinimumStoredEU() && this.mFluid.amount > 0) {
                  if(this.getBaseMetaTileEntity().increaseStoredEnergyUnits(tFuelValue, true)) {
                     --this.mFluid.amount;
                  }
               }
            }
         }

         if(this.mInventory[this.getInputSlot()] != null && this.getBaseMetaTileEntity().getUniversalEnergyStored() < this.getBaseMetaTileEntity().getOutputVoltage() * 10 + this.getMinimumStoredEU() && GT_Utility.getFluidForFilledItem(this.mInventory[this.getInputSlot()]) == null) {
            tFuelValue = this.getFuelValue(this.mInventory[this.getInputSlot()]);
            if(tFuelValue > 0) {
               ItemStack tEmptyContainer = this.getEmptyContainer(this.mInventory[this.getInputSlot()]);
               if(this.getBaseMetaTileEntity().addStackToSlot(this.getOutputSlot(), tEmptyContainer)) {
                  this.getBaseMetaTileEntity().increaseStoredEnergyUnits(tFuelValue, true);
                  this.getBaseMetaTileEntity().func_70298_a(this.getInputSlot(), 1);
               }
            }
         }
      }

      if(this.getBaseMetaTileEntity().isServerSide()) {
         this.getBaseMetaTileEntity().setActive(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().getUniversalEnergyStored() >= this.getBaseMetaTileEntity().getOutputVoltage() + this.getMinimumStoredEU());
      }

   }

   public abstract List<GT_Recipe> getRecipes();

   public abstract int getEfficiency();

   public int getFuelValue(FluidStack aLiquid) {
      if(aLiquid == null) {
         return 0;
      } else {
         Iterator i$ = this.getRecipes().iterator();

         FluidStack tLiquid;
         GT_Recipe tFuel;
         do {
            if(!i$.hasNext()) {
               return 0;
            }

            tFuel = (GT_Recipe)i$.next();
         } while((tLiquid = GT_Utility.getFluidForFilledItem(tFuel.getRepresentativeInput(0))) == null || !aLiquid.isFluidEqual(tLiquid));

         return (int)((long)tFuel.mStartEU * (long)this.getEfficiency() / 100L);
      }
   }

   public int getFuelValue(ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return 0;
      } else {
         GT_Recipe tFuel = GT_Recipe.findEqualRecipe(true, false, this.getRecipes(), new ItemStack[]{aStack});
         return tFuel != null?(int)((long)tFuel.mStartEU * 1000L * (long)this.getEfficiency() / 100L):0;
      }
   }

   public ItemStack getEmptyContainer(ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return null;
      } else {
         GT_Recipe tFuel = GT_Recipe.findEqualRecipe(true, false, this.getRecipes(), new ItemStack[]{aStack});
         return tFuel != null?GT_Utility.copy(new Object[]{tFuel.getOutput(0)}):GT_Utility.getContainerItem(aStack);
      }
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return super.allowPutStack(aIndex, aSide, aStack) && (this.getFuelValue(aStack) > 0 || this.getFuelValue(GT_Utility.getFluidForFilledItem(aStack)) > 0);
   }

   public int getCapacity() {
      return 10000;
   }

   public int getTankPressure() {
      return -100;
   }
}
