package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_LanguageManager;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_IntegratedCircuit_Item extends GT_Generic_Item {

   public GT_IntegratedCircuit_Item(int aID, String aUnlocalized, String aEnglish) {
      super(aID, aUnlocalized, aEnglish, "");
      this.func_77627_a(true);
      this.func_77656_e(0);
   }

   public ItemStack func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      return aStack;
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".configuration", "Configuration: ") + getConfigurationString(this.getDamage(aStack)));
   }

   @SideOnly(Side.CLIENT)
   public final void func_77633_a(int var1, CreativeTabs aCreativeTab, List aList) {
      aList.add(new ItemStack(this, 1, 0));
   }

   private static String getModeString(int aMetaData) {
      switch((byte)(aMetaData >>> 8)) {
      case 0:
         return "==";
      case 1:
         return "<=";
      case 2:
         return ">=";
      case 3:
         return "<";
      case 4:
         return ">";
      default:
         return "";
      }
   }

   private static String getConfigurationString(int aMetaData) {
      return getModeString(aMetaData) + " " + (byte)(aMetaData & 255);
   }
}
