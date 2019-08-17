package main.java.com.accenture.application.development.patterns.domain;

import main.java.com.accenture.application.development.patterns.constants.Positions;
import main.java.com.accenture.application.development.patterns.constants.Seniority;

import java.util.Objects;

public class Employee {

    private Long employeeId;
    private String name;
    private String surname;
    private Positions position;
    private Seniority level;
    private Double salary;

    public Long getEmployeeId() {
        return employeeId;
    }

    public Employee setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Employee setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Positions getPosition() {
        return position;
    }

    public Employee setPosition(Positions position) {
        this.position = position;
        return this;
    }

    public Seniority getLevel() {
        return level;
    }

    public Employee setLevel(Seniority level) {
        this.level = level;
        return this;
    }

    public Double getSalary() {
        return salary;
    }

    public Employee setSalary(Double salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(surname, employee.surname) &&
                position == employee.position &&
                level == employee.level &&
                Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, position, level, salary);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", position=").append(position);
        sb.append(", level=").append(level);
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }
}
