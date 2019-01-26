package gregtechmod.common.tileentities.storage;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.common.tileentities.storage.GT_MetaTileEntity_AdvancedCraftingTable;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeCraftingTable extends GT_MetaTileEntity_AdvancedCraftingTable {

   public GT_MetaTileEntity_BronzeCraftingTable(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BronzeCraftingTable() {}

   public boolean isElectric() {
      return false;
   }

   public boolean isPneumatic() {
      return false;
   }

   public boolean isSteampowered() {
      return false;
   }

   public boolean isTransformerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return false;
   }

   public boolean isEnetInput() {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return false;
   }

   public int maxEUInput() {
      return 0;
   }

   public int maxEUStore() {
      return 0;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_BronzeCraftingTable();
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 161);
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return GregTech_API.getCoverBehavior(aCoverID).isSimpleCover();
   }

   public int rechargerSlotStartIndex() {
      return 0;
   }

   public int rechargerSlotCount() {
      return 0;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?315:(aSide == 1?317:(aFacing != 0 && aFacing != 1?(aFacing != 2 && aFacing != 3?320:319):318));
   }

   public String getDescription() {
      return "For the smaller Projects";
   }

   public int getCapacity() {
      return 16000;
   }
}
