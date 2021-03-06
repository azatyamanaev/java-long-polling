package ru.itis.workproject.config;

import lombok.SneakyThrows;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


import javax.servlet.*;
import java.util.EnumSet;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    @SneakyThrows
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springWebContext = new AnnotationConfigWebApplicationContext();

        PropertySource propertySource = new ResourcePropertySource("classpath:application.properties");
        springWebContext.getEnvironment().setActiveProfiles((String) propertySource.getProperty("current.profile"));

        springWebContext.register(WebMvcConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(springWebContext));
        springWebContext.setServletContext(servletContext);

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(springWebContext));
        dispatcherServlet.setAsyncSupported(true);
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        security.setAsyncSupported(true);

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        security.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}
