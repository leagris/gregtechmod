package gregtechmod.api.items;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GT_Tool_Item extends GT_Generic_Item {

   public final List<String> mEffectiveAgainstList;
   public final List<Block> mEffectiveBlocksList;
   public final List<String> mEffectiveOreDictList;
   public final List<Material> mEffectiveMaterialsList;
   protected final int mEntityDamage;
   protected final int mChargedGTID;
   protected final int mDisChargedGTID;
   protected final int mToolQuality;
   protected final float mToolStrength;
   private short mDamagePerContainerCraft;
   private short mDamagePerWeaponUse;
   private short mDamagePerBlockBreak;
   private int mTier;
   private int mEUperBrokenBlock;
   private int mEUperHitEntity;
   private int mSilklevel;
   private int mFortunelevel;
   private int mLootinglevel;
   private String[] mToolClasses;
   private String mCraftingSound;
   private String mBreakingSound;
   private String mBlockBreakSound;
   private String mEntityHitSound;
   private float mCraftingSoundStrength;
   private float mBreakingSoundStrength;
   private float mBlockBreakSoundStrength;
   private float mEntityHitSoundStrength;
   private final boolean mSwingIfUsed;


   public GT_Tool_Item(int aID, String aUnlocalized, String aEnglish, String aTooltip, int aMaxDamage, int aEntityDamage, boolean aSwingIfUsed) {
      this(aID, aUnlocalized, aEnglish, aTooltip, aMaxDamage, aEntityDamage, aSwingIfUsed, -1, -1);
   }

   public GT_Tool_Item(int aID, String aUnlocalized, String aEnglish, String aTooltip, int aMaxDamage, int aEntityDamage, boolean aSwingIfUsed, int aChargedGTID, int aDisChargedGTID) {
      this(aID, aUnlocalized, aEnglish, aTooltip, aMaxDamage, aEntityDamage, aSwingIfUsed, aChargedGTID, aDisChargedGTID, 0, 0.0F);
   }

   public GT_Tool_Item(int aID, String aUnlocalized, String aEnglish, String aTooltip, int aMaxDamage, int aEntityDamage, boolean aSwingIfUsed, int aChargedGTID, int aDisChargedGTID, int aToolQuality, float aToolStrength) {
      super(aID, aUnlocalized, aEnglish, aTooltip, aTooltip != null && !aTooltip.equals("Doesn\'t work as intended, this is a Bug"));
      this.mEffectiveAgainstList = new ArrayList();
      this.mEffectiveBlocksList = new ArrayList();
      this.mEffectiveOreDictList = new ArrayList();
      this.mEffectiveMaterialsList = new ArrayList();
      this.mDamagePerContainerCraft = 10;
      this.mDamagePerWeaponUse = 3;
      this.mDamagePerBlockBreak = 1;
      this.mTier = 1;
      this.mEUperBrokenBlock = -1;
      this.mEUperHitEntity = -1;
      this.mSilklevel = 0;
      this.mFortunelevel = 0;
      this.mLootinglevel = 0;
      this.mCraftingSoundStrength = 1.0F;
      this.mBreakingSoundStrength = 1.0F;
      this.mBlockBreakSoundStrength = 1.0F;
      this.mEntityHitSoundStrength = 1.0F;
      this.mEntityDamage = aEntityDamage;
      this.mDisChargedGTID = aDisChargedGTID;
      this.mChargedGTID = aChargedGTID;
      this.mToolQuality = Math.max(aToolQuality, 0);
      this.mToolStrength = aToolStrength;
      this.mSwingIfUsed = aSwingIfUsed;
      this.func_77656_e(aMaxDamage);
      this.func_77625_d(1);
      this.setNoRepair();
      this.func_77664_n();
      GT_ModHandler.registerBoxableItemToToolBox((Item)this);
   }

   public final GT_Tool_Item addToEffectiveList(String aEntityClassName) {
      this.mEffectiveAgainstList.add(aEntityClassName.substring(aEntityClassName.lastIndexOf(".") + 1));
      return this;
   }

   public final GT_Tool_Item addToOreDictList(String aOreName) {
      this.mEffectiveOreDictList.add(aOreName);
      return this;
   }

   public final GT_Tool_Item addToBlockList(ItemStack aBlock) {
      return this.addToBlockList(GT_Utility.getBlockFromStack(aBlock));
   }

   public final GT_Tool_Item addToBlockList(Block aBlock) {
      if(GT_Utility.isBlockValid(aBlock)) {
         this.mEffectiveBlocksList.add(aBlock);
      }

      return this;
   }

   public final GT_Tool_Item addToMaterialList(Material aMaterial) {
      if(aMaterial != null) {
         this.mEffectiveMaterialsList.add(aMaterial);
      }

      return this;
   }

   public final GT_Tool_Item setElectricTier(int aTier) {
      this.mTier = Math.max(1, aTier);
      return this;
   }

   public final GT_Tool_Item setElectricConsumptionPerBrokenBlock(int aEU) {
      this.mEUperBrokenBlock = Math.max(0, aEU);
      return this;
   }

   public final GT_Tool_Item setElectricConsumptionPerHitEntity(int aEU) {
      this.mEUperHitEntity = Math.max(0, aEU);
      return this;
   }

   public final GT_Tool_Item setToolClasses(String ... aClasses) {
      this.mToolClasses = aClasses;
      return this;
   }

   public final GT_Tool_Item setCraftingSound(String aSound) {
      this.mCraftingSound = aSound;
      return this;
   }

   public final GT_Tool_Item setBreakingSound(String aSound) {
      this.mBreakingSound = aSound;
      return this;
   }

   public final GT_Tool_Item setBlockBreakSound(String aSound) {
      this.mBlockBreakSound = aSound;
      return this;
   }

   public final GT_Tool_Item setEntityHitSound(String aSound) {
      this.mEntityHitSound = aSound;
      return this;
   }

   public final GT_Tool_Item setUsageAmounts(int aDamagePerContainerCraft, int aDamagePerWeaponUse, int aDamagePerBlockBreak) {
      this.mDamagePerContainerCraft = (short)aDamagePerContainerCraft;
      this.mDamagePerWeaponUse = (short)aDamagePerWeaponUse;
      this.mDamagePerBlockBreak = (short)aDamagePerBlockBreak;
      return this;
   }

   public final GT_Tool_Item setSilkyness(int aLevel) {
      this.mSilklevel = aLevel;
      return this;
   }

   public final GT_Tool_Item setLuckyness(int aLevel) {
      this.mFortunelevel = aLevel;
      return this;
   }

   public final GT_Tool_Item setLootyness(int aLevel) {
      this.mLootinglevel = aLevel;
      return this;
   }

   public final GT_Tool_Item registerAtOreDict(Object aName) {
      GT_OreDictUnificator.registerOre(aName, new ItemStack(this, 1, 32767));
      return this;
   }

   public void setMode(ItemStack aStack, int aMode) {
      NBTTagCompound tNBT = aStack.func_77978_p();
      if(tNBT == null) {
         tNBT = new NBTTagCompound();
         aStack.func_77982_d(tNBT);
      }

      this.checkEnchantmentEffects(aStack);
      tNBT.func_74768_a("mMode", aMode);
   }

   public int getMode(ItemStack aStack) {
      if(aStack == null) {
         return 0;
      } else {
         NBTTagCompound tNBT = aStack.func_77978_p();
         if(tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.func_77982_d(tNBT);
         }

         this.checkEnchantmentEffects(aStack);
         return tNBT.func_74762_e("mMode");
      }
   }

   public final short getDamagePerContainerItemCraft() {
      return this.mDamagePerContainerCraft;
   }

   public final short getDamagePerWeaponUse() {
      return this.mDamagePerWeaponUse;
   }

   public final short getDamagePerBlockBreak() {
      return this.mDamagePerBlockBreak;
   }

   public void onHitEntity(Entity aEntity) {}

   public void checkEnchantmentEffects(ItemStack aStack) {
      if(aStack != null) {
         if(aStack.func_77948_v()) {
            aStack.field_77990_d.func_74758_c().remove(aStack.field_77990_d.func_74781_a("ench"));
         }

         if(!GT_ModHandler.isElectricItem(aStack) || GT_ModHandler.canUseElectricItem(aStack, this.mEUperBrokenBlock < 0?this.getDamagePerBlockBreak() * 1000:this.mEUperBrokenBlock)) {
            if(this.mSilklevel > 0) {
               aStack.func_77966_a(Enchantment.field_77348_q, this.mSilklevel);
            }

            if(this.mFortunelevel > 0) {
               aStack.func_77966_a(Enchantment.field_77346_s, this.mFortunelevel);
            }

            if(this.mLootinglevel > 0) {
               aStack.func_77966_a(Enchantment.field_77335_o, this.mLootinglevel);
            }
         }
      }

   }

   public ItemStack getEmptyItem(ItemStack aStack) {
      return null;
   }

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IconRegister aIconRegister) {
      super.func_94581_a(aIconRegister);
      if(this.mChargedGTID >= 0) {
         ((GT_Generic_Item)GregTech_API.sItemList[this.mChargedGTID]).mIcon = this.mIcon;
      }

      if(this.mDisChargedGTID >= 0) {
         ((GT_Generic_Item)GregTech_API.sItemList[this.mDisChargedGTID]).mIcon = this.mIcon;
      }

   }

   public ItemStack func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      GT_ModHandler.useElectricItem(aStack, 0, aPlayer);
      this.checkEnchantmentEffects(aStack);
      return aStack;
   }

   public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
      if(this.mSwingIfUsed) {
         aPlayer.func_71038_i();
      }

      GT_ModHandler.useElectricItem(aStack, 0, aPlayer);
      this.checkEnchantmentEffects(aStack);
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void func_77633_a(int var1, CreativeTabs var2, List aList) {
      if(GT_ModHandler.isElectricItem(new ItemStack(this, 1))) {
         ItemStack tCharged = new ItemStack(this, 1);
         GT_ModHandler.chargeElectricItem(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
         aList.add(tCharged);
      } else {
         aList.add(new ItemStack(this, 1));
      }
   }

   public int func_77619_b() {
      return 0;
   }

   public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {
      return false;
   }

   public boolean func_82789_a(ItemStack aStack, ItemStack aMaterial) {
      return false;
   }

   public final ItemStack getContainerItemStack(ItemStack aStack) {
      this.checkEnchantmentEffects(aStack);
      aStack = GT_Utility.copy(new Object[]{aStack});
      if(aStack.func_77960_j() > aStack.func_77958_k()) {
         GT_Utility.doSoundAtClient(this.mBreakingSound, 1, this.mBreakingSoundStrength);
         ItemStack tStack = this.getEmptyItem(aStack);
         if(tStack != null) {
            return tStack;
         }
      } else {
         GT_Utility.doSoundAtClient(this.mCraftingSound, 1, this.mCraftingSoundStrength);
         GT_ModHandler.damageOrDechargeItem(aStack, this.getDamagePerContainerItemCraft(), this.getDamagePerContainerItemCraft() * 1000, (EntityLivingBase)null);
      }

      this.checkEnchantmentEffects(aStack);
      return aStack;
   }

   public boolean func_77634_r() {
      return true;
   }

   public boolean func_77636_d(ItemStack aStack) {
      this.checkEnchantmentEffects(aStack);
      return false;
   }

   public final boolean func_77644_a(ItemStack aStack, EntityLivingBase aEntity, EntityLivingBase aPlayer) {
      GT_Utility.sendSoundToPlayers(aEntity.field_70170_p, this.mEntityHitSound, this.mEntityHitSoundStrength, -1.0F, (int)aEntity.field_70165_t, (int)aEntity.field_70163_u, (int)aEntity.field_70161_v);
      this.checkEnchantmentEffects(aStack);
      if(this.mEntityDamage > 1) {
         GT_ModHandler.damageOrDechargeItem(aStack, this.getDamagePerWeaponUse(), this.mEUperHitEntity < 0?this.getDamagePerWeaponUse() * 1000:this.mEUperHitEntity, aPlayer);
         this.onHitEntity(aEntity);
         this.checkEnchantmentEffects(aStack);
         return true;
      } else {
         return false;
      }
   }

   public Multimap func_111205_h() {
      Multimap multimap = super.func_111205_h();
      multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", this.mEntityDamage > 0?(double)this.mEntityDamage:1.0D, 0));
      return multimap;
   }

   public final float getDamageVsEntity(Entity aAttackedEntity, ItemStack aStack) {
      this.checkEnchantmentEffects(aStack);
      int tDamage = this.mEntityDamage;
      if(this.mEffectiveAgainstList.contains(GT_Utility.getClassName(aAttackedEntity))) {
         tDamage *= 2;
      }

      return tDamage > 1?(GT_ModHandler.isElectricItem(aStack)?(GT_ModHandler.canUseElectricItem(aStack, this.mEUperHitEntity < 0?this.getDamagePerWeaponUse() * 1000:this.mEUperHitEntity)?(float)tDamage:1.0F):(float)tDamage):1.0F;
   }

   private final boolean isEffectiveAgainst(Block aBlock, int aMeta) {
      if(this.mToolStrength > 1.0F && GT_Utility.isBlockValid(aBlock)) {
         if(this.mToolClasses != null) {
            String[] i$ = this.mToolClasses;
            int tString = i$.length;

            for(int i$1 = 0; i$1 < tString; ++i$1) {
               String tToolClass = i$[i$1];
               int tHarvestLevel = MinecraftForge.getBlockHarvestLevel(aBlock, aMeta == -1?0:aMeta, tToolClass);
               if(tHarvestLevel >= 0 && tHarvestLevel <= this.mToolQuality) {
                  return true;
               }
            }
         }

         if(this.mEffectiveMaterialsList.contains(aBlock.field_72018_cp)) {
            return true;
         }

         if(this.mEffectiveBlocksList.contains(aBlock)) {
            return true;
         }

         Iterator var8 = this.mEffectiveOreDictList.iterator();

         while(var8.hasNext()) {
            String var9 = (String)var8.next();
            if(GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(aBlock, 1, aMeta == -1?32767:aMeta), var9)) {
               return true;
            }
         }
      }

      return false;
   }

   public final boolean canHarvestBlock(Block aBlock, ItemStack aStack) {
      return this.func_77641_a(aBlock) && (!GT_ModHandler.isElectricItem(aStack) || GT_ModHandler.canUseElectricItem(aStack, this.mEUperBrokenBlock < 0?this.getDamagePerBlockBreak() * 1000:this.mEUperBrokenBlock));
   }

   public final boolean func_77641_a(Block aBlock) {
      return this.mToolStrength > 1.0F && this.isEffectiveAgainst(aBlock, -1);
   }

   public float getStrVsBlock(ItemStack aStack, Block aBlock, int aMeta) {
      this.checkEnchantmentEffects(aStack);
      return this.mToolStrength > 1.0F && this.isEffectiveAgainst(aBlock, aMeta) && (!GT_ModHandler.isElectricItem(aStack) || GT_ModHandler.canUseElectricItem(aStack, this.mEUperBrokenBlock < 0?this.getDamagePerBlockBreak() * 1000:this.mEUperBrokenBlock))?this.mToolStrength:1.0F;
   }

   public final boolean func_77660_a(ItemStack aStack, World aWorld, int aID, int aX, int aY, int aZ, EntityLivingBase aPlayer) {
      GT_Utility.sendSoundToPlayers(aWorld, this.mBlockBreakSound, this.mBlockBreakSoundStrength, -1.0F, aX, aY, aZ);
      this.checkEnchantmentEffects(aStack);
      if(this.mToolStrength > 1.0F && (double)Block.field_71973_m[aID].func_71934_m(aWorld, aX, aY, aZ) != 0.0D) {
         GT_ModHandler.damageOrDechargeItem(aStack, this.getDamagePerBlockBreak(), this.mEUperBrokenBlock < 0?this.getDamagePerBlockBreak() * 1000:this.mEUperBrokenBlock, aPlayer);
         this.checkEnchantmentEffects(aStack);
      }

      return true;
   }

   public final boolean func_77630_h(ItemStack aStack) {
      return false;
   }

   public boolean canProvideEnergy(ItemStack aStack) {
      return false;
   }

   public final int getChargedItemId(ItemStack aStack) {
      return this.mChargedGTID < 0?this.field_77779_bT:GregTech_API.sItemList[this.mChargedGTID].field_77779_bT;
   }

   public final int getEmptyItemId(ItemStack aStack) {
      return this.mDisChargedGTID < 0?this.field_77779_bT:GregTech_API.sItemList[this.mDisChargedGTID].field_77779_bT;
   }

   public final int getMaxCharge(ItemStack aStack) {
      return this.func_77612_l() * 1000;
   }

   public final int getTier(ItemStack aStack) {
      return this.mTier;
   }

   public final int getTransferLimit(ItemStack aStack) {
      return (int)(Math.pow(2.0D, (double)this.mTier) * 128.0D);
   }
}
