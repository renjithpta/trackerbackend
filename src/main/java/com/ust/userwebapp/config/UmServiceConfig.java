package com.ust.userwebapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.ust.userwebapp.services" })
public class UmServiceConfig {

    public UmServiceConfig() {
        super();
    }

    // beans

}
