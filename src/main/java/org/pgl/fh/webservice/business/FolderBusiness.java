package org.pgl.fh.webservice.business;

import org.pgl.fh.webservice.data.Folder;

public interface FolderBusiness {

	void addRootFolder(Folder folder);
	
	void addFolder(Folder newFolder, Folder parentFolder);
}
