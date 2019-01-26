package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.machines.steam.GT_MetaTileEntity_Boiler_Steel;
import java.util.Iterator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class GT_Container_SteelBoiler extends GT_ContainerMetaTile_Machine {

   public int mTemperature = 2;
   public int mProcessingEnergy = 0;
   public int mSteamAmount = 0;
   public int mWaterAmount = 0;


   public GT_Container_SteelBoiler(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
   }

   public void addSlots(InventoryPlayer aInventoryPlayer) {
      this.func_75146_a(new Slot(this.mTileEntity, 2, 116, 62));
      this.func_75146_a(new Slot(this.mTileEntity, 0, 44, 26));
      this.func_75146_a(new Slot(this.mTileEntity, 1, 44, 62));
      this.func_75146_a(new Slot(this.mTileEntity, 3, 116, 26));
   }

   public int getSlotCount() {
      return 4;
   }

   public int getShiftClickSlotCount() {
      return 1;
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
         this.mTemperature = ((GT_MetaTileEntity_Boiler_Steel)this.mTileEntity.getMetaTileEntity()).mTemperature;
         this.mProcessingEnergy = ((GT_MetaTileEntity_Boiler_Steel)this.mTileEntity.getMetaTileEntity()).mProcessingEnergy;
         this.mSteamAmount = ((GT_MetaTileEntity_Boiler_Steel)this.mTileEntity.getMetaTileEntity()).mSteam == null?0:((GT_MetaTileEntity_Boiler_Steel)this.mTileEntity.getMetaTileEntity()).mSteam.amount;
         this.mWaterAmount = ((GT_MetaTileEntity_Boiler_Steel)this.mTileEntity.getMetaTileEntity()).mFluid == null?0:((GT_MetaTileEntity_Boiler_Steel)this.mTileEntity.getMetaTileEntity()).mFluid.amount;
         this.mTemperature = Math.min(54, Math.max(0, this.mTemperature * 54 / 990));
         this.mSteamAmount = Math.min(54, Math.max(0, this.mSteamAmount * 54 / 31900));
         this.mWaterAmount = Math.min(54, Math.max(0, this.mWaterAmount * 54 / 15900));
         this.mProcessingEnergy = Math.min(14, Math.max(this.mProcessingEnergy > 0?1:0, this.mProcessingEnergy * 14 / 640));
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 100, this.mTemperature);
            var1.func_71112_a(this, 101, this.mProcessingEnergy);
            var1.func_71112_a(this, 102, this.mSteamAmount);
            var1.func_71112_a(this, 103, this.mWaterAmount);
         }

      }
   }

   @SideOnly(Side.CLIENT)
   public void func_75137_b(int par1, int par2) {
      super.func_75137_b(par1, par2);
      switch(par1) {
      case 100:
         this.mTemperature = par2;
         break;
      case 101:
         this.mProcessingEnergy = par2;
         break;
      case 102:
         this.mSteamAmount = par2;
         break;
      case 103:
         this.mWaterAmount = par2;
      }

   }
}
