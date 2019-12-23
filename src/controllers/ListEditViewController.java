package controllers;

import app.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListEditViewController {
	private String[] _listItems;
	private String _listName;
	
	public ListEditViewController(String listName, String[] listItems) {
		_listItems = (listItems != null ? listItems : new String[] {});
		_listName = (listName != null ? listName : "<NO LIST NAME>");
	}
	
	@FXML
    private ListView<String> listview_listContents;
	public String[] getItemList() {
		ObservableList<String> list = listview_listContents.getItems();
		return list.toArray(new String[list.size()]);
	}

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_remove;

    @FXML
    private Label lbl_listName;
    
    @FXML
    void onAddButtonPressed(ActionEvent event) {
    	Stage newStage = new Stage();
    	ListAdditionViewController controller = new ListAdditionViewController(listview_listContents.getItems(), newStage);
    	Parent root = Main.loadFXML(Main.class, "ListAdditionView.fxml", controller);
    	Scene rootScene = new Scene(root);
    	newStage.setResizable(false);
    	newStage.setScene(rootScene);
    	newStage.initModality(Modality.APPLICATION_MODAL);
    	newStage.showAndWait();
    }

    @FXML
    void onRemoveButtonPressed(ActionEvent event) {
    	int selected = listview_listContents.getSelectionModel().getSelectedIndex();
    	if (selected != -1) {
    		listview_listContents.getItems().remove(selected);
    		listview_listContents.getSelectionModel().clearSelection();
    	}
    }
    
    @FXML
    void initialize() {
    	lbl_listName.setText(_listName);
    	listview_listContents.getItems().clear();
    	listview_listContents.getItems().addAll(_listItems);
    }
}
