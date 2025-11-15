package com.example.business.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.business.User;

public class UserEventManager {
    private final EventBuffer<User> eventBuffer = new EventBuffer<>();
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public SseEmitter registerEmitter(String lastEventId) throws Exception {
        // while registering, we don't want any events published until the 
        // new emitter is in the list, and it got all the missed events
        lock.writeLock().lock();
        
        try {
            SseEmitter emitter = new SseEmitter();
            // this is important, as if we won't send this and there will be no message sent within the timeout,
            // the HTTP reseponse will be 503, and the client won't re-connect
            emitter.send(SseEmitter.event().comment("Welcome"));

            if(lastEventId != null) {
                List<Event<User>> missed = eventBuffer.getFromLastEventId(Integer.parseInt(lastEventId));
                try {
                    for(Event<User> missedEvent : missed) {
                        emitter.send(SseEmitter.event().id(String.valueOf(missedEvent.getId())).name("USER").data(missedEvent.getData()));
                    }
                } catch(IOException e) { 
                    System.out.println("Failed to sent missed events" + e);
                    throw e;
                }
            }

            emitters.add(emitter);
            emitter.onCompletion(() -> { emitters.remove(emitter); System.out.println("COMPLETED CALLBACK"); } );
            emitter.onTimeout   (() -> { emitters.remove(emitter); System.out.println("TIMEOUT CALLBACK"); });
            emitter.onError     ((e) ->  {  emitters.remove(emitter); System.out.println("ERROR CALLBACK: " + e); });

            return emitter;
        } catch(Exception e) {
            throw e;
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void addEvent(Event<User> event) {        
        lock.readLock().lock();

        try {
            // put the event in the buffer
            eventBuffer.put(event);
            
            // publish to all emitters
            List<SseEmitter> deadEmitters = new ArrayList<>();
            emitters.forEach(emitter -> {
                try {
                    emitter.send(SseEmitter.event().id(String.valueOf(event.getId())).name("USER").data(event.getData()));
                } catch(IOException e) {
                    deadEmitters.add(emitter);
                }
            });
            emitters.removeAll(deadEmitters);
        } finally {
            lock.readLock().unlock();
        }
    }
}
