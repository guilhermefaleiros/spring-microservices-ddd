package com.guifaleiros.productservice.infrastructure.util;

import com.google.gson.Gson;
import com.guifaleiros.core.ports.JsonHandlerPort;
import org.springframework.stereotype.Component;

@Component
public class GsonHandler implements JsonHandlerPort {

    private final Gson gson;

    public GsonHandler() {
        this.gson = new Gson();
    }

    @Override
    public <T> T deserialize(String json,  Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    @Override
    public String serialize(Object object) {
        return gson.toJson(object);
    }
}
