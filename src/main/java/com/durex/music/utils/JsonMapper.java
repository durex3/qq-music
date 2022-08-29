package com.durex.music.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMapper {

    private JsonMapper() {
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String object2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : OBJECT_MAPPER.writeValueAsString(src);
        } catch (Exception e) {
            //log.warn("parse object to string exception error:{}", e);
            return null;
        }
    }

    public static <T> T string2Object(String src, TypeReference<T> typeReference) {
        if (src == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : OBJECT_MAPPER.readValue(src, typeReference));
        } catch (Exception e) {
            //log.warn("parse string to object exception, String:{}, TypeReference<T>:{}, error:{}", src, typeReference.getType(), e);
            return null;
        }
    }
}
