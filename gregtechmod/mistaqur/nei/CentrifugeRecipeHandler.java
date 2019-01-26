package gregtechmod.mistaqur.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRectHandler;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_Centrifuge;
import gregtechmod.mistaqur.nei.GT_RecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

public class CentrifugeRecipeHandler extends GT_RecipeHandler {

   public void loadTransferRects() {
      try {
         this.transferRects.add(new RecipeTransferRect(new Rectangle(79 - sOffsetX, 22 - sOffsetY + 7, 18, 12), this.getRecipeId(), new Object[0]));
         this.transferRects.add(new RecipeTransferRect(new Rectangle(67 - sOffsetX, 34 - sOffsetY + 7, 12, 18), this.getRecipeId(), new Object[0]));
         this.transferRects.add(new RecipeTransferRect(new Rectangle(97 - sOffsetX, 34 - sOffsetY + 7, 12, 18), this.getRecipeId(), new Object[0]));
         this.transferRects.add(new RecipeTransferRect(new Rectangle(79 - sOffsetX, 52 - sOffsetY + 7, 18, 12), this.getRecipeId(), new Object[0]));
         ArrayList e = new ArrayList();
         ArrayList transferRects2 = new ArrayList();
         e.add(GT_GUIContainer_Centrifuge.class);
         transferRects2.add(new RecipeTransferRect(new Rectangle(74, 11, 18, 12), this.getRecipeId(), new Object[0]));
         transferRects2.add(new RecipeTransferRect(new Rectangle(62, 23, 12, 18), this.getRecipeId(), new Object[0]));
         transferRects2.add(new RecipeTransferRect(new Rectangle(92, 23, 12, 18), this.getRecipeId(), new Object[0]));
         transferRects2.add(new RecipeTransferRect(new Rectangle(74, 41, 18, 12), this.getRecipeId(), new Object[0]));
         RecipeTransferRectHandler.registerRectsToGuis(e, transferRects2);
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.out);
      }

   }

   public String getRecipeName() {
      return "Industrial Centrifuge";
   }

   public String getRecipeId() {
      return "gregtech.Centrifuge";
   }

   public String getGuiTexture() {
      return "gregtech_addon:textures/gui/NEICentrifuge.png";
   }

   public String getOverlayIdentifier() {
      return "gregtech.centrifuge";
   }

   public Collection<GT_Recipe> getRecipeList() {
      return GT_Recipe.sCentrifugeRecipes;
   }

   public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
      return new CentrifugeRecipeHandler.CachedCentrifugeRecipe(irecipe);
   }

   public void drawExtras(int recipe) {
      Integer time = Integer.valueOf(((CentrifugeRecipeHandler.CachedCentrifugeRecipe)this.arecipes.get(recipe)).mDuration);
      drawText(30, 90, "EU: " + GT_Utility.parseNumberToString(time.intValue() * 5), -16777216);
      drawText(30, 100, "Time: " + GT_Utility.parseNumberToString(time.intValue() / 20) + " secs", -16777216);
   }

   public class CachedCentrifugeRecipe extends GT_RecipeHandler.CachedGT_Recipe {

      public int mDuration;


      public CachedCentrifugeRecipe(GT_Recipe aRecipe) {
         super();
         this.resources = new ArrayList();
         if(aRecipe.getRepresentativeInput1() != null) {
            this.resources.add(new PositionedStack(aRecipe.getRepresentativeInput1(), 80 - GT_RecipeHandler.sOffsetX, 35 - GT_RecipeHandler.sOffsetY + 7));
         }

         if(aRecipe.getRepresentativeInput2() != null) {
            this.resources.add(new PositionedStack(aRecipe.getRepresentativeInput2(), 50 - GT_RecipeHandler.sOffsetX, 5 - GT_RecipeHandler.sOffsetY + 7));
         }

         this.products = new ArrayList();
         if(aRecipe.getOutput(0) != null) {
            this.products.add(new PositionedStack(aRecipe.getOutput(0), 80 - GT_RecipeHandler.sOffsetX, 5 - GT_RecipeHandler.sOffsetY + 7));
         }

         if(aRecipe.getOutput(1) != null) {
            this.products.add(new PositionedStack(aRecipe.getOutput(1), 110 - GT_RecipeHandler.sOffsetX, 35 - GT_RecipeHandler.sOffsetY + 7));
         }

         if(aRecipe.getOutput(2) != null) {
            this.products.add(new PositionedStack(aRecipe.getOutput(2), 80 - GT_RecipeHandler.sOffsetX, 65 - GT_RecipeHandler.sOffsetY + 7));
         }

         if(aRecipe.getOutput(3) != null) {
            this.products.add(new PositionedStack(aRecipe.getOutput(3), 50 - GT_RecipeHandler.sOffsetX, 35 - GT_RecipeHandler.sOffsetY + 7));
         }

         this.mDuration = aRecipe.mDuration;
      }
   }
}
