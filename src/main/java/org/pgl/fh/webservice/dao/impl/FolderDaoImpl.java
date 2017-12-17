package org.pgl.fh.webservice.dao.impl;

import java.util.Set;

import org.pgl.fh.webservice.dao.FolderDao;
import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.Folder;
import org.springframework.stereotype.Repository;

@Repository
public class FolderDaoImpl implements FolderDao {

	@Override
	public void createFolder(Account account, Folder folder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFolder(Account account, Folder folder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean hasChildren(Account account, Folder folder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Folder getFolderByPath(Account account, String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Folder> getAllFolder(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

//	private static final Logger LOGGER = LogManager.getLogger(FolderDaoImpl.class.getName());
//	
//	public FolderDaoImpl() {
//		LOGGER.debug("FolderDaoImpl CONSTRUCTOR");
//	}
//	
//	@Override
//	public void createFolder(Folder folder) {
//		LOGGER.debug("FolderDaoImpl Create folder");
//	}
//	
//	@Override
//	public void removeFolder(Folder folder) {
//		// TODO Auto-generated method stub
//	}
//	
//	@Override
//	public Boolean hasChildren(Folder folder) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public Folder getFolderByPath(Account account, String path) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
