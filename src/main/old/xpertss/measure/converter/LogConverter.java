package xpertss.measure.converter;

import java.math.BigDecimal;

/**
 * <p> This class represents a logarithmic converter. Such converter
 * is typically used to create logarithmic unit. For example:[code]
 * Unit<Dimensionless> BEL = Unit.ONE.transform(new LogConverter(10).inverse());
 * [/code]</p>
 * <p/>
 * <p> Instances of this class are immutable.</p>
 *
 * TODO This has lots of work to do Need to check for identity when parsing concat
 */
public final class LogConverter extends UnitConverter {

   private InverseLogConverter _inverse = new InverseLogConverter();



   /**
    * Holds the logarithmic base.
    */
   private final BigDecimal _base;

   /**
    * Holds the natural logarithm of the base.
    */
   private final BigDecimal _logBase;

   /**
    * Holds the inverse of the natural logarithm of the base.
    */
   private final BigDecimal _invLogBase;




   /**
    * Creates a logarithmic converter having the specified base.
    *
    * @param base the logarithmic base (e.g. <code>Math.E</code> for
    *             the Natural Logarithm).
    */
   public LogConverter(BigDecimal base)
   {
      _base = base;

      // TODO Can't do this here
      _logBase = new BigDecimal(Math.log(base.doubleValue())); // TODO Do I lose precision here?
      _invLogBase = BigDecimal.ONE.divide(_logBase);
   }





   /**
    * Returns the logarithmic base of this converter.
    *
    * @return the logarithmic base (e.g. <code>Math.E</code> for
    *         the Natural Logarithm).
    */
   public BigDecimal getBase()
   {
      return _base;
   }

   @Override
   public UnitConverter inverse()
   {
      return _inverse;
   }



   @Override
   public BigDecimal convert(BigDecimal amount)
   {
      // TODO Replace with BigMath.ln()
      return _invLogBase.multiply(new BigDecimal(Math.log(amount.doubleValue())));
   }

   @Override
   public boolean isLinear()
   {
      return false;
   }


   public boolean equals(Object obj)
   {
      if(obj == this) return true;
      if(obj instanceof LogConverter) {
         LogConverter cvtr = (LogConverter) obj;
         return _base.equals(cvtr._base);
      }
      return false;
   }

   public int hashCode()
   {
      return _base.hashCode();
   }

   

   /**
    * This inner class represents the inverse of the logarithmic converter
    * (exponentiation converter).
    */
   private class InverseLogConverter extends UnitConverter {


      public BigDecimal getBase()
      {
         return LogConverter.this.getBase();
      }


      @Override
      public UnitConverter inverse()
      {
         return LogConverter.this;
      }

      @Override
      public BigDecimal convert(BigDecimal amount)
      {
         // TODO Replace with BigMath.exp
         return new BigDecimal(Math.exp(_logBase.multiply(amount).doubleValue()));
      }

      @Override
      public boolean isLinear()
      {
         return false;
      }

      public boolean equals(Object obj)
      {
         if(obj == this) return true;
         if(obj instanceof InverseLogConverter) {
            InverseLogConverter cvtr = (InverseLogConverter) obj;
            return _base.equals(cvtr.getBase());
         }
         return false;
      }

      public int hashCode()
      {
         return _base.hashCode();
      }

      private static final long serialVersionUID = 1L;
   }

   private static final long serialVersionUID = 1L;
}