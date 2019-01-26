package gregtechmod.common;

import gregtechmod.common.GT_FuelHandler;
import gregtechmod.common.GT_TickHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class GT_Proxy {

   public static GT_TickHandler mServerTickHandler = new GT_TickHandler(true);
   public static GT_FuelHandler mFuelHandler = new GT_FuelHandler();


   public abstract boolean isServerSide();

   public abstract boolean isClientSide();

   public abstract boolean isBukkitSide();

   public abstract void doSonictronSound(ItemStack var1, World var2, double var3, double var5, double var7);

   public abstract int addArmor(String var1);

   public boolean registerRenderers() {
      return false;
   }

   public EntityPlayer getThePlayer() {
      return null;
   }

}
