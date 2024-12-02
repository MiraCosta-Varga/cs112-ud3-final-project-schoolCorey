package cs112.ud3.controllers;

import cs112.ud3.InitialView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class StatsInput {

    public static final int SPINNER_MIN = 0;
    public static final int SPINNER_MAX = 59;

    private boolean addingEvent;

    @FXML
    private TextField repTextField;
    @FXML
    private TextField creaturesYTextField;
    @FXML
    private TextField creaturesOTextField;
    @FXML
    private TextField sheidlsYTextField;
    @FXML
    private  TextField shieldsOTextField;
    @FXML
    private  TextField mWinsYTextField;
    @FXML
    private TextField mWinsOTextField;
    @FXML
    private  TextField tWinsYTextField;
    @FXML
    private TextField tWinsOTextField;
    @FXML
    private  TextField minuteTextField;
    @FXML
    private  TextField secondTextField;
    @FXML
    private  Button backButton;
    @FXML
    private  Button cancelButton;
    @FXML
    private   Button confirmButton;


    public void initializeData(boolean addingEvent){
        this.addingEvent = addingEvent;
        if(!addingEvent){
            repTextField.setEditable(false);
            creaturesOTextField.setEditable(false);
            creaturesYTextField.setEditable(false);
            sheidlsYTextField.setEditable(false);
            shieldsOTextField.setEditable(false);
            mWinsOTextField.setEditable(false);
            mWinsYTextField.setEditable(false);
            tWinsOTextField.setEditable(false);
            tWinsYTextField.setEditable(false);
            minuteTextField.setEditable(false);
            secondTextField.setEditable(false);
            cancelButton.setVisible(false);
            backButton.setVisible(false);

        }
        //I hope StackOverflow knows what the hell they're doing

        //Start by making all the text fields accept integers only
        repTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        creaturesOTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        creaturesYTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        sheidlsYTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        shieldsOTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        mWinsOTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        mWinsYTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        tWinsOTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        tWinsYTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        minuteTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        secondTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        //TODO: (UD3) send back Cards selected from previous page and refill it on that page. Maybe another initializeData()?
        Parent cardInput = FXMLLoader.load(InitialView.class.getResource("card-reward-input.fxml"));
        Scene cardInputScene = new Scene(cardInput);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(cardInputScene);
        window.show();
    }

    public void onCancelClick(ActionEvent actionEvent) throws IOException{
        Stage thisStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InitialView.class.getResource("cancel-confirmation.fxml"));
        Parent cancelParent = loader.load();

        CancelConfirmation cancelConfirmation = loader.getController();
        cancelConfirmation.initializeData(thisStage);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(cancelParent));
        stage.setResizable(false);
        stage.show();
    }

    public void onNextClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InitialView.class.getResource("confirm-page.fxml"));
        Parent confirmParent = loader.load();

        ConfirmPage confirmPage = loader.getController();
        confirmPage.initializeData(addingEvent);

        Scene confirmScene = new Scene(confirmParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(confirmScene);
        window.show();
    }

}
