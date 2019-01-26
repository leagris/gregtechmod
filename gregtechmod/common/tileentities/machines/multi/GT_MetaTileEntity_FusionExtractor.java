package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_FusionExtractor extends GT_MetaTileEntity_BasicTank {

   public IGregTechTileEntity mFusionComputer;


   public GT_MetaTileEntity_FusionExtractor(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_FusionExtractor() {}

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
      this.getBaseMetaTileEntity().openGUI(aPlayer, 145);
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_FusionExtractor();
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

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide != 0 && aSide != 1?16:(aActive?20:19);
   }

   public String getDescription() {
      return "Extracts your fused Materials out of the Coils";
   }

   public int getCapacity() {
      return 10000;
   }

   public int getTankPressure() {
      return 100;
   }
}
