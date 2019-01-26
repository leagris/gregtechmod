package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeDirection;

public abstract class GT_MetaTileEntity_BasicMachine_Bronze extends GT_MetaTileEntity_BasicMachine {

   public boolean mNeedsSteamVenting = false;


   public GT_MetaTileEntity_BasicMachine_Bronze(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_BasicMachine_Bronze() {}

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.func_74757_a("mNeedsSteamVenting", this.mNeedsSteamVenting);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mNeedsSteamVenting = aNBT.func_74767_n("mNeedsSteamVenting");
   }

   public int getElectricTier() {
      return 0;
   }

   public boolean isSteampowered() {
      return true;
   }

   public boolean isFacingValid(byte aFacing) {
      return super.isFacingValid(aFacing) && aFacing != this.mMainFacing;
   }

   public int getMinimumStoredEU() {
      return 1000;
   }

   public int maxSteamStore() {
      return 2000;
   }

   public boolean isLiquidInput(byte aSide) {
      return aSide != this.mMainFacing;
   }

   public boolean isLiquidOutput(byte aSide) {
      return aSide != this.mMainFacing;
   }

   public int getTopFacingInactive() {
      return 314;
   }

   public int getTopFacingPipe() {
      return 344;
   }

   public int getBottomFacingInactive() {
      return 315;
   }

   public int getBottomFacingPipe() {
      return 345;
   }

   public int getSideFacingInactive() {
      return 316;
   }

   public int getSideFacingPipe() {
      return 346;
   }

   public boolean doesAutoOutput() {
      return false;
   }

   public boolean allowToCheckRecipe() {
      if(this.mNeedsSteamVenting && this.getBaseMetaTileEntity().getCoverIDAtSide(this.getBaseMetaTileEntity().getFrontFacing()) == 0 && !GT_Utility.hasBlockHitBox(this.getBaseMetaTileEntity().getWorld(), this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), 1), this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), 1), this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), 1))) {
         this.sendSound((byte)9);
         this.mNeedsSteamVenting = false;

         try {
            Iterator e = ((ArrayList)this.getBaseMetaTileEntity().getWorld().func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), 1), (double)this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), 1), (double)this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), 1), (double)(this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 1), (double)(this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 1), (double)(this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 1)))).iterator();

            while(e.hasNext()) {
               EntityLivingBase tLiving = (EntityLivingBase)e.next();
               tLiving.func_70097_a(DamageSource.field_76377_j, this.getSteamDamage());
            }
         } catch (Throwable var3) {
            if(GregTech_API.DEBUG_MODE) {
               var3.printStackTrace(GT_Log.err);
            }
         }
      }

      return !this.mNeedsSteamVenting;
   }

   public void endProcess() {
      if(this.isSteampowered()) {
         this.mNeedsSteamVenting = true;
      }

   }

   public void doSound(byte aIndex, double aX, double aY, double aZ) {
      super.doSound(aIndex, aX, aY, aZ);
      if(aIndex == 9) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(4)), 5, 1.0F, aX, aY, aZ);

         for(int l = 0; l < 8; ++l) {
            this.getBaseMetaTileEntity().getWorld().func_72869_a("largesmoke", aX - 0.5D + Math.random(), aY - 0.5D + Math.random(), aZ - 0.5D + Math.random(), (double)ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getFrontFacing()).offsetX / 5.0D, (double)ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getFrontFacing()).offsetY / 5.0D, (double)ForgeDirection.getOrientation(this.getBaseMetaTileEntity().getFrontFacing()).offsetZ / 5.0D);
         }
      }

   }

   public boolean isGivingInformation() {
      return false;
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return GregTech_API.getCoverBehavior(aCoverID).isSimpleCover() && super.allowCoverOnSide(aSide, aCoverID);
   }

   public float getSteamDamage() {
      return 6.0F;
   }
}
