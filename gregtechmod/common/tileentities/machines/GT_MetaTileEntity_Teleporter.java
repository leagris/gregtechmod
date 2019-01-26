package gregtechmod.common.tileentities.machines;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_Utility;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

public class GT_MetaTileEntity_Teleporter extends MetaTileEntity {

   public int mTargetX = 0;
   public int mTargetY = 0;
   public int mTargetZ = 0;
   public int mTargetD = 0;
   public int mCharge = 0;
   public boolean mDebug = false;
   public boolean hasEgg = false;
   public static boolean sInterDimensionalTeleportAllowed = true;


   public GT_MetaTileEntity_Teleporter(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Teleporter() {}

   public boolean isBatteryUpgradable() {
      return true;
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
      return 1000000;
   }

   public int maxEUStore() {
      return 10000000;
   }

   public int maxMJStore() {
      return 10000000;
   }

   public int maxSteamStore() {
      return 10000000;
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public int getInvSize() {
      return 1;
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      if(GT_Utility.isDebugItem(aPlayer.func_71045_bC())) {
         this.mDebug = true;
      } else if(aSide != this.getBaseMetaTileEntity().getFrontFacing()) {
         this.getBaseMetaTileEntity().openGUI(aPlayer, 152);
      }

      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Teleporter();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mTargetX", this.mTargetX);
      aNBT.func_74768_a("mTargetY", this.mTargetY);
      aNBT.func_74768_a("mTargetZ", this.mTargetZ);
      aNBT.func_74768_a("mTargetD", this.mTargetD);
      aNBT.func_74768_a("mCharge", this.mCharge);
      aNBT.func_74757_a("mDebug", this.mDebug);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mTargetX = aNBT.func_74762_e("mTargetX");
      this.mTargetY = aNBT.func_74762_e("mTargetY");
      this.mTargetZ = aNBT.func_74762_e("mTargetZ");
      this.mTargetD = aNBT.func_74762_e("mTargetD");
      this.mCharge = aNBT.func_74762_e("mCharge");
      this.mDebug = aNBT.func_74767_n("mDebug");
   }

   public void onConfigLoad(GT_Config aConfig) {
      sInterDimensionalTeleportAllowed = aConfig.get(GT_ConfigCategories.machineconfig, "Teleporter.Interdimensional", true);
   }

   public void onFirstTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.mTargetX == 0 && this.mTargetY == 0 && this.mTargetZ == 0 && this.mTargetD == 0) {
            this.mTargetX = this.getBaseMetaTileEntity().getXCoord();
            this.mTargetY = this.getBaseMetaTileEntity().getYCoord();
            this.mTargetZ = this.getBaseMetaTileEntity().getZCoord();
            this.mTargetD = this.getBaseMetaTileEntity().getWorld().field_73011_w.field_76574_g;
         }

         this.hasEgg = this.checkForEgg();
      }

   }

   public boolean checkForEgg() {
      for(byte i = -5; i <= 5; ++i) {
         for(byte j = -5; j <= 5; ++j) {
            for(byte k = -5; k <= 5; ++k) {
               if(this.getBaseMetaTileEntity().getBlockOffset(i, j, k) == Block.field_72084_bK) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean hasDimensionalTeleportCapability() {
      return this.mDebug || this.hasEgg;
   }

   public boolean isDimensionalTeleportAvailable() {
      return this.mDebug || this.hasDimensionalTeleportCapability() && GT_Utility.isRealDimension(this.mTargetD) && GT_Utility.isRealDimension(this.getBaseMetaTileEntity().getWorld().field_73011_w.field_76574_g);
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.getBaseMetaTileEntity().getTimer() % 100L == 50L) {
            this.hasEgg = this.checkForEgg();
         }

         if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().getRedstone()) {
            this.mCharge -= 8192;
            if(this.mCharge < 0) {
               this.mCharge = 0;
            }

            int tDistance = this.distanceCalculation();
            boolean tCost = false;
            Iterator i$ = this.getBaseMetaTileEntity().getWorld().func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), 2) - 1), (double)(this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), 2) - 1), (double)(this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), 2) - 1), (double)(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), 2) + 2), (double)(this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), 2) + 2), (double)(this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), 2) + 2))).iterator();

            while(i$.hasNext()) {
               Object tObject = i$.next();
               if(tObject instanceof Entity && !((Entity)tObject).field_70128_L) {
                  Entity tEntity = (Entity)tObject;
                  int tCost1;
                  if((this.mCharge < (tCost1 = (int)((float)(tDistance * tDistance) * weightCalculation(tEntity))) || tCost1 < 0) && !this.mDebug) {
                     int tCharge = this.getBaseMetaTileEntity().getUniversalEnergyStored();
                     if(tCharge > 0 && this.mCharge + tCharge > 0) {
                        this.mCharge += tCharge;
                        this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCharge, true);
                     }
                  } else {
                     if(!this.mDebug) {
                        this.mCharge -= tCost1;
                     }

                     if(tEntity.field_70154_o != null) {
                        tEntity.func_70078_a((Entity)null);
                     }

                     if(tEntity.field_70153_n != null) {
                        tEntity.field_70153_n.func_70078_a((Entity)null);
                     }

                     if(this.mTargetD == this.getBaseMetaTileEntity().getWorld().field_73011_w.field_76574_g || !this.isDimensionalTeleportAvailable() || !GT_Utility.moveEntityToDimensionAtCoords(tEntity, this.mTargetD, (double)this.mTargetX + 0.5D, (double)this.mTargetY + 0.5D, (double)this.mTargetZ + 0.5D)) {
                        if(tEntity instanceof EntityLivingBase) {
                           ((EntityLivingBase)tEntity).func_70634_a((double)this.mTargetX + 0.5D, (double)this.mTargetY + 0.5D, (double)this.mTargetZ + 0.5D);
                        } else {
                           tEntity.func_70107_b((double)this.mTargetX + 0.5D, (double)this.mTargetY + 0.5D, (double)this.mTargetZ + 0.5D);
                        }
                     }
                  }
               }
            }

            this.getBaseMetaTileEntity().setActive(true);
         } else {
            this.getBaseMetaTileEntity().setActive(false);
         }
      }

   }

   private int distanceCalculation() {
      return Math.abs((this.mTargetD != this.getBaseMetaTileEntity().getWorld().field_73011_w.field_76574_g && this.isDimensionalTeleportAvailable()?100:1) * (int)Math.sqrt(Math.pow((double)(this.getBaseMetaTileEntity().getXCoord() - this.mTargetX), 2.0D) + Math.pow((double)(this.getBaseMetaTileEntity().getYCoord() - this.mTargetY), 2.0D) + Math.pow((double)(this.getBaseMetaTileEntity().getZCoord() - this.mTargetZ), 2.0D)));
   }

   private static float weightCalculation(Entity aEntity) {
      try {
         if(aEntity instanceof EntityFX) {
            return -1.0F;
         }
      } catch (Throwable var4) {
         ;
      }

      if(aEntity instanceof EntityFishHook) {
         return -1.0F;
      } else if(aEntity instanceof EntityDragonPart) {
         return -1.0F;
      } else if(aEntity instanceof EntityWeatherEffect) {
         return -1.0F;
      } else if(aEntity instanceof EntityPlayer) {
         EntityPlayer tPlayer = (EntityPlayer)aEntity;
         int tCount = 64;

         int i;
         for(i = 0; i < 36; ++i) {
            if(tPlayer.field_71071_by.func_70301_a(i) != null) {
               tCount += tPlayer.field_71071_by.func_70301_a(i).func_77976_d() > 1?tPlayer.field_71071_by.func_70301_a(i).field_77994_a:64;
            }
         }

         for(i = 0; i < 4; ++i) {
            if(tPlayer.field_71071_by.field_70460_b[i] != null) {
               tCount += 256;
            }
         }

         return Math.min(5.0F, (float)tCount / 666.6F);
      } else {
         return GT_Utility.getClassName(aEntity).equals("EntityItnt")?5.0F:(GT_Utility.getClassName(aEntity).equals("EntityNuke")?50.0F:(aEntity instanceof EntityArrow?0.001F:(aEntity instanceof EntityBoat?0.1F:(aEntity instanceof EntityEnderCrystal?2.0F:(aEntity instanceof EntityEnderEye?0.001F:(aEntity instanceof EntityFallingSand?0.01F:(aEntity instanceof EntityFireball?0.001F:(aEntity instanceof EntityFireworkRocket?0.001F:(aEntity instanceof EntityHanging?0.005F:(aEntity instanceof EntityItem?0.001F:(aEntity instanceof EntityLiving?0.5F:(aEntity instanceof EntityMinecart?0.1F:(aEntity instanceof EntityThrowable?0.001F:(aEntity instanceof EntityTNTPrimed?5.0F:(aEntity instanceof EntityXPOrb?0.001F:-1.0F)))))))))))))));
      }
   }

   public String[] getInfoData() {
      return new String[]{"Charge:", this.mCharge + " EU", "Coordinates:", "X: " + this.mTargetX, "Y: " + this.mTargetY, "Z: " + this.mTargetZ, "Dimension: " + this.mTargetD};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?(aActive?271:270):63;
   }

   public String getDescription() {
      return "Teleport long distances with this little device.";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

}
