package gregtechmod.common.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.MinecraftForge;

public class GT_Vanilla_Sword extends ItemSword {

   protected final int mHarvestLevel;
   protected final int mEnchantability;
   protected final String mMaterialName;
   protected final float efficiencyOnProperMaterial;
   protected final float damageVsEntity;


   public GT_Vanilla_Sword(int aID, String aUnlocalized, String aEnglish, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
      super(aID, EnumToolMaterial.STONE);
      this.mHarvestLevel = aHarvestLevel;
      this.mEnchantability = aEnchantability;
      this.efficiencyOnProperMaterial = aEfficiency;
      this.damageVsEntity = aEntityDamage + 4.0F;
      this.mMaterialName = aMaterialName;
      this.func_77637_a(CreativeTabs.field_78040_i);
      this.func_77655_b(aUnlocalized);
      GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".name", aEnglish);
      this.func_111206_d(aUnlocalized);
      this.func_77656_e(aMaxDamage);
      this.func_77625_d(1);
      MinecraftForge.setToolClass(this, "sword", this.mHarvestLevel);
   }

   public float func_82803_g() {
      return this.damageVsEntity - 4.0F;
   }

   public int func_77619_b() {
      return this.mEnchantability;
   }

   public String func_77825_f() {
      return this.mMaterialName;
   }

   public boolean func_82789_a(ItemStack aStack1, ItemStack aStack2) {
      return GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "ingot" + this.mMaterialName) || GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "gem" + this.mMaterialName);
   }

   public Multimap func_111205_h() {
      HashMultimap multimap = HashMultimap.create();
      multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.damageVsEntity, 0));
      return multimap;
   }
}
