

package my.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Cat {

    @PostConstruct
    void init() {
        System.out.println("Cat object created");
    }

    @PreDestroy
    void destory() {
        System.out.println("Cat object destroyed");
    }
    
}
