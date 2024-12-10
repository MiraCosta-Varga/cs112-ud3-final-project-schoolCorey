package cs112.ud3.Exceptions;

/**
 * Thrown if the user tries to create a card which does not actually exist in the game.
 * Can be given a boxNumber int param in constructor to inform the user which
 * box for card reward input has the issue.
 */
public class CardNotValidException extends Exception{
    public static final String DEFAULT_MESSAGE = "Card not found in-game; May be null or misspelled.";
    public CardNotValidException(){super(DEFAULT_MESSAGE);}
    public CardNotValidException(int boxNumber) {
        super(String.format("Card in box %d not found in-game; May be null or misspelled.",boxNumber));
    }
}
