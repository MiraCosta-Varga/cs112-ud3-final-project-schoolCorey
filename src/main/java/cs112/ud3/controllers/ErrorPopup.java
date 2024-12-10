package cs112.ud3.controllers;
/**
 * Displays a message to the user, which can be closed with a single click.
 * Primarily used for error messages, but can also be used for other feedback,
 * such as successfull addition of an event.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ErrorPopup {

    @FXML
    private Label messageLabel;

    @FXML
    private Button okButton;

    /**
     * Sets up the stage by determining the message to be displayed to the user
     * @param errorMessage the message to be displayed to the user.
     */
    public void initializeData(String errorMessage){
        messageLabel.setText(errorMessage);
    }

    /**
     * Closes the popup.
     * @param actionEvent the event (ok button click) which causes the method to fire.
     */
    public void onOkButtonClick(ActionEvent actionEvent){
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }


}
