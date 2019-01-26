package gregtechmod.loaders.postload;

import gregtechmod.GT_Mod;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.MinecraftForge;

public class GT_BlockResistanceLoader implements Runnable {

   public void run() {
      Block.field_72081_al.func_71894_b(20.0F);
      Block.field_111032_cD.func_71894_b(15.0F);
      Block.field_111039_cA.func_71894_b(15.0F);
      Block.field_72083_ai.func_71894_b(30.0F);
      Block.field_72071_ax.func_71894_b(60.0F);
      if(GT_Mod.sHardRock) {
         Block.field_71981_t.field_71989_cb = 16.0F;
         Block.field_72081_al.field_71989_cb = 32.0F;
         Block.field_111032_cD.field_71989_cb = 32.0F;
         Block.field_111039_cA.field_71989_cb = 32.0F;
         Block.field_71978_w.field_71989_cb = 12.0F;
         Block.field_72007_bm.field_71989_cb = 24.0F;
      }

      MinecraftForge.setBlockHarvestLevel(Block.field_71959_S, "axe", 0);
      MinecraftForge.setBlockHarvestLevel(Block.field_111038_cB, "axe", 0);
      MinecraftForge.setBlockHarvestLevel(Block.field_72091_am, "pickaxe", 0);
      MinecraftForge.setBlockHarvestLevel(Block.field_71945_L, "axe", 0);
      MinecraftForge.setBlockHarvestLevel(Block.field_72006_bl, "pickaxe", 0);
      GT_Utility.callMethod(Material.field_76262_s, "func_85158_p", true, false, false, new Object[0]);
      GT_Utility.callMethod(Material.field_76262_s, "setAdventureModeExempt", true, false, false, new Object[0]);

      try {
         ItemAxe.field_77868_c[0] = Block.field_71959_S;
         ItemAxe.field_77868_c[1] = Block.field_111038_cB;
         ItemAxe.field_77868_c[2] = Block.field_71945_L;
         ItemPickaxe.field_77867_c[0] = Block.field_72006_bl;
         ItemPickaxe.field_77867_c[3] = Block.field_72091_am;
      } catch (Throwable var2) {
         ;
      }

   }
}
