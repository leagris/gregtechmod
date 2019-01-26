package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_FusionInjector extends GT_MetaTileEntity_BasicTank {

   public IGregTechTileEntity mFusionComputer;


   public GT_MetaTileEntity_FusionInjector(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_FusionInjector() {}

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 2;
   }

   public boolean isFacingValid(byte aFacing) {
      return false;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 144);
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_FusionInjector();
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
      return true;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().getTimer() % 20L == 0L) {
         this.getBaseMetaTileEntity().setActive(this.mFusionComputer != null && this.mFusionComputer.isActive());
      }

   }

   public ItemStack getMaterial() {
      if(this.mInventory[this.getInputSlot()] == null) {
         ItemStack tStack = GT_Utility.fillFluidContainer(this.mFluid, GT_Items.Cell_Empty.get(1L, new Object[0]));
         if(tStack == null) {
            return null;
         } else {
            tStack.field_77994_a = this.mFluid.amount / GT_Utility.getFluidForFilledItem(tStack).amount;
            return tStack;
         }
      } else {
         return this.mInventory[this.getInputSlot()];
      }
   }

   public boolean consumeMaterial(ItemStack aStack) {
      if(aStack == null) {
         return false;
      } else {
         FluidStack tFluid = GT_Utility.getFluidForFilledItem(aStack);
         if(this.mFluid != null && tFluid != null && tFluid.isFluidEqual(this.mFluid) && tFluid.amount * aStack.field_77994_a <= this.mFluid.amount) {
            this.mFluid.amount -= tFluid.amount * aStack.field_77994_a;
            return true;
         } else if(GT_Utility.areStacksEqual(this.mInventory[this.getInputSlot()], aStack) && this.mInventory[this.getInputSlot()].field_77994_a >= aStack.field_77994_a) {
            this.getBaseMetaTileEntity().addStackToSlot(this.getOutputSlot(), GT_Utility.mul((long)aStack.field_77994_a, new Object[]{GT_Utility.getContainerItem(aStack)}));
            this.getBaseMetaTileEntity().func_70298_a(this.getInputSlot(), aStack.field_77994_a);
            return true;
         } else {
            return false;
         }
      }
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide != 0 && aSide != 1?(aActive?20:19):16;
   }

   public int getCapacity() {
      return 10000;
   }

   public String getDescription() {
      return "To inject your fusable Fluids into the Coils";
   }

   public int getTankPressure() {
      return -100;
   }
}
