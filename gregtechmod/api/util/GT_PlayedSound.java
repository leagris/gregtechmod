package gregtechmod.api.util;


public class GT_PlayedSound {

   public final String mSoundName;
   public final int mX;
   public final int mY;
   public final int mZ;


   public GT_PlayedSound(String aSoundName, double aX, double aY, double aZ) {
      this.mSoundName = aSoundName == null?"":aSoundName;
      this.mX = (int)aX;
      this.mY = (int)aY;
      this.mZ = (int)aZ;
   }

   public boolean equals(Object aObject) {
      return aObject != null && aObject instanceof GT_PlayedSound?((GT_PlayedSound)aObject).mX == this.mX && ((GT_PlayedSound)aObject).mY == this.mY && ((GT_PlayedSound)aObject).mZ == this.mZ && ((GT_PlayedSound)aObject).mSoundName.equals(this.mSoundName):false;
   }

   public int hashCode() {
      return this.mX + this.mY + this.mZ + this.mSoundName.hashCode();
   }
}
