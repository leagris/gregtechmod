package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Utility;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class GT_FluidDisplayItem extends GT_Generic_Item {

   public GT_FluidDisplayItem(int aID, String aUnlocalized, String aEnglish) {
      super(aID, aUnlocalized, aEnglish, (String)null);
   }

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IconRegister aIconRegister) {}

   public Icon func_77617_a(int aMeta) {
      Fluid tFluid = FluidRegistry.getFluid(aMeta);
      return tFluid != null?tFluid.getStillIcon():GregTech_API.FAIL_ICON;
   }

   public int func_94901_k() {
      return 0;
   }

   public String func_77667_c(ItemStack aStack) {
      return aStack != null?GT_Utility.getFluidName(FluidRegistry.getFluid(aStack.func_77960_j()), false):"";
   }

   public String func_77653_i(ItemStack aStack) {
      return aStack != null?GT_Utility.getFluidName(FluidRegistry.getFluid(aStack.func_77960_j()), true):"";
   }

   public String func_77628_j(ItemStack aStack) {
      return this.func_77653_i(aStack);
   }

   @SideOnly(Side.CLIENT)
   public void func_77633_a(int var1, CreativeTabs aTab, List aList) {}
}
