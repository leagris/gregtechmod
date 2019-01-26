package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Cover_ItemMeter extends GT_CoverBehavior {

   public GT_Cover_ItemMeter(ItemStack aStack) {
      super(aStack);
   }

   public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      int[] tSlots;
      if(aCoverVariable < 2) {
         tSlots = aTileEntity.func_94128_d(aSide);
      } else {
         tSlots = new int[]{aCoverVariable - 2};
      }

      int tAll = 0;
      int tFull = 0;
      int[] arr$ = tSlots;
      int len$ = tSlots.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         int i = arr$[i$];
         if(i > 0 && i < aTileEntity.func_70302_i_()) {
            tAll += 64;
            ItemStack tStack = aTileEntity.func_70301_a(i);
            if(tStack != null) {
               tFull += tStack.field_77994_a * 64 / tStack.func_77976_d();
            }
         }
      }

      tAll /= 15;
      if(tAll > 0) {
         aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable != 1?(byte)(tFull / tAll):(byte)(15 - tFull / tAll));
      } else {
         aTileEntity.setOutputRedstoneSignal(aSide, (byte)(aCoverVariable != 1?0:15));
      }

      return aCoverVariable;
   }

   public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      aCoverVariable = aCoverVariable++ % (2 + aTileEntity.func_70302_i_());
      if(aCoverVariable == 0) {
         GT_Utility.sendChatToPlayer(aPlayer, "Normal");
      } else if(aCoverVariable == 1) {
         GT_Utility.sendChatToPlayer(aPlayer, "Inverted");
      } else {
         GT_Utility.sendChatToPlayer(aPlayer, "Slot: " + (aCoverVariable - 2));
      }

      return aCoverVariable;
   }

   public boolean letsEnergyIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsEnergyOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsLiquidIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsLiquidOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public boolean manipulatesSidedRedstoneOutput(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return true;
   }

   public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
      return (short)5;
   }
}
