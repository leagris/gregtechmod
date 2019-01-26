package gregtechmod.common.tileentities.deprecated;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.deprecated.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

public class GT_TileEntity_Sonictron extends GT_TileEntityMetaID_Machine {

   public int mCurrentIndex = -1;
   public boolean sendClientData = true;
   public boolean sendStacksizeData = false;


   public int getInventorySlotCount() {
      return 64;
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public float getWrenchDropRate() {
      return 1.0F;
   }

   public void storeAdditionalData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mCurrentIndex", this.mCurrentIndex);
   }

   public void getAdditionalData(NBTTagCompound aNBT) {
      this.mCurrentIndex = aNBT.func_74762_e("mCurrentIndex");
   }

   public void func_70299_a(int slot, ItemStack stack) {
      super.func_70299_a(slot, stack);
      this.sendClientData = true;
   }

   public void onPostTickUpdate() {
      try {
         if(this.isServerSide()) {
            if(this.field_70331_k.func_72864_z(this.field_70329_l, this.field_70330_m, this.field_70327_n)) {
               if(this.mCurrentIndex < 0) {
                  this.sendBlockEvent((byte)10, (byte)0);
                  this.mCurrentIndex = 0;
               }

               int e;
               if(this.sendStacksizeData) {
                  for(e = 0; e < this.getInventorySlotCount(); ++e) {
                     if(this.mInventory[e] != null) {
                        this.sendBlockEvent((byte)(e - 100), (byte)this.mInventory[e].field_77994_a);
                     }
                  }

                  this.sendStacksizeData = false;
               }

               if(this.sendClientData) {
                  for(e = 0; e < this.getInventorySlotCount(); ++e) {
                     this.sendBlockEvent((byte)(e + 20), (byte)getSoundIndex(this.mInventory[e]));
                  }

                  this.sendClientData = false;
                  this.sendStacksizeData = true;
               }
            }

            this.mRedstone = this.mCurrentIndex == 63;
         }

         if(this.mTickTimer % 2L == 0L && this.mCurrentIndex > -1) {
            GregTech_API.gregtechmod.doSonictronSound(this.mInventory[this.mCurrentIndex], this.field_70331_k, (double)this.field_70329_l + 0.5D, (double)this.field_70330_m + 0.5D, (double)this.field_70327_n + 0.5D);
            if(++this.mCurrentIndex > 63) {
               this.mCurrentIndex = -1;
            }
         }
      } catch (Throwable var2) {
         var2.printStackTrace(GT_Log.err);
      }

   }

   public static short getSoundIndex(ItemStack aStack) {
      for(short i = 0; i < GT_Mod.mSoundItems.size(); ++i) {
         if(GT_Utility.areStacksEqual((ItemStack)GT_Mod.mSoundItems.get(i), aStack)) {
            return i;
         }
      }

      return (short)GT_Mod.mSoundItems.size();
   }

   public Packet func_70319_e() {
      this.sendClientData = true;
      return super.func_70319_e();
   }

   public boolean isAccessible(EntityPlayer aPlayer) {
      ItemStack tStack = aPlayer.func_71045_bC();
      return GT_Utility.isStackInvalid(tStack)?true:(GT_Items.Tool_Sonictron.isStackEqual(tStack, true, true)?false:!GT_Items.Tool_DataOrb.isStackEqual(tStack, true, true));
   }

   public boolean func_70315_b(int aEventID, int aValue) {
      super.func_70315_b(aEventID, aValue);

      try {
         if(this.field_70331_k.field_72995_K) {
            switch(aEventID) {
            case 10:
               this.mCurrentIndex = aValue;
            default:
               if(aEventID >= 20 && aEventID - 20 < this.getInventorySlotCount() && aValue >= 0) {
                  this.mInventory[aEventID - 20] = aValue < GT_Mod.mSoundItems.size()?GT_Utility.copy(new Object[]{GT_Mod.mSoundItems.get(aValue)}):null;
               }

               if(aEventID >= -100 && aEventID + 100 < this.getInventorySlotCount() && aValue >= 0 && this.mInventory[aEventID + 100] != null) {
                  this.mInventory[aEventID + 100].field_77994_a = aValue;
               }
            }
         }
      } catch (Throwable var4) {
         var4.printStackTrace(GT_Log.err);
      }

      return true;
   }

   public String func_70303_b() {
      return "";
   }

   public int getTexture(int aSide, int aMeta) {
      return this.mCurrentIndex > -1?35:39;
   }

   public void doEnergyExplosion() {}
}
