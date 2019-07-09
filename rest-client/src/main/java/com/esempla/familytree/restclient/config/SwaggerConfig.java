package com.esempla.familytree.restclient.config;

import com.esempla.familytree.restclient.controller.PersonController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackageClasses = PersonController.class)
@PropertySource("classpath:swagger.properties")
@Configuration
public class SwaggerConfig {
    private final String SWAGGER_TITLE = "FamilyTree Client Rest-Api";
    private final String SWAGGER_DESCRIPTION = "Spring Swagger API for client familytree";
    private final String SWAGGER_LICENSE_TEXT = "Therm of Servive";
    private final String SWAGGER_API_VERSION = "1.0";
    private final String SWAGGER_CONTACT_NAME = "Viorel Cojocaru v.cojocaru@esempla.com";
    private final String SWAGGER_LICENSE = "Apache 2.0";
    private final String SWAGGER_LICENSE_URl = "http://www.apache.org/licenses/LICENSE-2.0";

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaInfo())
                .pathMapping("/")
                .select()
                //.paths(PathSelectors.regex("/api.*"))
                .build()
                ;

    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                SWAGGER_TITLE,
                SWAGGER_DESCRIPTION,
                SWAGGER_API_VERSION,
                SWAGGER_LICENSE_TEXT,
                SWAGGER_CONTACT_NAME,
                SWAGGER_LICENSE,
                SWAGGER_LICENSE_URl
        );

        return apiInfo;
    }
}
