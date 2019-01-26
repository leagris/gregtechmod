package gregtechmod.common.blocks;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GT_BlockMetaID_Ore extends Block {

   public Icon[] mIconsRed = new Icon[96];
   public Icon[] mIconsBlack = new Icon[96];
   public Icon[] mIconsStone = new Icon[96];
   public Icon[] mIconsNether = new Icon[96];
   public Icon[] mIconsEnd = new Icon[96];
   public Icon[] mIcons = new Icon[96];
   public static final int Metablockcount = 16;


   public GT_BlockMetaID_Ore(int aID) {
      super(aID, Material.field_76246_e);
      this.func_71848_c(3.0F);
      this.func_71894_b(10.0F);
      this.func_71864_b("BlockMetaID_Ore");
      LanguageRegistry.addName(this, this.func_71917_a());
      String[] tRegionalNameList = new String[]{"GT_Ore", "Galena Ore", "Iridium Ore", "Ruby Ore", "Sapphire Ore", "Bauxite Ore", "Pyrite Ore", "Cinnabar Ore", "Sphalerite Ore", "Tungstate Ore", "Sheldonite Ore", "Olivine Ore", "Sodalite Ore", "Tetrahedrite Ore", "Cassiterite Ore", "Nickel Ore"};

      for(int i = 0; i < 16; ++i) {
         GT_LanguageManager.addStringLocalization(this.func_71917_a() + "." + i + ".name", tRegionalNameList[i]);
      }

      this.func_71884_a(Block.field_71976_h);
      this.func_71849_a(GregTech_API.TAB_GREGTECH);
      MinecraftForge.setBlockHarvestLevel(this, 0, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(this, 1, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(this, 2, "pickaxe", 3);
      MinecraftForge.setBlockHarvestLevel(this, 3, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(this, 4, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(this, 5, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(this, 6, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(this, 7, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(this, 8, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(this, 9, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(this, 10, "pickaxe", 3);
      MinecraftForge.setBlockHarvestLevel(this, 11, "pickaxe", 3);
      MinecraftForge.setBlockHarvestLevel(this, 12, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(this, 13, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(this, 14, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(this, 15, "pickaxe", 2);
   }

   @SideOnly(Side.CLIENT)
   public void func_94332_a(IconRegister par1IconRegister) {
      int i;
      for(i = 0; i < 96; ++i) {
         this.mIcons[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":"tile.Ore_Default/" + i));
      }

      for(i = 0; i < 96; ++i) {
         this.mIconsStone[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":"tile.Ore_Stone/" + i));
      }

      for(i = 0; i < 96; ++i) {
         this.mIconsNether[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":"tile.Ore_Nether/" + i));
      }

      for(i = 0; i < 96; ++i) {
         this.mIconsEnd[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":"tile.Ore_End/" + i));
      }

      for(i = 0; i < 96; ++i) {
         this.mIconsBlack[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":"tile.Ore_Black/" + i));
      }

      for(i = 0; i < 96; ++i) {
         this.mIconsRed[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":"tile.Ore_Red/" + i));
      }

   }

   public void func_71914_a(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
      super.func_71914_a(par1World, par2, par3, par4, par5, par6, par7);
      int var8 = 0;
      switch(par5) {
      case 2:
         var8 = 30 + par1World.field_73012_v.nextInt(21);
         break;
      case 3:
         var8 = 3 + par1World.field_73012_v.nextInt(5);
         break;
      case 4:
         var8 = 3 + par1World.field_73012_v.nextInt(5);
      case 5:
      default:
         break;
      case 6:
         var8 = 1 + par1World.field_73012_v.nextInt(1);
         break;
      case 7:
         var8 = 3 + par1World.field_73012_v.nextInt(3);
         break;
      case 8:
         var8 = 1 + par1World.field_73012_v.nextInt(1);
      }

      if(var8 > 0) {
         this.func_71923_g(par1World, par2, par3, par4, var8);
      }

   }

   public ArrayList<ItemStack> getBlockDropped(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
      ArrayList rList = new ArrayList();
      switch(aMeta) {
      case 2:
         rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, (Object)Materials.Iridium, (long)(1 + aWorld.field_73012_v.nextInt(1 + aFortune / 2))));
         break;
      case 3:
         rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, (Object)Materials.Ruby, (long)(1 + aWorld.field_73012_v.nextInt(1 + aFortune))));
         if(aWorld.field_73012_v.nextInt(Math.max(1, 16 / (aFortune + 1))) == 0) {
            rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, (Object)Materials.GarnetRed, 1L));
         }
         break;
      case 4:
         rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, (Object)Materials.Sapphire, (long)(1 + aWorld.field_73012_v.nextInt(1 + aFortune))));
         if(aWorld.field_73012_v.nextInt(Math.max(1, 16 / (aFortune + 1))) == 0) {
            rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, (Object)Materials.GreenSapphire, 1L));
         }
         break;
      case 5:
      case 9:
      case 10:
      default:
         rList.add(new ItemStack(this.field_71990_ca, 1, aMeta));
         break;
      case 6:
         rList.add(GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)Materials.Pyrite, (long)(1 + aWorld.field_73012_v.nextInt(1 + aFortune / 2))));
         break;
      case 7:
         rList.add(GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)Materials.Cinnabar, (long)(1 + aWorld.field_73012_v.nextInt(1 + aFortune / 2))));
         if(aWorld.field_73012_v.nextInt(Math.max(1, 4 / (aFortune + 1))) == 0) {
            rList.add(GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Redstone, 1L));
         }

         if(aWorld.field_73012_v.nextInt(Math.max(1, 16 / (aFortune + 1))) == 0) {
            rList.add(GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Sulfur, 1L));
         }
         break;
      case 8:
         rList.add(GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)Materials.Sphalerite, (long)(1 + aWorld.field_73012_v.nextInt(1 + aFortune / 2))));
         if(aWorld.field_73012_v.nextInt(Math.max(1, 4 / (aFortune + 1))) == 0) {
            rList.add(GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)Materials.Zinc, 1L));
         }

         if(aWorld.field_73012_v.nextInt(Math.max(1, 16 / (aFortune + 1))) == 0) {
            rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, (Object)Materials.GarnetYellow, 1L));
         }
         break;
      case 11:
         rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, (Object)Materials.Olivine, (long)(1 + aWorld.field_73012_v.nextInt(1 + aFortune))));
         break;
      case 12:
         rList.add(GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)Materials.Sodalite, (long)(6 + 3 * aWorld.field_73012_v.nextInt(1 + aFortune))));
         if(aWorld.field_73012_v.nextInt(Math.max(1, 4 / (aFortune + 1))) == 0) {
            rList.add(GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)Materials.Aluminium, 1L));
         }
      }

      return rList;
   }

   public boolean canDragonDestroy(World world, int x, int y, int z) {
      return false;
   }

   public float func_71934_m(World world, int x, int y, int z) {
      if(world == null) {
         return 0.0F;
      } else {
         Integer tMeta = Integer.valueOf(world.func_72805_g(x, y, z));
         if(tMeta == null) {
            tMeta = Integer.valueOf(0);
         }

         switch(world.func_72805_g(x, y, z)) {
         case 1:
            return 3.0F;
         case 2:
            return 20.0F;
         case 3:
            return 4.0F;
         case 4:
            return 4.0F;
         case 5:
            return 3.0F;
         case 6:
            return 2.0F;
         case 7:
            return 3.0F;
         case 8:
            return 2.0F;
         case 9:
            return 4.0F;
         case 10:
            return 3.5F;
         default:
            return super.func_71934_m(world, x, y, z);
         }
      }
   }

   public static byte getStyle(IBlockAccess aWorld, int aX, int aY, int aZ) {
      int tStone = Block.field_71981_t.field_71990_ca;
      int tNether = Block.field_72012_bb.field_71990_ca;
      int tEnd = Block.field_72082_bJ.field_71990_ca;
      int tBlockID = aWorld.func_72798_a(aX + 1, aY, aZ);
      if(tBlockID == tStone) {
         return (byte)0;
      } else if(tBlockID == tNether) {
         return (byte)-1;
      } else if(tBlockID == tEnd) {
         return (byte)1;
      } else {
         int tMeta;
         if(tBlockID != 0) {
            tMeta = aWorld.func_72805_g(aX + 1, aY, aZ);
            if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteBlack")) {
               return (byte)2;
            }

            if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneBasalt")) {
               return (byte)2;
            }

            if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneAbyssal")) {
               return (byte)2;
            }

            if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneRedRock")) {
               return (byte)3;
            }

            if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteRed")) {
               return (byte)3;
            }

            if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGranite")) {
               return (byte)2;
            }
         }

         tBlockID = aWorld.func_72798_a(aX - 1, aY, aZ);
         if(tBlockID == tStone) {
            return (byte)0;
         } else if(tBlockID == tNether) {
            return (byte)-1;
         } else if(tBlockID == tEnd) {
            return (byte)1;
         } else {
            if(tBlockID != 0) {
               tMeta = aWorld.func_72805_g(aX - 1, aY, aZ);
               if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteBlack")) {
                  return (byte)2;
               }

               if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneBasalt")) {
                  return (byte)2;
               }

               if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneAbyssal")) {
                  return (byte)2;
               }

               if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneRedRock")) {
                  return (byte)3;
               }

               if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteRed")) {
                  return (byte)3;
               }

               if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGranite")) {
                  return (byte)2;
               }
            }

            tBlockID = aWorld.func_72798_a(aX, aY + 1, aZ);
            if(tBlockID == tStone) {
               return (byte)0;
            } else if(tBlockID == tNether) {
               return (byte)-1;
            } else if(tBlockID == tEnd) {
               return (byte)1;
            } else {
               if(tBlockID != 0) {
                  tMeta = aWorld.func_72805_g(aX, aY + 1, aZ);
                  if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteBlack")) {
                     return (byte)2;
                  }

                  if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneBasalt")) {
                     return (byte)2;
                  }

                  if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneAbyssal")) {
                     return (byte)2;
                  }

                  if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneRedRock")) {
                     return (byte)3;
                  }

                  if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteRed")) {
                     return (byte)3;
                  }

                  if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGranite")) {
                     return (byte)2;
                  }
               }

               tBlockID = aWorld.func_72798_a(aX, aY - 1, aZ);
               if(tBlockID == tStone) {
                  return (byte)0;
               } else if(tBlockID == tNether) {
                  return (byte)-1;
               } else if(tBlockID == tEnd) {
                  return (byte)1;
               } else {
                  if(tBlockID != 0) {
                     tMeta = aWorld.func_72805_g(aX, aY - 1, aZ);
                     if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteBlack")) {
                        return (byte)2;
                     }

                     if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneBasalt")) {
                        return (byte)2;
                     }

                     if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneAbyssal")) {
                        return (byte)2;
                     }

                     if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneRedRock")) {
                        return (byte)3;
                     }

                     if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteRed")) {
                        return (byte)3;
                     }

                     if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGranite")) {
                        return (byte)2;
                     }
                  }

                  tBlockID = aWorld.func_72798_a(aX, aY, aZ + 1);
                  if(tBlockID == tStone) {
                     return (byte)0;
                  } else if(tBlockID == tNether) {
                     return (byte)-1;
                  } else if(tBlockID == tEnd) {
                     return (byte)1;
                  } else {
                     if(tBlockID != 0) {
                        tMeta = aWorld.func_72805_g(aX, aY, aZ + 1);
                        if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteBlack")) {
                           return (byte)2;
                        }

                        if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneBasalt")) {
                           return (byte)2;
                        }

                        if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneAbyssal")) {
                           return (byte)2;
                        }

                        if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneRedRock")) {
                           return (byte)3;
                        }

                        if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteRed")) {
                           return (byte)3;
                        }

                        if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGranite")) {
                           return (byte)2;
                        }
                     }

                     tBlockID = aWorld.func_72798_a(aX, aY, aZ - 1);
                     if(tBlockID == tStone) {
                        return (byte)0;
                     } else if(tBlockID == tNether) {
                        return (byte)-1;
                     } else if(tBlockID == tEnd) {
                        return (byte)1;
                     } else {
                        if(tBlockID != 0) {
                           tMeta = aWorld.func_72805_g(aX, aY, aZ - 1);
                           if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteBlack")) {
                              return (byte)2;
                           }

                           if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneBasalt")) {
                              return (byte)2;
                           }

                           if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneAbyssal")) {
                              return (byte)2;
                           }

                           if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneRedRock")) {
                              return (byte)3;
                           }

                           if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGraniteRed")) {
                              return (byte)3;
                           }

                           if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlockID, 1, tMeta), "stoneGranite")) {
                              return (byte)2;
                           }
                        }

                        try {
                           if(aWorld instanceof World) {
                              tBlockID = aWorld.func_72798_a(aX + 1, aY, aZ);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX + 1, aY, aZ, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX + 1, aY, aZ, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX + 1, aY, aZ, tEnd)) {
                                 return (byte)1;
                              }

                              tBlockID = aWorld.func_72798_a(aX - 1, aY, aZ);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX - 1, aY, aZ, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX - 1, aY, aZ, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX - 1, aY, aZ, tEnd)) {
                                 return (byte)1;
                              }

                              tBlockID = aWorld.func_72798_a(aX, aY + 1, aZ);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY + 1, aZ, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY + 1, aZ, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY + 1, aZ, tEnd)) {
                                 return (byte)1;
                              }

                              tBlockID = aWorld.func_72798_a(aX, aY - 1, aZ);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY - 1, aZ, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY - 1, aZ, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY - 1, aZ, tEnd)) {
                                 return (byte)1;
                              }

                              tBlockID = aWorld.func_72798_a(aX, aY, aZ + 1);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY, aZ + 1, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY, aZ + 1, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY, aZ + 1, tEnd)) {
                                 return (byte)1;
                              }

                              tBlockID = aWorld.func_72798_a(aX, aY, aZ - 1);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY, aZ - 1, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY, aZ - 1, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)aWorld, aX, aY, aZ - 1, tEnd)) {
                                 return (byte)1;
                              }
                           } else {
                              tBlockID = aWorld.func_72798_a(aX + 1, aY, aZ);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX + 1, aY, aZ, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX + 1, aY, aZ, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX + 1, aY, aZ, tEnd)) {
                                 return (byte)1;
                              }

                              tBlockID = aWorld.func_72798_a(aX - 1, aY, aZ);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX - 1, aY, aZ, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX - 1, aY, aZ, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX - 1, aY, aZ, tEnd)) {
                                 return (byte)1;
                              }

                              tBlockID = aWorld.func_72798_a(aX, aY + 1, aZ);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY + 1, aZ, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY + 1, aZ, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY + 1, aZ, tEnd)) {
                                 return (byte)1;
                              }

                              tBlockID = aWorld.func_72798_a(aX, aY - 1, aZ);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY - 1, aZ, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY - 1, aZ, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY - 1, aZ, tEnd)) {
                                 return (byte)1;
                              }

                              tBlockID = aWorld.func_72798_a(aX, aY, aZ + 1);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY, aZ + 1, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY, aZ + 1, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY, aZ + 1, tEnd)) {
                                 return (byte)1;
                              }

                              tBlockID = aWorld.func_72798_a(aX, aY, aZ - 1);
                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY, aZ - 1, tStone)) {
                                 return (byte)0;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY, aZ - 1, tNether)) {
                                 return (byte)-1;
                              }

                              if(Block.field_71973_m[tBlockID] != null && Block.field_71973_m[tBlockID].isGenMineableReplaceable((World)null, aX, aY, aZ - 1, tEnd)) {
                                 return (byte)1;
                              }
                           }
                        } catch (Throwable var10) {
                           ;
                        }

                        return (byte)-128;
                     }
                  }
               }
            }
         }
      }
   }

   public Icon func_71895_b(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
      int tSide;
      int aIndex = aWorld.func_72805_g(aX, aY, aZ) * 6 + (tSide = Math.abs(aSide ^ aX ^ aY ^ aZ)) % 6;
      if(aIndex >= 0 && aIndex < 96) {
         switch(getStyle(aWorld, aX, aY, aZ)) {
         case -1:
            if(GT_Mod.sInvisibleOres && tSide % 12 <= 6) {
               return Block.field_72012_bb.func_71851_a(aSide);
            }

            return this.mIconsNether[aIndex];
         case 0:
            if(GT_Mod.sInvisibleOres && tSide % 12 <= 6) {
               return Block.field_71981_t.func_71851_a(aSide);
            }

            return this.mIconsStone[aIndex];
         case 1:
            if(GT_Mod.sInvisibleOres && tSide % 12 <= 6) {
               return Block.field_72082_bJ.func_71851_a(aSide);
            }

            return this.mIconsEnd[aIndex];
         case 2:
            if(GT_Mod.sInvisibleOres && tSide % 12 <= 6) {
               return GregTech_API.sBlockList[5].func_71858_a(aSide, 0);
            }

            return this.mIconsBlack[aIndex];
         case 3:
            if(GT_Mod.sInvisibleOres && tSide % 12 <= 6) {
               return GregTech_API.sBlockList[5].func_71858_a(aSide, 8);
            }

            return this.mIconsRed[aIndex];
         default:
            return this.mIcons[aIndex];
         }
      } else {
         return null;
      }
   }

   public Icon func_71858_a(int aSide, int aMeta) {
      return aMeta >= 0 && aMeta * 6 + aSide < 96?this.mIconsStone[aMeta * 6 + aSide]:null;
   }

   public int func_71873_h(World par1World, int par2, int par3, int par4) {
      return par1World.func_72805_g(par2, par3, par4);
   }

   @SideOnly(Side.CLIENT)
   public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List) {
      for(int i = 1; i < 16; ++i) {
         par3List.add(new ItemStack(par1, 1, i));
      }

   }
}
