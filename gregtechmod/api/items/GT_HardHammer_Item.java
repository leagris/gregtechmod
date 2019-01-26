package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidBlock;

public class GT_HardHammer_Item extends GT_Tool_Item {

   public GT_HardHammer_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
      super(aID, aUnlocalized, aEnglish, "To give a Machine a hard whack", aMaxDamage, aEntityDamage, true);
      GregTech_API.registerHardHammer(new ItemStack(this, 1, 32767));
      GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolHardHammer, new ItemStack(this, 1, 32767));
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
      this.setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(1)));
      this.setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(2)));
      this.setToolClasses(new String[]{"hammer"});
      this.setUsageAmounts(4, 3, 1);
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_1", "Used to craft Plates from Ingots"));
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_2", "Can rotate some Blocks as well"));
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_3", "Also used to toggle general Machine states"));
      aList.add(GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".tooltip_4", "Usable as Prospectors Hammer"));
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
            if(aBlock != Block.field_71951_J && aBlock != Block.field_111038_cB) {
               if(aBlock != Block.field_71963_Z && aBlock != Block.field_71956_V && aBlock != Block.field_71958_P && aBlock != Block.field_96469_cy) {
                  if(aBlock != Block.field_72061_ba && aBlock != Block.field_72008_bf && aBlock != Block.field_72051_aB && aBlock != Block.field_72052_aC && aBlock != Block.field_72077_au && aBlock != Block.field_94347_ck) {
                     if(aBlock == Block.field_94340_cs) {
                        if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                           aWorld.func_72921_c(aX, aY, aZ, (aMeta + 1) % 6 == 1?(aMeta + 1) % 6:2, 3);
                           GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(1)), 1.0F, -1.0F, aX, aY, aZ);
                        }

                        return true;
                     } else {
                        String tString = GT_OreDictUnificator.getAssociation(new ItemStack(aBlock, 1, aMeta));
                        if(tString != null && tString.startsWith("ore")) {
                           GT_Utility.sendChatToPlayer(aPlayer, "This is " + GT_Utility.capitalizeString(tString.replaceFirst("ore", "")) + " Ore.");
                           GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(1)), 1.0F, -1.0F, aX, aY, aZ);
                           return true;
                        } else if(!aBlock.isGenMineableReplaceable(aWorld, aX, aY, aZ, Block.field_71981_t.field_71990_ca) && !aBlock.isGenMineableReplaceable(aWorld, aX, aY, aZ, GregTech_API.sBlockList[5].field_71990_ca) && !aBlock.isGenMineableReplaceable(aWorld, aX, aY, aZ, Block.field_72012_bb.field_71990_ca) && !aBlock.isGenMineableReplaceable(aWorld, aX, aY, aZ, Block.field_72082_bJ.field_71990_ca)) {
                           return false;
                        } else {
                           if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                              GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(1)), 1.0F, -1.0F, aX, aY, aZ);
                              int tX = aX;
                              int tY = aY;
                              int tZ = aZ;
                              boolean tBlockID = false;
                              boolean tMetaID = false;
                              byte tRandom = 0;

                              int var21;
                              while(tRandom < 7) {
                                 tX -= ForgeDirection.getOrientation(aSide).offsetX;
                                 tY -= ForgeDirection.getOrientation(aSide).offsetY;
                                 tZ -= ForgeDirection.getOrientation(aSide).offsetZ;
                                 var21 = aWorld.func_72798_a(tX, tY, tZ);
                                 if(var21 != Block.field_71938_D.field_71990_ca && var21 != Block.field_71944_C.field_71990_ca) {
                                    if(var21 != Block.field_71943_B.field_71990_ca && var21 != Block.field_71942_A.field_71990_ca && (var21 < 0 || var21 >= Block.field_71973_m.length || Block.field_71973_m[var21] == null || !(Block.field_71973_m[var21] instanceof IFluidBlock))) {
                                       if(var21 != Block.field_72006_bl.field_71990_ca && GT_Utility.hasBlockHitBox(aWorld, tX, tY, tZ)) {
                                          if(var21 != aBlock.field_71990_ca) {
                                             if(tRandom < 4) {
                                                GT_Utility.sendChatToPlayer(aPlayer, "Material is changing behind this Rock.");
                                             }
                                             break;
                                          }

                                          ++tRandom;
                                          continue;
                                       }

                                       GT_Utility.sendChatToPlayer(aPlayer, "There is an Air Pocket behind this Rock.");
                                       break;
                                    }

                                    GT_Utility.sendChatToPlayer(aPlayer, "There is a Liquid behind this Rock.");
                                    break;
                                 }

                                 GT_Utility.sendChatToPlayer(aPlayer, "There is Lava behind this Rock.");
                                 break;
                              }

                              Random var23 = new Random((long)(aX ^ aY ^ aZ ^ aSide));

                              for(byte i = 0; i < 11; ++i) {
                                 tX = aX - 5 + var23.nextInt(11);
                                 tY = aY - 5 + var23.nextInt(11);
                                 tZ = aZ - 5 + var23.nextInt(11);
                                 var21 = aWorld.func_72798_a(tX, tY, tZ);
                                 int var22 = aWorld.func_72805_g(tX, tY, tZ);
                                 tString = GT_OreDictUnificator.getAssociation(new ItemStack(var21, 1, var22));
                                 if(tString != null && tString.startsWith("ore")) {
                                    GT_Utility.sendChatToPlayer(aPlayer, "Found traces of " + GT_Utility.capitalizeString(tString.replaceFirst("ore", "")) + " Ore.");
                                    GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(1)), 1.0F, -1.0F, aX, aY, aZ);
                                    return true;
                                 }
                              }

                              GT_Utility.sendChatToPlayer(aPlayer, "No Ores found.");
                           }

                           return true;
                        }
                     }
                  } else {
                     if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                        aWorld.func_72921_c(aX, aY, aZ, (aMeta - 1) % 4 + 2, 3);
                        GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(1)), 1.0F, -1.0F, aX, aY, aZ);
                     }

                     return true;
                  }
               } else {
                  if(aMeta < 6 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                     aWorld.func_72921_c(aX, aY, aZ, (aMeta + 1) % 6, 3);
                     GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(1)), 1.0F, -1.0F, aX, aY, aZ);
                  }

                  return true;
               }
            } else {
               if(GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
                  aWorld.func_72921_c(aX, aY, aZ, (aMeta + 4) % 12, 3);
                  GT_Utility.sendSoundToPlayers(aWorld, (String)GregTech_API.sSoundList.get(Integer.valueOf(1)), 1.0F, -1.0F, aX, aY, aZ);
               }

               return true;
            }
         }
      }
   }
}
