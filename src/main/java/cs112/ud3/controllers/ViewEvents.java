package cs112.ud3.controllers;

import cs112.ud3.InitialView;
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

public class ViewEvents {

    @FXML
    private ComboBox eventChoiceComboBox; //TODO: (UD3)Make it a combo box of reward events
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;

    public void initializeData(){
        //stub
    }

    public void onBackClick(ActionEvent actionEvent){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public void onNextClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InitialView.class.getResource("confirm-page.fxml"));
        Parent confirmParent = loader.load();

        ConfirmPage confirmPage = loader.getController();
        confirmPage.initializeData(false);

        Scene confirmScene = new Scene(confirmParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(confirmScene);
        window.show();
    }


}
