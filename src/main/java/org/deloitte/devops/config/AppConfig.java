package org.deloitte.devops.config;


import java.util.Arrays;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@PropertySource(value = "classpath:ext-config.properties")
public class AppConfig {
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate template = builder.build();
		HttpMessageConverter<Object> converter = new MappingJackson2HttpMessageConverter(objectMapper());
		template.setMessageConverters(Arrays.asList(converter));
		return template;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
				.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false)
				.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
	}
}