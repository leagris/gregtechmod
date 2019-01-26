package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Crowbar_Item;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import java.util.List;
import mods.railcraft.api.core.items.IToolCrowbar;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_CrowbarRC_Item extends GT_Crowbar_Item implements IToolCrowbar {

   public GT_CrowbarRC_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, aMaxDamage, aEntityDamage);
   }

   protected boolean isRCCrowbar() {
      return true;
   }

   public boolean canWhack(EntityPlayer aPlayer, ItemStack aCrowbar, int aX, int aY, int aZ) {
      return true;
   }

   public void onWhack(EntityPlayer aPlayer, ItemStack aCrowbar, int aX, int aY, int aZ) {
      GT_Utility.sendSoundToPlayers(aPlayer.field_70170_p, (String)GregTech_API.sSoundList.get(Integer.valueOf(0)), 1.0F, -1.0F, aX, aY, aZ);
      GT_ModHandler.damageOrDechargeItem(aCrowbar, 1, 1000, aPlayer);
   }

   public boolean canLink(EntityPlayer aPlayer, ItemStack aCrowbar, EntityMinecart aCart) {
      return true;
   }

   public void onLink(EntityPlayer aPlayer, ItemStack aCrowbar, EntityMinecart aCart) {
      GT_Utility.sendSoundToPlayers(aCart.field_70170_p, (String)GregTech_API.sSoundList.get(Integer.valueOf(0)), 1.0F, -1.0F, (int)aCart.field_70165_t, (int)aCart.field_70163_u, (int)aCart.field_70161_v);
      GT_ModHandler.damageOrDechargeItem(aCrowbar, 1, 1000, aPlayer);
   }

   public boolean canBoost(EntityPlayer aPlayer, ItemStack aCrowbar, EntityMinecart aCart) {
      return true;
   }

   public void onBoost(EntityPlayer aPlayer, ItemStack aCrowbar, EntityMinecart aCart) {
      GT_Utility.sendSoundToPlayers(aCart.field_70170_p, (String)GregTech_API.sSoundList.get(Integer.valueOf(0)), 1.0F, -1.0F, (int)aCart.field_70165_t, (int)aCart.field_70163_u, (int)aCart.field_70161_v);
      GT_ModHandler.damageOrDechargeItem(aCrowbar, 5, 5000, aPlayer);
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_rc", "Works as Railcraft Crowbar too"));
   }
}
