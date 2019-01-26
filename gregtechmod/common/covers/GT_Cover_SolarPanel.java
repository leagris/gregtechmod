package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import net.minecraft.item.ItemStack;

public class GT_Cover_SolarPanel extends GT_CoverBehavior {

   private final int mEUtDay;
   private final int mEUtNight;


   public GT_Cover_SolarPanel(ItemStack aStack, int aEUtDay, int aEUtNight) {
      super(aStack);
      this.mEUtDay = aEUtDay;
      this.mEUtNight = aEUtNight;
   }

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      if(aSide == 1 && aTileEntity.getInputVoltage() * 32 >= this.mEUtDay && !aTileEntity.getWorld().func_72911_I()) {
         boolean bRain = aTileEntity.getWorld().func_72896_J() && aTileEntity.getBiome().field_76751_G > 0.0F;
         if((!bRain || aTileEntity.getWorld().field_73008_k < 4) && aTileEntity.getSkyAtSide(aSide)) {
            aTileEntity.increaseStoredEnergyUnits(!bRain && aTileEntity.getWorld().func_72935_r()?this.mEUtDay:this.mEUtNight, false);
         }
      }

      return aCoverVariable;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)32;
   }
}
