package gregtechmod.common.covers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.common.covers.GT_Cover_RedstoneWirelessBase;
import net.minecraft.item.ItemStack;

public class GT_Cover_RedstoneTransmitterInternal extends GT_Cover_RedstoneWirelessBase {

   public GT_Cover_RedstoneTransmitterInternal(ItemStack aStack) {
      super(aStack);
   }

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      GregTech_API.sWirelessRedstone.put(Integer.valueOf(aCoverVariable), Byte.valueOf(aTileEntity.getOutputRedstoneSignal(aSide)));
      return aCoverVariable;
   }

   public boolean letsRedstoneGoOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)1;
   }
}
