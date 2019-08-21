package main.java.com.accenture.application.development.patterns.domain;

import main.java.com.accenture.application.development.patterns.constants.Positions;
import main.java.com.accenture.application.development.patterns.constants.Seniority;


public class Employee extends Entity {

    private Positions position;
    private Seniority level;
    private Double salary;

    @Override
    public Employee setId(final Long id) {
        super.id = id;
        return this;
    }

    @Override
    public Employee setName(final String name) {
        super.name = name;
        return this;
    }

    @Override
    public Employee setSurname(final String surname) {
        super.surname = surname;
        return this;
    }

    public Positions getPosition() {
        return position;
    }

    public Employee setPosition(final Positions position) {
        this.position = position;
        return this;
    }

    public Seniority getLevel() {
        return level;
    }

    public Employee setLevel(final Seniority level) {
        this.level = level;
        return this;
    }

    public Double getSalary() {
        return salary;
    }

    public Employee setSalary(final Double salary) {
        this.salary = salary;
        return this;
    }

}
