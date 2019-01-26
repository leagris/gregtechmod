package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Hatch_Muffler extends MetaTileEntity {

   public GT_MetaTileEntity_Hatch_Muffler(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Hatch_Muffler() {}

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public int getInvSize() {
      return 0;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Hatch_Muffler();
   }

   public void saveNBTData(NBTTagCompound aNBT) {}

   public void loadNBTData(NBTTagCompound aNBT) {}

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?279:(aSide == 0?32:(aSide == 1?29:40));
   }

   public String getDescription() {
      return "Outputs the Pollution (Pollution might come later)";
   }

   public boolean polluteEnvironment() {
      return this.getBaseMetaTileEntity().getAirAtSide(this.getBaseMetaTileEntity().getFrontFacing());
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }
}
