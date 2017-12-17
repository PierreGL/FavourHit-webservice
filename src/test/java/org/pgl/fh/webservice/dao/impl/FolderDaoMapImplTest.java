package org.pgl.fh.webservice.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.pgl.fh.webservice.dao.DataFolderByAccountMap;
import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.Folder;

public class FolderDaoMapImplTest {

	private FolderDaoMapImpl sut;
	
	@Before
	public void setup() {
		sut = new FolderDaoMapImpl();
	}
	
	@Test
	public void testCreateNewFolder() {
		Account account = givenExistingAccount();
		Folder newFolder = new Folder();
		newFolder.setName("folderName");
		
		sut.createFolder(account, newFolder);
	
		assertTrue(DataFolderByAccountMap.data.get(account.getId()).containsKey(newFolder.getPath())); 
		assertEquals(DataFolderByAccountMap.data.get(account.getId()).get(newFolder.getPath()), newFolder); 
	}
	
	@Test
	public void testGetFolderByPath() {
		Account existingAccount = givenExistingAccount();
		Folder existingFolder = givenExistingFolder(existingAccount);
		
		Folder folderGet = sut.getFolderByPath(existingAccount, existingFolder.getPath());
		
		assertNotNull(folderGet);
		assertEquals(existingFolder.getName(), folderGet.getName());
		assertEquals(existingFolder.getPath(), folderGet.getPath());
	}
	
	@Test
	public void testFolderHasChildren_withoutChildren() {
		Account existingAccount = givenExistingAccount();
		Folder existingFolder = givenExistingFolder(existingAccount);

		Boolean result = sut.hasChildren(existingAccount, existingFolder);
		
		assertFalse(result);
	}
	
	@Test
	public void testFolderHasChildren_withChildren() {
		Account existingAccount = givenExistingAccount();
		Folder existingFolderWithChildren = givenExistingFolderWithChildren(existingAccount);

		Boolean result = sut.hasChildren(existingAccount, existingFolderWithChildren);
		
		assertTrue(result);
	}
	
	@Test
	public void testGetAllFolder_AccountExistNotEmpty() {
		Account existingAccountWithFolders = givenExistingAccountWithFolders();
		
		Set<Folder> allFolders = sut.getAllFolder(existingAccountWithFolders);
		
		assertNotNull(allFolders);
		assertFalse(allFolders.isEmpty());
	}
	
	private Folder givenExistingFolder(Account existingAccount) {
		String name = "folder10";
		Folder folderCreated = new Folder();
		folderCreated.setName(name);		
		sut.createFolder(existingAccount, folderCreated);
		return folderCreated;
	}
	
	private Folder givenExistingFolderWithChildren(Account existingAccount) {
		String name = "folder20";
		Folder folderParent = new Folder();
		folderParent.setName(name);		
		sut.createFolder(existingAccount, folderParent);
		
		String name2 = "folder21";
		Folder folderChild = new Folder();
		folderChild.setName(name2);
		folderChild.setParent(folderParent);
		
		folderParent.addChild(folderChild);
		sut.createFolder(existingAccount, folderChild);
		
		return folderParent;
	}
	
	private Account givenExistingAccount() {
		Account account = new Account();
		Long accountId = 1L;
		account.setId(accountId);
		DataFolderByAccountMap.data.put(accountId, new HashMap<>());
		return account;
	}
	
	private Account givenExistingAccountWithFolders() {
		Account account = new Account();
		Long accountId = 1L;
		account.setId(accountId);
		
		Map<String, Folder> mapFolders = new HashMap<>();
		Folder folder1 = new Folder();
		folder1.setName("name1");
		Folder folder2 = new Folder();
		folder2.setName("name2");
		mapFolders.put(folder1.getPath(), folder1);
		mapFolders.put(folder2.getPath(), folder2);

		DataFolderByAccountMap.data.put(accountId, mapFolders);
		return account;
	}
}
