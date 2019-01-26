package gregtechmod.common;

import gregtechmod.common.GT_Proxy;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Server extends GT_Proxy {

   public boolean isServerSide() {
      return true;
   }

   public boolean isClientSide() {
      return false;
   }

   public boolean isBukkitSide() {
      return false;
   }

   public void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {}

   public int addArmor(String aPrefix) {
      return 0;
   }
}
