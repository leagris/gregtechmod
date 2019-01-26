package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.util.GT_Recipe;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_PlasmaGenerator extends GT_MetaTileEntity_BasicGenerator {

   public GT_MetaTileEntity_PlasmaGenerator(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_PlasmaGenerator() {}

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing();
   }

   public int maxEUOutput() {
      return this.getBaseMetaTileEntity().isAllowedToWork()?2048:0;
   }

   public int maxEUStore() {
      return 1000000000;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 121);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_PlasmaGenerator();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?18:(aActive?20:19);
   }

   public String getDescription() {
      return "Harness " + this.getEfficiency() + "% of the immense Power of Plasma";
   }

   public List<GT_Recipe> getRecipes() {
      return GT_Recipe.sPlasmaFuels;
   }

   public int getEfficiency() {
      return 100;
   }
}
