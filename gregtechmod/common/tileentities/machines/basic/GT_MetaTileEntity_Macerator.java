package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Macerator extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Macerator(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Macerator() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 131);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Macerator();
   }

   public void onPreTick() {
      super.onPreTick();
      if(this.getBaseMetaTileEntity().isClientSide() && this.getBaseMetaTileEntity().isActive() && this.getBaseMetaTileEntity().getFrontFacing() != 1 && this.getBaseMetaTileEntity().getCoverIDAtSide((byte)1) == 0 && !this.getBaseMetaTileEntity().getOpacityAtSide((byte)1)) {
         Random tRandom = this.getBaseMetaTileEntity().getWorld().field_73012_v;
         this.getBaseMetaTileEntity().getWorld().func_72869_a("smoke", (double)((float)this.getBaseMetaTileEntity().getXCoord() + 0.8F - tRandom.nextFloat() * 0.6F), (double)((float)this.getBaseMetaTileEntity().getYCoord() + 0.9F + tRandom.nextFloat() * 0.2F), (double)((float)this.getBaseMetaTileEntity().getZCoord() + 0.8F - tRandom.nextFloat() * 0.6F), 0.0D, 0.0D, 0.0D);
      }

   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[2] != null && null != (this.mOutputItem1 = GT_ModHandler.getMaceratorOutput(this.mInventory[2], true, this.mInventory[3]))) {
         this.mEUt = 2;
         this.mMaxProgresstime = 400;
      }

   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return super.allowPutStack(aIndex, aSide, aStack) && GT_ModHandler.getMaceratorOutput(GT_Utility.copyAmount(64L, new Object[]{aStack}), false, (ItemStack)null) != null;
   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(201)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public int getFrontFacingInactive() {
      return 244;
   }

   public int getFrontFacingActive() {
      return 245;
   }

   public int getTopFacingInactive() {
      return 228;
   }

   public int getTopFacingActive() {
      return 229;
   }

   public String getDescription() {
      return "Maceratron-E-901";
   }
}
