package main.java.com.accenture.application.development.patterns.handlers.impl;

import main.java.com.accenture.application.development.patterns.domain.Employee;
import main.java.com.accenture.application.development.patterns.facade.EmployeeManagementFacade;
import main.java.com.accenture.application.development.patterns.handlers.RequestHandler;

import java.util.concurrent.TimeUnit;

public class CreateRequestHandler implements RequestHandler {

    private EmployeeManagementFacade facade;

    public CreateRequestHandler(final EmployeeManagementFacade facade) {
        this.facade = facade;
    }

    @Override
    public void handleRequest() throws InterruptedException {
        System.out.println("#### Creating a new employee. Please wait...");
        TimeUnit.SECONDS.sleep(2);
        final Employee createdEmployee = facade.createEmployee();
        System.out.println();
        System.out.println("#### Employee created! INFO:");
        System.out.println("#### Name: " + createdEmployee.getName());
        System.out.println("#### Surname: " + createdEmployee.getSurname());
        System.out.println("#### Employee ID: " + createdEmployee.getEmployeeId());
        System.out.println("#### Position: " + createdEmployee.getPosition());
        System.out.println("#### Level: " + createdEmployee.getLevel());
        System.out.println("#### Salary: " + createdEmployee.getSalary() + " $");
        System.out.println();
        TimeUnit.SECONDS.sleep(2);
    }
}
