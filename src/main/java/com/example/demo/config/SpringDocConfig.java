package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI restfulOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("后台Swagger的api文档")
                        .description("api文档，用于指定前后端访问api规范，方便测试者对接口进行可行性测试！")
                        .version("v2026")
                        .license(new License().name("").url("")));
    }
}
