package com.t00ter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Provides a simple API to access t00ts on the not-so-popular
 * website: www.t00ter.com.
 * @version 2009-11-24a
 * @author t a y l o r
 */
public class T00ter {
	/** Source of data from the website */
	private Scanner in;
	
	/** Flag to indicate if connection with the website was
	 * made successfully.
	 */
	private boolean isValid = false;
	
	/** The next t00t to be returned */
	private T00t nextToot = null;
	
	/**
	 * Default constructor that connects to www.t00ter.com
	 * and returns the most recent set of posts.
	 */
	public T00ter() {
		this("");
	}
	
	/**
	 * Constructor that connects to www.t00ter.com and
	 * returns the most recent set of posts for the
	 * specified user.
	 * @param user The user for whom posts are desired.
	 */
	public T00ter(String user) {
		try {
			// Construct the appropriate URL
			URL url;
			if(user==null || user.isEmpty()) {
				url = new URL("http://www.t00ter.com/txt");
			} else {
				url = new URL("http://www.t00ter.com/user/" + user + "/txt");	
			}
			
			// Create a scanner that is attached to the input stream
			//  connected to the url.
			in = new Scanner(url.openStream());
			
			// If the website returned an actual webpage, then something went wrong.
			//  If it doesn't look like a webpage, then we will mark the object as
			//  valid.
			if(!in.nextLine().contains("<!DOCTYPE")) {
				isValid = true;
			}
		} catch(Exception e) {
			// Something must have gone wrong, so we will mark the object as invalid.
			isValid = false;
		}
		// Prefetch the first t00t.
		nextToot = readToot();
	}
	
	/**
	 * Indicates whether a valid connection to www.t00ter.com
	 * was made.
	 * @return true if and only if a valid connection was made, otherwise false.
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * Indicates whether another t00t is available to be read.
	 * @return true if and only if a t00t remains to be read, otherwise false.
	 */
	public boolean hasNext() {
		return isValid && nextToot != null;
	}

	/**
	 * Finds and returns the next t00t from this t00ter object.
	 * @return the next t00t.
	 */
	public T00t next() {
		T00t current = null;
		// As long as the object is valid, try to read another t00t.
		if(isValid) {
			// Store the next t00t in current.
			current = nextToot;
			// Prefetch the next t00t.
			nextToot = readToot();
		}
		// Return the current t00t (or, if invalid, null)
		return current;
	}
	
	/**
	 * Finds and returns the next t00t from this t00ter object.
	 * Used as a helper method.
	 * @return the next t00t.
	 */
	private T00t readToot() {
		// Get to the beginning of the next t00t
		String line = "";
		while(in.hasNextLine() && !line.startsWith("id: ")) {
			line = in.nextLine();
		}
		// As long as we don't encounter problems, keep reading
		//  data from the source in order to get all of the
		//  components for a t00t.
		T00t result;
		try {
			// Extract the id.
			Scanner lineIn = new Scanner(line);
			lineIn.next();
			int id = lineIn.nextInt();
			
			// Extract the date
			lineIn = new Scanner(in.nextLine());
			lineIn.next();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date date = df.parse(lineIn.next());
			
			// Extract the author
			lineIn = new Scanner(in.nextLine());
			lineIn.next();
			String author = lineIn.next();
			
			// Extract the content
			lineIn = new Scanner(in.nextLine());
			lineIn.next();
			String message = lineIn.nextLine();
			message = message.substring(1);
			
			// If we got this far, then we have all we need to create
			//  a t00t... so we do it.
			result = new T00t(id, author, message, date);
		} catch(Exception e) {
			// Return null, if something went wrong.
			result = null;
		}
		return result;
	}
}
