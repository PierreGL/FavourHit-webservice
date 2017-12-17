package org.pgl.fh.webservice.data;

import java.util.HashSet;
import java.util.Set;

public class Folder {

	private static final String SEP = "/";
	
	private String name;
	private Folder parent;
	private Set<Folder> children = new HashSet<>();
	
	public void addChild(Folder folder) {
		children.add(folder);
	}
	
	public String getPath() {
		String path = "";
		if(parent != null) {
			path = parent.getPath();
		}
		path += SEP + name;
		
		return path;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Folder getParent() {
		return parent;
	}
	public void setParent(Folder parent) {
		this.parent = parent;
	}
	public Set<Folder> getChildren() {
		return children;
	}
}
