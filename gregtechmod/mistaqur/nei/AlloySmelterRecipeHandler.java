package gregtechmod.mistaqur.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRectHandler;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_AlloySmelter;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Compressor;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_E_Furnace;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Extractor;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Macerator;
import gregtechmod.common.gui.GT_GUIContainer_Scrapboxinator;
import gregtechmod.mistaqur.nei.GT_RecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

public class AlloySmelterRecipeHandler extends GT_RecipeHandler {

   public void loadTransferRects() {
      try {
         this.transferRects.add(new RecipeTransferRect(new Rectangle(70 - sOffsetX, 24 - sOffsetY, 36, 18), this.getRecipeId(), new Object[0]));
         ArrayList e = new ArrayList();
         ArrayList transferRects2 = new ArrayList();
         e.add(GT_GUIContainer_BasicMachine_AlloySmelter.class);
         transferRects2.add(new RecipeTransferRect(new Rectangle(65, 13, 36, 18), this.getRecipeId(), new Object[0]));
         RecipeTransferRectHandler.registerRectsToGuis(e, transferRects2);
         e = new ArrayList();
         transferRects2 = new ArrayList();
         e.add(GT_GUIContainer_BasicMachine_E_Furnace.class);
         transferRects2.add(new RecipeTransferRect(new Rectangle(65, 13, 36, 18), "smelting", new Object[0]));
         RecipeTransferRectHandler.registerRectsToGuis(e, transferRects2);
         e = new ArrayList();
         transferRects2 = new ArrayList();
         e.add(GT_GUIContainer_BasicMachine_Macerator.class);
         transferRects2.add(new RecipeTransferRect(new Rectangle(65, 13, 36, 18), "ic2.macerator", new Object[0]));
         RecipeTransferRectHandler.registerRectsToGuis(e, transferRects2);
         e = new ArrayList();
         transferRects2 = new ArrayList();
         e.add(GT_GUIContainer_BasicMachine_Extractor.class);
         transferRects2.add(new RecipeTransferRect(new Rectangle(65, 13, 36, 18), "ic2.extractor", new Object[0]));
         RecipeTransferRectHandler.registerRectsToGuis(e, transferRects2);
         e = new ArrayList();
         transferRects2 = new ArrayList();
         e.add(GT_GUIContainer_BasicMachine_Compressor.class);
         transferRects2.add(new RecipeTransferRect(new Rectangle(65, 13, 36, 18), "ic2.compressor", new Object[0]));
         RecipeTransferRectHandler.registerRectsToGuis(e, transferRects2);
         e = new ArrayList();
         transferRects2 = new ArrayList();
         e.add(GT_GUIContainer_Scrapboxinator.class);
         transferRects2.add(new RecipeTransferRect(new Rectangle(56, 51, 18, 18), "ic2.scrapbox", new Object[0]));
         RecipeTransferRectHandler.registerRectsToGuis(e, transferRects2);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.out);
      }

   }

   public String getRecipeName() {
      return "Alloy Smelter";
   }

   public String getRecipeId() {
      return "gregtech.Alloy";
   }

   public String getGuiTexture() {
      return "gregtech_addon:textures/gui/NEIAlloySmelter.png";
   }

   public String getOverlayIdentifier() {
      return "gregtech.Alloy";
   }

   public Collection<GT_Recipe> getRecipeList() {
      return GT_Recipe.sAlloySmelterRecipes;
   }

   public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
      return new AlloySmelterRecipeHandler.CachedAlloySmelterRecipe(irecipe);
   }

   public void drawExtras(int recipe) {
      Integer time = Integer.valueOf(((AlloySmelterRecipeHandler.CachedAlloySmelterRecipe)this.arecipes.get(recipe)).mDuration);
      drawText(30, 80, "EU: " + GT_Utility.parseNumberToString(time.intValue() * ((AlloySmelterRecipeHandler.CachedAlloySmelterRecipe)this.arecipes.get(recipe)).mEUt), -16777216);
      drawText(30, 90, "Time: " + GT_Utility.parseNumberToString(time.intValue() / 20) + " secs", -16777216);
      drawText(30, 100, "MaxEnergy: " + GT_Utility.parseNumberToString(((AlloySmelterRecipeHandler.CachedAlloySmelterRecipe)this.arecipes.get(recipe)).mEUt) + " EU/t", -16777216);
   }

   public class CachedAlloySmelterRecipe extends GT_RecipeHandler.CachedGT_Recipe {

      public int mDuration;
      public int mEUt;


      public CachedAlloySmelterRecipe(GT_Recipe aRecipe) {
         super();
         this.resources = new ArrayList();
         if(aRecipe.getRepresentativeInput1() != null) {
            this.resources.add(new PositionedStack(aRecipe.getRepresentativeInput1(), 35 - GT_RecipeHandler.sOffsetX, 25 - GT_RecipeHandler.sOffsetY));
         }

         if(aRecipe.getRepresentativeInput2() != null) {
            this.resources.add(new PositionedStack(aRecipe.getRepresentativeInput2(), 53 - GT_RecipeHandler.sOffsetX, 25 - GT_RecipeHandler.sOffsetY));
         }

         this.products = new ArrayList();
         if(aRecipe.getOutput(0) != null) {
            this.products.add(new PositionedStack(aRecipe.getOutput(0), 107 - GT_RecipeHandler.sOffsetX, 25 - GT_RecipeHandler.sOffsetY));
         }

         this.mDuration = aRecipe.mDuration;
         this.mEUt = aRecipe.mEUt;
      }
   }
}
