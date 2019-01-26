package gregtechmod.common.tileentities.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.common.tileentities.storage.GT_MetaTileEntity_Shelf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Shelf_Desk extends GT_MetaTileEntity_Shelf {

   public GT_MetaTileEntity_Shelf_Desk(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Shelf_Desk() {}

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Shelf_Desk();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?222:(aSide == 0?32:(aSide == 1?29:40));
   }

   public void onRightclick(EntityPlayer aPlayer) {
      ItemStack tStack = aPlayer.field_71071_by.func_70301_a(aPlayer.field_71071_by.field_70461_c);
      if(tStack == null) {
         if(this.mInventory[0] != null && this.mInventory[0].field_77994_a > 0) {
            aPlayer.field_71071_by.func_70299_a(aPlayer.field_71071_by.field_70461_c, this.mInventory[0]);
            this.getBaseMetaTileEntity().func_70299_a(0, (ItemStack)null);
            this.mType = 0;
         }
      } else if(this.mInventory[0] == null) {
         aPlayer.field_71071_by.func_70299_a(aPlayer.field_71071_by.field_70461_c, (ItemStack)null);
         this.getBaseMetaTileEntity().func_70299_a(0, tStack);
      }

   }
}
