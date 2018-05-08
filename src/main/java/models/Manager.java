package models;

import exceptions.InvalidAnnualPerformanceValueException;

public class Manager extends Employee {

    public Manager(EmployeeParameters employeeParameters) throws InvalidAnnualPerformanceValueException {
        super(employeeParameters);
    }

    @Override
    public int jobTitleMultiplier() {
        return 3;
    }

    @Override
    boolean isAllowedToParticipate(Company company) {
        return true;
    }
}
