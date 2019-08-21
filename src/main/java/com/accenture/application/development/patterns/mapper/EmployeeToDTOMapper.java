package main.java.com.accenture.application.development.patterns.mapper;

import main.java.com.accenture.application.development.patterns.domain.Employee;
import main.java.com.accenture.application.development.patterns.domain.Entity;
import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;

public class EmployeeToDTOMapper {

    public EmployeeDTO map(final Employee employee) {
        final EmployeeDTO employeeDTO = new EmployeeDTO();
        if (employee != null) {
            employeeDTO.setEmployeeId(employee.getId())
                    .setLevel(employee.getLevel())
                    .setName(employee.getName())
                    .setSurname(employee.getSurname())
                    .setPosition(employee.getPosition())
                    .setSalary(employee.getSalary());
        }
        return employeeDTO;
    }
}
