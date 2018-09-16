package com.utn.newspaper.config;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Profile("!test")
@EnableJpaRepositories(basePackages = "com.utn.newspaper.repository")
public interface ConfigJpaRepositories {
}
