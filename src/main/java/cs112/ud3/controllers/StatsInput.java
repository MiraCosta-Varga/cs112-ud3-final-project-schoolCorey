package cs112.ud3.controllers;
/**
 * Lets the user input information from the Duel Stats screen, as well as the Reputation Points earned,
 * from the current RewardEvent. Fields only take Integer input, and min/sec duel time fields are limited to
 * 0-59 inclusive.
 * User can hit back to return to the CardInputScreen, discarding changes to StatsInput but keeping the previous
 * CardRewardInput and OpponentInput information
 * User can hit next to create a DMBattleStats object and add it and the Reputation Points to the RewardEvent,
 * and send its info on to the confirmation screen.
 * If the user is not adding an event, the fields for all inputs will be filled in with the info of the
 * current RewardEvent, but are unable to be edited, and the next button does not modify the reward event either.
 */

import cs112.ud3.UtilityBelt;
import cs112.ud3.models.DMBattleStats;
import cs112.ud3.models.RewardEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

//TODO: change next button check based on if you're adding an event or not.

public class StatsInput extends InputScreen{

    public static final int SPINNER_MIN = 0;
    public static final int SPINNER_MAX = 59;

    private boolean amAddingEvent;
    private RewardEvent rewardEvent;

    @FXML
    private TextField repTextField;
    @FXML
    private TextField creaturesYTextField;
    @FXML
    private TextField creaturesOTextField;
    @FXML
    private TextField shieldsYTextField;
    @FXML
    private  TextField shieldsOTextField;
    @FXML
    private  TextField mWinsYTextField;
    @FXML
    private TextField mWinsOTextField;
    @FXML
    private  TextField tWinsYTextField;
    @FXML
    private TextField tWinsOTextField;
    @FXML
    private  TextField minuteTextField;
    @FXML
    private  TextField secondTextField;
    @FXML
    private  Button backButton;
    @FXML
    private  Button cancelButton;
    @FXML
    private   Button confirmButton;

    @Override
    public void initializeData(RewardEvent rewardEvent, boolean amAddingEvent){
        this.amAddingEvent = amAddingEvent;
        this.rewardEvent = rewardEvent;
        if(!amAddingEvent){
            repTextField.setEditable(false);
            creaturesOTextField.setEditable(false);
            creaturesYTextField.setEditable(false);
            shieldsYTextField.setEditable(false);
            shieldsOTextField.setEditable(false);
            mWinsOTextField.setEditable(false);
            mWinsYTextField.setEditable(false);
            tWinsOTextField.setEditable(false);
            tWinsYTextField.setEditable(false);
            minuteTextField.setEditable(false);
            secondTextField.setEditable(false);
            cancelButton.setVisible(false);
            backButton.setVisible(false);

        }

        //Make all the text fields accept integers only
        repTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        creaturesOTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        creaturesYTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        shieldsYTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        shieldsOTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        mWinsOTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        mWinsYTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        tWinsOTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        tWinsYTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        minuteTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        secondTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

        //Fill in fields with this RewardEvent
        DMBattleStats stats = rewardEvent.getRewardMods();
        if(stats!=null){
            repTextField.setText(Integer.toString(rewardEvent.getCurrency()));
            creaturesOTextField.setText(Integer.toString(stats.getCreaturesLostOpp()));
            creaturesYTextField.setText(Integer.toString(stats.getCreaturesLostPlayer()));
            shieldsOTextField.setText(Integer.toString(stats.getShieldsLostOpp()));
            shieldsYTextField.setText(Integer.toString(stats.getShieldsLostPlayer()));
            mWinsOTextField.setText(Integer.toString(stats.getPlayerLossesLocal()));
            mWinsYTextField.setText(Integer.toString(stats.getPlayerWinsLocal()));
            tWinsOTextField.setText(Integer.toString(stats.getPlayerLossesTotal()));
            tWinsYTextField.setText(Integer.toString(stats.getPlayerWinsTotal()));
            minuteTextField.setText(Integer.toString(stats.getShieldsLostPlayer()));
            shieldsYTextField.setText(Integer.toString(stats.getShieldsLostPlayer()));
            minuteTextField.setText(Integer.toString(stats.getDuelTimeMinutes()));
            secondTextField.setText(Integer.toString(stats.getDuelTimeSeconds()));

        }
    }

    /**
     * Sends the user back to the CardRewardInput screen. Changes on this scene will be discarded, but
     * previous changes to CardRewardInput or OpponentInput will be kept and recreated upon returning
     * to thsose screens.
     * @param actionEvent the event (back button action) causing the method to be fired.
     * @throws IOException from FXML.load() in UtilityBelt.changeInputScene()
     */
    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        UtilityBelt.changeInputScene("card-reward-input.fxml",rewardEvent,amAddingEvent,actionEvent);
    }

    /**
     * Creates a popup which allows the user to entirely cancel adding the event, discarding all changes and
     * closing the popup's window and this one

     */
    public void onCancelClick(ActionEvent actionEvent) throws IOException{
        UtilityBelt.createCancelPopup(actionEvent);
    }

    /**
     * Verifies that all fields have valid input, and creates an error popup for the
     * user if they do not. If they do, then creates a DMBattleStats object with the info, adds
     * it and the reputationPoints(currency) to the RewardEvent, sends the RewardEvent info
     * and whether the user is adding an event or not to the next scene (ConfirmPage),
     * and sends the user to that scene.
     * @param actionEvent the event (cancel button action) causing the method to be fired.
     * @throws IOException from FXML.load() in UtilityBelt.changeInputScene()
     */
    public void onNextClick(ActionEvent actionEvent) throws IOException{
        try{
            int creaturesLostPlayer = Integer.parseInt(creaturesYTextField.getText());
            int creaturesLostOpp = Integer.parseInt(creaturesOTextField.getText());
            int shieldsLostPlayer = Integer.parseInt(shieldsYTextField.getText());
            int shieldsLostOpp = Integer.parseInt(shieldsOTextField.getText());
            int playerWinsLocal = Integer.parseInt(mWinsYTextField.getText());
            int playerLossesLocal = Integer.parseInt(mWinsOTextField.getText());
            int playerWinsTotal = Integer.parseInt(tWinsYTextField.getText());
            int playerLossesTotal = Integer.parseInt(tWinsOTextField.getText());
            int minutes = Integer.parseInt(minuteTextField.getText());
            int seconds = Integer.parseInt(secondTextField.getText());
            DMBattleStats stats = new DMBattleStats(creaturesLostPlayer,creaturesLostOpp,shieldsLostPlayer,shieldsLostOpp,playerWinsLocal,playerLossesLocal,playerWinsTotal,playerLossesTotal,minutes,seconds);
            rewardEvent.setRewardMods(stats);
            rewardEvent.setCurrency(Integer.parseInt(repTextField.getText()));
            UtilityBelt.changeInputScene("confirm-page.fxml",rewardEvent,amAddingEvent,actionEvent);

        }catch (NumberFormatException nfe){
            UtilityBelt.createMessagePopup("Invalid stats.\nMost likely one or more fields were empty.");

        }catch (IllegalArgumentException iae){
            UtilityBelt.createMessagePopup("Invalid stats.\nMost likely a negative number was entered.");
        }

    }

}
