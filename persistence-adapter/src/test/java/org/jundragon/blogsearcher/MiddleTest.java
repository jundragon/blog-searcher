package org.jundragon.blogsearcher;

import org.jundragon.blogsearcher.persistence.config.JpaConfig;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = JpaConfig.class)
public class MiddleTest {

}
