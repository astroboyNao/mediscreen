package com.abernathy.notes.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableMongoRepositories
@ComponentScan("com.abernathy.notes.*")
@EnableSwagger2
public class ApplicationConfiguration {
}
