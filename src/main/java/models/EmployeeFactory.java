package models;

import exceptions.InvalidAnnualPerformanceValueException;
import exceptions.InvalidJobTitleException;
import models.enums.JobTitle;
import models.io.IOManager;

public class EmployeeFactory {

    private IOManager ioManager;

    public EmployeeFactory(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    public Employee getEmployee() throws InvalidJobTitleException, InvalidAnnualPerformanceValueException {

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
        return jobTitleFromString(ioManager.read());
    }

    private JobTitle jobTitleFromString(String jobTitleString) throws InvalidJobTitleException {

        try {
            return JobTitle.valueOf(jobTitleString.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new InvalidJobTitleException();
        }
    }

    private void writeJobTitleMessage() {
        ioManager.write("Please inform the job title:");
    }
}
