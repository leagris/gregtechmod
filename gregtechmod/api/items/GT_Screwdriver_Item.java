package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Screwdriver_Item extends GT_Tool_Item {

   public GT_Screwdriver_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
      super(aID, aUnlocalized, aEnglish, "To screw Covers on Machines", aMaxDamage, aEntityDamage, true, -1, aDischargedGTID);
      GregTech_API.registerScrewdriver(new ItemStack(this, 1, 32767));
      GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolScrewdriver, new ItemStack(this, 1, 32767));
      this.addToEffectiveList(EntityCaveSpider.class.getName());
      this.addToEffectiveList(EntitySpider.class.getName());
      this.addToEffectiveList("EntityTFHedgeSpider");
      this.addToEffectiveList("EntityTFKingSpider");
      this.addToEffectiveList("EntityTFSwarmSpider");
      this.addToEffectiveList("EntityTFTowerBroodling");
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(100)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(100)));
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_1", "Can switch the Design of certain Blocks"));
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_2", "Can rotate Repeaters and Comparators"));
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
            byte aMeta = (byte)aWorld.func_72805_g(aX, aY, aZ);
            if(aBlock != Block.field_72010_bh && aBlock != Block.field_72011_bi) {
               if(aBlock != Block.field_94346_cn && aBlock != Block.field_94343_co) {
                  return false;
               } else {
                  if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 200, aPlayer)) {
                     aWorld.func_72921_c(aX, aY, aZ, aMeta / 4 * 4 + (aMeta % 4 + 1) % 4, 3);
                     GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                  }

                  return true;
               }
            } else {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 200, aPlayer)) {
                  aWorld.func_72921_c(aX, aY, aZ, aMeta / 4 * 4 + (aMeta % 4 + 1) % 4, 3);
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
               }

               return true;
            }
         }
      }
   }
}
