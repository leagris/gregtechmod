package gregtechmod.common.tileentities.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Safe extends MetaTileEntity {

   public int success = 0;


   public GT_MetaTileEntity_Safe(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Safe() {}

   public boolean isSimpleMachine() {
      return true;
   }

   public int getInvSize() {
      return 29;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 28;
   }

   public boolean isFacingValid(byte aFacing) {
      return aFacing > 1;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 128);
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean ownerControl() {
      return true;
   }

   public boolean unbreakable() {
      return true;
   }

   public boolean isEnetOutput() {
      return false;
   }

   public boolean isEnetInput() {
      return false;
   }

   public boolean isOutputFacing(byte aSide) {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return false;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Safe();
   }

   public void saveNBTData(NBTTagCompound aNBT) {}

   public void loadNBTData(NBTTagCompound aNBT) {}

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide()) {
         if(this.mInventory[0] != null && (this.success-- > 0 || this.getBaseMetaTileEntity().getTimer() % 200L == 20L)) {
            int i;
            for(i = 1; this.mInventory[0] != null && i < this.getInvSize() - 1; ++i) {
               if(this.mInventory[i] != null && GT_Utility.areStacksEqual(this.mInventory[i], this.mInventory[0])) {
                  this.success = GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 0, i, (byte)64, (byte)1, (byte)64, (byte)1);
               }
            }

            for(i = 1; this.mInventory[0] != null && i < this.getInvSize() - 1; ++i) {
               if(this.mInventory[i] == null) {
                  this.success = GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 0, i, (byte)64, (byte)1, (byte)64, (byte)1);
               }
            }
         }

         if(this.mInventory[28] == null) {
            if(this.mInventory[0] != null && this.mInventory[0].field_77994_a < 1) {
               this.mInventory[0] = null;
            }
         } else {
            this.mInventory[28].field_77994_a = 0;
            if(this.mInventory[0] == null || this.mInventory[0].field_77994_a < 1) {
               this.mInventory[0] = GT_Utility.copy(new Object[]{this.mInventory[28]});
            }
         }
      }

   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0 || this.mInventory[0] == null || GT_Utility.areStacksEqual(this.mInventory[0], aStack);
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?32:(aSide == 1?29:(aSide == aFacing?214:40));
   }

   public String getDescription() {
      return "This is completly \'Safe\', except against Explosions";
   }
}
