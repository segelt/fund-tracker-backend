package com.fundtracker.fundtrackerbackend.config;

import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

import java.io.IOException;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {
	@Override
	public void serialize(LocalDate dateTime, JsonGenerator generator, SerializerProvider provider) throws IOException {
	
	    String dateTimeString = dateTime.format(ISO_LOCAL_DATE);
	    generator.writeString(dateTimeString);
	}
}
