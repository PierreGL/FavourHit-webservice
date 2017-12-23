package org.pgl.fh.webservice.business;

import org.pgl.fh.webservice.data.AccountCreateResponse;
import org.pgl.fh.webservice.data.AccountCreationData;

public interface AccountBusiness {

	AccountCreateResponse createAccount(AccountCreationData accountCreationData);
}
