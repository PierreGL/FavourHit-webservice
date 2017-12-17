package org.pgl.fh.webservice.data;

import java.util.HashSet;
import java.util.Set;

public class FolderTree {

	private Set<Folder> folderRootSet = new HashSet<>();
	
	public void setFolderRootSet(Set<Folder> folderRootSet) {
		this.folderRootSet = folderRootSet;
	}
	
	public Set<Folder> getFolderRootSet() {
		return folderRootSet;
	}
}
