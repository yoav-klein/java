
public enum Kid {
    MOSHE (10, "music"),
    DAVID (12, "sport"),
    YANIV (15, "computers");

    private final int age;
    private final String hobby;

    Kid(int age, String hobby) {
        this.age = age;
        this.hobby = hobby;
    }

    public int getAge() {
        return this.age;
    }

    public String getHobby() {
        return this.hobby;
    }
}

