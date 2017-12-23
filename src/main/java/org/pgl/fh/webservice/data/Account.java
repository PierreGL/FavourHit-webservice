package org.pgl.fh.webservice.data;

public class Account {

	/**
	 * Unique technical id.
	 * */
	private Long id;
	
	/**
	 * Unique identifier provided by the user.
	 * */
	private String identifier;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
