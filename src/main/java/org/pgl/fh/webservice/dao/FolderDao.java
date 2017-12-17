package org.pgl.fh.webservice.dao;

import java.util.Set;

import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.Folder;

public interface FolderDao {

	void createFolder(Account account, Folder folder);
	
	void removeFolder(Account account, Folder folder);
	
	/**
	 * @return true if the folder contains at least one folder or link or other.
	 * */
	Boolean hasChildren(Account account, Folder folder);
	
	Folder getFolderByPath(Account account, String path);
	
	Set<Folder> getAllFolder(Account account);
}
