package org.mach.fipedatafetcher.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DataConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T fetchData(String jsonData, Class<T> clazz) throws IOException {
        return objectMapper.readValue(jsonData, clazz);
    }
}