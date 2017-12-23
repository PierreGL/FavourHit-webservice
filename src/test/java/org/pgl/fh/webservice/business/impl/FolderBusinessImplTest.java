package org.pgl.fh.webservice.business.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.pgl.fh.webservice.business.FolderBusiness;
import org.pgl.fh.webservice.business.impl.FolderBusinessImpl;
import org.pgl.fh.webservice.dao.AccountDao;
import org.pgl.fh.webservice.dao.FolderDao;
import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.FolderCreateFailCause;
import org.pgl.fh.webservice.data.Folder;
import org.pgl.fh.webservice.data.FolderCreateResponse;
import org.pgl.fh.webservice.data.FolderRemoveResponse;
import org.pgl.fh.webservice.data.FolderTree;
import org.pgl.fh.webservice.data.RemoveFailCause;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

public class FolderBusinessImplTest {

	private FolderBusiness sut;
	
	private FolderDao folderDaoMock;
	private AccountDao accountDaoMock;
	
	@Before
	public void setup() {
		folderDaoMock = mock(FolderDao.class);
		accountDaoMock = mock(AccountDao.class);
		this.sut = new FolderBusinessImpl(folderDaoMock, accountDaoMock);
	}
	
	@Test
	public void testAddRootFolder_succeed() {
		//GIVEN
		Folder newFolder = new Folder();
		newFolder.setName("MyNewFolder");
		Account existingAccount = givenExistingAccount();
		
		//WHEN
		FolderCreateResponse folderCreateResponse = sut.addRootFolder(existingAccount, newFolder);
		
		//THEN
		assertTrue(folderCreateResponse.getCreationSucceed());
		verify(folderDaoMock, times(1)).createFolder(existingAccount, newFolder);
	}
	
	@Test
	public void testAddRootFolder_NotSucceed_AccountNotExisting() {
		//GIVEN
		Folder newFolder = new Folder();
		newFolder.setName("MyNewFolder");
		Account account = givenNotExistingAccount();
		
		//WHEN
		FolderCreateResponse folderCreateResponse = sut.addRootFolder(account, newFolder);
		
		//THEN
		assertFalse(folderCreateResponse.getCreationSucceed());
		assertTrue(folderCreateResponse.getCreateFailCauseSet().contains(FolderCreateFailCause.INEXISTING_ACCOUNT));
	}
	
	@Test
	public void testAddInnerFolder() {
		//GIVEN
		Folder newFolder = new Folder();
		newFolder.setName("MyNewFolder");
		
		Folder parentFolder = new Folder();
		parentFolder.setName("myParentFolder");
		
		Account existingAccount = givenExistingAccount();
		
		//WHEN
		sut.addInnerFolder(existingAccount, newFolder, parentFolder);
		
		//THEN
		assertEquals(newFolder.getParent(), parentFolder);
		verify(folderDaoMock, times(1)).createFolder(existingAccount, newFolder);		
	}
	
	@Test
	public void testRemoveFolder_Empty() {
		Account existingAccount = givenExistingAccount();
		Folder existingEmptyFolder = givenExistingEmptyFolder(existingAccount);
		
		FolderRemoveResponse folderRemoveResponse = sut.removeFolder(existingAccount, existingEmptyFolder);
		
		verify(folderDaoMock, times(1)).removeFolder(existingAccount, existingEmptyFolder);
		assertTrue(folderRemoveResponse.isSucceed());
	}
	
	@Test
	public void testRemoveFolder_NotEmpty() {
		//GIVEN
		Account existingAccount = givenExistingAccount();
		Folder existingNotEmptyFolder = givenExistingNotEmptyFolder(existingAccount);
		
		//WHEN
		FolderRemoveResponse folderRemoveResponse = sut.removeFolder(existingAccount, existingNotEmptyFolder);
		
		//THEN
		verify(folderDaoMock, times(0)).removeFolder(existingAccount, existingNotEmptyFolder);
		assertFalse(folderRemoveResponse.isSucceed());
		assertTrue(folderRemoveResponse.getRemoveFailCausesSet().contains(RemoveFailCause.HAS_CHILDREN));
	}
	
	@Test
	public void testGetFolderTree_Empty() {
		//GIVEN
		Account existingAccount = givenExistingAccountWith2RootFolderAndEachOneChild();
		
		//WHEN
		FolderTree folderTree = sut.getFolderTree(existingAccount);
		
		//THEN
		assertNotNull(folderTree);
		assertNotNull(folderTree.getFolderRootSet());
		assertEquals(folderTree.getFolderRootSet().size(), 2);
		
		folderTree.getFolderRootSet().stream()
			.forEach(rootFolder -> assertEquals(1, rootFolder.getChildren().size()));
	}
	
	private Folder givenExistingEmptyFolder(Account existingAccount) {
		Folder existingEmptyFolder = new Folder();
		when(folderDaoMock.hasChildren(existingAccount, existingEmptyFolder)).thenReturn(Boolean.FALSE);
		return existingEmptyFolder;
	}
	
	private Folder givenExistingNotEmptyFolder(Account existingAccount) {
		Folder existingNotEmptyFolder = new Folder();
		when(folderDaoMock.hasChildren(existingAccount, existingNotEmptyFolder)).thenReturn(Boolean.TRUE);
		return existingNotEmptyFolder;	
	}
	
	private Account givenExistingAccount() {		
		Account existingAccount = new Account();
		existingAccount.setIdentifier("Haddock");
		when(accountDaoMock.isIdentifierAccountExist(existingAccount.getIdentifier())).thenReturn(Boolean.TRUE);
		return existingAccount;
	}
	
	private Account givenNotExistingAccount() {		
		Account notExistingAccount = new Account();
		notExistingAccount.setId(2L);
		return notExistingAccount;
	}
	
	private Account givenExistingAccountWith2RootFolderAndEachOneChild() {
		Account existingAccount = new Account();
		existingAccount.setId(1L);
		
		Folder folderParent1 = new Folder();
		folderParent1.setName("parent1");
		Folder folderParent2 = new Folder();
		folderParent2.setName("parent2");
		Folder folderChildren1 = new Folder();
		folderChildren1.setName("children1");
		Folder folderChildren2 = new Folder();
		folderChildren2.setName("children2");
		folderChildren1.setParent(folderParent1);
		folderParent1.addChild(folderChildren1);
		folderChildren2.setParent(folderParent2);
		folderParent2.addChild(folderChildren2);
		
		Set<Folder> allFolders = new HashSet<>();
		allFolders.add(folderParent1);
		allFolders.add(folderParent2);
		allFolders.add(folderChildren1);
		allFolders.add(folderChildren2);

		when(folderDaoMock.getAllFolder(existingAccount)).thenReturn(allFolders);
		return existingAccount;
	}
	
}
