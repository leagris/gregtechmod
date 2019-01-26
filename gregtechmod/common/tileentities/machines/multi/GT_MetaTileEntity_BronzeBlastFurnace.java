package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_BronzeBlastFurnace extends MetaTileEntity {

   public int mProgresstime = 0;
   public int mMaxProgresstime = 0;
   public int mUpdate = 5;
   public ItemStack mOutputItem1;
   public ItemStack mOutputItem2;
   public boolean mMachine = false;


   public GT_MetaTileEntity_BronzeBlastFurnace(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BronzeBlastFurnace() {}

   public boolean isSteampowered() {
      return false;
   }

   public boolean isElectric() {
      return false;
   }

   public boolean isPneumatic() {
      return false;
   }

   public boolean isTransformerUpgradable() {
      return false;
   }

   public boolean isOverclockerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return false;
   }

   public boolean isEnetInput() {
      return false;
   }

   public boolean isEnetOutput() {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return false;
   }

   public boolean isOutputFacing(byte aSide) {
      return false;
   }

   public boolean isTeleporterCompatible() {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return aFacing > 1;
   }

   public int getMinimumStoredEU() {
      return 1000;
   }

   public int maxEUInput() {
      return 0;
   }

   public int maxEUOutput() {
      return 0;
   }

   public int maxEUStore() {
      return 0;
   }

   public int maxMJStore() {
      return 0;
   }

   public int maxSteamStore() {
      return 0;
   }

   public int getInvSize() {
      return 4;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public int getProgresstime() {
      return this.mProgresstime;
   }

   public int maxProgresstime() {
      return this.mMaxProgresstime;
   }

   public int increaseProgress(int aProgress) {
      this.mProgresstime += aProgress;
      return this.mMaxProgresstime - this.mProgresstime;
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return GregTech_API.getCoverBehavior(aCoverID).isSimpleCover() && super.allowCoverOnSide(aSide, aCoverID);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_BronzeBlastFurnace();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mProgresstime", this.mProgresstime);
      aNBT.func_74768_a("mMaxProgresstime", this.mMaxProgresstime);
      NBTTagCompound tNBT;
      if(this.mOutputItem1 != null) {
         tNBT = new NBTTagCompound();
         this.mOutputItem1.func_77955_b(tNBT);
         aNBT.func_74782_a("mOutputItem1", tNBT);
      }

      if(this.mOutputItem2 != null) {
         tNBT = new NBTTagCompound();
         this.mOutputItem2.func_77955_b(tNBT);
         aNBT.func_74782_a("mOutputItem2", tNBT);
      }

   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mUpdate = 5;
      this.mProgresstime = aNBT.func_74762_e("mProgresstime");
      this.mMaxProgresstime = aNBT.func_74762_e("mMaxProgresstime");
      NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.func_74781_a("mOutputItem1");
      if(tNBT1 != null) {
         this.mOutputItem1 = GT_Utility.loadItem(tNBT1);
      }

      NBTTagCompound tNBT2 = (NBTTagCompound)aNBT.func_74781_a("mOutputItem2");
      if(tNBT2 != null) {
         this.mOutputItem2 = GT_Utility.loadItem(tNBT2);
      }

   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 170);
   }

   private boolean checkMachine() {
      int xDir = ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getBackFacing()).offsetX;
      int zDir = ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getBackFacing()).offsetZ;

      for(int i = -1; i < 2; ++i) {
         for(int j = -1; j < 3; ++j) {
            for(int k = -1; k < 2; ++k) {
               if(xDir + i != 0 || j != 0 || zDir + k != 0) {
                  if(i == 0 && j != -1 && k == 0) {
                     if(!GT_Utility.arrayContains(this.getBaseMetaTileEntity().getBlockOffset(xDir + i, j, zDir + k), new Object[]{Block.field_71938_D, Block.field_71944_C, null}) && !this.getBaseMetaTileEntity().getAirOffset(xDir + i, j, zDir + k)) {
                        return false;
                     }
                  } else if(this.getBaseMetaTileEntity().getBlockOffset(xDir + i, j, zDir + k) != GregTech_API.sBlockList[4] || this.getBaseMetaTileEntity().getMetaIDOffset(xDir + i, j, zDir + k) != 13) {
                     return false;
                  }
               }
            }
         }
      }

      return true;
   }

   public void onMachineBlockUpdate() {
      this.mUpdate = 5;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isClientSide() && this.getBaseMetaTileEntity().isActive()) {
         this.getBaseMetaTileEntity().getWorld().func_72869_a("largesmoke", (double)this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getBackFacing(), 1) + Math.random(), (double)this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getBackFacing(), 1), (double)this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getBackFacing(), 1) + Math.random(), 0.0D, 0.3D, 0.0D);
      }

      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.mUpdate-- == 0) {
            this.mMachine = this.checkMachine();
         }

         if(this.mMachine) {
            if(this.mMaxProgresstime > 0) {
               if(++this.mProgresstime >= this.mMaxProgresstime) {
                  this.addOutputProducts();
                  this.mOutputItem1 = null;
                  this.mOutputItem2 = null;
                  this.mProgresstime = 0;
                  this.mMaxProgresstime = 0;
               }
            } else if(this.getBaseMetaTileEntity().isAllowedToWork()) {
               this.checkRecipe();
            }
         }

         this.getBaseMetaTileEntity().setActive(this.mMaxProgresstime > 0 && this.mMachine);
         if(this.getBaseMetaTileEntity().isActive()) {
            if(this.getBaseMetaTileEntity().getAir(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getBackFacing(), 1), this.getBaseMetaTileEntity().getYCoord(), this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getBackFacing(), 1))) {
               this.getBaseMetaTileEntity().getWorld().func_72832_d(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getBackFacing(), 1), this.getBaseMetaTileEntity().getYCoord(), this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getBackFacing(), 1), Block.field_71938_D.field_71990_ca, 1, 2);
               this.mUpdate = 1;
            }

            if(this.getBaseMetaTileEntity().getAir(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getBackFacing(), 1), this.getBaseMetaTileEntity().getYCoord() + 1, this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getBackFacing(), 1))) {
               this.getBaseMetaTileEntity().getWorld().func_72832_d(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getBackFacing(), 1), this.getBaseMetaTileEntity().getYCoord() + 1, this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getBackFacing(), 1), Block.field_71938_D.field_71990_ca, 1, 2);
               this.mUpdate = 1;
            }
         } else {
            if(this.getBaseMetaTileEntity().getBlock(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getBackFacing(), 1), this.getBaseMetaTileEntity().getYCoord(), this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getBackFacing(), 1)) == Block.field_71938_D) {
               this.getBaseMetaTileEntity().getWorld().func_72832_d(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getBackFacing(), 1), this.getBaseMetaTileEntity().getYCoord(), this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getBackFacing(), 1), 0, 0, 2);
               this.mUpdate = 1;
            }

            if(this.getBaseMetaTileEntity().getBlock(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getBackFacing(), 1), this.getBaseMetaTileEntity().getYCoord() + 1, this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getBackFacing(), 1)) == Block.field_71938_D) {
               this.getBaseMetaTileEntity().getWorld().func_72832_d(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getBackFacing(), 1), this.getBaseMetaTileEntity().getYCoord() + 1, this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getBackFacing(), 1), 0, 0, 2);
               this.mUpdate = 1;
            }
         }
      }

   }

   private void addOutputProducts() {
      if(this.mOutputItem1 != null) {
         if(this.mInventory[2] == null) {
            this.mInventory[2] = GT_Utility.copy(new Object[]{this.mOutputItem1});
         } else if(GT_Utility.areStacksEqual(this.mInventory[2], this.mOutputItem1)) {
            this.mInventory[2].field_77994_a = Math.min(this.mOutputItem1.func_77976_d(), this.mOutputItem1.field_77994_a + this.mInventory[2].field_77994_a);
         }
      }

      if(this.mOutputItem2 != null) {
         if(this.mInventory[3] == null) {
            this.mInventory[3] = GT_Utility.copy(new Object[]{this.mOutputItem2});
         } else if(GT_Utility.areStacksEqual(this.mInventory[3], this.mOutputItem2)) {
            this.mInventory[3].field_77994_a = Math.min(this.mOutputItem2.func_77976_d(), this.mOutputItem2.field_77994_a + this.mInventory[3].field_77994_a);
         }
      }

   }

   private boolean spaceForOutput(ItemStack aStack1, ItemStack aStack2) {
      return (this.mInventory[2] == null || aStack1 == null || this.mInventory[2].field_77994_a + aStack1.field_77994_a <= this.mInventory[2].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[2], aStack1)) && (this.mInventory[3] == null || aStack2 == null || this.mInventory[3].field_77994_a + aStack2.field_77994_a <= this.mInventory[3].func_77976_d() && GT_Utility.areStacksEqual(this.mInventory[3], aStack2));
   }

   private boolean checkRecipe() {
      if(!this.mMachine) {
         return false;
      } else {
         if(this.mInventory[0] != null && this.mInventory[1] != null && this.mInventory[0].field_77994_a >= 1) {
            if(!GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[0], "dustIron") && !GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[0], "ingotIron")) {
               if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[0], "dustSteel")) {
                  if(this.mInventory[1].func_77973_b() == Item.field_77705_m && this.mInventory[1].field_77994_a >= 2 && this.spaceForOutput(this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 1L), this.mOutputItem2 = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 2L))) {
                     this.getBaseMetaTileEntity().func_70298_a(0, 1);
                     this.getBaseMetaTileEntity().func_70298_a(1, 2);
                     this.mMaxProgresstime = 3600;
                     return true;
                  }

                  if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "fuelCoke") && this.mInventory[1].field_77994_a >= 1 && this.spaceForOutput(this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 1L), this.mOutputItem2 = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Ash, 2L))) {
                     this.getBaseMetaTileEntity().func_70298_a(0, 1);
                     this.getBaseMetaTileEntity().func_70298_a(1, 1);
                     this.mMaxProgresstime = 2400;
                     return true;
                  }

                  if(this.mInventory[0].field_77994_a >= 9 && (GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "blockCoal") || GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "blockCharcoal")) && this.mInventory[1].field_77994_a >= 2 && this.spaceForOutput(this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 9L), this.mOutputItem2 = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 18L))) {
                     this.getBaseMetaTileEntity().func_70298_a(0, 9);
                     this.getBaseMetaTileEntity().func_70298_a(1, 2);
                     this.mMaxProgresstime = 32400;
                     return true;
                  }
               } else if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[0], "blockIron")) {
                  if(this.mInventory[1].func_77973_b() == Item.field_77705_m && this.mInventory[1].field_77994_a >= 36 && this.spaceForOutput(this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 9L), this.mOutputItem2 = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 36L))) {
                     this.getBaseMetaTileEntity().func_70298_a(0, 1);
                     this.getBaseMetaTileEntity().func_70298_a(1, 36);
                     this.mMaxProgresstime = '\ufd20';
                     return true;
                  }

                  if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "fuelCoke") && this.mInventory[1].field_77994_a >= 18 && this.spaceForOutput(this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 9L), this.mOutputItem2 = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Ash, 36L))) {
                     this.getBaseMetaTileEntity().func_70298_a(0, 1);
                     this.getBaseMetaTileEntity().func_70298_a(1, 18);
                     this.mMaxProgresstime = '\ua8c0';
                     return true;
                  }

                  if((GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "blockCoal") || GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "blockCharcoal")) && this.mInventory[1].field_77994_a >= 4 && this.spaceForOutput(this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 9L), this.mOutputItem2 = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 36L))) {
                     this.getBaseMetaTileEntity().func_70298_a(0, 1);
                     this.getBaseMetaTileEntity().func_70298_a(1, 4);
                     this.mMaxProgresstime = '\ufd20';
                     return true;
                  }
               }
            } else {
               if(this.mInventory[1].func_77973_b() == Item.field_77705_m && this.mInventory[1].field_77994_a >= 4 && this.spaceForOutput(this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 1L), this.mOutputItem2 = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 4L))) {
                  this.getBaseMetaTileEntity().func_70298_a(0, 1);
                  this.getBaseMetaTileEntity().func_70298_a(1, 4);
                  this.mMaxProgresstime = 7200;
                  return true;
               }

               if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "fuelCoke") && this.mInventory[1].field_77994_a >= 2 && this.spaceForOutput(this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 1L), this.mOutputItem2 = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Ash, 4L))) {
                  this.getBaseMetaTileEntity().func_70298_a(0, 1);
                  this.getBaseMetaTileEntity().func_70298_a(1, 2);
                  this.mMaxProgresstime = 4800;
                  return true;
               }

               if(this.mInventory[0].field_77994_a >= 9 && (GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "blockCoal") || GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "blockCharcoal")) && this.mInventory[1].field_77994_a >= 4 && this.spaceForOutput(this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Steel, 9L), this.mOutputItem2 = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 36L))) {
                  this.getBaseMetaTileEntity().func_70298_a(0, 9);
                  this.getBaseMetaTileEntity().func_70298_a(1, 4);
                  this.mMaxProgresstime = '\ufd20';
                  return true;
               }
            }
         }

         this.mOutputItem1 = null;
         this.mOutputItem2 = null;
         return false;
      }
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?(aActive?348:347):(aSide == 0?315:(aSide == 1?314:316));
   }

   public boolean isGivingInformation() {
      return false;
   }

   public String getDescription() {
      return "To get your first Steel";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex > 1;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex < 2 && !GT_Utility.areStacksEqual(aStack, this.mInventory[aIndex == 0?1:0]);
   }
}
