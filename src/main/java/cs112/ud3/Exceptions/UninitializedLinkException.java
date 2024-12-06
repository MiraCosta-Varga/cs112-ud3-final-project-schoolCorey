package cs112.ud3.Exceptions;

/**
 * Thrown if a ValidLink class tries to call a method that depends on its valid arrays before
 * createValidArray() is called.
 */
public class UninitializedLinkException extends Exception{
    public static final String DEFAULT_MESSAGE = "Valid array not yet created. Please call createValidArray() before checking for validity of objects.";

    public UninitializedLinkException(){
        super(DEFAULT_MESSAGE);
    }
}
