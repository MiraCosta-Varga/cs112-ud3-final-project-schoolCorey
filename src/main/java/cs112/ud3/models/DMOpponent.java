package cs112.ud3.models;

import cs112.ud3.UtilityBelt;

public class DMOpponent {
    public static final String[] VALID_LOCATIONS ={"Card Shop","Restaurant","Park","School","Mall","Junkyard", "Power Plant", "Museum", "Library", "Forest Preserve","Tournament Hall"};
    public static final String DEFAULT_NAME = "Nobody";
    public static final String DEFAULT_LOCATION = "Nowhere";

    private String name;
    private String location;

    private DMCard[] decklist = new DMCard[40]; // to be added in future project versions. Currently defaults to all cards null.

    /****CONSTRUCTORS****/
    /**
     * Default constructor for DMOpponent class. Defaults to Nobody fought Nowhere; an invalid option.
     */
    public DMOpponent(){
        this(DEFAULT_NAME, DEFAULT_LOCATION);
    }

    /**
     * Full constructor for DMOpponent class.
     * @param name The name of this opponent.
     * @param location the Location this opponent is fought at. See DMOpponent.VALID_LOCATIONS for valid options.
     * @throws IllegalArgumentException if setAll returned false, meaning one of the parameters (probably location) was invalid.
     */
    public DMOpponent(String name, String location) throws IllegalArgumentException{
        if(!this.setAll(name,location)){
            String message = "Location not valid\n"; //may need updating in future version that would include decklists
            message += "Your location: " + location+"\n";
            message += "The valid civilizations are:\n";
            for (String loc : VALID_LOCATIONS) {
                message += loc + ", ";
            }
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Copy constructor for DMOpponent class.
     * @param original the original DMOpponent object to be copied
     * @throws IllegalArgumentException if the object to be copied was null. Because objects that fail setAll throw exceptions
     *          in their constructors, it should be impossible for this setAll to fail if the object is not null.
     */
    public DMOpponent(DMOpponent original) throws IllegalArgumentException{
        if (original == null) {
            throw new IllegalArgumentException("ERROR: Null arugment passed to DMCard copy constructor");
        }
        this.setAll(original.name, original.location);
    }

    /****MUTATORS****/
    /**
     * Setter for name instance variable
     * @param name The name of this opponent.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Seeter for location instance variable
     * @param location the Location this opponent is fought at. See DMOpponent.VALID_LOCATIONS for valid options.
     * @return true if the location was valid and thus was set; false if the location was invalid and therefore not set.
     */
    public boolean setLocation(String location) {
        if(UtilityBelt.arrayContains(VALID_LOCATIONS,location)){
            this.location = location;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Setter for all mutatable instance variables of this DMOpponent object
     * @param name The name of this opponent.
     * @param location the Location this opponent is fought at. See DMOpponent.VALID_LOCATIONS for valid options.
     * @return true if all params were valid and therefore set; false if one or more params were invalid and not set.
     */
    public boolean setAll(String name, String location){
        this.setName(name);
        return this.setLocation(location);
    }

    /***ACCESSORS***/
    /**
     * getter for name instance variable
     * @return a String for the Name of this opponent
     */
    public String getName(){
        return this.name;
    }

    /**
     * getter for location instance variable
     * @return a String representing the location the opponent is fought at. See DMOpponent.VALID_LOCATIONS for possible strings.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter for decklist instance variable. Not used in current version
     * @return an array of DMCards representing each known card in the opponent's deck
     */
    public DMCard[] getDecklist() {
        return decklist;
    }

    @Override
    public String toString(){
        return this.name + " (" + this.location + ")";
    }

    @Override
    public boolean equals(Object other){
        if ((other == null) || (! (other instanceof DMOpponent))){
            return false;
        } else{
            DMOpponent otherOpp = (DMOpponent) other;
            return this.name.equals(otherOpp.name) && (this.location==otherOpp.location)
                    && (this.decklist.equals(otherOpp.decklist));
        }
    }
}
