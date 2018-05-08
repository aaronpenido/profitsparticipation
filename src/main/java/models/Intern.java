package models;

import exceptions.InvalidAnnualPerformanceValueException;

public class Intern extends Employee {

    Intern(EmployeeParameters employeeParameters) throws InvalidAnnualPerformanceValueException {
        super(employeeParameters);
    }

    @Override
    int jobTitleMultiplier() {
        return 1;
    }

    @Override
    boolean isAllowedToParticipate(Company company) {
        return company.isInternAllowedToParticipate();
    }
}
