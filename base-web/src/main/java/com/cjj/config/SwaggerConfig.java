package com.cjj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/22 11:38
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                //描述字段支持Markdown语法
                .title("Study Project APIs")
                .description("ui is swagger-bootstrap-ui")
                .termsOfServiceUrl("https://doc.xiaominfo.com/")
                .version("1.0")
                .build();

        //指定使用Swagger2规范
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.cjj.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}

