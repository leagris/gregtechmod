package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_Container;
import gregtechmod.common.tileentities.deprecated.GT_TileEntityMetaID_Machine;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class GT_ContainerMetaID_Machine extends GT_Container {

   protected GT_TileEntityMetaID_Machine mOldTileEntity;
   public int mEnergy;
   public int mStorage;
   public int mOutput;
   public int mInput;
   public int mID;


   public GT_ContainerMetaID_Machine(InventoryPlayer aInventoryPlayer, GT_TileEntityMetaID_Machine aTileEntity) {
      super(aInventoryPlayer, aTileEntity);
      this.mOldTileEntity = aTileEntity;
      this.addSlots(aInventoryPlayer);
      if(this.doesBindPlayerInventory()) {
         this.bindPlayerInventory(aInventoryPlayer);
      }

      this.func_75142_b();
   }

   public void func_75142_b() {
      super.func_75142_b();
      if(!this.mOldTileEntity.field_70331_k.field_72995_K) {
         this.mStorage = this.mOldTileEntity.maxEUStore();
         this.mEnergy = this.mOldTileEntity.getStored();
         this.mOutput = this.mOldTileEntity.maxEUOutput();
         this.mInput = this.mOldTileEntity.maxEUInput();
         Iterator var2 = this.field_75149_d.iterator();

         while(var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.func_71112_a(this, 0, this.mEnergy & '\uffff');
            var1.func_71112_a(this, 1, this.mEnergy >>> 16);
            var1.func_71112_a(this, 2, this.mStorage & '\uffff');
            var1.func_71112_a(this, 3, this.mStorage >>> 16);
            var1.func_71112_a(this, 4, this.mOutput);
            var1.func_71112_a(this, 5, this.mInput);
         }

      }
   }

   public void func_75132_a(ICrafting par1ICrafting) {
      super.func_75132_a(par1ICrafting);
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
      }

   }

   public boolean func_75145_c(EntityPlayer player) {
      return this.mOldTileEntity.func_70300_a(player);
   }
}
