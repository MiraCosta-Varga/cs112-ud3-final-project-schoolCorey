package cs112.ud3.models;

public class DMCreature extends DMCard {
    /***CONSTANTS***/

    public static final int RACE_GHOST = 0;
    public static final int RACE_BRAIN_JACKER = 1;
    public static final int RACE_LIVING_DEAD = 2;
    public static final int RACE_DEMON_COMMAND = 3;
    public static final int RACE_CHIMERA = 4;
    public static final int RACE_PARASITE_WORM = 5;
    public static final int RACE_HEDRIAN = 6;
    public static final int RACE_DARK_LORD = 7;

    public static final int RACE_ARMORED_DRAGON = 8;
    public static final int RACE_HUMAN = 9;
    public static final int RACE_DRAGONOID = 10;
    public static final int RACE_ROCK_BEAST = 11;
    public static final int RACE_ARMORED_WYVERN = 12;
    public static final int RACE_MACHINE_EATER = 13;
    public static final int RACE_ARMORLOID = 14;

    public static final int RACE_ANGEL_COMMAND = 15;
    public static final int RACE_LIGHT_BRINGER = 16;
    public static final int RACE_GUARDIAN = 17;
    public static final int RACE_STARLIGHT_TREE = 18;
    public static final int RACE_INITIATE = 19;
    public static final int RACE_BERSERKER = 20;
    public static final int RACE_MECHA_THUNDER = 21;

    public static final int RACE_BEAST_FOLK = 22;
    public static final int RACE_GIANT = 23;
    public static final int RACE_GIANT_INSECT = 24;
    public static final int RACE_TREE_FOLK = 25;
    public static final int RACE_HORNED_BEAST = 26;

    public static final int RACE_LIQUID_PEOPLE = 27;
    public static final int RACE_CYBER_VIRUS = 28;
    public static final int RACE_CYBER_LORD = 29;
    public static final int RACE_LEVIATHAN = 30;
    public static final int RACE_GEL_FISH = 31;
    public static final int RACE_FISH = 32;

    public static final int LAST_RACE = RACE_FISH;


    //defaults to Belbetphlo
    public static final int DEFAULT_POWER = 1000;
    public static final int DEFAULT_RACE = RACE_GHOST;
    public static final boolean DEFAULT_IS_EVOLUTION = false;

    int power;
    int race;
    boolean isEvolution;

    /**** CONSTRUCTORS ****/

    /**
     * Default constructor for DMCard. Defaults to the information for Belbetphlo, Wailing Shadow; the first card in
     * the game's initial search ordering.
     */
    public DMCreature() {
        this(DEFAULT_NAME, DEFAULT_CIVILIZATION, DEFAULT_CARD_TYPE, DEFAULT_TEXTBOX, DEFAULT_RARITY,
                DEFAULT_COST, DEFAULT_HAS_SHIELD_TRIGGER,DEFAULT_POWER,DEFAULT_RACE,DEFAULT_IS_EVOLUTION, DEFAULT_IDNUM);
    }

    /**
     * Full Constructor for the DMCreature class, representing a creature-type Duel Masters card from the PS2 video game.
     * @param name A String for the full name of the Duel Masters card in the card viewer, NOT the shortened version occasionally used in-game
     * @param civilization An int representing the Duel Masters civilization (color) of the card. Use CIVILIZATION_... constants
     * @param cardType an int using the CARDTYPE_... constants for Creature or Spell
     * @param textbox A String for the text of the ability for the card. Includes static ablilities such as Sheild Trigger/Power
     *      Attacker as well as one-shot ablilities that only happen when the card is played. Multiple abilities should be
     *      shown on multiple lines in the single textbox variable
     * @param rarity An int representing the rarity of the card. Valid values are Common(0), Uncommon(1), Rare(2), Very Rare (3),
     *      Super Rare (4), and NONE (-1).
     * @param cost An int representing the amount of mana required to play the card. Must be >= 0.
     * @param hasSheildTrigger a boolean which is true if the card has the "Sheild Trigger" ability and false if it does not.
     * @param power An int for the base power of the creature card. Must be >=0; doesn't count in-game modifiers
     * @param race An int represending the race of the card. Use RACE_... constants.
     * @param isEvolution True if the creature is an evolution creature; false if it is not.
     * @param idNum a number for the id linked to the card. Used to reference card-specific resources, etc.
     * @throws IllegalArgumentException if any argument was invalid as seen in the param sections, will throw this exception with a messeage
     *      that includes some of the more likely mistakes that have been made.
     */
    public DMCreature(String name, int civilization, int cardType, String textbox,
                  int rarity, int cost, boolean hasSheildTrigger, int power, int race, boolean isEvolution,int idNum) throws IllegalArgumentException {
        if (!this.setAll(name, civilization, cardType, textbox, rarity, cost, hasSheildTrigger, power,race,isEvolution,idNum)) {
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

        // May later implement a check here to see if the first entry in Aliases matches the instance variable for name.
    }

    /*** MUTATORS ***/

    /**
     * Setter for power instance variable. Represents the power value of the duel monsters creature, whic is used to determine
     * combat against creatures and other effects in the Duel Masters video game
     * @param power An int for the base power of the creature card. Must be >=0; doesn't count in-game modifiers
     * @return true if the param was valid and power has been sucessfully set; false otherwise.
     */
    public boolean setPower(int power) {
        if(power>=0){
            this.power = power;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Setter for setRace instance variable. This is simply a String.
     * @param race An int represending the race of the card. Use RACE_... constants.
     * @return true if the parameter was valid and thus the cardRace was set, false otherwise.
     */
    public boolean setRace(int race) {
        boolean valid = true;
        if((race<0)||(race>LAST_RACE)){
            return false;
        }else {
            this.race = race;
        }
        return true;
    }

    /**
     * Setter for isEvolution instance variable. If true, in notes the creature as an
     * Evolution creature. If false, it is a regular creature without the Evolution restrictions.
     * @param isEvolution True if the creature is an evolution creature; false if it is not.
     */
    public void setIsEvolution(boolean isEvolution){
        this.isEvolution = isEvolution;
    }

    /**
     * Sets all instance varialbes for the Duel Masters creature card: name, aliases, civilization, cardType, textbox, rarity, cost,
     * hasSheildTrigger, power, race, and isEvolution.
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
     * @param power An int for the base power of the creature card. Must be >=0; doesn't count in-game modifiers
     * @param race an int representing the Race of the creature. Use RACE_... constants.
     * @param isEvolution True if the creature is an evolution creature; false if it is not.
     * @param idNum a number for the id linked to the card. Used to reference card-specific resources, etc.
     * @return True if all parameters were valid and have been set properly, false if one or more parameters were not set properly.
     * @throws IllegalArgumentException if the race argument was invalid, will throw this exception with a message
     * that includes some of the more likely mistakes that have been made.
     */
    public boolean setAll(String name, int civilization, int cardType, String textbox,
                          int rarity, int cost, boolean hasSheildTrigger,int power, int race, boolean isEvolution, int idNum) throws IllegalArgumentException{
        this.setIsEvolution(isEvolution);
        boolean raceValid = this.setRace(race);
        if(!raceValid){
            String message = "Race invalid.\n";
            message += "Please use DMCreature.RACE_... constants to set race.";
            throw new IllegalArgumentException(message);
        }
        boolean cardSuccess = super.setAll(name,civilization,cardType,textbox,rarity,cost,hasSheildTrigger,idNum);
        return cardSuccess && this.setPower(power); //&& raceValid, but that will always be true at this point
    }

    //ACCESSORS

    /**
     * Getter for the Race instance variable of the creature
     * @return an int representing the race of the creature, corresponding to a  RACE_... constant.
     */
    public int getRace(){
        return race;
    }

    /**
     * Getter for Power instance variable of  the creature.
     * @return an int representing the creature's power.
     */
    public int getPower(){
        return power;
    }

    /**
     * Getter for isEvolution instance variable
     * @return True if the creature is an evolution creature; False if it is not.
     */
    public boolean getIsEvolution(){
        return isEvolution;
    }


    /**
     * Conversion from RACE_... constants to Strings
     * @param raceInt the integer corresponding to the RACE_... constant to be converted.
     * @return A string for the name of the specified Race.
     */
    public String raceToString(int raceInt){
        String result;
        switch (raceInt){
            case RACE_GHOST:
                result = "Ghost";
                break;
            case RACE_BRAIN_JACKER:
                result = "Brain Jacker";
                break;
            case RACE_LIVING_DEAD:
                result = "Living Dead";
                break;
            case RACE_DEMON_COMMAND:
                result = "Demon Command";
                break;
            case RACE_CHIMERA:
                result = "Chimera";
                break;
            case RACE_PARASITE_WORM:
                result = "Parasite Worm";
                break;
            case RACE_HEDRIAN:
                result = "Hedrian";
                break;
            case RACE_DARK_LORD:
                result = "Dark Lord";
                break;
            case RACE_ARMORED_DRAGON:
                result = "Armored Dragon";
                break;
            case RACE_HUMAN:
                result = "Human";
                break;
            case RACE_DRAGONOID:
                result = "Dragonoid";
                break;
            case RACE_ROCK_BEAST:
                result = "Rock Beast";
                break;
            case RACE_ARMORED_WYVERN:
                result = "Armored Wyvern";
                break;
            case RACE_MACHINE_EATER:
                result = "Machine Eather";
                break;
            case RACE_ARMORLOID:
                result = "Armorloid";
                break;
            case RACE_ANGEL_COMMAND:
                result = "Angel Command";
                break;
            case RACE_LIGHT_BRINGER:
                result = "Light Bringer";
                break;
            case RACE_GUARDIAN:
                result = "Guardian";
                break;
            case RACE_STARLIGHT_TREE:
                result = "Starlight Tree";
                break;
            case RACE_INITIATE:
                result = "Initiate";
                break;
            case RACE_BERSERKER:
                result = "Berserker";
                break;
            case RACE_MECHA_THUNDER:
                result = "Mecha Thunder";
                break;
            case RACE_BEAST_FOLK:
                result = "Beast Folk";
                break;
            case RACE_GIANT:
                result = "Giant";
                break;
            case RACE_GIANT_INSECT:
                result = "Gaint Insect";
                break;
            case RACE_TREE_FOLK:
                result = "Tree Folk";
                break;
            case RACE_HORNED_BEAST:
                result = "Horned Beast";
                break;
            case RACE_LIQUID_PEOPLE:
                result = "Liquid People";
                break;
            case RACE_CYBER_VIRUS:
                result= "Cyber Virus";
                break;
            case RACE_CYBER_LORD:
                result = "Cyber Lord";
                break;
            case RACE_LEVIATHAN:
                result="Leviathan";
                break;
            case RACE_GEL_FISH:
                result = "Gel Fish";
                break;
            case RACE_FISH:
                result = "Fish";
                break;

            default:
                result = "INVALID_RACE";
        }
        return result;
    }



    @Override
    public boolean equals(Object other){
        if ((other == null) || (! (other instanceof DMCreature))){
            return false;
        } else{
            DMCreature otherCard = (DMCreature) other;
            return super.equals(otherCard)
                    && (this.power == otherCard.power)
                    && (this.race == otherCard.race)
                    && (this.isEvolution == otherCard.isEvolution);
        }
    }

    @Override
    public String toStringExpanded(){
        String superString = super.toStringExpanded();
        String evoString;
        if(isEvolution){
            evoString = "An Evolution creature.";
        }else {
            evoString = "Not an Evolution creature.";
        }
        String result = String.format("%s%nRace:%s%nPower: %d%n%s",superString,raceToString(race),power,evoString);

        return result;
    }



}
