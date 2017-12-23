package org.pgl.fh.webservice.technical.injection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class RestControllerInitializer implements WebApplicationInitializer {

	private final String REST_URL = "/rest";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		
		context.register(InjectionConfiguration.class);
				
		servletContext.addListener(new ContextLoaderListener(context));
		
		ServletRegistration.Dynamic restDispatcher = servletContext
				.addServlet("restDispatcher", new DispatcherServlet(context));
		
		restDispatcher.setLoadOnStartup(1);
		restDispatcher.addMapping(REST_URL+"/*");
		
	}

}
