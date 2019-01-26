package gregtechmod.api.items;

import codechicken.nei.api.API;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.items.GT_Tool_Item;
import net.minecraft.client.renderer.texture.IconRegister;

public class GT_EmptyTool_Item extends GT_Tool_Item {

   public GT_EmptyTool_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aChargedGTID) {
      super(aID, aUnlocalized, aEnglish, "Empty. You need to recharge it.", aMaxDamage, 0, false, aChargedGTID, -1);

      try {
         Class.forName("codechicken.nei.api.API");
         API.hideItem(this.field_77779_bT);
      } catch (Throwable var7) {
         ;
      }

   }

   public boolean func_77634_r() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IconRegister aIconRegister) {}
}
