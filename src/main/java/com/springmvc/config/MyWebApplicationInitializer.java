package com.springmvc.config;

import com.springmvc.config.others.ApplicationConfig;
import com.springmvc.config.others.RestServiceConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by zhang_htao on 2019/9/22.
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        initCharaterEncodingFilter(servletContext);
        initializeSpringConfig(servletContext);
        initializeSpringMVCConfig(servletContext);
    }

    /**
     * 初始化spring上下文信息
     * @param container
     */
    private void initializeSpringConfig(ServletContext container) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfig.class);
        // Manage the life cycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));
    }

    private void initializeSpringMVCConfig(ServletContext container) {
        // Create the spring rest servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(RestServiceConfiguration.class);

        // Register and map the spring rest servlet
        ServletRegistration.Dynamic dispatcher = container.addServlet("default",
                new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.setAsyncSupported(true);
        dispatcher.addMapping("/");
    }

    private void initCharaterEncodingFilter(ServletContext container) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8",true);
        container.addFilter("characterEncodingFilter",characterEncodingFilter);
    }
}
