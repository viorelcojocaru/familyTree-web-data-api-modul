package com.esempla.familyTree.familyTreewebstarter.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private final String SWAGGER_API_VERSION="1.0";
    private final String SWAGGER_DESCRIPTION="Spring Swagger example API for familytree";
    private final String SWAGGER_TITLE="Spring Swagger example API";
    private final String SWAGGER_LICENSE_TEXT="Therm of Servive";
    private final String SWAGGER_BASE_PACKAGE="com.esempla.familyTree.familyTreewebstarter";
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_BASE_PACKAGE))
                .build()
                .apiInfo(metaInfo())
                ;

    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                SWAGGER_TITLE,
                SWAGGER_DESCRIPTION,
                SWAGGER_API_VERSION,
                SWAGGER_LICENSE_TEXT,
                new Contact("Esempla", "localhost:8080/login",
                        "v.cojocaru@esempla.com"), "Personal licence", "www.esempla.com", null);

        return apiInfo;
    }
}
