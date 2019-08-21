package main.java.com.accenture.application.development.patterns.handlers.impl.Iterators;

import main.java.com.accenture.application.development.patterns.constants.Positions;
import main.java.com.accenture.application.development.patterns.constants.Seniority;
import main.java.com.accenture.application.development.patterns.dto.EmployeeDTO;

import java.util.Map;

/**
 * Behavioral pattern - Template Method
 * */

public abstract class EmployeeSorter {

    final public Map<Long, EmployeeDTO> sortEmployees(final Map<Long, EmployeeDTO> entityMap, final Positions position, final Seniority seniority) {
        if (shouldSortByPosition()) {
            return sortByPosition(entityMap, position);
        } else if (shouldSortByLevel()) {
            return sortByLevel(entityMap, seniority);
        }
        return null;
    }

    abstract Map<Long, EmployeeDTO> sortByPosition(final Map<Long, EmployeeDTO> entityMap, final Positions position);

    abstract Map<Long, EmployeeDTO> sortByLevel(final Map<Long, EmployeeDTO> entityMap, final Seniority seniority);

    boolean shouldSortByPosition() {
        return true;
    }

    boolean shouldSortByLevel() {
        return true;
    }

}

