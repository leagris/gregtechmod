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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Crowbar_Item extends GT_Tool_Item {

   public GT_Crowbar_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "To remove Covers from Machines", aMaxDamage, aEntityDamage, true, -1, -1, 5, 20.0F);
      GregTech_API.registerCrowbar(new ItemStack(this, 1, 32767));
      GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolCrowbar, new ItemStack(this, 1, 32767));
      this.addToBlockList(Block.field_72056_aG);
      this.addToBlockList(Block.field_71954_T);
      this.addToBlockList(Block.field_71953_U);
      this.addToBlockList(Block.field_94337_cv);
      this.addToBlockList(GT_ModHandler.getRCItem("track.boarding", 1L));
      this.addToBlockList(GT_ModHandler.getRCItem("track.elevator", 1L));
      this.setUsageAmounts(1, 2, 1);
   }

   protected boolean isRCCrowbar() {
      return false;
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_1", "Can turn Rails"));
   }

   public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
      super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
      if(aWorld.field_72995_K) {
         return false;
      } else if(this.isRCCrowbar()) {
         return false;
      } else {
         Block aBlock = Block.field_71973_m[aWorld.func_72798_a(aX, aY, aZ)];
         if(aBlock == null) {
            return false;
         } else {
            byte aMeta = (byte)aWorld.func_72805_g(aX, aY, aZ);
            if(aBlock == Block.field_72056_aG) {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  aWorld.field_72995_K = true;
                  aWorld.func_72832_d(aX, aY, aZ, aBlock.field_71990_ca, (aMeta + 1) % 10, 0);
                  aWorld.field_72995_K = false;
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(0)), 1.0F, -1.0F, aX, aY, aZ);
               }

               return true;
            } else if(aBlock != Block.field_71954_T && aBlock != Block.field_94337_cv && aBlock != Block.field_71953_U) {
               return false;
            } else {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  aWorld.field_72995_K = true;
                  aWorld.func_72832_d(aX, aY, aZ, aBlock.field_71990_ca, aMeta / 8 * 8 + (aMeta % 8 + 1) % 6, 0);
                  aWorld.field_72995_K = false;
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(0)), 1.0F, -1.0F, aX, aY, aZ);
               }

               return true;
            }
         }
      }
   }
}
