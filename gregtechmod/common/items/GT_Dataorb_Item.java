package gregtechmod.common.items;

import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Sonictron;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_Dataorb_Item extends GT_Generic_Item {

   public GT_Dataorb_Item(int aID, String aUnlocalized, String aEnglish) {
      super(aID, aUnlocalized, aEnglish, "Stores Data");
      this.setNoRepair();
   }

   public ItemStack func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      return aStack;
   }

   public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float var8, float var9, float var10) {
      if(aStack.field_77994_a <= 1 && !aWorld.field_72995_K) {
         TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
         if(tTileEntity == null) {
            return false;
         } else if(tTileEntity instanceof GT_TileEntity_Sonictron) {
            GT_TileEntity_Sonictron tSonictron = (GT_TileEntity_Sonictron)tTileEntity;
            ItemStack[] tInventory = getNBTInventory(aStack);
            if(aPlayer.func_70093_af()) {
               if(getDataTitle(aStack).equals("Sonictron-Data")) {
                  copyInventory(tSonictron.mInventory, tInventory, 64);
               }
            } else {
               copyInventory(tInventory, tSonictron.mInventory, 64);
               setDataTitle(aStack, "Sonictron-Data");
               setDataName(aStack, "" + tInventory.hashCode());
            }

            setNBTInventory(aStack, tInventory);
            tSonictron.sendClientData = true;
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      if(!getDataTitle(aStack).equals("")) {
         aList.add(getDataTitle(aStack));
         aList.add(getDataName(aStack));
      }

   }

   private static void copyInventory(ItemStack[] aInventory, ItemStack[] aNewContent, int aIndexlength) {
      for(int i = 0; i < aIndexlength; ++i) {
         if(aNewContent[i] == null) {
            aInventory[i] = null;
         } else {
            aInventory[i] = GT_Utility.copy(new Object[]{aNewContent[i]});
         }
      }

   }

   public static String getDataName(ItemStack aStack) {
      NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
      if(tNBTTagCompound == null) {
         tNBTTagCompound = new NBTTagCompound();
      }

      return tNBTTagCompound.func_74779_i("mDataName");
   }

   public static String getDataTitle(ItemStack aStack) {
      NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
      if(tNBTTagCompound == null) {
         tNBTTagCompound = new NBTTagCompound();
      }

      return tNBTTagCompound.func_74779_i("mDataTitle");
   }

   public static NBTTagCompound setDataName(ItemStack aStack, String aDataName) {
      NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
      if(tNBTTagCompound == null) {
         tNBTTagCompound = new NBTTagCompound();
      }

      tNBTTagCompound.func_74778_a("mDataName", aDataName);
      return tNBTTagCompound;
   }

   public static NBTTagCompound setDataTitle(ItemStack aStack, String aDataTitle) {
      NBTTagCompound tNBTTagCompound = aStack.func_77978_p();
      if(tNBTTagCompound == null) {
         tNBTTagCompound = new NBTTagCompound();
      }

      tNBTTagCompound.func_74778_a("mDataTitle", aDataTitle);
      return tNBTTagCompound;
   }

   public static ItemStack[] getNBTInventory(ItemStack aStack) {
      ItemStack[] tInventory = new ItemStack[256];
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

   public boolean func_77651_p() {
      return true;
   }
}
