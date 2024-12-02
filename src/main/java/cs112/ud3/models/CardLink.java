package cs112.ud3.models;

import cs112.ud3.UtilityBelt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/***
 * Class to hold an array of all valid DM cards
 * When the user wants to add a card, they go through linkObject() from this class
 * to ensure that they are adding a valid Duel Masters Card
 * /
 /*
 * * linking ALGORITHM:
 * * Figure out however the bleep combo box works
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
    public boolean objectIsValid(Object candidate){
        if(candidate==null||!(candidate instanceof DMCard)){
            return false;
        }else{
            return cardIsValid((DMCard) candidate);
        }
    }

    private boolean cardIsValid(DMCard candidate){
        return candidate.getHasShieldTrigger();
    }


}
