package com.guifaleiros.orderservice.infrastructure.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    Docket managerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.guifaleiros.orderservice"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo())
                .globalOperationParameters(Arrays.asList(
                        new ParameterBuilder()
                                .name("Authorization")
                                .description("Header para Token JWT")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Order Service")
                .description("Serviço de emissão de pedidos criado com Arquitetura Hexagonal, Event Sourcing e Sagas")
                .contact(new Contact("Time de Desenvolvimento", "https://www.mobicare.com.br/contato/",
                        "guilhermefaleiros2000@gmail.com"))
                .licenseUrl("guilhermefaleiros2000@gmail.com")
                .version("1.0.0")
                .build();
    }
}
