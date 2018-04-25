package utils;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.*;

public class EmployeeBuilder {

    private IOManager ioManager;

    public EmployeeBuilder(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    public Employee build() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        writeJobTitleMessage();
        JobTitle jobTitle = readJobTitle();

        return employeeFromJobTitle(jobTitle);
    }

    private Employee employeeFromJobTitle(final JobTitle jobTitle)
            throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

        switch (jobTitle) {
            case ANALYST:
                return new Analyst(ioManager);
            case MANAGER:
                return new Manager(ioManager);
            case TRAINEE:
                return new Trainee(ioManager);
            default:
                throw new InvalidJobTitleException();
        }
    }

    private JobTitle readJobTitle() throws InvalidJobTitleException {
        return JobTitle.jobTitleFromString(ioManager.read());
    }

    private void writeJobTitleMessage() {
        ioManager.write("Please inform the job title:");
    }
}
