package controllers;

import java.io.File;

import config.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SettingsViewController {

	private Stage _stage;
	public SettingsViewController(Stage stage) {
		_stage = stage;
	}
	
    @FXML
    private Label lbl_folderName;

    @FXML
    private Button btn_changeFolder;
    
    @FXML
    void initialize() {
    	lbl_folderName.setText(Settings.getDestinationFolder() != null ? Settings.getDestinationFolder().getPath() : "<No Folder>");
    }
    
    @FXML
    void onChangeFolderButtonPressed(ActionEvent event) {
    	DirectoryChooser folderDialog = new DirectoryChooser();
    	folderDialog.setTitle("Choose a destination for all downloaded files");
    	File chosen = folderDialog.showDialog(_stage);
    	if (chosen != null) {
    		lbl_folderName.setText(chosen.getPath());
    		Settings.setDestinationFolder(chosen);
    	}
    }

}