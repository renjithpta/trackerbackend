package com.ust.userwebapp.config.run;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.ust.userwebapp.config.CommonWebConfig;
import com.ust.userwebapp.config.UmContextConfig;
import com.ust.userwebapp.config.UmJavaSecurityConfig;
import com.ust.userwebapp.config.UmPersistenceJpaConfig;
import com.ust.userwebapp.config.UmServiceConfig;
import com.ust.userwebapp.config.UmServletConfig;
import com.ust.userwebapp.config.UmWebConfig;
import com.ust.userwebapp.setup.UserWebApplicationContextInitializer;

@SpringBootApplication(exclude = { // @formatter:off
        SecurityAutoConfiguration.class
        , ErrorMvcAutoConfiguration.class
})// @formatter:on
public class UmApp extends SpringBootServletInitializer {

    private final static Object[] CONFIGS = { // @formatter:off
            UmApp.class,
            UmContextConfig.class,
            UmPersistenceJpaConfig.class,
            UmJavaSecurityConfig.class,
            UmServiceConfig.class,
            UmWebConfig.class,
            UmServletConfig.class,
            CommonWebConfig.class
           
    }; // @formatter:on

    //

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(CONFIGS).initializers(new UserWebApplicationContextInitializer());
    }

    public static void main(final String... args) {
        final SpringApplication springApplication = new SpringApplication(CONFIGS);
        springApplication.addInitializers(new UserWebApplicationContextInitializer());
        springApplication.run(args);
    }

}
