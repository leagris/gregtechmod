package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.GT_Items;
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
import net.minecraftforge.common.ForgeDirection;

public class GT_Spray_Color_Item extends GT_Tool_Item {

   public byte mColorMeta = 0;


   public GT_Spray_Color_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, byte aColorMeta) {
      super(aID, aUnlocalized, aEnglish, "To give the World more Color", aMaxDamage, aEntityDamage, true);
      GT_OreDictUnificator.registerOre(Dyes.get(this.mColorMeta = aColorMeta), new ItemStack(this, 1, 32767));
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setUsageAmounts(32, 3, 1);
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_3", "Enough for dying " + this.func_77612_l() + " Blocks in World " + Dyes.get(this.mColorMeta).mName.toLowerCase()));
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_2", "Enough for crafting " + this.func_77612_l() / this.getDamagePerContainerItemCraft() + " times"));
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
            byte aMeta = (byte)aWorld.func_72805_g(aX, aY, aZ);
            if(aBlock != Block.field_111031_cC && aBlock != Block.field_111032_cD && aBlock != Block.field_111039_cA && aBlock != GT_Items.TE_Rockwool.getBlock()) {
               if(aBlock.recolourBlock(aWorld, aX, aY, aZ, ForgeDirection.getOrientation(aSide), ~this.mColorMeta & 15)) {
                  GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer);
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                  return true;
               } else {
                  return false;
               }
            } else if(aMeta == (~this.mColorMeta & 15) && aBlock != Block.field_111032_cD) {
               return false;
            } else {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                  if(aBlock == Block.field_111032_cD) {
                     aWorld.func_72832_d(aX, aY, aZ, Block.field_111039_cA.field_71990_ca, ~this.mColorMeta & 15, 3);
                  } else {
                     aWorld.func_72921_c(aX, aY, aZ, ~this.mColorMeta & 15, 3);
                  }
               }

               return true;
            }
         }
      }
   }
}
