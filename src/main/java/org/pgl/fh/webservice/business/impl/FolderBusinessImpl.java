package org.pgl.fh.webservice.business.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.fh.webservice.business.FolderBusiness;
import org.pgl.fh.webservice.dao.AccountDao;
import org.pgl.fh.webservice.dao.FolderDao;
import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.CreateFailCause;
import org.pgl.fh.webservice.data.Folder;
import org.pgl.fh.webservice.data.FolderCreateResponse;
import org.pgl.fh.webservice.data.FolderRemoveResponse;
import org.pgl.fh.webservice.data.FolderTree;
import org.pgl.fh.webservice.data.RemoveFailCause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderBusinessImpl implements FolderBusiness {

	private static final Logger LOGGER = LogManager.getLogger(FolderBusinessImpl.class.getName());
	
	private AccountDao accountDao;
	private FolderDao folderDao;
		
	@Autowired
	public FolderBusinessImpl(FolderDao folderDao, AccountDao accountDao) {
		LOGGER.debug("FolderBusinessImpl CONSTRUCTOR");
		this.folderDao = folderDao;
		this.accountDao = accountDao;
	}
	
	@Override
	public FolderCreateResponse addRootFolder(Account account, Folder folder) {
		LOGGER.debug("FolderBusinessImpl addRootFolder");
		FolderCreateResponse result = new FolderCreateResponse();

		if(accountDao.isAccountExist(account)) {
			folderDao.createFolder(account, folder);
			result.setCreationSucceed(Boolean.TRUE);
		}else {
			result.setCreationSucceed(Boolean.FALSE);
			result.addCreateFailCause(CreateFailCause.INEXISTING_ACCOUNT);
		}
		
		return result;
	}
	
	@Override
	public FolderCreateResponse addInnerFolder(Account account, Folder newFolder, Folder parentFolder) {
		FolderCreateResponse result = new FolderCreateResponse();
		
		newFolder.setParent(parentFolder);
		if(accountDao.isAccountExist(account)) {
			folderDao.createFolder(account, newFolder);
			result.setCreationSucceed(Boolean.TRUE);
		}else {
			result.setCreationSucceed(Boolean.FALSE);
			result.addCreateFailCause(CreateFailCause.INEXISTING_ACCOUNT);
		}
		
		return result;

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
