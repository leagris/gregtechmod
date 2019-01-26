package gregtechmod.common.tileentities.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class GT_MetaTileEntity_AdvancedCraftingTable extends GT_MetaTileEntity_BasicTank {

   public boolean mFlushMode = false;


   public GT_MetaTileEntity_AdvancedCraftingTable(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_AdvancedCraftingTable() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 31 || aIndex > 32;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isInputFacing(byte aSide) {
      return true;
   }

   public int maxEUInput() {
      return 128;
   }

   public int maxEUStore() {
      return 1000;
   }

   public int getInvSize() {
      return 35;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_AdvancedCraftingTable();
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 160);
   }

   public void onFirstTick() {
      this.getCraftingOutput();
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

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.getBaseMetaTileEntity().hasInventoryBeenModified()) {
            this.getCraftingOutput();
         }

         this.fillLiquidContainers();
         if(this.mFlushMode) {
            this.mFlushMode = false;

            for(byte i = 21; i < 30; ++i) {
               if(this.mInventory[i] != null) {
                  if(this.mInventory[i].field_77994_a != 0) {
                     this.mFlushMode = true;
                     break;
                  }

                  this.mInventory[i] = null;
               }
            }
         }
      }

   }

   public void sortIntoTheInputSlots() {
      for(byte i = 21; i < 30; ++i) {
         if(this.mInventory[i] != null) {
            if(this.mInventory[i].field_77994_a == 0) {
               this.mInventory[i] = null;
            }

            byte j;
            if(this.mInventory[i] != null) {
               for(j = 0; j < 16; ++j) {
                  if(GT_Utility.areStacksEqual(this.mInventory[i], this.mInventory[j])) {
                     GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), i, j, (byte)64, (byte)1, (byte)64, (byte)1);
                  }
               }
            }

            if(this.mInventory[i] != null) {
               for(j = 0; j < 16; ++j) {
                  if(this.mInventory[j] == null) {
                     GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), i, j, (byte)64, (byte)1, (byte)64, (byte)1);
                  }
               }
            }
         }
      }

   }

   private void fillLiquidContainers() {
      for(byte i = 16; i < 21 && this.mFluid != null; ++i) {
         ItemStack tOutput = GT_Utility.fillFluidContainer(this.mFluid, this.mInventory[i]);
         if(tOutput != null) {
            if(this.mInventory[i].field_77994_a == 1) {
               this.mFluid.amount -= GT_Utility.getFluidForFilledItem(tOutput).amount * tOutput.field_77994_a;
               this.mInventory[i] = tOutput;
            } else {
               for(byte j = 16; j < 21; ++j) {
                  if(this.mInventory[j] == null || GT_Utility.areStacksEqual(tOutput, this.mInventory[j]) && this.mInventory[j].field_77994_a + tOutput.field_77994_a <= tOutput.func_77976_d()) {
                     this.mFluid.amount -= GT_Utility.getFluidForFilledItem(tOutput).amount * tOutput.field_77994_a;
                     this.getBaseMetaTileEntity().func_70298_a(i, 1);
                     if(this.mInventory[j] == null) {
                        this.mInventory[j] = tOutput;
                     } else {
                        ++this.mInventory[j].field_77994_a;
                     }
                     break;
                  }
               }
            }

            if(this.mFluid != null && this.mFluid.amount <= 0) {
               this.mFluid = null;
            }
         }
      }

      if(this.mFluid != null && this.mFluid.amount <= 0) {
         this.mFluid = null;
      }

   }

   public void setBluePrint(ItemStack aStack) {
      if(aStack == null) {
         aStack = this.mInventory[30];
      }

      if(this.mInventory[31] != null && aStack != null && aStack.field_77993_c == -2 && aStack.func_77960_j() == 0 && aStack.field_77994_a == 1 && !aStack.func_77942_o()) {
         NBTTagCompound tNBT = new NBTTagCompound();
         NBTTagList tNBT_ItemList = new NBTTagList();

         for(int i = 0; i < 9; ++i) {
            ItemStack tStack = this.mInventory[i + 21];
            if(tStack != null) {
               NBTTagCompound tag = new NBTTagCompound();
               tag.func_74774_a("Slot", (byte)i);
               tStack.func_77955_b(tag);
               tNBT_ItemList.func_74742_a(tag);
            }
         }

         tNBT.func_74782_a("Inventory", tNBT_ItemList);
         aStack.func_77982_d(tNBT);
      }
   }

   public ItemStack getCraftingOutput() {
      if(this.mInventory[30] != null && this.mInventory[30].field_77993_c == -2 && this.mInventory[30].func_77960_j() == 0 && this.mInventory[30].func_77942_o()) {
         NBTTagCompound tNBT = this.mInventory[30].func_77978_p();
         NBTTagList tNBT_ItemList = tNBT.func_74761_m("Blueprint");

         for(int i = 0; i < tNBT_ItemList.func_74745_c() && i < 9; ++i) {
            NBTTagCompound tag = (NBTTagCompound)tNBT_ItemList.func_74743_b(i);
            byte slot = tag.func_74771_c("Slot");
            if(slot >= 0 && slot < 9 && this.mInventory[slot + 21] == null) {
               this.mInventory[slot + 21] = GT_Utility.loadItem(tag);
               if(this.mInventory[slot + 21] != null) {
                  this.mInventory[slot + 21].field_77994_a = 0;
               }
            }
         }
      }

      this.mInventory[31] = GT_ModHandler.getAllRecipeOutput(this.getBaseMetaTileEntity().getWorld(), new ItemStack[]{this.mInventory[21], this.mInventory[22], this.mInventory[23], this.mInventory[24], this.mInventory[25], this.mInventory[26], this.mInventory[27], this.mInventory[28], this.mInventory[29]});
      return this.mInventory[31];
   }

   public boolean canDoCraftingOutput() {
      if(this.mInventory[31] == null) {
         return false;
      } else {
         Iterator i$ = this.recipeContent().iterator();

         ItemStack tStack;
         do {
            if(!i$.hasNext()) {
               return true;
            }

            tStack = (ItemStack)i$.next();
         } while(tStack.field_77994_a <= this.getAmountOf(tStack));

         return false;
      }
   }

   private int getAmountOf(ItemStack aStack) {
      int tAmount = 0;

      for(byte i = 0; i < 30 && tAmount < 9; ++i) {
         if(GT_Utility.areStacksOrToolsEqual(aStack, this.mInventory[i])) {
            tAmount += this.mInventory[i].field_77994_a;
         }
      }

      return tAmount;
   }

   private ArrayList<ItemStack> recipeContent() {
      ArrayList tList = new ArrayList();

      for(byte i = 21; i < 30; ++i) {
         if(this.mInventory[i] != null) {
            boolean temp = false;

            for(byte j = 0; j < tList.size(); ++j) {
               if(GT_Utility.areStacksOrToolsEqual(this.mInventory[i], (ItemStack)tList.get(j))) {
                  ++((ItemStack)tList.get(j)).field_77994_a;
                  temp = true;
                  break;
               }
            }

            if(!temp) {
               tList.add(GT_Utility.copyAmount(1L, new Object[]{this.mInventory[i]}));
            }
         }
      }

      return tList;
   }

   public ItemStack consumeMaterials(EntityPlayer aPlayer, ItemStack aHoldStack) {
      if(this.mInventory[31] == null) {
         return aHoldStack;
      } else {
         if(aHoldStack != null) {
            if(!GT_Utility.areStacksEqual(aHoldStack, this.mInventory[31])) {
               return aHoldStack;
            }

            if(aHoldStack.field_77994_a + this.mInventory[31].field_77994_a > aHoldStack.func_77976_d()) {
               return aHoldStack;
            }
         }

         label84:
         for(byte i = 21; i < 30; ++i) {
            if(this.mInventory[i] != null) {
               for(byte j = 0; j <= i; ++j) {
                  if((j < 21 || j == i) && GT_Utility.areStacksOrToolsEqual(this.mInventory[i], this.mInventory[j]) && this.mInventory[j].field_77994_a > 0) {
                     ItemStack tStack = GT_Utility.getContainerItem(this.mInventory[j]);
                     if(tStack != null && (!tStack.func_77984_f() || tStack.func_77960_j() < tStack.func_77958_k())) {
                        if(this.mInventory[j].field_77994_a == 1) {
                           this.mInventory[j] = tStack;
                           break;
                        }

                        this.getBaseMetaTileEntity().func_70298_a(j, 1);
                        byte k = 0;

                        while(true) {
                           if(k >= 21) {
                              continue label84;
                           }

                           if(this.mInventory[k] == null) {
                              this.mInventory[k] = tStack;
                              continue label84;
                           }

                           if(GT_Utility.areStacksEqual(tStack, this.mInventory[k]) && tStack.field_77994_a + this.mInventory[k].field_77994_a <= this.mInventory[k].func_77976_d()) {
                              this.mInventory[k].field_77994_a += tStack.field_77994_a;
                              continue label84;
                           }

                           ++k;
                        }
                     }

                     this.getBaseMetaTileEntity().func_70298_a(j, 1);
                     break;
                  }
               }
            }
         }

         if(aHoldStack == null) {
            aHoldStack = GT_Utility.copy(new Object[]{this.mInventory[31]});
            aHoldStack.func_77980_a(this.getBaseMetaTileEntity().getWorld(), aPlayer, this.mInventory[31].field_77994_a);
         } else {
            aHoldStack.field_77994_a += this.mInventory[31].field_77994_a;
            aHoldStack.func_77980_a(this.getBaseMetaTileEntity().getWorld(), aPlayer, this.mInventory[31].field_77994_a);
         }

         this.fillLiquidContainers();
         return aHoldStack;
      }
   }

   public int getInputTier() {
      return GT_Utility.getTier(this.getBaseMetaTileEntity().getInputVoltage());
   }

   public int getOutputTier() {
      return GT_Utility.getTier(this.getBaseMetaTileEntity().getInputVoltage());
   }

   public int rechargerSlotStartIndex() {
      return 16;
   }

   public int rechargerSlotCount() {
      return 5;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?32:(aSide == 1?290:(aFacing != 0 && aFacing != 1?(aFacing != 2 && aFacing != 3?215:223):222));
   }

   public String getDescription() {
      return "For the very large Projects";
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      if(aIndex < 16) {
         for(byte i = 0; i < 16; ++i) {
            if(GT_Utility.areStacksOrToolsEqual(aStack, this.mInventory[i])) {
               return aIndex == i;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 33 || this.mFlushMode && aIndex >= 21 && aIndex < 30;
   }

   public int getCapacity() {
      return '\ufa00';
   }

   public int getTankPressure() {
      return -100;
   }
}
