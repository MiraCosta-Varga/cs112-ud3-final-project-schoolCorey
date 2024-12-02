package cs112.ud3;

import cs112.ud3.models.CardLink;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InitialView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //TODO:(UD3) Setup validlink classes, database files, etc.
        CardLink cardLink = new CardLink();
        cardLink.createValidArray();
        //Original initial view stuff
        FXMLLoader fxmlLoader = new FXMLLoader(InitialView.class.getResource("initial-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 320);



        //Testing card input screen in main
        //FXMLLoader fxmlLoader = new FXMLLoader(InitialView.class.getResource("card-reward-input.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 600, 400);


        stage.setTitle("Event Reward Tracker!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}