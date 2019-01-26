package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_Spray_Hardener_Item extends GT_Tool_Item {

   public GT_Spray_Hardener_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "Construction Foam Hardener", aMaxDamage, aEntityDamage, true);
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setUsageAmounts(16, 3, 1);
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
               if(GT_Utility.getClassName(aTileEntity).startsWith("TileEntityCable")) {
                  if(GT_Utility.getPublicField(aTileEntity, "foamed").getByte(aTileEntity) == 1 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                     GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                     GT_Utility.callPublicMethod(aTileEntity, "changeFoam", new Object[]{Byte.valueOf((byte)2)});
                     return true;
                  }

                  return false;
               }
            } catch (Throwable var15) {
               if(GregTech_API.DEBUG_MODE) {
                  var15.printStackTrace(GT_Log.err);
               }
            }

            ItemStack tStack1 = GT_ModHandler.getIC2Item("constructionFoam", 1L);
            ItemStack tStack2 = GT_ModHandler.getIC2Item("constructionFoamWall", 1L);
            if(tStack1 != null && tStack1.func_77969_a(new ItemStack(aBlock)) && tStack2 != null && tStack2.func_77973_b() != null && tStack2.func_77973_b() instanceof ItemBlock) {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                  aWorld.func_72832_d(aX, aY, aZ, ((ItemBlock)tStack2.func_77973_b()).func_77883_f(), 7, 3);
               }

               return true;
            } else if(aTileEntity instanceof BaseMetaPipeEntity && (((BaseMetaPipeEntity)aTileEntity).mConnections & -64) == 64) {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                  ((BaseMetaPipeEntity)aTileEntity).mConnections = (byte)(((BaseMetaPipeEntity)aTileEntity).mConnections & -65 | -128);
               }

               return true;
            } else {
               return false;
            }
         }
      }
   }
}
