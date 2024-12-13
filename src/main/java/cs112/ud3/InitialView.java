package cs112.ud3;
/**
 * The screen the application starts at. Controller is HelloController
 * Also calls the createValidArray() methods of ValidLink classes at the
 * earliest possible opportunity in the program.
 */

import cs112.ud3.models.CardLink;
import cs112.ud3.models.OpponentLink;
import cs112.ud3.models.RewardEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InitialView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        CardLink cardLink = new CardLink();
        cardLink.createValidArray();
        OpponentLink opponentLink = new OpponentLink();
        opponentLink.createValidArray();

        //Original initial view stuff
        FXMLLoader fxmlLoader = new FXMLLoader(InitialView.class.getResource("initial-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 320);

        stage.setTitle("Event Reward Tracker!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}