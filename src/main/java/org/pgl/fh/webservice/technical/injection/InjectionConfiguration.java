package org.pgl.fh.webservice.technical.injection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.fh.webservice.business.FolderBusiness;
import org.pgl.fh.webservice.business.impl.FolderBusinessImpl;
import org.pgl.fh.webservice.dao.AccountDao;
import org.pgl.fh.webservice.dao.FolderDao;
import org.pgl.fh.webservice.dao.impl.AccountDaoMapImpl;
import org.pgl.fh.webservice.dao.impl.FolderDaoImpl;
import org.pgl.fh.webservice.dao.impl.FolderDaoMapImpl;
import org.pgl.fh.webservice.restcontroller.FolderRestController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
//@ComponentScan(value = {"org.pgl.fh.webservice.business.impl"})
public class InjectionConfiguration {

	private static final Logger LOGGER = LogManager.getLogger(InjectionConfiguration.class.getName());
	
	@Bean
	@Scope(scopeName = "singleton")
	public FolderBusiness getFolderBusiness() {
		LOGGER.debug("FolderBusiness INJECTION");
		return new FolderBusinessImpl(getFolderDaoMapImpl(), getAccountDaoMapImpl());
	}
	
	@Bean("folderDaoDefault")
	@Scope(scopeName = "singleton")
	public FolderDao getFolderDaoImpl() {
		LOGGER.debug("FolderDao DEFAULT INJECTION");
		return new FolderDaoImpl();
	}
	
	@Bean("folderDaoMap")
	@Scope(scopeName = "singleton")
	public FolderDao getFolderDaoMapImpl() {
		LOGGER.debug("FolderDao MAP INJECTION");
		return new FolderDaoMapImpl();
	}

	@Bean
	@Scope(scopeName = "singleton")
	public AccountDao getAccountDaoMapImpl() {
		return new AccountDaoMapImpl();
	}
	
	@Bean("folderRestController")
	@Scope(scopeName = "singleton")
	public FolderRestController getFolderRestController() {
		LOGGER.debug("FolderRestController DEFAULT INJECTION");
		return new FolderRestController(getFolderBusiness());
	}
	
}
