package org.pgl.fh.webservice.business.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.fh.webservice.business.FolderBusiness;
import org.pgl.fh.webservice.dao.FolderDao;
import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.Folder;
import org.pgl.fh.webservice.data.FolderRemoveResponse;
import org.pgl.fh.webservice.data.FolderTree;
import org.pgl.fh.webservice.data.RemoveFailCause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderBusinessImpl implements FolderBusiness {

	private static final Logger LOGGER = LogManager.getLogger(FolderBusinessImpl.class.getName());
	
	private FolderDao folderDao;
		
	@Autowired
	public FolderBusinessImpl(FolderDao folderDao) {
		LOGGER.debug("FolderBusinessImpl CONSTRUCTOR");
		this.folderDao = folderDao;
	}
	
	@Override
	public void addRootFolder(Account account, Folder folder) {
		LOGGER.debug("FolderBusinessImpl addRootFolder");
		folderDao.createFolder(account, folder);
	}
	
	@Override
	public void addInnerFolder(Account account, Folder newFolder, Folder parentFolder) {
		newFolder.setParent(parentFolder);
		folderDao.createFolder(account, newFolder);
	}
	
	@Override
	public FolderRemoveResponse removeFolder(Account account, Folder folder) {
		FolderRemoveResponse result = new FolderRemoveResponse();
		
		if(folderDao.hasChildren(account, folder)) {
			result.setRemovingSucceed(Boolean.FALSE);
			result.addRemoveFailCause(RemoveFailCause.HAS_CHILDREN);
		}else {
			folderDao.removeFolder(account, folder);
			result.setRemovingSucceed(Boolean.TRUE);
		}
		
		return result;
	}
	
	@Override
	public FolderTree getFolderTree(Account account) {
		FolderTree folderTree = new FolderTree();
		
		Set<Folder> allFolders = folderDao.getAllFolder(account);
		
		Set<Folder> rootFolder = allFolders.stream()
				.filter(folder -> folder.getParent() == null)
				.collect(Collectors.toSet());
	
		folderTree.setFolderRootSet(rootFolder);
		
		return folderTree;
	}
}
