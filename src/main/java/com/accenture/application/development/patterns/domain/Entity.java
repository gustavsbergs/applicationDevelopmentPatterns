package main.java.com.accenture.application.development.patterns.domain;

public class Entity {
    Long id;
    String name;
    String surname;

    public Long getId() {
        return id;
    }

    public Entity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Entity setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Entity setSurname(String surname) {
        this.surname = surname;
        return this;
    }
}
