package gregtechmod.api.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class GT_Generic_Item extends Item {

   public Icon mIcon;
   private final String mTooltip;


   public GT_Generic_Item(int aID, String aUnlocalized, String aEnglish, String aEnglishTooltip) {
      this(aID, aUnlocalized, aEnglish, aEnglishTooltip, true);
   }

   public GT_Generic_Item(int aID, String aUnlocalized, String aEnglish, String aEnglishTooltip, boolean aWriteToolTipIntoLangFile) {
      super(aID);
      this.func_77655_b(aUnlocalized);
      GT_LanguageManager.addStringLocalization(this.func_77658_a() + ".name", aEnglish);
      if(GT_Utility.isStringValid(aEnglishTooltip)) {
         GT_LanguageManager.addStringLocalization(this.mTooltip = this.func_77658_a() + ".tooltip_main", aEnglishTooltip, aWriteToolTipIntoLangFile);
      } else {
         this.mTooltip = null;
      }

      this.func_77637_a(GregTech_API.TAB_GREGTECH);
   }

   public final GT_Generic_Item registerAtOreDict(String aName, short aDamage) {
      GT_OreDictUnificator.registerOre(aName, new ItemStack(this, 1, aDamage));
      return this;
   }

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IconRegister aIconRegister) {
      this.mIcon = aIconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":this.func_77658_a()));
   }

   public boolean shouldPassSneakingClickToBlock(World aWorld, int aX, int aY, int aZ) {
      return true;
   }

   public Icon func_77617_a(int par1) {
      return this.mIcon;
   }

   public int getTier(ItemStack aStack) {
      return 0;
   }

   public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
      if(this.func_77612_l() > 0 && !this.func_77614_k()) {
         aList.add(aStack.func_77958_k() - this.getDamage(aStack) + " / " + aStack.func_77958_k());
      }

      if(this.mTooltip != null) {
         aList.add(GT_LanguageManager.getTranslation(this.mTooltip));
      }

      if(GT_ModHandler.isElectricItem(aStack)) {
         aList.add("Tier: " + this.getTier(aStack));
      }

      this.addAdditionalToolTips(aList, aStack);
   }

   protected void addAdditionalToolTips(List aList, ItemStack aStack) {}
}
