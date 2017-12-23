package org.pgl.fh.webservice.business;

import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.Folder;
import org.pgl.fh.webservice.data.FolderCreateResponse;
import org.pgl.fh.webservice.data.FolderRemoveResponse;
import org.pgl.fh.webservice.data.FolderTree;

public interface FolderBusiness {

	FolderCreateResponse addRootFolder(Account account, Folder folder);
	
	FolderCreateResponse addInnerFolder(Account account, Folder newFolder, Folder parentFolder);
	
	FolderRemoveResponse removeFolder(Account account, Folder folder);
	
	FolderTree getFolderTree(Account account);
}
