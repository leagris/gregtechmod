package gregtechmod.mistaqur.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.gui.GT_GUIContainer_AdvancedWorkbench;
import gregtechmod.common.gui.GT_GUIContainer_BronzeWorkbench;
import gregtechmod.mistaqur.nei.AlloySmelterRecipeHandler;
import gregtechmod.mistaqur.nei.AssemblerRecipeHandler;
import gregtechmod.mistaqur.nei.BenderRecipeHandler;
import gregtechmod.mistaqur.nei.BlastRecipeHandler;
import gregtechmod.mistaqur.nei.CannerRecipeHandler;
import gregtechmod.mistaqur.nei.CentrifugeRecipeHandler;
import gregtechmod.mistaqur.nei.ChemicalRecipeHandler;
import gregtechmod.mistaqur.nei.CutterRecipeHandler;
import gregtechmod.mistaqur.nei.DistillationRecipeHandler;
import gregtechmod.mistaqur.nei.ElectrolyzerRecipeHandler;
import gregtechmod.mistaqur.nei.ExtruderRecipeHandler;
import gregtechmod.mistaqur.nei.FusionRecipeHandler;
import gregtechmod.mistaqur.nei.GrinderRecipeHandler;
import gregtechmod.mistaqur.nei.ImplosionRecipeHandler;
import gregtechmod.mistaqur.nei.LatheRecipeHandler;
import gregtechmod.mistaqur.nei.SawmillRecipeHandler;
import gregtechmod.mistaqur.nei.VacuumFreezerRecipeHandler;
import gregtechmod.mistaqur.nei.WiremillRecipeHandler;

public class NEI_GregTech_Config implements IConfigureNEI {

   public static boolean sIsAdded = true;


   public void loadConfig() {
      sIsAdded = false;
      new CentrifugeRecipeHandler();
      new ElectrolyzerRecipeHandler();
      new ChemicalRecipeHandler();
      new VacuumFreezerRecipeHandler();
      new GrinderRecipeHandler();
      new BlastRecipeHandler();
      new SawmillRecipeHandler();
      new ImplosionRecipeHandler();
      new FusionRecipeHandler();
      new DistillationRecipeHandler();
      new WiremillRecipeHandler();
      new AlloySmelterRecipeHandler();
      new CannerRecipeHandler();
      new BenderRecipeHandler();
      new AssemblerRecipeHandler();
      new LatheRecipeHandler();
      new CutterRecipeHandler();
      new ExtruderRecipeHandler();

      try {
         Class.forName("codechicken.nei.api.API");
         API.registerGuiOverlay(GT_GUIContainer_AdvancedWorkbench.class, "crafting", 57, 22);
         API.registerGuiOverlayHandler(GT_GUIContainer_AdvancedWorkbench.class, new DefaultOverlayHandler(57, 22), "crafting");
         API.registerGuiOverlay(GT_GUIContainer_BronzeWorkbench.class, "crafting", 57, 22);
         API.registerGuiOverlayHandler(GT_GUIContainer_BronzeWorkbench.class, new DefaultOverlayHandler(57, 22), "crafting");
      } catch (Throwable var2) {
         if(GregTech_API.DEBUG_MODE) {
            var2.printStackTrace(GT_Log.err);
         }
      }

      sIsAdded = true;
   }

   public String getName() {
      return "GregTech NEI Plugin";
   }

   public String getVersion() {
      return "(3.02c)";
   }

}
