package main.java.com.accenture.application.development.patterns.handlers.impl;

import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;
import main.java.com.accenture.application.development.patterns.facade.EmployeeManagementFacade;
import main.java.com.accenture.application.development.patterns.factory.EmployeeFactory;
import main.java.com.accenture.application.development.patterns.handlers.RequestHandler;
import main.java.com.accenture.application.development.patterns.mapper.EmployeeToDTOMapper;
import main.java.com.accenture.application.development.patterns.util.RandomNumberGenerator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UpdateRequestHandler implements RequestHandler {

    private boolean fullyRandomMode;
    private EmployeeManagementFacade facade;
    private EmployeeFactory factory;
    private Scanner input = new Scanner(System.in);
    private EmployeeToDTOMapper mapper;
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public UpdateRequestHandler(final EmployeeManagementFacade facade, final EmployeeFactory factory, final boolean fullyRandomMode, final EmployeeToDTOMapper mapper) {
        this.facade = facade;
        this.factory = factory;
        this.fullyRandomMode = fullyRandomMode;
        this.mapper = mapper;
    }

    @Override
    public void handleRequest() throws InterruptedException {
        final Long id = getId();
        if (id == 0) {
            System.out.println();
            System.out.println("Invalid input. You will be returned to the main menu.");
            System.out.println();
            TimeUnit.SECONDS.sleep(2);
        } else {
            final EmployeeDTO employeeToUpdate = facade.getEmployee(id);
            if (employeeToUpdate == null) {
                System.out.println();
                System.out.println("####User with ID: " + id + " was not found. You will be returned to the main menu.");
                TimeUnit.SECONDS.sleep(2);
                return;
            }
            updateEmployee(employeeToUpdate);
        }
    }

    private Long getId() throws InterruptedException {
        if (fullyRandomMode) {
            if(facade.amountOfEmployees() == 0){
                return 0L;
            }
            return randomNumberGenerator.generate(facade.amountOfEmployees()).longValue();
        } else {
            try {
                System.out.println();
                System.out.println("#### Enter the ID of the employee you wish to update: ");
                final Long id = input.nextLong();
                TimeUnit.SECONDS.sleep(2);
                return id;
            } catch (final InputMismatchException e) {
                return 0L;
            }
        }

    }

    private void updateEmployee(EmployeeDTO employeeToUpdate) throws InterruptedException {
        final EmployeeDTO updatedEmployee = mapper.map(factory.createEmployee(0));
        updatedEmployee.setEmployeeId(employeeToUpdate.getEmployeeId());
        System.out.println();
        System.out.println("Updating employee. Please wait...");
        TimeUnit.SECONDS.sleep(2);
        facade.updateEmployee(updatedEmployee);
        System.out.println();
        System.out.println("Employee with ID: " + employeeToUpdate.getEmployeeId() + " was updated successfully.");
        TimeUnit.SECONDS.sleep(2);
    }
}

