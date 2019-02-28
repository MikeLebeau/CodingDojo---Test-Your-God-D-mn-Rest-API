package com.oodrive.codingdojoapitest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;


@Configuration
public class JacksonConfiguration {

	@Bean
	public ParameterNamesModule parametersNameModule() {
		return new ParameterNamesModule(JsonCreator.Mode.PROPERTIES);
	}
}
