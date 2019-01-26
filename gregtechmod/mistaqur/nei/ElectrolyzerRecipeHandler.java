package gregtechmod.mistaqur.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRectHandler;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_Electrolyzer;
import gregtechmod.mistaqur.nei.GT_RecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

public class ElectrolyzerRecipeHandler extends GT_RecipeHandler {

   public void loadTransferRects() {
      try {
         this.transferRects.add(new RecipeTransferRect(new Rectangle(74 - sOffsetX, 33 - sOffsetY, 30, 10), this.getRecipeId(), new Object[0]));
         ArrayList e = new ArrayList();
         ArrayList transferRects2 = new ArrayList();
         e.add(GT_GUIContainer_Electrolyzer.class);
         transferRects2.add(new RecipeTransferRect(new Rectangle(69, 22, 30, 10), this.getRecipeId(), new Object[0]));
         RecipeTransferRectHandler.registerRectsToGuis(e, transferRects2);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.out);
      }

   }

   public String getRecipeName() {
      return "Industrial Electrolyzer";
   }

   public String getRecipeId() {
      return "gregtech.Electrolyzer";
   }

   public String getGuiTexture() {
      return "gregtech_addon:textures/gui/NEIElectrolyzer.png";
   }

   public String getOverlayIdentifier() {
      return "gregtech.electrolyzer";
   }

   public Collection<GT_Recipe> getRecipeList() {
      return GT_Recipe.sElectrolyzerRecipes;
   }

   public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
      return new ElectrolyzerRecipeHandler.CachedElectrolyzerRecipe(irecipe);
   }

   public void drawExtras(int recipe) {
      Integer time = Integer.valueOf(((ElectrolyzerRecipeHandler.CachedElectrolyzerRecipe)this.arecipes.get(recipe)).mDuration);
      drawText(30, 80, "EU: " + GT_Utility.parseNumberToString(time.intValue() * ((ElectrolyzerRecipeHandler.CachedElectrolyzerRecipe)this.arecipes.get(recipe)).mEUt), -16777216);
      drawText(30, 90, "Time: " + GT_Utility.parseNumberToString(time.intValue() / 20) + " secs", -16777216);
      drawText(30, 100, "MaxEnergy: " + GT_Utility.parseNumberToString(((ElectrolyzerRecipeHandler.CachedElectrolyzerRecipe)this.arecipes.get(recipe)).mEUt) + " EU/t", -16777216);
   }

   public class CachedElectrolyzerRecipe extends GT_RecipeHandler.CachedGT_Recipe {

      public int mDuration;
      public int mEUt;


      public CachedElectrolyzerRecipe(GT_Recipe aRecipe) {
         super();
         this.resources = new ArrayList();
         if(aRecipe.getRepresentativeInput1() != null) {
            this.resources.add(new PositionedStack(aRecipe.getRepresentativeInput1(), 80 - GT_RecipeHandler.sOffsetX, 46 - GT_RecipeHandler.sOffsetY));
         }

         if(aRecipe.getRepresentativeInput2() != null) {
            this.resources.add(new PositionedStack(aRecipe.getRepresentativeInput2(), 50 - GT_RecipeHandler.sOffsetX, 46 - GT_RecipeHandler.sOffsetY));
         }

         this.products = new ArrayList();
         if(aRecipe.getOutput(0) != null) {
            this.products.add(new PositionedStack(aRecipe.getOutput(0), 50 - GT_RecipeHandler.sOffsetX, 16 - GT_RecipeHandler.sOffsetY));
         }

         if(aRecipe.getOutput(1) != null) {
            this.products.add(new PositionedStack(aRecipe.getOutput(1), 70 - GT_RecipeHandler.sOffsetX, 16 - GT_RecipeHandler.sOffsetY));
         }

         if(aRecipe.getOutput(2) != null) {
            this.products.add(new PositionedStack(aRecipe.getOutput(2), 90 - GT_RecipeHandler.sOffsetX, 16 - GT_RecipeHandler.sOffsetY));
         }

         if(aRecipe.getOutput(3) != null) {
            this.products.add(new PositionedStack(aRecipe.getOutput(3), 110 - GT_RecipeHandler.sOffsetX, 16 - GT_RecipeHandler.sOffsetY));
         }

         this.mDuration = aRecipe.mDuration;
         this.mEUt = aRecipe.mEUt;
      }
   }
}
