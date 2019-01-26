package gregtechmod.common.tileentities.redstone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;

public class GT_MetaTileEntity_RedstoneStrengthDisplay extends MetaTileEntity {

   public byte mRedstoneStrength = 0;
   public byte mType = 0;
   public static Icon[] sIconList = new Icon[256];


   public GT_MetaTileEntity_RedstoneStrengthDisplay(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_RedstoneStrengthDisplay() {}

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
      return new GT_MetaTileEntity_RedstoneStrengthDisplay();
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
      if(this.mType < 0) {
         this.mType = (byte)(this.mType + 16);
      }

   }

   public byte getUpdateData() {
      return (byte)(this.mRedstoneStrength & 15 | this.mType << 4);
   }

   public void onPreTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide()) {
         this.mRedstoneStrength = this.getBaseMetaTileEntity().getStrongestRedstone();
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?(aRedstone?60:59):(aSide == 1?(aRedstone?58:57):(aRedstone?62:61));
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getFrontFacing()) {
         this.mType = (byte)((this.mType + 1) % 9);
      }

   }

   public Icon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?sIconList[this.mType * 16 + this.mRedstoneStrength]:null;
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister aBlockIconRegister) {
      for(int i = 0; i < 144; ++i) {
         sIconList[i] = aBlockIconRegister.func_94245_a("gregtech_addon:tile.RedstoneDisplay/" + i);
      }

   }

   public String getDescription() {
      return "Displays Redstone Strength";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

}
