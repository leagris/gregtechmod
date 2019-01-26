package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_ItsNotMyFaultException;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Log;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_MetaMachine_Item extends ItemBlock {

   public static int mItemID = 0;
   public String[] mString0 = new String[32766];
   public String[] mString1 = new String[32766];
   public String[] mString2 = new String[32766];
   public String[] mString3 = new String[32766];
   public String[] mString4 = new String[32766];
   public String[] mString5 = new String[32766];
   public String[] mString6 = new String[32766];


   public GT_MetaMachine_Item(int par1) {
      super(par1);
      mItemID = par1;
      this.func_77656_e(0);
      this.func_77627_a(true);
      this.func_77637_a(GregTech_API.TAB_GREGTECH);
   }

   public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean par4) {
      try {
         int e = this.getDamage(aStack);
         if(e < 0 || e >= 32766) {
            return;
         }

         if(this.mString1[e] == null) {
            this.mString0[e] = "";
            this.mString1[e] = "";
            this.mString2[e] = "";
            this.mString3[e] = "";
            this.mString4[e] = "";
            this.mString5[e] = "";
            this.mString6[e] = "";
            if(e != 0) {
               TileEntity aNBT = GregTech_API.sBlockList[1].createTileEntity(aPlayer.field_70170_p, e > 15?(GregTech_API.mMetaTileList[e] == null?0:GregTech_API.mMetaTileList[e].getTileEntityBaseType()):e);
               if(aNBT != null) {
                  aNBT.field_70331_k = aPlayer.field_70170_p;
                  aNBT.field_70329_l = 0;
                  aNBT.field_70330_m = 0;
                  aNBT.field_70327_n = 0;
                  if(aNBT instanceof IGregTechTileEntity) {
                     IGregTechTileEntity tAmount = (IGregTechTileEntity)aNBT;
                     tAmount.setInitialValuesAsNBT(new NBTTagCompound(), (short)e);
                     if(tAmount.getDescription() != null) {
                        this.mString0[e] = GT_LanguageManager.addStringLocalization("TileEntity_DESCRIPTION_" + e, tAmount.getDescription());
                     }

                     if(tAmount.getInputVoltage() > 0) {
                        this.mString1[e] = GT_LanguageManager.addStringLocalization("TileEntity_EUp_IN", "Max EU/p IN: ") + tAmount.getInputVoltage();
                     } else {
                        this.mString1[e] = "";
                     }

                     if(tAmount.getMetaTileEntity() != null && tAmount.getMetaTileEntity().getSpecialVoltageToolTip() != null) {
                        this.mString2[e] = GT_LanguageManager.addStringLocalization("TileEntity_VoltageToolTip_" + e, tAmount.getMetaTileEntity().getSpecialVoltageToolTip());
                     } else if(tAmount.getOutputVoltage() > 0) {
                        this.mString2[e] = GT_LanguageManager.addStringLocalization("TileEntity_EUp_OUT", "Max EU/p OUT: ") + tAmount.getOutputVoltage();
                     } else {
                        this.mString2[e] = "";
                     }

                     if(tAmount.getOutputAmperage() > 1) {
                        this.mString3[e] = GT_LanguageManager.addStringLocalization("TileEntity_EUp_AMOUNT", "Amount of Output Packets: ") + tAmount.getOutputAmperage();
                     } else {
                        this.mString3[e] = "";
                     }

                     if(tAmount.getEUCapacity() > 10000) {
                        this.mString4[e] = GT_LanguageManager.addStringLocalization("TileEntity_EUp_STORE", "EU Storage: ") + tAmount.getEUCapacity();
                     } else {
                        this.mString4[e] = "";
                     }

                     this.mString5[e] = (tAmount.isOverclockerUpgradable()?"O ":"") + (tAmount.isTransformerUpgradable()?"T ":"") + (tAmount.isBatteryUpgradable(0, (byte)0)?"B ":"") + (tAmount.isMJConverterUpgradable()?"M ":"") + (tAmount.isSteamEngineUpgradable()?"S ":"");
                     if(!this.mString5[e].equals("")) {
                        this.mString5[e] = GT_LanguageManager.addStringLocalization("TileEntity_UPGRADES", "Possible Upgrades: ") + this.mString5[e];
                     }

                     this.mString6[e] = "";
                  }
               }
            }
         }

         if(!this.mString0[e].equals("")) {
            aList.add(this.mString0[e]);
         }

         if(!this.mString1[e].equals("")) {
            aList.add(this.mString1[e]);
         }

         if(!this.mString2[e].equals("")) {
            aList.add(this.mString2[e]);
         }

         if(!this.mString3[e].equals("")) {
            aList.add(this.mString3[e]);
         }

         if(!this.mString4[e].equals("")) {
            aList.add(this.mString4[e]);
         }

         if(!this.mString5[e].equals("")) {
            aList.add(this.mString5[e]);
         }

         if(!this.mString6[e].equals("")) {
            aList.add(this.mString6[e]);
         }

         NBTTagCompound aNBT1 = aStack.func_77978_p();
         if(aNBT1 != null) {
            if(aNBT1.func_74767_n("mMuffler")) {
               aList.add(GT_LanguageManager.addStringLocalization("GT_TileEntity_MUFFLER", "has Muffler Upgrade"));
            }

            if(aNBT1.func_74767_n("mMJConverter")) {
               aList.add(GT_LanguageManager.addStringLocalization("GT_TileEntity_MJCONVERTER", "has MJ-Converter"));
            }

            if(aNBT1.func_74767_n("mSteamConverter")) {
               aList.add(GT_LanguageManager.addStringLocalization("GT_TileEntity_STEAMCONVERTER", "has Steam Upgrade"));
            }

            boolean tAmount1 = false;
            byte tAmount2;
            if((tAmount2 = aNBT1.func_74771_c("mOverclockers")) > 0) {
               aList.add(tAmount2 + " " + GT_LanguageManager.addStringLocalization("GT_TileEntity_OVERCLOCKERS", "Overclocker Upgrades"));
            }

            if((tAmount2 = aNBT1.func_74771_c("mTransformers")) > 0) {
               aList.add(tAmount2 + " " + GT_LanguageManager.addStringLocalization("GT_TileEntity_TRANSFORMERS", "Transformer Upgrades"));
            }

            if((tAmount2 = aNBT1.func_74771_c("mRSEnergyCells")) > 0) {
               aList.add(tAmount2 + " " + GT_LanguageManager.addStringLocalization("GT_TileEntity_ENERGYCELLS", "Energy Cell Upgrades"));
            }

            if((tAmount2 = aNBT1.func_74771_c("mSteamTanks")) > 0) {
               aList.add(tAmount2 + " " + GT_LanguageManager.addStringLocalization("GT_TileEntity_STEAMTANKS", "Steam Tank Upgrades"));
            }

            int tAmount3;
            if((tAmount3 = aNBT1.func_74762_e("mUpgradedStorage")) > 0) {
               aList.add(tAmount3 + " " + GT_LanguageManager.addStringLocalization("GT_TileEntity_EUSTORAGES", "Additional EU-Storage"));
            }
         }
      } catch (Throwable var8) {
         var8.printStackTrace(GT_Log.err);
      }

   }

   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
      return false;
   }

   public String func_77667_c(ItemStack aStack) {
      short tDamage = (short)this.getDamage(aStack);
      return tDamage >= 0 && tDamage < 32766?(tDamage < 16?this.func_77658_a() + "." + tDamage:(GregTech_API.mMetaTileList[tDamage] != null?this.func_77658_a() + "." + GregTech_API.mMetaTileList[tDamage].getMetaName():"")):"";
   }

   public boolean placeBlockAt(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int side, float hitX, float hitY, float hitZ, int aMeta) {
      short tDamage = (short)this.getDamage(aStack);
      if(tDamage > 15) {
         if(GregTech_API.mMetaTileList[tDamage] == null) {
            return false;
         }

         byte tMetaData = GregTech_API.mMetaTileList[tDamage].getTileEntityBaseType();
         if(!aWorld.func_72832_d(aX, aY, aZ, this.func_77883_f(), tMetaData, 3)) {
            return false;
         }

         if(aWorld.func_72798_a(aX, aY, aZ) != this.func_77883_f()) {
            throw new GT_ItsNotMyFaultException("Failed to place Block even though World.setBlock returned true. It COULD be MCPC/Bukkit causing that. In case you really have that installed, don\'t report this Bug to me, I don\'t know how to fix it.");
         }

         if(aWorld.func_72805_g(aX, aY, aZ) != tMetaData) {
            throw new GT_ItsNotMyFaultException("Failed to set the MetaValue of the Block even though World.setBlock returned true. It COULD be MCPC/Bukkit causing that. In case you really have that installed, don\'t report this Bug to me, I don\'t know how to fix it.");
         }

         IGregTechTileEntity tTileEntity = (IGregTechTileEntity)aWorld.func_72796_p(aX, aY, aZ);
         if(tTileEntity != null) {
            tTileEntity.setInitialValuesAsNBT(tTileEntity.isServerSide()?aStack.func_77978_p():null, tDamage);
            if(aPlayer != null) {
               tTileEntity.setOwnerName(aPlayer.field_71092_bJ);
            }

            tTileEntity.getMetaTileEntity().initDefaultModes(aStack.func_77978_p());
         }
      } else if(!aWorld.func_72832_d(aX, aY, aZ, this.func_77883_f(), tDamage, 3)) {
         return false;
      }

      if(aWorld.func_72798_a(aX, aY, aZ) == this.func_77883_f()) {
         Block.field_71973_m[this.func_77883_f()].func_71860_a(aWorld, aX, aY, aZ, aPlayer, aStack);
         Block.field_71973_m[this.func_77883_f()].func_85105_g(aWorld, aX, aY, aZ, tDamage);
      }

      return true;
   }

}
