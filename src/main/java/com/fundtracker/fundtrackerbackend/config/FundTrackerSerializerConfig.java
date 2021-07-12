package com.fundtracker.fundtrackerbackend.config;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class FundTrackerSerializerConfig {

    public static final String DATETIME_FORMAT = "dd-MM-yyyy";
    public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
}
