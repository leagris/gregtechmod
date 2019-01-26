package gregtechmod.api.interfaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Config;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

public interface IMetaTileEntity extends ISidedInventory, IFluidTank, IFluidHandler {

   byte getTileEntityBaseType();

   IMetaTileEntity newMetaEntity(IGregTechTileEntity var1);

   ItemStack getStackForm(long var1);

   void setBaseMetaTileEntity(IGregTechTileEntity var1);

   IGregTechTileEntity getBaseMetaTileEntity();

   void initDefaultModes(NBTTagCompound var1);

   void saveNBTData(NBTTagCompound var1);

   void loadNBTData(NBTTagCompound var1);

   void setItemNBT(NBTTagCompound var1);

   void onServerStart();

   void onFirstServerTick();

   void onServerStop();

   void onConfigLoad(GT_Config var1);

   boolean allowCoverOnSide(byte var1, int var2);

   void onScrewdriverRightClick(byte var1, EntityPlayer var2, float var3, float var4, float var5);

   void onExplosion();

   void onFirstTick();

   void onPreTick();

   void onPostTick();

   void inValidate();

   void onRemoval();

   boolean isFacingValid(byte var1);

   boolean allowPullStack(int var1, byte var2, ItemStack var3);

   boolean allowPutStack(int var1, byte var2, ItemStack var3);

   boolean isValidSlot(int var1);

   boolean setStackToZeroInsteadOfNull(int var1);

   boolean isLiquidInput(byte var1);

   boolean isLiquidOutput(byte var1);

   String getMetaName();

   boolean isAccessAllowed(EntityPlayer var1);

   boolean isWrenchable();

   void onMachineBlockUpdate();

   boolean onRightclick(EntityPlayer var1, byte var2, float var3, float var4, float var5);

   void onLeftclick(EntityPlayer var1);

   void onValueUpdate(byte var1);

   byte getUpdateData();

   void receiveClientEvent(byte var1, byte var2);

   void doSound(byte var1, double var2, double var4, double var6);

   void startSoundLoop(byte var1, double var2, double var4, double var6);

   void stopSoundLoop(byte var1, double var2, double var4, double var6);

   void sendSound(byte var1);

   void sendLoopStart(byte var1);

   void sendLoopEnd(byte var1);

   boolean isSimpleMachine();

   boolean doTickProfilingMessageDuringThisTick();

   ArrayList<String> getSpecialDebugInfo(EntityPlayer var1, int var2, ArrayList<String> var3);

   String getDescription();

   String getSpecialVoltageToolTip();

   int getTextureIndex(byte var1, byte var2, boolean var3, boolean var4);

   Icon getTextureIcon(byte var1, byte var2, boolean var3, boolean var4);

   @SideOnly(Side.CLIENT)
   void registerIcons(IconRegister var1);

   byte getComparatorValue(byte var1);

   float getExplosionResistance(byte var1);

   String func_70303_b();

   String[] getInfoData();

   boolean isGivingInformation();

   ItemStack[] getRealInventory();

   boolean connectsToItemPipe(byte var1);
}
