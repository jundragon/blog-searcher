package org.jundragon.blogsearcher;

import org.jundragon.core.ComponentScanCoreModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanCoreModule.class})
public class BlogSearcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSearcherApplication.class, args);
    }
}
