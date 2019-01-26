package gregtechmod.loaders.misc;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.blocks.GT_BlockMetaID_Machine;
import java.util.Collection;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GT_CoverLoader implements Runnable {

   public void run() {
      GregTech_API.sCovers.put(Integer.valueOf(-1), GT_BlockMetaID_Machine.mIcons[51]);
      GregTech_API.sCovers.put(Integer.valueOf(-2), GT_BlockMetaID_Machine.mIcons[95]);
      GregTech_API.registerCover(GregTech_API.getGregTechMaterial(15, 1), GT_BlockMetaID_Machine.mIcons[10]);
      GregTech_API.registerCover(new ItemStack(GregTech_API.sItemList[8], 1, 17809), GT_BlockMetaID_Machine.mIcons[10]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(30, 1), GT_BlockMetaID_Machine.mIcons[260]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(31, 1), GT_BlockMetaID_Machine.mIcons[261]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(15, 1), GT_BlockMetaID_Machine.mIcons[262]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(0, 1), GT_BlockMetaID_Machine.mIcons[289]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(1, 1), GT_BlockMetaID_Machine.mIcons[288]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(4, 1), GT_BlockMetaID_Machine.mIcons[48]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(5, 1), GT_BlockMetaID_Machine.mIcons[263]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(6, 1), GT_BlockMetaID_Machine.mIcons[280]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(7, 1), GT_BlockMetaID_Machine.mIcons[285]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(8, 1), GT_BlockMetaID_Machine.mIcons[283]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(9, 1), GT_BlockMetaID_Machine.mIcons[284]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(10, 1), GT_BlockMetaID_Machine.mIcons[287]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(11, 1), GT_BlockMetaID_Machine.mIcons[286]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(64, 1), GT_BlockMetaID_Machine.mIcons[290]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(65, 1), GT_BlockMetaID_Machine.mIcons[285]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(66, 1), GT_BlockMetaID_Machine.mIcons[285]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(67, 1), GT_BlockMetaID_Machine.mIcons[285]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(69, 1), GT_BlockMetaID_Machine.mIcons[263]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(82, 1), GT_BlockMetaID_Machine.mIcons[264]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(83, 1), GT_BlockMetaID_Machine.mIcons[265]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(84, 1), GT_BlockMetaID_Machine.mIcons[266]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(85, 1), GT_BlockMetaID_Machine.mIcons[267]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(86, 1), GT_BlockMetaID_Machine.mIcons[268]);
      GregTech_API.registerCover(GregTech_API.getGregTechComponent(87, 1), GT_BlockMetaID_Machine.mIcons[269]);

      try {
         GregTech_API.registerCover(GregTech_API.getGregTechMaterial(60, 1), Block.field_71988_x.func_71858_a(0, 0));
         GregTech_API.registerCover(GregTech_API.getGregTechMaterial(61, 1), Block.field_71988_x.func_71858_a(0, 1));
         GregTech_API.registerCover(GregTech_API.getGregTechMaterial(62, 1), Block.field_71988_x.func_71858_a(0, 2));
         GregTech_API.registerCover(GregTech_API.getGregTechMaterial(63, 1), Block.field_71988_x.func_71858_a(0, 3));
      } catch (Throwable var2) {
         GregTech_API.registerCover(GregTech_API.getGregTechMaterial(60, 1), "");
         GregTech_API.registerCover(GregTech_API.getGregTechMaterial(61, 1), "");
         GregTech_API.registerCover(GregTech_API.getGregTechMaterial(62, 1), "");
         GregTech_API.registerCover(GregTech_API.getGregTechMaterial(63, 1), "");
      }

      for(byte i = 0; i < 16; ++i) {
         if(GregTech_API.gregtechmod.isClientSide()) {
            GregTech_API.registerCover(new ItemStack(Block.field_111031_cC, 1, i), Block.field_72101_ab.func_71858_a(0, i));
         } else {
            GregTech_API.registerCover(new ItemStack(Block.field_111031_cC, 1, i), (Icon)null);
         }
      }

      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateCoal"), "coal_block");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateIron"), "iron_block");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGold"), "gold_block");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateLapis"), "lapis_block");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateDiamond"), "diamond_block");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateEmerald"), "emerald_block");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateRedstone"), "redstone_block");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateGlowstone"), "glowstone");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateRefinedIron"), "ic2:machine/blockMachine");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateTin"), "ic2:blockMetalTin");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateCopper"), "ic2:blockMetalCopper");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateBronze"), "ic2:blockMetalBronze");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateUranium"), "ic2:blockMetalUranium");
      GregTech_API.registerCover((Collection)GT_OreDictUnificator.getOres("plateAlloyAdvanced"), "ic2:blockAlloy");
      GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorPlating", 1L), "ic2:generator/blockNuclearReactor");
      GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorPlatingHeat", 1L), "ic2:generator/blockNuclearReactor");
      GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorPlatingExplosive", 1L), "ic2:generator/blockNuclearReactor");
      GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVent", 1L), GT_BlockMetaID_Machine.mIcons[259]);
      GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentCore", 1L), GT_BlockMetaID_Machine.mIcons[259]);
      GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentGold", 1L), GT_BlockMetaID_Machine.mIcons[111]);
      GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentSpread", 1L), GT_BlockMetaID_Machine.mIcons[258]);
      GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentDiamond", 1L), GT_BlockMetaID_Machine.mIcons[111]);
   }
}
