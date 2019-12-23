package app.sites;

import java.util.LinkedList;

import app.sites.content.Post;

/**
 * A Site is a site from which images are to be taken from.
 * 
 * This site could be anything at all, as long as it has
 * a URL and post data with which to parse.
 * 
 * This can be a booru or unconventional site.
 * 
 * @author @WarvenAfterDark
 */
public interface Site {
	/**
	 * Constructs a URL from the given page number,
	 * whatever URL the posts need to be parsed from.
	 * @param pageNumber The number of the page for the URL
	 * @return The full URL (that would be useable in a browser)
	 */
	public String constructURL(int pageNumber);
	/**
	 * Parses posts from a given HTML. Depending on the constructed URL,
	 * this could be any string data, such as JSON.
	 * @param html The HTML received from a URL request.
	 * @return A list of Posts parsed from the given HTML
	 */
	public LinkedList<Post> parsePostsFromHTML(String html);
}