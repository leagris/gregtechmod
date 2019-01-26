package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechDeviceInformation;
import gregtechmod.api.items.GT_Generic_Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GT_SensorKit_Item extends GT_Generic_Item {

   public GT_SensorKit_Item(int aID, String aUnlocalized, String aEnglish) {
      super(aID, aUnlocalized, aEnglish, "Attach to GregTech Machines");
      this.func_77637_a(GregTech_API.TAB_GREGTECH);
      this.func_77625_d(1);
   }

   private static ChunkCoordinates getTargetCoordinates(EntityPlayer aPlayer, IBlockAccess aWorld, int aX, int aY, int aZ) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      if(tTileEntity != null && tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation)tTileEntity).isGivingInformation()) {
         if(aPlayer != null && tTileEntity instanceof IInventory && !((IInventory)tTileEntity).func_70300_a(aPlayer)) {
            return null;
         } else {
            ChunkCoordinates coordinates = new ChunkCoordinates();
            coordinates.field_71574_a = aX;
            coordinates.field_71572_b = aY;
            coordinates.field_71573_c = aZ;
            return coordinates;
         }
      } else {
         return null;
      }
   }

   private static void setCoordinates(ItemStack aStack, int x, int y, int z) {
      if(aStack != null) {
         NBTTagCompound tNBT = aStack.func_77978_p();
         if(tNBT == null) {
            tNBT = new NBTTagCompound();
         }

         tNBT.func_74768_a("x", x);
         tNBT.func_74768_a("y", y);
         tNBT.func_74768_a("z", z);
         aStack.func_77982_d(tNBT);
      }

   }

   public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float var8, float var9, float var10) {
      if(aPlayer != null && aPlayer instanceof EntityPlayerMP) {
         ChunkCoordinates position = getTargetCoordinates(aPlayer, aWorld, aX, aY, aZ);
         if(position != null) {
            ItemStack sensorLocationCard = GT_Items.NC_SensorCard.get(1L, new Object[0]);
            setCoordinates(sensorLocationCard, position.field_71574_a, position.field_71572_b, position.field_71573_c);
            aPlayer.field_71071_by.field_70462_a[aPlayer.field_71071_by.field_70461_c] = sensorLocationCard;
         }

         return true;
      } else {
         return false;
      }
   }
}
