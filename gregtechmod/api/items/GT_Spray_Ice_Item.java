package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class GT_Spray_Ice_Item extends GT_Tool_Item {

   public GT_Spray_Ice_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "Very effective against Slimes", aMaxDamage, aEntityDamage, true);
      this.addToEffectiveList(EntitySlime.class.getName());
      this.addToEffectiveList("BlueSlime");
      this.addToEffectiveList("SlimeClone");
      this.addToEffectiveList("MetalSlime");
      this.addToEffectiveList("EntityTFFireBeetle");
      this.addToEffectiveList("EntityTFMazeSlime");
      this.addToEffectiveList("EntityTFSlimeBeetle");
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setUsageAmounts(4, 16, 1);
      Iterator i$ = Arrays.asList(new String[]{OrePrefixes.bucket.get(Materials.Water), OrePrefixes.cell.get(Materials.Water), OrePrefixes.capsule.get(Materials.Water)}).iterator();

      while(i$.hasNext()) {
         String tName = (String)i$.next();
         GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.field_72036_aT, 1, 0), false, new Object[]{new ItemStack(this, 1, 32767), tName});
      }

   }

   public void onHitEntity(Entity aEntity) {
      if(aEntity instanceof EntityLiving) {
         ((EntityLiving)aEntity).func_70690_d(new PotionEffect(Potion.field_76437_t.func_76396_c(), 400, 2, false));
         ((EntityLiving)aEntity).func_70690_d(new PotionEffect(Potion.field_76421_d.func_76396_c(), 400, 2, false));
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
         aX += ForgeDirection.getOrientation(aSide).offsetX;
         aY += ForgeDirection.getOrientation(aSide).offsetY;
         aZ += ForgeDirection.getOrientation(aSide).offsetZ;
         Block aBlock = Block.field_71973_m[aWorld.func_72798_a(aX, aY, aZ)];
         if(aBlock == null) {
            return false;
         } else {
            byte aMeta = (byte)aWorld.func_72805_g(aX, aY, aZ);
            if(aBlock != Block.field_71943_B && aBlock != Block.field_71942_A) {
               if(aBlock != Block.field_71938_D && aBlock != Block.field_71944_C) {
                  return false;
               } else if(aMeta == 0 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                  aWorld.func_72832_d(aX, aY, aZ, Block.field_72089_ap.field_71990_ca, 0, 3);
                  return true;
               } else {
                  return false;
               }
            } else if(aMeta == 0 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
               GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
               aWorld.func_72832_d(aX, aY, aZ, Block.field_72036_aT.field_71990_ca, 0, 3);
               return true;
            } else {
               return false;
            }
         }
      }
   }
}
