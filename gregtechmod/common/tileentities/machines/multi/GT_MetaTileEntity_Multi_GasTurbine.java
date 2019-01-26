package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GT_MetaTileEntity_Multi_GasTurbine extends GT_MetaTileEntity_MultiBlockBase {

   public boolean isFacingValid(byte aFacing) {
      return aFacing > 1;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 156, GregTech_API.gregtechmod);
   }

   public GT_MetaTileEntity_Multi_GasTurbine(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Multi_GasTurbine() {}

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Multi_GasTurbine();
   }

   public boolean isCorrectMachinePart(ItemStack aStack) {
      return this.getMaxEfficiency(aStack) > 0;
   }

   public int getDamageToComponent(ItemStack aStack) {
      return GT_Utility.areStacksEqual(GT_ModHandler.getRCItem("part.turbine.rotor", 1L, 32767), aStack)?2:1;
   }

   public boolean checkRecipe(ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return false;
      } else {
         Iterator i$ = GT_Recipe.sTurbineFuels.iterator();

         GT_Recipe tRecipe;
         do {
            if(!i$.hasNext()) {
               return false;
            }

            tRecipe = (GT_Recipe)i$.next();
         } while(!this.depleteInput(tRecipe.getRepresentativeInput(0)));

         this.mEUt = 1000;
         this.mOutputItems = new ItemStack[]{GT_Utility.copy(new Object[]{tRecipe.getOutput(0)})};
         this.mMaxProgresstime = tRecipe.mStartEU;
         if(GT_Items.Component_Turbine_Bronze.isStackEqual(aStack, true, true)) {
            this.mEfficiencyIncrease = this.mMaxProgresstime * 10;
         } else if(GT_Items.Component_Turbine_Steel.isStackEqual(aStack, true, true)) {
            this.mEfficiencyIncrease = this.mMaxProgresstime * 20;
         } else if(GT_Items.Component_Turbine_Magnalium.isStackEqual(aStack, true, true)) {
            this.mEfficiencyIncrease = this.mMaxProgresstime * 50;
         } else if(GT_Items.Component_Turbine_TungstenSteel.isStackEqual(aStack, true, true)) {
            this.mEfficiencyIncrease = this.mMaxProgresstime * 15;
         } else if(GT_Items.Component_Turbine_Carbon.isStackEqual(aStack, true, true)) {
            this.mEfficiencyIncrease = this.mMaxProgresstime * 100;
         } else {
            this.mEfficiencyIncrease = this.mMaxProgresstime * 20;
         }

         return true;
      }
   }

   public boolean checkMachine(ItemStack aStack) {
      byte tSide = this.getBaseMetaTileEntity().getBackFacing();
      if(this.getBaseMetaTileEntity().getAirAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 1) && this.getBaseMetaTileEntity().getAirAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 2)) {
         int tAirCount = 0;

         byte tX;
         for(byte tTileEntity = -1; tTileEntity < 2; ++tTileEntity) {
            for(tX = -1; tX < 2; ++tX) {
               for(byte tY = -1; tY < 2; ++tY) {
                  if(this.getBaseMetaTileEntity().getAirOffset(tTileEntity, tX, tY)) {
                     ++tAirCount;
                  }
               }
            }
         }

         if(tAirCount != 10) {
            return false;
         } else {
            IGregTechTileEntity var11;
            for(tX = 2; tX < 6; ++tX) {
               if(null != (var11 = this.getBaseMetaTileEntity().getIGregTechTileEntityAtSideAndDistance(tX, 2)) && var11.getFrontFacing() == this.getBaseMetaTileEntity().getFrontFacing() && var11.getMetaTileEntity() != null && var11.getMetaTileEntity() instanceof GT_MetaTileEntity_Multi_GasTurbine) {
                  return false;
               }
            }

            int var12 = this.getBaseMetaTileEntity().getXCoord();
            short var13 = this.getBaseMetaTileEntity().getYCoord();
            int tZ = this.getBaseMetaTileEntity().getZCoord();

            for(byte i = -1; i < 2; ++i) {
               for(byte j = -1; j < 2; ++j) {
                  if(i != 0 || j != 0) {
                     for(byte k = 0; k < 4; ++k) {
                        if((i == 0 || j == 0) && (k == 1 || k == 2)) {
                           if((this.getBaseMetaTileEntity().getBlock(var12 + (tSide < 4?i:(tSide == 5?k:-k)), var13 + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != GregTech_API.sBlockList[0] || this.getBaseMetaTileEntity().getMetaID(var12 + (tSide < 4?i:(tSide == 5?k:-k)), var13 + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != 14) && !this.addToMachineList(this.getBaseMetaTileEntity().getIGregTechTileEntity(var12 + (tSide < 4?i:(tSide == 5?k:-k)), var13 + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)))) {
                              return false;
                           }
                        } else if(this.getBaseMetaTileEntity().getBlock(var12 + (tSide < 4?i:(tSide == 5?k:-k)), var13 + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != GregTech_API.sBlockList[0] || this.getBaseMetaTileEntity().getMetaID(var12 + (tSide < 4?i:(tSide == 5?k:-k)), var13 + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != 14) {
                           return false;
                        }
                     }
                  }
               }
            }

            this.mDynamoHatches.clear();
            var11 = this.getBaseMetaTileEntity().getIGregTechTileEntityAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 3);
            if(var11 != null && var11.getMetaTileEntity() != null) {
               if(!(var11.getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Dynamo)) {
                  return false;
               }

               this.mDynamoHatches.add((GT_MetaTileEntity_Hatch_Dynamo)var11.getMetaTileEntity());
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public boolean explodesOnComponentBreak(ItemStack aStack) {
      return true;
   }

   public int getMaxEfficiency(ItemStack aStack) {
      return GT_Utility.isStackInvalid(aStack)?0:(GT_Items.Component_Turbine_Bronze.isStackEqual(aStack, true, true)?6000:(GT_Items.Component_Turbine_Steel.isStackEqual(aStack, true, true)?8000:(GT_Items.Component_Turbine_Magnalium.isStackEqual(aStack, true, true)?10000:(GT_Items.Component_Turbine_TungstenSteel.isStackEqual(aStack, true, true)?9000:(GT_Items.Component_Turbine_Carbon.isStackEqual(aStack, true, true)?12500:(GT_Utility.areStacksEqual(aStack, GT_ModHandler.getRCItem("part.turbine.rotor", 1L, 32767))?8000:0))))));
   }

   public Icon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?(aActive?GT_BlockMetaID_Block.mIconGasTurbineActive[4]:GT_BlockMetaID_Block.mIconGasTurbine[4]):null;
   }

   public int getPollutionPerTick(ItemStack aStack) {
      return 1;
   }

   public int getAmountOfOutputs() {
      return 1;
   }

   public String getDescription() {
      return "About 42 small Gas Turbines worth";
   }
}
