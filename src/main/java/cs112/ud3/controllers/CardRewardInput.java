package cs112.ud3.controllers;
/**
 * Uses combo boxes of DMCards with editable TextFields to allow the user to input
 * the cards gained as rewards. Includes (somewhat scuffed) autocomplete functionality
 * and uses card images as visual feedback so the user can tell they chose the right card.
 */

import cs112.ud3.Exceptions.CardNotValidException;
import cs112.ud3.Exceptions.UninitializedLinkException;
import cs112.ud3.UtilityBelt;
import cs112.ud3.models.CardLink;
import cs112.ud3.models.DMCard;
import cs112.ud3.models.RewardEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class CardRewardInput extends InputScreen{
    /***CONSTANTS***/
    public static final int CARD_1_INDEX = 0;
    public static final int CARD_2_INDEX = 1;
    public static final int CARD_3_INDEX = 2;
    public static final int NOT_FOUND = -1;
    public static final int CARDS_PER_EVENT = 3;

    /***INSTANCE VARS***/
    //used to hold onto our combo box selections until we confirm our choices.
    private DMCard[] selectedCards = new DMCard[CARDS_PER_EVENT];
    private RewardEvent rewardEvent;
    private boolean amAddingEvent;

    /***GUI components***/
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

    /**
     * Sets up scence before it is shown. Populates combo boxes with valid choices, updates them to match
     * the event which has been passed if applicable, and sets whether the user is adding an event or not,
     * disabling the combo boxes if they are not.
     * @param rewardEvent The current event that is being tracked in this window.
     * @param amAddingEvent true if the user is currently adding an event, false if they are viewing an event.
     */
    @Override
    public void initializeData(RewardEvent rewardEvent, boolean amAddingEvent){

        this.rewardEvent = rewardEvent;
        this.amAddingEvent = amAddingEvent;

        //TODO: Make alphabetically ordered version
        CardLink link = new CardLink();
        for(DMCard card: link.getValidCards()){
            cardRewardComboBox1.getItems().add(card);
            cardRewardComboBox3.getItems().add(card);
            cardRewardComboBox2.getItems().add(card);
        }

        //copies card drops if they exist
        DMCard[] drops = rewardEvent.getItemDrops();
        if(drops!=null){
            for (int i = 0; i < drops.length; i++){
                DMCard original = drops[i];
                selectedCards[i] = original;
                if(original!=null){
                    int index;
                    ComboBox<DMCard> currentBox = null;
                    ImageView currentImage = null;
                    switch (i){
                        case CARD_1_INDEX:
                            index = findCardInComboBox(cardRewardComboBox1,original);
                            if (index!=-1){
                                cardRewardComboBox1.getSelectionModel().select(index);
                                currentBox = cardRewardComboBox1;
                                currentImage = card1image;
                            }
                            break;
                        case CARD_2_INDEX:
                            index = findCardInComboBox(cardRewardComboBox2,original);
                            if (index!=-1){
                                cardRewardComboBox2.getSelectionModel().select(index);
                                currentBox = cardRewardComboBox2;
                                currentImage = card2image;
                            }
                            break;
                        case CARD_3_INDEX:
                            index = findCardInComboBox(cardRewardComboBox3,original);
                            if (index!=-1){
                                cardRewardComboBox3.getSelectionModel().select(index);
                                currentBox = cardRewardComboBox3;
                                currentImage = card3image;
                            }
                            break;
                        default: break;
                    }
                    if(currentBox!=null){
                        try{
                            DMCard currentCard = currentBox.getSelectionModel().getSelectedItem();
                            int idNum = currentCard.getIdNum();
                            String selection = Integer.toString(idNum);
                            if(idNum>-1){
                                String filepath = "/cs112/ud3/cardImages/" +selection+".png";
                                Image newImage = new Image(getClass().getResourceAsStream(filepath));
                                currentImage.setImage(newImage);
                            }
                        }catch (ClassCastException cce){
                            System.out.println("Misspelling");
                        }catch (NullPointerException npe){
                            System.out.println("Nullpo. Probably caused by a missing image file.");
                        }
                    }

                }
            }
        }

        if(!amAddingEvent){
            cardRewardComboBox1.setDisable(true);
            cardRewardComboBox2.setDisable(true);
            cardRewardComboBox3.setDisable(true);
        }

    }

    //ok, so I guess enter is consumed on the action event or something?

    /**
     * Checks if the key was pressed while a ComboBox was focused, and if it was,
     * automatically selects a card which matches the currently inputted text.
     * If enter was pressed, automatically fills in the rest of the name of the
     * matching card in the box's TextField.
     * @param keyEvent the keyEvent that triggers the method.
     */
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
                    if(keyEvent.getCode()!=KeyCode.ENTER ){ //&& keyEvent.getCode()!= KeyCode.TAB
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

    /**
     * Called from onComboBoxAction, takes params to update components of specific boxes. Autocompletes, and updates the
     * image corresponding to the current box if enter is pressed.
     * @param currentBox the combo box that the action was taken in
     * @param nextFocus the next component to request focus when this box is completed. Usually the next combo box, but
     *                  should be the next button if you're on box 3
     * @param currentImage the ImageView object corresponding to the combo box the action was taken in
     * @param rewardIndex the index number of the selectedCards array that corresponds to the combo box that the action was taken in.
     *                    see CARD_X_INDEX constants; example: CARD_1_INDEX is for the card linked to cardRewardComboBox1
     */
    private void specificComboBoxAction(ComboBox<DMCard> currentBox, Node nextFocus, ImageView currentImage, int rewardIndex){
        if(comboBoxAutocomplete(currentBox)){
            nextFocus.requestFocus();
            try{
                DMCard currentCard = currentBox.getSelectionModel().getSelectedItem();
                int idNum = currentCard.getIdNum();
                String selection = Integer.toString(idNum);
                if(idNum>-1){
                    String filepath = "/cs112/ud3/cardImages/" +selection+".png";
                    Image newImage = new Image(getClass().getResourceAsStream(filepath));
                    currentImage.setImage(newImage);
                    selectedCards[rewardIndex] = currentCard;
                }
            }catch (ClassCastException cce){
                System.out.println("Misspelling");
            }catch (NullPointerException npe){
                System.out.println("Nullpo. Probably caused by a missing image file.");
            }
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
        findInComboBox(currentBox,currentText);
        return currentIndex != currentBox.getSelectionModel().getSelectedIndex();
    }

    /**
     * Takes a string and checks if a DMCard combo box contains a card corresponding to that string.
     * @param currentBox The box to search for.
     * @param textToFind The text to search for (the name of the DMCard or a portion of its name)
     * @return true if corresponding card is found in the combo box, false if it is not.
     */
    private boolean findInComboBox(ComboBox<DMCard> currentBox, String textToFind){
        TextField currentEditor = currentBox.getEditor();
        for (int i=0; i<currentBox.getItems().size(); i++){
            if(currentBox.getItems().get(i).getName().toLowerCase().contains(textToFind.toLowerCase())){
                currentBox.getSelectionModel().select(i);
                ComboBoxListViewSkin<?> currentSkin = (ComboBoxListViewSkin<?>) currentBox.getSkin();
                ListView<?> list = (ListView<?>) currentSkin.getPopupContent();
                list.scrollTo(i);
                currentEditor.setText(textToFind);
                currentEditor.positionCaret(currentEditor.getText().length());
                return true;
                //return currentIndex != currentBox.getSelectionModel().getSelectedIndex();
            }
        }
        return false; //not found
    }

    /**
     * Checks if a combo box contains the specified card, and returns the index if it is.
     * @param currentBox The DMCard ComboBox to check for the specified card
     * @param cardToFind The DMCard object to look for
     * @return an int for the index of currentBox.getItems() where the card is, or -1 if it is not found in the box.
     */
    private int findCardInComboBox(ComboBox<DMCard> currentBox, DMCard cardToFind){
        for(int i = 0; i < currentBox.getItems().size(); i++){
            DMCard check = currentBox.getItems().get(i);
            if(check.equals(cardToFind)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /*** SCENE CHANGE BUTTONS ***/

    /**
     * Sends the user to the previous scene, and also sends the information of the current RewardEvent
     * and whether or not the user is adding an event to the new scene
     * @param actionEvent the event (clicking the back button) which causes the trigger
     * @throws IOException required by FXMLLoader .load() in UtilityBelt.changeInputScene
     */
    @FXML
    public void onBackClick(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource()==backButton){
            UtilityBelt.changeInputScene("opponent-input.fxml",rewardEvent,amAddingEvent,actionEvent);
        }

    }

    /**
     * Creates a popup that lets the user cancel adding the event entirely, closing the windows and
     * discarding changes to RewardEvents, and returning to the original stage.
     * @param actionEvent the event (clicking the back button) which causes the trigger
     * @throws IOException required by FXMLLoader .load() in UtilityBelt.changeInputScene
     */
    @FXML
    public void onCancelClick(ActionEvent actionEvent) throws IOException{
        if(actionEvent.getSource()==cancelButton){
            UtilityBelt.createCancelPopup(actionEvent);
        }

    }

    /**
     * Sends the user to the next scene, and also sends the information of the current RewardEvent
     * and whether or not the user is adding an event to the new scene
     * @param actionEvent the event (clicking the back button) which causes the trigger
     * @throws IOException required by FXMLLoader .load() in UtilityBelt.changeInputScene
     */
    @FXML
    void  onNextClick(ActionEvent actionEvent) throws  IOException{
        if (actionEvent.getSource()==nextButton){
            if(amAddingEvent){
                try {
                    //try adding cards to event
                    CardLink link = new CardLink();
                    for(int i = 0; i <CARDS_PER_EVENT; i++ ){
                        if(!link.objectIsValid(selectedCards[i])){
                            throw new CardNotValidException(i+1);
                        }
                        rewardEvent.addDrop(selectedCards[i],i);
                    }

                    UtilityBelt.changeInputScene("stats-input.fxml",rewardEvent,amAddingEvent,actionEvent);

                }catch (UninitializedLinkException ule){
                    System.err.println(ule.getMessage());
                }catch (CardNotValidException cnve){
                    UtilityBelt.createMessagePopup(cnve.getMessage());
                }
            }else {
                UtilityBelt.changeInputScene("stats-input.fxml",rewardEvent,amAddingEvent,actionEvent);
            }
        }


    }

}
