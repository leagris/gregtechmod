package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Printer extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Printer(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Printer() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 142);
   }

   public int dechargerSlotStartIndex() {
      return 0;
   }

   public int dechargerSlotCount() {
      return 0;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Printer();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[3] != null) {
         this.bOutputBlocked = true;
      } else if(GT_Utility.isStackValid(this.mInventory[1]) && this.mInventory[1].field_77994_a > 0) {
         if(GT_Utility.isStackInvalid(this.mInventory[5])) {
            if(GT_Utility.areStacksEqual(this.mInventory[1], new ItemStack(Item.field_77758_aJ, 1, 0))) {
               this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.plate, (Object)Materials.Paper, 1L);
               this.mEUt = 1;
               this.mMaxProgresstime = 200;
               --this.mInventory[1].field_77994_a;
               return;
            }

            if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "dustWood")) {
               this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.plate, (Object)Materials.Paper, 1L);
               this.mEUt = 1;
               this.mMaxProgresstime = 200;
               --this.mInventory[1].field_77994_a;
               return;
            }

            if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "dustPaper")) {
               this.mOutputItem1 = GT_OreDictUnificator.get(OrePrefixes.plate, (Object)Materials.Paper, 1L);
               this.mEUt = 1;
               this.mMaxProgresstime = 100;
               --this.mInventory[1].field_77994_a;
               return;
            }

            if(OrePrefixes.block.contains(this.mInventory[1])) {
               ArrayList tNBT = GT_OreDictUnificator.getOres(GT_OreDictUnificator.getAssociation(this.mInventory[1]));
               if(tNBT.size() > 1) {
                  tNBT.add(tNBT.get(0));
                  int tName = 0;

                  for(int j = tNBT.size() - 1; tName < j; ++tName) {
                     if(GT_Utility.areStacksEqual(this.mInventory[1], (ItemStack)tNBT.get(tName))) {
                        this.mOutputItem1 = GT_Utility.copyAmount(1L, new Object[]{tNBT.get(tName + 1)});
                        this.mEUt = 1;
                        this.mMaxProgresstime = 20;
                        --this.mInventory[1].field_77994_a;
                        return;
                     }
                  }
               }
            }

            if(GT_Utility.isStackValid(this.mInventory[2]) && this.mInventory[2].field_77994_a > 0) {
               if(this.mInventory[1].func_77973_b() == Item.field_77770_aF && this.mInventory[2].func_77973_b() == Item.field_111214_ch) {
                  this.mOutputItem1 = new ItemStack(Item.field_111212_ci, 1, 0);
                  this.mEUt = 2;
                  this.mMaxProgresstime = 400;
                  --this.mInventory[1].field_77994_a;
                  --this.mInventory[2].field_77994_a;
                  return;
               }

               if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "platePaper")) {
                  if(this.mInventory[1].field_77994_a >= 8 && this.mInventory[2].func_77973_b() == Item.field_77750_aQ) {
                     this.mOutputItem1 = new ItemStack(Item.field_82801_bO, 1, 0);
                     this.mEUt = 2;
                     this.mMaxProgresstime = 400;
                     this.mInventory[1].field_77994_a -= 8;
                     --this.mInventory[2].field_77994_a;
                     return;
                  }

                  if(this.mInventory[1].field_77994_a >= 3 && this.mInventory[2].func_77973_b() == Item.field_77770_aF) {
                     this.mOutputItem1 = new ItemStack(Item.field_77760_aL, 1, 0);
                     this.mEUt = 2;
                     this.mMaxProgresstime = 400;
                     this.mInventory[1].field_77994_a -= 3;
                     --this.mInventory[2].field_77994_a;
                     return;
                  }
               }

               if(GT_OreDictUnificator.isItemStackDye(this.mInventory[2])) {
                  this.mOutputItem1 = GT_ModHandler.getRecipeOutput(new ItemStack[]{this.mInventory[1], this.mInventory[1], this.mInventory[1], this.mInventory[1], this.mInventory[2], this.mInventory[1], this.mInventory[1], this.mInventory[1], this.mInventory[1]});
                  if(this.mOutputItem1 != null && this.mInventory[1].field_77994_a > 7) {
                     this.mEUt = 2;
                     this.mMaxProgresstime = 1600;
                     this.mInventory[1].field_77994_a -= 8;
                     if(GT_Utility.getContainerItem(this.mInventory[2]) != null) {
                        if(this.mInventory[2].field_77994_a == 1) {
                           this.mInventory[2] = GT_Utility.copy(new Object[]{GT_Utility.getContainerItem(this.mInventory[2])});
                           if(!GT_OreDictUnificator.isItemStackDye(this.mInventory[2])) {
                              GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 2, 4, (byte)64, (byte)1, (byte)64, (byte)1);
                              GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 2, 3, (byte)64, (byte)1, (byte)64, (byte)1);
                           }
                        } else {
                           this.mOutputItem2 = GT_Utility.copy(new Object[]{GT_Utility.getContainerItem(GT_Utility.copyAmount(1L, new Object[]{this.mInventory[2]}))});
                           --this.mInventory[2].field_77994_a;
                        }
                     } else {
                        --this.mInventory[2].field_77994_a;
                     }

                     return;
                  }

                  if(this.mOutputItem1 == null) {
                     this.mOutputItem1 = GT_ModHandler.getRecipeOutput(new ItemStack[]{this.mInventory[1], this.mInventory[2]});
                     if(this.mOutputItem1 != null) {
                        this.mEUt = 2;
                        this.mMaxProgresstime = 200;
                        --this.mInventory[1].field_77994_a;
                        if(GT_Utility.getContainerItem(this.mInventory[2]) != null) {
                           if(this.mInventory[2].field_77994_a == 1) {
                              this.mInventory[2] = GT_Utility.copy(new Object[]{GT_Utility.getContainerItem(this.mInventory[2])});
                              if(!GT_OreDictUnificator.isItemStackDye(this.mInventory[2])) {
                                 GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 2, 4, (byte)64, (byte)1, (byte)64, (byte)1);
                                 GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 2, 3, (byte)64, (byte)1, (byte)64, (byte)1);
                              }
                           } else {
                              this.mOutputItem2 = GT_Utility.copy(new Object[]{GT_Utility.getContainerItem(GT_Utility.copyAmount(1L, new Object[]{this.mInventory[2]}))});
                              --this.mInventory[2].field_77994_a;
                           }
                        } else {
                           --this.mInventory[2].field_77994_a;
                        }

                        return;
                     }
                  }
               }
            }
         } else {
            NBTTagCompound var4;
            if(GT_Items.Shape_Mold_Credit.isStackEqual(this.mInventory[5], false, true)) {
               var4 = this.mInventory[5].func_77978_p();
               if(var4 == null) {
                  var4 = new NBTTagCompound();
               }

               if(!var4.func_74764_b("credit_security_id")) {
                  var4.func_74772_a("credit_security_id", System.nanoTime());
               }

               this.mInventory[5].func_77982_d(var4);
               this.mEUt = 8;
               this.mMaxProgresstime = 0;
               if(GT_Items.Credit_Copper.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 4;
               }

               if(GT_Items.Credit_Iron.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 32;
               }

               if(GT_Items.Credit_Silver.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 256;
               }

               if(GT_Items.Credit_Gold.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 2048;
               }

               if(GT_Items.Credit_Platinum.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 16384;
               }

               if(GT_Items.Credit_Osmium.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 131072;
               }

               if(GT_Items.Shape_Mold_Credit.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 1048576;
               }

               if(GT_Items.Credit_Greg_Copper.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 32;
               }

               if(GT_Items.Credit_Greg_Cupronickel.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 256;
               }

               if(GT_Items.Credit_Greg_Silver.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 2048;
               }

               if(GT_Items.Credit_Greg_Gold.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 16384;
               }

               if(GT_Items.Credit_Greg_Platinum.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 131072;
               }

               if(GT_Items.Credit_Greg_Osmium.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 1048576;
               }

               if(GT_Items.Credit_Greg_Naquadah.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 8388608;
               }

               if(GT_Items.Credit_Greg_Neutronium.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 67108864;
               }

               if(GT_Items.Coin_Doge.isStackEqual(this.mInventory[1], false, true)) {
                  this.mMaxProgresstime = 256;
               }

               if(this.mMaxProgresstime > 0) {
                  this.mOutputItem1 = GT_Utility.copyAmount(1L, new Object[]{this.mInventory[1]});
                  this.mOutputItem1.func_77982_d(var4);
                  --this.mInventory[1].field_77994_a;
               }

               return;
            }

            if(GT_Utility.isStackValid(this.mInventory[2]) && this.mInventory[2].field_77994_a > 0) {
               if((this.mInventory[5].func_77973_b() == Item.field_77821_bF || this.mInventory[5].func_77973_b() == Item.field_77823_bG) && GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "bookEmpty") && GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], "dyeBlack")) {
                  this.mEUt = 1;
                  this.mMaxProgresstime = 200;
                  this.mOutputItem1 = GT_Utility.copyAmount(1L, new Object[]{this.mInventory[5]});
                  --this.mInventory[1].field_77994_a;
                  if(GT_Utility.getContainerItem(this.mInventory[2]) != null) {
                     if(this.mInventory[2].field_77994_a == 1) {
                        this.mInventory[2] = GT_Utility.copy(new Object[]{GT_Utility.getContainerItem(this.mInventory[2])});
                        if(!GT_OreDictUnificator.isItemStackDye(this.mInventory[2])) {
                           GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 2, 4, (byte)64, (byte)1, (byte)64, (byte)1);
                           GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 2, 3, (byte)64, (byte)1, (byte)64, (byte)1);
                        }
                     } else {
                        this.mOutputItem2 = GT_Utility.copy(new Object[]{GT_Utility.getContainerItem(GT_Utility.copyAmount(1L, new Object[]{this.mInventory[2]}))});
                        --this.mInventory[2].field_77994_a;
                     }
                  } else {
                     --this.mInventory[2].field_77994_a;
                  }

                  return;
               }

               if(GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[5], "platePaper") && GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], "dyeBlack")) {
                  var4 = this.mInventory[5].func_77978_p();
                  if(var4 != null && var4.func_74764_b("display")) {
                     String var5 = var4.func_74775_l("display").func_74779_i("Name");
                     if(GT_Utility.isStringValid(var5)) {
                        this.mEUt = 4;
                        this.mMaxProgresstime = 100;
                        this.mOutputItem1 = GT_Utility.copyAmount(1L, new Object[]{this.mInventory[1]});
                        this.mOutputItem1.func_82834_c(var5);
                        --this.mInventory[1].field_77994_a;
                        if(GT_Utility.getContainerItem(this.mInventory[2]) != null) {
                           if(this.mInventory[2].field_77994_a == 1) {
                              this.mInventory[2] = GT_Utility.copy(new Object[]{GT_Utility.getContainerItem(this.mInventory[2])});
                              if(!GT_OreDictUnificator.isItemStackDye(this.mInventory[2])) {
                                 GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 2, 4, (byte)64, (byte)1, (byte)64, (byte)1);
                                 GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 2, 3, (byte)64, (byte)1, (byte)64, (byte)1);
                              }
                           } else {
                              this.mOutputItem2 = GT_Utility.copy(new Object[]{GT_Utility.getContainerItem(GT_Utility.copyAmount(1L, new Object[]{this.mInventory[2]}))});
                              --this.mInventory[2].field_77994_a;
                           }
                        } else {
                           --this.mInventory[2].field_77994_a;
                        }
                     }
                  }

                  return;
               }

               if(this.mInventory[5].func_77973_b() == Item.field_77744_bd && GT_Utility.areStacksEqual(this.mInventory[1], new ItemStack(Item.field_82801_bO, 1, 0)) && GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], "dyeBlack")) {
                  this.mEUt = 1;
                  this.mMaxProgresstime = 100;
                  this.mOutputItem1 = GT_Utility.copyAmount(1L, new Object[]{this.mInventory[5]});
                  --this.mInventory[1].field_77994_a;
                  if(GT_Utility.getContainerItem(this.mInventory[2]) != null) {
                     if(this.mInventory[2].field_77994_a == 1) {
                        this.mInventory[2] = GT_Utility.copy(new Object[]{GT_Utility.getContainerItem(this.mInventory[2])});
                        if(!GT_OreDictUnificator.isItemStackDye(this.mInventory[2])) {
                           GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 2, 4, (byte)64, (byte)1, (byte)64, (byte)1);
                           GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 2, 3, (byte)64, (byte)1, (byte)64, (byte)1);
                        }
                     } else {
                        this.mOutputItem2 = GT_Utility.copy(new Object[]{GT_Utility.getContainerItem(GT_Utility.copyAmount(1L, new Object[]{this.mInventory[2]}))});
                        --this.mInventory[2].field_77994_a;
                     }
                  } else {
                     --this.mInventory[2].field_77994_a;
                  }

                  return;
               }
            }
         }
      }

      this.mMaxProgresstime = 0;
   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(203)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public boolean hasTwoSeperateInputs() {
      return true;
   }

   public int getFrontFacingInactive() {
      return 33;
   }

   public int getFrontFacingActive() {
      return 34;
   }

   public String getDescription() {
      return "It can copy Books and paint Stuff";
   }
}
