package gregtechmod.loaders.postload;

import gregtechmod.GT_Mod;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.util.GT_Log;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class GT_ItemMaxStacksizeLoader implements Runnable {

   public void run() {
      GT_Log.out.println("GT_Mod: Changing maximum Stacksizes if configured.");
      GT_Items.Upgrade_Overclocker.getItem().func_77625_d(GT_Mod.sUpgradeCount);
      Item.field_77746_aZ.func_77625_d(64);
      Item.field_77790_av.func_77625_d(8);
      Item.field_77766_aB.func_77625_d(8);
      if(GT_Mod.sPlankStackSize < 64) {
         Item.field_77698_e[Block.field_72092_bO.field_71990_ca].func_77625_d(GT_Mod.sPlankStackSize);
         Item.field_77698_e[Block.field_72090_bN.field_71990_ca].func_77625_d(GT_Mod.sPlankStackSize);
         Item.field_77698_e[Block.field_72063_at.field_71990_ca].func_77625_d(GT_Mod.sPlankStackSize);
         Item.field_77698_e[Block.field_72072_bX.field_71990_ca].func_77625_d(GT_Mod.sPlankStackSize);
         Item.field_77698_e[Block.field_72070_bY.field_71990_ca].func_77625_d(GT_Mod.sPlankStackSize);
         Item.field_77698_e[Block.field_72074_bW.field_71990_ca].func_77625_d(GT_Mod.sPlankStackSize);
      }

      if(GT_Mod.sBlockStackSize < 64) {
         Item.field_77698_e[Block.field_72079_ak.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72085_aj.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71992_bw.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72100_bC.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72088_bQ.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71995_bx.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72057_aH.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72036_aT.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72013_bc.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72014_bd.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72037_aS.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72039_aU.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72083_ai.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72105_ah.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72076_bV.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71948_O.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72071_ax.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72041_aW.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72078_bL.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72080_bM.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71979_v.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71980_u.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71994_by.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71940_F.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71939_E.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72081_al.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72101_ab.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71997_br.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72061_ba.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72008_bf.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71958_P.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72089_ap.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71963_Z.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71956_V.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72060_ay.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71946_M.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72032_aY.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_82510_ck.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72077_au.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_71960_R.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72065_as.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72093_an.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72051_aB.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
         Item.field_77698_e[Block.field_72052_aC.field_71990_ca].func_77625_d(GT_Mod.sBlockStackSize);
      }

   }
}
