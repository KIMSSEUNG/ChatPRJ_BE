package com.hello.animalChat.config;

import java.beans.BeanProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

// http://localhost:port/swagger-ui.html
@Configuration
//@OpenAPIDefinition(info = @Info(title = "",description = ""))
public class SwaggerConfig {
	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("rest-api").pathsToMatch("/api/**").build();
	}
}
