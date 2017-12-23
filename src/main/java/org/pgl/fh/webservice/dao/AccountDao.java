package org.pgl.fh.webservice.dao;

import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.AccountCreationData;

public interface AccountDao {
	
	Account createAccount(AccountCreationData accountCreationData);

	boolean isIdentifierAccountExist(String identifier);
}
