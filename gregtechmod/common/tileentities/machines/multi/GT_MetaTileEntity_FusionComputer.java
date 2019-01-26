package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.machines.multi.GT_MetaTileEntity_FusionEnergyInjector;
import gregtechmod.common.tileentities.machines.multi.GT_MetaTileEntity_FusionExtractor;
import gregtechmod.common.tileentities.machines.multi.GT_MetaTileEntity_FusionInjector;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_FusionComputer extends MetaTileEntity {

   public int mProgresstime = 0;
   public int mMaxProgresstime = 0;
   public int mEUt = 0;
   public int mUpdate = 0;
   public int mStartUpCheck = 100;
   private ItemStack mOutputItem1;
   public boolean mMachine = true;
   private ArrayList<IGregTechTileEntity> mPlasmaExtractors = new ArrayList();
   private ArrayList<IGregTechTileEntity> mEnergyInjectors = new ArrayList();
   private ArrayList<IGregTechTileEntity> mPrimaryInjectors = new ArrayList();
   private ArrayList<IGregTechTileEntity> mSecondaryInjectors = new ArrayList();


   public GT_MetaTileEntity_FusionComputer(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_FusionComputer() {}

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return aFacing > 1;
   }

   public int getInvSize() {
      return 1;
   }

   public int maxEUStore() {
      return 160000000;
   }

   public int getEUVar() {
      return this.getStoredEU();
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 143);
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
      return aProgress;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_FusionComputer();
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mEUt", this.mEUt);
      aNBT.func_74768_a("mProgresstime", this.mProgresstime);
      aNBT.func_74768_a("mMaxProgresstime", this.mMaxProgresstime);
      if(this.mOutputItem1 != null) {
         NBTTagCompound tNBT = new NBTTagCompound();
         this.mOutputItem1.func_77955_b(tNBT);
         aNBT.func_74782_a("mOutputItem1", tNBT);
      }

   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mUpdate = 100;
      this.mEUt = aNBT.func_74762_e("mEUt");
      this.mProgresstime = aNBT.func_74762_e("mProgresstime");
      this.mMaxProgresstime = aNBT.func_74762_e("mMaxProgresstime");
      NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.func_74781_a("mOutputItem1");
      if(tNBT1 != null) {
         this.mOutputItem1 = GT_Utility.loadItem(tNBT1);
      }

   }

   private void setComputerOf(MetaTileEntity aMetaTileEntity, boolean setreset) {
      if(aMetaTileEntity != null) {
         if(aMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector) {
            ((GT_MetaTileEntity_FusionInjector)aMetaTileEntity).mFusionComputer = setreset?this.getBaseMetaTileEntity():null;
            if(setreset) {
               if(aMetaTileEntity.getBaseMetaTileEntity().getYCoord() > this.getBaseMetaTileEntity().getYCoord()) {
                  this.mPrimaryInjectors.add(aMetaTileEntity.getBaseMetaTileEntity());
               } else {
                  this.mSecondaryInjectors.add(aMetaTileEntity.getBaseMetaTileEntity());
               }
            }
         }

         if(aMetaTileEntity instanceof GT_MetaTileEntity_FusionEnergyInjector) {
            ((GT_MetaTileEntity_FusionEnergyInjector)aMetaTileEntity).mFusionComputer = setreset?this.getBaseMetaTileEntity():null;
            if(setreset) {
               this.mEnergyInjectors.add(aMetaTileEntity.getBaseMetaTileEntity());
            }
         }

         if(aMetaTileEntity instanceof GT_MetaTileEntity_FusionExtractor) {
            ((GT_MetaTileEntity_FusionExtractor)aMetaTileEntity).mFusionComputer = setreset?this.getBaseMetaTileEntity():null;
            if(setreset) {
               this.mPlasmaExtractors.add(aMetaTileEntity.getBaseMetaTileEntity());
            }
         }
      }

   }

   private void reset() {
      Iterator i$ = this.mPlasmaExtractors.iterator();

      IGregTechTileEntity tTileEntity;
      while(i$.hasNext()) {
         tTileEntity = (IGregTechTileEntity)i$.next();
         this.setComputerOf((MetaTileEntity)tTileEntity.getMetaTileEntity(), false);
      }

      i$ = this.mPrimaryInjectors.iterator();

      while(i$.hasNext()) {
         tTileEntity = (IGregTechTileEntity)i$.next();
         this.setComputerOf((MetaTileEntity)tTileEntity.getMetaTileEntity(), false);
      }

      i$ = this.mSecondaryInjectors.iterator();

      while(i$.hasNext()) {
         tTileEntity = (IGregTechTileEntity)i$.next();
         this.setComputerOf((MetaTileEntity)tTileEntity.getMetaTileEntity(), false);
      }

      i$ = this.mEnergyInjectors.iterator();

      while(i$.hasNext()) {
         tTileEntity = (IGregTechTileEntity)i$.next();
         this.setComputerOf((MetaTileEntity)tTileEntity.getMetaTileEntity(), false);
      }

      this.mPlasmaExtractors = new ArrayList();
      this.mPrimaryInjectors = new ArrayList();
      this.mSecondaryInjectors = new ArrayList();
      this.mEnergyInjectors = new ArrayList();
   }

   public void onMachineBlockUpdate() {
      this.mUpdate = 100;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.mUpdate-- == 0 || this.mStartUpCheck == 0) {
            this.mMachine = this.checkMachine();
         }

         if(this.mStartUpCheck-- < 0) {
            if(this.mMaxProgresstime > 0) {
               if(this.mMachine && this.decreaseStoredEU(-this.mEUt)) {
                  if(++this.mProgresstime > this.mMaxProgresstime) {
                     this.addOutput(this.mOutputItem1);
                     this.mOutputItem1 = null;
                     this.mProgresstime = 0;
                     this.mMaxProgresstime = 0;
                     this.checkRecipe();
                  }
               } else {
                  this.addOutput(this.mOutputItem1);
                  this.mOutputItem1 = null;
                  this.mProgresstime = 0;
                  this.mMaxProgresstime = 0;
               }
            } else {
               this.checkRecipe();
            }

            this.getBaseMetaTileEntity().setActive(this.mMaxProgresstime > 0);
         }
      }

   }

   private boolean checkRecipe() {
      if(this.mMachine && this.getBaseMetaTileEntity().isAllowedToWork()) {
         GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(true, false, GT_Recipe.sFusionRecipes, new ItemStack[]{this.getPrimaryInput(), this.getSecondaryInput()});
         if(tRecipe != null && this.consumeInput(tRecipe.getRepresentativeInput(0), tRecipe.getRepresentativeInput(1), this.getBaseMetaTileEntity().isActive()?0:tRecipe.mStartEU)) {
            this.mMaxProgresstime = tRecipe.mDuration;
            this.mEUt = tRecipe.mEUt;
            this.mOutputItem1 = GT_Utility.copy(new Object[]{tRecipe.getOutput(0)});
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private ItemStack getPrimaryInput() {
      Iterator i$ = this.mPrimaryInjectors.iterator();

      while(i$.hasNext()) {
         IGregTechTileEntity tTileEntity = (IGregTechTileEntity)i$.next();
         if(tTileEntity.getMetaTileEntity() != null && tTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_FusionInjector && tTileEntity.isAllowedToWork()) {
            ItemStack rStack = ((GT_MetaTileEntity_FusionInjector)tTileEntity.getMetaTileEntity()).getMaterial();
            if(rStack != null) {
               return rStack;
            }
         }
      }

      return null;
   }

   private ItemStack getSecondaryInput() {
      Iterator i$ = this.mSecondaryInjectors.iterator();

      while(i$.hasNext()) {
         IGregTechTileEntity tTileEntity = (IGregTechTileEntity)i$.next();
         if(tTileEntity.getMetaTileEntity() != null && tTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_FusionInjector && tTileEntity.isAllowedToWork()) {
            ItemStack rStack = ((GT_MetaTileEntity_FusionInjector)tTileEntity.getMetaTileEntity()).getMaterial();
            if(rStack != null) {
               return rStack;
            }
         }
      }

      return null;
   }

   private int getStoredEU() {
      int rEU = 0;
      Iterator i$ = this.mEnergyInjectors.iterator();

      while(i$.hasNext()) {
         IGregTechTileEntity tTileEntity = (IGregTechTileEntity)i$.next();
         if(tTileEntity.isAllowedToWork()) {
            rEU += tTileEntity.getUniversalEnergyStored();
         }
      }

      return rEU;
   }

   private boolean decreaseStoredEU(int aEU) {
      if(aEU <= 0) {
         return true;
      } else if(this.getStoredEU() < aEU) {
         return false;
      } else {
         Iterator i$ = this.mEnergyInjectors.iterator();

         while(i$.hasNext()) {
            IGregTechTileEntity tTileEntity = (IGregTechTileEntity)i$.next();
            if(tTileEntity.isAllowedToWork()) {
               if(aEU <= tTileEntity.getUniversalEnergyStored()) {
                  return tTileEntity.decreaseStoredEnergyUnits(aEU, true);
               }

               aEU -= tTileEntity.getUniversalEnergyStored();
               if(!tTileEntity.decreaseStoredEnergyUnits(tTileEntity.getUniversalEnergyStored(), true)) {
                  return false;
               }
            }
         }

         return false;
      }
   }

   private boolean consumeInput(ItemStack aInput1, ItemStack aInput2, int aEU) {
      if(aInput1 != null && aInput2 != null && (aEU <= 0 || this.getStoredEU() >= aEU)) {
         Iterator i$ = this.mPrimaryInjectors.iterator();

         IGregTechTileEntity tTileEntity;
         ItemStack tStack;
         Iterator i$1;
         IGregTechTileEntity tTileEntity2;
         while(i$.hasNext()) {
            tTileEntity = (IGregTechTileEntity)i$.next();
            if(tTileEntity.getMetaTileEntity() != null && tTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_FusionInjector && tTileEntity.isAllowedToWork()) {
               tStack = ((GT_MetaTileEntity_FusionInjector)tTileEntity.getMetaTileEntity()).getMaterial();
               if(tStack != null && GT_Utility.areStacksEqual(tStack, aInput1) && tStack.field_77994_a >= aInput1.field_77994_a) {
                  i$1 = this.mSecondaryInjectors.iterator();

                  do {
                     if(!i$1.hasNext()) {
                        return false;
                     }

                     tTileEntity2 = (IGregTechTileEntity)i$1.next();
                  } while(tTileEntity2.getMetaTileEntity() == null || !(tTileEntity2.getMetaTileEntity() instanceof GT_MetaTileEntity_FusionInjector) || !((GT_MetaTileEntity_FusionInjector)tTileEntity2.getMetaTileEntity()).consumeMaterial(aInput2));

                  return this.decreaseStoredEU(aEU) && ((GT_MetaTileEntity_FusionInjector)tTileEntity.getMetaTileEntity()).consumeMaterial(aInput1);
               }
            }
         }

         i$ = this.mSecondaryInjectors.iterator();

         while(i$.hasNext()) {
            tTileEntity = (IGregTechTileEntity)i$.next();
            if(tTileEntity.getMetaTileEntity() != null && tTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_FusionInjector && tTileEntity.isAllowedToWork()) {
               tStack = ((GT_MetaTileEntity_FusionInjector)tTileEntity.getMetaTileEntity()).getMaterial();
               if(tStack != null && GT_Utility.areStacksEqual(tStack, aInput1) && tStack.field_77994_a >= aInput1.field_77994_a) {
                  i$1 = this.mPrimaryInjectors.iterator();

                  do {
                     if(!i$1.hasNext()) {
                        return false;
                     }

                     tTileEntity2 = (IGregTechTileEntity)i$1.next();
                  } while(tTileEntity2.getMetaTileEntity() == null || !(tTileEntity2.getMetaTileEntity() instanceof GT_MetaTileEntity_FusionInjector) || !((GT_MetaTileEntity_FusionInjector)tTileEntity2.getMetaTileEntity()).consumeMaterial(aInput2));

                  return this.decreaseStoredEU(aEU) && ((GT_MetaTileEntity_FusionInjector)tTileEntity.getMetaTileEntity()).consumeMaterial(aInput1);
               }
            }
         }
      }

      return false;
   }

   private void addOutput(ItemStack aOutput) {
      if(aOutput != null) {
         FluidStack tLiquid = GT_Utility.getFluidForFilledItem(aOutput);
         Iterator i$;
         IGregTechTileEntity tTileEntity;
         if(tLiquid == null) {
            i$ = this.mPlasmaExtractors.iterator();

            while(i$.hasNext()) {
               tTileEntity = (IGregTechTileEntity)i$.next();
               if(tTileEntity.isAllowedToWork()) {
                  ItemStack tStack = tTileEntity.func_70301_a(1);
                  if(tStack == null) {
                     tTileEntity.func_70299_a(1, GT_Utility.copy(new Object[]{aOutput}));
                     return;
                  }

                  if(GT_Utility.areStacksEqual(tStack, aOutput) && tStack.field_77994_a + aOutput.field_77994_a <= tStack.func_77976_d()) {
                     tStack.field_77994_a += aOutput.field_77994_a;
                     return;
                  }
               }
            }
         } else {
            i$ = this.mPlasmaExtractors.iterator();

            while(i$.hasNext()) {
               tTileEntity = (IGregTechTileEntity)i$.next();
               if(tTileEntity.isAllowedToWork() && ((MetaTileEntity)tTileEntity.getMetaTileEntity()).fill(tLiquid, false) == tLiquid.amount) {
                  ((MetaTileEntity)tTileEntity.getMetaTileEntity()).fill(tLiquid, true);
                  return;
               }
            }
         }

      }
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide != aFacing?(aActive?20:19):(aActive?50:48);
   }

   public String[] getInfoData() {
      return new String[]{"tile.BlockMetaID_Machine." + this.mName + ".name", this.mMachine?"Ready":"Incomplete", "Progress:", this.mProgresstime / 20 + "secs", this.mMaxProgresstime / 20 + "secs", "Charge:", this.getStoredEU() + " EU"};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public String getDescription() {
      return "FUUUUUUU-SION, HAH!";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   private boolean checkMachine() {
      this.reset();
      int xCenter = this.getBaseMetaTileEntity().getXCoord() + ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getFrontFacing()).offsetX * 5;
      short yCenter = this.getBaseMetaTileEntity().getYCoord();
      int zCenter = this.getBaseMetaTileEntity().getZCoord() + ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getFrontFacing()).offsetZ * 5;
      if((this.isAdvancedMachineCasing(xCenter + 5, yCenter, zCenter) || xCenter + 5 == this.getBaseMetaTileEntity().getXCoord()) && (this.isAdvancedMachineCasing(xCenter - 5, yCenter, zCenter) || xCenter - 5 == this.getBaseMetaTileEntity().getXCoord()) && (this.isAdvancedMachineCasing(xCenter, yCenter, zCenter + 5) || zCenter + 5 == this.getBaseMetaTileEntity().getZCoord()) && (this.isAdvancedMachineCasing(xCenter, yCenter, zCenter - 5) || zCenter - 5 == this.getBaseMetaTileEntity().getZCoord()) && this.checkCoils(xCenter, yCenter, zCenter) && this.checkHulls(xCenter, yCenter, zCenter) && this.checkUpperOrLowerHulls(xCenter, yCenter + 1, zCenter) && this.checkUpperOrLowerHulls(xCenter, yCenter - 1, zCenter) && this.addIfEnergyInjector(xCenter + 4, yCenter, zCenter + 3) && this.addIfEnergyInjector(xCenter + 4, yCenter, zCenter - 3) && this.addIfEnergyInjector(xCenter + 4, yCenter, zCenter + 5) && this.addIfEnergyInjector(xCenter + 4, yCenter, zCenter - 5) && this.addIfEnergyInjector(xCenter - 4, yCenter, zCenter + 3) && this.addIfEnergyInjector(xCenter - 4, yCenter, zCenter - 3) && this.addIfEnergyInjector(xCenter - 4, yCenter, zCenter + 5) && this.addIfEnergyInjector(xCenter - 4, yCenter, zCenter - 5) && this.addIfEnergyInjector(xCenter + 3, yCenter, zCenter + 4) && this.addIfEnergyInjector(xCenter - 3, yCenter, zCenter + 4) && this.addIfEnergyInjector(xCenter + 5, yCenter, zCenter + 4) && this.addIfEnergyInjector(xCenter - 5, yCenter, zCenter + 4) && this.addIfEnergyInjector(xCenter + 3, yCenter, zCenter - 4) && this.addIfEnergyInjector(xCenter - 3, yCenter, zCenter - 4) && this.addIfEnergyInjector(xCenter + 5, yCenter, zCenter - 4) && this.addIfEnergyInjector(xCenter - 5, yCenter, zCenter - 4) && this.addIfExtractor(xCenter + 1, yCenter, zCenter - 5) && this.addIfExtractor(xCenter + 1, yCenter, zCenter + 5) && this.addIfExtractor(xCenter - 1, yCenter, zCenter - 5) && this.addIfExtractor(xCenter - 1, yCenter, zCenter + 5) && this.addIfExtractor(xCenter + 1, yCenter, zCenter - 7) && this.addIfExtractor(xCenter + 1, yCenter, zCenter + 7) && this.addIfExtractor(xCenter - 1, yCenter, zCenter - 7) && this.addIfExtractor(xCenter - 1, yCenter, zCenter + 7) && this.addIfExtractor(xCenter + 5, yCenter, zCenter - 1) && this.addIfExtractor(xCenter + 5, yCenter, zCenter + 1) && this.addIfExtractor(xCenter - 5, yCenter, zCenter - 1) && this.addIfExtractor(xCenter - 5, yCenter, zCenter + 1) && this.addIfExtractor(xCenter + 7, yCenter, zCenter - 1) && this.addIfExtractor(xCenter + 7, yCenter, zCenter + 1) && this.addIfExtractor(xCenter - 7, yCenter, zCenter - 1) && this.addIfExtractor(xCenter - 7, yCenter, zCenter + 1) && this.addIfInjector(xCenter + 1, yCenter + 1, zCenter - 6) && this.addIfInjector(xCenter + 1, yCenter + 1, zCenter + 6) && this.addIfInjector(xCenter - 1, yCenter + 1, zCenter - 6) && this.addIfInjector(xCenter - 1, yCenter + 1, zCenter + 6) && this.addIfInjector(xCenter - 6, yCenter + 1, zCenter + 1) && this.addIfInjector(xCenter + 6, yCenter + 1, zCenter + 1) && this.addIfInjector(xCenter - 6, yCenter + 1, zCenter - 1) && this.addIfInjector(xCenter + 6, yCenter + 1, zCenter - 1) && this.addIfInjector(xCenter + 1, yCenter - 1, zCenter - 6) && this.addIfInjector(xCenter + 1, yCenter - 1, zCenter + 6) && this.addIfInjector(xCenter - 1, yCenter - 1, zCenter - 6) && this.addIfInjector(xCenter - 1, yCenter - 1, zCenter + 6) && this.addIfInjector(xCenter - 6, yCenter - 1, zCenter + 1) && this.addIfInjector(xCenter + 6, yCenter - 1, zCenter + 1) && this.addIfInjector(xCenter - 6, yCenter - 1, zCenter - 1) && this.addIfInjector(xCenter + 6, yCenter - 1, zCenter - 1)) {
         return true;
      } else {
         this.reset();
         return false;
      }
   }

   private boolean checkCoils(int aX, int aY, int aZ) {
      return this.isFusionCoil(aX + 6, aY, aZ - 1) && this.isFusionCoil(aX + 6, aY, aZ) && this.isFusionCoil(aX + 6, aY, aZ + 1) && this.isFusionCoil(aX + 5, aY, aZ - 3) && this.isFusionCoil(aX + 5, aY, aZ - 2) && this.isFusionCoil(aX + 5, aY, aZ + 2) && this.isFusionCoil(aX + 5, aY, aZ + 3) && this.isFusionCoil(aX + 4, aY, aZ - 4) && this.isFusionCoil(aX + 4, aY, aZ + 4) && this.isFusionCoil(aX + 3, aY, aZ - 5) && this.isFusionCoil(aX + 3, aY, aZ + 5) && this.isFusionCoil(aX + 2, aY, aZ - 5) && this.isFusionCoil(aX + 2, aY, aZ + 5) && this.isFusionCoil(aX + 1, aY, aZ - 6) && this.isFusionCoil(aX + 1, aY, aZ + 6) && this.isFusionCoil(aX, aY, aZ - 6) && this.isFusionCoil(aX, aY, aZ + 6) && this.isFusionCoil(aX - 1, aY, aZ - 6) && this.isFusionCoil(aX - 1, aY, aZ + 6) && this.isFusionCoil(aX - 2, aY, aZ - 5) && this.isFusionCoil(aX - 2, aY, aZ + 5) && this.isFusionCoil(aX - 3, aY, aZ - 5) && this.isFusionCoil(aX - 3, aY, aZ + 5) && this.isFusionCoil(aX - 4, aY, aZ - 4) && this.isFusionCoil(aX - 4, aY, aZ + 4) && this.isFusionCoil(aX - 5, aY, aZ - 3) && this.isFusionCoil(aX - 5, aY, aZ - 2) && this.isFusionCoil(aX - 5, aY, aZ + 2) && this.isFusionCoil(aX - 5, aY, aZ + 3) && this.isFusionCoil(aX - 6, aY, aZ - 1) && this.isFusionCoil(aX - 6, aY, aZ) && this.isFusionCoil(aX - 6, aY, aZ + 1);
   }

   private boolean checkUpperOrLowerHulls(int aX, int aY, int aZ) {
      return this.isAdvancedMachineCasing(aX + 6, aY, aZ) && this.isAdvancedMachineCasing(aX + 5, aY, aZ - 3) && this.isAdvancedMachineCasing(aX + 5, aY, aZ - 2) && this.isAdvancedMachineCasing(aX + 5, aY, aZ + 2) && this.isAdvancedMachineCasing(aX + 5, aY, aZ + 3) && this.isAdvancedMachineCasing(aX + 4, aY, aZ - 4) && this.isAdvancedMachineCasing(aX + 4, aY, aZ + 4) && this.isAdvancedMachineCasing(aX + 3, aY, aZ - 5) && this.isAdvancedMachineCasing(aX + 3, aY, aZ + 5) && this.isAdvancedMachineCasing(aX + 2, aY, aZ - 5) && this.isAdvancedMachineCasing(aX + 2, aY, aZ + 5) && this.isAdvancedMachineCasing(aX, aY, aZ - 6) && this.isAdvancedMachineCasing(aX, aY, aZ + 6) && this.isAdvancedMachineCasing(aX - 2, aY, aZ - 5) && this.isAdvancedMachineCasing(aX - 2, aY, aZ + 5) && this.isAdvancedMachineCasing(aX - 3, aY, aZ - 5) && this.isAdvancedMachineCasing(aX - 3, aY, aZ + 5) && this.isAdvancedMachineCasing(aX - 4, aY, aZ - 4) && this.isAdvancedMachineCasing(aX - 4, aY, aZ + 4) && this.isAdvancedMachineCasing(aX - 5, aY, aZ - 3) && this.isAdvancedMachineCasing(aX - 5, aY, aZ - 2) && this.isAdvancedMachineCasing(aX - 5, aY, aZ + 2) && this.isAdvancedMachineCasing(aX - 5, aY, aZ + 3) && this.isAdvancedMachineCasing(aX - 6, aY, aZ);
   }

   private boolean checkHulls(int aX, int aY, int aZ) {
      return this.isAdvancedMachineCasing(aX + 6, aY, aZ - 3) && this.isAdvancedMachineCasing(aX + 6, aY, aZ - 2) && this.isAdvancedMachineCasing(aX + 6, aY, aZ + 2) && this.isAdvancedMachineCasing(aX + 6, aY, aZ + 3) && this.isAdvancedMachineCasing(aX + 3, aY, aZ - 6) && this.isAdvancedMachineCasing(aX + 3, aY, aZ + 6) && this.isAdvancedMachineCasing(aX + 2, aY, aZ - 6) && this.isAdvancedMachineCasing(aX + 2, aY, aZ + 6) && this.isAdvancedMachineCasing(aX - 2, aY, aZ - 6) && this.isAdvancedMachineCasing(aX - 2, aY, aZ + 6) && this.isAdvancedMachineCasing(aX - 3, aY, aZ - 6) && this.isAdvancedMachineCasing(aX - 3, aY, aZ + 6) && this.isAdvancedMachineCasing(aX - 7, aY, aZ) && this.isAdvancedMachineCasing(aX + 7, aY, aZ) && this.isAdvancedMachineCasing(aX, aY, aZ - 7) && this.isAdvancedMachineCasing(aX, aY, aZ + 7) && this.isAdvancedMachineCasing(aX - 6, aY, aZ - 3) && this.isAdvancedMachineCasing(aX - 6, aY, aZ - 2) && this.isAdvancedMachineCasing(aX - 6, aY, aZ + 2) && this.isAdvancedMachineCasing(aX - 6, aY, aZ + 3) && this.isAdvancedMachineCasing(aX - 4, aY, aZ - 2) && this.isAdvancedMachineCasing(aX - 4, aY, aZ + 2) && this.isAdvancedMachineCasing(aX + 4, aY, aZ - 2) && this.isAdvancedMachineCasing(aX + 4, aY, aZ + 2) && this.isAdvancedMachineCasing(aX - 2, aY, aZ - 4) && this.isAdvancedMachineCasing(aX - 2, aY, aZ + 4) && this.isAdvancedMachineCasing(aX + 2, aY, aZ - 4) && this.isAdvancedMachineCasing(aX + 2, aY, aZ + 4);
   }

   private boolean addIfEnergyInjector(int aX, int aY, int aZ) {
      if(this.isEnergyInjector(aX, aY, aZ)) {
         this.setComputerOf(this.getMetaTileEntity(aX, aY, aZ), true);
         return true;
      } else {
         return this.isAdvancedMachineCasing(aX, aY, aZ);
      }
   }

   private boolean addIfInjector(int aX, int aY, int aZ) {
      if(this.isInjector(aX, aY, aZ)) {
         this.setComputerOf(this.getMetaTileEntity(aX, aY, aZ), true);
         return true;
      } else {
         return this.isAdvancedMachineCasing(aX, aY, aZ);
      }
   }

   private boolean addIfExtractor(int aX, int aY, int aZ) {
      if(this.isExtractor(aX, aY, aZ)) {
         this.setComputerOf(this.getMetaTileEntity(aX, aY, aZ), true);
         return true;
      } else {
         return this.isAdvancedMachineCasing(aX, aY, aZ);
      }
   }

   private boolean isAdvancedMachineCasing(int aX, int aY, int aZ) {
      return this.getBaseMetaTileEntity().getBlock(aX, aY, aZ) == GregTech_API.sBlockList[0] && this.getBaseMetaTileEntity().getMetaID(aX, aY, aZ) == 15;
   }

   private boolean isFusionCoil(int aX, int aY, int aZ) {
      return this.getBaseMetaTileEntity().getBlock(aX, aY, aZ) == GregTech_API.sBlockList[0] && this.getBaseMetaTileEntity().getMetaID(aX, aY, aZ) == 1;
   }

   private boolean isEnergyInjector(int aX, int aY, int aZ) {
      MetaTileEntity tMetaTileEntity = this.getMetaTileEntity(aX, aY, aZ);
      return tMetaTileEntity == null?false:tMetaTileEntity instanceof GT_MetaTileEntity_FusionEnergyInjector;
   }

   private boolean isInjector(int aX, int aY, int aZ) {
      MetaTileEntity tMetaTileEntity = this.getMetaTileEntity(aX, aY, aZ);
      return tMetaTileEntity == null?false:tMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector;
   }

   private boolean isExtractor(int aX, int aY, int aZ) {
      MetaTileEntity tMetaTileEntity = this.getMetaTileEntity(aX, aY, aZ);
      return tMetaTileEntity == null?false:tMetaTileEntity instanceof GT_MetaTileEntity_FusionExtractor;
   }

   private MetaTileEntity getMetaTileEntity(int aX, int aY, int aZ) {
      IGregTechTileEntity tTileEntity = this.getBaseMetaTileEntity().getIGregTechTileEntity(aX, aY, aZ);
      if(tTileEntity == null) {
         return null;
      } else {
         IMetaTileEntity tMetaTileEntity = tTileEntity.getMetaTileEntity();
         return tMetaTileEntity != null && tMetaTileEntity instanceof MetaTileEntity?(MetaTileEntity)tMetaTileEntity:null;
      }
   }
}
