package gregtechmod.api.enums;

import gregtechmod.api.enums.Materials;

public class MaterialStack implements Cloneable {

   public long mAmount;
   public Materials mMaterial;


   public MaterialStack(Materials aMaterial, long aAmount) {
      this.mMaterial = aMaterial == null?Materials._NULL:aMaterial;
      this.mAmount = aAmount;
   }

   public MaterialStack copy(long aAmount) {
      return new MaterialStack(this.mMaterial, aAmount);
   }

   public MaterialStack clone() {
      return new MaterialStack(this.mMaterial, this.mAmount);
   }

   public boolean equals(Object aObject) {
      return aObject == this?true:(aObject == null?false:(aObject instanceof Materials?aObject == this.mMaterial:(!(aObject instanceof MaterialStack)?false:((MaterialStack)aObject).mMaterial == this.mMaterial && (this.mAmount < 0L || ((MaterialStack)aObject).mAmount < 0L || ((MaterialStack)aObject).mAmount == this.mAmount))));
   }

   public String toString() {
      return (this.mMaterial.mMaterialList.size() > 1 && this.mAmount > 1L?"(":"") + this.mMaterial.getToolTip(true) + (this.mMaterial.mMaterialList.size() > 1 && this.mAmount > 1L?")":"") + (this.mAmount > 1L?Long.valueOf(this.mAmount):"");
   }

   public int hashCode() {
      return this.mMaterial.hashCode();
   }
}
