package gregtechmod.mistaqur.nei;

import codechicken.core.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe;
import cpw.mods.fml.common.event.FMLInterModComms;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.mistaqur.nei.NEI_GregTech_Config;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public abstract class GT_RecipeHandler extends TemplateRecipeHandler {

   public static int sOffsetX = 5;
   public static int sOffsetY = 11;


   public GT_RecipeHandler() {
      if(!NEI_GregTech_Config.sIsAdded) {
         FMLInterModComms.sendRuntimeMessage(GregTech_API.gregtechmod, "NEIPlugins", "register-crafting-handler", "gregtech_addon@" + this.getRecipeName() + "@" + this.getRecipeId());
         API.registerRecipeHandler(this);
         API.registerUsageHandler(this);
      }

   }

   public abstract String getRecipeId();

   public abstract Collection<GT_Recipe> getRecipeList();

   public abstract GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe var1);

   public void drawBackground(int recipe) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GuiDraw.changeTexture(this.getGuiTexture());
      GuiDraw.drawTexturedModalRect(0, 0, sOffsetX, sOffsetY, 168, 79);
   }

   public int recipiesPerPage() {
      return 1;
   }

   public void loadTransferRects() {}

   public void loadCraftingRecipes(String outputId, Object ... results) {
      if(outputId.equals(this.getRecipeId())) {
         Iterator i$ = this.getRecipeList().iterator();

         while(i$.hasNext()) {
            GT_Recipe irecipe = (GT_Recipe)i$.next();
            this.arecipes.add(this.getRecipe(irecipe));
         }
      } else {
         super.loadCraftingRecipes(outputId, results);
      }

   }

   public void loadCraftingRecipes(ItemStack result) {
      ItemStack tStack = GT_OreDictUnificator.get(true, result);
      Iterator i$ = this.getRecipeList().iterator();

      while(i$.hasNext()) {
         GT_Recipe irecipe = (GT_Recipe)i$.next();
         GT_RecipeHandler.CachedGT_Recipe recipe = this.getRecipe(irecipe);
         if(recipe.contains(recipe.products, tStack) || recipe.contains(recipe.products, result)) {
            this.arecipes.add(recipe);
         }
      }

   }

   public void loadUsageRecipes(ItemStack ingredient) {
      ItemStack tStack = GT_OreDictUnificator.get(false, ingredient);
      Iterator i$ = this.getRecipeList().iterator();

      while(i$.hasNext()) {
         GT_Recipe irecipe = (GT_Recipe)i$.next();
         GT_RecipeHandler.CachedGT_Recipe recipe = this.getRecipe(irecipe);
         if(recipe.contains(recipe.resources, tStack) || recipe.contains(recipe.resources, ingredient)) {
            this.arecipes.add(recipe);
         }
      }

   }

   public static void drawText(int aX, int aY, String aString, int aColor) {
      Minecraft.func_71410_x().field_71466_p.func_78276_b(aString, aX, aY, aColor);
   }


   public abstract class CachedGT_Recipe extends CachedRecipe {

      public List<PositionedStack> products;
      public List<PositionedStack> resources;


      public CachedGT_Recipe() {
         super(GT_RecipeHandler.this);
      }

      public List<PositionedStack> getIngredients() {
         return this.getCycledIngredients(GT_RecipeHandler.this.cycleticks / 20, this.resources);
      }

      public PositionedStack getResult() {
         return null;
      }

      public List<PositionedStack> getOtherStacks() {
         return this.products;
      }
   }
}
