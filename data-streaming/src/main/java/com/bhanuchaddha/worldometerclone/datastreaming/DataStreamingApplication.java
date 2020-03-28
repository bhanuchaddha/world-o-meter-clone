package com.bhanuchaddha.worldometerclone.datastreaming;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataStreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataStreamingApplication.class, args);
	}

	@Bean
	ObjectMapper objectMapper(){
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		// Solve the problem that jackson2 cannot deserialize LocalDateTime
		om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		om.registerModule(new JavaTimeModule());
		//om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
		return om;
	}
}
