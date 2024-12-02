package cs112.ud3.Exceptions;

public class UninitializedLinkException extends Exception{
    public static final String DEFAULT_MESSAGE = "Valid array not yet created. Please call createValidArray() before checking for validity of objects.";

    public UninitializedLinkException(){
        super(DEFAULT_MESSAGE);
    }
}
