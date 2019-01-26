package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Multi_ThermalBoiler extends GT_MetaTileEntity_MultiBlockBase {

   public boolean isFacingValid(byte aFacing) {
      return aFacing > 1;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 158, GregTech_API.gregtechmod);
   }

   public GT_MetaTileEntity_Multi_ThermalBoiler(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Multi_ThermalBoiler() {}

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Multi_ThermalBoiler();
   }

   public boolean isCorrectMachinePart(ItemStack aStack) {
      return true;
   }

   public int getDamageToComponent(ItemStack aStack) {
      return GT_Utility.areStacksEqual(aStack, GT_Items.Component_LavaFilter.getWildcard(1L, new Object[0]))?1:0;
   }

   public boolean checkRecipe(ItemStack aStack) {
      Iterator i$ = GT_Recipe.sHotFuels.iterator();

      GT_Recipe tRecipe;
      do {
         if(!i$.hasNext()) {
            return false;
         }

         tRecipe = (GT_Recipe)i$.next();
      } while(!this.depleteInput(tRecipe.getRepresentativeInput(0)));

      this.mEUt = 400;
      this.mMaxProgresstime = tRecipe.mStartEU * 2 / 5;
      this.mEfficiencyIncrease = this.mMaxProgresstime * 30;
      if(tRecipe.getOutput(0) != null) {
         this.mOutputItems = new ItemStack[]{GT_Utility.copy(new Object[]{tRecipe.getOutput(0)})};
      }

      if(GT_Utility.areStacksEqual(aStack, GT_Items.Component_LavaFilter.getWildcard(1L, new Object[0]))) {
         if(tRecipe.getOutput(1) != null && this.getBaseMetaTileEntity().getRandomNumber(1000) < 100) {
            this.mOutputItems = new ItemStack[]{GT_Utility.copy(new Object[]{tRecipe.getOutput(1)})};
         } else if(tRecipe.getOutput(2) != null && this.getBaseMetaTileEntity().getRandomNumber(900) < 50) {
            this.mOutputItems = new ItemStack[]{GT_Utility.copy(new Object[]{tRecipe.getOutput(2)})};
         } else if(tRecipe.getOutput(3) != null && this.getBaseMetaTileEntity().getRandomNumber(850) < 25) {
            this.mOutputItems = new ItemStack[]{GT_Utility.copy(new Object[]{tRecipe.getOutput(3)})};
         }
      }

      return true;
   }

   public boolean onRunningTick(ItemStack aStack) {
      if(this.mEUt > 0) {
         int tGeneratedEU = (int)((long)this.mEUt * 2L * (long)this.mEfficiency / 10000L);
         if(tGeneratedEU > 0 && this.depleteInput(GT_ModHandler.getWater((long)((tGeneratedEU + 160) / 160)))) {
            this.addOutput(GT_ModHandler.getSteam((long)tGeneratedEU));
         }

         return true;
      } else {
         return true;
      }
   }

   public boolean checkMachine(ItemStack aStack) {
      byte tSide = this.getBaseMetaTileEntity().getBackFacing();
      if(!this.getBaseMetaTileEntity().getAirAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 1)) {
         return false;
      } else if((this.getBaseMetaTileEntity().getBlockAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 2) != GregTech_API.sBlockList[0] || this.getBaseMetaTileEntity().getMetaIDAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 2) != 14) && !this.addToMachineList(this.getBaseMetaTileEntity().getIGregTechTileEntityAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 2))) {
         return false;
      } else {
         int tX = this.getBaseMetaTileEntity().getXCoord();
         short tY = this.getBaseMetaTileEntity().getYCoord();
         int tZ = this.getBaseMetaTileEntity().getZCoord();

         for(byte i = -1; i < 2; ++i) {
            for(byte j = -1; j < 2; ++j) {
               if(i != 0 || j != 0) {
                  for(byte k = 0; k < 3; ++k) {
                     if((i == 0 || j == 0) && k == 1) {
                        if((this.getBaseMetaTileEntity().getBlock(tX + (tSide < 4?i:(tSide == 5?k:-k)), tY + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != GregTech_API.sBlockList[0] || this.getBaseMetaTileEntity().getMetaID(tX + (tSide < 4?i:(tSide == 5?k:-k)), tY + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != 14) && !this.addToMachineList(this.getBaseMetaTileEntity().getIGregTechTileEntity(tX + (tSide < 4?i:(tSide == 5?k:-k)), tY + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)))) {
                           return false;
                        }
                     } else if(this.getBaseMetaTileEntity().getBlock(tX + (tSide < 4?i:(tSide == 5?k:-k)), tY + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != GregTech_API.sBlockList[0] || this.getBaseMetaTileEntity().getMetaID(tX + (tSide < 4?i:(tSide == 5?k:-k)), tY + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != 14) {
                        return false;
                     }
                  }
               }
            }
         }

         return true;
      }
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?(aActive?84:83):super.getTextureIndex(aSide, aFacing, aActive, aRedstone);
   }

   public boolean explodesOnComponentBreak(ItemStack aStack) {
      return false;
   }

   public int getMaxEfficiency(ItemStack aStack) {
      return 10000;
   }

   public int getPollutionPerTick(ItemStack aStack) {
      return 0;
   }

   public int getAmountOfOutputs() {
      return 1;
   }

   public String getDescription() {
      return "Converts Heat into Steam";
   }
}
