package com.abernathy.assess.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableMongoRepositories
@ComponentScan("com.abernathy.assess.*")
@EnableJpaRepositories("com.abernathy.mediscreen.repository")
@EntityScan("com.abernathy.mediscreen.model.domain")
public class ApplicationConfiguration {
}
