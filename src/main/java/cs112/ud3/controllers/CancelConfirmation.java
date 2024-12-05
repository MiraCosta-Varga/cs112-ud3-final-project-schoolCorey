package cs112.ud3.controllers;

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

    public void initializeData(Stage lastStage){
        this.lastStage = lastStage;
    }

    @FXML
    public void onYesClick(ActionEvent actionEvent){
        lastStage.close();
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onNoClick(ActionEvent actionEvent){
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }

}
