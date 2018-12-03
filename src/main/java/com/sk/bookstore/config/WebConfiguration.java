/**
 * 
 */
package com.sk.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Surendra Kumar
 *
 */
@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/sklogos/**").addResourceLocations("classpath:/static/image/sklogos/");
		registry.addResourceHandler("/image/book/**").addResourceLocations("classpath:/static/image/book/");
		registry.addResourceHandler("/files/**").addResourceLocations("classpath:/static/files/");

	}
	
}
