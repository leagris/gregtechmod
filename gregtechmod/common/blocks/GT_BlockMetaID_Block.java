package gregtechmod.common.blocks;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.tileentities.machines.multi.GT_MetaTileEntity_Multi_GasTurbine;
import gregtechmod.common.tileentities.machines.multi.GT_MetaTileEntity_Multi_SteamTurbine;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GT_BlockMetaID_Block extends Block {

   public static Icon[] mIcons = new Icon[52];
   public static Icon[] mIconGasTurbine = new Icon[9];
   public static Icon[] mIconGasTurbineActive = new Icon[9];
   public static Icon[] mIconSteamTurbine = new Icon[9];
   public static Icon[] mIconSteamTurbineActive = new Icon[9];
   public static boolean mConnectedMachineTextures = true;


   public GT_BlockMetaID_Block(int aID) {
      super(aID, Material.field_76243_f);
      this.func_71848_c(3.0F);
      this.func_71894_b(10.0F);
      this.func_71864_b("BlockMetaID_Block");
      LanguageRegistry.addName(this, this.func_71917_a());
      String[] tRegionalNameList = new String[]{"Advanced Machine Block", "Fusion Coil", "Iridium Reinforced Stone", "Block of Silver", "Block of Ruby", "Block of Sapphire", "LESU-Block", "Block of Aluminium", "Block of Titanium", "Block of Chrome", "Highly Advanced Machineblock", "Block of Steel", "Block of Brass", "Standard Machine Casing", "Reinforced Machine Casing", "Advanced Machine Casing"};

      int i;
      for(i = 0; i < 16; ++i) {
         GT_LanguageManager.addStringLocalization(this.func_71917_a() + "." + i + ".name", tRegionalNameList[i]);
      }

      this.func_71884_a(Block.field_71977_i);
      this.func_71849_a(GregTech_API.TAB_GREGTECH);

      for(i = 0; i < 16; ++i) {
         MinecraftForge.setBlockHarvestLevel(this, i, "pickaxe", 2);
      }

   }

   @SideOnly(Side.CLIENT)
   public void func_94332_a(IconRegister par1IconRegister) {
      int i;
      for(i = 0; i < mIcons.length; ++i) {
         mIcons[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":this.func_71917_a() + "/" + i));
      }

      for(i = 0; i < mIconGasTurbine.length; ++i) {
         mIconGasTurbine[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":"tile.GasTurbine/GasTurbine" + (i + 1)));
      }

      for(i = 0; i < mIconGasTurbineActive.length; ++i) {
         mIconGasTurbineActive[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":"tile.GasTurbine/GasTurbineActive" + (i + 1)));
      }

      for(i = 0; i < mIconSteamTurbine.length; ++i) {
         mIconSteamTurbine[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":"tile.SteamTurbine/SteamTurbine" + (i + 1)));
      }

      for(i = 0; i < mIconSteamTurbineActive.length; ++i) {
         mIconSteamTurbineActive[i] = par1IconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":"tile.SteamTurbine/SteamTurbineActive" + (i + 1)));
      }

      if(GregTech_API.sPostloadFinished) {
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateSilver"), mIcons[3]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateRuby"), mIcons[4]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateSapphire"), mIcons[5]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateAluminium"), mIcons[7]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateTitanium"), mIcons[8]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateChrome"), mIcons[9]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateSteel"), mIcons[11]);
         GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateBrass"), mIcons[12]);
      }

   }

   public boolean isBeaconBase(World aWorld, int aX, int aY, int aZ, int beaconX, int beaconY, int beaconZ) {
      return !GregTech_API.isMachineBlock(this, aWorld.func_72805_g(aX, aY, aZ));
   }

   public void func_71852_a(World aWorld, int aX, int aY, int aZ, int par5, int par6) {
      if(GregTech_API.isMachineBlock(this, aWorld.func_72805_g(aX, aY, aZ))) {
         GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
      }

   }

   public void func_71861_g(World aWorld, int aX, int aY, int aZ) {
      if(GregTech_API.isMachineBlock(this, aWorld.func_72805_g(aX, aY, aZ))) {
         GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
      }

   }

   public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
      return false;
   }

   public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
      if(world == null) {
         return 0.0F;
      } else {
         Integer tMeta = Integer.valueOf(world.func_72805_g(x, y, z));
         if(tMeta == null) {
            tMeta = Integer.valueOf(0);
         }

         return tMeta.intValue() == 0?30.0F:(tMeta.intValue() == 1?30.0F:(tMeta.intValue() == 2?300.0F:(tMeta.intValue() == 3?30.0F:(tMeta.intValue() == 4?30.0F:(tMeta.intValue() == 5?30.0F:(tMeta.intValue() == 6?30.0F:(tMeta.intValue() == 7?30.0F:(tMeta.intValue() == 8?200.0F:(tMeta.intValue() == 9?100.0F:(tMeta.intValue() == 10?200.0F:(tMeta.intValue() == 11?100.0F:(tMeta.intValue() == 12?30.0F:(tMeta.intValue() == 13?30.0F:(tMeta.intValue() == 14?60.0F:(tMeta.intValue() == 15?30.0F:super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ))))))))))))))));
      }
   }

   public float func_71934_m(World world, int x, int y, int z) {
      if(world == null) {
         return 0.0F;
      } else {
         Integer tMeta = Integer.valueOf(world.func_72805_g(x, y, z));
         if(tMeta == null) {
            tMeta = Integer.valueOf(0);
         }

         return tMeta.intValue() == 2?100.0F:(tMeta.intValue() == 8?10.0F:(tMeta.intValue() == 9?10.0F:(tMeta.intValue() == 10?10.0F:3.0F)));
      }
   }

   public Icon func_71858_a(int aSide, int aMeta) {
      return aMeta >= 0 && aMeta < mIcons.length?mIcons[aMeta]:null;
   }

   public int func_71899_b(int par1) {
      return par1;
   }

   public int func_71873_h(World par1World, int par2, int par3, int par4) {
      return par1World.func_72805_g(par2, par3, par4);
   }

   public int func_71925_a(Random par1Random) {
      return 1;
   }

   public int func_71885_a(int par1, Random par2Random, int par3) {
      return this.field_71990_ca;
   }

   @SideOnly(Side.CLIENT)
   public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List) {
      for(int i = 1; i < 16; ++i) {
         if(i != 6) {
            par3List.add(new ItemStack(par1, 1, i));
         }
      }

   }

   public Icon func_71895_b(IBlockAccess aWorld, int xCoord, int yCoord, int zCoord, int aSide) {
      int tMeta = aWorld.func_72805_g(xCoord, yCoord, zCoord);
      if(tMeta >= 13 && (xCoord != 0 || yCoord != 0 || zCoord != 0) && mConnectedMachineTextures) {
         int tStartIndex = tMeta == 15?40:(tMeta == 14?28:16);
         TileEntity tConnectedSides;
         IMetaTileEntity tMetaTileEntity;
         if(tMeta == 13) {
            if(aSide != 2 && aSide != 3) {
               if(aSide == 4 || aSide == 5) {
                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord - 1, zCoord + (aSide == 4?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconSteamTurbineActive[0];
                     }

                     return mIconSteamTurbine[0];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord, zCoord + (aSide == 4?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconSteamTurbineActive[3];
                     }

                     return mIconSteamTurbine[3];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord + 1, zCoord + (aSide == 4?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconSteamTurbineActive[6];
                     }

                     return mIconSteamTurbine[6];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord - 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconSteamTurbineActive[1];
                     }

                     return mIconSteamTurbine[1];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord + 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconSteamTurbineActive[7];
                     }

                     return mIconSteamTurbine[7];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord + 1, zCoord + (aSide == 5?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconSteamTurbineActive[8];
                     }

                     return mIconSteamTurbine[8];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord, zCoord + (aSide == 5?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconSteamTurbineActive[5];
                     }

                     return mIconSteamTurbine[5];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord - 1, zCoord + (aSide == 5?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconSteamTurbineActive[2];
                     }

                     return mIconSteamTurbine[2];
                  }
               }
            } else {
               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 3?1:-1), yCoord - 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconSteamTurbineActive[0];
                  }

                  return mIconSteamTurbine[0];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 3?1:-1), yCoord, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconSteamTurbineActive[3];
                  }

                  return mIconSteamTurbine[3];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 3?1:-1), yCoord + 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconSteamTurbineActive[6];
                  }

                  return mIconSteamTurbine[6];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord - 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconSteamTurbineActive[1];
                  }

                  return mIconSteamTurbine[1];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord + 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconSteamTurbineActive[7];
                  }

                  return mIconSteamTurbine[7];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 2?1:-1), yCoord + 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconSteamTurbineActive[8];
                  }

                  return mIconSteamTurbine[8];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 2?1:-1), yCoord, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconSteamTurbineActive[5];
                  }

                  return mIconSteamTurbine[5];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 2?1:-1), yCoord - 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconSteamTurbineActive[2];
                  }

                  return mIconSteamTurbine[2];
               }
            }
         }

         if(tMeta == 14) {
            if(aSide != 2 && aSide != 3) {
               if(aSide == 4 || aSide == 5) {
                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord - 1, zCoord + (aSide == 4?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconGasTurbineActive[0];
                     }

                     return mIconGasTurbine[0];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord, zCoord + (aSide == 4?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconGasTurbineActive[3];
                     }

                     return mIconGasTurbine[3];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord + 1, zCoord + (aSide == 4?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconGasTurbineActive[6];
                     }

                     return mIconGasTurbine[6];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord - 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconGasTurbineActive[1];
                     }

                     return mIconGasTurbine[1];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord + 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconGasTurbineActive[7];
                     }

                     return mIconGasTurbine[7];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord + 1, zCoord + (aSide == 5?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconGasTurbineActive[8];
                     }

                     return mIconGasTurbine[8];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord, zCoord + (aSide == 5?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconGasTurbineActive[5];
                     }

                     return mIconGasTurbine[5];
                  }

                  if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord - 1, zCoord + (aSide == 5?1:-1))) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                     if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                        return mIconGasTurbineActive[2];
                     }

                     return mIconGasTurbine[2];
                  }
               }
            } else {
               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 3?1:-1), yCoord - 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconGasTurbineActive[0];
                  }

                  return mIconGasTurbine[0];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 3?1:-1), yCoord, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconGasTurbineActive[3];
                  }

                  return mIconGasTurbine[3];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 3?1:-1), yCoord + 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconGasTurbineActive[6];
                  }

                  return mIconGasTurbine[6];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord - 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconGasTurbineActive[1];
                  }

                  return mIconGasTurbine[1];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord, yCoord + 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconGasTurbineActive[7];
                  }

                  return mIconGasTurbine[7];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 2?1:-1), yCoord + 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconGasTurbineActive[8];
                  }

                  return mIconGasTurbine[8];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 2?1:-1), yCoord, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconGasTurbineActive[5];
                  }

                  return mIconGasTurbine[5];
               }

               if(null != (tConnectedSides = aWorld.func_72796_p(xCoord + (aSide == 2?1:-1), yCoord - 1, zCoord)) && tConnectedSides instanceof IGregTechTileEntity && ((IGregTechTileEntity)tConnectedSides).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tConnectedSides).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                  if(((IGregTechTileEntity)tConnectedSides).isActive()) {
                     return mIconGasTurbineActive[2];
                  }

                  return mIconGasTurbine[2];
               }
            }
         }

         boolean[] tConnectedSides1 = new boolean[]{aWorld.func_72798_a(xCoord, yCoord - 1, zCoord) == this.field_71990_ca && aWorld.func_72805_g(xCoord, yCoord - 1, zCoord) == tMeta, aWorld.func_72798_a(xCoord, yCoord + 1, zCoord) == this.field_71990_ca && aWorld.func_72805_g(xCoord, yCoord + 1, zCoord) == tMeta, aWorld.func_72798_a(xCoord + 1, yCoord, zCoord) == this.field_71990_ca && aWorld.func_72805_g(xCoord + 1, yCoord, zCoord) == tMeta, aWorld.func_72798_a(xCoord, yCoord, zCoord + 1) == this.field_71990_ca && aWorld.func_72805_g(xCoord, yCoord, zCoord + 1) == tMeta, aWorld.func_72798_a(xCoord - 1, yCoord, zCoord) == this.field_71990_ca && aWorld.func_72805_g(xCoord - 1, yCoord, zCoord) == tMeta, aWorld.func_72798_a(xCoord, yCoord, zCoord - 1) == this.field_71990_ca && aWorld.func_72805_g(xCoord, yCoord, zCoord - 1) == tMeta};
         switch(aSide) {
         case 0:
            if(tConnectedSides1[0]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides1[4] && tConnectedSides1[5] && tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides1[4] && tConnectedSides1[5] && tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 2];
            } else if(tConnectedSides1[4] && !tConnectedSides1[5] && tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 3];
            } else if(tConnectedSides1[4] && tConnectedSides1[5] && !tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 4];
            } else if(tConnectedSides1[4] && tConnectedSides1[5] && tConnectedSides1[2] && !tConnectedSides1[3]) {
               return mIcons[tStartIndex + 5];
            } else if(!tConnectedSides1[4] && !tConnectedSides1[5] && tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 8];
            } else if(tConnectedSides1[4] && !tConnectedSides1[5] && !tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 9];
            } else if(tConnectedSides1[4] && tConnectedSides1[5] && !tConnectedSides1[2] && !tConnectedSides1[3]) {
               return mIcons[tStartIndex + 10];
            } else if(!tConnectedSides1[4] && tConnectedSides1[5] && tConnectedSides1[2] && !tConnectedSides1[3]) {
               return mIcons[tStartIndex + 11];
            } else if(!tConnectedSides1[4] && !tConnectedSides1[5] && !tConnectedSides1[2] && !tConnectedSides1[3]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides1[4] && !tConnectedSides1[2]) {
               return mIcons[tStartIndex + 1];
            } else if(!tConnectedSides1[5] && !tConnectedSides1[3]) {
               return mIcons[tStartIndex + 0];
            }
         case 1:
            if(tConnectedSides1[1]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides1[4] && tConnectedSides1[5] && tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides1[4] && tConnectedSides1[5] && tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 2];
            } else if(tConnectedSides1[4] && !tConnectedSides1[5] && tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 3];
            } else if(tConnectedSides1[4] && tConnectedSides1[5] && !tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 4];
            } else if(tConnectedSides1[4] && tConnectedSides1[5] && tConnectedSides1[2] && !tConnectedSides1[3]) {
               return mIcons[tStartIndex + 5];
            } else if(!tConnectedSides1[4] && !tConnectedSides1[5] && tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 8];
            } else if(tConnectedSides1[4] && !tConnectedSides1[5] && !tConnectedSides1[2] && tConnectedSides1[3]) {
               return mIcons[tStartIndex + 9];
            } else if(tConnectedSides1[4] && tConnectedSides1[5] && !tConnectedSides1[2] && !tConnectedSides1[3]) {
               return mIcons[tStartIndex + 10];
            } else if(!tConnectedSides1[4] && tConnectedSides1[5] && tConnectedSides1[2] && !tConnectedSides1[3]) {
               return mIcons[tStartIndex + 11];
            } else if(!tConnectedSides1[4] && !tConnectedSides1[5] && !tConnectedSides1[2] && !tConnectedSides1[3]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides1[2] && !tConnectedSides1[4]) {
               return mIcons[tStartIndex + 1];
            } else if(!tConnectedSides1[3] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 0];
            }
         case 2:
            if(tConnectedSides1[5]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides1[2] && tConnectedSides1[0] && tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides1[2] && tConnectedSides1[0] && tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 2];
            } else if(tConnectedSides1[2] && !tConnectedSides1[0] && tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 5];
            } else if(tConnectedSides1[2] && tConnectedSides1[0] && !tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 4];
            } else if(tConnectedSides1[2] && tConnectedSides1[0] && tConnectedSides1[4] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 3];
            } else if(!tConnectedSides1[2] && !tConnectedSides1[0] && tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 11];
            } else if(tConnectedSides1[2] && !tConnectedSides1[0] && !tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 10];
            } else if(tConnectedSides1[2] && tConnectedSides1[0] && !tConnectedSides1[4] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 9];
            } else if(!tConnectedSides1[2] && tConnectedSides1[0] && tConnectedSides1[4] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 8];
            } else if(!tConnectedSides1[2] && !tConnectedSides1[0] && !tConnectedSides1[4] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides1[2] && !tConnectedSides1[4]) {
               return mIcons[tStartIndex + 1];
            } else if(!tConnectedSides1[0] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 0];
            }
         case 3:
            if(tConnectedSides1[3]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides1[2] && tConnectedSides1[0] && tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides1[2] && tConnectedSides1[0] && tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 4];
            } else if(tConnectedSides1[2] && !tConnectedSides1[0] && tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 5];
            } else if(tConnectedSides1[2] && tConnectedSides1[0] && !tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 2];
            } else if(tConnectedSides1[2] && tConnectedSides1[0] && tConnectedSides1[4] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 3];
            } else if(!tConnectedSides1[2] && !tConnectedSides1[0] && tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 10];
            } else if(tConnectedSides1[2] && !tConnectedSides1[0] && !tConnectedSides1[4] && tConnectedSides1[1]) {
               return mIcons[tStartIndex + 11];
            } else if(tConnectedSides1[2] && tConnectedSides1[0] && !tConnectedSides1[4] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 8];
            } else if(!tConnectedSides1[2] && tConnectedSides1[0] && tConnectedSides1[4] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 9];
            } else if(!tConnectedSides1[2] && !tConnectedSides1[0] && !tConnectedSides1[4] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides1[2] && !tConnectedSides1[4]) {
               return mIcons[tStartIndex + 1];
            } else if(!tConnectedSides1[0] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 0];
            }
         case 4:
            if(tConnectedSides1[4]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides1[0] && tConnectedSides1[3] && tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides1[0] && tConnectedSides1[3] && tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 5];
            } else if(tConnectedSides1[0] && !tConnectedSides1[3] && tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 4];
            } else if(tConnectedSides1[0] && tConnectedSides1[3] && !tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 3];
            } else if(tConnectedSides1[0] && tConnectedSides1[3] && tConnectedSides1[1] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 2];
            } else if(!tConnectedSides1[0] && !tConnectedSides1[3] && tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 10];
            } else if(tConnectedSides1[0] && !tConnectedSides1[3] && !tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 9];
            } else if(tConnectedSides1[0] && tConnectedSides1[3] && !tConnectedSides1[1] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 8];
            } else if(!tConnectedSides1[0] && tConnectedSides1[3] && tConnectedSides1[1] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 11];
            } else if(!tConnectedSides1[0] && !tConnectedSides1[3] && !tConnectedSides1[1] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides1[0] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 0];
            } else if(!tConnectedSides1[3] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 1];
            }
         case 5:
            if(tConnectedSides1[2]) {
               return mIcons[tStartIndex + 7];
            } else if(tConnectedSides1[0] && tConnectedSides1[3] && tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 6];
            } else if(!tConnectedSides1[0] && tConnectedSides1[3] && tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 5];
            } else if(tConnectedSides1[0] && !tConnectedSides1[3] && tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 2];
            } else if(tConnectedSides1[0] && tConnectedSides1[3] && !tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 3];
            } else if(tConnectedSides1[0] && tConnectedSides1[3] && tConnectedSides1[1] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 4];
            } else if(!tConnectedSides1[0] && !tConnectedSides1[3] && tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 11];
            } else if(tConnectedSides1[0] && !tConnectedSides1[3] && !tConnectedSides1[1] && tConnectedSides1[5]) {
               return mIcons[tStartIndex + 8];
            } else if(tConnectedSides1[0] && tConnectedSides1[3] && !tConnectedSides1[1] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 9];
            } else if(!tConnectedSides1[0] && tConnectedSides1[3] && tConnectedSides1[1] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 10];
            } else if(!tConnectedSides1[0] && !tConnectedSides1[3] && !tConnectedSides1[1] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 7];
            } else if(!tConnectedSides1[0] && !tConnectedSides1[1]) {
               return mIcons[tStartIndex + 0];
            } else if(!tConnectedSides1[3] && !tConnectedSides1[5]) {
               return mIcons[tStartIndex + 1];
            }
         default:
            return mIcons[tStartIndex + 7];
         }
      } else {
         return this.func_71858_a(aSide, tMeta);
      }
   }

}
