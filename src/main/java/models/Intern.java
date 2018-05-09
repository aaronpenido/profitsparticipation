package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.company.Company;

public class Intern extends Employee {

    Intern(EmployeeParameters employeeParameters) throws InvalidAnnualPerformanceValueException {
        super(employeeParameters);
    }

    @Override
    public int jobTitleMultiplier() {
        return 1;
    }

    @Override
    public boolean isAllowedToParticipate(Company company) {
        return company.isInternAllowedToParticipate();
    }
}
