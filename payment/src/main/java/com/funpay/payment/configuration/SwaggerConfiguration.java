package com.funpay.payment.configuration;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Leeko
 * @date 2022/1/26
 **/
@Configuration
@EnableOpenApi
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("funpay项目的接口文档")
                .description("payment项目，提供payment的相关功能")
                .contact(new Contact("主页", "http://www.faithgreen.com", "lijinfeigreen@vip.163.com"))
                .contact(new Contact("funding", "http://www.green.com/funding", "lijinfeigreen@vip.163.com"))
                .version("1.0")
                .build();
    }
}
