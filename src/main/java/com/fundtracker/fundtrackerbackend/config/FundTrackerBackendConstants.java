package com.fundtracker.fundtrackerbackend.config;

import static com.fundtracker.fundtrackerbackend.config.FundTrackerSerializerConfig.DATETIME_FORMAT;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class FundTrackerBackendConstants {

	@Bean
	@Scope("singleton")
	SimpleDateFormat simpleDateFormat(){
		return new SimpleDateFormat("dd-MM-yyyy");
	}
	
	@Bean
	@Scope("singleton")
	DateTimeFormatter dateTimeFormatter(){
		return DateTimeFormatter.ofPattern("dd-MM-yyyy");
	}
	
	@Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        
        SimpleDateFormat df = new SimpleDateFormat(DATETIME_FORMAT);
        mapper.setDateFormat(df);

        registerSerializerDeserializer(mapper);
        return mapper;
    }
	
	private void registerSerializerDeserializer(ObjectMapper objectMapper) {
	    SimpleModule module = new SimpleModule();

	    module.addSerializer(LocalDate.class, new LocalDateSerializer());

	    objectMapper.registerModule(module);
	}

}
