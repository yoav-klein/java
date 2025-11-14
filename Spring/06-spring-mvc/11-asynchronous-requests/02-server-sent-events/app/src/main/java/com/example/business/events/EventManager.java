package com.example.business.events;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.business.User;

public class EventManager {
    private final EventBuffer eventBuffer = new EventBuffer();
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private void sendUserEvent(Event<User> event, SseEmitter emitter) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        
        try {
            emitter.send(SseEmitter.event().id(String.valueOf(event.getId())).name("USER").data(event.getData()));
        } catch(Exception e) {
            deadEmitters.add(emitter);
        }
        emitters.removeAll(deadEmitters);

    }

    public void registerEmitter(SseEmitter emitter, String lastEventId) {
        // while registering, we don't want any events published until the 
        // new emitter is in the list, and it got all the missed events
        lock.writeLock().lock();

        
        try {
            // this is important, as if we won't send this and there will be no message sent within the timeout,
            // the HTTP reseponse will be 503, and the client won't re-connect
            emitter.send(SseEmitter.event().comment("Welcome"));

            if(lastEventId != null) {
                List<Event<User>> missed = eventBuffer.getFromLastEventId(Integer.parseInt(lastEventId));
                for(Event<User> missedEvent : missed) {
                    sendUserEvent(missedEvent, emitter);
                }
            }

            emitters.add(emitter);
            emitter.onCompletion(() -> { emitters.remove(emitter); System.out.println("COMPLETED CALLBACK"); } );
            emitter.onTimeout   (() -> { emitters.remove(emitter); System.out.println("TIMEOUT CALLBACK"); });
            emitter.onError     ((e) ->  {  emitters.remove(emitter); System.out.println("ERROR CALLBACK: " + e); });
        } catch(Exception e) {
            
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void addEvent(Event<User> event) {        
        lock.readLock().lock();

        eventBuffer.put(event);
        try {
            emitters.forEach(emitter -> {
                sendUserEvent(event, emitter);
            });
        } finally {
            lock.readLock().unlock();
        }
    }
}
