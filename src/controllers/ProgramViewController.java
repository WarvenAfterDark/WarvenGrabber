package controllers;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import config.SearchParameters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProgramViewController {

	// FXML Loading
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    // Menu
    @FXML
    private MenuItem menu_tagList;
    @FXML
    private MenuItem menu_artistList;
    @FXML
    private MenuItem menu_characterList;
    @FXML
    private MenuItem menu_settings;
    @FXML
    private MenuItem menu_about;
    @FXML
    private MenuItem menu_instructions;

    // Download items
    @FXML
    private Label lbl_currentTag;
    @FXML
    private Label lbl_beingDownloaded;
    
    // Progress
    @FXML
    private ProgressBar progressbar_progress;
    @FXML
    private Label lbl_progress;

    // Grabber checkboxes
    @FXML
    private CheckBox checkbox_artists;
    @FXML
    private CheckBox checkbox_characters;
    @FXML
    private CheckBox checkbox_tags;

    @FXML
    private Button btn_startGrabbing;

    @FXML
    void initialize() {
    	// Error Checking
        assert menu_tagList != null : "fx:id=\"menu_tagList\" was not injected: check your FXML file 'Untitled'.";
        assert menu_artistList != null : "fx:id=\"menu_artistList\" was not injected: check your FXML file 'Untitled'.";
        assert menu_characterList != null : "fx:id=\"menu_characterList\" was not injected: check your FXML file 'Untitled'.";
        assert menu_settings != null : "fx:id=\"menu_destinationFolder\" was not injected: check your FXML file 'Untitled'.";
        assert menu_about != null : "fx:id=\"menu_about\" was not injected: check your FXML file 'Untitled'.";
        assert menu_instructions != null : "fx:id=\"menu_instructions\" was not injected: check your FXML file 'Untitled'.";
        assert lbl_currentTag != null : "fx:id=\"lbl_currentTag\" was not injected: check your FXML file 'Untitled'.";
        assert lbl_beingDownloaded != null : "fx:id=\"lbl_beingDownloaded\" was not injected: check your FXML file 'Untitled'.";
        assert lbl_progress != null : "fx:id=\"lbl_progress\" was not injected: check your FXML file 'Untitled'.";
        assert progressbar_progress != null : "fx:id=\"progressbar_progress\" was not injected: check your FXML file 'Untitled'.";
        assert checkbox_artists != null : "fx:id=\"checkbox_artists\" was not injected: check your FXML file 'Untitled'.";
        assert checkbox_characters != null : "fx:id=\"checkbox_characters\" was not injected: check your FXML file 'Untitled'.";
        assert checkbox_tags != null : "fx:id=\"checkbox_tags\" was not injected: check your FXML file 'Untitled'.";
        assert btn_startGrabbing != null : "fx:id=\"btn_startGrabbing\" was not injected: check your FXML file 'Untitled'.";
    }
    
    private boolean downloadArtists;
    private boolean downloadCharacters;
    private boolean downloadTags;
    
    // Checkboxes
    @FXML
    void onCheckArtistsCheckBox(ActionEvent event) {
    	CheckBox artistCheckBox = (CheckBox)event.getSource();
    	downloadArtists = artistCheckBox.isSelected();
    }
    @FXML
    void onCheckCharactersCheckBox(ActionEvent event) {
    	CheckBox characterCheckBox = (CheckBox)event.getSource();
    	downloadCharacters = characterCheckBox.isSelected();
    }
    @FXML
    void onCheckTagsCheckBox(ActionEvent event) {
    	CheckBox tagCheckBox = (CheckBox)event.getSource();
    	downloadTags = tagCheckBox.isSelected();
    }

    // Menu events
    @FXML
    void onMenuAboutClick(ActionEvent event) {

    }
    @FXML
    void onMenuArtistListClick(ActionEvent event) {
    	openEditListView("Artists", SearchParameters.getArtists());
    }
    @FXML
    void onMenuCharacterListClick(ActionEvent event) {
    	openEditListView("Characters", SearchParameters.getCharacters());
    }
    @FXML
    void onMenuSettingClick(ActionEvent event) {
    	Stage newStage = new Stage();
    	SettingsViewController controller = new SettingsViewController(newStage);
    	Parent root = Main.loadFXML(Main.class, "SettingsView.fxml", controller);
    	Scene rootScene = new Scene(root);
    	newStage.setResizable(false);
    	newStage.setScene(rootScene);
    	newStage.initModality(Modality.APPLICATION_MODAL);
    	newStage.showAndWait();
    }
    @FXML
    void onMenuInstructionsClick(ActionEvent event) {

    }
    @FXML
    void onMenuTagListClick(ActionEvent event) {
    	openEditListView("Tags", SearchParameters.getTags());
    }

    // Buttons
    @FXML
    void onStartGrabbingButtonPressed(ActionEvent event) {

    }
    
    private void openEditListView(String listTitle, String[] listItems) {
    	ListEditViewController controller = new ListEditViewController(listTitle, listItems);
    	Parent root = Main.loadFXML(Main.class, "ListEditView.fxml", controller);
    	Scene rootScene = new Scene(root);
    	Stage newStage = new Stage();
    	newStage.setResizable(false);
    	newStage.setScene(rootScene);
    	newStage.setOnCloseRequest(value -> {
    		switch(listTitle) {
    		case "Artists":
    			SearchParameters.setArtists(controller.getItemList());
    			break;
    		case "Characters":
    			SearchParameters.setCharacters(controller.getItemList());
    			break;
    		case "Tags":
    			SearchParameters.setTags(controller.getItemList());
    			break;
    		}
    	});
    	newStage.initModality(Modality.APPLICATION_MODAL);
    	newStage.showAndWait();
    }
    
}