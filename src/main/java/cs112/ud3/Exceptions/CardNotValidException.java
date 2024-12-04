package cs112.ud3.Exceptions;

public class CardNotValidException extends Exception{
    public static final String DEFAULT_MESSAGE = "Card not found in-game; May be null or misspelled.";
    public CardNotValidException(){super(DEFAULT_MESSAGE);}
    public CardNotValidException(int boxNumber) {
        super(String.format("Card in box %d not found in-game; May be null or misspelled.",boxNumber));
    }
}
