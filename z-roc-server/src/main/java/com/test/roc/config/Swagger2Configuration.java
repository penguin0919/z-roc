package com.test.roc.config;

import com.test.roc.core.util.SystemConstants;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 鲍红建
 * @date: 2020/12/28
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Value("${spring.application.name}")
    private String projectName;

    @Value("${swagger.is.enable}")
    private boolean swaggerIsEnable;

    @Value("${swagger.version}")
    private String version;

    @Bean
    public Docket buildDocket() {
        // 设置 head 信息 token
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.parameterType("header")
                .name(SystemConstants.TOKEN).description("token参数")
                .modelRef(new ModelRef("string")).required(false).build();

        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerIsEnable)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                // 错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build().globalOperationParameters(aParameters);

    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title(projectName)
                .version(version)
                .build();
    }
}
