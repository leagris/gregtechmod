package gregtechmod.api.interfaces;


public interface ITurnable {

   byte getFrontFacing();

   byte getBackFacing();

   boolean isValidFacing(byte var1);

   void setFrontFacing(byte var1);
}
