package main.java.com.accenture.application.development.patterns.handlers.impl;

import main.java.com.accenture.application.development.patterns.constants.RequestTypes;
import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;
import main.java.com.accenture.application.development.patterns.facade.EmployeeManagementFacade;
import main.java.com.accenture.application.development.patterns.factory.impl.EmployeeFactory;
import main.java.com.accenture.application.development.patterns.handlers.RequestHandler;
import main.java.com.accenture.application.development.patterns.mapper.EmployeeToDTOMapper;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ViewAllRequestHandler implements RequestHandler {

    private EmployeeManagementFacade facade;
    private EmployeeFactory factory;
    private EmployeeToDTOMapper mapper;
    private boolean fullyRandomMode;

    public ViewAllRequestHandler(final EmployeeManagementFacade facade, final EmployeeFactory factory, final EmployeeToDTOMapper mapper, final boolean fullyRandomMode) {
        this.facade = facade;
        this.factory = factory;
        this.mapper = mapper;
        this.fullyRandomMode = fullyRandomMode;
    }

    @Override
    public void handleRequest(final RequestTypes type) throws InterruptedException {
        if(type == RequestTypes.VIEW_ALL) {
            final Map<Long, EmployeeDTO> employees = facade.getAllEmployees();
            System.out.println();
            System.out.println("Populating employees list. Please wait...");
            TimeUnit.SECONDS.sleep(2);
            createTopRow();
            employees.forEach((id, employee) -> createEmployeeRow(employee));
            createBottomRow();
            TimeUnit.SECONDS.sleep(2);
        } else {
            RequestHandler nextHandler = new DeleteRequestHandler(facade, fullyRandomMode, mapper, factory);
            nextHandler.handleRequest(type);
        }
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
