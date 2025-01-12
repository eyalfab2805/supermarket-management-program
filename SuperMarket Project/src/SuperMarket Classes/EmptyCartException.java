package TomBitran_Eyal_Fabian;

 //Exception thrown when an operation is attempted on an empty cart.
public class EmptyCartException extends Exception {

     // Constructs a new EmptyCartException with the specified detail message.
    public EmptyCartException(String message) {
        super(message);
    }
}