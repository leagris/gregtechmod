package gregtechmod.common.tileentities.automation;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IDigitalChest;
import gregtechmod.api.interfaces.IGregTechDeviceInformation;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_TesseractTerminal;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_MetaTileEntity_TesseractGenerator extends MetaTileEntity {

   public static int TESSERACT_ENERGY_COST = 8;
   public static int TESSERACT_ENERGY_COST_DIMENSIONAL = 32;
   public static final Map<Integer, GT_MetaTileEntity_TesseractGenerator> sTesseractGenerators = new HashMap();
   public byte isWorking = 0;
   public int mFrequency = 0;
   public int oFrequency = 0;
   public int mNeededEnergy = 0;


   public GT_MetaTileEntity_TesseractGenerator(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_TesseractGenerator() {}

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
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isEnetOutput() {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return true;
   }

   public boolean isOutputFacing(byte aSide) {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public int getInvSize() {
      return 0;
   }

   public int getMinimumStoredEU() {
      return this.getBaseMetaTileEntity().getEUCapacity() / 2;
   }

   public int maxEUInput() {
      return 128;
   }

   public int maxEUOutput() {
      return 0;
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
      return sTesseractGenerators.get(Integer.valueOf(this.mFrequency)) == this && this.isWorking >= 20?999:0;
   }

   public int maxProgresstime() {
      return 1000;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_TesseractGenerator();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74768_a("mFrequency", this.mFrequency);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mFrequency = aNBT.func_74762_e("mFrequency");
   }

   public void onConfigLoad(GT_Config aConfig) {
      TESSERACT_ENERGY_COST = aConfig.get(GT_ConfigCategories.machineconfig, "Tesseract.EnergyPerTick", 8);
      TESSERACT_ENERGY_COST_DIMENSIONAL = aConfig.get(GT_ConfigCategories.machineconfig, "Tesseract.InterDimensionalEnergyPerTick", 32);
   }

   public void onServerStart() {
      sTesseractGenerators.clear();
   }

   public void onServerStop() {
      sTesseractGenerators.clear();
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

         GT_Utility.sendChatToPlayer(aPlayer, "Frequency: " + this.mFrequency + (sTesseractGenerators.get(Integer.valueOf(this.mFrequency)) != null && sTesseractGenerators.get(Integer.valueOf(this.mFrequency)) != this?EnumChatFormatting.RED + " (Occupied)":""));
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

         GT_Utility.sendChatToPlayer(aPlayer, "Frequency: " + this.mFrequency + (sTesseractGenerators.get(Integer.valueOf(this.mFrequency)) != null && sTesseractGenerators.get(Integer.valueOf(this.mFrequency)) != this?EnumChatFormatting.RED + " (Occupied)":""));
      }

   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public String[] getInfoData() {
      TileEntity tTileEntity = this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation)tTileEntity).isGivingInformation()?((IGregTechDeviceInformation)tTileEntity).getInfoData():new String[]{"Tesseract Generator", "Freqency:", "" + this.mFrequency, sTesseractGenerators.get(Integer.valueOf(this.mFrequency)) == this && this.isWorking >= 20?"Active":"Inactive"};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public boolean isSendingInformation() {
      TileEntity tTileEntity = this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IGregTechDeviceInformation?((IGregTechDeviceInformation)tTileEntity).isGivingInformation():false;
   }

   public boolean isDigitalChest() {
      TileEntity tTileEntity = this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IDigitalChest?((IDigitalChest)tTileEntity).isDigitalChest():false;
   }

   public ItemStack[] getStoredItemData() {
      TileEntity tTileEntity = this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IDigitalChest?((IDigitalChest)tTileEntity).getStoredItemData():null;
   }

   public void setItemCount(int aCount) {
      TileEntity tTileEntity = this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing());
      if(tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IDigitalChest) {
         ((IDigitalChest)tTileEntity).setItemCount(aCount);
      }

   }

   public int getMaxItemCount() {
      TileEntity tTileEntity = this.getBaseMetaTileEntity().getTileEntityAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IDigitalChest?((IDigitalChest)tTileEntity).getMaxItemCount():0;
   }

   public boolean func_94041_b(int aIndex, ItemStack aStack) {
      IInventory tTileEntity = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_94041_b(aIndex, aStack):false;
   }

   public int[] func_94128_d(int aSide) {
      IInventory tTileEntity = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
      if(tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()) {
         if(tTileEntity instanceof ISidedInventory) {
            return ((ISidedInventory)tTileEntity).func_94128_d(aSide);
         } else {
            int[] rArray = new int[this.func_70302_i_()];

            for(int i = 0; i < this.func_70302_i_(); rArray[i] = i++) {
               ;
            }

            return rArray;
         }
      } else {
         return new int[0];
      }
   }

   public boolean func_102007_a(int aIndex, ItemStack aStack, int aSide) {
      IInventory tTileEntity = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?(tTileEntity instanceof ISidedInventory?((ISidedInventory)tTileEntity).func_102007_a(aIndex, aStack, aSide):true):false;
   }

   public boolean func_102008_b(int aIndex, ItemStack aStack, int aSide) {
      IInventory tTileEntity = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?(tTileEntity instanceof ISidedInventory?((ISidedInventory)tTileEntity).func_102008_b(aIndex, aStack, aSide):true):false;
   }

   public int func_70302_i_() {
      IInventory tTileEntity = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_70302_i_():0;
   }

   public ItemStack func_70301_a(int aIndex) {
      IInventory tTileEntity = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_70301_a(aIndex):null;
   }

   public void func_70299_a(int aIndex, ItemStack aStack) {
      IInventory tTileEntity = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
      if(tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()) {
         tTileEntity.func_70299_a(aIndex, aStack);
      }
   }

   public ItemStack func_70298_a(int aIndex, int aAmount) {
      IInventory tTileEntity = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_70298_a(aIndex, aAmount):null;
   }

   public String func_70303_b() {
      IInventory tTileEntity = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_70303_b():"";
   }

   public int func_70297_j_() {
      IInventory tTileEntity = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.func_70297_j_():0;
   }

   public boolean canFill(ForgeDirection aSide, Fluid aFluid) {
      IFluidHandler tTileEntity = this.getBaseMetaTileEntity().getITankContainerAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.canFill(aSide, aFluid):false;
   }

   public boolean canDrain(ForgeDirection aSide, Fluid aFluid) {
      IFluidHandler tTileEntity = this.getBaseMetaTileEntity().getITankContainerAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.canDrain(aSide, aFluid):false;
   }

   public FluidTankInfo[] getTankInfo(ForgeDirection aSide) {
      IFluidHandler tTileEntity = this.getBaseMetaTileEntity().getITankContainerAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.getTankInfo(aSide):new FluidTankInfo[0];
   }

   public int fill_default(ForgeDirection aDirection, FluidStack aFluid, boolean doFill) {
      IFluidHandler tTileEntity = this.getBaseMetaTileEntity().getITankContainerAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.fill(aDirection, aFluid, doFill):0;
   }

   public FluidStack drain(ForgeDirection aDirection, int maxDrain, boolean doDrain) {
      IFluidHandler tTileEntity = this.getBaseMetaTileEntity().getITankContainerAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.drain(aDirection, maxDrain, doDrain):null;
   }

   public FluidStack drain(ForgeDirection aSide, FluidStack aFluid, boolean doDrain) {
      IFluidHandler tTileEntity = this.getBaseMetaTileEntity().getITankContainerAtSide(this.getBaseMetaTileEntity().getBackFacing());
      return tTileEntity != null && this.getBaseMetaTileEntity().isAllowedToWork()?tTileEntity.drain(aSide, aFluid, doDrain):null;
   }

   public boolean addEnergyConsumption(GT_MetaTileEntity_TesseractTerminal aTerminal) {
      if(!this.getBaseMetaTileEntity().isAllowedToWork()) {
         return false;
      } else {
         this.mNeededEnergy += aTerminal.getBaseMetaTileEntity().getWorld() == this.getBaseMetaTileEntity().getWorld()?TESSERACT_ENERGY_COST:TESSERACT_ENERGY_COST_DIMENSIONAL;
         return true;
      }
   }

   public boolean isValidTesseractGenerator(String aOwnerName, boolean aWorkIrrelevant) {
      return this.getBaseMetaTileEntity() != null && !this.getBaseMetaTileEntity().isInvalidTileEntity() && this.getBaseMetaTileEntity().isAllowedToWork() && (aOwnerName == null || this.getBaseMetaTileEntity().getOwnerName().equals(aOwnerName)) && (aWorkIrrelevant || this.isWorking >= 20);
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         if(this.mFrequency != this.oFrequency) {
            if(sTesseractGenerators.get(Integer.valueOf(this.oFrequency)) == this) {
               sTesseractGenerators.remove(Integer.valueOf(this.oFrequency));
               this.getBaseMetaTileEntity().issueBlockUpdate();
            }

            this.oFrequency = this.mFrequency;
         }

         if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(this.mNeededEnergy, false)) {
            if(sTesseractGenerators.get(Integer.valueOf(this.mFrequency)) == null || !((GT_MetaTileEntity_TesseractGenerator)sTesseractGenerators.get(Integer.valueOf(this.mFrequency))).isValidTesseractGenerator((String)null, true)) {
               sTesseractGenerators.put(Integer.valueOf(this.mFrequency), this);
            }
         } else {
            if(sTesseractGenerators.get(Integer.valueOf(this.mFrequency)) == this) {
               sTesseractGenerators.remove(Integer.valueOf(this.mFrequency));
               this.getBaseMetaTileEntity().issueBlockUpdate();
            }

            this.isWorking = 0;
         }

         if(sTesseractGenerators.get(Integer.valueOf(this.mFrequency)) == this) {
            if(this.isWorking < 20) {
               ++this.isWorking;
            }

            if(this.isWorking == 20) {
               this.getBaseMetaTileEntity().issueBlockUpdate();
               ++this.isWorking;
            }
         } else {
            this.isWorking = 0;
         }

         this.mNeededEnergy = 0;
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?313:(GT_Utility.getOppositeSide(aSide) == aFacing?71:312);
   }

   public String getDescription() {
      return "Generates a Tesseract for the attached Inventory";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

}
