package gregtechmod.api.util;

import gregtechmod.api.interfaces.IFoodStat;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class GT_FoodStat implements IFoodStat {

   private final int mFoodLevel;
   private final int[] mPotionEffects;
   private final float mSaturation;
   private final EnumAction mAction;
   private final ItemStack mEmptyContainer;
   private final boolean mAlwaysEdible;
   private final boolean mInvisibleParticles;


   public GT_FoodStat(int aFoodLevel, float aSaturation, EnumAction aAction, ItemStack aEmptyContainer, boolean aAlwaysEdible, boolean aInvisibleParticles, int ... aPotionEffects) {
      this.mFoodLevel = aFoodLevel;
      this.mSaturation = aSaturation;
      this.mAction = aAction == null?EnumAction.eat:aAction;
      this.mPotionEffects = aPotionEffects;
      this.mEmptyContainer = GT_Utility.copy(new Object[]{aEmptyContainer});
      this.mInvisibleParticles = aInvisibleParticles;
      this.mAlwaysEdible = aAlwaysEdible;
   }

   public int getFoodLevel(GT_MetaGenerated_Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
      return this.mFoodLevel;
   }

   public float getSaturation(GT_MetaGenerated_Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
      return this.mSaturation;
   }

   public void onEaten(GT_MetaGenerated_Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
      --aStack.field_77994_a;
      ItemStack tStack = GT_OreDictUnificator.get(GT_Utility.copy(new Object[]{this.mEmptyContainer}));
      if(tStack != null && !aPlayer.field_71071_by.func_70441_a(tStack)) {
         aPlayer.func_71021_b(tStack);
      }

      aPlayer.field_70170_p.func_72956_a(aPlayer, "random.burp", 0.5F, aPlayer.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
      if(!aPlayer.field_70170_p.field_72995_K) {
         for(int i = 3; i < this.mPotionEffects.length; i += 4) {
            if(aPlayer.field_70170_p.field_73012_v.nextInt(100) < this.mPotionEffects[i]) {
               aPlayer.func_70690_d(new PotionEffect(this.mPotionEffects[i - 3], this.mPotionEffects[i - 2], this.mPotionEffects[i - 1], this.mInvisibleParticles));
            }
         }
      }

   }

   public EnumAction getFoodAction(GT_MetaGenerated_Item aItem, ItemStack aStack) {
      return this.mAction;
   }

   public boolean alwaysEdible(GT_MetaGenerated_Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
      return this.mAlwaysEdible;
   }
}
