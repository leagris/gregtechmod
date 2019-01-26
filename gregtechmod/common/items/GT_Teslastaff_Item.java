package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_ModHandler;
import ic2.api.item.IElectricItem;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;

public class GT_Teslastaff_Item extends ItemTool implements IElectricItem {

   public int mCharge;
   public int mTransfer;
   public int mTier;


   public GT_Teslastaff_Item(int aID, String aUnlocalized, String aEnglish) {
      super(aID, 0.0F, EnumToolMaterial.GOLD, new Block[0]);
      this.func_77637_a(GregTech_API.TAB_GREGTECH);
      this.func_77625_d(1);
      this.func_77656_e(100);
      this.setNoRepair();
      this.func_77655_b(aUnlocalized);
      GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".name", aEnglish);
      this.mCharge = 100000000;
      this.mTransfer = 8192;
      this.mTier = 4;
   }

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IconRegister par1IconRegister) {
      this.field_77791_bV = par1IconRegister.func_94245_a("gregtech_addon:" + this.func_77658_a());
   }

   public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
      aList.add("No warranty!");
   }

   public boolean func_77644_a(ItemStack aStack, EntityLivingBase aTarget, EntityLivingBase aPlayer) {
      if(aTarget instanceof EntityPlayer && aPlayer instanceof EntityPlayer && GT_ModHandler.canUseElectricItem(aStack, 9000000)) {
         EntityPlayer tTarget = (EntityPlayer)aTarget;
         EntityPlayer tPlayer = (EntityPlayer)aPlayer;
         GT_ModHandler.useElectricItem(aStack, 9000000, tPlayer);

         for(int i = 0; i < 4; ++i) {
            if(tTarget.field_71071_by.field_70460_b[i] != null && tTarget.field_71071_by.field_70460_b[i].func_77973_b() instanceof IElectricItem) {
               tTarget.field_71071_by.field_70460_b[i] = null;
            }
         }

         aPlayer.func_70097_a(DamageSource.field_76376_m, 19.0F);
         aTarget.func_70097_a(DamageSource.field_76376_m, 19.0F);
      }

      return true;
   }

   @SideOnly(Side.CLIENT)
   public void func_77633_a(int var1, CreativeTabs var2, List var3) {
      ItemStack tCharged = new ItemStack(this, 1);
      ItemStack tUncharged = new ItemStack(this, 1, this.func_77612_l());
      GT_ModHandler.chargeElectricItem(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
      var3.add(tCharged);
      var3.add(tUncharged);
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

   public boolean func_77662_d() {
      return true;
   }

   public boolean func_77651_p() {
      return true;
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
      return this.mCharge;
   }

   public int getTier(ItemStack aStack) {
      return this.mTier;
   }

   public int getTransferLimit(ItemStack aStack) {
      return this.mTransfer;
   }
}
