package gregtechmod.mistaqur.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRectHandler;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Canner;
import gregtechmod.mistaqur.nei.GT_RecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

public class CannerRecipeHandler extends GT_RecipeHandler {

   public void loadTransferRects() {
      try {
         this.transferRects.add(new RecipeTransferRect(new Rectangle(70 - sOffsetX, 24 - sOffsetY, 36, 18), this.getRecipeId(), new Object[0]));
         ArrayList e = new ArrayList();
         ArrayList transferRects2 = new ArrayList();
         e.add(GT_GUIContainer_BasicMachine_Canner.class);
         transferRects2.add(new RecipeTransferRect(new Rectangle(65, 13, 36, 18), this.getRecipeId(), new Object[0]));
         RecipeTransferRectHandler.registerRectsToGuis(e, transferRects2);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.out);
      }

   }

   public String getRecipeName() {
      return "Automatic Canning Machine";
   }

   public String getRecipeId() {
      return "gregtech.Canner";
   }

   public String getGuiTexture() {
      return "gregtech_addon:textures/gui/NEICanner.png";
   }

   public String getOverlayIdentifier() {
      return "gregtech.Canner";
   }

   public Collection<GT_Recipe> getRecipeList() {
      return GT_Recipe.sCannerRecipes;
   }

   public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
      return new CannerRecipeHandler.CachedCannerRecipe(irecipe);
   }

   public void drawExtras(int recipe) {
      Integer time = Integer.valueOf(((CannerRecipeHandler.CachedCannerRecipe)this.arecipes.get(recipe)).mDuration);
      drawText(30, 80, "EU: " + GT_Utility.parseNumberToString(time.intValue() * ((CannerRecipeHandler.CachedCannerRecipe)this.arecipes.get(recipe)).mEUt), -16777216);
      drawText(30, 90, "Time: " + GT_Utility.parseNumberToString(time.intValue() / 20) + " secs", -16777216);
      drawText(30, 100, "MaxEnergy: " + GT_Utility.parseNumberToString(((CannerRecipeHandler.CachedCannerRecipe)this.arecipes.get(recipe)).mEUt) + " EU/t", -16777216);
   }

   public class CachedCannerRecipe extends GT_RecipeHandler.CachedGT_Recipe {

      public int mDuration;
      public int mEUt;


      public CachedCannerRecipe(GT_Recipe aRecipe) {
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

         if(aRecipe.getOutput(1) != null) {
            this.products.add(new PositionedStack(aRecipe.getOutput(1), 125 - GT_RecipeHandler.sOffsetX, 25 - GT_RecipeHandler.sOffsetY));
         }

         this.mDuration = aRecipe.mDuration;
         this.mEUt = aRecipe.mEUt;
      }
   }
}
