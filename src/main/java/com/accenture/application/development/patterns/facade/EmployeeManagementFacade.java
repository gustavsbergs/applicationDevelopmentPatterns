package main.java.com.accenture.application.development.patterns.facade;

import main.java.com.accenture.application.development.patterns.domain.Entity;
import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;

import java.util.Map;

/**
 * Structural pattern - Facade pattern. In my application, the facade is responsible only for handling employee management i.e CRUD operations.
 */
public interface EmployeeManagementFacade {

    Entity createEmployee();

    EmployeeDTO getEmployee(Long id);

    Map<Long, EmployeeDTO> getAllEmployees();

    Entity deleteEmployee(Long id);

    Entity updateEmployee(EmployeeDTO employee);

    Integer amountOfEmployees();
}
