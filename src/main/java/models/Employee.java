package models;

import exceptions.InvalidAnnualPerformanceValueException;
import models.io.IOReader;
import models.io.IOWriter;

public abstract class Employee {

    private IOWriter ioWriter;
    private int annualPerformanceValue;

    Employee(IOReader ioReader, IOWriter ioWriter) throws InvalidAnnualPerformanceValueException {

        try {
            this.ioWriter = ioWriter;

            writeAnnualPerformanceValueMessage();
            this.annualPerformanceValue = Integer.parseInt(ioReader.read());

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
        ioWriter.write("Please inform the employee's annual performance value:");
    }

    abstract int jobTitleMultiplier();

    abstract boolean isAllowedToParticipate(Company company);
}
