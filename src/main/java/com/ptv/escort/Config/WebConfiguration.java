package com.ptv.escort.Config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

public interface WebConfiguration {

     void addCorsMappings(CorsRegistry registry);
}
