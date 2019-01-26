package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_PulverizerRecipe;
import gregtechmod.api.util.GT_Utility;
import java.util.Iterator;
import java.util.Random;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Pulverizer extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Pulverizer(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Pulverizer() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 149);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Pulverizer();
   }

   public void onPreTick() {
      super.onPreTick();
      if(this.getBaseMetaTileEntity().isClientSide() && this.getBaseMetaTileEntity().isActive() && this.getBaseMetaTileEntity().getFrontFacing() != 1 && this.getBaseMetaTileEntity().getCoverIDAtSide((byte)1) == 0 && !this.getBaseMetaTileEntity().getOpacityAtSide((byte)1)) {
         Random tRandom = this.getBaseMetaTileEntity().getWorld().field_73012_v;
         this.getBaseMetaTileEntity().getWorld().func_72869_a("smoke", (double)((float)this.getBaseMetaTileEntity().getXCoord() + 0.8F - tRandom.nextFloat() * 0.6F), (double)((float)this.getBaseMetaTileEntity().getYCoord() + 0.9F + tRandom.nextFloat() * 0.2F), (double)((float)this.getBaseMetaTileEntity().getZCoord() + 0.8F - tRandom.nextFloat() * 0.6F), 0.0D, 0.0D, 0.0D);
      }

   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[2] != null) {
         if(this.mInventory[3] == null && this.mInventory[4] == null) {
            if(!GT_Utility.areStacksEqual(this.mInventory[2], new ItemStack(Block.field_71940_F, 1), true)) {
               Object e = GT_ModHandler.getPulverizerRecipe(this.mInventory[2]);

               try {
                  if(e instanceof GT_PulverizerRecipe) {
                     GT_PulverizerRecipe tOutput = (GT_PulverizerRecipe)e;
                     if(tOutput.getInput().field_77994_a <= this.mInventory[2].field_77994_a && (this.mOutputItem1 = tOutput.getPrimaryOutput()) != null) {
                        if(this.getBaseMetaTileEntity().getRandomNumber(100) < tOutput.getSecondaryOutputChance()) {
                           this.mOutputItem2 = tOutput.getSecondaryOutput();
                        }

                        this.mInventory[2].field_77994_a -= tOutput.getInput().field_77994_a;
                        this.mMaxProgresstime = 300 * tOutput.getInput().field_77994_a;
                        this.mEUt = 3;
                        return;
                     }
                  }
               } catch (Throwable var4) {
                  if(GregTech_API.DEBUG_MODE) {
                     var4.printStackTrace(GT_Log.err);
                  }
               }
            }

            try {
               Iterator e1 = RailcraftCraftingManager.rockCrusher.getRecipe(this.mInventory[2]).getRandomizedOuputs().iterator();

               while(e1.hasNext()) {
                  ItemStack tOutput1 = (ItemStack)e1.next();
                  tOutput1 = GT_OreDictUnificator.get(tOutput1);
                  if(tOutput1 != null) {
                     if(this.mOutputItem1 == null) {
                        this.mOutputItem1 = GT_Utility.copy(new Object[]{tOutput1});
                     } else if(GT_Utility.areStacksEqual(this.mOutputItem1, tOutput1)) {
                        this.mOutputItem1.field_77994_a += tOutput1.field_77994_a;
                     } else if(this.mOutputItem2 == null) {
                        this.mOutputItem2 = GT_Utility.copy(new Object[]{tOutput1});
                     } else if(GT_Utility.areStacksEqual(this.mOutputItem2, tOutput1)) {
                        this.mOutputItem2.field_77994_a += tOutput1.field_77994_a;
                     }
                  }
               }

               if(this.mOutputItem1 != null) {
                  --this.mInventory[2].field_77994_a;
                  this.mMaxProgresstime = 300;
                  this.mEUt = 4;
                  return;
               }
            } catch (Throwable var3) {
               if(GregTech_API.DEBUG_MODE) {
                  var3.printStackTrace(GT_Log.err);
               }
            }

            if(null != (this.mOutputItem1 = GT_ModHandler.getMaceratorOutput(this.mInventory[2], true, this.mInventory[3]))) {
               this.mOutputItem2 = null;
               this.mMaxProgresstime = 400;
               this.mEUt = 2;
               return;
            }
         } else {
            this.bOutputBlocked = true;
         }
      }

   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(201)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public int getFrontFacingInactive() {
      return 256;
   }

   public int getFrontFacingActive() {
      return 257;
   }

   public int getTopFacingInactive() {
      return 228;
   }

   public int getTopFacingActive() {
      return 229;
   }

   public String getDescription() {
      return "Macerator + Pulverizer + Rock Crusher in one Machine";
   }
}
