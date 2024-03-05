package com.interview.project.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@UtilityClass
public class JSON {

    private final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public String toJson(Object payload) throws JsonProcessingException {
        return objectMapper.writeValueAsString(payload);
    }

    public String toJsonOrEmptyString(Object payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (IOException e) {
            return StringUtils.EMPTY;
        }
    }

    public <T> T fromJson(String content, Class<T> clazz) throws IOException {
        return objectMapper.readValue(content, clazz);
    }

    public <T> Optional<T> fromJsonOrEmpty(String content, Class<T> clazz) {
        try {
            return Optional.of(objectMapper.readValue(content, clazz));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Map convertObjToMapStr(Object object) {
        return objectMapper.convertValue(object, Map.class);
    }
}

