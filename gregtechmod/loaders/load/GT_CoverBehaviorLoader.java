package gregtechmod.loaders.load;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.common.covers.GT_Cover_ControlsWork;
import gregtechmod.common.covers.GT_Cover_Conveyor;
import gregtechmod.common.covers.GT_Cover_Crafting;
import gregtechmod.common.covers.GT_Cover_DoesWork;
import gregtechmod.common.covers.GT_Cover_Drain;
import gregtechmod.common.covers.GT_Cover_EUMeter;
import gregtechmod.common.covers.GT_Cover_EnergyOnly;
import gregtechmod.common.covers.GT_Cover_ItemMeter;
import gregtechmod.common.covers.GT_Cover_ItemValve;
import gregtechmod.common.covers.GT_Cover_LiquidMeter;
import gregtechmod.common.covers.GT_Cover_RedstoneConductor;
import gregtechmod.common.covers.GT_Cover_RedstoneOnly;
import gregtechmod.common.covers.GT_Cover_RedstoneReceiverExternal;
import gregtechmod.common.covers.GT_Cover_RedstoneReceiverInternal;
import gregtechmod.common.covers.GT_Cover_RedstoneSignalizer;
import gregtechmod.common.covers.GT_Cover_RedstoneTransmitterExternal;
import gregtechmod.common.covers.GT_Cover_RedstoneTransmitterInternal;
import gregtechmod.common.covers.GT_Cover_Screen;
import gregtechmod.common.covers.GT_Cover_Shutter;
import gregtechmod.common.covers.GT_Cover_SolarPanel;
import gregtechmod.common.covers.GT_Cover_Valve;
import gregtechmod.common.covers.GT_Cover_Vent;
import net.minecraft.item.ItemStack;

public class GT_CoverBehaviorLoader implements Runnable {

   public void run() {
      GT_Log.out.println("GT_Mod: Adding Cover Behaviors");
      new GT_Cover_Vent(new ItemStack[]{GT_ModHandler.getIC2Item("reactorVent", 1L), GT_ModHandler.getIC2Item("reactorVentCore", 1L), GT_ModHandler.getIC2Item("reactorVentSpread", 1L)}, 1);
      new GT_Cover_Vent(new ItemStack[]{GT_ModHandler.getIC2Item("reactorVentDiamond", 1L), GT_ModHandler.getIC2Item("reactorVentGold", 1L)}, 2);
      new GT_Cover_DoesWork(GregTech_API.getGregTechComponent(30, 1));
      new GT_Cover_ControlsWork(GregTech_API.getGregTechComponent(31, 1));
      new GT_Cover_EUMeter(GregTech_API.getGregTechComponent(15, 1));
      new GT_Cover_EnergyOnly(GregTech_API.getGregTechComponent(0, 1));
      new GT_Cover_RedstoneOnly(GregTech_API.getGregTechComponent(1, 1));
      new GT_Cover_Screen(GregTech_API.getGregTechComponent(4, 1));
      new GT_Cover_Conveyor(GregTech_API.getGregTechComponent(5, 1));
      new GT_Cover_Valve(GregTech_API.getGregTechComponent(6, 1));
      new GT_Cover_SolarPanel(GregTech_API.getGregTechComponent(7, 1), 32, 4);
      new GT_Cover_ItemValve(GregTech_API.getGregTechComponent(8, 1));
      new GT_Cover_Drain(GregTech_API.getGregTechComponent(9, 1));
      new GT_Cover_LiquidMeter(GregTech_API.getGregTechComponent(10, 1));
      new GT_Cover_ItemMeter(GregTech_API.getGregTechComponent(11, 1));
      new GT_Cover_Crafting(GregTech_API.getGregTechComponent(64, 1));
      new GT_Cover_SolarPanel(GregTech_API.getGregTechComponent(65, 1), 160, 32);
      new GT_Cover_SolarPanel(GregTech_API.getGregTechComponent(66, 1), 2048, 256);
      new GT_Cover_SolarPanel(GregTech_API.getGregTechComponent(67, 1), 16384, 2048);
      new GT_Cover_Shutter(GregTech_API.getGregTechComponent(69, 1));
      new GT_Cover_RedstoneReceiverInternal(GregTech_API.getGregTechComponent(82, 1));
      new GT_Cover_RedstoneReceiverExternal(GregTech_API.getGregTechComponent(83, 1));
      new GT_Cover_RedstoneTransmitterInternal(GregTech_API.getGregTechComponent(84, 1));
      new GT_Cover_RedstoneTransmitterExternal(GregTech_API.getGregTechComponent(85, 1));
      new GT_Cover_RedstoneConductor(GregTech_API.getGregTechComponent(86, 1));
      new GT_Cover_RedstoneSignalizer(GregTech_API.getGregTechComponent(87, 1));
   }
}
