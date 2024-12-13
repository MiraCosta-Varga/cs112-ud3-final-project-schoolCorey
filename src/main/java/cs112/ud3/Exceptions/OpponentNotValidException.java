package cs112.ud3.Exceptions;

/**
 * Thrown if the user tries to create a DMOpponent that does not actually exist in game,
 * such as one which has an invalid combination of name and location instance variables.
 */
public class OpponentNotValidException extends Exception {
    public static final String DEFAULT_MESSAGE = "Opponent of selected name does not exist at selected location in-game.\nTry double-checking your selections.";
    public OpponentNotValidException(){
        super(DEFAULT_MESSAGE);
    }
    public OpponentNotValidException(String message){super(message);}
}
