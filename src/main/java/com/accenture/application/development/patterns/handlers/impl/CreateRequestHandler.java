package main.java.com.accenture.application.development.patterns.handlers.impl;

import main.java.com.accenture.application.development.patterns.constants.RequestTypes;
import main.java.com.accenture.application.development.patterns.domain.Employee;
import main.java.com.accenture.application.development.patterns.facade.EmployeeManagementFacade;
import main.java.com.accenture.application.development.patterns.factory.EntityFactory;
import main.java.com.accenture.application.development.patterns.handlers.RequestHandler;
import main.java.com.accenture.application.development.patterns.mapper.EmployeeToDTOMapper;

import java.util.concurrent.TimeUnit;

public class CreateRequestHandler implements RequestHandler {

    private EmployeeManagementFacade facade;
    private boolean trueRandomMode;
    private EntityFactory factory;
    private EmployeeToDTOMapper mapper;

    public CreateRequestHandler(final EmployeeManagementFacade facade, final boolean trueRandomMode, final EntityFactory factory, final EmployeeToDTOMapper mapper) {
        this.facade = facade;
        this.trueRandomMode = trueRandomMode;
        this.factory = factory;
        this.mapper = mapper;
    }

    @Override
    public void handleRequest(final RequestTypes type) throws InterruptedException {
        if (type == RequestTypes.CREATE) {
            System.out.println("#### Creating a new employee. Please wait...");
            TimeUnit.SECONDS.sleep(2);
            final Employee createdEmployee = (Employee) facade.createEmployee();
            System.out.println();
            System.out.println("#### Employee created! INFO:");
            System.out.println("#### Name: " + createdEmployee.getName());
            System.out.println("#### Surname: " + createdEmployee.getSurname());
            System.out.println("#### Employee ID: " + createdEmployee.getId());
            System.out.println("#### Position: " + createdEmployee.getPosition());
            System.out.println("#### Level: " + createdEmployee.getLevel());
            System.out.println("#### Salary: " + createdEmployee.getSalary() + " $");
            System.out.println();
            TimeUnit.SECONDS.sleep(2);
        } else {
            final RequestHandler nextRequestHandler = new ViewRequestHandler(facade, trueRandomMode, mapper, factory);
            nextRequestHandler.handleRequest(type);
        }
    }
}
