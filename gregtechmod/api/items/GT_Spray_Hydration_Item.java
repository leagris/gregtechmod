package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import ic2.api.crops.ICropTile;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_Spray_Hydration_Item extends GT_Tool_Item {

   public GT_Spray_Hydration_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "To hydrate Crops and similar", aMaxDamage, aEntityDamage, true);
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setUsageAmounts(20, 3, 1);
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
                  int e = ((ICropTile)aTileEntity).getHydrationStorage();
                  if(e <= 100 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                     ((ICropTile)aTileEntity).setHydrationStorage(e + 100);
                     GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                  }

                  return true;
               }
            } catch (Throwable var14) {
               ;
            }

            if(aTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)aTileEntity).getColorization() >= 0 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
               ((IGregTechTileEntity)aTileEntity).setColorization((byte)(((IGregTechTileEntity)aTileEntity).getColorization() > 15?-2:-1));
               GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
            }

            return false;
         }
      }
   }
}
