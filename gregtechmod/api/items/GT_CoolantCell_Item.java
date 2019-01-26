package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_CoolantCell_Item extends GT_Generic_Item {

   protected int heatStorage;


   public GT_CoolantCell_Item(int aID, String aUnlocalized, String aEnglish, int aMaxStore) {
      super(aID, aUnlocalized, aEnglish, (String)null);
      this.func_77625_d(1);
      this.func_77656_e(100);
      this.setNoRepair();
      this.heatStorage = aMaxStore;
      this.func_77637_a(GregTech_API.TAB_GREGTECH);
   }

   protected void setHeatForStack(ItemStack aStack, int aHeat) {
      NBTTagCompound tNBT = aStack.func_77978_p();
      if(tNBT == null) {
         tNBT = new NBTTagCompound();
         aStack.func_77982_d(tNBT);
      }

      tNBT.func_74768_a("heat", aHeat);
      if(this.heatStorage > 0) {
         double var4 = (double)aHeat / (double)this.heatStorage;
         int var6 = (int)((double)aStack.func_77958_k() * var4);
         if(var6 >= aStack.func_77958_k()) {
            var6 = aStack.func_77958_k() - 1;
         }

         aStack.func_77964_b(var6);
      }

   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      aList.add("Stored Heat: " + getHeatOfStack(aStack));
   }

   protected static int getHeatOfStack(ItemStack aStack) {
      NBTTagCompound tNBT = aStack.func_77978_p();
      if(tNBT == null) {
         tNBT = new NBTTagCompound();
         aStack.func_77982_d(tNBT);
      }

      return tNBT.func_74762_e("heat");
   }
}
