package com.theladders.bankkata.money;


public abstract class Type
{
   private static Type DEBIT = new DebitType();
   private static Type CREDIT = new CreditType();


   public static Type debit()
   {
     return DEBIT;
   }
   public static Type credit()
   {
     return CREDIT;
   }

   public static Type zeroType()
   {
     return DEBIT;
   }
   
   @Override
   public boolean equals(Object o)
   {
     if (!(o instanceof Type))
       return false;
     Type other = (Type) o;
     String otherIdentity = other.getIdentity();
     return getIdentity().equals(otherIdentity);
   }
   
   public abstract Type flip();
   protected abstract String getIdentity();

}
