package gregtechmod.api.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class GT_EnergyArmor_Item extends ItemArmor implements ISpecialArmor {

   public int mCharge;
   public int mTransfer;
   public int mTier;
   public int mDamageEnergyCost;
   public int mSpecials;
   public boolean mChargeProvider;
   public double mArmorAbsorbtionPercentage;
   public static Map jumpChargeMap = new HashMap();


   public GT_EnergyArmor_Item(int aID, String aUnlocalized, String aEnglish, int aCharge, int aTransfer, int aTier, int aDamageEnergyCost, int aSpecials, double aArmorAbsorbtionPercentage, boolean aChargeProvider, int aType, int aArmorIndex) {
      super(aID, EnumArmorMaterial.DIAMOND, aArmorIndex, aType);
      this.func_77625_d(1);
      this.func_77656_e(100);
      this.setNoRepair();
      this.func_77655_b(aUnlocalized);
      GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".name", aEnglish);
      this.mCharge = Math.max(1, aCharge);
      this.mTransfer = Math.max(1, aTransfer);
      this.mTier = Math.max(1, aTier);
      this.mSpecials = aSpecials;
      this.mChargeProvider = aChargeProvider;
      this.mDamageEnergyCost = Math.max(0, aDamageEnergyCost);
      this.mArmorAbsorbtionPercentage = aArmorAbsorbtionPercentage;
      this.func_77637_a(GregTech_API.TAB_GREGTECH);
      MinecraftForge.EVENT_BUS.register(this);
   }

   public ItemStack func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      ItemStack tStack = aPlayer.field_71071_by.field_70460_b[3 - this.field_77881_a];
      if(tStack != null) {
         for(int i = 0; i < 9; ++i) {
            if(aPlayer.field_71071_by.field_70462_a[i] == aStack) {
               aPlayer.field_71071_by.field_70460_b[3 - this.field_77881_a] = aPlayer.field_71071_by.field_70462_a[i];
               aPlayer.field_71071_by.field_70462_a[i] = tStack;
               return tStack;
            }
         }
      }

      return super.func_77659_a(aStack, aWorld, aPlayer);
   }

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IconRegister aIconRegister) {
      this.field_77791_bV = aIconRegister.func_94245_a("gregtech_addon:" + this.func_77658_a());
   }

   public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
      aList.add("Tier: " + this.mTier);
      if((this.mSpecials & 1) != 0) {
         aList.add("Rebreather");
      }

      if((this.mSpecials & 2) != 0) {
         aList.add("Inertia Damper");
      }

      if((this.mSpecials & 4) != 0) {
         aList.add("Food Replicator");
      }

      if((this.mSpecials & 8) != 0) {
         aList.add("Medicine Module");
      }

      if((this.mSpecials & 16) != 0) {
         aList.add("Lamp");
      }

      if((this.mSpecials & 32) != 0) {
         aList.add("Solarpanel");
      }

      if((this.mSpecials & 64) != 0) {
         aList.add("Extinguisher Module");
      }

      if((this.mSpecials & 128) != 0) {
         aList.add("Jump Booster");
      }

      if((this.mSpecials & 256) != 0) {
         aList.add("Speed Booster");
      }

      if((this.mSpecials & 512) != 0) {
         aList.add("Invisibility Field");
      }

      if((this.mSpecials & 1024) != 0) {
         aList.add("Infinite Charge");
      }

   }

   private static void setCharge(ItemStack aStack) {
      NBTTagCompound tNBT = aStack.func_77978_p();
      if(tNBT == null) {
         tNBT = new NBTTagCompound();
      }

      tNBT.func_74768_a("charge", 1000000000);
      aStack.func_77982_d(tNBT);
   }

   public void onArmorTickUpdate(World aWorld, EntityPlayer aPlayer, ItemStack aStack) {
      if(this.mSpecials != 0) {
         if(!aPlayer.field_70170_p.field_72995_K && (this.mSpecials & 1) != 0) {
            int tTargetChargeItem = aPlayer.func_70086_ai();
            if(GT_ModHandler.canUseElectricItem(aStack, 1000) && tTargetChargeItem < 50) {
               aPlayer.func_70050_g(tTargetChargeItem + 250);
               GT_ModHandler.useElectricItem(aStack, 1000, aPlayer);
            }
         }

         if(!aPlayer.field_70170_p.field_72995_K && (this.mSpecials & 4) != 0 && GT_ModHandler.canUseElectricItem(aStack, '\uc350') && aPlayer.func_71024_bL().func_75121_c()) {
            aPlayer.func_71024_bL().func_75122_a(1, 0.0F);
            GT_ModHandler.useElectricItem(aStack, '\uc350', aPlayer);
         }

         if((this.mSpecials & 8) != 0) {
            if(GT_ModHandler.canUseElectricItem(aStack, 10000) && aPlayer.func_70644_a(Potion.field_76436_u)) {
               GT_Utility.removePotion(aPlayer, Potion.field_76436_u.field_76415_H);
               GT_ModHandler.useElectricItem(aStack, 10000, aPlayer);
            }

            if(GT_ModHandler.canUseElectricItem(aStack, 100000) && aPlayer.func_70644_a(Potion.field_82731_v)) {
               GT_Utility.removePotion(aPlayer, Potion.field_82731_v.field_76415_H);
               GT_ModHandler.useElectricItem(aStack, 100000, aPlayer);
            }
         }

         if((this.mSpecials & 64) != 0) {
            aPlayer.func_70015_d(0);
         }

         float tTargetChargeItem1;
         if(!aPlayer.field_70170_p.field_72995_K && (this.mSpecials & 128) != 0) {
            tTargetChargeItem1 = jumpChargeMap.containsKey(aPlayer)?((Float)jumpChargeMap.get(aPlayer)).floatValue():1.0F;
            if(GT_ModHandler.canUseElectricItem(aStack, 1000) && aPlayer.field_70122_E && tTargetChargeItem1 < 1.0F) {
               tTargetChargeItem1 = 1.0F;
               GT_ModHandler.useElectricItem(aStack, 1000, aPlayer);
            }

            if(aPlayer.field_70181_x >= 0.0D && tTargetChargeItem1 > 0.0F && !aPlayer.func_70090_H()) {
               if(GT_ModHandler.getJumpKeyDown(aPlayer) && GT_ModHandler.getBoostKeyDown(aPlayer)) {
                  if(tTargetChargeItem1 == 1.0F) {
                     aPlayer.field_70159_w *= 3.5D;
                     aPlayer.field_70179_y *= 3.5D;
                  }

                  aPlayer.field_70181_x += (double)(tTargetChargeItem1 * 0.3F);
                  tTargetChargeItem1 = (float)((double)tTargetChargeItem1 * 0.75D);
               } else if(tTargetChargeItem1 < 1.0F) {
                  tTargetChargeItem1 = 0.0F;
               }
            }

            jumpChargeMap.put(aPlayer, Float.valueOf(tTargetChargeItem1));
         }

         if((this.mSpecials & 256) != 0 && GT_ModHandler.canUseElectricItem(aStack, 100) && aPlayer.func_70051_ag() && (aPlayer.field_70122_E && Math.abs(aPlayer.field_70159_w) + Math.abs(aPlayer.field_70179_y) > 0.10000000149011612D || aPlayer.func_70090_H())) {
            GT_ModHandler.useElectricItem(aStack, 100, aPlayer);
            tTargetChargeItem1 = 0.22F;
            if(aPlayer.func_70090_H()) {
               GT_ModHandler.useElectricItem(aStack, 100, aPlayer);
               tTargetChargeItem1 = 0.1F;
               if(aPlayer.field_70181_x > 0.0D) {
                  aPlayer.field_70181_x += 0.10000000149011612D;
               }
            }

            if(tTargetChargeItem1 > 0.0F) {
               aPlayer.func_70060_a(0.0F, 1.0F, tTargetChargeItem1);
            }
         }

         if((this.mSpecials & 512) != 0 && GT_ModHandler.canUseElectricItem(aStack, 10000)) {
            GT_ModHandler.useElectricItem(aStack, 10000, aPlayer);
            aPlayer.func_70690_d(new PotionEffect(Potion.field_76441_p.func_76396_c(), 25, 1, true));
         }

         if(!aPlayer.field_70170_p.field_72995_K && (this.mSpecials & 48) != 0 && GregTech_API.sWorldTickCounter % 20L == 0L) {
            ItemStack tTargetChargeItem2 = aStack;
            ItemStack tTargetDechargeItem = aStack;
            if(GT_ModHandler.chargeElectricItem(aStack, 1, Integer.MAX_VALUE, true, true) < 1) {
               tTargetChargeItem2 = aPlayer.field_71071_by.field_70460_b[2];
            }

            if(GT_ModHandler.dischargeElectricItem(aStack, 10, Integer.MAX_VALUE, true, true, true) < 10) {
               tTargetDechargeItem = aPlayer.field_71071_by.field_70460_b[2];
            }

            if(tTargetChargeItem2 == null || !GT_ModHandler.isElectricItem(tTargetChargeItem2)) {
               tTargetChargeItem2 = null;
            }

            if(tTargetDechargeItem == null || !GT_ModHandler.isElectricItem(tTargetChargeItem2) || aStack != tTargetDechargeItem && !GT_ModHandler.isChargerItem(tTargetDechargeItem)) {
               tTargetDechargeItem = null;
            }

            if(aPlayer.field_70170_p.func_72935_r() && aPlayer.field_70170_p.func_72937_j(MathHelper.func_76128_c(aPlayer.field_70165_t), MathHelper.func_76128_c(aPlayer.field_70163_u + 1.0D), MathHelper.func_76128_c(aPlayer.field_70161_v))) {
               if((this.mSpecials & 32) != 0 && tTargetChargeItem2 != null) {
                  GT_ModHandler.chargeElectricItem(tTargetChargeItem2, 20, Integer.MAX_VALUE, true, false);
               }
            } else if((this.mSpecials & 16) != 0 && tTargetDechargeItem != null && GT_ModHandler.canUseElectricItem(tTargetDechargeItem, 10)) {
               if(aPlayer.field_70170_p.func_72798_a((int)aPlayer.field_70165_t, (int)aPlayer.field_70163_u + 1, (int)aPlayer.field_70161_v) == 0) {
                  aPlayer.field_70170_p.func_94575_c((int)aPlayer.field_70165_t, (int)aPlayer.field_70163_u + 1, (int)aPlayer.field_70161_v, GregTech_API.sBlockList[3].field_71990_ca);
               }

               GT_ModHandler.useElectricItem(tTargetDechargeItem, 10, aPlayer);
            }
         }

      }
   }

   public boolean func_77651_p() {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void func_77633_a(int aStack, CreativeTabs var2, List var3) {
      ItemStack tCharged = new ItemStack(this, 1);
      ItemStack tUncharged = new ItemStack(this, 1, this.func_77612_l());
      GT_ModHandler.chargeElectricItem(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
      var3.add(tCharged);
      var3.add(tUncharged);
   }

   public boolean canProvideEnergy(ItemStack aStack) {
      if((this.mSpecials & 1024) != 0) {
         setCharge(aStack);
      }

      return this.mChargeProvider;
   }

   public int getChargedItemId(ItemStack aStack) {
      if((this.mSpecials & 1024) != 0) {
         setCharge(aStack);
      }

      return this.field_77779_bT;
   }

   public int getEmptyItemId(ItemStack aStack) {
      if((this.mSpecials & 1024) != 0) {
         setCharge(aStack);
      }

      return this.field_77779_bT;
   }

   public int getMaxCharge(ItemStack aStack) {
      if((this.mSpecials & 1024) != 0) {
         setCharge(aStack);
      }

      return this.mCharge;
   }

   public int getTier(ItemStack aStack) {
      if((this.mSpecials & 1024) != 0) {
         setCharge(aStack);
      }

      return this.mTier;
   }

   public int getTransferLimit(ItemStack aStack) {
      if((this.mSpecials & 1024) != 0) {
         setCharge(aStack);
      }

      return this.mTransfer;
   }

   public int func_77619_b() {
      return 0;
   }

   public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
      return false;
   }

   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return false;
   }

   @ForgeSubscribe
   public void onEntityLivingFallEvent(LivingFallEvent var1) {
      if(!var1.entity.field_70170_p.field_72995_K && var1.entity instanceof EntityPlayer) {
         EntityPlayer var2 = (EntityPlayer)var1.entity;

         for(int i = 0; i < 4; ++i) {
            ItemStack var3 = var2.field_71071_by.field_70460_b[i];
            if(var3 != null && var3.func_77973_b() == this && (this.mSpecials & 2) != 0) {
               int var4 = (int)var1.distance - 3;
               int var5 = this.mDamageEnergyCost * var4 / 4;
               if(var5 <= GT_ModHandler.dischargeElectricItem(var3, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, true)) {
                  GT_ModHandler.dischargeElectricItem(var3, var5, Integer.MAX_VALUE, true, false, true);
                  var1.setCanceled(true);
                  break;
               }
            }
         }
      }

   }

   public ArmorProperties getProperties(EntityLivingBase var1, ItemStack var2, DamageSource var3, double var4, int var6) {
      return new ArmorProperties(var3 == DamageSource.field_76379_h && (this.mSpecials & 2) != 0?10:0, this.getBaseAbsorptionRatio() * this.mArmorAbsorbtionPercentage, this.mDamageEnergyCost > 0?25 * GT_ModHandler.dischargeElectricItem(var2, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, true) / this.mDamageEnergyCost:0);
   }

   public int getArmorDisplay(EntityPlayer var1, ItemStack var2, int var3) {
      return (int)Math.round(20.0D * this.getBaseAbsorptionRatio() * this.mArmorAbsorbtionPercentage);
   }

   public void damageArmor(EntityLivingBase var1, ItemStack var2, DamageSource var3, int var4, int var5) {
      GT_ModHandler.dischargeElectricItem(var2, var4 * this.mDamageEnergyCost, Integer.MAX_VALUE, true, false, true);
   }

   private double getBaseAbsorptionRatio() {
      if(this.mArmorAbsorbtionPercentage <= 0.0D) {
         return 0.0D;
      } else {
         switch(this.field_77881_a) {
         case 0:
            return 0.15D;
         case 1:
            return 0.4D;
         case 2:
            return 0.3D;
         case 3:
            return 0.15D;
         default:
            return 0.0D;
         }
      }
   }

}
