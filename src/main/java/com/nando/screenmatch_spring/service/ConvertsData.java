package com.nando.screenmatch_spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertsData implements IConvertsData{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertData(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            System.out.println("Json processor error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
