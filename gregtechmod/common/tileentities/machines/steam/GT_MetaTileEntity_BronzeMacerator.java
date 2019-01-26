package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_PulverizerRecipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeMacerator extends GT_MetaTileEntity_BasicMachine_Bronze {

   public GT_MetaTileEntity_BronzeMacerator(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BronzeMacerator() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 164);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_BronzeMacerator();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[2] != null) {
         if(!OrePrefixes.ore.contains(this.mInventory[2]) && !OrePrefixes.oreDense.contains(this.mInventory[2]) && !OrePrefixes.oreEnd.contains(this.mInventory[2]) && !OrePrefixes.oreNether.contains(this.mInventory[2])) {
            if(null != (this.mOutputItem1 = GT_ModHandler.getMaceratorOutput(this.mInventory[2], true, this.mInventory[3]))) {
               if(OrePrefixes.ingot.contains(this.mInventory[2])) {
                  this.mEUt = 6;
                  this.mMaxProgresstime = 200;
               } else if(OrePrefixes.gem.contains(this.mInventory[2])) {
                  this.mEUt = 8;
                  this.mMaxProgresstime = 400;
               } else {
                  this.mEUt = 6;
                  this.mMaxProgresstime = 300;
               }
            }
         } else {
            Object tObject = GT_ModHandler.getPulverizerRecipe(this.mInventory[2]);
            if(tObject instanceof GT_PulverizerRecipe) {
               GT_PulverizerRecipe tRecipe = (GT_PulverizerRecipe)tObject;
               if(tRecipe.getInput().field_77994_a <= this.mInventory[2].field_77994_a && (this.mOutputItem1 = tRecipe.getPrimaryOutput()) != null) {
                  if(this.mOutputItem1.field_77994_a > 1) {
                     this.mOutputItem1.field_77994_a /= 2;
                  }

                  if(this.getBaseMetaTileEntity().getRandomNumber(200) < tRecipe.getSecondaryOutputChance()) {
                     this.mOutputItem2 = tRecipe.getSecondaryOutput();
                  }

                  if(this.spaceForOutput(this.mOutputItem1, tRecipe.getSecondaryOutput())) {
                     this.mInventory[2].field_77994_a -= tRecipe.getInput().field_77994_a;
                     this.mMaxProgresstime = 400 * tRecipe.getInput().field_77994_a;
                     this.mEUt = 10;
                  }
               }
            }
         }
      }

   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(201)), 10, 1.0F, aX, aY, aZ);
   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public int getFrontFacingInactive() {
      return 326;
   }

   public int getFrontFacingActive() {
      return 327;
   }

   public int getTopFacingInactive() {
      return 328;
   }

   public int getTopFacingActive() {
      return 329;
   }

   public String getDescription() {
      return "Grinding up Ores and also gives byproducts";
   }
}
