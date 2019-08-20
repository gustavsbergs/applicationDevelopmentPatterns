package main.java.com.accenture.application.development.patterns.factory.impl;

import main.java.com.accenture.application.development.patterns.constants.Names;
import main.java.com.accenture.application.development.patterns.constants.Positions;
import main.java.com.accenture.application.development.patterns.constants.Seniority;
import main.java.com.accenture.application.development.patterns.constants.Surnames;
import main.java.com.accenture.application.development.patterns.domain.Employee;
import main.java.com.accenture.application.development.patterns.factory.Factory;
import main.java.com.accenture.application.development.patterns.util.RandomNumberGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeFactory implements Factory {

    private SalaryCalculator salaryCalculator;
    private Names names;
    private Surnames surnames;
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public EmployeeFactory(final SalaryCalculator salaryCalculator, final Names names, final Surnames surnames) {
        this.salaryCalculator = salaryCalculator;
        this.names = names;
        this.surnames = surnames;
    }

    public Employee create(final Integer amountOfEmployees) {
        final Employee employee = new Employee()
                .setId(amountOfEmployees.longValue() + 1L)
                .setName(generateRandomName())
                .setSurname(generateRandomSurname())
                .setPosition(generateRandomPosition());
        employee.setLevel((employee.getPosition() == Positions.DIRECTOR || employee.getPosition() == Positions.EXECUTIVE_MANAGER)
                ? generateRandomSeniorityExecutiveDirector() : generateRandomSeniorityManagerStaff())
                .setSalary(salaryCalculator.calculateSalary(employee.getLevel(), employee.getPosition()));
        return employee;
    }

    private Seniority generateRandomSeniorityExecutiveDirector() {
        final List<Seniority> seniorities = Collections.unmodifiableList(Arrays.asList(Seniority.ASSOCIATE, Seniority.SENIOR));
        return seniorities.get(randomNumberGenerator.generate(seniorities.size()) -1);
    }

    private Seniority generateRandomSeniorityManagerStaff() {
        final List<Seniority> seniorities = Collections.unmodifiableList(Arrays.asList(Seniority.values()));
        return seniorities.get(randomNumberGenerator.generate(seniorities.size()) -1);
    }

    private Positions generateRandomPosition() {
        final List<Positions> positions = Collections.unmodifiableList(Arrays.asList(Positions.values()));
        return positions.get(randomNumberGenerator.generate(positions.size()) -1);
    }

    private String generateRandomSurname() {
        return surnames.getName(randomNumberGenerator.generate(surnames.getSize()) -1);
    }

    private String generateRandomName() {
        return names.getName(randomNumberGenerator.generate(names.getSize()) -1);
    }
}
