package cs112.ud3.controllers;
/**
 * Allows the user to completely cancel adding the current event, rather than
 * having to click back on multiple stages.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CancelConfirmation {
    //Setup Gui
    @FXML
    Button yesButton;
    @FXML
    Button noButton;

    //variables from others
    Stage lastStage; // to close that window and this one

    /**
     * Sets up stage before viewing. Links the last stage so it can close both.
     * @param lastStage the previous stage that created this stage's popup
     */
    public void initializeData(Stage lastStage){
        this.lastStage = lastStage;
    }

    /**
     * Cancels adding the event by closing this stage and the stage that created it
     * @param actionEvent the event caused by clicking the Yes button
     */
    @FXML
    public void onYesClick(ActionEvent actionEvent){
        lastStage.close();
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes this stage and leaves the previous one that created the popup as it was
     * @param actionEvent the event caused by clicking the No button
     */
    @FXML
    public void onNoClick(ActionEvent actionEvent){
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }

}
