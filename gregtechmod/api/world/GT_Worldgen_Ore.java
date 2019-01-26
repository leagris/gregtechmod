package gregtechmod.api.world;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.world.GT_Worldgen;
import java.util.ArrayList;
import java.util.Collection;

public abstract class GT_Worldgen_Ore extends GT_Worldgen {

   public final int mBlockMeta;
   public final int mBlockID;
   public final int mAmount;
   public final int mSize;
   public final int mMinY;
   public final int mMaxY;
   public final int mProbability;
   public final int mDimensionType;
   public final Collection<String> mBiomeList = new ArrayList();
   public final boolean mAllowToGenerateinVoid;


   public GT_Worldgen_Ore(String aName, boolean aDefault, int aBlockID, int aBlockMeta, int aDimensionType, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid) {
      super(aName, aDefault);
      this.mBlockID = Math.min(Math.max(aBlockID, 0), 4095);
      this.mBlockMeta = Math.min(Math.max(aBlockMeta, 0), 15);
      this.mDimensionType = aDimensionType;
      this.mProbability = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "Probability", aProbability);
      this.mAmount = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "Amount", aAmount);
      this.mSize = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "Size", aSize);
      this.mMinY = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "MinHeight", aMinY);
      this.mMaxY = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "MaxHeight", aMaxY);
      if(aBiomeList != null) {
         this.mBiomeList.addAll(aBiomeList);
      }

      this.mAllowToGenerateinVoid = aAllowToGenerateinVoid;
   }
}
