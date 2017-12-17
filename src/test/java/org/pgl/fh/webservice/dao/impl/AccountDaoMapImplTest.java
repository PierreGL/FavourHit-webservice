package org.pgl.fh.webservice.dao.impl;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.pgl.fh.webservice.dao.AccountDao;
import org.pgl.fh.webservice.dao.DataFolderByAccountMap;
import org.pgl.fh.webservice.data.Account;

public class AccountDaoMapImplTest {
	
	private AccountDao sut;
	
	@Before
	public void setup() {
		sut = new AccountDaoMapImpl();
	}
	
	@Test
	public void testCreateAccount_NotExist() {
		Account newAccount = new Account();
		Long accountId = 1L;
		newAccount.setId(accountId);
		
		sut.createAccount(newAccount);
		
		assertNotNull(DataFolderByAccountMap.data.get(accountId));
	}
	
	@Test(expected = RuntimeException.class)
	public void testCreateAccount_AlreadyExist() {
		Account newAccount = givenExistingAccount();
		
		sut.createAccount(newAccount);
		
		assertNotNull(DataFolderByAccountMap.data.get(newAccount.getId()));
	}
	
	private Account givenExistingAccount() {
		Long accountId = 1L;
		DataFolderByAccountMap.data.put(accountId, new HashMap<>());
		Account existingAccount = new Account();
		existingAccount.setId(accountId);
		return existingAccount;
	}
}