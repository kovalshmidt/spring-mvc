package com.epam.springmvc.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.epam.springmvc.service", "com.epam.springmvc.utility"})
public class SpringConfig {
}
