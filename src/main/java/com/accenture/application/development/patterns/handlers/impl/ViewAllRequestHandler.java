package main.java.com.accenture.application.development.patterns.handlers.impl;

import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;
import main.java.com.accenture.application.development.patterns.facade.EmployeeManagementFacade;
import main.java.com.accenture.application.development.patterns.handlers.RequestHandler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ViewAllRequestHandler implements RequestHandler {

    private EmployeeManagementFacade facade;

    public ViewAllRequestHandler(final EmployeeManagementFacade facade) {
        this.facade = facade;
    }

    @Override
    public void handleRequest() throws InterruptedException {
        final Map<Long, EmployeeDTO> employees = facade.getAllEmployees();
        System.out.println();
        System.out.println("Populating employees list. Please wait...");
        TimeUnit.SECONDS.sleep(2);
        createTopRow();
        employees.forEach((id, employee) -> createEmployeeRow(employee));
        createBottomRow();
        TimeUnit.SECONDS.sleep(2);
    }

    private void createEmployeeRow(EmployeeDTO employee) {
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
