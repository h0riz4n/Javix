package com.Javix.JavixTg.config;

import com.Javix.JavixTg.backend.HttpClientJavix;
import com.Javix.JavixTg.modelsJSON.CommandModel;
import com.Javix.JavixTg.service.GsonParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.ApplicationContext;

@SpringBootConfiguration
@ComponentScan("com.Javix.JavixTg")
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    private GsonParserService gsonParserService;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext,
                        GsonParserService gsonParserService) {
        this.applicationContext = applicationContext;
        this.gsonParserService = gsonParserService;
    }

    @Bean
    public CommandModel commandModel() {
        CommandModel commandModel = new CommandModel();
        commandModel = gsonParserService.parse();
        return commandModel;
    }

    @Bean
    public HttpClientJavix httpClientJavix() {
        return new HttpClientJavix();
    }
}