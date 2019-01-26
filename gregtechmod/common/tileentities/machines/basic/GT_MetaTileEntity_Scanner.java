package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Scanner extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Scanner(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Scanner() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 180);
   }

   public int getElectricTier() {
      return 3;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Scanner();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[3] != null) {
         this.bOutputBlocked = true;
      } else if(GT_Utility.isStackValid(this.mInventory[1]) && this.mInventory[1].field_77994_a > 0 && GT_Items.IC2_Crop_Seeds.isStackEqual(this.mInventory[1], true, true)) {
         NBTTagCompound tNBT = this.mInventory[1].func_77978_p();
         if(tNBT == null) {
            tNBT = new NBTTagCompound();
         }

         if(tNBT.func_74771_c("scan") < 4) {
            tNBT.func_74774_a("scan", (byte)4);
            this.mMaxProgresstime = 20;
            this.mEUt = 500;
         } else {
            this.mMaxProgresstime = 1;
            this.mEUt = 1;
         }

         --this.mInventory[1].field_77994_a;
         this.mOutputItem1 = GT_Utility.copyAmount(1L, new Object[]{this.mInventory[1]});
         this.mOutputItem1.func_77982_d(tNBT);
         return;
      }

      this.mMaxProgresstime = 0;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return super.allowPutStack(aIndex, aSide, aStack) && GT_Items.IC2_Crop_Seeds.isStackEqual(aStack, true, true);
   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(212)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public boolean hasTwoSeperateInputs() {
      return true;
   }

   public int getTopFacingInactive() {
      return 37;
   }

   public int getTopFacingActive() {
      return 37;
   }

   public int getFrontFacingInactive() {
      return 224;
   }

   public int getFrontFacingActive() {
      return 225;
   }

   public String getDescription() {
      return "Scans Crops and other things.";
   }
}
