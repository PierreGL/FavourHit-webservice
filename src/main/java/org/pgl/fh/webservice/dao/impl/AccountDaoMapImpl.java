package org.pgl.fh.webservice.dao.impl;

import java.util.HashMap;

import org.pgl.fh.webservice.dao.AccountDao;
import org.pgl.fh.webservice.dao.DataFolderByAccountMap;
import org.pgl.fh.webservice.data.Account;

public class AccountDaoMapImpl implements AccountDao {

	@Override
	public void createAccount(Account account) {
		if(DataFolderByAccountMap.data.get(account.getId()) != null) {
			throw new RuntimeException();
		}
		DataFolderByAccountMap.data.put(account.getId(), new HashMap<>());
	}
	
	@Override
	public boolean isAccountExist(Account account) {
		if(account != null) {
			return DataFolderByAccountMap.data.containsKey(account.getId());
		}
		return false;
	}

}
