package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.metatileentity.MetaPipeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class GT_MetaPipeEntity_Frame extends MetaPipeEntity {

   public GT_MetaPipeEntity_Frame(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaPipeEntity_Frame() {}

   public String getDescription() {
      return "Just something you can put a Cover or CFoam on.";
   }

   public int getTextureIndex(byte aSide, byte aConnections, boolean aActive, boolean aRedstone) {
      return 3;
   }

   public final boolean isSimpleMachine() {
      return true;
   }

   public final boolean isFacingValid(byte aFacing) {
      return false;
   }

   public final boolean isValidSlot(int aIndex) {
      return false;
   }

   public final int getInvSize() {
      return 0;
   }

   public final boolean renderInside() {
      return true;
   }

   public final float getThickNess() {
      return 1.0F;
   }

   public final void saveNBTData(NBTTagCompound aNBT) {}

   public final void loadNBTData(NBTTagCompound aNBT) {}

   public final boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public final boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }
}
