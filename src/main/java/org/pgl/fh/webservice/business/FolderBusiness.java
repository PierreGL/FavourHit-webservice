package org.pgl.fh.webservice.business;

import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.Folder;
import org.pgl.fh.webservice.data.FolderRemoveResponse;
import org.pgl.fh.webservice.data.FolderTree;

public interface FolderBusiness {

	void addRootFolder(Account account, Folder folder);
	
	void addInnerFolder(Account account, Folder newFolder, Folder parentFolder);
	
	FolderRemoveResponse removeFolder(Account account, Folder folder);
	
	FolderTree getFolderTree(Account account);
}
