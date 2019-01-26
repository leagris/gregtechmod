package gregtechmod.api.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;

public interface IGT_Mod {

   boolean isServerSide();

   boolean isClientSide();

   boolean isBukkitSide();

   EntityPlayer getThePlayer();

   boolean allowPacketToBeSent(Packet var1, EntityPlayerMP var2);

   int addArmor(String var1);

   void doSonictronSound(ItemStack var1, World var2, double var3, double var5, double var7);
}
