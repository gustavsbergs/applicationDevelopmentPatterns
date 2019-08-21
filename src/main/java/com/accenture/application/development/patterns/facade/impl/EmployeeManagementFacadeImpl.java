package main.java.com.accenture.application.development.patterns.facade.impl;

import main.java.com.accenture.application.development.patterns.domain.Employee;
import main.java.com.accenture.application.development.patterns.domain.Entity;
import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;
import main.java.com.accenture.application.development.patterns.facade.EmployeeManagementFacade;
import main.java.com.accenture.application.development.patterns.factory.EntityFactory;
import main.java.com.accenture.application.development.patterns.mapper.DTOToEmployeeMapper;
import main.java.com.accenture.application.development.patterns.mapper.EmployeeToDTOMapper;
import main.java.com.accenture.application.development.patterns.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.Map;

public class EmployeeManagementFacadeImpl implements EmployeeManagementFacade {

    private EmployeeRepository repository;

    private EntityFactory factory;

    private EmployeeToDTOMapper employeeToDTOMapper;

    private DTOToEmployeeMapper dtoToEmployeeMapper;

    public EmployeeManagementFacadeImpl(final EmployeeRepository repository, final EntityFactory factory,
                                        final EmployeeToDTOMapper employeeToDTOMapper, final DTOToEmployeeMapper dtoToEmployeeMapper) {
        this.repository = repository;
        this.factory = factory;
        this.employeeToDTOMapper = employeeToDTOMapper;
        this.dtoToEmployeeMapper = dtoToEmployeeMapper;
    }

    @Override
    public Entity createEmployee() {
        final Entity employee = factory.createEntity("Employee", repository.getAmountOfEmployees());
        repository.saveEmployee((Employee) employee);
        return employee;
    }

    @Override
    public EmployeeDTO getEmployee(final Long id) {
        final Employee returnedEmployee = repository.getEmployee(id);
        if (returnedEmployee == null) {
            return null;
        }
        return employeeToDTOMapper.map(returnedEmployee);
    }

    @Override
    public Map<Long, EmployeeDTO> getAllEmployees() {
        final Map<Long, Employee> employees = repository.getAllEmployees();
        final Map<Long, EmployeeDTO> mappedEmployees = new HashMap<>();
        employees.forEach((id, employee) -> mappedEmployees.put(id, employeeToDTOMapper.map(employee)));
        return mappedEmployees;
    }

    @Override
    public Employee deleteEmployee(final Long id) {
        return repository.deleteEmployee(id);
    }

    @Override
    public Employee updateEmployee(final EmployeeDTO employee) {
        return repository.updateEmployee(dtoToEmployeeMapper.map(employee));
    }

    @Override
    public Integer amountOfEmployees() {
        return repository.getAmountOfEmployees();
    }
}
