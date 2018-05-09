package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.company.Company;

public class Trainee extends Employee {

    public Trainee(EmployeeParameters employeeParameters) throws InvalidAnnualPerformanceValueException {
        super(employeeParameters);
    }

    @Override
    public int jobTitleMultiplier() {
        return 1;
    }

    @Override
    public boolean isAllowedToParticipate(Company company) {
        return true;
    }
}
