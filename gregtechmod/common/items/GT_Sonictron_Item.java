package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Sonictron;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class GT_Sonictron_Item extends GT_Generic_Item {

   public GT_Sonictron_Item(int par1, String aUnlocalized, String aEnglish) {
      super(par1, aUnlocalized, aEnglish, (String)null);
      this.func_77625_d(1);
      this.setNoRepair();
   }

   public ItemStack func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      setCurrentIndex(aStack, 0);
      return aStack;
   }

   public boolean onItemUseFirst(ItemStack aStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
      if(!par3World.field_72995_K && par3World.func_72798_a(par4, par5, par6) == GregTech_API.sBlockList[1].field_71990_ca && par3World.func_72805_g(par4, par5, par6) == 6) {
         GT_TileEntity_Sonictron tSonictron = (GT_TileEntity_Sonictron)par3World.func_72796_p(par4, par5, par6);
         if(tSonictron != null) {
            ItemStack[] tInventory = getNBTInventory(aStack);
            if(par2EntityPlayer.func_70093_af()) {
               copyInventory(tSonictron.mInventory, tInventory);
            } else {
               copyInventory(tInventory, tSonictron.mInventory);
            }

            setNBTInventory(aStack, tInventory);
            tSonictron.sendClientData = true;
            return true;
         }
      }

      setCurrentIndex(aStack, -1);
      return false;
   }

   private static void copyInventory(ItemStack[] aInventory, ItemStack[] aNewContent) {
      for(int i = 0; i < 64; ++i) {
         if(aNewContent[i] == null) {
            aInventory[i] = null;
         } else {
            aInventory[i] = GT_Utility.copy(new Object[]{aNewContent[i]});
         }
      }

   }

   public static int getCurrentIndex(ItemStack aStack) {
      NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
      if(tNBTTagCompound == null) {
         tNBTTagCompound = new NBTTagCompound();
      }

      return tNBTTagCompound.func_74762_e("mCurrentIndex");
   }

   public static int getTickTimer(ItemStack aStack) {
      NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
      if(tNBTTagCompound == null) {
         tNBTTagCompound = new NBTTagCompound();
      }

      return tNBTTagCompound.func_74762_e("mTickTimer");
   }

   public static NBTTagCompound setCurrentIndex(ItemStack aStack, int aIndex) {
      NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
      if(tNBTTagCompound == null) {
         tNBTTagCompound = new NBTTagCompound();
      }

      tNBTTagCompound.func_74768_a("mCurrentIndex", aIndex);
      return tNBTTagCompound;
   }

   public static NBTTagCompound setTickTimer(ItemStack aStack, int aTime) {
      NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
      if(tNBTTagCompound == null) {
         tNBTTagCompound = new NBTTagCompound();
      }

      tNBTTagCompound.func_74768_a("mTickTimer", aTime);
      return tNBTTagCompound;
   }

   public static ItemStack[] getNBTInventory(ItemStack aStack) {
      ItemStack[] tInventory = new ItemStack[64];
      NBTTagCompound tNBT = aStack.func_77978_p();
      if(tNBT == null) {
         return tInventory;
      } else {
         NBTTagList tNBT_ItemList = tNBT.func_74761_m("Inventory");

         for(int i = 0; i < tNBT_ItemList.func_74745_c(); ++i) {
            NBTTagCompound tag = (NBTTagCompound)tNBT_ItemList.func_74743_b(i);
            byte slot = tag.func_74771_c("Slot");
            if(slot >= 0 && slot < tInventory.length) {
               tInventory[slot] = GT_Utility.loadItem(tag);
            }
         }

         return tInventory;
      }
   }

   public static NBTTagCompound setNBTInventory(ItemStack aStack, ItemStack[] aInventory) {
      NBTTagCompound tNBT = aStack.func_77978_p();
      if(tNBT == null) {
         tNBT = new NBTTagCompound();
      }

      NBTTagList tNBT_ItemList = new NBTTagList();

      for(int i = 0; i < aInventory.length; ++i) {
         ItemStack stack = aInventory[i];
         if(stack != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.func_74774_a("Slot", (byte)i);
            stack.func_77955_b(tag);
            tNBT_ItemList.func_74742_a(tag);
         }
      }

      tNBT.func_74782_a("Inventory", tNBT_ItemList);
      aStack.func_77982_d(tNBT);
      return tNBT;
   }

   public void func_77622_d(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      setNBTInventory(aStack, new ItemStack[64]);
   }

   public void func_77663_a(ItemStack aStack, World par2World, Entity aPlayer, int aTimer, boolean aIsInHand) {
      int tTickTimer = getTickTimer(aStack);
      int tCurrentIndex = getCurrentIndex(aStack);
      if(tTickTimer++ % 2 == 0 && tCurrentIndex > -1) {
         ItemStack[] tInventory = getNBTInventory(aStack);
         GregTech_API.gregtechmod.doSonictronSound(tInventory[tCurrentIndex], aPlayer.field_70170_p, aPlayer.field_70165_t, aPlayer.field_70163_u, aPlayer.field_70161_v);
         ++tCurrentIndex;
         if(tCurrentIndex > 63) {
            tCurrentIndex = -1;
         }
      }

      setTickTimer(aStack, tTickTimer);
      setCurrentIndex(aStack, tCurrentIndex);
   }

   public boolean func_77651_p() {
      return true;
   }
}
