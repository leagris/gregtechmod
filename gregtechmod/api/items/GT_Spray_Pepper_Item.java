package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_LanguageManager;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class GT_Spray_Pepper_Item extends GT_Tool_Item {

   public GT_Spray_Pepper_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "To defend yourself against Bears", aMaxDamage, aEntityDamage, true);
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setUsageAmounts(1, 8, 1);
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_1", "especially Pedobears, Care Bears,"));
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_2", "Confession Bears and Bear Grylls"));
   }

   public void onHitEntity(Entity aEntity) {
      if(aEntity instanceof EntityLiving) {
         ((EntityLiving)aEntity).func_70690_d(new PotionEffect(Potion.field_76440_q.func_76396_c(), 1200, 2, false));
         ((EntityLiving)aEntity).func_70690_d(new PotionEffect(Potion.field_76436_u.func_76396_c(), 120, 2, false));
         ((EntityLiving)aEntity).func_70690_d(new PotionEffect(Potion.field_76437_t.func_76396_c(), 200, 2, false));
         ((EntityLiving)aEntity).func_70690_d(new PotionEffect(Potion.field_76431_k.func_76396_c(), 600, 2, false));
      }

   }

   public ItemStack getEmptyItem(ItemStack aStack) {
      return GT_Items.Spray_Empty.get(1L, new Object[0]);
   }

   public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
      super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
      if(aWorld.field_72995_K) {
         return false;
      } else {
         Block aBlock = Block.field_71973_m[aWorld.func_72798_a(aX, aY, aZ)];
         return aBlock == null?false:false;
      }
   }
}
