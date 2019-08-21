package main.java.com.accenture.application.development.patterns.factory;

import main.java.com.accenture.application.development.patterns.constants.Names;
import main.java.com.accenture.application.development.patterns.constants.Surnames;
import main.java.com.accenture.application.development.patterns.domain.Entity;

/**
 * Creational pattern - Factory method
 */
public class EntityFactory {


    private Names names;
    private Surnames surnames;
    private SalaryCalculator salaryCalculator;
    private EmployeeFactory employeeFactory;

    public EntityFactory(final Names names, final Surnames surnames, final SalaryCalculator calculator) {
        this.names = names;
        this.surnames = surnames;
        this.salaryCalculator = calculator;
    }

    public Entity createEntity(String type, Integer ammountofEntities) {
        if (type.equals("Employee")) {
            employeeFactory = new EmployeeFactory(salaryCalculator, names, surnames);
            return employeeFactory.create(ammountofEntities);
        }
        return null;
    }
}
