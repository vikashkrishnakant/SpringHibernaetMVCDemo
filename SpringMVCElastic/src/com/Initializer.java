package com;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext actx=new AnnotationConfigWebApplicationContext();
		actx.register(SpringConfiguration.class);
		actx.setServletContext(servletContext);
		
		Dynamic dynamic= servletContext.addServlet("dispatcher",new DispatcherServlet(actx));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
		
		
		
	}

}
