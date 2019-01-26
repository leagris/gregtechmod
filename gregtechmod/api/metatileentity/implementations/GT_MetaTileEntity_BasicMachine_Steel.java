package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;

public abstract class GT_MetaTileEntity_BasicMachine_Steel extends GT_MetaTileEntity_BasicMachine_Bronze {

   public GT_MetaTileEntity_BasicMachine_Steel(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BasicMachine_Steel() {}

   public int getTopFacingInactive() {
      return 354;
   }

   public int getTopFacingPipe() {
      return 357;
   }

   public int getBottomFacingInactive() {
      return 355;
   }

   public int getBottomFacingPipe() {
      return 358;
   }

   public int getSideFacingInactive() {
      return 356;
   }

   public int getSideFacingPipe() {
      return 359;
   }

   public float getSteamDamage() {
      return 12.0F;
   }
}
