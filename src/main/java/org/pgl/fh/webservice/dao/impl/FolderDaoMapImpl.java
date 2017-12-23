package org.pgl.fh.webservice.dao.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.fh.webservice.dao.FolderDao;
import org.pgl.fh.webservice.dao.DataFolderByAccountMap;
import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.Folder;
import org.pgl.fh.webservice.data.exception.NoAccountException;
import org.springframework.stereotype.Repository;

@Repository
public class FolderDaoMapImpl implements FolderDao {

	private static final Logger LOGGER = LogManager.getLogger(FolderDaoMapImpl.class.getName());
	
	@Override
	public void createFolder(Account account, Folder folder) {
		LOGGER.debug("FolderDaoImpl add folder in map");
		if(account != null && account.getId() != null) {
			updateOrCreateParent(account, folder);
			processCreateFolder(account, folder);
		}
	}
	
	@Override
	public void removeFolder(Account account, Folder folder) {
		LOGGER.debug("FolderDaoImpl remove folder in map");
		Map<String, Folder> mapFolders = DataFolderByAccountMap.data.get(account.getId());
		mapFolders.remove(folder.getPath());		
	}
	
	@Override
	public Boolean hasChildren(Account account, Folder folder) {
		LOGGER.debug("FolderDaoImpl hasChildren in map");

		Map<String, Folder> mapFolders = DataFolderByAccountMap.data.get(account.getId());
		Boolean result = mapFolders.values().stream().anyMatch(
				fd -> fd.getParent() != null ? folder.getPath().equals(fd.getParent().getPath()) : false);
		
		return result;
	}

	@Override
	public Folder getFolderByPath(Account account, String path) {
		Map<String, Folder> mapFolders = DataFolderByAccountMap.data.get(account.getId());
		return mapFolders.get(path);
	}
	
	@Override
	public Set<Folder> getAllFolder(Account account) {
		Set<Folder> result = new HashSet<>();
		if(account!= null && account.getId() != null) {
			Map<String, Folder> mapFolders = DataFolderByAccountMap.data.get(account.getId());
			if(mapFolders != null) {
				result = mapFolders.values().stream().collect(Collectors.toSet());
			}
		}
		return result;
	}
	
	private void updateOrCreateParent(Account account, Folder folder) {
		Folder parent = folder.getParent();
		if(parent != null) {
			Map<String, Folder> mapFolders = DataFolderByAccountMap.data.get(account.getId());
			Folder existingParent = mapFolders.get(parent.getPath());
			if(existingParent != null) {
				folder.setParent(existingParent);
				existingParent.addChild(folder);
			}else {
				createFolder(account, parent);
				parent.addChild(folder);
			}
		}
	}
	
	private void processCreateFolder(Account account, Folder folder){
		Map<String, Folder> mapFolders = DataFolderByAccountMap.data.get(account.getId());
		if(mapFolders != null) {
			mapFolders.put(folder.getPath(), folder);
		}else {
			throw new NoAccountException();
		}
	}
	
}
