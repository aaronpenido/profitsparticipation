package models;

import exceptions.InvalidAnnualPerformanceValueException;
import utils.IOManager;

public abstract class Employee {

    private final IOManager ioManager;
    private int annualPerformanceValue;

    public Employee(IOManager ioManager) throws InvalidAnnualPerformanceValueException {

        try {
            this.ioManager = ioManager;
            writeAnnualPerformanceValueMessage();
            this.annualPerformanceValue = Integer.parseInt(ioManager.read());
        } catch (NumberFormatException numberFormatException) {
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
}
