package com.ssafy.board.config;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;

import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.board.controller"))
				.paths(PathSelectors.ant("/api*/**"))
				.build()
				.apiInfo(apiInfo())
				.securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()));
	}
	
	private SecurityContext securityContext() {
	    return SecurityContext.builder()
	        .securityReferences(defaultAuth())
	        .forPaths(PathSelectors.regex("/.*"))
	        .build();
	}
	
	private List<SecurityReference> defaultAuth() {
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = new AuthorizationScope("global", "accessEverything");
	    List<SecurityReference> defaultAuth = new ArrayList<>();
	    defaultAuth.add(new SecurityReference("JWT", authorizationScopes));
	    return defaultAuth;
	}
	
	private ApiKey apiKey() {
	    return new ApiKey("JWT", "Authorization", "header");
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("SSAFIT REST API")
				.description("싸핏서버.")
				.version("0.1")
				.build();
	}
}
