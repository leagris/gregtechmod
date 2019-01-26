package gregtechmod.common.tileentities.redstone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;

public class GT_MetaTileEntity_RedstoneButtonPanel extends MetaTileEntity {

   public byte mRedstoneStrength = 0;
   public byte mType = 0;
   public byte mUpdate = 0;
   public static Icon[] sIconList = new Icon[256];


   public GT_MetaTileEntity_RedstoneButtonPanel(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_RedstoneButtonPanel() {}

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public int getInvSize() {
      return 0;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_RedstoneButtonPanel();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.func_74774_a("mRedstoneStrength", this.mRedstoneStrength);
      aNBT.func_74774_a("mType", this.mType);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mRedstoneStrength = aNBT.func_74771_c("mRedstoneStrength");
      this.mType = aNBT.func_74771_c("mType");
   }

   public void onValueUpdate(byte aValue) {
      this.mRedstoneStrength = (byte)(aValue & 15);
      this.mType = (byte)(aValue >>> 4);
   }

   public byte getUpdateData() {
      return (byte)(this.mRedstoneStrength & 15 | this.mType << 4);
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getFrontFacing()) {
         if(this.getBaseMetaTileEntity().isServerSide()) {
            this.mUpdate = 2;
            float[] tCoords = GT_Utility.getClickedFacingCoords(aSide, aX, aY, aZ);
            switch(this.mType) {
            case 1:
               this.mRedstoneStrength = (byte)(this.mRedstoneStrength ^ 1 << (byte)((int)(tCoords[0] * 2.0F)) + 2 * (byte)((int)(tCoords[1] * 2.0F)));
               break;
            case 2:
               this.mRedstoneStrength = (byte)(this.mRedstoneStrength ^ 1 << (byte)((int)(tCoords[1] * 4.0F)));
               break;
            default:
               this.mRedstoneStrength = (byte)((byte)((int)(tCoords[0] * 4.0F)) + 4 * (byte)((int)(tCoords[1] * 4.0F)));
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public void onPreTick() {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         this.getBaseMetaTileEntity().setGenericRedstoneOutput(true);
         if(this.mUpdate > 0) {
            --this.mUpdate;
         } else if(this.getBaseMetaTileEntity().isAllowedToWork()) {
            this.mRedstoneStrength = 0;
         }

         for(byte i = 0; i < 6; ++i) {
            this.getBaseMetaTileEntity().setInternalOutputRedstoneSignal(i, i == this.getBaseMetaTileEntity().getFrontFacing()?0:this.mRedstoneStrength);
         }
      }

   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing() || GregTech_API.getCoverBehavior(aCoverID).isGUIClickable(aSide, aCoverID, 0, this.getBaseMetaTileEntity());
   }

   public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getFrontFacing()) {
         this.mType = (byte)((this.mType + 1) % 4);
      }

   }

   public Icon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?sIconList[this.mType * 16 + this.mRedstoneStrength]:null;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister aBlockIconRegister) {
      for(int i = 0; i < 64; ++i) {
         sIconList[i] = aBlockIconRegister.func_94245_a("gregtech_addon:tile.ButtonPanel/" + i);
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?(aRedstone?56:54):(aSide == 1?(aRedstone?53:52):(aRedstone?94:93));
   }

   public String getDescription() {
      return "Rightclick with Screwdriver to change Button Design";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

}
