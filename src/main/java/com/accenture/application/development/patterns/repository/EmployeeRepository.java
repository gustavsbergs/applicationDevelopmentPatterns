package main.java.com.accenture.application.development.patterns.repository;

import main.java.com.accenture.application.development.patterns.domain.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {

    private Map<Long, Employee> employees = new HashMap<>();
    private Integer employeeIdCount = 0;

    public void saveEmployee(final Employee employee) {
        employeeIdCount++;
        employees.put(employee.getId(), employee);
    }

    public Employee getEmployee(final Long id) {
        return employees.get(id);
    }

    public Map<Long, Employee> getAllEmployees() {
        return employees;
    }

    public Employee updateEmployee(final Employee employee) {
        return employees.replace(employee.getId(), employee);
    }

    public Employee deleteEmployee(final Long id) {
        return employees.remove(id);
    }

    public Integer getAmountOfEmployees() {
        return employeeIdCount;
    }
}
