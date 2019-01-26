package gregtechmod.common.tileentities.deprecated;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class GT_TileEntity_LightSource extends TileEntity {

   private int mTickTimer = 0;


   public void func_70316_g() {
      if(!this.field_70331_k.field_72995_K) {
         Iterator tIterator = this.field_70331_k.field_73010_i.iterator();
         boolean temp = true;
         if(++this.mTickTimer % 20 == 0) {
            while(tIterator.hasNext() && temp) {
               EntityPlayer tPlayer = (EntityPlayer)tIterator.next();
               if(MathHelper.func_76128_c(tPlayer.field_70165_t) == this.field_70329_l && MathHelper.func_76128_c(tPlayer.field_70163_u + 1.0D) == this.field_70330_m && MathHelper.func_76128_c(tPlayer.field_70161_v) == this.field_70327_n) {
                  temp = false;
               }
            }

            if(temp) {
               this.field_70331_k.func_94571_i(this.field_70329_l, this.field_70330_m, this.field_70327_n);
            }
         }

      }
   }
}
