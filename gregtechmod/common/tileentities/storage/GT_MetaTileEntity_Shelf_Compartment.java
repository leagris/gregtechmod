package gregtechmod.common.tileentities.storage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.storage.GT_MetaTileEntity_Shelf;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GT_MetaTileEntity_Shelf_Compartment extends GT_MetaTileEntity_Shelf {

   public static Icon[] sIconList = new Icon[256];


   public GT_MetaTileEntity_Shelf_Compartment(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Shelf_Compartment() {}

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Shelf_Compartment();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?32:(aSide == 1?29:40);
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getFrontFacing()) {
         this.mType = (byte)((this.mType + 1) % 16);
      }

   }

   public Icon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?sIconList[this.mType]:null;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister aBlockIconRegister) {
      for(int i = 0; i < 32; ++i) {
         sIconList[i] = aBlockIconRegister.func_94245_a("gregtech_addon:tile.Compartment/" + i);
      }

   }

   public void onLeftclick(EntityPlayer aPlayer) {
      if(this.mInventory[0] != null && this.mInventory[0].field_77994_a > 0) {
         ItemStack tOutput = GT_Utility.copy(new Object[]{this.mInventory[0]});
         if(!aPlayer.func_70093_af()) {
            tOutput.field_77994_a = 1;
         }

         this.getBaseMetaTileEntity().func_70298_a(0, tOutput.field_77994_a);
         EntityItem tEntity = new EntityItem(this.getBaseMetaTileEntity().getWorld(), (double)this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5D, (double)this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5D, (double)this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5D, tOutput);
         tEntity.field_70159_w = 0.0D;
         tEntity.field_70181_x = 0.0D;
         tEntity.field_70179_y = 0.0D;
         this.getBaseMetaTileEntity().getWorld().func_72838_d(tEntity);
      }

   }

   public void onRightclick(EntityPlayer aPlayer) {
      ItemStack tStack = aPlayer.field_71071_by.func_70301_a(aPlayer.field_71071_by.field_70461_c);
      if(tStack == null) {
         if(this.mInventory[0] != null && this.mInventory[0].field_77994_a > 0) {
            aPlayer.field_71071_by.func_70299_a(aPlayer.field_71071_by.field_70461_c, this.mInventory[0]);
            this.getBaseMetaTileEntity().func_70299_a(0, (ItemStack)null);
         }
      } else if(this.mInventory[0] == null) {
         aPlayer.field_71071_by.func_70299_a(aPlayer.field_71071_by.field_70461_c, (ItemStack)null);
         this.getBaseMetaTileEntity().func_70299_a(0, tStack);
      }

   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0;
   }

}
