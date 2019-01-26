package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_Utility;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GT_MetaItem_Abstract extends Item {

   protected static final short MAXIMUM_META_IDS = 512;
   public String[] mToolTipList = new String[512];
   public ItemStack[] mStackList = new ItemStack[512];
   public boolean[] mGlowList = new boolean[512];
   public Icon[] mIconList = new Icon[512];


   public GT_MetaItem_Abstract(int aID, String aName) {
      super(aID);
      this.func_77637_a(GregTech_API.TAB_GREGTECH_MATERIALS);
      this.func_77656_e(0);
      this.func_77627_a(true);
      Arrays.fill(this.mGlowList, false);
      Arrays.fill(this.mToolTipList, "");
      this.func_77655_b(aName);
   }

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IconRegister par1IconRegister) {
      for(int i = 0; i < 512; ++i) {
         if(this.mStackList[i] != null) {
            this.mIconList[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":this.func_77658_a() + "/" + i));
         }
      }

   }

   public Icon func_77617_a(int aIndex) {
      return this.mIconList[aIndex];
   }

   public int func_77647_b(int aIndex) {
      return aIndex;
   }

   public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
      if(this.getDamage(aStack) >= 0 && this.getDamage(aStack) < 512 && !this.mToolTipList[this.getDamage(aStack)].equals("")) {
         aList.add(this.mToolTipList[this.getDamage(aStack)]);
      }

   }

   public String func_77667_c(ItemStack aStack) {
      return this.func_77658_a() + "." + this.getDamage(aStack);
   }

   @SideOnly(Side.CLIENT)
   public void func_77633_a(int var1, CreativeTabs var2, List var3) {
      for(int i = 0; i < 512; ++i) {
         if(this.mStackList[i] != null) {
            var3.add(this.getUnunifiedStack(i, 1));
         }
      }

   }

   public boolean hasEffect(ItemStack aStack, int aPass) {
      return this.getDamage(aStack) >= 0 && this.getDamage(aStack) < 512 && this.mGlowList[this.getDamage(aStack)] || super.hasEffect(aStack, aPass);
   }

   public static ItemStack[] getStackList() {
      return null;
   }

   public ItemStack getUnunifiedStack(int aMeta, int aCount) {
      return aMeta >= 0 && aMeta < 512 && this.mStackList[aMeta] != null?GT_Utility.copyAmount((long)aCount, new Object[]{this.mStackList[aMeta]}):null;
   }
}
