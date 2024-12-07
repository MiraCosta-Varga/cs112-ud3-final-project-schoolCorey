package cs112.ud3.models;

import cs112.ud3.Exceptions.UninitializedLinkException;
import cs112.ud3.UtilityBelt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/***
 * Class to hold an array of all valid DM cards
 * When the user wants to add a card, they go through linkObject() from this class
 * to ensure that they are adding a valid Duel Masters Card
 */

public class CardLink extends ValidLink {
    private static final int TOTAL_CARDS = 138; // txt file was missing a card, I think it was green, will add back when we need it again
    private static final String FILENAME = "src/main/resources/cs112/ud3/DuelMastersCardsCleaned.txt";
    private static final int NAME_INDEX_SPELL = 0;
    private static final int CIVILIZATION_INDEX_SPELL = 1;
    private static final int ABILITY_INDEX_SPELL = 2;
    private static final int COST_INDEX_SPELL = 3;
    private static final int RARITY_INDEX_SPELL = 4;
    private static final int NAME_INDEX_CREATURE = 0;
    private static final int CIVILIZATION_INDEX_CREATURE = 1;
    private static final int RACE_INDEX_CREATURE = 2;
    private static final int ABILITY_INDEX_CREATURE = 3;
    private static final int COST_INDEX_CREATURE = 4;
    private static final int POWER_INDEX_CREATURE = 5;
    private static final int RARITY_INDEX_CREATURE = 6;


    public static DMCard[] validCards = new DMCard[TOTAL_CARDS]; //static so we don't have to re-read the file every time we go to cardInput
    private static int currentCardID = 0;

    /**
     * Getter for static member variable validCards
     * @return an array of DMCard objects representing all valid versions of DMCard objects (each of the Duel Masters cards available in the PS2 game)
     */
    public DMCard[] getValidCards(){
        return validCards;
    }

    @Override
    public void createValidArray(){
        Scanner iStream = null;
        String cardBlock;
        DMCard temp;
        String[] keyVals =null;

        try{
            iStream = new Scanner(new FileInputStream(FILENAME));

            iStream.useDelimiter("\n\r\n");

            while (iStream.hasNextLine()){
                cardBlock = iStream.next();
                //The prints were for testing
                //System.out.println(cardBlock);
                //System.out.println("LIMITED");
                keyVals = cardBlock.split(": ");
                String typeCheck = keyVals[0];
                try{
                    if(typeCheck.equalsIgnoreCase("Name")){
                        temp = creatureCreateFromStringArray(cardBlock);
                        validCards[currentCardID] = temp;
                        currentCardID++;
                    } else if (typeCheck.equalsIgnoreCase("Spell")) {
                        temp = spellCreateFromStringArray(cardBlock);
                        validCards[currentCardID] = temp;
                        currentCardID++;
                    }else{
                        System.out.println("ERROR PARSING CARD TYPE:");
                        System.out.println(cardBlock);
                        System.out.println();
                    }
                }catch (IllegalArgumentException iae){
                    System.out.println("Error parsing card:");
                    System.out.println(cardBlock);
                    System.out.println();
                }catch (Exception e){
                    System.out.println("Some other error?");
                    System.out.println(e.getMessage());
                    System.out.println(cardBlock);
                    System.out.println();
                }
            }
            iStream.close();

        }catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage()); // maybe
        }

    }

    /**
     * Takes a string of input which is the block of text for a specific creature card found in a text file
     * of card information, and creates a DMCreature with that information.
     * @param cardBlock the block of text to create the DMCreature from. Should be formatted
     *                  like in this example:
     *
     *                  Name: Bloody Squito
     *                  Civilization: Darkness
     *                  Race: Brain Jacker
     *                  Abilities: Blocker, Can't Attack, Gets Destroyed after battling
     *                  Mana Cost: 2
     *                  Power: 4000
     *                  Rarity: Common
     *                  Ways to Acquire: Starter Deck
     * @return a DMCreature with the specified name, civilization, race, textbox (abilities), cost, power, and rarity.
     */
    public DMCreature creatureCreateFromStringArray(String cardBlock){
        int cardType = DMCard.CARDTYPE_CREATURE;
        String[] keyVals;
        keyVals = cardBlock.split(": |\r\n");
        String name = keyVals[(NAME_INDEX_CREATURE*2)+1];
        String civString = keyVals[(CIVILIZATION_INDEX_CREATURE*2)+1];
        int civ = UtilityBelt.civStringToInt(civString);
        String ability = keyVals[(ABILITY_INDEX_CREATURE*2)+1];
        String rarityString = keyVals[(RARITY_INDEX_CREATURE*2)+1];
        int rarity = UtilityBelt.rarityStringToInt(rarityString);
        int cost = Integer.parseInt(keyVals[(COST_INDEX_CREATURE*2)+1]);
        String lowercaseAbility = ability.toLowerCase();
        boolean hasShieldTrigger = lowercaseAbility.contains("shield trigger");
        int power = Integer.parseInt(keyVals[(POWER_INDEX_CREATURE*2)+1]);
        String raceString = keyVals[(RACE_INDEX_CREATURE*2)+1];
        int race = UtilityBelt.raceStringToInt(raceString);
        boolean isEvolution = lowercaseAbility.contains("evolution");
        DMCreature thisCard = new DMCreature(name,civ,cardType,ability,rarity,cost,hasShieldTrigger,power,race,isEvolution,currentCardID);
        return thisCard;
    }

    /**
     * Takes a string of input which is the block of text for a specific spell card found in a text file
     * of card information, and creates a DMCard with that information.
     * @param cardBlock the block of text to create the DMCreature from. Should be formatted
     *                  like in this example:
     *
     *                  Spell: Lost Soul
     *                  Civilization: Darkness
     *                  Abilities: opponent discards all cards
     *                  Mana Cost: 7
     *                  Rarity: Uncommon
     * @return a DMCard with the specified name, civilization, race, textbox (abilities), cost, and rarity.
     */
    public DMCard spellCreateFromStringArray(String cardBlock){
        int cardType = DMCard.CARDTYPE_SPELL;
        String[] keyVals;
        keyVals = cardBlock.split(": |\r\n"); //Splits on a colon and space or on a new line
        String name = keyVals[(NAME_INDEX_SPELL*2) +1];
        String civString = keyVals[(CIVILIZATION_INDEX_SPELL*2)+1];
        int civ = UtilityBelt.civStringToInt(civString);
        String ability = keyVals[(ABILITY_INDEX_SPELL*2)+1];
        int cost = Integer.parseInt(keyVals[(COST_INDEX_SPELL*2)+1]);
        String rarityString = keyVals[(RARITY_INDEX_SPELL*2)+1];
        int rarity = UtilityBelt.rarityStringToInt(rarityString);
        String lowercaseAbility = ability.toLowerCase();
        boolean hasShieldTrigger = ability.contains("shield trigger");

        DMCard thisCard = new DMCard(name,civ,cardType,ability,rarity,cost,hasShieldTrigger,currentCardID);
        return thisCard;
    }

    @Override
    public boolean objectIsValid(Object candidate) throws UninitializedLinkException {
        if(validCards==null){
            throw new UninitializedLinkException();
        }
        if(candidate==null||!(candidate instanceof DMCard)){
            return false;
        }else{
            return cardIsValid((DMCard) candidate);
        }
    }

    /**
     * ObjectIsValid method, specifically for DMCard canditates. Checks if a given card
     * is valid by checking if it matches a card in the validCards array.
     * @param candidate the DMCard to check for validity of. Whether the object is a DMCard should be checked in
     *                  the objectIsValid() method that this method is called from
     * @return true if the card is found in the array and thus valid, false if it is not found in the array and is invalid.
     */
    private boolean cardIsValid(DMCard candidate){
        for(int i = 0; i < validCards.length; i++){
            if (validCards[i].equals(candidate)){
                return true;
            }
        }
        return false;
    }


}
