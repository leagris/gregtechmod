package gregtechmod.common;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_FuelHandler implements IFuelHandler {

   public GT_FuelHandler() {
      GameRegistry.registerFuelHandler(this);
   }

   public int getBurnTime(ItemStack aFuel) {
      if(aFuel != null && aFuel.func_77973_b() != null) {
         short rFuelValue = 0;
         if(aFuel.func_77973_b() instanceof GT_MetaGenerated_Item) {
            Short tNBT = (Short)((GT_MetaGenerated_Item)aFuel.func_77973_b()).mBurnValues.get(Short.valueOf((short)aFuel.func_77960_j()));
            if(tNBT != null) {
               rFuelValue = (short)Math.max(rFuelValue, tNBT.shortValue());
            }
         }

         NBTTagCompound tNBT1 = aFuel.func_77978_p();
         if(tNBT1 != null) {
            short tValue = tNBT1.func_74765_d("GT.ItemFuelValue");
            rFuelValue = (short)Math.max(rFuelValue, tValue);
         }

         if(GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "gemLignite")) {
            rFuelValue = (short)Math.max(rFuelValue, 300);
         }

         if(GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustWood")) {
            rFuelValue = (short)Math.max(rFuelValue, 100);
         }

         if(GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSmallWood")) {
            rFuelValue = (short)Math.max(rFuelValue, 25);
         }

         if(GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustTinyWood")) {
            rFuelValue = (short)Math.max(rFuelValue, 11);
         }

         if(GT_Utility.areStacksEqual(aFuel, new ItemStack(Item.field_77792_au, 1))) {
            rFuelValue = (short)Math.max(rFuelValue, 600);
         }

         if(GT_Utility.areStacksEqual(aFuel, new ItemStack(Item.field_77790_av, 1))) {
            rFuelValue = (short)Math.max(rFuelValue, 600);
         }

         if(GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechMaterial(15, 1))) {
            rFuelValue = (short)Math.max(rFuelValue, 1600);
         }

         if(GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechMaterial(60, 1))) {
            rFuelValue = (short)Math.max(rFuelValue, 200);
         }

         if(GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechMaterial(61, 1))) {
            rFuelValue = (short)Math.max(rFuelValue, 200);
         }

         if(GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechMaterial(62, 1))) {
            rFuelValue = (short)Math.max(rFuelValue, 200);
         }

         if(GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechMaterial(63, 1))) {
            rFuelValue = (short)Math.max(rFuelValue, 200);
         }

         return rFuelValue;
      } else {
         return 0;
      }
   }
}
