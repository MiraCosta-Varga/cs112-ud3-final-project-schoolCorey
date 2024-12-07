package cs112.ud3.controllers;
/**
 * Controller for InitialView.
 * Is a simple intro screen with buttons to let the user either begin adding a new
 * RewardEvent or view various RewardEvents
 */

import cs112.ud3.InitialView;
import cs112.ud3.UtilityBelt;
import cs112.ud3.models.RewardEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button addEventButton;
    @FXML
    private Button viewDataButton;

    /**
     * Creates a new RewardEvent, and sets up an OppentInput scene with the new event, letting it know
     * that the user is adding (not viewing) a new RewardEvent.
     * @throws IOException for MXMLLoader.load()
     */
    @FXML
    protected void onAddEventButtonClick(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource()==addEventButton){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InitialView.class.getResource("opponent-input.fxml"));
            Parent opponentInputParent = loader.load();

            OpponentInput opponentInput = loader.getController();
            RewardEvent event = new RewardEvent();
            opponentInput.initializeData(event,true);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(opponentInputParent));
            stage.setResizable(false);
            stage.show();
        }

    }

    /**
     * Tries to open the eventDatabase file and create an ArrayList of RewardEvents from it.
     * If the file is missing or fails for some other reason, it creates a popup informing the
     * user of the issue.
     * If the file is successfully read to the end, then it will set up a viewEvents scene and send
     * it the ArrayList which had been created.
     * @param actionEvent the event (viewDataButton action) which caused the method to fire
     * @throws IOException for FXMLLoader.load()
     */
    public void onViewDataClick(ActionEvent actionEvent) throws IOException{
        if(actionEvent.getSource()==viewDataButton){
            ArrayList<RewardEvent> database = new ArrayList<>();
            //trying the input stream stuff
            ObjectInputStream inputStream = null;
            try{
                inputStream = new ObjectInputStream(new FileInputStream("src/main/resources/cs112/ud3/eventDatabase.dat"));
                while (true){
                    RewardEvent nextEvent = (RewardEvent) inputStream.readObject();
                    database.add(nextEvent);
                }
            }catch (EOFException e){
                System.out.println("End of file reached");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(InitialView.class.getResource("view-events.fxml"));
                Parent viewEventsParent = loader.load();

                ViewEvents viewEvents = loader.getController();
                viewEvents.initializeData(database);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(viewEventsParent));
                stage.setResizable(false);
                stage.show();
            }catch (FileNotFoundException fnfe){
                UtilityBelt.createMessagePopup("File not yet created.\n please add events before trying to view.");
            }catch (IOException ioe){
                UtilityBelt.createMessagePopup("Failed to open file.");
            }catch (ClassNotFoundException cnfe){
                UtilityBelt.createMessagePopup("Tried to load object for unrecognized class");
            }
        }



    }


}