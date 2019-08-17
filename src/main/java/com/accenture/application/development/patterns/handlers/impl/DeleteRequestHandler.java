package main.java.com.accenture.application.development.patterns.handlers.impl;

import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;
import main.java.com.accenture.application.development.patterns.facade.EmployeeManagementFacade;
import main.java.com.accenture.application.development.patterns.handlers.RequestHandler;
import main.java.com.accenture.application.development.patterns.util.RandomNumberGenerator;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DeleteRequestHandler implements RequestHandler {

    private boolean fullyRandomMode;
    private Scanner input = new Scanner(System.in);
    private EmployeeManagementFacade facade;
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public DeleteRequestHandler(final EmployeeManagementFacade facade, final boolean fullyRandomMode) {
        this.facade = facade;
        this.fullyRandomMode = fullyRandomMode;
    }

    @Override
    public void handleRequest() throws InterruptedException {
        final Map<Long, EmployeeDTO> employees = facade.getAllEmployees();
        final Long id = getId();
        if (id == 0L) {
            System.out.println();
            System.out.println("Invalid input. You will be returned to the main menu.");
            System.out.println();
            TimeUnit.SECONDS.sleep(2);
        } else if (doesUserExist(employees, id)) {
            final EmployeeDTO deletedEmployee = employees.get(id);
            System.out.println();
            System.out.println("####Deleting employee. Please wait...");
            TimeUnit.SECONDS.sleep(2);
            facade.deleteEmployee(id);
            System.out.println();
            System.out.println("####Employee " + deletedEmployee.getName() + " " + deletedEmployee.getSurname()
                    + " with employee ID " + deletedEmployee.getEmployeeId() + " deleted.");
            System.out.println();
            TimeUnit.SECONDS.sleep(2);
        } else {
            System.out.println();
            System.out.println("User not found. You will be returned to main menu.");
            System.out.println();
            TimeUnit.SECONDS.sleep(2);
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
                System.out.println("#### Type id of the employee to delete: ");
                final long id = input.nextLong();
                TimeUnit.SECONDS.sleep(2);
                return id;
            } catch (InputMismatchException e) {
                return 0L;
            }
        }
    }

    private boolean doesUserExist(final Map<Long, EmployeeDTO> employees, final Long id) {
        if (null != employees && employees.size() > 0) {
            return employees.containsKey(id);
        }
        return false;
    }
}