package cs112.ud3.models;

/**
 * Class for an event that gives the player a reward. For the Duel Masters PS2 game that this program is intended for,
 * a Reward Event would be a duel. It gives the player currency in the form of reputation points, itemDrops in the form
 * of 3 Duel Masters cards, and has a specific opponent tied to the duel (the origin of the event). It is also possible that
 * the amount of reputation points or the card drop rates are effected by the stats of the duel,
 * which are recorded in rewardMods.
 */
public class RewardEvent {
    public static final String NOT_FOUND = "NOT FOUND";
    public static final int MAX_REWARDS_PER_EVENT = 3;
    public static final int DEFAULT_CURRENCY = 0;

    private int currency;
    private DMCard[] itemDrops = new DMCard[MAX_REWARDS_PER_EVENT];
    private DMBattleStats rewardMods;
    private DMOpponent origin;

    /***CONSTRUCTORS**/
    /**
     * Default constructor for RewardEvent object. currency to 0, objects to null, itemDrops exists but all items are null.
     */
    public RewardEvent(){
        this(DEFAULT_CURRENCY,null,null);
    }

    /**
     * Full constructor for RewardEvent object, except for itemDrops. Those should be added by addDrop after the
     * object is created.
     * @param currency the amount of currency (Reputation Points) gained from this event
     * @param rewardMods DMBattleStats object representing the stats of the duel, which may modify currency/card drop rates.
     * @param origin the DMOpponent that was fought during this event.
     * @throws IllegalArgumentException if one or more parameters were invalid.
     */
    public RewardEvent(int currency, DMBattleStats rewardMods, DMOpponent origin)throws IllegalArgumentException{
        if(!this.setAll(currency,rewardMods,origin)){
            throw new IllegalArgumentException("One or more parameters invalid, most likely negative currency.");
        }

    }

    /**
     * Copy Constructor for RewardEvent object.
     * @param original original rewardEvent to be copied. Must not be null
     * @throws IllegalArgumentException if original is null, or has invalid instance variables.
     */
    public RewardEvent(RewardEvent original)throws IllegalArgumentException{
        if(original==null){
            throw new IllegalArgumentException("Tried to copy null object");
        }
        if(!this.setAll(original.currency,original.rewardMods,original.origin,original.itemDrops)){
            throw new IllegalArgumentException("One or more issues with validity of original object. Could not copy.");
        }
    }

    /***MUTATORS***/

    /**
     * Setter for currency intance variable. For the Duel Masters PS2 game, this is Reputation points
     * @param currency the amount of currency (Reputation Points) gained from this event
     * @return true if param was valid and set; false if invalid and thus not set.
     */
    public boolean setCurrency(int currency) {
        if(currency<0){
            return false;
        }else {
            this.currency = currency;
            return true;
        }

    }

    /**
     * Adds a reward to a specified index in the itemDrops array.
     * @param drop The reward (DMCard) which was dropped to be added to this event
     * @param index The index of the itemDrops array the reward should be added to.
     * @return true if the index was null before the event was added, false if there was a reward already at that index which was overwritten.
     * @throws IllegalArgumentException If the reward trying to add is null, or index would be out of bounds of the array.
     */
    public boolean addDrop(DMCard drop, int index) throws IllegalArgumentException{
        if (drop == null || index < 0 || index > (MAX_REWARDS_PER_EVENT -1)){
            String message = String.format("Tried to add null reward or tried to add outside bounds of array.%nThere are %d rewards per event, so the index needs to be less than that.", MAX_REWARDS_PER_EVENT);
            throw new IllegalArgumentException(message);
        }else {
            boolean wasNotOverwritten = (itemDrops[index] == null);
            itemDrops[index] = drop;
            return wasNotOverwritten;
        }
    }

    //TODO: Make sure this (shallow copy) works with file i/o
    /**
     * Setter for itemDrops DMCard array instance variable. It deep copies the array, but only needs to
     * shallow copy the DMCards, as we only need to reference the attributes of each different card.
     * Should only be used by the copy constructor.
     * @param itemDrops the DMCard array to copy into this RewardEvent. null is valid, and will cause this
     *                  object's itemDrops array to become null.
     * @throws IllegalArgumentException if the param's size does not match the expected size of the itemDrops array.
     */
    public void setItemDrops (DMCard[] itemDrops) throws IllegalArgumentException{
        if(itemDrops==null){
            throw new IllegalArgumentException("attempted copy of itemDrops array is null");
        }else{
            if (itemDrops.length != this.itemDrops.length){
                String message = String.format("Array to set does not match proper size. Should have %d items.",MAX_REWARDS_PER_EVENT);
                throw new IllegalArgumentException(message);
            }else {
                for(int i=0; i<itemDrops.length; i++){
                    this.itemDrops[i] = itemDrops[i];
                }
            }
        }
    }

    /**
     * Setter for rewardMods instance variable. For the Duel Masters PS2 game, this is the information from the DuelStats screen.
     * @param rewardMods DMBattleStats object representing the stats of the duel, which may modify currency/card drop rates.
     */
    public void setRewardMods(DMBattleStats rewardMods){
        this.rewardMods = rewardMods;
    }

    /**
     * Setter for origin instance variable. For the Duel Masters PS2 game, this is the opponent you face in a duel.
     * @param origin the DMOpponent that was fought during this event.
     */
    public void setOrigin(DMOpponent origin){
        this.origin = origin;
    }

    /**
     * Setter for instance variables except for itemDrops, which should have items added individually by addDrop()
     * @param currency the amount of currency (Reputation Points) gained from this event
     * @param rewardMods DMBattleStats object representing the stats of the duel, which may modify currency/card drop rates.
     * @param origin the DMOpponent that was fought during this event.
     * @return true if all params were valid and thus set; false if one or more were invalid and not set
     */
    public boolean setAll(int currency, DMBattleStats rewardMods, DMOpponent origin){
        this.setRewardMods(rewardMods);
        this.setOrigin(origin);
        return this.setCurrency(currency);
    }

    /**
     * Setter for all instance variables. Since it includes itemDrops, should only be called from copy constructor.
     * @param currency the amount of currency (Reputation Points) gained from this event
     * @param rewardMods DMBattleStats object representing the stats of the duel, which may modify currency/card drop rates.
     * @param origin the DMOpponent that was fought during this event.
     * @param itemDrops itemDrops the DMCard array to copy into this RewardEvent. null is valid, and will cause this
     *                  object's itemDrops array to become null.
     * @return true if all params were valid and thus set; false if one or more were invalid and not set
     */
    public boolean setAll(int currency, DMBattleStats rewardMods, DMOpponent origin, DMCard[]itemDrops){
        this.setItemDrops(itemDrops);
        return this.setAll(currency,rewardMods,origin);

    }



    /***ACCESSORS***/

    /**
     * Getter for currency
     * @return the amount of Reputation Points gained from this event.
     */
    public int getCurrency() {
        return currency;
    }

    /**
     * Getter for itemDrops instance variable
     * @return and array of all DMCard rewards gained from this event.
     */
    public DMCard[] getItemDrops() {
        return itemDrops;
    }

    /**
     * Getter for rewardMods instance variable
     * @return DMBattleStats for all information about the duel from the Duel Stats screen, which may affect rewards.
     */
    public DMBattleStats getRewardMods() {
        return rewardMods;
    }

    /**
     * Getter for origin instance variable
     * @return the DMOpponent object fought as the cause for this reward event
     */
    public DMOpponent getOrigin() {
        return origin;
    }

    /***OVERRIDES**/
    @Override
    public String toString(){
        String result = "Opponent:\n";
        if(origin!=null){
            result += origin.toString() + "\n";
        }else {
            result += NOT_FOUND +"\n";
        }

        result += String.format("Reputation Points:%n%d%n",currency);
        result += "Card Rewards:\n";
        for (DMCard drop : itemDrops){
            if (drop!=null){
                result+= drop.toString() + "\n";
            }else {
                result += "NOT FOUND\n";
            }
        }

        result += "Duel Stats:\n";
        if(rewardMods!=null){
            result += rewardMods.toString();
        }else {
            result += NOT_FOUND +"\n";
        }

        return result;
    }

    @Override
    public boolean equals(Object other){
        if (other == null || ! (other instanceof RewardEvent)){
            return false;
        }else {
            RewardEvent otherEvent = (RewardEvent) other;
            return otherEvent.currency == this.currency &&
                    otherEvent.itemDrops.equals(this.itemDrops) &&
                    otherEvent.rewardMods.equals(this.rewardMods) &&
                    otherEvent.origin.equals(this.origin);
        }
    }
}
