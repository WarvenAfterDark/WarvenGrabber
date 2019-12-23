package app.sites.content;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;

public class Post {
	
	private LinkedList<String> _tags;
	public Iterator<String> getTags() {
		
		return _tags.iterator();
		
	}

	public final Media media;
	
	public final int ID;
	
	public final String artist;
	
	public Post(int ID, String artist, Media media, String[] tags) {
		
		this.ID = ID;
		this.artist = artist;
		this.media = media;
		
		_tags = new LinkedList<String>();
		for (String tag : tags) {
			_tags.add(tag.toLowerCase());
		}
		
	}
	
	public void download(String dirPath) {
		
		if (!Files.exists(Paths.get(dirPath))) {
			try {
				Files.createDirectories(Paths.get(dirPath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}
		}
		
		try (InputStream in = new URL(media.url).openStream(); OutputStream out = new FileOutputStream(dirPath + "/" + media.name);) {
			
			byte[] buffer = new byte[512000];
			
			int read;
			
			while((read = in.read(buffer)) != -1) {
				
				out.write(buffer, 0, read);
				
			}
			
		} catch (IOException e) {
			System.err.printf("Error downloading '%s': %s\n",
							   media.name,
							   e.getMessage());
			return;
		}

	}
	
}