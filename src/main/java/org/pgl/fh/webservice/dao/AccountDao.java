package org.pgl.fh.webservice.dao;

import org.pgl.fh.webservice.data.Account;

public interface AccountDao {
	
	void createAccount(Account account);

	boolean isAccountExist(Account account);
}
