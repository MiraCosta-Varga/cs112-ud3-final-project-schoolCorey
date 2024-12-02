package cs112.ud3.controllers;

import cs112.ud3.InitialView;
import cs112.ud3.models.DMOpponent;
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

public class OpponentInput {

    //Setup GUI components
    @FXML
    ComboBox<String> locationComboBox;
    @FXML
    ComboBox<String> nameComboBox;
    @FXML
    Button backButton;
    @FXML
    Button nextButton;

    public void initializeData(){
        //TODO: (UD3) Populate combo boxes w/ valid location and names
        for(String location: DMOpponent.VALID_LOCATIONS){
            locationComboBox.getItems().add(location);
        }
    }

    @FXML
    public void onBackClick(ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public void onNextClick(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InitialView.class.getResource("card-reward-input.fxml"));
        Parent rewardInputParent = loader.load();

        CardRewardInput cardRewardInput = loader.getController();
        cardRewardInput.initializeData();


        //Parent cardInput = FXMLLoader.load(InitialView.class.getResource("card-reward-input.fxml"));
        Scene cardRewardScene = new Scene(rewardInputParent); //was cardInput
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(cardRewardScene);
        window.show();
    }
}
