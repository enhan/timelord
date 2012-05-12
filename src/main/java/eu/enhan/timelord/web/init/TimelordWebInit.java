/*
 *   This file is part of Timelord.
 *
 *   Timelord is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Affero General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Timelord is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the Affero GNU General Public License
 *   along with Timemord.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.enhan.timelord.web.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import eu.enhan.timelord.domain.config.AppConfig;
import eu.enhan.timelord.domain.config.data.Neo4jConfig;
import eu.enhan.timelord.web.config.WebConfig;


/**
 * @author Emmanuel Nhan
 *
 */
public class TimelordWebInit implements WebApplicationInitializer{

    private static final Logger log = LoggerFactory.getLogger(TimelordWebInit.class);
    
    /**
     * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
	AnnotationConfigWebApplicationContext rootCtx = new AnnotationConfigWebApplicationContext();
	String neo = rootCtx.getEnvironment().getProperty(Neo4jConfig.LOCATION_KEY);
	log.debug("Neo4j DB location : '{}'.",neo);
	rootCtx.register(AppConfig.class);
	servletContext.addListener(new ContextLoaderListener(rootCtx));
	
	AnnotationConfigWebApplicationContext webAppCtx = new AnnotationConfigWebApplicationContext();
	webAppCtx.setParent(rootCtx);
	webAppCtx.register(WebConfig.class);
	ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(webAppCtx));
	dispatcher.setLoadOnStartup(1);
	dispatcher.addMapping("/");
	
    }
    
    

}
