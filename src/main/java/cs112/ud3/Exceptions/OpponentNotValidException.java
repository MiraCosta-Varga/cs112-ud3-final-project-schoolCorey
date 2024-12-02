package cs112.ud3.Exceptions;

public class OpponentNotValidException extends Exception {
    public static final String DEFAULT_MESSAGE = "Opponent of selected name does not exist at selected location in-game. Try double-checking your selections.";
    public OpponentNotValidException(){
        super(DEFAULT_MESSAGE);
    }
}
