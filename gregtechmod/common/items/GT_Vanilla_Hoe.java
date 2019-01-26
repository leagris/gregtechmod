package gregtechmod.common.items;

import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.items.GT_Vanilla_Tool;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GT_Vanilla_Hoe extends GT_Vanilla_Tool {

   public GT_Vanilla_Hoe(int aID, String aUnlocalized, String aEnglish, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, aMaterialName, 0, 0, aMaxDamage, 1.0F, aEntityDamage, new Block[0]);
      MinecraftForge.setToolClass(this, "hoe", this.mHarvestLevel);
   }

   public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
      return Item.field_77678_N.func_77648_a(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
   }

   public boolean func_82789_a(ItemStack aStack1, ItemStack aStack2) {
      return GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "ingot" + this.mMaterialName) || GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "gem" + this.mMaterialName);
   }
}
