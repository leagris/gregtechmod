package gregtechmod.api.metatileentity;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.BaseTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public class BaseMetaTileEntity extends BaseTileEntity implements IGregTechTileEntity {

   public static volatile int VERSION = 408;
   protected MetaTileEntity mMetaTileEntity;
   protected int mStoredMJ = 0;
   protected int mStoredEnergy = 0;
   protected int mStoredSteam = 0;
   protected int mAverageEUInputIndex = 0;
   protected int mAverageEUOutputIndex = 0;
   protected boolean mIsAddedToEnet = false;
   protected boolean mReleaseEnergy = false;
   protected int[] mAverageEUInput = new int[]{0, 0, 0, 0, 0};
   protected int[] mAverageEUOutput = new int[]{0, 0, 0, 0, 0};
   private boolean[] mActiveEUInputs = new boolean[]{false, false, false, false, false, false};
   private boolean[] mActiveEUOutputs = new boolean[]{false, false, false, false, false, false};
   private byte[] mSidedRedstone = new byte[]{(byte)15, (byte)15, (byte)15, (byte)15, (byte)15, (byte)15};
   private int[] mCoverSides = new int[]{0, 0, 0, 0, 0, 0};
   private int[] mCoverData = new int[]{0, 0, 0, 0, 0, 0};
   private int[] mTimeStatistics;
   private boolean mHasEnoughEnergy;
   private boolean mNeedsBatteryUpgrade;
   private boolean mRunningThroughTick;
   private boolean mInputDisabled;
   private boolean mOutputDisabled;
   private boolean mMuffler;
   private boolean mLockUpgrade;
   private boolean mActive;
   private boolean mRedstone;
   private boolean mWorkUpdate;
   private boolean mSteamConverter;
   private boolean mMJConverter;
   private boolean mInventoryChanged;
   private boolean mWorks;
   private boolean mNeedsUpdate;
   private boolean mNeedsBlockUpdate;
   private boolean mSendClientData;
   private boolean oRedstone;
   private byte mColor;
   private byte oColor;
   private byte mStrongRedstone;
   private byte oRedstoneData;
   private byte oTextureData;
   private byte oUpdateData;
   private byte oLightValueClient;
   private byte oLightValue;
   private byte mLightValue;
   private byte mRSEnergyCells;
   private byte mSteamTanks;
   private byte mOverclockers;
   private byte mTransformers;
   private byte mOtherUpgrades;
   private byte mFacing;
   private byte oFacing;
   private byte mWorkData;
   private int mDisplayErrorCode;
   private int oOutput;
   private int oX;
   private int oY;
   private int oZ;
   private int mUpgradedStorage;
   private int mTimeStatisticsIndex;
   private int mLagWarningCount;
   private short mID;
   private long mTickTimer;
   private String mOwnerName;


   public BaseMetaTileEntity() {
      this.mTimeStatistics = new int[GregTech_API.TICKS_FOR_LAG_AVERAGING];
      this.mHasEnoughEnergy = true;
      this.mNeedsBatteryUpgrade = false;
      this.mRunningThroughTick = false;
      this.mInputDisabled = false;
      this.mOutputDisabled = false;
      this.mMuffler = false;
      this.mLockUpgrade = false;
      this.mActive = false;
      this.mRedstone = false;
      this.mWorkUpdate = false;
      this.mSteamConverter = false;
      this.mMJConverter = false;
      this.mInventoryChanged = false;
      this.mWorks = true;
      this.mNeedsUpdate = true;
      this.mNeedsBlockUpdate = true;
      this.mSendClientData = false;
      this.oRedstone = false;
      this.mColor = 0;
      this.oColor = 0;
      this.mStrongRedstone = 0;
      this.oRedstoneData = 63;
      this.oTextureData = 0;
      this.oUpdateData = 0;
      this.oLightValueClient = 0;
      this.oLightValue = 0;
      this.mLightValue = 0;
      this.mRSEnergyCells = 0;
      this.mSteamTanks = 0;
      this.mOverclockers = 0;
      this.mTransformers = 0;
      this.mOtherUpgrades = 0;
      this.mFacing = 0;
      this.oFacing = 0;
      this.mWorkData = 0;
      this.mDisplayErrorCode = 0;
      this.oOutput = 0;
      this.oX = 0;
      this.oY = 0;
      this.oZ = 0;
      this.mUpgradedStorage = 0;
      this.mTimeStatisticsIndex = 0;
      this.mLagWarningCount = 0;
      this.mID = 0;
      this.mTickTimer = 0L;
      this.mOwnerName = "";
   }

   public void func_70310_b(NBTTagCompound aNBT) {
      try {
         super.func_70310_b(aNBT);
      } catch (Throwable var8) {
         GT_Log.err.println("Encountered CRITICAL ERROR while saving MetaTileEntity, the Chunk whould\'ve been corrupted by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
         var8.printStackTrace(GT_Log.err);
      }

      try {
         aNBT.func_74768_a("mID", this.mID);
         aNBT.func_74768_a("mStoredMJ", this.mStoredMJ);
         aNBT.func_74768_a("mStoredSteam", this.mStoredSteam);
         aNBT.func_74768_a("mStoredEnergy", this.mStoredEnergy);
         aNBT.func_74768_a("mUpgradedStorage", this.mUpgradedStorage);
         aNBT.func_74783_a("mCoverData", this.mCoverData);
         aNBT.func_74783_a("mCoverSides", this.mCoverSides);
         aNBT.func_74773_a("mRedstoneSided", this.mSidedRedstone);
         aNBT.func_74774_a("mColor", this.mColor);
         aNBT.func_74774_a("mLightValue", this.mLightValue);
         aNBT.func_74774_a("mOverclockers", this.mOverclockers);
         aNBT.func_74774_a("mTransformers", this.mTransformers);
         aNBT.func_74774_a("mRSEnergyCells", this.mRSEnergyCells);
         aNBT.func_74774_a("mSteamTanks", this.mSteamTanks);
         aNBT.func_74774_a("mOtherUpgrades", this.mOtherUpgrades);
         aNBT.func_74774_a("mWorkData", this.mWorkData);
         aNBT.func_74774_a("mStrongRedstone", this.mStrongRedstone);
         aNBT.func_74777_a("mFacing", (short)this.getFrontFacing());
         aNBT.func_74778_a("mOwnerName", this.mOwnerName);
         aNBT.func_74757_a("mLockUpgrade", this.mLockUpgrade);
         aNBT.func_74757_a("mMuffler", this.mMuffler);
         aNBT.func_74757_a("mMJConverter", this.mMJConverter);
         aNBT.func_74757_a("mSteamConverter", this.mSteamConverter);
         aNBT.func_74757_a("mActive", this.mActive);
         aNBT.func_74757_a("mRedstone", this.mRedstone);
         aNBT.func_74757_a("mWorks", !this.mWorks);
         aNBT.func_74757_a("mInputDisabled", this.mInputDisabled);
         aNBT.func_74757_a("mOutputDisabled", this.mOutputDisabled);
      } catch (Throwable var7) {
         GT_Log.err.println("Encountered CRITICAL ERROR while saving MetaTileEntity, the Chunk whould\'ve been corrupted by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
         var7.printStackTrace(GT_Log.err);
      }

      try {
         if(this.hasValidMetaTileEntity()) {
            NBTTagList e = new NBTTagList();

            for(int e1 = 0; e1 < this.mMetaTileEntity.getRealInventory().length; ++e1) {
               ItemStack tStack = this.mMetaTileEntity.getRealInventory()[e1];
               if(tStack != null) {
                  NBTTagCompound tTag = new NBTTagCompound();
                  tTag.func_74768_a("IntSlot", e1);
                  tStack.func_77955_b(tTag);
                  e.func_74742_a(tTag);
               }
            }

            aNBT.func_74782_a("Inventory", e);

            try {
               this.mMetaTileEntity.saveNBTData(aNBT);
            } catch (Throwable var6) {
               GT_Log.err.println("Encountered CRITICAL ERROR while saving MetaTileEntity, the Chunk whould\'ve been corrupted by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
               var6.printStackTrace(GT_Log.err);
            }
         }
      } catch (Throwable var9) {
         GT_Log.err.println("Encountered CRITICAL ERROR while saving MetaTileEntity, the Chunk whould\'ve been corrupted by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
         var9.printStackTrace(GT_Log.err);
      }

   }

   public void func_70307_a(NBTTagCompound aNBT) {
      super.func_70307_a(aNBT);
      this.setInitialValuesAsNBT(aNBT, (short)0);
   }

   public void setInitialValuesAsNBT(NBTTagCompound aNBT, short aID) {
      if(aNBT == null) {
         if(aID > 0) {
            this.mID = aID;
         } else {
            this.mID = this.mID > 0?this.mID:0;
         }

         if(this.mID != 0) {
            this.createNewMetatileEntity(this.mID);
         }

         this.mSidedRedstone = this.hasValidMetaTileEntity() && this.mMetaTileEntity.hasSidedRedstoneOutputBehavior()?new byte[]{(byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0}:new byte[]{(byte)15, (byte)15, (byte)15, (byte)15, (byte)15, (byte)15};
      } else {
         if(aID <= 0) {
            this.mID = (short)aNBT.func_74762_e("mID");
         } else {
            this.mID = aID;
         }

         this.mStoredMJ = aNBT.func_74762_e("mStoredMJ");
         this.mStoredSteam = aNBT.func_74762_e("mStoredSteam");
         this.mStoredEnergy = aNBT.func_74762_e("mStoredEnergy");
         this.mUpgradedStorage = aNBT.func_74762_e("mUpgradedStorage") + aNBT.func_74771_c("mBatteries") * 10000 + aNBT.func_74771_c("mLiBatteries") * 100000;
         this.mColor = aNBT.func_74771_c("mColor");
         this.mLightValue = aNBT.func_74771_c("mLightValue");
         this.mSteamTanks = aNBT.func_74771_c("mSteamTanks");
         this.mRSEnergyCells = aNBT.func_74771_c("mRSEnergyCells");
         this.mOverclockers = aNBT.func_74771_c("mOverclockers");
         this.mTransformers = aNBT.func_74771_c("mTransformers");
         this.mWorkData = aNBT.func_74771_c("mWorkData");
         this.mStrongRedstone = aNBT.func_74771_c("mStrongRedstone");
         this.mFacing = this.oFacing = (byte)aNBT.func_74765_d("mFacing");
         this.mOwnerName = aNBT.func_74779_i("mOwnerName");
         this.mLockUpgrade = aNBT.func_74767_n("mLockUpgrade");
         this.mMuffler = aNBT.func_74767_n("mMuffler");
         this.mMJConverter = aNBT.func_74767_n("mMJConverter");
         this.mSteamConverter = aNBT.func_74767_n("mSteamConverter");
         this.mActive = aNBT.func_74767_n("mActive");
         this.mRedstone = aNBT.func_74767_n("mRedstone");
         this.mWorks = !aNBT.func_74767_n("mWorks");
         this.mInputDisabled = aNBT.func_74767_n("mInputDisabled");
         this.mOutputDisabled = aNBT.func_74767_n("mOutputDisabled");
         this.mOtherUpgrades = (byte)(aNBT.func_74771_c("mOtherUpgrades") + aNBT.func_74771_c("mBatteries") + aNBT.func_74771_c("mLiBatteries"));
         this.mCoverSides = aNBT.func_74759_k("mCoverSides");
         this.mCoverData = aNBT.func_74759_k("mCoverData");
         this.mSidedRedstone = aNBT.func_74770_j("mRedstoneSided");
         if(this.mCoverData.length != 6) {
            this.mCoverData = new int[]{0, 0, 0, 0, 0, 0};
         }

         if(this.mCoverSides.length != 6) {
            this.mCoverSides = new int[]{0, 0, 0, 0, 0, 0};
         }

         if(this.mSidedRedstone.length != 6) {
            if(this.hasValidMetaTileEntity() && this.mMetaTileEntity.hasSidedRedstoneOutputBehavior()) {
               this.mSidedRedstone = new byte[]{(byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
            } else {
               this.mSidedRedstone = new byte[]{(byte)15, (byte)15, (byte)15, (byte)15, (byte)15, (byte)15};
            }
         }

         if(this.mID != 0 && this.createNewMetatileEntity(this.mID)) {
            NBTTagList tItemList = aNBT.func_74761_m("Inventory");

            for(int e = 0; e < tItemList.func_74745_c(); ++e) {
               NBTTagCompound tTag = (NBTTagCompound)tItemList.func_74743_b(e);
               int tSlot = tTag.func_74762_e("IntSlot");
               if(tSlot >= 0 && tSlot < this.mMetaTileEntity.getRealInventory().length) {
                  this.mMetaTileEntity.getRealInventory()[tSlot] = GT_Utility.loadItem(tTag);
               }
            }

            try {
               this.mMetaTileEntity.loadNBTData(aNBT);
            } catch (Throwable var7) {
               GT_Log.err.println("Encountered Exception while loading MetaTileEntity, the Server should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
               var7.printStackTrace(GT_Log.err);
            }
         }
      }

      if(this.mCoverData.length != 6) {
         this.mCoverData = new int[]{0, 0, 0, 0, 0, 0};
      }

      if(this.mCoverSides.length != 6) {
         this.mCoverSides = new int[]{0, 0, 0, 0, 0, 0};
      }

      if(this.mSidedRedstone.length != 6) {
         if(this.hasValidMetaTileEntity() && this.mMetaTileEntity.hasSidedRedstoneOutputBehavior()) {
            this.mSidedRedstone = new byte[]{(byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
         } else {
            this.mSidedRedstone = new byte[]{(byte)15, (byte)15, (byte)15, (byte)15, (byte)15, (byte)15};
         }
      }

   }

   private boolean createNewMetatileEntity(short aID) {
      if(aID >= 16 && aID < 32766 && GregTech_API.mMetaTileList[aID] != null) {
         if(aID != 0) {
            if(this.hasValidMetaTileEntity()) {
               this.mMetaTileEntity.setBaseMetaTileEntity((IGregTechTileEntity)null);
            }

            GregTech_API.mMetaTileList[aID].newMetaEntity(this).setBaseMetaTileEntity(this);
            this.mTickTimer = 0L;
            this.mID = aID;
            return true;
         }
      } else {
         GT_Log.err.println("MetaID " + aID + " not loadable => locking TileEntity!");
      }

      return false;
   }

   public void updateStatus() {}

   public void chargeItem(ItemStack aStack) {
      this.decreaseStoredEU(GT_ModHandler.chargeElectricItem(aStack, this.getStoredEU(), this.mMetaTileEntity.getOutputTier(), false, false), true);
   }

   public void dischargeItem(ItemStack aStack) {
      this.increaseStoredEnergyUnits(GT_ModHandler.dischargeElectricItem(aStack, this.getEUCapacity() - this.getStoredEU(), this.mMetaTileEntity.getInputTier(), false, false, false), true);
   }

   public void func_70316_g() {
      if(!this.hasValidMetaTileEntity()) {
         if(this.mMetaTileEntity == null) {
            return;
         }

         this.mMetaTileEntity.setBaseMetaTileEntity(this);
      }

      this.mRunningThroughTick = true;
      long tTime = System.currentTimeMillis();

      try {
         if(this.hasValidMetaTileEntity()) {
            byte e;
            if(this.mTickTimer++ == 0L) {
               this.oX = this.field_70329_l;
               this.oY = this.field_70330_m;
               this.oZ = this.field_70327_n;
               if(this.isServerSide()) {
                  for(e = 0; e < 6; ++e) {
                     if(this.getCoverIDAtSide(e) != 0 && !this.mMetaTileEntity.allowCoverOnSide(e, this.getCoverIDAtSide(e))) {
                        this.dropCover(e, e, true);
                     }
                  }
               }

               try {
                  this.mMetaTileEntity.onFirstTick();
               } catch (Throwable var11) {
                  GT_Log.err.println("Encountered Exception while ticking MetaTileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
                  var11.printStackTrace(GT_Log.err);
               }

               if(!this.hasValidMetaTileEntity()) {
                  this.mRunningThroughTick = false;
                  return;
               }
            }

            if(this.isClientSide()) {
               if(this.mColor != this.oColor) {
                  this.oColor = this.mColor;
                  this.issueTextureUpdate();
               }

               if(this.mLightValue != this.oLightValueClient) {
                  this.field_70331_k.func_72915_b(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m, this.field_70327_n, this.mLightValue);
                  this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                  this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l + 1, this.field_70330_m, this.field_70327_n);
                  this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l - 1, this.field_70330_m, this.field_70327_n);
                  this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m + 1, this.field_70327_n);
                  this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m - 1, this.field_70327_n);
                  this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m, this.field_70327_n + 1);
                  this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m, this.field_70327_n - 1);
                  this.oLightValueClient = this.mLightValue;
                  this.issueTextureUpdate();
               }

               if(this.mNeedsUpdate) {
                  this.field_70331_k.func_72902_n(this.field_70329_l, this.field_70330_m, this.field_70327_n);
                  this.mNeedsUpdate = false;
               }
            }

            if(this.isServerSide()) {
               for(e = 0; e < 6; ++e) {
                  short l = this.getCoverBehaviorAtSide(e).getTickRate(e, this.getCoverIDAtSide(e), this.mCoverData[e], this);
                  if(l > 0 && this.mTickTimer % (long)l == 0L) {
                     try {
                        this.mCoverData[e] = this.getCoverBehaviorAtSide(e).doCoverThings(e, this.getInputRedstoneSignal(e), this.getCoverIDAtSide(e), this.mCoverData[e], this);
                     } catch (Throwable var10) {
                        GT_Log.err.println("Encountered Exception while ticking Cover, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
                        var10.printStackTrace(GT_Log.err);
                     }
                  }
               }

               if(++this.mAverageEUInputIndex >= this.mAverageEUInput.length) {
                  this.mAverageEUInputIndex = 0;
               }

               if(++this.mAverageEUOutputIndex >= this.mAverageEUOutput.length) {
                  this.mAverageEUOutputIndex = 0;
               }

               this.mAverageEUInput[this.mAverageEUInputIndex] = 0;
               this.mAverageEUOutput[this.mAverageEUOutputIndex] = 0;
            }

            try {
               this.mMetaTileEntity.onPreTick();
            } catch (Throwable var9) {
               GT_Log.err.println("Encountered Exception while ticking MetaTileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
               var9.printStackTrace(GT_Log.err);
            }

            if(!this.hasValidMetaTileEntity()) {
               this.mRunningThroughTick = false;
               return;
            }

            if(this.isServerSide()) {
               if(this.mRedstone != this.oRedstone || this.mTickTimer == 10L) {
                  this.oRedstone = this.mRedstone;
                  this.issueBlockUpdate();
               }

               if(this.field_70329_l != this.oX || this.field_70330_m != this.oY || this.field_70327_n != this.oZ) {
                  this.oX = this.field_70329_l;
                  this.oY = this.field_70330_m;
                  this.oZ = this.field_70327_n;
                  this.issueClientUpdate();
               }

               if(this.mFacing != this.oFacing) {
                  this.oFacing = this.mFacing;

                  for(e = 0; e < 6; ++e) {
                     if(this.getCoverIDAtSide(e) != 0 && !this.mMetaTileEntity.allowCoverOnSide(e, this.getCoverIDAtSide(e))) {
                        this.dropCover(e, e, true);
                     }
                  }

                  this.issueBlockUpdate();
               }

               if(this.getOutputVoltage() != this.oOutput) {
                  this.oOutput = this.getOutputVoltage();
               }

               if(this.mMetaTileEntity.isElectric() && (this.mMetaTileEntity.isEnetOutput() || this.mMetaTileEntity.isEnetInput())) {
                  for(e = 0; e < 6; ++e) {
                     boolean var15 = this.isEnergyInputSide(e);
                     if(var15 != this.mActiveEUInputs[e]) {
                        this.mActiveEUInputs[e] = var15;
                        if(this.mIsAddedToEnet) {
                           this.mIsAddedToEnet = !GT_ModHandler.removeTileFromEnet(this.field_70331_k, this);
                        }
                     }

                     var15 = this.isEnergyOutputSide(e);
                     if(var15 != this.mActiveEUOutputs[e]) {
                        this.mActiveEUOutputs[e] = var15;
                        if(this.mIsAddedToEnet) {
                           this.mIsAddedToEnet = !GT_ModHandler.removeTileFromEnet(this.field_70331_k, this);
                        }
                     }
                  }

                  if(!this.mIsAddedToEnet) {
                     this.mIsAddedToEnet = GT_ModHandler.addTileToEnet(this.field_70331_k, this);
                  }
               }

               int var16;
               int var17;
               if(this.mIsAddedToEnet && this.mMetaTileEntity.isElectric() && this.mMetaTileEntity.isEnetOutput() && this.getOutputVoltage() > 0) {
                  for(var17 = 0; var17 < this.getOutputAmperage() && this.getStoredEU() >= this.getOutputVoltage() + this.mMetaTileEntity.getMinimumStoredEU(); ++var17) {
                     var16 = GT_ModHandler.emitEnergyToEnet(this.getOutputVoltage(), this.field_70331_k, this) - this.getOutputVoltage();
                     this.mAverageEUOutput[this.mAverageEUOutputIndex] -= var16;
                     this.decreaseStoredEU(-var16, true);
                  }
               }

               try {
                  if(this.getEUCapacity() > 0) {
                     if(GregTech_API.sMachineFireExplosions && this.getRandomNumber(1000) == 0) {
                        Block var18 = this.getBlockAtSide((byte)this.getRandomNumber(6));
                        if(var18 != null && (var18 == Block.field_72067_ar || var18 instanceof BlockFire)) {
                           this.doEnergyExplosion();
                        }
                     }

                     if(!this.hasValidMetaTileEntity()) {
                        this.mRunningThroughTick = false;
                        return;
                     }

                     if(this.getRandomNumber(1000) == 0 && (this.getCoverIDAtSide((byte)1) == 0 && this.field_70331_k.func_72874_g(this.field_70329_l, this.field_70327_n) - 2 < this.field_70330_m || this.getCoverIDAtSide((byte)2) == 0 && this.field_70331_k.func_72874_g(this.field_70329_l, this.field_70327_n - 1) - 1 < this.field_70330_m || this.getCoverIDAtSide((byte)3) == 0 && this.field_70331_k.func_72874_g(this.field_70329_l, this.field_70327_n + 1) - 1 < this.field_70330_m || this.getCoverIDAtSide((byte)4) == 0 && this.field_70331_k.func_72874_g(this.field_70329_l - 1, this.field_70327_n) - 1 < this.field_70330_m || this.getCoverIDAtSide((byte)5) == 0 && this.field_70331_k.func_72874_g(this.field_70329_l + 1, this.field_70327_n) - 1 < this.field_70330_m)) {
                        if(GregTech_API.sMachineRainExplosions && this.field_70331_k.func_72896_J() && this.getBiome().field_76751_G > 0.0F) {
                           if(this.getRandomNumber(10) == 0) {
                              this.doEnergyExplosion();
                           } else {
                              this.setOnFire();
                           }
                        }

                        if(!this.hasValidMetaTileEntity()) {
                           this.mRunningThroughTick = false;
                           return;
                        }

                        if(GregTech_API.sMachineThunderExplosions && this.field_70331_k.func_72911_I() && this.getRandomNumber(3) == 0) {
                           this.doEnergyExplosion();
                        }
                     }
                  }
               } catch (Throwable var13) {
                  GT_Log.err.println("Encountered Exception while checking for Explosion conditions");
                  var13.printStackTrace(GT_Log.err);
               }

               if(!this.hasValidMetaTileEntity()) {
                  this.mRunningThroughTick = false;
                  return;
               }

               try {
                  int i;
                  int k;
                  if(this.mMetaTileEntity.dechargerSlotCount() > 0 && this.getStoredEU() < this.getEUCapacity()) {
                     var17 = 0;

                     for(var16 = this.mMetaTileEntity.getDechargeCyles(); var17 < var16; ++var17) {
                        i = this.mMetaTileEntity.dechargerSlotStartIndex();

                        for(k = this.mMetaTileEntity.dechargerSlotCount() + i; i < k; ++i) {
                           if(this.mMetaTileEntity.mInventory[i] != null && this.getStoredEU() < this.getEUCapacity()) {
                              this.dischargeItem(this.mMetaTileEntity.mInventory[i]);
                              if(this.mMetaTileEntity.mInventory[i].field_77994_a <= 0) {
                                 this.mMetaTileEntity.mInventory[i] = null;
                              }

                              this.mInventoryChanged = true;
                           }
                        }
                     }
                  }

                  if(this.mMetaTileEntity.rechargerSlotCount() > 0 && this.getStoredEU() > 0) {
                     var17 = 0;

                     for(var16 = this.mMetaTileEntity.getChargeCyles(); var17 < var16; ++var17) {
                        i = this.mMetaTileEntity.rechargerSlotStartIndex();

                        for(k = this.mMetaTileEntity.rechargerSlotCount() + i; i < k; ++i) {
                           if(this.getStoredEU() > 0 && this.mMetaTileEntity.mInventory[i] != null) {
                              this.chargeItem(this.mMetaTileEntity.mInventory[i]);
                              if(this.mMetaTileEntity.mInventory[i].field_77994_a <= 0) {
                                 this.mMetaTileEntity.mInventory[i] = null;
                              }

                              this.mInventoryChanged = true;
                           }
                        }
                     }
                  }
               } catch (Throwable var12) {
                  GT_Log.err.println("Encountered Exception while charging/decharging Items");
                  var12.printStackTrace(GT_Log.err);
               }
            }

            try {
               this.updateStatus();
            } catch (Throwable var8) {
               GT_Log.err.println("Encountered Exception in Cross Mod Energy Systems");
               var8.printStackTrace(GT_Log.err);
            }

            if(!this.hasValidMetaTileEntity()) {
               this.mRunningThroughTick = false;
               return;
            }

            try {
               this.mMetaTileEntity.onPostTick();
            } catch (Throwable var7) {
               GT_Log.err.println("Encountered Exception while ticking MetaTileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
               var7.printStackTrace(GT_Log.err);
            }

            if(!this.hasValidMetaTileEntity()) {
               this.mRunningThroughTick = false;
               return;
            }

            if(this.isServerSide()) {
               if(this.mTickTimer % 10L == 0L && this.mSendClientData) {
                  GT_Utility.sendPacketToAllPlayersInRange(this.field_70331_k, this.func_70319_e(), this.field_70329_l, this.field_70327_n);
                  this.mSendClientData = false;
               }

               if(this.mTickTimer > 10L) {
                  e = (byte)(this.getFrontFacing() & 7 | (this.mActive?8:0) | (this.mRedstone?16:0) | (this.mLockUpgrade?32:0));
                  if(e != this.oTextureData) {
                     this.sendBlockEvent((byte)0, this.oTextureData = e);
                  }

                  e = this.mMetaTileEntity.getUpdateData();
                  if(e != this.oUpdateData) {
                     this.sendBlockEvent((byte)1, this.oUpdateData = e);
                  }

                  if(this.mColor != this.oColor) {
                     this.sendBlockEvent((byte)2, this.oColor = this.mColor);
                  }

                  e = (byte)((this.mSidedRedstone[0] > 0?1:0) | (this.mSidedRedstone[1] > 0?2:0) | (this.mSidedRedstone[2] > 0?4:0) | (this.mSidedRedstone[3] > 0?8:0) | (this.mSidedRedstone[4] > 0?16:0) | (this.mSidedRedstone[5] > 0?32:0));
                  if(e != this.oRedstoneData) {
                     this.sendBlockEvent((byte)3, this.oRedstoneData = e);
                  }

                  if(this.mLightValue != this.oLightValue) {
                     this.field_70331_k.func_72915_b(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m, this.field_70327_n, this.mLightValue);
                     this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                     this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l + 1, this.field_70330_m, this.field_70327_n);
                     this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l - 1, this.field_70330_m, this.field_70327_n);
                     this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m + 1, this.field_70327_n);
                     this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m - 1, this.field_70327_n);
                     this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m, this.field_70327_n + 1);
                     this.field_70331_k.func_72936_c(EnumSkyBlock.Block, this.field_70329_l, this.field_70330_m, this.field_70327_n - 1);
                     this.issueTextureUpdate();
                     this.sendBlockEvent((byte)7, this.oLightValue = this.mLightValue);
                  }
               }

               if(this.mNeedsBlockUpdate) {
                  this.field_70331_k.func_72898_h(this.field_70329_l, this.field_70330_m, this.field_70327_n, this.getBlockIDOffset(0, 0, 0));
                  this.mNeedsBlockUpdate = false;
               }
            }
         }
      } catch (Throwable var14) {
         GT_Log.err.println("Encountered Exception while ticking TileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
         var14.printStackTrace(GT_Log.err);
      }

      if(this.isServerSide() && this.hasValidMetaTileEntity()) {
         tTime = System.currentTimeMillis() - tTime;
         if(this.mTimeStatistics.length > 0) {
            this.mTimeStatistics[this.mTimeStatisticsIndex = (this.mTimeStatisticsIndex + 1) % this.mTimeStatistics.length] = (int)tTime;
         }

         if(tTime > 0L && tTime > (long)GregTech_API.MILLISECOND_THRESHOLD_UNTIL_LAG_WARNING && this.mTickTimer > 1000L && this.getMetaTileEntity().doTickProfilingMessageDuringThisTick() && this.mLagWarningCount++ < 10) {
            System.out.println("WARNING: Possible Lag Source at [" + this.field_70329_l + ", " + this.field_70330_m + ", " + this.field_70327_n + "] in Dimension " + this.field_70331_k.field_73011_w.field_76574_g + " with " + tTime + "ms caused by an instance of " + this.getMetaTileEntity().getClass());
         }
      }

      this.mWorkUpdate = this.mInventoryChanged = this.mRunningThroughTick = false;
   }

   public Packet func_70319_e() {
      Packet250CustomPayload rPacket = new Packet250CustomPayload();
      rPacket.field_73630_a = "GTTile";
      rPacket.field_73287_r = true;
      ByteArrayDataOutput tOut = ByteStreams.newDataOutput();
      tOut.writeInt(this.field_70329_l);
      tOut.writeShort(this.field_70330_m);
      tOut.writeInt(this.field_70327_n);
      tOut.writeShort(this.mID);
      tOut.writeInt(this.mCoverSides[0]);
      tOut.writeInt(this.mCoverSides[1]);
      tOut.writeInt(this.mCoverSides[2]);
      tOut.writeInt(this.mCoverSides[3]);
      tOut.writeInt(this.mCoverSides[4]);
      tOut.writeInt(this.mCoverSides[5]);
      tOut.writeByte(this.oTextureData = (byte)(this.getFrontFacing() & 7 | (this.mActive?8:0) | (this.mRedstone?16:0) | (this.mLockUpgrade?32:0)));
      tOut.writeByte(this.oUpdateData = this.hasValidMetaTileEntity()?this.mMetaTileEntity.getUpdateData():0);
      tOut.writeByte(this.oRedstoneData = (byte)((this.mSidedRedstone[0] > 0?1:0) | (this.mSidedRedstone[1] > 0?2:0) | (this.mSidedRedstone[2] > 0?4:0) | (this.mSidedRedstone[3] > 0?8:0) | (this.mSidedRedstone[4] > 0?16:0) | (this.mSidedRedstone[5] > 0?32:0)));
      tOut.writeByte(this.oColor = this.mColor);
      this.oLightValue = this.oLightValueClient = -1;
      rPacket.field_73629_c = tOut.toByteArray();
      rPacket.field_73628_b = rPacket.field_73629_c.length;
      return rPacket;
   }

   public final void receiveMetaTileEntityData(short aID, int aCover0, int aCover1, int aCover2, int aCover3, int aCover4, int aCover5, byte aTextureData, byte aUpdateData, byte aRedstoneData, byte aColorData) {
      this.issueTextureUpdate();
      if(this.mID != aID && aID > 0) {
         this.mID = aID;
         this.createNewMetatileEntity(this.mID);
      }

      this.mCoverSides[0] = aCover0;
      this.mCoverSides[1] = aCover1;
      this.mCoverSides[2] = aCover2;
      this.mCoverSides[3] = aCover3;
      this.mCoverSides[4] = aCover4;
      this.mCoverSides[5] = aCover5;
      this.func_70315_b(0, aTextureData);
      this.func_70315_b(1, aUpdateData);
      this.func_70315_b(3, aRedstoneData);
      this.func_70315_b(2, aColorData);
   }

   public boolean func_70315_b(int aEventID, int aValue) {
      super.func_70315_b(aEventID, aValue);
      if(this.hasValidMetaTileEntity()) {
         try {
            this.mMetaTileEntity.receiveClientEvent((byte)aEventID, (byte)aValue);
         } catch (Throwable var4) {
            GT_Log.err.println("Encountered Exception while receiving Data from the Server, the Client should\'ve been crashed by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
            var4.printStackTrace(GT_Log.err);
         }
      }

      if(this.isClientSide()) {
         this.issueTextureUpdate();
         switch(aEventID) {
         case 0:
            this.mFacing = (byte)(aValue & 7);
            this.mActive = (aValue & 8) != 0;
            this.mRedstone = (aValue & 16) != 0;
            this.mLockUpgrade = (aValue & 32) != 0;
            break;
         case 1:
            if(this.hasValidMetaTileEntity()) {
               this.mMetaTileEntity.onValueUpdate((byte)aValue);
            }
            break;
         case 2:
            this.mColor = (byte)aValue;
            break;
         case 3:
            this.mSidedRedstone[0] = (byte)((aValue & 1) > 0?15:0);
            this.mSidedRedstone[1] = (byte)((aValue & 2) > 0?15:0);
            this.mSidedRedstone[2] = (byte)((aValue & 4) > 0?15:0);
            this.mSidedRedstone[3] = (byte)((aValue & 8) > 0?15:0);
            this.mSidedRedstone[4] = (byte)((aValue & 16) > 0?15:0);
            this.mSidedRedstone[5] = (byte)((aValue & 32) > 0?15:0);
            break;
         case 4:
            if(this.hasValidMetaTileEntity() && this.mTickTimer > 20L) {
               this.mMetaTileEntity.doSound((byte)aValue, (double)this.field_70329_l + 0.5D, (double)this.field_70330_m + 0.5D, (double)this.field_70327_n + 0.5D);
            }
            break;
         case 5:
            if(this.hasValidMetaTileEntity() && this.mTickTimer > 20L) {
               this.mMetaTileEntity.startSoundLoop((byte)aValue, (double)this.field_70329_l + 0.5D, (double)this.field_70330_m + 0.5D, (double)this.field_70327_n + 0.5D);
            }
            break;
         case 6:
            if(this.hasValidMetaTileEntity() && this.mTickTimer > 20L) {
               this.mMetaTileEntity.stopSoundLoop((byte)aValue, (double)this.field_70329_l + 0.5D, (double)this.field_70330_m + 0.5D, (double)this.field_70327_n + 0.5D);
            }
            break;
         case 7:
            this.mLightValue = (byte)aValue;
         }
      }

      return true;
   }

   public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aLogLevel) {
      ArrayList tList = new ArrayList();
      if(aLogLevel > 2) {
         tList.add("Meta-ID: " + this.mID + (this.hasValidMetaTileEntity()?" valid":" invalid") + (this.mMetaTileEntity == null?" MetaTileEntity == null!":" "));
      }

      if(aLogLevel > 1) {
         if(this.mTimeStatistics.length > 0) {
            double tAverageTime = 0.0D;
            int[] arr$ = this.mTimeStatistics;
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               int tTime = arr$[i$];
               tAverageTime += (double)tTime;
            }

            tList.add("This particular TileEntity has caused an average CPU-load of ~" + tAverageTime / (double)this.mTimeStatistics.length + "ms over the last " + this.mTimeStatistics.length + " ticks.");
         }

         if(this.mLagWarningCount > 0) {
            tList.add("This TileEntity has also caused " + (this.mLagWarningCount >= 10?"more than 10":Integer.valueOf(this.mLagWarningCount)) + " Lag Spike Warnings (anything taking longer than " + GregTech_API.MILLISECOND_THRESHOLD_UNTIL_LAG_WARNING + "ms) on the Server.");
         }

         tList.add("Is" + (this.mMetaTileEntity.isAccessAllowed(aPlayer)?" ":" not ") + "accessible for you");
      }

      if(aLogLevel > 0) {
         if(this.getMJCapacity() > 0 && this.hasMJConverterUpgrade()) {
            tList.add(this.getStoredMJ() + " of " + this.getMJCapacity() + " MJ");
         }

         if(this.getSteamCapacity() > 0 && this.hasSteamEngineUpgrade()) {
            tList.add(this.getStoredSteam() + " of " + this.getSteamCapacity() + " Steam");
         }

         tList.add("Machine is " + (this.mActive?"active":"inactive"));
         if(this.mNeedsBatteryUpgrade && this.isBatteryUpgradable(10000, (byte)1)) {
            tList.add("WARNING: Requires more Energy Capacity to work! Add Battery Upgrades!");
         }

         if(!this.mHasEnoughEnergy) {
            tList.add("ATTENTION: This Device consumes Energy at a higher Rate than you input. You could insert more to speed up the process.");
         }
      }

      return this.mMetaTileEntity.getSpecialDebugInfo(aPlayer, aLogLevel, tList);
   }

   public void issueTextureUpdate() {
      this.mNeedsUpdate = true;
   }

   public void issueBlockUpdate() {
      this.mNeedsBlockUpdate = true;
   }

   public void issueClientUpdate() {
      this.mSendClientData = true;
   }

   public void issueCoverUpdate(byte aSide) {
      this.issueClientUpdate();
   }

   public byte getStrongestRedstone() {
      return (byte)Math.max(this.getInternalInputRedstoneSignal((byte)0), Math.max(this.getInternalInputRedstoneSignal((byte)1), Math.max(this.getInternalInputRedstoneSignal((byte)2), Math.max(this.getInternalInputRedstoneSignal((byte)3), Math.max(this.getInternalInputRedstoneSignal((byte)4), this.getInternalInputRedstoneSignal((byte)5))))));
   }

   public boolean getRedstone() {
      return this.getRedstone((byte)0) || this.getRedstone((byte)1) || this.getRedstone((byte)2) || this.getRedstone((byte)3) || this.getRedstone((byte)4) || this.getRedstone((byte)5);
   }

   public boolean getRedstone(byte aSide) {
      return this.getInternalInputRedstoneSignal(aSide) > 0;
   }

   public Icon getCoverTexture(byte aSide) {
      return (Icon)GregTech_API.sCovers.get(Integer.valueOf(this.getCoverIDAtSide(aSide)));
   }

   public boolean isGivingInformation() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.isGivingInformation():false;
   }

   public boolean isValidFacing(byte aSide) {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.isFacingValid(aSide):false;
   }

   public byte getBackFacing() {
      return GT_Utility.getOppositeSide(this.getFrontFacing());
   }

   public byte getFrontFacing() {
      return this.mFacing;
   }

   public void setFrontFacing(byte aFacing) {
      if(this.isValidFacing(aFacing)) {
         this.mFacing = aFacing;
         this.mMetaTileEntity.onFacingChange();
         this.onMachineBlockUpdate();
      }

   }

   public int func_70302_i_() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.func_70302_i_():0;
   }

   public ItemStack func_70301_a(int aIndex) {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.func_70301_a(aIndex):null;
   }

   public void func_70299_a(int aIndex, ItemStack aStack) {
      this.mInventoryChanged = true;
      if(this.hasValidMetaTileEntity()) {
         this.mMetaTileEntity.func_70299_a(aIndex, GT_OreDictUnificator.setStack(true, aStack));
      }

   }

   public String func_70303_b() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.func_70303_b():(GregTech_API.mMetaTileList[this.mID] != null?GregTech_API.mMetaTileList[this.mID].func_70303_b():"");
   }

   public int func_70297_j_() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.func_70297_j_():64;
   }

   public void func_70295_k_() {
      if(this.hasValidMetaTileEntity()) {
         this.mMetaTileEntity.onOpenGUI();
      }

   }

   public void func_70305_f() {
      if(this.hasValidMetaTileEntity()) {
         this.mMetaTileEntity.onCloseGUI();
      }

   }

   public boolean func_70300_a(EntityPlayer aPlayer) {
      return this.hasValidMetaTileEntity() && this.playerOwnsThis(aPlayer, false) && this.mTickTimer > 40L && this.getTileEntityOffset(0, 0, 0) == this && aPlayer.func_70092_e((double)this.field_70329_l + 0.5D, (double)this.field_70330_m + 0.5D, (double)this.field_70327_n + 0.5D) < 64.0D && this.mMetaTileEntity.isAccessAllowed(aPlayer);
   }

   public void func_70312_q() {
      super.func_70312_q();
      this.mTickTimer = 0L;
   }

   public void func_70313_j() {
      this.field_70328_o = false;
      if(this.hasValidMetaTileEntity()) {
         this.mMetaTileEntity.onRemoval();
         this.mMetaTileEntity.setBaseMetaTileEntity((IGregTechTileEntity)null);
      }

      if(this.isServerSide()) {
         GT_ModHandler.removeTileFromEnet(this.field_70331_k, this);
         this.mIsAddedToEnet = false;
      }

      super.func_70313_j();
   }

   public void onChunkUnload() {
      if(this.isServerSide()) {
         GT_ModHandler.removeTileFromEnet(this.field_70331_k, this);
         this.mIsAddedToEnet = false;
      }

      super.onChunkUnload();
   }

   public boolean func_94042_c() {
      return false;
   }

   public ItemStack func_70304_b(int slot) {
      ItemStack stack = this.func_70301_a(slot);
      if(stack != null) {
         this.func_70299_a(slot, (ItemStack)null);
      }

      return stack;
   }

   public void onMachineBlockUpdate() {
      if(this.hasValidMetaTileEntity()) {
         this.mMetaTileEntity.onMachineBlockUpdate();
      }

   }

   public int getProgress() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.getProgresstime():0;
   }

   public int getMaxProgress() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.maxProgresstime():0;
   }

   public boolean increaseProgress(int aProgressAmountInTicks) {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.increaseProgress(aProgressAmountInTicks) != aProgressAmountInTicks:false;
   }

   public boolean hasThingsToDo() {
      return this.getMaxProgress() > 0;
   }

   public void enableWorking() {
      if(!this.mWorks) {
         this.mWorkUpdate = true;
      }

      this.mWorks = true;
   }

   public void disableWorking() {
      this.mWorks = false;
   }

   public boolean isAllowedToWork() {
      return this.mWorks;
   }

   public boolean hasWorkJustBeenEnabled() {
      return this.mWorkUpdate;
   }

   public void setWorkDataValue(byte aValue) {
      this.mWorkData = aValue;
   }

   public byte getWorkDataValue() {
      return this.mWorkData;
   }

   public int getMetaTileID() {
      return this.mID;
   }

   public int setMetaTileID(short aID) {
      return this.mID = aID;
   }

   public boolean isActive() {
      return this.mActive;
   }

   public void setActive(boolean aActive) {
      this.mActive = aActive;
   }

   public long getTimer() {
      return this.mTickTimer;
   }

   public boolean decreaseStoredEnergyUnits(int aEnergy, boolean aIgnoreTooLessEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else {
         if(this.getUniversalEnergyCapacity() < aEnergy) {
            this.mNeedsBatteryUpgrade = true;
         }

         return this.mHasEnoughEnergy = this.decreaseStoredMJ(aEnergy, false) || this.decreaseStoredSteam(aEnergy, false) || this.decreaseStoredEU(aEnergy, aIgnoreTooLessEnergy) || aIgnoreTooLessEnergy && (this.decreaseStoredMJ(aEnergy, true) || this.decreaseStoredSteam(aEnergy, true));
      }
   }

   public boolean increaseStoredEnergyUnits(int aEnergy, boolean aIgnoreTooMuchEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else if(this.getStoredEU() >= this.getEUCapacity() && !aIgnoreTooMuchEnergy) {
         return false;
      } else {
         this.setStoredEU(this.mMetaTileEntity.getEUVar() + aEnergy);
         return true;
      }
   }

   public boolean inputEnergyFrom(byte aSide) {
      return aSide == 6?true:(!this.isServerSide()?this.isEnergyInputSide(aSide):aSide >= 0 && aSide < 6 && this.mActiveEUInputs[aSide] && !this.mReleaseEnergy);
   }

   public boolean outputsEnergyTo(byte aSide) {
      return aSide == 6?true:(!this.isServerSide()?this.isEnergyOutputSide(aSide):aSide >= 0 && aSide < 6 && this.mActiveEUOutputs[aSide] || this.mReleaseEnergy);
   }

   public int getOutputAmperage() {
      return this.hasValidMetaTileEntity() && this.mMetaTileEntity.isElectric()?(this.mMetaTileEntity.maxEUPulses() == 1 && this.mMetaTileEntity.isTransformingLowEnergy() && this.mTransformers > 0?4:this.mMetaTileEntity.maxEUPulses()):0;
   }

   public int getOutputVoltage() {
      return this.hasValidMetaTileEntity() && this.mMetaTileEntity.isElectric() && this.mMetaTileEntity.isEnetOutput()?this.mMetaTileEntity.maxEUOutput() * (this.mTransformers > 0?(int)Math.pow(4.0D, (double)(this.mTransformers - (this.mMetaTileEntity.isTransformingLowEnergy()?1:0))):1):0;
   }

   public int getInputVoltage() {
      return this.hasValidMetaTileEntity() && this.mMetaTileEntity.isElectric()?this.mMetaTileEntity.maxEUInput() * (int)Math.pow(4.0D, (double)this.mTransformers):(this.mMetaTileEntity.isElectric()?Integer.MAX_VALUE:0);
   }

   public boolean increaseStoredMJ(int aEnergy, boolean aIgnoreTooMuchEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else if(this.mMetaTileEntity.getMJVar() >= this.getMJCapacity() && !aIgnoreTooMuchEnergy) {
         return false;
      } else {
         this.setStoredMJ(this.mMetaTileEntity.getMJVar() + aEnergy);
         return true;
      }
   }

   public boolean increaseStoredSteam(int aEnergy, boolean aIgnoreTooMuchEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else if(this.mMetaTileEntity.getSteamVar() >= this.getSteamCapacity() && !aIgnoreTooMuchEnergy) {
         return false;
      } else {
         this.setStoredSteam(this.mMetaTileEntity.getSteamVar() + aEnergy);
         return true;
      }
   }

   public String getDescription() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.getDescription():"";
   }

   public boolean isValidSlot(int aIndex) {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.isValidSlot(aIndex):false;
   }

   public int getUniversalEnergyStored() {
      return Math.max(Math.max(this.getStoredEU(), this.getStoredMJ()), this.getStoredSteam());
   }

   public int getUniversalEnergyCapacity() {
      return Math.max(Math.max(this.getEUCapacity(), this.getMJCapacity()), this.getSteamCapacity());
   }

   public int getStoredMJ() {
      return this.hasValidMetaTileEntity()?Math.min(this.mMetaTileEntity.getMJVar(), this.getMJCapacity()):0;
   }

   public int getMJCapacity() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.maxMJStore() + this.mRSEnergyCells * 100000:0;
   }

   public int getStoredEU() {
      return this.hasValidMetaTileEntity()?Math.min(this.mMetaTileEntity.getEUVar(), this.getEUCapacity()):0;
   }

   public int getEUCapacity() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.maxEUStore() + this.getUpgradeStorageVolume():0;
   }

   public int getStoredSteam() {
      return this.hasValidMetaTileEntity()?Math.min(this.mMetaTileEntity.getSteamVar(), this.getSteamCapacity()):0;
   }

   public int getSteamCapacity() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.maxSteamStore() + this.mSteamTanks * 32000:0;
   }

   public int getTextureIndex(byte aSide, byte aMeta) {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.getTextureIndex(aSide, this.getFrontFacing(), this.mActive, this.getOutputRedstoneSignal(aSide) > 0):-2;
   }

   public Icon getTextureIcon(byte aSide, byte aMeta) {
      Icon rIcon = this.getCoverTexture(aSide);
      return rIcon != null?rIcon:(this.hasValidMetaTileEntity()?this.mMetaTileEntity.getTextureIcon(aSide, this.getFrontFacing(), this.mActive, this.getOutputRedstoneSignal(aSide) > 0):null);
   }

   private boolean isEnergyInputSide(byte aSide) {
      if(aSide >= 0 && aSide < 6) {
         if(!this.getCoverBehaviorAtSide(aSide).letsEnergyIn(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this)) {
            return false;
         }

         if(this.func_70320_p() || this.mReleaseEnergy) {
            return false;
         }

         if(this.hasValidMetaTileEntity() && this.mMetaTileEntity.isElectric() && this.mMetaTileEntity.isEnetInput()) {
            return this.mMetaTileEntity.isInputFacing(aSide);
         }
      }

      return false;
   }

   private boolean isEnergyOutputSide(byte aSide) {
      if(aSide >= 0 && aSide < 6) {
         if(!this.getCoverBehaviorAtSide(aSide).letsEnergyOut(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this)) {
            return false;
         }

         if(this.func_70320_p() || this.mReleaseEnergy) {
            return this.mReleaseEnergy;
         }

         if(this.hasValidMetaTileEntity() && this.mMetaTileEntity.isElectric() && this.mMetaTileEntity.isEnetOutput()) {
            return this.mMetaTileEntity.isOutputFacing(aSide);
         }
      }

      return false;
   }

   protected boolean hasValidMetaTileEntity() {
      return this.mMetaTileEntity != null && this.mMetaTileEntity.getBaseMetaTileEntity() == this;
   }

   public boolean setStoredEU(int aEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else {
         if(aEnergy < 0) {
            aEnergy = 0;
         }

         this.mMetaTileEntity.setEUVar(aEnergy);
         return true;
      }
   }

   public boolean setStoredMJ(int aEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else {
         if(aEnergy < 0) {
            aEnergy = 0;
         }

         this.mMetaTileEntity.setMJVar(aEnergy);
         return true;
      }
   }

   public boolean setStoredSteam(int aEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else {
         if(aEnergy < 0) {
            aEnergy = 0;
         }

         this.mMetaTileEntity.setSteamVar(aEnergy);
         return true;
      }
   }

   public boolean decreaseStoredEU(int aEnergy, boolean aIgnoreTooLessEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else if(this.mMetaTileEntity.getEUVar() - aEnergy < 0 && !aIgnoreTooLessEnergy) {
         return false;
      } else {
         this.setStoredEU(this.mMetaTileEntity.getEUVar() - aEnergy);
         if(this.mMetaTileEntity.getEUVar() < 0) {
            this.setStoredEU(0);
            return false;
         } else {
            return true;
         }
      }
   }

   public boolean decreaseStoredMJ(int aEnergy, boolean aIgnoreTooLessEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else if(this.mMetaTileEntity.getMJVar() - aEnergy < 0 && !aIgnoreTooLessEnergy) {
         return false;
      } else {
         this.setStoredMJ(this.mMetaTileEntity.getMJVar() - aEnergy);
         if(this.mMetaTileEntity.getMJVar() < 0) {
            this.setStoredMJ(0);
            return false;
         } else {
            return true;
         }
      }
   }

   public boolean decreaseStoredSteam(int aEnergy, boolean aIgnoreTooLessEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else if(this.mMetaTileEntity.getSteamVar() - aEnergy < 0 && !aIgnoreTooLessEnergy) {
         return false;
      } else {
         this.setStoredSteam(this.mMetaTileEntity.getSteamVar() - aEnergy);
         if(this.mMetaTileEntity.getSteamVar() < 0) {
            this.setStoredSteam(0);
            return false;
         } else {
            return true;
         }
      }
   }

   public boolean playerOwnsThis(EntityPlayer aPlayer, boolean aCheckPrecicely) {
      if(!this.hasValidMetaTileEntity()) {
         return false;
      } else {
         if(aCheckPrecicely || this.unbreakable() || this.privateAccess() || this.mOwnerName.equals("")) {
            if(this.mOwnerName.equals("") && this.isServerSide()) {
               this.setOwnerName(aPlayer.field_71092_bJ);
            } else if(this.privateAccess() && !aPlayer.field_71092_bJ.equals("Player") && !this.mOwnerName.equals("Player") && !this.mOwnerName.equals(aPlayer.field_71092_bJ)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean privateAccess() {
      return !this.hasValidMetaTileEntity()?this.mLockUpgrade:this.mLockUpgrade || this.mMetaTileEntity.ownerControl();
   }

   public boolean unbreakable() {
      return !this.hasValidMetaTileEntity()?this.mLockUpgrade:this.mLockUpgrade || this.mMetaTileEntity.unbreakable();
   }

   public void doEnergyExplosion() {
      if(this.getUniversalEnergyCapacity() > 0 && this.getUniversalEnergyStored() >= this.getUniversalEnergyCapacity() / 5) {
         this.doExplosion(this.getOutputVoltage() * (this.getUniversalEnergyStored() >= this.getUniversalEnergyCapacity()?4:(this.getUniversalEnergyStored() >= this.getUniversalEnergyCapacity() / 2?2:1)));
      }

   }

   public void doExplosion(int aAmount) {
      if(this.mIsAddedToEnet && GregTech_API.sMachineWireFire && this.hasValidMetaTileEntity() && this.mMetaTileEntity.isElectric()) {
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
      if(this.hasValidMetaTileEntity()) {
         this.mMetaTileEntity.onExplosion();
      }

      float tStrength = aAmount < 8?1.0F:(aAmount < 32?2.0F:(aAmount < 128?3.0F:(aAmount < 512?4.0F:(aAmount < 2048?5.0F:(aAmount < 4096?6.0F:(aAmount < 8192?7.0F:(aAmount < '\u8000'?8.0F:(aAmount < 1000000?9.0F:10.0F))))))));
      int tX = this.field_70329_l;
      int tY = this.field_70330_m;
      int tZ = this.field_70327_n;
      GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(209)), 1.0F, -1.0F, tX, tY, tZ);
      this.field_70331_k.func_94575_c(tX, tY, tZ, 0);
      if(GregTech_API.sMachineExplosions) {
         this.field_70331_k.func_72876_a((Entity)null, (double)tX + 0.5D, (double)tY + 0.5D, (double)tZ + 0.5D, tStrength, true);
      }

   }

   private ItemStack getDrop() {
      ItemStack rStack = new ItemStack(GregTech_API.sBlockList[1], 1, this.mID);
      NBTTagCompound tNBT = new NBTTagCompound();
      if(this.mMuffler) {
         tNBT.func_74757_a("mMuffler", this.mMuffler);
      }

      if(this.mLockUpgrade) {
         tNBT.func_74757_a("mLockUpgrade", this.mLockUpgrade);
      }

      if(this.mMJConverter) {
         tNBT.func_74757_a("mMJConverter", this.mMJConverter);
      }

      if(this.mSteamConverter) {
         tNBT.func_74757_a("mSteamConverter", this.mSteamConverter);
      }

      if(this.mColor > 0) {
         tNBT.func_74774_a("mColor", this.mColor);
      }

      if(this.mTransformers > 0) {
         tNBT.func_74774_a("mTransformers", this.mTransformers);
      }

      if(this.mOverclockers > 0) {
         tNBT.func_74774_a("mOverclockers", this.mOverclockers);
      }

      if(this.mRSEnergyCells > 0) {
         tNBT.func_74774_a("mRSEnergyCells", this.mRSEnergyCells);
      }

      if(this.mSteamTanks > 0) {
         tNBT.func_74774_a("mSteamTanks", this.mSteamTanks);
      }

      if(this.mOtherUpgrades > 0) {
         tNBT.func_74774_a("mOtherUpgrades", this.mOtherUpgrades);
      }

      if(this.mUpgradedStorage > 0) {
         tNBT.func_74768_a("mUpgradedStorage", this.mUpgradedStorage);
      }

      if(this.mStrongRedstone > 0) {
         tNBT.func_74774_a("mStrongRedstone", this.mStrongRedstone);
      }

      for(byte i = 0; i < this.mCoverSides.length; ++i) {
         if(this.mCoverSides[i] != 0) {
            tNBT.func_74783_a("mCoverData", this.mCoverData);
            tNBT.func_74783_a("mCoverSides", this.mCoverSides);
            break;
         }
      }

      if(this.hasValidMetaTileEntity()) {
         this.mMetaTileEntity.setItemNBT(tNBT);
      }

      if(!tNBT.func_82582_d()) {
         rStack.func_77982_d(tNBT);
      }

      return rStack;
   }

   public int getUpgradeCount() {
      return (this.mMuffler?1:0) + (this.mLockUpgrade?1:0) + (this.mMJConverter?1:0) + (this.mSteamConverter?1:0) + this.mSteamTanks + this.mTransformers + this.mOverclockers + this.mOtherUpgrades + this.mRSEnergyCells;
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      if(this.isClientSide() && this.getCoverBehaviorAtSide(aSide).onCoverRightclickClient(aSide, this, aPlayer, aX, aY, aZ)) {
         return true;
      } else {
         if(this.isServerSide()) {
            if(!this.unbreakable() || aPlayer.field_71092_bJ.equalsIgnoreCase(this.getOwnerName())) {
               if(this.getColorization() >= 0 && GT_Utility.areStacksEqual(new ItemStack(Item.field_77786_ax, 1), aPlayer.field_71071_by.func_70448_g())) {
                  aPlayer.field_71071_by.func_70448_g().field_77993_c = Item.field_77788_aw.field_77779_bT;
                  this.setColorization((byte)(this.getColorization() >= 16?-2:-1));
                  return true;
               }

               byte e;
               if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sWrenchList)) {
                  e = GT_Utility.determineWrenchingSide(aSide, aX, aY, aZ);
                  if(this.isValidFacing(e) && e != this.getFrontFacing()) {
                     if(GT_ModHandler.damageOrDechargeItem(aPlayer.field_71071_by.func_70448_g(), 1, 1000, aPlayer)) {
                        this.setFrontFacing(e);
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                     }
                  } else if(this.mMetaTileEntity.isWrenchable() && GT_ModHandler.damageOrDechargeItem(aPlayer.field_71071_by.func_70448_g(), this.mMetaTileEntity.isSimpleMachine()?3:10, this.mMetaTileEntity.isSimpleMachine()?3000:10000, aPlayer)) {
                     this.field_70331_k.func_72838_d(new EntityItem(this.field_70331_k, (double)this.field_70329_l + 0.5D, (double)this.field_70330_m + 0.5D, (double)this.field_70327_n + 0.5D, this.getDrop()));
                     this.field_70331_k.func_94571_i(this.field_70329_l, this.field_70330_m, this.field_70327_n);
                     GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                  }

                  return true;
               }

               if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sScrewdriverList)) {
                  if(GT_ModHandler.damageOrDechargeItem(aPlayer.field_71071_by.func_70448_g(), 1, 200, aPlayer)) {
                     if(this.getCoverIDAtSide(aSide) == -2 && this.mMetaTileEntity.allowCoverOnSide(aSide, -1)) {
                        this.setCoverIDAtSide(aSide, -1);
                     } else if(this.getCoverIDAtSide(aSide) == -1 && this.mMetaTileEntity.allowCoverOnSide(aSide, -2)) {
                        this.setCoverIDAtSide(aSide, -2);
                     } else if(this.getCoverIDAtSide(aSide) == 0 && this.mMetaTileEntity.allowCoverOnSide(aSide, -1)) {
                        this.setCoverIDAtSide(aSide, -1);
                     } else {
                        this.setCoverDataAtSide(aSide, this.getCoverBehaviorAtSide(aSide).onCoverScrewdriverclick(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this, aPlayer, aX, aY, aZ));
                     }

                     this.mMetaTileEntity.onScrewdriverRightClick(aSide, aPlayer, aX, aY, aZ);
                     GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                  }

                  return true;
               }

               if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sHardHammerList)) {
                  if(GT_ModHandler.damageOrDechargeItem(aPlayer.field_71071_by.func_70448_g(), 1, 1000, aPlayer)) {
                     this.mInputDisabled = !this.mInputDisabled;
                     if(this.mInputDisabled) {
                        this.mOutputDisabled = !this.mOutputDisabled;
                     }

                     GT_Utility.sendChatToPlayer(aPlayer, "Auto-Input: " + (this.mInputDisabled?"Disabled":"Enabled") + "  Auto-Output: " + (this.mOutputDisabled?"Disabled":"Enabled"));
                     GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(1)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                  }

                  return true;
               }

               if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sSoftHammerList)) {
                  if(GT_ModHandler.damageOrDechargeItem(aPlayer.field_71071_by.func_70448_g(), 1, 1000, aPlayer)) {
                     this.mWorks = !this.mWorks;
                     GT_Utility.sendChatToPlayer(aPlayer, "Machine Processing: " + (this.isAllowedToWork()?"Enabled":"Disabled"));
                     GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(101)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                  }

                  return true;
               }

               if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sSolderingToolList)) {
                  e = GT_Utility.determineWrenchingSide(aSide, aX, aY, aZ);
                  if(GT_ModHandler.useSolderingIron(aPlayer.field_71071_by.func_70448_g(), aPlayer)) {
                     this.mStrongRedstone = (byte)(this.mStrongRedstone ^ 1 << e);
                     GT_Utility.sendChatToPlayer(aPlayer, "Redstone Output at Side " + e + " set to: " + ((this.mStrongRedstone & 1 << e) != 0?"Strong":"Weak"));
                     GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(103)), 3.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                  }

                  return true;
               }

               if(this.getCoverIDAtSide(aSide) == 0) {
                  if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sCovers.keySet())) {
                     if(GregTech_API.getCoverBehavior(aPlayer.field_71071_by.func_70448_g()).isCoverPlaceable(aSide, GT_Utility.stackToInt(aPlayer.field_71071_by.func_70448_g()), this) && this.mMetaTileEntity.allowCoverOnSide(aSide, GT_Utility.stackToInt(aPlayer.field_71071_by.func_70448_g()))) {
                        this.setCoverItemAtSide(aSide, aPlayer.field_71071_by.func_70448_g());
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }

                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                     }

                     return true;
                  }
               } else if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sCrowbarList)) {
                  if(GT_ModHandler.damageOrDechargeItem(aPlayer.field_71071_by.func_70448_g(), 1, 1000, aPlayer)) {
                     GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(0)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                     this.dropCover(aSide, aSide, false);
                  }

                  return true;
               }

               if(this.getCoverBehaviorAtSide(aSide).onCoverRightclick(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this, aPlayer, aX, aY, aZ)) {
                  return true;
               }

               if(!this.getCoverBehaviorAtSide(aSide).isGUIClickable(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this)) {
                  return false;
               }

               if(this.isUpgradable() && aPlayer.field_71071_by.func_70448_g() != null) {
                  if(GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(25, 1))) {
                     if(this.addMJConverterUpgrade()) {
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(80, 1))) {
                     if(this.addSteamEngineUpgrade()) {
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(89, 1))) {
                     if(this.addMufflerUpgrade()) {
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(88, 1))) {
                     if(this.isUpgradable() && !this.mLockUpgrade) {
                        this.mLockUpgrade = true;
                        this.setOwnerName(aPlayer.field_71092_bJ);
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(GT_Items.Upgrade_Overclocker.isStackEqual(aPlayer.field_71071_by.func_70448_g())) {
                     if(this.addOverclockerUpgrade()) {
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(this.getInputVoltage() < 512 && this.getOutputVoltage() * this.getOutputAmperage() < 512) {
                     if(GT_Items.Upgrade_Transformer.isStackEqual(aPlayer.field_71071_by.func_70448_g())) {
                        if(this.addTransformerUpgrade()) {
                           GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                           if(!aPlayer.field_71075_bZ.field_75098_d) {
                              --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                           }
                        }

                        return true;
                     }
                  } else if(GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(27, 1))) {
                     if(this.addTransformerUpgrade()) {
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(GT_Items.Upgrade_Battery.isStackEqual(aPlayer.field_71071_by.func_70448_g())) {
                     if(this.addBatteryUpgrade(10000, (byte)1)) {
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(26, 1))) {
                     if(this.addBatteryUpgrade(100000, (byte)1)) {
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(12, 1))) {
                     if(this.addBatteryUpgrade(1000000, (byte)3)) {
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(13, 1))) {
                     if(this.addBatteryUpgrade(10000000, (byte)4)) {
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(14, 1))) {
                     if(this.addBatteryUpgrade(100000000, (byte)5)) {
                        GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                        if(!aPlayer.field_71075_bZ.field_75098_d) {
                           --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                        }
                     }

                     return true;
                  }

                  if(this.hasMJConverterUpgrade() && GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(28, 1))) {
                     ++this.mRSEnergyCells;
                     this.mNeedsBatteryUpgrade = false;
                     GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                     if(!aPlayer.field_71075_bZ.field_75098_d) {
                        --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                     }

                     return true;
                  }

                  if(this.hasSteamEngineUpgrade() && GT_Utility.areStacksEqual(aPlayer.field_71071_by.func_70448_g(), GregTech_API.getGregTechComponent(81, 1))) {
                     ++this.mSteamTanks;
                     this.mNeedsBatteryUpgrade = false;
                     GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(3)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                     if(!aPlayer.field_71075_bZ.field_75098_d) {
                        --aPlayer.field_71071_by.func_70448_g().field_77994_a;
                     }

                     return true;
                  }
               }
            }

            try {
               if(this.hasValidMetaTileEntity() && this.mID > 15) {
                  return this.mMetaTileEntity.onRightclick(aPlayer, aSide, aX, aY, aZ);
               }
            } catch (Throwable var7) {
               GT_Log.err.println("Encountered Exception while rightclicking TileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
               var7.printStackTrace(GT_Log.err);
            }
         }

         return true;
      }
   }

   public void onLeftclick(EntityPlayer aPlayer) {
      try {
         if(aPlayer != null && this.hasValidMetaTileEntity() && this.mID > 15) {
            this.mMetaTileEntity.onLeftclick(aPlayer);
         }
      } catch (Throwable var3) {
         GT_Log.err.println("Encountered Exception while leftclicking TileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
         var3.printStackTrace(GT_Log.err);
      }

   }

   public boolean isDigitalChest() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.isDigitalChest():false;
   }

   public ItemStack[] getStoredItemData() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.getStoredItemData():null;
   }

   public void setItemCount(int aCount) {
      if(this.hasValidMetaTileEntity()) {
         this.mMetaTileEntity.setItemCount(aCount);
      }

   }

   public int getMaxItemCount() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.getMaxItemCount():0;
   }

   public boolean func_94041_b(int aIndex, ItemStack aStack) {
      return this.hasValidMetaTileEntity() && this.mMetaTileEntity.func_94041_b(aIndex, aStack);
   }

   public int[] func_94128_d(int aSide) {
      return this.hasValidMetaTileEntity() && (this.getCoverBehaviorAtSide((byte)aSide).letsItemsOut((byte)aSide, this.getCoverIDAtSide((byte)aSide), this.getCoverDataAtSide((byte)aSide), this) || this.getCoverBehaviorAtSide((byte)aSide).letsItemsIn((byte)aSide, this.getCoverIDAtSide((byte)aSide), this.getCoverDataAtSide((byte)aSide), this))?this.mMetaTileEntity.func_94128_d(aSide):new int[0];
   }

   public boolean func_102007_a(int aIndex, ItemStack aStack, int aSide) {
      return this.hasValidMetaTileEntity() && (this.mRunningThroughTick || !this.mInputDisabled) && this.getCoverBehaviorAtSide((byte)aSide).letsItemsIn((byte)aSide, this.getCoverIDAtSide((byte)aSide), this.getCoverDataAtSide((byte)aSide), this) && this.mMetaTileEntity.func_102007_a(aIndex, aStack, aSide);
   }

   public boolean func_102008_b(int aIndex, ItemStack aStack, int aSide) {
      return this.hasValidMetaTileEntity() && (this.mRunningThroughTick || !this.mOutputDisabled) && this.getCoverBehaviorAtSide((byte)aSide).letsItemsOut((byte)aSide, this.getCoverIDAtSide((byte)aSide), this.getCoverDataAtSide((byte)aSide), this) && this.mMetaTileEntity.func_102008_b(aIndex, aStack, aSide);
   }

   public boolean isUpgradable() {
      return this.hasValidMetaTileEntity() && this.getUpgradeCount() < 16;
   }

   public boolean isMJConverterUpgradable() {
      return this.isUpgradable() && this.mMetaTileEntity.isElectric() && !this.hasMJConverterUpgrade() && this.getMJCapacity() > 0;
   }

   public boolean isOverclockerUpgradable() {
      return this.isUpgradable() && this.mMetaTileEntity.isOverclockerUpgradable() && this.mOverclockers < 4;
   }

   public boolean isTransformerUpgradable() {
      return this.isUpgradable() && this.mMetaTileEntity.isElectric() && this.mMetaTileEntity.isTransformerUpgradable() && this.getInputVoltage() < 8192 && this.getOutputVoltage() * this.getOutputAmperage() < 8192;
   }

   public boolean isBatteryUpgradable(int aStorage, byte aTier) {
      return this.isUpgradable() && this.mMetaTileEntity.isElectric() && this.mMetaTileEntity.isBatteryUpgradable() && GT_Utility.getTier(this.getInputVoltage()) >= aTier && aStorage + this.getEUCapacity() > 0;
   }

   public boolean hasMJConverterUpgrade() {
      return this.hasValidMetaTileEntity() && this.mMetaTileEntity.isPneumatic()?true:this.mMJConverter;
   }

   public byte getOverclockerUpgradeCount() {
      return this.mOverclockers;
   }

   public byte getTransformerUpgradeCount() {
      return this.mTransformers;
   }

   public int getUpgradeStorageVolume() {
      return this.mUpgradedStorage;
   }

   public byte getInternalInputRedstoneSignal(byte aSide) {
      return (byte)(this.getCoverBehaviorAtSide(aSide).getRedstoneInput(aSide, this.getInputRedstoneSignal(aSide), this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this) & 15);
   }

   public byte getInputRedstoneSignal(byte aSide) {
      return (byte)(this.field_70331_k.func_72878_l(this.getOffsetX(aSide, 1), this.getOffsetY(aSide, 1), this.getOffsetZ(aSide, 1), aSide) & 15);
   }

   public byte getOutputRedstoneSignal(byte aSide) {
      return (byte)(!this.getCoverBehaviorAtSide(aSide).manipulatesSidedRedstoneOutput(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this) && (!this.mRedstone || !this.getCoverBehaviorAtSide(aSide).letsRedstoneGoOut(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this))?0:this.mSidedRedstone[aSide] & 15);
   }

   public void setInternalOutputRedstoneSignal(byte aSide, byte aStrength) {
      if(!this.getCoverBehaviorAtSide(aSide).manipulatesSidedRedstoneOutput(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this)) {
         this.setOutputRedstoneSignal(aSide, aStrength);
      }

   }

   public void setOutputRedstoneSignal(byte aSide, byte aStrength) {
      aStrength = (byte)Math.min(Math.max(0, aStrength), 15);
      if(aSide >= 0 && aSide < 6 && this.mSidedRedstone[aSide] != aStrength) {
         this.mSidedRedstone[aSide] = aStrength;
         this.issueBlockUpdate();
      }

   }

   public boolean isSteamEngineUpgradable() {
      return this.isUpgradable() && !this.hasSteamEngineUpgrade() && this.getSteamCapacity() > 0;
   }

   public boolean addSteamEngineUpgrade() {
      if(this.isSteamEngineUpgradable()) {
         this.issueBlockUpdate();
         this.mSteamConverter = true;
         return true;
      } else {
         return false;
      }
   }

   public boolean hasSteamEngineUpgrade() {
      return this.hasValidMetaTileEntity() && this.mMetaTileEntity.isSteampowered()?true:this.mSteamConverter;
   }

   public boolean hasMufflerUpgrade() {
      return this.mMuffler;
   }

   public boolean isMufflerUpgradable() {
      return this.isUpgradable() && !this.hasMufflerUpgrade();
   }

   public boolean addMufflerUpgrade() {
      return this.isMufflerUpgradable()?(this.mMuffler = true):false;
   }

   public boolean addMJConverterUpgrade() {
      return this.isMJConverterUpgradable()?(this.mMJConverter = true):false;
   }

   public boolean addOverclockerUpgrade() {
      if(this.isOverclockerUpgradable()) {
         ++this.mOverclockers;
         return true;
      } else {
         return false;
      }
   }

   public boolean addTransformerUpgrade() {
      if(this.isTransformerUpgradable()) {
         ++this.mTransformers;
         return true;
      } else {
         return false;
      }
   }

   public boolean addBatteryUpgrade(int aStorage, byte aTier) {
      this.mNeedsBatteryUpgrade = false;
      if(this.isBatteryUpgradable(aStorage, aTier)) {
         this.mUpgradedStorage += aStorage;
         ++this.mOtherUpgrades;
         return true;
      } else {
         return false;
      }
   }

   public boolean hasInventoryBeenModified() {
      return this.mInventoryChanged;
   }

   public void setGenericRedstoneOutput(boolean aOnOff) {
      this.mRedstone = aOnOff;
   }

   public int getErrorDisplayID() {
      return this.mDisplayErrorCode;
   }

   public void setErrorDisplayID(int aErrorID) {
      this.mDisplayErrorCode = aErrorID;
   }

   public IMetaTileEntity getMetaTileEntity() {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity:null;
   }

   public void setCoverIDAtSide(byte aSide, int aID) {
      if(aSide >= 0 && aSide < 6) {
         this.mCoverSides[aSide] = aID;
         this.mCoverData[aSide] = 0;
         this.issueCoverUpdate(aSide);
         this.issueBlockUpdate();
      }

   }

   public void setCoverItemAtSide(byte aSide, ItemStack aCover) {
      this.setCoverIDAtSide(aSide, GT_Utility.stackToInt(aCover));
   }

   public int getCoverIDAtSide(byte aSide) {
      return aSide >= 0 && aSide < 6?this.mCoverSides[aSide]:0;
   }

   public ItemStack getCoverItemAtSide(byte aSide) {
      return GT_Utility.intToStack(this.getCoverIDAtSide(aSide));
   }

   public GT_CoverBehavior getCoverBehaviorAtSide(byte aSide) {
      return GregTech_API.getCoverBehavior(this.getCoverIDAtSide(aSide));
   }

   public boolean canPlaceCoverIDAtSide(byte aSide, int aID) {
      return this.getCoverIDAtSide(aSide) == 0;
   }

   public boolean canPlaceCoverItemAtSide(byte aSide, ItemStack aCover) {
      return this.getCoverIDAtSide(aSide) == 0;
   }

   public void setCoverDataAtSide(byte aSide, int aData) {
      if(aSide >= 0 && aSide < 6) {
         this.mCoverData[aSide] = aData;
      }

   }

   public int getCoverDataAtSide(byte aSide) {
      return aSide >= 0 && aSide < 6?this.mCoverData[aSide]:0;
   }

   public void setLightValue(byte aLightValue) {
      this.mLightValue = (byte)(aLightValue & 15);
   }

   public byte getLightValue() {
      return this.mLightValue;
   }

   public void setOnFire() {
      for(byte i = 0; i < 6; ++i) {
         Block tBlock = this.getBlockAtSide(i);
         if(tBlock == null || null == tBlock.func_71872_e(this.field_70331_k, this.getOffsetX(i, 1), this.getOffsetY(i, 1), this.getOffsetZ(i, 1))) {
            this.field_70331_k.func_94575_c(this.getOffsetX(i, 1), this.getOffsetY(i, 1), this.getOffsetZ(i, 1), Block.field_72067_ar.field_71990_ca);
         }
      }

   }

   public int getAverageElectricInput() {
      int rEU = 0;
      int[] arr$ = this.mAverageEUInput;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         int tEU = arr$[i$];
         rEU += tEU;
      }

      return rEU / this.mAverageEUInput.length;
   }

   public int getAverageElectricOutput() {
      int rEU = 0;
      int[] arr$ = this.mAverageEUOutput;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         int tEU = arr$[i$];
         rEU += tEU;
      }

      return rEU / this.mAverageEUOutput.length;
   }

   public boolean dropCover(byte aSide, byte aDroppedSide, boolean aForced) {
      if(!this.getCoverBehaviorAtSide(aSide).onCoverRemoval(aSide, this.getCoverIDAtSide(aSide), this.mCoverData[aSide], this, aForced) && !aForced) {
         return false;
      } else {
         ItemStack tStack = GT_OreDictUnificator.get(true, this.getCoverItemAtSide(aSide));
         if(tStack != null) {
            EntityItem tEntity = new EntityItem(this.field_70331_k, (double)this.getOffsetX(aDroppedSide, 1) + 0.5D, (double)this.getOffsetY(aDroppedSide, 1) + 0.5D, (double)this.getOffsetZ(aDroppedSide, 1) + 0.5D, tStack);
            tEntity.field_70159_w = 0.0D;
            tEntity.field_70181_x = 0.0D;
            tEntity.field_70179_y = 0.0D;
            this.field_70331_k.func_72838_d(tEntity);
         }

         this.setCoverIDAtSide(aSide, 0);
         if(this.mMetaTileEntity.hasSidedRedstoneOutputBehavior()) {
            this.setOutputRedstoneSignal(aSide, (byte)0);
         } else {
            this.setOutputRedstoneSignal(aSide, (byte)15);
         }

         return true;
      }
   }

   public String getOwnerName() {
      return GT_Utility.isStringInvalid(this.mOwnerName)?"Player":this.mOwnerName;
   }

   public String setOwnerName(String aName) {
      return GT_Utility.isStringInvalid(aName)?(this.mOwnerName = "Player"):(this.mOwnerName = aName);
   }

   public byte getComparatorValue(byte aSide) {
      return this.hasValidMetaTileEntity()?this.mMetaTileEntity.getComparatorValue(aSide):0;
   }

   public byte getStrongOutputRedstoneSignal(byte aSide) {
      return aSide >= 0 && aSide < 6 && (this.mStrongRedstone & 1 << aSide) != 0?(byte)(this.mSidedRedstone[aSide] & 15):0;
   }

   public void setStrongOutputRedstoneSignal(byte aSide, byte aStrength) {
      this.mStrongRedstone = (byte)(this.mStrongRedstone | 1 << aSide);
      this.setOutputRedstoneSignal(aSide, aStrength);
   }

   public ItemStack func_70298_a(int aIndex, int aAmount) {
      if(this.hasValidMetaTileEntity()) {
         this.mInventoryChanged = true;
         return this.mMetaTileEntity.func_70298_a(aIndex, aAmount);
      } else {
         return null;
      }
   }

   public boolean injectEnergyUnits(byte aSide, int aVoltage, int aAmperage) {
      if(this.hasValidMetaTileEntity() && this.mMetaTileEntity.isElectric() && this.inputEnergyFrom(aSide)) {
         if(aVoltage > this.getInputVoltage()) {
            this.doExplosion(aVoltage);
            return true;
         } else if(this.increaseStoredEnergyUnits(aVoltage * aAmperage, true)) {
            this.mAverageEUInput[this.mAverageEUInputIndex] += aVoltage * aAmperage;
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean drainEnergyUnits(byte aSide, int aVoltage, int aAmperage) {
      if(this.hasValidMetaTileEntity() && this.mMetaTileEntity.isElectric() && this.outputsEnergyTo(aSide) && this.getStoredEU() - aVoltage * aAmperage >= this.mMetaTileEntity.getMinimumStoredEU()) {
         if(this.decreaseStoredEU(aVoltage * aAmperage, false)) {
            this.mAverageEUOutput[this.mAverageEUOutputIndex] += aVoltage * aAmperage;
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean acceptsRotationalEnergy(byte aSide) {
      return this.hasValidMetaTileEntity() && this.getCoverIDAtSide(aSide) == 0?this.mMetaTileEntity.acceptsRotationalEnergy(aSide):false;
   }

   public boolean injectRotationalEnergy(byte aSide, int aSpeed, int aEnergy) {
      return this.hasValidMetaTileEntity() && this.getCoverIDAtSide(aSide) == 0?this.mMetaTileEntity.injectRotationalEnergy(aSide, aSpeed, aEnergy):false;
   }

   public int fill(ForgeDirection aSide, FluidStack aFluid, boolean doFill) {
      return this.mTickTimer > 5L && this.hasValidMetaTileEntity() && (this.mRunningThroughTick || !this.mInputDisabled) && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidInput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidIn((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.fill(aSide, aFluid, doFill):0;
   }

   public FluidStack drain(ForgeDirection aSide, int maxDrain, boolean doDrain) {
      return this.mTickTimer > 5L && this.hasValidMetaTileEntity() && (this.mRunningThroughTick || !this.mOutputDisabled) && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidOutput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidOut((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.drain(aSide, maxDrain, doDrain):null;
   }

   public FluidStack drain(ForgeDirection aSide, FluidStack aFluid, boolean doDrain) {
      return this.mTickTimer > 5L && this.hasValidMetaTileEntity() && (this.mRunningThroughTick || !this.mOutputDisabled) && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidOutput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidOut((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.drain(aSide, aFluid, doDrain):null;
   }

   public boolean canFill(ForgeDirection aSide, Fluid aFluid) {
      return this.mTickTimer > 5L && this.hasValidMetaTileEntity() && (this.mRunningThroughTick || !this.mInputDisabled) && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidInput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidIn((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.canFill(aSide, aFluid):false;
   }

   public boolean canDrain(ForgeDirection aSide, Fluid aFluid) {
      return this.mTickTimer > 5L && this.hasValidMetaTileEntity() && (this.mRunningThroughTick || !this.mOutputDisabled) && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidOutput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidOut((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.canDrain(aSide, aFluid):false;
   }

   public FluidTankInfo[] getTankInfo(ForgeDirection aSide) {
      return this.hasValidMetaTileEntity() && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidInput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidIn((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this) || this.mMetaTileEntity.isLiquidOutput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidOut((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.getTankInfo(aSide):new FluidTankInfo[0];
   }

   public double getOutputEnergyUnitsPerTick() {
      return (double)this.getOutputVoltage();
   }

   public boolean isTeleporterCompatible(ForgeDirection aSide) {
      return this.hasValidMetaTileEntity() && this.mMetaTileEntity.isTeleporterCompatible();
   }

   public double demandedEnergyUnits() {
      return !this.mReleaseEnergy && this.hasValidMetaTileEntity() && this.mMetaTileEntity.isEnetInput()?(double)(this.getEUCapacity() - this.getStoredEU()):0.0D;
   }

   public double injectEnergyUnits(ForgeDirection aDirection, double aAmount) {
      return this.injectEnergyUnits((byte)aDirection.ordinal(), (int)aAmount, 1)?0.0D:aAmount;
   }

   public boolean acceptsEnergyFrom(TileEntity aEmitter, ForgeDirection aDirection) {
      return this.inputEnergyFrom((byte)aDirection.ordinal());
   }

   public boolean emitsEnergyTo(TileEntity aReceiver, ForgeDirection aDirection) {
      return this.outputsEnergyTo((byte)aDirection.ordinal());
   }

   public double getOfferedEnergy() {
      return this.hasValidMetaTileEntity() && this.getStoredEU() - this.mMetaTileEntity.getMinimumStoredEU() >= this.getOutputVoltage()?(double)Math.max(0, this.getOutputVoltage()):0.0D;
   }

   public void drawEnergy(double amount) {
      this.mAverageEUOutput[this.mAverageEUOutputIndex] = (int)((double)this.mAverageEUOutput[this.mAverageEUOutputIndex] + amount);
      this.decreaseStoredEU((int)amount, true);
   }

   public int addEnergy(int aEnergy) {
      if(!this.hasValidMetaTileEntity()) {
         return 0;
      } else {
         if(aEnergy > 0) {
            this.increaseStoredEnergyUnits(aEnergy, true);
         } else {
            this.decreaseStoredEU(-aEnergy, true);
         }

         return this.mMetaTileEntity.getEUVar();
      }
   }

   public boolean isAddedToEnergyNet() {
      return this.mIsAddedToEnet;
   }

   public void setStored(int aEU) {
      if(this.hasValidMetaTileEntity()) {
         this.setStoredEU(aEU);
      }

   }

   public int demandsEnergy() {
      return !this.mReleaseEnergy && this.hasValidMetaTileEntity() && this.mMetaTileEntity.isEnetInput()?this.getCapacity() - this.getStored():0;
   }

   public int getCapacity() {
      return this.getEUCapacity();
   }

   public int getStored() {
      return Math.min(this.getStoredEU(), this.getCapacity());
   }

   public int getMaxSafeInput() {
      return this.getInputVoltage();
   }

   public int getMaxEnergyOutput() {
      return this.mReleaseEnergy?Integer.MAX_VALUE:this.getOutput();
   }

   public int getOutput() {
      return this.getOutputVoltage();
   }

   public boolean isInvalidTileEntity() {
      return this.func_70320_p();
   }

   public boolean addStackToSlot(int aIndex, ItemStack aStack) {
      if(GT_Utility.isStackInvalid(aStack)) {
         return true;
      } else if(aIndex >= 0 && aIndex < this.func_70302_i_()) {
         ItemStack tStack = this.func_70301_a(aIndex);
         if(GT_Utility.isStackInvalid(tStack)) {
            this.func_70299_a(aIndex, aStack);
            return true;
         } else {
            aStack = GT_OreDictUnificator.get(aStack);
            if(GT_Utility.areStacksEqual(tStack, aStack) && tStack.field_77994_a + aStack.field_77994_a <= Math.min(aStack.func_77976_d(), this.func_70297_j_())) {
               tStack.field_77994_a += aStack.field_77994_a;
               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public boolean addStackToSlot(int aIndex, ItemStack aStack, int aAmount) {
      return this.addStackToSlot(aIndex, GT_Utility.copyAmount((long)aAmount, new Object[]{aStack}));
   }

   public void setMetaTileEntity(IMetaTileEntity aMetaTileEntity) {
      this.mMetaTileEntity = (MetaTileEntity)aMetaTileEntity;
   }

   public byte getColorization() {
      return (byte)(this.mColor - 1);
   }

   public byte setColorization(byte aColor) {
      return this.mColor = (byte)(aColor + 1);
   }

   public float getBlastResistance(byte aSide) {
      return this.hasValidMetaTileEntity()?Math.max(0.0F, this.getMetaTileEntity().getExplosionResistance(aSide)):10.0F;
   }

   public boolean isUniversalEnergyStored(int aEnergyAmount) {
      if(this.getUniversalEnergyStored() >= aEnergyAmount) {
         return true;
      } else {
         this.mHasEnoughEnergy = false;
         return false;
      }
   }

   public String[] getInfoData() {
      return this.hasValidMetaTileEntity()?this.getMetaTileEntity().getInfoData():new String[0];
   }

}
