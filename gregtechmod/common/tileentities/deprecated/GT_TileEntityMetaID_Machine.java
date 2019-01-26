package gregtechmod.common.tileentities.deprecated;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IIC2TileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.BaseTileEntity;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import ic2.api.item.IElectricItem;
import ic2.api.tile.IWrenchable;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public class GT_TileEntityMetaID_Machine extends BaseTileEntity implements IGregTechTileEntity, IIC2TileEntity, IWrenchable {

   public ItemStack[] mInventory = new ItemStack[this.getInventorySlotCount()];
   public boolean mIsAddedToEnet = false;
   public boolean mFirstTick = true;
   public boolean mNeedsUpdate = true;
   public boolean mActive;
   public boolean oActive;
   public boolean mRedstone;
   public boolean oRedstone;
   public boolean mReleaseEnergy = false;
   private int mStoredEnergy = 0;
   private int oOutput;
   private int oX = 0;
   private int oY = 0;
   private int oZ = 0;
   private short mFacing = -1;
   private short oFacing;
   public String mOwnerName = "";
   public long mTickTimer = 0L;


   public int getTexture(int aSide, int aMeta) {
      return 0;
   }

   public boolean isEnetOutput() {
      return false;
   }

   public boolean isEnetInput() {
      return false;
   }

   public int maxEUStore() {
      return 0;
   }

   public int maxEUInput() {
      return 0;
   }

   public int maxEUOutput() {
      return 0;
   }

   public boolean isOutputFacing(short aDirection) {
      return false;
   }

   public boolean isInputFacing(short aDirection) {
      return false;
   }

   public boolean isFacingValid(int aFacing) {
      return false;
   }

   public void onRemoval() {}

   public int getInventorySlotCount() {
      return 0;
   }

   public void storeAdditionalData(NBTTagCompound aNBT) {}

   public void getAdditionalData(NBTTagCompound aNBT) {}

   public void onPreTickUpdate() {}

   public void onPostTickUpdate() {}

   public void onFirstTickUpdate() {}

   public boolean isAccessible(EntityPlayer aPlayer) {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return true;
   }

   public void setEnergyVar(int aEU) {
      this.mStoredEnergy = aEU;
   }

   public int getEnergyVar() {
      return this.mStoredEnergy;
   }

   public int getTier() {
      return GT_Utility.getTier(Math.max(this.maxEUOutput(), this.maxEUInput()));
   }

   public int getChargeTier() {
      return this.getTier();
   }

   public int rechargerSlotStartIndex() {
      return 0;
   }

   public int rechargerSlotCount() {
      return 0;
   }

   public int dechargerSlotStartIndex() {
      return 0;
   }

   public int dechargerSlotCount() {
      return 0;
   }

   public boolean ownerControl() {
      return false;
   }

   public boolean hasAnimation() {
      return false;
   }

   public ArrayList<String> getSpecialDebugInfo(EntityPlayer aPlayer, int aLogLevel, ArrayList<String> aList) {
      return aList;
   }

   public void onLeftclick(EntityPlayer aPlayer) {}

   public boolean isGivingInformation() {
      return false;
   }

   public String func_70303_b() {
      return "Defaultmachine";
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public void func_70316_g() {
      if(!this.func_70320_p()) {
         try {
            ++this.mTickTimer;
            if(this.mFirstTick) {
               if(this.mFacing == -1) {
                  this.mFacing = 0;
               }

               if(this.mTickTimer <= 19L) {
                  return;
               }

               this.mFirstTick = false;
               this.onFirstTickUpdate();
               this.mNeedsUpdate = true;
            }

            if(this.mTickTimer % 6000L == 0L) {
               this.mNeedsUpdate = true;
            }

            this.onPreTickUpdate();
            if(this.field_70331_k.field_72995_K) {
               if(this.mNeedsUpdate) {
                  this.field_70331_k.func_72902_n(this.field_70329_l, this.field_70330_m, this.field_70327_n);
                  this.mNeedsUpdate = false;
               }
            } else {
               if(this.mNeedsUpdate) {
                  this.field_70331_k.func_72965_b(this.field_70329_l, this.field_70330_m, this.field_70327_n, GregTech_API.sBlockList[1].field_71990_ca, 0, this.mFacing);
                  this.field_70331_k.func_72965_b(this.field_70329_l, this.field_70330_m, this.field_70327_n, GregTech_API.sBlockList[1].field_71990_ca, 1, this.mActive?1:0);
                  this.field_70331_k.func_72965_b(this.field_70329_l, this.field_70330_m, this.field_70327_n, GregTech_API.sBlockList[1].field_71990_ca, 2, this.mRedstone?1:0);
                  this.mNeedsUpdate = false;
               }

               if(this.mActive != this.oActive) {
                  this.oActive = this.mActive;
                  this.mNeedsUpdate = true;
               }

               if(this.mRedstone != this.oRedstone) {
                  this.field_70331_k.func_72898_h(this.field_70329_l, this.field_70330_m, this.field_70327_n, this.field_70331_k.func_72798_a(this.field_70329_l, this.field_70330_m, this.field_70327_n));
                  this.oRedstone = this.mRedstone;
                  this.mNeedsUpdate = true;
               }

               if(this.mTickTimer > 30L && (this.isEnetOutput() || this.isEnetInput()) && !this.mIsAddedToEnet) {
                  this.mIsAddedToEnet = GT_ModHandler.addTileToEnet(this.field_70331_k, this);
               }

               if(this.field_70329_l != this.oX || this.field_70330_m != this.oY || this.field_70327_n != this.oZ) {
                  this.oX = this.field_70329_l;
                  this.oY = this.field_70330_m;
                  this.oZ = this.field_70327_n;
                  if(this.mIsAddedToEnet) {
                     GT_ModHandler.removeTileFromEnet(this.field_70331_k, this);
                  }

                  this.mIsAddedToEnet = false;
                  this.mNeedsUpdate = true;
               }

               if(this.mFacing != this.oFacing) {
                  this.oFacing = this.mFacing;
                  if(this.mIsAddedToEnet) {
                     GT_ModHandler.removeTileFromEnet(this.field_70331_k, this);
                  }

                  this.mIsAddedToEnet = false;
               }

               if(this.getOutput() != this.oOutput) {
                  this.oOutput = this.getOutput();
                  if(this.mIsAddedToEnet) {
                     GT_ModHandler.removeTileFromEnet(this.field_70331_k, this);
                  }

                  this.mIsAddedToEnet = false;
                  this.mNeedsUpdate = true;
               }

               if(this.mIsAddedToEnet && GregTech_API.sMachineFireExplosions && this.field_70331_k.field_73012_v.nextInt(1000) == 0) {
                  switch(this.field_70331_k.field_73012_v.nextInt(6)) {
                  case 0:
                     if(this.field_70331_k.func_72798_a(this.field_70329_l + 1, this.field_70330_m, this.field_70327_n) == Block.field_72067_ar.field_71990_ca) {
                        this.doEnergyExplosion();
                     }
                     break;
                  case 1:
                     if(this.field_70331_k.func_72798_a(this.field_70329_l - 1, this.field_70330_m, this.field_70327_n) == Block.field_72067_ar.field_71990_ca) {
                        this.doEnergyExplosion();
                     }
                     break;
                  case 2:
                     if(this.field_70331_k.func_72798_a(this.field_70329_l, this.field_70330_m + 1, this.field_70327_n) == Block.field_72067_ar.field_71990_ca) {
                        this.doEnergyExplosion();
                     }
                     break;
                  case 3:
                     if(this.field_70331_k.func_72798_a(this.field_70329_l, this.field_70330_m - 1, this.field_70327_n) == Block.field_72067_ar.field_71990_ca) {
                        this.doEnergyExplosion();
                     }
                     break;
                  case 4:
                     if(this.field_70331_k.func_72798_a(this.field_70329_l, this.field_70330_m, this.field_70327_n + 1) == Block.field_72067_ar.field_71990_ca) {
                        this.doEnergyExplosion();
                     }
                     break;
                  case 5:
                     if(this.field_70331_k.func_72798_a(this.field_70329_l, this.field_70330_m, this.field_70327_n - 1) == Block.field_72067_ar.field_71990_ca) {
                        this.doEnergyExplosion();
                     }
                  }
               }

               if(this.mIsAddedToEnet && this.isEnetOutput() && this.getEnergyVar() >= this.maxEUOutput() && this.maxEUOutput() > 0) {
                  this.setStoredEnergy(this.getEnergyVar() + GT_ModHandler.emitEnergyToEnet(this.maxEUOutput(), this.field_70331_k, this) - this.maxEUOutput());
               }

               for(int e = 0; e < this.getChargeTier(); ++e) {
                  int i;
                  for(i = this.dechargerSlotStartIndex(); i < this.dechargerSlotCount() + this.dechargerSlotStartIndex(); ++i) {
                     if(this.mInventory[i] != null && this.demandsEnergy() > 0 && this.mInventory[i].func_77973_b() instanceof IElectricItem && ((IElectricItem)this.mInventory[i].func_77973_b()).canProvideEnergy(this.mInventory[i])) {
                        this.increaseStoredEnergy(GT_ModHandler.dischargeElectricItem(this.mInventory[i], this.maxEUStore() - this.getEnergyVar(), this.getChargeTier(), false, false, false));
                     }
                  }

                  for(i = this.rechargerSlotStartIndex(); i < this.rechargerSlotCount() + this.rechargerSlotStartIndex(); ++i) {
                     if(this.getEnergyVar() > 0 && this.mInventory[i] != null && this.mInventory[i].func_77973_b() instanceof IElectricItem) {
                        this.decreaseStoredEnergy(GT_ModHandler.chargeElectricItem(this.mInventory[i], this.getEnergyVar(), this.getChargeTier(), false, false), true);
                     }
                  }
               }
            }

            this.onPostTickUpdate();
            this.func_70296_d();
         } catch (Throwable var3) {
            GT_Log.err.println("Encountered Exception while ticking TileEntity, the Game should\'ve crashed by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
            var3.printStackTrace(GT_Log.err);
         }

      }
   }

   public boolean func_70315_b(int aEventID, int aValue) {
      super.func_70315_b(aEventID, aValue);
      if(this.field_70331_k.field_72995_K) {
         switch(aEventID) {
         case 0:
            this.mFacing = (short)aValue;
            this.mNeedsUpdate = true;
            break;
         case 1:
            this.mActive = aValue != 0;
            this.mNeedsUpdate = true;
            break;
         case 2:
            this.mRedstone = aValue != 0;
            this.mNeedsUpdate = true;
         }
      }

      return true;
   }

   public void func_70307_a(NBTTagCompound aNBT) {
      if(aNBT != null) {
         super.func_70307_a(aNBT);
         this.mStoredEnergy = aNBT.func_74762_e("mStoredEnergy");
         this.mFacing = aNBT.func_74765_d("mFacing");
         this.mOwnerName = aNBT.func_74779_i("mOwnerName");
         this.mActive = aNBT.func_74767_n("mActive");
         this.mRedstone = aNBT.func_74767_n("mRedstone");
         this.getAdditionalData(aNBT);
         NBTTagList tagList = aNBT.func_74761_m("Inventory");

         for(int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound tag = (NBTTagCompound)tagList.func_74743_b(i);
            byte slot = tag.func_74771_c("Slot");
            if(slot >= 0 && slot < this.mInventory.length) {
               this.mInventory[slot] = GT_Utility.loadItem(tag);
            }
         }

      }
   }

   public void func_70310_b(NBTTagCompound aNBT) {
      super.func_70310_b(aNBT);
      aNBT.func_74768_a("mStoredEnergy", this.mStoredEnergy);
      aNBT.func_74777_a("mFacing", this.mFacing);
      aNBT.func_74778_a("mOwnerName", this.mOwnerName);
      aNBT.func_74757_a("mActive", this.mActive);
      aNBT.func_74757_a("mRedstone", this.mRedstone);
      this.storeAdditionalData(aNBT);
      NBTTagList itemList = new NBTTagList();

      for(int i = 0; i < this.mInventory.length; ++i) {
         ItemStack stack = this.mInventory[i];
         if(stack != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.func_74774_a("Slot", (byte)i);
            stack.func_77955_b(tag);
            itemList.func_74742_a(tag);
         }
      }

      aNBT.func_74782_a("Inventory", itemList);
   }

   public void func_70313_j() {
      this.field_70328_o = false;
      this.onRemoval();
      GT_ModHandler.removeTileFromEnet(this.field_70331_k, this);
      this.mIsAddedToEnet = false;
      super.func_70313_j();
   }

   public void func_70312_q() {
      super.func_70312_q();
      this.mNeedsUpdate = true;
      this.mTickTimer = 0L;
   }

   public boolean isAddedToEnergyNet() {
      return this.mIsAddedToEnet;
   }

   public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int aFacing) {
      return aFacing != this.mFacing && this.isFacingValid(aFacing);
   }

   public short getFacing() {
      return (short)this.getFrontFacing();
   }

   public byte getBackFacing() {
      return GT_Utility.getOppositeSide(this.getFrontFacing());
   }

   public byte getFrontFacing() {
      return (byte)this.mFacing;
   }

   public void setFacing(short aFacing) {
      this.mNeedsUpdate = true;
      if(this.isFacingValid(aFacing)) {
         this.mFacing = aFacing;
      }

   }

   public boolean wrenchCanRemove(EntityPlayer aPlayer) {
      return this.playerOwnsThis(aPlayer);
   }

   public float getWrenchDropRate() {
      return 0.8F;
   }

   public int func_70302_i_() {
      return this.mInventory.length;
   }

   public ItemStack func_70301_a(int slot) {
      return this.mInventory[slot];
   }

   public void func_70299_a(int slot, ItemStack stack) {
      this.mInventory[slot] = stack;
      if(stack != null && stack.field_77994_a > this.func_70297_j_()) {
         stack.field_77994_a = this.func_70297_j_();
      }

   }

   public ItemStack func_70298_a(int slot, int amt) {
      ItemStack stack = this.func_70301_a(slot);
      if(stack != null) {
         if(stack.field_77994_a <= amt) {
            this.func_70299_a(slot, (ItemStack)null);
         } else {
            stack = stack.func_77979_a(amt);
            if(stack.field_77994_a == 0) {
               this.func_70299_a(slot, (ItemStack)null);
            }
         }
      }

      return stack;
   }

   public ItemStack func_70304_b(int slot) {
      ItemStack stack = this.func_70301_a(slot);
      if(stack != null) {
         this.func_70299_a(slot, (ItemStack)null);
      }

      return stack;
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean playerOwnsThis(EntityPlayer aPlayer) {
      if(this.ownerControl()) {
         if(this.mOwnerName.equals("") && !this.field_70331_k.field_72995_K) {
            this.mOwnerName = aPlayer.field_71092_bJ;
         } else if(!aPlayer.field_71092_bJ.equals("Player") && !this.mOwnerName.equals("Player") && !this.mOwnerName.equals(aPlayer.field_71092_bJ)) {
            return false;
         }
      }

      return true;
   }

   public boolean func_70300_a(EntityPlayer aPlayer) {
      this.mNeedsUpdate = true;
      return this.playerOwnsThis(aPlayer) && !this.mFirstTick && this.mTickTimer > 20L && this.field_70331_k.func_72796_p(this.field_70329_l, this.field_70330_m, this.field_70327_n) == this && aPlayer.func_70092_e((double)this.field_70329_l + 0.5D, (double)this.field_70330_m + 0.5D, (double)this.field_70327_n + 0.5D) < 64.0D && this.isAccessible(aPlayer);
   }

   public int getStored() {
      return Math.min(this.getEnergyVar(), this.maxEUStore());
   }

   public boolean setStoredEnergy(int aEnergy) {
      this.setEnergyVar(aEnergy < 0?0:aEnergy);
      return true;
   }

   public boolean decreaseStoredEnergy(int aEnergy, boolean aIgnoreTooLessEnergy) {
      if(this.getEnergyVar() - aEnergy < 0 && !aIgnoreTooLessEnergy) {
         return false;
      } else {
         this.setEnergyVar(this.getEnergyVar() - aEnergy);
         if(this.getEnergyVar() < 0) {
            this.setStoredEnergy(0);
            return false;
         } else {
            return true;
         }
      }
   }

   public boolean increaseStoredEnergy(int aEnergy) {
      if(this.getEnergyVar() < this.maxEUStore()) {
         this.setStoredEnergy(this.getEnergyVar() + aEnergy);
         return true;
      } else {
         return false;
      }
   }

   public void doExplosion(int aAmount) {
      if(this.isAddedToEnergyNet() && GregTech_API.sMachineWireFire) {
         try {
            this.mReleaseEnergy = true;
            GT_ModHandler.removeTileFromEnet(this.field_70331_k, this);
            GT_ModHandler.addTileToEnet(this.field_70331_k, this);
            GT_ModHandler.emitEnergyToEnet(32, this.field_70331_k, this);
            GT_ModHandler.emitEnergyToEnet(128, this.field_70331_k, this);
            GT_ModHandler.emitEnergyToEnet(512, this.field_70331_k, this);
            GT_ModHandler.emitEnergyToEnet(2048, this.field_70331_k, this);
            GT_ModHandler.emitEnergyToEnet(8192, this.field_70331_k, this);
         } catch (Exception var6) {
            ;
         }
      }

      this.mReleaseEnergy = false;
      float tStrength = aAmount < 10?1.0F:(aAmount < 32?2.0F:(aAmount < 128?3.0F:(aAmount < 512?4.0F:(aAmount < 2048?5.0F:(aAmount < 4096?6.0F:(aAmount < 8192?7.0F:8.0F))))));
      int tX = this.field_70329_l;
      int tY = this.field_70330_m;
      int tZ = this.field_70327_n;
      this.field_70331_k.func_94575_c(tX, tY, tZ, 0);
      this.field_70331_k.func_72876_a((Entity)null, (double)tX + 0.5D, (double)tY + 0.5D, (double)tZ + 0.5D, tStrength, true);
   }

   public int getMaxEnergyOutput() {
      return this.mReleaseEnergy?Integer.MAX_VALUE:this.maxEUOutput();
   }

   public int demandsEnergy() {
      return this.mReleaseEnergy?0:this.maxEUStore() - this.getEnergyVar();
   }

   public int getCapacity() {
      return this.maxEUStore();
   }

   public int getOutput() {
      return this.maxEUOutput();
   }

   public boolean isActive() {
      return this.mActive;
   }

   public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aLogLevel) {
      ArrayList tList = new ArrayList();
      if(aLogLevel > 1) {
         tList.add("Is" + (this.isAccessible(aPlayer)?" ":" not ") + "accessible for you");
      }

      if(aLogLevel > 0) {
         tList.add("Machine is " + (this.mActive?"active":"inactive"));
      }

      return this.getSpecialDebugInfo(aPlayer, aLogLevel, tList);
   }

   public void setStored(int aEU) {
      this.setEnergyVar(aEU);
   }

   public int addEnergy(int aEnergy) {
      if(aEnergy > 0) {
         this.increaseStoredEnergy(aEnergy);
      } else {
         this.decreaseStoredEnergy(-aEnergy, true);
      }

      return this.getStored();
   }

   public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
      return new ItemStack(GregTech_API.sBlockList[1], 1, this.field_70331_k.func_72805_g(this.field_70329_l, this.field_70330_m, this.field_70327_n));
   }

   public void doEnergyExplosion() {
      if(this.getStored() >= this.getCapacity() / 5) {
         this.doExplosion(this.getOutput() * (this.getStored() >= this.getCapacity()?4:(this.getStored() >= this.getCapacity() / 2?2:1)));
      }

   }

   public int getMaxSafeInput() {
      return this.maxEUInput();
   }

   public boolean func_94042_c() {
      return false;
   }

   public boolean func_94041_b(int i, ItemStack aStack) {
      return !this.isValidSlot(i)?false:aStack != null;
   }

   public int[] func_94128_d(int var1) {
      ArrayList tList = new ArrayList();

      for(int rArray = 0; rArray < this.func_70302_i_(); ++rArray) {
         if(this.isValidSlot(rArray)) {
            tList.add(Integer.valueOf(rArray));
         }
      }

      int[] var5 = new int[tList.size()];

      for(int i = 0; i < var5.length; ++i) {
         var5[i] = ((Integer)tList.get(i)).intValue();
      }

      return var5;
   }

   public boolean func_102007_a(int i, ItemStack itemstack, int j) {
      return this.isValidSlot(i);
   }

   public boolean func_102008_b(int i, ItemStack itemstack, int j) {
      return this.isValidSlot(i);
   }

   public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
      return 0;
   }

   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
      return null;
   }

   public void onMachineBlockUpdate() {}

   public boolean isDigitalChest() {
      return false;
   }

   public ItemStack[] getStoredItemData() {
      return null;
   }

   public void setItemCount(int aCount) {}

   public int getMaxItemCount() {
      return 0;
   }

   public boolean isUpgradable() {
      return false;
   }

   public boolean isMJConverterUpgradable() {
      return false;
   }

   public boolean isOverclockerUpgradable() {
      return false;
   }

   public boolean isTransformerUpgradable() {
      return false;
   }

   public boolean hasMJConverterUpgrade() {
      return false;
   }

   public byte getOverclockerUpgradeCount() {
      return (byte)0;
   }

   public byte getTransformerUpgradeCount() {
      return (byte)0;
   }

   public int getUpgradeStorageVolume() {
      return 0;
   }

   public String getDescription() {
      return "";
   }

   public int getProgress() {
      return 0;
   }

   public int getMaxProgress() {
      return 0;
   }

   public byte getOutputRedstoneSignal(byte aSide) {
      return (byte)(this.mRedstone?15:0);
   }

   public byte getInputRedstoneSignal(byte aSide) {
      return (byte)(this.getWorld().func_72878_l(this.getOffsetX(aSide, 1), this.getOffsetY(aSide, 1), this.getOffsetZ(aSide, 1), aSide) & 15);
   }

   public boolean increaseProgress(int aProgressAmountInTicks) {
      return false;
   }

   public boolean hasThingsToDo() {
      return false;
   }

   public void enableWorking() {}

   public void disableWorking() {}

   public boolean isAllowedToWork() {
      return true;
   }

   public void setOutputRedstoneSignal(byte aSide, byte aStrength) {}

   public void setWorkDataValue(byte aValue) {}

   public byte getWorkDataValue() {
      return (byte)0;
   }

   public void setActive(boolean aActive) {
      this.mActive = aActive;
   }

   public int getMetaTileID() {
      return this.getMetaIDOffset(0, 0, 0);
   }

   public int setMetaTileID(short aID) {
      return 0;
   }

   public long getTimer() {
      return this.mTickTimer;
   }

   public int getUniversalEnergyStored() {
      return this.getStored();
   }

   public boolean drainEnergyUnits(byte aSide, int aVoltage, int aAmperage) {
      return !this.outputsEnergyTo(aSide)?false:this.decreaseStoredEnergy(aVoltage * aAmperage, false);
   }

   public boolean increaseStoredEnergyUnits(int aEnergy, boolean aIgnoreTooMuchEnergy) {
      return this.increaseStoredEnergy(aEnergy);
   }

   public boolean increaseStoredMJ(int aEnergy, boolean aIgnoreTooMuchEnergy) {
      return false;
   }

   public void issueTextureUpdate() {
      this.mNeedsUpdate = true;
   }

   public void issueCoverUpdate(byte aSide) {
      this.mNeedsUpdate = true;
   }

   public void issueBlockUpdate() {
      this.getWorld().func_72898_h(this.getXCoord(), this.getYCoord(), this.getZCoord(), this.getBlockIDOffset(0, 0, 0));
   }

   public void issueClientUpdate() {
      this.mNeedsUpdate = true;
   }

   public boolean isBatteryUpgradable(int aStorage, byte aTier) {
      return false;
   }

   public boolean addMJConverterUpgrade() {
      return false;
   }

   public boolean addOverclockerUpgrade() {
      return false;
   }

   public boolean addTransformerUpgrade() {
      return false;
   }

   public boolean addBatteryUpgrade(int aStorage, byte aTier) {
      return false;
   }

   public void setGenericRedstoneOutput(boolean aOnOff) {
      this.mRedstone = aOnOff;
   }

   public boolean hasInventoryBeenModified() {
      return false;
   }

   public int getErrorDisplayID() {
      return 0;
   }

   public void setErrorDisplayID(int aErrorID) {}

   public IMetaTileEntity getMetaTileEntity() {
      return null;
   }

   public boolean hasWorkJustBeenEnabled() {
      return false;
   }

   public void setCoverIDAtSide(byte aSide, int aID) {}

   public void setCoverItemAtSide(byte aSide, ItemStack aCover) {}

   public int getCoverIDAtSide(byte aSide) {
      return 0;
   }

   public ItemStack getCoverItemAtSide(byte aSide) {
      return null;
   }

   public GT_CoverBehavior getCoverBehaviorAtSide(byte aSide) {
      return GregTech_API.sGenericBehavior;
   }

   public boolean canPlaceCoverIDAtSide(byte aSide, int aID) {
      return false;
   }

   public boolean canPlaceCoverItemAtSide(byte aSide, ItemStack aCover) {
      return false;
   }

   public void setCoverDataAtSide(byte aSide, int aData) {}

   public int getCoverDataAtSide(byte aSide) {
      return 0;
   }

   public byte getInternalInputRedstoneSignal(byte aSide) {
      return this.getInputRedstoneSignal(aSide);
   }

   public byte getStrongestRedstone() {
      return (byte)Math.max(this.getInternalInputRedstoneSignal((byte)0), Math.max(this.getInternalInputRedstoneSignal((byte)1), Math.max(this.getInternalInputRedstoneSignal((byte)2), Math.max(this.getInternalInputRedstoneSignal((byte)3), Math.max(this.getInternalInputRedstoneSignal((byte)4), this.getInternalInputRedstoneSignal((byte)5))))));
   }

   public void setInitialValuesAsNBT(NBTTagCompound aNBT, short aID) {
      this.func_70307_a(aNBT);
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float par1, float par2, float par3) {
      return true;
   }

   public int getStoredMJ() {
      return 0;
   }

   public void setLightValue(byte aLightValue) {}

   public void setOnFire() {
      for(byte i = 0; i < 6; ++i) {
         Block tBlock = this.getBlockAtSide(i);
         if(tBlock == null || null == tBlock.func_71872_e(this.getWorld(), this.getXCoord() + this.getOffsetX(i, 1), this.getOffsetY(i, 1), this.getOffsetZ(i, 1))) {
            this.getWorld().func_94575_c(this.getOffsetX(i, 1), this.getOffsetY(i, 1), this.getOffsetZ(i, 1), Block.field_72067_ar.field_71990_ca);
         }
      }

   }

   public int getUniversalEnergyCapacity() {
      return this.getCapacity();
   }

   public int getOutputAmperage() {
      return 1;
   }

   public int getOutputVoltage() {
      return this.getOutput();
   }

   public int getInputVoltage() {
      return this.getMaxSafeInput();
   }

   public boolean decreaseStoredEnergyUnits(int aEnergy, boolean aIgnoreTooLessEnergy) {
      return this.decreaseStoredEnergy(aEnergy, aIgnoreTooLessEnergy);
   }

   public boolean inputEnergyFrom(byte aSide) {
      return false;
   }

   public boolean outputsEnergyTo(byte aSide) {
      return false;
   }

   public int getMJCapacity() {
      return 0;
   }

   public void setFrontFacing(byte aFacing) {
      this.setFacing((short)aFacing);
   }

   public int getStoredSteam() {
      return 0;
   }

   public int getSteamCapacity() {
      return 0;
   }

   public boolean increaseStoredSteam(int aEnergy, boolean aIgnoreTooMuchEnergy) {
      return false;
   }

   public boolean isSteamEngineUpgradable() {
      return false;
   }

   public boolean addSteamEngineUpgrade() {
      return false;
   }

   public boolean hasSteamEngineUpgrade() {
      return false;
   }

   public int getStoredEU() {
      return this.getStored();
   }

   public int getEUCapacity() {
      return this.getCapacity();
   }

   public void setInternalOutputRedstoneSignal(byte aSide, byte aStrength) {
      this.setOutputRedstoneSignal(aSide, aStrength);
   }

   public int getAverageElectricInput() {
      return 0;
   }

   public int getAverageElectricOutput() {
      return 0;
   }

   public String getOwnerName() {
      return this.mOwnerName != null && !this.mOwnerName.equals("")?this.mOwnerName:"Player";
   }

   public String setOwnerName(String aName) {
      return aName == null?(this.mOwnerName = "Player"):(this.mOwnerName = aName);
   }

   public boolean isValidFacing(byte aSide) {
      return this.isFacingValid(aSide);
   }

   public byte getComparatorValue(byte aSide) {
      return (byte)0;
   }

   public boolean getRedstone() {
      return this.getRedstone((byte)0) || this.getRedstone((byte)1) || this.getRedstone((byte)2) || this.getRedstone((byte)3) || this.getRedstone((byte)4) || this.getRedstone((byte)5);
   }

   public boolean getRedstone(byte aSide) {
      return this.getInternalInputRedstoneSignal(aSide) > 0;
   }

   public byte getStrongOutputRedstoneSignal(byte aSide) {
      return (byte)0;
   }

   public void setStrongOutputRedstoneSignal(byte aSide, byte aStrength) {}

   public boolean injectEnergyUnits(byte Side, int aVoltage, int aAmperage) {
      return false;
   }

   public boolean acceptsRotationalEnergy(byte aSide) {
      return false;
   }

   public boolean injectRotationalEnergy(byte aSide, int aSpeed, int aEnergy) {
      return false;
   }

   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
      return null;
   }

   public boolean canFill(ForgeDirection from, Fluid fluid) {
      return false;
   }

   public boolean canDrain(ForgeDirection from, Fluid fluid) {
      return false;
   }

   public FluidTankInfo[] getTankInfo(ForgeDirection from) {
      return null;
   }

   public double getOutputEnergyUnitsPerTick() {
      return 0.0D;
   }

   public boolean isTeleporterCompatible(ForgeDirection side) {
      return false;
   }

   public double demandedEnergyUnits() {
      return 0.0D;
   }

   public double injectEnergyUnits(ForgeDirection directionFrom, double amount) {
      return 0.0D;
   }

   public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
      return false;
   }

   public double getOfferedEnergy() {
      return 0.0D;
   }

   public void drawEnergy(double amount) {}

   public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
      return false;
   }

   public boolean isInvalidTileEntity() {
      return this.func_70320_p();
   }

   public boolean addStackToSlot(int aIndex, ItemStack aStack) {
      if(aStack == null) {
         return true;
      } else if(aIndex >= 0 && aIndex < this.func_70302_i_()) {
         if(this.func_70301_a(aIndex) == null) {
            this.func_70299_a(aIndex, aStack);
            return true;
         } else if(GT_Utility.areStacksEqual(this.func_70301_a(aIndex), aStack) && this.func_70301_a(aIndex).field_77994_a + aStack.field_77994_a <= Math.min(aStack.func_77976_d(), this.func_70297_j_())) {
            ItemStack var10000 = this.func_70301_a(aIndex);
            var10000.field_77994_a += aStack.field_77994_a;
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean addStackToSlot(int aIndex, ItemStack aStack, int aAmount) {
      return this.addStackToSlot(aIndex, GT_Utility.copyAmount((long)aAmount, new Object[]{aStack}));
   }

   public void setMetaTileEntity(IMetaTileEntity aMetaTileEntity) {}

   public int getTextureIndex(byte aSide, byte aMeta) {
      return 0;
   }

   public Icon getTextureIcon(byte aSide, byte aMeta) {
      return null;
   }

   public byte getColorization() {
      return (byte)-1;
   }

   public byte setColorization(byte aColor) {
      return (byte)-1;
   }

   public boolean dropCover(byte aSide, byte aDroppedSide, boolean aForced) {
      return false;
   }

   public float getBlastResistance(byte aSide) {
      return 10.0F;
   }

   public boolean isMufflerUpgradable() {
      return false;
   }

   public boolean addMufflerUpgrade() {
      return false;
   }

   public boolean hasMufflerUpgrade() {
      return false;
   }

   public boolean isUniversalEnergyStored(int aEnergyAmount) {
      return this.getUniversalEnergyStored() >= aEnergyAmount;
   }

   public String[] getInfoData() {
      return new String[0];
   }
}
