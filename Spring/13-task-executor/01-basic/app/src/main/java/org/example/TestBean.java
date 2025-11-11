package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

public class TestBean {
    
    @Autowired
    TaskExecutor taskExecutor;

    public void sayHi() {
        taskExecutor.execute(() -> { 
            try {
                Thread.sleep(3000);
                System.out.println("After 3 seconds"); 
            } catch(InterruptedException ignored) {}  
        });

        taskExecutor.execute(() -> { 
            try {
                Thread.sleep(10000);
                System.out.println("After 10 seconds"); 
            } catch(InterruptedException ignored) {}  
        });

        System.out.println("Main thread");
    }
}
