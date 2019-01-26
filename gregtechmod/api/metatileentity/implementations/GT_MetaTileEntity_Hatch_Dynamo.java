package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Hatch_Dynamo extends MetaTileEntity {

   public GT_MetaTileEntity_Hatch_Dynamo(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Hatch_Dynamo() {}

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing();
   }

   public int getInvSize() {
      return 0;
   }

   public int getMinimumStoredEU() {
      return 512;
   }

   public int maxEUOutput() {
      return Math.max(0, Math.min(this.getEUVar() - this.getMinimumStoredEU(), 2048));
   }

   public int maxEUStore() {
      return 8192 + this.getMinimumStoredEU();
   }

   public String getSpecialVoltageToolTip() {
      return "Max EU/p OUT: 0 - 2048 (depends on generated Energy)";
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Hatch_Dynamo();
   }

   public void saveNBTData(NBTTagCompound aNBT) {}

   public void loadNBTData(NBTTagCompound aNBT) {}

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?274:(aSide == 0?32:(aSide == 1?29:40));
   }

   public String getDescription() {
      return "Generating electric Energy from Multiblocks";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }
}
