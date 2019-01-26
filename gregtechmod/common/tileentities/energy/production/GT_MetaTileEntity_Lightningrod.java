package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Lightningrod extends MetaTileEntity {

   public int mOutput = 0;
   public static int sLightningEnergy = 25000000;


   public GT_MetaTileEntity_Lightningrod(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Lightningrod() {}

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide > 1;
   }

   public boolean isTeleporterCompatible() {
      return false;
   }

   public int maxEUOutput() {
      return 8192;
   }

   public int maxEUStore() {
      return sLightningEnergy;
   }

   public int getInvSize() {
      return 0;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public void onConfigLoad(GT_Config aConfig) {
      sLightningEnergy = aConfig.get(GT_ConfigCategories.machineconfig, "LightningRod.EnergyPerStrike", sLightningEnergy);
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      return false;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Lightningrod();
   }

   public void saveNBTData(NBTTagCompound aNBT) {}

   public void loadNBTData(NBTTagCompound aNBT) {}

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().getTimer() % 256L == 0L && (this.getBaseMetaTileEntity().getWorld().func_72911_I() || this.getBaseMetaTileEntity().getWorld().func_72896_J() && this.getBaseMetaTileEntity().getRandomNumber(10) == 0)) {
         int rodvalue = 0;
         boolean rodvalid = true;

         for(int i = this.getBaseMetaTileEntity().getYCoord() + 1; i < this.getBaseMetaTileEntity().getWorld().func_72800_K() - 1; ++i) {
            if(rodvalid && this.getBaseMetaTileEntity().getBlockOffset(0, i - this.getBaseMetaTileEntity().getYCoord(), 0) == GT_Utility.getBlockFromStack(GT_ModHandler.getIC2Item("ironFence", 1L))) {
               ++rodvalue;
            } else {
               rodvalid = false;
               if(!this.getBaseMetaTileEntity().getAirOffset(0, i - this.getBaseMetaTileEntity().getYCoord(), 0)) {
                  rodvalue = 0;
                  break;
               }
            }
         }

         if(!this.getBaseMetaTileEntity().getWorld().func_72911_I() && this.getBaseMetaTileEntity().getYCoord() + rodvalue < 128) {
            rodvalue = 0;
         }

         if(this.getBaseMetaTileEntity().getRandomNumber(4096 * this.getBaseMetaTileEntity().getWorld().func_72800_K()) < rodvalue * (this.getBaseMetaTileEntity().getYCoord() + rodvalue)) {
            this.setEUVar(sLightningEnergy);
            this.getBaseMetaTileEntity().getWorld().func_72942_c(new EntityLightningBolt(this.getBaseMetaTileEntity().getWorld(), (double)this.getBaseMetaTileEntity().getXCoord(), (double)(this.getBaseMetaTileEntity().getYCoord() + rodvalue), (double)this.getBaseMetaTileEntity().getZCoord()));
         }
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?2:(aSide == 1?21:311);
   }

   public String getDescription() {
      return "Generates Power during Thunderstorms";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

}
