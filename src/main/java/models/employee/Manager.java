package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import models.company.Company;

public class Manager extends Employee {

    public Manager(EmployeeParametersReader employeeParametersReader) throws InvalidAnnualPerformanceValueException {
        super(employeeParametersReader);
    }

    @Override
    public int jobTitleMultiplier() {
        return 3;
    }

    @Override
    public boolean isAllowedToParticipate(Company company) {
        return true;
    }
}
