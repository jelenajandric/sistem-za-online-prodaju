package org.unibl.etf.beans;

import java.io.Serializable;

public class MessageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String content;
	private String senderName;
	private String senderSurname;
	private String senderUsername;
	private boolean seen;

	public MessageBean() {
		// TODO Auto-generated constructor stub
	}

	public MessageBean(int id, String content, String senderName, String senderSurname,String senderUsername, boolean seen) {
		super();
		this.id = id;
		this.content = content;
		this.senderName = senderName;
		this.senderSurname = senderSurname;
		this.senderUsername = senderUsername;
		this.seen = seen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderSurname() {
		return senderSurname;
	}

	public void setSenderSurname(String senderSurname) {
		this.senderSurname = senderSurname;
	}
	
	public String getSenderUsername() {
		return senderUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	
}
