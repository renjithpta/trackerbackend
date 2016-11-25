package com.ust.userwebapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan({"com.ust.userwebapp.common.util","com.ust.userwebapp.web.event.listner.common"})
public class CommonWebConfig {
	 public CommonWebConfig() {
	        super();
	    }

}
