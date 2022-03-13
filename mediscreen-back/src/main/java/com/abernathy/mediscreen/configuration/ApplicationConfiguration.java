package com.abernathy.mediscreen.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories("com.abernathy.mediscreen.repository")
@EntityScan("com.abernathy.mediscreen.model.domain")
@ComponentScan("com.abernathy.mediscreen.*")
@EnableSwagger2
public class ApplicationConfiguration {
}
