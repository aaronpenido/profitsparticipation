package models.employee;

import exceptions.InvalidAnnualPerformanceValueException;
import models.company.Company;

public abstract class Employee {

    private int annualPerformanceValue;

    Employee(EmployeeParametersReader employeeParametersReader) throws InvalidAnnualPerformanceValueException {
        this.annualPerformanceValue = employeeParametersReader.readAnnualPerformanceValue();
        throwInvalidAnnualPerformanceValueExceptionIfValueRangeIsInvalid();
    }

    private void throwInvalidAnnualPerformanceValueExceptionIfValueRangeIsInvalid() throws InvalidAnnualPerformanceValueException {
        if (this.annualPerformanceValue < 1 || this.annualPerformanceValue > 5) {
            throw new InvalidAnnualPerformanceValueException();
        }
    }

    public int getAnnualPerformanceValue() {
        return annualPerformanceValue;
    }

    public abstract int jobTitleMultiplier();

    public abstract boolean isAllowedToParticipate(Company company);
}
