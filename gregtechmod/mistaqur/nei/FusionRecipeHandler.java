package gregtechmod.mistaqur.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRectHandler;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_FusionComputer;
import gregtechmod.mistaqur.nei.GT_RecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

public class FusionRecipeHandler extends GT_RecipeHandler {

   public void loadTransferRects() {
      try {
         this.transferRects.add(new RecipeTransferRect(new Rectangle(47 - sOffsetX, 34 - sOffsetY, 50, 18), this.getRecipeId(), new Object[0]));
         ArrayList e = new ArrayList();
         ArrayList transferRects2 = new ArrayList();
         e.add(GT_GUIContainer_FusionComputer.class);
         transferRects2.add(new RecipeTransferRect(new Rectangle(149, -7, 18, 18), this.getRecipeId(), new Object[0]));
         RecipeTransferRectHandler.registerRectsToGuis(e, transferRects2);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.out);
      }

   }

   public String getRecipeName() {
      return "Fusion Reactor";
   }

   public String getRecipeId() {
      return "gregtech.Fusionreactor";
   }

   public String getGuiTexture() {
      return "gregtech_addon:textures/gui/NEIFusionreactor.png";
   }

   public String getOverlayIdentifier() {
      return "gregtech.fusion";
   }

   public Collection<GT_Recipe> getRecipeList() {
      return GT_Recipe.sFusionRecipes;
   }

   public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
      return new FusionRecipeHandler.CachedFusionRecipe(irecipe);
   }

   public void drawExtras(int recipe) {
      FusionRecipeHandler.CachedFusionRecipe t = (FusionRecipeHandler.CachedFusionRecipe)this.arecipes.get(recipe);
      drawText(30, 80, "Start: " + GT_Utility.parseNumberToString(t.mStartEU) + "EU", -16777216);
      drawText(30, 90, "EU/t: " + GT_Utility.parseNumberToString(t.mEUt), -16777216);
      drawText(30, 100, GT_Utility.parseNumberToString(t.mDuration) + " Ticks", -16777216);
      if(t.mEUt < 0) {
         drawText(30, 110, "IN: " + GT_Utility.parseNumberToString(-t.mEUt * t.mDuration) + "EU", -16777216);
      } else {
         drawText(30, 110, "OUT: " + GT_Utility.parseNumberToString(t.mEUt * t.mDuration) + "EU", -16777216);
      }

   }

   public class CachedFusionRecipe extends GT_RecipeHandler.CachedGT_Recipe {

      public int mDuration;
      public int mEUt;
      public int mStartEU;


      public CachedFusionRecipe(GT_Recipe irecipe) {
         super();
         this.resources = new ArrayList();
         if(irecipe.getRepresentativeInput1() != null) {
            this.resources.add(new PositionedStack(irecipe.getRepresentativeInput1(), 48 - GT_RecipeHandler.sOffsetX, 17 - GT_RecipeHandler.sOffsetY));
         }

         if(irecipe.getRepresentativeInput2() != null) {
            this.resources.add(new PositionedStack(irecipe.getRepresentativeInput2(), 48 - GT_RecipeHandler.sOffsetX, 53 - GT_RecipeHandler.sOffsetY));
         }

         this.products = new ArrayList();
         if(irecipe.getOutput(0) != null) {
            this.products.add(new PositionedStack(irecipe.getOutput(0), 108 - GT_RecipeHandler.sOffsetX, 35 - GT_RecipeHandler.sOffsetY));
         }

         this.mDuration = irecipe.mDuration;
         this.mEUt = irecipe.mEUt;
         this.mStartEU = irecipe.mStartEU;
      }
   }
}
