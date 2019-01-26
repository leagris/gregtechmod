package gregtechmod.common.tileentities.automation;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_TesseractGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public class GT_MetaTileEntity_TesseractTerminal extends MetaTileEntity {

   public int mFrequency = 0;
   public boolean mDidWork = false;
   public static boolean sInterDimensionalTesseractAllowed = true;


   public GT_MetaTileEntity_TesseractTerminal(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_TesseractTerminal() {}

   public boolean isTransformerUpgradable() {
      return false;
   }

   public boolean isOverclockerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return false;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public int getInvSize() {
      return 0;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean ownerControl() {
      return true;
   }

   public boolean unbreakable() {
      return true;
   }

   public int getProgresstime() {
      return this.getTesseract(this.mFrequency, false) != null?999:0;
   }

   public int maxProgresstime() {
      return 1000;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_TesseractTerminal();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mFrequency", this.mFrequency);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mFrequency = aNBT.func_74762_e("mFrequency");
   }

   public void onConfigLoad(GT_Config aConfig) {
      sInterDimensionalTesseractAllowed = aConfig.get(GT_ConfigCategories.machineconfig, "Tesseract.Interdimensional", true);
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getFrontFacing()) {
         float[] tCoords = GT_Utility.getClickedFacingCoords(aSide, aX, aY, aZ);
         switch((byte)((byte)((int)(tCoords[0] * 2.0F)) + 2 * (byte)((int)(tCoords[1] * 2.0F)))) {
         case 0:
            --this.mFrequency;
            break;
         case 1:
            ++this.mFrequency;
         }

         GT_Utility.sendChatToPlayer(aPlayer, "Frequency: " + this.mFrequency + (this.getTesseract(this.mFrequency, false) == null?"":EnumChatFormatting.GREEN + " (Connected)"));
      }

      return true;
   }

   public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getFrontFacing()) {
         float[] tCoords = GT_Utility.getClickedFacingCoords(aSide, aX, aY, aZ);
         switch((byte)((byte)((int)(tCoords[0] * 2.0F)) + 2 * (byte)((int)(tCoords[1] * 2.0F)))) {
         case 0:
            this.mFrequency -= 64;
            break;
         case 1:
            this.mFrequency += 64;
            break;
         case 2:
            this.mFrequency -= 512;
            break;
         case 3:
            this.mFrequency += 512;
         }

         GT_Utility.sendChatToPlayer(aPlayer, "Frequency: " + this.mFrequency + (this.getTesseract(this.mFrequency, false) == null?"":EnumChatFormatting.GREEN + " (Connected)"));
      }

   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public GT_MetaTileEntity_TesseractGenerator getTesseract(int aFrequency, boolean aWorkIrrelevant) {
      GT_MetaTileEntity_TesseractGenerator rTesseract = (GT_MetaTileEntity_TesseractGenerator)GT_MetaTileEntity_TesseractGenerator.sTesseractGenerators.get(Integer.valueOf(aFrequency));
      if(rTesseract == null) {
         return null;
      } else if(rTesseract.mFrequency != aFrequency) {
         GT_MetaTileEntity_TesseractGenerator.sTesseractGenerators.put(Integer.valueOf(aFrequency), (Object)null);
         return null;
      } else {
         return !rTesseract.isValidTesseractGenerator(this.getBaseMetaTileEntity().getOwnerName(), aWorkIrrelevant)?null:(!sInterDimensionalTesseractAllowed && rTesseract.getBaseMetaTileEntity().getWorld() != this.getBaseMetaTileEntity().getWorld()?null:rTesseract);
      }
   }

   public String[] getInfoData() {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork() && tTileEntity.isSendingInformation()?tTileEntity.getInfoData():new String[]{"Tesseract Generator", "Freqency:", "" + this.mFrequency, this.getTesseract(this.mFrequency, false) != null?"Active":"Inactive"};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public boolean isDigitalChest() {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.isDigitalChest():false;
   }

   public ItemStack[] getStoredItemData() {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.getStoredItemData():null;
   }

   public void setItemCount(int aCount) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      if(tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()) {
         tTileEntity.setItemCount(aCount);
      }
   }

   public int getMaxItemCount() {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.getMaxItemCount():0;
   }

   public boolean func_94041_b(int aIndex, ItemStack aStack) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_94041_b(aIndex, aStack):false;
   }

   public int[] func_94128_d(int aSide) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_94128_d(aSide):new int[0];
   }

   public boolean func_102007_a(int aIndex, ItemStack aStack, int aSide) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_102007_a(aIndex, aStack, aSide):false;
   }

   public boolean func_102008_b(int aIndex, ItemStack aStack, int aSide) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_102008_b(aIndex, aStack, aSide):false;
   }

   public int func_70302_i_() {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_70302_i_():0;
   }

   public ItemStack func_70301_a(int aIndex) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_70301_a(aIndex):null;
   }

   public void func_70299_a(int aIndex, ItemStack aStack) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      if(tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()) {
         tTileEntity.func_70299_a(aIndex, aStack);
      }
   }

   public ItemStack func_70298_a(int aIndex, int aAmount) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_70298_a(aIndex, aAmount):null;
   }

   public String func_70303_b() {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_70303_b():"";
   }

   public int func_70297_j_() {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_70297_j_():0;
   }

   public boolean canFill(ForgeDirection aSide, Fluid aFluid) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.canFill(aSide, aFluid):false;
   }

   public boolean canDrain(ForgeDirection aSide, Fluid aFluid) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.canDrain(aSide, aFluid):false;
   }

   public FluidTankInfo[] getTankInfo(ForgeDirection aSide) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.getTankInfo(aSide):new FluidTankInfo[0];
   }

   public int fill_default(ForgeDirection aDirection, FluidStack aFluid, boolean doFill) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.fill(aDirection, aFluid, doFill):0;
   }

   public FluidStack drain(ForgeDirection aDirection, int maxDrain, boolean doDrain) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.drain(aDirection, maxDrain, doDrain):null;
   }

   public FluidStack drain(ForgeDirection aSide, FluidStack aFluid, boolean doDrain) {
      GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, false);
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.drain(aSide, aFluid, doDrain):null;
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isAllowedToWork()) {
         GT_MetaTileEntity_TesseractGenerator tTileEntity = this.getTesseract(this.mFrequency, true);
         if(tTileEntity != null) {
            tTileEntity.addEnergyConsumption(this);
            if(!this.mDidWork && this.getTesseract(this.mFrequency, false) != null) {
               this.mDidWork = true;
               this.getBaseMetaTileEntity().issueBlockUpdate();
            }
         } else if(this.mDidWork) {
            this.mDidWork = false;
            this.getBaseMetaTileEntity().issueBlockUpdate();
         }
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?313:312;
   }

   public String getDescription() {
      return "Accesses Tesseracts remotely";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

}
