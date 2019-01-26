package gregtechmod.common;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_Container_BasicMachine;
import gregtechmod.api.gui.GT_Container_BasicTank;
import gregtechmod.api.gui.GT_Container_MultiMachine;
import gregtechmod.api.gui.GT_GUIContainer_BasicMachine;
import gregtechmod.api.gui.GT_GUIContainer_MultiMachine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.containers.GT_Container_1by1;
import gregtechmod.common.containers.GT_Container_2by2;
import gregtechmod.common.containers.GT_Container_3by3;
import gregtechmod.common.containers.GT_Container_4by4;
import gregtechmod.common.containers.GT_Container_AESU_Meta;
import gregtechmod.common.containers.GT_Container_AdvancedPump;
import gregtechmod.common.containers.GT_Container_AdvancedTranslocator;
import gregtechmod.common.containers.GT_Container_AdvancedWorkbench;
import gregtechmod.common.containers.GT_Container_BlastFurnace;
import gregtechmod.common.containers.GT_Container_BronzeBlastFurnace;
import gregtechmod.common.containers.GT_Container_BronzeBoiler;
import gregtechmod.common.containers.GT_Container_BronzeWorkbench;
import gregtechmod.common.containers.GT_Container_Centrifuge;
import gregtechmod.common.containers.GT_Container_ChemicalReactor;
import gregtechmod.common.containers.GT_Container_CropHarvestor;
import gregtechmod.common.containers.GT_Container_DistillationTower;
import gregtechmod.common.containers.GT_Container_ElectricAutoWorkbench;
import gregtechmod.common.containers.GT_Container_ElectricBufferAdvanced;
import gregtechmod.common.containers.GT_Container_ElectricBufferLarge;
import gregtechmod.common.containers.GT_Container_ElectricBufferSmall;
import gregtechmod.common.containers.GT_Container_ElectricFilter;
import gregtechmod.common.containers.GT_Container_ElectricInventoryManager;
import gregtechmod.common.containers.GT_Container_ElectricItemClearer;
import gregtechmod.common.containers.GT_Container_ElectricRegulatorAdvanced;
import gregtechmod.common.containers.GT_Container_ElectricRetrieverAdvanced;
import gregtechmod.common.containers.GT_Container_ElectricSorter;
import gregtechmod.common.containers.GT_Container_ElectricTypeFilter;
import gregtechmod.common.containers.GT_Container_ElectricTypeSorter;
import gregtechmod.common.containers.GT_Container_Electrolyzer;
import gregtechmod.common.containers.GT_Container_FusionComputer;
import gregtechmod.common.containers.GT_Container_Grinder;
import gregtechmod.common.containers.GT_Container_IDSU_Meta;
import gregtechmod.common.containers.GT_Container_ImplosionCompressor;
import gregtechmod.common.containers.GT_Container_Item_Destructopack;
import gregtechmod.common.containers.GT_Container_MagicEnergyAbsorber;
import gregtechmod.common.containers.GT_Container_MaintenanceHatch;
import gregtechmod.common.containers.GT_Container_RedstoneCircuitBlock;
import gregtechmod.common.containers.GT_Container_RockBreaker;
import gregtechmod.common.containers.GT_Container_Safe;
import gregtechmod.common.containers.GT_Container_Sawmill;
import gregtechmod.common.containers.GT_Container_Scrapboxinator;
import gregtechmod.common.containers.GT_Container_Sonictron;
import gregtechmod.common.containers.GT_Container_SteelBoiler;
import gregtechmod.common.containers.GT_Container_Teleporter;
import gregtechmod.common.containers.GT_Container_TradeOMat_Inventory_Money;
import gregtechmod.common.containers.GT_Container_TradeOMat_Inventory_Objects;
import gregtechmod.common.containers.GT_Container_TradeOMat_Main;
import gregtechmod.common.containers.GT_Container_TradeOMat_Settings;
import gregtechmod.common.containers.GT_Container_Translocator;
import gregtechmod.common.containers.GT_Container_VacuumFreezer;
import gregtechmod.common.gui.GT_GUIContainerMetaID_Machine;
import gregtechmod.common.gui.GT_GUIContainer_1by1;
import gregtechmod.common.gui.GT_GUIContainer_2by2;
import gregtechmod.common.gui.GT_GUIContainer_3by3;
import gregtechmod.common.gui.GT_GUIContainer_4by4;
import gregtechmod.common.gui.GT_GUIContainer_AESU_Meta;
import gregtechmod.common.gui.GT_GUIContainer_AdvancedPump;
import gregtechmod.common.gui.GT_GUIContainer_AdvancedTranslocator;
import gregtechmod.common.gui.GT_GUIContainer_AdvancedWorkbench;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_AlloySmelter;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Assembler;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Bender;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Canner;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Compressor;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Cutter;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_E_Furnace;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Extractor;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Extruder;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Lathe;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Macerator;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Wiremill;
import gregtechmod.common.gui.GT_GUIContainer_BlastFurnace;
import gregtechmod.common.gui.GT_GUIContainer_BronzeBlastFurnace;
import gregtechmod.common.gui.GT_GUIContainer_BronzeBoiler;
import gregtechmod.common.gui.GT_GUIContainer_BronzeWorkbench;
import gregtechmod.common.gui.GT_GUIContainer_Centrifuge;
import gregtechmod.common.gui.GT_GUIContainer_ChemicalReactor;
import gregtechmod.common.gui.GT_GUIContainer_CropHarvestor;
import gregtechmod.common.gui.GT_GUIContainer_Destructopack;
import gregtechmod.common.gui.GT_GUIContainer_DieselGenerator;
import gregtechmod.common.gui.GT_GUIContainer_DigitalTank;
import gregtechmod.common.gui.GT_GUIContainer_DistillationTower;
import gregtechmod.common.gui.GT_GUIContainer_ElectricAutoWorkbench;
import gregtechmod.common.gui.GT_GUIContainer_ElectricBufferAdvanced;
import gregtechmod.common.gui.GT_GUIContainer_ElectricBufferLarge;
import gregtechmod.common.gui.GT_GUIContainer_ElectricBufferSmall;
import gregtechmod.common.gui.GT_GUIContainer_ElectricFilter;
import gregtechmod.common.gui.GT_GUIContainer_ElectricInventoryManager;
import gregtechmod.common.gui.GT_GUIContainer_ElectricItemClearer;
import gregtechmod.common.gui.GT_GUIContainer_ElectricRegulatorAdvanced;
import gregtechmod.common.gui.GT_GUIContainer_ElectricRetrieverAdvanced;
import gregtechmod.common.gui.GT_GUIContainer_ElectricSorter;
import gregtechmod.common.gui.GT_GUIContainer_ElectricTypeFilter;
import gregtechmod.common.gui.GT_GUIContainer_ElectricTypeSorter;
import gregtechmod.common.gui.GT_GUIContainer_Electrolyzer;
import gregtechmod.common.gui.GT_GUIContainer_FusionComputer;
import gregtechmod.common.gui.GT_GUIContainer_FusionExtractor;
import gregtechmod.common.gui.GT_GUIContainer_FusionInjector;
import gregtechmod.common.gui.GT_GUIContainer_GasTurbine;
import gregtechmod.common.gui.GT_GUIContainer_Grinder;
import gregtechmod.common.gui.GT_GUIContainer_Hatch_Input;
import gregtechmod.common.gui.GT_GUIContainer_Hatch_Output;
import gregtechmod.common.gui.GT_GUIContainer_IDSU_Meta;
import gregtechmod.common.gui.GT_GUIContainer_ImplosionCompressor;
import gregtechmod.common.gui.GT_GUIContainer_MagicEnergyAbsorber;
import gregtechmod.common.gui.GT_GUIContainer_MagicEnergyConverter;
import gregtechmod.common.gui.GT_GUIContainer_MaintenanceHatch;
import gregtechmod.common.gui.GT_GUIContainer_PlasmaGenerator;
import gregtechmod.common.gui.GT_GUIContainer_Quantumtank;
import gregtechmod.common.gui.GT_GUIContainer_RedstoneCircuitBlock;
import gregtechmod.common.gui.GT_GUIContainer_RockBreaker;
import gregtechmod.common.gui.GT_GUIContainer_Safe;
import gregtechmod.common.gui.GT_GUIContainer_Sawmill;
import gregtechmod.common.gui.GT_GUIContainer_Scrapboxinator;
import gregtechmod.common.gui.GT_GUIContainer_SemifluidGenerator;
import gregtechmod.common.gui.GT_GUIContainer_Sonictron;
import gregtechmod.common.gui.GT_GUIContainer_SteelBoiler;
import gregtechmod.common.gui.GT_GUIContainer_Teleporter;
import gregtechmod.common.gui.GT_GUIContainer_ThermalGenerator;
import gregtechmod.common.gui.GT_GUIContainer_TradeOMat_Inventory_Money;
import gregtechmod.common.gui.GT_GUIContainer_TradeOMat_Inventory_Objects;
import gregtechmod.common.gui.GT_GUIContainer_TradeOMat_Main;
import gregtechmod.common.gui.GT_GUIContainer_TradeOMat_Settings;
import gregtechmod.common.gui.GT_GUIContainer_Translocator;
import gregtechmod.common.gui.GT_GUIContainer_VacuumFreezer;
import gregtechmod.common.tileentities.deprecated.GT_TileEntityMetaID_Machine;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Sonictron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_GUIHandler implements IGuiHandler {

   public GT_GUIHandler() {
      NetworkRegistry.instance().registerGuiHandler(GregTech_API.gregtechmod, this);
   }

   public Object getServerGuiElement(int aGUIID, EntityPlayer player, World world, int x, int y, int z) {
      TileEntity tTileEntity = world.func_72796_p(x, y, z);
      switch(aGUIID) {
      case 0:
         return new GT_ContainerMetaID_Machine(player.field_71071_by, (GT_TileEntityMetaID_Machine)tTileEntity);
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
      case 46:
      case 47:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
      case 65:
      case 66:
      case 67:
      case 68:
      case 69:
      case 70:
      case 71:
      case 72:
      case 73:
      case 74:
      case 75:
      case 76:
      case 77:
      case 78:
      case 79:
      case 80:
      case 81:
      case 82:
      case 83:
      case 84:
      case 85:
      case 86:
      case 87:
      case 88:
      case 89:
      case 90:
      case 91:
      case 92:
      case 93:
      case 94:
      case 95:
      default:
         return null;
      case 6:
         return new GT_Container_Sonictron(player.field_71071_by, (GT_TileEntity_Sonictron)tTileEntity);
      case 33:
         return new GT_Container_Item_Destructopack(player.field_71071_by, player.func_71045_bC());
      case 96:
         return new GT_Container_1by1(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 97:
         return new GT_Container_2by2(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 98:
         return new GT_Container_3by3(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 99:
         return new GT_Container_4by4(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 100:
         return new GT_Container_ElectricAutoWorkbench(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 101:
         return new GT_Container_Translocator(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 102:
         return new GT_Container_ElectricBufferSmall(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 103:
         return new GT_Container_ElectricBufferLarge(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 104:
         return new GT_Container_AdvancedTranslocator(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 105:
         return new GT_Container_ElectricBufferAdvanced(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 106:
         return new GT_Container_RockBreaker(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 107:
         return new GT_Container_ElectricSorter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 108:
         return new GT_Container_ElectricItemClearer(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 109:
         return new GT_Container_Electrolyzer(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 110:
         return new GT_Container_CropHarvestor(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 111:
         return new GT_Container_Scrapboxinator(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 112:
         return new GT_Container_Grinder(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 113:
         return new GT_Container_BlastFurnace(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 114:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 115:
         return new GT_Container_ImplosionCompressor(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 116:
         return new GT_Container_Sawmill(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 117:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 118:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 119:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 120:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 121:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 122:
         return new GT_Container_VacuumFreezer(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 123:
         return new GT_Container_ElectricRegulatorAdvanced(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 124:
         return new GT_Container_ChemicalReactor(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 125:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 126:
         return new GT_Container_MagicEnergyAbsorber(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 127:
         return new GT_Container_DistillationTower(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 128:
         return new GT_Container_Safe(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 129:
         return new GT_Container_ElectricInventoryManager(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 130:
         return new GT_Container_AdvancedPump(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 131:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 132:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 133:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 134:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 135:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 136:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 137:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 138:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 139:
         return new GT_Container_ElectricTypeSorter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 140:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 141:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 142:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 143:
         return new GT_Container_FusionComputer(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 144:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 145:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 146:
         return new GT_Container_Centrifuge(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 147:
         return new GT_Container_RedstoneCircuitBlock(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 148:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 149:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 150:
         return new GT_Container_AESU_Meta(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 151:
         return new GT_Container_IDSU_Meta(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 152:
         return new GT_Container_Teleporter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 153:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 154:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 155:
         return new GT_Container_MaintenanceHatch(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 156:
         return new GT_Container_MultiMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 157:
         return new GT_Container_MultiMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 158:
         return new GT_Container_MultiMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 159:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 160:
         return new GT_Container_AdvancedWorkbench(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 161:
         return new GT_Container_BronzeWorkbench(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 162:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 163:
         return new GT_Container_BronzeBoiler(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 164:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 165:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 166:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 167:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 168:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 169:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 170:
         return new GT_Container_BronzeBlastFurnace(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 171:
         return new GT_Container_SteelBoiler(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 172:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 173:
         return new GT_Container_ElectricFilter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 174:
         return new GT_Container_ElectricTypeFilter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 175:
         return new GT_Container_BasicTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 176:
         return new GT_Container_TradeOMat_Inventory_Objects(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 177:
         return new GT_Container_TradeOMat_Main(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 178:
         return new GT_Container_TradeOMat_Inventory_Money(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 179:
         return new GT_Container_TradeOMat_Settings(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 180:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 181:
         return new GT_Container_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 182:
         return new GT_Container_ElectricRetrieverAdvanced(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      }
   }

   public Object getClientGuiElement(int aGUIID, EntityPlayer player, World world, int x, int y, int z) {
      TileEntity tTileEntity = world.func_72796_p(x, y, z);
      switch(aGUIID) {
      case 0:
         return new GT_GUIContainerMetaID_Machine(player.field_71071_by, (GT_TileEntityMetaID_Machine)tTileEntity, "");
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
      case 46:
      case 47:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
      case 65:
      case 66:
      case 67:
      case 68:
      case 69:
      case 70:
      case 71:
      case 72:
      case 73:
      case 74:
      case 75:
      case 76:
      case 77:
      case 78:
      case 79:
      case 80:
      case 81:
      case 82:
      case 83:
      case 84:
      case 85:
      case 86:
      case 87:
      case 88:
      case 89:
      case 90:
      case 91:
      case 92:
      case 93:
      case 94:
      case 95:
      default:
         return null;
      case 6:
         return new GT_GUIContainer_Sonictron(player.field_71071_by, (GT_TileEntity_Sonictron)tTileEntity);
      case 33:
         return new GT_GUIContainer_Destructopack(player.field_71071_by, player.func_71045_bC());
      case 96:
         return new GT_GUIContainer_1by1(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "");
      case 97:
         return new GT_GUIContainer_2by2(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "");
      case 98:
         return new GT_GUIContainer_3by3(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "");
      case 99:
         return new GT_GUIContainer_4by4(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "");
      case 100:
         return new GT_GUIContainer_ElectricAutoWorkbench(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 101:
         return new GT_GUIContainer_Translocator(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 102:
         return new GT_GUIContainer_ElectricBufferSmall(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 103:
         return new GT_GUIContainer_ElectricBufferLarge(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 104:
         return new GT_GUIContainer_AdvancedTranslocator(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 105:
         return new GT_GUIContainer_ElectricBufferAdvanced(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 106:
         return new GT_GUIContainer_RockBreaker(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 107:
         return new GT_GUIContainer_ElectricSorter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 108:
         return new GT_GUIContainer_ElectricItemClearer(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 109:
         return new GT_GUIContainer_Electrolyzer(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 110:
         return new GT_GUIContainer_CropHarvestor(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 111:
         return new GT_GUIContainer_Scrapboxinator(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 112:
         return new GT_GUIContainer_Grinder(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 113:
         return new GT_GUIContainer_BlastFurnace(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 114:
         return new GT_GUIContainer_Quantumtank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 115:
         return new GT_GUIContainer_ImplosionCompressor(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 116:
         return new GT_GUIContainer_Sawmill(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 117:
         return new GT_GUIContainer_DieselGenerator(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 118:
         return new GT_GUIContainer_GasTurbine(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 119:
         return new GT_GUIContainer_ThermalGenerator(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 120:
         return new GT_GUIContainer_SemifluidGenerator(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 121:
         return new GT_GUIContainer_PlasmaGenerator(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 122:
         return new GT_GUIContainer_VacuumFreezer(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 123:
         return new GT_GUIContainer_ElectricRegulatorAdvanced(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 124:
         return new GT_GUIContainer_ChemicalReactor(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 125:
         return new GT_GUIContainer_MagicEnergyConverter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 126:
         return new GT_GUIContainer_MagicEnergyAbsorber(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 127:
         return new GT_GUIContainer_DistillationTower(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 128:
         return new GT_GUIContainer_Safe(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 129:
         return new GT_GUIContainer_ElectricInventoryManager(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 130:
         return new GT_GUIContainer_AdvancedPump(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 131:
         return new GT_GUIContainer_BasicMachine_Macerator(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Automatic Macerator", "Macerator.png");
      case 132:
         return new GT_GUIContainer_BasicMachine_Extractor(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Automatic Extractor", "Extractor.png");
      case 133:
         return new GT_GUIContainer_BasicMachine_Compressor(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Automatic Compressor", "Compressor.png");
      case 134:
         return new GT_GUIContainer_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Automatic Recycler", "Recycler.png");
      case 135:
         return new GT_GUIContainer_BasicMachine_E_Furnace(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Automatic E-Furnace", "E_Furnace.png");
      case 136:
         return new GT_GUIContainer_BasicMachine_Wiremill(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Automatic Wiremill", "Wiremill.png");
      case 137:
         return new GT_GUIContainer_BasicMachine_AlloySmelter(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Alloy Smelter", "E_Furnace.png");
      case 138:
         return new GT_GUIContainer_BasicMachine_Canner(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Automatic Canning Machine", "Canner.png");
      case 139:
         return new GT_GUIContainer_ElectricTypeSorter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 140:
         return new GT_GUIContainer_BasicMachine_Bender(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Plate Bending Machine", "Bender.png");
      case 141:
         return new GT_GUIContainer_BasicMachine_Assembler(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Assembling Machine", "Assembler.png");
      case 142:
         return new GT_GUIContainer_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Printing Factory", "Printer.png");
      case 143:
         return new GT_GUIContainer_FusionComputer(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 144:
         return new GT_GUIContainer_FusionInjector(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 145:
         return new GT_GUIContainer_FusionExtractor(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 146:
         return new GT_GUIContainer_Centrifuge(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 147:
         return new GT_GUIContainer_RedstoneCircuitBlock(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 148:
         return new GT_GUIContainer_BasicMachine_E_Furnace(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Microwave Oven", "E_Furnace.png");
      case 149:
         return new GT_GUIContainer_BasicMachine_Macerator(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Universal Macerator", "Macerator.png");
      case 150:
         return new GT_GUIContainer_AESU_Meta(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 151:
         return new GT_GUIContainer_IDSU_Meta(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 152:
         return new GT_GUIContainer_Teleporter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 153:
         return new GT_GUIContainer_Hatch_Input(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 154:
         return new GT_GUIContainer_Hatch_Output(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 155:
         return new GT_GUIContainer_MaintenanceHatch(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 156:
         return new GT_GUIContainer_MultiMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Gas Turbine", "MultiblockDisplay.png");
      case 157:
         return new GT_GUIContainer_MultiMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Steam Turbine", "MultiblockDisplay.png");
      case 158:
         return new GT_GUIContainer_MultiMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Thermal Boiler", "MultiblockDisplay.png");
      case 159:
         return new GT_GUIContainer_BasicMachine_Lathe(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Lathe", "Lathe.png");
      case 160:
         return new GT_GUIContainer_AdvancedWorkbench(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 161:
         return new GT_GUIContainer_BronzeWorkbench(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 162:
         return new GT_GUIContainer_BasicMachine_Cutter(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Plate Cutting Machine", "Cutter.png");
      case 163:
         return new GT_GUIContainer_BronzeBoiler(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 164:
         return new GT_GUIContainer_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Sturdy Grinder", "BronzeMacerator.png");
      case 165:
         return new GT_GUIContainer_BasicMachine_E_Furnace(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Steam Furnace", "BronzeFurnace.png");
      case 166:
         return new GT_GUIContainer_BasicMachine_AlloySmelter(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Steam Smelter", "BronzeFurnace.png");
      case 167:
         return new GT_GUIContainer_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Steam Forge Hammer", "BronzeHammer.png", (byte)6, (byte)3);
      case 168:
         return new GT_GUIContainer_BasicMachine_Compressor(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Steam Compressor", "BronzeCompressor.png");
      case 169:
         return new GT_GUIContainer_BasicMachine_Extractor(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Steam Extractor", "BronzeExtractor.png");
      case 170:
         return new GT_GUIContainer_BronzeBlastFurnace(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 171:
         return new GT_GUIContainer_SteelBoiler(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 172:
         return new GT_GUIContainer_BasicMachine_E_Furnace(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "High Pressure Steam Furnace", "SteelFurnace.png");
      case 173:
         return new GT_GUIContainer_ElectricFilter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 174:
         return new GT_GUIContainer_ElectricTypeFilter(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 175:
         return new GT_GUIContainer_DigitalTank(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 176:
         return new GT_GUIContainer_TradeOMat_Inventory_Objects(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 177:
         return new GT_GUIContainer_TradeOMat_Main(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 178:
         return new GT_GUIContainer_TradeOMat_Inventory_Money(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 179:
         return new GT_GUIContainer_TradeOMat_Settings(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      case 180:
         return new GT_GUIContainer_BasicMachine(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Scanner", "Scanner.png");
      case 181:
         return new GT_GUIContainer_BasicMachine_Extruder(player.field_71071_by, (IGregTechTileEntity)tTileEntity, "Extruder", "Extruder.png");
      case 182:
         return new GT_GUIContainer_ElectricRetrieverAdvanced(player.field_71071_by, (IGregTechTileEntity)tTileEntity);
      }
   }
}
