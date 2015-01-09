package com.t00ter;

import java.util.Date;

/**
 * Represents a single t00t from T00ter.com.
 * @version 2009-11-24a
 * @author t a y l o r
 */
public class T00t {

	/** Identification number of the t00t */
	private final int id;
	
	/** Author of the t00t */
	private final String author;
	
	/** Content of the t00t */
	private final String message;

	/** Date the t00t was posted */
	private final Date date;
	
	/**
	 * Constructor.  Note that all of the attributes of the class
	 * are immutable.  Therefore, all attributes must be given
	 * legitimate values when the object is constructed.
	 * 
	 * @param id identification number of the t00t
	 * @param author Author of the t00t
	 * @param message Content of the t00t
	 * @param date Date/Time the t00t was posted
	 */
	public T00t(int id, String author, String message, Date date) {
		this.id = id;
		this.author = author;
		this.message = message;
		this.date = date;
	}
	
	/**
	 * Returns the string representation of the t00t.
	 * @return The t00t in character form.
	 */
	public String toString() {
		return "id: " + id + '\n'
			+ "date: " + date + '\n'
			+ "author: " + author + '\n'
			+ "t00t: " + message + '\n';
	}

	/**
	 * Id accessor.
	 * @return the identification number of the t00t.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Author accessor.
	 * @return The author of the t00t.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Content accessor.
	 * @return The content of the t00t.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Date accessor.
	 * @return The Date/Time the t00t was posted.
	 */
	public Date getDate() {
		return date;
	}
}