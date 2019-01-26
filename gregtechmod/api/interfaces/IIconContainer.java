package gregtechmod.api.interfaces;

import net.minecraft.util.Icon;

public interface IIconContainer {

   Icon getIcon();

   Icon getOverlayIcon();

   int getOverlayX();

   int getOverlayY();

   int getOverlayWidth();

   int getOverlayHeight();
}
