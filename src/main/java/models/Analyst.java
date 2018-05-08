package models;

import exceptions.InvalidAnnualPerformanceValueException;

public class Analyst extends Employee {

    Analyst(EmployeeParameters employeeParameters) throws InvalidAnnualPerformanceValueException {
        super(employeeParameters);
    }

    @Override
    public int jobTitleMultiplier() {
        return 2;
    }

    @Override
    boolean isAllowedToParticipate(Company company) {
        return true;
    }
}
