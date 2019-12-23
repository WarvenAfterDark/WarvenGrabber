package app;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.json.JSONObject;

import config.SearchParameters;
import config.Settings;
import controllers.ProgramViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		Main.launch(args);
	}
	
	private static Stage _stage;
	public static Stage getStage() {
		return _stage;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = loadFXML(Main.class, "ProgramView.fxml", new ProgramViewController());
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.setOnCloseRequest(value -> {
			savePreferences();
		});
		loadPreferences();
		stage.show();
		_stage = stage;
	}

	public static <T> Parent loadFXML(Class<T> resourceClass, String fxmlPath, Object controller) {
		FXMLLoader loader = new FXMLLoader();
		URL xmlUrl = resourceClass.getResource(fxmlPath);
		loader.setLocation(xmlUrl);
		loader.setController(controller);
		try {
			return loader.load();
		} catch (IOException e) {
			System.err.printf("Error loading FXML '%s': %s", fxmlPath, e.getMessage());
			return null;
		}
	}
	
	public static void savePreferences() {
		File prefFile = new File("prefs.json");
		JSONObject saveObject = new JSONObject();
		saveObject.put(SearchParameters.JSON_KEY, SearchParameters.save());
		saveObject.put(Settings.JSON_KEY, Settings.save());
		try {
			Files.write(Paths.get(prefFile.toURI()), saveObject.toString().getBytes(), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.err.printf("Could not save preferences: %s", e.getMessage());
		}
	}
	
	public static void loadPreferences() {
		File prefFile = new File("prefs.json");
		try {
			List<String> allLines = Files.readAllLines(Paths.get(prefFile.toURI()));
			StringBuilder jsonBuilder = new StringBuilder();
			for (String line : allLines) {
				jsonBuilder.append(line + "\n");
			}
			jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
			JSONObject loadObj = new JSONObject(jsonBuilder.toString());
			SearchParameters.load(loadObj);
			Settings.load(loadObj);
		} catch (IOException e) {
			System.err.printf("Could not load preferences: %s", e.getMessage());
			return;
		}
	}
	
}