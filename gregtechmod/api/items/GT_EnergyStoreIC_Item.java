package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_EnergyStore_Item;
import ic2.api.item.IElectricItem;
import net.minecraft.item.ItemStack;

public class GT_EnergyStoreIC_Item extends GT_EnergyStore_Item implements IElectricItem {

   public GT_EnergyStoreIC_Item(int aID, String aUnlocalized, String aEnglish, int aCharge, int aTransfer, int aTier, int aEmptyID, int aFullID) {
      super(aID, aUnlocalized, aEnglish, aCharge, aTransfer, aTier, aEmptyID, aFullID);
   }

   public boolean canProvideEnergy(ItemStack aStack) {
      return true;
   }

   public int getChargedItemId(ItemStack aStack) {
      return GregTech_API.sItemList[this.mFullID].field_77779_bT;
   }

   public int getEmptyItemId(ItemStack aStack) {
      return GregTech_API.sItemList[this.mEmptyID].field_77779_bT;
   }

   public int getMaxCharge(ItemStack aStack) {
      return this.mCharge;
   }

   public int getTransferLimit(ItemStack aStack) {
      return this.mTransfer;
   }
}
