package gregtechmod.common.tileentities.redstone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.common.tileentities.redstone.GT_MetaTileEntity_RedstoneStrengthDisplay;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;

public class GT_MetaTileEntity_RedstoneStrengthScale extends GT_MetaTileEntity_RedstoneStrengthDisplay {

   public static Icon[] sOwnIconList = new Icon[256];


   public GT_MetaTileEntity_RedstoneStrengthScale(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_RedstoneStrengthScale() {}

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_RedstoneStrengthScale();
   }

   public Icon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?sOwnIconList[this.mType * 16 + this.mRedstoneStrength]:null;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister aBlockIconRegister) {
      for(int i = 0; i < 32; ++i) {
         sOwnIconList[i] = aBlockIconRegister.func_94245_a("gregtech_addon:tile.RedstoneScale/" + i);
      }

   }

   public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getFrontFacing()) {
         this.mType = (byte)((this.mType + 1) % 2);
      }

   }

   public String getDescription() {
      return "Redstone Strength on a Scale";
   }

}
