package app.sites.content;

public class Media {

	public final long fileSize;
	public final String url;
	public final String extension;
	public final String name;
	
	public Media(String name, String url, String extension, long fileSize) {
		
		this.fileSize = fileSize;
		this.url = url;
		this.extension = extension;
		this.name = name;
		
	}

}
