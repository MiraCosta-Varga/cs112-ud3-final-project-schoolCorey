package cs112.ud3.controllers;

import cs112.ud3.InitialView;
import cs112.ud3.UtilityBelt;
import cs112.ud3.models.DMCard;
import cs112.ud3.models.RewardEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmPage extends InputScreen{
    public static final int CARD_1_INDEX = 0;
    public static final int CARD_2_INDEX = 1;
    public static final int CARD_3_INDEX = 2;

    private boolean amAddingEvent;
    private RewardEvent rewardEvent;


    //setup GUi
    @FXML
    private Label repPointsLabel;
    @FXML
    private Label opponentLabel;
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

    @Override
    public void initializeData(RewardEvent rewardEvent, boolean addingEvent){

        this.amAddingEvent = addingEvent;
        this.rewardEvent = rewardEvent;
        if(!addingEvent){
            homeButton.setText("Return to Home");
            duelStatsButton.setText("See Event stats");
        }
        repPointsLabel.setText(Integer.toString(rewardEvent.getCurrency()));
        opponentLabel.setText(rewardEvent.getOrigin().toString());
        DMCard[] rewards = rewardEvent.getItemDrops();
        //TODO: add images
        for(int i = 0; i < rewards.length; i++){
            ImageView currentImage;
            switch (i){
                case CARD_1_INDEX:
                    currentImage = card1Image;
                    break;
                case CARD_2_INDEX:
                    currentImage = card2Image;
                    break;
                case CARD_3_INDEX:
                    currentImage = card3Image;
                    break;
                default:
                    currentImage = null;
            }
            DMCard currentCard = rewards[i];
            int idNum = currentCard.getIdNum();
            String selection = Integer.toString(idNum);
            if(idNum>-1) {
                String filepath = "/cs112/ud3/cardImages/" + selection + ".png";
                Image newImage = new Image(getClass().getResourceAsStream(filepath));
                currentImage.setImage(newImage);

            }
        }

    }

    public void onHomeClick(ActionEvent actionEvent){
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
    }

    public void onDuelStatsClick(ActionEvent actionEvent) throws IOException {
        UtilityBelt.changeInputScene("stats-input.fxml",rewardEvent,amAddingEvent,actionEvent);
    }
}
