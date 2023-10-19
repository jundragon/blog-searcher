package org.jundragon.blogsearcher.config;

import org.jundragon.persistence.ComponentScanPersistenceModule;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = ComponentScanPersistenceModule.class)
@EnableJpaRepositories(basePackageClasses = ComponentScanPersistenceModule.class)
public class JpaConfig {

}
