package main.java.com.accenture.application.development.patterns.factory;

import main.java.com.accenture.application.development.patterns.domain.Entity;

public interface Factory {
    Entity create(Integer amountOfEntities);
}
