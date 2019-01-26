package gregtechmod.loaders.postload;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_BaseCrop;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_CropLoader implements Runnable {

   public void run() {
      GT_Log.out.println("GT_Mod: Register Crops to IC2.");

      try {
         new GT_BaseCrop(124, "Indigo", "Eloraam", GT_Items.Crop_Drop_Indigo.get(1L, new Object[0]), (ItemStack[])null, GT_Items.Crop_Drop_Indigo.get(4L, new Object[0]), 2, 4, 0, 1, 4, 1, 1, 0, 4, 0, new String[]{"Flower", "Color", "Ingredient"});
         new GT_BaseCrop(125, "Flax", "Eloraam", new ItemStack(Item.field_77683_K, 1), (ItemStack[])null, (ItemStack)null, 2, 4, 0, 1, 4, 1, 1, 2, 0, 1, new String[]{"Silk", "Vine", "Addictive"});
         new GT_BaseCrop(126, "Oilberries", "Spacetoad", GT_Items.Crop_Drop_OilBerry.get(1L, new Object[0]), (ItemStack[])null, (ItemStack)null, 9, 4, 0, 1, 4, 6, 1, 2, 1, 12, new String[]{"Fire", "Dark", "Reed", "Rotten", "Coal", "Oil"});
         new GT_BaseCrop(127, "Bobsyeruncleranks", "GenerikB", GT_Items.Crop_Drop_BobsYerUncleRanks.get(1L, new Object[0]), new ItemStack[]{new ItemStack(Item.field_77817_bH, 1)}, (ItemStack)null, 11, 4, 0, 1, 4, 4, 0, 8, 2, 9, new String[]{"Shiny", "Vine", "Emerald", "Berylium", "Crystal"});
         new GT_BaseCrop(128, "Diareed", "Direwolf20", GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)Materials.Diamond, 1L), new ItemStack[]{new ItemStack(Item.field_77702_n, 1)}, (ItemStack)null, 12, 4, 0, 1, 4, 5, 0, 10, 2, 10, new String[]{"Fire", "Shiny", "Reed", "Coal", "Diamond", "Crystal"});
         new GT_BaseCrop(129, "Withereed", "CovertJaguar", GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Coal, 1L), new ItemStack[]{new ItemStack(Item.field_77705_m, 1), new ItemStack(Item.field_77705_m, 1)}, (ItemStack)null, 8, 4, 0, 1, 4, 2, 0, 4, 1, 3, new String[]{"Fire", "Undead", "Reed", "Coal", "Rotten", "Wither"});
         new GT_BaseCrop(130, "Blazereed", "Mr. Brain", new ItemStack(Item.field_77722_bw, 1), new ItemStack[]{new ItemStack(Item.field_77731_bo, 1)}, (ItemStack)null, 6, 4, 0, 1, 4, 0, 4, 1, 0, 0, new String[]{"Fire", "Blaze", "Reed", "Sulfur"});
         new GT_BaseCrop(131, "Eggplant", "Link", new ItemStack(Item.field_77764_aP, 1), new ItemStack[]{new ItemStack(Item.field_77735_bk, 1), new ItemStack(Item.field_77676_L, 1), new ItemStack(Item.field_77676_L, 1), new ItemStack(Item.field_77676_L, 1)}, (ItemStack)null, 6, 3, 900, 2, 3, 0, 4, 1, 0, 0, new String[]{"Chicken", "Egg", "Edible", "Feather", "Flower", "Addictive"});
         new GT_BaseCrop(132, "Corium", "Gregorius Techneticies", new ItemStack(Item.field_77770_aF, 1), (ItemStack[])null, (ItemStack)null, 6, 4, 0, 1, 4, 0, 2, 3, 1, 0, new String[]{"Cow", "Silk", "Vine"});
         new GT_BaseCrop(133, "Corpseplant", "Mr. Kenny", new ItemStack(Item.field_77737_bm, 1), new ItemStack[]{new ItemStack(Item.field_77756_aW, 1, 15), new ItemStack(Item.field_77756_aW, 1, 15), new ItemStack(Item.field_77755_aX, 1)}, (ItemStack)null, 5, 4, 0, 1, 4, 0, 2, 1, 0, 3, new String[]{"Toxic", "Undead", "Vine", "Edible", "Rotten"});
         new GT_BaseCrop(134, "Creeperweed", "General Spaz", GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Gunpowder, 1L), (ItemStack[])null, (ItemStack)null, 7, 4, 0, 1, 4, 3, 0, 5, 1, 3, new String[]{"Creeper", "Vine", "Explosive", "Fire", "Sulfur", "Saltpeter", "Coal"});
         new GT_BaseCrop(135, "Enderbloom", "RichardG", GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.EnderPearl, 1L), new ItemStack[]{new ItemStack(Item.field_77730_bn, 1), new ItemStack(Item.field_77730_bn, 1), new ItemStack(Item.field_77748_bA, 1)}, (ItemStack)null, 10, 4, 0, 1, 4, 5, 0, 2, 1, 6, new String[]{"Ender", "Flower", "Shiny"});
         new GT_BaseCrop(136, "Meatrose", "VintageBeef", new ItemStack(Item.field_77756_aW, 1, 9), new ItemStack[]{new ItemStack(Item.field_77741_bi, 1), new ItemStack(Item.field_77784_aq, 1), new ItemStack(Item.field_77735_bk, 1), new ItemStack(Item.field_77754_aU, 1)}, (ItemStack)null, 7, 4, 1500, 1, 4, 0, 4, 1, 3, 0, new String[]{"Edible", "Flower", "Cow", "Fish", "Chicken", "Pig"});
         new GT_BaseCrop(137, "Milkwart", "Mr. Brain", GT_Items.Crop_Drop_MilkWart.get(1L, new Object[0]), (ItemStack[])null, (ItemStack)null, 6, 3, 900, 1, 3, 0, 3, 0, 1, 0, new String[]{"Edible", "Milk", "Cow"});
         new GT_BaseCrop(138, "Slimeplant", "Neowulf", new ItemStack(Item.field_77761_aM, 1), (ItemStack[])null, (ItemStack)null, 6, 4, 0, 3, 4, 3, 0, 0, 0, 2, new String[]{"Slime", "Bouncy", "Sticky", "Bush"});
         new GT_BaseCrop(139, "Spidernip", "Mr. Kenny", new ItemStack(Item.field_77683_K, 1), new ItemStack[]{new ItemStack(Item.field_77728_bu, 1), new ItemStack(Block.field_71955_W, 1)}, (ItemStack)null, 4, 4, 600, 1, 4, 2, 1, 4, 1, 3, new String[]{"Toxic", "Silk", "Spider", "Flower", "Ingredient", "Addictive"});
         new GT_BaseCrop(140, "Tearstalks", "Neowulf", new ItemStack(Item.field_77732_bp, 1), (ItemStack[])null, (ItemStack)null, 8, 4, 0, 1, 4, 1, 2, 0, 0, 0, new String[]{"Healing", "Nether", "Ingredient", "Reed", "Ghast"});
         new GT_BaseCrop(141, "Tine", "Gregorius Techneticies", GT_Items.Crop_Drop_Tine.get(1L, new Object[0]), (ItemStack[])null, (ItemStack)null, 5, 3, 0, 2, 3, 2, 0, 3, 0, 0, new String[]{"Shiny", "Metal", "Pine", "Tin", "Bush"});
         new GT_BaseCrop(142, "Coppon", "Mr. Brain", GT_Items.Crop_Drop_Coppon.get(1L, new Object[0]), (ItemStack[])null, (ItemStack)null, 6, 3, 0, 2, 3, 2, 0, 1, 1, 1, new String[]{"Shiny", "Metal", "Cotton", "Copper", "Bush"});
         new GT_BaseCrop(143, "Brown Mushrooms", "Mr. Brain", new ItemStack(Block.field_72109_af, 1), (ItemStack[])null, new ItemStack(Block.field_72109_af, 4), 1, 3, 0, 1, 3, 0, 2, 0, 0, 2, new String[]{"Edible", "Mushroom", "Ingredient"});
         new GT_BaseCrop(144, "Red Mushrooms", "Mr. Kenny", new ItemStack(Block.field_72103_ag, 1), (ItemStack[])null, new ItemStack(Block.field_72103_ag, 4), 1, 3, 0, 1, 3, 0, 1, 3, 0, 2, new String[]{"Toxic", "Mushroom", "Ingredient"});
         new GT_BaseCrop(145, "Argentia", "Eloraam", GT_Items.Crop_Drop_Argentia.get(1L, new Object[0]), (ItemStack[])null, (ItemStack)null, 7, 4, 0, 3, 4, 2, 0, 1, 0, 0, new String[]{"Shiny", "Metal", "Silver", "Reed"});
         new GT_BaseCrop(146, "Plumbilia", "KingLemming", GT_Items.Crop_Drop_Plumbilia.get(1L, new Object[0]), (ItemStack[])null, (ItemStack)null, 6, 4, 0, 3, 4, 2, 0, 3, 1, 1, new String[]{"Heavy", "Metal", "Lead", "Reed"});
      } catch (Throwable var2) {
         GT_Log.err.println("GT_Mod: Failed to register Crops to IC2.");
      }

   }
}
