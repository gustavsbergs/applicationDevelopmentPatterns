package main.java.com.accenture.application.development.patterns.handlers.impl.Iterators;

import main.java.com.accenture.application.development.patterns.constants.Positions;
import main.java.com.accenture.application.development.patterns.constants.Seniority;
import main.java.com.accenture.application.development.patterns.domain.Employee;
import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Behavioral patter - Iterator pattern
 */
public class EmployeeByLevelSorter extends EmployeeSorter {


    @Override
    boolean shouldSortByPosition() {
        return false;
    }

    @Override
    Map<Long, EmployeeDTO> sortByPosition(Map<Long, EmployeeDTO> entityMap, Positions position) {
        return null;
    }

    @Override
    Map<Long, EmployeeDTO> sortByLevel(Map<Long, EmployeeDTO> entityMap, Seniority seniority) {
        Map<Long, EmployeeDTO> sortedByLevel = new HashMap<>();
        entityMap.forEach((id, employee) -> {
            if (employee.getLevel() == seniority) {
                sortedByLevel.put(id, employee);
            }
        });
        return sortedByLevel;

    }
}
