package gregtechmod.api.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IRedstoneCircuitBlock;

public abstract class GT_CircuitryBehavior {

   public static volatile int VERSION = 408;


   public GT_CircuitryBehavior(int aIndex) {
      GregTech_API.sCircuitryBehaviors.put(Integer.valueOf(aIndex), this);
   }

   public abstract void initParameters(int[] var1, IRedstoneCircuitBlock var2);

   public abstract void validateParameters(int[] var1, IRedstoneCircuitBlock var2);

   public abstract void onTick(int[] var1, IRedstoneCircuitBlock var2);

   public abstract boolean displayItemStack(int[] var1, IRedstoneCircuitBlock var2, int var3);

   @SideOnly(Side.CLIENT)
   public abstract String getName();

   @SideOnly(Side.CLIENT)
   public abstract String getDescription();

   @SideOnly(Side.CLIENT)
   public abstract String getDataDescription(int[] var1, int var2);

   @SideOnly(Side.CLIENT)
   public String getDataDisplay(int[] aCircuitData, int aCircuitDataIndex) {
      return null;
   }

   public static final boolean getAnyRedstone(IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      for(byte i = 0; i < 6; ++i) {
         if(i != aRedstoneCircuitBlock.getOutputFacing() && aRedstoneCircuitBlock.getCover(i).letsRedstoneGoIn(i, aRedstoneCircuitBlock.getCoverID(i), aRedstoneCircuitBlock.getCoverVariable(i), aRedstoneCircuitBlock.getOwnTileEntity()) && aRedstoneCircuitBlock.getInputRedstone(i) > 0) {
            return true;
         }
      }

      return false;
   }

   public static final boolean getAllRedstone(IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      for(byte i = 0; i < 6; ++i) {
         if(i != aRedstoneCircuitBlock.getOutputFacing() && aRedstoneCircuitBlock.getCover(i).letsRedstoneGoIn(i, aRedstoneCircuitBlock.getCoverID(i), aRedstoneCircuitBlock.getCoverVariable(i), aRedstoneCircuitBlock.getOwnTileEntity()) && aRedstoneCircuitBlock.getInputRedstone(i) == 0) {
            return false;
         }
      }

      return true;
   }

   public static final boolean getOneRedstone(IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      int tRedstoneAmount = 0;

      for(byte i = 0; i < 6; ++i) {
         if(i != aRedstoneCircuitBlock.getOutputFacing() && aRedstoneCircuitBlock.getCover(i).letsRedstoneGoIn(i, aRedstoneCircuitBlock.getCoverID(i), aRedstoneCircuitBlock.getCoverVariable(i), aRedstoneCircuitBlock.getOwnTileEntity()) && aRedstoneCircuitBlock.getInputRedstone(i) > 0) {
            ++tRedstoneAmount;
         }
      }

      return tRedstoneAmount == 1;
   }

   public static final byte getStrongestRedstone(IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      byte tRedstoneAmount = 0;

      for(byte i = 0; i < 6; ++i) {
         if(i != aRedstoneCircuitBlock.getOutputFacing() && aRedstoneCircuitBlock.getCover(i).letsRedstoneGoIn(i, aRedstoneCircuitBlock.getCoverID(i), aRedstoneCircuitBlock.getCoverVariable(i), aRedstoneCircuitBlock.getOwnTileEntity())) {
            tRedstoneAmount = (byte)Math.max(tRedstoneAmount, aRedstoneCircuitBlock.getInputRedstone(i));
         }
      }

      return tRedstoneAmount;
   }

   public static final byte getWeakestNonZeroRedstone(IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      if(!getAnyRedstone(aRedstoneCircuitBlock)) {
         return (byte)0;
      } else {
         byte tRedstoneAmount = 15;

         for(byte i = 0; i < 6; ++i) {
            if(i != aRedstoneCircuitBlock.getOutputFacing() && aRedstoneCircuitBlock.getCover(i).letsRedstoneGoIn(i, aRedstoneCircuitBlock.getCoverID(i), aRedstoneCircuitBlock.getCoverVariable(i), aRedstoneCircuitBlock.getOwnTileEntity()) && aRedstoneCircuitBlock.getInputRedstone(i) > 0) {
               tRedstoneAmount = (byte)Math.min(tRedstoneAmount, aRedstoneCircuitBlock.getInputRedstone(i));
            }
         }

         return tRedstoneAmount;
      }
   }

   public static final byte getWeakestRedstone(IRedstoneCircuitBlock aRedstoneCircuitBlock) {
      if(!getAnyRedstone(aRedstoneCircuitBlock)) {
         return (byte)0;
      } else {
         byte tRedstoneAmount = 15;

         for(byte i = 0; i < 6; ++i) {
            if(i != aRedstoneCircuitBlock.getOutputFacing() && aRedstoneCircuitBlock.getCover(i).letsRedstoneGoIn(i, aRedstoneCircuitBlock.getCoverID(i), aRedstoneCircuitBlock.getCoverVariable(i), aRedstoneCircuitBlock.getOwnTileEntity())) {
               tRedstoneAmount = (byte)Math.min(tRedstoneAmount, aRedstoneCircuitBlock.getInputRedstone(i));
            }
         }

         return tRedstoneAmount;
      }
   }

}
