package com.dentaclinic.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class AppSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public AppSecurityWebApplicationInitializer(){
        super(AppSecurityWebApplicationInitializer.class);
    }
}
