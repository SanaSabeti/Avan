package ir.asta;
import java.time.LocalDateTime;

public abstract class Person {
    private final String name;
    private final LocalDateTime createdAt;

    {
        createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt.toString();
    }

    protected Person(String name) {
        this.name = name;
    }

    protected String showInfo() {
        return "name= " + name + ", createdAt= " + createdAt;
    }

    public abstract String describe();
}
