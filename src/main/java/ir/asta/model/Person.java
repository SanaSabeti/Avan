package ir.asta.model;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Person {
    private String name;
    private LocalDateTime createdAt;

    protected Person() {
        this.createdAt = LocalDateTime.now();
    }

    protected Person(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt.toString();
    }

    protected String showInfo() {
        return "name= " + name + ", createdAt= " + createdAt;
    }

    public abstract String describe();
}
