package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_MetaTileEntity_Boiler_Bronze extends GT_MetaTileEntity_BasicTank {

   public int mTemperature = 20;
   public int mProcessingEnergy = 0;
   public int mLossTimer = 0;
   public FluidStack mSteam = null;
   public boolean mHadNoWater = false;


   public GT_MetaTileEntity_Boiler_Bronze(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Boiler_Bronze() {}

   public boolean isElectric() {
      return false;
   }

   public boolean isPneumatic() {
      return false;
   }

   public boolean isSteampowered() {
      return false;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return aFacing > 1;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean isValidSlot(int aIndex) {
      return true;
   }

   public int getInvSize() {
      return 4;
   }

   public int getProgresstime() {
      return this.mTemperature;
   }

   public int maxProgresstime() {
      return 500;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      if(aPlayer != null) {
         if(GT_Utility.areStacksEqual(aPlayer.func_71045_bC(), new ItemStack(Item.field_77786_ax, 1))) {
            this.fill(GT_ModHandler.getWater((long)(1000 * aPlayer.func_71045_bC().field_77994_a)), true);
            aPlayer.func_71045_bC().field_77993_c = Item.field_77788_aw.field_77779_bT;
         } else {
            this.getBaseMetaTileEntity().openGUI(aPlayer, 163);
         }
      }

   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Boiler_Bronze();
   }

   public boolean doesFillContainers() {
      return true;
   }

   public boolean doesEmptyContainers() {
      return true;
   }

   public boolean canTankBeFilled() {
      return true;
   }

   public boolean canTankBeEmptied() {
      return true;
   }

   public boolean displaysItemStack() {
      return false;
   }

   public boolean displaysStackSize() {
      return false;
   }

   public boolean isFluidInputAllowed(FluidStack aFluid) {
      return GT_ModHandler.isWater(aFluid);
   }

   public FluidStack getDrainableStack() {
      return this.mSteam;
   }

   public FluidStack setDrainableStack(FluidStack aFluid) {
      this.mSteam = aFluid;
      return this.mSteam;
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return GregTech_API.getCoverBehavior(aCoverID).isSimpleCover();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74768_a("mLossTimer", this.mLossTimer);
      aNBT.func_74768_a("mTemperature", this.mTemperature);
      aNBT.func_74768_a("mProcessingEnergy", this.mProcessingEnergy);
      if(this.mSteam != null) {
         try {
            aNBT.func_74766_a("mSteam", this.mSteam.writeToNBT(new NBTTagCompound("mSteam")));
         } catch (Throwable var3) {
            ;
         }
      }

   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mLossTimer = aNBT.func_74762_e("mLossTimer");
      this.mTemperature = aNBT.func_74762_e("mTemperature");
      this.mProcessingEnergy = aNBT.func_74762_e("mProcessingEnergy");
      this.mSteam = FluidStack.loadFluidStackFromNBT(aNBT.func_74775_l("mSteam"));
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.mTemperature <= 20) {
            this.mTemperature = 20;
            this.mLossTimer = 0;
         }

         if(++this.mLossTimer > 45) {
            --this.mTemperature;
            this.mLossTimer = 0;
         }

         for(byte i = 1; this.mSteam != null && i < 6; ++i) {
            if(i != this.getBaseMetaTileEntity().getFrontFacing()) {
               IFluidHandler tTileEntity = this.getBaseMetaTileEntity().getITankContainerAtSide(i);
               if(tTileEntity != null) {
                  FluidStack tDrained = this.getBaseMetaTileEntity().drain(ForgeDirection.getOrientation(i), 1000, false);
                  if(tDrained != null) {
                     int tFilledAmount = tTileEntity.fill(ForgeDirection.getOrientation(i).getOpposite(), tDrained, false);
                     if(tFilledAmount > 0) {
                        tTileEntity.fill(ForgeDirection.getOrientation(i).getOpposite(), this.getBaseMetaTileEntity().drain(ForgeDirection.getOrientation(i), tFilledAmount, true), true);
                     }
                  }
               }
            }
         }

         if(this.getBaseMetaTileEntity().getTimer() % 25L == 0L) {
            if(this.mTemperature > 100) {
               if(this.mFluid != null && GT_ModHandler.isWater(this.mFluid) && this.mFluid.amount > 0) {
                  if(this.mHadNoWater) {
                     this.getBaseMetaTileEntity().doExplosion(2048);
                     return;
                  }

                  --this.mFluid.amount;
                  if(this.mSteam == null) {
                     this.mSteam = GT_ModHandler.getSteam(150L);
                  } else if(GT_ModHandler.isSteam(this.mSteam)) {
                     this.mSteam.amount += 150;
                  } else {
                     this.mSteam = GT_ModHandler.getSteam(150L);
                  }
               } else {
                  this.mHadNoWater = true;
               }
            } else {
               this.mHadNoWater = false;
            }
         }

         if(this.mSteam != null && this.mSteam.amount > 16000) {
            this.sendSound((byte)1);
            this.mSteam.amount = 16000;
         }

         if(this.mProcessingEnergy <= 0 && this.getBaseMetaTileEntity().isAllowedToWork() && this.mInventory[2] != null) {
            if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], OrePrefixes.gem.get(Materials.Coal))) {
               this.mProcessingEnergy += 160;
               this.getBaseMetaTileEntity().func_70298_a(2, 1);
               if(this.getBaseMetaTileEntity().getRandomNumber(3) == 0) {
                  this.getBaseMetaTileEntity().addStackToSlot(3, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 1L));
               }
            } else if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], OrePrefixes.gem.get(Materials.Charcoal))) {
               this.mProcessingEnergy += 160;
               this.getBaseMetaTileEntity().func_70298_a(2, 1);
               if(this.getBaseMetaTileEntity().getRandomNumber(3) == 0) {
                  this.getBaseMetaTileEntity().addStackToSlot(3, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Ash, 1L));
               }
            } else if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], "fuelCoke")) {
               this.mProcessingEnergy += 640;
               this.getBaseMetaTileEntity().func_70298_a(2, 1);
               if(this.getBaseMetaTileEntity().getRandomNumber(2) == 0) {
                  this.getBaseMetaTileEntity().addStackToSlot(3, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Ash, 1L));
               }
            } else if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], OrePrefixes.gem.get(Materials.Lignite))) {
               this.mProcessingEnergy += 40;
               this.getBaseMetaTileEntity().func_70298_a(2, 1);
               if(this.getBaseMetaTileEntity().getRandomNumber(8) == 0) {
                  this.getBaseMetaTileEntity().addStackToSlot(3, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 1L));
               }
            }
         }

         if(this.mTemperature < 500 && this.mProcessingEnergy > 0 && this.getBaseMetaTileEntity().getTimer() % 12L == 0L) {
            --this.mProcessingEnergy;
            ++this.mTemperature;
         }

         this.getBaseMetaTileEntity().setActive(this.mProcessingEnergy > 0);
      }

   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 1 || aIndex == 3;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 2;
   }

   public void doSound(byte aIndex, double aX, double aY, double aZ) {
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(4)), 2, 1.0F, aX, aY, aZ);

         for(int l = 0; l < 8; ++l) {
            this.getBaseMetaTileEntity().getWorld().func_72869_a("largesmoke", aX - 0.5D + Math.random(), aY, aZ - 0.5D + Math.random(), 0.0D, 0.0D, 0.0D);
         }
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?323:(aSide == 1?322:(aSide == aFacing?(aActive?325:324):321));
   }

   public String getDescription() {
      return "An early way to get Steam Power";
   }

   public int getCapacity() {
      return 16000;
   }

   public int getTankPressure() {
      return 100;
   }
}
