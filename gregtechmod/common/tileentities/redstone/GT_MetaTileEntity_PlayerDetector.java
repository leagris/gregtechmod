package gregtechmod.common.tileentities.redstone;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_PlayerDetector extends MetaTileEntity {

   public byte mMode = 0;
   public String mDetectedPlayer = "";


   public GT_MetaTileEntity_PlayerDetector(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_PlayerDetector() {}

   public boolean hasSidedRedstoneOutputBehavior() {
      return true;
   }

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isOverclockerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean ownerControl() {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isTeleporterCompatible() {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return true;
   }

   public int getMinimumStoredEU() {
      return 1000;
   }

   public int maxEUInput() {
      return 128;
   }

   public int maxEUStore() {
      return 10000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public int getInvSize() {
      return 0;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_PlayerDetector();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74774_a("mMode", this.mMode);
      aNBT.func_74778_a("mDetectedPlayer", this.mDetectedPlayer);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mMode = aNBT.func_74771_c("mMode");
      this.mDetectedPlayer = aNBT.func_74779_i("mDetectedPlayer");
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      ItemStack tStack = aPlayer.func_71045_bC();
      if(GT_Utility.areStacksEqual(tStack, GT_OreDictUnificator.get(OrePrefixes.plate, (Object)Materials.Paper, 1L), true)) {
         NBTTagCompound tMessage = tStack.func_77978_p().func_74775_l("display");
         if(tMessage != null) {
            this.mDetectedPlayer = tMessage.func_74779_i("Name");
            return true;
         }
      }

      this.mMode = (byte)((this.mMode + 1) % 3);
      String tMessage1 = null;
      switch(this.mMode) {
      case 0:
         tMessage1 = "Detects all Players";
         break;
      case 1:
         tMessage1 = "Detects only " + this.mDetectedPlayer;
         break;
      case 2:
         tMessage1 = "Detects all Players except " + this.mDetectedPlayer;
      }

      GT_Utility.sendChatToPlayer(aPlayer, tMessage1);
      return true;
   }

   public void onFirstTick() {
      this.getBaseMetaTileEntity().setGenericRedstoneOutput(true);
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().getTimer() % 20L == 10L) {
         this.getBaseMetaTileEntity().setGenericRedstoneOutput(true);
         if(this.mDetectedPlayer != null && !this.mDetectedPlayer.equals("")) {
            byte tRedstone = 0;
            if(this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(50, false)) {
               Iterator i = this.getBaseMetaTileEntity().getWorld().field_72996_f.iterator();

               while(i.hasNext()) {
                  Object tObject = i.next();
                  if(tObject instanceof EntityLivingBase) {
                     EntityLivingBase tEntity = (EntityLivingBase)tObject;
                     int dist = Math.max(1, (int)tEntity.func_70011_f((double)this.getBaseMetaTileEntity().getXCoord() + 0.5D, (double)this.getBaseMetaTileEntity().getYCoord() + 0.5D, (double)this.getBaseMetaTileEntity().getZCoord() + 0.5D));
                     if(dist < 16) {
                        if(this.mMode == 0) {
                           tRedstone = (byte)(16 - dist);
                           break;
                        }

                        if(tEntity.func_70023_ak().equalsIgnoreCase(this.mDetectedPlayer)) {
                           if(this.mMode == 1) {
                              tRedstone = (byte)(16 - dist);
                              break;
                           }
                        } else if(this.mMode == 2) {
                           tRedstone = (byte)(16 - dist);
                           break;
                        }
                     }
                  }
               }
            }

            this.getBaseMetaTileEntity().setActive(tRedstone > 0);

            for(byte var6 = 0; var6 < 6; ++var6) {
               this.getBaseMetaTileEntity().setOutputRedstoneSignal(var6, tRedstone);
            }
         } else {
            this.mDetectedPlayer = this.getBaseMetaTileEntity().getOwnerName();
         }
      }

   }

   public byte getComparatorValue(byte aSide) {
      return (byte)(15 - this.getBaseMetaTileEntity().getOutputRedstoneSignal(aSide));
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aActive?24:23;
   }

   public String getDescription() {
      return "Player Proximity Sensor";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }
}
