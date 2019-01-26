package gregtechmod.api.interfaces;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public interface IGT_RecipeAdder {

   boolean addFusionReactorRecipe(ItemStack var1, ItemStack var2, ItemStack var3, int var4, int var5, int var6);

   boolean addCentrifugeRecipe(ItemStack var1, int var2, ItemStack var3, ItemStack var4, ItemStack var5, ItemStack var6, int var7);

   boolean addElectrolyzerRecipe(ItemStack var1, int var2, ItemStack var3, ItemStack var4, ItemStack var5, ItemStack var6, int var7, int var8);

   boolean addChemicalRecipe(ItemStack var1, ItemStack var2, ItemStack var3, int var4);

   boolean addBlastRecipe(ItemStack var1, ItemStack var2, ItemStack var3, ItemStack var4, int var5, int var6, int var7);

   boolean addCannerRecipe(ItemStack var1, ItemStack var2, ItemStack var3, ItemStack var4, int var5, int var6);

   boolean addAlloySmelterRecipe(ItemStack var1, ItemStack var2, ItemStack var3, int var4, int var5);

   boolean addCNCRecipe(ItemStack var1, ItemStack var2, int var3, int var4);

   boolean addAssemblerRecipe(ItemStack var1, ItemStack var2, ItemStack var3, int var4, int var5);

   boolean addForgeHammerRecipe(ItemStack var1, ItemStack var2, int var3, int var4);

   boolean addWiremillRecipe(ItemStack var1, ItemStack var2, int var3, int var4);

   boolean addBenderRecipe(ItemStack var1, ItemStack var2, int var3, int var4);

   boolean addExtruderRecipe(ItemStack var1, ItemStack var2, ItemStack var3, int var4, int var5);

   boolean addImplosionRecipe(ItemStack var1, int var2, ItemStack var3, ItemStack var4);

   @Deprecated
   boolean addGrinderRecipe(ItemStack var1, int var2, ItemStack var3, ItemStack var4, ItemStack var5, ItemStack var6);

   boolean addGrinderRecipe(ItemStack var1, ItemStack var2, ItemStack var3, ItemStack var4, ItemStack var5, ItemStack var6);

   boolean addDistillationRecipe(ItemStack var1, int var2, ItemStack var3, ItemStack var4, ItemStack var5, ItemStack var6, int var7, int var8);

   boolean addLatheRecipe(ItemStack var1, ItemStack var2, ItemStack var3, int var4, int var5);

   boolean addCutterRecipe(ItemStack var1, ItemStack var2, int var3, int var4);

   boolean addVacuumFreezerRecipe(ItemStack var1, ItemStack var2, int var3);

   boolean addSawmillRecipe(ItemStack var1, int var2, ItemStack var3, ItemStack var4, ItemStack var5);

   boolean addSawmillRecipe(ItemStack var1, ItemStack var2, ItemStack var3, ItemStack var4, ItemStack var5);

   boolean addFuel(ItemStack var1, ItemStack var2, int var3, int var4);

   boolean addJackHammerMinableBlock(Block var1, boolean var2);

   boolean addSonictronSound(ItemStack var1, String var2);

   boolean addComputercubeDescriptionSet(ItemStack[] var1, String[] var2);
}
