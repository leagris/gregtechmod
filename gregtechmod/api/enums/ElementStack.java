package gregtechmod.api.enums;

import gregtechmod.api.enums.Element;

public class ElementStack implements Cloneable {

   public int mAmount;
   public Element mElement;


   public ElementStack(Element aElement, int aAmount) {
      this.mElement = aElement == null?Element._NULL:aElement;
      this.mAmount = aAmount;
   }

   public ElementStack copy(int aAmount) {
      return new ElementStack(this.mElement, aAmount);
   }

   public ElementStack clone() {
      return new ElementStack(this.mElement, this.mAmount);
   }

   public boolean equals(Object aObject) {
      return aObject == this?true:(aObject == null?false:(aObject instanceof Element?aObject == this.mElement:(!(aObject instanceof ElementStack)?false:((ElementStack)aObject).mElement == this.mElement && (this.mAmount < 0 || ((ElementStack)aObject).mAmount < 0 || ((ElementStack)aObject).mAmount == this.mAmount))));
   }

   public String toString() {
      return this.mElement.toString() + this.mAmount;
   }

   public int hashCode() {
      return this.mElement.hashCode();
   }
}
