package gregtechmod.api.interfaces;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;

public interface IDebugableBlock {

   ArrayList<String> getDebugInfo(EntityPlayer var1, int var2, int var3, int var4, int var5);
}
