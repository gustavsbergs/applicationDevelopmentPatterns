package main.java.com.accenture.application.development.patterns.handlers.impl;

import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;
import main.java.com.accenture.application.development.patterns.facade.EmployeeManagementFacade;
import main.java.com.accenture.application.development.patterns.handlers.RequestHandler;
import main.java.com.accenture.application.development.patterns.util.RandomNumberGenerator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ViewRequestHandler implements RequestHandler {

    private boolean fullyRandomMode;
    private Scanner input = new Scanner(System.in);
    private EmployeeManagementFacade facade;
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public ViewRequestHandler(final EmployeeManagementFacade facade, final boolean fullyRandomMode) {
        this.facade = facade;
        this.fullyRandomMode = fullyRandomMode;
    }

    @Override
    public void handleRequest() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        final Long id = getId();
        if (id == 0L) {
            System.out.println();
            System.out.println("Invalid input. You will be returned to the main menu.");
            System.out.println();
            TimeUnit.SECONDS.sleep(2);
        } else {
            final EmployeeDTO employeeToShow = facade.getEmployee(id);
            if (employeeToShow != null) {
                System.out.println();
                System.out.println("Looking for specific employee. Please wait...");
                showUser(employeeToShow);
                TimeUnit.SECONDS.sleep(2);
            } else {
                System.out.println();
                System.out.println("User with ID: " + id + " was not found.");
                System.out.println();
                TimeUnit.SECONDS.sleep(2);
            }
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
                System.out.println("#### Enter the id of the employee you wish to find: ");
                final long id = input.nextLong();
                TimeUnit.SECONDS.sleep(2);
                return id;
            } catch (InputMismatchException e) {
                return 0L;
            }
        }
    }

    private void showUser(EmployeeDTO employeeToShow) {
        System.out.println();
        System.out.println("#################################################################");
        System.out.println("#### Name: " + employeeToShow.getName());
        System.out.println("#### Surname: " + employeeToShow.getSurname());
        System.out.println("#### Employee ID: " + employeeToShow.getEmployeeId());
        System.out.println("#### Position: " + employeeToShow.getPosition());
        System.out.println("#### Level: " + employeeToShow.getLevel());
        System.out.println("#### Salary: " + employeeToShow.getSalary() + " $");
        System.out.println("#################################################################");
        System.out.println();
    }
}
