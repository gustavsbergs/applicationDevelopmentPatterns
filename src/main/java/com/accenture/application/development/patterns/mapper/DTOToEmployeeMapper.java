package main.java.com.accenture.application.development.patterns.mapper;

import main.java.com.accenture.application.development.patterns.domain.Employee;
import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;

public class DTOToEmployeeMapper {

    public Employee map(final EmployeeDTO dto) {
        Employee employee = new Employee();

        if (dto != null) {
            employee.setSalary(dto.getSalary())
                    .setLevel(dto.getLevel())
                    .setPosition(dto.getPosition())
                    .setName(dto.getName())
                    .setSurname(dto.getSurname())
                    .setEmployeeId(dto.getEmployeeId());
        }
        return employee;
    }
}
