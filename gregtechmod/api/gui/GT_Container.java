package gregtechmod.api.gui;

import gregtechmod.api.gui.GT_Slot_Armor;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container extends Container {

   public IGregTechTileEntity mTileEntity;
   public InventoryPlayer mPlayerInventory;


   public GT_Container(InventoryPlayer aPlayerInventory, IGregTechTileEntity aTileEntityInventory) {
      this.mTileEntity = aTileEntityInventory;
      this.mPlayerInventory = aPlayerInventory;
   }

   public void addSlots(InventoryPlayer aPlayerInventory) {}

   public int getSlotCount() {
      return 0;
   }

   protected final int getAllSlotCount() {
      return this.field_75151_b != null?(this.doesBindPlayerInventory()?this.field_75151_b.size() - 36:this.field_75151_b.size()):this.getSlotCount();
   }

   public int getSlotStartIndex() {
      return 0;
   }

   public int getShiftClickSlotCount() {
      return 0;
   }

   public boolean doesBindPlayerInventory() {
      return true;
   }

   public boolean func_75145_c(EntityPlayer aPlayer) {
      return false;
   }

   protected void bindPlayerInventory(InventoryPlayer aInventoryPlayer) {
      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.func_75146_a(new Slot(aInventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.func_75146_a(new Slot(aInventoryPlayer, i, 8 + i * 18, 142));
      }

   }

   public ItemStack func_75144_a(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
      if(aSlotIndex >= 0) {
         if(this.field_75151_b.get(aSlotIndex) == null || this.field_75151_b.get(aSlotIndex) instanceof GT_Slot_Holo) {
            return null;
         }

         if(!(this.field_75151_b.get(aSlotIndex) instanceof GT_Slot_Armor) && aSlotIndex < this.getAllSlotCount() && (aSlotIndex < this.getSlotStartIndex() || aSlotIndex >= this.getSlotStartIndex() + this.getSlotCount())) {
            return null;
         }
      }

      try {
         return super.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
      } catch (Throwable var12) {
         var12.printStackTrace(GT_Log.err);
         ItemStack rStack = null;
         InventoryPlayer aPlayerInventory = aPlayer.field_71071_by;
         Slot aSlot;
         ItemStack tTempStack;
         int tTempStackSize;
         ItemStack aHoldStack;
         if((aShifthold == 0 || aShifthold == 1) && (aMouseclick == 0 || aMouseclick == 1)) {
            if(aSlotIndex == -999) {
               if(aPlayerInventory.func_70445_o() != null && aSlotIndex == -999) {
                  if(aMouseclick == 0) {
                     aPlayer.func_71021_b(aPlayerInventory.func_70445_o());
                     aPlayerInventory.func_70437_b((ItemStack)null);
                  }

                  if(aMouseclick == 1) {
                     aPlayer.func_71021_b(aPlayerInventory.func_70445_o().func_77979_a(1));
                     if(aPlayerInventory.func_70445_o().field_77994_a == 0) {
                        aPlayerInventory.func_70437_b((ItemStack)null);
                     }
                  }
               }
            } else if(aShifthold == 1) {
               aSlot = (Slot)this.field_75151_b.get(aSlotIndex);
               if(aSlot != null && aSlot.func_82869_a(aPlayer)) {
                  tTempStack = this.func_82846_b(aPlayer, aSlotIndex);
                  if(tTempStack != null) {
                     rStack = GT_Utility.copy(new Object[]{tTempStack});
                     if(aSlot.func_75211_c() != null && aSlot.func_75211_c().func_77973_b() == tTempStack.func_77973_b()) {
                        this.func_75144_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
                     }
                  }
               }
            } else {
               if(aSlotIndex < 0) {
                  return null;
               }

               aSlot = (Slot)this.field_75151_b.get(aSlotIndex);
               if(aSlot != null) {
                  tTempStack = aSlot.func_75211_c();
                  ItemStack var91 = aPlayerInventory.func_70445_o();
                  if(tTempStack != null) {
                     rStack = GT_Utility.copy(new Object[]{tTempStack});
                  }

                  if(tTempStack == null) {
                     if(var91 != null && aSlot.func_75214_a(var91)) {
                        tTempStackSize = aMouseclick == 0?var91.field_77994_a:1;
                        if(tTempStackSize > aSlot.func_75219_a()) {
                           tTempStackSize = aSlot.func_75219_a();
                        }

                        aSlot.func_75215_d(var91.func_77979_a(tTempStackSize));
                        if(var91.field_77994_a == 0) {
                           aPlayerInventory.func_70437_b((ItemStack)null);
                        }
                     }
                  } else if(aSlot.func_82869_a(aPlayer)) {
                     if(var91 == null) {
                        tTempStackSize = aMouseclick == 0?tTempStack.field_77994_a:(tTempStack.field_77994_a + 1) / 2;
                        aHoldStack = aSlot.func_75209_a(tTempStackSize);
                        aPlayerInventory.func_70437_b(aHoldStack);
                        if(tTempStack.field_77994_a == 0) {
                           aSlot.func_75215_d((ItemStack)null);
                        }

                        aSlot.func_82870_a(aPlayer, aPlayerInventory.func_70445_o());
                     } else if(aSlot.func_75214_a(var91)) {
                        if(tTempStack.func_77973_b() == var91.func_77973_b() && tTempStack.func_77960_j() == var91.func_77960_j() && ItemStack.func_77970_a(tTempStack, var91)) {
                           tTempStackSize = aMouseclick == 0?var91.field_77994_a:1;
                           if(tTempStackSize > aSlot.func_75219_a() - tTempStack.field_77994_a) {
                              tTempStackSize = aSlot.func_75219_a() - tTempStack.field_77994_a;
                           }

                           if(tTempStackSize > var91.func_77976_d() - tTempStack.field_77994_a) {
                              tTempStackSize = var91.func_77976_d() - tTempStack.field_77994_a;
                           }

                           var91.func_77979_a(tTempStackSize);
                           if(var91.field_77994_a == 0) {
                              aPlayerInventory.func_70437_b((ItemStack)null);
                           }

                           tTempStack.field_77994_a += tTempStackSize;
                        } else if(var91.field_77994_a <= aSlot.func_75219_a()) {
                           aSlot.func_75215_d(var91);
                           aPlayerInventory.func_70437_b(tTempStack);
                        }
                     } else if(tTempStack.func_77973_b() == var91.func_77973_b() && var91.func_77976_d() > 1 && (!tTempStack.func_77981_g() || tTempStack.func_77960_j() == var91.func_77960_j()) && ItemStack.func_77970_a(tTempStack, var91)) {
                        tTempStackSize = tTempStack.field_77994_a;
                        if(tTempStackSize > 0 && tTempStackSize + var91.field_77994_a <= var91.func_77976_d()) {
                           var91.field_77994_a += tTempStackSize;
                           tTempStack = aSlot.func_75209_a(tTempStackSize);
                           if(tTempStack.field_77994_a == 0) {
                              aSlot.func_75215_d((ItemStack)null);
                           }

                           aSlot.func_82870_a(aPlayer, aPlayerInventory.func_70445_o());
                        }
                     }
                  }

                  aSlot.func_75218_e();
               }
            }
         } else if(aShifthold == 2 && aMouseclick >= 0 && aMouseclick < 9) {
            aSlot = (Slot)this.field_75151_b.get(aSlotIndex);
            if(aSlot.func_82869_a(aPlayer)) {
               tTempStack = aPlayerInventory.func_70301_a(aMouseclick);
               boolean var9 = tTempStack == null || aSlot.field_75224_c == aPlayerInventory && aSlot.func_75214_a(tTempStack);
               tTempStackSize = -1;
               if(!var9) {
                  tTempStackSize = aPlayerInventory.func_70447_i();
                  var9 |= tTempStackSize > -1;
               }

               if(aSlot.func_75216_d() && var9) {
                  aHoldStack = aSlot.func_75211_c();
                  aPlayerInventory.func_70299_a(aMouseclick, aHoldStack);
                  if((aSlot.field_75224_c != aPlayerInventory || !aSlot.func_75214_a(tTempStack)) && tTempStack != null) {
                     if(tTempStackSize > -1) {
                        aPlayerInventory.func_70441_a(tTempStack);
                        aSlot.func_75209_a(aHoldStack.field_77994_a);
                        aSlot.func_75215_d((ItemStack)null);
                        aSlot.func_82870_a(aPlayer, aHoldStack);
                     }
                  } else {
                     aSlot.func_75209_a(aHoldStack.field_77994_a);
                     aSlot.func_75215_d(tTempStack);
                     aSlot.func_82870_a(aPlayer, aHoldStack);
                  }
               } else if(!aSlot.func_75216_d() && tTempStack != null && aSlot.func_75214_a(tTempStack)) {
                  aPlayerInventory.func_70299_a(aMouseclick, (ItemStack)null);
                  aSlot.func_75215_d(tTempStack);
               }
            }
         } else if(aShifthold == 3 && aPlayer.field_71075_bZ.field_75098_d && aPlayerInventory.func_70445_o() == null && aSlotIndex >= 0) {
            aSlot = (Slot)this.field_75151_b.get(aSlotIndex);
            if(aSlot != null && aSlot.func_75216_d()) {
               tTempStack = GT_Utility.copy(new Object[]{aSlot.func_75211_c()});
               tTempStack.field_77994_a = tTempStack.func_77976_d();
               aPlayerInventory.func_70437_b(tTempStack);
            }
         }

         return rStack;
      }
   }

   public ItemStack func_82846_b(EntityPlayer aPlayer, int aSlotIndex) {
      ItemStack stack = null;
      Slot slotObject = (Slot)this.field_75151_b.get(aSlotIndex);
      if(this.getSlotCount() > 0 && slotObject != null && slotObject.func_75216_d() && !(slotObject instanceof GT_Slot_Holo)) {
         ItemStack stackInSlot = slotObject.func_75211_c();
         stack = GT_Utility.copy(new Object[]{stackInSlot});
         if(aSlotIndex < this.getAllSlotCount()) {
            if(this.doesBindPlayerInventory() && !this.func_75135_a(stackInSlot, this.getAllSlotCount(), this.getAllSlotCount() + 36, true)) {
               return null;
            }
         } else if(!this.func_75135_a(stackInSlot, this.getSlotStartIndex(), this.getSlotStartIndex() + this.getShiftClickSlotCount(), false)) {
            return null;
         }

         if(stackInSlot.field_77994_a == 0) {
            slotObject.func_75215_d((ItemStack)null);
         } else {
            slotObject.func_75218_e();
         }
      }

      return stack;
   }

   protected boolean func_75135_a(ItemStack aStack, int aStartIndex, int aSlotCount, boolean par4) {
      boolean var5 = false;
      int var6 = aStartIndex;
      if(par4) {
         var6 = aSlotCount - 1;
      }

      Slot var7;
      ItemStack var8;
      if(aStack.func_77985_e()) {
         while(aStack.field_77994_a > 0 && (!par4 && var6 < aSlotCount || par4 && var6 >= aStartIndex)) {
            var7 = (Slot)this.field_75151_b.get(var6);
            var8 = var7.func_75211_c();
            if(!(var7 instanceof GT_Slot_Holo) && !(var7 instanceof GT_Slot_Output) && var8 != null && var8.func_77973_b() == aStack.func_77973_b() && (!aStack.func_77981_g() || aStack.func_77960_j() == var8.func_77960_j()) && ItemStack.func_77970_a(aStack, var8)) {
               int var9 = var8.field_77994_a + aStack.field_77994_a;
               if(var9 <= aStack.func_77976_d()) {
                  aStack.field_77994_a = 0;
                  var8.field_77994_a = var9;
                  var7.func_75218_e();
                  var5 = true;
               } else if(var8.field_77994_a < aStack.func_77976_d()) {
                  aStack.field_77994_a -= aStack.func_77976_d() - var8.field_77994_a;
                  var8.field_77994_a = aStack.func_77976_d();
                  var7.func_75218_e();
                  var5 = true;
               }
            }

            if(par4) {
               --var6;
            } else {
               ++var6;
            }
         }
      }

      if(aStack.field_77994_a > 0) {
         if(par4) {
            var6 = aSlotCount - 1;
         } else {
            var6 = aStartIndex;
         }

         while(!par4 && var6 < aSlotCount || par4 && var6 >= aStartIndex) {
            var7 = (Slot)this.field_75151_b.get(var6);
            var8 = var7.func_75211_c();
            if(var8 == null) {
               var7.func_75215_d(GT_Utility.copy(new Object[]{aStack}));
               var7.func_75218_e();
               aStack.field_77994_a = 0;
               var5 = true;
               break;
            }

            if(par4) {
               --var6;
            } else {
               ++var6;
            }
         }
      }

      return var5;
   }

   protected Slot func_75146_a(Slot par1Slot) {
      try {
         return super.func_75146_a(par1Slot);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
         return par1Slot;
      }
   }

   public void func_75132_a(ICrafting par1ICrafting) {
      try {
         super.func_75132_a(par1ICrafting);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
      }

   }

   public List func_75138_a() {
      try {
         return super.func_75138_a();
      } catch (Throwable var2) {
         var2.printStackTrace(GT_Log.err);
         return null;
      }
   }

   public void func_82847_b(ICrafting par1ICrafting) {
      try {
         super.func_82847_b(par1ICrafting);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
      }

   }

   public void func_75142_b() {
      try {
         super.func_75142_b();
      } catch (Throwable var2) {
         var2.printStackTrace(GT_Log.err);
      }

   }

   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int par2) {
      try {
         return super.func_75140_a(par1EntityPlayer, par2);
      } catch (Throwable var4) {
         var4.printStackTrace(GT_Log.err);
         return false;
      }
   }

   public Slot func_75147_a(IInventory par1IInventory, int par2) {
      try {
         return super.func_75147_a(par1IInventory, par2);
      } catch (Throwable var4) {
         var4.printStackTrace(GT_Log.err);
         return null;
      }
   }

   public Slot func_75139_a(int par1) {
      try {
         if(this.field_75151_b.size() > par1) {
            return super.func_75139_a(par1);
         }
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
      }

      return null;
   }

   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot) {
      try {
         return super.func_94530_a(par1ItemStack, par2Slot);
      } catch (Throwable var4) {
         var4.printStackTrace(GT_Log.err);
         return true;
      }
   }

   protected void func_75133_b(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer) {
      try {
         super.func_75133_b(par1, par2, par3, par4EntityPlayer);
      } catch (Throwable var6) {
         var6.printStackTrace(GT_Log.err);
      }

   }

   public void func_75134_a(EntityPlayer par1EntityPlayer) {
      try {
         super.func_75134_a(par1EntityPlayer);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
      }

   }

   public void func_75130_a(IInventory par1IInventory) {
      try {
         super.func_75130_a(par1IInventory);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
      }

   }

   public void func_75141_a(int par1, ItemStack par2ItemStack) {
      try {
         super.func_75141_a(par1, par2ItemStack);
      } catch (Throwable var4) {
         var4.printStackTrace(GT_Log.err);
      }

   }

   public void func_75131_a(ItemStack[] par1ArrayOfItemStack) {
      try {
         super.func_75131_a(par1ArrayOfItemStack);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
      }

   }

   public void func_75137_b(int par1, int par2) {
      try {
         super.func_75137_b(par1, par2);
      } catch (Throwable var4) {
         var4.printStackTrace(GT_Log.err);
      }

   }

   public short func_75136_a(InventoryPlayer par1InventoryPlayer) {
      try {
         return super.func_75136_a(par1InventoryPlayer);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
         return (short)0;
      }
   }

   public boolean func_75129_b(EntityPlayer par1EntityPlayer) {
      try {
         return super.func_75129_b(par1EntityPlayer);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
         return true;
      }
   }

   public void func_75128_a(EntityPlayer par1EntityPlayer, boolean par2) {
      try {
         super.func_75128_a(par1EntityPlayer, par2);
      } catch (Throwable var4) {
         var4.printStackTrace(GT_Log.err);
      }

   }

   protected void func_94533_d() {
      try {
         super.func_94533_d();
      } catch (Throwable var2) {
         var2.printStackTrace(GT_Log.err);
      }

   }

   public boolean func_94531_b(Slot par1Slot) {
      try {
         return super.func_94531_b(par1Slot);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
         return true;
      }
   }
}
