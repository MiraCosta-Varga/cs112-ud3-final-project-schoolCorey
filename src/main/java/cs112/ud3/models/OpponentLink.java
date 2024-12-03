package cs112.ud3.models;

import cs112.ud3.Exceptions.OpponentNotValidException;
import cs112.ud3.Exceptions.UninitializedLinkException;

public class OpponentLink extends CardLink{
    public static DMOpponent[] knownOpponents;

    @Override
    public void createValidArray() {
        knownOpponents = new DMOpponent[]{
                new DMOpponent("Craig", "Card Shop"),
                new DMOpponent("Sean", "Card Shop"),
                new DMOpponent("Hooper", "Card Shop"),
                new DMOpponent("Lynne", "Junkyard"),
                new DMOpponent("Ice Cream", "Junkyard"),
                new DMOpponent("Simon", "Junkyard"),
                new DMOpponent("Sherisa", "Junkyard"),
                new DMOpponent("Lucy", "Restaurant"),
                new DMOpponent("Quinn", "Restaurant"),
                new DMOpponent("Mandy", "Restaurant"),
                new DMOpponent("Timmy", "Park"),
                new DMOpponent("Jennifer", "Park"),
                new DMOpponent("Skylar", "Park"),
                new DMOpponent("Finch", "School"),
                new DMOpponent("Laura", "School"),
                new DMOpponent("Raquel", "School"),
                new DMOpponent("Patrick", "Mall"),
                new DMOpponent("Ernie", "Mall"),
                new DMOpponent("Bruce", "Mall"),
                new DMOpponent("Anissa", "Mall"),
                new DMOpponent("Robin", "Power Plant"),
                new DMOpponent("Karl", "Power Plant"),
                new DMOpponent("Mikey", "Power Plant"),
                new DMOpponent("Johnny", "Power Plant"),
                new DMOpponent("Jodi", "Power Plant"),
                new DMOpponent("Geoff", "Museum"),
                new DMOpponent("Monika", "Museum"),
                new DMOpponent("Jamie", "Museum"),
                new DMOpponent("Downey", "Library"),
                new DMOpponent("Evan", "Library"),
                new DMOpponent("Duncan", "Library"),
                new DMOpponent("Nathan", "Forest Preserve"),
                new DMOpponent("Adam", "Forest Preserve"),
                new DMOpponent("Abagail", "Forest Preserve"),
                new DMOpponent("Angela", "Forest Preserve"),
        };
    }

    @Override
    public boolean objectIsValid(Object obj)throws UninitializedLinkException {
        if(knownOpponents == null){
            throw new UninitializedLinkException();
        }
        if((obj == null )||! (obj instanceof DMOpponent)){
            return false;
        }
        DMOpponent candidate = (DMOpponent) obj;
        if(opponentIsValid(candidate)==-1){
            return false;
        }else return true;
    }

    /**
     * Checks if the given opponent is a valid version of the DMOpponent object (Corresponds to and opponent that actaully exists
     * in the PS2 Duel Masters game) by checking it against the knownOpponents array. That array must already exist.
     * @param candidate The DMOpponent object to check the validity of
     * @return The location in the knownOpponents index that has the corresponding opponent. Returns -1 if not found.
     * @throws UninitializedLinkException thrown if the knownOpponents array was never created or was deleted before this was called.
     */
    private int opponentIsValid(DMOpponent candidate) throws UninitializedLinkException{
        if (knownOpponents==null){
            throw new UninitializedLinkException();
        }
        int location = 0;
        boolean isPresent = false;

        while (!isPresent && (location < knownOpponents.length)) {
            isPresent = knownOpponents[location].equals(candidate);
            if(!isPresent){
                location++;
            }
        }
        if(!isPresent) location = -1;
        return location;
    }

    /**
     * Takes name and location, and gives the object in the knownOpponents array that corresponds to them.
     * @param name name of the character
     * @param location location the character can be fought at
     * @return the DMOpponent in the knownOpponents array which has the provided name and location
     * @throws OpponentNotValidException if there is no opponent in the DMOpponents array with the given combination of name and location
     * @throws UninitializedLinkException if the knownOpponents array has not been created or has been deleted before this was called.
     */
    public DMOpponent linkOpponent(String name, String location) throws OpponentNotValidException, UninitializedLinkException{

        try{
            DMOpponent opponent = new DMOpponent(name,location);
            int opponentIndex = opponentIsValid(opponent);
            if(opponentIndex==-1){
                throw new OpponentNotValidException();
            }else {
                return knownOpponents[opponentIndex];
            }
        }catch (UninitializedLinkException ule){
            System.err.println(ule.getMessage());
            return null;
        }catch (IllegalArgumentException iae){
            throw new OpponentNotValidException("Opponent not valid due to invalid name or location");
        }

    }

}
