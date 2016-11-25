package com.ust.userwebapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({ "com.ust.userwebapp.mvc.web.controller","com.ust.userwebapp.web.event.listner.common", "com.ust.userwebapp.web.exception.advice","com.ust.userwebapp.web.mvc.controller.common","com.ust.userwebapp.setup"})
@EnableWebMvc
public class UmWebConfig extends WebMvcConfigurerAdapter {

    public UmWebConfig() {
        super();
    }

    // beans

}
