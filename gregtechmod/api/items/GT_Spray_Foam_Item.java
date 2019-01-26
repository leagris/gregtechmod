package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class GT_Spray_Foam_Item extends GT_Tool_Item {

   public GT_Spray_Foam_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "Precision Spray", aMaxDamage, aEntityDamage, true);
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(102)));
      this.setUsageAmounts(25, 3, 1);
   }

   public ItemStack getEmptyItem(ItemStack aStack) {
      return GT_Items.Spray_Empty.get(1L, new Object[0]);
   }

   public void switchMode(ItemStack aStack, EntityPlayer aPlayer) {
      this.setMode(aStack, (this.getMode(aStack) + 1) % 3);
      switch(this.getMode(aStack)) {
      case 0:
         GT_Utility.sendChatToPlayer(aPlayer, "Single Block Mode");
         break;
      case 1:
         GT_Utility.sendChatToPlayer(aPlayer, "4m Line Mode");
         break;
      case 2:
         GT_Utility.sendChatToPlayer(aPlayer, "3mx3m Area Mode");
      }

   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      switch(this.getMode(aStack)) {
      case 0:
         aList.add("Single Block Mode");
         break;
      case 1:
         aList.add("4m Line Mode");
         break;
      case 2:
         aList.add("3mx3m Area Mode");
      }

   }

   public ItemStack func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      if(aPlayer.func_70093_af()) {
         this.switchMode(aStack, aPlayer);
      }

      return super.func_77659_a(aStack, aWorld, aPlayer);
   }

   public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
      super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
      if(aPlayer.func_70093_af()) {
         return false;
      } else if(aWorld.field_72995_K) {
         return false;
      } else {
         Block aBlock = Block.field_71973_m[aWorld.func_72798_a(aX, aY, aZ)];
         if(aBlock == null) {
            return false;
         } else {
            TileEntity aTileEntity = aWorld.func_72796_p(aX, aY, aZ);

            try {
               if(GT_Utility.getClassName(aTileEntity).startsWith("TileEntityCable")) {
                  if(GT_Utility.getPublicField(aTileEntity, "foamed").getByte(aTileEntity) == 0 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                     GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                     GT_Utility.callPublicMethod(aTileEntity, "changeFoam", new Object[]{Byte.valueOf((byte)1)});
                     return true;
                  }

                  return false;
               }
            } catch (Throwable var22) {
               if(GregTech_API.DEBUG_MODE) {
                  var22.printStackTrace(GT_Log.err);
               }
            }

            if(aTileEntity instanceof BaseMetaPipeEntity && (((BaseMetaPipeEntity)aTileEntity).mConnections & -64) == 0) {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                  ((BaseMetaPipeEntity)aTileEntity).mConnections = (byte)(((BaseMetaPipeEntity)aTileEntity).mConnections | 64);
               }

               return true;
            } else {
               aX += ForgeDirection.getOrientation(aSide).offsetX;
               aY += ForgeDirection.getOrientation(aSide).offsetY;
               aZ += ForgeDirection.getOrientation(aSide).offsetZ;
               ItemStack tStack = GT_ModHandler.getIC2Item("constructionFoam", 1L);
               if(tStack != null && tStack.func_77973_b() instanceof ItemBlock) {
                  int tRotationPitch = Math.round(aPlayer.field_70125_A);
                  byte tSide = 0;
                  if(tRotationPitch >= 65) {
                     tSide = 1;
                  } else if(tRotationPitch <= -65) {
                     tSide = 0;
                  } else {
                     switch(MathHelper.func_76128_c((double)(aPlayer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3) {
                     case 0:
                        tSide = 2;
                        break;
                     case 1:
                        tSide = 5;
                        break;
                     case 2:
                        tSide = 3;
                        break;
                     case 3:
                        tSide = 4;
                     }
                  }

                  switch(this.getMode(aStack)) {
                  case 0:
                     if(GT_Utility.isAirBlock(aWorld, aX, aY, aZ) && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                        GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                        aWorld.func_72832_d(aX, aY, aZ, ((ItemBlock)tStack.func_77973_b()).func_77883_f(), tStack.func_77960_j(), 3);
                        return true;
                     }
                     break;
                  case 1:
                     for(byte var23 = 0; var23 < 4; ++var23) {
                        if(!GT_Utility.isAirBlock(aWorld, aX, aY, aZ) || !GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                           if(var23 == 0) {
                              return false;
                           }
                           break;
                        }

                        GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                        aWorld.func_72832_d(aX, aY, aZ, ((ItemBlock)tStack.func_77973_b()).func_77883_f(), tStack.func_77960_j(), 3);
                        aX -= ForgeDirection.getOrientation(tSide).offsetX;
                        aY -= ForgeDirection.getOrientation(tSide).offsetY;
                        aZ -= ForgeDirection.getOrientation(tSide).offsetZ;
                     }

                     return true;
                  case 2:
                     boolean temp = false;
                     boolean tXFactor = ForgeDirection.getOrientation(tSide).offsetX == 0;
                     boolean tYFactor = ForgeDirection.getOrientation(tSide).offsetY == 0;
                     boolean tZFactor = ForgeDirection.getOrientation(tSide).offsetZ == 0;
                     aX -= tXFactor?1:0;
                     aY -= tYFactor?1:0;
                     aZ -= tZFactor?1:0;
                     byte i = 0;

                     while(i < 3) {
                        byte j = 0;

                        while(true) {
                           if(j < 3) {
                              label248: {
                                 if(GT_Utility.isAirBlock(aWorld, aX + (tXFactor?i:0), aY + (!tXFactor && tYFactor?i:0) + (!tZFactor && tYFactor?j:0), aZ + (tZFactor?j:0))) {
                                    if(!GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                                       break label248;
                                    }

                                    GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(102)), 1.0F, -1.0F, aX, aY, aZ);
                                    aWorld.func_72832_d(aX + (tXFactor?i:0), aY + (!tXFactor && tYFactor?i:0) + (!tZFactor && tYFactor?j:0), aZ + (tZFactor?j:0), ((ItemBlock)tStack.func_77973_b()).func_77883_f(), tStack.func_77960_j(), 3);
                                    temp = true;
                                 }

                                 ++j;
                                 continue;
                              }
                           }

                           ++i;
                           break;
                        }
                     }

                     return temp;
                  }
               }

               return false;
            }
         }
      }
   }
}
