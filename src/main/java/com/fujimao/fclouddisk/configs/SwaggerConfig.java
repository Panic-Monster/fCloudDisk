package com.fujimao.fclouddisk.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Jayson_Y
 * @date: 2025/12/2
 * @project: fCloudDisk
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("fcloud_disk接口文档")
                        .description("腹肌猫网盘项目接口文档")
                        .version("v1"))
                .externalDocs(new ExternalDocumentation()
                        .description("项目API文档")
                        .url("/"));
    }
}
