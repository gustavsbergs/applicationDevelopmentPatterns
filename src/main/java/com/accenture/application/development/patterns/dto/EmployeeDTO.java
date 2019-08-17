package main.java.com.accenture.application.development.patterns.dto;

import main.java.com.accenture.application.development.patterns.constants.Positions;
import main.java.com.accenture.application.development.patterns.constants.Seniority;

public class EmployeeDTO {

    private Long employeeId;
    private String name;
    private String surname;
    private Positions position;
    private Seniority level;
    private Double salary;

    public Long getEmployeeId() {
        return employeeId;
    }

    public EmployeeDTO setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmployeeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public EmployeeDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Positions getPosition() {
        return position;
    }

    public EmployeeDTO setPosition(Positions position) {
        this.position = position;
        return this;
    }

    public Seniority getLevel() {
        return level;
    }

    public EmployeeDTO setLevel(Seniority level) {
        this.level = level;
        return this;
    }

    public Double getSalary() {
        return salary;
    }

    public EmployeeDTO setSalary(Double salary) {
        this.salary = salary;
        return this;
    }
}
