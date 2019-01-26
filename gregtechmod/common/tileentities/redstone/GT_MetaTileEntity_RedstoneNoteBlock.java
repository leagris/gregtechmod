package gregtechmod.common.tileentities.redstone;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_RedstoneNoteBlock extends MetaTileEntity {

   private byte mRedstoneStrength = 0;


   public GT_MetaTileEntity_RedstoneNoteBlock(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_RedstoneNoteBlock() {}

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
      return new GT_MetaTileEntity_RedstoneNoteBlock();
   }

   public void saveNBTData(NBTTagCompound aNBT) {}

   public void loadNBTData(NBTTagCompound aNBT) {}

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      return false;
   }

   public void onPreTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().getStrongestRedstone() != this.mRedstoneStrength) {
         this.mRedstoneStrength = this.getBaseMetaTileEntity().getStrongestRedstone();
         if(this.mRedstoneStrength > 0) {
            this.sendSound(this.mRedstoneStrength);
         }
      }

   }

   public void doSound(byte aIndex, double aX, double aY, double aZ) {
      --aIndex;
      ItemStack tStack;
      switch(this.getBaseMetaTileEntity().getFrontFacing()) {
      case 1:
         tStack = new ItemStack(Block.field_72105_ah, 1);
         break;
      case 2:
         tStack = new ItemStack(Block.field_71981_t, 1);
         break;
      case 3:
         tStack = new ItemStack(Block.field_71939_E, 1);
         break;
      case 4:
         tStack = new ItemStack(Block.field_71946_M, 1);
         break;
      case 5:
         tStack = new ItemStack(Block.field_71951_J, 1);
         break;
      default:
         tStack = new ItemStack(Block.field_72083_ai, 1);
      }

      tStack.field_77994_a = 1 + (int)((double)aIndex * 1.714D);
      GregTech_API.gregtechmod.doSonictronSound(tStack, this.getBaseMetaTileEntity().getWorld(), aX, aY, aZ);
      this.getBaseMetaTileEntity().getWorld().func_72869_a("note", aX, aY + 0.7D, aZ, (double)aIndex / 14.0D, 0.0D, 0.0D);
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return 293;
   }

   public String getDescription() {
      return "Rotate to switch Notes";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }
}
