package cs112.ud3.controllers;

import cs112.ud3.InitialView;
import cs112.ud3.models.CardLink;
import cs112.ud3.models.DMCard;
import cs112.ud3.models.DMCreature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CardRewardInput {
    //Setup that would really be from another class
    /*
    public static final DMCard[] testAllPossibleCards =  {new DMCreature("Belbetphlo, Wailing Shadow", DMCard.CIVILIZATION_DARKNESS,DMCard.CARDTYPE_CREATURE,"Slayer",DMCard.RARITY_COMMON,3,false,1000,DMCreature.RACE_GHOST,false,0),
            new DMCreature("Bloody Squito",DMCard.CIVILIZATION_DARKNESS,DMCard.CARDTYPE_CREATURE,"Blocker\nCan't Attack\nAfter this creature battles, destroy it.",DMCard.RARITY_COMMON,2,false,4000,DMCreature.RACE_GEL_FISH,false,1),
            new DMCreature("Alpha Squito",DMCard.CIVILIZATION_DARKNESS,DMCard.CARDTYPE_CREATURE,"Blocker\nCan't Attack\nAfter this creature battles, destroy it.",DMCard.RARITY_COMMON,2,false,4000,DMCreature.RACE_GEL_FISH,false,1)};
    */

    public static final int CARD_1_INDEX = 0;
    public static final int CARD_2_INDEX = 1;
    public static final int CARD_3_INDEX = 2;

    //used to hold onto our combo box selections until we confirm our choices.
    private DMCard[] selectedCards = new DMCard[3];

    //GUI components
    @FXML
    private ComboBox<DMCard> cardRewardComboBox1 = new ComboBox<>();
    @FXML
    private ComboBox<DMCard> cardRewardComboBox2 = new ComboBox<>();
    @FXML
    private ComboBox<DMCard> cardRewardComboBox3 = new ComboBox<>();
    @FXML
    private ImageView card1image = new ImageView();
    @FXML
    private ImageView card2image = new ImageView();
    @FXML
    private ImageView card3image = new ImageView();
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private Button cancelButton;

    //TODO: move image view set to when enter/tab is pressed, so that it doesn't lag as you're typing.



    public void initializeData(){
        //TODO: Make alphabetically ordered version
        CardLink link = new CardLink();
        for(DMCard card: link.getValidCards()){
            cardRewardComboBox1.getItems().add(card);
            cardRewardComboBox3.getItems().add(card);
            cardRewardComboBox2.getItems().add(card);
        }
    }

    //ok, so I guess enter is consumed on the action event or something?
    @FXML
    public void keyPressed(KeyEvent keyEvent){
        ComboBox<DMCard> currentBox = null;
        if(keyEvent.getSource().equals(cardRewardComboBox1)){
            currentBox = cardRewardComboBox1;

        }else if(keyEvent.getSource().equals(cardRewardComboBox2)){
            currentBox = cardRewardComboBox2;
        }
        if(keyEvent.getSource().equals(cardRewardComboBox3)){
            currentBox = cardRewardComboBox3;
        }
        if((!(currentBox == null))){
            TextField currentEditor = currentBox.getEditor();
            String currentText = currentEditor.getText();
            for (int i=0; i<currentBox.getItems().size(); i++){
                if(currentBox.getItems().get(i) == null){
                    int mikon = 359; //Debug stub
                }
                if(currentBox.getItems().get(i).getName().toLowerCase().contains(currentText.toLowerCase())){
                    currentBox.getSelectionModel().select(i);
                    if(keyEvent.getCode()!=KeyCode.ENTER){
                        currentEditor.setText(currentText);
                        currentEditor.positionCaret(currentEditor.getText().length());
                    }else {
                        currentEditor.setText(currentBox.getSelectionModel().getSelectedItem().getName());
                    }
                    currentBox.show();
                }
            }
        }
    }

    /**
     * Event that fires whenever an action is made in the combo box. Because this fires on
     * selection and our keyPressed event causes automatic selection, this is fired from a
     * key press event as well.
     * It will update the image for the current card to be the current selection, and update
     * selectedIndices with the current selection so we can refer to that single array when we
     * move forward and need to create the cards.
     * Becasue our image files are named corresponding to their number in the ValidCards array,
     * we can get the image path for the correct card programmatically based on the combo
     * box's index of the current selection.
     * @param actionEvent event fired when text field of combo box changes
     */
    @FXML
    public void onComboBoxAction(ActionEvent actionEvent){
        ComboBox<DMCard> currentBox = null;
        if(actionEvent.getSource().equals(cardRewardComboBox1)){
            specificComboBoxAction(cardRewardComboBox1,cardRewardComboBox2,card1image,CARD_1_INDEX);
        } else if (actionEvent.getSource().equals(cardRewardComboBox2)) {
            specificComboBoxAction(cardRewardComboBox2,cardRewardComboBox3,card2image,CARD_2_INDEX);
        } else if (actionEvent.getSource().equals(cardRewardComboBox3)) {
            specificComboBoxAction(cardRewardComboBox3,nextButton,card3image,CARD_3_INDEX);
        }

    }

    //True if selection changes, false if it doesn't
    /**
     * Checks the currently-inputted value of the selected ComboBox, and automaticlaly selects an
     * item from the box which matches the string. If fired from a keyEvent, effectively becomes
     * an autocomplete function.
     * @param currentBox the comboBox to select from
     * @return True if the selection changes from what it previously was, false if it does not
     */
    private boolean comboBoxAutocomplete(ComboBox<DMCard> currentBox){
        TextField currentEditor = currentBox.getEditor();
        int currentIndex = currentBox.getSelectionModel().getSelectedIndex();
        String currentText = currentEditor.getText();
        for (int i=0; i<currentBox.getItems().size(); i++){
            if(currentBox.getItems().get(i).getName().toLowerCase().contains(currentText.toLowerCase())){
                currentBox.getSelectionModel().select(i);
                currentEditor.setText(currentText);
                currentEditor.positionCaret(currentEditor.getText().length());
            }
        }
        return currentIndex != currentBox.getSelectionModel().getSelectedIndex();
    }

    private void specificComboBoxAction(ComboBox<DMCard> currentBox, Node nextFocus, ImageView currentImage, int rewardIndex){
        currentBox = currentBox;// = cardRewardComboBox1;
        if(comboBoxAutocomplete(currentBox)){
            nextFocus.requestFocus();
        }
        try{
            DMCard currentCard = currentBox.getSelectionModel().getSelectedItem();
            int idNum = currentCard.getIdNum();
            String selection = Integer.toString(idNum);
            if(idNum>-1){
                String filepath = "/cs112/ud3/cardImages/" +selection+".png";
                //Testing new above, old below
                //String filepath = "/cardImages/"+selection+".png";
                Image newImage = new Image(getClass().getResourceAsStream(filepath));
                currentImage.setImage(newImage);
                selectedCards[rewardIndex] = currentCard;

            }
        }catch (ClassCastException cce){
            System.out.println("Misspelling");
        }catch (NullPointerException npe){
            //System.out.println("Nullpo. Should only be caused by no images yet, but please check.");
        }

    }

    //TODO: (UD3)change button functions depending on whether this scene came from addevents or viewevents
    //Buttons
    @FXML
    void onBackClick(ActionEvent actionEvent) throws IOException {
        //TODO: (UD3)send back Opponent selected from previous page and refill it on that page. Maybe another initializeData()?
        Parent opponentInput = FXMLLoader.load(InitialView.class.getResource("opponent-input.fxml"));
        Scene opponentInputScene = new Scene(opponentInput);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(opponentInputScene);
        window.show();
    }

    @FXML
    void onCancelClick(ActionEvent actionEvent) throws IOException{
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

    @FXML
    void  onNextClick(ActionEvent actionEvent) throws  IOException{
        //TODO: (UD3) Make sure all cards are selected before continuing

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InitialView.class.getResource("stats-input.fxml"));
        Parent statsParent = loader.load();

        StatsInput statsInput = loader.getController();
        statsInput.initializeData(true);

        Scene opponentInputScene = new Scene(statsParent);
        // initializeData() goes here
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(opponentInputScene);
        window.show();

    }




}
