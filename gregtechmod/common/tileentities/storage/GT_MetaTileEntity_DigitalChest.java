package gregtechmod.common.tileentities.storage;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_DigitalChest extends MetaTileEntity {

   public int mItemCount = 0;
   public int mItemID = 0;
   public int mItemMeta = 0;
   public boolean isDigitalChest = true;
   public static int sDigitalItemCount = '\u8000';


   public GT_MetaTileEntity_DigitalChest(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_DigitalChest() {}

   public void onConfigLoad(GT_Config aConfig) {
      sDigitalItemCount = Math.max(1024, aConfig.get(GT_ConfigCategories.machineconfig, "DigitalChest.MaxItems", sDigitalItemCount));
   }

   public boolean unbreakable() {
      return true;
   }

   public boolean isWrenchable() {
      return this.mItemCount <= 0;
   }

   public boolean isSimpleMachine() {
      return true;
   }

   public int getInvSize() {
      return 3;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean ownerControl() {
      return false;
   }

   public boolean isEnetOutput() {
      return false;
   }

   public boolean isEnetInput() {
      return false;
   }

   public boolean isOutputFacing(byte aSide) {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return false;
   }

   public int getProgresstime() {
      return this.mItemCount + (this.mInventory[0] == null?0:this.mInventory[0].field_77994_a) + (this.mInventory[1] == null?0:this.mInventory[1].field_77994_a) + (this.mInventory[2] == null?0:this.mInventory[2].field_77994_a);
   }

   public int maxProgresstime() {
      return this.getMaxItemCount();
   }

   public int getMaxItemCount() {
      return sDigitalItemCount - 192;
   }

   public void setItemCount(int aCount) {
      this.mItemCount = aCount;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      ItemStack tPlayerItem = aPlayer.field_71071_by.func_70448_g();
      if(tPlayerItem == null) {
         if(this.mItemID > 0) {
            for(int tNBT = 0; this.mItemCount < this.getMaxItemCount() && tNBT < aPlayer.field_71071_by.func_70302_i_(); ++tNBT) {
               if(aPlayer.field_71071_by.func_70301_a(tNBT) != null && aPlayer.field_71071_by.func_70301_a(tNBT).field_77993_c == this.mItemID && aPlayer.field_71071_by.func_70301_a(tNBT).func_77960_j() == this.mItemMeta && !aPlayer.field_71071_by.func_70301_a(tNBT).func_77942_o()) {
                  this.mItemCount += aPlayer.field_71071_by.func_70301_a(tNBT).field_77994_a;
                  if(aPlayer.field_71071_by.func_70301_a(tNBT).field_77994_a == 111) {
                     this.mItemCount = this.getMaxItemCount() + 192 - (this.mItemCount + (this.mInventory[0] == null?0:this.mInventory[0].field_77994_a) + (this.mInventory[1] == null?0:this.mInventory[1].field_77994_a) + (this.mInventory[2] == null?0:this.mInventory[2].field_77994_a));
                  } else if(this.mItemCount > this.getMaxItemCount()) {
                     aPlayer.field_71071_by.func_70301_a(tNBT).field_77994_a = this.mItemCount - this.getMaxItemCount();
                     this.mItemCount = this.getMaxItemCount();
                  } else {
                     aPlayer.field_71071_by.func_70301_a(tNBT).field_77994_a = 0;
                  }
               }

               if(aPlayer.field_71071_by.func_70301_a(tNBT) != null && aPlayer.field_71071_by.func_70301_a(tNBT).field_77994_a <= 0) {
                  aPlayer.field_71071_by.func_70299_a(tNBT, (ItemStack)null);
               }
            }

            GT_Utility.sendChatToPlayer(aPlayer, this.mItemCount + (this.mInventory[0] == null?0:this.mInventory[0].field_77994_a) + (this.mInventory[1] == null?0:this.mInventory[1].field_77994_a) + (this.mInventory[2] == null?0:this.mInventory[2].field_77994_a) + " of " + (new ItemStack(this.mItemID, 1, this.mItemMeta)).func_82833_r());
         }
      } else {
         if(this.isDigitalChest && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingQuantumChestUpgrade")) {
            if(tPlayerItem.field_77994_a > 0 || aPlayer.field_71075_bZ.field_75098_d) {
               if(!aPlayer.field_71075_bZ.field_75098_d) {
                  --tPlayerItem.field_77994_a;
               }

               NBTTagCompound var4 = new NBTTagCompound();
               ((TileEntity)this.getBaseMetaTileEntity()).func_70310_b(var4);
               this.getBaseMetaTileEntity().issueClientUpdate();
               this.getBaseMetaTileEntity().setInitialValuesAsNBT(var4, (short)49);
            }

            return;
         }

         if(this.mItemID <= 0) {
            this.mItemID = tPlayerItem.field_77993_c;
            this.mItemMeta = tPlayerItem.func_77960_j();
            this.mItemCount = 0;
         }

         if(this.mItemID > 0) {
            if(this.mItemCount < this.getMaxItemCount() && tPlayerItem.field_77993_c == this.mItemID && tPlayerItem.func_77960_j() == this.mItemMeta && !tPlayerItem.func_77942_o()) {
               this.mItemCount += tPlayerItem.field_77994_a;
               if(tPlayerItem.field_77994_a == 111) {
                  this.mItemCount = this.getMaxItemCount();
               } else if(this.mItemCount > this.getMaxItemCount()) {
                  tPlayerItem.field_77994_a = this.mItemCount - this.getMaxItemCount();
                  this.mItemCount = this.getMaxItemCount();
               } else {
                  tPlayerItem.field_77994_a = 0;
               }
            } else {
               GT_Utility.sendChatToPlayer(aPlayer, this.mItemCount + (this.mInventory[0] == null?0:this.mInventory[0].field_77994_a) + (this.mInventory[1] == null?0:this.mInventory[1].field_77994_a) + (this.mInventory[2] == null?0:this.mInventory[2].field_77994_a) + " of " + (new ItemStack(this.mItemID, 1, this.mItemMeta)).func_82833_r());
            }
         }
      }

      if(aPlayer.field_71069_bz != null) {
         aPlayer.field_71069_bz.func_75142_b();
      }

   }

   public void onLeftclick(EntityPlayer aPlayer) {
      if(this.mInventory[0] != null && this.mInventory[0].field_77994_a > 0) {
         ItemStack tOutput = GT_Utility.copy(new Object[]{this.mInventory[0]});
         if(aPlayer.func_70093_af()) {
            tOutput.field_77994_a = 1;
         }

         this.getBaseMetaTileEntity().func_70298_a(0, tOutput.field_77994_a);
         EntityItem tEntity = new EntityItem(this.getBaseMetaTileEntity().getWorld(), (double)this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5D, (double)this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5D, (double)this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5D, tOutput);
         tEntity.field_70159_w = 0.0D;
         tEntity.field_70181_x = 0.0D;
         tEntity.field_70179_y = 0.0D;
         this.getBaseMetaTileEntity().getWorld().func_72838_d(tEntity);
      }

   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_DigitalChest();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mItemCount", this.mItemCount);
      aNBT.func_74768_a("mItemID", this.mItemID);
      aNBT.func_74768_a("mItemMeta", this.mItemMeta);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mItemCount = aNBT.func_74762_e("mItemCount");
      this.mItemID = aNBT.func_74762_e("mItemID");
      this.mItemMeta = aNBT.func_74762_e("mItemMeta");
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.mItemID != 0) {
         if(Item.field_77698_e[this.mItemID] == null || this.getItemCount() <= 0 && this.getBaseMetaTileEntity().isAllowedToWork()) {
            this.mItemID = 0;
            this.mItemMeta = 0;
            this.mItemCount = 0;
         }

         if(this.mInventory[1] == null) {
            this.mInventory[1] = new ItemStack(this.mItemID, 0, this.mItemMeta);
         } else if(this.mItemCount < this.getMaxItemCount() && this.mInventory[1].field_77993_c == this.mItemID && this.mInventory[1].func_77960_j() == this.mItemMeta && !this.mInventory[1].func_77942_o()) {
            this.mItemCount += this.mInventory[1].field_77994_a;
            if(this.mItemCount > this.getMaxItemCount()) {
               this.mInventory[1].field_77994_a = this.mItemCount - this.getMaxItemCount();
               this.mItemCount = this.getMaxItemCount();
            } else {
               this.mInventory[1].field_77994_a = 0;
            }
         }

         if(this.mInventory[2] == null) {
            this.mInventory[2] = new ItemStack(this.mItemID, 0, this.mItemMeta);
         } else if(this.mItemCount < this.getMaxItemCount() && this.mInventory[2].field_77993_c == this.mItemID && this.mInventory[2].func_77960_j() == this.mItemMeta && !this.mInventory[2].func_77942_o()) {
            this.mItemCount += this.mInventory[2].field_77994_a;
            if(this.mItemCount > this.getMaxItemCount()) {
               this.mInventory[2].field_77994_a = this.mItemCount - this.getMaxItemCount();
               this.mItemCount = this.getMaxItemCount();
            } else {
               this.mInventory[2].field_77994_a = 0;
            }
         }

         if(this.mItemCount > 0) {
            if(this.mInventory[0] == null) {
               this.mInventory[0] = new ItemStack(this.mItemID, 0, this.mItemMeta);
            }

            if(this.mInventory[0] != null && this.mInventory[0].field_77993_c == this.mItemID && this.mInventory[0].func_77960_j() == this.mItemMeta && !this.mInventory[0].func_77942_o()) {
               while(this.mInventory[0].field_77994_a < this.mInventory[0].func_77976_d() && this.mItemCount > 0) {
                  --this.mItemCount;
                  ++this.mInventory[0].field_77994_a;
               }
            }
         }
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?37:(aSide == 0?32:(aSide == 1?29:40));
   }

   private ItemStack getStoredItem() {
      return this.mItemID == 0?null:new ItemStack(this.mItemID, 1, this.mItemMeta);
   }

   private int getItemCount() {
      return this.mItemCount + (this.mInventory[0] == null?0:this.mInventory[0].field_77994_a) + (this.mInventory[1] == null?0:this.mInventory[1].field_77994_a) + (this.mInventory[2] == null?0:this.mInventory[2].field_77994_a);
   }

   public String[] getInfoData() {
      return this.getStoredItem() == null?new String[]{"", "", "Max: " + (this.getMaxItemCount() + 192)}:new String[]{GT_LanguageManager.getTranslateableItemStackName(this.getStoredItem()), "" + this.getItemCount(), "Max: " + (this.getMaxItemCount() + 192)};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public String getDescription() {
      return "To lock the saved Item as Filter, use Rubber Hammer on it.";
   }

   public boolean isDigitalChest() {
      return true;
   }

   public ItemStack[] getStoredItemData() {
      return new ItemStack[]{new ItemStack(this.mItemID, this.mItemCount, this.mItemMeta)};
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return (aIndex == 1 || aIndex == 2) && aStack.field_77993_c == this.mItemID && aStack.func_77960_j() == this.mItemMeta && !aStack.func_77942_o();
   }

}
