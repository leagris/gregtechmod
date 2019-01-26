package gregtechmod.api.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IFoodStat;
import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.interfaces.IOnItemClick;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IElectricItemManager;
import ic2.api.item.ISpecialElectricItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public abstract class GT_MetaGenerated_Item extends GT_Generic_Item implements ISpecialElectricItem, IElectricItemManager {

   public static final HashMap<String, GT_MetaGenerated_Item> sInstances = new HashMap();
   private BitSet mEnabledItems = new BitSet(766);
   private final OrePrefixes[] mGeneratedPrefixList;
   public Icon[][] mIconList;
   public final HashMap<Short, ArrayList<IOnItemClick>> mClickBehaviors;
   public final HashMap<Short, IFoodStat> mFoodStats;
   public final HashMap<Short, Integer[]> mElectricStats;
   public final HashMap<Short, Short> mBurnValues;


   public GT_MetaGenerated_Item(int aID, String aUnlocalized, OrePrefixes ... aGeneratedPrefixList) {
      super(aID, aUnlocalized, "Generated Item", (String)null, false);
      this.mIconList = new Icon[this.mEnabledItems.size()][1];
      this.mClickBehaviors = new HashMap();
      this.mFoodStats = new HashMap();
      this.mElectricStats = new HashMap();
      this.mBurnValues = new HashMap();
      this.mGeneratedPrefixList = (OrePrefixes[])Arrays.copyOf(aGeneratedPrefixList, 32);
      this.func_77637_a(GregTech_API.TAB_GREGTECH_MATERIALS);
      this.func_77627_a(true);
      this.func_77656_e(0);
      sInstances.put(this.func_77658_a(), this);

      for(int i = 0; i < 32000; ++i) {
         OrePrefixes tPrefix = this.mGeneratedPrefixList[i / 1000];
         if(tPrefix != null) {
            Materials tMaterial = GregTech_API.sGeneratedMaterials[i % 1000];
            if(tMaterial != null && this.doesMaterialAllowGeneration(tPrefix, tMaterial)) {
               GT_LanguageManager.addStringLocalization(this.func_77658_a() + "." + i + ".name", this.getDefaultLocalization(tPrefix, tMaterial, i));
               GT_LanguageManager.addStringLocalization(this.func_77658_a() + "." + i + ".tooltip", tMaterial.getToolTip(tPrefix.mMaterialAmount / 3628800L));
               String tOreName = this.getOreDictString(tPrefix, tMaterial);
               tPrefix = OrePrefixes.getOrePrefix(tOreName);
               GT_OreDictUnificator.registerOre(tOreName, new ItemStack(this, 1, i));
            }
         }
      }

   }

   public String getDefaultLocalization(OrePrefixes aPrefix, Materials aMaterial, int aMetaData) {
      return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + aPrefix.mLocalizedMaterialPost;
   }

   public abstract IIconContainer getIconContainer(int var1, Materials var2);

   public boolean doesMaterialAllowGeneration(OrePrefixes aPrefix, Materials aMaterial) {
      return aPrefix != null && aMaterial != null && !aPrefix.dontGenerateItem(aMaterial);
   }

   public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
      return true;
   }

   public String getOreDictString(OrePrefixes aPrefix, Materials aMaterial) {
      return aPrefix.get(aMaterial);
   }

   public short[] getRGBa(ItemStack aStack) {
      int aMetaData = this.getDamage(aStack);
      if(aMetaData < 0) {
         return Materials._NULL.mRGBa;
      } else if(aMetaData < 32000) {
         Materials tMaterial = GregTech_API.sGeneratedMaterials[aMetaData % 1000];
         if(tMaterial == null) {
            return Materials._NULL.mRGBa;
         } else {
            for(byte i = 0; i < tMaterial.mRGBa.length; ++i) {
               if(tMaterial.mRGBa[i] > 255) {
                  tMaterial.mRGBa[i] = 255;
               }

               if(tMaterial.mRGBa[i] < 0) {
                  tMaterial.mRGBa[i] = 0;
               }
            }

            return tMaterial.mRGBa;
         }
      } else {
         return Materials._NULL.mRGBa;
      }
   }

   public boolean useStandardMetaItemRenderer() {
      return true;
   }

   public final ItemStack addItem(int aID, String aEnglish, String aToolTip, IFoodStat aFoodBehavior, Object ... aOreDictNames) {
      if(aToolTip == null) {
         aToolTip = "";
      }

      if(aID >= 0 && aID < this.mEnabledItems.size()) {
         this.mEnabledItems.set(aID);
         GT_LanguageManager.addStringLocalization(this.func_77658_a() + "." + (32000 + aID) + ".name", aEnglish);
         GT_LanguageManager.addStringLocalization(this.func_77658_a() + "." + (32000 + aID) + ".tooltip", aToolTip);
         this.setFoodBehavior(32000 + aID, aFoodBehavior);
         ItemStack rStack = new ItemStack(this, 1, 32000 + aID);
         Object[] arr$ = aOreDictNames;
         int len$ = aOreDictNames.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Object tOreDictName = arr$[i$];
            GT_OreDictUnificator.registerOre(tOreDictName, rStack);
         }

         return rStack;
      } else {
         return null;
      }
   }

   public final GT_MetaGenerated_Item addClickBehavior(int aMetaValue, IOnItemClick aClickBehavior) {
      if(aMetaValue >= 0 && aMetaValue < 32000 + this.mEnabledItems.length() && aClickBehavior != null) {
         ArrayList tList = (ArrayList)this.mClickBehaviors.get(Short.valueOf((short)aMetaValue));
         if(tList == null) {
            tList = new ArrayList(1);
            this.mClickBehaviors.put(Short.valueOf((short)aMetaValue), tList);
         }

         tList.add(aClickBehavior);
         return this;
      } else {
         return this;
      }
   }

   public final GT_MetaGenerated_Item setFoodBehavior(int aMetaValue, IFoodStat aFoodBehavior) {
      if(aMetaValue >= 0 && aMetaValue < 32000 + this.mEnabledItems.length()) {
         if(aFoodBehavior == null) {
            this.mFoodStats.remove(Short.valueOf((short)aMetaValue));
         } else {
            this.mFoodStats.put(Short.valueOf((short)aMetaValue), aFoodBehavior);
         }

         return this;
      } else {
         return this;
      }
   }

   public final GT_MetaGenerated_Item setBurnValue(int aMetaValue, int aValue) {
      if(aMetaValue >= 0 && aMetaValue < 32000 + this.mEnabledItems.length() && aValue >= 0) {
         if(aValue == 0) {
            this.mBurnValues.remove(Short.valueOf((short)aMetaValue));
         } else {
            this.mBurnValues.put(Short.valueOf((short)aMetaValue), Short.valueOf(aValue > 32767?32767:(short)aValue));
         }

         return this;
      } else {
         return this;
      }
   }

   public final GT_MetaGenerated_Item setElectricStats(int aMetaValue, int aMaxCharge, int aTransferLimit, int aTier, int aSpecialData) {
      if(aMetaValue >= 0 && aMetaValue < 32000 + this.mEnabledItems.length()) {
         if(aMaxCharge < 0) {
            this.mElectricStats.remove(Short.valueOf((short)aMetaValue));
         } else {
            this.mElectricStats.put(Short.valueOf((short)aMetaValue), new Integer[]{Integer.valueOf(aMaxCharge), Integer.valueOf(Math.max(0, aTransferLimit)), Integer.valueOf(Math.max(0, aTier)), Integer.valueOf(aSpecialData)});
            if(aMetaValue >= 32000) {
               this.mIconList[aMetaValue - 32000] = (Icon[])Arrays.copyOf(this.mIconList[aMetaValue - 32000], Math.max(9, this.mIconList[aMetaValue - 32000].length));
            }
         }

         return this;
      } else {
         return this;
      }
   }

   public final int func_77626_a(ItemStack aStack) {
      return this.mFoodStats.get(Short.valueOf((short)this.getDamage(aStack))) == null?0:32;
   }

   public final EnumAction func_77661_b(ItemStack aStack) {
      IFoodStat tStat = (IFoodStat)this.mFoodStats.get(Short.valueOf((short)this.getDamage(aStack)));
      return tStat == null?EnumAction.none:tStat.getFoodAction(this, aStack);
   }

   public final ItemStack func_77654_b(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      IFoodStat tStat = (IFoodStat)this.mFoodStats.get(Short.valueOf((short)this.getDamage(aStack)));
      if(tStat != null) {
         aPlayer.func_71024_bL().func_75122_a(tStat.getFoodLevel(this, aStack, aPlayer), tStat.getSaturation(this, aStack, aPlayer));
         tStat.onEaten(this, aStack, aPlayer);
      }

      return aStack;
   }

   public final boolean func_77648_a(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
      ArrayList tList = (ArrayList)this.mClickBehaviors.get(Short.valueOf((short)this.getDamage(aStack)));
      if(tList != null) {
         Iterator i$ = tList.iterator();

         while(i$.hasNext()) {
            IOnItemClick tBehavior = (IOnItemClick)i$.next();
            if(tBehavior.onItemUse(this, aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ)) {
               return true;
            }
         }
      }

      return false;
   }

   public final boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
      ArrayList tList = (ArrayList)this.mClickBehaviors.get(Short.valueOf((short)this.getDamage(aStack)));
      if(tList != null) {
         Iterator i$ = tList.iterator();

         while(i$.hasNext()) {
            IOnItemClick tBehavior = (IOnItemClick)i$.next();
            if(tBehavior.onItemUseFirst(this, aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ)) {
               return true;
            }
         }
      }

      return false;
   }

   public final ItemStack func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      ArrayList tList = (ArrayList)this.mClickBehaviors.get(Short.valueOf((short)this.getDamage(aStack)));
      IOnItemClick tBehavior;
      if(tList != null) {
         for(Iterator tStat = tList.iterator(); tStat.hasNext(); aStack = tBehavior.onItemRightClick(this, aStack, aWorld, aPlayer)) {
            tBehavior = (IOnItemClick)tStat.next();
         }
      }

      IFoodStat tStat1 = (IFoodStat)this.mFoodStats.get(Short.valueOf((short)this.getDamage(aStack)));
      if(tStat1 != null && aPlayer.func_71043_e(tStat1.alwaysEdible(this, aStack, aPlayer))) {
         aPlayer.func_71008_a(aStack, this.func_77626_a(aStack));
      }

      return aStack;
   }

   public final IIconContainer getIconContainer(int aMetaData) {
      if(aMetaData < 0) {
         return null;
      } else if(aMetaData < 32000) {
         Materials tMaterial = GregTech_API.sGeneratedMaterials[aMetaData % 1000];
         return tMaterial == null?null:this.getIconContainer(aMetaData, tMaterial);
      } else {
         return null;
      }
   }

   @SideOnly(Side.CLIENT)
   public final void func_77633_a(int var1, CreativeTabs aCreativeTab, List aList) {
      int i;
      for(i = 0; i < 32000; ++i) {
         if(this.doesMaterialAllowGeneration(this.mGeneratedPrefixList[i / 1000], GregTech_API.sGeneratedMaterials[i % 1000]) && this.doesShowInCreative(this.mGeneratedPrefixList[i / 1000], GregTech_API.sGeneratedMaterials[i % 1000], GregTech_API.sDoShowAllItemsInCreative)) {
            aList.add(new ItemStack(this, 1, i));
         }
      }

      i = 0;

      for(int j = this.mEnabledItems.length(); i < j; ++i) {
         if(this.mEnabledItems.get(i)) {
            Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)(32000 + i)));
            if(tStats != null && tStats[3].intValue() < 0) {
               ItemStack tStack = new ItemStack(this, 1, 32000 + i);
               this.setCharge(tStack, tStats[0].intValue());
               aList.add(tStack);
            }

            if(tStats == null || tStats[3].intValue() != -2) {
               aList.add(new ItemStack(this, 1, 32000 + i));
            }
         }
      }

   }

   public final String func_77667_c(ItemStack aStack) {
      return this.func_77658_a() + "." + this.getDamage(aStack);
   }

   @SideOnly(Side.CLIENT)
   public final void func_94581_a(IconRegister aIconRegister) {
      short i = 0;

      for(short j = (short)this.mEnabledItems.length(); i < j; ++i) {
         if(this.mEnabledItems.get(i)) {
            for(byte k = 1; k < this.mIconList[i].length; ++k) {
               this.mIconList[i][k] = aIconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":this.func_77658_a() + "/" + i + "/" + k));
            }

            this.mIconList[i][0] = aIconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":this.func_77658_a() + "/" + i));
         }
      }

   }

   public final Icon func_77617_a(int aMetaData) {
      if(aMetaData < 0) {
         return null;
      } else if(aMetaData < 32000) {
         Materials tMaterial = GregTech_API.sGeneratedMaterials[aMetaData % 1000];
         if(tMaterial == null) {
            return null;
         } else {
            IIconContainer tIcon = this.getIconContainer(aMetaData, tMaterial);
            return tIcon != null?tIcon.getIcon():null;
         }
      } else {
         return aMetaData - 32000 < this.mIconList.length?this.mIconList[aMetaData - 32000][0]:null;
      }
   }

   public final boolean func_77636_d(ItemStack aStack) {
      if(super.func_77636_d(aStack)) {
         return true;
      } else {
         int aMetaData = this.getDamage(aStack);
         if(aMetaData < 0) {
            return false;
         } else if(aMetaData < 32000) {
            Materials tMaterial = GregTech_API.sGeneratedMaterials[aMetaData % 1000];
            return tMaterial == null?false:tMaterial.isRadioactive() || tMaterial.contains(SubTag.ENCHANTMENT_GLOW);
         } else {
            return false;
         }
      }
   }

   public final boolean hasEffect(ItemStack aStack, int aPass) {
      if(super.hasEffect(aStack, aPass)) {
         return true;
      } else {
         int aMetaData = this.getDamage(aStack);
         if(aMetaData < 0) {
            return false;
         } else if(aMetaData < 32000) {
            Materials tMaterial = GregTech_API.sGeneratedMaterials[aMetaData % 1000];
            return tMaterial == null?false:tMaterial.isRadioactive() || tMaterial.contains(SubTag.ENCHANTMENT_GLOW);
         } else {
            return false;
         }
      }
   }

   public final void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
      String tKey = this.func_77658_a() + "." + this.getDamage(aStack) + ".tooltip";
      String tString = GT_LanguageManager.getTranslation(tKey);
      if(GT_Utility.isStringValid(tString) && !tKey.equals(tString)) {
         aList.add(tString);
      }

      Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)aStack.func_77960_j()));
      if(tStats != null) {
         if(tStats[3].intValue() > 0) {
            aList.add("Contains " + tStats[3] + " EU   Tier: " + (tStats[2].intValue() > 0?tStats[2].intValue():1));
         } else {
            int tCharge = this.getCharge(aStack);
            if(tStats[3].intValue() == -2 && tCharge <= 0) {
               aList.add("Empty. You should recycle it properly.");
            } else {
               aList.add(tCharge + " / " + tStats[0] + " EU - Voltage: " + GregTech_API.VOLTAGES[tStats[2].intValue() > 0?(tStats[2].intValue() < GregTech_API.VOLTAGES.length?tStats[2].intValue():GregTech_API.VOLTAGES.length - 1):1]);
            }
         }
      }

   }

   public final int getMaxCharge(ItemStack aStack) {
      Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)aStack.func_77960_j()));
      return tStats == null?0:tStats[0].intValue();
   }

   public final int getTransferLimit(ItemStack aStack) {
      Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)aStack.func_77960_j()));
      return tStats == null?0:Math.max(tStats[1].intValue(), tStats[3].intValue());
   }

   public final boolean canProvideEnergy(ItemStack aStack) {
      Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)aStack.func_77960_j()));
      return tStats == null?false:tStats[3].intValue() > 0 || aStack.field_77994_a == 1 && (tStats[3].intValue() == -2 || tStats[3].intValue() == -3);
   }

   public final int charge(ItemStack aStack, int aCharge, int aTier, boolean aIgnoreTransferLimit, boolean aSimulate) {
      Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)aStack.func_77960_j()));
      if(tStats != null && tStats[2].intValue() <= aTier && (tStats[3].intValue() == -1 || tStats[3].intValue() == -3 || tStats[3].intValue() < 0 && aCharge == Integer.MAX_VALUE) && aStack.field_77994_a == 1) {
         int tChargeBefore = this.getCharge(aStack);
         int tNewCharge = Math.min(tStats[0].intValue(), tChargeBefore + (aIgnoreTransferLimit?aCharge:Math.min(tStats[1].intValue(), aCharge)));
         if(!aSimulate) {
            this.setCharge(aStack, tNewCharge);
         }

         return tNewCharge - tChargeBefore;
      } else {
         return 0;
      }
   }

   public final int discharge(ItemStack aStack, int aCharge, int aTier, boolean aIgnoreTransferLimit, boolean aSimulate) {
      Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)aStack.func_77960_j()));
      if(tStats != null && tStats[2].intValue() <= aTier) {
         if(tStats[3].intValue() > 0) {
            if(aCharge >= tStats[3].intValue() && aStack.field_77994_a >= 1) {
               if(!aSimulate) {
                  --aStack.field_77994_a;
               }

               return tStats[3].intValue();
            } else {
               return 0;
            }
         } else {
            int tChargeBefore = this.getCharge(aStack);
            int tNewCharge = Math.max(0, tChargeBefore - (aIgnoreTransferLimit?aCharge:Math.min(tStats[1].intValue(), aCharge)));
            if(!aSimulate) {
               this.setCharge(aStack, tNewCharge);
            }

            return tChargeBefore - tNewCharge;
         }
      } else {
         return 0;
      }
   }

   public final int getCharge(ItemStack aStack) {
      Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)aStack.func_77960_j()));
      if(tStats == null) {
         return 0;
      } else if(tStats[3].intValue() > 0) {
         return tStats[3].intValue();
      } else {
         NBTTagCompound tNBT = aStack.func_77978_p();
         return tNBT == null?0:tNBT.func_74762_e("GT.ItemCharge");
      }
   }

   public final boolean canUse(ItemStack aStack, int aAmount) {
      return this.getCharge(aStack) >= aAmount;
   }

   public final boolean use(ItemStack aStack, int aAmount, EntityLivingBase aPlayer) {
      if(aPlayer.field_70170_p.field_72995_K) {
         return false;
      } else if(aAmount <= 0) {
         this.chargeFromArmor(aStack, aPlayer);
         return true;
      } else {
         int transfer = this.discharge(aStack, aAmount, Integer.MAX_VALUE, true, true);
         if(transfer == aAmount) {
            this.discharge(aStack, aAmount, Integer.MAX_VALUE, true, false);
            this.chargeFromArmor(aStack, aPlayer);
            return true;
         } else {
            return false;
         }
      }
   }

   public final void chargeFromArmor(ItemStack aStack, EntityLivingBase aPlayer) {
      if(!aPlayer.field_70170_p.field_72995_K) {
         for(int i = 1; i < 5; ++i) {
            ItemStack tArmor = aPlayer.func_71124_b(i);
            if(GT_ModHandler.isElectricItem(tArmor)) {
               IElectricItem tArmorItem = (IElectricItem)tArmor.func_77973_b();
               if(tArmorItem.canProvideEnergy(tArmor) && tArmorItem.getTier(tArmor) >= this.getTier(aStack)) {
                  int tCharge = ElectricItem.manager.discharge(tArmor, this.charge(aStack, 2147483646, Integer.MAX_VALUE, true, true), Integer.MAX_VALUE, true, false);
                  if(tCharge > 0) {
                     this.charge(aStack, tCharge, Integer.MAX_VALUE, true, false);
                     if(aPlayer instanceof EntityPlayer) {
                        Container tContainer = ((EntityPlayer)aPlayer).field_71070_bA;
                        if(tContainer != null) {
                           tContainer.func_75142_b();
                        }
                     }
                  }
               }
            }
         }

      }
   }

   public final boolean setCharge(ItemStack aStack, int aCharge) {
      Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)aStack.func_77960_j()));
      if(tStats != null && tStats[3].intValue() <= 0) {
         NBTTagCompound tNBT = aStack.func_77978_p();
         if(tNBT == null) {
            tNBT = new NBTTagCompound();
         }

         tNBT.func_82580_o("GT.ItemCharge");
         aCharge = Math.min(aCharge, tStats[0].intValue());
         if(aCharge > 0) {
            tNBT.func_74768_a("GT.ItemCharge", aCharge);
         }

         if(tNBT.func_74758_c().isEmpty()) {
            aStack.func_77982_d((NBTTagCompound)null);
         } else {
            aStack.func_77982_d(tNBT);
         }

         return true;
      } else {
         return false;
      }
   }

   public final int getItemStackLimit(ItemStack aStack) {
      Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)aStack.func_77960_j()));
      return tStats != null && (tStats[3].intValue() == -1 || tStats[3].intValue() == -3) && this.getCharge(aStack) > 0?1:64;
   }

   public final int getTier(ItemStack aStack) {
      Integer[] tStats = (Integer[])this.mElectricStats.get(Short.valueOf((short)aStack.func_77960_j()));
      return tStats == null?Integer.MAX_VALUE:(tStats[2].intValue() > 0?tStats[2].intValue():1);
   }

   public final String getToolTip(ItemStack aStack) {
      return null;
   }

   public final int getChargedItemId(ItemStack aStack) {
      return this.field_77779_bT;
   }

   public final int getEmptyItemId(ItemStack aStack) {
      return this.field_77779_bT;
   }

   public final IElectricItemManager getManager(ItemStack aStack) {
      return this;
   }

   public final boolean func_77651_p() {
      return true;
   }

}
