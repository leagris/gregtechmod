package gregtechmod.common.tileentities.energy.storage;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class GT_MetaTileEntity_IDSU extends MetaTileEntity {

   public int mFrequency = 0;


   public GT_MetaTileEntity_IDSU(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_IDSU() {}

   public boolean isBatteryUpgradable() {
      return false;
   }

   public boolean isTransformingLowEnergy() {
      return false;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing();
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public boolean isTeleporterCompatible() {
      return true;
   }

   public int maxEUInput() {
      return 8192;
   }

   public int maxEUOutput() {
      return 8192;
   }

   public int maxEUStore() {
      return 1000000000;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 2;
   }

   public int getInvSize() {
      return 3;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 151);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_IDSU();
   }

   public void saveNBTData(NBTTagCompound aNBT) {}

   public void loadNBTData(NBTTagCompound aNBT) {}

   public void onFirstTick() {
      this.mFrequency = this.getBaseMetaTileEntity().getOwnerName().hashCode();
   }

   public int getInputTier() {
      return 5;
   }

   public int getOutputTier() {
      return 5;
   }

   public int rechargerSlotStartIndex() {
      return 0;
   }

   public int rechargerSlotCount() {
      return 1;
   }

   public int dechargerSlotStartIndex() {
      return 1;
   }

   public int dechargerSlotCount() {
      return 1;
   }

   public void onFirstServerTick() {
      GregTech_API.sIDSUList.clear();
      if(GT_Mod.mUniverse != null && !GT_Mod.mUniverse.field_72995_K) {
         GregTech_API.sIDSUList.clear();

         try {
            File e = GT_Mod.getSaveDirectory();
            if(e != null) {
               NBTTagCompound tNBT = CompressedStreamTools.func_74796_a(new FileInputStream(new File(e, "GT_IDSU_Energyvalues.dat")));
               NBTTagList tList = tNBT.func_74761_m("Energy");

               for(int i = 0; i < tList.func_74745_c(); ++i) {
                  NBTTagCompound tTag = (NBTTagCompound)tList.func_74743_b(i);
                  GregTech_API.sIDSUList.put(Integer.valueOf(tTag.func_74762_e("Hash")), Integer.valueOf(tTag.func_74762_e("EU")));
               }
            }
         } catch (Throwable var6) {
            if(!(var6 instanceof FileNotFoundException)) {
               var6.printStackTrace();
            }
         }
      }

   }

   public void onServerStop() {
      if(GT_Mod.mUniverse != null && !GT_Mod.mUniverse.field_72995_K) {
         try {
            File e = GT_Mod.getSaveDirectory();
            if(e != null) {
               NBTTagCompound tNBT = new NBTTagCompound();
               NBTTagList tList = new NBTTagList();
               Iterator i$ = GregTech_API.sIDSUList.entrySet().iterator();

               while(i$.hasNext()) {
                  Entry tEntry = (Entry)i$.next();
                  NBTTagCompound tTag = new NBTTagCompound();
                  tTag.func_74768_a("Hash", ((Integer)tEntry.getKey()).intValue());
                  tTag.func_74768_a("EU", ((Integer)tEntry.getValue()).intValue());
                  tList.func_74742_a(tTag);
               }

               tNBT.func_74782_a("Energy", tList);
               CompressedStreamTools.func_74799_a(tNBT, new FileOutputStream(new File(e, "GT_IDSU_Energyvalues.dat")));
            }
         } catch (Throwable var7) {
            var7.printStackTrace();
         }
      }

      GregTech_API.sIDSUList.clear();
   }

   public void setEUVar(int aEU) {
      GregTech_API.sIDSUList.put(Integer.valueOf(this.mFrequency), Integer.valueOf(aEU));
   }

   public int getEUVar() {
      Integer tEU = (Integer)GregTech_API.sIDSUList.get(Integer.valueOf(this.mFrequency));
      if(tEU == null) {
         tEU = Integer.valueOf(0);
      }

      return tEU.intValue();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?311:22;
   }

   public String getDescription() {
      return "Interdimensional Storage Unit";
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex < 2;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex < 2;
   }
}
