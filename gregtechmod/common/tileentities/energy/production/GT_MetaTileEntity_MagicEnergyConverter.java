package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.util.GT_Recipe;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.common.aura.AuraManager;

public class GT_MetaTileEntity_MagicEnergyConverter extends GT_MetaTileEntity_BasicGenerator {

   public GT_MetaTileEntity_MagicEnergyConverter(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_MagicEnergyConverter() {}

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 125);
   }

   public int maxEUOutput() {
      return this.getBaseMetaTileEntity().isAllowedToWork()?24:0;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_MagicEnergyConverter();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?(aActive?86:83):90;
   }

   public String getDescription() {
      return "A Generator running off Magic Fuel (" + this.getEfficiency() + "%)";
   }

   public List<GT_Recipe> getRecipes() {
      return GT_Recipe.sMagicFuels;
   }

   public int getEfficiency() {
      return 100;
   }

   public void onExplosion() {
      try {
         ObjectTags e = (ObjectTags)ObjectTags.class.newInstance();
         e.add(EnumTag.MECHANISM, 20 + this.getBaseMetaTileEntity().getRandomNumber(20));
         e.add(EnumTag.DESTRUCTION, 20 + this.getBaseMetaTileEntity().getRandomNumber(20));
         e.add(EnumTag.FLUX, 20 + this.getBaseMetaTileEntity().getRandomNumber(20));
         e.add(EnumTag.EVIL, 20 + this.getBaseMetaTileEntity().getRandomNumber(20));
         e.add(EnumTag.FIRE, 20 + this.getBaseMetaTileEntity().getRandomNumber(20));
         e.add(EnumTag.DARK, 20 + this.getBaseMetaTileEntity().getRandomNumber(20));
         e.add(EnumTag.POWER, 20 + this.getBaseMetaTileEntity().getRandomNumber(20));
         e.add(EnumTag.EXCHANGE, 20 + this.getBaseMetaTileEntity().getRandomNumber(20));
         AuraManager.addFluxToClosest(this.getBaseMetaTileEntity().getWorld(), (float)this.getBaseMetaTileEntity().getXCoord(), (float)this.getBaseMetaTileEntity().getYCoord(), (float)this.getBaseMetaTileEntity().getZCoord(), e);
      } catch (Throwable var2) {
         ;
      }

   }
}
