package xpertss.measure.converter;

/**
 * Signals that a problem of some sort has occurred either when creating a
 * converter between two units or during the conversion itself.
 */
public class ConversionException extends RuntimeException {

   /**
    * Constructs a <code>ConversionException</code> with no detail message.
    */
   public ConversionException()
   {
      super();
   }

   /**
    * Constructs a <code>ConversionException</code> with the specified detail
    * message.
    *
    * @param message the detail message.
    */
   public ConversionException(String message)
   {
      super(message);
   }

   private static final long serialVersionUID = 1L;
}