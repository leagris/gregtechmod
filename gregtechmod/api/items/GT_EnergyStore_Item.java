package gregtechmod.api.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_ModHandler;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class GT_EnergyStore_Item extends GT_Generic_Item {

   protected int mCharge;
   protected int mTransfer;
   protected int mTier;
   protected int mEmptyID;
   protected int mFullID;


   public GT_EnergyStore_Item(int aID, String aUnlocalized, String aEnglish, int aCharge, int aTransfer, int aTier, int aEmptyID, int aFullID) {
      super(aID, aUnlocalized, aEnglish, (String)null);
      this.func_77625_d(1);
      this.func_77656_e(100);
      this.setNoRepair();
      this.mCharge = aCharge;
      this.mTransfer = aTransfer;
      this.mTier = aTier;
      this.mEmptyID = aEmptyID;
      this.mFullID = aFullID;
   }

   public boolean func_77651_p() {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void func_77633_a(int var1, CreativeTabs var2, List var3) {
      ItemStack tCharged = new ItemStack(GregTech_API.sItemList[this.mFullID], 1, 0);
      ItemStack tUncharged = new ItemStack(GregTech_API.sItemList[this.mEmptyID], 1, this.func_77612_l() - 1);
      GT_ModHandler.chargeElectricItem(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
      if(this == GregTech_API.sItemList[this.mFullID]) {
         var3.add(tCharged);
      }

      if(this == GregTech_API.sItemList[this.mEmptyID]) {
         var3.add(tUncharged);
      }

   }

   public int func_77619_b() {
      return 0;
   }

   public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
      return false;
   }

   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return false;
   }

   public int getTier(ItemStack aStack) {
      return this.mTier;
   }
}
