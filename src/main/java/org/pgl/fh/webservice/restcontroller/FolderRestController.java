package org.pgl.fh.webservice.restcontroller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.fh.webservice.business.FolderBusiness;
import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.Folder;
import org.pgl.fh.webservice.data.FolderCreateResponse;
import org.pgl.fh.webservice.data.FolderTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/folder")
public class FolderRestController {

	private static final Logger LOGGER = LogManager.getLogger(FolderRestController.class.getName());
	
	private FolderBusiness folderBusiness;
	
	@Autowired
	public FolderRestController(FolderBusiness folderBusiness) {
		this.folderBusiness = folderBusiness;
	}
	
	@RequestMapping(value = "/get/{name}", method= RequestMethod.GET)
	public String getFolderByName(@PathVariable String name) {
		LOGGER.debug("FolderRestController getFolderByName : "+name);
		return "azertyuiop";
	}
	
	@RequestMapping(value = "/addroot/{accountId}/{folderName}", method= RequestMethod.POST)
	public ResponseEntity<Void> addRootFolderByName(@PathVariable String accountId, 
													  @PathVariable String folderName) {
		
		Account account = getAccountMock(accountId);
		
		Folder newRootFolder = new Folder();
		newRootFolder.setName(folderName);

		FolderCreateResponse folderCreateResponse = folderBusiness.addRootFolder(account, newRootFolder);
		
		ResponseEntity<Void> response = null;
		if(folderCreateResponse.getCreationSucceed()) {
			response = new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@RequestMapping(value = "/tree/{accountId}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public FolderTree getFolderTree(@PathVariable String accountId) {		
		Account accountValidGet = getAccountMock(accountId);
		
		FolderTree folderTree = folderBusiness.getFolderTree(accountValidGet);
		
//		ResponseEntity<FolderTree> response = new ResponseEntity<>(folderTree, HttpStatus.OK);
		return folderTree;
	}
	
	private Account getAccountMock(String accountId) {
		Account account = new Account();
		account.setId(Long.valueOf(accountId));
		return account;
	}
	
}
