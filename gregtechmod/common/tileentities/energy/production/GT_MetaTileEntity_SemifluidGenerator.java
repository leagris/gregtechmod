package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.util.GT_Recipe;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_SemifluidGenerator extends GT_MetaTileEntity_BasicGenerator {

   public GT_MetaTileEntity_SemifluidGenerator(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_SemifluidGenerator() {}

   public boolean isFacingValid(byte aFacing) {
      return false;
   }

   public int maxEUOutput() {
      return this.getBaseMetaTileEntity().isAllowedToWork()?8:0;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 120);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_SemifluidGenerator();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?38:(aSide == 1?29:85);
   }

   public String getDescription() {
      return "Makes Energy of more dense Fluids (" + this.getEfficiency() + "%)";
   }

   public List<GT_Recipe> getRecipes() {
      return GT_Recipe.sDenseLiquidFuels;
   }

   public int getEfficiency() {
      return 100;
   }
}
