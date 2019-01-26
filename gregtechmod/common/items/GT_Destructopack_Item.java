package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Log;
import java.util.Iterator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Destructopack_Item extends GT_Generic_Item {

   public GT_Destructopack_Item(int aID, String aUnlocalized, String aEnglish) {
      super(aID, aUnlocalized, aEnglish, "Mobile Trash Bin");
      this.func_77625_d(1);
      this.setNoRepair();
   }

   public ItemStack func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      aPlayer.openGui(GregTech_API.gregtechmod, 33, aWorld, 0, 0, 0);
      return aStack;
   }

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IconRegister aIconRegister) {
      super.func_94581_a(aIconRegister);
      if(GregTech_API.sPostloadFinished) {
         GT_Log.out.println("GT_Mod: Setting up Icon Register for Items");
         GregTech_API.sItemIcons = aIconRegister;
         GT_Log.out.println("GT_Mod: Starting Item Icon Load Phase Clientside");
         Iterator i$ = GregTech_API.sGTItemIconload.iterator();

         while(i$.hasNext()) {
            Runnable tRunnable = (Runnable)i$.next();

            try {
               tRunnable.run();
            } catch (Throwable var5) {
               var5.printStackTrace(GT_Log.err);
            }
         }
      }

   }
}
