package org.pgl.fh.test.webservice.business.impl;

import org.junit.jupiter.api.BeforeEach;
import org.pgl.fh.webservice.business.FolderBusiness;
import org.pgl.fh.webservice.business.impl.FolderBusinessImpl;

public class FolderBusinessImplTest {

	private FolderBusiness sut;
	
	@BeforeEach
	public void setup() {
		this.sut = new FolderBusinessImpl();
	}
	
	//@Test
	public void testAddRootFolder() {
		sut.addRootFolder(null);
	}
}
