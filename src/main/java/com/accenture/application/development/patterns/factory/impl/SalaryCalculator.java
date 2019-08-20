package main.java.com.accenture.application.development.patterns.factory.impl;

import main.java.com.accenture.application.development.patterns.constants.Positions;
import main.java.com.accenture.application.development.patterns.constants.Seniority;

import java.util.HashMap;
import java.util.Map;

public class SalaryCalculator {

    private static final Map<Positions, Map<Seniority, Double>> SALARY_LEVELS = new HashMap<Positions, Map<Seniority, Double>>() {{
        put(Positions.EXECUTIVE_MANAGER, new HashMap<Seniority, Double>() {{
            put(Seniority.SENIOR, 8000D);
            put(Seniority.ASSOCIATE, 7000D);
        }});
        put(Positions.DIRECTOR, new HashMap<Seniority, Double>() {{
            put(Seniority.SENIOR, 6000D);
            put(Seniority.ASSOCIATE, 5000D);
        }});
        put(Positions.MANAGER, new HashMap<Seniority, Double>() {{
            put(Seniority.SENIOR, 4000D);
            put(Seniority.INTERMEDIATE, 3500D);
            put(Seniority.ASSOCIATE, 3000D);
        }});
        put(Positions.STAFF, new HashMap<Seniority, Double>() {{
            put(Seniority.SENIOR, 2000D);
            put(Seniority.INTERMEDIATE, 1500D);
            put(Seniority.ASSOCIATE, 1000D);
        }});
    }};

    public Double calculateSalary(final Seniority seniority, final Positions position) {
        return SALARY_LEVELS.get(position).get(seniority);
    }

}
