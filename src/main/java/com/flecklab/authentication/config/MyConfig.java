package com.flecklab.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Component
@EnableWebMvc
public class MyConfig {

    @Bean
   public WebMvcConfigurer corsConfigurer(){
    return new WebMvcConfigurer() {
        public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins("http://192.168.100.3:3000","http://localhost:3000");
        }
    };
   } 
    
}
