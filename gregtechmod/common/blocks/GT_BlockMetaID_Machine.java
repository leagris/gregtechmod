package gregtechmod.common.blocks;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IDebugableBlock;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import gregtechmod.api.metatileentity.BaseTileEntity;
import gregtechmod.api.util.GT_BaseCrop;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.GT_FluidRegistry;
import gregtechmod.common.blocks.GT_MachineMaterial;
import gregtechmod.common.render.GT_Block_Renderer;
import gregtechmod.common.tileentities.deprecated.GT_TileEntityMetaID_Machine;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_ComputerCube;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_PlayerDetector;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Sonictron;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Superconductor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;

public class GT_BlockMetaID_Machine extends BlockContainer implements IDebugableBlock {

   public static Icon[] mIcons = new Icon[420];


   public GT_BlockMetaID_Machine(int aID) {
      super(aID, new GT_MachineMaterial());
      this.func_71848_c(10.0F);
      this.func_71894_b(10.0F);
      this.func_71864_b("BlockMetaID_Machine");
      LanguageRegistry.addName(this, this.func_71917_a());
      String[] tRegionalNameList = new String[]{"You ran into a serious Bug, if you have aquired (in your Inventory) this Block legitimately, please report it immidiatly", "Fusion Reactor", "Lightning Rod", "Old Quantum Chest", "GregTech Computer Cube", "UUM-Assembler", "Sonictron", "Lapotronic Energy Storage Unit", "Interdimensional Energy Storage Unit", "Adjustable Energy Storage Unit", "Charge-O-Mat", "Industrial Centrifuge", "Superconductor Wire", "Player Detector", "Matter Fabricator", "Supercondensator"};

      int i;
      for(i = 0; i < 16; ++i) {
         GT_LanguageManager.addStringLocalization(this.func_71917_a() + "." + i + ".name", tRegionalNameList[i]);
      }

      this.func_71884_a(Block.field_71977_i);
      this.func_71849_a(GregTech_API.TAB_GREGTECH);

      for(i = 0; i < 16; ++i) {
         MinecraftForge.setBlockHarvestLevel(this, i, "wrench", 1);
      }

   }

   @SideOnly(Side.CLIENT)
   public void func_94332_a(IconRegister aIconRegister) {
      GregTech_API.FAIL_ICON = aIconRegister.func_94245_a("gregtech_addon:" + (GT_Config.system?"troll":this.func_71917_a() + "/failed"));
      mIcons[0] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine");
      mIcons[2] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_logo");
      mIcons[11] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_uum");
      mIcons[12] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_lesu");
      mIcons[13] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_lesu_lv_out");
      mIcons[14] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_lesu_mv_out");
      mIcons[15] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_lesu_hv_out");
      mIcons[30] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_matterfab");
      mIcons[31] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_matterfab_active");
      mIcons[70] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_grinder");
      mIcons[71] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_connector");
      mIcons[72] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_fire");
      mIcons[73] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_implosion");
      mIcons[74] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_itnt");
      mIcons[75] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_saw");
      mIcons[76] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_water");
      mIcons[77] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_ice");
      mIcons[78] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_oil");
      mIcons[109] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pump");
      mIcons[110] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_miningpipe");
      mIcons[111] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_vent_rotating");
      mIcons[112] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_green");
      mIcons[113] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_red");
      mIcons[114] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_crafting");
      mIcons[115] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_cobblegen");
      mIcons[116] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_blue");
      mIcons[117] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_yellow");
      mIcons[118] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_cropharvesting");
      mIcons[119] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_scrapbox");
      mIcons[120] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_green_redstone");
      mIcons[121] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_red_redstone");
      mIcons[122] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_crafting_redstone");
      mIcons[123] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_cobblegen_redstone");
      mIcons[124] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_blue_redstone");
      mIcons[125] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_yellow_redstone");
      mIcons[126] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_cropharvesting_redstone");
      mIcons[127] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_scrapbox_redstone");
      mIcons[212] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_magenta");
      mIcons[213] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_cyan");
      mIcons[220] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_magenta_redstone");
      mIcons[221] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pipe_cyan_redstone");
      mIcons[258] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_vent");
      mIcons[270] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_teleporter");
      mIcons[271] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_teleporter_active");
      mIcons[308] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_pump_side");
      mIcons[310] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_mv_out");
      mIcons[312] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_dimensional");
      mIcons[128] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_translocator_back");
      mIcons[129] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advtranslocator_back");
      mIcons[130] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_buffer_back");
      mIcons[131] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_largebuffer_back");
      mIcons[132] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advbuffer_back");
      mIcons[133] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_inserter_back");
      mIcons[134] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_sorter_back");
      mIcons[135] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_regulator_back");
      mIcons[136] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_translocator_back_redstone");
      mIcons[137] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advtranslocator_back_redstone");
      mIcons[138] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_buffer_back_redstone");
      mIcons[139] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_largebuffer_back_redstone");
      mIcons[140] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advbuffer_back_redstone");
      mIcons[141] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_inserter_back_redstone");
      mIcons[142] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_sorter_back_redstone");
      mIcons[143] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_regulator_back_redstone");
      mIcons[144] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_translocator_right");
      mIcons[145] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advtranslocator_right");
      mIcons[146] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_buffer_right");
      mIcons[147] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_largebuffer_right");
      mIcons[148] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advbuffer_right");
      mIcons[149] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_inserter_right");
      mIcons[150] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_sorter_right");
      mIcons[151] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_regulator_right");
      mIcons[152] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_translocator_right_redstone");
      mIcons[153] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advtranslocator_right_redstone");
      mIcons[154] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_buffer_right_redstone");
      mIcons[155] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_largebuffer_right_redstone");
      mIcons[156] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advbuffer_right_redstone");
      mIcons[157] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_inserter_right_redstone");
      mIcons[158] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_sorter_right_redstone");
      mIcons[159] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_regulator_right_redstone");
      mIcons[160] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_translocator_down");
      mIcons[161] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advtranslocator_down");
      mIcons[162] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_buffer_down");
      mIcons[163] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_largebuffer_down");
      mIcons[164] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advbuffer_down");
      mIcons[165] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_inserter_down");
      mIcons[166] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_sorter_down");
      mIcons[167] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_regulator_down");
      mIcons[168] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_translocator_down_redstone");
      mIcons[169] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advtranslocator_down_redstone");
      mIcons[170] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_buffer_down_redstone");
      mIcons[171] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_largebuffer_down_redstone");
      mIcons[172] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advbuffer_down_redstone");
      mIcons[173] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_inserter_down_redstone");
      mIcons[174] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_sorter_down_redstone");
      mIcons[175] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_regulator_down_redstone");
      mIcons[176] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_translocator_left");
      mIcons[177] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advtranslocator_left");
      mIcons[178] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_buffer_left");
      mIcons[179] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_largebuffer_left");
      mIcons[180] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advbuffer_left");
      mIcons[181] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_inserter_left");
      mIcons[182] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_sorter_left");
      mIcons[183] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_regulator_left");
      mIcons[184] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_translocator_left_redstone");
      mIcons[185] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advtranslocator_left_redstone");
      mIcons[186] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_buffer_left_redstone");
      mIcons[187] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_largebuffer_left_redstone");
      mIcons[188] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advbuffer_left_redstone");
      mIcons[189] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_inserter_left_redstone");
      mIcons[190] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_sorter_left_redstone");
      mIcons[191] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_regulator_left_redstone");
      mIcons[192] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_translocator_up");
      mIcons[193] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advtranslocator_up");
      mIcons[194] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_buffer_up");
      mIcons[195] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_largebuffer_up");
      mIcons[196] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advbuffer_up");
      mIcons[197] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_inserter_up");
      mIcons[198] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_sorter_up");
      mIcons[199] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_regulator_up");
      mIcons[200] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_translocator_up_redstone");
      mIcons[201] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advtranslocator_up_redstone");
      mIcons[202] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_buffer_up_redstone");
      mIcons[203] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_largebuffer_up_redstone");
      mIcons[204] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_advbuffer_up_redstone");
      mIcons[205] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_inserter_up_redstone");
      mIcons[206] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_sorter_up_redstone");
      mIcons[207] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_regulator_up_redstone");
      mIcons[390] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_filter_back");
      mIcons[391] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_filter_right");
      mIcons[392] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_filter_down");
      mIcons[393] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_filter_left");
      mIcons[394] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_filter_up");
      mIcons[395] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_filter_back_redstone");
      mIcons[396] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_filter_right_redstone");
      mIcons[397] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_filter_down_redstone");
      mIcons[398] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_filter_left_redstone");
      mIcons[399] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_filter_up_redstone");
      mIcons[4] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_square");
      mIcons[6] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_lightning");
      mIcons[7] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_crafting");
      mIcons[8] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_logo");
      mIcons[23] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_grid");
      mIcons[24] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_grid_active");
      mIcons[45] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_scanning");
      mIcons[46] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_radioactive");
      mIcons[47] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen");
      mIcons[48] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_random1");
      mIcons[49] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_random2");
      mIcons[50] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_random3");
      mIcons[55] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_data");
      mIcons[63] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_text");
      mIcons[68] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_inactive");
      mIcons[69] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_active");
      mIcons[313] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/adv_machine_screen_frequency");
      mIcons[16] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_adv_machine");
      mIcons[17] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_adv_machine_hv_out");
      mIcons[18] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_adv_machine_ev_out");
      mIcons[19] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_adv_machine_glass");
      mIcons[20] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_adv_machine_glass_yellow");
      mIcons[21] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_adv_machine_rod");
      mIcons[22] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_adv_machine_dimensional");
      mIcons[311] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_adv_machine_qev_out");
      mIcons[29] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top");
      mIcons[37] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_screen_data");
      mIcons[41] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_centrifuge");
      mIcons[42] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_centrifuge_active1");
      mIcons[43] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_centrifuge_active2");
      mIcons[44] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_centrifuge_active3");
      mIcons[52] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_redstone_main_off");
      mIcons[53] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_redstone_main_on");
      mIcons[57] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_redstone_off");
      mIcons[58] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_redstone_on");
      mIcons[79] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_pipe");
      mIcons[80] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_dieselmotor");
      mIcons[81] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_turbine");
      mIcons[83] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_glass");
      mIcons[84] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_glass_orange");
      mIcons[85] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_glass_gauge");
      mIcons[86] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_glass_cyan");
      mIcons[87] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_glass_magenta");
      mIcons[88] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_glass_yellow");
      mIcons[89] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_dragonegg");
      mIcons[90] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_crystal_cyan");
      mIcons[91] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_crystal_yellow");
      mIcons[92] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_crystal");
      mIcons[228] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_macerator");
      mIcons[229] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_macerator_active");
      mIcons[230] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_gauge");
      mIcons[231] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_gauge_green");
      mIcons[232] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_recycler");
      mIcons[233] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_recycler_active");
      mIcons[234] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_gauge_brown");
      mIcons[235] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_wiremill");
      mIcons[236] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_wiremill_active");
      mIcons[237] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_circuitry");
      mIcons[259] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_vent_rotating");
      mIcons[272] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_mv_out");
      mIcons[273] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_hv_out");
      mIcons[274] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_ev_out");
      mIcons[275] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_lv_in");
      mIcons[276] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_mv_in");
      mIcons[277] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_hv_in");
      mIcons[278] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_ev_in");
      mIcons[279] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_muffler");
      mIcons[281] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_dieselmotor_active");
      mIcons[282] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_lv_out");
      mIcons[296] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_gear");
      mIcons[297] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_gear_active");
      mIcons[302] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_dieselmotor2");
      mIcons[303] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_dieselmotor2_active");
      mIcons[405] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_extruder");
      mIcons[406] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_top_extruder_active");
      mIcons[32] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_bottom");
      mIcons[38] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_bottom_pipe");
      mIcons[54] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_bottom_redstone_main_off");
      mIcons[56] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_bottom_redstone_main_on");
      mIcons[59] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_bottom_redstone_off");
      mIcons[60] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_bottom_redstone_on");
      mIcons[82] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_bottom_turbine");
      mIcons[298] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_bottom_gear");
      mIcons[299] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_bottom_gear_active");
      mIcons[1] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_microwave");
      mIcons[5] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_microwave_active");
      mIcons[25] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_centrifuge");
      mIcons[26] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_centrifuge_active1");
      mIcons[27] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_centrifuge_active2");
      mIcons[28] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_centrifuge_active3");
      mIcons[33] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_printer");
      mIcons[34] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_printer_active");
      mIcons[36] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_pipe");
      mIcons[40] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side");
      mIcons[61] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_redstone_off");
      mIcons[62] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_redstone_on");
      mIcons[64] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_electrolyzer");
      mIcons[65] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_electrolyzer_active");
      mIcons[66] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_chemical");
      mIcons[67] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_chemical_active");
      mIcons[93] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_redstone_main_off");
      mIcons[94] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_redstone_main_on");
      mIcons[214] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_safe");
      mIcons[215] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_compartment");
      mIcons[216] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_shelf");
      mIcons[217] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_shelf_paper");
      mIcons[218] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_shelf_books");
      mIcons[219] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_shelf_cans");
      mIcons[222] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_desk");
      mIcons[223] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_cabinet");
      mIcons[224] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_controlpanel");
      mIcons[225] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_controlpanel_active");
      mIcons[226] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_gauge_red");
      mIcons[227] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_gauge_green");
      mIcons[238] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_bender");
      mIcons[239] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_bender_active");
      mIcons[240] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_pump");
      mIcons[241] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_pump_active");
      mIcons[242] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_gauge");
      mIcons[243] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_gauge_blue");
      mIcons[244] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_macerator");
      mIcons[245] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_macerator_active");
      mIcons[246] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_extractor");
      mIcons[247] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_extractor_active");
      mIcons[248] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_compressor");
      mIcons[249] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_compressor_active");
      mIcons[250] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_furnace");
      mIcons[251] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_furnace_active");
      mIcons[252] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_alloysmelter");
      mIcons[253] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_alloysmelter_active");
      mIcons[254] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_canner");
      mIcons[255] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_canner_active");
      mIcons[256] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_pulverizer");
      mIcons[257] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_pulverizer_active");
      mIcons[294] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_maintenance");
      mIcons[295] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_maintenance_ducttape");
      mIcons[300] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_gear");
      mIcons[301] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_gear_active");
      mIcons[304] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_lathe");
      mIcons[305] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_lathe_active");
      mIcons[306] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_cutter");
      mIcons[307] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_cutter_active");
      mIcons[309] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_tradomat");
      mIcons[407] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_extruder");
      mIcons[408] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/machine_side_extruder_active");
      mIcons[96] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_0");
      mIcons[97] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_1");
      mIcons[98] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_2");
      mIcons[99] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_3");
      mIcons[100] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_4");
      mIcons[101] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_5");
      mIcons[102] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_6");
      mIcons[103] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_7");
      mIcons[104] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_8");
      mIcons[105] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_9");
      mIcons[106] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_10");
      mIcons[107] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_11");
      mIcons[108] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/superconductor_in");
      mIcons[9] = mIcons[0];
      mIcons[10] = Block.field_71988_x.func_71858_a(0, 0);
      mIcons[208] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/wood_shelf");
      mIcons[209] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/wood_shelf_paper");
      mIcons[210] = Block.field_72093_an.func_71858_a(2, 0);
      mIcons[211] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/wood_shelf_cans");
      mIcons[35] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/audio_out_active");
      mIcons[39] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/audio_out");
      mIcons[293] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/audio");
      mIcons[291] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/lamp_off");
      mIcons[292] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/lamp_on");
      mIcons[51] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover");
      mIcons[95] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_noredstone");
      mIcons[260] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_emitter");
      mIcons[261] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_controller");
      mIcons[262] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_eumeter");
      mIcons[263] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_conveyor");
      mIcons[264] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_receiver_in");
      mIcons[265] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_receiver_out");
      mIcons[266] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_transmitter_in");
      mIcons[267] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_transmitter_out");
      mIcons[268] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_conductor");
      mIcons[269] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_signalizer");
      mIcons[280] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_valve");
      mIcons[283] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_itemvalve");
      mIcons[284] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_drain");
      mIcons[285] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_solar_0");
      mIcons[286] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_itemmeter");
      mIcons[287] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_redstone_liquidmeter");
      mIcons[288] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_datacircuit");
      mIcons[289] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_energycircuit");
      mIcons[290] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/cover_crafting");
      mIcons[314] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_top");
      mIcons[317] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_top_crafting");
      mIcons[328] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_top_macerator");
      mIcons[329] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_top_macerator_active");
      mIcons[344] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_top_pipe");
      mIcons[315] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_bottom");
      mIcons[335] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_bottom_alloysmelter");
      mIcons[339] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_bottom_furnace");
      mIcons[345] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_bottom_pipe");
      mIcons[316] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side");
      mIcons[318] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_desk");
      mIcons[319] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_cabinet");
      mIcons[320] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_compartment");
      mIcons[326] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_macerator");
      mIcons[327] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_macerator_active");
      mIcons[330] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_furnace");
      mIcons[331] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_furnace_active");
      mIcons[338] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_furnace_side");
      mIcons[332] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_alloysmelter");
      mIcons[333] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_alloysmelter_active");
      mIcons[334] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_alloysmelter_side");
      mIcons[336] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_hammer");
      mIcons[337] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_hammer_active");
      mIcons[340] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_compressor");
      mIcons[341] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_compressor_active");
      mIcons[342] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_extractor");
      mIcons[343] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_extractor_active");
      mIcons[346] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_pipe");
      mIcons[347] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_blastfurnace");
      mIcons[348] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_side_blastfurnace_active");
      mIcons[321] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_boiler_side");
      mIcons[322] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_boiler_top");
      mIcons[323] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_boiler_bottom");
      mIcons[324] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_boiler_front");
      mIcons[325] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/bronze_boiler_front_active");
      mIcons[349] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_boiler_side");
      mIcons[350] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_boiler_top");
      mIcons[351] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_boiler_bottom");
      mIcons[352] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_boiler_front");
      mIcons[353] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_boiler_front_active");
      mIcons[354] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_top");
      mIcons[357] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_top_pipe");
      mIcons[355] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_bottom");
      mIcons[358] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_bottom_pipe");
      mIcons[363] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_bottom_furnace");
      mIcons[356] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_side");
      mIcons[359] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_side_pipe");
      mIcons[360] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_side_furnace");
      mIcons[361] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_side_furnace_active");
      mIcons[362] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/steel_side_furnace_side");
      mIcons[3] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/frame_iron");
      mIcons[387] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/frame_aluminium");
      mIcons[388] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/frame_steel");
      mIcons[389] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/frame_stainlesssteel");
      mIcons[400] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/frame_tungstensteel");
      mIcons[368] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/concrete_foam");
      mIcons[369] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/concrete_white");
      mIcons[364] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_bronze");
      mIcons[365] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_bronze_connected");
      mIcons[366] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_steel");
      mIcons[367] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_steel_connected");
      mIcons[370] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_brass");
      mIcons[371] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_brass_connected");
      mIcons[372] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_bronze_connected_large");
      mIcons[373] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_steel_connected_large");
      mIcons[374] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_brass_connected_large");
      mIcons[375] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_electrum");
      mIcons[376] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_electrum_connected");
      mIcons[377] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_electrum_connected_large");
      mIcons[378] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_stainlesssteel");
      mIcons[379] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_stainlesssteel_connected");
      mIcons[380] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_stainlesssteel_connected_large");
      mIcons[381] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_tungstensteel");
      mIcons[382] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_tungstensteel_connected");
      mIcons[383] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_tungstensteel_connected_large");
      mIcons[384] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_platinum");
      mIcons[385] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_platinum_connected");
      mIcons[386] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_platinum_connected_large");
      mIcons[401] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_bronze_connected_small");
      mIcons[402] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_steel_connected_small");
      mIcons[403] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_stainlesssteel_connected_small");
      mIcons[404] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/pipe_tungstensteel_connected_small");
      mIcons[409] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_voltage_charger_bottom");
      mIcons[410] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_voltage_charger_side");
      mIcons[411] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_voltage_charger_top");
      mIcons[412] = aIconRegister.func_94245_a("gregtech_addon:" + this.func_71917_a() + "/high_voltage_charger_top_active");
      if(GregTech_API.sPostloadFinished) {
         GT_Log.out.println("GT_Mod: Setting up Icon Register for Blocks");
         GregTech_API.sBlockIcons = aIconRegister;
         GT_Log.out.println("GT_Mod: Registering MetaTileEntity specific Textures");
         IMetaTileEntity[] i = GregTech_API.mMetaTileList;
         int tRunnable = i.length;

         for(int e = 0; e < tRunnable; ++e) {
            IMetaTileEntity tMetaTileEntity = i[e];

            try {
               if(tMetaTileEntity != null) {
                  tMetaTileEntity.registerIcons(aIconRegister);
               }
            } catch (Throwable var9) {
               var9.printStackTrace(GT_Log.err);
            }
         }

         GT_Log.out.println("GT_Mod: Registering Crop specific Textures");

         Iterator var11;
         try {
            var11 = GT_BaseCrop.sCropList.iterator();

            while(var11.hasNext()) {
               GT_BaseCrop var12 = (GT_BaseCrop)var11.next();
               var12.registerSprites(aIconRegister);
            }
         } catch (Throwable var10) {
            var10.printStackTrace(GT_Log.err);
         }

         GT_Log.out.println("GT_Mod: Loading Fluid Icons");
         var11 = GT_FluidRegistry.sFluids.iterator();

         while(var11.hasNext()) {
            Fluid var13 = (Fluid)var11.next();
            var13.setIcons(aIconRegister.func_94245_a("gregtech_addon:fluids/" + var13.getUnlocalizedName()));
         }

         GT_Log.out.println("GT_Mod: Starting Cover Load Phase Clientside");
         var11 = GregTech_API.sGTCoverload.iterator();

         Runnable var15;
         while(var11.hasNext()) {
            var15 = (Runnable)var11.next();

            try {
               var15.run();
            } catch (Throwable var8) {
               var8.printStackTrace(GT_Log.err);
            }
         }

         GT_Log.out.println("GT_Mod: Starting Block Icon Load Phase Clientside");
         var11 = GregTech_API.sGTBlockIconload.iterator();

         while(var11.hasNext()) {
            var15 = (Runnable)var11.next();

            try {
               var15.run();
            } catch (Throwable var7) {
               var7.printStackTrace(GT_Log.err);
            }
         }

         if(GregTech_API.DEBUG_MODE) {
            GT_Log.out.println("GT_Mod: Dumping out free spaces in the Machine Icon List: ");

            for(int var14 = 0; var14 < mIcons.length; ++var14) {
               if(mIcons[var14] == null) {
                  GT_Log.out.println("Free Machine-Icon-ID at: " + var14);
               }
            }
         }
      }

   }

   public Icon func_71895_b(IBlockAccess aIBlockAccess, int aX, int aY, int aZ, int aSide) {
      byte tMeta = (byte)aIBlockAccess.func_72805_g(aX, aY, aZ);
      TileEntity tTileEntity = aIBlockAccess.func_72796_p(aX, aY, aZ);
      Icon rIcon = null;
      int tIndex = -1;
      if(tTileEntity == null) {
         tIndex = -2;
      } else if(tTileEntity instanceof GT_TileEntityMetaID_Machine) {
         tIndex = ((GT_TileEntityMetaID_Machine)tTileEntity).getTexture(aSide, tMeta);
      } else if(tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) {
         rIcon = ((IGregTechTileEntity)tTileEntity).getTextureIcon((byte)aSide, tMeta);
         if(rIcon == null) {
            tIndex = ((IGregTechTileEntity)tTileEntity).getTextureIndex((byte)aSide, tMeta);
         }
      } else {
         tIndex = -2;
      }

      if(rIcon == null) {
         if(tIndex >= 0 && tIndex < mIcons.length) {
            rIcon = mIcons[tIndex];
            if(rIcon == null) {
               System.err.println("GregTech: Missing Texture for index: " + tIndex);
            }
         } else if(tIndex != -2) {
            System.err.println("GregTech: Invalid Texture Index: " + tIndex);
         }
      }

      if(GT_Config.system || rIcon == null) {
         rIcon = GregTech_API.FAIL_ICON;
      }

      return rIcon;
   }

   public Icon func_71858_a(int aSide, int aMeta) {
      if(aMeta >= 0 && aMeta < 32766) {
         Icon rIcon = null;
         int tIndex = -1;

         try {
            if(aMeta > 0 && aMeta < 16) {
               tIndex = ((GT_TileEntityMetaID_Machine)this.createTileEntity((World)null, aMeta)).getTexture(aSide, aMeta);
            } else if(GregTech_API.mMetaTileList[aMeta] != null) {
               rIcon = GregTech_API.mMetaTileList[aMeta].getTextureIcon((byte)aSide, (byte)4, true, false);
               if(rIcon == null) {
                  tIndex = GregTech_API.mMetaTileList[aMeta].getTextureIndex((byte)aSide, (byte)4, true, false);
               }
            }
         } catch (Throwable var6) {
            tIndex = -2;
         }

         if(rIcon == null && tIndex >= 0 && tIndex < mIcons.length) {
            rIcon = mIcons[tIndex];
         }

         if(GT_Config.system || rIcon == null) {
            rIcon = GregTech_API.FAIL_ICON;
         }

         return rIcon;
      } else {
         return GregTech_API.FAIL_ICON;
      }
   }

   public float func_71934_m(World aWorld, int aX, int aY, int aZ) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      return tTileEntity instanceof BaseMetaTileEntity && ((BaseMetaTileEntity)tTileEntity).unbreakable()?-1.0F:super.func_71934_m(aWorld, aX, aY, aZ);
   }

   public float func_71908_a(EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      if(tTileEntity instanceof BaseMetaTileEntity) {
         if(((BaseMetaTileEntity)tTileEntity).privateAccess()) {
            return -1.0F;
         }

         if(((BaseMetaTileEntity)tTileEntity).unbreakable()) {
            return -1.0F;
         }
      }

      return tTileEntity instanceof GT_TileEntityMetaID_Machine && ((GT_TileEntityMetaID_Machine)tTileEntity).ownerControl()?-1.0F:super.func_71908_a(aPlayer, aWorld, aX, aY, aZ);
   }

   public boolean func_71903_a(World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer, int aSide, float par1, float par2, float par3) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      if(tTileEntity != null && !aPlayer.func_70093_af()) {
         if(!aWorld.field_72995_K && tTileEntity instanceof GT_TileEntityMetaID_Machine) {
            if(((GT_TileEntityMetaID_Machine)tTileEntity).func_70300_a(aPlayer)) {
               ((GT_TileEntityMetaID_Machine)tTileEntity).openGUI(aPlayer, aWorld.func_72805_g(aX, aY, aZ));
            }

            return true;
         } else {
            return tTileEntity instanceof IGregTechTileEntity?(((IGregTechTileEntity)tTileEntity).getTimer() < 50L?false:(!aWorld.field_72995_K && !((IGregTechTileEntity)tTileEntity).func_70300_a(aPlayer)?true:((IGregTechTileEntity)tTileEntity).onRightclick(aPlayer, (byte)aSide, par1, par2, par3))):false;
         }
      } else {
         return false;
      }
   }

   public void func_71921_a(World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      if(tTileEntity != null && tTileEntity instanceof IGregTechTileEntity) {
         ((IGregTechTileEntity)tTileEntity).onLeftclick(aPlayer);
      }

   }

   public int func_71873_h(World aWorld, int aX, int aY, int aZ) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      return tTileEntity != null && tTileEntity instanceof IGregTechTileEntity?((IGregTechTileEntity)tTileEntity).getMetaTileID():0;
   }

   public ArrayList<ItemStack> getBlockDropped(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
      ArrayList ret = new ArrayList();
      ret.add(GregTech_API.getGregTechComponent(22, 1));
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      return tTileEntity == null?ret:ret;
   }

   public void func_71852_a(World aWorld, int aX, int aY, int aZ, int par5, int par6) {
      dropItems(aWorld, aX, aY, aZ);
      GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
      super.func_71852_a(aWorld, aX, aY, aZ, par5, par6);
   }

   private static void dropItems(World aWorld, int aX, int aY, int aZ) {
      Random rand = new Random();
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      if(tTileEntity != null) {
         if(tTileEntity instanceof IGregTechTileEntity) {
            IGregTechTileEntity tGregTechTileEntity = (IGregTechTileEntity)tTileEntity;

            for(int i = 0; i < tGregTechTileEntity.func_70302_i_(); ++i) {
               ItemStack item = tGregTechTileEntity.func_70301_a(i);
               if(item != null && item.field_77994_a > 0 && tGregTechTileEntity.isValidSlot(i)) {
                  float rx = rand.nextFloat() * 0.8F + 0.1F;
                  float ry = rand.nextFloat() * 0.8F + 0.1F;
                  float rz = rand.nextFloat() * 0.8F + 0.1F;
                  EntityItem entityItem = new EntityItem(aWorld, (double)((float)aX + rx), (double)((float)aY + ry), (double)((float)aZ + rz), new ItemStack(item.func_77973_b(), item.field_77994_a, item.func_77960_j()));
                  if(item.func_77942_o()) {
                     entityItem.func_92059_d().func_77982_d((NBTTagCompound)item.func_77978_p().func_74737_b());
                  }

                  float factor = 0.05F;
                  entityItem.field_70159_w = rand.nextGaussian() * (double)factor;
                  entityItem.field_70181_x = rand.nextGaussian() * (double)factor + 0.20000000298023224D;
                  entityItem.field_70179_y = rand.nextGaussian() * (double)factor;
                  aWorld.func_72838_d(entityItem);
                  item.field_77994_a = 0;
                  tGregTechTileEntity.func_70299_a(i, (ItemStack)null);
               }
            }
         }

      }
   }

   public boolean func_96468_q_() {
      return true;
   }

   public int func_94328_b_(World aWorld, int aX, int aY, int aZ, int aSide) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      return tTileEntity != null && tTileEntity instanceof IGregTechTileEntity?((IGregTechTileEntity)tTileEntity).getComparatorValue((byte)aSide):0;
   }

   public int func_71865_a(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
      if(aSide >= 0 && aSide <= 5) {
         TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
         return tTileEntity != null && tTileEntity instanceof IGregTechTileEntity?((IGregTechTileEntity)tTileEntity).getOutputRedstoneSignal(GT_Utility.getOppositeSide(aSide)):0;
      } else {
         return 0;
      }
   }

   public int func_71855_c(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
      if(aSide >= 0 && aSide <= 5) {
         TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
         return tTileEntity != null && tTileEntity instanceof IGregTechTileEntity?((IGregTechTileEntity)tTileEntity).getStrongOutputRedstoneSignal(GT_Utility.getOppositeSide(aSide)):0;
      } else {
         return 0;
      }
   }

   public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
      TileEntity tTileEntity = world.func_72796_p(x, y, z);
      if(GregTech_API.sMachineNonWrenchExplosions && tTileEntity != null && (player == null || !player.field_71075_bZ.field_75098_d)) {
         if(tTileEntity instanceof GT_TileEntityMetaID_Machine) {
            ((GT_TileEntityMetaID_Machine)tTileEntity).doEnergyExplosion();
         } else if(tTileEntity instanceof BaseMetaTileEntity) {
            ((BaseMetaTileEntity)tTileEntity).doEnergyExplosion();
         }
      }

      return world.func_72832_d(x, y, z, 0, 0, 3);
   }

   public void func_71914_a(World aWorld, int aX, int aY, int aZ, int par5, float chance, int par7) {
      if(!aWorld.field_72995_K && GregTech_API.sMachineNonWrenchExplosions) {
         TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
         if(tTileEntity != null && chance < 1.0F) {
            if(tTileEntity instanceof GT_TileEntityMetaID_Machine) {
               ((GT_TileEntityMetaID_Machine)tTileEntity).doEnergyExplosion();
            } else if(tTileEntity instanceof BaseMetaTileEntity) {
               ((BaseMetaTileEntity)tTileEntity).doEnergyExplosion();
            }
         } else {
            super.func_71914_a(aWorld, aX, aY, aZ, par5, chance, par7);
         }
      }

   }

   public boolean canConnectRedstone(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return true;
   }

   public boolean func_71853_i() {
      return true;
   }

   public boolean func_71886_c() {
      return false;
   }

   public float func_71870_f(IBlockAccess aWorld, int aX, int aY, int aZ) {
      return 0.0F;
   }

   public boolean isBlockSolidOnSide(World aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
      if(aWorld.func_72805_g(aX, aY, aZ) == 0) {
         return true;
      } else {
         TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
         if(tTileEntity != null) {
            if(tTileEntity instanceof BaseMetaPipeEntity && (((BaseMetaPipeEntity)tTileEntity).mConnections & -64) != 0) {
               return true;
            }

            if(tTileEntity instanceof ICoverable && ((ICoverable)tTileEntity).getCoverIDAtSide((byte)aSide.ordinal()) != 0) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean isBlockNormalCube(World aWorld, int aX, int aY, int aZ) {
      return false;
   }

   public int getLightOpacity(World aWorld, int aX, int aY, int aZ) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      return tTileEntity == null?0:(tTileEntity instanceof BaseMetaTileEntity?(((BaseMetaTileEntity)tTileEntity).getLightValue() == 0?255:0):(aWorld.func_72805_g(aX, aY, aZ) == 0?255:0));
   }

   public int getLightValue(IBlockAccess aWorld, int aX, int aY, int aZ) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      return tTileEntity != null && tTileEntity instanceof BaseMetaTileEntity?((BaseMetaTileEntity)tTileEntity).getLightValue():0;
   }

   public boolean func_71926_d() {
      return false;
   }

   public int func_71857_b() {
      return GT_Block_Renderer.instance == null?super.func_71857_b():GT_Block_Renderer.instance.mRenderID;
   }

   public boolean canBeReplacedByLeaves(World aWorld, int aX, int aY, int aZ) {
      return false;
   }

   public TileEntity func_72274_a(World aWorld) {
      return GregTech_API.constructBaseMetaTileEntity();
   }

   public TileEntity createTileEntity(World aWorld, int aMeta) {
      switch(aMeta) {
      case 0:
         return GregTech_API.constructBaseMetaTileEntity();
      case 1:
         return new BaseMetaPipeEntity();
      case 2:
         return new BaseMetaPipeEntity();
      case 3:
         return new BaseMetaPipeEntity();
      case 4:
         return new GT_TileEntity_ComputerCube();
      case 5:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      default:
         return null;
      case 6:
         return new GT_TileEntity_Sonictron();
      case 12:
         return new GT_TileEntity_Superconductor();
      case 13:
         return new GT_TileEntity_PlayerDetector();
      }
   }

   public float getExplosionResistance(Entity par1Entity, World aWorld, int aX, int aY, int aZ, double explosionX, double explosionY, double explosionZ) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      return tTileEntity != null && tTileEntity instanceof IGregTechTileEntity?((IGregTechTileEntity)tTileEntity).getBlastResistance((byte)6):10.0F;
   }

   @SideOnly(Side.CLIENT)
   public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List) {
      for(int i = 4; i < GregTech_API.mMetaTileList.length; ++i) {
         if(i < 16 && i != 13 && this.createTileEntity((World)null, i) != null) {
            par3List.add(new ItemStack(par1, 1, i));
         } else if(i > 15 && GregTech_API.mMetaTileList[i] != null) {
            par3List.add(new ItemStack(par1, 1, i));
         }
      }

   }

   public boolean canCreatureSpawn(EnumCreatureType type, World aWorld, int aX, int aY, int aZ) {
      return false;
   }

   public void func_71860_a(World aWorld, int aX, int aY, int aZ, EntityLivingBase aPlayer, ItemStack aStack) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      if(tTileEntity != null) {
         if(tTileEntity instanceof IGregTechTileEntity) {
            IGregTechTileEntity var6 = (IGregTechTileEntity)tTileEntity;
            if(aPlayer == null) {
               var6.setFrontFacing((byte)1);
            } else {
               int var7 = MathHelper.func_76128_c((double)(aPlayer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
               int var8 = Math.round(aPlayer.field_70125_A);
               if(var8 >= 65 && var6.isValidFacing((byte)1)) {
                  var6.setFrontFacing((byte)1);
               } else if(var8 <= -65 && var6.isValidFacing((byte)0)) {
                  var6.setFrontFacing((byte)0);
               } else {
                  switch(var7) {
                  case 0:
                     var6.setFrontFacing((byte)2);
                     break;
                  case 1:
                     var6.setFrontFacing((byte)5);
                     break;
                  case 2:
                     var6.setFrontFacing((byte)3);
                     break;
                  case 3:
                     var6.setFrontFacing((byte)4);
                  }
               }
            }
         }

      }
   }

   public void func_71861_g(World aWorld, int aX, int aY, int aZ) {
      if(GregTech_API.isMachineBlock(this, aWorld.func_72805_g(aX, aY, aZ))) {
         GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
      }

   }

   public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aX, int aY, int aZ, int aLogLevel) {
      TileEntity tTileEntity = aPlayer.field_70170_p.func_72796_p(aX, aY, aZ);
      return tTileEntity == null?null:(tTileEntity instanceof GT_TileEntityMetaID_Machine?((GT_TileEntityMetaID_Machine)tTileEntity).getDebugInfo(aPlayer, aLogLevel):(tTileEntity instanceof BaseMetaTileEntity?((BaseMetaTileEntity)tTileEntity).getDebugInfo(aPlayer, aLogLevel):(tTileEntity instanceof BaseMetaPipeEntity?((BaseMetaPipeEntity)tTileEntity).getDebugInfo(aPlayer, aLogLevel):null)));
   }

   public int func_71920_b(IBlockAccess aWorld, int aX, int aY, int aZ) {
      if(aWorld == null) {
         return super.func_71920_b(aWorld, aX, aY, aZ);
      } else {
         TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
         if(tTileEntity == null) {
            return super.func_71920_b(aWorld, aX, aY, aZ);
         } else {
            if(tTileEntity instanceof IGregTechTileEntity) {
               byte tColor = ((IGregTechTileEntity)tTileEntity).getColorization();
               if(tColor >= 0 && tColor < ItemDye.field_77859_b.length) {
                  return ItemDye.field_77859_b[tColor];
               }
            }

            return 16777215;
         }
      }
   }

   public boolean recolourBlock(World aWorld, int aX, int aY, int aZ, ForgeDirection aSide, int aColor) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      if(tTileEntity instanceof IGregTechTileEntity) {
         byte tStartColor = ((IGregTechTileEntity)tTileEntity).getColorization();
         if(tStartColor == -1) {
            ((IGregTechTileEntity)tTileEntity).setColorization((byte)(~aColor & 15));
            return true;
         }

         if(tStartColor == -2) {
            ((IGregTechTileEntity)tTileEntity).setColorization((byte)(16 | ~aColor & 15));
            return true;
         }

         if(tStartColor >= 0 && (tStartColor & 15) != (~aColor & 15)) {
            ((IGregTechTileEntity)tTileEntity).setColorization((byte)(tStartColor & -16 | ~aColor & 15));
            return true;
         }
      }

      return false;
   }

   public void onNeighborTileChange(World aWorld, int aX, int aY, int aZ, int aTileX, int aTileY, int aTileZ) {
      TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
      if(tTileEntity instanceof BaseTileEntity) {
         ((BaseTileEntity)tTileEntity).onAdjacentBlockChange(aTileX, aTileY, aTileZ);
      }

   }

   public int getFlammability(IBlockAccess aWorld, int aX, int aY, int aZ, int metadata, ForgeDirection face) {
      return 0;
   }

   public boolean isFlammable(IBlockAccess aWorld, int aX, int aY, int aZ, int metadata, ForgeDirection face) {
      return GregTech_API.sMachineFlammable && aWorld.func_72805_g(aX, aY, aZ) == 0;
   }

   public int getFireSpreadSpeed(World aWorld, int aX, int aY, int aZ, int metadata, ForgeDirection face) {
      return GregTech_API.sMachineFlammable && aWorld.func_72805_g(aX, aY, aZ) == 0?100:0;
   }

   public boolean isFireSource(World aWorld, int aX, int aY, int aZ, int metadata, ForgeDirection side) {
      return GregTech_API.sMachineFlammable && aWorld.func_72805_g(aX, aY, aZ) == 0;
   }

}
