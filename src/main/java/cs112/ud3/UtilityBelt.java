package cs112.ud3;

import cs112.ud3.models.DMCard;
import cs112.ud3.models.DMCreature;

/**
 * Utility Belt class holds helper methods for various other classes to use
 */
public class UtilityBelt {

    /**
     * Iterates over and array to see if it contains a specified value. Ignores capitalization.
     * Must fully match a string in the array (partial match returns false)
     * @param validStringsArray The array to search for the value
     * @param value the string to search for in the array.
     * @return true if an element of the array exactly matches the value, ignoring case. False if there are no matches in the array.
     */
    public static boolean arrayContains(String[] validStringsArray, String value) {
        if (value == null || validStringsArray == null) {
            return false;
        }

        int location = 0;
        boolean isPresent = false;

        while (!isPresent && (location < validStringsArray.length)) { // while not found and haven't gone through full array
            isPresent = validStringsArray[location].equalsIgnoreCase(value);
            location++;
        }

        return isPresent;
    }

    /**
     * Takes a string version of a DMCard's civilization and returns the matching constant int version. Used to help create
     * DMCards from text file input
     * @param civilization A String version of the cards civilization
     * @return the int corresponding to the cards civilization, or -1 if there is no match.
     */
    public static int civStringToInt(String civilization){
        civilization = civilization.trim();
        if(civilization.equalsIgnoreCase("Darkness")){
            return DMCard.CIVILIZATION_DARKNESS;
        } else if (civilization.equalsIgnoreCase("Water")) {
            return DMCard.CIVILIZATION_WATER;
        } else if (civilization.equalsIgnoreCase("Nature")) {
            return DMCard.CIVILIZATION_NATURE;
        } else if (civilization.equalsIgnoreCase("Light")) {
            return DMCard.CIVILIZATION_LIGHT;
        } else if (civilization.equalsIgnoreCase("Fire")) {
            return DMCard.CIVILIZATION_FIRE;
        }else{
            return -1;
        }
    }

    /**
     * Takes a string version of a DMCard's rarity and returns the matching constant int version. Used to help create
     * DMCards from text file input
     * @param rarity A String version of the cards rarity
     * @return the int corresponding to the cards rarit., or -1  (DMCard.RARITY_NONE) if there is no match.
     */
    public static int rarityStringToInt(String rarity){
        rarity = rarity.trim();
        if(rarity.equalsIgnoreCase("Common")){
            return DMCard.RARITY_COMMON;
        } else if (rarity.equalsIgnoreCase("Uncommon")) {
            return DMCard.RARITY_UNCOMMON;
        } else if (rarity.equalsIgnoreCase("Rare")) {
            return DMCard.RARITY_RARE;
        } else if (rarity.equalsIgnoreCase("Very Rare")) {
            return DMCard.RARITY_VERY_RARE;
        } else if (rarity.equalsIgnoreCase("Super Rare")) {
            return DMCard.RARITY_SUPER_RARE;
        }else{
            return DMCard.RARITY_NONE;
        }
    }

    /**
     * Takes a string version of a DMCard's race and returns the matching constant int version. Used to help create
     * DMCards from text file input
     * @param race A String version of the cards rarity
     * @return the int corresponding to the cards race, or -1  if there is no match.
     */
    public static int raceStringToInt(String race){
        race = race.trim();
        if(race.equalsIgnoreCase("Ghost")){
            return DMCreature.RACE_GHOST;
        } else if (race.equalsIgnoreCase("brain jacker")) {
            return DMCreature.RACE_BRAIN_JACKER;
        } else if (race.equalsIgnoreCase("living dead")) {
            return DMCreature.RACE_LIVING_DEAD;
        } else if (race.equalsIgnoreCase("DEMON COMMAND")) {
            return DMCreature.RACE_DEMON_COMMAND;
        } else if (race.equalsIgnoreCase("CHIMERA")) {
            return DMCreature.RACE_CHIMERA;
        } else if (race.equalsIgnoreCase("PARASITE WORM")) {
            return DMCreature.RACE_PARASITE_WORM;
        } else if (race.equalsIgnoreCase("HEDRIAN")) {
            return DMCreature.RACE_HEDRIAN;
        } else if (race.equalsIgnoreCase("DARK LORD")) {
            return DMCreature.RACE_DARK_LORD;
        } else if (race.equalsIgnoreCase("ARMORED DRAGON")) {
            return DMCreature.RACE_ARMORED_DRAGON;
        } else if (race.equalsIgnoreCase("HUMAN")) {
            return DMCreature.RACE_HUMAN;
        } else if (race.equalsIgnoreCase("DRAGONOID")) {
            return DMCreature.RACE_DRAGONOID;
        } else if (race.equalsIgnoreCase("ROCK BEAST")) {
            return DMCreature.RACE_ROCK_BEAST;
        } else if (race.equalsIgnoreCase("ARMORED WYVERN")) {
            return DMCreature.RACE_ARMORED_WYVERN;
        } else if (race.equalsIgnoreCase("MACHINE EATER")) {
            return DMCreature.RACE_MACHINE_EATER;
        } else if (race.equalsIgnoreCase("ARMORLOID")) {
            return DMCreature.RACE_ARMORLOID;
        } else if (race.equalsIgnoreCase("ANGEL COMMAND")) {
            return DMCreature.RACE_ANGEL_COMMAND;
        } else if (race.equalsIgnoreCase("LIGHT BRINGER")) {
            return DMCreature.RACE_LIGHT_BRINGER;
        } else if (race.equalsIgnoreCase("GUARDIAN")) {
            return DMCreature.RACE_GUARDIAN;
        } else if (race.equalsIgnoreCase("STARLIGHT TREE")) {
            return DMCreature.RACE_STARLIGHT_TREE;
        } else if (race.equalsIgnoreCase("INITIATE")) {
            return DMCreature.RACE_INITIATE;
        } else if (race.equalsIgnoreCase("BERSERKER")) {
            return DMCreature.RACE_BERSERKER;
        } else if (race.equalsIgnoreCase("MECHA THUNDER")) {
            return DMCreature.RACE_MECHA_THUNDER;
        } else if (race.equalsIgnoreCase("BEAST FOLK")) {
            return DMCreature.RACE_BEAST_FOLK;
        } else if (race.equalsIgnoreCase("GIANT")) {
            return DMCreature.RACE_GIANT;
        } else if (race.equalsIgnoreCase("GIANT INSECT")) {
            return DMCreature.RACE_GIANT_INSECT;
        } else if (race.equalsIgnoreCase("TREE FOLK")) {
            return DMCreature.RACE_TREE_FOLK;
        } else if (race.equalsIgnoreCase("HORNED BEAST")) {
            return DMCreature.RACE_HORNED_BEAST;
        } else if (race.equalsIgnoreCase("LIQUID PEOPLE")) {
            return DMCreature.RACE_LIQUID_PEOPLE;
        } else if (race.equalsIgnoreCase("CYBER VIRUS")) {
            return DMCreature.RACE_CYBER_VIRUS;
        } else if (race.equalsIgnoreCase("CYBER LORD")) {
            return DMCreature.RACE_CYBER_LORD;
        } else if (race.equalsIgnoreCase("LEVIATHAN")) {
            return DMCreature.RACE_LEVIATHAN;
        } else if (race.equalsIgnoreCase("GEL FISH")) {
            return DMCreature.RACE_GEL_FISH;
        } else if (race.equalsIgnoreCase("FISH")) {
            return DMCreature.RACE_FISH;
        }else{
            return -1;
        }
    }


}
