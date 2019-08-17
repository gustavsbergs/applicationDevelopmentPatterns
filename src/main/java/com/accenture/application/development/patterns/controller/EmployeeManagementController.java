package main.java.com.accenture.application.development.patterns.controller;

import main.java.com.accenture.application.development.patterns.constants.Names;
import main.java.com.accenture.application.development.patterns.constants.Surnames;
import main.java.com.accenture.application.development.patterns.facade.EmployeeManagementFacade;
import main.java.com.accenture.application.development.patterns.facade.impl.EmployeeManagementFacadeImpl;
import main.java.com.accenture.application.development.patterns.factory.EmployeeFactory;
import main.java.com.accenture.application.development.patterns.factory.SalaryCalculator;
import main.java.com.accenture.application.development.patterns.handlers.RequestHandler;
import main.java.com.accenture.application.development.patterns.handlers.impl.*;
import main.java.com.accenture.application.development.patterns.mapper.DTOToEmployeeMapper;
import main.java.com.accenture.application.development.patterns.mapper.EmployeeToDTOMapper;
import main.java.com.accenture.application.development.patterns.repository.EmployeeRepository;
import main.java.com.accenture.application.development.patterns.util.RandomNumberGenerator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class EmployeeManagementController {

    private boolean fullyRandomMode = false;

    private final EmployeeRepository repository = new EmployeeRepository();
    private final Names names = new Names();
    private final Surnames surnames = new Surnames();
    private final SalaryCalculator calculator = new SalaryCalculator();
    private final EmployeeFactory factory = new EmployeeFactory(calculator, names, surnames);
    private final EmployeeToDTOMapper mapperToDTO = new EmployeeToDTOMapper();
    private final DTOToEmployeeMapper mapperToEmployee = new DTOToEmployeeMapper();
    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    private boolean running = true;

    private Scanner userInput = new Scanner(System.in);
    private EmployeeManagementFacade managementFacade = new EmployeeManagementFacadeImpl(repository, factory, mapperToDTO, mapperToEmployee);
    private RequestHandler handler;

    public void loadUserInterface() {
        System.out.println("#################################################################");
        System.out.println("########### Welcome to the employee management system ###########");
        System.out.println("#################################################################");
        createMenu();
    }

    private void createMenu() {
        int input;
        do {
            System.out.println("#################################################################");
            System.out.println("Select one of the following actions by typing the corresponding number.");
            System.out.println("1. Create a new employee.");
            System.out.println("2. Find an employee by ID.");
            System.out.println("3. View a table of all employees.");
            System.out.println("4. Update an employees information.");
            System.out.println("5. Delete an employee from the system.");
            System.out.println("6. Exit the application.");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("Delay failed. Continuing application.");
            }

            input = getInput();
            System.out.println(input);
            if (input == 0) {
                System.out.println("Input invalid, try again.");
            } else if (input == 1) {
                createEmployee();
            } else if (input == 2) {
                viewEmployee();
            } else if (input == 3) {
                viewAllEmployees();
            } else if (input == 4) {
                updateEmployee();
            } else if (input == 5) {
                deleteEmployee();
            } else if (input == 6) {
                running = false;
            } else {
                System.out.println();
                System.out.println("Input invalid, try again.");
            }
        }
        while (running);
        System.out.println("Thanks for using employee management system. Application closing.");
    }


    private void deleteEmployee() {
        handler = new DeleteRequestHandler(managementFacade, fullyRandomMode);
        try {
            handler.handleRequest();
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println("Request unsuccessful. Please try again!");
        }
    }

    private void updateEmployee() {
        handler = new UpdateRequestHandler(managementFacade, factory, fullyRandomMode, mapperToDTO);
        try {
            handler.handleRequest();
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println("Request unsuccessful. Please try again!");

        }
    }

    private void viewAllEmployees() {
        handler = new ViewAllRequestHandler(managementFacade);
        try {
            handler.handleRequest();
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println("Request unsuccessful. Please try again!");
        }
    }

    private void viewEmployee() {
        handler = new ViewRequestHandler(managementFacade, fullyRandomMode);
        try {
            handler.handleRequest();
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println("Request unsuccessful. Please try again!");
        }
    }

    private void createEmployee() {
        handler = new CreateRequestHandler(managementFacade);
        try {
            handler.handleRequest();
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println("Request unsuccessful. Please try again!");
        }
    }

    private Integer getInput() {
        if (fullyRandomMode) {
            return randomNumberGenerator.generate(6);
        } else {
            try {
                return userInput.nextInt();
            } catch (InputMismatchException e) {
                return 0;
            }
        }
    }
}
