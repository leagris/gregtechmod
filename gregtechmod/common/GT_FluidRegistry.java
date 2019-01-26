package gregtechmod.common;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.util.GT_LanguageManager;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GT_FluidRegistry {

   public static ArrayList<Fluid> sFluids = new ArrayList();


   public static void addFluid(String aName, String aLocalized, Materials aMaterial, int aState, ItemStack aFullContainer, ItemStack aEmptyContainer) {
      aName = aName.toLowerCase();
      Fluid tFluid = new Fluid(aName);
      GT_LanguageManager.addStringLocalization(tFluid.getUnlocalizedName(), aLocalized == null?aName:aLocalized);
      if(FluidRegistry.registerFluid(tFluid)) {
         sFluids.add(tFluid);
         switch(aState) {
         case 0:
            tFluid.setGaseous(false);
            tFluid.setViscosity(10000);
            break;
         case 1:
            tFluid.setGaseous(false);
            tFluid.setViscosity(1000);
            break;
         case 2:
            tFluid.setGaseous(true);
            tFluid.setDensity(-100);
            tFluid.setViscosity(200);
            break;
         case 3:
            tFluid.setGaseous(true);
            tFluid.setDensity(-10000);
            tFluid.setViscosity(10);
            tFluid.setLuminosity(15);
         }
      } else {
         tFluid = FluidRegistry.getFluid(aName);
      }

      if(aMaterial != null) {
         switch(aState) {
         case 0:
            aMaterial.mSolid = new FluidStack(tFluid, 1000);
            break;
         case 1:
            aMaterial.mFluid = new FluidStack(tFluid, 1000);
            break;
         case 2:
            aMaterial.mGas = new FluidStack(tFluid, 1000);
            break;
         case 3:
            aMaterial.mPlasma = new FluidStack(tFluid, 1000);
         }
      }

      if(aFullContainer != null && aEmptyContainer != null) {
         FluidContainerRegistry.registerFluidContainer(new FluidStack(tFluid, 1000), aFullContainer, aEmptyContainer);
      }

   }

}
