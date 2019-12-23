package controllers;
import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ListAdditionViewController {

	private Stage _stage;
	private ObservableList<String> _items;
	
	public ListAdditionViewController(ObservableList<String> itemList, Stage stage) {
		_stage = stage;
		_items = itemList;
	}
	
    @FXML
    private TextArea txtArea_itemAdditions;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_cancel;

    @FXML
    void onAddButtonPressed(ActionEvent event) {
    	_items.addAll(parseEntry());
    	_stage.close();
    }

    @FXML
    void onCancelButtonPressed(ActionEvent event) {
    	_stage.close();
    }
    
    private String[] parseEntry() {
    	String enteredText = txtArea_itemAdditions.getText();
    	String[] enteredSplit = enteredText.trim().split(",");
    	LinkedList<String> trimmedSplits = new LinkedList<>();
    	for (String split : enteredSplit) {
    		if (split.length() > 0) {
    			trimmedSplits.add(split.trim());
    		}
    	}
    	return trimmedSplits.toArray(new String[enteredSplit.length]);
    }

}