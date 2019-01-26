package gregtechmod.api.util;

import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class GT_PulverizerRecipe {

   private final ItemStack mInput;
   private final ItemStack mOutput1;
   private final ItemStack mOutput2;
   private final int mChance;


   public GT_PulverizerRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance) {
      this.mInput = GT_Utility.copy(new Object[]{aInput});
      this.mOutput1 = GT_Utility.copy(new Object[]{aOutput1});
      this.mOutput2 = GT_Utility.copy(new Object[]{aOutput2});
      this.mChance = aChance;
   }

   public ItemStack getInput() {
      return GT_Utility.copy(new Object[]{this.mInput});
   }

   public ItemStack getPrimaryOutput() {
      return GT_Utility.copy(new Object[]{this.mOutput1});
   }

   public ItemStack getSecondaryOutput() {
      return GT_Utility.copy(new Object[]{this.mOutput2});
   }

   public int getSecondaryOutputChance() {
      return this.mChance;
   }

   public int getEnergy() {
      return 400;
   }
}
