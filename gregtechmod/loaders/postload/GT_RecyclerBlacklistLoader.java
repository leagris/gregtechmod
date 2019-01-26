package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_RecyclerBlacklistLoader implements Runnable {

   public void run() {
      GT_Log.out.println("GT_Mod: Adding Stuff to the Recycler Blacklist.");
      if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "easymobgrinderrecycling", true)) {
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.field_77704_l, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.field_77755_aX, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.field_77756_aW, 1, 15));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.field_77737_bm, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.field_77683_K, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.field_77764_aP, 1, 0));
      }

      if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "easystonerecycling", true)) {
         for(ItemStack tStack = new ItemStack(Block.field_71978_w, 1, 0); tStack != null; tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack})) {
            GT_ModHandler.addToRecyclerBlackList(tStack);
         }

         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71939_E, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71957_Q, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71957_Q, 1, 1));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71957_Q, 1, 2));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71957_Q, 1, 3));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71957_Q, 1, 4));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71957_Q, 1, 5));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71957_Q, 1, 6));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71957_Q, 1, 7));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71946_M, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.field_77729_bt, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.field_77726_bs, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getSmeltingOutput(new ItemStack(Block.field_71981_t, 1, 0), false, (ItemStack)null));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71981_t, 1, 0), null, null, new ItemStack(Block.field_71981_t, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71978_w, 1, 0), null, null, new ItemStack(Block.field_71978_w, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71981_t, 1, 0), null, new ItemStack(Block.field_71981_t, 1, 0), null, new ItemStack(Block.field_71981_t, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71981_t, 1, 0), new ItemStack(Block.field_71946_M, 1, 0), new ItemStack(Block.field_71981_t, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71978_w, 1, 0), new ItemStack(Block.field_71946_M, 1, 0), new ItemStack(Block.field_71978_w, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71957_Q, 1, 0), new ItemStack(Block.field_71946_M, 1, 0), new ItemStack(Block.field_71957_Q, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71939_E, 1, 0), new ItemStack(Block.field_71946_M, 1, 0), new ItemStack(Block.field_71939_E, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71957_Q, 1, 0), new ItemStack(Block.field_71957_Q, 1, 0), new ItemStack(Block.field_71957_Q, 1, 0), new ItemStack(Block.field_71957_Q, 1, 0), new ItemStack(Block.field_71957_Q, 1, 0), new ItemStack(Block.field_71957_Q, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71946_M, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71946_M, 1, 0), new ItemStack(Block.field_71946_M, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.field_71946_M, 1, 0), null, null, new ItemStack(Block.field_71946_M, 1, 0)}));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72003_bq, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_82515_ce, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72088_bQ, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72057_aH, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71995_bx, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72052_aC, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72051_aB, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72079_ak, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72085_aj, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72079_ak, 1, 1));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72085_aj, 1, 1));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72079_ak, 1, 3));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72085_aj, 1, 3));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72079_ak, 1, 5));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72085_aj, 1, 5));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72079_ak, 1, 7));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72085_aj, 1, 7));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72044_aK, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72034_aR, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72007_bm, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72007_bm, 1, 1));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72007_bm, 1, 2));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72007_bm, 1, 3));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_71981_t, 1, 0));
         GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72043_aJ, 1, 0));
      }

      GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.field_77768_aD, 1));
      GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72036_aT, 1));
      GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72037_aS, 1));
      GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.field_72039_aU, 1));
   }
}
