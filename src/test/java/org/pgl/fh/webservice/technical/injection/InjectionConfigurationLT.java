package org.pgl.fh.webservice.technical.injection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.pgl.fh.webservice.business.FolderBusiness;
import org.pgl.fh.webservice.dao.FolderDao;
import org.pgl.fh.webservice.dao.impl.FolderDaoImpl;
import org.pgl.fh.webservice.dao.impl.FolderDaoMapImpl;
import org.pgl.fh.webservice.data.Account;
import org.pgl.fh.webservice.data.Folder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Learning Tests about injection configuration with spring.
 * */
public class InjectionConfigurationLT {
	
	@Test
	public void testGetBean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InjectionConfiguration.class);
		
		//Get simple bean. Not include injected bean
		FolderBusiness folderBusiness = context.getBean(FolderBusiness.class);
		assertNotNull(folderBusiness);
		
		context.close();
	}
	
	@Test
	public void testGetBeanMultiImplem() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InjectionConfiguration.class);

		FolderDao folderDao = context.getBean("folderDaoDefault", FolderDao.class);
		assertTrue(folderDao instanceof FolderDaoImpl);
		
		FolderDao folderDaoMap = context.getBean("folderDaoMap", FolderDao.class);
		assertTrue(folderDaoMap instanceof FolderDaoMapImpl);
		
		context.close();
	}
	
	@Test
	public void testGetBeanConstructorInjection() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InjectionConfiguration.class);

		FolderBusiness folderBusiness = context.getBean(FolderBusiness.class);

		//FolderBusiness has a Folderdao injected by constructor and it is invoked in addRootFolder :
		folderBusiness.addRootFolder(new Account(), new Folder());	
		
		context.close();
	}
	
	@Test
	public void testBeanScopeSingleton() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InjectionConfiguration.class);

		FolderBusiness folderBusiness = context.getBean(FolderBusiness.class);
		FolderBusiness folderBusiness2 = context.getBean(FolderBusiness.class);

		assertEquals("FolderBusiness is singleton. The two references should be the same.", folderBusiness, folderBusiness2);
		
		context.close();
	}
}
