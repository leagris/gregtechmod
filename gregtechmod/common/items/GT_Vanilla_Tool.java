package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class GT_Vanilla_Tool extends ItemTool {

   protected final int mHarvestLevel;
   protected final int mEnchantability;
   protected final String mMaterialName;


   public GT_Vanilla_Tool(int aID, String aUnlocalized, String aEnglish, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage, Block[] aBlockList) {
      super(aID, 0.0F, EnumToolMaterial.STONE, aBlockList);
      this.mHarvestLevel = aHarvestLevel;
      this.mEnchantability = aEnchantability;
      this.field_77864_a = aEfficiency;
      this.field_77865_bY = aEntityDamage;
      this.mMaterialName = aMaterialName;
      this.func_77637_a(CreativeTabs.field_78040_i);
      this.func_77655_b(aUnlocalized);
      GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".name", aEnglish);
      this.func_111206_d(aUnlocalized);
      this.func_77656_e(aMaxDamage);
      this.func_77625_d(1);
   }

   @SideOnly(Side.CLIENT)
   public boolean func_77662_d() {
      return true;
   }

   public int func_77619_b() {
      return this.mEnchantability;
   }

   public String func_77861_e() {
      return this.mMaterialName;
   }

   public boolean func_82789_a(ItemStack aStack1, ItemStack aStack2) {
      return GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "ingot" + this.mMaterialName) || GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "gem" + this.mMaterialName);
   }
}
