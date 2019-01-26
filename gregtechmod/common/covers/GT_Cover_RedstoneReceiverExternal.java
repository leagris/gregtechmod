package gregtechmod.common.covers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.common.covers.GT_Cover_RedstoneWirelessBase;
import net.minecraft.item.ItemStack;

public class GT_Cover_RedstoneReceiverExternal extends GT_Cover_RedstoneWirelessBase {

   public GT_Cover_RedstoneReceiverExternal(ItemStack aStack) {
      super(aStack);
   }

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      aTileEntity.setOutputRedstoneSignal(aSide, GregTech_API.sWirelessRedstone.get(Integer.valueOf(aCoverVariable)) == null?0:((Byte)GregTech_API.sWirelessRedstone.get(Integer.valueOf(aCoverVariable))).byteValue());
      return aCoverVariable;
   }

   public boolean manipulatesSidedRedstoneOutput(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)1;
   }
}
