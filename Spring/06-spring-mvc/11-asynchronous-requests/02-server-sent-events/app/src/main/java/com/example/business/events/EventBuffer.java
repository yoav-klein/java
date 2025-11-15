package com.example.business.events;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EventBuffer<T> {
    private final Map<Integer, T> eventMap;
    private final AtomicInteger lastId = new AtomicInteger(0);

    public EventBuffer() {
        this.eventMap = new ConcurrentHashMap<>();
    }

    public void put(Event<T> event) {
        // first, put the event in the map
        int eventId = event.getId();
        eventMap.put(eventId, event.getData());
        
        // compare and swap for thread safety. multiple threads can execute this method concurrently
        int last = lastId.get();
        do {
            last = lastId.get();
            if(last > eventId) break;
        } while(!lastId.compareAndSet(last, event.getId()));
        
    }

    public List<Event<T>> getFromLastEventId(int lastReceived) {
        List<Event<T>> missedEvents = new ArrayList<>();

        for(int i = lastReceived + 1; i <= lastId.get(); ++i) {
            missedEvents.add(new Event<>(i, this.eventMap.get(i)));
        }

        return missedEvents;
    }
}
