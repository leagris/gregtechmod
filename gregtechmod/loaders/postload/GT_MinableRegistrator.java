package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GT_MinableRegistrator implements Runnable {

   public void run() {
      GT_Log.out.println("GT_Mod: Adding JackHammer minable Blocks.");
      GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.field_72014_bd, false);
      ItemStack tStack = GT_ModHandler.getIC2Item("constructionFoam", 1L);
      if(tStack != null) {
         GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.field_71973_m[((ItemBlock)tStack.func_77973_b()).func_77883_f()], false);
      }

      tStack = GT_ModHandler.getIC2Item("constructionFoamWall", 1L);
      if(tStack != null) {
         GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.field_71973_m[((ItemBlock)tStack.func_77973_b()).func_77883_f()], false);
      }

      tStack = GT_ModHandler.getIC2Item("reinforcedStone", 1L);
      if(tStack != null) {
         GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.field_71973_m[((ItemBlock)tStack.func_77973_b()).func_77883_f()], true);
      }

      tStack = GT_ModHandler.getIC2Item("reinforcedGlass", 1L);
      if(tStack != null) {
         GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.field_71973_m[((ItemBlock)tStack.func_77973_b()).func_77883_f()], true);
      }

      tStack = GT_ModHandler.getIC2Item("reinforcedDoorBlock", 1L);
      if(tStack != null) {
         GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.field_71973_m[((ItemBlock)tStack.func_77973_b()).func_77883_f()], true);
      }

      GT_Log.out.println("GT_Mod: Adding Blocks to the Miners Valuable List.");
      GT_ModHandler.addValuableOre(Block.field_72014_bd.field_71990_ca, 0, 1);
      GT_ModHandler.addValuableOre(Block.field_72013_bc.field_71990_ca, 0, 1);
   }
}
