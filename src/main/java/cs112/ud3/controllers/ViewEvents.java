package cs112.ud3.controllers;
/**
 * Allows the user to select which event they would like to view via a ComboBox.
 * Events are differentiated by the time (local system time) at which they were added to the
 * database from the confirm page.
 * Hitting the next button will allow the user to view the Confirm Page of the chosen event, and from there
 * the StatsInput Page, to see all of the event's info without being able to edit the event or re-add it to the database
 */

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

    /**
     * Populates the database RewardEvent ComboBox with an array list of RewardEvents (taken from
     * file input on the InitialView scene)
     * @param database an ArrayList of every RewardEvent that has been added to the database (created on HelloController's onViewDataClick() )
     */
    public void initializeData(ArrayList<RewardEvent> database){
        //stub
        this.database = database;
        for(RewardEvent event: database){
            eventChoiceComboBox.getItems().add(event);
        }
    }

    /**
     * Takes the user back to the initial view by closing this stage
     * @param actionEvent the event (back button action) which causes the method to fire
     */
    public void onBackClick(ActionEvent actionEvent){
        if (actionEvent.getSource()==backButton){
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        }

    }

    /**
     * Takes the user to the ConfirmPage of the chosen event, but lets the confirmPage know that the
     * user is just viewing the event and shouldn't be able to modify it.
     * @param actionEvent the event (back button action) which causes the method to fire
     * @throws IOException for FMXLLoader .load()
     */
    public void onNextClick(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource()==nextButton){
            RewardEvent selection = eventChoiceComboBox.getSelectionModel().getSelectedItem();

            if (selection!= null){
                UtilityBelt.changeInputScene("confirm-page.fxml",selection,false,actionEvent);
            }else {
                UtilityBelt.createMessagePopup("No event chosen.\nPlease make a selection.");
            }
        }


    }


}
