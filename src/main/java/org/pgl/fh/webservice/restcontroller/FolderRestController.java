package org.pgl.fh.webservice.restcontroller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.fh.webservice.business.FolderBusiness;
import org.pgl.fh.webservice.data.Folder;
import org.pgl.fh.webservice.data.FolderTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/add/{userName}/{folderName}", method= RequestMethod.POST)
	public void addRootFolderByName(@PathVariable String userName, 
									@PathVariable String folderName) {
		Folder newRootFolder = new Folder();
		newRootFolder.setName(folderName);

//		folderBusiness.addRootFolder(newRootFolder);
	}
	
	@RequestMapping(value = "/get/{name}", method= RequestMethod.GET)
	public String getFolderByName(@PathVariable String name) {
		LOGGER.debug("FolderRestController getFolderByName : "+name);
		return "azertyuiop";
	}
	
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public ResponseEntity<FolderTree> getFolderTree() {
		//...
		
		FolderTree folderTree = new FolderTree();
		
		ResponseEntity<FolderTree> response = new ResponseEntity<>(folderTree, HttpStatus.OK);
		return response;

		
	}
}
