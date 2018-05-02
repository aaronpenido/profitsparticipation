package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOManager;

public abstract class Employee {

    private final IOManager ioManager;
    private int annualPerformanceValue;

    Employee(IOManager ioManager) throws InvalidAnnualPerformanceValueException {

        try {
            this.ioManager = ioManager;
            writeAnnualPerformanceValueMessage();
            this.annualPerformanceValue = Integer.parseInt(ioManager.read());

            throwInvalidAnnualPerformanceValueExceptionIfValueRangeIsInvalid();
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidAnnualPerformanceValueException();
        }
    }

    private void throwInvalidAnnualPerformanceValueExceptionIfValueRangeIsInvalid() throws InvalidAnnualPerformanceValueException {

        if(this.annualPerformanceValue < 1 || this.annualPerformanceValue > 5) {
            throw new InvalidAnnualPerformanceValueException();
        }
    }

    int getAnnualPerformanceValue() {
        return annualPerformanceValue;
    }

    private void writeAnnualPerformanceValueMessage() {
        ioManager.write("Please inform the employee's annual performance value:");
    }

    abstract int jobTitleMultiplier();

    abstract boolean isAllowedToParticipate(Company company);
}
