package gregtechmod.api.util;

import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_ItsNotMyFaultException;
import gregtechmod.api.util.GT_Utility;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class GT_BaseCrop extends CropCard {

   private String mName = "";
   private String mDiscoveredBy = "Gregorius Techneticies";
   private String[] mAttributes;
   private int mTier = 0;
   private int mMaxSize = 0;
   private int mAfterHarvestSize = 0;
   private int mHarvestSize = 0;
   private int[] mStats = new int[5];
   private ItemStack mDrop = null;
   private ItemStack[] mSpecialDrops = null;
   public static ArrayList<GT_BaseCrop> sCropList = new ArrayList();


   public GT_BaseCrop(int aID, String aCropName, String aDiscoveredBy, ItemStack aDrop, ItemStack[] aSpecialDrops, ItemStack aBaseSeed, int aTier, int aMaxSize, int aGrowthSpeed, int aAfterHarvestSize, int aHarvestSize, int aStatChemical, int aStatFood, int aStatDefensive, int aStatColor, int aStatWeed, String[] aAttributes) {
      this.mName = aCropName;
      aID = GT_Config.addIDConfig("crops", this.mName.replaceAll(" ", "_"), aID);
      if(aDiscoveredBy != null && !aDiscoveredBy.equals("")) {
         this.mDiscoveredBy = aDiscoveredBy;
      }

      if(aDrop != null && aID > 0 && aID < 256) {
         this.mDrop = GT_Utility.copy(new Object[]{aDrop});
         this.mSpecialDrops = aSpecialDrops;
         this.mTier = Math.max(1, aTier);
         this.mMaxSize = Math.max(3, aMaxSize);
         this.mHarvestSize = Math.min(Math.max(aHarvestSize, 2), this.mMaxSize);
         this.mAfterHarvestSize = Math.min(Math.max(aAfterHarvestSize, 1), this.mMaxSize - 1);
         this.mStats[0] = aStatChemical;
         this.mStats[1] = aStatFood;
         this.mStats[2] = aStatDefensive;
         this.mStats[3] = aStatColor;
         this.mStats[4] = aStatWeed;
         this.mAttributes = aAttributes;
         if(!Crops.instance.registerCrop(this, aID)) {
            throw new GT_ItsNotMyFaultException("Make sure the Crop ID is valid!");
         }

         if(aBaseSeed != null) {
            Crops.instance.registerBaseSeed(aBaseSeed, aID, 1, 1, 1, 1);
         }

         sCropList.add(this);
      }

   }

   public byte getSizeAfterHarvest(ICropTile crop) {
      return (byte)this.mAfterHarvestSize;
   }

   public String[] attributes() {
      return this.mAttributes;
   }

   public String discoveredBy() {
      return this.mDiscoveredBy;
   }

   public final boolean canGrow(ICropTile aCrop) {
      return aCrop.getSize() < this.maxSize();
   }

   public final boolean canBeHarvested(ICropTile aCrop) {
      return aCrop.getSize() >= this.mHarvestSize;
   }

   public boolean canCross(ICropTile aCrop) {
      return aCrop.getSize() + 2 > this.maxSize();
   }

   public int stat(int n) {
      return n >= 0 && n < this.mStats.length?this.mStats[n]:0;
   }

   public String name() {
      return this.mName;
   }

   public int tier() {
      return this.mTier;
   }

   public int maxSize() {
      return this.mMaxSize;
   }

   public ItemStack getGain(ICropTile aCrop) {
      boolean tDrop = false;
      int tDrop1;
      return this.mSpecialDrops != null && (tDrop1 = (new Random()).nextInt(this.mSpecialDrops.length + 4)) < this.mSpecialDrops.length && this.mSpecialDrops[tDrop1] != null?GT_Utility.copy(new Object[]{this.mSpecialDrops[tDrop1]}):(this.mDrop.func_77973_b().getContainerItemStack(this.mDrop) == null?GT_Utility.copy(new Object[]{this.mDrop}):null);
   }

   public boolean rightclick(ICropTile aCrop, EntityPlayer aPlayer) {
      if(!this.canBeHarvested(aCrop)) {
         return false;
      } else {
         ItemStack tContainerItem = this.mDrop.func_77973_b().getContainerItemStack(this.mDrop);
         if(tContainerItem == null) {
            return aCrop.harvest(aPlayer == null?false:aPlayer instanceof EntityPlayerMP);
         } else if(aPlayer != null && GT_Utility.areStacksEqual(tContainerItem, aPlayer.func_71045_bC()) && aPlayer.func_71045_bC().field_77994_a >= this.mDrop.field_77994_a && aPlayer.field_71071_by.func_70441_a(GT_Utility.copy(new Object[]{this.mDrop}))) {
            ItemStack var10000 = aPlayer.func_71045_bC();
            var10000.field_77994_a -= this.mDrop.field_77994_a;
            return aCrop.harvest(aPlayer instanceof EntityPlayerMP);
         } else {
            return false;
         }
      }
   }

}
