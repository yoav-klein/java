package com.example.business.events;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.example.business.User;

public class EventBuffer {
    private final Map<Integer, User> eventMap;
    private final AtomicInteger lastId = new AtomicInteger(0);

    public EventBuffer() {
        this.eventMap = new ConcurrentHashMap<>();
    }

    public void put(Event<User> event) {
        // compare and swap for thread safety. multiple threads can execute this method concurrently
        int eventId = event.getId();
        int last = lastId.get();
        do {
            last = lastId.get();
            if(last > eventId) break;
        } while(!lastId.compareAndSet(last, event.getId()));
        
        eventMap.put(eventId, event.getData());
    }

    public List<Event<User>> getFromLastEventId(int lastReceived) {
        List<Event<User>> missedEvents = new ArrayList<>();

        for(int i = lastReceived + 1; i <= lastId.get(); ++i) {
            missedEvents.add(new Event<>(i, this.eventMap.get(i)));
        }

        return missedEvents;
    }
}
