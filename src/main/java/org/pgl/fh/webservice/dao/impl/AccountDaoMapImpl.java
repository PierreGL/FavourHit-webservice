package org.pgl.fh.webservice.dao.impl;

import java.util.HashMap;

import org.pgl.fh.webservice.dao.AccountDao;
import org.pgl.fh.webservice.dao.DataFolderByAccountMap;
import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.AccountCreationData;

public class AccountDaoMapImpl implements AccountDao {

	@Override
	public Account createAccount(AccountCreationData accountCreationData) {
		String identifier = accountCreationData.getIdentifier();
		if(DataFolderByAccountMap.data.get(identifier) != null) {
			throw new RuntimeException();
		}
		DataFolderByAccountMap.data.put(identifier, new HashMap<>());
		Account result = new Account();
		result.setIdentifier(identifier);
		return result;
	}
	
	@Override
	public boolean isIdentifierAccountExist(String identifier) {
		if(identifier != null) {
			return DataFolderByAccountMap.data.containsKey(identifier);
		}
		return false;
	}

}
