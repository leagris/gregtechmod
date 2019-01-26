package gregtechmod.common.tileentities.automation;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricAutoWorkbench extends GT_MetaTileEntity_BasicTank {

   public int mMode = 0;
   public int mCurrentSlot = 0;
   public int mThroughPut = 1;
   public int mTicksUntilNextUpdate = 20;
   public boolean mLastCraftSuccessful = true;
   private static final int MAX_MODES = 10;


   public GT_MetaTileEntity_ElectricAutoWorkbench(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_ElectricAutoWorkbench() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isOverclockerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 19;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getBackFacing();
   }

   public boolean isTeleporterCompatible() {
      return false;
   }

   public int getMinimumStoredEU() {
      return 3000;
   }

   public int maxEUInput() {
      return 32;
   }

   public int maxEUOutput() {
      return this.mThroughPut % 2 == 0?32:0;
   }

   public int maxEUStore() {
      return 10000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public int getInvSize() {
      return 30;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 100);
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricAutoWorkbench();
   }

   public void initDefaultModes(NBTTagCompound aNBT) {
      this.mThroughPut = 1;
      this.mMode = 0;
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74768_a("mMode", this.mMode);
      aNBT.func_74768_a("mThroughPut", this.mThroughPut);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mMode = aNBT.func_74762_e("mMode");
      this.mThroughPut = aNBT.func_74762_e("mThroughPut");
   }

   public boolean doesFillContainers() {
      return false;
   }

   public boolean doesEmptyContainers() {
      return false;
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

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getBackFacing();
   }

   public void switchModeForward() {
      this.mMode = (this.mMode + 1) % 10;
      this.switchMode();
   }

   public void switchModeBackward() {
      --this.mMode;
      if(this.mMode < 0) {
         this.mMode = 9;
      }

      this.switchMode();
   }

   private void switchMode() {
      this.mInventory[28] = null;
   }

   public void switchThrough() {
      this.mThroughPut = (this.mThroughPut + 1) % 4;
   }

   public String getDescription() {
      return "Automatic Crafting Table Mk III";
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || --this.mTicksUntilNextUpdate < 1)) {
         this.mTicksUntilNextUpdate = 32;
         byte tRecipe;
         if(this.mThroughPut < 2) {
            for(tRecipe = 0; this.mInventory[18] != null && this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(GT_Utility.moveOneItemStack(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing()), this.getBaseMetaTileEntity().getBackFacing(), this.getBaseMetaTileEntity().getFrontFacing(), (List)null, false, (byte)64, (byte)1, (byte)64, (byte)1) * 10, true) && tRecipe < 64; ++tRecipe) {
               ;
            }
         }

         for(tRecipe = 19; tRecipe < 28; ++tRecipe) {
            if(this.mInventory[tRecipe] != null && this.mInventory[tRecipe].func_77984_f() && GT_Utility.getContainerItem(this.mInventory[tRecipe]) != null) {
               Item.field_77676_L.setDamage(this.mInventory[tRecipe], 32767);
            }
         }

         if(this.mInventory[18] == null) {
            ItemStack tTempStack;
            for(tRecipe = 0; tRecipe < 18 && this.mFluid != null; ++tRecipe) {
               tTempStack = GT_Utility.fillFluidContainer(this.mFluid, this.mInventory[tRecipe]);
               if(tTempStack != null) {
                  for(byte tOutput = 0; tOutput < 9; ++tOutput) {
                     if(this.mInventory[tOutput] == null || GT_Utility.areStacksEqual(tTempStack, this.mInventory[tOutput]) && this.mInventory[tOutput].field_77994_a + tTempStack.field_77994_a <= tTempStack.func_77976_d()) {
                        this.mFluid.amount -= GT_Utility.getFluidForFilledItem(tTempStack).amount * tTempStack.field_77994_a;
                        this.getBaseMetaTileEntity().func_70298_a(tRecipe, 1);
                        if(this.mInventory[tOutput] == null) {
                           this.mInventory[tOutput] = tTempStack;
                        } else {
                           ++this.mInventory[tOutput].field_77994_a;
                        }

                        if(this.mFluid.amount <= 0) {
                           this.mFluid = null;
                        }
                        break;
                     }
                  }
               }
            }

            ItemStack[] var11 = new ItemStack[9];
            tTempStack = null;
            ItemStack var12 = null;
            if(this.mInventory[17] != null && this.mThroughPut < 2 && this.mMode != 0) {
               if(this.mInventory[18] == null) {
                  this.mInventory[18] = this.mInventory[17];
                  this.mInventory[17] = null;
               }
            } else {
               int tList;
               if(!this.mLastCraftSuccessful) {
                  this.mCurrentSlot = (this.mCurrentSlot + 1) % 18;

                  for(tList = 0; tList < 17 && this.mInventory[this.mCurrentSlot] == null; ++tList) {
                     this.mCurrentSlot = (this.mCurrentSlot + 1) % 18;
                  }
               }

               label392:
               switch(this.mMode) {
               case 0:
                  tList = 0;

                  while(true) {
                     if(tList >= 9) {
                        break label392;
                     }

                     var11[tList] = this.mInventory[tList + 19];
                     if(var11[tList] != null) {
                        var11[tList] = GT_Utility.copy(new Object[]{var11[tList]});
                        var11[tList].field_77994_a = 1;
                     }

                     ++tList;
                  }
               case 1:
                  if(this.isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(this.mInventory[this.mCurrentSlot])) {
                     if(this.mInventory[18] == null && this.mThroughPut < 2) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else {
                     tTempStack = GT_Utility.copy(new Object[]{this.mInventory[this.mCurrentSlot]});
                     tTempStack.field_77994_a = 1;
                     var11[0] = tTempStack;
                     if(GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null) {
                        var11[1] = tTempStack;
                        var11[3] = tTempStack;
                        var11[4] = tTempStack;
                        if(GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null) {
                           var11[2] = tTempStack;
                           var11[5] = tTempStack;
                           var11[6] = tTempStack;
                           var11[7] = tTempStack;
                           var11[8] = tTempStack;
                           if(GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null && this.mInventory[18] == null) {
                              this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                              this.mInventory[this.mCurrentSlot] = null;
                              this.mTicksUntilNextUpdate = 1;
                           }
                        }
                     }
                  }
                  break;
               case 2:
                  if(this.isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(this.mInventory[this.mCurrentSlot])) {
                     if(this.mInventory[18] == null && this.mThroughPut < 2) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else {
                     tTempStack = GT_Utility.copy(new Object[]{this.mInventory[this.mCurrentSlot]});
                     tTempStack.field_77994_a = 1;
                     var11[0] = tTempStack;
                     if(GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null && this.mInventory[18] == null) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  }
                  break;
               case 3:
                  if(this.isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(this.mInventory[this.mCurrentSlot])) {
                     if(this.mInventory[18] == null && this.mThroughPut < 2) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else {
                     tTempStack = GT_Utility.copy(new Object[]{this.mInventory[this.mCurrentSlot]});
                     tTempStack.field_77994_a = 1;
                     var11[0] = tTempStack;
                     var11[1] = tTempStack;
                     var11[3] = tTempStack;
                     var11[4] = tTempStack;
                     if(GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null && this.mInventory[18] == null) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  }
                  break;
               case 4:
                  if(this.isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(this.mInventory[this.mCurrentSlot])) {
                     if(this.mInventory[18] == null && this.mThroughPut < 2) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else {
                     tTempStack = GT_Utility.copy(new Object[]{this.mInventory[this.mCurrentSlot]});
                     tTempStack.field_77994_a = 1;
                     var11[0] = tTempStack;
                     var11[1] = tTempStack;
                     var11[2] = tTempStack;
                     var11[3] = tTempStack;
                     var11[4] = tTempStack;
                     var11[5] = tTempStack;
                     var11[6] = tTempStack;
                     var11[7] = tTempStack;
                     var11[8] = tTempStack;
                     if(GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null && this.mInventory[18] == null) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  }
                  break;
               case 5:
                  if(this.isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(this.mInventory[this.mCurrentSlot])) {
                     if(this.mInventory[18] == null && this.mThroughPut < 2) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else {
                     tTempStack = GT_Utility.copy(new Object[]{this.mInventory[this.mCurrentSlot]});
                     tTempStack.field_77994_a = 1;
                     var11[0] = tTempStack;
                     var12 = GT_OreDictUnificator.get(true, tTempStack);
                     if(var12 != null && GT_Utility.areStacksEqual(var12, tTempStack)) {
                        var12 = null;
                     }

                     if(var12 == null) {
                        var11[0] = null;
                        if(this.mInventory[18] == null) {
                           this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                           this.mInventory[this.mCurrentSlot] = null;
                           this.mTicksUntilNextUpdate = 1;
                        }
                     }
                  }
                  break;
               case 6:
                  if(this.isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(this.mInventory[this.mCurrentSlot])) {
                     if(this.mInventory[18] == null && this.mThroughPut < 2) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else if(OrePrefixes.dustSmall.contains(this.mInventory[this.mCurrentSlot])) {
                     tTempStack = GT_Utility.copy(new Object[]{this.mInventory[this.mCurrentSlot]});
                     tTempStack.field_77994_a = 1;
                     var11[0] = tTempStack;
                     var11[1] = tTempStack;
                     var11[3] = tTempStack;
                     var11[4] = tTempStack;
                     if(GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null && this.mInventory[18] == null) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else if(OrePrefixes.dustTiny.contains(this.mInventory[this.mCurrentSlot])) {
                     tTempStack = GT_Utility.copy(new Object[]{this.mInventory[this.mCurrentSlot]});
                     tTempStack.field_77994_a = 1;
                     var11[0] = tTempStack;
                     var11[1] = tTempStack;
                     var11[2] = tTempStack;
                     var11[3] = tTempStack;
                     var11[4] = tTempStack;
                     var11[5] = tTempStack;
                     var11[6] = tTempStack;
                     var11[7] = tTempStack;
                     var11[8] = tTempStack;
                     if(GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null && this.mInventory[18] == null) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else if(this.mInventory[18] == null && this.mThroughPut < 2) {
                     this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                     this.mInventory[this.mCurrentSlot] = null;
                     this.mTicksUntilNextUpdate = 1;
                  }
                  break;
               case 7:
                  if(!this.isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(this.mInventory[this.mCurrentSlot]) && OrePrefixes.nugget.contains(this.mInventory[this.mCurrentSlot])) {
                     tTempStack = GT_Utility.copy(new Object[]{this.mInventory[this.mCurrentSlot]});
                     tTempStack.field_77994_a = 1;
                     var11[0] = tTempStack;
                     var11[1] = tTempStack;
                     var11[2] = tTempStack;
                     var11[3] = tTempStack;
                     var11[4] = tTempStack;
                     var11[5] = tTempStack;
                     var11[6] = tTempStack;
                     var11[7] = tTempStack;
                     var11[8] = tTempStack;
                     if(GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null && this.mInventory[18] == null) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else if(this.mInventory[18] == null && this.mThroughPut < 2) {
                     this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                     this.mInventory[this.mCurrentSlot] = null;
                     this.mTicksUntilNextUpdate = 1;
                  }
                  break;
               case 8:
                  if(!this.isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(this.mInventory[this.mCurrentSlot]) && this.mInventory[this.mCurrentSlot].func_77960_j() > 0 && this.mInventory[this.mCurrentSlot].func_77973_b().isRepairable()) {
                     tTempStack = GT_Utility.copy(new Object[]{this.mInventory[this.mCurrentSlot]});
                     tTempStack.field_77994_a = 1;
                     tList = this.mCurrentSlot + 1;

                     while(true) {
                        if(tList >= 18) {
                           break label392;
                        }

                        if(this.mInventory[tList] != null && this.mInventory[tList].func_77973_b() == tTempStack.func_77973_b() && this.mInventory[this.mCurrentSlot].func_77960_j() + this.mInventory[tList].func_77960_j() > tTempStack.func_77958_k()) {
                           var11[0] = tTempStack;
                           var11[1] = GT_Utility.copy(new Object[]{this.mInventory[tList]});
                           if(GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null && this.mInventory[18] == null) {
                              this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                              this.mInventory[this.mCurrentSlot] = null;
                              this.mTicksUntilNextUpdate = 1;
                           }
                           break label392;
                        }

                        ++tList;
                     }
                  } else {
                     if(this.mInventory[18] == null && this.mThroughPut < 2) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                     break;
                  }
               case 9:
                  if(this.isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(this.mInventory[this.mCurrentSlot])) {
                     if(this.mInventory[18] == null && this.mThroughPut < 2) {
                        this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                        this.mInventory[this.mCurrentSlot] = null;
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else {
                     byte var13 = 0;

                     for(byte tContent = 0; var13 < 18 && tContent < 9 && (tContent < 2 || GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11) == null); ++var13) {
                        var11[tContent] = this.mInventory[(this.mCurrentSlot + var13) % 18];
                        if(var11[tContent] != null) {
                           var11[tContent] = GT_Utility.copy(new Object[]{var11[tContent]});
                           var11[tContent].field_77994_a = 1;
                           ++tContent;
                        }
                     }

                     if(var11[1] == null) {
                        var11[0] = null;
                     }
                  }
               }
            }

            if(var12 == null) {
               var12 = GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), var11);
            }

            if(var12 != null || this.mMode == 0) {
               this.mInventory[28] = var12;
            }

            if(var12 == null) {
               this.mLastCraftSuccessful = false;
            } else {
               if((tTempStack = GT_OreDictUnificator.get(true, var12)) != null) {
                  tTempStack.field_77994_a = var12.field_77994_a;
                  var12 = tTempStack;
               }

               this.mInventory[28] = GT_Utility.copy(new Object[]{var12});
               ArrayList var15 = recipeContent(var11);
               ArrayList var14 = this.benchContent();
               if(this.mInventory[18] == null && var15.size() > 0 && var14.size() > 0) {
                  boolean success = this.mMode == 6 || this.mMode == 7 || this.mInventory[17] == null;

                  byte i;
                  byte j;
                  for(i = 0; i < var15.size() && success; ++i) {
                     success = false;

                     for(j = 0; j < var14.size() && !success; ++j) {
                        if(GT_Utility.areStacksEqual((ItemStack)var15.get(i), (ItemStack)var14.get(j)) && ((ItemStack)var15.get(i)).field_77994_a <= ((ItemStack)var14.get(j)).field_77994_a) {
                           success = true;
                        }
                     }
                  }

                  this.mLastCraftSuccessful = success;
                  if(this.mLastCraftSuccessful) {
                     if(this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(this.mMode != 5 && this.mMode != 6 && this.mMode != 7?2048:128, false)) {
                        label313:
                        for(i = 8; i > -1; --i) {
                           for(j = 17; j > -1; --j) {
                              if(var11[i] != null && this.mInventory[j] != null && GT_Utility.areStacksEqual(var11[i], this.mInventory[j])) {
                                 ItemStack tStack = GT_Utility.getContainerItem(this.mInventory[j]);
                                 if(tStack != null) {
                                    this.getBaseMetaTileEntity().func_70298_a(j, 1);
                                    if(!tStack.func_77984_f() || tStack.func_77960_j() < tStack.func_77958_k()) {
                                       for(byte k = 9; k < 18; ++k) {
                                          if(this.mInventory[k] == null) {
                                             this.mInventory[k] = GT_Utility.copy(new Object[]{tStack});
                                             continue label313;
                                          }

                                          if(GT_Utility.areStacksEqual(this.mInventory[k], tStack) && this.mInventory[k].field_77994_a + tStack.field_77994_a <= tStack.func_77976_d()) {
                                             this.mInventory[k].field_77994_a += tStack.field_77994_a;
                                             continue label313;
                                          }
                                       }
                                    }
                                 } else {
                                    this.getBaseMetaTileEntity().func_70298_a(j, 1);
                                 }
                                 break;
                              }
                           }
                        }

                        this.mInventory[18] = GT_Utility.copy(new Object[]{var12});
                        this.mTicksUntilNextUpdate = 1;
                     }
                  } else if(this.mMode != 0 && this.mInventory[17] != null && this.mInventory[18] == null && this.mThroughPut < 2) {
                     this.mInventory[18] = this.mInventory[17];
                     this.mInventory[17] = null;
                     this.mTicksUntilNextUpdate = 1;
                  }
               }
            }
         }
      }

   }

   private boolean isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(ItemStack aStack) {
      if(aStack == null) {
         return true;
      } else {
         for(byte i = 19; i < 28; ++i) {
            if(this.mInventory[i] != null) {
               if(GT_Utility.areStacksEqual(this.mInventory[i], aStack)) {
                  return true;
               }

               if(GT_Utility.areStacksEqual(GT_Utility.getContainerForFilledItem(this.mInventory[i]), aStack)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private static ArrayList<ItemStack> recipeContent(ItemStack ... tRecipe) {
      ArrayList tList = new ArrayList();

      for(byte i = 0; i < 9; ++i) {
         if(tRecipe[i] != null) {
            boolean temp = false;

            for(byte j = 0; j < tList.size(); ++j) {
               if(GT_Utility.areStacksEqual(tRecipe[i], (ItemStack)tList.get(j))) {
                  ++((ItemStack)tList.get(j)).field_77994_a;
                  temp = true;
                  break;
               }
            }

            if(!temp) {
               tList.add(GT_Utility.copyAmount(1L, new Object[]{tRecipe[i]}));
            }
         }
      }

      return tList;
   }

   private ArrayList<ItemStack> benchContent() {
      ArrayList tList = new ArrayList();

      for(byte i = 0; i < 18; ++i) {
         if(this.mInventory[i] != null) {
            boolean temp = false;

            for(byte j = 0; j < tList.size(); ++j) {
               if(GT_Utility.areStacksEqual(this.mInventory[i], this.mInventory[j])) {
                  ItemStack var10000 = (ItemStack)tList.get(j);
                  var10000.field_77994_a += this.mInventory[i].field_77994_a;
                  temp = true;
                  break;
               }
            }

            if(!temp) {
               tList.add(GT_Utility.copy(new Object[]{this.mInventory[i]}));
            }
         }
      }

      return tList;
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return this.mMode == 0?aIndex >= 10:aIndex >= 18;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      boolean var10000;
      if(aIndex != 9) {
         label31: {
            if(this.mMode == 0) {
               if(aIndex >= 9 || !GT_Utility.areStacksEqual(aStack, this.mInventory[aIndex + 19])) {
                  break label31;
               }
            } else if(aIndex >= 18 || this.isItemTypeOrItsEmptyLiquidContainerInCraftingGrid(aStack)) {
               break label31;
            }

            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return GT_Utility.getOppositeSide(aSide) == aFacing?113:114;
   }

   public int getCapacity() {
      return 16000;
   }

   public int getTankPressure() {
      return -100;
   }
}
