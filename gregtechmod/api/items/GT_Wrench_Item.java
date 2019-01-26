package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import ic2.api.tile.IWrenchable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.prefab.tile.IRotatable;

public class GT_Wrench_Item extends GT_Tool_Item {

   public GT_Wrench_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
      super(aID, aUnlocalized, aEnglish, "To dismantle and rotate Blocks of most Mods", aMaxDamage, aEntityDamage, true, -1, aDischargedGTID);
      GregTech_API.registerWrench(new ItemStack(this, 1, 32767));
      GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolWrench, new ItemStack(this, 1, 32767));
      this.addToEffectiveList(EntityIronGolem.class.getName());
      this.addToEffectiveList("EntityTFTowerGolem");
      this.addToEffectiveList("EntityGolemBase");
      this.addToEffectiveList("EntityGolemClay");
      this.addToEffectiveList("EntityGolemClayAdvanced");
      this.addToEffectiveList("EntityGolemIronGuardian");
      this.addToEffectiveList("EntityGolemStone");
      this.addToEffectiveList("EntityGolemStoneAdvanced");
      this.addToEffectiveList("EntityGolemStraw");
      this.addToEffectiveList("EntityGolemTallow");
      this.addToEffectiveList("EntityGolemTallowAdvanced");
      this.addToEffectiveList("EntityGolemWarrior");
      this.addToEffectiveList("EntityGolemWood");
      this.addToEffectiveList("EntityGolemWorker");
      this.addToEffectiveList("EntitySummonedEarthGolem");
      this.addToEffectiveList("EntityTowerGuardian");
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(100)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(100)));
      this.setUsageAmounts(8, 3, 1);
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_1", "Rotation of target depends on where exactly you click"));
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
            byte aTargetSide = GT_Utility.determineWrenchingSide((byte)aSide, hitX, hitY, hitZ);
            TileEntity aTileEntity = aWorld.func_72796_p(aX, aY, aZ);

            try {
               if(aTileEntity != null && aTileEntity instanceof IWrenchable) {
                  if(((IWrenchable)aTileEntity).wrenchCanSetFacing(aPlayer, aTargetSide)) {
                     if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                        ((IWrenchable)aTileEntity).setFacing((short)aTargetSide);
                        GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                     }

                     return true;
                  }

                  if(((IWrenchable)aTileEntity).wrenchCanRemove(aPlayer)) {
                     int e = ((IWrenchable)aTileEntity).getWrenchDropRate() < 1.0F?10:3;
                     if(GT_ModHandler.damageOrDechargeItem(aStack, e, e * 1000, aPlayer)) {
                        ItemStack tOutput = ((IWrenchable)aTileEntity).getWrenchDrop(aPlayer);
                        Iterator i$ = aBlock.getBlockDropped(aWorld, aX, aY, aZ, aMeta, 0).iterator();

                        while(i$.hasNext()) {
                           ItemStack tStack = (ItemStack)i$.next();
                           if(tOutput == null) {
                              aWorld.func_72838_d(new EntityItem(aWorld, (double)aX + 0.5D, (double)aY + 0.5D, (double)aZ + 0.5D, tStack));
                           } else {
                              aWorld.func_72838_d(new EntityItem(aWorld, (double)aX + 0.5D, (double)aY + 0.5D, (double)aZ + 0.5D, tOutput));
                              tOutput = null;
                           }
                        }

                        aWorld.func_94571_i(aX, aY, aZ);
                        GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                     }

                     return true;
                  }

                  return true;
               }
            } catch (Throwable var20) {
               ;
            }

            try {
               if(aTileEntity instanceof IRotatable) {
                  if(((IRotatable)aTileEntity).getDirection().ordinal() != aTargetSide) {
                     if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                        ((IRotatable)aTileEntity).setDirection(ForgeDirection.getOrientation(aTargetSide));
                        GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                     }

                     return true;
                  }

                  return true;
               }
            } catch (Throwable var19) {
               ;
            }

            if(aBlock != Block.field_71951_J && aBlock != Block.field_111038_cB) {
               if(aBlock != Block.field_72010_bh && aBlock != Block.field_72011_bi) {
                  if(aBlock != Block.field_94346_cn && aBlock != Block.field_94343_co) {
                     if(aBlock != Block.field_72060_ay && aBlock != Block.field_72093_an) {
                        if(aMeta == aTargetSide) {
                           if(aBlock == Block.field_71963_Z || aBlock == Block.field_71956_V || aBlock == Block.field_71958_P || aBlock == Block.field_96469_cy || aBlock == Block.field_72051_aB || aBlock == Block.field_72052_aC || aBlock == Block.field_72077_au || aBlock == Block.field_94347_ck || aBlock == Block.field_94340_cs) {
                              if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                                 aWorld.func_72838_d(new EntityItem(aWorld, (double)aX + 0.5D, (double)aY + 0.5D, (double)aZ + 0.5D, new ItemStack(aBlock, 1, 0)));
                                 aWorld.func_94571_i(aX, aY, aZ);
                                 GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                              }

                              return true;
                           }
                        } else {
                           if(aBlock == Block.field_71963_Z || aBlock == Block.field_71956_V || aBlock == Block.field_71958_P || aBlock == Block.field_96469_cy) {
                              if(aMeta < 6 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                                 aWorld.func_72921_c(aX, aY, aZ, aTargetSide, 3);
                                 GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                              }

                              return true;
                           }

                           if(aBlock == Block.field_72061_ba || aBlock == Block.field_72008_bf || aBlock == Block.field_72051_aB || aBlock == Block.field_72052_aC || aBlock == Block.field_72077_au || aBlock == Block.field_94347_ck) {
                              if(aTargetSide > 1 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                                 aWorld.func_72921_c(aX, aY, aZ, aTargetSide, 3);
                                 GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                              }

                              return true;
                           }

                           if(aBlock == Block.field_94340_cs) {
                              if(aTargetSide != 1 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                                 aWorld.func_72921_c(aX, aY, aZ, aTargetSide, 3);
                                 GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                              }

                              return true;
                           }
                        }

                        if(Arrays.asList(aBlock.getValidRotations(aWorld, aX, aY, aZ)).contains(ForgeDirection.getOrientation(aTargetSide)) && (!GT_ModHandler.isElectricItem(aStack) || GT_ModHandler.canUseElectricItem(aStack, 1000)) && aBlock.rotateBlock(aWorld, aX, aY, aZ, ForgeDirection.getOrientation(aTargetSide))) {
                           GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer);
                           GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                        }

                        return false;
                     } else {
                        if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                           aWorld.func_72838_d(new EntityItem(aWorld, (double)aX + 0.5D, (double)aY + 0.5D, (double)aZ + 0.5D, new ItemStack(aBlock, 1, aMeta)));
                           aWorld.func_94571_i(aX, aY, aZ);
                           GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                        }

                        return true;
                     }
                  } else {
                     if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                        aWorld.func_72921_c(aX, aY, aZ, aMeta / 4 * 4 + (aMeta % 4 + 1) % 4, 3);
                        GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                     }

                     return true;
                  }
               } else {
                  if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                     aWorld.func_72921_c(aX, aY, aZ, aMeta / 4 * 4 + (aMeta % 4 + 1) % 4, 3);
                     GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
                  }

                  return true;
               }
            } else {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  aWorld.func_72921_c(aX, aY, aZ, (aMeta + 4) % 12, 3);
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, aX, aY, aZ);
               }

               return true;
            }
         }
      }
   }
}
