package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.interfaces.IGregTechDeviceInformation;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_LanguageManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import shedar.mods.ic2.nuclearcontrol.api.CardState;
import shedar.mods.ic2.nuclearcontrol.api.ICardWrapper;
import shedar.mods.ic2.nuclearcontrol.api.IPanelDataSource;
import shedar.mods.ic2.nuclearcontrol.api.IRemoteSensor;
import shedar.mods.ic2.nuclearcontrol.api.PanelSetting;
import shedar.mods.ic2.nuclearcontrol.api.PanelString;

public class GT_SensorCard_Item extends GT_Generic_Item implements IRemoteSensor, IPanelDataSource {

   private static final UUID CARD_TYPE = new UUID(0L, 41L);


   public GT_SensorCard_Item(int aID, String aUnlocalized, String aEnglish) {
      super(aID, aUnlocalized, aEnglish, "Insert into Display Panel");
      this.func_77625_d(1);
   }

   public void addAdditionalToolTips(List aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      if(aStack != null) {
         NBTTagCompound tNBT = aStack.func_77978_p();
         if(tNBT == null) {
            aList.add("Missing Coodinates!");
         } else {
            aList.add("Device at:");
            aList.add(String.format("x: %d, y: %d, z: %d", new Object[]{Integer.valueOf(tNBT.func_74762_e("x")), Integer.valueOf(tNBT.func_74762_e("y")), Integer.valueOf(tNBT.func_74762_e("z"))}));
         }
      }

   }

   public CardState update(TileEntity aPanel, ICardWrapper aCard, int aMaxRange) {
      ChunkCoordinates target = aCard.getTarget();
      TileEntity tTileEntity = aPanel.field_70331_k.func_72796_p(target.field_71574_a, target.field_71572_b, target.field_71573_c);
      if(tTileEntity != null && tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation)tTileEntity).isGivingInformation()) {
         String[] tInfoData = ((IGregTechDeviceInformation)tTileEntity).getInfoData();

         for(int i = 0; i < tInfoData.length; ++i) {
            aCard.setString("mString" + i, tInfoData[i]);
         }

         return CardState.OK;
      } else {
         return CardState.NO_TARGET;
      }
   }

   public List<PanelString> getStringData(int aSettings, ICardWrapper aCard, boolean aLabels) {
      LinkedList rList = new LinkedList();

      for(int i = 0; i < 8; ++i) {
         if((aSettings & 1 << i) != 0) {
            PanelString line = new PanelString();
            line.textLeft = GT_LanguageManager.getTranslation(aCard.getString("mString" + i), "\\\\");
            rList.add(line);
         }
      }

      return rList;
   }

   public List<PanelSetting> getSettingsList() {
      ArrayList rList = new ArrayList(30);

      for(int i = 0; i < 8; ++i) {
         rList.add(new PanelSetting("" + (i + 1), 1 << i, this.getCardType()));
      }

      return rList;
   }

   public UUID getCardType() {
      return CARD_TYPE;
   }

   @SideOnly(Side.CLIENT)
   public void func_77633_a(int var1, CreativeTabs aTab, List aList) {}

}
