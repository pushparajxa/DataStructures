package com.springAsync.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@ComponentScan(basePackageClasses = {com.springAsync.beans.FirstComponent.class})
public class ConfigurationClass {

}
