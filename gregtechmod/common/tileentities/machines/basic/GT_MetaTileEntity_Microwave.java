package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.FakePlayer;

public class GT_MetaTileEntity_Microwave extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Microwave(int aID, String aName, String aNameRegional) {
      super(aID, aName, aNameRegional);
   }

   public GT_MetaTileEntity_Microwave() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 148);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Microwave();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(this.mInventory[2] != null) {
         ItemStack tOutput = GT_ModHandler.getSmeltingOutput(this.mInventory[2], false, this.mInventory[3]);
         if(GT_Items.Spray_CFoam.isStackEqual(this.mInventory[2], true, true) || this.mInventory[2].func_77973_b() == Item.field_77811_bE || this.mInventory[2].func_77973_b() == Item.field_92106_bV || this.mInventory[2].func_77973_b() == Item.field_92104_bU || GT_Utility.areStacksEqual(this.mInventory[2], GT_ModHandler.getIC2Item("constructionFoamSprayer", 1L, 32767)) || GT_Utility.areStacksEqual(this.mInventory[2], GT_ModHandler.getIC2Item("constructionFoamPellet", 1L)) || GT_Utility.areStacksEqual(this.mInventory[2], GT_ModHandler.getIC2Item("constructionFoam", 1L, 32767))) {
            this.mInventory[2] = null;
            this.getBaseMetaTileEntity().doExplosion(128);

            try {
               for(byte e = 0; e < 6; ++e) {
                  GT_ModHandler.getIC2Item("constructionFoamSprayer", 1L, new ItemStack(Block.field_71945_L, 1)).func_77973_b().func_77648_a(GT_ModHandler.getIC2Item("constructionFoamSprayer", 1L, new ItemStack(Block.field_71945_L, 1)), new FakePlayer(this.getBaseMetaTileEntity().getWorld(), "Foo"), this.getBaseMetaTileEntity().getWorld(), this.getBaseMetaTileEntity().getXCoord(), this.getBaseMetaTileEntity().getYCoord(), this.getBaseMetaTileEntity().getZCoord(), e, 0.0F, 0.0F, 0.0F);
               }
            } catch (Throwable var3) {
               ;
            }

            return;
         }

         if(OrePrefixes.ingot.contains(this.mInventory[2]) && !Materials.Meat.contains(new ItemStack[]{this.mInventory[2]}) && !Materials.MeatCooked.contains(new ItemStack[]{this.mInventory[2]}) && !Materials.MeatRaw.contains(new ItemStack[]{this.mInventory[2]}) || OrePrefixes.ingot.contains(tOutput) && !Materials.Meat.contains(new ItemStack[]{tOutput}) && !Materials.MeatCooked.contains(new ItemStack[]{tOutput}) && !Materials.MeatRaw.contains(new ItemStack[]{tOutput}) || Materials.Netherrack.contains(new ItemStack[]{this.mInventory[2]}) || Materials.Gunpowder.contains(new ItemStack[]{this.mInventory[2]}) || Materials.Gunpowder.contains(new ItemStack[]{tOutput}) || GT_Utility.getBlockFromStack(this.mInventory[2]) == Block.field_72012_bb || this.mInventory[2].func_77973_b() == Item.field_77764_aP) {
            this.mInventory[2] = null;
            this.getBaseMetaTileEntity().doExplosion(128);
            return;
         }

         if(TileEntityFurnace.func_70398_a(this.mInventory[2]) > 0 || TileEntityFurnace.func_70398_a(tOutput) > 0) {
            this.mInventory[2] = null;
            this.getBaseMetaTileEntity().setOnFire();
            return;
         }

         if(tOutput != null) {
            this.mOutputItem1 = tOutput;
            this.mEUt = 4;
            this.mMaxProgresstime = 25;
            --this.mInventory[2].field_77994_a;
         }
      }

   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(207)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public int getFrontFacingInactive() {
      return 1;
   }

   public int getFrontFacingActive() {
      return 5;
   }

   public String getDescription() {
      return "Did you really read the instruction Manual?";
   }
}
