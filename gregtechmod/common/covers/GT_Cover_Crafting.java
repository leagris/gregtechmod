package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet100OpenWindow;

public class GT_Cover_Crafting extends GT_CoverBehavior {

   public GT_Cover_Crafting(ItemStack aStack) {
      super(aStack);
   }

   public boolean onCoverRightclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(aPlayer instanceof EntityPlayerMP) {
         ((EntityPlayerMP)aPlayer).func_71117_bO();
         ((EntityPlayerMP)aPlayer).field_71135_a.func_72567_b(new Packet100OpenWindow(((EntityPlayerMP)aPlayer).field_71139_cq, 1, "Crafting", 9, true));
         ((EntityPlayerMP)aPlayer).field_71070_bA = new ContainerWorkbench(((EntityPlayerMP)aPlayer).field_71071_by, ((EntityPlayerMP)aPlayer).field_70170_p, aTileEntity.getXCoord(), aTileEntity.getYCoord(), aTileEntity.getZCoord()) {
            public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
               return true;
            }
         };
         ((EntityPlayerMP)aPlayer).field_71070_bA.field_75152_c = ((EntityPlayerMP)aPlayer).field_71139_cq;
         ((EntityPlayerMP)aPlayer).field_71070_bA.func_75132_a((EntityPlayerMP)aPlayer);
      }

      return true;
   }
}
