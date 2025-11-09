

package jackson;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Person implements Serializable {
    private String name;
    private int age;
    private LocalDateTime time;

    public Person() {}

    public Person(String name, int age, LocalDateTime time) {
        this.name = name;
        this.age = age;
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}