package cs112.ud3;

import cs112.ud3.controllers.CancelConfirmation;
import cs112.ud3.controllers.ErrorPopup;
import cs112.ud3.controllers.InputScreen;
import cs112.ud3.models.DMCard;
import cs112.ud3.models.DMCreature;
import cs112.ud3.models.RewardEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

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

    /**
     * Creates a simple popup scene from the error-popup.fxml file that displays
     * a message to the user and can be closed with a single input to return
     * to the previous scene. Can be used to inform of errors or successful addition of events
     * @param message The message the popup should show to the user. Should inform them
     *                of the issue which caused the error. Can be an Exception's message.
     * @throws IOException required to use FXMLLoader's .load()
     */
    public static void createMessagePopup(String message) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InitialView.class.getResource("error-popup.fxml"));
        Parent cancelParent = loader.load();

        ErrorPopup errorPopup = loader.getController();
        errorPopup.initializeData(message);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(cancelParent));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Changes the scene to an InputScreen scene and sets it up with necessary info from the current RewardEvent, etc
     * to be properly displayed and intractable
     * @param sceneFXMLfilename the filename of the .fxml file of the scene to change to
     * @param rewardEvent the current RewardEvent which is being tracked/modified
     * @param amAddingEvent true if the player is adding(and thus modifying) a Reward Event, false if they are only viewing
     * @param triggerEvent the ActionEvent which triggers the scene change. Used to get the stage window.
     * @throws IOException required for FXMLLoader's .load() method.
     */
    public static void changeInputScene(String sceneFXMLfilename, RewardEvent rewardEvent, boolean amAddingEvent, ActionEvent triggerEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InitialView.class.getResource(sceneFXMLfilename));
        Parent sceneParent = loader.load();

        InputScreen nextScreen = loader.getController();
        nextScreen.initializeData(rewardEvent,amAddingEvent);

        Scene nextScene = new Scene(sceneParent);
        Stage window = (Stage) ((Node)triggerEvent.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }

    /**
     * Creates a popup of a CancelConfirmation scene, and gives it the stage of the previous window
     * so that it has the ability to close both itself and the previous window.
     * @param triggerEvent the event which is creating the cancel popup (usually a cancel button click, needed to
     *                     get the window of the previous stage)
     * @throws IOException for FXMLLoader.load()
     */
    public static void createCancelPopup(ActionEvent triggerEvent) throws  IOException{
        Stage thisStage = (Stage) ((Node)triggerEvent.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InitialView.class.getResource("cancel-confirmation.fxml"));
        Parent cancelParent = loader.load();

        CancelConfirmation cancelConfirmation = loader.getController();
        cancelConfirmation.initializeData(thisStage);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(cancelParent));
        stage.setResizable(false);
        stage.show();
    }


}
