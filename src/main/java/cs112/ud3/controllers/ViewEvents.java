package cs112.ud3.controllers;

import cs112.ud3.InitialView;
import cs112.ud3.UtilityBelt;
import cs112.ud3.models.RewardEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewEvents {

    private ArrayList<RewardEvent> database;

    @FXML
    private ComboBox<RewardEvent> eventChoiceComboBox;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;

    public void initializeData(ArrayList<RewardEvent> database){
        //stub
        this.database = database;
        for(RewardEvent event: database){
            eventChoiceComboBox.getItems().add(event);
        }
    }

    public void onBackClick(ActionEvent actionEvent){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public void onNextClick(ActionEvent actionEvent) throws IOException {
        RewardEvent selection = eventChoiceComboBox.getSelectionModel().getSelectedItem();

        if (selection!= null){
            UtilityBelt.changeInputScene("confirm-page.fxml",selection,false,actionEvent);
        }else {
            UtilityBelt.createMessagePopup("No event chosen.\nPlease make a selection.");
        }

    }


}
