package gregtechmod.api.metatileentity;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.BaseTileEntity;
import gregtechmod.api.metatileentity.MetaPipeEntity;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public class BaseMetaPipeEntity extends BaseTileEntity implements IGregTechTileEntity {

   public static volatile int VERSION = 408;
   public byte mConnections = 0;
   protected MetaPipeEntity mMetaTileEntity;
   private byte[] mSidedRedstone = new byte[]{(byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
   private int[] mCoverSides = new int[]{0, 0, 0, 0, 0, 0};
   private int[] mCoverData = new int[]{0, 0, 0, 0, 0, 0};
   private int[] mTimeStatistics;
   private boolean mInventoryChanged;
   private boolean mWorkUpdate;
   private boolean mWorks;
   private boolean mNeedsUpdate;
   private boolean mNeedsBlockUpdate;
   private boolean mSendClientData;
   private byte mColor;
   private byte oColor;
   private byte mStrongRedstone;
   private byte oRedstoneData;
   private byte oTextureData;
   private byte oUpdateData;
   private byte mLagWarningCount;
   private int oX;
   private int oY;
   private int oZ;
   private int mTimeStatisticsIndex;
   private short mID;
   private long mTickTimer;


   public BaseMetaPipeEntity() {
      this.mTimeStatistics = new int[GregTech_API.TICKS_FOR_LAG_AVERAGING];
      this.mInventoryChanged = false;
      this.mWorkUpdate = false;
      this.mWorks = true;
      this.mNeedsUpdate = true;
      this.mNeedsBlockUpdate = true;
      this.mSendClientData = false;
      this.mColor = 0;
      this.oColor = 0;
      this.mStrongRedstone = 0;
      this.oRedstoneData = 63;
      this.oTextureData = 0;
      this.oUpdateData = 0;
      this.mLagWarningCount = 0;
      this.oX = 0;
      this.oY = 0;
      this.oZ = 0;
      this.mTimeStatisticsIndex = 0;
      this.mID = 0;
      this.mTickTimer = 0L;
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
         aNBT.func_74783_a("mCoverData", this.mCoverData);
         aNBT.func_74783_a("mCoverSides", this.mCoverSides);
         aNBT.func_74773_a("mRedstoneSided", this.mSidedRedstone);
         aNBT.func_74774_a("mConnections", this.mConnections);
         aNBT.func_74774_a("mColor", this.mColor);
         aNBT.func_74774_a("mStrongRedstone", this.mStrongRedstone);
         aNBT.func_74757_a("mWorks", !this.mWorks);
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
      } else {
         if(aID <= 0) {
            this.mID = (short)aNBT.func_74762_e("mID");
         } else {
            this.mID = aID;
         }

         this.mCoverSides = aNBT.func_74759_k("mCoverSides");
         this.mCoverData = aNBT.func_74759_k("mCoverData");
         this.mSidedRedstone = aNBT.func_74770_j("mRedstoneSided");
         this.mConnections = aNBT.func_74771_c("mConnections");
         this.mColor = aNBT.func_74771_c("mColor");
         this.mStrongRedstone = aNBT.func_74771_c("mStrongRedstone");
         this.mWorks = !aNBT.func_74767_n("mWorks");
         if(this.mCoverData.length != 6) {
            this.mCoverData = new int[]{0, 0, 0, 0, 0, 0};
         }

         if(this.mCoverSides.length != 6) {
            this.mCoverSides = new int[]{0, 0, 0, 0, 0, 0};
         }

         if(this.mSidedRedstone.length != 6) {
            this.mSidedRedstone = new byte[]{(byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
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
         this.mSidedRedstone = new byte[]{(byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
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

   public void func_70316_g() {
      if(!this.hasValidMetaTileEntity()) {
         if(this.mMetaTileEntity == null) {
            return;
         }

         this.mMetaTileEntity.setBaseMetaTileEntity(this);
      }

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
               } catch (Throwable var9) {
                  GT_Log.err.println("Encountered Exception while ticking MetaTileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
                  var9.printStackTrace(GT_Log.err);
               }

               if(!this.hasValidMetaTileEntity()) {
                  return;
               }
            }

            if(this.isClientSide()) {
               if(this.mColor != this.oColor) {
                  this.oColor = this.mColor;
                  this.issueTextureUpdate();
               }

               if(this.mNeedsUpdate) {
                  this.field_70331_k.func_72902_n(this.field_70329_l, this.field_70330_m, this.field_70327_n);
                  this.mNeedsUpdate = false;
               }
            }

            if(this.isServerSide()) {
               for(e = 0; e < 6; ++e) {
                  short tCoverTickRate = this.getCoverBehaviorAtSide(e).getTickRate(e, this.getCoverIDAtSide(e), this.mCoverData[e], this);
                  if(tCoverTickRate > 0 && this.mTickTimer % (long)tCoverTickRate == 0L) {
                     try {
                        this.mCoverData[e] = this.getCoverBehaviorAtSide(e).doCoverThings(e, this.getInputRedstoneSignal(e), this.getCoverIDAtSide(e), this.mCoverData[e], this);
                     } catch (Throwable var8) {
                        GT_Log.err.println("Encountered Exception while ticking Cover, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
                        var8.printStackTrace(GT_Log.err);
                     }
                  }
               }

               this.mConnections = (byte)(this.mMetaTileEntity.mConnections | this.mConnections & -64);
               if((this.mConnections & -64) == 64 && this.getRandomNumber(1000) == 0) {
                  this.mConnections = (byte)(this.mConnections & -65 | -128);
               }
            }

            try {
               this.mMetaTileEntity.onPreTick();
            } catch (Throwable var7) {
               GT_Log.err.println("Encountered Exception while ticking MetaTileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
               var7.printStackTrace(GT_Log.err);
            }

            if(!this.hasValidMetaTileEntity()) {
               return;
            }

            if(this.isServerSide()) {
               if(this.mTickTimer == 10L) {
                  this.issueBlockUpdate();
               }

               if(this.field_70329_l != this.oX || this.field_70330_m != this.oY || this.field_70327_n != this.oZ) {
                  this.oX = this.field_70329_l;
                  this.oY = this.field_70330_m;
                  this.oZ = this.field_70327_n;
                  this.issueClientUpdate();
               }
            }

            try {
               this.mMetaTileEntity.onPostTick();
            } catch (Throwable var6) {
               GT_Log.err.println("Encountered Exception while ticking MetaTileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
               var6.printStackTrace(GT_Log.err);
            }

            if(!this.hasValidMetaTileEntity()) {
               return;
            }

            if(this.isServerSide()) {
               if(this.mTickTimer % 10L == 0L && this.mSendClientData) {
                  GT_Utility.sendPacketToAllPlayersInRange(this.field_70331_k, this.func_70319_e(), this.field_70329_l, this.field_70327_n);
                  this.mSendClientData = false;
               }

               if(this.mTickTimer > 10L) {
                  e = this.mConnections;
                  if(e != this.oTextureData) {
                     this.sendBlockEvent((byte)0, this.oTextureData = e);
                  }

                  e = this.mMetaTileEntity.getUpdateData();
                  if(e != this.oUpdateData) {
                     this.sendBlockEvent((byte)1, this.oUpdateData = e);
                  }

                  e = this.mColor;
                  if(e != this.oColor) {
                     this.sendBlockEvent((byte)2, this.oColor = e);
                  }

                  e = (byte)((this.mSidedRedstone[0] > 0?1:0) | (this.mSidedRedstone[1] > 0?2:0) | (this.mSidedRedstone[2] > 0?4:0) | (this.mSidedRedstone[3] > 0?8:0) | (this.mSidedRedstone[4] > 0?16:0) | (this.mSidedRedstone[5] > 0?32:0));
                  if(e != this.oRedstoneData) {
                     this.sendBlockEvent((byte)3, this.oRedstoneData = e);
                  }
               }

               if(this.mNeedsBlockUpdate) {
                  this.field_70331_k.func_72898_h(this.field_70329_l, this.field_70330_m, this.field_70327_n, this.getBlockIDOffset(0, 0, 0));
                  this.mNeedsBlockUpdate = false;
               }
            }
         }
      } catch (Throwable var10) {
         GT_Log.err.println("Encountered Exception while ticking TileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
         var10.printStackTrace(GT_Log.err);
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

      this.mWorkUpdate = this.mInventoryChanged = false;
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
      tOut.writeByte(this.oTextureData = this.mConnections);
      tOut.writeByte(this.oUpdateData = this.hasValidMetaTileEntity()?this.mMetaTileEntity.getUpdateData():0);
      tOut.writeByte(this.oRedstoneData = (byte)((this.mSidedRedstone[0] > 0?1:0) | (this.mSidedRedstone[1] > 0?2:0) | (this.mSidedRedstone[2] > 0?4:0) | (this.mSidedRedstone[3] > 0?8:0) | (this.mSidedRedstone[4] > 0?16:0) | (this.mSidedRedstone[5] > 0?32:0)));
      tOut.writeByte(this.oColor = this.mColor);
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
            this.mConnections = (byte)aValue;
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
            tList.add("This TileEntity has also caused " + (this.mLagWarningCount >= 10?"more than 10":Byte.valueOf(this.mLagWarningCount)) + " Lag Spike Warnings (anything taking longer than " + GregTech_API.MILLISECOND_THRESHOLD_UNTIL_LAG_WARNING + "ms) on the Server.");
         }

         tList.add("Is" + (this.mMetaTileEntity.isAccessAllowed(aPlayer)?" ":" not ") + "accessible for you");
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
      return (byte)6;
   }

   public void setFrontFacing(byte aFacing) {}

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

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_70300_a(EntityPlayer aPlayer) {
      return this.hasValidMetaTileEntity() && this.mTickTimer > 40L && this.getTileEntityOffset(0, 0, 0) == this && aPlayer.func_70092_e((double)this.field_70329_l + 0.5D, (double)this.field_70330_m + 0.5D, (double)this.field_70327_n + 0.5D) < 64.0D && this.mMetaTileEntity.isAccessAllowed(aPlayer);
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

      super.func_70313_j();
   }

   public void onChunkUnload() {
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

   public void setWorkDataValue(byte aValue) {}

   public byte getWorkDataValue() {
      return (byte)0;
   }

   public int getMetaTileID() {
      return this.mID;
   }

   public int setMetaTileID(short aID) {
      return this.mID = aID;
   }

   public boolean isActive() {
      return false;
   }

   public void setActive(boolean aActive) {}

   public long getTimer() {
      return this.mTickTimer;
   }

   public boolean decreaseStoredEnergyUnits(int aEnergy, boolean aIgnoreTooLessEnergy) {
      return false;
   }

   public boolean increaseStoredEnergyUnits(int aEnergy, boolean aIgnoreTooMuchEnergy) {
      return false;
   }

   public boolean inputEnergyFrom(byte aSide) {
      return false;
   }

   public boolean outputsEnergyTo(byte aSide) {
      return false;
   }

   public int getOutputAmperage() {
      return 0;
   }

   public int getOutputVoltage() {
      return 0;
   }

   public int getInputVoltage() {
      return 0;
   }

   public boolean increaseStoredMJ(int aEnergy, boolean aIgnoreTooMuchEnergy) {
      return false;
   }

   public boolean increaseStoredSteam(int aEnergy, boolean aIgnoreTooMuchEnergy) {
      return false;
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
      return 0;
   }

   public int getMJCapacity() {
      return 0;
   }

   public int getStoredEU() {
      return 0;
   }

   public int getEUCapacity() {
      return 0;
   }

   public int getStoredSteam() {
      return 0;
   }

   public int getSteamCapacity() {
      return 0;
   }

   public int getTextureIndex(byte aSide, byte aMeta) {
      return this.getUncoveredIndex(aSide, aMeta);
   }

   public Icon getTextureIcon(byte aSide, byte aMeta) {
      Icon rIcon = this.getCoverTexture(aSide);
      return rIcon != null?rIcon:this.getUncoveredIcon(aSide, aMeta);
   }

   public Icon getUncoveredIcon(byte aSide, byte aMeta) {
      if((this.mConnections & -64) != 0) {
         return null;
      } else {
         byte tConnections = this.mConnections;
         if(tConnections != 1 && tConnections != 2) {
            if(tConnections != 4 && tConnections != 8) {
               if(tConnections == 16 || tConnections == 32) {
                  tConnections = 48;
               }
            } else {
               tConnections = 12;
            }
         } else {
            tConnections = 3;
         }

         return !this.hasValidMetaTileEntity()?null:this.mMetaTileEntity.getTextureIcon(aSide, tConnections, tConnections == 0 || (tConnections & 1 << aSide) != 0, this.getOutputRedstoneSignal(aSide) > 0);
      }
   }

   public int getUncoveredIndex(byte aSide, byte aMeta) {
      if((this.mConnections & 64) != 0) {
         return 368;
      } else if((this.mConnections & -128) != 0) {
         return 369;
      } else {
         byte tConnections = this.mConnections;
         if(tConnections != 1 && tConnections != 2) {
            if(tConnections != 4 && tConnections != 8) {
               if(tConnections == 16 || tConnections == 32) {
                  tConnections = 48;
               }
            } else {
               tConnections = 12;
            }
         } else {
            tConnections = 3;
         }

         return !this.hasValidMetaTileEntity()?-2:this.mMetaTileEntity.getTextureIndex(aSide, tConnections, tConnections == 0 || (tConnections & 1 << aSide) != 0, this.getOutputRedstoneSignal(aSide) > 0);
      }
   }

   protected boolean hasValidMetaTileEntity() {
      return this.mMetaTileEntity != null && this.mMetaTileEntity.getBaseMetaTileEntity() == this;
   }

   public void doExplosion(int aAmount) {
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

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      if(this.isClientSide() && this.getCoverBehaviorAtSide(aSide).onCoverRightclickClient(aSide, this, aPlayer, aX, aY, aZ)) {
         return true;
      } else {
         if(this.isServerSide()) {
            byte tSide = GT_Utility.determineWrenchingSide(aSide, aX, aY, aZ);
            if(this.getColorization() >= 0 && GT_Utility.areStacksEqual(new ItemStack(Item.field_77786_ax, 1), aPlayer.field_71071_by.func_70448_g())) {
               aPlayer.field_71071_by.func_70448_g().field_77993_c = Item.field_77788_aw.field_77779_bT;
               this.setColorization((byte)(this.getColorization() >= 16?-2:-1));
               return true;
            }

            if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sWrenchList)) {
               if(this.isValidFacing(tSide) && tSide != this.getFrontFacing()) {
                  if(GT_ModHandler.damageOrDechargeItem(aPlayer.field_71071_by.func_70448_g(), 1, 1000, aPlayer)) {
                     this.setFrontFacing(tSide);
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
               if(this.getCoverIDAtSide(aSide) == 0 && this.getCoverIDAtSide(tSide) != 0) {
                  if(GT_ModHandler.damageOrDechargeItem(aPlayer.field_71071_by.func_70448_g(), 1, 200, aPlayer)) {
                     this.setCoverDataAtSide(tSide, this.getCoverBehaviorAtSide(tSide).onCoverScrewdriverclick(tSide, this.getCoverIDAtSide(tSide), this.getCoverDataAtSide(tSide), this, aPlayer, 0.5F, 0.5F, 0.5F));
                     this.mMetaTileEntity.onScrewdriverRightClick(tSide, aPlayer, aX, aY, aZ);
                     GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
                  }
               } else if(GT_ModHandler.damageOrDechargeItem(aPlayer.field_71071_by.func_70448_g(), 1, 1000, aPlayer)) {
                  this.setCoverDataAtSide(aSide, this.getCoverBehaviorAtSide(aSide).onCoverScrewdriverclick(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this, aPlayer, aX, aY, aZ));
                  this.mMetaTileEntity.onScrewdriverRightClick(aSide, aPlayer, aX, aY, aZ);
                  GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(100)), 1.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
               }

               return true;
            }

            if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sHardHammerList)) {
               return true;
            }

            if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sSoftHammerList)) {
               return true;
            }

            if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sSolderingToolList)) {
               if(GT_ModHandler.useSolderingIron(aPlayer.field_71071_by.func_70448_g(), aPlayer)) {
                  this.mStrongRedstone = (byte)(this.mStrongRedstone ^ 1 << tSide);
                  GT_Utility.sendChatToPlayer(aPlayer, "Redstone Output at Side " + tSide + " set to: " + ((this.mStrongRedstone & 1 << tSide) != 0?"Strong":"Weak"));
                  GT_Utility.sendSoundToPlayers(this.field_70331_k, (String)GregTech_API.sSoundList.get(Integer.valueOf(103)), 3.0F, -1.0F, this.field_70329_l, this.field_70330_m, this.field_70327_n);
               }

               return true;
            }

            byte cSide = tSide;
            if(this.getCoverIDAtSide(aSide) != 0) {
               cSide = aSide;
            }

            if(this.getCoverIDAtSide(cSide) == 0) {
               if(GT_Utility.isItemStackInList(aPlayer.field_71071_by.func_70448_g(), GregTech_API.sCovers.keySet())) {
                  if(GregTech_API.getCoverBehavior(aPlayer.field_71071_by.func_70448_g()).isCoverPlaceable(cSide, GT_Utility.stackToInt(aPlayer.field_71071_by.func_70448_g()), this) && this.mMetaTileEntity.allowCoverOnSide(cSide, GT_Utility.stackToInt(aPlayer.field_71071_by.func_70448_g()))) {
                     this.setCoverItemAtSide(cSide, aPlayer.field_71071_by.func_70448_g());
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
                  this.dropCover(cSide, aSide, false);
               }

               return true;
            }

            if(this.getCoverBehaviorAtSide(aSide).onCoverRightclick(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this, aPlayer, aX, aY, aZ)) {
               return true;
            }

            if(!this.getCoverBehaviorAtSide(aSide).isGUIClickable(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this)) {
               return false;
            }

            try {
               if(this.hasValidMetaTileEntity() && this.mID > 15) {
                  return this.mMetaTileEntity.onRightclick(aPlayer, aSide, aX, aY, aZ);
               }
            } catch (Throwable var9) {
               GT_Log.err.println("Encountered Exception while rightclicking TileEntity, the Game should\'ve crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
               var9.printStackTrace(GT_Log.err);
            }
         }

         return false;
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
      return false;
   }

   public ItemStack[] getStoredItemData() {
      return null;
   }

   public void setItemCount(int aCount) {}

   public int getMaxItemCount() {
      return 0;
   }

   public boolean func_94041_b(int aIndex, ItemStack aStack) {
      return this.hasValidMetaTileEntity() && this.mMetaTileEntity.func_94041_b(aIndex, aStack);
   }

   public int[] func_94128_d(int aSide) {
      return this.hasValidMetaTileEntity() && (this.getCoverBehaviorAtSide((byte)aSide).letsItemsOut((byte)aSide, this.getCoverIDAtSide((byte)aSide), this.getCoverDataAtSide((byte)aSide), this) || this.getCoverBehaviorAtSide((byte)aSide).letsItemsIn((byte)aSide, this.getCoverIDAtSide((byte)aSide), this.getCoverDataAtSide((byte)aSide), this))?this.mMetaTileEntity.func_94128_d(aSide):new int[0];
   }

   public boolean func_102007_a(int aIndex, ItemStack aStack, int aSide) {
      return this.hasValidMetaTileEntity() && this.getCoverBehaviorAtSide((byte)aSide).letsItemsIn((byte)aSide, this.getCoverIDAtSide((byte)aSide), this.getCoverDataAtSide((byte)aSide), this) && this.mMetaTileEntity.func_102007_a(aIndex, aStack, aSide);
   }

   public boolean func_102008_b(int aIndex, ItemStack aStack, int aSide) {
      return this.hasValidMetaTileEntity() && this.getCoverBehaviorAtSide((byte)aSide).letsItemsOut((byte)aSide, this.getCoverIDAtSide((byte)aSide), this.getCoverDataAtSide((byte)aSide), this) && this.mMetaTileEntity.func_102008_b(aIndex, aStack, aSide);
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

   public boolean isBatteryUpgradable(int aStorage, byte aTier) {
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

   public byte getInternalInputRedstoneSignal(byte aSide) {
      return (byte)(this.getCoverBehaviorAtSide(aSide).getRedstoneInput(aSide, this.getInputRedstoneSignal(aSide), this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this) & 15);
   }

   public byte getInputRedstoneSignal(byte aSide) {
      return (byte)(this.field_70331_k.func_72878_l(this.getOffsetX(aSide, 1), this.getOffsetY(aSide, 1), this.getOffsetZ(aSide, 1), aSide) & 15);
   }

   public byte getOutputRedstoneSignal(byte aSide) {
      return (byte)(!this.getCoverBehaviorAtSide(aSide).manipulatesSidedRedstoneOutput(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this) && !this.getCoverBehaviorAtSide(aSide).letsRedstoneGoOut(aSide, this.getCoverIDAtSide(aSide), this.getCoverDataAtSide(aSide), this)?0:this.mSidedRedstone[aSide] & 15);
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
         return true;
      } else {
         return false;
      }
   }

   public boolean hasSteamEngineUpgrade() {
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

   public boolean hasInventoryBeenModified() {
      return this.mInventoryChanged;
   }

   public void setGenericRedstoneOutput(boolean aOnOff) {}

   public int getErrorDisplayID() {
      return 0;
   }

   public void setErrorDisplayID(int aErrorID) {}

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

   public void setLightValue(byte aLightValue) {}

   public void setOnFire() {
      for(byte i = 0; i < 6; ++i) {
         Block tBlock = this.getBlockAtSide(i);
         if(tBlock == null || null == tBlock.func_71872_e(this.field_70331_k, this.getOffsetX(i, 1), this.getOffsetY(i, 1), this.getOffsetZ(i, 1))) {
            this.field_70331_k.func_94575_c(this.getOffsetX(i, 1), this.getOffsetY(i, 1), this.getOffsetZ(i, 1), Block.field_72067_ar.field_71990_ca);
         }
      }

   }

   public int getAverageElectricInput() {
      return 0;
   }

   public int getAverageElectricOutput() {
      return 0;
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
         this.setOutputRedstoneSignal(aSide, (byte)0);
         return true;
      }
   }

   public String getOwnerName() {
      return "Player";
   }

   public String setOwnerName(String aName) {
      return "Player";
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
      return false;
   }

   public boolean drainEnergyUnits(byte aSide, int aVoltage, int aAmperage) {
      return false;
   }

   public boolean acceptsRotationalEnergy(byte aSide) {
      return false;
   }

   public boolean injectRotationalEnergy(byte aSide, int aSpeed, int aEnergy) {
      return false;
   }

   public int fill(ForgeDirection aSide, FluidStack aFluid, boolean doFill) {
      return this.mTickTimer > 5L && this.hasValidMetaTileEntity() && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidInput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidIn((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.fill(aSide, aFluid, doFill):0;
   }

   public FluidStack drain(ForgeDirection aSide, int maxDrain, boolean doDrain) {
      return this.mTickTimer > 5L && this.hasValidMetaTileEntity() && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidOutput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidOut((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.drain(aSide, maxDrain, doDrain):null;
   }

   public FluidStack drain(ForgeDirection aSide, FluidStack aFluid, boolean doDrain) {
      return this.mTickTimer > 5L && this.hasValidMetaTileEntity() && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidOutput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidOut((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.drain(aSide, aFluid, doDrain):null;
   }

   public boolean canFill(ForgeDirection aSide, Fluid aFluid) {
      return this.mTickTimer > 5L && this.hasValidMetaTileEntity() && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidInput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidIn((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.canFill(aSide, aFluid):false;
   }

   public boolean canDrain(ForgeDirection aSide, Fluid aFluid) {
      return this.mTickTimer > 5L && this.hasValidMetaTileEntity() && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidOutput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidOut((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.canDrain(aSide, aFluid):false;
   }

   public FluidTankInfo[] getTankInfo(ForgeDirection aSide) {
      return this.hasValidMetaTileEntity() && (aSide == ForgeDirection.UNKNOWN || this.mMetaTileEntity.isLiquidInput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidIn((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this) || this.mMetaTileEntity.isLiquidOutput((byte)aSide.ordinal()) && this.getCoverBehaviorAtSide((byte)aSide.ordinal()).letsLiquidOut((byte)aSide.ordinal(), this.getCoverIDAtSide((byte)aSide.ordinal()), this.getCoverDataAtSide((byte)aSide.ordinal()), this))?this.mMetaTileEntity.getTankInfo(aSide):new FluidTankInfo[0];
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
      this.mMetaTileEntity = (MetaPipeEntity)aMetaTileEntity;
   }

   public byte getColorization() {
      return (byte)(this.mColor - 1);
   }

   public byte setColorization(byte aColor) {
      return this.mColor = (byte)(aColor + 1);
   }

   public float getThickNess() {
      return !this.hasValidMetaTileEntity()?0.0F:this.mMetaTileEntity.getThickNess();
   }

   public boolean renderInside() {
      return !this.hasValidMetaTileEntity()?false:this.mMetaTileEntity.renderInside();
   }

   public float getBlastResistance(byte aSide) {
      return (this.mConnections & 192) != 0?50.0F:5.0F;
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
      return this.hasValidMetaTileEntity()?this.getMetaTileEntity().getInfoData():new String[0];
   }

}
