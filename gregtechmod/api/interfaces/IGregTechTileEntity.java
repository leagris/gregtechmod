package gregtechmod.api.interfaces;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IDescribable;
import gregtechmod.api.interfaces.IDigitalChest;
import gregtechmod.api.interfaces.IGearEnergyTileEntity;
import gregtechmod.api.interfaces.IGregTechDeviceInformation;
import gregtechmod.api.interfaces.IMachineBlockUpdateable;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.interfaces.ITurnable;
import gregtechmod.api.interfaces.IUpgradableMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.IFluidHandler;

public interface IGregTechTileEntity extends IGearEnergyTileEntity, ICoverable, IFluidHandler, ITurnable, IGregTechDeviceInformation, IUpgradableMachine, IDigitalChest, IDescribable, IMachineBlockUpdateable {

   int getErrorDisplayID();

   void setErrorDisplayID(int var1);

   int getMetaTileID();

   int setMetaTileID(short var1);

   IMetaTileEntity getMetaTileEntity();

   void setMetaTileEntity(IMetaTileEntity var1);

   void issueTextureUpdate();

   void issueClientUpdate();

   void doExplosion(int var1);

   void setOnFire();

   String setOwnerName(String var1);

   String getOwnerName();

   void setInitialValuesAsNBT(NBTTagCompound var1, short var2);

   byte getColorization();

   byte setColorization(byte var1);

   void onLeftclick(EntityPlayer var1);

   boolean onRightclick(EntityPlayer var1, byte var2, float var3, float var4, float var5);

   float getBlastResistance(byte var1);

   int getTextureIndex(byte var1, byte var2);

   Icon getTextureIcon(byte var1, byte var2);
}
