package gregtechmod.api.items;

import gregtechmod.api.items.GT_Generic_Item;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_RadioactiveCell_Item extends GT_Generic_Item {

   protected int maxDelay;
   protected int pulserate;
   protected int cellCount;
   protected ItemStack mDepleted;


   public GT_RadioactiveCell_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDelay, int aCellcount, int aPulseRate, ItemStack aDepleted) {
      super(aID, aUnlocalized, aEnglish, (String)null);
      this.func_77625_d(1);
      this.func_77656_e(10000);
      this.setNoRepair();
      this.cellCount = Math.max(1, aCellcount);
      this.pulserate = aPulseRate;
      this.maxDelay = aMaxDelay;
      this.mDepleted = aDepleted;
   }

   protected boolean outputPulseForStack(ItemStack aStack) {
      NBTTagCompound tNBT = aStack.func_77978_p();
      if(tNBT == null) {
         tNBT = new NBTTagCompound();
         aStack.func_77982_d(tNBT);
      }

      tNBT.func_74768_a("output", tNBT.func_74762_e("output") + 1);
      return this.pulserate > 0 || tNBT.func_74762_e("output") % -this.pulserate == 0;
   }

   protected boolean incrementPulseForStack(ItemStack aStack) {
      NBTTagCompound tNBT = aStack.func_77978_p();
      if(tNBT == null) {
         tNBT = new NBTTagCompound();
         aStack.func_77982_d(tNBT);
      }

      tNBT.func_74768_a("pulse", tNBT.func_74762_e("pulse") + 1);
      return this.pulserate > 0 || tNBT.func_74762_e("pulse") % -this.pulserate == 0;
   }

   protected void setDurabilityForStack(ItemStack aStack, int aDurability) {
      NBTTagCompound tNBT = aStack.func_77978_p();
      if(tNBT == null) {
         tNBT = new NBTTagCompound();
         aStack.func_77982_d(tNBT);
      }

      tNBT.func_74768_a("durability", aDurability);
      if(this.maxDelay > 0) {
         double var4 = ((double)this.maxDelay - (double)aDurability) / (double)this.maxDelay;
         int var6 = (int)((double)aStack.func_77958_k() * var4);
         if(var6 >= aStack.func_77958_k()) {
            var6 = aStack.func_77958_k() - 1;
         }

         aStack.func_77964_b(aStack.func_77958_k() - var6);
      }

   }

   public static int getDurabilityOfStack(ItemStack aStack) {
      NBTTagCompound tNBT = aStack.func_77978_p();
      if(tNBT == null) {
         tNBT = new NBTTagCompound();
         aStack.func_77982_d(tNBT);
      }

      return tNBT.func_74762_e("durability");
   }

   public int getMaxNuclearDurability() {
      return this.maxDelay;
   }

   public int func_77619_b() {
      return 0;
   }

   public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
      return false;
   }

   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return false;
   }

   protected static int sumUp(int a) {
      int b = 0;

      for(int c = 1; c <= a; ++c) {
         b += c;
      }

      return b;
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      aList.add("Time left: " + (this.maxDelay - getDurabilityOfStack(aStack)) + " secs");
   }
}
