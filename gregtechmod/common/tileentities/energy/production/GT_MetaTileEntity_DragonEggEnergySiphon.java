package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.common.aura.AuraManager;

public class GT_MetaTileEntity_DragonEggEnergySiphon extends MetaTileEntity {

   public static int sDragonEggEnergyPerTick = 128;
   public static boolean sAllowMultipleEggs = true;
   public static boolean sAllowFlux = true;
   public static GT_MetaTileEntity_DragonEggEnergySiphon mActiveSiphon = null;


   public GT_MetaTileEntity_DragonEggEnergySiphon(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_DragonEggEnergySiphon() {}

   public boolean unbreakable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return false;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide != 1;
   }

   public int maxEUOutput() {
      return sDragonEggEnergyPerTick;
   }

   public int getInvSize() {
      return 0;
   }

   public int maxEUStore() {
      return this.getMinimumStoredEU() + sDragonEggEnergyPerTick * 2;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_DragonEggEnergySiphon();
   }

   public void saveNBTData(NBTTagCompound aNBT) {}

   public void loadNBTData(NBTTagCompound aNBT) {}

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      return false;
   }

   public void onConfigLoad(GT_Config aConfig) {
      sDragonEggEnergyPerTick = aConfig.get(GT_ConfigCategories.machineconfig, "DragonEggEnergySiphon.EnergyPerTick", 1024);
      sAllowMultipleEggs = aConfig.get(GT_ConfigCategories.machineconfig, "DragonEggEnergySiphon.AllowMultipleEggs", false);
      sAllowFlux = aConfig.get(GT_ConfigCategories.machineconfig, "DragonEggEnergySiphon.OutputFlux", true);
   }

   public void onServerStart() {
      mActiveSiphon = null;
   }

   public void onServerStop() {
      mActiveSiphon = null;
   }

   public void onFirstTick() {
      mActiveSiphon = null;
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != 1;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         this.getBaseMetaTileEntity().setActive(false);
         if(this.getBaseMetaTileEntity().isAllowedToWork() && this.hasEgg()) {
            this.getBaseMetaTileEntity().setActive(true);
            if(this.getBaseMetaTileEntity().increaseStoredEnergyUnits(sDragonEggEnergyPerTick, false) && sAllowFlux) {
               try {
                  ObjectTags e = (ObjectTags)ObjectTags.class.newInstance();
                  switch(this.getBaseMetaTileEntity().getRandomNumber(1000)) {
                  case 0:
                     e.add(EnumTag.MECHANISM, 3);
                     break;
                  case 1:
                     e.add(EnumTag.CONTROL, 1);
                     break;
                  case 2:
                     e.add(EnumTag.VOID, 1);
                     break;
                  case 3:
                     e.add(EnumTag.FLUX, 2);
                     break;
                  case 4:
                     e.add(EnumTag.ELDRITCH, 2);
                     break;
                  case 5:
                     e.add(EnumTag.EXCHANGE, 1);
                     break;
                  case 6:
                     e.add(EnumTag.MAGIC, 1);
                     break;
                  case 7:
                     e.add(EnumTag.POWER, 1);
                     break;
                  case 8:
                     e.add(EnumTag.MOTION, 3);
                     break;
                  case 9:
                     e.add(EnumTag.SPIRIT, 5);
                     break;
                  default:
                     e = null;
                  }

                  if(e != null) {
                     AuraManager.addFluxToClosest(this.getBaseMetaTileEntity().getWorld(), (float)this.getBaseMetaTileEntity().getXCoord(), (float)this.getBaseMetaTileEntity().getYCoord(), (float)this.getBaseMetaTileEntity().getZCoord(), e);
                  }
               } catch (Throwable var2) {
                  ;
               }
            }

            if(mActiveSiphon != this && !sAllowMultipleEggs) {
               if(mActiveSiphon != null && mActiveSiphon.getBaseMetaTileEntity() != null && !mActiveSiphon.getBaseMetaTileEntity().isInvalidTileEntity() && mActiveSiphon.hasEgg()) {
                  this.getBaseMetaTileEntity().doExplosion(Integer.MAX_VALUE);
               } else {
                  mActiveSiphon = this;
               }
            }
         } else if(mActiveSiphon == this) {
            mActiveSiphon = null;
         }
      }

   }

   public String[] getInfoData() {
      return new String[]{this.getBaseMetaTileEntity().isActive()?"Active":"Inactive", "Output:", sDragonEggEnergyPerTick + " EU/t"};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public String getDescription() {
      return "Harness the teleportation Power of the Dragon Egg!";
   }

   public void inValidate() {
      if(mActiveSiphon == this) {
         mActiveSiphon = null;
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 1?89:(aActive?87:83);
   }

   public boolean hasEgg() {
      return Block.field_72084_bK == this.getBaseMetaTileEntity().getBlockOffset(0, 1, 0);
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

}
