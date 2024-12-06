package cs112.ud3.controllers;

import cs112.ud3.Exceptions.OpponentNotValidException;
import cs112.ud3.Exceptions.UninitializedLinkException;
import cs112.ud3.UtilityBelt;
import cs112.ud3.models.DMOpponent;
import cs112.ud3.models.OpponentLink;
import cs112.ud3.models.RewardEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class OpponentInput extends InputScreen{

    public static final int NOT_FOUND = -1;

    RewardEvent rewardEvent;
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
    @Override
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
        this.rewardEvent = event;
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

    public void onNextClick(ActionEvent actionEvent) throws IOException{

        if(amAddingEvent){
            OpponentLink link = new OpponentLink();
            try{
                //Check if opponent is valid
                String name = nameComboBox.getSelectionModel().getSelectedItem();
                String location = locationComboBox.getSelectionModel().getSelectedItem();
                DMOpponent opponent = link.linkOpponent(name,location);
                rewardEvent.setOrigin(opponent);

                UtilityBelt.changeInputScene("card-reward-input.fxml",rewardEvent,amAddingEvent,actionEvent);

            }catch (OpponentNotValidException onve){
                UtilityBelt.createMessagePopup(onve.getMessage());
            }catch (UninitializedLinkException ule){
                System.err.println(ule.getMessage());
            }

        }else{
            UtilityBelt.changeInputScene("card-reward-input.fxml",rewardEvent,amAddingEvent,actionEvent);
        }


    }
}
