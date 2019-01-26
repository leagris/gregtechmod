package gregtechmod.common;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_PlayedSound;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.World;

public class GT_TickHandler implements ITickHandler {

   public static boolean isFirstTick = true;
   private long mAnimationTick = 0L;
   private boolean mAnimationDirection = false;


   public GT_TickHandler(boolean aServer) {
      TickRegistry.registerTickHandler(this, aServer?Side.SERVER:Side.CLIENT);
   }

   public void tickStart(EnumSet<TickType> aType, Object ... aData) {}

   public synchronized void tickEnd(EnumSet<TickType> aType, Object ... aData) {
      if(!GT_Mod.mDoNotInit) {
         if(aType.contains(TickType.SERVER)) {
            ++GregTech_API.sServerTickCounter;
         }

         if(aType.contains(TickType.CLIENT)) {
            ++GregTech_API.sClientTickCounter;
            ++this.mAnimationTick;
            if(this.mAnimationTick % 50L == 0L) {
               this.mAnimationDirection = !this.mAnimationDirection;
            }

            if(this.mAnimationDirection) {
               ++Materials.Plutonium241.mRGBa[0];
               ++Materials.Plutonium241.mRGBa[1];
               ++Materials.Plutonium241.mRGBa[2];
               ++Materials.InfusedGold.mRGBa[0];
               ++Materials.InfusedGold.mRGBa[1];
               ++Materials.InfusedGold.mRGBa[2];
               ++Materials.Uranium235.mRGBa[0];
               ++Materials.Uranium235.mRGBa[1];
               ++Materials.Uranium235.mRGBa[2];
               ++Materials.NetherStar.mRGBa[0];
               ++Materials.NetherStar.mRGBa[1];
               ++Materials.NetherStar.mRGBa[2];
               ++Materials.Glowstone.mRGBa[0];
               ++Materials.Glowstone.mRGBa[1];
               ++Materials.Sunnarium.mRGBa[0];
               ++Materials.Sunnarium.mRGBa[1];
               ++Materials.Pyrotheum.mRGBa[0];
               ++Materials.Pyrotheum.mRGBa[1];
               ++Materials.Enderium.mRGBa[0];
               ++Materials.Enderium.mRGBa[1];
               ++Materials.Enderium.mRGBa[2];
               ++Materials.Thaumium.mRGBa[0];
               ++Materials.Thaumium.mRGBa[2];
               ++Materials.Vinteum.mRGBa[0];
               ++Materials.Vinteum.mRGBa[1];
               ++Materials.Vinteum.mRGBa[2];
               ++Materials.Force.mRGBa[0];
               ++Materials.Force.mRGBa[1];
            } else {
               --Materials.Plutonium241.mRGBa[0];
               --Materials.Plutonium241.mRGBa[1];
               --Materials.Plutonium241.mRGBa[2];
               --Materials.InfusedGold.mRGBa[0];
               --Materials.InfusedGold.mRGBa[1];
               --Materials.InfusedGold.mRGBa[2];
               --Materials.Uranium235.mRGBa[0];
               --Materials.Uranium235.mRGBa[1];
               --Materials.Uranium235.mRGBa[2];
               --Materials.NetherStar.mRGBa[0];
               --Materials.NetherStar.mRGBa[1];
               --Materials.NetherStar.mRGBa[2];
               --Materials.Glowstone.mRGBa[0];
               --Materials.Glowstone.mRGBa[1];
               --Materials.Sunnarium.mRGBa[0];
               --Materials.Sunnarium.mRGBa[1];
               --Materials.Pyrotheum.mRGBa[0];
               --Materials.Pyrotheum.mRGBa[1];
               --Materials.Enderium.mRGBa[0];
               --Materials.Enderium.mRGBa[1];
               --Materials.Enderium.mRGBa[2];
               --Materials.Thaumium.mRGBa[0];
               --Materials.Thaumium.mRGBa[2];
               --Materials.Vinteum.mRGBa[0];
               --Materials.Vinteum.mRGBa[1];
               --Materials.Vinteum.mRGBa[2];
               --Materials.Force.mRGBa[0];
               --Materials.Force.mRGBa[1];
            }
         }

         int i;
         if(aType.contains(TickType.WORLD)) {
            ++GregTech_API.sWorldTickCounter;
            World tPlayer = (World)aData[0];
            if(tPlayer != null) {
               if(GT_Mod.mUniverse == null) {
                  GT_Mod.mUniverse = tPlayer;
               }

               if(isFirstTick) {
                  IMetaTileEntity[] tCount = GregTech_API.mMetaTileList;
                  i = tCount.length;

                  for(int tKey = 0; tKey < i; ++tKey) {
                     IMetaTileEntity tMetaTileEntity = tCount[tKey];

                     try {
                        if(tMetaTileEntity != null) {
                           tMetaTileEntity.onFirstServerTick();
                        }
                     } catch (Throwable var9) {
                        var9.printStackTrace(GT_Log.err);
                     }
                  }

                  isFirstTick = false;
               }

               if(GregTech_API.sServerTickCounter % 20L == 0L) {
                  Iterator var11 = tPlayer.field_72996_f.iterator();

                  while(var11.hasNext()) {
                     Object var15 = var11.next();
                     if(var15 instanceof EntityItem && ((EntityItem)var15).lifespan == 6000) {
                        ((EntityItem)var15).lifespan = GT_Mod.sItemDespawnTime;
                     }
                  }
               }
            }
         }

         if(aType.contains(TickType.PLAYER)) {
            EntityPlayer var10 = (EntityPlayer)aData[0];
            if(var10 != null && !var10.field_70128_L) {
               if(var10.field_70170_p.field_72995_K) {
                  ArrayList var12 = new ArrayList();
                  Iterator var16 = GT_Utility.sPlayedSoundMap.entrySet().iterator();

                  while(var16.hasNext()) {
                     Entry var17 = (Entry)var16.next();
                     if(((Integer)var17.getValue()).intValue() < 0) {
                        var12.add(var17.getKey());
                     } else {
                        var17.setValue(Integer.valueOf(((Integer)var17.getValue()).intValue() - 1));
                     }
                  }

                  var16 = var12.iterator();

                  while(var16.hasNext()) {
                     GT_PlayedSound var18 = (GT_PlayedSound)var16.next();
                     GT_Utility.sPlayedSoundMap.remove(var18);
                  }

                  if(var10.field_71092_bJ.equalsIgnoreCase("immibis")) {
                     for(i = 1; i <= 42; ++i) {
                        if(GregTech_API.sSpecialFile.get("PumpkinOfShame", "Config.Random.Number." + (new Random((long)i)).nextInt(4200), true)) {
                           var10.field_71071_by.field_70460_b[3] = new ItemStack(Block.field_72061_ba, 1, 0);
                           var10.field_71071_by.field_70460_b[3].func_82834_c("Pumpkin of Shame");
                           break;
                        }
                     }
                  }
               } else {
                  if(GregTech_API.sServerTickCounter % 200L == 0L && (var10.field_71075_bZ.field_75099_e || var10.func_82246_f(0, -10000, 0)) && !var10.field_71075_bZ.field_75098_d && GT_Mod.sSurvivalIntoAdventure) {
                     var10.func_71033_a(EnumGameType.ADVENTURE);
                     boolean var13 = var10.field_71075_bZ.field_75099_e || var10.func_82246_f(0, -10000, 0);
                     var10.field_71075_bZ.field_75099_e = false;
                     if(var13) {
                        GT_Utility.sendChatToPlayer(var10, "Adventure Mode has been broken.");
                     }

                     if(GT_Mod.sAxeWhenAdventure) {
                        if(!var13) {
                           GT_Utility.sendChatToPlayer(var10, "It\'s dangerous to go alone! Take this.");
                        }

                        var10.field_70170_p.func_72838_d(new EntityItem(var10.field_70170_p, var10.field_70165_t, var10.field_70163_u, var10.field_70161_v, GT_Items.Tool_Axe_Flint.get(1L, new Object[0])));
                     }
                  }

                  if(GregTech_API.sServerTickCounter % 120L == 0L) {
                     int var14 = 64;

                     for(i = 0; i < 36; ++i) {
                        if(var10.field_71071_by.func_70301_a(i) != null) {
                           var14 += var10.field_71071_by.func_70301_a(i).field_77994_a * 64 / Math.max(1, var10.field_71071_by.func_70301_a(i).func_77976_d());
                           if(GT_Mod.sInventoryUnification) {
                              GT_OreDictUnificator.setStack(true, var10.field_71071_by.func_70301_a(i));
                           }
                        }
                     }

                     for(i = 0; i < 4; ++i) {
                        if(var10.field_71071_by.field_70460_b[i] != null) {
                           var14 += 256;
                        }
                     }

                     if(GT_Mod.sHungerEffect && GregTech_API.sServerTickCounter % 2400L == 1200L) {
                        var10.func_71020_j(Math.max(1.0F, (float)var14 / 666.6F));
                     }
                  }
               }
            }
         }

      }
   }

   public EnumSet<TickType> ticks() {
      return EnumSet.of(TickType.RENDER, TickType.WORLD, TickType.PLAYER, TickType.SERVER, TickType.CLIENT);
   }

   public String getLabel() {
      return "GT_TickHandler";
   }

}
