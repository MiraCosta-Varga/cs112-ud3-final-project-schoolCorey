package cs112.ud3.controllers;

import cs112.ud3.InitialView;
import cs112.ud3.models.RewardEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmPage {
    boolean addingEvent;

    //setup GUi
    @FXML
    private Label repPointsLabel;
    @FXML
    private ImageView card1Image;
    @FXML
    private ImageView card2Image;
    @FXML
    private ImageView card3Image;
    @FXML
    private Button duelStatsButton;
    @FXML
    private Button homeButton;

    public void initializeData(boolean addingEvent){
        this.addingEvent = addingEvent;
        if(!addingEvent){
            homeButton.setText("Return to Home");
            duelStatsButton.setText("See Event stats");
        }

        //stub for now
    }

    public void onHomeClick(ActionEvent actionEvent){
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
    }

    public void onDuelStatsClick(ActionEvent actionEvent) throws IOException {
        //TODO: (UD3) Save selections and send them back
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InitialView.class.getResource("stats-input.fxml"));
        Parent statsParent = loader.load();

        StatsInput statsInput = loader.getController();
        //TODO: unstub this
        statsInput.initializeData(new RewardEvent(),addingEvent);

        Scene opponentInputScene = new Scene(statsParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(opponentInputScene);
        window.show();
    }
}
