package gregtechmod.common;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class GT_PacketHandler implements IPacketHandler {

   public void onPacketData(INetworkManager aManager, Packet250CustomPayload aPacket, Player aPlayer) {
      try {
         ByteArrayDataInput e;
         int tX;
         short tY;
         int tZ;
         if(aPacket.field_73630_a.equals("GTTile")) {
            e = ByteStreams.newDataInput(aPacket.field_73629_c);
            tX = e.readInt();
            tY = e.readShort();
            tZ = e.readInt();
            if(aPlayer != null && aPlayer instanceof EntityPlayer) {
               if(GregTech_API.DEBUG_MODE && (((EntityPlayer)aPlayer).field_71092_bJ.equals("Player") || ((EntityPlayer)aPlayer).field_71092_bJ.equals("GregoriusT"))) {
                  GT_Log.out.println("Received initial MetaTileEntity Data: " + aPacket.field_73628_b + " Bytes @ (" + tX + ";" + tY + ";" + tZ + ") during Tick: " + GregTech_API.sClientTickCounter);
               }

               TileEntity tTileEntity = ((EntityPlayer)aPlayer).field_70170_p.func_72796_p(tX, tY, tZ);
               if(tTileEntity != null) {
                  if(tTileEntity instanceof BaseMetaTileEntity) {
                     ((BaseMetaTileEntity)tTileEntity).receiveMetaTileEntityData(e.readShort(), e.readInt(), e.readInt(), e.readInt(), e.readInt(), e.readInt(), e.readInt(), e.readByte(), e.readByte(), e.readByte(), e.readByte());
                  } else if(tTileEntity instanceof BaseMetaPipeEntity) {
                     ((BaseMetaPipeEntity)tTileEntity).receiveMetaTileEntityData(e.readShort(), e.readInt(), e.readInt(), e.readInt(), e.readInt(), e.readInt(), e.readInt(), e.readByte(), e.readByte(), e.readByte(), e.readByte());
                  }
               }
            }
         } else if(aPacket.field_73630_a.equals("GTSound")) {
            e = ByteStreams.newDataInput(aPacket.field_73629_c);
            tX = e.readInt();
            tY = e.readShort();
            tZ = e.readInt();
            if(aPlayer != null && aPlayer instanceof EntityPlayer) {
               GT_Utility.doSoundAtClient(e.readUTF(), 1, e.readFloat(), e.readFloat(), (double)tX, (double)tY, (double)tZ);
            }
         } else if(aPacket.field_73630_a.equals("gregtech")) {
            if(GregTech_API.DEBUG_MODE) {
               GT_Log.out.println("Tick " + GregTech_API.sClientTickCounter + " @ " + aPacket.field_73630_a + " -> " + aPacket.field_73629_c.length + " Bytes");
            }
         } else {
            GT_Log.out.println("Tick " + GregTech_API.sClientTickCounter + " @ " + aPacket.field_73630_a + " -> " + aPacket.field_73629_c.length + " Bytes");
         }
      } catch (Throwable var9) {
         var9.printStackTrace(GT_Log.err);
      }

   }
}
