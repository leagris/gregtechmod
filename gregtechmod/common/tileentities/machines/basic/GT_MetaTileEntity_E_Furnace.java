package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_E_Furnace extends GT_MetaTileEntity_BasicMachine {

   public int mHeatingCoilTier = 0;


   public GT_MetaTileEntity_E_Furnace(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_E_Furnace() {}

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74774_a("mHeatingCoilTier", (byte)this.mHeatingCoilTier);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mHeatingCoilTier = aNBT.func_74771_c("mHeatingCoilTier");
   }

   public void setItemNBT(NBTTagCompound aNBT) {
      super.setItemNBT(aNBT);
      if(this.mHeatingCoilTier > 0) {
         aNBT.func_74774_a("mHeatingCoilTier", (byte)this.mHeatingCoilTier);
      }

   }

   public void onRightclick(EntityPlayer aPlayer) {
      ItemStack tPlayerItem = aPlayer.field_71071_by.func_70448_g();
      if(this.mHeatingCoilTier <= 0 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier01")) {
         if(!aPlayer.field_71075_bZ.field_75098_d) {
            --tPlayerItem.field_77994_a;
         }

         this.mHeatingCoilTier = 1;
      } else if(this.mHeatingCoilTier == 1 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier02")) {
         if(!aPlayer.field_71075_bZ.field_75098_d) {
            --tPlayerItem.field_77994_a;
         }

         this.mHeatingCoilTier = 2;
      } else if(this.mHeatingCoilTier == 2 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier03")) {
         if(!aPlayer.field_71075_bZ.field_75098_d) {
            --tPlayerItem.field_77994_a;
         }

         this.mHeatingCoilTier = 3;
      } else {
         this.getBaseMetaTileEntity().openGUI(aPlayer, 135);
      }
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_E_Furnace();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[2] != null && null != (this.mOutputItem1 = GT_ModHandler.getSmeltingOutput(this.mInventory[2], true, this.mInventory[3]))) {
         this.mEUt = 3;
         this.mMaxProgresstime = 130 / (1 + this.mHeatingCoilTier);
      }

   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return super.allowPutStack(aIndex, aSide, aStack) && GT_ModHandler.getSmeltingOutput(GT_Utility.copyAmount(64L, new Object[]{aStack}), false, (ItemStack)null) != null;
   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(207)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public int getFrontFacingInactive() {
      return 250;
   }

   public int getFrontFacingActive() {
      return 251;
   }

   public String getDescription() {
      return "Not like using a Commodore 64";
   }
}
