package main.java.com.accenture.application.development.patterns.facade;

import main.java.com.accenture.application.development.patterns.domain.Employee;
import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;

import java.util.Map;

public interface EmployeeManagementFacade {

    Employee createEmployee();

    EmployeeDTO getEmployee(Long id);

    Map<Long, EmployeeDTO> getAllEmployees();

    Employee deleteEmployee(Long id);

    Employee updateEmployee(EmployeeDTO employee);

    Integer amountOfEmployees();
}
