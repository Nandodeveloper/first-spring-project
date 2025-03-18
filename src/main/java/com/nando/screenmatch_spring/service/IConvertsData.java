package com.nando.screenmatch_spring.service;

public interface IConvertsData {
    <T> T convertData(String json, Class<T> tClass);
}
