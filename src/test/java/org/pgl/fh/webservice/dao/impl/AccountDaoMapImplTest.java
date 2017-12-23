package org.pgl.fh.webservice.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.pgl.fh.webservice.dao.AccountDao;
import org.pgl.fh.webservice.dao.DataFolderByAccountMap;
import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.AccountCreationData;

public class AccountDaoMapImplTest {
	
	private AccountDao sut;
	
	@Before
	public void setup() {
		sut = new AccountDaoMapImpl();
	}
	
	@Test
	public void testCreateAccount_NotExist() {
		String newIdentifier = "Marius";
		AccountCreationData accountCreationData = new AccountCreationData();
		accountCreationData.setIdentifier(newIdentifier);
		
		Account account = sut.createAccount(accountCreationData);
		
		assertNotNull(DataFolderByAccountMap.data.get(newIdentifier));
		assertEquals(account.getIdentifier(), newIdentifier);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCreateAccount_AlreadyExist() {
		Account existingAccount = givenExistingAccount();
		AccountCreationData accountCreationData = new AccountCreationData();
		accountCreationData.setIdentifier(existingAccount.getIdentifier());
		
		Account account = sut.createAccount(accountCreationData);
		
		assertNotNull(DataFolderByAccountMap.data.get(account.getIdentifier()));
	}
	
	@Test
	public void testIsAccountExist_Exist() {
		Account existingAccount = givenExistingAccount();
		
		boolean result = sut.isIdentifierAccountExist(existingAccount.getIdentifier());
		
		assertTrue(result);
	}
	
	@Test
	public void testIsAccountExist_NotExist() {
		boolean result = sut.isIdentifierAccountExist("fakeIdentifier");
		
		assertFalse(result);
	}
	
	private Account givenExistingAccount() {
		String identifier = "Gustave";
		DataFolderByAccountMap.data.put(identifier, new HashMap<>());
		Account existingAccount = new Account();
		existingAccount.setIdentifier(identifier);
		return existingAccount;
	}
}
