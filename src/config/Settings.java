package config;

import java.io.File;

import org.json.JSONObject;

public class Settings {
	public static final String JSON_KEY = "Settings";
	private static final String JSON_KEY_DESTINATION_FOLDER = "destFolder";
	
	private static File destinationFolder;
	public static void setDestinationFolder(File folder) {
		if (folder.exists()) {
			destinationFolder = folder;
		}
	}
	public static File getDestinationFolder() {
		return destinationFolder;
	}
	public static JSONObject save() {
		JSONObject settings = new JSONObject();
		settings.put(JSON_KEY_DESTINATION_FOLDER, destinationFolder != null ? destinationFolder.getPath() : "");
		return settings;
	}
	public static void load(JSONObject saveData) {
		if (saveData.has(JSON_KEY)) {
			saveData = (JSONObject)saveData.get(JSON_KEY);
			if (saveData.has(JSON_KEY_DESTINATION_FOLDER)) {
				setDestinationFolder(new File(saveData.getString(JSON_KEY_DESTINATION_FOLDER)));
			}
		}
	}
}