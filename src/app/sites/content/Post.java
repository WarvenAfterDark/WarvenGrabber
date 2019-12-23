package app.sites.content;
import java.util.Iterator;
import java.util.LinkedList;

public class Post {
	public final int ID;
	public final String artist;
	private LinkedList<String> _tags;
	public Iterator<String> getTags() {
		return _tags.iterator();
	}
	public final String mediaURL;
	public final String mediaFileExtension;
	public final String mediaName;
	
	public Post(int ID, String artist, String[] tags,
			String mediaURL, String mediaFileExtension, String mediaName)
	{
		this.ID = ID;
		this.artist = artist;
		_tags = new LinkedList<String>();
		for (String tag : tags) {
			_tags.add(tag.toLowerCase());
		}
		this.mediaURL = mediaURL;
		this.mediaFileExtension = mediaFileExtension;
		this.mediaName = mediaName;
	}
}