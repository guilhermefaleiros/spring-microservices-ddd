package com.guifaleiros.core.ports;

public interface JsonHandlerPort {
    String serialize(Object object);
    <T> T deserialize(String json, Class<T> classType);
}
