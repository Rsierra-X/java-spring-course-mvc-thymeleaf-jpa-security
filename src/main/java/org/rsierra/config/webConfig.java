package org.rsierra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {
    @Value("${companiesApp.path.images}")
    private String rutaImagenes;
    @Value("${companiesApp.path.cv}")
    private String pathCv;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/logos/**").addResourceLocations("file:/empleos/img-vacantes/"); // Linux
        registry.addResourceHandler("/logos/**").addResourceLocations("file:C:/empleos/img-vacancies/"); // Windows
        registry.addResourceHandler("/logos/**").addResourceLocations("file:" + rutaImagenes); // Windows
        registry.addResourceHandler("/cv/**").addResourceLocations("file:" + pathCv); // Windows
    }
}
