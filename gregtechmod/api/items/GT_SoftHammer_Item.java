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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_SoftHammer_Item extends GT_Tool_Item {

   public GT_SoftHammer_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "To give a Machine a soft whack", aMaxDamage, aEntityDamage, true);
      GregTech_API.registerSoftHammer(new ItemStack(this, 1, 32767));
      GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolSoftHammer, new ItemStack(this, 1, 32767));
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(101)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(101)));
      this.setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(101)));
   }

   public void checkEnchantmentEffects(ItemStack aStack) {
      super.checkEnchantmentEffects(aStack);
      if(aStack != null) {
         aStack.func_77966_a(Enchantment.field_77337_m, 2);
      }

   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_1", "Can enable/disable Machines"));
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_2", "Can rotate some Blocks as well"));
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_4", "Can switch Redstone Lamps and Booster Tracks"));
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
            if(aBlock == Block.field_72080_bM) {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  aWorld.field_72995_K = true;
                  aWorld.func_72832_d(aX, aY, aZ, Block.field_72078_bL.field_71990_ca, 0, 0);
                  aWorld.field_72995_K = false;
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(101)), 1.0F, -1.0F, aX, aY, aZ);
               }

               return true;
            } else if(aBlock == Block.field_72078_bL) {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  aWorld.field_72995_K = true;
                  aWorld.func_72832_d(aX, aY, aZ, Block.field_72080_bM.field_71990_ca, 0, 0);
                  aWorld.field_72995_K = false;
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(101)), 1.0F, -1.0F, aX, aY, aZ);
               }

               return true;
            } else if(aBlock == Block.field_71954_T) {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  aWorld.field_72995_K = true;
                  aWorld.func_72832_d(aX, aY, aZ, aBlock.field_71990_ca, (aMeta + 8) % 16, 0);
                  aWorld.field_72995_K = false;
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(101)), 1.0F, -1.0F, aX, aY, aZ);
               }

               return true;
            } else if(aBlock == Block.field_94337_cv) {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  aWorld.field_72995_K = true;
                  aWorld.func_72832_d(aX, aY, aZ, aBlock.field_71990_ca, (aMeta + 8) % 16, 0);
                  aWorld.field_72995_K = false;
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(101)), 1.0F, -1.0F, aX, aY, aZ);
               }

               return true;
            } else if(aBlock != Block.field_71951_J && aBlock != Block.field_111038_cB) {
               if(aBlock != Block.field_71963_Z && aBlock != Block.field_71956_V && aBlock != Block.field_71958_P && aBlock != Block.field_96469_cy) {
                  if(aBlock != Block.field_72061_ba && aBlock != Block.field_72008_bf && aBlock != Block.field_72051_aB && aBlock != Block.field_72052_aC && aBlock != Block.field_72077_au && aBlock != Block.field_94347_ck) {
                     if(aBlock == Block.field_94340_cs) {
                        if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                           aWorld.func_72921_c(aX, aY, aZ, (aMeta + 1) % 6 == 1?(aMeta + 1) % 6:2, 3);
                           GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(101)), 1.0F, -1.0F, aX, aY, aZ);
                        }

                        return true;
                     } else {
                        return false;
                     }
                  } else {
                     if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                        aWorld.func_72921_c(aX, aY, aZ, (aMeta - 1) % 4 + 2, 3);
                        GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(101)), 1.0F, -1.0F, aX, aY, aZ);
                     }

                     return true;
                  }
               } else {
                  if(aMeta < 6 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                     aWorld.func_72921_c(aX, aY, aZ, (aMeta + 1) % 6, 3);
                     GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(101)), 1.0F, -1.0F, aX, aY, aZ);
                  }

                  return true;
               }
            } else {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  aWorld.func_72921_c(aX, aY, aZ, (aMeta + 4) % 12, 3);
               }

               return true;
            }
         }
      }
   }
}
