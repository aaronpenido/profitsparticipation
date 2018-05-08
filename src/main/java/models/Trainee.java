package models;

import exceptions.InvalidAnnualPerformanceValueException;

public class Trainee extends Employee {

    public Trainee(EmployeeParameters employeeParameters) throws InvalidAnnualPerformanceValueException {
        super(employeeParameters);
    }

    @Override
    public int jobTitleMultiplier() {
        return 1;
    }

    @Override
    boolean isAllowedToParticipate(Company company) {
        return true;
    }
}
