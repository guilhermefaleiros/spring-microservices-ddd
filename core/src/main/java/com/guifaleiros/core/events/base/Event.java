package com.guifaleiros.core.events.base;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class Event<T> {
    private String eventId;
    private String eventName;
    private T payload;

    public Event(String eventName, T payload) {
        this.eventId = UUID.randomUUID().toString();
        this.eventName = eventName;
        this.payload = payload;
    }
}
