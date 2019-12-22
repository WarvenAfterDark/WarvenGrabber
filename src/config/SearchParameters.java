package config;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchParameters {
	public static final String JSON_KEY = "SearchParameters";
	private static final String JSON_KEY_ARTIST = "artists";
	private static final String JSON_KEY_CHARACTER = "characters";
	private static final String JSON_KEY_TAG = "tags";
	
	private static String[] _artists;
	public static void setArtists(String[] artists) {
		_artists = artists;
	}
	public static String[] getArtists() {
		return _artists != null ? _artists : new String[] {};
	}
	
	private static String[] _characters;
	public static void setCharacters(String[] characters) {
		_characters = characters;
	}
	public static String[] getCharacters() {
		return _characters != null ? _characters : new String[] {};
	}
	
	private static String[] _tags;
	public static void setTags(String[] tags) {
		_tags = tags;
	}
	public static String[] getTags() {
		return _tags != null ? _tags : new String[] {};
	}
	
	public static JSONObject save() {
		// Search Parameters
		JSONObject searchParams = new JSONObject();
		searchParams.put("artists", SearchParameters.getArtists());
		searchParams.put("characters", SearchParameters.getCharacters());
		searchParams.put("tags", SearchParameters.getTags());
		return searchParams;
	}
	public static void load(JSONObject saveData) {
		JSONObject dataObj = (JSONObject)saveData.get(JSON_KEY);
		JSONArray artistList = dataObj.getJSONArray(JSON_KEY_ARTIST);
		JSONArray characterList = dataObj.getJSONArray(JSON_KEY_CHARACTER);
		JSONArray tagList = dataObj.getJSONArray(JSON_KEY_TAG);
		setArtists(artistList.toList().toArray(new String[artistList.length()]));
		setCharacters(characterList.toList().toArray(new String[characterList.length()]));
		setTags(tagList.toList().toArray(new String[tagList.length()]));
	}
}