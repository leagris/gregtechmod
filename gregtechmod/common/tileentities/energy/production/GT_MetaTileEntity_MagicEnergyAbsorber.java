package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_Log;
import java.util.ArrayList;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.common.aura.AuraManager;
import thaumcraft.common.entities.monster.EntityWisp;

public class GT_MetaTileEntity_MagicEnergyAbsorber extends MetaTileEntity {

   public static int sEnergyPerEnderCrystal = 32;
   public static int sEnergyFromVis = 12800;
   public static final ArrayList<EntityEnderCrystal> sUsedDragonCrystalList = new ArrayList();
   public EntityEnderCrystal mTargetedCrystal;
   public boolean isActive1 = false;
   public boolean isActive2 = false;


   public GT_MetaTileEntity_MagicEnergyAbsorber(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_MagicEnergyAbsorber() {}

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 2;
   }

   public boolean isFacingValid(byte aFacing) {
      return false;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isOutputFacing(byte aSide) {
      return true;
   }

   public int maxEUOutput() {
      return Math.max(128, sEnergyPerEnderCrystal);
   }

   public int getInvSize() {
      return 3;
   }

   public int maxEUStore() {
      return Math.max(1000000, Math.max(sEnergyFromVis, sEnergyPerEnderCrystal));
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 126);
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_MagicEnergyAbsorber();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74757_a("isActive1", this.isActive1);
      aNBT.func_74757_a("isActive2", this.isActive2);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.isActive1 = aNBT.func_74767_n("isActive1");
      this.isActive2 = aNBT.func_74767_n("isActive2");
   }

   public void onConfigLoad(GT_Config aConfig) {
      sEnergyPerEnderCrystal = aConfig.get(GT_ConfigCategories.machineconfig, "MagicEnergyAbsorber.EnergyPerTick.EnderCrystal", 32);
      sEnergyFromVis = aConfig.get(GT_ConfigCategories.machineconfig, "MagicEnergyAbsorber.EnergyPerVis", 12800);
   }

   public void onServerStart() {
      sUsedDragonCrystalList.clear();
   }

   public void onServerStop() {
      sUsedDragonCrystalList.clear();
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().getTimer() % 10L == 0L) {
         if(this.getBaseMetaTileEntity().getUniversalEnergyStored() < this.getBaseMetaTileEntity().getOutputVoltage() * 10 + this.getMinimumStoredEU()) {
            try {
               if(this.mInventory[0] != null && this.mInventory[1] == null) {
                  NBTTagList e;
                  int tList;
                  short tID;
                  short tLevel;
                  Enchantment tEnchantment;
                  if(this.mInventory[0].func_77948_v() && this.mInventory[0].func_77973_b().func_77619_b() > 0) {
                     e = this.mInventory[0].func_77986_q();
                     if(e != null) {
                        for(tList = 0; tList < e.func_74745_c(); ++tList) {
                           tID = ((NBTTagCompound)e.func_74743_b(tList)).func_74765_d("id");
                           tLevel = ((NBTTagCompound)e.func_74743_b(tList)).func_74765_d("lvl");
                           if(tID > -1 && tID < Enchantment.field_77331_b.length) {
                              tEnchantment = Enchantment.field_77331_b[tID];
                              if(tEnchantment != null) {
                                 this.getBaseMetaTileEntity().increaseStoredEnergyUnits(1000000 * tLevel / (tEnchantment.func_77325_b() * tEnchantment.func_77324_c()), true);
                              }
                           }
                        }

                        this.mInventory[0].field_77990_d.func_82580_o("ench");
                     }
                  } else if(this.mInventory[0].func_77973_b() instanceof ItemEnchantedBook) {
                     e = ((ItemEnchantedBook)this.mInventory[0].func_77973_b()).func_92110_g(this.mInventory[0]);
                     if(e != null) {
                        for(tList = 0; tList < e.func_74745_c(); ++tList) {
                           tID = ((NBTTagCompound)e.func_74743_b(tList)).func_74765_d("id");
                           tLevel = ((NBTTagCompound)e.func_74743_b(tList)).func_74765_d("lvl");
                           if(tID > -1 && tID < Enchantment.field_77331_b.length) {
                              tEnchantment = Enchantment.field_77331_b[tID];
                              if(tEnchantment != null) {
                                 this.getBaseMetaTileEntity().increaseStoredEnergyUnits(1000000 * tLevel / (tEnchantment.func_77325_b() * tEnchantment.func_77324_c()), true);
                              }
                           }
                        }

                        this.mInventory[0] = new ItemStack(Item.field_77760_aL, 1);
                     }
                  }

                  this.mInventory[1] = this.mInventory[0];
                  this.mInventory[0] = null;
               }
            } catch (Throwable var7) {
               if(GregTech_API.DEBUG_MODE) {
                  var7.printStackTrace(GT_Log.err);
               }
            }
         }

         if(sEnergyPerEnderCrystal > 0 && this.isActive1) {
            if(this.mTargetedCrystal == null) {
               ArrayList var8 = (ArrayList)this.getBaseMetaTileEntity().getWorld().func_72872_a(EntityEnderCrystal.class, AxisAlignedBB.func_72330_a((double)(this.getBaseMetaTileEntity().getXCoord() - 64), (double)(this.getBaseMetaTileEntity().getYCoord() - 64), (double)(this.getBaseMetaTileEntity().getZCoord() - 64), (double)(this.getBaseMetaTileEntity().getXCoord() + 64), (double)(this.getBaseMetaTileEntity().getYCoord() + 64), (double)(this.getBaseMetaTileEntity().getZCoord() + 64)));
               if(var8 != null && !var8.isEmpty()) {
                  var8.removeAll(sUsedDragonCrystalList);
                  if(var8.size() > 0) {
                     this.mTargetedCrystal = (EntityEnderCrystal)var8.get(0);
                     if(this.mTargetedCrystal != null) {
                        sUsedDragonCrystalList.add(this.mTargetedCrystal);
                     }
                  }
               }
            } else if(this.mTargetedCrystal.func_70089_S()) {
               this.getBaseMetaTileEntity().increaseStoredEnergyUnits(sEnergyPerEnderCrystal * 10, false);
            } else {
               sUsedDragonCrystalList.remove(this.mTargetedCrystal);
               this.mTargetedCrystal = null;
            }
         }

         if(sEnergyFromVis > 0 && this.isActive2 && this.getBaseMetaTileEntity().getUniversalEnergyStored() < sEnergyFromVis) {
            try {
               if(AuraManager.decreaseClosestAura(this.getBaseMetaTileEntity().getWorld(), (double)this.getBaseMetaTileEntity().getXCoord(), (double)this.getBaseMetaTileEntity().getYCoord(), (double)this.getBaseMetaTileEntity().getZCoord(), 1)) {
                  this.getBaseMetaTileEntity().increaseStoredEnergyUnits(sEnergyFromVis, true);
                  ObjectTags var9 = new ObjectTags();
                  var9.add(EnumTag.MECHANISM, 1 + this.getBaseMetaTileEntity().getRandomNumber(3));
                  var9.add(EnumTag.VOID, 1 + this.getBaseMetaTileEntity().getRandomNumber(2));
                  var9.add(EnumTag.FLUX, 1 + this.getBaseMetaTileEntity().getRandomNumber(2));
                  AuraManager.addFluxToClosest(this.getBaseMetaTileEntity().getWorld(), (float)this.getBaseMetaTileEntity().getXCoord(), (float)this.getBaseMetaTileEntity().getYCoord(), (float)this.getBaseMetaTileEntity().getZCoord(), var9);
                  ArrayList var10 = (ArrayList)this.getBaseMetaTileEntity().getWorld().func_72872_a(EntityWisp.class, AxisAlignedBB.func_72330_a((double)(this.getBaseMetaTileEntity().getXCoord() - 8), (double)(this.getBaseMetaTileEntity().getYCoord() - 8), (double)(this.getBaseMetaTileEntity().getZCoord() - 8), (double)(this.getBaseMetaTileEntity().getXCoord() + 8), (double)(this.getBaseMetaTileEntity().getYCoord() + 8), (double)(this.getBaseMetaTileEntity().getZCoord() + 8)));
                  if(!var10.isEmpty()) {
                     this.getBaseMetaTileEntity().doExplosion(8192);
                  }
               }
            } catch (Throwable var6) {
               if(GregTech_API.DEBUG_MODE) {
                  var6.printStackTrace(GT_Log.err);
               }
            }
         }

         this.getBaseMetaTileEntity().setActive(this.getBaseMetaTileEntity().getUniversalEnergyStored() >= this.getBaseMetaTileEntity().getOutputVoltage() + this.getMinimumStoredEU());
      }

   }

   public void onExplosion() {
      if(sEnergyFromVis > 0 && this.isActive2) {
         try {
            ObjectTags e = (ObjectTags)ObjectTags.class.newInstance();
            e.add(EnumTag.MECHANISM, 50 + this.getBaseMetaTileEntity().getRandomNumber(50));
            e.add(EnumTag.DESTRUCTION, 50 + this.getBaseMetaTileEntity().getRandomNumber(50));
            e.add(EnumTag.FLUX, 50 + this.getBaseMetaTileEntity().getRandomNumber(50));
            e.add(EnumTag.EVIL, 50 + this.getBaseMetaTileEntity().getRandomNumber(50));
            e.add(EnumTag.FIRE, 50 + this.getBaseMetaTileEntity().getRandomNumber(50));
            e.add(EnumTag.DARK, 50 + this.getBaseMetaTileEntity().getRandomNumber(50));
            e.add(EnumTag.POWER, 50 + this.getBaseMetaTileEntity().getRandomNumber(50));
            e.add(EnumTag.VOID, 50 + this.getBaseMetaTileEntity().getRandomNumber(50));
            AuraManager.addFluxToClosest(this.getBaseMetaTileEntity().getWorld(), (float)this.getBaseMetaTileEntity().getXCoord(), (float)this.getBaseMetaTileEntity().getYCoord(), (float)this.getBaseMetaTileEntity().getZCoord(), e);
         } catch (Throwable var2) {
            if(GregTech_API.DEBUG_MODE) {
               var2.printStackTrace(GT_Log.err);
            }
         }
      }

   }

   public void inValidate() {
      if(this.mTargetedCrystal != null) {
         sUsedDragonCrystalList.remove(this.mTargetedCrystal);
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aActive?91:92;
   }

   public String getDescription() {
      return "Absorbs Magic Energy and disenchants Items";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 1;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0;
   }

}
