package org.example;

import java.util.List;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import com.example.business.events.*;
import com.example.business.*;

public class EventBufferTest {
    static int id = 0;

    private Event<User> generateUserEvent() {
        User u = new User();
        u.setName("Bob");
        u.setAge(30);
        Event<User> e = new Event<>();
        e.setData(u);
        e.setId(id++);

        return e;
    }

    @Test public void test() {
        EventBuffer eb = new EventBuffer();

        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        eb.put(generateUserEvent());
        
        try {
            List<Event<User>> missed = eb.getFromLastEventId(2);
            for(Event<User> e : missed) {
                System.out.println(e.getId());
            }
        } catch(Exception e) { System.out.println("EXCEP"); }
    }
}
