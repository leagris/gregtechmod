package gregtechmod.api.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_Container;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class GT_ContainerMetaTile_Machine extends GT_Container {

   public int mActive = 0;
   public int mMaxProgressTime = 0;
   public int mProgressTime = 0;
   public int mMJ = 0;
   public int mEnergy = 0;
   public int mMJStorage = 0;
   public int mSteam = 0;
   public int mSteamStorage = 0;
   public int mStorage = 0;
   public int mOutput = 0;
   public int mInput = 0;
   public int mID = 0;
   public int mDisplayErrorCode = 0;
   private int oActive = 0;
   private int oMaxProgressTime = 0;
   private int oProgressTime = 0;
   private int oMJ = 0;
   private int oEnergy = 0;
   private int oMJStorage = 0;
   private int oSteam = 0;
   private int oSteamStorage = 0;
   private int oStorage = 0;
   private int oOutput = 0;
   private int oInput = 0;
   private int oID = 0;
   private int oDisplayErrorCode = 0;
   private int mTimer = 0;


   public GT_ContainerMetaTile_Machine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
      this.mTileEntity = aTileEntity;
      if(this.mTileEntity != null && this.mTileEntity.getMetaTileEntity() != null) {
         this.addSlots(aInventoryPlayer);
         if(this.doesBindPlayerInventory()) {
            this.bindPlayerInventory(aInventoryPlayer);
         }

         this.func_75142_b();
      } else {
         aInventoryPlayer.field_70458_d.func_71053_j();
      }

   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mStorage = this.mTileEntity.getEUCapacity();
         this.mEnergy = this.mTileEntity.getStoredEU();
         this.mMJStorage = this.mTileEntity.getMJCapacity();
         this.mMJ = this.mTileEntity.getStoredMJ();
         this.mSteamStorage = this.mTileEntity.getSteamCapacity();
         this.mSteam = this.mTileEntity.getStoredSteam();
         this.mOutput = this.mTileEntity.getOutputVoltage();
         this.mInput = this.mTileEntity.getInputVoltage();
         this.mDisplayErrorCode = this.mTileEntity.getErrorDisplayID();
         this.mProgressTime = this.mTileEntity.getProgress();
         this.mMaxProgressTime = this.mTileEntity.getMaxProgress();
         this.mActive = this.mTileEntity.isActive()?1:0;
         ++this.mTimer;
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            if(this.mTimer % 500 == 10 || this.oEnergy != this.mEnergy) {
               var1.func_71112_a(this, 0, this.mEnergy & '\uffff');
               var1.func_71112_a(this, 1, this.mEnergy >>> 16);
            }

            if(this.mTimer % 500 == 10 || this.oStorage != this.mStorage) {
               var1.func_71112_a(this, 2, this.mStorage & '\uffff');
               var1.func_71112_a(this, 3, this.mStorage >>> 16);
            }

            if(this.mTimer % 500 == 10 || this.oOutput != this.mOutput) {
               var1.func_71112_a(this, 4, this.mOutput);
            }

            if(this.mTimer % 500 == 10 || this.oInput != this.mInput) {
               var1.func_71112_a(this, 5, this.mInput);
            }

            if(this.mTimer % 500 == 10 || this.oDisplayErrorCode != this.mDisplayErrorCode) {
               var1.func_71112_a(this, 6, this.mDisplayErrorCode);
            }

            if(this.mTimer % 500 == 10 || this.oMJ != this.mMJ) {
               var1.func_71112_a(this, 7, this.mMJ & '\uffff');
               var1.func_71112_a(this, 8, this.mMJ >>> 16);
            }

            if(this.mTimer % 500 == 10 || this.oMJStorage != this.mMJStorage) {
               var1.func_71112_a(this, 9, this.mMJStorage & '\uffff');
               var1.func_71112_a(this, 10, this.mMJStorage >>> 16);
            }

            if(this.mTimer % 500 == 10 || this.oProgressTime != this.mProgressTime) {
               var1.func_71112_a(this, 11, this.mProgressTime & '\uffff');
               var1.func_71112_a(this, 12, this.mProgressTime >>> 16);
            }

            if(this.mTimer % 500 == 10 || this.oMaxProgressTime != this.mMaxProgressTime) {
               var1.func_71112_a(this, 13, this.mMaxProgressTime & '\uffff');
               var1.func_71112_a(this, 14, this.mMaxProgressTime >>> 16);
            }

            if(this.mTimer % 500 == 10 || this.oID != this.mID) {
               var1.func_71112_a(this, 15, this.mID);
            }

            if(this.mTimer % 500 == 10 || this.oActive != this.mActive) {
               var1.func_71112_a(this, 16, this.mActive);
            }

            if(this.mTimer % 500 == 10 || this.oSteam != this.mSteam) {
               var1.func_71112_a(this, 17, this.mSteam & '\uffff');
               var1.func_71112_a(this, 18, this.mSteam >>> 16);
            }

            if(this.mTimer % 500 == 10 || this.oSteamStorage != this.mSteamStorage) {
               var1.func_71112_a(this, 19, this.mSteamStorage & '\uffff');
               var1.func_71112_a(this, 20, this.mSteamStorage >>> 16);
            }
         }

         this.oID = this.mID;
         this.oMJ = this.mMJ;
         this.oSteam = this.mSteam;
         this.oInput = this.mInput;
         this.oActive = this.mActive;
         this.oOutput = this.mOutput;
         this.oEnergy = this.mEnergy;
         this.oStorage = this.mStorage;
         this.oMJStorage = this.mMJStorage;
         this.oSteamStorage = this.mSteamStorage;
         this.oProgressTime = this.mProgressTime;
         this.oMaxProgressTime = this.mMaxProgressTime;
         this.oDisplayErrorCode = this.mDisplayErrorCode;
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 0:
         this.mEnergy = this.mEnergy & -65536 | par2;
         break;
      case 1:
         this.mEnergy = this.mEnergy & '\uffff' | par2 << 16;
         break;
      case 2:
         this.mStorage = this.mStorage & -65536 | par2;
         break;
      case 3:
         this.mStorage = this.mStorage & '\uffff' | par2 << 16;
         break;
      case 4:
         this.mOutput = par2;
         break;
      case 5:
         this.mInput = par2;
         break;
      case 6:
         this.mDisplayErrorCode = par2;
         break;
      case 7:
         this.mMJ = this.mMJ & -65536 | par2;
         break;
      case 8:
         this.mMJ = this.mMJ & '\uffff' | par2 << 16;
         break;
      case 9:
         this.mMJStorage = this.mMJStorage & -65536 | par2;
         break;
      case 10:
         this.mMJStorage = this.mMJStorage & '\uffff' | par2 << 16;
         break;
      case 11:
         this.mProgressTime = this.mProgressTime & -65536 | par2;
         break;
      case 12:
         this.mProgressTime = this.mProgressTime & '\uffff' | par2 << 16;
         break;
      case 13:
         this.mMaxProgressTime = this.mMaxProgressTime & -65536 | par2;
         break;
      case 14:
         this.mMaxProgressTime = this.mMaxProgressTime & '\uffff' | par2 << 16;
         break;
      case 15:
         this.mID = par2;
         break;
      case 16:
         this.mActive = par2;
         break;
      case 17:
         this.mSteam = this.mSteam & -65536 | par2;
         break;
      case 18:
         this.mSteam = this.mSteam & '\uffff' | par2 << 16;
         break;
      case 19:
         this.mSteamStorage = this.mSteamStorage & -65536 | par2;
         break;
      case 20:
         this.mSteamStorage = this.mSteamStorage & '\uffff' | par2 << 16;
      }

   }

   public boolean func_75145_c(EntityPlayer player) {
      return this.mTileEntity.func_70300_a(player);
   }
}
