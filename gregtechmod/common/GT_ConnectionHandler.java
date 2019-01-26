package gregtechmod.common;

import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.GT_GUIHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;

public class GT_ConnectionHandler implements IConnectionHandler {

   public void playerLoggedIn(Player player, NetHandler aNetServerHandler, INetworkManager aManager) {}

   public String connectionReceived(NetLoginHandler aNetHandler, INetworkManager aManager) {
      return null;
   }

   public void connectionOpened(NetHandler aClientHandler, String aServer, int aPort, INetworkManager aManager) {}

   public void connectionOpened(NetHandler aClientHandler, MinecraftServer aServer, INetworkManager aManager) {}

   public void connectionClosed(INetworkManager aManager) {}

   public void clientLoggedIn(NetHandler aClientHandler, INetworkManager aManager, Packet1Login aLoginPacket) {
      EntityPlayer aPlayer = aClientHandler.getPlayer();
      String aUserName = aPlayer.field_71092_bJ;
      NetworkRegistry.instance().registerGuiHandler(GregTech_API.gregtechmod, new GT_GUIHandler());
      if(!GT_Mod.mAlreadyPlayed || aUserName.equalsIgnoreCase("richardg867")) {
         aPlayer.func_71035_c("GregTech is known for massivly changing the Tech Tree of modded Minecraft. Please make sure to look up Recipes via NEI (you will definetly need NEI), before complaining about missing Recipes. I needed to change some Recipes to prevent exploits and to improve the Tech Tree, even for regular unmodded Minecraft Recipes. Most of them are Configurable, so don\'t complain.");
         aPlayer.func_71035_c("~ Gregorius Techneticies");
         if(aUserName.equalsIgnoreCase("richardg867")) {
            aPlayer.func_71035_c("Is that enough of a disclaimer for you RichardG?");
         }

         try {
            GT_Log.mLogFile.createNewFile();
         } catch (Throwable var8) {
            ;
         }
      }

      if(GT_Mod.sMessage != null && GT_Mod.sMessage.length() > 5 && GregTech_API.sSpecialFile.get(GT_ConfigCategories.news, GT_Mod.sMessage, true)) {
         aPlayer.func_71035_c(GT_Mod.sMessage);
      }

      try {
         int e = Integer.parseInt(((String)Class.forName("ic2.core.IC2").getField("VERSION").get((Object)null)).substring(4, 7));
         if(GregTech_API.DEBUG_MODE) {
            GT_Log.out.println("Industrialcraft Version: " + e);
         }

         if(e < 397) {
            aPlayer.func_71035_c("GregTech: Please update your IndustrialCraft here:");
            aPlayer.func_71035_c("ic2api.player.to:8080/job/IC2_experimental/?");
         }
      } catch (Throwable var7) {
         aPlayer.func_71035_c("GregTech: Please update your IndustrialCraft here:");
         aPlayer.func_71035_c("ic2api.player.to:8080/job/IC2_experimental/?");
      }

   }
}
