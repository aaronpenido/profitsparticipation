package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import models.company.Company;

public class Intern extends Employee {

    public Intern(EmployeeParametersReader employeeParametersReader) throws InvalidAnnualPerformanceValueException {
        super(employeeParametersReader);
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
