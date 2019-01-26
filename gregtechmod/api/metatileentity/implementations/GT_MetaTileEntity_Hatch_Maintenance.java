package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Hatch_Maintenance extends MetaTileEntity {

   public boolean mDuctTape = false;
   public boolean mWrench = false;
   public boolean mScrewdriver = false;
   public boolean mSoftHammer = false;
   public boolean mHardHammer = false;
   public boolean mSolderingTool = false;
   public boolean mCrowbar = false;


   public GT_MetaTileEntity_Hatch_Maintenance(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Hatch_Maintenance() {}

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex > 0;
   }

   public int getInvSize() {
      return 4;
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getFrontFacing()) {
         this.getBaseMetaTileEntity().openGUI(aPlayer, 155, GregTech_API.gregtechmod);
      }

      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Hatch_Maintenance();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74757_a("mDuctTape", this.mDuctTape);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mDuctTape = aNBT.func_74767_n("mDuctTape");
   }

   public void onValueUpdate(byte aValue) {
      this.mDuctTape = (aValue & 1) != 0;
   }

   public byte getUpdateData() {
      return (byte)(this.mDuctTape?1:0);
   }

   public void onToolClick(ItemStack aStack, EntityLivingBase aPlayer) {
      if(aStack != null && aPlayer != null) {
         if(GT_Utility.isItemStackInList(aStack, GregTech_API.sWrenchList) && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
            this.mWrench = true;
         }

         if(GT_Utility.isItemStackInList(aStack, GregTech_API.sScrewdriverList) && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
            this.mScrewdriver = true;
         }

         if(GT_Utility.isItemStackInList(aStack, GregTech_API.sSoftHammerList) && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
            this.mSoftHammer = true;
         }

         if(GT_Utility.isItemStackInList(aStack, GregTech_API.sHardHammerList) && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
            this.mHardHammer = true;
         }

         if(GT_Utility.isItemStackInList(aStack, GregTech_API.sCrowbarList) && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
            this.mCrowbar = true;
         }

         if(GT_ModHandler.useSolderingIron(aStack, aPlayer)) {
            this.mSolderingTool = true;
         }

         if(GT_OreDictUnificator.isItemStackInstanceOf(aStack, "craftingDuctTape")) {
            this.mDuctTape = this.mWrench = this.mScrewdriver = this.mSoftHammer = this.mHardHammer = this.mCrowbar = this.mSolderingTool = true;
            --aStack.field_77994_a;
         }

      }
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?(this.mDuctTape?295:294):(aSide == 0?32:(aSide == 1?29:40));
   }

   public String getDescription() {
      return "For maintaining Multiblocks";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }
}
