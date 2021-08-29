package com.example.converter.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.concurrent.TimeUnit

//@EnableWebMvc
//@Configuration
//@ComponentScan("com.example.converter")
//class MvcConfig: WebMvcConfigurer {
//    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
//        registry
//            .addResourceHandler("/static/**")
//            .addResourceLocations("/static/")
//    }
//}