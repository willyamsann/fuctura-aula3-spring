package com.fuctura.fuctura.config;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /*
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.baeldung.web.controller"))
            .paths(PathSelectors.ant("/foos/*"))
            .build()
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false)
            .globalResponses(HttpMethod.GET, ArrayList(
                new ResponseBuilder().code("500")
                    .description("500 message").build(),
                new ResponseBuilder().code("403")
                    .description("Forbidden!!!!!").build()
            ));
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("My REST API", "Some custom description of API.", "API TOS", "Terms of service", new Contact("John Doe", "www.example.com", "myeaddress@company.com"), "License of API", "API license URL", Collections.emptyList());
        return apiInfo;
    }

    */

    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("br.com.treinaweb.springbootapi.controller"))    
          .paths(PathSelectors.any())
          .build()
          .useDefaultResponseMessages(false)                                   
          .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
          .securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
          .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simple Spring Boot REST API")
                .description("\"Um exemplo de aplicação Spring Boot REST API\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Wladimilson", "https://treinaweb.com.br", "contato@treinaweb.com.br"))
                .build();
    }

    private List<ResponseMessage> responseMessageForGET()
    {
        return new ArrayList<ResponseMessage>() {
            private static final long serialVersionUID = 1L;

            {
            add(new ResponseMessageBuilder()   
                .code(500)
                .message("500 message")
                .responseModel(new ModelRef("Error"))
                .build());
            add(new ResponseMessageBuilder() 
                .code(403)
                .message("Forbidden!")
                .build());
        }};
    }


}