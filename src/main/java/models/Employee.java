package models;

import exceptions.InvalidAnnualPerformanceValueException;

public abstract class Employee {

    private int annualPerformanceValue;

    Employee(EmployeeParameters employeeParameters) throws InvalidAnnualPerformanceValueException {
        this.annualPerformanceValue = employeeParameters.readAnnualPerformanceValue();
        throwInvalidAnnualPerformanceValueExceptionIfValueRangeIsInvalid();
    }

    private void throwInvalidAnnualPerformanceValueExceptionIfValueRangeIsInvalid() throws InvalidAnnualPerformanceValueException {
        if (this.annualPerformanceValue < 1 || this.annualPerformanceValue > 5) {
            throw new InvalidAnnualPerformanceValueException();
        }
    }

    int getAnnualPerformanceValue() {
        return annualPerformanceValue;
    }

    abstract int jobTitleMultiplier();

    abstract boolean isAllowedToParticipate(Company company);
}
