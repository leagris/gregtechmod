package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import ic2.api.item.IElectricItem;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Scanner_Item extends GT_Generic_Item implements IElectricItem {

   public GT_Scanner_Item(int aID, String aUnlocalized, String aEnglish) {
      super(aID, aUnlocalized, aEnglish, "Tricorder");
      this.func_77625_d(1);
      this.func_77656_e(100);
      this.setNoRepair();
   }

   public boolean func_77651_p() {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void func_77633_a(int var1, CreativeTabs var2, List var3) {
      ItemStack tCharged = new ItemStack(this, 1);
      GT_ModHandler.chargeElectricItem(tCharged, this.getMaxCharge(tCharged), Integer.MAX_VALUE, true, false);
      var3.add(tCharged);
   }

   public boolean canProvideEnergy(ItemStack aStack) {
      return false;
   }

   public int getChargedItemId(ItemStack aStack) {
      return this.field_77779_bT;
   }

   public int getEmptyItemId(ItemStack aStack) {
      return this.field_77779_bT;
   }

   public int getMaxCharge(ItemStack aStack) {
      return 100000;
   }

   public int getTier(ItemStack aStack) {
      return 1;
   }

   public int getTransferLimit(ItemStack aStack) {
      return 100;
   }

   public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aClickX, float aClickY, float aClickZ) {
      if(aWorld.field_72995_K) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(108)), 1, 1.0F, (double)aX, (double)aY, (double)aZ);
         return false;
      } else {
         GT_ModHandler.useElectricItem(aStack, 0, aPlayer);
         if(aPlayer instanceof EntityPlayerMP && GT_ModHandler.canUseElectricItem(aStack, 25000)) {
            ArrayList tList = new ArrayList();
            GT_ModHandler.useElectricItem(aStack, GT_Utility.getCoordinateScan(tList, aPlayer, aWorld, 1, aX, aY, aZ, aSide, aClickX, aClickY, aClickZ), aPlayer);

            for(int i = 0; i < tList.size(); ++i) {
               GT_Utility.sendChatToPlayer(aPlayer, (String)tList.get(i));
            }

            return true;
         } else {
            return true;
         }
      }
   }
}
