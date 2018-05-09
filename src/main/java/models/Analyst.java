package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.company.Company;

public class Analyst extends Employee {

    Analyst(EmployeeParameters employeeParameters) throws InvalidAnnualPerformanceValueException {
        super(employeeParameters);
    }

    @Override
    public int jobTitleMultiplier() {
        return 2;
    }

    @Override
    public boolean isAllowedToParticipate(Company company) {
        return true;
    }
}
