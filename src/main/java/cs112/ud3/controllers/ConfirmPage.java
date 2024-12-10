package cs112.ud3.controllers;
/**
 * Shows the user the Opponent, Rep points, and card rewards of the current reward event.
 * If the user is adding an event, allows them to add the event to the database.
 * Button labels change based on whether the user is adding an event or viewing them,
 * to make it more clear what the function of the results would be.
 * The buttons still lead to the same scenes either way.
 */

import cs112.ud3.NoHeaderObjectOutputStream;
import cs112.ud3.UtilityBelt;
import cs112.ud3.models.CardLink;
import cs112.ud3.models.DMCard;
import cs112.ud3.models.RewardEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

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
    private Label card1Label;
    @FXML
    private Label card2Label;
    @FXML
    private Label card3Label;
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
        int[] rewards = rewardEvent.getItemDrops();
        for(int i = 0; i < rewards.length; i++){
            ImageView currentImage;
            Label currentLabel;
            switch (i){
                case CARD_1_INDEX:
                    currentImage = card1Image;
                    currentLabel = card1Label;
                    break;
                case CARD_2_INDEX:
                    currentImage = card2Image;
                    currentLabel = card2Label;
                    break;
                case CARD_3_INDEX:
                    currentImage = card3Image;
                    currentLabel = card3Label;
                    break;
                default:
                    currentImage = null;
                    currentLabel = null;
            }
            DMCard currentCard = CardLink.linkCardFromID(rewards[i]);
            int idNum = currentCard.getIdNum();
            String selection = Integer.toString(idNum);
            if(idNum>-1) {
                String filepath = "/cs112/ud3/cardImages/" + selection + ".png";
                Image newImage = new Image(getClass().getResourceAsStream(filepath));
                currentImage.setImage(newImage);
                currentLabel.setText(currentCard.toString());

            }
        }

    }

    /**
     * Finishes the viewing/creation of this RewardEvent.
     * If the user is adding an event,
     * it first sets the last instance var of the RewardEvent, timeCreated. It then
     * looks for the eventDatabase.dat file, creating it if it is missing.
     * It uses ObjectOutputStream to append the current RewardEvent to the file,
     * and then creates a popup informing the user of the successful addition of the event.
     *
     * The method then closes the stage, returning the user to the initial view.
     * @param actionEvent the actionEvent which caused the method to be fired
     * @throws IOException needed for FXML.load(), thrown if there are issues with our .fxml files
     */
    public void onHomeClick(ActionEvent actionEvent)throws IOException{
        if (actionEvent.getSource()==homeButton){


            //save RewardEvent to database if adding event
            //hopefully the writeUnshared() fixes the issues I was having??
            if(amAddingEvent){
                try{
                    //set reward event time here before saving
                    rewardEvent.initializeTimeCreated();
                    if (rewardEvent.isValid()){
                        File eventDatabase = new File("src/main/resources/cs112/ud3/eventDatabase.dat");
                        if(eventDatabase.createNewFile()){
                            System.out.println("database file created.");
                        }
                        //New stuff that hopefully works
                        if(eventDatabase.length() == 0){
                            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(eventDatabase,true));
                            outputStream.writeUnshared(rewardEvent);
                            outputStream.close();
                        }else {
                            NoHeaderObjectOutputStream outputStream = new NoHeaderObjectOutputStream(new FileOutputStream(eventDatabase,true));
                            outputStream.writeUnshared(rewardEvent);
                            outputStream.close();
                        }
                        UtilityBelt.createMessagePopup("Event Added!");
                    }else {
                        UtilityBelt.createMessagePopup("Current reward event contains invalid data.");
                    }

                }catch (IOException ioe){
                    System.out.println("Failed to open binary file.");
                }

            }

            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.close();
        }

    }

    /**
     * Sends the user to the Stats input screen, passing on information about the
     * current RewardEvent and whether the user is adding or viewing an event.
     * @param actionEvent the event (duelStatsButton action) causing the method to be fired.
     * @throws IOException needed for FXML.load(), thrown if there are issues with our .fxml files
     */
    public void onDuelStatsClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource()==duelStatsButton){
            UtilityBelt.changeInputScene("stats-input.fxml",rewardEvent,amAddingEvent,actionEvent);
        }

    }
}
