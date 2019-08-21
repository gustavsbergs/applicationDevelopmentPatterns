package main.java.com.accenture.application.development.patterns.handlers.impl;

import main.java.com.accenture.application.development.patterns.constants.Positions;
import main.java.com.accenture.application.development.patterns.constants.RequestTypes;
import main.java.com.accenture.application.development.patterns.constants.Seniority;
import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;
import main.java.com.accenture.application.development.patterns.facade.EmployeeManagementFacade;
import main.java.com.accenture.application.development.patterns.factory.EntityFactory;
import main.java.com.accenture.application.development.patterns.handlers.RequestHandler;
import main.java.com.accenture.application.development.patterns.handlers.impl.Iterators.EmployeeByLevelSorter;
import main.java.com.accenture.application.development.patterns.handlers.impl.Iterators.EmployeeByPositionSorter;
import main.java.com.accenture.application.development.patterns.handlers.impl.Iterators.EmployeeSorter;
import main.java.com.accenture.application.development.patterns.mapper.EmployeeToDTOMapper;
import main.java.com.accenture.application.development.patterns.util.RandomNumberGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.requireNonNull;

public class ViewAllRequestHandler implements RequestHandler {

    private EmployeeManagementFacade facade;
    private EntityFactory factory;
    private EmployeeToDTOMapper mapperToDTO;
    private boolean fullyRandomMode;
    private Scanner userInput = new Scanner(System.in);
    private EmployeeSorter sorter;
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    ViewAllRequestHandler(final EmployeeManagementFacade facade, final EntityFactory factory, final EmployeeToDTOMapper mapperToDTO, final boolean fullyRandomMode) {
        this.facade = facade;
        this.factory = factory;
        this.mapperToDTO = mapperToDTO;
        this.fullyRandomMode = fullyRandomMode;
    }

    @Override
    public void handleRequest(final RequestTypes type) throws InterruptedException {
        if (type == RequestTypes.VIEW_ALL) {
            boolean processRunning = true;
            do {
                System.out.println("Do you want to sort employees by position or level? 1. Yes 2. No");
                final Integer choice;

                if (fullyRandomMode) {
                    choice = randomNumberGenerator.generate(2);
                } else {
                    choice = userInput.nextInt();
                }
                if (choice == 2) {
                    final Map<Long, EmployeeDTO> employees = facade.getAllEmployees();
                    showEmployeeList(employees);
                    processRunning = false;
                } else if (choice == 1) {
                    System.out.println("Which criteria do you want to search by? 1.Position 2.Level");
                    Integer searchChoice;
                    if (fullyRandomMode) {
                        searchChoice = randomNumberGenerator.generate(2);
                    } else {
                        searchChoice = userInput.nextInt();
                    }
                    TimeUnit.SECONDS.sleep(2);
                    if (searchChoice == 1) {
                        System.out.println("Pick position to search by. 1. Executive_manager 2. Director 3.Manager 4. Staff");
                        Integer positionChoice;
                        if (fullyRandomMode) {
                            positionChoice = randomNumberGenerator.generate(4);
                        } else {
                            positionChoice = userInput.nextInt();
                        }
                        TimeUnit.SECONDS.sleep(2);
                        showEmployeeListByPosition(positionChoice);
                        processRunning = false;
                    } else if (searchChoice == 2) {
                        System.out.println("Pick level to search by. 1. Associate 2.Intermediate 3.Senior");
                        Integer levelChoice;
                        if (fullyRandomMode) {
                            levelChoice = randomNumberGenerator.generate(3);
                        } else {
                            levelChoice = userInput.nextInt();
                        }
                        TimeUnit.SECONDS.sleep(2);
                        showEmployeeListByLevel(levelChoice);
                        processRunning = false;
                    } else {
                        System.out.println("Invalid input. Try again.");
                        TimeUnit.SECONDS.sleep(1);
                    }
                } else {
                    System.out.println("Invalid input. Try again.");
                    TimeUnit.SECONDS.sleep(1);
                }
            } while (processRunning);

        } else {
            final RequestHandler nextHandler = new DeleteRequestHandler(facade, fullyRandomMode, mapperToDTO, factory);
            nextHandler.handleRequest(type);
        }
    }

    private void showEmployeeListByPosition(final Integer positionChoice) throws InterruptedException {
        final Map<Integer, Positions> choices = new HashMap<>();
        choices.put(1, Positions.EXECUTIVE_MANAGER);
        choices.put(2, Positions.DIRECTOR);
        choices.put(3, Positions.MANAGER);
        choices.put(4, Positions.STAFF);
        final Map<Long, EmployeeDTO> employeesToSort = facade.getAllEmployees();
        sorter = new EmployeeByPositionSorter();
        showEmployeeList(requireNonNull(sorter.sortEmployees(employeesToSort, choices.get(positionChoice), null)));
    }

    private void showEmployeeListByLevel(final Integer levelChoice) throws InterruptedException {
        final Map<Integer, Seniority> choices = new HashMap<>();
        choices.put(1, Seniority.ASSOCIATE);
        choices.put(2, Seniority.INTERMEDIATE);
        choices.put(3, Seniority.SENIOR);
        final Map<Long, EmployeeDTO> employeesToSort = facade.getAllEmployees();
        sorter = new EmployeeByLevelSorter();
        showEmployeeList(requireNonNull(sorter.sortEmployees(employeesToSort, null, choices.get(levelChoice))));
    }

    private void showEmployeeList(final Map<Long, EmployeeDTO> employees) throws InterruptedException {
        if (employees.isEmpty()) {
            System.out.println("No employees were found");
            TimeUnit.SECONDS.sleep(2);
            return;
        }
        System.out.println();
        System.out.println("Populating employees list. Please wait...");
        TimeUnit.SECONDS.sleep(2);
        createTopRow();
        employees.forEach((id, employee) -> createEmployeeRow(employee));
        createBottomRow();
        TimeUnit.SECONDS.sleep(2);
    }

    private void createEmployeeRow(final EmployeeDTO employee) {
        System.out.println("  " + employee.getEmployeeId() + "    " + employee.getName() + "    " + employee.getSurname() + "    " + employee.getPosition() + "   " + employee.getLevel() + "   " + employee.getSalary());
    }

    private void createBottomRow() {
        System.out.println("#################################################################");
        System.out.println();
    }

    private void createTopRow() {
        System.out.println("#################################################################");
        System.out.println("  ID   Name    Surname      Position       Level       Salary  ");
    }
}
