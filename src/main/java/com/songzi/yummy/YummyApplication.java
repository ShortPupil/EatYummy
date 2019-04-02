package com.songzi.yummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.songzi.yummy.repository")
@EnableJpaRepositories("com.songzi.yummy.repository")
@ComponentScan("com.songzi.yummy.service")
@ServletComponentScan
@SpringBootApplication
public class YummyApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YummyApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(YummyApplication.class, args);
    }

}

