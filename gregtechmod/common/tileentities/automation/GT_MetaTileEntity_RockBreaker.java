package gregtechmod.common.tileentities.automation;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_RockBreaker extends GT_MetaTileEntity_ElectricBufferSmall {

   public GT_MetaTileEntity_RockBreaker(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_RockBreaker() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isOverclockerUpgradable() {
      return true;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public int getMinimumStoredEU() {
      return 1000 + (int)Math.pow(4.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()) * 100;
   }

   public int maxEUStore() {
      return 10000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int getInvSize() {
      return 3;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 106);
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isUniversalEnergyStored(1000) && this.getBaseMetaTileEntity().getTimer() % (long)(40 / (int)Math.pow(2.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount())) == 0L) {
         ItemStack tOutput = new ItemStack(Block.field_71978_w, 1);
         boolean tWater = true;
         if(this.getBaseMetaTileEntity().getBlockOffset(0, 0, 1) != Block.field_71943_B && this.getBaseMetaTileEntity().getBlockOffset(0, 0, -1) != Block.field_71943_B && this.getBaseMetaTileEntity().getBlockOffset(-1, 0, 0) != Block.field_71943_B && this.getBaseMetaTileEntity().getBlockOffset(1, 0, 0) != Block.field_71943_B) {
            tWater = false;
         }

         if(this.getBaseMetaTileEntity().getBlockOffset(0, 1, 0) == Block.field_71938_D) {
            tOutput = new ItemStack(Block.field_71981_t, 1);
         } else if(this.getBaseMetaTileEntity().getBlockOffset(0, 0, 1) != Block.field_71938_D && this.getBaseMetaTileEntity().getBlockOffset(0, 0, -1) != Block.field_71938_D && this.getBaseMetaTileEntity().getBlockOffset(-1, 0, 0) != Block.field_71938_D && this.getBaseMetaTileEntity().getBlockOffset(1, 0, 0) != Block.field_71938_D) {
            tOutput = null;
         }

         if(tOutput != null && tWater) {
            if(this.mInventory[0] == null) {
               if(this.mInventory[1] != null && this.mInventory[1].func_77973_b() == Item.field_77767_aC) {
                  if(this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(500 * (int)Math.pow(4.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()), false)) {
                     this.mInventory[0] = new ItemStack(Block.field_72089_ap, 1);
                     this.getBaseMetaTileEntity().func_70298_a(1, 1);
                     this.sendLoopStart((byte)1);
                  }
               } else if(this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(100 * (int)Math.pow(4.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()), false)) {
                  this.mInventory[0] = tOutput;
                  this.sendLoopStart((byte)1);
               }
            } else if(this.mInventory[1] != null && this.mInventory[1].func_77973_b() == Item.field_77767_aC && GT_Utility.areStacksEqual(this.mInventory[0], new ItemStack(Block.field_72089_ap)) && this.mInventory[0].field_77994_a < 64) {
               if(this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(500 * (int)Math.pow(4.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()), false)) {
                  ++this.mInventory[0].field_77994_a;
                  this.getBaseMetaTileEntity().func_70298_a(1, 1);
                  this.sendLoopStart((byte)1);
               }
            } else if(GT_Utility.areStacksEqual(this.mInventory[0], tOutput) && this.mInventory[0].field_77994_a < 64 && this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(100 * (int)Math.pow(4.0D, (double)this.getBaseMetaTileEntity().getOverclockerUpgradeCount()), false)) {
               ++this.mInventory[0].field_77994_a;
               this.sendLoopStart((byte)1);
            }
         }
      }

      super.onPostTick();
   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(205)), 10, 1.0F, aX, aY, aZ);
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 1 && GT_Utility.areStacksEqual(new ItemStack(Item.field_77767_aC, 1), aStack) && aSide != this.getBaseMetaTileEntity().getBackFacing();
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_RockBreaker();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return GT_Utility.getOppositeSide(aSide) == aFacing?113 + (aRedstone?8:0):115 + (aRedstone?8:0);
   }

   public String getDescription() {
      return "Electric Cobble Generator Mk VI";
   }
}
