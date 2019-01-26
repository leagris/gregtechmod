package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_FusionEnergyInjector extends MetaTileEntity {

   public IGregTechTileEntity mFusionComputer;


   public GT_MetaTileEntity_FusionEnergyInjector(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_FusionEnergyInjector() {}

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return false;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isInputFacing(byte aSide) {
      return true;
   }

   public int maxEUInput() {
      return 8192;
   }

   public int maxEUStore() {
      return 10000000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public int getInvSize() {
      return 0;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_FusionEnergyInjector();
   }

   public void saveNBTData(NBTTagCompound aNBT) {}

   public void loadNBTData(NBTTagCompound aNBT) {}

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         this.getBaseMetaTileEntity().setActive(this.mFusionComputer != null && this.mFusionComputer.isActive());
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aActive?20:19;
   }

   public String getDescription() {
      return "Stores 10kk EU for the Fusion Reactor";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }
}
