package cs112.ud3.models;

import java.io.Serializable;

/**
 * Represents a card available in the video game version of Duel Masters. Should only be created/modified by CardLink and tester classes,
 * but can be accessed by other classes to read its data.  
 */
public class DMCard implements Serializable {
    /*CONSTANTS*/
    public static final int RARITY_NONE = -1;
    public static final int RARITY_COMMON = 0;
    public static final int RARITY_UNCOMMON = 1;
    public static final int RARITY_RARE = 2;
    public static final int RARITY_VERY_RARE = 3;
    public static final int RARITY_SUPER_RARE = 4;

    public static final int CARDTYPE_CREATURE = 0;
    public static final int CARDTYPE_SPELL = 1;

    public static final int CIVILIZATION_DARKNESS = 0;
    public static final int CIVILIZATION_FIRE = 1;
    public static final int CIVILIZATION_LIGHT = 2;
    public static final int CIVILIZATION_NATURE = 3;
    public static final int CIVILIZATION_WATER = 4;
    public static final int LAST_CIVILIZATION = CIVILIZATION_WATER;

    public static final String[] CIVILIZATIONS = { "Darkness", "Fire", "Light", "Nature", "Water" };

    // Default constats, defaults to the information for Belbetphlo, Wailing Shadow; the first card in the game's initial search ordering.
    public static final String DEFAULT_NAME = "Belbetphlo, Wailing Shadow";
    public static final int DEFAULT_CIVILIZATION = CIVILIZATION_DARKNESS;
    public static final int DEFAULT_CARD_TYPE = CARDTYPE_CREATURE;
    public static final String DEFAULT_TEXTBOX = "Slayer";
    public static final int DEFAULT_RARITY = RARITY_COMMON;
    public static final int DEFAULT_COST = 3;
    public static final boolean DEFAULT_HAS_SHIELD_TRIGGER = false; //SHEILD SHIELD
    public static final int DEFAULT_IDNUM = 0;

    /*Instance Variables */

    private String name;
    private int civilization;
    private int cardType;
    private String textbox;
    private int rarity;
    private int cost;
    private boolean hasShieldTrigger;
    int idNum;

    /**** CONSTRUCTORS ****/

    /**
     * Default constructor for DMCard. Defaults to the information for Belbetphlo, Wailing Shadow; the first card in
     * the game's initial search ordering.
     */
    public DMCard() {
        this(DEFAULT_NAME, DEFAULT_CIVILIZATION, DEFAULT_CARD_TYPE, DEFAULT_TEXTBOX, DEFAULT_RARITY,
                DEFAULT_COST, DEFAULT_HAS_SHIELD_TRIGGER, DEFAULT_IDNUM);
    }

    /**
     * Full Constructor for the DMCard class, representing a Duel Masters card from the PS2 video game.
     * @param name A String for the full name of the Duel Masters card in the card viewer, NOT the shortened version occasionally used in-game
     *      The first entry in the array should be the same as the card's Name value, but is not checked at this point.
     * @param civilization An int representing the Duel Masters civilization (color) of the card. Use CIVILIZATION_... constants
     * @param cardType an int using the CARDTYPE_... constants for Creature or Spell
     * @param textbox A String for the text of the ability for the card. Includes static ablilities such as Sheild Trigger/Power
     *      Attacker as well as one-shot ablilities that only happen when the card is played. Multiple abilities should be
     *      shown on multiple lines in the single textbox variable
     * @param rarity An int representing the rarity of the card. Valid values are Common(0), Uncommon(1), Rare(2), Very Rare (3),
     *      Super Rare (4), and NONE (-1).
     * @param cost An int representing the amount of mana required to play the card. Must be >= 0.
     * @param hasShieldTrigger a boolean which is true if the card has the "Sheild Trigger" ability and false if it does not.
     * @param idNum a number for the id linked to the card. Used to reference card-specific resources, etc.
     * @throws IllegalArgumentException if any argument was invalid as seen in the param sections, will throw this exception with a messeage
     *      that includes some of the more likely mistakes that have been made.
     */
    public DMCard(String name,  int civilization, int cardType, String textbox,
            int rarity, int cost, boolean hasShieldTrigger, int idNum) throws IllegalArgumentException {
        if (!this.setAll(name, civilization, cardType, textbox, rarity, cost, hasShieldTrigger, idNum)) {
            String message = "One or more fields were invalid. Most likely, you had an invalid civilization.\n";
            message += "The valid civilizations are:\n";
            for (String civ : CIVILIZATIONS) {
                message += civ + ", ";
            }
            message = message.substring(0, message.length() - 2);
            message += "\nYour inputted civilization: " + civilization;
            message += "\nOr perhaps rarity was out of bounds. It only goes up to Super Rare (" + RARITY_SUPER_RARE
                    + ")";
            throw new IllegalArgumentException(message);
        }

    }

    /**
     * Copy Constructor for DMCard class
     * @param original The original DMCard to be copied. Cannot be null.
     * @throws IllegalArgumentException If the original DMCard was null, throws this excpetion with an error message.
     */
    public DMCard(DMCard original) throws IllegalArgumentException {
        if (original == null) {
            throw new IllegalArgumentException("ERROR: Null arugment passed to DMCard copy constructor");
        }
        this.setAll(original.name, original.civilization, original.cardType, original.textbox,
                original.rarity, original.cost, original.hasShieldTrigger, original.idNum);
    }

    
    

    /*** MUTATORS ***/

    /**
     * Setter for name instance variable. Represents the full name of the Duel Masters card.
     * @param name A String for the full name of the Duel Masters card in the card viewer, NOT the shortened version occasionally used in-game
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Setter for the civilization instance variable. Sets the card to be one of the five Duel Masters civilizations:
     * Fire, Water, Nature, Darkness, or Light.
     * @param civilization An int representing the Duel Masters civilization (color) of the card. Use CIVILIATION_... constants
     * @return true if civilization param was a valid value and the string was set, False if it was not valid and thus not set.
     */
    public boolean setCivilization(int civilization) {
        if ((civilization>LAST_CIVILIZATION)||(civilization<0)){
            return false;
        }else{
            this.civilization = civilization;
            return true;
        }
    }


    /**
     * Setter for CardType instance variable. This is an int, where 0 represents a creature and 1 represents
     * a spell. see the CARDTYPE static variables for reference
     * @param cardType an int using the CARDTYPE_... constants for Creature or Spell
     * @return true if the parameter was valid and thus the cardType was set, false otherwise.
     */
    public boolean setCardType(int cardType) {
        boolean valid = true;
        if((cardType == CARDTYPE_CREATURE) || (cardType == CARDTYPE_SPELL) ){
            this.cardType = cardType;
        }else{
            valid = false;
        }
        return valid;
    }
    
    /**
     * Setter for textbox variable. Represents the ablilites the card has in-game. As a String;
     * NOT Ability objects, etc.
     * @param textbox A String for the text of the ability for the card. Includes static ablilities such as Sheild Trigger/Power
     *      Attacker as well as one-shot ablilities that only happen when the card is played. Multiple abilities should be
     *      shown on multiple lines in the single textbox variable
     */
    public void setTextbox(String textbox) {
        this.textbox = textbox;
    }

    /**
     * Setter for the rarity instance var, representing the card's rarity. In-Game values are Common(0),
     * Uncommon(1), rare(2), very rare(3), and super rare(4). I've also included a "none"(-1) rarity for cards that
     * can't be obtained through normal means should they be found, and as a default for an invalid rarity.
     * RARITY_... constants are used to help using the correct integers.
     * @param rarity An int representing the rarity of the card. Valid values are Common(0), Uncommon(1), Rare(2), Very Rare (3),
     *      Super Rare (4), and NONE (-1).
     * @return true if the parameter was one of the 6 available values and the var was set to match; false i
     *      the parameter was not a vailid value and the var was set to the default RARITY_NONE value.
     */
    public boolean setRarity(int rarity) {
        boolean valid = false;
        if (rarity == RARITY_COMMON || rarity == RARITY_UNCOMMON || rarity == RARITY_RARE ||
                rarity == RARITY_VERY_RARE || rarity == RARITY_SUPER_RARE) {
            this.rarity = rarity;
            valid = true;
        } else {
            if (rarity == RARITY_NONE) {
                valid = true;
            }
            this.rarity = RARITY_NONE;
        }

        return valid;
    }
    
    /**
     * Setter for cost instance var. Represents the amount of mana required to play the card.
     * @param cost An int representing the amount of mana required to play the card. Must be >= 0.
     * @return True if the paramater was non-negative and the value was set; false if the param was invalid and the value not set.
     */
    public boolean setCost(int cost) {
        boolean valid = false;
        if (cost >= 0) {
            this.cost = cost;
            valid = true;
        }
        return valid;
    }

    /**
     * Setter for hasSheildTrigger instance var. hasSheildTrigger becomes true if the card has the
     * "Sheild Trigger" ability, false if it does not have it.
     * @param hasShieldTrigger a boolean which is true if the card has the "Sheild Trigger" ability and false if it does not.
     */
    public void setHasShieldTrigger(boolean hasShieldTrigger) {
        this.hasShieldTrigger = hasShieldTrigger;
    }

    /**
     * Setter for idNum variable of DMCard.
     * @param idNum a number for the id linked to the card. Used to reference card-specific resources, etc. Must be >-1
     * @return true if idNum was valid and thus properly set, false if invalid and therefore not set.
     */
    public boolean setIdNum(int idNum){
        if(idNum>-1){
            this.idNum = idNum;
            return true;
        }else {
            return false;
        }
    }
    
    /**
     * Sets all instance varialbes for the Duel Masters card: name,  civilization, cardType, textbox, rarity, cost,
     * hasSheildTrigger, and idNum.
     * @param name A String for the full name of the Duel Masters card in the card viewer, NOT the shortened version occasionally used in-game
     * @param civilization A string representing the Duel Masters civilization (color) of the card. Capitalization doesn't matter.
     *      Valid Strings are Fire, Red, Water, Blue, Nature, Green, Darkness, Black, Light, Yellow, and White.
     * @param cardType an int using the CARDTYPE_... constants for Creature or Spell
     * @param textbox A String for the text of the ability for the card. Includes static ablilities such as Sheild Trigger/Power
     *      Attacker as well as one-shot ablilities that only happen when the card is played. Multiple abilities should be
     *      shown on multiple lines in the single textbox variable
     * @param rarity An int representing the rarity of the card. Valid values are Common(0), Uncommon(1), Rare(2), Very Rare (3),
     *      Super Rare (4), and NONE (-1).
     * @param cost An int representing the amount of mana required to play the card. Must be >= 0.
     * @param hasSheildTrigger a boolean which is true if the card has the "Sheild Trigger" ability and false if it does not.
     * @param idNum a number for the id linked to the card. Used to reference card-specific resources, etc.
     * @return True if all parameters were valid and have been set properly, false if one or more parameters were not set properly.
     */
    public boolean setAll(String name, int civilization, int cardType, String textbox,
            int rarity, int cost, boolean hasSheildTrigger, int idNum) {
        this.setName(name);
        this.setTextbox(textbox);
        this.setHasShieldTrigger(hasSheildTrigger);
        return this.setCivilization(civilization) && this.setCardType(cardType) && this.setRarity(rarity)
                && this.setCost(cost) && this.setIdNum(idNum);
    }

    /****ACCESSORS *****/
    
    /**
     * Getter for the Name instance var.
     * @return A String for the full name of the Duel Masters card as seen in the card viewer
     */
    public String getName() {
        return this.name;
    }


    /**
     * Getter for civilization instance variable
     * @return An int for which of the 5 civilizations the card is: Darkness(0), Fire(1), Light(2), Nature(3), or Water(4).
     */
    public int getCivilization(){
        return this.civilization;
    }

    /**
     * Getter for the card type instance variable of the DMCard object
     * @return the type of the card, either CARDTYPE_CREATURE or CARDTYPE_SPELL
     */
    public int getCardType() {
        return this.cardType;
    }

    /**
     * Getter for the textbox instance varible of the DMCard object
     * @return A String that lists all the abilities of the card in plain text.
     */
    public String getTextbox() {
        return this.textbox;
    }

    /**
     * Getter for the rarity instance varible of the DMCard object
     * @return an int representing the rarity of the card: 0 is Common, 1 is Uncommon, 2 is Rare, 3 is Very Rare, 
             * 4 is Super Rare, and -1 is NONE
     */
    public int getRarity() {
        return this.rarity;
    }

    /**
     * Getter for the cost instance varible of the DMCard object
     * @return An int for the amount of mana required to play the card
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Getter for the hasSheildTrigger instance varible of the DMCard object
     * @return True if the card has the sheild trigger ability, false otherwise
     */
    public boolean getHasShieldTrigger() {
        return hasShieldTrigger;
    }

    public int getIdNum(){return this.idNum;}


    /***** OTHER REQUIRED METHODS *****/

    @Override
    public String toString(){
        return this.getName();
    }
    public String toStringExpanded() {
        String typeString;
        switch (cardType){
            case CARDTYPE_CREATURE:
                typeString = "creature";
                break;
            case CARDTYPE_SPELL:
                typeString = "spell";
                break;
            default:
                typeString = "INVALID_TYPE";
        }
        String civString;
        switch (civilization){
            case CIVILIZATION_DARKNESS:
                civString = "Darkness";
                break;
            case CIVILIZATION_FIRE:
                civString = "Fire";
                break;
            case CIVILIZATION_LIGHT:
                civString = "Light";
                break;
            case CIVILIZATION_NATURE:
                civString = "Nature";
                break;
            case CIVILIZATION_WATER:
                civString = "Water";
                break;
            default:
                civString = "UNKOWN_CIVILIZATION";
        }
        String printString = String.format("%s%nA %s %s.%nCost: %d%nAbility: %s%nRarity: ", name, civString,
                typeString,
                cost,
                textbox);
        String rarityString;
        switch (rarity) {
            case RARITY_NONE:
                rarityString = "NONE";
                break;
            case RARITY_COMMON:
                rarityString = "Common";
                break;
            case RARITY_UNCOMMON:
                rarityString = "Uncommon";
                break;
            case RARITY_RARE:
                rarityString = "Rare";
                break;
            case RARITY_VERY_RARE:
                rarityString = "Very Rare";
                break;
            case RARITY_SUPER_RARE:
                rarityString = "Super Rare";
                break;

            default:
                rarityString = "NONE";
                break;
        }
        printString += rarityString + "\n";
        if (hasShieldTrigger) {
            printString += "Has ";
        } else {
            printString += "Doesn't have ";
        }
        printString += "sheild trigger.\n";
        printString += String.format("Id Number: %d",idNum);

        return printString;
    }
    
    @Override
    public boolean equals(Object other) {

        if ((other == null) || (! (other instanceof DMCard))){
            return false;
        } else{
            DMCard otherCard = (DMCard) other;
            return this.name.equals(otherCard.name) && (this.civilization==otherCard.civilization)
                    && (this.cardType==otherCard.cardType) && this.textbox.equals(otherCard.textbox)
                    && this.rarity == otherCard.rarity && this.cost == otherCard.cost
                    && this.hasShieldTrigger == otherCard.hasShieldTrigger && this.idNum == otherCard.idNum;
        }
        
    }

}
