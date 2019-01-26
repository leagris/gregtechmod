package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import ic2.api.crops.ICropTile;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_Spray_Bug_Item extends GT_Tool_Item {

   public GT_Spray_Bug_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "A very \'buggy\' Spray", aMaxDamage, aEntityDamage, true);
      this.addToEffectiveList(EntityCaveSpider.class.getName());
      this.addToEffectiveList(EntitySpider.class.getName());
      this.addToEffectiveList("EntityTFHedgeSpider");
      this.addToEffectiveList("EntityTFKingSpider");
      this.addToEffectiveList("EntityTFSwarmSpider");
      this.addToEffectiveList("EntityTFTowerBroodling");
      this.addToEffectiveList("EntityTFFireBeetle");
      this.addToEffectiveList("EntityTFSlimeBeetle");
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setUsageAmounts(8, 4, 1);
   }

   public void onHitEntity(Entity aEntity) {
      if(aEntity instanceof EntityLiving) {
         ((EntityLiving)aEntity).func_70690_d(new PotionEffect(Potion.field_76436_u.func_76396_c(), 60, 1, false));
         ((EntityLiving)aEntity).func_70690_d(new PotionEffect(Potion.field_76431_k.func_76396_c(), 600, 1, false));
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
         if(aBlock == null) {
            return false;
         } else {
            TileEntity aTileEntity = aWorld.func_72796_p(aX, aY, aZ);

            try {
               if(aTileEntity instanceof ICropTile) {
                  int e = ((ICropTile)aTileEntity).getWeedExStorage();
                  if(e <= 100 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                     ((ICropTile)aTileEntity).setWeedExStorage(e + 100);
                     GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                     return true;
                  }
               }
            } catch (Throwable var14) {
               ;
            }

            return false;
         }
      }
   }
}
