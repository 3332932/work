package com.cn.springboot.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerInit {
    @Bean
    public Docket createSwagger2(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.cn.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        String title="springboot swagger test";
        String description="springboot swagger test";
        String version="1.0.0";
        String termsOfServiceUrl="1.0.0";
        Contact contact=new Contact("MarryFeng", "http://www.baidu.com", "");
        String license="1.0.0";
        String licenseUrl="1.0.0";
        List<VendorExtension> vendorExtensions= Collections.emptyList();
        return new ApiInfo(title,description,version,termsOfServiceUrl,contact
        ,license,licenseUrl,vendorExtensions);
    }
}
