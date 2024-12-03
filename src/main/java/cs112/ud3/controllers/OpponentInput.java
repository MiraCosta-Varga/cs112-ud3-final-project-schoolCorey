package cs112.ud3.controllers;

import cs112.ud3.Exceptions.OpponentNotValidException;
import cs112.ud3.InitialView;
import cs112.ud3.models.DMOpponent;
import cs112.ud3.models.OpponentLink;
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

public class OpponentInput {

    public static final int NOT_FOUND = -1;

    RewardEvent event;
    boolean amAddingEvent;

    //Setup GUI components
    @FXML
    ComboBox<String> locationComboBox;
    @FXML
    ComboBox<String> nameComboBox;
    @FXML
    Button backButton;
    @FXML
    Button nextButton;

    /**
     * Sets up the scene before it pops up. Fills combo boxes with proper choices,
     * tracks status of inputs, keeps the current RewardEvent updated
     * @param event this specific reward event. Is passed between scenes and referenced/updated by them when necessary
     * @param amAddingEvent True if the user is adding an event, False if they are not. Used to modify the scene so that
     *                      the reward event can only be viewed, not changed, if they are not adding an event.
     */
    public void initializeData(RewardEvent event, boolean amAddingEvent){

        //Populate combo boxes
        for(String location: DMOpponent.VALID_LOCATIONS){
            locationComboBox.getItems().add(location);
        }
        for(DMOpponent possibleOpponent : OpponentLink.knownOpponents){
            String name = possibleOpponent.getName();
            nameComboBox.getItems().add(name);
        }

        //auto-select RewardEvent Info
        this.event = event;
        DMOpponent origin = event.getOrigin();
        if(origin!=null){
            //Set location
            String location = origin.getLocation();
            //Set name
            int index = findInComboBox(location,locationComboBox);
            if (index!=-1){
                locationComboBox.getSelectionModel().select(index);
            }
            String name = origin.getName();
            index = findInComboBox(name,nameComboBox);
            if (index!=-1){
                nameComboBox.getSelectionModel().select(index);
            }
        }

        //Disable boxes if not adding an event, update amAddingEvent
        this.amAddingEvent = amAddingEvent;
        if(!amAddingEvent){
            nameComboBox.setDisable(true);
            locationComboBox.setDisable(true);
        }

    }

    /**
     * Searches a String Combo box for a specified String and returns the index location. Ignores capitalization.
     * @param searchFor The String to search the combo box for.
     * @param boxToSearch the ComboBox in which to search for the string
     * @return The index of the ComboBox where the string is found, or -1 if the string is not found.
     */
    private int findInComboBox(String searchFor,ComboBox<String> boxToSearch){

        for (int i = 0; i < boxToSearch.getItems().size(); i++){
            if(boxToSearch.getItems().get(i).equalsIgnoreCase(searchFor)){
                return i;
            }
        }
        //if not found, returns -1
        return NOT_FOUND;
    }





    @FXML
    public void onBackClick(ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public void onNextClick(ActionEvent event) throws IOException{
        System.out.println("Next Clicked");
        System.out.println(amAddingEvent);

        if(amAddingEvent){
            OpponentLink link = new OpponentLink();
            try{
                //Check if opponent is valid
                String name = nameComboBox.getSelectionModel().getSelectedItem();
                String location = locationComboBox.getSelectionModel().getSelectedItem();
                DMOpponent opponent = link.linkOpponent(name,location);


                //Go to next scene
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
            }catch (OpponentNotValidException onve){
                //TODO: Make this a popup
                System.out.println(onve.getMessage());
            }


        }else{
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
}
