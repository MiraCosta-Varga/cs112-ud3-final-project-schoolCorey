package cs112.ud3.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ErrorPopup {

    @FXML
    Label messageLabel;

    @FXML
    Button okButton;

    public void initializeData(String errorMessage){
        messageLabel.setText(errorMessage);
    }

    public void onOkButtonClick(ActionEvent actionEvent){
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }


}
