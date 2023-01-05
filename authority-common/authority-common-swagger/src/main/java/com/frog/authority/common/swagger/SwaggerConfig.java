package com.frog.authority.common.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author frog
 */
@Data
@Configuration
@EnableSwagger2
@EnableKnife4j
@ConfigurationProperties(prefix = "swagger-api.doc")
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    /**
     * 文档名称
     */
    private String name;

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档描述
     */
    private String description;

    /**
     * 接口版本号
     */
    private String version;

    @Bean
    public Docket restApi() {
        ApiInfo apiInfo = new ApiInfoBuilder().title(title)
                                              .description(description)
                                              .version(version)
                                              .build();
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                                                      .groupName(name)
                                                      .select()
                                                      .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                                                      .paths(PathSelectors.any())
                                                      .build();
    }
}
